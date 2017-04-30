-- Create table 机构科室
create table ORG_DEPT
(
   id                   varchar(64) not null comment '主键',
   parent_id            varchar(64) comment '父级ID',
   parent_ids           varchar(2000) comment '父级ID集合',
   dept_name            varchar(100) not null comment '科室名称',
   dept_code            varchar(20) not null comment '科室code',
   dept_propertity      char(1) comment '科室属性',
   org_id               varchar(64) comment '机构id',
   input_code           varchar(100) comment '拼音码',
   clinic_attr          char(1) comment '临床标识',
   sort                 int comment '排序（升序）',
   outp_or_inp          char(1) comment '门诊住院科室属性',
   internal_or_serger   char(1) comment '内外科标识',
   del_flag             char(1) comment '标志',
   create_by            varchar(64) comment '创建人',
   create_date          datetime comment '创建时间',
   update_by            varchar(64) comment '更新人',
   update_date          datetime comment '更新时间',
   remarks              varchar(500) comment '备注信息',
   primary key (id)
);

alter table ORG_DEPT comment '机构科室-ORG_DEPT';
insert into `org_dept` (`id`, `parent_id`, `parent_ids`, `dept_name`, `dept_code`, `dept_propertity`, `org_id`, `input_code`, `clinic_attr`, `sort`, `outp_or_inp`, `internal_or_serger`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('144414001b5e4a629098a875915932e8','0','0,','骨科门诊','1001',NULL,'4745915623bc439da8584b14857dae0b','GKMZ','0','30','0','1','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-02-25 10:56:07','d6030f93e3bb422fa848f23ff9d10cc4','2017-02-25 10:56:07','');
insert into `org_dept` (`id`, `parent_id`, `parent_ids`, `dept_name`, `dept_code`, `dept_propertity`, `org_id`, `input_code`, `clinic_attr`, `sort`, `outp_or_inp`, `internal_or_serger`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('5568c1ae6ef249c69c5cb8424723f7f2','144414001b5e4a629098a875915932e8','0,144414001b5e4a629098a875915932e8,','骨科一','1002',NULL,'4745915623bc439da8584b14857dae0b','GKY','0','30','0','1','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-02-25 10:56:29','d6030f93e3bb422fa848f23ff9d10cc4','2017-02-25 10:56:29','');
insert into `org_dept` (`id`, `parent_id`, `parent_ids`, `dept_name`, `dept_code`, `dept_propertity`, `org_id`, `input_code`, `clinic_attr`, `sort`, `outp_or_inp`, `internal_or_serger`, `del_flag`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`) values('dc17ce201c6e4164a64ebbc4d3a74188','144414001b5e4a629098a875915932e8','0,144414001b5e4a629098a875915932e8,','骨科二','1003',NULL,'4745915623bc439da8584b14857dae0b','GKE','0','30','0','1','0','d6030f93e3bb422fa848f23ff9d10cc4','2017-02-25 10:56:51','d6030f93e3bb422fa848f23ff9d10cc4','2017-02-25 10:56:51','');
