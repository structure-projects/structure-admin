# Structure Admin

脚手架集成管理平台

## 项目简介

Structure Admin 是一个基于 Spring Boot 的企业级管理后台脚手架，提供了完整的用户管理、权限控制、菜单管理、部门管理、字典管理等核心功能。

## 技术栈

- **框架**: Spring Boot
- **数据库**: MySQL 8.0+
- **ORM**: MyBatis
- **安全认证**: JWT
- **数据库迁移**: Flyway
- **API文档**: Swagger

## 模块说明

```
structure-admin/
├── structure-admin-api       # API层：DTO、VO、枚举、工具类
├── structure-admin-biz       # 业务层：配置、控制器
├── structure-admin-boot      # 启动模块：应用启动类、配置文件
├── structure-admin-cloud     # 云版本：Feign客户端、微服务支持
└── structure-admin-domain    # 领域层：实体、服务、Mapper、端点
```

## 核心功能

- **用户管理**: 用户CRUD、密码重置、成员邀请
- **权限管理**: 菜单权限、角色权限
- **菜单管理**: 树形菜单配置
- **部门管理**: 组织架构管理
- **字典管理**: 数据字典分类和项管理
- **公告管理**: 系统公告发布
- **操作审计**: 操作日志记录
- **配置管理**: 系统参数配置

## 快速开始

### 环境要求

- JDK 8+
- Maven 3.6+
- MySQL 8.0+

### 数据库配置

1. 创建数据库
2. 修改 `structure-admin-boot/src/main/resources/application-dev.yaml` 中的数据库连接配置
3. 启动项目，Flyway会自动执行数据库迁移脚本

### 运行项目

```bash
# 编译项目
mvn clean install

# 启动应用
cd structure-admin-boot
mvn spring-boot:run
```

应用默认启动在 `http://localhost:18000`

### API文档

启动项目后访问：`http://localhost:18000/swagger-ui.html`

## 项目结构说明

### structure-admin-api

- `dto/`: 数据传输对象
- `vo/`: 视图对象
- `enums/`: 枚举类
- `utils/`: 工具类
- `aop/`: 切面注解

### structure-admin-domain

- `entity/`: 数据库实体
- `service/`: 业务服务接口和实现
- `mapper/`: MyBatis Mapper接口
- `endpoint/`: REST API端点
- `aspect/`: 切面实现
- `configuration/`: 自动配置类

### structure-admin-boot

- `controller/`: 控制器
- `service/`: 服务实现
- `manager/`: 管理器实现
- `configuration/`: 配置类
- `resources/migration/`: Flyway数据库迁移脚本

## 构建部署

### Docker部署

项目提供了Dockerfile用于容器化部署：

```bash
# 构建镜像
./scripts/dockerbuild.sh

# 运行容器
docker run -p 18000:18000 structure-admin:latest
```

### 脚本说明

- `scripts/mavenbuild.sh`: Maven构建脚本
- `scripts/dockerbuild.sh`: Docker构建脚本
- `scripts/install.sh`: 安装脚本
- `scripts/release.sh`: 发布脚本
- `scripts/update-snapshots.sh`: 更新快照版本脚本

## 版本历史

- 1.0.5-SNAPSHOT: 当前开发版本

## 许可证

待补充
