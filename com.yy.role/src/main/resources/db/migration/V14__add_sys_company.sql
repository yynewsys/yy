-- Create table 组织机构
create table SYS_COMPANY
(
   id                   varchar(64) not null comment '主键',
   org_name             varchar(50) not null comment '组织机构名称',
   org_code             varchar(20) not null comment '组织机构代码',
   address              varchar(50) not null comment '联系地址',
   phone                varchar(20) not null comment '联系电话',
   link_man             varchar(10) not null comment '联系人',
   email                varchar(50) not null comment '邮箱',
   apply_status         char(1) comment '申请状态',
   parent_ids           varchar(2000),
   parent_id            varchar(64) comment '上级机构Id',
   del_flag             char(1) comment '标志',
   create_by            varchar(64) comment '创建人',
   create_date          datetime comment '创建时间',
   update_by            varchar(64) comment '更新人',
   update_date          datetime comment '更新时间',
   remarks              varchar(500) comment '备注信息',
   primary key (id)
);

alter table SYS_COMPANY comment '组织机构信息-SYS_COMPANY';
insert into `sys_company` (`id`, `org_name`, `org_code`, `address`, `phone`, `link_man`, `email`, `apply_status`, `parent_ids`, `parent_id`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('2ab87cb819a346bc8197fabf0a05709b','中国卫生集团','101','','','','','',NULL,'','0',NULL,'2017-02-25 10:31:41',NULL,'2017-02-25 10:31:41','');
insert into `sys_company` (`id`, `org_name`, `org_code`, `address`, `phone`, `link_man`, `email`, `apply_status`, `parent_ids`, `parent_id`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('4745915623bc439da8584b14857dae0b','安平医院','1001','','','','','',NULL,'2ab87cb819a346bc8197fabf0a05709b','0',NULL,'2017-02-25 10:31:55',NULL,'2017-02-25 10:31:55','');
