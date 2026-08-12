# IAM 管理后台设计 (structure-admin)

> **所属模块**：`structure-admin` | 端口：18110 | context-path: /admin | 数据库: admin (预留)
> **引用总体设计**：`docs/OVERVIEW_DESIGN.md`
> **DDD 分层架构**：7+1 模块标准

---

## 1. 模块职责

IAM 管理后台是平台超级管理员的核心管理入口，负责系统级配置管理。

| 功能模块 | 说明 |
|---------|------|
| 字典管理 | 系统数据字典的 CRUD 与树形结构管理 |
| 配置管理 | 系统参数配置、开关控制、全局设置 |
| 公告管理 | 系统公告发布、推送、状态管理 |
| 仪表盘 | 系统运行指标、统计概览 |

---

## 2. DDD 模块结构

### 2.1 模块概览（7+1）

```
structure-admin/
├── structure-admin-common/       # DTO/VO/Query/枚举/异常
├── structure-admin-domain/       # Entity(纯POJO) + Repository接口 + DomainService
├── structure-admin-infra/        # Delegate接口 + RepositoryFacade实现
├── structure-admin-repository-mybatis/  # PO + Mapper + MybatisPlusDelegate
├── structure-admin-application/  # IXxxService接口 + ServiceImpl + Assembler
├── structure-admin-interfaces/   # Controller (REST API)
└── structure-admin-boot/         # SpringBoot 启动类 + 配置文件
```

### 2.2 包声明规范

包声明路径与物理目录一一对应（已修复早年 `core.` 前缀问题）：

```
cn.structured.admin.common       → structure-admin-common/src/main/java/cn/structured/admin/common/
cn.structured.admin.domain       → structure-admin-domain/src/main/java/cn/structured/admin/domain/
cn.structured.admin.infra        → structure-admin-infra/src/main/java/cn/structured/admin/infra/
cn.structured.admin.repository   → structure-admin-repository-mybatis/src/main/java/cn/structured/admin/repository/
cn.structured.admin.application  → structure-admin-application/src/main/java/cn/structured/admin/application/
cn.structured.admin.interfaces   → structure-admin-interfaces/src/main/java/cn/structured/admin/interfaces/
cn.structured.admin.boot         → structure-admin-boot/src/main/java/cn/structured/admin/boot/
```

### 2.3 启动类关键配置

```java
@SpringBootApplication(scanBasePackages = "cn.structured.admin")
@MapperScan("cn.structured.admin.repository.mybatis.mapper")
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
```

---

## 3. 数据库设计

### 3.1 数据库：admin（预留，当前未实现独立 DB）

当前 admin-service 主要负责系统级配置管理，使用轻量级数据存储方案。

### 3.2 核心实体（Domain 层）

| Entity | 说明 |
|--------|------|
| `Dict` | 数据字典项（编码、名称、值、父子关系） |
| `DictType` | 字典类型分类 |
| `Config` | 系统配置项（键值对 + 分类） |
| `Announcement` | 系统公告（标题、内容、类型、发布状态） |

---

## 4. API 设计

### AdminDictController — `@RequestMapping("/admin/dict")`

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/admin/dict/types` | 字典类型列表 |
| POST | `/admin/dict/type` | 创建字典类型 |
| PUT | `/admin/dict/type/{id}` | 更新字典类型 |
| DELETE | `/admin/dict/type/{id}` | 删除字典类型 |
| GET | `/admin/dict/{dictType}/items` | 指定类型的字典项列表 |
| POST | `/admin/dict/item` | 创建字典项 |
| PUT | `/admin/dict/item/{id}` | 更新字典项 |
| DELETE | `/admin/dict/item/{id}` | 删除字典项 |
| GET | `/admin/dict/{dictType}/tree` | 字典树形结构 |

### AdminConfigController — `@RequestMapping("/admin/config")`

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/admin/config` | 查询配置列表 |
| GET | `/admin/config/{key}` | 查询单个配置 |
| POST | `/admin/config` | 创建配置 |
| PUT | `/admin/config/{id}` | 更新配置 |
| DELETE | `/admin/config/{id}` | 删除配置 |
| POST | `/admin/config/refresh` | 刷新配置缓存 |

### AdminAnnouncementController — `@RequestMapping("/admin/announcement")`

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/admin/announcement/list` | 公告列表（分页） |
| GET | `/admin/announcement/{id}` | 公告详情 |
| POST | `/admin/announcement` | 发布公告 |
| PUT | `/admin/announcement/{id}` | 更新公告 |
| DELETE | `/admin/announcement/{id}` | 删除公告 |
| PUT | `/admin/announcement/{id}/publish` | 发布公告 |
| PUT | `/admin/announcement/{id}/revoke` | 撤回公告 |

### AdminDashboardController — `@RequestMapping("/admin/dashboard")`

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/admin/dashboard/overview` | 系统概览统计 |
| GET | `/admin/dashboard/users` | 用户统计 |
| GET | `/admin/dashboard/tenants` | 租户统计 |

---

## 5. 跨模块集成

### 5.1 依赖服务

| 外部服务 | 用途 |
|---------|------|
| auth-service | 管理员身份认证（JWT 验证） |
| user-service | 用户统计数据 |
| tenant-service | 租户统计数据 |
| audit-service | 记录管理操作审计 |
| message-service | 公告推送通知 |

### 5.2 事件

| 事件 | 说明 | 消费者 |
|------|------|--------|
| `admin.dict.updated` | 字典变更 | 各业务服务（缓存刷新） |
| `admin.config.updated` | 配置变更 | 各业务服务（配置热加载） |
| `admin.announcement.published` | 公告发布 | message-service（推送通知） |

---

## 6. 关键设计决策

### 6.1 管理后台独立性

- admin-service 是平台管理入口，面向超级管理员
- 不依赖租户隔离，auth-service 验证管理员角色
- 配置管理支持实时刷新（缓存失效策略）

### 6.2 字典设计

- 支持多级树形字典（父子关系）
- 字典类型与字典项分离（DictType → Dict）
- 变更时通过事件通知各服务刷新本地缓存

### 6.3 公告管理

- 支持定时发布、立即发布、撤回
- 通过 message-service 推送通知
- 公告支持类型分类（系统公告、维护通知、功能更新）

---

## 7. 待确认清单

| # | 不一致项 | 当前状态 | 建议 |
|---|---------|---------|------|
| 1 | 数据库实现 | admin DB 为预留状态，当前可能使用共享存储 | **待确认**：是否启用独立 admin DB？ |
| 2 | Flyway 迁移 | 未找到 admin 模块的 Flyway 迁移脚本 | **待确认**：是否需要 Flyway 管理 admin DB 的表结构？ |
| 3 | 配置缓存 | 配置刷新接口已定义，需确认实现方式 | **待确认**：使用 Redis 缓存还是本地缓存？ |
