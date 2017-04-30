-- Create table 用户名表
create table sys_login_name
(
   id                   varchar(64) not null comment '主键',
   user_id              varchar(64) not null comment '用户id',
   login_name           varchar(200) not null comment '登录名',
   type                 char(1) comment '类型',
   del_flag             char(1) comment '标志',
   create_by            varchar(64) comment '创建人',
   create_date          datetime comment '创建时间',
   update_by            varchar(64) comment '更新人',
   update_date          datetime comment '更新时间',
   remarks              varchar(500) comment '备注信息',
   primary key (id)
);

alter table SYS_LOGIN_NAME comment '登录用户名表-SYS_LOGIN_NAME';

alter table sys_login_name add constraint FK_Reference_2 foreign key (user_id)
      references sys_user (id) on delete restrict on update restrict;

insert into `sys_login_name` (`id`, `user_id`, `login_name`, `type`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('1','1','13800138000','0','0',NULL,NULL,NULL,NULL,NULL);
insert into `sys_login_name` (`id`, `user_id`, `login_name`, `type`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('2','1','130425199502021234','1','0',NULL,NULL,NULL,NULL,NULL);
insert into `sys_login_name` (`id`, `user_id`, `login_name`, `type`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('2325f7b6eb7a480d87d83d8d4e2513cb','caf20d9238f5495db2f46ccfed1e1894','123@qq.com','1','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:22:42','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:22:42',NULL);
insert into `sys_login_name` (`id`, `user_id`, `login_name`, `type`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('3','1','8000@163.com','2','0',NULL,NULL,NULL,NULL,NULL);
insert into `sys_login_name` (`id`, `user_id`, `login_name`, `type`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('4','1','admin','3','0',NULL,NULL,NULL,NULL,NULL);
insert into `sys_login_name` (`id`, `user_id`, `login_name`, `type`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('42bffabbc09941c4a1a1e22d6ca54cdf','d6030f93e3bb422fa848f23ff9d10cc4','13566887784','0','0','1','2017-02-25 10:32:34','1','2017-02-25 10:32:34',NULL);
insert into `sys_login_name` (`id`, `user_id`, `login_name`, `type`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('842a1df512204ebf98bead1a69eeb205','caf20d9238f5495db2f46ccfed1e1894','530422198511174812','2','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:22:42','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:22:42',NULL);
insert into `sys_login_name` (`id`, `user_id`, `login_name`, `type`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('ae1ad81fc1b2466ea17d1b72d364d23e','d6030f93e3bb422fa848f23ff9d10cc4','510811197910204411','2','0','1','2017-02-25 10:32:34','1','2017-02-25 10:32:34',NULL);
insert into `sys_login_name` (`id`, `user_id`, `login_name`, `type`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('b29019f236b647668b88458a52376674','caf20d9238f5495db2f46ccfed1e1894','13988998878','0','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:22:42','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:22:42',NULL);
insert into `sys_login_name` (`id`, `user_id`, `login_name`, `type`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('b481c2f564b0451394906ad904ec5df7','caf20d9238f5495db2f46ccfed1e1894','zhangsan','3','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:22:42','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:22:42',NULL);
insert into `sys_login_name` (`id`, `user_id`, `login_name`, `type`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('ba042649511641e8980c112cbac0205a','d6030f93e3bb422fa848f23ff9d10cc4','apadmin','3','0','1','2017-02-25 10:32:34','1','2017-02-25 10:32:34',NULL);
insert into `sys_login_name` (`id`, `user_id`, `login_name`, `type`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('dd07ac5346214887b3d3fc68f4ef3a4b','d6030f93e3bb422fa848f23ff9d10cc4','123456789@qq.com','1','0','1','2017-02-25 10:32:34','1','2017-02-25 10:32:34',NULL);
