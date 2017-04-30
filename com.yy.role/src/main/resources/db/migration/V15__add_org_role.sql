-- Create table 组织角色
create table ORG_ROLE
(
   id                   varchar(64) not null comment '主键',
   role_name            varchar(30) not null comment '角色名称',
   org_id               varchar(64) not null comment '组织机构Id',
   del_flag             char(1) comment '标志',
   create_by            varchar(64) comment '创建人',
   create_date          datetime comment '创建时间',
   update_by            varchar(64) comment '更新人',
   update_date          datetime comment '更新时间',
   remarks              varchar(500) comment '备注信息',
   primary key (id)
);

alter table ORG_ROLE comment '组织机构角色信息-ORG_ROLE';

alter table ORG_ROLE add constraint FK_Reference_8 foreign key (org_id)
      references SYS_COMPANY (id) on delete restrict on update restrict;
insert into `org_role` (`id`, `role_name`, `org_id`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('16ce80803b8f4b26ad7cf4d0a97f55dd','药品角色','4745915623bc439da8584b14857dae0b','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:26:18','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:26:18','');
insert into `org_role` (`id`, `role_name`, `org_id`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('22df0d69d06e4dec805721a598e52afd','医技角色','4745915623bc439da8584b14857dae0b','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-04-21 15:42:52','d6030f93e3bb422fa848f23ff9d10cc4','2017-04-21 15:42:52','');
insert into `org_role` (`id`, `role_name`, `org_id`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('3bafc895c5594434b2a9504c2ff09c7a','系统管理','4745915623bc439da8584b14857dae0b','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-02-25 10:57:12','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:37:33','');
insert into `org_role` (`id`, `role_name`, `org_id`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('3cbb48c7db52421da3800b8dd7713726','医生角色','4745915623bc439da8584b14857dae0b','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:26:31','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:26:31','');
insert into `org_role` (`id`, `role_name`, `org_id`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('41b11e95140641feb710faa59ab8740e','护士角色','4745915623bc439da8584b14857dae0b','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-04-21 15:42:41','d6030f93e3bb422fa848f23ff9d10cc4','2017-04-21 15:42:41','');
insert into `org_role` (`id`, `role_name`, `org_id`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('48ced336eaca40cd8dcf272a9249c3a7','药房角色','4745915623bc439da8584b14857dae0b','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-04-21 15:43:55','d6030f93e3bb422fa848f23ff9d10cc4','2017-04-21 15:43:55','');
insert into `org_role` (`id`, `role_name`, `org_id`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('6f9a56f8b511488ab54b80602f9a72d2','运维角色','4745915623bc439da8584b14857dae0b','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-02-25 10:57:30','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:37:31','');
insert into `org_role` (`id`, `role_name`, `org_id`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('768e7bcb61274a52bd72c00ace467b36','用血角色','4745915623bc439da8584b14857dae0b','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-04-21 15:44:15','d6030f93e3bb422fa848f23ff9d10cc4','2017-04-21 15:44:15','');
insert into `org_role` (`id`, `role_name`, `org_id`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('983d60f709dc416aa64fa053676ed0f8','收费角色','4745915623bc439da8584b14857dae0b','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:37:16','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:37:16','');
insert into `org_role` (`id`, `role_name`, `org_id`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('a13f0ddbeed94708a5cf4ec41d0ddb38','手术角色','4745915623bc439da8584b14857dae0b','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-04-21 15:44:06','d6030f93e3bb422fa848f23ff9d10cc4','2017-04-21 15:44:06','');
insert into `org_role` (`id`, `role_name`, `org_id`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('f0123b8f2df44894b2b32a92e08d3373','挂号角色','4745915623bc439da8584b14857dae0b','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-02-25 10:57:47','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:26:43','');
