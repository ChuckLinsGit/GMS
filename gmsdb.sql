/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : gmsdb

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 30/06/2019 01:07:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blacklist
-- ----------------------------
DROP TABLE IF EXISTS `blacklist`;
CREATE TABLE `blacklist`  (
  `blacklistid` int(11) NOT NULL AUTO_INCREMENT COMMENT '黑名单编号',
  `userid` int(11) NOT NULL COMMENT '用户编号',
  `regulationid` int(11) NOT NULL COMMENT '条例编号',
  `vioreason` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '原因',
  `solution` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '处理',
  PRIMARY KEY (`blacklistid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for broadcast
-- ----------------------------
DROP TABLE IF EXISTS `broadcast`;
CREATE TABLE `broadcast`  (
  `broadcastID` int(8) NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `state` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`broadcastID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for check
-- ----------------------------
DROP TABLE IF EXISTS `check`;
CREATE TABLE `check`  (
  `check_id` int(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `orders_id` int(8) UNSIGNED NULL DEFAULT NULL,
  `check_state` int(1) NOT NULL,
  PRIMARY KEY (`check_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dropmassage
-- ----------------------------
DROP TABLE IF EXISTS `dropmassage`;
CREATE TABLE `dropmassage`  (
  `drop_id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(8) UNSIGNED NOT NULL,
  `orders_id` int(8) UNSIGNED ZEROFILL NOT NULL,
  `drop_state` int(1) UNSIGNED NOT NULL,
  PRIMARY KEY (`drop_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for equipment
-- ----------------------------
DROP TABLE IF EXISTS `equipment`;
CREATE TABLE `equipment`  (
  `equip_id` int(4) UNSIGNED NOT NULL AUTO_INCREMENT,
  `equip_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `equip_value` decimal(10, 0) NOT NULL,
  `equip_price` decimal(10, 0) NOT NULL,
  `equip_sum` int(10) UNSIGNED NOT NULL,
  `equip_last` int(10) UNSIGNED NOT NULL,
  `equip_state` int(1) NOT NULL,
  `equip_book` int(10) UNSIGNED ZEROFILL NOT NULL,
  `equip_rent` int(10) UNSIGNED ZEROFILL NOT NULL,
  `equip_damage` int(10) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`equip_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of equipment
-- ----------------------------
INSERT INTO `equipment` VALUES (1, '篮球', 180, 15, 30, 20, 1, 0000000004, 0000000003, 0000000003);
INSERT INTO `equipment` VALUES (2, '足球', 100, 11, 35, 30, 1, 0000000000, 0000000003, 0000000002);
INSERT INTO `equipment` VALUES (3, '乒乓球', 3, 1, 150, 146, 1, 0000000000, 0000000004, 0000000000);
INSERT INTO `equipment` VALUES (4, '乒乓球拍', 10, 5, 300, 300, 1, 0000000000, 0000000000, 0000000000);
INSERT INTO `equipment` VALUES (5, '羽毛球', 5, 2, 100, 100, 1, 0000000000, 0000000000, 0000000000);
INSERT INTO `equipment` VALUES (6, '羽毛球拍', 30, 10, 75, 75, 1, 0000000000, 0000000000, 0000000000);
INSERT INTO `equipment` VALUES (7, '排球', 30, 10, 50, 50, 1, 0000000000, 0000000000, 0000000000);
INSERT INTO `equipment` VALUES (8, '跳绳', 10, 15, 80, 30, 1, 0000000000, 0000000000, 0000000000);
INSERT INTO `equipment` VALUES (9, '保龄球', 185, 15, 30, 30, 1, 0000000000, 0000000000, 0000000000);

-- ----------------------------
-- Table structure for event
-- ----------------------------
DROP TABLE IF EXISTS `event`;
CREATE TABLE `event`  (
  `eventID` int(8) NOT NULL AUTO_INCREMENT,
  `userID` int(8) NOT NULL,
  `fieldID` int(8) NOT NULL,
  `equipmentID` int(8) NOT NULL,
  `date` date NOT NULL,
  `endDate` date NOT NULL,
  `content` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `judgement` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`eventID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of event
-- ----------------------------
INSERT INTO `event` VALUES (1, 1, 0, 0, '2019-01-01', '2019-01-01', '三人篮球赛', '无', 2);
INSERT INTO `event` VALUES (2, 1, 0, 0, '2019-06-30', '2019-07-01', '三人篮球赛', '无', 3);
INSERT INTO `event` VALUES (3, 2, 0, 0, '2019-01-01', '2019-01-01', '三人篮球赛', '王', 2);

-- ----------------------------
-- Table structure for eventapplication
-- ----------------------------
DROP TABLE IF EXISTS `eventapplication`;
CREATE TABLE `eventapplication`  (
  `applicationID` int(8) NOT NULL AUTO_INCREMENT,
  `userID` int(8) NOT NULL,
  `fieldID` int(8) NOT NULL,
  `equipmentID` int(8) NOT NULL,
  `date` date NOT NULL,
  `endDate` date NOT NULL,
  `content` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `judgement` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `State` int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`applicationID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eventapplication
-- ----------------------------
INSERT INTO `eventapplication` VALUES (1, 1, 0, 0, '2019-01-01', '2019-01-01', '三人篮球赛', '无', 2);
INSERT INTO `eventapplication` VALUES (2, 1, 0, 0, '2019-07-01', '2019-08-01', '三人篮球赛', '无', 0);
INSERT INTO `eventapplication` VALUES (3, 1, 0, 0, '2019-07-01', '2019-08-01', '三人篮球赛', '无', 2);
INSERT INTO `eventapplication` VALUES (4, 1, 0, 0, '2019-07-01', '2019-08-01', '三人篮球赛', '无', 1);
INSERT INTO `eventapplication` VALUES (8, 1, 0, 0, '2019-06-30', '2019-07-03', '三人篮球赛', '无', 0);

-- ----------------------------
-- Table structure for eventinform
-- ----------------------------
DROP TABLE IF EXISTS `eventinform`;
CREATE TABLE `eventinform`  (
  `informID` int(8) NOT NULL AUTO_INCREMENT,
  `userID` int(8) NOT NULL,
  `date` date NOT NULL,
  `content` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `state` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`informID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of eventinform
-- ----------------------------
INSERT INTO `eventinform` VALUES (12, 1, '2019-06-30', '三人篮球赛申请失败！申请过期，请重新申请。', 1);
INSERT INTO `eventinform` VALUES (13, 1, '2019-06-30', '三人篮球赛申请失败！申请过期，请重新申请。', 0);

-- ----------------------------
-- Table structure for field
-- ----------------------------
DROP TABLE IF EXISTS `field`;
CREATE TABLE `field`  (
  `fieldid` int(11) NOT NULL AUTO_INCREMENT COMMENT '场地id自增',
  `fieldaddordel` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '判断场地是否被删除的状态位',
  `fieldname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '场地名称',
  `fieldtype` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '场地类型',
  `fieldinout` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '室内外',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '场地位置',
  `fieldstate` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '场地状态',
  `fieldlong` int(11) NOT NULL COMMENT '场地长度',
  `fieldwidth` int(11) NOT NULL COMMENT '场地宽度',
  `fieldimg` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '场地图片url',
  `unitprice` int(11) NOT NULL COMMENT '单位金额',
  PRIMARY KEY (`fieldid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of field
-- ----------------------------
INSERT INTO `field` VALUES (1, '未删除', '东区篮球场1号', '篮球场', '室外', '东区', '被租赁', 100, 12, 'static/img/oBasketball.jpg', 100);

-- ----------------------------
-- Table structure for fieldnotice
-- ----------------------------
DROP TABLE IF EXISTS `fieldnotice`;
CREATE TABLE `fieldnotice`  (
  `noticefid` int(11) NOT NULL AUTO_INCREMENT COMMENT '场地公告id',
  `noticename` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '公告名称',
  `noticemessage` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '公告信息',
  `fieldid` int(11) NOT NULL COMMENT '公告对应场地的id',
  `noticestate` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '公告过时',
  PRIMARY KEY (`noticefid`) USING BTREE,
  INDEX `fieldid`(`fieldid`) USING BTREE,
  CONSTRAINT `fk_field` FOREIGN KEY (`fieldid`) REFERENCES `field` (`fieldid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for fieldorder
-- ----------------------------
DROP TABLE IF EXISTS `fieldorder`;
CREATE TABLE `fieldorder`  (
  `orderid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `fieldid` int(11) NOT NULL COMMENT '外键场地id',
  `rentstart` timestamp(0) NOT NULL COMMENT '租赁起始时间时间戳',
  `rentend` timestamp(0) NOT NULL COMMENT '租赁结束时间时间戳',
  `returnid` int(11) NULL DEFAULT NULL COMMENT '非必填选项',
  `orderstate` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '预约状态',
  `userid` int(11) NOT NULL COMMENT '用户id',
  `payid` int(11) NOT NULL COMMENT '支付订单id',
  `phone` char(13) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '联系电话',
  PRIMARY KEY (`orderid`) USING BTREE,
  INDEX `fk_field_1`(`fieldid`) USING BTREE,
  INDEX `fk_return_1`(`returnid`) USING BTREE,
  INDEX `fk_pay_1`(`payid`) USING BTREE,
  CONSTRAINT `fk_field_1` FOREIGN KEY (`fieldid`) REFERENCES `field` (`fieldid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_pay_1` FOREIGN KEY (`payid`) REFERENCES `pay` (`payid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ordersdetail
-- ----------------------------
DROP TABLE IF EXISTS `ordersdetail`;
CREATE TABLE `ordersdetail`  (
  `orders_id` int(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `equip_id` int(4) UNSIGNED NULL DEFAULT NULL,
  `user_id` int(8) UNSIGNED NULL DEFAULT NULL,
  `orders_renttime` datetime(0) NOT NULL,
  `orders_backtime` datetime(0) NOT NULL,
  `equip_num` int(2) NOT NULL,
  `orders_createtime` datetime(0) NOT NULL,
  `orders_state` int(1) UNSIGNED NOT NULL,
  PRIMARY KEY (`orders_id`) USING BTREE,
  INDEX `ordersdetail_ibfk_1`(`equip_id`) USING BTREE,
  INDEX `ordersdetail_ibfk_2`(`user_id`) USING BTREE,
  CONSTRAINT `ordersdetail_ibfk_1` FOREIGN KEY (`equip_id`) REFERENCES `equipment` (`equip_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ordersdetail
-- ----------------------------
INSERT INTO `ordersdetail` VALUES (00000001, 1, 10, '2019-06-30 20:10:11', '2019-06-30 23:10:11', 3, '2019-06-30 20:10:19', 1);
INSERT INTO `ordersdetail` VALUES (00000002, 1, 1, '2019-06-30 21:12:35', '2019-06-30 22:12:35', 1, '2019-06-30 21:12:46', 3);
INSERT INTO `ordersdetail` VALUES (00000003, 1, 4, '2019-06-30 21:14:11', '2019-06-30 22:14:11', 1, '2019-06-30 21:14:13', 2);

-- ----------------------------
-- Table structure for papfunction
-- ----------------------------
DROP TABLE IF EXISTS `papfunction`;
CREATE TABLE `papfunction`  (
  `PAPFID` int(11) NOT NULL AUTO_INCREMENT,
  `PID` int(11) NULL DEFAULT NULL,
  `PFID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`PAPFID`) USING BTREE,
  INDEX `FK_Reference_5`(`PID`) USING BTREE,
  INDEX `FK_Reference_6`(`PFID`) USING BTREE,
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`PID`) REFERENCES `permission` (`PID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`PFID`) REFERENCES `pfunction` (`PFID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pay
-- ----------------------------
DROP TABLE IF EXISTS `pay`;
CREATE TABLE `pay`  (
  `payid` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单表id',
  `fieldid` int(11) NOT NULL COMMENT '场地id外键',
  `allTime` int(11) NOT NULL COMMENT '总时长',
  `paystate` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '订单状态，已过期和未过期',
  PRIMARY KEY (`payid`) USING BTREE,
  INDEX `fk_field_2`(`fieldid`) USING BTREE,
  CONSTRAINT `fk_field_2` FOREIGN KEY (`fieldid`) REFERENCES `field` (`fieldid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment`  (
  `paymentid` int(11) NOT NULL AUTO_INCREMENT COMMENT '金额表id',
  `payid` int(11) NOT NULL COMMENT '支付id',
  `returnid` int(11) NOT NULL COMMENT '返还id',
  `money` decimal(11, 0) NOT NULL COMMENT '支付money',
  PRIMARY KEY (`paymentid`) USING BTREE,
  INDEX `payid`(`payid`) USING BTREE,
  CONSTRAINT `fk_payid_1` FOREIGN KEY (`payid`) REFERENCES `pay` (`payid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `PID` int(11) NOT NULL AUTO_INCREMENT,
  `PLevelD` int(11) NOT NULL,
  `PName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`PID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pfunction
-- ----------------------------
DROP TABLE IF EXISTS `pfunction`;
CREATE TABLE `pfunction`  (
  `PFID` int(11) NOT NULL AUTO_INCREMENT,
  `PFName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PFDetails` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`PFID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for regulation
-- ----------------------------
DROP TABLE IF EXISTS `regulation`;
CREATE TABLE `regulation`  (
  `regulationid` int(11) NOT NULL AUTO_INCREMENT COMMENT '条例id',
  `regname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `regcontent` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '条例内容',
  `viodispose` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '违反处理',
  `regstate` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '状态',
  PRIMARY KEY (`regulationid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for return
-- ----------------------------
DROP TABLE IF EXISTS `return`;
CREATE TABLE `return`  (
  `returnid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `payid` int(11) NOT NULL COMMENT '外键',
  `fieldid` int(11) NOT NULL COMMENT '外键',
  `paymentid` int(11) NOT NULL COMMENT '外键',
  `returndate` date NOT NULL COMMENT '时间',
  `returnreason` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '原因',
  PRIMARY KEY (`returnid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `RID` int(11) NOT NULL AUTO_INCREMENT,
  `RType` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `RName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`RID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for roleapermission
-- ----------------------------
DROP TABLE IF EXISTS `roleapermission`;
CREATE TABLE `roleapermission`  (
  `RAPID` int(11) NOT NULL AUTO_INCREMENT,
  `RID` int(11) NULL DEFAULT NULL,
  `PID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`RAPID`) USING BTREE,
  INDEX `FK_Reference_3`(`RID`) USING BTREE,
  INDEX `FK_Reference_4`(`PID`) USING BTREE,
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`RID`) REFERENCES `role` (`RID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`PID`) REFERENCES `permission` (`PID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `UID` int(11) NOT NULL AUTO_INCREMENT,
  `UName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `UAccountID` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `UPassword` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `UEmail` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `UPhone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`UID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '123', '123', '123', '123', '123');

-- ----------------------------
-- Table structure for userarole
-- ----------------------------
DROP TABLE IF EXISTS `userarole`;
CREATE TABLE `userarole`  (
  `UARID` int(11) NOT NULL AUTO_INCREMENT,
  `UID` int(11) NULL DEFAULT NULL,
  `RID` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`UARID`) USING BTREE,
  INDEX `FK_Reference_1`(`UID`) USING BTREE,
  INDEX `FK_Reference_2`(`RID`) USING BTREE,
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`UID`) REFERENCES `user` (`UID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`RID`) REFERENCES `role` (`RID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
