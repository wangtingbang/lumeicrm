CREATE TABLE `op_auth_user` (
  `id` varchar(32) NOT NULL COMMENT '主键',
    `user_name` varchar(100) NOT NULL COMMENT '登陆名',
  `password` varchar(100) NOT NULL COMMENT '登陆密码',
    `salt` varchar(100) DEFAULT NULL COMMENT 'salt',
  `nick_name` varchar(100) NOT NULL COMMENT '用户名',
    `enabled` tinyint(4) NOT NULL COMMENT '是否启用 1-启用 0-禁用',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `delete_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除 1-已删除 0-未删除'
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `op_auth_user_role` (
  `id` varchar(32) NOT NULL COMMENT '主键',
    `user_id` varchar(32) DEFAULT NULL COMMENT '用户主键ID',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色key',
    `role_name` varchar(32) DEFAULT NULL COMMENT '角色名',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `delete_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除 1-已删除 0-未删除'
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


