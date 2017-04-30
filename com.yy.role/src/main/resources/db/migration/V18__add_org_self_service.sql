-- Create table 机构自定义服务表
create table ORG_SELF_SERVICE
(
   id                   varchar(64) not null comment '主键',
   org_id               varchar(64) not null comment '组织机构Id',
   service_name         varchar(50) not null comment '自定义服务名称',
   sort                  int comment '排序',
   del_flag             char(1) comment '标志',
   create_by            varchar(64) comment '创建人',
   create_date          datetime comment '创建时间',
   update_by            varchar(64) comment '更新人',
   update_date          datetime comment '更新时间',
   remarks              varchar(500) comment '备注信息',
   primary key (id)
);

alter table ORG_SELF_SERVICE comment '组织机构自定义服务-ORG_SELF_SERVICE';

alter table ORG_SELF_SERVICE add constraint FK_Reference_11 foreign key (org_id)
      references SYS_COMPANY (id) on delete restrict on update restrict;
insert into `org_self_service` (`id`, `org_id`, `service_name`, `sort`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('5033900b83f147d59b76fd648f61a1b2','4745915623bc439da8584b14857dae0b','药品服务','7','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:30:16','d6030f93e3bb422fa848f23ff9d10cc4','2017-04-21 15:26:23',NULL);
insert into `org_self_service` (`id`, `org_id`, `service_name`, `sort`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('5b2c5021249048e9b8c958751e3da286','4745915623bc439da8584b14857dae0b','手术服务','9','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-04-21 15:27:12','d6030f93e3bb422fa848f23ff9d10cc4','2017-04-21 15:27:12',NULL);
insert into `org_self_service` (`id`, `org_id`, `service_name`, `sort`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('a179de4e84f54f0a8c6253cb65e3dfc3','4745915623bc439da8584b14857dae0b','运维服务','2','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-02-25 10:58:19','d6030f93e3bb422fa848f23ff9d10cc4','2017-02-25 10:58:27',NULL);
insert into `org_self_service` (`id`, `org_id`, `service_name`, `sort`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('aa9695d772a045cea677dbcbb4ff8f5f','4745915623bc439da8584b14857dae0b','用血服务','10','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-04-21 15:27:21','d6030f93e3bb422fa848f23ff9d10cc4','2017-04-21 15:27:21',NULL);
insert into `org_self_service` (`id`, `org_id`, `service_name`, `sort`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('b559497211ed4de99d967810cb840152','4745915623bc439da8584b14857dae0b','收费服务','11','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:40:06','d6030f93e3bb422fa848f23ff9d10cc4','2017-04-21 15:29:02',NULL);
insert into `org_self_service` (`id`, `org_id`, `service_name`, `sort`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('c0546f5383c34c6d82c5004f3bd5746a','4745915623bc439da8584b14857dae0b','护士服务','5','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-04-21 15:25:26','d6030f93e3bb422fa848f23ff9d10cc4','2017-04-21 15:25:33',NULL);
insert into `org_self_service` (`id`, `org_id`, `service_name`, `sort`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('d10029e8fcd34d63a459a5f952137206','4745915623bc439da8584b14857dae0b','挂号服务','3','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-02-25 10:58:05','d6030f93e3bb422fa848f23ff9d10cc4','2017-02-25 10:58:30',NULL);
insert into `org_self_service` (`id`, `org_id`, `service_name`, `sort`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('d2d97efc06434a5799dfefa9049fbcf1','4745915623bc439da8584b14857dae0b','医生服务','4','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:30:04','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:30:08',NULL);
insert into `org_self_service` (`id`, `org_id`, `service_name`, `sort`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('db51177018234595ab8647d90603436c','4745915623bc439da8584b14857dae0b','系统服务','1','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-02-25 10:58:12','d6030f93e3bb422fa848f23ff9d10cc4','2017-02-25 10:58:23',NULL);
insert into `org_self_service` (`id`, `org_id`, `service_name`, `sort`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('dca0689556b048ee8b5bebcea5fd3f52','4745915623bc439da8584b14857dae0b','医技服务','6','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-04-21 15:26:03','d6030f93e3bb422fa848f23ff9d10cc4','2017-04-21 15:26:03',NULL);
insert into `org_self_service` (`id`, `org_id`, `service_name`, `sort`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('f237c5614ed344428a9d59d4cd4a89ae','4745915623bc439da8584b14857dae0b','药房服务','8','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-04-21 15:27:34','d6030f93e3bb422fa848f23ff9d10cc4','2017-04-21 15:28:53',NULL);
