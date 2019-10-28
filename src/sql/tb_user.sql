/*
 Navicat Premium Data Transfer

 Source Server         : localhost-mysql-lottery
 Source Server Type    : MySQL
 Source Server Version : 100138
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 100138
 File Encoding         : 65001

 Date: 28/10/2019 19:15:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户密码',
  `lastlogindate` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间(强制让之前的token失效)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (14, 'admin', '$2a$10$R6Tk3UD6xqO2LJoFIWMsDOAUnCteiLFKMNZfJ9jQ6Bf.njDFjGbfy', NULL);
INSERT INTO `tb_user` VALUES (15, '18883173476', '$2a$10$TAWcqoZYZnxWDnqap1uRUuGBbw6qya37hTIVnJUZUcmwVQVOLSilW', '2019-10-28 19:03:44');

SET FOREIGN_KEY_CHECKS = 1;
