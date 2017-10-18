CREATE USER 'freecrm'@'localhost';
SET PASSWORD FOR 'freecrm'@'localhost' = PASSWORD('freecrm');

GRANT SELECT ON freecrm.* TO 'freecrm'@'localhost';
GRANT ALL on freecrm TO 'freecrm'@'localhost';

CREATE TABLE custom_info (
	id int(11) not null AUTO_INCREMENT COMMENT '',
    cus_name varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    cus_type varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    linkman varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    address varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    areas varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    create_time datetime null COMMENT '',
    leader varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    primary key (id)
) ENGINE = INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '';

CREATE TABLE project_info (
	id int(11) not null AUTO_INCREMENT COMMENT '',
    proname varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    define_time datetime null,
    leader varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    contract_amount decimal,
    expected_month decimal,
    schedule varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    schedule_time datetime null,
    actual_amount decimal,
    paid_amount decimal,
    return_amount decimal,
    paymentplan datetime,
    projectcycle int,
    contract_id varchar(50),
    primary key (id)
) ENGINE = INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '';

CREATE TABLE project_detail (
	id int(11) not null AUTO_INCREMENT COMMENT '',
    contact_time datetime,
    contactway varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    participant varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    salescontent varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    propulsionplan varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    propulstime datetime,
    primary key (id)
) ENGINE = INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '';

CREATE TABLE contract_info (
	id int(11) not null AUTO_INCREMENT COMMENT '',
	product_name varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '' not null,
	product_Cnt varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '' not null,
	company_name varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '' not null,
	price varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '' not null,
	sum_money varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '' not null,
	rebate varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '' not null,
	remark varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '' not null,
	confirm_confirm varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '' not null,
	return_id varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '' not null,
	invoice_id varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '' not null,
    primary key (id)
) ENGINE = INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '';

CREATE TABLE phonebook_info (
	id int(11) not null AUTO_INCREMENT COMMENT '',
	p_name varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '姓名' not null,
	p_duties varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '职务' not null,
	p_depart varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '所属部门' not null,
	p_tel varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '电话' not null,
	p_mail varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '邮件' not null,
    primary key (id)
) ENGINE = INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '联系人表';

CREATE TABLE payment_info (
	id int(11) not null AUTO_INCREMENT COMMENT '',
	money varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '' not null,
	proportion varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '' not null,
	residue varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '' not null,
	payment_date varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '' not null,
	type varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '' not null,
    primary key (id)
) ENGINE = INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '';

CREATE TABLE invoice_info (
	id int(11) not null AUTO_INCREMENT COMMENT '',
	invoice_id varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '' not null,
	money varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '' not null,
	invoice_type varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '' not null,
	invoice_date varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '' not null,
    primary key (id)
) ENGINE = INNODB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '';

CREATE TABLE user_info (
	id int(11) not null AUTO_INCREMENT COMMENT '自增id',
	login_name varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci not null COMMENT '登录用户名',
	login_pwd varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci not null COMMENT '登陆密码',
    nick_name varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci not null COMMENT '用户昵称',
	user_status int(1) not null default 0 COMMENT '0:正常, 1:冻结, 2:删除',
    primary key (id),
    key (login_name)
) ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表';

INSERT INTO `freecrm`.`user_info` VALUES (null, 'admin', 'admin', 'admin', 0);
INSERT INTO `freecrm`.`user_info` VALUES (null, '测试用户', '测试密码', '测试用户', default);