-- Create table 机构人员与角色对照表
create table STAFF_VS_ROLE
(
   staff_id             varchar(64) not null comment 'org_staff 主键',
   role_id              varchar(64) not null comment 'org_role  主键'
);

alter table STAFF_VS_ROLE comment '组织机构人员与角色对照表-STAFF_VS_ROLE';

alter table STAFF_VS_ROLE add constraint FK_Reference_10 foreign key (role_id)
      references ORG_ROLE (id) on delete restrict on update restrict;

alter table STAFF_VS_ROLE add constraint FK_Reference_9 foreign key (staff_id)
      references ORG_STAFF (id) on delete restrict on update restrict;

