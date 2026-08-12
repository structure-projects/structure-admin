# structure-admin 系统管理平台

脚手架集成管理平台，提供系统公告、字典、配置、验证码、文件等基础管理能力，并集成用户体系与多租户能力。采用多模块分层架构组织（core / common / biz / boot / cloud）。

- **GroupId / ArtifactId**：`cn.structured:structure-admin`
- **版本**：`2.0.0-SNAPSHOT`
- **父 POM**：`cn.structured:structure-dependencies:1.5.0`（根 POM 同时作为聚合模块）
- **基础包路径**：`cn.structured.admin`

## 技术栈

| 组件 | 版本 | 说明 |
| --- | --- | --- |
| Spring Boot | 4.0.6 | 主框架（Jakarta EE） |
| JDK | 17 | 编译与运行 |
| Spring Cloud | 2025.1.0 | 微服务套件 |
| Spring Cloud Alibaba | 2025.1.0.0 | Nacos 注册/配置中心 |
| MyBatis-Plus | 3.5.16 | ORM（`mybatis-plus-spring-boot4-starter`） |
| SpringDoc OpenAPI | - | 接口文档（版本由父 BOM 管理） |
| Flyway | - | 数据库版本迁移 |
| Redis | - | 缓存 / 验证码 |
| Hutool | - | 通用工具集 |
| MySQL Connector | 8.3.0 | MySQL 驱动 |
| structure-security | 1.1.5 | 安全鉴权（jwt-starter / resource-starter） |
| structure-user | 2.0.0-SNAPSHOT | 用户中心集成 |

## 模块结构（多模块分层）

```
structure-admin                         # 根 POM，聚合与依赖管理
├── structure-admin-core    # 核心：实体、Mapper、Service、Manager、自动配置（公告/字典/配置/用户）
├── structure-admin-common  # 公共：DTO/VO/枚举/AOP 注解/工具
├── structure-admin-biz     # 业务：Controller、业务 Service（验证码、文件端点）、配置
├── structure-admin-boot    # 启动模块：AdminApplication，登录端点、JWT 配置、用户管理
└── structure-admin-cloud   # 云模块：AdminCloudApplication，Feign 客户端、云环境用户管理
```

依赖方向：`common → core`，`biz → core + common`，`boot → biz + core`，`cloud → biz + core`。`boot` 为单体启动入口，`cloud` 为微服务（Nacos）启动入口。

## 环境要求

- JDK 17
- Maven 3.9+
- MySQL 8.0+
- Redis 7+
- Nacos 2.x（`cloud` 模块注册与配置中心）

## 快速启动

单体模式（admin-service）：

```bash
cd structure-admin-boot
mvn clean package -DskipTests
java -jar target/admin-service.jar
```

微服务模式（接入 Nacos）：

```bash
cd structure-admin-cloud
mvn clean package -DskipTests
java -jar target/admin-cloud.jar
```

默认激活 `dev` profile，从 classpath 加载 `admin-service.yaml`；生产环境使用 `pro` profile 并从 Nacos 拉取配置。数据库结构由 Flyway 自动迁移。

## 端口配置

- 服务名：`admin-service`
- 默认端口：`8080`
- `dev` 环境端口：`18110`
- `pro` 环境管理端口：`7777`
- 接口文档：`http://localhost:18110/swagger-ui.html`，API 分组 `admin`
