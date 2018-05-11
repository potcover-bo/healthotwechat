
--数据库语句
-- 血压表
CREATE TABLE `blood_pressure` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号码 用户关联用户',
  `high_pressure` varchar(5) NOT NULL DEFAULT '' COMMENT '高压值',
  `low_pressure` varchar(5) NOT NULL DEFAULT '' COMMENT '低压值',
  `meal_condition` varchar(3) NOT NULL DEFAULT '' COMMENT '1代表饭前 2代表饭后',
  `medicine_condition` varchar(3) NOT NULL DEFAULT '' COMMENT '1代表服药前 2代表服药后',
  `save_health_record` varchar(3) DEFAULT NULL COMMENT '1代表录入健康档案  2 代表不录入健康档案',
  `measure_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '测量时间',
  PRIMARY KEY (`id`),
  KEY `openid_index` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;


-- 血糖表
CREATE TABLE `blood_sugar` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号码 用户关联用户',
  `blood_sugar_value` varchar(5) NOT NULL DEFAULT '' COMMENT '血糖值',
  `meal_condition` varchar(3) NOT NULL DEFAULT '' COMMENT '1代表饭前 2 代表饭后',
  `medicine_condition` varchar(3) NOT NULL DEFAULT '' COMMENT '1代表服药前  2代表服药后',
  `save_health_record` varchar(3) DEFAULT NULL COMMENT '1代表录入健康档案  2 代表不录入健康档案',
  `measure_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '测量时间',
  PRIMARY KEY (`id`),
  KEY `openid_index` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- 身体指数表
CREATE TABLE `blood_sugar` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号码 用户关联用户',
  `blood_sugar_value` varchar(5) NOT NULL DEFAULT '' COMMENT '血糖值',
  `meal_condition` varchar(3) NOT NULL DEFAULT '' COMMENT '1代表饭前 2 代表饭后',
  `medicine_condition` varchar(3) NOT NULL DEFAULT '' COMMENT '1代表服药前  2代表服药后',
  `save_health_record` varchar(3) DEFAULT NULL COMMENT '1代表录入健康档案  2 代表不录入健康档案',
  `measure_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '测量时间',
  PRIMARY KEY (`id`),
  KEY `openid_index` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- 监护人表
CREATE TABLE `custody_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `phone` varchar(11) NOT NULL DEFAULT '' COMMENT '监护人手机号码',
  `idcard` varchar(18) DEFAULT '' COMMENT '监护人身份证号码',
  `username` varchar(10) DEFAULT '' COMMENT '监护人姓名',
  `password` varchar(18) DEFAULT '' COMMENT '监护人密码',
  `headimgurl` varchar(50) DEFAULT '' COMMENT '监护人头像',
  `sex` int(2) DEFAULT NULL COMMENT '用户性别 1位男性 2位女性 0为未知',
  `age` int(3) DEFAULT NULL COMMENT '监护人年龄',
  `custody_phone` varchar(20) DEFAULT '' COMMENT '被监护人phone',
  `custody_relationship` varchar(5) DEFAULT '' COMMENT '与被监护人的关系',
  PRIMARY KEY (`id`),
  KEY `phone_index` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='监护人表';

-- 服药表
CREATE TABLE `medicine` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号码 用于关联用户',
  `morning_medicine` varchar(15) DEFAULT '' COMMENT '早晨药物名字',
  `morning_number` varchar(6) DEFAULT NULL COMMENT '早晨药物数量',
  `noon_medicine` varchar(11) DEFAULT NULL COMMENT '中午药物名字',
  `noon_number` varchar(6) DEFAULT NULL COMMENT '中午药物数量',
  `night_medicine` varchar(11) DEFAULT NULL COMMENT '晚上药物名字',
  `night_number` varchar(6) DEFAULT NULL COMMENT '晚上药物数量',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `openid_index` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 心情表
CREATE TABLE `mood` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号码 用于关联用户',
  `morning_mood` varchar(2) NOT NULL DEFAULT '' COMMENT '早上心情 1为良好 2为一般 3为差',
  `noon_mood` varchar(2) NOT NULL DEFAULT '' COMMENT '中午心情 1为良好 2为一般 3为差',
  `night_mood` varchar(2) NOT NULL DEFAULT '' COMMENT '晚上心情  1为良好 2为一般 3为差',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `openid_index` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 睡眠表
CREATE TABLE `sleeping` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `phone` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号码 用于关联用户',
  `noon_time` varchar(2) NOT NULL DEFAULT '' COMMENT '午睡时间 单位为小时',
  `night_time` varchar(2) NOT NULL DEFAULT '' COMMENT '夜晚睡眠时间 单位为小时',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `openid_index` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 用户表
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `phone` varchar(11) NOT NULL DEFAULT '' COMMENT '用户手机号码',
  `idcard` varchar(18) DEFAULT '' COMMENT '用户身份证号码',
  `headimgurl` varchar(50) DEFAULT '' COMMENT '用户头像',
  `username` varchar(10) DEFAULT '' COMMENT '用户姓名',
  `password` varchar(18) DEFAULT '' COMMENT '用户密码',
  `sex` varchar(2) DEFAULT NULL COMMENT '用户性别 1位男性 2位女性 0为未知',
  `age` varchar(5) DEFAULT NULL COMMENT '用户年龄',
  `custody_phone` varchar(11) DEFAULT '' COMMENT '监护人手机号码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_unique` (`phone`),
  KEY `phone_index` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
