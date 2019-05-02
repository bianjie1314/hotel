/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : syshotel

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2019-05-02 12:37:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for evelate
-- ----------------------------
DROP TABLE IF EXISTS `evelate`;
CREATE TABLE `evelate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '评价内容',
  `order_id` int(11) DEFAULT NULL COMMENT '订单主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(11) DEFAULT NULL COMMENT '审核状态,1待审核,2审核通过，3审核拒绝',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of evelate
-- ----------------------------
INSERT INTO `evelate` VALUES ('1', '1', '1', '2019-02-08 01:35:20', '2', '2019-04-20 13:27:48');
INSERT INTO `evelate` VALUES ('2', '1', '2', '2019-02-08 01:35:22', '1', '2019-02-08 01:35:26');
INSERT INTO `evelate` VALUES ('3', '1', '7', '2019-02-08 01:35:22', '3', '2019-04-20 13:28:32');
INSERT INTO `evelate` VALUES ('4', 'c 测', '13', '2019-04-21 14:38:35', '1', null);

-- ----------------------------
-- Table structure for hotel
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '酒店名称',
  `user_id` int(11) DEFAULT NULL COMMENT '所属用户',
  `status` int(11) DEFAULT NULL COMMENT '状态,1正常，2下线',
  `location` varchar(255) DEFAULT NULL COMMENT '地理位置',
  `picture_ids` varchar(255) DEFAULT NULL COMMENT '图片',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `open_time` varchar(128) DEFAULT NULL COMMENT '营业开始时间描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES ('1', '测试1', '2', '1', '北京', null, '2019-03-24 15:42:03', '2019-03-24 16:04:01', '24小时营业');
INSERT INTO `hotel` VALUES ('2', '测试2', '5', '1', '北京', null, '2019-03-25 12:42:03', '2019-04-20 12:56:53', '24小时营业');
INSERT INTO `hotel` VALUES ('3', '测试32', '5', '1', '上海2', ',5', '2019-03-20 12:42:03', '2019-04-20 12:54:56', '24小时营业');
INSERT INTO `hotel` VALUES ('4', 'ces', '5', '1', '上海松江', ',9', '2019-04-18 23:07:58', '2019-04-20 12:35:45', '24小时营业');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '名称',
  `url` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '链接',
  `icon` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '图标',
  `status` int(11) DEFAULT NULL COMMENT '状态,1正常,2禁用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `parent_id` int(11) DEFAULT NULL COMMENT '所属父菜单id',
  `index` int(11) DEFAULT NULL COMMENT '顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('2', '财务管理', null, '&#xe620;', '1', '2019-01-26 16:44:41', '2019-01-26 16:44:51', null, '5');
INSERT INTO `menu` VALUES ('3', '用户管理', '', '&#xe60d;', '1', '2019-01-26 16:44:44', '2019-01-26 16:44:53', null, '4');
INSERT INTO `menu` VALUES ('4', '系统设置', null, '&#xe62e;', '1', '2019-01-26 16:44:46', '2019-01-26 16:44:55', null, '7');
INSERT INTO `menu` VALUES ('6', '房间列表', '/room/getRoomList', '', '1', '2019-01-20 21:27:53', '2019-03-24 16:09:32', '8', '2');
INSERT INTO `menu` VALUES ('7', '菜单列表', '/menu/getMenuList', '', '1', '2019-01-26 16:44:48', '2019-01-26 16:44:57', '4', '2');
INSERT INTO `menu` VALUES ('8', '酒店管理', '', '', '1', '2019-01-26 16:44:48', '2019-03-24 14:52:37', null, '2');
INSERT INTO `menu` VALUES ('9', '订单管理', '', '&#xe627;', '1', '2019-01-26 16:44:48', '2019-01-26 16:44:57', null, '3');
INSERT INTO `menu` VALUES ('10', '订单列表', '/order/getOrderList', '', '1', '2019-01-26 16:44:48', '2019-01-26 16:44:57', '9', '1');
INSERT INTO `menu` VALUES ('11', '酒店列表', '/hotel/getHotelList', '', '1', '2019-01-26 16:44:48', '2019-03-24 14:53:07', '8', '1');
INSERT INTO `menu` VALUES ('12', '用户列表', '/user/getUserList', '', '1', '2019-01-26 16:44:48', '2019-01-26 16:44:57', '3', '1');
INSERT INTO `menu` VALUES ('13', '交易列表', '/wallet/getWalletList', '', '1', '2019-01-26 16:44:48', '2019-01-26 16:44:57', '2', '1');
INSERT INTO `menu` VALUES ('14', '角色设置', '/role/getRoleList', '', '1', '2019-01-26 16:44:48', '2019-01-26 16:44:57', '4', '1');
INSERT INTO `menu` VALUES ('15', '评价列表', '/evelate/getEvelateList', '', '1', '2019-01-26 16:44:48', '2019-01-26 16:44:57', '9', '2');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_code` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '订单编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `room_id` int(11) DEFAULT NULL COMMENT '房间编号',
  `pay` float(10,2) DEFAULT '0.00' COMMENT '支付费用',
  `status` int(11) DEFAULT NULL COMMENT '状态，1待支付,2:预订中,3正常入住,4取消订单,5延长入住时间，6已退房，7已评价',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1', '0X201812112222', '1', '1', '4222.00', '1', '2019-01-27 12:51:13', '2019-01-27 12:51:15');
