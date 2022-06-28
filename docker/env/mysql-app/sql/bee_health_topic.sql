/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:3306
 Source Schema         : bee_health_topic

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 18/06/2022 13:49:06
*/
CREATE DATABASE IF NOT EXISTS bee_health_topic  DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;
USE bee_health_topic;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者名称',
  `uid` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封面',
  `category_id` bigint(255) NULL DEFAULT NULL COMMENT '分类ID',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '文章内容',
  `read_count` bigint(255) NULL DEFAULT 0 COMMENT '阅读数量',
  `like_count` bigint(255) NULL DEFAULT 0 COMMENT '点赞数量',
  `collection_count` bigint(255) NULL DEFAULT 0 COMMENT '收藏数量',
  `is_deleted` tinyint(255) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类编码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  `is_deleted` tinyint(255) NOT NULL DEFAULT 0 COMMENT '逻辑删除(1:已删除，0:未删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_category
-- ----------------------------
INSERT INTO `t_category` VALUES (1, '1000', '食品健康', '食品健康', '2022-04-23 13:25:50', '2022-04-23 13:25:53', 0);
INSERT INTO `t_category` VALUES (2, '1001', '运动养生', '运动养生', '2022-04-23 13:26:27', '2022-04-23 13:26:29', 0);
INSERT INTO `t_category` VALUES (3, '1002', '体重控制', '体重控制', '2022-04-23 13:26:46', '2022-04-23 13:26:50', 0);
INSERT INTO `t_category` VALUES (4, '1003', '慢性疾病', '慢性疾病', '2022-04-23 13:27:40', '2022-04-23 13:27:43', 0);

SET FOREIGN_KEY_CHECKS = 1;
