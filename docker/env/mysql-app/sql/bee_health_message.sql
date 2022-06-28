/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:3306
 Source Schema         : bee_health_message

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 18/06/2022 13:48:47
*/
CREATE DATABASE IF NOT EXISTS bee_health_message  DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;
USE bee_health_message;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_chat
-- ----------------------------
DROP TABLE IF EXISTS `t_chat`;
CREATE TABLE `t_chat`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `member_id` bigint(20) NULL DEFAULT NULL COMMENT '会员ID',
  `another_id` bigint(20) NULL DEFAULT NULL COMMENT '另一个会员ID',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(255) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_chat
-- ----------------------------
INSERT INTO `t_chat` VALUES (1, 1, 2, '2022-04-21 18:26:29', 0);
INSERT INTO `t_chat` VALUES (2, 1, 3, '2022-04-24 13:58:00', 0);

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `from_id` bigint(20) NULL DEFAULT NULL COMMENT '发送者ID',
  `to_id` bigint(20) NULL DEFAULT NULL COMMENT '接受者ID',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '消息的内容',
  `type` int(255) NULL DEFAULT NULL COMMENT '消息的类型',
  `send_time` datetime(0) NULL DEFAULT NULL COMMENT '消息发送的时间',
  `status` int(255) NULL DEFAULT NULL COMMENT '消息的状态(1已读 0未读)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_message
-- ----------------------------
INSERT INTO `t_message` VALUES (1, 1, 2, '你好，我是陈思祥', 0, '2022-04-22 14:37:14', 1);
INSERT INTO `t_message` VALUES (2, 2, 1, '你好，我是王波', 0, '2022-04-22 14:37:41', 1);
INSERT INTO `t_message` VALUES (3, 2, 1, '请问您有什么问题', 0, '2022-04-22 14:37:48', 1);
INSERT INTO `t_message` VALUES (4, 1, 2, '我最近有点头疼，失眠', 0, '2022-04-22 14:38:15', 1);
INSERT INTO `t_message` VALUES (5, 2, 1, '注意合理安排作息时间，可以适当运动，提高睡眠质量，还要注意饮食，不要吃太多油腻的食物', 0, '2022-04-22 14:39:30', 1);
INSERT INTO `t_message` VALUES (6, 1, 2, '谢谢医生', 0, '2022-04-22 15:33:07', 1);
INSERT INTO `t_message` VALUES (7, 1, 3, '你好，张丽医生', 0, '2022-04-24 13:58:14', 1);
INSERT INTO `t_message` VALUES (8, 3, 1, '你好，陈思祥', 0, '2022-06-02 17:45:37', 1);
INSERT INTO `t_message` VALUES (9, 3, 1, '有什么问题吗', 0, '2022-06-02 17:46:06', 1);
INSERT INTO `t_message` VALUES (10, 1, 3, '最近头疼，有点失眠', 0, '2022-06-02 17:48:27', 1);
INSERT INTO `t_message` VALUES (11, 3, 1, '注意多休息', 0, '2022-06-15 10:29:30', 1);

SET FOREIGN_KEY_CHECKS = 1;
