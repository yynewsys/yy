-- Create table 组织人员
create table ORG_STAFF
(
   id                   varchar(64) not null comment '主键',
   org_id               varchar(64) not null comment '组织机构Id',
   user_id              varchar(64) not null comment '用户ID',
   dept_id              varchar(64)  comment '科室信息Id',
   title                varchar(20) comment '职称',
   del_flag             char(1) comment '标志',
   create_by            varchar(64) comment '创建人',
   create_date          datetime comment '创建时间',
   update_by            varchar(64) comment '更新人',
   update_date          datetime comment '更新时间',
   remarks              varchar(500) comment '备注信息',
   primary key (id)
);

alter table ORG_STAFF comment '人员与组织机构对照-ORG_STAFF';

alter table ORG_STAFF add constraint FK_Reference_17 foreign key (user_id)
      references sys_user (id) on delete restrict on update restrict;

alter table ORG_STAFF add constraint FK_Reference_6 foreign key (org_id)
      references SYS_COMPANY (id) on delete restrict on update restrict;

alter table ORG_STAFF add constraint FK_Reference_7 foreign key (dept_id)
      references ORG_DEPT (id) on delete restrict on update restrict;
insert into `org_staff` (`id`, `org_id`, `user_id`, `dept_id`, `title`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('d979b2caf4d44da29490299fefc268ed','4745915623bc439da8584b14857dae0b','d6030f93e3bb422fa848f23ff9d10cc4',NULL,NULL,'0','1','2017-02-25 10:32:34','1','2017-02-25 10:32:34',NULL);
insert into `org_staff` (`id`, `org_id`, `user_id`, `dept_id`, `title`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('fb01e6b556de48369d4516b343b1e6d0','4745915623bc439da8584b14857dae0b','caf20d9238f5495db2f46ccfed1e1894','5568c1ae6ef249c69c5cb8424723f7f2','234','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:22:42','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:22:42',NULL);