INSERT INTO `orders` VALUES ('2', '0X201812112223', '1', '2', '33.45', '5', '2019-01-27 12:51:13', '2019-04-20 17:33:31');
INSERT INTO `orders` VALUES ('3', '0X201812112224', '2', '3', '33.20', '10', '2019-01-27 12:51:13', '2019-01-27 22:29:54');
INSERT INTO `orders` VALUES ('4', '0X201812112255', '2', '4', '33.20', '10', '2019-01-27 12:51:13', '2019-01-27 22:36:16');
INSERT INTO `orders` VALUES ('5', '0X201902112222', '1', '3', '33.20', '6', '2019-01-27 12:51:13', '2019-04-20 17:25:29');
INSERT INTO `orders` VALUES ('6', '0X201902112221', '1', '4', '999.00', '3', '2019-01-27 12:51:13', '2019-04-20 16:00:07');
INSERT INTO `orders` VALUES ('7', '0X201902113333', '5', '2', '999.00', '4', '2019-01-27 12:51:13', '2019-04-20 15:59:21');
INSERT INTO `orders` VALUES ('8', '0X201902113333', '6', '1', '100.00', '4', '2019-03-22 12:51:13', '2019-04-20 15:52:46');
INSERT INTO `orders` VALUES ('9', '201904202234231555770863120', '6', '1', '1645.00', '4', '2019-04-20 22:34:18', '2019-04-21 12:28:11');
INSERT INTO `orders` VALUES ('10', '201904211237521555821472594', '6', '2', '1410.00', '4', '2019-04-21 12:37:51', '2019-04-21 13:25:53');
INSERT INTO `orders` VALUES ('11', '201904211332551555824775855', '6', '2', '1175.00', '4', '2019-04-21 13:32:56', '2019-04-21 13:33:41');
INSERT INTO `orders` VALUES ('12', '201904211332561555824776218', '6', '2', '1175.00', '4', '2019-04-21 13:32:56', '2019-04-21 13:33:10');
INSERT INTO `orders` VALUES ('13', '201904211335231555824923297', '6', '3', '3055.00', '7', '2019-04-21 13:35:23', '2019-04-21 14:38:35');
INSERT INTO `orders` VALUES ('14', '201904211352151555825935823', '6', '2', '3760.00', '5', '2019-04-21 13:52:16', '2019-04-21 18:41:15');

