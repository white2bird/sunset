/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : sunset

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2023-04-05 23:38:39
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
INSERT INTO `tb_comment` VALUES ('C01C23F3-BD96-412C-B7C2-3BF5FF583D79', '845412FF-CDEF-4158-8221-BF396F0C404B', null, '30C41865-E01F-4570-AD0A-4DFF9CC90024', '赞一个', null, '2023-04-05 21:32:36');
INSERT INTO `tb_comment` VALUES ('8F3DC7D4-713E-4ECB-991D-B9407EF781C0', '845412FF-CDEF-4158-8221-BF396F0C404B', null, '30C41865-E01F-4570-AD0A-4DFF9CC90024', '赞一个1', null, '2023-04-05 21:32:53');
INSERT INTO `tb_comment` VALUES ('F1BFA982-5BAF-45F1-8FA0-142EE1A7348B', '845412FF-CDEF-4158-8221-BF396F0C404B', null, '30C41865-E01F-4570-AD0A-4DFF9CC90024', '赞一个2', null, '2023-04-05 21:32:56');
INSERT INTO `tb_comment` VALUES ('98B79FFC-67FD-46C5-9840-67451BC501FA', '845412FF-CDEF-4158-8221-BF396F0C404B', null, '30C41865-E01F-4570-AD0A-4DFF9CC90024', '赞一个3', null, '2023-04-05 21:32:59');
INSERT INTO `tb_comment` VALUES ('FFDF6141-1441-4F13-8C21-D590DE8DF7B0', '845412FF-CDEF-4158-8221-BF396F0C404B', null, '30C41865-E01F-4570-AD0A-4DFF9CC90024', '赞一个4', null, '2023-04-05 21:33:01');
INSERT INTO `tb_comment` VALUES ('20F487F9-E6ED-4492-B451-4FB439D1E7CB', '845412FF-CDEF-4158-8221-BF396F0C404B', null, '30C41865-E01F-4570-AD0A-4DFF9CC90024', '赞一个5', null, '2023-04-05 21:33:05');
INSERT INTO `tb_comment` VALUES ('E97D9A28-3C5B-42C2-83D0-694F3607C022', '845412FF-CDEF-4158-8221-BF396F0C404B', null, '30C41865-E01F-4570-AD0A-4DFF9CC90024', '赞一个6', null, '2023-04-05 21:33:08');

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
INSERT INTO `tb_follow` VALUES ('1', '1', '0', null, null);

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
INSERT INTO `tb_trends` VALUES ('30C41865-E01F-4570-AD0A-4DFF9CC90024', '8281611F-4FA0-4238-BABD-71F46EB25B88', '测试1', null, '[\"http://\"]', '2023-04-05 17:23:27');
INSERT INTO `tb_trends` VALUES ('6E67FC7A-3F1D-4180-90D9-ECCAC943DFC7', '8281611F-4FA0-4238-BABD-71F46EB25B88', '测试2', null, '[\"http://\"]', '2023-04-05 17:23:36');
INSERT INTO `tb_trends` VALUES ('E27957CE-49F9-4C8F-B703-24CC6E73ACE5', '8281611F-4FA0-4238-BABD-71F46EB25B88', '测试3', null, '[\"http://\"]', '2023-04-05 17:23:40');
INSERT INTO `tb_trends` VALUES ('0D26F6F5-F05A-42BD-8AF5-638300F98AEE', '8281611F-4FA0-4238-BABD-71F46EB25B88', '测试4', null, '[\"http://\"]', '2023-04-05 17:23:43');
INSERT INTO `tb_trends` VALUES ('914BA3E7-BA2A-4ADD-AA99-B6886485B989', '8281611F-4FA0-4238-BABD-71F46EB25B88', '测试5', null, '[\"http://\"]', '2023-04-05 17:23:47');
INSERT INTO `tb_trends` VALUES ('A7CEC2FF-8A11-4A17-847A-911C33A61A91', '8281611F-4FA0-4238-BABD-71F46EB25B88', '测试6', null, '[\"http://\"]', '2023-04-05 17:23:50');
INSERT INTO `tb_trends` VALUES ('017E42B8-6571-435A-9213-6DD11A611F57', '845412FF-CDEF-4158-8221-BF396F0C404B', '书本1', null, '[\"http://\"]', '2023-04-05 17:24:13');
INSERT INTO `tb_trends` VALUES ('A4FE186F-AF5E-4781-9769-B5760457BCF5', '845412FF-CDEF-4158-8221-BF396F0C404B', '书本2', null, '[\"http://\"]', '2023-04-05 17:24:16');
INSERT INTO `tb_trends` VALUES ('F01FB025-B90D-4CC7-99FC-10471FFECD2B', '845412FF-CDEF-4158-8221-BF396F0C404B', '书本3', null, '[\"http://\"]', '2023-04-05 17:24:19');
INSERT INTO `tb_trends` VALUES ('C08B9678-A63B-4ABB-95E0-B8DF8CC93589', '845412FF-CDEF-4158-8221-BF396F0C404B', '书本4', null, '[\"http://\"]', '2023-04-05 17:24:22');
INSERT INTO `tb_trends` VALUES ('AC5F7B24-7D51-40FD-8444-3E572E72D755', '845412FF-CDEF-4158-8221-BF396F0C404B', '书本5', null, '[\"http://\"]', '2023-04-05 17:24:25');

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
INSERT INTO `tb_userinfo` VALUES ('387099D2-588D-4268-9ACD-3FD69C7E6E73', '用户-447332', '/avator/sunset202303311711.png', '1998-05-04', null, '0', '金牛座', '0', '0', '0', '845412FF-CDEF-4158-8221-BF396F0C404B', '4303166733', '2023-04-05 11:46:36', null);
INSERT INTO `tb_userinfo` VALUES ('58F6646B-ED97-4BB3-AEFF-FA9D7C10C6E1', '用户-600211', '/avator/sunset202303311711.png', '1998-05-04', null, '0', '金牛座', '0', '0', '0', '862DA805-1AFE-47EF-87C9-C84644443E0E', '8816194845', '2023-04-05 22:24:24', null);
