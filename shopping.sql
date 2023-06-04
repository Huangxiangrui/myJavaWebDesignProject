DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT,
  `a_name` varchar(20) DEFAULT NULL,
  `a_pass` char(32) DEFAULT NULL,
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '202cb962ac59075b964b07152d234b70');
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(50) DEFAULT NULL,
  `u_pass` char(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `u_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'zar', '202cb962ac59075b964b07152d234b70', '0');
INSERT INTO `users` VALUES ('2', 'zhangsan', '202cb962ac59075b964b07152d234b70', '1');
DROP TABLE IF EXISTS `businessman`;
CREATE TABLE `businessman` (
  `b_id` int(11) NOT NULL AUTO_INCREMENT,
  `b_name` varchar(50) DEFAULT NULL,
  `b_pass` char(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `b_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`b_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `businessman` VALUES ('1', 'zar', '202cb962ac59075b964b07152d234b70', '0');
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
                                `p_id` int(11) NOT NULL AUTO_INCREMENT,
                                `p_name` varchar(20) DEFAULT NULL,
                                `p_content` varchar(200) DEFAULT NULL,
                                `p_price` int(11) DEFAULT NULL,
                                `p_image` varchar(200) DEFAULT NULL,
                                `p_number` int(11) DEFAULT NULL,
                                `type_id` int(11) DEFAULT NULL,
                                `p_date` date DEFAULT NULL,
                                `b_id` int(11) DEFAULT NULL,
                                PRIMARY KEY (`p_id`),
                                KEY `FK_Reference_1`(`type_id`),
                                KEY `FK_Reference_2`(`b_id`),
                                CONSTRAINT `product_info_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `product_type` (`type_id`),
                                CONSTRAINT `product_info_ibfk_2` FOREIGN KEY (`b_id`) REFERENCES `businessman` (`b_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

INSERT INTO `product_info` VALUES ('1', '小米Note2', '双曲面 黑色 6GB内存 64GB闪存', '2899', 'xmNote2.jpg', '500', '1', '2018-01-04','1');
INSERT INTO `product_info` VALUES ('3', '红米Note4X', '5.5英寸 绿色 4GB内存 64GB闪存', '1299', 'hmNote4X.jpg', '500', '1', '2018-01-06','1');
INSERT INTO `product_info` VALUES ('4', '红米4', '5英寸 金色 3GB内存 32GB闪存', '999', 'hm4.jpg', '500', '1', '2018-01-07','1');
INSERT INTO `product_info` VALUES ('5', '红米4X', '5英寸 黑色 3GB内存 32GB闪存', '899', 'hm4X.jpg', '500', '1', '2018-01-08','1');
INSERT INTO `product_info` VALUES ('6', '小米平板3', '7.9英寸 金色 4GB内存 64GB闪存', '1499', 'xmPad3.jpg', '500', '2', '2018-01-09','1');
INSERT INTO `product_info` VALUES ('7', '小米Air12', '12.5英寸 银色 4GB内存 128GB闪存', '3599', 'xmAir12.jpg', '500', '2', '2018-01-18','1');
INSERT INTO `product_info` VALUES ('8', '小米Air13', '13.3英寸 银色 8GB内存 256GB闪存', '4999', 'xmAir13.jpg', '500', '2', '2018-01-17','1');
INSERT INTO `product_info` VALUES ('9', '小米Pro', '15.6英寸 灰色 16GB内存 256GB闪存', '6999', 'xmPro.jpg', '500', '2', '2018-01-16','1');
INSERT INTO `product_info` VALUES ('10', '小米电视4', '49英寸 原装LG屏 3840×2160 真4K', '3299', 'xmTV4-49.jpg', '500', '3', '2018-01-15','1');
INSERT INTO `product_info` VALUES ('11', '小米电视4', '55英寸 原装三星屏 3840×2160 真4K', '3999', 'xmTV4-55.jpg', '500', '3', '2018-01-13','1');
INSERT INTO `product_info` VALUES ('12', '小米电视4', '65英寸 原装三星屏 3840×2160 真4K', '8999', 'xmTV4-65.jpg', '500', '3', '2018-01-22','1');
INSERT INTO `product_info` VALUES ('13', '小米电视4A', '44英寸 FHD全高清屏 1920*1080', '1999', 'xmTV4A-43.jpg', '500', '3', '2018-01-11','1');
INSERT INTO `product_info` VALUES ('14', '小米电视4A', '49英寸 FHD全高清屏 1920*1080', '2299', 'xmTV4A-49.jpg', '500', '3', '2018-01-21','1');
INSERT INTO `product_info` VALUES ('15', '小米MIX2', '全陶瓷 黑色 8GB内存 128GB闪存', '4699', 'xmMIX2.jpg', '500', '1', '2018-04-01','1');
INSERT INTO `product_info` VALUES ('16', '小米Note3', '全网通 蓝色 6GB内存 64GB闪存', '2499', 'xmNote3.jpg', '500', '1', '2018-03-01','1');
INSERT INTO `product_info` VALUES ('18', '小米MAX2', '全金属 金色 4GB内存 64GB闪存', '1599', 'xmMAX2.jpg', '500', '1', '2018-01-02','1');
INSERT INTO `product_info` VALUES ('19', '小米5X', '全金属 金色 4GB内存 64GB闪存', '1599', 'xm5X.jpg', '500', '1', '2018-01-03','1');
DROP TABLE IF EXISTS `product_type`;
CREATE TABLE `product_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_type
-- ----------------------------
INSERT INTO `product_type` VALUES ('1', '手机');
INSERT INTO `product_type` VALUES ('2', '电脑');
INSERT INTO `product_type` VALUES ('3', '电视');
DROP TABLE IF EXISTS `carshop`;
CREATE TABLE `carshop`
(
    `c_id`    int(11) NOT NULL AUTO_INCREMENT,
    `u_id`    int(11) DEFAULT NULL,
    `p_id`    int(11) DEFAULT NULL,
    `numbers` int(11) DEFAULT NULL,
    PRIMARY KEY (`c_id`),
    KEY `FK_Reference_3` (`u_id`),
    KEY `FK_Reference_4` (`p_id`),
    CONSTRAINT `FK_Reference_3` FOREIGN KEY (`u_id`) REFERENCES `users` (`u_id`),
    CONSTRAINT `FK_Reference_4` FOREIGN KEY (`p_id`) REFERENCES `product_info` (`p_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of carshop
-- ----------------------------
INSERT INTO `carshop`
VALUES ('1', '1', '1', '2');
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `o_id` char(32) NOT NULL,
  `u_id` int(11) DEFAULT NULL,
  `b_id` int(11) DEFAULT NULL,
  `p_id` int(11) DEFAULT NULL,
  `totalprice` double(10,2) DEFAULT NULL,
  `status` varchar(6) DEFAULT NULL,
  `odate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`o_id`),
  KEY `FK_Reference_5` (`u_id`),
  KEY `FK_Reference_6` (`b_id`),
  KEY `FK_Reference_7` (`p_id`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`u_id`) REFERENCES `users` (`u_id`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`b_id`) REFERENCES `users` (`b_id`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`p_id`) REFERENCES `users` (`p_id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xmorder
-- ----------------------------
INSERT INTO `xmorder` VALUES ('abcd111222333444777888999000wwww', '1', '1', '1','1','9996.00', '待发货', '2021-09-09 09:50:33');