-- ----------------------------
-- Table structure for order_process_log
-- ----------------------------
DROP TABLE IF EXISTS `order_process_log`;
CREATE TABLE `order_process_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL COMMENT '订单主键',
  `status` int(11) DEFAULT NULL COMMENT '状态，1待支付,2:预订中,3正常入住,4取消订单,5延长入住时间，6已退房，7已评价',
  `do_user_id` int(11) DEFAULT NULL COMMENT '操作人',
  `note` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建订单时间',
  `start_time` datetime DEFAULT NULL COMMENT '入住开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '入住结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of order_process_log
-- ----------------------------
INSERT INTO `order_process_log` VALUES ('1', '4', '10', '0', null, '2019-01-27 22:36:16', null, null);
INSERT INTO `order_process_log` VALUES ('2', '2', '3', '0', null, '2019-01-27 23:05:19', null, null);
INSERT INTO `order_process_log` VALUES ('3', '6', '2', '1', null, '2019-03-09 00:20:30', '2019-04-10 14:45:49', '2019-04-12 14:45:53');
INSERT INTO `order_process_log` VALUES ('4', '8', '4', '2', null, '2019-04-20 15:52:46', null, null);
INSERT INTO `order_process_log` VALUES ('5', '7', '4', '2', null, '2019-04-20 15:59:20', null, null);
INSERT INTO `order_process_log` VALUES ('6', '6', '3', '2', null, '2019-04-20 16:00:07', null, null);
INSERT INTO `order_process_log` VALUES ('7', '5', '5', '2', null, '2019-04-20 17:22:15', null, null);
INSERT INTO `order_process_log` VALUES ('8', '5', '6', '2', null, '2019-04-20 17:25:29', null, null);
INSERT INTO `order_process_log` VALUES ('9', '2', '5', '2', null, '2019-04-20 17:33:01', '2019-04-20 08:00:00', '2019-04-27 08:00:00');
INSERT INTO `order_process_log` VALUES ('10', '2', '5', '2', null, '2019-04-20 17:33:31', '2019-04-20 08:00:00', '2019-04-29 08:00:00');
INSERT INTO `order_process_log` VALUES ('11', '1', '1', '9', null, '2019-04-20 22:34:23', '2019-04-20 00:00:00', '2019-04-27 00:00:00');
INSERT INTO `order_process_log` VALUES ('12', '9', '4', '6', null, '2019-04-21 12:28:11', null, null);
INSERT INTO `order_process_log` VALUES ('13', '1', '1', '10', null, '2019-04-21 12:37:53', '2019-04-21 00:00:00', '2019-04-27 00:00:00');
INSERT INTO `order_process_log` VALUES ('14', '10', '2', '6', null, '2019-04-21 13:09:23', null, null);
INSERT INTO `order_process_log` VALUES ('15', '10', '2', '6', null, '2019-04-21 13:09:23', null, null);
INSERT INTO `order_process_log` VALUES ('16', '10', '4', '6', null, '2019-04-21 13:25:53', null, null);
INSERT INTO `order_process_log` VALUES ('17', '1', '1', '11', null, '2019-04-21 13:32:56', '2019-04-21 00:00:00', '2019-04-26 00:00:00');
INSERT INTO `order_process_log` VALUES ('18', '1', '1', '12', null, '2019-04-21 13:32:56', '2019-04-21 00:00:00', '2019-04-26 00:00:00');
INSERT INTO `order_process_log` VALUES ('19', '12', '4', '6', null, '2019-04-21 13:33:10', null, null);
INSERT INTO `order_process_log` VALUES ('20', '11', '2', '6', null, '2019-04-21 13:33:15', null, null);
INSERT INTO `order_process_log` VALUES ('21', '11', '2', '6', null, '2019-04-21 13:33:15', null, null);
INSERT INTO `order_process_log` VALUES ('22', '11', '4', '6', null, '2019-04-21 13:33:41', null, null);
INSERT INTO `order_process_log` VALUES ('23', '1', '1', '13', null, '2019-04-21 13:35:23', '2019-04-21 00:00:00', '2019-05-04 00:00:00');
INSERT INTO `order_process_log` VALUES ('24', '13', '2', '6', null, '2019-04-21 13:35:31', null, null);
INSERT INTO `order_process_log` VALUES ('25', '13', '2', '6', null, '2019-04-21 13:35:31', null, null);
INSERT INTO `order_process_log` VALUES ('26', '13', '3', '2', null, '2019-04-21 13:37:32', null, null);
INSERT INTO `order_process_log` VALUES ('27', '1', '1', '14', null, '2019-04-21 13:52:16', '2019-04-21 00:00:00', '2019-04-26 00:00:00');
INSERT INTO `order_process_log` VALUES ('28', '14', '2', '6', null, '2019-04-21 13:52:21', null, null);
INSERT INTO `order_process_log` VALUES ('29', '14', '2', '6', null, '2019-04-21 13:52:21', null, null);
INSERT INTO `order_process_log` VALUES ('30', '14', '3', '2', null, '2019-04-21 13:52:56', null, null);
INSERT INTO `order_process_log` VALUES ('31', '13', '5', '2', null, '2019-04-21 13:54:25', '2019-04-29 08:00:00', '2019-05-11 08:00:00');
INSERT INTO `order_process_log` VALUES ('32', '13', '6', '2', null, '2019-04-21 13:58:41', null, null);
INSERT INTO `order_process_log` VALUES ('33', '13', '7', '6', null, '2019-04-21 14:38:35', null, null);
INSERT INTO `order_process_log` VALUES ('35', '14', '5', '6', null, '2019-04-21 18:41:15', '2019-05-04 08:00:00', '2019-05-15 08:00:00');

-- ----------------------------
-- Table structure for picture
-- ----------------------------
DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path_url` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '存储路径',
  `type` int(11) DEFAULT NULL COMMENT '类型,1:房间照片信息,2:用户评价照片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of picture
