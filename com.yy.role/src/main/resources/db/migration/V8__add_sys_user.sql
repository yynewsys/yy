-- Create table 用户表
create table sys_user
(
   id                   varchar(64) not null comment '主键',
   password            varchar(100) not null comment '密码',
   current_login        varchar(100),
   user_type          char(1) comment '用户类型1管理员',
   login_flag           char(1) comment '是否允许登录',
   persion_id           varchar(64) not null comment 'persion_info主键',
   del_flag             char(1) comment '标志',
   create_by            varchar(64) comment '创建人',
   create_date          datetime comment '创建时间',
   update_by            varchar(64) comment '更新人',
   update_date          datetime comment '更新时间',
   remarks              varchar(500) comment '备注信息',
   primary key (id)
);

alter table sys_user comment '人员登录信息表-SYS_USER';

alter table sys_user add constraint FK_Reference_1 foreign key (persion_id)
      references person_info (id) on delete restrict on update restrict;
ALTER TABLE sys_user
   CHANGE `login_flag` `login_flag` CHAR(1)  DEFAULT '1' NULL  COMMENT '是否允许登录';

insert into `sys_user` (`id`, `password`, `current_login`, `user_type`, `login_flag`, `persion_id`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('1','504f41d334d1698ff62b62a62aed28634ebe465b56ee02814991e8b2','admin','1','1','1','0',NULL,NULL,NULL,NULL,NULL);
insert into `sys_user` (`id`, `password`, `current_login`, `user_type`, `login_flag`, `persion_id`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('caf20d9238f5495db2f46ccfed1e1894','329f22586fc93b0ef194db8a8a20c68baed0ca2bdd1dd96705b4606d','zhangsan','3','1','6956bcb6b3f44c65b20bcc6390ee6fe6','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:22:42','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:22:42',NULL);
insert into `sys_user` (`id`, `password`, `current_login`, `user_type`, `login_flag`, `persion_id`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('d6030f93e3bb422fa848f23ff9d10cc4','43db368583c34d1c4bb0ecd732404490b6f577728d58e98d4dff1cc4','apadmin','2','1','a9e9f13f6d844179a1147e49e919dc32','0','1','2017-02-25 10:32:34','1','2017-02-25 10:32:34',NULL);
