-- Create table 所有服务表
create table sys_service
(
   id                   varchar(64) not null comment '主键',
   server_name          varchar(20) not null comment '服务名称',
   service_type         char(1) not null comment '服务类型',
   service_class        char(1) not null comment '服务类别',
   service_image        varchar(50) comment '服务图片路径',
   description          varchar(1000) comment '服务描述',
   del_flag             char(1) comment '标志',
   create_by            varchar(64) comment '创建人',
   create_date          datetime comment '创建时间',
   update_by            varchar(64) comment '更新人',
   update_date          datetime comment '更新时间',
   remarks              varchar(500) comment '备注信息',
   primary key (id)
);

alter table SYS_SERVICE comment '系统所有服务-SYS_SERVICE';
insert into `sys_service` (`id`, `server_name`, `service_type`, `service_class`, `service_image`, `description`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('5f3fb8a5f4884312b815cee18f953f97','医院服务','1','1','','','0','1','2017-02-25 10:43:20','1','2017-02-25 10:43:20',NULL);
