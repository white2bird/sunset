/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : sunset

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2023-04-08 20:13:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment` (
  `id` varchar(50) NOT NULL,
  `uid` varchar(50) DEFAULT NULL,
  `to_uid` varchar(50) DEFAULT NULL,
  `trends_id` varchar(50) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `start` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------
INSERT INTO `tb_comment` VALUES ('4D1D8310-BE5C-45C1-BDE5-9B9A35E5B508', '845412FF-CDEF-4158-8221-BF396F0C404B', null, '0C75B2CD-29C3-4C29-B630-D7D5ED95D1F5', '你够', null, '2023-04-08 20:10:40');
INSERT INTO `tb_comment` VALUES ('9D29B381-7F78-497A-8D54-5D8ECF821A47', '845412FF-CDEF-4158-8221-BF396F0C404B', null, '0C75B2CD-29C3-4C29-B630-D7D5ED95D1F5', '个', null, '2023-04-08 19:50:16');
INSERT INTO `tb_comment` VALUES ('99C2FF13-FD36-4C4A-BA2F-E38DBE40D829', '845412FF-CDEF-4158-8221-BF396F0C404B', null, '0C75B2CD-29C3-4C29-B630-D7D5ED95D1F5', '跟的', null, '2023-04-08 19:51:42');
INSERT INTO `tb_comment` VALUES ('82E25219-A1C3-4C3B-BC6E-A207E924BF6B', '845412FF-CDEF-4158-8221-BF396F0C404B', null, '0C75B2CD-29C3-4C29-B630-D7D5ED95D1F5', '地面', null, '2023-04-08 19:53:56');

-- ----------------------------
-- Table structure for tb_follow
-- ----------------------------
DROP TABLE IF EXISTS `tb_follow`;
CREATE TABLE `tb_follow` (
  `id` varchar(255) NOT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `following` varchar(255) DEFAULT NULL,
  `followers` varchar(255) DEFAULT NULL,
  `star` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_follow
-- ----------------------------
INSERT INTO `tb_follow` VALUES ('1', '845412FF-CDEF-4158-8221-BF396F0C404B', '0', null, null);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_register
-- ----------------------------
INSERT INTO `tb_register` VALUES ('8281611F-4FA0-4238-BABD-71F46EB25B88', '13096954409', null, '2023-04-05 17:22:50', null);
INSERT INTO `tb_register` VALUES ('845412FF-CDEF-4158-8221-BF396F0C404B', '18794388410', null, '2023-04-05 11:46:36', null);
INSERT INTO `tb_register` VALUES ('862DA805-1AFE-47EF-87C9-C84644443E0E', '1879438841', null, '2023-04-05 22:24:24', null);

-- ----------------------------
-- Table structure for tb_trends
-- ----------------------------
DROP TABLE IF EXISTS `tb_trends`;
CREATE TABLE `tb_trends` (
  `id` varchar(255) NOT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `star` varchar(255) DEFAULT NULL,
  `images` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_trends
-- ----------------------------
INSERT INTO `tb_trends` VALUES ('0C75B2CD-29C3-4C29-B630-D7D5ED95D1F5', '845412FF-CDEF-4158-8221-BF396F0C404B', '加油干', null, '[\"/images/3c71d89a-fe69-4b91-bd0b-809e868b89a4-1680940791164.jpg\",\"/images/59b6f2c0-52bc-4f41-86ac-b43d205ea525-1680940791165.jpg\",\"/images/9714caba-be4e-4e7e-9630-f554d60b9021-1680940791166.jpg\"]', '2023-04-08 15:59:51');

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
  `sex` int(11) DEFAULT NULL COMMENT 'sex',
  `constellation` varchar(255) DEFAULT NULL,
  `height` varchar(128) DEFAULT NULL COMMENT 'height',
  `weight` varchar(128) DEFAULT NULL COMMENT 'weight',
  `waistline` varchar(128) DEFAULT NULL COMMENT 'waistline',
  `uid` varchar(128) DEFAULT NULL COMMENT 'uid',
  `showid` varchar(128) DEFAULT NULL COMMENT 'showid',
  `create_time` datetime DEFAULT NULL COMMENT 'create_time',
  `update_time` datetime DEFAULT NULL COMMENT 'update_time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='tb_userinfo ';

-- ----------------------------
-- Records of tb_userinfo
-- ----------------------------
INSERT INTO `tb_userinfo` VALUES ('0A3C3AA5-E5C0-44D4-82CE-7777BDF8AAB6', '用户-239859', '/avator/sunset202303311711.png', '1998-05-04', null, '0', '金牛座', '0', '0', '0', '8281611F-4FA0-4238-BABD-71F46EB25B88', '6272470148', '2023-04-05 17:22:50', null);
INSERT INTO `tb_userinfo` VALUES ('387099D2-588D-4268-9ACD-3FD69C7E6E73', '用户-447332', '/avator/b6da615e-ce63-458c-a827-29a73ec46839-1680940617485.jpg', '1998-05-04', '取半舍满，克己修身', '1', '金牛座', '0', '0', '0', '845412FF-CDEF-4158-8221-BF396F0C404B', '4303166733', '2023-04-05 11:46:36', '2023-04-08 17:07:58');
INSERT INTO `tb_userinfo` VALUES ('58F6646B-ED97-4BB3-AEFF-FA9D7C10C6E1', '用户-600211', '/avator/sunset202303311711.png', '1998-05-04', null, '0', '金牛座', '0', '0', '0', '862DA805-1AFE-47EF-87C9-C84644443E0E', '8816194845', '2023-04-05 22:24:24', null);
