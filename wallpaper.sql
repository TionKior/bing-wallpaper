/*
 Navicat Premium Data Transfer

 Source Server         : 115.29.190.2
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : wallpaper

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 02/09/2022 13:27:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '最新');
INSERT INTO `category` VALUES (2, '热门');
INSERT INTO `category` VALUES (3, '自然');
INSERT INTO `category` VALUES (4, '动物');
INSERT INTO `category` VALUES (5, '植物');
INSERT INTO `category` VALUES (6, '运动');
INSERT INTO `category` VALUES (7, '汽车');
INSERT INTO `category` VALUES (8, '田园');
INSERT INTO `category` VALUES (13, 'sdsd');

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '反馈标题',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '反馈正文',
  `feedback_time` timestamp(0) DEFAULT NULL COMMENT '反馈时间',
  `state` int(0) DEFAULT 0 COMMENT '0:未解决,1:已解决',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `feedback_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户反馈表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES (5, 'sdsd', 'sdsd', '2022-05-26 01:35:55', 1);
INSERT INTO `feedback` VALUES (11, 'asdfaf', 'asfds', '2022-05-26 07:34:59', 0);
INSERT INTO `feedback` VALUES (12, 'asfdasf', 'adfasdf', '2022-05-26 07:35:01', 1);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `per_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键,权限表ID',
  `per_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '资源名称',
  `per_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '资源名称,menu,button等',
  `per_permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '权限字符串',
  `per_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '访问url地址',
  `per_available` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '是否可用,1:可用,0:不可用',
  PRIMARY KEY (`per_id`) USING BTREE,
  UNIQUE INDEX `permission_per_id_uindex`(`per_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键,角色ID',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色名称',
  `role_available` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色是否可用,1:可用,0:不可用',
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `role_role_id_uindex`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键,用户ID',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `user_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `user_salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户盐',
  `user_integral` int(0) DEFAULT 0 COMMENT '用户积分',
  `user_locked` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '账户是否被锁定,1:锁定,0:未锁定',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (6, 'ssx', '2948b2a14f28d65acd4d09ab1a8280d9', 'ssxsalt', 20, '0');
INSERT INTO `user` VALUES (7, 'asd', '1363b900689803a09daca55162045bb8', 'asdsalt', 0, '0');
INSERT INTO `user` VALUES (11, 'qwe', 'a3617f7aa698100cfdc8a951b5d043dc', 'qwesalt', 0, '0');
INSERT INTO `user` VALUES (13, 'sdf', '9cf34fa8d76fd45fb474d1e4724d3a14', 'sdfsalt', 0, '0');
INSERT INTO `user` VALUES (15, 'sd', '36843c817e95f3a245d9e292d2734913', 'sdsalt', 0, '0');

-- ----------------------------
-- Table structure for user_favorite
-- ----------------------------
DROP TABLE IF EXISTS `user_favorite`;
CREATE TABLE `user_favorite`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL,
  `wallpaper_id` int(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_favorite_id_uindex`(`id`) USING BTREE,
  INDEX `user_favorite_user_user_id_fk`(`user_id`) USING BTREE,
  INDEX `user_favorite_wallpaper_id_fk`(`wallpaper_id`) USING BTREE,
  CONSTRAINT `user_favorite_user_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_favorite_wallpaper_id_fk` FOREIGN KEY (`wallpaper_id`) REFERENCES `wallpaper` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_favorite
-- ----------------------------
INSERT INTO `user_favorite` VALUES (3, 6, 55);
INSERT INTO `user_favorite` VALUES (6, 6, 41);
INSERT INTO `user_favorite` VALUES (12, 6, 24);
INSERT INTO `user_favorite` VALUES (13, 6, 12);
INSERT INTO `user_favorite` VALUES (15, 6, 7);
INSERT INTO `user_favorite` VALUES (21, 11, 20);
INSERT INTO `user_favorite` VALUES (26, 11, 47);
INSERT INTO `user_favorite` VALUES (28, 11, 55);
INSERT INTO `user_favorite` VALUES (29, 15, 76);

-- ----------------------------
-- Table structure for user_feedback
-- ----------------------------
DROP TABLE IF EXISTS `user_feedback`;
CREATE TABLE `user_feedback`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(0) DEFAULT NULL COMMENT '用户ID',
  `feedback_id` int(0) DEFAULT NULL COMMENT '反馈信息ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_feedback_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户反馈中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_feedback
-- ----------------------------
INSERT INTO `user_feedback` VALUES (2, 13, 5);
INSERT INTO `user_feedback` VALUES (8, 11, 11);
INSERT INTO `user_feedback` VALUES (9, 11, 12);

-- ----------------------------
-- Table structure for wallpaper
-- ----------------------------
DROP TABLE IF EXISTS `wallpaper`;
CREATE TABLE `wallpaper`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `image_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `category_id` bigint(0) DEFAULT 1,
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '史晟贤',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `create_time` timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `wallpaper_classify_id_fk`(`category_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '壁纸表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wallpaper
-- ----------------------------
INSERT INTO `wallpaper` VALUES (1, 'http://localhost:8080/images/bingwallpaper/350a67d9-da8f-11ec-ac92-e0d4e8a3edc7.jpg', '彩虹河里的佛罗里达红肚龟', 1, 'Michel Roggo/Minden Pictures', '彩虹泉州立公园', '2022-05-23 00:00:00');
INSERT INTO `wallpaper` VALUES (5, 'http://localhost:8080/images/bingwallpaper/39bd3c19-da8f-11ec-9af1-e0d4e8a3edc7.jpg', '张家界大峡谷玻璃桥', 1, 'MediaProduction/Getty Images', '湖南', '2022-05-23 00:00:00');
INSERT INTO `wallpaper` VALUES (6, 'http://localhost:8080/images/bingwallpaper/3ab81a6e-da8f-11ec-821d-e0d4e8a3edc7.jpg', '护城河边的格吕克斯堡城堡', 1, 'Christian Back/eStock Photo', '德国', '2022-05-23 19:55:24');
INSERT INTO `wallpaper` VALUES (7, 'http://localhost:8080/images/bingwallpaper/3b29cace-da8f-11ec-9f7f-e0d4e8a3edc7.jpg', '马拉斯盐田', 1, 'Fotofeeling/Westend61 on Offset/Shutterstock', '秘鲁', '2022-05-23 19:55:25');
INSERT INTO `wallpaper` VALUES (9, 'http://localhost:8080/images/bingwallpaper/3dc3d444-da8f-11ec-b637-e0d4e8a3edc7.jpg', '贝尔尼纳山脉和血月', 1, 'Bernd Zoller/Shutterstock', '瑞士', '2022-05-23 19:55:29');
INSERT INTO `wallpaper` VALUES (10, 'http://localhost:8080/images/bingwallpaper/3ed261e8-da8f-11ec-ad79-e0d4e8a3edc7.jpg', '金德代克风车群', 1, 'Achim Thomae/Getty Images', '荷兰', '2022-05-23 19:55:31');
INSERT INTO `wallpaper` VALUES (11, 'http://localhost:8080/images/bingwallpaper/4067ed4a-da8f-11ec-9b82-e0d4e8a3edc7.jpg', '一只马赛长颈鹿', 1, 'Andy Rouse/Minden Pictures', '肯尼亚马赛马拉国家保护区', '2022-05-23 19:55:33');
INSERT INTO `wallpaper` VALUES (12, 'http://localhost:8080/images/bingwallpaper/419e5a8e-da8f-11ec-9113-e0d4e8a3edc7.jpg', '布拉塞河上的雾天黎明', 1, 'fstopphotography/Getty Images', '英国湖区国家公园', '2022-05-23 19:55:36');
INSERT INTO `wallpaper` VALUES (13, 'http://localhost:8080/images/bingwallpaper/43dc344d-da8f-11ec-a961-e0d4e8a3edc7.jpg', '圣托里尼岛上的伊亚镇', 1, 'Zebra-Studio/Shutterstock', '希腊', '2022-05-23 19:55:39');
INSERT INTO `wallpaper` VALUES (14, 'http://localhost:8080/images/bingwallpaper/447b9a07-da8f-11ec-bf83-e0d4e8a3edc7.jpg', '春天的豹溪瀑布', 1, 'Stephen Matera/Tandem Stills + Motion', '吉福德·平肖国家森林', '2022-05-23 19:55:40');
INSERT INTO `wallpaper` VALUES (19, 'http://localhost:8080/images/bingwallpaper/e6c28064-3db7-469d-b136-66a1fe9b2510.jpg', '长颈鹿', 8, '长颈鹿', '长颈鹿', '2022-05-24 08:48:08');
INSERT INTO `wallpaper` VALUES (20, 'http://localhost:8080/images/bingwallpaper/f7fd9db9-db3f-11ec-90c1-e0d4e8a3edc7.jpg', '科纳提国家公园', 1, 'Anton Petrus/Getty Images', '克罗地亚', '2022-05-24 17:00:33');
INSERT INTO `wallpaper` VALUES (21, 'http://localhost:8080/images/bingwallpaper/f8fbbb35-db3f-11ec-b40b-e0d4e8a3edc7.jpg', '彩虹河里的佛罗里达红肚龟', 1, 'Michel Roggo/Minden Pictures', '彩虹泉州立公园', '2022-05-24 17:00:34');
INSERT INTO `wallpaper` VALUES (22, 'http://localhost:8080/images/bingwallpaper/fab8df1e-db3f-11ec-84d9-e0d4e8a3edc7.jpg', '伯切尔氏斑马和一只牛白鹭', 1, 'Richard Du Toit/Minden Pictures', '南非利特弗雷自然保护区', '2022-05-24 17:00:37');
INSERT INTO `wallpaper` VALUES (23, 'http://localhost:8080/images/bingwallpaper/fbccffc3-db3f-11ec-86b7-e0d4e8a3edc7.jpg', '阿尔比恩瀑布', 1, 'Alexander Sviridov/Shutterstock', '汉密尔顿', '2022-05-24 17:00:39');
INSERT INTO `wallpaper` VALUES (24, 'http://localhost:8080/images/bingwallpaper/fc944e5f-db3f-11ec-bc3b-e0d4e8a3edc7.jpg', '', 1, 'Mirek Kijewski/Getty Images', '山西', '2022-05-24 17:00:41');
INSERT INTO `wallpaper` VALUES (25, 'http://localhost:8080/images/bingwallpaper/fd9d6e2a-db3f-11ec-bd71-e0d4e8a3edc7.jpg', '张家界大峡谷玻璃桥', 1, 'MediaProduction/Getty Images', '湖南', '2022-05-24 17:00:42');
INSERT INTO `wallpaper` VALUES (26, 'http://localhost:8080/images/bingwallpaper/feef4536-db3f-11ec-865c-e0d4e8a3edc7.jpg', '护城河边的格吕克斯堡城堡', 1, 'Christian Back/eStock Photo', '德国', '2022-05-24 17:00:44');
INSERT INTO `wallpaper` VALUES (27, 'http://localhost:8080/images/bingwallpaper/002c2c93-db40-11ec-83c7-e0d4e8a3edc7.jpg', '马拉斯盐田', 1, 'Fotofeeling/Westend61 on Offset/Shutterstock', '秘鲁', '2022-05-24 17:00:50');
INSERT INTO `wallpaper` VALUES (28, 'http://localhost:8080/images/bingwallpaper/03d88a61-db40-11ec-838a-e0d4e8a3edc7.jpg', '花丛中的小穴鸮们', 1, 'Roberta Olenick/Alamy', '美国科罗拉多州', '2022-05-24 17:00:53');
INSERT INTO `wallpaper` VALUES (29, 'http://localhost:8080/images/bingwallpaper/041d5e53-db40-11ec-ac8e-e0d4e8a3edc7.jpg', '贝尔尼纳山脉和血月', 1, 'Bernd Zoller/Shutterstock', '瑞士', '2022-05-24 17:00:56');
INSERT INTO `wallpaper` VALUES (30, 'http://localhost:8080/images/bingwallpaper/06cc8a69-db40-11ec-adfa-e0d4e8a3edc7.jpg', '金德代克风车群', 1, 'Achim Thomae/Getty Images', '荷兰', '2022-05-24 17:00:58');
INSERT INTO `wallpaper` VALUES (31, 'http://localhost:8080/images/bingwallpaper/08641976-db40-11ec-a472-e0d4e8a3edc7.jpg', '一只马赛长颈鹿', 1, 'Andy Rouse/Minden Pictures', '肯尼亚马赛马拉国家保护区', '2022-05-24 17:01:00');
INSERT INTO `wallpaper` VALUES (32, 'http://localhost:8080/images/bingwallpaper/0b39df5c-db40-11ec-85d3-e0d4e8a3edc7.jpg', '布拉塞河上的雾天黎明', 1, 'fstopphotography/Getty Images', '英国湖区国家公园', '2022-05-24 17:01:05');
INSERT INTO `wallpaper` VALUES (33, 'http://localhost:8080/images/bingwallpaper/0bca7980-db40-11ec-a08c-e0d4e8a3edc7.jpg', '圣托里尼岛上的伊亚镇', 1, 'Zebra-Studio/Shutterstock', '希腊', '2022-05-24 17:01:06');
INSERT INTO `wallpaper` VALUES (34, 'http://localhost:8080/images/bingwallpaper/0c0bc41c-db40-11ec-a4e2-e0d4e8a3edc7.jpg', '春天的豹溪瀑布', 1, 'Stephen Matera/Tandem Stills + Motion', '吉福德·平肖国家森林', '2022-05-24 17:01:07');
INSERT INTO `wallpaper` VALUES (35, 'http://localhost:8080/images/bingwallpaper/0dec2f39-db40-11ec-a220-e0d4e8a3edc7.jpg', '从观景台俯瞰格雷梅', 1, 'Anton Petrus/Getty Images', '格雷梅国家公园', '2022-05-24 17:01:10');
INSERT INTO `wallpaper` VALUES (36, 'http://localhost:8080/images/bingwallpaper/0f74ae79-db40-11ec-a21f-e0d4e8a3edc7.jpg', '考拉熊妈妈和个月大的宝宝', 1, 'Suzi Eszterhas/Minden Pictures', '澳大利亚昆士兰', '2022-05-24 17:01:12');
INSERT INTO `wallpaper` VALUES (37, 'http://localhost:8080/images/bingwallpaper/10c481fa-db40-11ec-a046-e0d4e8a3edc7.jpg', '拉西拉天文台上的天文望远镜设备', 1, 'Alberto Ghizzi Panizza/Getty Images', '智利', '2022-05-24 17:01:14');
INSERT INTO `wallpaper` VALUES (38, 'http://localhost:8080/images/bingwallpaper/1160bb95-db40-11ec-94eb-e0d4e8a3edc7.jpg', '一条蜿蜒穿过开满蓝铃花森林的小径', 1, 'JayKay57/Getty Images', '英格兰赫特福德郡', '2022-05-24 17:01:15');
INSERT INTO `wallpaper` VALUES (39, 'http://localhost:8080/images/bingwallpaper/13211d15-db40-11ec-aa62-e0d4e8a3edc7.jpg', '蓝色龙舌兰田', 1, 'Brian Overcast/Alamy', '墨西哥哈利斯科州龙舌兰酒产区', '2022-05-24 17:01:18');
INSERT INTO `wallpaper` VALUES (40, 'http://localhost:8080/images/bingwallpaper/14e32054-db40-11ec-9b48-e0d4e8a3edc7.jpg', '托菲诺的麦肯齐海滩', 1, 'Robert Postma/Plainpicture/Design Pics', '加拿大不列颠哥伦比亚省', '2022-05-24 17:01:22');
INSERT INTO `wallpaper` VALUES (41, 'http://localhost:8080/images/bingwallpaper/15c8a66f-db40-11ec-ba1e-e0d4e8a3edc7.jpg', '鸳鸯妈妈和小鸳鸯们', 1, 'VDCM image/Getty Images', '韩国', '2022-05-24 17:01:23');
INSERT INTO `wallpaper` VALUES (42, 'http://localhost:8080/images/bingwallpaper/1603782b-db40-11ec-b031-e0d4e8a3edc7.jpg', '土耳其棉花堡的石灰华梯田', 1, 'bybostanci/Getty Images', '山西', '2022-05-24 17:01:23');
INSERT INTO `wallpaper` VALUES (43, 'http://localhost:8080/images/bingwallpaper/17d3253f-db40-11ec-8196-e0d4e8a3edc7.jpg', '盛开的金链花树和紫色葱属植物', 1, 'Greg Vaughn/Alamy', '加拿大温哥华范度森植物园', '2022-05-24 17:01:26');
INSERT INTO `wallpaper` VALUES (44, 'http://localhost:8080/images/bingwallpaper/230341d4-db40-11ec-a139-e0d4e8a3edc7.jpg', '正在投喂幼鸟的戴胜鸟', 1, 'Slawek Staszczuk/Alamy', '德国', '2022-05-24 17:01:45');
INSERT INTO `wallpaper` VALUES (45, 'http://localhost:8080/images/bingwallpaper/24b93025-db40-11ec-8e53-e0d4e8a3edc7.jpg', '巨型红杉的小萌芽', 1, 'Hutch Axilrod/Getty Images', '加利福尼亚州', '2022-05-24 17:01:51');
INSERT INTO `wallpaper` VALUES (46, 'http://localhost:8080/images/bingwallpaper/26cda7f3-db40-11ec-b077-e0d4e8a3edc7.jpg', '大脊山', 1, 'John Finney Photography/Moment/Getty Images', '英格兰峰区国家公园', '2022-05-24 17:01:51');
INSERT INTO `wallpaper` VALUES (47, 'http://localhost:8080/images/bingwallpaper/27caaa5a-db40-11ec-ba2e-e0d4e8a3edc7.jpg', '午夜的太阳', 1, 'Nature Picture Library/Alamy', '挪威斯瓦尔巴群岛', '2022-05-24 17:01:53');
INSERT INTO `wallpaper` VALUES (48, 'http://localhost:8080/images/bingwallpaper/28bbb38d-db40-11ec-8364-e0d4e8a3edc7.jpg', '下莱茵省的胡内城堡', 1, 'Leonid Andronov/Alamy', '法国阿尔萨斯大区', '2022-05-24 17:01:55');
INSERT INTO `wallpaper` VALUES (49, 'http://localhost:8080/images/bingwallpaper/28eb96b5-db40-11ec-83c1-e0d4e8a3edc7.jpg', '圣安德鲁斯湾岸边的三只王企鹅', 1, 'Paul Souders/Getty Images', '南乔治亚岛', '2022-05-24 17:01:55');
INSERT INTO `wallpaper` VALUES (50, 'http://localhost:8080/images/bingwallpaper/2abd4975-db40-11ec-9b1c-e0d4e8a3edc7.jpg', '半穹顶景观点上空的银河', 1, 'Cory Marshall/Tandem Stills + Motion', '优胜美地国家公园', '2022-05-24 17:01:58');
INSERT INTO `wallpaper` VALUES (51, 'http://localhost:8080/images/bingwallpaper/2b6a62bd-db40-11ec-a08b-e0d4e8a3edc7.jpg', '维多利亚纪念堂', 1, 'Roop_Dey/Shutterstock', '印度加尔各答', '2022-05-24 17:01:59');
INSERT INTO `wallpaper` VALUES (52, 'http://localhost:8080/images/bingwallpaper/2c0d3a8e-db40-11ec-9857-e0d4e8a3edc7.jpg', '卡鲁拉国家公园里的小湖', 1, 'Sven Zacek/Minden Pictures', '爱沙尼亚', '2022-05-24 17:02:00');
INSERT INTO `wallpaper` VALUES (53, 'http://localhost:8080/images/bingwallpaper/2c299fad-db40-11ec-aded-e0d4e8a3edc7.jpg', '在公路上吃草的绵羊', 1, 'Matthew Kuhns/Tandem Stills + Motion', '冰岛', '2022-05-24 17:02:00');
INSERT INTO `wallpaper` VALUES (54, 'http://localhost:8080/images/bingwallpaper/2ca70f4d-db40-11ec-ac67-e0d4e8a3edc7.jpg', '两只躲在妈妈尾羽下的疣鼻小天鹅', 1, 'Vicki Jauron/Getty Images', '纽约长岛', '2022-05-24 17:02:01');
INSERT INTO `wallpaper` VALUES (55, 'http://localhost:8080/images/bingwallpaper/2d5d3f47-db40-11ec-8b25-e0d4e8a3edc7.jpg', '十六湖国家公园', 1, 'Janne Kahila/Getty Images', '克罗地亚', '2022-05-24 17:02:02');
INSERT INTO `wallpaper` VALUES (56, 'http://localhost:8080/images/bingwallpaper/c9f73f1f-dcd3-11ec-a7d5-e0d4e8a3edc7.jpg', '蒙特维德云雾森林中的吊桥', 1, 'Dmitriy Burlakov/Getty Images', '哥斯达黎加', '2022-05-26 17:11:12');
INSERT INTO `wallpaper` VALUES (57, 'http://localhost:8080/images/bingwallpaper/caf473a3-dcd3-11ec-9e47-e0d4e8a3edc7.jpg', '格拉纳达的阿尔罕布拉', 1, 'Armand Tamboly/Getty Images', '安达卢西亚省', '2022-05-26 17:11:14');
INSERT INTO `wallpaper` VALUES (58, 'http://localhost:8080/images/bingwallpaper/cc82f866-dcd3-11ec-a812-e0d4e8a3edc7.jpg', '科纳提国家公园', 1, 'Anton Petrus/Getty Images', '克罗地亚', '2022-05-26 17:11:17');
INSERT INTO `wallpaper` VALUES (59, 'http://localhost:8080/images/bingwallpaper/ccad4dfa-dcd3-11ec-9f49-e0d4e8a3edc7.jpg', '彩虹河里的佛罗里达红肚龟', 1, 'Michel Roggo/Minden Pictures', '彩虹泉州立公园', '2022-05-26 17:11:17');
INSERT INTO `wallpaper` VALUES (60, 'http://localhost:8080/images/bingwallpaper/cd0c384a-dcd3-11ec-9951-e0d4e8a3edc7.jpg', '伯切尔氏斑马和一只牛白鹭', 1, 'Richard Du Toit/Minden Pictures', '南非利特弗雷自然保护区', '2022-05-26 17:11:17');
INSERT INTO `wallpaper` VALUES (61, 'http://localhost:8080/images/bingwallpaper/ce061d94-dcd3-11ec-9911-e0d4e8a3edc7.jpg', '阿尔比恩瀑布', 1, 'Alexander Sviridov/Shutterstock', '汉密尔顿', '2022-05-26 17:11:19');
INSERT INTO `wallpaper` VALUES (62, 'http://localhost:8080/images/bingwallpaper/ce6d3a09-dcd3-11ec-9b0b-e0d4e8a3edc7.jpg', '', 1, 'Mirek Kijewski/Getty Images', '山西', '2022-05-26 17:11:20');
INSERT INTO `wallpaper` VALUES (63, 'http://localhost:8080/images/bingwallpaper/d01cd098-dcd3-11ec-af7f-e0d4e8a3edc7.jpg', '张家界大峡谷玻璃桥', 1, 'MediaProduction/Getty Images', '湖南', '2022-05-26 17:11:23');
INSERT INTO `wallpaper` VALUES (64, 'http://localhost:8080/images/bingwallpaper/d181158f-dcd3-11ec-b5f2-e0d4e8a3edc7.jpg', '护城河边的格吕克斯堡城堡', 1, 'Christian Back/eStock Photo', '德国', '2022-05-26 17:11:25');
INSERT INTO `wallpaper` VALUES (65, 'http://localhost:8080/images/bingwallpaper/d1e50fbd-dcd3-11ec-95cf-e0d4e8a3edc7.jpg', '马拉斯盐田', 1, 'Fotofeeling/Westend61 on Offset/Shutterstock', '秘鲁', '2022-05-26 17:11:26');
INSERT INTO `wallpaper` VALUES (66, 'http://localhost:8080/images/bingwallpaper/d3415e8b-dcd3-11ec-9218-e0d4e8a3edc7.jpg', '花丛中的小穴鸮们', 1, 'Roberta Olenick/Alamy', '美国科罗拉多州', '2022-05-26 17:11:28');
INSERT INTO `wallpaper` VALUES (67, 'http://localhost:8080/images/bingwallpaper/d4975a09-dcd3-11ec-b2fe-e0d4e8a3edc7.jpg', '贝尔尼纳山脉和血月', 1, 'Bernd Zoller/Shutterstock', '瑞士', '2022-05-26 17:11:30');
INSERT INTO `wallpaper` VALUES (68, 'http://localhost:8080/images/bingwallpaper/d69c903a-dcd3-11ec-a45b-e0d4e8a3edc7.jpg', '金德代克风车群', 1, 'Achim Thomae/Getty Images', '荷兰', '2022-05-26 17:11:34');
INSERT INTO `wallpaper` VALUES (69, 'http://localhost:8080/images/bingwallpaper/d81742d7-dcd3-11ec-bc90-e0d4e8a3edc7.jpg', '一只马赛长颈鹿', 1, 'Andy Rouse/Minden Pictures', '肯尼亚马赛马拉国家保护区', '2022-05-26 17:11:36');
INSERT INTO `wallpaper` VALUES (70, 'http://localhost:8080/images/bingwallpaper/d999663c-dcd3-11ec-b20b-e0d4e8a3edc7.jpg', '布拉塞河上的雾天黎明', 1, 'fstopphotography/Getty Images', '英国湖区国家公园', '2022-05-26 17:11:39');
INSERT INTO `wallpaper` VALUES (71, 'http://localhost:8080/images/bingwallpaper/da12e636-dcd3-11ec-82dd-e0d4e8a3edc7.jpg', '圣托里尼岛上的伊亚镇', 1, 'Zebra-Studio/Shutterstock', '希腊', '2022-05-26 17:11:39');
INSERT INTO `wallpaper` VALUES (72, 'http://localhost:8080/images/bingwallpaper/da428e1a-dcd3-11ec-94e3-e0d4e8a3edc7.jpg', '春天的豹溪瀑布', 1, 'Stephen Matera/Tandem Stills + Motion', '吉福德·平肖国家森林', '2022-05-26 17:11:40');
INSERT INTO `wallpaper` VALUES (73, 'http://localhost:8080/images/bingwallpaper/dba242e9-dcd3-11ec-9324-e0d4e8a3edc7.jpg', '从观景台俯瞰格雷梅', 1, 'Anton Petrus/Getty Images', '格雷梅国家公园', '2022-05-26 17:11:42');
INSERT INTO `wallpaper` VALUES (74, 'http://localhost:8080/images/bingwallpaper/dd128efa-dcd3-11ec-b2d2-e0d4e8a3edc7.jpg', '考拉熊妈妈和个月大的宝宝', 1, 'Suzi Eszterhas/Minden Pictures', '澳大利亚昆士兰', '2022-05-26 17:11:44');
INSERT INTO `wallpaper` VALUES (75, 'http://localhost:8080/images/bingwallpaper/dee2447e-dcd3-11ec-8714-e0d4e8a3edc7.jpg', '拉西拉天文台上的天文望远镜设备', 1, 'Alberto Ghizzi Panizza/Getty Images', '智利', '2022-05-26 17:11:47');
INSERT INTO `wallpaper` VALUES (76, 'http://localhost:8080/images/bingwallpaper/e03957c0-dcd3-11ec-815c-e0d4e8a3edc7.jpg', '一条蜿蜒穿过开满蓝铃花森林的小径', 1, 'JayKay57/Getty Images', '英格兰赫特福德郡', '2022-05-26 17:11:50');
INSERT INTO `wallpaper` VALUES (77, 'http://localhost:8080/images/bingwallpaper/e10d5520-dcd3-11ec-8876-e0d4e8a3edc7.jpg', '蓝色龙舌兰田', 1, 'Brian Overcast/Alamy', '墨西哥哈利斯科州龙舌兰酒产区', '2022-05-26 17:11:51');
INSERT INTO `wallpaper` VALUES (78, 'http://localhost:8080/images/bingwallpaper/b91eacb3-2338-4fd2-ba5a-50f6b07b0826.jpg', '时代', 5, '时代', '时代', '2022-05-26 09:38:10');
INSERT INTO `wallpaper` VALUES (79, 'http://localhost:8080/images/bingwallpaper/641e2fc7-0a1d-4362-8343-63183a13929f.jpg', '时代', 5, '时代', '时代', '2022-05-26 09:42:52');
INSERT INTO `wallpaper` VALUES (81, 'http://localhost:8080/images/bingwallpaper/626a1344-db3f-4ff1-a8a8-47f279a4f859.jpg', '时代', 4, '时代', '时代', '2022-05-26 09:45:57');
INSERT INTO `wallpaper` VALUES (82, 'http://localhost:8080/images/bingwallpaper/b0c962da-1c38-46d8-bbd5-8b181238047a.jpg', 'fsdf ', 2, 'sdfds', 'dsffd', '2022-05-26 09:46:40');
INSERT INTO `wallpaper` VALUES (83, 'http://localhost:8080/images/bingwallpaper/6b6bacf3-43d2-4d56-83b5-5a0943acfa5a.jpg', 'sdasd ', 2, 'asd as', ' asd ', '2022-05-26 09:49:26');

-- ----------------------------
-- Table structure for wallpaper_category
-- ----------------------------
DROP TABLE IF EXISTS `wallpaper_category`;
CREATE TABLE `wallpaper_category`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `wallpaper_id` bigint(0) NOT NULL COMMENT '壁纸ID',
  `category_id` bigint(0) NOT NULL COMMENT '分类ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `wallpaper_category_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '壁纸分类中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wallpaper_category
-- ----------------------------
INSERT INTO `wallpaper_category` VALUES (1, 230, 2);
INSERT INTO `wallpaper_category` VALUES (2, 231, 3);
INSERT INTO `wallpaper_category` VALUES (3, 232, 4);
INSERT INTO `wallpaper_category` VALUES (4, 233, 5);
INSERT INTO `wallpaper_category` VALUES (5, 234, 6);
INSERT INTO `wallpaper_category` VALUES (6, 237, 7);
INSERT INTO `wallpaper_category` VALUES (7, 239, 2);
INSERT INTO `wallpaper_category` VALUES (8, 240, 3);
INSERT INTO `wallpaper_category` VALUES (9, 241, 4);
INSERT INTO `wallpaper_category` VALUES (10, 245, 5);
INSERT INTO `wallpaper_category` VALUES (11, 1, 1);
INSERT INTO `wallpaper_category` VALUES (12, 15, 6);
INSERT INTO `wallpaper_category` VALUES (13, 16, 4);
INSERT INTO `wallpaper_category` VALUES (14, 17, 2);
INSERT INTO `wallpaper_category` VALUES (15, 18, 1);
INSERT INTO `wallpaper_category` VALUES (16, 19, 8);
INSERT INTO `wallpaper_category` VALUES (17, 78, 5);
INSERT INTO `wallpaper_category` VALUES (18, 79, 5);
INSERT INTO `wallpaper_category` VALUES (19, 81, 4);
INSERT INTO `wallpaper_category` VALUES (20, 82, 2);
INSERT INTO `wallpaper_category` VALUES (21, 83, 2);

SET FOREIGN_KEY_CHECKS = 1;
