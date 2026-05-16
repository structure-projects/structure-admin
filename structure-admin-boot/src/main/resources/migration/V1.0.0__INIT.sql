
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config
-- ----------------------------
CREATE TABLE `config` (
                          `id` bigint NOT NULL COMMENT ' 主键ID ',
                          `code` varchar(64) NOT NULL COMMENT '配置键',
                          `value` varchar(255) NOT NULL COMMENT '配置值',
                          `remark` varchar(255) DEFAULT NULL COMMENT '备注',
                          `organization_id` bigint NOT NULL COMMENT '组织ID',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统全局配置表';

-- ----------------------------
-- Records of config
-- ----------------------------
BEGIN;
INSERT INTO `config` (`id`, `code`, `value`, `remark`, `organization_id`) VALUES (2055526883274842114, 'test', 'test', 'ttt', 1);
COMMIT;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
CREATE TABLE `dept` (
                        `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                        `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '部门名称',
                        `parent_id` bigint DEFAULT '0' COMMENT '父节点id',
                        `tree_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '父节点id路径',
                        `sort` int DEFAULT '0' COMMENT '显示顺序',
                        `is_enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用 1:  启用 0:未启用',
                        `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 ，1 是',
                        `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                        `create_by` bigint DEFAULT NULL COMMENT '创建人',
                        `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                        `update_by` bigint DEFAULT NULL COMMENT '更新人',
                        `organization_id` bigint NOT NULL COMMENT '组织ID',
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='部门表';

-- ----------------------------
-- Records of dept
-- ----------------------------
BEGIN;
INSERT INTO `dept` (`id`, `name`, `parent_id`, `tree_path`, `sort`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (1, '研发部', 0, '0', 1, 1, 0, '2024-07-13 11:11:55', NULL, '2024-07-13 11:11:55', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for dict_category
-- ----------------------------
CREATE TABLE `dict_category` (
                                 `id` bigint NOT NULL COMMENT '主键',
                                 `name` varchar(128) NOT NULL COMMENT '名称',
                                 `code` varchar(64) NOT NULL COMMENT '编码',
                                 `remark` varchar(255) DEFAULT NULL COMMENT '备注',
                                 `is_enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用 0 否 ，1 是',
                                 `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 ，1 是',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `create_by` bigint DEFAULT NULL COMMENT '创建人',
                                 `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 `update_by` bigint DEFAULT NULL COMMENT '更新人',
                                 `organization_id` bigint NOT NULL COMMENT '组织ID',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典分类表';

-- ----------------------------
-- Records of dict_category
-- ----------------------------
BEGIN;
INSERT INTO `dict_category` (`id`, `name`, `code`, `remark`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (1812076696464162817, '性别', 'sex', NULL, 1, 0, '2024-07-13 10:48:46', NULL, '2024-07-13 10:48:46', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for dict_item
-- ----------------------------
CREATE TABLE `dict_item` (
                             `id` bigint NOT NULL COMMENT '主键',
                             `code` varchar(64) NOT NULL COMMENT '编码',
                             `name` varchar(128) DEFAULT NULL COMMENT '名称',
                             `sort` int DEFAULT NULL,
                             `value` text NOT NULL COMMENT '值',
                             `is_enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用 0 否 ，1 是',
                             `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 ，1 是',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `create_by` bigint DEFAULT NULL COMMENT '创建人',
                             `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                             `update_by` bigint DEFAULT NULL COMMENT '更新人',
                             `organization_id` bigint NOT NULL COMMENT '机构ID',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典项';

-- ----------------------------
-- Records of dict_item
-- ----------------------------
BEGIN;
INSERT INTO `dict_item` (`id`, `code`, `name`, `sort`, `value`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (1812076736423297026, 'sex', '男', 1, 'M', 1, 0, '2024-07-13 10:48:55', NULL, '2024-07-13 10:48:55', NULL, 1);
INSERT INTO `dict_item` (`id`, `code`, `name`, `sort`, `value`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (1812076781193297922, 'sex', '女', 1, 'F', 1, 0, '2024-07-13 10:49:06', NULL, '2024-07-13 10:49:06', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for member
-- ----------------------------
CREATE TABLE `member` (
                          `id` bigint NOT NULL COMMENT '成员ID',
                          `user_id` bigint NOT NULL COMMENT '用户ID',
                          `phone` varchar(64) NOT NULL COMMENT '成员手机号',
                          `name` varchar(64) DEFAULT NULL COMMENT '姓名',
                          `sex` char(1) DEFAULT NULL COMMENT '性别,N 未知,M 男 ,F 女',
                          `dept_id` bigint NOT NULL COMMENT '部门id',
                          `state` tinyint NOT NULL COMMENT '成员状态',
                          `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 ，1 是',
                          `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                          `create_by` bigint DEFAULT NULL COMMENT '创建人',
                          `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                          `update_by` bigint DEFAULT NULL COMMENT '更新人',
                          `organization_id` bigint NOT NULL COMMENT '组织ID',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='成员表';

-- ----------------------------
-- Records of member
-- ----------------------------
BEGIN;
INSERT INTO `member` (`id`, `user_id`, `phone`, `name`, `sex`, `dept_id`, `state`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (1812102383938011138, 1, '18888888888', '测试', 'M', 1, 1, 0, '2024-07-13 12:30:50', NULL, '2024-07-13 12:30:50', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
CREATE TABLE `menu` (
                        `id` bigint NOT NULL COMMENT '主键',
                        `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能编号',
                        `parent_id` bigint DEFAULT NULL COMMENT '父菜单ID',
                        `type` tinyint DEFAULT NULL COMMENT '菜单类型(1：菜单；2：目录；3：外链；4：按钮)',
                        `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '菜单名称',
                        `path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '路由路径(浏览器地址栏路径)',
                        `component` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组件路径(vue页面完整路径，省略.vue后缀)',
                        `perm` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '按钮权限标识',
                        `icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '菜单图标',
                        `sort` int DEFAULT '0' COMMENT '排序',
                        `visible` tinyint(1) DEFAULT '1' COMMENT '状态(0:禁用;1:开启)',
                        `redirect` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '跳转路径',
                        `tree_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                        `always_show` tinyint(1) DEFAULT NULL COMMENT '【目录】只有一个子路由是否始终显示(1:是 0:否)',
                        `keep_alive` tinyint(1) DEFAULT NULL COMMENT '【菜单】是否开启页面缓存(1:是 0:否)',
                        `is_enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用 0 否 ，1 是',
                        `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 ，1 是',
                        `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                        `create_by` bigint DEFAULT NULL COMMENT '创建人',
                        `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                        `update_by` bigint DEFAULT NULL COMMENT '更新人',
                        `organization_id` bigint NOT NULL COMMENT '组织ID',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='功能菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
BEGIN;
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (1, 'sys', 0, 2, '系统设置', '/system', 'Layout', 'sys', 'system', 2, 1, '/system/member', '0', NULL, NULL, 1, 0, '2021-08-28 09:12:21', 1, '2025-12-17 23:28:28', NULL, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (3, 'sys:role', 44, 1, '角色管理', 'role', 'iam/role/index', 'sys:role', 'role', 2, 1, NULL, '0,44', NULL, 1, 1, 0, '2021-08-28 09:12:21', 1, '2025-12-17 23:46:47', NULL, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (4, 'sys:menu', 1, 1, '菜单管理', 'menu', 'system/menu/index', 'sys:menu', 'menu', 3, 1, NULL, NULL, NULL, 1, 1, 0, '2021-08-28 09:12:21', 1, '2021-08-28 09:12:21', 1, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (6, 'sys:dict', 1, 1, '字典管理', 'dict', 'system/dict/index', 'sys:dict', 'dict', 5, 1, NULL, NULL, NULL, 1, 1, 0, '2021-08-28 09:12:21', 1, '2021-08-28 09:12:21', 1, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (9, 'sys:role:add', 3, 4, '新增角色', '', NULL, 'sys:role:add', '', 1, 1, NULL, '3', NULL, NULL, 1, 0, '2023-05-20 23:39:09', 1, '2025-12-15 02:46:38', NULL, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (10, 'sys:role:edit', 3, 4, '编辑角色', '', NULL, 'sys:role:edit', '', 2, 1, NULL, '3', NULL, NULL, 1, 0, '2023-05-20 23:40:31', 1, '2025-12-15 02:46:51', NULL, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (11, 'sys:role:del', 3, 4, '删除角色', '', NULL, 'sys:role:del', '', 3, 1, NULL, '3', NULL, NULL, 1, 0, '2023-05-20 23:41:08', 1, '2025-12-15 02:47:03', NULL, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (12, 'sys:menu:add', 4, 4, '新增菜单', '', NULL, 'sys:menu:add', '', 1, 1, NULL, '4', NULL, NULL, 1, 0, '2023-05-20 23:41:35', 1, '2025-12-15 02:45:37', NULL, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (13, 'sys:menu:edit', 4, 4, '编辑菜单', '', NULL, 'sys:menu:edit', '', 3, 1, NULL, '4', NULL, NULL, 1, 0, '2023-05-20 23:41:58', 1, '2025-12-15 02:45:58', NULL, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (14, 'sys:menu:del', 4, 4, '删除菜单', '', NULL, 'sys:menu:del', '', 3, 1, NULL, '4', NULL, NULL, 1, 0, '2023-05-20 23:44:18', 1, '2025-12-15 02:46:11', NULL, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (15, 'sys:dict_type:add', 6, 4, '字典类型新增', '', NULL, 'sys:dict_type:add', '', 1, 1, NULL, '0,1,6', NULL, NULL, 1, 0, '2023-05-21 00:16:06', 1, '2023-05-21 00:16:06', 1, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (16, 'sys:dict_type:edit', 6, 4, '字典类型编辑', '', NULL, 'sys:dict_type:edit', '', 2, 1, NULL, '0,1,6', NULL, NULL, 1, 0, '2023-05-21 00:27:37', 1, '2023-05-21 00:27:37', 1, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (17, 'sys:dict_type:del', 6, 4, '字典类型删除', '', NULL, 'sys:dict_type:del', '', 3, 1, NULL, '0,1,6', NULL, NULL, 1, 0, '2023-05-21 00:29:39', 1, '2023-05-21 00:29:39', 1, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (18, 'sys:dict:add', 6, 4, '字典数据新增', '', NULL, 'sys:dict:add', '', 4, 1, NULL, '0,1,6', NULL, NULL, 1, 0, '2023-05-21 00:46:56', 1, '2023-05-21 00:47:06', 1, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (19, 'sys:dict:edit', 6, 4, '字典数据编辑', '', NULL, 'sys:dict:edit', '', 5, 1, NULL, '0,1,6', NULL, NULL, 1, 0, '2023-05-21 00:47:36', 1, '2023-05-21 00:47:36', 1, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (20, 'sys:dict:del', 6, 4, '字典数据删除', '', NULL, 'sys:dict:del', '', 6, 1, NULL, '0,1,6', NULL, NULL, 1, 0, '2023-05-21 00:48:10', 1, '2023-05-21 00:48:20', 1, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (21, 'sys:user:reset_pwd', 30, 4, '重置密码', '', NULL, 'sys:user:reset_pwd', '', 4, 1, NULL, '1,30', NULL, NULL, 1, 0, '2023-05-21 00:49:18', 1, '2025-12-15 02:37:45', NULL, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (26, 'sys:dict_type:read', 6, 4, '字典类只读权限', '', NULL, 'sys:dict_type:read', '', 1, 1, '', '6', 0, 0, 1, 0, '2025-12-13 14:24:18', NULL, '2025-12-13 14:24:38', NULL, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (27, 'sys:dict:read', 6, 4, '字典项只读权限', '', NULL, 'sys:dict:read', '', 7, 1, '', '6', NULL, NULL, 1, 0, '2025-12-13 14:25:41', NULL, '2025-12-13 14:25:41', NULL, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (28, 'sys:dict_type:enable', 6, 4, '启用字典类', '', NULL, 'sys:dict_type:enable', '', 1, 1, '', '6', NULL, NULL, 1, 0, '2025-12-13 14:26:44', NULL, '2025-12-13 15:39:33', NULL, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (30, 'sys:user', 44, 1, '用户管理', 'user', 'iam/user/index', NULL, 'user', 1, 1, '', '0,44', NULL, NULL, 1, 0, '2025-12-13 15:09:13', NULL, '2025-12-17 23:46:30', NULL, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (31, 'sys:user:list', 30, 4, '检索用户', '', NULL, 'sys:user:list', '', 1, 1, '', '1,30', NULL, NULL, 1, 0, '2025-12-13 15:10:22', NULL, '2025-12-13 15:26:20', NULL, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (33, 'sys:dict_type:disable', 6, 4, '禁用字典类', '', NULL, 'sys:dict_type:disable', '', 1, 1, '', '6', NULL, NULL, 1, 0, '2025-12-13 15:39:18', NULL, '2025-12-13 15:39:45', NULL, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (34, 'sys:user:enabled', 30, 4, '启用用户', '', NULL, 'sys:user:enabled', '', 1, 1, '', '1,30', NULL, NULL, 1, 0, '2025-12-15 02:38:17', NULL, '2025-12-15 02:38:17', NULL, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (35, 'sys:user:disable', 30, 4, '停用用户', '', NULL, 'sys:user:disable', '', 1, 1, '', '1,30', NULL, NULL, 1, 0, '2025-12-15 02:39:00', NULL, '2025-12-15 02:39:00', NULL, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (36, 'sys:user:lock', 30, 4, '锁定用户', '', NULL, 'sys:user:lock', '', 1, 1, '', '1,30', NULL, NULL, 1, 0, '2025-12-15 02:39:57', NULL, '2025-12-15 02:39:57', NULL, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (37, 'sys:user:unlock', 30, 4, '解锁用户', '', NULL, 'sys:user:unlock', '', 1, 1, '', '1,30', NULL, NULL, 1, 0, '2025-12-15 02:40:27', NULL, '2025-12-15 02:40:27', NULL, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (38, 'sys:user:del', 30, 4, '删除用户', '', NULL, 'sys:user:del', '', 1, 1, '', '1,30', 0, 0, 1, 0, '2025-12-17 21:54:37', NULL, '2025-12-17 21:54:37', NULL, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (39, 'sys:user:add', 30, 4, '添加用户', '', NULL, 'sys:user:add', '', 1, 1, '', '1,30', NULL, NULL, 1, 0, '2025-12-17 21:55:03', NULL, '2025-12-17 21:55:03', NULL, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (40, 'sys:user:edit', 30, 4, '编辑用户', '', NULL, 'sys:user:edit', '', 1, 1, '', '1,30', NULL, NULL, 1, 0, '2025-12-17 21:56:02', NULL, '2025-12-17 21:56:02', NULL, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (43, 'config', 1, 1, '系统配置', 'config', 'system/config/index', 'config', 'setting', 10, 1, '', '0,1', 0, 0, 1, 0, '2025-12-17 23:32:31', NULL, '2025-12-17 23:32:43', NULL, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (44, 'iam', 0, 2, '身份管理', '/iam', 'Layout', 'iam', 'peoples', 2, 1, '', '0', NULL, NULL, 1, 0, '2025-12-17 23:33:58', NULL, '2026-04-15 01:42:57', 1812100202631475200, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (63, 'announcement', 1, 1, '系统公告', 'announcement', 'system/announcement/index', NULL, 'message', 1, 1, '', '0,1', 0, 1, 1, 1, '2026-04-15 01:42:38', 1812100202631475200, '2026-04-27 17:22:55', 1812100202631475200, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (64, 'operationrecord', 1, 1, '操作记录', 'operation-record', 'system/operation-record/index', NULL, 'document', 2, 1, '', '0,1', 0, 1, 1, 0, '2026-04-15 01:42:38', 1812100202631475200, '2026-05-16 20:14:41', 1812100202631475200, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (65, 'gather', 0, 2, '回收日志', '/gather', 'Layout', NULL, 'document', 2, 1, '', '0', 1, 0, 1, 1, '2026-04-15 01:43:16', 1812100202631475200, '2026-04-15 01:43:16', 1812100202631475200, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (66, 'recyclerecord', 65, 1, '回收记录', 'recycleRecord', 'gather/recycleRecord/index', NULL, 'todolist', 1, 1, '', '0,65', 0, 1, 1, 1, '2026-04-15 01:43:16', 1812100202631475200, '2026-04-15 01:43:16', 1812100202631475200, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (67, 'prize', 0, 2, '奖品管理', '/prize', 'Layout', NULL, 'coupon', 4, 1, '', '0', 1, 0, 1, 1, '2026-04-15 01:43:16', 1812100202631475200, '2026-04-15 01:43:16', 1812100202631475200, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (68, 'sendrecord', 67, 1, '奖品发放记录', 'sendRecord', 'prize/sendRecord/index', NULL, 'coupon', 1, 1, '', '0,67', 0, 1, 1, 1, '2026-04-15 01:43:16', 1812100202631475200, '2026-04-15 01:43:16', 1812100202631475200, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (69, 'advertising', 0, 2, '广告管理', '/advertising', 'Layout', NULL, 'advert', 5, 1, '/advertising/list', '0', 1, 0, 1, 1, '2026-04-15 01:43:16', 1812100202631475200, '2026-04-15 01:43:16', 1812100202631475200, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (70, 'advertiser', 69, 1, '广告商管理', 'advertiser', 'advertiser/index', NULL, 'brand', 1, 1, '', '0,69', 0, 1, 1, 1, '2026-04-15 01:43:16', 1812100202631475200, '2026-04-15 01:43:16', 1812100202631475200, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (71, 'advertisinglist', 69, 1, '广告列表', 'list', 'advertising/index', NULL, 'advert', 2, 1, '', '0,69', 0, 1, 1, 1, '2026-04-15 01:43:17', 1812100202631475200, '2026-04-15 01:43:17', 1812100202631475200, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (72, 'advertisingcallback', 69, 1, '广告回调记录', 'callback', 'advertising/callback/index', NULL, 'document', 3, 1, '', '0,69', 0, 1, 1, 1, '2026-04-15 01:43:17', 1812100202631475200, '2026-04-15 01:43:17', 1812100202631475200, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (73, 'campaign', 0, 2, '活动管理', '/campaign', 'Layout', NULL, 'chart', 6, 1, '', '0', 1, 0, 1, 1, '2026-04-15 01:43:17', 1812100202631475200, '2026-04-15 01:43:17', 1812100202631475200, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (74, 'campaigncategory', 73, 1, '活动分类', 'category', 'campaign/category/index', NULL, 'tree', 1, 1, '', '0,73', 0, 1, 1, 1, '2026-04-15 01:43:17', 1812100202631475200, '2026-04-15 01:43:17', 1812100202631475200, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (75, 'campaigntemplate', 73, 1, '活动模板', 'template', 'campaign/template/index', NULL, 'project', 2, 1, '', '0,73', 0, 1, 1, 1, '2026-04-15 01:43:17', 1812100202631475200, '2026-04-15 01:43:17', 1812100202631475200, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (76, 'campaignplan', 73, 1, '活动计划', 'plan', 'campaign/plan/index', NULL, 'chart', 3, 1, '', '0,73', 0, 1, 1, 1, '2026-04-15 01:43:17', 1812100202631475200, '2026-04-15 01:43:17', 1812100202631475200, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (77, 'campaignrecord', 73, 1, '活动记录', 'record', 'campaign/record/index', NULL, 'document', 4, 1, '', '0,73', 0, 1, 1, 1, '2026-04-15 01:43:17', 1812100202631475200, '2026-04-15 01:43:17', 1812100202631475200, 1);
INSERT INTO `menu` (`id`, `code`, `parent_id`, `type`, `name`, `path`, `component`, `perm`, `icon`, `sort`, `visible`, `redirect`, `tree_path`, `always_show`, `keep_alive`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (78, 'sys:user:assignRole', 30, 4, '分配权限', '', NULL, 'sys:user:assignRole', '', 1, 1, '', '0,44,30', 0, 0, 1, 0, '2026-05-16 02:10:41', 1812100202631475200, '2026-05-16 02:10:41', 1812100202631475200, 1);
COMMIT;

-- ----------------------------
-- Table structure for operation_record
-- ----------------------------
CREATE TABLE `operation_record` (
                                    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                    `mid` bigint DEFAULT NULL COMMENT '成员ID',
                                    `action` varchar(128) NOT NULL COMMENT '操作行为',
                                    `module` int DEFAULT NULL COMMENT '操作模块',
                                    `score` bigint DEFAULT NULL COMMENT '积分变动',
                                    `operation_params` text COMMENT '操作参数',
                                    `operation_result` text COMMENT '操作结果',
                                    `status` int DEFAULT '1' COMMENT '操作状态：1成功 0失败',
                                    `error_msg` text COMMENT '错误信息',
                                    `ip_address` varchar(45) DEFAULT NULL COMMENT '操作IP',
                                    `user_agent` varchar(500) DEFAULT NULL COMMENT '用户代理',
                                    `cost_time` bigint DEFAULT NULL COMMENT '耗时(毫秒)',
                                    `organization_id` bigint DEFAULT NULL,
                                    `create_time` datetime(3) NOT NULL COMMENT '创建时间',
                                    `update_time` datetime(3) NOT NULL COMMENT '修改时间',
                                    `create_by` bigint DEFAULT NULL,
                                    `update_by` bigint DEFAULT NULL,
                                    `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除 0-否 1-是',
                                    PRIMARY KEY (`id`),
                                    KEY `idx_create_time` (`create_time`),
                                    KEY `idx_organization_id` (`organization_id`),
                                    KEY `idx_organization_action` (`organization_id`,`action`),
                                    KEY `idx_organization_status_time` (`organization_id`,`status`,`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志表';


-- ----------------------------
-- Table structure for role
-- ----------------------------
CREATE TABLE `role` (
                        `id` bigint NOT NULL COMMENT '主键id',
                        `name` varchar(128) NOT NULL COMMENT '名称',
                        `code` varchar(64) NOT NULL COMMENT '编码',
                        `remark` varchar(255) DEFAULT NULL COMMENT '描述',
                        `data_scope` tinyint DEFAULT NULL COMMENT '数据权限(0-所有数据；1-部门及子部门数据；2-本部门数据；3-本人数据)',
                        `is_enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用 1:  启用 0:未启用',
                        `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 ，1 是',
                        `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                        `create_by` bigint DEFAULT NULL COMMENT '创建人',
                        `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                        `update_by` bigint DEFAULT NULL COMMENT '更新人',
                        `organization_id` bigint NOT NULL COMMENT '组织ID',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` (`id`, `name`, `code`, `remark`, `data_scope`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (1812100202631475200, '超级管理员', 'super_admin', '超级管理员', 0, 1, 0, '2025-12-17 23:23:01', NULL, '2025-12-17 23:23:01', NULL, 1);
INSERT INTO `role` (`id`, `name`, `code`, `remark`, `data_scope`, `is_enabled`, `is_deleted`, `create_time`, `create_by`, `update_time`, `update_by`, `organization_id`) VALUES (1812100202631475201, '系统管理员', 'sys_admin', '系统管理员', 1, 1, 0, '2023-05-21 00:49:18', 1, '2025-12-17 23:05:10', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for role_authority_mapping
-- ----------------------------
CREATE TABLE `role_authority_mapping` (
                                          `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                          `role_id` bigint NOT NULL COMMENT '角色ID',
                                          `authority_code` varchar(32) NOT NULL COMMENT '权限CODE',
                                          `create_time` datetime(3) NOT NULL COMMENT '创建时间',
                                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=970 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色权限表';

-- ----------------------------
-- Records of role_authority_mapping
-- ----------------------------
BEGIN;
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (903, 1812100202631475200, 'sys', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (904, 1812100202631475200, 'sys:role', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (905, 1812100202631475200, 'sys:menu', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (906, 1812100202631475200, 'sys:dict', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (907, 1812100202631475200, 'sys:role:add', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (908, 1812100202631475200, 'sys:role:edit', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (909, 1812100202631475200, 'sys:role:del', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (910, 1812100202631475200, 'sys:menu:add', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (911, 1812100202631475200, 'sys:menu:edit', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (912, 1812100202631475200, 'sys:menu:del', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (913, 1812100202631475200, 'sys:dict_type:add', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (914, 1812100202631475200, 'sys:dict_type:edit', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (915, 1812100202631475200, 'sys:dict_type:del', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (916, 1812100202631475200, 'sys:dict:add', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (917, 1812100202631475200, 'sys:dict:edit', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (918, 1812100202631475200, 'sys:dict:del', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (919, 1812100202631475200, 'sys:user:reset_pwd', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (920, 1812100202631475200, 'sys:dict_type:read', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (921, 1812100202631475200, 'sys:dict:read', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (922, 1812100202631475200, 'sys:dict_type:enable', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (923, 1812100202631475200, 'sys:user', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (924, 1812100202631475200, 'sys:user:list', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (925, 1812100202631475200, 'sys:dict_type:disable', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (926, 1812100202631475200, 'sys:user:enabled', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (927, 1812100202631475200, 'sys:user:disable', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (928, 1812100202631475200, 'sys:user:lock', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (929, 1812100202631475200, 'sys:user:unlock', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (930, 1812100202631475200, 'sys:user:del', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (931, 1812100202631475200, 'sys:user:add', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (932, 1812100202631475200, 'sys:user:edit', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (933, 1812100202631475200, 'config', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (934, 1812100202631475200, 'iam', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (935, 1812100202631475200, 'announcement', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (936, 1812100202631475200, 'operationrecord', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (937, 1812100202631475200, 'gather', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (938, 1812100202631475200, 'recyclerecord', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (939, 1812100202631475200, 'prize', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (940, 1812100202631475200, 'sendrecord', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (941, 1812100202631475200, 'advertising', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (942, 1812100202631475200, 'advertiser', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (943, 1812100202631475200, 'advertisinglist', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (944, 1812100202631475200, 'advertisingcallback', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (945, 1812100202631475200, 'campaign', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (946, 1812100202631475200, 'campaigncategory', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (947, 1812100202631475200, 'campaigntemplate', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (948, 1812100202631475200, 'campaignplan', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (949, 1812100202631475200, 'campaignrecord', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (950, 1812100202631475200, 'sys:user:assignRole', '2026-05-16 14:05:26.000');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (966, 1812100202631475201, 'sys', '2026-05-16 14:15:09.944');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (967, 1812100202631475201, 'sys:dict', '2026-05-16 14:15:09.944');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (968, 1812100202631475201, 'sys:dict_type:read', '2026-05-16 14:15:09.944');
INSERT INTO `role_authority_mapping` (`id`, `role_id`, `authority_code`, `create_time`) VALUES (969, 1812100202631475201, 'sys:dict:read', '2026-05-16 14:15:09.944');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user` (
                        `id` bigint NOT NULL AUTO_INCREMENT COMMENT ' 主键ID ',
                        `username` varchar(64) NOT NULL COMMENT '用户名',
                        `password` varchar(128) DEFAULT NULL COMMENT '加密后的密码',
                        `nick_name` varchar(64) DEFAULT NULL COMMENT '昵称',
                        `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
                        `intro` varchar(255) DEFAULT NULL COMMENT '简介',
                        `sex` char(1) DEFAULT NULL COMMENT '性别,N 未知,M 男 ,F 女',
                        `phone` varchar(20) DEFAULT NULL COMMENT '电话',
                        `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
                        `is_unexpired` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否未过期 0：过期 1：未过期',
                        `is_enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用 1:  启用 0:未启用',
                        `is_unlocked` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否未锁定 0:  锁定 1:未锁定',
                        `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0：未删除 1：删除',
                        `create_time` datetime(3) NOT NULL COMMENT '创建时间',
                        `update_time` datetime(3) NOT NULL COMMENT '修改时间',
                        `update_by` bigint DEFAULT NULL COMMENT '修改人',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `uk_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1812100202631475201 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `username`, `password`, `nick_name`, `avatar`, `intro`, `sex`, `phone`, `email`, `is_unexpired`, `is_enabled`, `is_unlocked`, `is_deleted`, `create_time`, `update_time`, `update_by`) VALUES (1812100202631475200, 'admin', '$2a$10$apqYBdTcbRvgya0CMemDP.tvLUGz8RIDtSsuoi50/ziy.wOJKxm1G', '超级管理员', 'http://localhost:18000/api/files/viewImg/373f44375571416fb8e27a09133c41cd.gif', '有志者事竟成，破釜沉舟百二秦关终属楚，苦心人天不负，卧薪尝胆三千越甲可吞吴。', 'F', '1888888888', '1888888888@888.com', 1, 1, 1, 0, '2024-06-19 00:00:00.000', '2026-05-16 15:20:55.150', 1812100202631475200);
COMMIT;

-- ----------------------------
-- Table structure for user_authority_mapping
-- ----------------------------
CREATE TABLE `user_authority_mapping` (
                                          `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                          `user_id` bigint NOT NULL COMMENT '用户ID',
                                          `authority_code` varchar(32) NOT NULL COMMENT '权限CODE',
                                          `create_time` datetime(3) NOT NULL COMMENT '创建时间',
                                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户权限关系表';

-- ----------------------------
-- Records of user_authority_mapping
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_bind
-- ----------------------------
CREATE TABLE `user_bind` (
                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                             `user_id` bigint NOT NULL COMMENT '用户ID',
                             `platform_user_id` varchar(32) NOT NULL COMMENT '平台用户ID',
                             `platform_code` varchar(32) NOT NULL COMMENT '平台编码',
                             `create_time` datetime(3) NOT NULL COMMENT '创建时间',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_bind
-- ----------------------------
BEGIN;
INSERT INTO `user_bind` (`id`, `user_id`, `platform_user_id`, `platform_code`, `create_time`) VALUES (1, 1, '18888888888', 'phone', '2024-07-13 12:21:38.853');
COMMIT;

-- ----------------------------
-- Table structure for user_role_mapping
-- ----------------------------
CREATE TABLE `user_role_mapping` (
                                     `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                     `role_id` bigint NOT NULL COMMENT '角色ID',
                                     `user_id` bigint NOT NULL COMMENT '用户ID',
                                     `create_time` datetime(3) NOT NULL COMMENT '创建时间',
                                     PRIMARY KEY (`id`),
                                     UNIQUE KEY `uk_role_user` (`role_id`,`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1812100202631475203 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色关系表';

-- ----------------------------
-- Records of user_role_mapping
-- ----------------------------
BEGIN;
INSERT INTO `user_role_mapping` (`id`, `role_id`, `user_id`, `create_time`) VALUES (1812100202631475201, 1812100202631475200, 1812100202631475200, '2025-12-13 14:13:23.763');
COMMIT;

-- ----------------------------
-- Table structure for user_tag
-- ----------------------------
CREATE TABLE `user_tag` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                            `tag` varchar(64) NOT NULL COMMENT '标签名',
                            `user_id` bigint NOT NULL COMMENT '用户ID',
                            `create_time` datetime(3) NOT NULL COMMENT '创建时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户标签表';

-- ----------------------------
-- Records of user_tag
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
