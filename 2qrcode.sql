/*
 Navicat Premium Data Transfer

 Source Server         : 139.159.236.186
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 139.159.236.186:3306
 Source Schema         : 2qrcode

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 10/04/2020 17:30:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for 2code_code
-- ----------------------------
DROP TABLE IF EXISTS `2code_code`;
CREATE TABLE `2code_code`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NOT NULL,
  `view_num` int(0) NOT NULL,
  `content` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address_id` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT ' 空',
  `info` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 2code_code
-- ----------------------------
INSERT INTO `2code_code` VALUES (2, 0, 2, 'www.qq.com', '北京', '', ' 腾讯', '');
INSERT INTO `2code_code` VALUES (4, 0, 2, 'www.hao123.com', ' 南宁', '', ' hao123', '');
INSERT INTO `2code_code` VALUES (6, 0, 2, 'www.mi.com', ' 杭州', '', ' 小米', '');
INSERT INTO `2code_code` VALUES (7, 0, 1, '  jxjweb.top  ', '西安', '', ' 个人空间', '');
INSERT INTO `2code_code` VALUES (8, 0, 1, 'www.  jxjweb.top  ', '西宁', '', '个人空间', '');
INSERT INTO `2code_code` VALUES (14, 0, 1, '   jxjweb.top  ', ' 西宁', '', '测试', '');
INSERT INTO `2code_code` VALUES (15, 0, 2, '  jxjweb.top  ', '西宁', '', 'baiyu', '');
INSERT INTO `2code_code` VALUES (16, 0, 6, '  jxjweb.top  ', '西宁', '', 'baiyu', '');
INSERT INTO `2code_code` VALUES (17, 0, 2, '  jxjweb.top  ', '西宁', '', 'baiyu', '');
INSERT INTO `2code_code` VALUES (18, 0, 6, 'www.baidu.com', ' 西雅图', '', '天猫', '');
INSERT INTO `2code_code` VALUES (19, 0, 3, '  jxjweb.top  ', ' 西安', '', '个人空间', '');
INSERT INTO `2code_code` VALUES (20, 0, 5, 'www.mi.com', '西安55', '', '百度 test', '');
INSERT INTO `2code_code` VALUES (21, 0, 1, '  jxjweb.top  ', '辽宁省 丹东市 振兴区 ', '', '测试2', '测试15');
INSERT INTO `2code_code` VALUES (23, 0, 1, '  jxjweb.top  ', '', '', '测试', 'test1552');
INSERT INTO `2code_code` VALUES (24, 0, 0, '  jxjweb.top  ', '内蒙古自治区 乌兰察布市 察哈尔', '', '测试', 'test1634');
INSERT INTO `2code_code` VALUES (25, 0, 6, 'http://www.nwpu.edu.cn/', '陕西省 西安市 长安区 ', '', '西北工业大学', '公诚勇毅');
INSERT INTO `2code_code` VALUES (26, 0, 0, 'www.baidu.com', '天津 天津市 武清区 ', '', '以前的故事', '忘了');

-- ----------------------------
-- Table structure for 2code_user
-- ----------------------------
DROP TABLE IF EXISTS `2code_user`;
CREATE TABLE `2code_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` date NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_username_unique`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 2code_user
-- ----------------------------
INSERT INTO `2code_user` VALUES (1, 'npc208', '$2a$2a$10$wBzIQfU5DD6iSNqiZP4Wo.JkIjbAlnuYWtueXd/PKSdQdl6IppAK6', '1', '2020-04-10');
INSERT INTO `2code_user` VALUES (5, 'npc2048', '$2a$10$FFaV1LeToYMyslDhrvdUwewiL9oAY8w1PELbxT/ltz12oVC7o/bFK', '', '2020-04-10');

SET FOREIGN_KEY_CHECKS = 1;
