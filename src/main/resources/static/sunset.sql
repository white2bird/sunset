/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : sunset

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2023-04-11 23:41:36
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
  `star` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------
INSERT INTO `tb_comment` VALUES ('6E6B318D-CBD2-47FA-BB3E-766DB50DDF38', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':grin:', null, '2023-04-09 21:07:14');
INSERT INTO `tb_comment` VALUES ('35BAECDF-E1F6-40F1-90BC-D9FF82512967', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':joy::smiley:', null, '2023-04-09 21:07:18');
INSERT INTO `tb_comment` VALUES ('43E41381-6B8D-4498-9231-1BBBF2F18D73', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':angry:', null, '2023-04-09 21:07:23');
INSERT INTO `tb_comment` VALUES ('23510929-0734-4ADA-BE76-8B47C17F8BB9', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', '可以', null, '2023-04-09 21:07:26');
INSERT INTO `tb_comment` VALUES ('1536F752-D305-4E9A-9192-E1D622474407', '845412FF-CDEF-4158-8221-BF396F0C404B', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':joy:', null, '2023-04-09 20:16:00');
INSERT INTO `tb_comment` VALUES ('40BFA72B-6CD8-4194-8382-83A005F3AD63', '845412FF-CDEF-4158-8221-BF396F0C404B', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', '哈哈:smiley:', null, '2023-04-09 20:16:06');
INSERT INTO `tb_comment` VALUES ('BF1E4FC0-67E1-47B9-B5FB-1758AE10BE39', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':stuck_out_tongue::kissing:', null, '2023-04-09 21:07:32');
INSERT INTO `tb_comment` VALUES ('61025A1C-200E-474A-A79B-32FEA58CAF9A', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':sweat_smile:', null, '2023-04-09 21:07:37');
INSERT INTO `tb_comment` VALUES ('CC2A7041-7703-40E9-BC56-F21422C3A2CE', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':cactus::evergreen_tree:', null, '2023-04-09 21:07:44');
INSERT INTO `tb_comment` VALUES ('7EF1D57B-144B-4396-BBB4-66A60A56F169', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':kiss::eyes:', null, '2023-04-09 21:07:49');
INSERT INTO `tb_comment` VALUES ('4E806966-4527-4646-9851-C829F06862F7', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', '₍˄·͈༝·͈˄*₎◞ ̑̑', null, '2023-04-09 21:07:54');
INSERT INTO `tb_comment` VALUES ('4ECF0B23-6D92-4580-82CC-7AFC2D9299A4', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':foot::foot::foot:', null, '2023-04-09 21:08:02');
INSERT INTO `tb_comment` VALUES ('F59E3EA3-0BF2-4060-B04C-D540537926F9', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':writing:️:writing:️:writing:️:writing:️:writing:️:writing:️:writing:️:writing:️:writing:️:writing:️:writing:️:writing:️:writing:️:writing:️:writing:️:writing:️:writing:️:writing:️:writing:️:writing:️:writing:️:writing:️:writing:️', null, '2023-04-09 21:11:22');
INSERT INTO `tb_comment` VALUES ('8283F914-733D-4D65-9C03-5F1ACA76160C', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':footprints::footprints::footprints::footprints::footprints::footprints::footprints:', null, '2023-04-09 21:12:04');
INSERT INTO `tb_comment` VALUES ('999F9AB0-733D-4B4B-8C14-F59CF37DDBA3', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':love_you_gesture::hand_with_index_and_middle_fingers_crossed::v|type_5:️', null, '2023-04-09 21:12:32');
INSERT INTO `tb_comment` VALUES ('3B0401DF-DCF3-4077-A3F1-D4968D078F6E', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':foot:', null, '2023-04-09 21:12:36');
INSERT INTO `tb_comment` VALUES ('1CC77040-E00B-4765-ABD8-CB1ED4119902', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':santa|type_1_2::santa|type_1_2::santa|type_1_2::santa|type_1_2::santa|type_1_2:', null, '2023-04-09 21:12:45');
INSERT INTO `tb_comment` VALUES ('4CD27958-B470-4194-8FB9-67B0EFB29619', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':tada::sparkles::sparkles::balloon::balloon::gift:', null, '2023-04-09 21:12:50');
INSERT INTO `tb_comment` VALUES ('98BFD43B-6EA5-4467-ADC9-E394D02C8363', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', '哈哈哈:grin::grin::grin::grin:', null, '2023-04-09 21:12:56');
INSERT INTO `tb_comment` VALUES ('D16F0494-6CC0-491A-BC16-5BB0377A64CE', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':trolleybus::trolleybus::trolleybus::trolleybus::trolleybus:', null, '2023-04-09 21:13:01');
INSERT INTO `tb_comment` VALUES ('6947FB9B-6979-4871-A67E-DB6D6D192E4D', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':bike::bike::bike::bike::bike::bike:', null, '2023-04-09 21:13:06');
INSERT INTO `tb_comment` VALUES ('BA467980-63BD-4F1D-A3EB-F536B0A2197F', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':busstop::busstop::busstop::busstop:', null, '2023-04-09 21:13:11');
INSERT INTO `tb_comment` VALUES ('3490EC04-9190-4BF1-B04E-C72B1DDD58E6', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':oncoming_bus::oncoming_bus::oncoming_bus::oncoming_bus::oncoming_bus::oncoming_automobile::oncoming_automobile::oncoming_taxi:', null, '2023-04-09 21:13:16');
INSERT INTO `tb_comment` VALUES ('B961C057-84DF-4C77-BE63-4DC561549232', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':construction::construction::construction::construction::construction::construction::construction:', null, '2023-04-09 21:13:21');
INSERT INTO `tb_comment` VALUES ('9A8403B4-2C1B-49B2-979E-4DB7E2D4903D', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, '79F48BE2-3551-4A78-A7C4-320324528DCD', ':man:\r:necktie:\r:jeans:', '1', '2023-04-09 21:13:28');

-- ----------------------------
-- Table structure for tb_followcomm
-- ----------------------------
DROP TABLE IF EXISTS `tb_followcomm`;
CREATE TABLE `tb_followcomm` (
  `id` varchar(255) NOT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `trends_id` varchar(255) DEFAULT NULL,
  `comment_id` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_followcomm
-- ----------------------------
INSERT INTO `tb_followcomm` VALUES ('303ED584-0042-4343-9674-41396A3426B2', '8281611F-4FA0-4238-BABD-71F46EB25B88', '79F48BE2-3551-4A78-A7C4-320324528DCD', null, '0', '2023-04-11 21:13:36');
INSERT INTO `tb_followcomm` VALUES ('752F6BAF-6CC3-4C49-BD15-5B5174EE921A', '8281611F-4FA0-4238-BABD-71F46EB25B88', '79F48BE2-3551-4A78-A7C4-320324528DCD', '9A8403B4-2C1B-49B2-979E-4DB7E2D4903D', '0', '2023-04-11 23:08:39');

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
  `star` varchar(255) DEFAULT NULL,
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
-- Table structure for tb_userfollow
-- ----------------------------
DROP TABLE IF EXISTS `tb_userfollow`;
CREATE TABLE `tb_userfollow` (
  `id` varchar(255) NOT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `following` varchar(255) DEFAULT NULL,
  `followers` varchar(255) DEFAULT NULL,
  `star` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_userfollow
-- ----------------------------
INSERT INTO `tb_userfollow` VALUES ('0A95E6A6-0B2C-4372-868C-2F7BCFBE03A4', '845412FF-CDEF-4158-8221-BF396F0C404B', '0', null, '1');
INSERT INTO `tb_userfollow` VALUES ('8281611F-4FA0-4238-BABD-71F46EB25B45', '8281611F-4FA0-4238-BABD-71F46EB25B88', null, null, null);

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
INSERT INTO `tb_userinfo` VALUES ('0A3C3AA5-E5C0-44D4-82CE-7777BDF8AAB6', '冰消叶散', '/avator/02751bf9-825d-418c-abb1-cd249d9974fb-1681045030278.jpg', '1998-01-01', '道阻且长:joy:', '0', '摩羯座', '173', '70.0', '120', '8281611F-4FA0-4238-BABD-71F46EB25B88', '6272470148', '2023-04-05 17:22:50', '2023-04-09 22:19:08');
INSERT INTO `tb_userinfo` VALUES ('387099D2-588D-4268-9ACD-3FD69C7E6E73', '书本书华', '/avator/b6da615e-ce63-458c-a827-29a73ec46839-1680940617485.jpg', '1998-05-04', '取半舍满，克己修身', '1', '金牛座', '180', '80.0', '80', '845412FF-CDEF-4158-8221-BF396F0C404B', '4303166733', '2023-04-05 11:46:36', '2023-04-09 20:15:11');
