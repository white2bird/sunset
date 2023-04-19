/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : sunset

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2023-04-19 20:39:12
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
-- Table structure for tb_knowledge
-- ----------------------------
DROP TABLE IF EXISTS `tb_knowledge`;
CREATE TABLE `tb_knowledge` (
  `id` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `cover_img` varchar(255) DEFAULT NULL,
  `content` text,
  `type_name` varchar(255) DEFAULT NULL,
  `type` int(255) DEFAULT NULL,
  `isthird` int(11) DEFAULT '0',
  `read_num` int(11) DEFAULT '0',
  `like_num` int(11) DEFAULT '0',
  `comment_num` int(11) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_knowledge
-- ----------------------------
INSERT INTO `tb_knowledge` VALUES ('3300', '大多数癌症与饮食习惯有关！4种“吃”出来的癌症，你中招了吗？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3300', 'https://image.findlinked.cn/xiangrui/2023-01-13/cf1d13d0-e6a2-4c97-8d4f-883eb81740be.jpg', null, '饮食', '1', '1', '386', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3299', '多吃零食危害大，家长们要提高警惕！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3299', 'https://image.findlinked.cn/xiangrui/2023-01-13/30b2ad9c-d80d-41a9-8ad4-199571afc7d0.jpg', null, '饮食', '1', '1', '346', '1', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3296', '饮食减肥效果好 但要避开这4大误区', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3296', 'https://image.findlinked.cn/xiangrui/2023-01-12/0a1e2a07-21c6-43a2-a5e8-92155699ed07.jpg', null, '饮食', '1', '1', '822', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3295', '吃得咸会让人更饿！盐摄入或是成人肥胖的危险因素', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3295', 'https://image.findlinked.cn/xiangrui/2023-01-11/fc00cecd-50f5-4e94-a28d-a2d558069a3a.jpg', null, '饮食', '1', '1', '151', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3290', '要想减肥效果好，吃晚餐要注意这3点', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3290', 'https://image.findlinked.cn/xiangrui/2023-01-10/62d0e1e8-0ef8-4a5a-855e-9d6d80a4412a.jpg', null, '饮食', '1', '1', '530', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3289', '减肥不可以吃肉？这5种肉可以放心吃', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3289', 'https://image.findlinked.cn/xiangrui/2023-01-10/b568b610-ac91-4246-9509-775a28d52b1c.jpg', null, '饮食', '1', '1', '924', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('87EDB9C5-AEA8-4F4B-982D-A1FD8461BDB1', '新中国70年中国体育新篇章', null, '/images/f55cbd91-37f1-4910-85ea-c52a3e562112-1681824106439.png', '<h3 style=\"text-indent: 20pt; text-align: justify;\"><span style=\"font-family: 微软雅黑;\">9月3日，“金龙鱼国家队运动营养师入驻国家队”启动仪式在人民日报新媒体演播厅正式举行。启动仪式由国家体育总局运动医学研究所、中国营养学会、益海嘉里食品营销有限公司共同举办。国家体育总局运动医学研究所所长谢敏豪、国家体育总局备战奥运会科技专家组专家冯美云、中国营养学会副理事长、中国营养学会运动营养分会主委常翠青、益海嘉里金龙鱼粮油食品股份有限公司总裁穆彦魁、益海嘉里食品营销有限公司董事长兼总经理陈波等嘉宾，与国家队运动员代表和入队营养师代表一起出席了启动仪式。</span></h3><h3 style=\"text-indent: 20pt; text-align: justify;\"><img src=\"https://i1.douguo.com/upload/keditor_img/20190904/20190904145843_57675.jpg\" alt=\"\" data-href=\"\" style=\"width: 690px;\"></h3><h3 style=\"text-align: center;\"><span style=\"font-family: 微软雅黑;\"> </span>备战2020东京奥运会金龙鱼运动营养师入驻国家队项目启动</h3><h3 style=\"text-align: justify;\"><span style=\"font-family: 微软雅黑;\"> &nbsp; &nbsp; 启动仪式现场，金龙鱼国家队运动营养师精彩亮相。据了解，未来，这些金龙鱼国家队运动营养师将入驻备战2020东京奥运会国家队，以科学健康的理念为奥运健儿提供营养支持，助力运动员备战奥运。与会嘉宾与金龙鱼国家队运动营养师以及国家队现役运动员代表何子红、黄丽英、张洁、谢作瑞一同为项目的官方LOGO揭幕。现场，益海嘉里研发中心副总经理姜元荣、国家体育总局备战奥运科技专家组专家冯美云还与中国女足现役国脚古雅沙、刘杉杉、中国国家田径跳高运动员郭劲岐以及前奥运冠军、原国家女排队长魏秋月还开展了一场精彩、特别的互动环节。</span></h3><h3 style=\"text-align: start;\"><strong>金龙鱼国家队运动营养师将入驻备战东京奥运会国家队，为奥运加油</strong></h3><h3 style=\"text-indent: 20pt; text-align: justify;\"><span style=\"font-family: 微软雅黑;\">科学营养是运动员保持良好体能和竞技状态的基础，是科学训练的重要组成部分。随着2020年东京奥运会的临近以及北京冬奥会备战工作的全面开展，完善国家队营养工作机制，增加国家队、训练基地营养师队伍，为国家队运动员们提供科学的营养保障与服务成为备战奥运的重中之重。</span></h3><h3 style=\"text-indent: 20pt; text-align: justify;\"><span style=\"font-family: 微软雅黑;\">益海嘉里旗下的金龙鱼作为国民粮油品牌，经过无数次市场的严苛考验和高标准要求，多年来一直坚守着卓越、安全、健康的高品质。此次牵手国家体育总局运动医学研究所、中国营养学会、共同启动“金龙鱼国家队运动营养师驻队项目”，通过在全国范围内招募、选拔、培训以及考核后，将有众多优秀的注册营养师，一路披荆斩棘通过考验，经过系统化的培训及实践，最终入驻55支奥运国家队，为国家队建立一支专业的营养师团队，这是自新中国成立以来，首次为国家队运动员们配备专门的营养师。</span><img src=\"https://i1.douguo.com/upload/keditor_img/20190904/20190904150057_54103.jpg\" alt=\"\" data-href=\"\" style=\"width: 690px;\"></h3><h3 style=\"text-indent: 20pt; text-align: justify;\"></h3><h3 style=\"text-align: center;\">金龙鱼运动营养师入驻国家队项目启动，益海嘉里金龙鱼粮油食品股份有限公司总裁穆彦魁发言</h3><h3 style=\"text-indent: 20pt; text-align: justify;\"></h3><h3 style=\"text-indent: 20pt; text-align: justify;\"><span style=\"font-family: 微软雅黑;\">据益海嘉里食品营销有限公司董事长兼总经理陈波介绍，这些金龙鱼国家队运动营养师组成的专业团队，将为国家队备战2020年东京奥运会提供科学的营养保障与服务，其中包括运动员日常膳食营养管理、运动员不同训练阶段以及大赛备战期间的营养方案制定，以及围绕营养监控、营养评估等各方面形成营养问题解决方案等等，全力保障运动员们的科学营养。同时，金龙鱼国家队运动营养师们还会记录服务国家队期间的所有数据结果，形成国家队运动员膳食保障与服务的相关报告，进一步完善建立国家队膳食营养体系。金龙鱼将以一贯倡导的科学营养、均衡健康的饮食方式，高品质的健康产品配合专业运动营养师队伍，为中国奥运健儿提供全方位的营养支持。为奥运，助力中国体育代表团未来在2020年东京奥运会、2022年北京冬奥会、2024年巴黎奥运会上赛出好成绩！</span></h3><h3 style=\"text-align: start;\"><strong>十年奥运坚守，金龙鱼用高品质助力中国体育</strong></h3><h3 style=\"text-indent: 20pt; text-align: justify;\"><span style=\"font-family: 微软雅黑;\">2008年，金龙鱼成为北京奥运会食用油独家供应商，在此后的十年里，金龙鱼一直秉持严谨的科学态度，坚持创新、品质、极致、放心、健康的全民健康新准则，以高标准、高品质的优质产品服务于消费者。与此同时，金龙鱼还借助于母公司益海嘉里的研发实力，不断将世界前沿高科技应用到产品创新研发中，这不仅是对粮油产品质量的进一步提升，还通过技术的创新实现了金龙鱼循环产业链模式的创新。</span></h3><h3 style=\"text-indent: 20pt; text-align: justify;\"><span style=\"font-family: 微软雅黑;\">2018年，北京冬奥组委正式宣布益海嘉里旗下的金龙鱼品牌成为北京2022年冬奥会和冬残奥会官方粮油产品赞助商。值得关注的是，金龙鱼还是中国奥委会官方粮油产品赞助商、2020年东京奥运会中国体育代表团官方粮油产品赞助商、2022年北京冬奥会中国体育代表团官方粮油产品赞助商以及2024年巴黎奥运会中国体育代表团官方粮油产品赞助商。</span><img src=\"https://i1.douguo.com/upload/keditor_img/20190904/20190904150221_43594.jpg\" alt=\"\" data-href=\"\" style=\"width: 690px;\"></h3><h3 style=\"text-indent: 20pt; text-align: justify;\"></h3><h3 style=\"text-align: center;\">金龙鱼运动营养师基地授牌仪式</h3><h3 style=\"text-indent: 20pt; text-align: justify;\"><span style=\"font-family: 微软雅黑;\">十年奥运坚守路，金龙鱼坚守的不仅仅是高品质的产品，更是一种对实现全民健康生活的目标追求。除了此次“金龙鱼国家队运动营养师入驻国家队”项目外，金龙鱼还联合专业营养师队伍，深入民众当中进行全民健康宣讲，以服务大众的赤诚之心推动国民营养新观念，金龙鱼正以身作则，为中国体育和全民的健康事业保驾护航。</span></h3><h3 style=\"text-align: start;\"><strong>秉持奥运精神，金龙鱼为全民健康保驾护航</strong></h3><h3 style=\"text-indent: 20pt; text-align: justify;\"><span style=\"font-family: 微软雅黑;\">在中国粮油行业的发展史上，金龙鱼一直有着举足轻重的位置。上市28年来，金龙鱼一直专注为国人打造安全的餐桌食品，以满足消费者的需求为已任，从食用油到大米，面粉再到挂面、杂粮以及米粉产品，金龙鱼的粮油产品已覆盖老百姓餐桌食品的方方面面，为老百姓的健康生活提供了丰富的选择。</span><img src=\"https://i1.douguo.com/upload/keditor_img/20190904/20190904150859_65294.jpg\" alt=\"\" data-href=\"\" style=\"width: 690px;\"></h3><h3 style=\"text-indent: 20pt; text-align: justify;\"></h3><h3 style=\"text-align: center;\">金龙鱼运动营养师入驻国家队项目启动，营养师代表宣誓备战2020东京奥运会</h3><h3 style=\"text-indent: 20pt; text-align: justify;\"><span style=\"font-family: 微软雅黑;\">益海嘉里金龙鱼粮油食品股份有限公司总裁穆彦魁表示，履行社会责任，守护国民健康生活是金龙鱼一直坚持的发展理念。未来，金龙鱼还将锐意进取，继续秉持奥运精神，为国人提供安全营养的高品质粮油产品，传递健康营养新理念，以新升级的高品质米、面、油等产品为奥运健儿保驾护航，成为全民健康水平提升背后的力量。</span></h3>', '饮食', '1', '0', '0', '0', '0', '2023-04-18 21:23:21');
INSERT INTO `tb_knowledge` VALUES ('3303', '新年活动已经上线|参与赢ipadmini6、无线耳机等好礼！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3303', 'https://image.findlinked.cn/xiangrui/2023-01-13/09d02ba9-6e9b-4732-9daf-83a607c7813e.png', null, '饮食', '1', '1', '208', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3304', '减肥期间注意早餐搭配 这4款早餐不要碰', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3304', 'https://image.findlinked.cn/xiangrui/2023-01-16/c23e194a-bf04-4a9e-8f93-34259ee09c44.jpg', null, '饮食', '1', '1', '381', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3308', '饮食方面注意这4点 “吃不胖”也不再是梦！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3308', 'https://image.findlinked.cn/xiangrui/2023-01-16/31f084af-80c3-496e-b175-ec393bd85c35.jpg', null, '饮食', '1', '1', '294', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3310', '这5种食物看似健康，但减肥要慎吃，它们是身材杀手', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3310', 'https://image.findlinked.cn/xiangrui/2023-01-16/1cf74704-dec8-43a6-b3cd-b0bb5c3594f1.png', null, '饮食', '1', '1', '384', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3314', '早餐吃什么营养又减肥', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3314', 'https://image.findlinked.cn/xiangrui/2023-01-16/7497368d-5082-45c9-b558-334fb44ec95c.png', null, '饮食', '1', '1', '331', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3315', '减肥水果早餐食谱 帮你燃脂又美颜', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3315', 'https://image.findlinked.cn/xiangrui/2023-01-16/2aae2114-d6bf-462f-badb-90d996435f9c.jpg', null, '饮食', '1', '1', '278', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3316', '减肥的人晚上吃西红柿效果怎么样？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3316', 'https://image.findlinked.cn/xiangrui/2023-01-16/804bcf46-e91d-4bf3-a15b-45b3b2eed56d.png', null, '饮食', '1', '1', '395', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3318', '吃芒果易长胖吗？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3318', 'https://image.findlinked.cn/xiangrui/2023-01-16/a0f606b8-5af7-4821-b7a3-3ddb7127b662.png', null, '饮食', '1', '1', '196', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3319', '减肥吃什么食物好？推荐这4种食物，帮助减肥又养生！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3319', 'https://image.findlinked.cn/xiangrui/2023-01-17/9514b9ed-308d-4fb6-b7f0-d091194e4080.jpg', null, '饮食', '1', '1', '510', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3321', '一个月不吃晚饭会怎么样？过午不食或让你变得更胖', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3321', 'https://image.findlinked.cn/xiangrui/2023-01-17/043a96b9-82ab-4c6c-82fe-b87519788d90.jpg', null, '饮食', '1', '1', '844', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3325', '猪肝是很好的补血食物，可有人说猪肝有毒？真正要担心的是胆固醇', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3325', 'https://image.findlinked.cn/xiangrui/2023-01-31/8ca38bdf-6c68-409a-b34e-869cd13824ce.jpg', null, '饮食', '1', '1', '157', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3326', '吃腊肉会致癌？食品专家指点4招，放心吃！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3326', 'https://image.findlinked.cn/xiangrui/2023-01-31/e92351db-6698-41dc-8a88-db20f362ee3d.jpg', null, '饮食', '1', '1', '144', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3328', '咖啡的好处你知道吗？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3328', 'https://image.findlinked.cn/xiangrui/2023-02-01/98369f7b-99d4-4402-9926-6503eb513d0d.jpg', null, '饮食', '1', '1', '357', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3330', '睡前大吃大喝会怎么样？如何吃夜宵才能不长胖！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3330', 'https://image.findlinked.cn/xiangrui/2023-02-01/61679c72-bddf-4da3-b59a-1c2a8c593c06.jpg', null, '饮食', '1', '1', '312', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3332', '你被这些“健康常识”欺骗了多久？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3332', 'https://image.findlinked.cn/xiangrui/2023-02-02/f3f16f91-eb75-47aa-bfdb-95ffa09f2013.jpg', null, '饮食', '1', '1', '209', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3336', '减肥期间怎么吃主食才不会有负罪感？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3336', 'https://image.findlinked.cn/xiangrui/2023-02-03/a13d7081-616a-4530-bbc1-65272056b79a.jpg', null, '饮食', '1', '1', '382', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3346', '为什么魔芋的饱腹感很强？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3346', 'https://image.findlinked.cn/xiangrui/2023-02-06/e05b7634-82bb-4428-8959-42c8dac8228d.jpg', null, '饮食', '1', '1', '244', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3349', '低碳饮食法真的能减肥吗？ 哪些食物有减肥效果？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3349', 'https://image.findlinked.cn/xiangrui/2023-02-07/308160e9-d8a8-454c-a640-d376428ecf27.jpeg', null, '饮食', '1', '1', '1285', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3353', '减肥欺骗餐是怎么回事？欺骗餐怎么吃才能减肥？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3353', 'https://image.findlinked.cn/xiangrui/2023-02-08/3fdd7137-082c-4511-8996-2a0f063150da.jpg', null, '饮食', '1', '1', '470', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3355', '晚餐决定你的体重和寿命！到底怎么吃才更健康', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3355', 'https://image.findlinked.cn/xiangrui/2023-02-08/01cf7ba5-646b-4aea-92cb-26dcbbda4bce.jpg', null, '饮食', '1', '1', '862', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3357', '越吃越瘦的杂粮有哪些？推荐这六种', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3357', 'https://image.findlinked.cn/xiangrui/2023-02-09/6ad30b79-aea0-4dbe-9199-7de1075e28a8.jpg', null, '饮食', '1', '1', '654', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3362', '吃姜需要去皮吗？吃对了养生，吃错反伤身！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3362', 'https://image.findlinked.cn/xiangrui/2023-02-10/fa1e7c3a-0976-413b-aaf7-f3b2980e14a8.jpg', null, '饮食', '1', '1', '185', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3363', '减肥吃什么面包不容易胖', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3363', 'https://image.findlinked.cn/xiangrui/2023-02-10/663ba534-04bc-4991-9722-b8e22f98860e.jpg', null, '饮食', '1', '1', '819', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3364', '高碳水饮食会导致肥胖？营养师更建议你吃，这3种低碳水的主食', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3364', 'https://image.findlinked.cn/xiangrui/2023-02-10/f6b2656c-7cf9-4643-8795-16ec280aa181.jpg', null, '饮食', '1', '1', '532', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3367', '减肥中，哪种日常用油更好？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3367', 'https://image.findlinked.cn/xiangrui/2023-02-10/177cb36a-5e89-4fbe-bfbf-300b84f95552.jpg', null, '饮食', '1', '1', '543', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3366', '健康吃火锅秘笈在此！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3366', 'https://image.findlinked.cn/xiangrui/2023-02-10/3867153d-52ae-43e5-98bf-866c535c8dac.jpg', null, '饮食', '1', '1', '465', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3368', '能用水果代替晚餐吗？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3368', 'https://image.findlinked.cn/xiangrui/2023-02-10/e8dfba3d-2c5c-4d36-a4c1-99fa50828516.jpg', null, '饮食', '1', '1', '886', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3370', '喝咖啡可以帮助减肥？咖啡要这样喝才能减肥', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3370', 'https://image.findlinked.cn/xiangrui/2023-02-13/fae6eb81-410c-44dc-8499-f261e5f08b2a.jpg', null, '饮食', '1', '1', '576', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3374', '怎样快速健康的减肥？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3374', 'https://image.findlinked.cn/xiangrui/2023-02-14/54906903-abbc-4a1f-8b1c-fb340de5695c.jpg', null, '饮食', '1', '1', '568', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3376', '隔夜菜会致癌？放了多久算隔夜？到底还能不能吃？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3376', 'https://image.findlinked.cn/xiangrui/2023-02-15/1e26bb9f-d6a5-4cd7-a5be-c6fa29339ee7.jpg', null, '饮食', '1', '1', '288', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3377', '喝茶对健康是有益还是有害？提醒：这5种茶或会伤身，要少喝', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3377', 'https://image.findlinked.cn/xiangrui/2023-02-15/2c983e3a-2713-4c51-9bf1-9e2713315f91.jpg', null, '饮食', '1', '1', '360', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3380', '减肥的人要多吃蔬菜？吃蔬菜要牢记这4点，才管用！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3380', 'https://image.findlinked.cn/xiangrui/2023-02-16/5e0828f8-dfb9-4477-84bc-8785109cccdd.jpg', null, '饮食', '1', '1', '730', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3388', '春季减肥饮食原则', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3388', 'https://image.findlinked.cn/xiangrui/2023-02-17/769170e2-8ce5-4a9c-a57f-c8811de7ecbd.jpg', null, '饮食', '1', '1', '1019', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3390', '吃馒头容易胖吗？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3390', 'https://image.findlinked.cn/xiangrui/2023-02-17/6195f1fb-036d-4d2d-87b5-5999635a6579.jpg', null, '饮食', '1', '1', '710', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3387', '腰围大怎么减肥效果好？试试7个“排毒通便”方法', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3387', 'https://image.findlinked.cn/xiangrui/2023-02-17/9f722a58-2db7-4f66-903f-6b555dd7e81c.jpg', null, '饮食', '1', '1', '643', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3391', '咸菜的热量高吗?', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3391', 'https://image.findlinked.cn/xiangrui/2023-02-17/ceadb0af-ae22-4be4-b4be-9ed707b01d16.jpg', null, '饮食', '1', '1', '364', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3392', '管不住嘴又想减肥？给出4点建议！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3392', 'https://image.findlinked.cn/xiangrui/2023-02-20/cc90bedf-3d06-4342-89f9-a3b1d5dd4820.jpg', null, '饮食', '1', '1', '907', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3394', '吃火龙果也能减肥 教你3种吃火龙果减肥的方法', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3394', 'https://image.findlinked.cn/xiangrui/2023-02-20/b6151dcd-09d6-4fbf-8d6a-a263f80f346c.jpg', null, '饮食', '1', '1', '550', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3395', '水果越吃越多就会对身体越来越好吗？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3395', 'https://image.findlinked.cn/xiangrui/2023-02-21/1a489cac-cd67-4391-abe6-e65e61fd6b0a.jpg', null, '饮食', '1', '1', '426', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3396', '牛油果能减肥还是增肥？给出标准答案！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3396', 'https://image.findlinked.cn/xiangrui/2023-02-21/7a3a4e7b-06e5-46e1-900d-00bc10131fb4.jpg', null, '饮食', '1', '1', '259', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3399', '减肥期间如何饮食？送你5个小技巧快速瘦身！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3399', 'https://image.findlinked.cn/xiangrui/2023-02-22/9fb7648e-06d5-4ad2-af35-573fb8dea7ab.jpg', null, '饮食', '1', '1', '776', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3400', '哪些食物吸脂能力强？减肥快试试这7种低卡食物', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3400', 'https://image.findlinked.cn/xiangrui/2023-02-22/512cfda6-790f-4390-95d6-bc8f70154491.jpg', null, '饮食', '1', '1', '1110', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3405', '适合减肥人群吃的食物有哪些？送你能帮助减脂的食物', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3405', 'https://image.findlinked.cn/xiangrui/2023-02-24/64758a4e-7168-4552-94b2-9eff5ff52a66.jpg', null, '饮食', '1', '1', '774', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3412', '减肥要管住嘴 减肥吃什么可以不长肉呢？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3412', 'https://image.findlinked.cn/xiangrui/2023-02-27/c26d8d29-0bd9-4ab2-baae-d8d35c78dcff.jpg', null, '饮食', '1', '1', '578', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3411', '减肥吃什么好呢？什么食物吃了不胖', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3411', 'https://image.findlinked.cn/xiangrui/2023-02-27/273a4cd2-f1fa-4dda-8f91-e998b4dc1469.jpg', null, '饮食', '1', '1', '819', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3413', '瘦身要多吃蔬菜水果？7款天然好食物帮来你瘦身', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3413', 'https://image.findlinked.cn/xiangrui/2023-02-28/239e5a4b-d04b-4c76-be76-2fcb740e36f2.jpg', null, '饮食', '1', '1', '523', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3415', '饮食快速减肥方法有哪些？ 这些天然减肥法效果神奇', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3415', 'https://image.findlinked.cn/xiangrui/2023-02-28/8bf82d1d-073b-40d9-8464-08b2dc55ba1e.jpg', null, '饮食', '1', '1', '403', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3417', '减肥也能吃碳水食物？盘点5种食物适合减肥期的食物！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3417', 'https://image.findlinked.cn/xiangrui/2023-03-01/d9c41e88-6619-4e9f-bed4-419ff3556c40.jpg', null, '饮食', '1', '1', '836', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3421', '减肥期间不要乱吃，这5种伪装食物最好是远离！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3421', 'https://image.findlinked.cn/xiangrui/2023-03-02/e43248ce-2d09-47a1-bf96-8a8c35e66753.jpg', null, '饮食', '1', '1', '1361', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3431', '土豆当主食可以减肥吗', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3431', 'https://image.findlinked.cn/xiangrui/2023-03-05/dbef51a8-a3fe-4cb5-b0ec-25ec8032d56a.jpg', null, '饮食', '1', '1', '871', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3139', '蔬菜热量低，吃蔬菜不会胖？这3大误区不要再中招了！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3139', 'https://image.findlinked.cn/xiangrui/2022-11-18/dfa45974-83fc-4e1c-82df-69f1573860a5.jpg', null, '饮食', '1', '1', '2875', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3432', '讨厌香菜，你会损失4大好处', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3432', 'https://image.findlinked.cn/xiangrui/2023-03-06/7ca65fe9-1738-4157-b115-e0d3a522fcc5.jpg', null, '饮食', '1', '1', '553', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3436', '人是如何一口一口吃胖的？详细讲解人类发胖的全过程！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3436', 'https://image.findlinked.cn/xiangrui/2023-03-07/da4c6004-917d-4987-bf3e-7ede313d1809.jpg', null, '饮食', '1', '1', '932', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3439', '健康的早餐应该是怎样的？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3439', 'https://image.findlinked.cn/xiangrui/2023-03-07/2cd5a8a3-9622-4f0d-aaf6-e5da0fee7db1.jpg', null, '饮食', '1', '1', '657', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3447', '控制好食欲能减肥吗？ 要怎么科学控制食欲呢？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3447', 'https://image.findlinked.cn/xiangrui/2023-03-10/bcb2d575-24b6-4dfa-88ea-4cdc016ec5be.jpg', null, '饮食', '1', '1', '439', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3446', '减肥期间食欲大开什么都想吃？控制食欲记住这5点', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3446', 'https://image.findlinked.cn/xiangrui/2023-03-10/76e60623-5146-4b82-b723-e42abd7f5a04.jpg', null, '饮食', '1', '1', '773', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3448', '减肥期间怎么吃晚餐？五个饮食要点要牢记', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3448', 'https://image.findlinked.cn/xiangrui/2023-03-10/08c42d7a-b761-4817-9b25-bf650cacc1a5.jpg', null, '饮食', '1', '1', '1676', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3451', '减肥期间需要戒掉的饮品有哪些？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3451', 'https://image.findlinked.cn/xiangrui/2023-03-12/5c04dfcf-5f43-480e-a68a-29cd8f45854d.jpg', null, '饮食', '1', '1', '739', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3453', '减肥期间吃这4种低热量食物有好处 增加饱腹感！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3453', 'https://image.findlinked.cn/xiangrui/2023-03-13/70895cf3-8934-4078-a894-f2982fc36a65.jpg', null, '饮食', '1', '1', '916', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3458', '4款减肥早餐搭配 从早开始疯狂甩肉', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3458', 'https://image.findlinked.cn/xiangrui/2023-03-14/989350b0-2e44-4a1f-91b4-c6b3d3b07c0f.jpg', null, '饮食', '1', '1', '1255', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3462', '减肥者可以放心吃哪些食物？ 这十种食物利于减肥', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3462', 'https://image.findlinked.cn/xiangrui/2023-03-15/e0011717-956e-4be3-a60c-8acd7e05394d.jpg', null, '饮食', '1', '1', '1199', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3465', '减肥时要多吃这6种食物，有助于快速减肥！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3465', 'https://image.findlinked.cn/xiangrui/2023-03-16/45bcdffc-4d34-416c-92e1-de92b7b8a3c7.jpg', null, '饮食', '1', '1', '1427', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3471', '减肥的人要多吃这4种食物 不容易发胖！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3471', 'https://image.findlinked.cn/xiangrui/2023-03-17/35a1ccee-cbff-41ac-87ca-4963ac08f4d4.jpg', null, '饮食', '1', '1', '1913', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3475', '可以帮助提高新陈代谢的几种食物', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3475', 'https://image.findlinked.cn/xiangrui/2023-03-18/a891a179-33bb-42c8-82c2-4f5a408c5ff5.jpg', null, '饮食', '1', '1', '950', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3476', '可以帮助提高新陈代谢的几种食物', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3476', 'https://image.findlinked.cn/xiangrui/2023-03-18/c27be098-9df5-46de-97d5-bcf2639aa7b3.jpg', null, '饮食', '1', '1', '1389', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3477', '几种饱腹感强热量低的食物', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3477', 'https://image.findlinked.cn/xiangrui/2023-03-19/3567b854-09b7-4ca5-a80d-1296b612ab12.jpg', null, '饮食', '1', '1', '1302', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3202', '易瘦体质真的存在吗？养成这5个习惯，让你变成易瘦体质！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3202', 'https://image.findlinked.cn/xiangrui/2023-03-20/f42492e8-94e7-45af-bbf5-0d3df0cc8b54.jpg', null, '饮食', '1', '1', '2403', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3478', '减肥期如何安排饮食结构？应掌握4个饮食小技巧', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3478', 'https://image.findlinked.cn/xiangrui/2023-03-20/84a86147-1760-4542-b3d8-cdf8c8e81f7f.jpg', null, '饮食', '1', '1', '740', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3480', '减肥人群要如何健康吃早餐？这5种食物不可缺少！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3480', 'https://image.findlinked.cn/xiangrui/2023-03-21/4c8878ce-234f-4d91-ad4a-1a340f2a7ad6.jpg', null, '饮食', '1', '1', '927', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3491', '怎么吃饭减肥 调节饮食顺序多吃也不胖', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3491', 'https://image.findlinked.cn/xiangrui/2023-03-23/60b5a547-c02d-4c0a-bd35-5deebfdcf13e.jpg', null, '饮食', '1', '1', '445', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3492', '每天吃鸡蛋，身体会发生什么变化？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3492', 'https://image.findlinked.cn/xiangrui/2023-03-24/f4a15f8e-8621-48e6-a292-1ee4d5cd6820.jpg', null, '饮食', '1', '1', '1417', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3496', '适合减肥时候吃的几种水果', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3496', 'https://image.findlinked.cn/xiangrui/2023-03-25/9f0d925e-8e3d-4bd5-afee-2a41bab57880.jpg', null, '饮食', '1', '1', '1311', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3497', '减肥吃鸡蛋能吃黄吗？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3497', 'https://image.findlinked.cn/xiangrui/2023-03-26/2f63ad7e-39a5-4543-bb92-d25091e6414b.jpg', null, '饮食', '1', '1', '776', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3499', '如何能做到吃饱又能够减肥？这6个饮食技巧快学起来', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3499', 'https://image.findlinked.cn/xiangrui/2023-03-27/6b2eb35e-2cd0-4a42-94e4-23a2117ef0d1.jpg', null, '饮食', '1', '1', '1037', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3501', '减肥的多吃这几种蔬菜有好处 燃脂效果好', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3501', 'https://image.findlinked.cn/xiangrui/2023-03-28/d66f00f7-eb0c-4b19-a085-d951c04eee28.jpg', null, '饮食', '1', '1', '1322', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3502', '适合减肥的几款食物 多吃能轻松减脂', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3502', 'https://image.findlinked.cn/xiangrui/2023-03-28/4f82c863-de22-47f7-bda9-c4b0975d0ed0.jpg', null, '饮食', '1', '1', '616', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3508', '减肥要怎么管住嘴迈开腿？ 哪些食物利于减肥？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3508', 'https://image.findlinked.cn/xiangrui/2023-03-29/9cdddb5d-d43b-4093-9288-6e397882a7f7.jpg', null, '饮食', '1', '1', '842', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3513', '减肥期间适合吃米饭吗？教你减肥期吃米饭的方法', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3513', 'https://image.findlinked.cn/xiangrui/2023-03-30/e608520e-e341-4d40-9f46-024128a4a9cb.jpg', null, '饮食', '1', '1', '550', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3514', '要想减肥效果好 这几种水果离不了', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3514', 'https://image.findlinked.cn/xiangrui/2023-03-30/cd981b1e-3e1d-404f-a853-360ece70204f.jpg', null, '饮食', '1', '1', '497', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3516', '茄子增肥还是减肥？4种吃法发挥减肥功效！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3516', 'https://image.findlinked.cn/xiangrui/2023-03-31/ef5e18a9-a01c-4aa0-9cb2-c731d981b4c4.jpg', null, '饮食', '1', '1', '712', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3521', '减肥时吃蔬菜的误区，会让你越来越胖！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3521', 'https://image.findlinked.cn/xiangrui/2023-04-02/3ccba6c8-8594-4b34-9c88-195584e3b7e5.jpg', null, '饮食', '1', '1', '1099', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3526', '想要减肥，一定要做的四件事', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3526', 'https://image.findlinked.cn/xiangrui/2023-04-04/34de806e-61b3-4c5e-b016-57a7ee9536d1.jpg', null, '饮食', '1', '1', '676', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3529', '减肥吃哪些食物不会胖', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3529', 'https://image.findlinked.cn/xiangrui/2023-04-06/b4fdd4aa-618c-45bb-8aee-2e3d2b916d54.jpg', null, '饮食', '1', '1', '498', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3535', '减肥期间吃哪些主食好？吃什么水果能帮助减肥？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3535', 'https://image.findlinked.cn/xiangrui/2023-04-07/d82cef81-fe61-40eb-b935-b06cbb0a0b04.jpg', null, '饮食', '1', '1', '594', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3536', '减肥期间，多吃这7种低热量食物，有助于减肥！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3536', 'https://image.findlinked.cn/xiangrui/2023-04-08/9b2c6180-e375-4109-ae60-dce773c03ed9.jpg', null, '饮食', '1', '1', '1482', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3537', '减肥期间吃水果有什么好处？这4种水果可常吃！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3537', 'https://image.findlinked.cn/xiangrui/2023-04-09/d7647af7-0e20-408b-a9c7-2d368e13ebcd.jpg', null, '饮食', '1', '1', '1088', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3538', '4种食物助力把脂肪“搬走”？教你把脂肪吃走！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3538', 'https://image.findlinked.cn/xiangrui/2023-04-10/3d07b8f7-1e75-4f12-a14f-940d78f82153.jpg', null, '饮食', '1', '1', '746', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3541', '多吃蔬菜可以减肥？可常吃哪些蔬菜减肥？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3541', 'https://image.findlinked.cn/xiangrui/2023-04-11/04ca73f1-8f50-424e-ae3f-2de6eea79ffb.jpg', null, '饮食', '1', '1', '639', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3545', '你错入减肥误区了吗？纠正8大饮食减肥错误', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3545', 'https://image.findlinked.cn/xiangrui/2023-04-12/fb42724e-f4a3-40f3-96cb-388437ede06b.jpg', null, '饮食', '1', '1', '775', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3546', '饮食减肥不可行？避免这些饮食减肥误区', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3546', 'https://image.findlinked.cn/xiangrui/2023-04-12/68e28ea0-2762-4a7c-83d6-6fb9744863bb.jpg', null, '饮食', '1', '1', '169', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3548', '牛奶减肥小常识有哪些？', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3548', 'https://image.findlinked.cn/xiangrui/2023-04-12/06406566-1515-4eef-a712-191e8047456c.jpg', null, '饮食', '1', '1', '173', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3552', '减肥期间不能错过的9种食物，帮你燃烧脂肪！', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3552', 'https://image.findlinked.cn/xiangrui/2023-04-13/8dc860ad-6871-4f13-b904-0d654bf4473a.jpg', null, '饮食', '1', '1', '1081', '0', '0', null);
INSERT INTO `tb_knowledge` VALUES ('3558', '吃绿豆芽能减肥吗', 'https://m.findlinked.com/pages/linkH5/recommendDetails/recommendDetails?id=3558', 'https://image.findlinked.cn/xiangrui/2023-04-15/8f7f791f-92b4-42b3-ba6b-8eb717652070.jpg', null, '饮食', '1', '1', '197', '0', '0', null);

-- ----------------------------
-- Table structure for tb_know_likelog
-- ----------------------------
DROP TABLE IF EXISTS `tb_know_likelog`;
CREATE TABLE `tb_know_likelog` (
  `id` varchar(255) NOT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `know_id` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_know_likelog
-- ----------------------------
INSERT INTO `tb_know_likelog` VALUES ('E6D4CD74-07C1-4144-B2E5-A4D8A3E90C56', '8281611F-4FA0-4238-BABD-71F46EB25B88', '3299', '2023-04-17 21:46:13');

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
