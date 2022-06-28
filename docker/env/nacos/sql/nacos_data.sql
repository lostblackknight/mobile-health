/*
 Navicat Premium Data Transfer

 Source Server         : nacos_data
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 192.168.86.130:3306
 Source Schema         : nacos_data

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 21/06/2022 20:45:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
                                `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
                                `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
                                `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
                                `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
                                `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
                                `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
                                `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
                                PRIMARY KEY (`id`) USING BTREE,
                                UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (1, 'bee-health-gateway-dev.yaml', 'DEFAULT_GROUP', 'server:\n  port: 7001\n\nspring:\n  application:\n    name: bee-health-gateway\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: all', '3ab5d2c5b6fff4ee8d5def2ad966156b', '2022-04-22 16:36:37', '2022-06-19 07:57:25', '', '192.168.86.1', '小蜜蜂医疗', '', '小蜜蜂医疗', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (2, 'bee-health-admin-dev.yaml', 'DEFAULT_GROUP', 'server:\r\n  port: 7002\r\n\r\nspring:\r\n  application:\r\n    name: bee-health-admin\r\n\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: all', '68df69d8c0e6581d826d5de0ecef630b', '2022-06-19 07:22:06', '2022-06-19 07:22:06', NULL, '192.168.86.1', '小蜜蜂医疗', '', '小蜜蜂医疗', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (3, 'bee-health-hospital-dev.yaml', 'DEFAULT_GROUP', 'server:\n  port: 7003\n\nspring:\n  application:\n    name: bee-health-hospital\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: all\n', 'c95b915b8bde7b866622884b4a7bd87c', '2022-04-22 16:36:37', '2022-06-19 08:02:02', '', '192.168.86.1', '小蜜蜂医疗', '', '小蜜蜂医疗', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (4, 'bee-health-member-dev.yaml', 'DEFAULT_GROUP', 'server:\r\n  port: 7004\r\n\r\nspring:\r\n  application:\r\n    name: bee-health-member\r\n\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: all\r\n', '9ab29ca33306bbb73b80c4bbbf1f5ae8', '2022-04-22 16:36:37', '2022-04-22 16:36:37', NULL, '192.168.86.1', '小蜜蜂医疗', '', '小蜜蜂医疗', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (5, 'bee-health-message-dev.yaml', 'DEFAULT_GROUP', 'server:\n  port: 7005\n\nspring:\n  application:\n    name: bee-health-message\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: all\n', 'ccb102aaef43bf24966f01862dab4ea1', '2022-04-22 16:36:37', '2022-04-22 16:36:37', NULL, '192.168.86.1', '小蜜蜂医疗', '', '小蜜蜂医疗', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (6, 'bee-health-order-dev.yaml', 'DEFAULT_GROUP', 'server:\n  port: 7006\n\nspring:\n  application:\n    name: bee-health-order\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: all\n\nalipay:\n  app-id: 2021000118600732\n  gateway-url: https://openapi.alipaydev.com/gateway.do\n  alipay-public-key: MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoJTEu7nE6Rq+FJmJxPavzk6T0JwZuyeFyIZ+Ob9c7ot4PGOhf01if+OtIDXLfdSdHu0qjvuLNPp6hw4KGbqp06YJiBJseUkLIgxQPCtYy4FxVrwjUYWiKMst7hX3QhF2P+97LiB4XmI30ZZesU75Jqgy16v0MCMxtKjGA19eaMfN3YemJ+uuIDr9Exfkv+beGIBB9ds5bipK1FAJcWj7BAw9ADcYl1ODpHgEC7wfzuE+p2KXrJlo6IP278PlpV/MsAYZX4KJlRhR01a/fQk4liJSAiojIol7DSoE96CKoqkuy7z8MG2gn8NO7BIRaLn7t+m4womyxBwGy3TGgl0KKwIDAQAB\n  app-private-key: MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCnDQ++6JDJ7JAgUTOu0Tq9sHmgkbLjIup4Li7PEj4iA3l3R83nm47LOtuiSeosihyC4oQRg3NswtYMpfExi0CjbJQjdh5wNYTZIpdFVI6WGhmVSJP/Ypk+GLqAKKsQIbGrV5ami6ETGSC/jhRHptPaZ+J/9VRsUPpKlDwx7IK7abAb+QOX/jZsOpEoC1Ger16nOIhMfkgHwoOb164mNbyQ73PBbMoEY8L0ISPRBTM/cZZIFaSWEgaVbxmcbTT+7e7gWFVmfu53b9SZT2X9ozonPUoPKSQTSjvbqyCJfw1bRgqiCuaG4TPXrMOwSxliDR+idjvxFdBOXkmWabfiZkZZAgMBAAECggEBAI/IFj1jPvwNuR+XxLw7KiwKsSUbQrVtYBKA7ZkdQo1UIEAidX9bzNPvnQtaQ0yCs3eUL8hDNB3wtPu9+JOEH+aJlhfPmvNJnUanFzx9Qxy+6jYrefbejrd74Updtp7z4VBw2yIOHF2ryxwU1i+1z7K0p6crKbbxG/0FCaqOvxc+w6mWlNuCEs5pmLAPVhk35yNEnnzZIrZvqgQqUL1rEejyH8nnKft9yF2P8nOcKiaMbmmR4lz622sqTqF+qxDQwmB6WeeUZPkOUNvwUuTS4AQjbE/Im49xclIDd9PxjnRs0FDpx6q9exEW65QIig3cm1eaAeADcjN/NtnA+U+OqGECgYEA3O7+RUkYfKHy/3ERzxHsu0YtSX2Dek2ASRC/zHCCAd6aR7woHUTCfR7GiB4xTxIxG+Bo5XFpZSIsiDXTjZvNTL/KIBU4hUptGOc9NmuFH9mm/9lGgBkw4vBU6menHLPTizhJco2jt8GxYJgTcDJRZ0Zciuz4tZz+S1294BgQg2sCgYEAwZC0UKbftNmnWdS1u7sPiNxTO+W1pBXi9RIipH9eoQJoTWeXlQcxI7neQclYIZiCR7F8QlNAyoW22dDYssDtWnzvZBu65ifgYM8lwrMFktizFEe52kq2xB4tLSF5Ys/qJM0ni6AMC26ZHpHvkGVmpcmvxwuRmmOZYjTJhy/G0ksCgYEAkam82V+FFn8MIGCjFjxCMYLmTuLC8brxTrsNSbQP8z2ZyJwEcq+n7yiASrRlXDWpTTDa2BO7ImOyqqeacG39oJF+qUJ0fmUAov+quxuOrN4qndraAZEaSzWbNuTaKDFj0xFsU2uROpEo6n+5qSpzwzx/vVTZ8ZutiXEnw/z5vjsCgYEAkrCZFe4A5stzue4rc4PFuBBQu7nPt/QmNVdzDXrBOf/bD54MEBKNOOfo+a4ergcn5DErXBA/r7s54UQWlREET2kS+qjRMQ7TSkpx8WU+OhUjUaExxa6NZ37++Y6+sRWj+hPHflbiCd/dBn/OL33JFqcMISoxQ/FrEjOy7lJUuJUCgYBPQPE7lmoJgjjAw1wZRjovYRrKTTnYNrjU9ljs9tLpF5FxaIRjdZJsyCyO+uVS5IweDOzAty8RNp+LsUDdCdmwvZNu1z5vgUFzHjIU4lVC2EktjB00eq0n5MxfGJtt1EgJZhKoZyUpGFvC95RiJE408634xrUT9ilFFQdQZpX+8A==\n  notify-url: http://39.106.85.230:7001/api/order/payment/notify\n  return-url: http://localhost:9527/payment\n', '522b14e55ccc4a4ff7bf91e7fe10ef3e', '2022-04-22 16:36:37', '2022-04-22 16:36:37', NULL, '192.168.86.1', '小蜜蜂医疗', '', '小蜜蜂医疗', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (7, 'bee-health-search-dev.yaml', 'DEFAULT_GROUP', 'server:\r\n  port: 7007\r\n\r\nspring:\r\n  application:\r\n    name: bee-health-search\r\n\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: all\r\n', '7ece1cbf40ca0126616edef987b3d7f9', '2022-04-22 16:36:37', '2022-04-22 16:36:37', NULL, '192.168.86.1', '小蜜蜂医疗', '', '小蜜蜂医疗', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (8, 'bee-health-third-party-dev.yaml', 'DEFAULT_GROUP', 'server:\n  port: 7008\n\nspring:\n  application:\n    name: bee-health-third-party\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: all\n\naliyun:\n  oss:\n    endpoint: oss-cn-beijing.aliyuncs.com\n    access-key-id: LTAI5tEVTUCxUwss7JmhF7JJ\n    access-key-secret: 9r6sH3xEu2jhZskCA5kugGPTtDaqGg\n    bucket-name: bee-health\n  sms:\n    access-key-id: LTAI5tEVTUCxUwss7JmhF7JJ\n    access-key-secret: 9r6sH3xEu2jhZskCA5kugGPTtDaqGg\n    endpoint: dysmsapi.aliyuncs.com\n', 'b4e63c22bca8c3210ea34b565b2e54f8', '2022-04-22 16:36:37', '2022-05-06 07:14:02', '', '192.168.86.1', '小蜜蜂医疗', '', '小蜜蜂医疗', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (9, 'bee-health-topic-dev.yaml', 'DEFAULT_GROUP', 'server:\r\n  port: 7009\r\n\r\nspring:\r\n  application:\r\n    name: bee-health-topic\r\n\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: all\r\n', '317232b382614f823ec18e04c22405d6', '2022-04-22 16:36:37', '2022-04-22 16:36:37', NULL, '192.168.86.1', '小蜜蜂医疗', '', '小蜜蜂医疗', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (10, 'bee-health-gateway-prod.yaml', 'DEFAULT_GROUP', 'server:\n  port: 7001\n\nspring:\n  application:\n    name: bee-health-gateway\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: all', 'f335e1fe65de76e673248ff496fbd3ec', '2022-06-21 12:38:44', '2022-06-21 12:38:44', NULL, '192.168.86.1', '小蜜蜂医疗', '', '小蜜蜂医疗', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (11, 'bee-health-admin-prod.yaml', 'DEFAULT_GROUP', 'server:\r\n  port: 7002\r\n\r\nspring:\r\n  application:\r\n    name: bee-health-admin\r\n\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: all', '03c7c0ace395d80182db07ae2c30f034', '2022-06-21 12:41:29', '2022-06-21 12:41:29', NULL, '192.168.86.1', '小蜜蜂医疗', '', '小蜜蜂医疗', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (12, 'bee-health-hospital-pord.yaml', 'DEFAULT_GROUP', 'server:\n  port: 7003\n\nspring:\n  application:\n    name: bee-health-hospital\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: all\n', '0cc175b9c0f1b6a831c399e269772661', '2022-06-21 12:39:39', '2022-06-21 12:39:39', NULL, '192.168.86.1', '小蜜蜂医疗', '', '小蜜蜂医疗', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (13, 'bee-health-member-prod.yaml', 'DEFAULT_GROUP', 'server:\r\n  port: 7004\r\n\r\nspring:\r\n  application:\r\n    name: bee-health-member\r\n\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: all\r\n', '7694f4a66316e53c8cdd9d9954bd611d', '2022-06-21 12:39:56', '2022-06-21 12:39:56', NULL, '192.168.86.1', '小蜜蜂医疗', '', '小蜜蜂医疗', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (14, 'bee-health-message-prod.yaml', 'DEFAULT_GROUP', 'server:\n  port: 7005\n\nspring:\n  application:\n    name: bee-health-message\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: all\n', '0cc175b9c0f1b6a831c399e269772661', '2022-06-21 12:40:10', '2022-06-21 12:40:10', NULL, '192.168.86.1', '小蜜蜂医疗', '', '小蜜蜂医疗', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (15, 'bee-health-order-prod.yaml', 'DEFAULT_GROUP', 'server:\n  port: 7006\n\nspring:\n  application:\n    name: bee-health-order\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: all\n\nalipay:\n  app-id: 2021000118600732\n  gateway-url: https://openapi.alipaydev.com/gateway.do\n  alipay-public-key: MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoJTEu7nE6Rq+FJmJxPavzk6T0JwZuyeFyIZ+Ob9c7ot4PGOhf01if+OtIDXLfdSdHu0qjvuLNPp6hw4KGbqp06YJiBJseUkLIgxQPCtYy4FxVrwjUYWiKMst7hX3QhF2P+97LiB4XmI30ZZesU75Jqgy16v0MCMxtKjGA19eaMfN3YemJ+uuIDr9Exfkv+beGIBB9ds5bipK1FAJcWj7BAw9ADcYl1ODpHgEC7wfzuE+p2KXrJlo6IP278PlpV/MsAYZX4KJlRhR01a/fQk4liJSAiojIol7DSoE96CKoqkuy7z8MG2gn8NO7BIRaLn7t+m4womyxBwGy3TGgl0KKwIDAQAB\n  app-private-key: MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCnDQ++6JDJ7JAgUTOu0Tq9sHmgkbLjIup4Li7PEj4iA3l3R83nm47LOtuiSeosihyC4oQRg3NswtYMpfExi0CjbJQjdh5wNYTZIpdFVI6WGhmVSJP/Ypk+GLqAKKsQIbGrV5ami6ETGSC/jhRHptPaZ+J/9VRsUPpKlDwx7IK7abAb+QOX/jZsOpEoC1Ger16nOIhMfkgHwoOb164mNbyQ73PBbMoEY8L0ISPRBTM/cZZIFaSWEgaVbxmcbTT+7e7gWFVmfu53b9SZT2X9ozonPUoPKSQTSjvbqyCJfw1bRgqiCuaG4TPXrMOwSxliDR+idjvxFdBOXkmWabfiZkZZAgMBAAECggEBAI/IFj1jPvwNuR+XxLw7KiwKsSUbQrVtYBKA7ZkdQo1UIEAidX9bzNPvnQtaQ0yCs3eUL8hDNB3wtPu9+JOEH+aJlhfPmvNJnUanFzx9Qxy+6jYrefbejrd74Updtp7z4VBw2yIOHF2ryxwU1i+1z7K0p6crKbbxG/0FCaqOvxc+w6mWlNuCEs5pmLAPVhk35yNEnnzZIrZvqgQqUL1rEejyH8nnKft9yF2P8nOcKiaMbmmR4lz622sqTqF+qxDQwmB6WeeUZPkOUNvwUuTS4AQjbE/Im49xclIDd9PxjnRs0FDpx6q9exEW65QIig3cm1eaAeADcjN/NtnA+U+OqGECgYEA3O7+RUkYfKHy/3ERzxHsu0YtSX2Dek2ASRC/zHCCAd6aR7woHUTCfR7GiB4xTxIxG+Bo5XFpZSIsiDXTjZvNTL/KIBU4hUptGOc9NmuFH9mm/9lGgBkw4vBU6menHLPTizhJco2jt8GxYJgTcDJRZ0Zciuz4tZz+S1294BgQg2sCgYEAwZC0UKbftNmnWdS1u7sPiNxTO+W1pBXi9RIipH9eoQJoTWeXlQcxI7neQclYIZiCR7F8QlNAyoW22dDYssDtWnzvZBu65ifgYM8lwrMFktizFEe52kq2xB4tLSF5Ys/qJM0ni6AMC26ZHpHvkGVmpcmvxwuRmmOZYjTJhy/G0ksCgYEAkam82V+FFn8MIGCjFjxCMYLmTuLC8brxTrsNSbQP8z2ZyJwEcq+n7yiASrRlXDWpTTDa2BO7ImOyqqeacG39oJF+qUJ0fmUAov+quxuOrN4qndraAZEaSzWbNuTaKDFj0xFsU2uROpEo6n+5qSpzwzx/vVTZ8ZutiXEnw/z5vjsCgYEAkrCZFe4A5stzue4rc4PFuBBQu7nPt/QmNVdzDXrBOf/bD54MEBKNOOfo+a4ergcn5DErXBA/r7s54UQWlREET2kS+qjRMQ7TSkpx8WU+OhUjUaExxa6NZ37++Y6+sRWj+hPHflbiCd/dBn/OL33JFqcMISoxQ/FrEjOy7lJUuJUCgYBPQPE7lmoJgjjAw1wZRjovYRrKTTnYNrjU9ljs9tLpF5FxaIRjdZJsyCyO+uVS5IweDOzAty8RNp+LsUDdCdmwvZNu1z5vgUFzHjIU4lVC2EktjB00eq0n5MxfGJtt1EgJZhKoZyUpGFvC95RiJE408634xrUT9ilFFQdQZpX+8A==\n  notify-url: http://39.106.85.230:7001/api/order/payment/notify\n  return-url: http://localhost:9527/payment\n', '7694f4a66316e53c8cdd9d9954bd611d', '2022-06-21 12:40:30', '2022-06-21 12:40:30', NULL, '192.168.86.1', '小蜜蜂医疗', '', '小蜜蜂医疗', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (16, 'bee-health-search-prod.yaml', 'DEFAULT_GROUP', 'server:\r\n  port: 7007\r\n\r\nspring:\r\n  application:\r\n    name: bee-health-search\r\n\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: all\r\n', 'f1290186a5d0b1ceab27f4e77c0c5d68', '2022-06-21 12:40:46', '2022-06-21 12:40:46', NULL, '192.168.86.1', '小蜜蜂医疗', '', '小蜜蜂医疗', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (17, 'bee-health-third-party-prod.yaml', 'DEFAULT_GROUP', 'server:\n  port: 7008\n\nspring:\n  application:\n    name: bee-health-third-party\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: all\n\naliyun:\n  oss:\n    endpoint: oss-cn-beijing.aliyuncs.com\n    access-key-id: LTAI5tEVTUCxUwss7JmhF7JJ\n    access-key-secret: 9r6sH3xEu2jhZskCA5kugGPTtDaqGg\n    bucket-name: bee-health\n  sms:\n    access-key-id: LTAI5tEVTUCxUwss7JmhF7JJ\n    access-key-secret: 9r6sH3xEu2jhZskCA5kugGPTtDaqGg\n    endpoint: dysmsapi.aliyuncs.com\n', '03c7c0ace395d80182db07ae2c30f034', '2022-06-21 12:40:59', '2022-06-21 12:40:59', NULL, '192.168.86.1', '小蜜蜂医疗', '', '小蜜蜂医疗', NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (18, 'bee-health-topic-prod.yaml', 'DEFAULT_GROUP', 'server:\r\n  port: 7009\r\n\r\nspring:\r\n  application:\r\n    name: bee-health-topic\r\n\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: all\r\n', '7694f4a66316e53c8cdd9d9954bd611d', '2022-06-21 12:41:16', '2022-06-21 12:41:16', NULL, '192.168.86.1', '小蜜蜂医疗', '', '小蜜蜂医疗', NULL, NULL, 'yaml', NULL);

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
                                     `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                     `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
                                     `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
                                     `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
                                     `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
                                     `gmt_modified` datetime(0) NOT NULL COMMENT '修改时间',
                                     `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                     `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
                                     PRIMARY KEY (`id`) USING BTREE,
                                     UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
                                     `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                     `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
                                     `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
                                     `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
                                     `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
                                     `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
                                     `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
                                     `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                     `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
                                     `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
                                     `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
                                     `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
                                     PRIMARY KEY (`id`) USING BTREE,
                                     UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                    `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
                                    `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
                                    `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
                                    `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
                                    `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
                                    `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
                                    `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
                                    `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                    `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
                                    `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
                                    `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
                                    PRIMARY KEY (`id`) USING BTREE,
                                    UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
                                         `id` bigint(20) NOT NULL COMMENT 'id',
                                         `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
                                         `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
                                         `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
                                         `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
                                         `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
                                         `nid` bigint(20) NOT NULL AUTO_INCREMENT,
                                         PRIMARY KEY (`nid`) USING BTREE,
                                         UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
                                         INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
                                   `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                   `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
                                   `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
                                   `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
                                   `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
                                   `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
                                   `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
                                   `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
                                   `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                   `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
                                   PRIMARY KEY (`id`) USING BTREE,
                                   UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
                                    `id` bigint(64) UNSIGNED NOT NULL,
                                    `nid` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                                    `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
                                    `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
                                    `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
                                    `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
                                    `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                    `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                                    `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                                    `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
                                    `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                    `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                    `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
                                    PRIMARY KEY (`nid`) USING BTREE,
                                    INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
                                    INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
                                    INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
                                `role` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                `resource` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                `action` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                UNIQUE INDEX `uk_role_permission`(`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permissions
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
                          `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                          `role` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                          UNIQUE INDEX `idx_user_role`(`username`, `role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
                                    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                    `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
                                    `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
                                    `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
                                    `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
                                    `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
                                    `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
                                    `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
                                    `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                    `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
                                    PRIMARY KEY (`id`) USING BTREE,
                                    UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
                                `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
                                `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
                                `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
                                `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
                                `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
                                `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
                                PRIMARY KEY (`id`) USING BTREE,
                                UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
                                INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
                          `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                          `password` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                          `enabled` tinyint(1) NOT NULL,
                          PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;
