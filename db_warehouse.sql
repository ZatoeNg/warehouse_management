/*
 Navicat Premium Dump SQL

 Source Server         : JspStudy
 Source Server Type    : MySQL
 Source Server Version : 50547 (5.5.47)
 Source Host           : localhost:3306
 Source Schema         : db_warehouse

 Target Server Type    : MySQL
 Target Server Version : 50547 (5.5.47)
 File Encoding         : 65001

 Date: 14/03/2025 12:06:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auth_info
-- ----------------------------
DROP TABLE IF EXISTS `auth_info`;
CREATE TABLE `auth_info`  (
  `auth_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父id为空或为0，表示一级权限',
  `auth_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `auth_desc` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `auth_grade` int(11) NULL DEFAULT NULL,
  `auth_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '1 模块 、2  列表、 3  按钮',
  `auth_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `auth_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `auth_order` int(11) NULL DEFAULT NULL,
  `auth_state` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '1 启用 、0 禁用',
  `create_by` int(11) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` int(11) NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`auth_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of auth_info
-- ----------------------------
INSERT INTO `auth_info` VALUES (1, 0, '用户管理', '用户管理', 1, '1', NULL, NULL, 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (2, 1, '用户列表', '用户列表', 2, '2', '/user/index', NULL, 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (3, 2, '添加用户', '添加用户', 3, '3', NULL, 'user-addUser', 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (4, 0, '角色管理', '角色管理', 1, '1', NULL, NULL, 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (5, 4, '角色列表', '角色列表', 2, '2', '/role/index', NULL, 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (6, 5, '添加角色', '添加角色', 3, '3', NULL, 'role-addRole', 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (7, 0, '权限管理', '权限管理', 1, '1', NULL, NULL, 0, '0', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (8, 7, '权限树', '权限树', 2, '2', '/auth/index', NULL, 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (9, 8, '添加权限', '添加权限', 3, '3', NULL, 'auth-addAuth', 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (10, 2, '删除用户', '删除用户', 3, '3', '', 'user-deleteUser', 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (19, 0, '订单管理', '订单管理', 1, '1', '', '', 0, '0', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (20, 19, '订单列表', '订单列表', 2, '2', '', '', 0, '0', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (21, 20, '修改订单', '修改订单', 3, '3', '', 'updateOrder', 0, '0', 0, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (22, 20, '删除订单', '删除订单', 3, '3', '', 'auth-delete', 0, '0', 0, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (23, 2, '查询用户', '查询用户', 3, '3', '', 'user-findUser', 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (24, 2, '修改用户', '修改用户信息', 3, '3', '', 'user-updateUser', 0, '1', 0, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (25, 2, '分配角色', '分配角色', 3, '3', '', 'user-assignRole', 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (26, 2, '更改权限', '更改权限', 3, '3', '', 'user-updateAuth', 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (27, 5, '修改角色', '修改角色', 3, '3', '', 'role-updateRole', 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (28, 5, '查询角色', '查询角色', 3, '3', '', 'role-findRole', 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (30, 5, '删除角色', '删除角色', 3, '3', '', 'role-deleteRole', 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (31, 5, '更改权限', '更改权限', 3, '3', '', 'role-updateAuth', 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (32, 8, '修改权限', '修改权限', 3, '3', '', 'auth-updateAuth', 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (33, 8, '删除权限', '删除权限', 3, '3', NULL, 'auth-deleteAuth', 0, '1', 0, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (34, 8, '恢复权限', '恢复权限', 3, '3', NULL, 'auth-restartAuth', 0, '1', 0, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (35, 2, '修改状态', '修改状态', 3, '3', '', 'user-updateState', 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (36, 2, '重置密码', '重置密码', 3, '3', '', 'user-resetPwd', 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (37, 5, '修改角色状态', '修改角色状态', 3, '3', '', 'role-updateState', 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (38, 19, '订单详情', '订单详情', 2, '2', '', '', 1, '0', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (41, 19, '订单分类', '订单分类', 2, '2', '', '', 1, '0', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (42, 0, '商品管理', '商品管理', 1, '1', '', '', 1, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (43, 38, '查看详情', '查看详情', 3, '3', '', 'order-selectDetils', 1, '0', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (44, 38, '修改详情', '修改详情', 3, '3', '', 'order-updateDetils', 1, '0', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (46, 41, '分类', '分类', 3, '3', '', 'auth-flAuth', 1, '0', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (50, 42, '商品列表', '商品列表', 2, '2', '/commodity/index', '', 1, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (53, 0, '采购管理', '采购管理', 1, '1', '', '', 1, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (54, 0, '入库管理', '入库管理', 1, '1', '', '', 1, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (55, 0, '出库管理', '出库管理', 1, '1', '', '', 1, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (56, 53, '采购列表', '采购列表', 2, '2', '/purchase/index', '', 1, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (57, 54, '入库列表', '入库列表', 2, '2', '/instore/index', '', 1, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (58, 55, '出库列表', '出库列表', 2, '2', '/outstore/index', '', 1, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (61, 0, '盘点管理', '盘点管理', 1, '1', '', '', 1, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (62, 61, '盘点列表', '盘点列表', 2, '2', '/inventory/index', '', 1, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (63, 0, '统计查询', '统计查询', 1, '1', '', '', 1, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (64, 63, '统计报表', '统计报表', 2, '2', '/statistics/index', '', 1, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (68, 0, '转载管理', '转载管理', 1, '1', '', '', 1, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (69, 0, '仓库管理', '仓库管理', 1, '1', '', '', 1, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (70, 69, '仓库列表', '仓库列表', 2, '2', '/store/index', '', 1, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (71, 68, '转载单列表', '转载单列表', 2, '2', '/transshipment/index', NULL, NULL, '1', NULL, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (72, 0, '供货商管理', '供货商管理', 1, '1', '', '', 1, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (74, 72, '供货商列表', '供货商列表', 2, '2', '/supplier/index', '', 1, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (76, 42, '商品分类', '商品分类', 2, '2', '/commodity/category', '', 1, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (92, 50, '转载', '转载', 3, '3', '/transshipment/transfer', 'transfer_commodity', 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (93, 50, '商品查询', '商品查询', 3, '3', '/product/product-page-list', 'select_product', 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (94, 50, '添加商品', '添加商品', 3, '3', '/product/product-add', 'product-add', 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (95, 57, '确定入库', '确定入库', 3, '3', '/instore/instore-confirm', 'instore-confirm', 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (96, 71, '确认调货', '确认调货', 3, '3', '/transshipment/transshipment-confirm', 'transshipment-confirm', 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (97, 50, '采购', '采购', 3, '3', '/purchase/purchase-add', 'purchase-add', 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (98, 0, '销售管理', '销售列表', 1, '1', NULL, NULL, 0, '1', 1, '2025-02-10 22:40:10', NULL, NULL);
INSERT INTO `auth_info` VALUES (99, 0, '货物查询', '普户商品管理', 1, '1', NULL, NULL, 1, '1', 1, '2025-02-27 19:07:49', NULL, NULL);
INSERT INTO `auth_info` VALUES (100, 99, '货物列表', '用户商品列表', 2, '2', '/commonUser/product-list', 'common_user_product_list', 1, '1', 1, '2025-02-27 19:00:53', NULL, NULL);

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand`  (
  `brand_id` int(11) NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `brand_leter` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `brand_desc` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`brand_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '品牌' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES (4, '华为', 'H', '中华有为');
INSERT INTO `brand` VALUES (5, '小米', 'M', '小米之家');

-- ----------------------------
-- Table structure for buy_list
-- ----------------------------
DROP TABLE IF EXISTS `buy_list`;
CREATE TABLE `buy_list`  (
  `buy_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NULL DEFAULT NULL,
  `store_id` int(11) NULL DEFAULT NULL,
  `buy_num` int(11) NULL DEFAULT NULL,
  `fact_buy_num` int(11) NULL DEFAULT NULL,
  `buy_time` datetime NULL DEFAULT NULL,
  `supply_id` int(11) NULL DEFAULT NULL,
  `place_id` int(11) NULL DEFAULT NULL,
  `buy_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_in` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0 否 1 是',
  PRIMARY KEY (`buy_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '采购单' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of buy_list
-- ----------------------------
INSERT INTO `buy_list` VALUES (1, 18, 6, 1000, 1000, '2025-02-27 18:22:43', 6, 6, '小李子', '11234567890', '1');
INSERT INTO `buy_list` VALUES (2, 18, 6, 1000, 900, '2025-02-28 02:24:31', 6, 6, '小李子', '11234567890', '1');
INSERT INTO `buy_list` VALUES (3, 18, 6, 1000, 1000, '2025-02-28 19:55:07', 6, 6, '小李子', '11234567890', '1');

-- ----------------------------
-- Table structure for in_store
-- ----------------------------
DROP TABLE IF EXISTS `in_store`;
CREATE TABLE `in_store`  (
  `ins_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) NULL DEFAULT NULL,
  `product_id` int(11) NULL DEFAULT NULL,
  `in_num` int(11) NULL DEFAULT NULL,
  `create_by` int(11) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `is_in` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0 否 1 是',
  PRIMARY KEY (`ins_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '入库单' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of in_store
-- ----------------------------
INSERT INTO `in_store` VALUES (26, 4, 14, 1000, 1, '2025-02-28 21:37:27', '1');
INSERT INTO `in_store` VALUES (27, 6, 18, 1000, 1, '2025-02-28 21:37:53', '1');
INSERT INTO `in_store` VALUES (28, 4, 14, 1000, 1, '2025-02-28 21:49:33', '1');
INSERT INTO `in_store` VALUES (29, 6, 18, 900, 1, '2025-03-14 10:41:55', '1');

-- ----------------------------
-- Table structure for inventory
-- ----------------------------
DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory`  (
  `inventory_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) NULL DEFAULT NULL,
  `product_id` int(11) NULL DEFAULT NULL,
  `source_num` int(11) NULL DEFAULT NULL,
  `inventory_num` int(11) NULL DEFAULT NULL,
  `inventory_by` int(11) NULL DEFAULT NULL,
  `inventory_state` char(1) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `create_by` int(11) NULL DEFAULT NULL,
  `inventory_time` datetime NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`inventory_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 20 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of inventory
-- ----------------------------
INSERT INTO `inventory` VALUES (18, 6, 18, 1000, 1000, 1, '3', 1, '2025-02-28 21:12:56', '2025-02-28 21:12:52');
INSERT INTO `inventory` VALUES (17, 6, 18, 500, 1000, 1, '2', 1, '2025-02-28 21:12:45', '2025-02-28 21:12:40');
INSERT INTO `inventory` VALUES (16, 6, 18, 1000, 500, 1, '1', 1, '2025-02-28 21:10:44', '2025-02-28 21:09:29');

-- ----------------------------
-- Table structure for out_store
-- ----------------------------
DROP TABLE IF EXISTS `out_store`;
CREATE TABLE `out_store`  (
  `outs_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NULL DEFAULT NULL,
  `store_id` int(11) NULL DEFAULT NULL,
  `tally_id` int(11) NULL DEFAULT NULL,
  `out_price` decimal(8, 2) NULL DEFAULT NULL,
  `out_num` int(11) NULL DEFAULT NULL,
  `create_by` int(11) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `is_out` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0 否 1 是',
  PRIMARY KEY (`outs_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '出库单' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of out_store
-- ----------------------------

-- ----------------------------
-- Table structure for place
-- ----------------------------
DROP TABLE IF EXISTS `place`;
CREATE TABLE `place`  (
  `place_id` int(11) NOT NULL AUTO_INCREMENT,
  `place_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `place_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introduce` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '0:可用  1:不可用',
  PRIMARY KEY (`place_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产地' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of place
-- ----------------------------
INSERT INTO `place` VALUES (1, '湖南', 'hunan', '湖南', '0');
INSERT INTO `place` VALUES (2, '湖北', 'hubei', '湖北', '0');
INSERT INTO `place` VALUES (3, '陕西', 'shanxi', '陕西', '0');
INSERT INTO `place` VALUES (4, '浙江', 'zhejiang', '浙江', '0');
INSERT INTO `place` VALUES (5, '山东', 'shandong', '山东', '0');
INSERT INTO `place` VALUES (6, '广东', 'guangdong', '广东', '0');
INSERT INTO `place` VALUES (7, '广西', 'guangxi', '广西', '0');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) NULL DEFAULT NULL,
  `brand_id` int(11) NULL DEFAULT NULL,
  `product_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_invent` int(11) NULL DEFAULT NULL,
  `type_id` int(11) NULL DEFAULT NULL,
  `supply_id` int(11) NULL DEFAULT NULL,
  `place_id` int(11) NULL DEFAULT NULL,
  `unit_id` int(11) NULL DEFAULT NULL,
  `introduce` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `up_down_state` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0 下架 1 上架',
  `in_price` decimal(10, 2) NULL DEFAULT NULL,
  `sale_price` decimal(10, 2) NULL DEFAULT NULL,
  `mem_price` decimal(10, 2) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `create_by` int(11) NULL DEFAULT NULL,
  `update_by` int(11) NULL DEFAULT NULL,
  `imgs` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_date` datetime NULL DEFAULT NULL,
  `supp_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (14, 4, 4, '华为Mate70', 'HWMate70_12512', 0, 4, 6, 6, 6, '曜石黑/云域白', '1', 5699.00, 5999.00, 5999.00, '2025-02-25 20:19:33', '2025-02-25 22:06:13', 2, 1, '/img/upload/ths.jpg', '2025-02-19 00:00:00', '2027-02-28 00:00:00');
INSERT INTO `product` VALUES (18, 6, 4, '华为Mate70', 'HWMate70_12512', 900, 4, 6, 6, 6, '曜石黑/云域白', '1', 5699.00, 5999.00, 5999.00, '2025-02-25 22:06:21', '2025-02-28 21:12:56', 1, 1, '/img/upload/ths.jpg', '2025-02-19 00:00:00', '2027-02-28 00:00:00');

-- ----------------------------
-- Table structure for product_type
-- ----------------------------
DROP TABLE IF EXISTS `product_type`;
CREATE TABLE `product_type`  (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NULL DEFAULT NULL,
  `type_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type_desc` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品分类表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of product_type
-- ----------------------------
INSERT INTO `product_type` VALUES (3, 0, 'HUAWEI', '华为', '华为产品');
INSERT INTO `product_type` VALUES (4, 3, 'HuaWeiMate', 'HuaWeiMate', '华为旗舰系列');
INSERT INTO `product_type` VALUES (5, 0, 'XIAOMI', '小米', '小米产品');
INSERT INTO `product_type` VALUES (6, 5, 'XiaoMiDigitalSeries', 'XiaoMiDigitalSeries', '小米数字系列');
INSERT INTO `product_type` VALUES (7, 0, 'MEIZU', '魅族', '魅族产品');
INSERT INTO `product_type` VALUES (8, 7, 'MeiZuDigitalSeries', 'MeiZuDigitalSeries', '魅族数字系列');
INSERT INTO `product_type` VALUES (9, 0, '苹果', '苹果', '苹果产品');
INSERT INTO `product_type` VALUES (10, 9, 'AppleDigitalSeries', 'AppleDigitalSeries', '苹果数字系列');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_desc` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_state` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '1 启用 0 禁用',
  `create_by` int(11) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` int(11) NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', '超级管理员', 'supper_manage', '1', 1, '2017-11-14 16:49:12', 1, '2017-11-14 16:49:20');
INSERT INTO `role` VALUES (2, '管理员', '管理员', 'manage', '1', 1, '2018-11-14 16:49:12', 1, NULL);
INSERT INTO `role` VALUES (3, '普通用户', '普通用户', 'comm_manage', '1', 1, '2019-11-14 16:49:12', 1, '2022-03-25 15:09:41');
INSERT INTO `role` VALUES (4, '采购', '采购管理员', 'purchase_man', '1', 1, '2022-04-22 12:29:00', 1, '2022-04-22 13:56:29');
INSERT INTO `role` VALUES (5, '入库', '入库管理员', 'in_store', '1', 1, '2022-04-22 13:56:17', NULL, NULL);
INSERT INTO `role` VALUES (6, '商品', '商品管理员', 'commodity_manager', '1', 1, '2022-04-22 13:57:42', NULL, NULL);
INSERT INTO `role` VALUES (7, '出库', '出库管理员', 'out_store', '1', 1, '2022-04-22 14:00:00', NULL, NULL);
INSERT INTO `role` VALUES (8, '调货', '调货管理员', 'transshipment_manager', '1', 1, '2022-04-22 14:00:38', NULL, NULL);
INSERT INTO `role` VALUES (9, '仓库', '仓库管理员', 'store_manager', '1', 1, '2022-04-24 15:12:56', NULL, NULL);

-- ----------------------------
-- Table structure for role_auth
-- ----------------------------
DROP TABLE IF EXISTS `role_auth`;
CREATE TABLE `role_auth`  (
  `role_auth_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `auth_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`role_auth_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1479 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_auth
-- ----------------------------
INSERT INTO `role_auth` VALUES (1408, 3, 99);
INSERT INTO `role_auth` VALUES (1409, 3, 100);
INSERT INTO `role_auth` VALUES (1428, 9, 69);
INSERT INTO `role_auth` VALUES (1429, 9, 70);
INSERT INTO `role_auth` VALUES (1430, 8, 68);
INSERT INTO `role_auth` VALUES (1431, 8, 71);
INSERT INTO `role_auth` VALUES (1432, 7, 55);
INSERT INTO `role_auth` VALUES (1433, 7, 58);
INSERT INTO `role_auth` VALUES (1434, 6, 42);
INSERT INTO `role_auth` VALUES (1435, 6, 50);
INSERT INTO `role_auth` VALUES (1436, 6, 76);
INSERT INTO `role_auth` VALUES (1437, 5, 54);
INSERT INTO `role_auth` VALUES (1438, 5, 57);
INSERT INTO `role_auth` VALUES (1439, 4, 53);
INSERT INTO `role_auth` VALUES (1440, 4, 56);
INSERT INTO `role_auth` VALUES (1441, 1, 1);
INSERT INTO `role_auth` VALUES (1442, 1, 2);
INSERT INTO `role_auth` VALUES (1443, 1, 4);
INSERT INTO `role_auth` VALUES (1444, 1, 5);
INSERT INTO `role_auth` VALUES (1445, 1, 42);
INSERT INTO `role_auth` VALUES (1446, 1, 50);
INSERT INTO `role_auth` VALUES (1447, 1, 76);
INSERT INTO `role_auth` VALUES (1448, 1, 53);
INSERT INTO `role_auth` VALUES (1449, 1, 56);
INSERT INTO `role_auth` VALUES (1450, 1, 54);
INSERT INTO `role_auth` VALUES (1451, 1, 57);
INSERT INTO `role_auth` VALUES (1452, 1, 55);
INSERT INTO `role_auth` VALUES (1453, 1, 58);
INSERT INTO `role_auth` VALUES (1454, 1, 61);
INSERT INTO `role_auth` VALUES (1455, 1, 62);
INSERT INTO `role_auth` VALUES (1456, 1, 68);
INSERT INTO `role_auth` VALUES (1457, 1, 71);
INSERT INTO `role_auth` VALUES (1458, 1, 69);
INSERT INTO `role_auth` VALUES (1459, 1, 70);
INSERT INTO `role_auth` VALUES (1460, 1, 72);
INSERT INTO `role_auth` VALUES (1461, 1, 74);
INSERT INTO `role_auth` VALUES (1462, 2, 42);
INSERT INTO `role_auth` VALUES (1463, 2, 50);
INSERT INTO `role_auth` VALUES (1464, 2, 76);
INSERT INTO `role_auth` VALUES (1465, 2, 53);
INSERT INTO `role_auth` VALUES (1466, 2, 56);
INSERT INTO `role_auth` VALUES (1467, 2, 54);
INSERT INTO `role_auth` VALUES (1468, 2, 57);
INSERT INTO `role_auth` VALUES (1469, 2, 55);
INSERT INTO `role_auth` VALUES (1470, 2, 58);
INSERT INTO `role_auth` VALUES (1471, 2, 61);
INSERT INTO `role_auth` VALUES (1472, 2, 62);
INSERT INTO `role_auth` VALUES (1473, 2, 68);
INSERT INTO `role_auth` VALUES (1474, 2, 71);
INSERT INTO `role_auth` VALUES (1475, 2, 69);
INSERT INTO `role_auth` VALUES (1476, 2, 70);
INSERT INTO `role_auth` VALUES (1477, 2, 72);
INSERT INTO `role_auth` VALUES (1478, 2, 74);

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `store_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `store_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `store_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `concat` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`store_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '仓库表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES (1, '西安新城仓库', 'XA000', '陕西省新城区', '张三', '13829086629');
INSERT INTO `store` VALUES (2, '北京朝阳仓库', 'BJ000', '北京市朝阳区 ', '李四', '15229267291');
INSERT INTO `store` VALUES (3, '上海浦东仓库', 'SH000', '上海市浦东区', '王五', '18092647320');
INSERT INTO `store` VALUES (4, '广东湛江仓库', 'GD000', '广东省湛江市', '赵六', '12345678900');
INSERT INTO `store` VALUES (6, '广东深圳仓库', 'GD001', '广东省深圳市', '吴九', '11234567891');

-- ----------------------------
-- Table structure for supply
-- ----------------------------
DROP TABLE IF EXISTS `supply`;
CREATE TABLE `supply`  (
  `supply_id` int(11) NOT NULL AUTO_INCREMENT,
  `supply_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `supply_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `supply_introduce` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `concat` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '0:可用  1:不可用',
  PRIMARY KEY (`supply_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '供货商' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of supply
-- ----------------------------
INSERT INTO `supply` VALUES (6, 'HuaWei', '华为技术有限公司', '深圳市龙岗区坂田华为总部办公楼', '小李子', '1234567890', '深圳市龙岗区坂田华为总部办公楼', '0');

-- ----------------------------
-- Table structure for trans_list
-- ----------------------------
DROP TABLE IF EXISTS `trans_list`;
CREATE TABLE `trans_list`  (
  `transshipment_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NULL DEFAULT NULL,
  `source_store_id` int(11) NULL DEFAULT NULL,
  `target_store_id` int(11) NULL DEFAULT NULL,
  `source_product_name` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `target_product_name` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `trans_num` int(11) NULL DEFAULT NULL,
  `trans_state` char(1) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `trans_by` int(11) NULL DEFAULT NULL,
  `trans_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`transshipment_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 20 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of trans_list
-- ----------------------------
INSERT INTO `trans_list` VALUES (17, 18, 6, 4, '华为Mate70', '华为Mate70', 1000, '1', 1, '2025-02-28 21:37:25');
INSERT INTO `trans_list` VALUES (16, 18, 6, 4, '华为Mate70', '华为Mate70', 1000, '1', 1, '2025-02-28 21:36:12');
INSERT INTO `trans_list` VALUES (15, 18, 6, 4, '华为Mate70', '华为Mate70', 500, '1', 1, '2025-02-28 03:06:30');
INSERT INTO `trans_list` VALUES (13, 18, 6, 4, '华为Mate70', '华为Mate70', 500, '1', 1, '2025-02-25 22:10:19');
INSERT INTO `trans_list` VALUES (14, 18, 6, 4, '华为Mate70', '华为Mate70', 500, '1', 1, '2025-02-28 03:05:50');
INSERT INTO `trans_list` VALUES (12, 14, 4, 6, '华为Mate70', '华为Mate70', 1000, '1', 1, '2025-02-25 22:06:21');
INSERT INTO `trans_list` VALUES (18, 14, 4, 6, '华为Mate70', '华为Mate70', 1000, '1', 1, '2025-02-28 21:37:51');
INSERT INTO `trans_list` VALUES (19, 18, 6, 4, '华为Mate70', '华为Mate70', 1000, '1', 1, '2025-02-28 21:49:32');

-- ----------------------------
-- Table structure for unit
-- ----------------------------
DROP TABLE IF EXISTS `unit`;
CREATE TABLE `unit`  (
  `unit_id` int(11) NOT NULL AUTO_INCREMENT,
  `unit_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unit_desc` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`unit_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '规格单位表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of unit
-- ----------------------------
INSERT INTO `unit` VALUES (1, '箱/件', '箱/件');
INSERT INTO `unit` VALUES (6, '台', '台');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_pwd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '1 超级管理员 、 2  管理员 、 3 普通用户',
  `user_state` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0 未审核 、1 已审核',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0 正常、 1 已删除',
  `create_by` int(11) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` int(11) NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 'admin', '超级管理员', 'c431d451c81e75ffac75a640590ed0a1', NULL, '1', '0', 1, '2017-11-14 15:14:31', 1, '2022-04-24 15:33:12');
INSERT INTO `user_info` VALUES (2, 'root', '普通管理员', 'c431d451c81e75ffac75a640590ed0a1', NULL, '1', '0', 1, '2017-11-16 19:31:49', 1, '2025-02-28 02:22:18');
INSERT INTO `user_info` VALUES (4, 'anonymous', '匿名用户', 'c431d451c81e75ffac75a640590ed0a1', NULL, '1', '0', 1, '2025-02-19 15:51:00', 4, '2025-02-27 23:39:58');
INSERT INTO `user_info` VALUES (5, 'zhangsan', '张三', 'c431d451c81e75ffac75a640590ed0a1', NULL, '1', '0', 1, '2025-02-27 19:05:50', NULL, NULL);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`user_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (20, 1, 1);
INSERT INTO `user_role` VALUES (23, 3, 4);
INSERT INTO `user_role` VALUES (26, 2, 2);
INSERT INTO `user_role` VALUES (35, 4, 5);
INSERT INTO `user_role` VALUES (36, 5, 5);
INSERT INTO `user_role` VALUES (37, 6, 5);
INSERT INTO `user_role` VALUES (38, 7, 5);
INSERT INTO `user_role` VALUES (39, 8, 5);
INSERT INTO `user_role` VALUES (40, 9, 5);

SET FOREIGN_KEY_CHECKS = 1;
