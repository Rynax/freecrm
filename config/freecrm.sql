CREATE USER 'freecrm'@'localhost';
SET PASSWORD FOR 'freecrm'@'localhost' = PASSWORD('freecrm');

GRANT SELECT ON freecrm.* TO 'freecrm'@'localhost';
GRANT ALL on freecrm.* TO 'freecrm'@'localhost';

CREATE SCHEMA `freecrm` DEFAULT CHARACTER SET utf8 ;

DROP TABLE IF EXISTS `freecrm`.`custom_info`;
DROP TABLE IF EXISTS `freecrm`.`project_info`;
DROP TABLE IF EXISTS `freecrm`.`project_detail`;
DROP TABLE IF EXISTS `freecrm`.`contract_info`;
DROP TABLE IF EXISTS `freecrm`.`phonebook_info`;
DROP TABLE IF EXISTS `freecrm`.`payment_info`;
DROP TABLE IF EXISTS `freecrm`.`invoice_info`;
DROP TABLE IF EXISTS `freecrm`.`user_info`;


CREATE TABLE custom_info (
	id int(11) AUTO_INCREMENT COMMENT '' not null,
    cus_name varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '客户全称' not null,
    cus_type varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '客户类型' not null,
    linkman varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '客户联系人' not null,
    address varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '地址' not null,
    areas varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '区域' not null,
    create_time datetime default now() COMMENT '创建时间' not null,
    leader varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '负责人' not null,
    primary key (id),
    key (cus_name)
) ENGINE = INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户表';

INSERT INTO `freecrm`.`custom_info` VALUES (null, '苦逼1号', '苦逼', '苦逼1号业务员', '中国', '亚洲', default, '售后1号');
INSERT INTO `freecrm`.`custom_info` VALUES (null, '逗逼1号', '逗逼', '逗逼1号业务员', '某地', '北方', default, '售后2号');


CREATE TABLE project_info (
	id int(11) AUTO_INCREMENT COMMENT '' not null,
    proname varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '项目名称' not null,
    define_time datetime default now() COMMENT '立项时间',
    leader varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '负责人' not null,
    contract_amount varchar(50) COMMENT '预计合同额',
    expected_month varchar(50) COMMENT '预计成交月',
    schedule varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '进度' not null,
    schedule_time datetime default now() COMMENT '进度时间',
    actual_amount varchar(50) COMMENT '实际合同额',
    paid_amount varchar(50) COMMENT '支出金额',
    return_amount varchar(50) COMMENT '回款额',
    paymentplan datetime default now() COMMENT '回款计划',
    projectcycle int COMMENT '项目周期',
    contract_id varchar(50) COMMENT '合同ID',
    primary key (id)
) ENGINE = INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目表';

INSERT INTO `freecrm`.`project_info` VALUES (null, '逗逼1号项目', '2017-09-15 10:12:15', '逗逼1号', '10000', '5', '50%', '2017-10-12 18:30:15', '15000', '7000', '5000', '2017-12-12 20:30:15', 5, '1');


CREATE TABLE project_detail (
	id int(11) AUTO_INCREMENT COMMENT '' not null,
    contact_time datetime default now() COMMENT '联系时间',
    contactway varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '联系方式' not null,
    participant varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '客户参与人' not null,
    salescontent varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '销售行动内容' not null,
    propulsionplan varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '推进计划' not null,
    propulstime datetime default now() COMMENT '推进时间',
    primary key (id)
) ENGINE = INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '销售记录表';

INSERT INTO `freecrm`.`project_detail` VALUES (null, '2017-09-05 10:12:15', '123456789', '逗逼1号', '内容', '计划', '2017-11-12 18:12:15');


CREATE TABLE contract_info (
	id int(11) AUTO_INCREMENT COMMENT '' not null,
	product_name varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '产品名称' not null,
	product_Cnt varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '数量' not null,
	company_name varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '公司' not null,
	price varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '单价' not null,
	sum_money varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '总金额' not null,
	rebate varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '折扣' not null,
	remark varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '备注' not null,
	confirm_confirm varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '确认发货' not null,
	return_id varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '回款信息' not null,
	invoice_id varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '发票信息' not null,
    primary key (id),
    key (product_name)
) ENGINE = INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '合同表';

INSERT INTO `freecrm`.`contract_info` VALUES (null, '苦逼摇摇乐', '10', '苦逼智商有限公司', '999.91', '9999.10', '1', '苦逼最爱', '已发货', '1', '1');
INSERT INTO `freecrm`.`contract_info` VALUES (null, '逗逼刮刮乐', '100', '逗逼人傻钱多智商低有限公司', '21.21', '2121.00', '1', '谁买谁逗逼', '已发货', '1', '1');


CREATE TABLE phonebook_info (
	id int(11) AUTO_INCREMENT COMMENT '' not null,
	p_name varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '姓名' not null,
	p_duties varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '职务' not null,
	p_depart varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '所属部门' not null,
	p_tel varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '电话' not null,
	p_mail varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '邮件' not null,
    primary key (id),
    key (p_name)
) ENGINE = INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '联系人表';

INSERT INTO `freecrm`.`phonebook_info` VALUES (null, '测试苦逼', '测试工程师', '测试部', '13012345678', 'db@abc.123');
INSERT INTO `freecrm`.`phonebook_info` VALUES (null, '测试老大', '测试经理', '测试部', '13987654321', 'db@123.com');


CREATE TABLE payment_info (
	id int(11) AUTO_INCREMENT COMMENT '' not null,
	money varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '金额' not null,
	proportion varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '回款比例' not null,
	residue varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '剩余应收款' not null,
	payment_date varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '日期' not null,
	type varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '类型' not null,
    primary key (id)
) ENGINE = INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '回款信息表';

INSERT INTO `freecrm`.`payment_info` VALUES (null, '10000', '80%', '2000', '2017-11-11', '类型');


CREATE TABLE invoice_info (
	id int(11) AUTO_INCREMENT COMMENT '' not null,
	invoice_id varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '发票编号' not null,
	money varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '金额' not null,
	invoice_type varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '发票类型' not null,
	invoice_date varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '开票时间' not null,
    primary key (id),
    key (invoice_id)
) ENGINE = INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '发票信息表';

INSERT INTO `freecrm`.`invoice_info` VALUES (null, '1234567', '10000', '增值税', '2017');


CREATE TABLE user_info (
	id int(11) AUTO_INCREMENT COMMENT '自增id' not null,
	login_name varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '登录用户名' not null,
	login_pwd varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '登陆密码' not null,
    nick_name varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '用户昵称' not null,
	user_status int(1) default 0 COMMENT '0:正常, 1:冻结, 2:删除' not null,
    primary key (id),
    key (login_name)
) ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表';

INSERT INTO `freecrm`.`user_info` VALUES (null, 'admin', 'admin', 'admin', 0);
INSERT INTO `freecrm`.`user_info` VALUES (null, '测试用户', '测试密码', '测试用户', default);
