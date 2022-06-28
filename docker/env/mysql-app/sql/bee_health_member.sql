/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:3306
 Source Schema         : bee_health_member

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 18/06/2022 13:48:39
*/
CREATE DATABASE IF NOT EXISTS bee_health_member  DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;
USE bee_health_member;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_doctor_auth
-- ----------------------------
DROP TABLE IF EXISTS `t_doctor_auth`;
CREATE TABLE `t_doctor_auth`  (
  `member_id` bigint(20) NOT NULL COMMENT '会员ID',
  `certificates` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件照',
  `status` bigint(255) NULL DEFAULT NULL COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(255) NULL DEFAULT 0 COMMENT '逻辑删除'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_doctor_auth
-- ----------------------------
INSERT INTO `t_doctor_auth` VALUES (1, 'https://bee-health.oss-cn-beijing.aliyuncs.com/bee-health/208282d0048f49258b95ee5bb55a7bd0.png', -1, '2022-06-15 12:09:14', 1);
INSERT INTO `t_doctor_auth` VALUES (2, 'https://bee-health.oss-cn-beijing.aliyuncs.com/bee-health/db35720886e7424aafac8ade7a7cd934.png', 1, '2022-04-20 00:42:09', 0);
INSERT INTO `t_doctor_auth` VALUES (3, 'https://bee-health.oss-cn-beijing.aliyuncs.com/bee-health/db35720886e7424aafac8ade7a7cd934.png', 1, '2022-04-21 00:42:41', 0);
INSERT INTO `t_doctor_auth` VALUES (1, 'https://bee-health.oss-cn-beijing.aliyuncs.com/bee-health/808ed80ffad64bf5aa54627c22720ac4.png', -1, '2022-06-15 12:14:34', 1);
INSERT INTO `t_doctor_auth` VALUES (1, 'https://bee-health.oss-cn-beijing.aliyuncs.com/bee-health/1885936864654984b5bc4af72e37bd7c.png', -1, '2022-06-16 00:33:21', 1);

-- ----------------------------
-- Table structure for t_member
-- ----------------------------
DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '会员ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `status` int(255) NULL DEFAULT 0 COMMENT '认证状态（0：未认证 1：认证中 2：认证成功 -1：认证失败）',
  `certificates_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(255) NOT NULL DEFAULT 0 COMMENT '逻辑删除(1:已删除，0:未删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会员信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_member
-- ----------------------------
INSERT INTO `t_member` VALUES (1, '17735746553', '$2a$10$U6cauKB.tms7CzIDAcioYe54wkG0oKbQfKY7TsFYxHPaKbev4V3Cq', '陈思祥', '陈思祥', '17735746553', 'https://bee-health.oss-cn-beijing.aliyuncs.com/bee-health/80eb6d3448c2417baae16669d4961f8f.jpg', 0, '140423200004300056', '2022-04-12 21:01:25', '2022-06-16 00:34:01', 0);
INSERT INTO `t_member` VALUES (2, '18835551055', '$2a$10$Hl46xZetIT8kgnm/LFcsXOpJJzpKND22JMqfnbeiy1RHZ9dTXnyEy', '王波', '王波', '18835551055', 'https://images.91160.com/thumbnail/doctor/100x100/upload/doctor/3/doctor_2912_16329083746212.png', 1, '140423200004300057', '2022-04-13 01:06:47', '2022-04-13 01:06:47', 0);
INSERT INTO `t_member` VALUES (3, '18135241405', '$2a$10$Hl46xZetIT8kgnm/LFcsXOpJJzpKND22JMqfnbeiy1RHZ9dTXnyEy', '张丽', '张丽', '18135241405', 'https://images.91160.com/thumbnail/doctor/100x100/upload/doctor/3/doctor_2941_16353930654806.jpeg', 1, '140423200004300058', '2022-04-13 17:35:03', '2022-04-30 17:07:57', 0);
INSERT INTO `t_member` VALUES (4, '17735745921', '$2a$10$bN1jC0oqaRiY6qw02Jw2BOQkcasWg4OS1dG5ToF0HWiFY/SgeCkfW', '17735745921', NULL, '17735745921', 'https://s2.loli.net/2022/04/13/dKn7mrQwuUYqVNz.png', 0, NULL, '2022-06-06 11:16:29', '2022-06-06 11:25:50', 0);

-- ----------------------------
-- Table structure for t_member_role
-- ----------------------------
DROP TABLE IF EXISTS `t_member_role`;
CREATE TABLE `t_member_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `member_id` bigint(20) NULL DEFAULT NULL COMMENT '会员ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会员类型关联关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_member_role
-- ----------------------------
INSERT INTO `t_member_role` VALUES (1, 1, 4);
INSERT INTO `t_member_role` VALUES (2, 2, 4);
INSERT INTO `t_member_role` VALUES (3, 3, 4);
INSERT INTO `t_member_role` VALUES (4, 2, 3);
INSERT INTO `t_member_role` VALUES (5, 3, 3);
INSERT INTO `t_member_role` VALUES (6, 4, 4);

-- ----------------------------
-- Table structure for t_patient
-- ----------------------------
DROP TABLE IF EXISTS `t_patient`;
CREATE TABLE `t_patient`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `member_id` bigint(20) NULL DEFAULT NULL COMMENT '会员id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `certificates_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `birth_date` date NULL DEFAULT NULL COMMENT '出生年月',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `contacts_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人姓名',
  `contacts_certificates_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人证件号',
  `contacts_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人手机',
  `status` tinyint(3) NOT NULL DEFAULT 0 COMMENT '状态（0：默认 1：已认证）',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_deleted` tinyint(255) NOT NULL DEFAULT 0 COMMENT '逻辑删除(1:已删除，0:未删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `certificates_no`(`certificates_no`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE,
  INDEX `idx_user_id`(`member_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '就诊人表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_patient
-- ----------------------------
INSERT INTO `t_patient` VALUES (1, 1, '陈思祥', '140423200004300056', '男', '2000-04-30', '17735746553', '', '', '', 1, '2022-04-17 11:49:16', '2022-04-17 14:43:59', 0);
INSERT INTO `t_patient` VALUES (2, 1, '王鑫', '140423200004300066', '男', '2010-04-01', '18835551055', '', '', '', 0, '2022-04-17 14:34:05', '2022-04-17 14:45:43', 0);
INSERT INTO `t_patient` VALUES (3, 1, '吴群巍', '140423200004300088', '男', '2022-04-17', '18135241405', '', '', '', 0, '2022-04-17 14:36:54', '2022-04-17 14:43:51', 0);
INSERT INTO `t_patient` VALUES (5, 1, '赵柳', '140423200004300052', '女', '2022-04-17', '18835551067', '', '', '', 0, '2022-04-17 14:50:05', '2022-04-28 15:06:33', 0);
INSERT INTO `t_patient` VALUES (6, 1, '李斯', '140423200004300086', '男', '2022-04-17', '18835551038', '', '', '', 0, '2022-04-17 14:55:42', '2022-04-28 15:06:45', 0);
INSERT INTO `t_patient` VALUES (7, 4, '123', '123', '女', '2022-06-06', '123', '123', '123', '123', 0, '2022-06-06 11:19:21', '2022-06-06 11:19:21', 0);

SET FOREIGN_KEY_CHECKS = 1;
