-- Create table 机构对应服务
create table company_vs_service
(
   company_id           varchar(64) not null comment '机构ID',
   service_id           varchar(64) not null comment '服务ID',
   primary key (company_id, service_id)
);

alter table company_vs_service comment '机构对应服务表company_vs_service';

alter table company_vs_service add constraint FK_Reference_18 foreign key (service_id)
      references SYS_SERVICE (id) on delete restrict on update restrict;

alter table company_vs_service add constraint FK_Reference_19 foreign key (company_id)
      references SYS_COMPANY (id) on delete restrict on update restrict;

insert into `company_vs_service` (`company_id`, `service_id`) values('4745915623bc439da8584b14857dae0b','5f3fb8a5f4884312b815cee18f953f97');
