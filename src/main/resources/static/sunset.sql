/*
Navicat MySQL Data Transfer

Source Server         : sunset
Source Server Version : 80029
Source Host           : www.dillonl.com:3306
Source Database       : sunset

Target Server Type    : MYSQL
Target Server Version : 80029
File Encoding         : 65001

Date: 2023-04-01 20:10:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_register
-- ----------------------------
DROP TABLE IF EXISTS `tb_register`;
CREATE TABLE `tb_register` (
  `uid` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of tb_register
-- ----------------------------
INSERT INTO `tb_register` VALUES ('C8FC2662-8C35-405C-A644-B7113E014187', '18794388410', null, '2023-03-31 21:22:26', null);

-- ----------------------------
-- Table structure for tb_userinfo
-- ----------------------------
DROP TABLE IF EXISTS `tb_userinfo`;
CREATE TABLE `tb_userinfo` (
  `id` varchar(128) NOT NULL COMMENT 'id',
  `nickname` varchar(128) DEFAULT NULL COMMENT 'nickname',
  `avator` varchar(128) DEFAULT NULL COMMENT 'avator',
  `birthday` date DEFAULT NULL COMMENT 'birthday',
  `description` varchar(128) DEFAULT NULL COMMENT 'description',
  `sex` int DEFAULT NULL COMMENT 'sex',
  `height` varchar(128) DEFAULT NULL COMMENT 'height',
  `weight` varchar(128) DEFAULT NULL COMMENT 'weight',
  `waistline` varchar(128) DEFAULT NULL COMMENT 'waistline',
  `uid` varchar(128) DEFAULT NULL COMMENT 'uid',
  `showid` varchar(128) DEFAULT NULL COMMENT 'showid',
  `create_time` datetime DEFAULT NULL COMMENT 'create_time',
  `update_time` datetime DEFAULT NULL COMMENT 'update_time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='tb_userinfo ';

-- ----------------------------
-- Records of tb_userinfo
-- ----------------------------
INSERT INTO `tb_userinfo` VALUES ('2740E0F3-A5F2-48BB-A51A-C82E275A6DC2', '书本书华', 'string', '1998-05-04', '取半舍满,克己修身', '1', '181', '78', '88', 'C8FC2662-8C35-405C-A644-B7113E014187', '6101476947', '2023-03-31 21:22:26', '2023-03-31 21:29:48');
