
-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (1, 'sys', 0, 2, '系统管理', '/system', 'Layout', 'sys', 'system', 1, 1, '/system/member', null, null, null, 1, 0, '2021-08-28 09:12:21', 1, '2021-08-28 09:12:21', 1, 1);
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (2, 'sys:member', 1, 1, '成员管理', 'member', 'system/member/index', 'sys:member', 'user', 1, 1, null, null, null, 1, 1, 0, '2021-08-28 09:12:21', 1, '2021-08-28 09:12:21', 1, 1);
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (3, 'sys:role', 1, 1, '角色管理', 'role', 'system/role/index', 'sys:role', 'role', 2, 1, null, null, null, 1, 1, 0, '2021-08-28 09:12:21', 1, '2021-08-28 09:12:21', 1, 1);
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (4, 'sys:menu', 1, 1, '菜单管理', 'menu', 'system/menu/index', 'sys:menu', 'menu', 3, 1, null, null, null, 1, 1, 0, '2021-08-28 09:12:21', 1, '2021-08-28 09:12:21', 1, 1);
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (5, 'sys:dept', 1, 1, '部门管理', 'dept', 'system/dept/index', 'sys:dept', 'tree', 4, 1, null, null, null, 1, 1, 0, '2021-08-28 09:12:21', 1, '2021-08-28 09:12:21', 1, 1);
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (6, 'sys:dict', 1, 1, '字典管理', 'dict', 'system/dict/index', 'sys:dict', 'dict', 5, 1, null, null, null, 1, 1, 0, '2021-08-28 09:12:21', 1, '2021-08-28 09:12:21', 1, 1);
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (7, 'sys:member:edit', 2, 4, '修改成员', '', null, 'sys:member:edit', '', 2, 1, '', null, null, null, 1, 0, '2022-11-05 01:26:44', 1, '2022-11-05 01:26:44', 1, 1);
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (8, 'sys:member:del', 2, 4, '删除成员', '', null, 'sys:member:del', '', 3, 1, '', null, null, null, 1, 0, '2022-11-05 01:27:13', 1, '2022-11-05 01:27:13', 1, 1);
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (9, 'sys:role:add', 3, 4, '角色新增', '', null, 'sys:role:add', '', 1, 1, null, '0,1,3', null, null, 1, 0, '2023-05-20 23:39:09', 1, '2023-05-20 23:39:09', 1, 1);
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (10, 'sys:role:edit', 3, 4, '角色编辑', '', null, 'sys:role:edit', '', 2, 1, null, '0,1,3', null, null, 1, 0, '2023-05-20 23:40:31', 1, '2023-05-20 23:40:31', 1, 1);
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (11, 'sys:role:del', 3, 4, '角色删除', '', null, 'sys:role:del', '', 3, 1, null, '0,1,3', null, null, 1, 0, '2023-05-20 23:41:08', 1, '2023-05-20 23:41:08', 1, 1);
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (12, 'sys:menu:add', 4, 4, '菜单新增', '', null, 'sys:menu:add', '', 1, 1, null, '0,1,4', null, null, 1, 0, '2023-05-20 23:41:35', 1, '2023-05-20 23:41:35', 1, 1);
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (13, 'sys:menu:edit', 4, 4, '菜单编辑', '', null, 'sys:menu:edit', '', 3, 1, null, '0,1,4', null, null, 1, 0, '2023-05-20 23:41:58', 1, '2023-05-20 23:41:58', 1, 1);
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (14, 'sys:menu:del', 4, 4, '菜单删除', '', null, 'sys:menu:del', '', 3, 1, null, '0,1,4', null, null, 1, 0, '2023-05-20 23:44:18', 1, '2023-05-20 23:44:18', 1, 1);
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (15, 'sys:dict_type:add', 6, 4, '字典类型新增', '', null, 'sys:dict_type:add', '', 1, 1, null, '0,1,6', null, null, 1, 0, '2023-05-21 00:16:06', 1, '2023-05-21 00:16:06', 1, 1);
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (16, 'sys:dict_type:edit', 6, 4, '字典类型编辑', '', null, 'sys:dict_type:edit', '', 2, 1, null, '0,1,6', null, null, 1, 0, '2023-05-21 00:27:37', 1, '2023-05-21 00:27:37', 1, 1);
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (17, 'sys:dict_type:del', 6, 4, '字典类型删除', '', null, 'sys:dict_type:del', '', 3, 1, null, '0,1,6', null, null, 1, 0, '2023-05-21 00:29:39', 1, '2023-05-21 00:29:39', 1, 1);
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (18, 'sys:dict:add', 6, 4, '字典数据新增', '', null, 'sys:dict:add', '', 4, 1, null, '0,1,6', null, null, 1, 0, '2023-05-21 00:46:56', 1, '2023-05-21 00:47:06', 1, 1);
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (19, 'sys:dict:edit', 6, 4, '字典数据编辑', '', null, 'sys:dict:edit', '', 5, 1, null, '0,1,6', null, null, 1, 0, '2023-05-21 00:47:36', 1, '2023-05-21 00:47:36', 1, 1);
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (20, 'sys:dict:del', 6, 4, '字典数据删除', '', null, 'sys:dict:del', '', 6, 1, null, '0,1,6', null, null, 1, 0, '2023-05-21 00:48:10', 1, '2023-05-21 00:48:20', 1, 1);
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (21, 'sys:member:reset_pwd', 2, 4, '重置成员密码', '', null, 'sys:member:reset_pwd', '', 4, 1, null, '0,1,2', null, null, 1, 0, '2023-05-21 00:49:18', 1, '2023-05-21 00:49:18', 1, 1);
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (22, 'sys:dept:add', 5, 4, '部门新增', '', null, 'sys:dept:add', '', 1, 1, null, '0,1,5', null, null, 1, 0, '2023-05-21 00:49:18', 1, '2023-05-21 00:49:18', 1, 1);
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (23, 'sys:dept:edit', 5, 4, '部门编辑', '', null, 'sys:dept:edit', '', 2, 1, null, '0,1,5', null, null, 1, 0, '2023-05-21 00:49:18', 1, '2023-05-21 00:49:18', 1, 1);
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (24, 'sys:dept:del', 5, 4, '部门删除', '', null, 'sys:dept:del', '', 3, 1, null, '0,1,5', null, null, 1, 0, '2023-05-21 00:49:18', 1, '2023-05-21 00:49:18', 1, 1);
INSERT INTO menu (id, code, parent_id, type, name, path, component, perm, icon, sort, visible, redirect, tree_path, always_show, keep_alive, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (25, 'sys:member:add', 2, 4, '添加成员', '', null, 'sys:member:add', '', 1, 1, '', '2', 0, 0, 1, 0, '2024-07-13 10:58:04', null, '2024-07-13 10:58:45', null, 1);


INSERT INTO dept (id, name, parent_id, tree_path, sort, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (1, '研发部', 0, '0', 1, 1, 0, '2024-07-13 11:11:55', null, '2024-07-13 11:11:55', null, 1);

INSERT INTO dict_category (id, name, code, remark, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (1812076696464162817, '性别', 'sex', null, 1, 0, '2024-07-13 10:48:46', null, '2024-07-13 10:48:46', null, 1);

INSERT INTO dict_item (id, code, name, sort, value, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (1812076736423297026, 'sex', '男', 1, 'M', 1, 0, '2024-07-13 10:48:55', null, '2024-07-13 10:48:55', null, 1);
INSERT INTO dict_item (id, code, name, sort, value, is_enabled, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (1812076781193297922, 'sex', '女', 1, 'F', 1, 0, '2024-07-13 10:49:06', null, '2024-07-13 10:49:06', null, 1);
INSERT INTO member (id, user_id, phone, name, sex, dept_id, state, is_deleted, create_time, create_by, update_time, update_by, organization_id) VALUES (1812102383938011138, 1, '18888888888', '测试', 'M', 1, 1, 0, '2024-07-13 12:30:50', null, '2024-07-13 12:30:50', null, 1);