-- ----------------------------
INSERT INTO `picture` VALUES ('1', '/upload/1554813335646r3.jpg', '1', '2019-04-09 20:35:36');
INSERT INTO `picture` VALUES ('2', '/upload/1554813345207lo.png', '1', '2019-04-09 20:35:45');
INSERT INTO `picture` VALUES ('3', '/upload/1555598147911r4.jpg', '1', '2019-04-18 22:35:48');
INSERT INTO `picture` VALUES ('4', '/upload/1555598668635r5.jpg', '1', '2019-04-18 22:44:29');
INSERT INTO `picture` VALUES ('5', '/upload/1555598859395r4.jpg', '1', '2019-04-18 22:47:39');
INSERT INTO `picture` VALUES ('6', '/upload/1555599723283r2.jpg', '1', '2019-04-18 23:02:03');
INSERT INTO `picture` VALUES ('7', '/upload/1555599935297r4.jpg', '1', '2019-04-18 23:05:35');
INSERT INTO `picture` VALUES ('8', '/upload/1555600075087r1.jpg', '1', '2019-04-18 23:07:55');
INSERT INTO `picture` VALUES ('9', '/upload/1555600112235r5.jpg', '1', '2019-04-18 23:08:32');
INSERT INTO `picture` VALUES ('10', '/upload/1555730593583r3.jpg', '1', '2019-04-20 11:23:14');
INSERT INTO `picture` VALUES ('11', '/upload/1555731939606r2.jpg', '1', '2019-04-20 11:45:40');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '角色名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员', '2019-01-20 21:32:51', '2019-01-20 21:32:58');
INSERT INTO `role` VALUES ('2', '店家', '2019-01-20 21:32:54', '2019-01-20 21:33:01');
INSERT INTO `role` VALUES ('3', '普通用户', '2019-01-20 21:32:56', '2019-01-20 21:33:03');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单编号',
  `role_id` int(11) DEFAULT NULL COMMENT '角色编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('32', '2', '1', '2019-03-07 18:41:10', null);
