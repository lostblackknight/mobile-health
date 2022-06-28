/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:3306
 Source Schema         : bee_health_admin

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 18/06/2022 13:48:16
*/
CREATE DATABASE IF NOT EXISTS bee_health_admin  DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;
USE bee_health_admin;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父id',
  `dict_label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典的标签',
  `dict_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典的值',
  `dict_sort` bigint(255) NULL DEFAULT NULL COMMENT '字典的排序',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 415 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dict
-- ----------------------------
INSERT INTO `t_dict` VALUES (1, 0, '区域列表', 'area_list', 1, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (2, 1, '广东', '2', 1, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (3, 2, '广州', '2918', 1, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (4, 2, '深圳', '5', 2, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (5, 2, '东莞', '2920', 3, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (6, 2, '惠州', '2927', 4, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (7, 2, '佛山', '2922', 5, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (8, 2, '珠海', '2916', 6, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (9, 2, '汕头', '2919', 7, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (10, 2, '清远', '2936', 8, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (11, 2, '梅州', '2935', 9, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (12, 2, '茂名', '2933', 10, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (13, 2, '中山', '2934', 11, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (14, 2, '肇庆', '2932', 12, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (15, 2, '揭阳', '2931', 13, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (16, 2, '汕尾', '2921', 14, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (17, 2, '韶关', '2923', 15, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (18, 2, '河源', '2924', 16, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (19, 2, '潮州', '2925', 17, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (20, 2, '阳江', '2926', 18, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (21, 2, '云浮', '2928', 19, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (22, 2, '湛江', '2929', 20, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (23, 2, '江门', '2930', 21, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (24, 1, '湖南', '3260', 2, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (25, 24, '长沙', '3274', 1, '2022-04-02 13:14:06', 'admin');
INSERT INTO `t_dict` VALUES (26, 24, '湘潭', '3268', 2, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (27, 24, '株洲', '3266', 3, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (28, 24, '益阳', '3270', 4, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (29, 24, '怀化', '3265', 5, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (30, 24, '郴州', '3273', 6, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (31, 24, '衡阳', '3271', 7, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (32, 24, '常德', '3263', 8, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (33, 24, '娄底', '3261', 9, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (34, 24, '岳阳', '3262', 10, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (35, 24, '张家界', '3264', 11, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (36, 24, '永州', '3267', 12, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (37, 24, '湘西州', '3269', 13, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (38, 24, '邵阳', '3272', 14, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (39, 1, '北京', '2913', 3, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (40, 39, '北京', '2912', 1, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (41, 1, '上海', '3305', 4, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (42, 41, '上海', '3306', 1, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (43, 1, '香港', '3313', 5, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (44, 43, '香港', '3314', 1, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (45, 1, '河南', '3241', 6, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (46, 45, '郑州', '3242', 1, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (47, 45, '洛阳', '3250', 2, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (48, 45, '开封', '3252', 3, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (49, 45, '平顶山', '3253', 4, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (50, 45, '安阳', '3254', 5, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (51, 45, '商丘', '3255', 6, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (52, 45, '焦作', '3246', 7, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (53, 45, '濮阳', '3247', 8, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (54, 45, '漯河', '3248', 9, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (55, 45, '新乡', '3251', 10, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (56, 45, '周口', '3256', 11, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (57, 45, '南阳', '3257', 12, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (58, 45, '三门峡', '3259', 13, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (59, 45, '信阳', '3258', 14, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (60, 45, '济源', '3249', 15, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (61, 45, '许昌', '3245', 16, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (62, 45, '鹤壁', '3244', 17, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (63, 45, '驻马店', '3243', 18, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (64, 1, '湖北', '3275', 7, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (65, 64, '天门', '3288', 1, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (66, 64, '咸宁', '3289', 2, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (67, 64, '十堰', '3290', 3, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (68, 64, '仙桃', '3291', 4, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (69, 64, '恩施', '3292', 5, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (70, 64, '宜昌', '3286', 6, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (71, 64, '孝感', '3287', 7, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (72, 64, '潜江', '3285', 8, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (73, 64, '神农架', '3284', 9, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (74, 64, '荆州', '3283', 10, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (75, 64, '荆门', '3282', 11, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (76, 64, '襄阳', '3281', 12, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (77, 64, '鄂州', '3280', 13, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (78, 64, '随州', '3279', 14, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (79, 64, '黄冈', '3278', 15, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (80, 64, '黄石', '3277', 16, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (81, 64, '武汉', '3276', 17, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (82, 1, '海南', '3157', 8, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (83, 82, '海口', '3158', 1, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (84, 82, '琼海', '3166', 2, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (85, 82, '保亭', '3165', 3, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (86, 82, '万宁', '3164', 4, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (87, 82, '昌江', '3163', 5, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (88, 82, '东方', '3162', 6, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (89, 82, '琼中', '3161', 7, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (90, 82, '三亚', '3160', 8, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (91, 82, '白沙', '3159', 9, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (92, 82, '文昌', '3168', 10, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (93, 82, '乐东', '3167', 11, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (94, 82, '陵水', '3169', 12, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (95, 82, '儋州', '3170', 13, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (96, 82, '临高', '3178', 14, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (97, 82, '中沙群岛', '3177', 15, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (98, 82, '定安', '3176', 16, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (99, 82, '西沙群岛', '3175', 17, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (100, 82, '屯昌', '3174', 18, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (101, 82, '南沙群岛', '3173', 19, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (102, 82, '澄迈', '3172', 20, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (103, 82, '五指山', '3171', 21, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (104, 82, '三沙', '14974', 22, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (105, 1, '重庆', '3315', 9, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (106, 105, '重庆', '3316', 1, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (107, 105, '县级', '14973', 2, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (108, 1, '四川', '3092', 10, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (109, 108, '成都', '3103', 1, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (110, 108, '德阳', '3102', 2, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (111, 108, '凉山州', '3096', 3, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (112, 108, '绵阳', '3107', 4, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (113, 108, '达州', '3110', 5, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (114, 108, '攀枝花', '3093', 6, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (115, 108, '内江', '3095', 7, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (116, 108, '乐山', '3094', 8, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (117, 108, '南充', '3097', 9, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (118, 108, '遂宁', '3111', 10, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (119, 108, '雅安', '3113', 11, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (120, 108, '阿坝州', '3112', 12, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (121, 108, '资阳', '3109', 13, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (122, 108, '宜宾', '3098', 14, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (123, 108, '自贡', '3108', 15, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (124, 108, '眉山', '3106', 16, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (125, 108, '甘孜州', '3105', 17, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (126, 108, '泸州', '3104', 18, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (127, 108, '广安', '3101', 19, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (128, 108, '广元', '3100', 20, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (129, 108, '巴中', '3099', 21, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (130, 1, '黑龙江', '3124', 11, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (131, 130, '绥化市', '3135', 1, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (132, 130, '大兴安岭', '3137', 2, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (133, 130, '齐齐哈尔', '3136', 3, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (134, 130, '七台河', '3134', 4, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (135, 130, '鸡西', '3133', 5, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (136, 130, '牡丹江', '3132', 6, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (137, 130, '黑河', '3131', 7, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (138, 130, '佳木斯', '3130', 8, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (139, 130, '鹤岗', '3129', 9, '2022-04-02 13:14:07', 'admin');
INSERT INTO `t_dict` VALUES (140, 130, '双鸭山', '3128', 10, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (141, 130, '大庆', '3127', 11, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (142, 130, '伊春', '3126', 12, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (143, 130, '哈尔滨', '3125', 13, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (144, 1, '河北', '3201', 12, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (145, 144, '雄安新区', '21532', 1, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (146, 144, '保定', '3212', 2, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (147, 144, '唐山', '3211', 3, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (148, 144, '廊坊', '3210', 4, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (149, 144, '张家口', '3209', 5, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (150, 144, '承德', '3208', 6, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (151, 144, '沧州', '3207', 7, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (152, 144, '秦皇岛', '3206', 8, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (153, 144, '衡水', '3205', 9, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (154, 144, '邯郸', '3204', 10, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (155, 144, '邢台', '3203', 11, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (156, 144, '石家庄', '3202', 12, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (157, 1, '广西', '3213', 13, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (158, 157, '宜州', '14251', 1, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (159, 157, '贵港', '3224', 2, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (160, 157, '防城港', '3227', 3, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (161, 157, '钦州', '3226', 4, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (162, 157, '贺州', '3225', 5, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (163, 157, '百色', '3223', 6, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (164, 157, '玉林', '3222', 7, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (165, 157, '河池', '3221', 8, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (166, 157, '梧州', '3220', 9, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (167, 157, '桂林', '3219', 10, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (168, 157, '柳州', '3218', 11, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (169, 157, '来宾', '3217', 12, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (170, 157, '崇左', '3216', 13, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (171, 157, '南宁', '3215', 14, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (172, 157, '北海', '3214', 15, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (173, 1, '天津', '3307', 14, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (174, 173, '天津', '3308', 1, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (175, 1, '山西', '2937', 15, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (176, 175, '临汾', '2938', 1, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (177, 175, '大同', '2940', 2, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (178, 175, '吕梁', '2939', 3, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (179, 175, '太原', '2941', 4, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (180, 175, '忻州', '2942', 5, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (181, 175, '晋中', '2943', 6, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (182, 175, '长治', '2944', 7, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (183, 175, '晋城', '2945', 8, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (184, 175, '朔州', '2946', 9, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (185, 175, '阳泉', '2947', 10, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (186, 175, '运城', '2948', 11, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (187, 1, '内蒙古', '3228', 16, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (188, 187, '鄂尔多斯', '3232', 1, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (189, 187, '阿拉善', '3231', 2, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (190, 187, '锡林郭勒', '3230', 3, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (191, 187, '呼和浩特', '3229', 4, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (192, 187, '呼伦贝尔', '3234', 5, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (193, 187, '巴彦淖尔', '3233', 6, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (194, 187, '乌兰察布', '3235', 7, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (195, 187, '乌海', '3236', 8, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (196, 187, '通辽', '3240', 9, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (197, 187, '赤峰', '3239', 10, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (198, 187, '兴安', '3238', 11, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (199, 187, '包头', '3237', 12, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (200, 1, '山东', '2949', 17, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (201, 200, '淄博', '2959', 1, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (202, 200, '泰安', '2956', 2, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (203, 200, '济南', '2958', 3, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (204, 200, '滨州', '2960', 4, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (205, 200, '济宁', '2957', 5, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (206, 200, '潍坊', '2961', 6, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (207, 200, '威海', '2953', 7, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (208, 200, '德州', '2952', 8, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (209, 200, '枣庄', '2955', 9, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (210, 200, '东营', '2950', 10, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (211, 200, '临沂', '2951', 11, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (212, 200, '日照', '2954', 12, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (213, 200, '聊城', '2963', 13, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (214, 200, '烟台', '2962', 14, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (215, 200, '菏泽', '2965', 15, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (216, 200, '青岛', '2966', 16, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (217, 1, '云南', '2967', 18, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (218, 217, '大理州', '2971', 1, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (219, 217, '曲靖', '2978', 2, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (220, 217, '迪庆', '2983', 3, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (221, 217, '西双版纳', '2982', 4, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (222, 217, '红河州', '2981', 5, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (223, 217, '玉溪', '2980', 6, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (224, 217, '楚雄州', '2979', 7, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (225, 217, '昭通', '2977', 8, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (226, 217, '昆明', '2976', 9, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (227, 217, '文山州', '2975', 10, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (228, 217, '思茅', '2974', 11, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (229, 217, '怒江', '2973', 12, '2022-04-02 13:14:08', 'admin');
INSERT INTO `t_dict` VALUES (230, 217, '德宏州', '2972', 13, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (231, 217, '保山', '2970', 14, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (232, 217, '丽江', '2969', 15, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (233, 217, '临沧', '2968', 16, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (234, 217, '普洱', '14962', 17, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (235, 1, '福建', '2993', 19, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (236, 235, '福州', '2994', 1, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (237, 235, '南平', '2996', 2, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (238, 235, '莆田', '2998', 3, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (239, 235, '泉州', '2999', 4, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (240, 235, '厦门', '3001', 5, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (241, 235, '武夷山', '3003', 6, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (242, 235, '漳州', '3002', 7, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (243, 235, '三明', '3000', 8, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (244, 235, '宁德', '2997', 9, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (245, 235, '龙岩', '2995', 10, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (246, 1, '浙江', '3022', 20, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (247, 246, '台州', '3033', 1, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (248, 246, '温州', '3032', 2, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (249, 246, '绍兴', '3031', 3, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (250, 246, '衢州', '3030', 4, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (251, 246, '丽水', '3029', 5, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (252, 246, '舟山', '3028', 6, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (253, 246, '金华', '3027', 7, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (254, 246, '嘉兴', '3026', 8, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (255, 246, '湖州', '3025', 9, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (256, 246, '杭州', '3024', 10, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (257, 246, '宁波', '3023', 11, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (258, 1, '安徽', '3004', 21, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (259, 258, '黄山', '3021', 1, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (260, 258, '马鞍山', '3020', 2, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (261, 258, '阜阳', '3019', 3, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (262, 258, '宣城', '3009', 4, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (263, 258, '铜陵', '3018', 5, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (264, 258, '蚌埠', '3017', 6, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (265, 258, '芜湖', '3016', 7, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (266, 258, '滁州', '3015', 8, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (267, 258, '淮南', '3014', 9, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (268, 258, '淮北', '3013', 10, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (269, 258, '池州', '3012', 11, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (270, 258, '巢湖', '3011', 12, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (271, 258, '宿州', '3010', 13, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (272, 258, '安庆', '3008', 14, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (273, 258, '合肥', '3007', 15, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (274, 258, '六安', '3006', 16, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (275, 258, '亳州', '3005', 17, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (276, 1, '西藏', '3049', 22, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (277, 276, '阿里', '3056', 1, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (278, 276, '那曲', '3055', 2, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (279, 276, '林芝', '3054', 3, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (280, 276, '昌都', '3053', 4, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (281, 276, '日喀则', '3052', 5, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (282, 276, '拉萨', '3051', 6, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (283, 276, '山南', '3050', 7, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (284, 1, '辽宁', '3057', 23, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (285, 284, '锦州', '3061', 1, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (286, 284, '阜新', '3070', 2, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (287, 284, '铁岭', '3069', 3, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (288, 284, '抚顺', '3068', 4, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (289, 284, '沈阳', '3067', 5, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (290, 284, '丹东', '3066', 6, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (291, 284, '盘锦', '3065', 7, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (292, 284, '大连', '3064', 8, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (293, 284, '辽阳', '3063', 9, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (294, 284, '朝阳', '3062', 10, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (295, 284, '本溪', '3060', 11, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (296, 284, '葫芦岛', '3059', 12, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (297, 284, '鞍山', '3058', 13, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (298, 284, '营口', '3071', 14, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (299, 1, '江苏', '3072', 24, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (300, 299, '南京', '3073', 1, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (301, 299, '连云港', '3084', 2, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (302, 299, '镇江', '3085', 3, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (303, 299, '苏州', '3083', 4, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (304, 299, '盐城', '3082', 5, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (305, 299, '淮安', '3081', 6, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (306, 299, '泰州', '3080', 7, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (307, 299, '无锡', '3079', 8, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (308, 299, '扬州', '3078', 9, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (309, 299, '徐州', '3077', 10, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (310, 299, '常州', '3076', 11, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (311, 299, '宿迁', '3075', 12, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (312, 299, '南通', '3074', 13, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (313, 1, '宁夏', '3086', 25, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (314, 313, '中卫', '3091', 1, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (315, 313, '石嘴山', '3090', 2, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (316, 313, '吴忠', '3089', 3, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (317, 313, '固原', '3088', 4, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (318, 313, '银川', '3087', 5, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (319, 1, '贵州', '3114', 26, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (320, 319, '黔西南州', '3123', 1, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (321, 319, '黔南州', '3122', 2, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (322, 319, '黔东南州', '3121', 3, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (323, 319, '铜仁', '3120', 4, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (324, 319, '遵义', '3119', 5, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (325, 319, '毕节', '3118', 6, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (326, 319, '安顺', '3117', 7, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (327, 319, '六盘水', '3116', 8, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (328, 319, '贵阳', '3115', 9, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (329, 1, '新疆', '3138', 27, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (330, 329, '克拉玛依市', '3143', 1, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (331, 329, '克孜勒苏柯尔克孜自治州', '3142', 2, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (332, 329, '伊犁哈萨克自治州', '3141', 3, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (333, 329, '五家渠', '3140', 4, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (334, 329, '乌鲁木齐市', '3139', 5, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (335, 329, '吐鲁番市', '3145', 6, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (336, 329, '博尔塔拉蒙古自治州', '3144', 7, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (337, 329, '和田地区', '3146', 8, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (338, 329, '阿拉尔', '3156', 9, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (339, 329, '哈密市', '3147', 10, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (340, 329, '阿勒泰地区', '3155', 11, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (341, 329, '阿克苏地区', '3154', 12, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (342, 329, '石河子', '3153', 13, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (343, 329, '昌吉回族自治州', '3152', 14, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (344, 329, '巴音郭楞蒙古自治州', '3151', 15, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (345, 329, '塔城地区', '3150', 16, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (346, 329, '图木舒克', '3149', 17, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (347, 329, '喀什地区', '3148', 18, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (348, 329, '自治区直辖县级行政区划', '14956', 19, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (349, 1, '吉林', '3179', 28, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (350, 349, '白山市', '3181', 1, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (351, 349, '通化市', '3187', 2, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (352, 349, '辽源市', '3186', 3, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (353, 349, '四平市', '3185', 4, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (354, 349, '长春市', '3184', 5, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (355, 349, '吉林市', '3183', 6, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (356, 349, '白城市', '3182', 7, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (357, 349, '延边朝鲜族自治州', '3189', 8, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (358, 349, '松原市', '3188', 9, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (359, 1, '陕西', '3190', 29, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (360, 359, '咸阳', '3191', 1, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (361, 359, '铜川', '3200', 2, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (362, 359, '西安', '3199', 3, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (363, 359, '商洛', '3192', 4, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (364, 359, '渭南', '3198', 5, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (365, 359, '汉中', '3197', 6, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (366, 359, '榆林', '3196', 7, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (367, 359, '延安', '3195', 8, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (368, 359, '宝鸡', '3194', 9, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (369, 359, '安康', '3193', 10, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (370, 1, '江西', '3293', 30, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (371, 370, '萍乡', '3303', 1, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (372, 370, '鹰潭', '3294', 2, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (373, 370, '上饶', '3295', 3, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (374, 370, '九江', '3296', 4, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (375, 370, '南昌', '3297', 5, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (376, 370, '吉安', '3298', 6, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (377, 370, '宜春', '3299', 7, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (378, 370, '抚州', '3300', 8, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (379, 370, '新余', '3301', 9, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (380, 370, '景德镇', '3302', 10, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (381, 370, '赣州', '3304', 11, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (382, 1, '青海', '2984', 31, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (383, 382, '玉树', '2987', 1, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (384, 382, '海东', '2986', 2, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (385, 382, '西宁', '2985', 3, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (386, 382, '海南州', '2989', 4, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (387, 382, '黄南', '2988', 5, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (388, 382, '海北州', '2990', 6, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (389, 382, '果洛', '2991', 7, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (390, 382, '海西', '2992', 8, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (391, 1, '甘肃', '3034', 32, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (392, 391, '兰州', '3035', 1, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (393, 391, '嘉峪关', '3044', 2, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (394, 391, '金昌', '3043', 3, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (395, 391, '酒泉', '3042', 4, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (396, 391, '陇南', '3041', 5, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (397, 391, '平凉', '3040', 6, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (398, 391, '庆阳', '3039', 7, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (399, 391, '天水', '3038', 8, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (400, 391, '武威', '3037', 9, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (401, 391, '张掖', '3036', 10, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (402, 391, '白银', '3046', 11, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (403, 391, '定西', '3045', 12, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (404, 391, '甘南', '3047', 13, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (405, 391, '临夏', '3048', 14, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (406, 1, '澳门', '3311', 33, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (407, 406, '澳门', '3312', 1, '2022-04-02 13:14:09', 'admin');
INSERT INTO `t_dict` VALUES (408, 0, '医院等级', 'hospital_level', 2, '2022-04-06 14:18:03', 'admin');
INSERT INTO `t_dict` VALUES (409, 408, '特等医院', 'te_deng', 1, '2022-04-06 21:49:22', 'admin');
INSERT INTO `t_dict` VALUES (410, 408, '三甲医院', 'san_jia', 2, '2022-04-06 21:49:50', 'admin');
INSERT INTO `t_dict` VALUES (411, 408, '三级医院', 'san_ji', 3, '2022-04-06 21:50:14', 'admin');
INSERT INTO `t_dict` VALUES (412, 408, '二级医院', 'er_ji', 4, '2022-04-06 21:50:39', 'admin');
INSERT INTO `t_dict` VALUES (413, 408, '一级医院', 'yi_ji', 5, '2022-04-06 21:51:07', 'admin');
INSERT INTO `t_dict` VALUES (414, 408, '其他', 'qi_ta', 6, '2022-04-06 21:51:30', 'admin');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色权限字符串',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '超级管理员', 'admin', '2022-03-24 13:04:12', '2022-03-24 13:04:16');
INSERT INTO `t_role` VALUES (2, '医院', 'hospital', '2022-03-26 17:02:17', '2022-03-26 17:02:21');
INSERT INTO `t_role` VALUES (3, '医生', 'doctor', '2022-03-26 17:02:40', '2022-03-26 17:02:42');
INSERT INTO `t_role` VALUES (4, '病患', 'patient', '2022-04-06 21:34:08', '2022-04-06 21:34:08');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `gender` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` int(11) NULL DEFAULT 0 COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', '$2a$10$mJDRalLTvAl992vC3QSrCOvKv6BHAOwaUJHTVOv7rzEG5GF6OLh3y', '男', 'https://bee-health.oss-cn-beijing.aliyuncs.com/bee-health/80eb6d3448c2417baae16669d4961f8f.jpg', '17735746553', 'chensixiang1234@gmail.com', 0, '2022-03-24 13:39:19', '2022-04-06 13:34:43');
INSERT INTO `t_user` VALUES (2, 'aimer', '$2a$10$kL4vQc4fu3XuvF9r.lwCv.P0bYqIkBoeGZgZ.w7wWsrOmVhmBzVrm', '女', 'https://bee-health.oss-cn-beijing.aliyuncs.com/bee-health/9c45761ecf38491882ceb84ee3b1c27f.png', '17735746556', 'aimer@gmail.com', 0, '2022-03-25 14:06:34', '2022-04-06 14:08:21');
INSERT INTO `t_user` VALUES (3, 'maria', '$2a$10$lUdB7UigdDx/pEr6HC752O8BVlC1tklyUvqTL7vYNEz3EgFlIeRrK', '女', 'https://bee-health.oss-cn-beijing.aliyuncs.com/bee-health/0f477fa2acb2408b95f7eb1a2345b36c.jpeg', '18835551055', 'maria@gmail.com', 0, '2022-04-05 20:05:48', '2022-04-06 13:27:56');
INSERT INTO `t_user` VALUES (4, 'lisa', '$2a$10$lUhvsusDibHKYgnjOOnKluXB622hP5H50jpPQJcSkCcIi7c5/YYZe', '女', 'https://bee-health.oss-cn-beijing.aliyuncs.com/bee-health/1d53f5bc567f488399ce47f48d42cef8.jpeg', '18835552313', 'lisa@gmail.com', 0, '2022-04-06 13:38:21', '2022-04-06 14:08:31');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关联关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (12, 3, 1);
INSERT INTO `t_user_role` VALUES (15, 1, 1);
INSERT INTO `t_user_role` VALUES (26, 2, 1);
INSERT INTO `t_user_role` VALUES (27, 4, 1);

SET FOREIGN_KEY_CHECKS = 1;
