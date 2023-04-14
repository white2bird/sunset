/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : sunset

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2023-04-15 01:08:37
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
  `star` int(255) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------
INSERT INTO `tb_comment` VALUES ('67298D99-5155-4100-A16A-EE3300624599', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':eyes::eyes::eyes::eyes::eyes::eyes:', '0', '2023-04-14 20:29:45');
INSERT INTO `tb_comment` VALUES ('786FB91D-1F67-47FC-9280-767A38D37650', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':palms_up_together::palms_up_together::palms_up_together::palms_up_together::palms_up_together:', '0', '2023-04-14 20:29:51');
INSERT INTO `tb_comment` VALUES ('EEF4A406-3741-45CE-85AB-634BF4AAF683', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':muscle|type_1_2::muscle|type_1_2::muscle|type_1_2::muscle|type_1_2::muscle|type_1_2::muscle|type_1_2::muscle|type_1_2:', '0', '2023-04-14 20:29:59');
INSERT INTO `tb_comment` VALUES ('2E2C9329-FF16-4BF0-8874-C6F1A6E5C69A', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':left-facing_fist::left-facing_fist::left-facing_fist::left-facing_fist::left-facing_fist::left-facing_fist:', '0', '2023-04-14 20:30:04');
INSERT INTO `tb_comment` VALUES ('E77362DB-4953-478A-9368-406A81C4E57F', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', '哈哈哈:grin:', '1', '2023-04-14 20:30:10');

-- ----------------------------
-- Table structure for tb_comment_starlog
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment_starlog`;
CREATE TABLE `tb_comment_starlog` (
  `id` varchar(255) NOT NULL,
  `trends_id` varchar(255) DEFAULT NULL,
  `comment_id` varchar(255) DEFAULT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_comment_starlog
-- ----------------------------
INSERT INTO `tb_comment_starlog` VALUES ('07C8A8E5-EB9B-4155-994D-93100D1AA0DE', '79F48BE2-3551-4A78-A7C4-320324528DCD', 'E77362DB-4953-478A-9368-406A81C4E57F', '8281611F-4FA0-4238-BABD-71F46EB25B88', '2023-04-14 20:30:39');

-- ----------------------------
-- Table structure for tb_follower
-- ----------------------------
DROP TABLE IF EXISTS `tb_follower`;
CREATE TABLE `tb_follower` (
  `id` varchar(255) NOT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `my_id` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_follower
-- ----------------------------
INSERT INTO `tb_follower` VALUES ('2BF0B576-94C9-4AAE-9376-C27FD8DE8552', '845412FF-CDEF-4158-8221-BF396F0C404B', '8281611F-4FA0-4238-BABD-71F46EB25B88', '2023-04-15 01:05:37');

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

-- ----------------------------
-- Table structure for tb_trends
-- ----------------------------
DROP TABLE IF EXISTS `tb_trends`;
CREATE TABLE `tb_trends` (
  `id` varchar(255) NOT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `star` int(255) DEFAULT NULL,
  `images` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_trends
-- ----------------------------
INSERT INTO `tb_trends` VALUES ('79F48BE2-3551-4A78-A7C4-320324528DCD', '845412FF-CDEF-4158-8221-BF396F0C404B', '又开始了:joy:', '1', '[\"/images/5ef15519-639a-45ab-b690-4a704338f0a9-1681041141310.jpg\",\"/images/1aa3cf49-2aab-4ae4-b7f0-ca9e18aa10d9-1681041141311.jpg\"]', '2023-04-09 19:52:21');
INSERT INTO `tb_trends` VALUES ('1DB06575-6DA7-41D0-97E3-F8E1DABD8143', '8281611F-4FA0-4238-BABD-71F46EB25B88', '残灯无焰影幢幢，此夕闻君谪九江。垂死病中惊坐起，暗风吹雨入寒窗。', '0', null, '2023-04-09 20:14:16');
INSERT INTO `tb_trends` VALUES ('A832F2EF-C489-470D-B946-B656622A34BF', '845412FF-CDEF-4158-8221-BF396F0C404B', '人生若只如初见，何事秋风悲画扇。____纳兰性德《木兰词·拟古决绝词柬友》', null, '', '2023-04-09 19:47:31');

-- ----------------------------
-- Table structure for tb_trends_starlog
-- ----------------------------
DROP TABLE IF EXISTS `tb_trends_starlog`;
CREATE TABLE `tb_trends_starlog` (
  `id` varchar(255) NOT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `trends_id` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_trends_starlog
-- ----------------------------
INSERT INTO `tb_trends_starlog` VALUES ('BD58D172-D3CB-4929-9749-70028242956B', '8281611F-4FA0-4238-BABD-71F46EB25B88', '79F48BE2-3551-4A78-A7C4-320324528DCD', '2023-04-14 22:48:38');

-- ----------------------------
-- Table structure for tb_userfollow
-- ----------------------------
DROP TABLE IF EXISTS `tb_userfollow`;
CREATE TABLE `tb_userfollow` (
  `id` varchar(255) NOT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `following` int(255) DEFAULT '0',
  `followers` int(255) DEFAULT '0',
  `star` int(255) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_userfollow
-- ----------------------------
INSERT INTO `tb_userfollow` VALUES ('0A95E6A6-0B2C-4372-868C-2F7BCFBE03A4', '845412FF-CDEF-4158-8221-BF396F0C404B', '0', '1', '1');
INSERT INTO `tb_userfollow` VALUES ('8281611F-4FA0-4238-BABD-71F46EB25B45', '8281611F-4FA0-4238-BABD-71F46EB25B88', '1', '0', '0');

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
INSERT INTO `tb_userinfo` VALUES ('0A3C3AA5-E5C0-44D4-82CE-7777BDF8AAB6', '冰消叶散', '/avator/deeaa359-d250-4e4a-a78b-81a0274f15f4-1681491736464.jpg', '1998-01-01', '道阻且长:joy:', '0', '摩羯座', '173', '70.0', '120', '8281611F-4FA0-4238-BABD-71F46EB25B88', '6272470148', '2023-04-05 17:22:50', '2023-04-15 01:02:16');
INSERT INTO `tb_userinfo` VALUES ('387099D2-588D-4268-9ACD-3FD69C7E6E73', '书本书华', '/avator/b6da615e-ce63-458c-a827-29a73ec46839-1680940617485.jpg', '1998-05-04', '取半舍满，克己修身', '1', '金牛座', '180', '80.0', '80', '845412FF-CDEF-4158-8221-BF396F0C404B', '4303166733', '2023-04-05 11:46:36', '2023-04-09 20:15:11');