INSERT INTO `role_menu` VALUES ('33', '3', '1', '2019-03-07 18:41:10', null);
INSERT INTO `role_menu` VALUES ('34', '4', '1', '2019-03-07 18:41:10', null);
INSERT INTO `role_menu` VALUES ('36', '6', '1', '2019-03-07 18:41:10', null);
INSERT INTO `role_menu` VALUES ('37', '7', '1', '2019-03-07 18:41:10', null);
INSERT INTO `role_menu` VALUES ('38', '8', '1', '2019-03-07 18:41:10', null);
INSERT INTO `role_menu` VALUES ('39', '9', '1', '2019-03-07 18:41:10', null);
INSERT INTO `role_menu` VALUES ('40', '10', '1', '2019-03-07 18:41:10', null);
INSERT INTO `role_menu` VALUES ('41', '11', '1', '2019-03-07 18:41:10', null);
INSERT INTO `role_menu` VALUES ('42', '12', '1', '2019-03-07 18:41:10', null);
INSERT INTO `role_menu` VALUES ('43', '13', '1', '2019-03-07 18:41:10', null);
INSERT INTO `role_menu` VALUES ('44', '14', '1', '2019-03-07 18:41:10', null);
INSERT INTO `role_menu` VALUES ('45', '15', '1', '2019-03-07 18:41:10', null);
INSERT INTO `role_menu` VALUES ('46', '6', '2', '2019-03-25 23:15:52', null);
INSERT INTO `role_menu` VALUES ('47', '8', '2', '2019-03-25 23:15:52', null);
INSERT INTO `role_menu` VALUES ('48', '9', '2', '2019-03-25 23:15:52', null);
INSERT INTO `role_menu` VALUES ('49', '10', '2', '2019-03-25 23:15:52', null);
INSERT INTO `role_menu` VALUES ('50', '11', '2', '2019-03-25 23:15:52', null);

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(11) DEFAULT NULL COMMENT '房间编号',
  `status` int(11) DEFAULT NULL COMMENT '状态,1正常，2使用中，3下线',
  `picture_ids` varchar(128) DEFAULT NULL,
  `hotel_id` int(11) DEFAULT NULL COMMENT '所属酒店编号',
  `area` double DEFAULT NULL COMMENT '房间面积',
  `toilet` int(11) DEFAULT NULL COMMENT '是否有独卫,1有，2没有',
  `window` int(11) DEFAULT NULL COMMENT '是否有窗户,1有，2没有',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `money` float(10,2) DEFAULT NULL COMMENT '房费',
  `deposit` float(10,2) DEFAULT NULL COMMENT '押金',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('1', '2011', '1', null, '1', '22', '1', '1', '2019-03-24 19:52:08', null, '120.00', '100.00');
