-- Create table 用户信息表
create table person_info
(
   id                   varchar(64) not null comment '主键',
   name                 varchar(50) not null comment '姓名',
   sex                  varchar(2) comment '性别',
   id_card              varchar(50) not null comment '身份证号',
   phone                varchar(20) not null comment '联系电话',
   email                varchar(30) not null comment '邮箱',
   nick_name            varchar(30)  comment '昵称',
   input_code           varchar(20) comment '拼音码',
   del_flag             char(1) comment '标志',
   create_by            varchar(64) comment '创建人',
   create_date          datetime comment '创建时间',
   update_by            varchar(64) comment '更新人',
   update_date          datetime comment '更新时间',
   remarks              varchar(500) comment '备注信息',
   primary key (id)
);

alter table person_info comment '人员基础信息表---PERSON_INFO';

insert into `person_info` (`id`, `name`, `sex`, `id_card`, `phone`, `email`, `nick_name`, `input_code`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('1','系统管理员','1','130425199502021234','13800138000','8000@163.com','系统管理员',NULL,'0',NULL,NULL,NULL,NULL,NULL);
insert into `person_info` (`id`, `name`, `sex`, `id_card`, `phone`, `email`, `nick_name`, `input_code`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('6956bcb6b3f44c65b20bcc6390ee6fe6','张三','2','530422198511174812','13988998878','123@qq.com',NULL,'ZS','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:22:42','d6030f93e3bb422fa848f23ff9d10cc4','2017-03-20 11:22:42',NULL);
insert into `person_info` (`id`, `name`, `sex`, `id_card`, `phone`, `email`, `nick_name`, `input_code`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('a9e9f13f6d844179a1147e49e919dc32','安平系统管理员','2','510811197910204411','13566887784','123456789@qq.com',NULL,'APXTGLY','0','1','2017-02-25 10:32:34',NULL,'2017-02-25 10:32:34',NULL);
