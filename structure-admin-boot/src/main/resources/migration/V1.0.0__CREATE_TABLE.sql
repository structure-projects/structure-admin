--
-- 表的结构 `config`
--
CREATE TABLE IF NOT EXISTS `config`
(
    `id`              bigint(20)   NOT NULL COMMENT ' 主键ID ',
    `code`            varchar(64)  NOT NULL COMMENT '配置键',
    `value`           varchar(255) NOT NULL COMMENT '配置值',
    `remark` varchar(255) DEFAULT NULL COMMENT '备注',
    `organization_id` bigint(64)   NOT NULL COMMENT '组织ID',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统全局配置表';
--
-- 表的结构 `menu`
--
CREATE TABLE IF NOT EXISTS `menu`
(
    `id`              bigint(20)                                                    NOT NULL COMMENT '主键',
    `code`            varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '功能编号',
    `parent_id`       bigint                                                        NULL     DEFAULT NULL COMMENT '父菜单ID',
    `type`            tinyint                                                       NULL     DEFAULT NULL COMMENT '菜单类型(1：菜单；2：目录；3：外链；4：按钮)',
    `name`            varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT '' COMMENT '菜单名称',
    `path`            varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT '' COMMENT '路由路径(浏览器地址栏路径)',
    `component`       varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '组件路径(vue页面完整路径，省略.vue后缀)',
    `perm`            varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '按钮权限标识',
    `icon`            varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT '' COMMENT '菜单图标',
    `sort`            int                                                           NULL     DEFAULT 0 COMMENT '排序',
    `visible`         tinyint(1)                                                    NULL     DEFAULT 1 COMMENT '状态(0:禁用;1:开启)',
    `redirect`        varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT '' COMMENT '跳转路径',
    `tree_path`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL,
    `always_show`     tinyint(1)                                                    NULL     DEFAULT NULL COMMENT '【目录】只有一个子路由是否始终显示(1:是 0:否)',
    `keep_alive`      tinyint(1)                                                    NULL     DEFAULT NULL COMMENT '【菜单】是否开启页面缓存(1:是 0:否)',
    `is_enabled`      tinyint(1)                                                    NOT NULL DEFAULT '1' COMMENT '是否启用 0 否 ，1 是',
    `is_deleted`      tinyint(1)                                                    NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 ，1 是',
    `create_time`     datetime                                                               DEFAULT NULL COMMENT '创建时间',
    `create_by`       bigint(20)                                                             DEFAULT NULL COMMENT '创建人',
    `update_time`     datetime                                                               DEFAULT NULL COMMENT '更新时间',
    `update_by`       bigint(20)                                                             DEFAULT NULL COMMENT '更新人',
    `organization_id` bigint(20)                                                    NOT NULL COMMENT '组织ID',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='功能菜单表';

--
-- 表的结构 `dict_category`
--
CREATE TABLE IF NOT EXISTS `dict_category`
(
    `id`              bigint(20)   NOT NULL COMMENT '主键',
    `name`            varchar(128) NOT NULL COMMENT '名称',
    `code`            varchar(64)  NOT NULL COMMENT '编码',
    `remark`          varchar(255)          DEFAULT NULL COMMENT '备注',
    `is_enabled`      tinyint(1)   NOT NULL DEFAULT '1' COMMENT '是否启用 0 否 ，1 是',
    `is_deleted`      tinyint(1)   NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 ，1 是',
    `create_time`     datetime              DEFAULT NULL COMMENT '创建时间',
    `create_by`       bigint(20)            DEFAULT NULL COMMENT '创建人',
    `update_time`     datetime              DEFAULT NULL COMMENT '更新时间',
    `update_by`       bigint(20)            DEFAULT NULL COMMENT '更新人',
    `organization_id` bigint(20)   NOT NULL COMMENT '组织ID',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='字典分类表';
--
-- 表的结构 `dict_item`
--
CREATE TABLE IF NOT EXISTS `dict_item`
(
    `id`              bigint(20)  NOT NULL COMMENT '主键',
    `code`            varchar(64) NOT NULL COMMENT '编码',
    `name`            varchar(128)         DEFAULT NULL COMMENT '名称',
    `sort`            int,
    `value`           text        NOT NULL COMMENT '值',
    `is_enabled`      tinyint(1)  NOT NULL DEFAULT '1' COMMENT '是否启用 0 否 ，1 是',
    `is_deleted`      tinyint(1)  NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 ，1 是',
    `create_time`     datetime             DEFAULT NULL COMMENT '创建时间',
    `create_by`       bigint(20)           DEFAULT NULL COMMENT '创建人',
    `update_time`     datetime             DEFAULT NULL COMMENT '更新时间',
    `update_by`       bigint(20)           DEFAULT NULL COMMENT '更新人',
    `organization_id` bigint(20)  NOT NULL COMMENT '机构ID',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='字典项';

-- --------------------------------------------------------
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`
(
    `id`              bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`            varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT '' COMMENT '部门名称',
    `parent_id`       bigint                                                        NULL     DEFAULT 0 COMMENT '父节点id',
    `tree_path`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT '' COMMENT '父节点id路径',
    `sort`            int                                                        NULL     DEFAULT 0 COMMENT '显示顺序',
    `is_enabled`      tinyint(1)                                                    NOT NULL DEFAULT '1' COMMENT '是否启用 1:  启用 0:未启用',
    `is_deleted`      tinyint(1)                                                    NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 ，1 是',
    `create_time`     datetime                                                               DEFAULT NULL COMMENT '创建时间',
    `create_by`       bigint(20)                                                             DEFAULT NULL COMMENT '创建人',
    `update_time`     datetime                                                               DEFAULT NULL COMMENT '更新时间',
    `update_by`       bigint(20)                                                             DEFAULT NULL COMMENT '更新人',
    `organization_id` bigint(20)                                                    NOT NULL COMMENT '组织ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '部门表'
  ROW_FORMAT = DYNAMIC;

CREATE TABLE IF NOT EXISTS `member`
(
    `id`              bigint(20)  NOT NULL COMMENT '成员ID',
    `user_id`         bigint(20)  NOT NULL COMMENT '用户ID',
    `phone`           varchar(64) NOT NULL COMMENT '成员手机号',
    `name`            varchar(64)          DEFAULT NULL COMMENT '姓名',
    `sex`             char(1)              DEFAULT NULL COMMENT '性别,N 未知,M 男 ,F 女',
    `dept_id`         bigint(20)  NOT NULL COMMENT '部门id',
    `state`           tinyint(2) NOT NULL COMMENT '成员状态',
    `is_deleted`      tinyint(1)  NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 ，1 是',
    `create_time`     datetime             DEFAULT NULL COMMENT '创建时间',
    `create_by`       bigint(20)           DEFAULT NULL COMMENT '创建人',
    `update_time`     datetime             DEFAULT NULL COMMENT '更新时间',
    `update_by`       bigint(20)           DEFAULT NULL COMMENT '更新人',
    `organization_id` bigint(20)  NOT NULL COMMENT '组织ID',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='成员表';