INSERT INTO `room` VALUES ('2', 'S2012', '1', null, '1', '45', '1', '1', '2019-03-24 19:52:08', '2019-04-20 12:54:20', '235.00', '101.00');
INSERT INTO `room` VALUES ('3', 'S2011', '1', null, '2', '45', '1', '1', '2019-03-24 19:52:08', '2019-04-21 13:58:41', '235.00', '101.00');
INSERT INTO `room` VALUES ('4', 'S2013', '1', null, '2', '25', '0', '1', '2019-03-24 19:52:08', '2019-03-24 23:25:35', '300.00', '100.00');
INSERT INTO `room` VALUES ('5', 'X1221', '1', ',1,2', '2', '11', '1', '1', '2019-04-09 20:36:13', null, '111.00', '111.00');
INSERT INTO `room` VALUES ('6', 'SO101', '2', ',10', '1', '13', '2', '2', '2019-04-20 11:23:36', '2019-04-20 11:48:37', '121.00', '123.00');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(13) CHARACTER SET utf8 DEFAULT NULL COMMENT '手机号',
  `username` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '姓名',
  `password` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '密码',
  `role_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '角色编号',
  `identity` varchar(20) DEFAULT NULL COMMENT '身份证号码',
  `balance` double(10,2) DEFAULT NULL COMMENT '账户余额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '18828282822', 'lin', '123', '1', '350521189009118931', null, '2018-11-17 19:04:56', '2019-02-07 11:00:51');
INSERT INTO `user` VALUES ('2', '13110877374', 'test', '123', '1', '350521189009118931', null, '2018-11-17 21:23:48', '2019-04-20 13:13:28');
INSERT INTO `user` VALUES ('4', '13110877371', '13110877374', '123', '1', '350521189009118933', null, '2019-03-02 17:27:37', null);
INSERT INTO `user` VALUES ('5', '13110877372', '哈喽', '123', '2', '350521189009118933', null, '2019-03-13 17:27:37', null);
INSERT INTO `user` VALUES ('6', '13110877375', '测试用户1', '123', '3', '350521189009118933', '95770.00', '2019-03-13 17:27:37', '2019-04-21 18:41:15');
INSERT INTO `user` VALUES ('7', '13110877321', 'cw', '123', '3', '111111111111111111', '111.00', '2019-04-20 13:04:27', null);

-- ----------------------------
-- Table structure for wallet_log
-- ----------------------------
DROP TABLE IF EXISTS `wallet_log`;
CREATE TABLE `wallet_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `order_id` int(11) DEFAULT NULL COMMENT '订单号主键',
  `money` double(12,2) DEFAULT NULL COMMENT '钱包',
  `type` int(11) DEFAULT '0' COMMENT '类型，1充值，2付款，3退款',
  `note` varchar(128) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of wallet_log
-- ----------------------------
INSERT INTO `wallet_log` VALUES ('1', '2', '2', '22.22', '1', null, '2019-03-25 23:09:57');
INSERT INTO `wallet_log` VALUES ('2', '2', '1', '33.00', '2', null, '2019-03-25 23:10:01');
INSERT INTO `wallet_log` VALUES ('3', '5', '7', '999.00', '3', '订单取消退款', '2019-04-20 15:59:21');
INSERT INTO `wallet_log` VALUES ('4', '6', '0', '200.00', '1', '账户充值', '2019-04-21 12:49:11');
INSERT INTO `wallet_log` VALUES ('5', '6', '0', '500000.00', '1', '账户充值', '2019-04-21 12:49:32');
INSERT INTO `wallet_log` VALUES ('6', '6', '0', '2000.00', '1', '账户充值', '2019-04-21 12:54:07');
INSERT INTO `wallet_log` VALUES ('7', '6', '0', '200.00', '1', '账户充值', '2019-04-21 12:59:14');
INSERT INTO `wallet_log` VALUES ('8', '6', '0', '300.00', '1', '账户充值', '2019-04-21 13:08:51');
INSERT INTO `wallet_log` VALUES ('9', '6', '0', '10000.00', '1', '账户充值', '2019-04-21 13:09:14');
INSERT INTO `wallet_log` VALUES ('10', '6', '10', '1410.00', '2', '订单支付', '2019-04-21 13:09:23');
INSERT INTO `wallet_log` VALUES ('11', '6', '10', '1410.00', '3', '订单取消退款', '2019-04-21 13:25:53');
INSERT INTO `wallet_log` VALUES ('12', '6', '11', '1175.00', '2', '订单支付', '2019-04-21 13:33:15');
INSERT INTO `wallet_log` VALUES ('13', '6', '11', '1175.00', '3', '订单取消退款', '2019-04-21 13:33:41');
INSERT INTO `wallet_log` VALUES ('14', '6', '13', '3055.00', '2', '订单支付', '2019-04-21 13:35:31');
INSERT INTO `wallet_log` VALUES ('15', '6', '14', '1175.00', '2', '订单支付', '2019-04-21 13:52:21');
INSERT INTO `wallet_log` VALUES ('16', '6', '0', '100000.00', '1', '账户充值', '2019-04-21 17:05:09');
INSERT INTO `wallet_log` VALUES ('18', '6', '14', '2585.00', '2', '订单延长入住支付', '2019-04-21 18:41:15');
