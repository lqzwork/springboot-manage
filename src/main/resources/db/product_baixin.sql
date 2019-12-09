/*
 Navicat Premium Data Transfer

 Source Server         : localhost-liyj
 Source Server Type    : MySQL
 Source Server Version : 50709
 Source Host           : 192.168.253.61:3306
 Source Schema         : product_baixin

 Target Server Type    : MySQL
 Target Server Version : 50709
 File Encoding         : 65001

 Date: 03/12/2019 15:42:07
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for kuaidi100_delivery
-- ----------------------------
DROP TABLE IF EXISTS `kuaidi100_delivery`;
CREATE TABLE `kuaidi100_delivery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `delivery_name` varchar(255) NOT NULL,
  `delivery_code` varchar(255) NOT NULL,
  `delivery_sort` varchar(2) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for operate_log
-- ----------------------------
DROP TABLE IF EXISTS `operate_log`;
CREATE TABLE `operate_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_id` bigint(20) DEFAULT NULL COMMENT '药品ID',
  `modify_num` int(11) DEFAULT NULL COMMENT '买入/卖出数量',
  `operate` varchar(20) DEFAULT NULL COMMENT '操作：买入、卖出',
  `operate_user_id` varchar(32) DEFAULT NULL COMMENT '操作人ID',
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operate_log
-- ----------------------------
BEGIN;
INSERT INTO `operate_log` VALUES (1, 14, 10, '买入', '7', '2019-11-22 16:51:34');
INSERT INTO `operate_log` VALUES (2, 14, -10, '卖出', '7', '2019-11-22 16:52:42');
INSERT INTO `operate_log` VALUES (3, 4, -100, '卖出', '1', '2019-11-22 16:53:54');
INSERT INTO `operate_log` VALUES (4, 2, 10, '买入', '7', '2019-11-22 17:29:33');
INSERT INTO `operate_log` VALUES (5, 1, -25, '卖出', '7', '2019-11-22 17:29:43');
INSERT INTO `operate_log` VALUES (6, 2, -30, '卖出', '7', '2019-11-23 00:36:14');
INSERT INTO `operate_log` VALUES (7, 14, -99, '卖出', '1', '2019-11-23 14:06:53');
INSERT INTO `operate_log` VALUES (8, 1, -3, '卖出', '20', '2019-11-23 14:07:42');
INSERT INTO `operate_log` VALUES (9, 1, -23, '卖出', '1', '2019-11-23 14:14:41');
INSERT INTO `operate_log` VALUES (10, 4, -100, '卖出', '20', '2019-11-23 16:12:31');
INSERT INTO `operate_log` VALUES (11, 2, -10, '卖出', '20', '2019-11-23 16:13:38');
INSERT INTO `operate_log` VALUES (12, 2, 100, '买入', '20', '2019-11-23 16:13:45');
INSERT INTO `operate_log` VALUES (13, 15, 5, '买入', '22', '2019-11-24 13:31:44');
INSERT INTO `operate_log` VALUES (14, 15, -3, '卖出', '22', '2019-11-24 13:31:59');
INSERT INTO `operate_log` VALUES (15, 4, -5, '卖出', '0', '2019-11-24 13:33:58');
INSERT INTO `operate_log` VALUES (16, 15, 6, '买入', '22', '2019-11-24 13:39:37');
INSERT INTO `operate_log` VALUES (17, 15, 7, '买入', '22', '2019-11-24 13:39:43');
INSERT INTO `operate_log` VALUES (18, 2, -5, '卖出', '20', '2019-11-24 13:57:09');
INSERT INTO `operate_log` VALUES (19, 1, -7, '卖出', '22', '2019-11-24 13:57:32');
INSERT INTO `operate_log` VALUES (20, 16, -10, '卖出', '23', '2019-11-24 20:29:56');
INSERT INTO `operate_log` VALUES (21, 16, 20, '买入', '23', '2019-11-24 20:30:08');
INSERT INTO `operate_log` VALUES (22, 16, -10, '卖出', '23', '2019-11-24 20:31:05');
INSERT INTO `operate_log` VALUES (23, 16, -25, '卖出', '23', '2019-11-24 20:34:24');
INSERT INTO `operate_log` VALUES (24, 20, -3, '卖出', '20', '2019-11-25 18:07:13');
INSERT INTO `operate_log` VALUES (25, 20, -20, '卖出', '23', '2019-11-25 18:07:37');
INSERT INTO `operate_log` VALUES (26, 20, -99, '卖出', '22', '2019-11-25 18:08:30');
INSERT INTO `operate_log` VALUES (27, 20, 99, '买入', '7', '2019-11-25 18:34:27');
INSERT INTO `operate_log` VALUES (28, 1, -19, '卖出', '20', '2019-11-26 10:49:54');
INSERT INTO `operate_log` VALUES (29, 1, 58, '买入', '8', '2019-11-26 14:49:45');
COMMIT;

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类目ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父类目ID=0时，代表的是一级的类目',
  `name` varchar(50) DEFAULT NULL COMMENT '分类名称',
  `status` int(1) DEFAULT '1' COMMENT '状态。可选值:1(正常),2(删除)',
  `sort_order` int(4) DEFAULT NULL COMMENT '排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数',
  `is_parent` tinyint(1) DEFAULT '1' COMMENT '该类目是否为父类目，1为true，0为false',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `parent_id` (`parent_id`,`status`) USING BTREE,
  KEY `sort_order` (`sort_order`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=350 DEFAULT CHARSET=utf8 COMMENT='内容分类';

-- ----------------------------
-- Records of tb_category
-- ----------------------------
BEGIN;
INSERT INTO `tb_category` VALUES (217, NULL, '片剂', 1, NULL, NULL, '2019-11-24 13:29:27', '2019-11-24 13:29:27');
INSERT INTO `tb_category` VALUES (254, NULL, '气体', 1, NULL, NULL, '2019-11-22 13:21:44', '2019-11-22 13:21:44');
INSERT INTO `tb_category` VALUES (284, NULL, '固体', 1, NULL, NULL, '2019-11-22 13:21:37', '2019-11-22 13:21:37');
INSERT INTO `tb_category` VALUES (349, NULL, '液体', 1, NULL, NULL, '2019-11-22 13:21:28', '2019-11-22 13:21:28');
COMMIT;

-- ----------------------------
-- Table structure for tb_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_item`;
CREATE TABLE `tb_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品id，同时也是商品编号',
  `title` varchar(100) NOT NULL COMMENT '商品标题',
  `supplier` varchar(100) DEFAULT NULL COMMENT '供应商/产地',
  `sell_point` varchar(500) DEFAULT NULL COMMENT '商品卖点',
  `norm` varchar(100) DEFAULT NULL COMMENT '规格',
  `unit` varchar(100) DEFAULT NULL COMMENT '单位',
  `price` double(6,2) NOT NULL COMMENT '进价，单位元',
  `sell_price` double(6,2) DEFAULT NULL COMMENT '售价，单位元',
  `num` int(10) NOT NULL COMMENT '库存数量',
  `barcode` varchar(30) DEFAULT NULL COMMENT '商品条形码',
  `image` varchar(500) DEFAULT NULL COMMENT '商品图片',
  `cid` bigint(10) NOT NULL COMMENT '所属类目，叶子类目',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '商品状态，1-正常，2-下架，3-删除',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '创建人',
  `created` datetime NOT NULL COMMENT '创建时间',
  `updte_user_id` varchar(32) DEFAULT NULL COMMENT '更新人',
  `updated` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `cid` (`cid`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  KEY `updated` (`updated`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of tb_item
-- ----------------------------
BEGIN;
INSERT INTO `tb_item` VALUES (1, '葡萄糖', '', '234', '500ml', '瓶', 20.00, NULL, 100, '', '', 349, 1, '7', '2019-11-22 13:22:15', '20', '2019-11-26 10:53:43');
INSERT INTO `tb_item` VALUES (2, '奥美拉唑', NULL, '阿凡达', '20片', '瓶', 200.00, NULL, 90, '', '', 284, 1, '7', '2019-11-22 13:25:11', '7', '2019-11-22 13:29:50');
INSERT INTO `tb_item` VALUES (4, '甘草片', NULL, '短发短发', '50片', '瓶', 15.00, NULL, 95, '', '', 284, 1, '7', '2019-11-22 14:21:49', '7', '2019-11-22 16:55:01');
INSERT INTO `tb_item` VALUES (14, '甘草片', NULL, '皇天后土', '100片', '瓶', 15.00, NULL, 301, '', '', 284, 1, '7', '2019-11-22 16:18:53', '7', '2019-11-22 16:18:53');
INSERT INTO `tb_item` VALUES (15, '甘草片', NULL, '', '1*100片', '瓶', 11.00, NULL, 20, '', '', 217, 1, '0', '2019-11-24 13:31:06', '0', '2019-11-24 13:31:06');
INSERT INTO `tb_item` VALUES (16, '葡萄糖', NULL, '', '100ml', '瓶', 4.00, NULL, 15, '', '', 349, 1, '23', '2019-11-24 20:28:38', '23', '2019-11-24 20:28:38');
INSERT INTO `tb_item` VALUES (20, '头孢', '河北省沧州市东光县龙王李镇120号一单元201', '是的撒', '10片', '盒', 15.00, 20.00, 100, '', '', 217, 1, '23', '2019-11-25 18:07:00', '20', '2019-11-25 18:34:04');
COMMIT;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `order_id` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '订单id',
  `payment` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分',
  `payment_type` int(2) DEFAULT NULL COMMENT '支付类型，1、在线支付，2、货到付款',
  `post_fee` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分',
  `status` int(10) DEFAULT NULL COMMENT '状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '订单更新时间',
  `payment_time` datetime DEFAULT NULL COMMENT '付款时间',
  `consign_time` datetime DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `shipping_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '物流名称',
  `shipping_code` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '物流单号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `buyer_message` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '买家留言',
  `buyer_nick` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '买家昵称',
  `buyer_rate` int(2) DEFAULT NULL COMMENT '买家是否已经评价',
  `refundStatus` int(2) DEFAULT NULL,
  `refundReason` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `isRefund` int(2) DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  KEY `create_time` (`create_time`) USING BTREE,
  KEY `buyer_nick` (`buyer_nick`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  KEY `payment_type` (`payment_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tb_order_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_item`;
CREATE TABLE `tb_order_item` (
  `id` varchar(20) COLLATE utf8_bin NOT NULL,
  `item_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '商品id',
  `order_id` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '订单id',
  `num` int(10) DEFAULT NULL COMMENT '商品购买数量',
  `title` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '商品标题',
  `price` bigint(50) DEFAULT NULL COMMENT '商品单价',
  `total_fee` bigint(50) DEFAULT NULL COMMENT '商品总金额',
  `pic_path` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '商品图片地址',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `item_id` (`item_id`) USING BTREE,
  KEY `order_id` (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for tb_order_shipping
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_shipping`;
CREATE TABLE `tb_order_shipping` (
  `order_id` varchar(50) NOT NULL COMMENT '订单ID',
  `receiver_name` varchar(20) DEFAULT NULL COMMENT '收货人全名',
  `receiver_phone` varchar(20) DEFAULT NULL COMMENT '固定电话',
  `receiver_mobile` varchar(30) DEFAULT NULL COMMENT '移动电话',
  `receiver_state` varchar(10) DEFAULT NULL COMMENT '省份',
  `receiver_city` varchar(10) DEFAULT NULL COMMENT '城市',
  `receiver_district` varchar(20) DEFAULT NULL COMMENT '区/县',
  `receiver_address` varchar(200) DEFAULT NULL COMMENT '收货地址，如：xx路xx号',
  `receiver_zip` varchar(6) DEFAULT NULL COMMENT '邮政编码,如：310001',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_re_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_re_item`;
CREATE TABLE `tb_re_item` (
  `id` bigint(20) NOT NULL COMMENT '商品id，同时也是商品编号',
  `title` varchar(100) NOT NULL COMMENT '商品标题',
  `supplier` varchar(100) DEFAULT NULL COMMENT '供应商/产地',
  `sell_point` varchar(500) DEFAULT NULL COMMENT '商品卖点',
  `norm` varchar(100) DEFAULT NULL,
  `unit` varchar(100) DEFAULT NULL COMMENT '单位',
  `price` double(6,2) NOT NULL COMMENT '进价，单位元',
  `sell_price` double(6,2) DEFAULT NULL COMMENT '售价，单位元',
  `num` int(10) NOT NULL COMMENT '库存数量',
  `barcode` varchar(30) DEFAULT NULL COMMENT '商品条形码',
  `image` varchar(500) DEFAULT NULL COMMENT '商品图片',
  `cid` bigint(10) NOT NULL COMMENT '所属类目，叶子类目',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '商品状态，1-正常，2-下架，3-删除',
  `recovered` datetime NOT NULL COMMENT '回收时间',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '初始创建人',
  `updte_user_id` varchar(32) DEFAULT NULL COMMENT '删除人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `cid` (`cid`) USING BTREE,
  KEY `status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `realName` varchar(45) DEFAULT NULL,
  `business` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `headPicture` varchar(45) DEFAULT NULL,
  `addDate` date DEFAULT NULL,
  `updateDate` date DEFAULT NULL,
  `state` int(11) DEFAULT '0' COMMENT '1：正常\n2：冻结\n3：删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name_UNIQUE` (`userName`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES (1, 'thinkgem', 'admin', '小花', '运营岗', '1818181818', 'admin', '2018-03-19', '2018-03-19', 1);
INSERT INTO `tb_user` VALUES (4, 'jesper', 'jesper', '李四', '111', '1773232392@qq.com', NULL, '2018-03-30', '2018-04-04', 1);
INSERT INTO `tb_user` VALUES (7, 'test', 'test', '张三', '32323', '2323232', NULL, '2019-11-19', '2019-11-19', 1);
INSERT INTO `tb_user` VALUES (20, 'baixin', 'baixin', '百信', NULL, '392707696@qq.com', 'admin', '2019-11-23', '2019-11-23', 1);
INSERT INTO `tb_user` VALUES (21, '王丽', 'admin', NULL, NULL, '13333@ss', NULL, '2019-11-23', '2019-11-23', 1);
INSERT INTO `tb_user` VALUES (22, 'liang', 'liang', '梁超', 'bangong', '18231795644@163.COM', NULL, '2019-11-24', '2019-11-24', 1);
INSERT INTO `tb_user` VALUES (23, 'iy70', '700805', '刘莹', '', '571174268@qq.com', NULL, '2019-11-24', '2019-11-24', 1);
COMMIT;

-- ----------------------------
-- Function structure for formatDateTime
-- ----------------------------
DROP FUNCTION IF EXISTS `formatDateTime`;
delimiter ;;
CREATE FUNCTION `product_baixin`.`formatDateTime`(fdate datetime)
 RETURNS varchar(255) CHARSET utf8
begin 
 
declare x varchar(255) default '';
 
set x= date_format(fdate,'%Y年%m月%d日%h时%i分%s秒');
 
return x;
 
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
