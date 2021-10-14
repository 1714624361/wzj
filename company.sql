/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : company

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2021-04-10 14:31:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bridge_detection
-- ----------------------------
DROP TABLE IF EXISTS `bridge_detection`;
CREATE TABLE `bridge_detection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL COMMENT '材料内容',
  `detection_status` int(3) DEFAULT NULL COMMENT '检测状态(1默认提交申请2审批通过3审批未通过)',
  `detection_user_id` varchar(100) DEFAULT NULL COMMENT '审批人员ID',
  `detection_user_name` varchar(50) DEFAULT NULL COMMENT '审批人员姓名',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `user_id` varchar(100) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='桥梁检测';

-- ----------------------------
-- Records of bridge_detection
-- ----------------------------

-- ----------------------------
-- Table structure for engineering_design
-- ----------------------------
DROP TABLE IF EXISTS `engineering_design`;
CREATE TABLE `engineering_design` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task` varchar(255) DEFAULT NULL COMMENT '任务',
  `apply_user_id` varchar(100) DEFAULT NULL COMMENT '申请人员ID',
  `apply_user_name` varchar(50) DEFAULT NULL COMMENT '申请人员姓名',
  `status` int(3) DEFAULT NULL COMMENT '检测状态(1默认等待申请2提交申请3审批通过4审批未通过)',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `user_id` varchar(100) DEFAULT NULL COMMENT '用户ID',
  `feasibility_study_report` varchar(255) DEFAULT NULL COMMENT '可行性分析报告',
  `opinion` varchar(255) DEFAULT NULL COMMENT '单位意见',
  `construction_bidding` varchar(255) DEFAULT NULL COMMENT '建设招标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='工程设计';

-- ----------------------------
-- Records of engineering_design
-- ----------------------------
INSERT INTO `engineering_design` VALUES ('1', '跨太平洋大桥一期工程', null, null, null, '2021-04-10 13:36:01', null, null, null, null);

-- ----------------------------
-- Table structure for engineering_materials
-- ----------------------------
DROP TABLE IF EXISTS `engineering_materials`;
CREATE TABLE `engineering_materials` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL COMMENT '材料内容',
  `detection_status` int(3) DEFAULT NULL COMMENT '检测状态(1默认提交申请2审批通过3审批未通过)',
  `detection_user_id` varchar(100) DEFAULT NULL COMMENT '审批人员ID',
  `detection_user_name` varchar(50) DEFAULT NULL COMMENT '审批人员姓名',
  `type` int(2) DEFAULT NULL COMMENT '类型(1检测2鉴定)',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `user_id` varchar(100) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='工程材料';

-- ----------------------------
-- Records of engineering_materials
-- ----------------------------
INSERT INTO `engineering_materials` VALUES ('1', '工程材料内容', '2', '7b85ffd2b9fc44cdbf9ece7dfb74bc46', '系统管理员', '1', '2021-04-10 13:04:01', null);

-- ----------------------------
-- Table structure for recruitment
-- ----------------------------
DROP TABLE IF EXISTS `recruitment`;
CREATE TABLE `recruitment` (
  `id` int(11) NOT NULL,
  `position` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '职位',
  `desc` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '职位描述',
  `create_time` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='招聘表';

-- ----------------------------
-- Records of recruitment
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `DEPT_ID` varchar(100) NOT NULL,
  `DEPT_NAME` varchar(100) NOT NULL,
  `DEPT_LEV` varchar(100) DEFAULT NULL,
  `DEPT_PID` varchar(100) NOT NULL,
  `DEPT_ORDER` varchar(10) DEFAULT NULL,
  `DEPT_MARK` varchar(255) DEFAULT NULL,
  `DEPT_LAYERORDER` varchar(500) DEFAULT NULL,
  `DEPT_SEATGROUPID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`DEPT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('0c0d1608cd164ae0b2288ef06e134eca', '房贷', '2', '54b3d0134929480eaa148933cf2fc0d7', '2', '备注', '1-54b3d0134929480eaa148933cf2fc0d7-0c0d1608cd164ae0b2288ef06e134eca', null);
INSERT INTO `sys_dept` VALUES ('1', '总部', '0', '0', '0', '最高指挥', '1', null);
INSERT INTO `sys_dept` VALUES ('199fd6aec841421aaff996d8654c95fd', '营销中心', '1', '1', '1', '', '1-199fd6aec841421aaff996d8654c95fd', null);
INSERT INTO `sys_dept` VALUES ('54b3d0134929480eaa148933cf2fc0d7', '运营中心', '1', '1', '2', '备注', '1-54b3d0134929480eaa148933cf2fc0d7', null);
INSERT INTO `sys_dept` VALUES ('5d59b1bf237f494b934bc3c75f60d775', '营销1-4', null, 'c37ab8b8c5394760b3391810dc640528', null, null, '1-199fd6aec841421aaff996d8654c95fd-c37ab8b8c5394760b3391810dc640528-5d59b1bf237f494b934bc3c75f60d775', null);
INSERT INTO `sys_dept` VALUES ('5e6fc371b2054fc188ec905dce56924a', '财务部', '1', '1', '4', '备注', '1-5e6fc371b2054fc188ec905dce56924a', null);
INSERT INTO `sys_dept` VALUES ('6f33163144f8420992293a74899a12e3', '营销1-3', null, 'c37ab8b8c5394760b3391810dc640528', null, null, '1-199fd6aec841421aaff996d8654c95fd-c37ab8b8c5394760b3391810dc640528-6f33163144f8420992293a74899a12e3', null);
INSERT INTO `sys_dept` VALUES ('793ffcabdc0a466aa4be5060ba8c903b', '行政人事部', '1', '1', '5', '备注', '1-793ffcabdc0a466aa4be5060ba8c903b', null);
INSERT INTO `sys_dept` VALUES ('989c614033a04103ac9009e7cf053aaf', '网贷', null, '54b3d0134929480eaa148933cf2fc0d7', null, null, '1-54b3d0134929480eaa148933cf2fc0d7-989c614033a04103ac9009e7cf053aaf', null);
INSERT INTO `sys_dept` VALUES ('c159a563fcce4ddea19d98951b9af88c', '信贷', '2', '54b3d0134929480eaa148933cf2fc0d7', '1', '备注', '1-54b3d0134929480eaa148933cf2fc0d7-c159a563fcce4ddea19d98951b9af88c', null);
INSERT INTO `sys_dept` VALUES ('c189ef2469f44a9f8eea7e70c2c31c5a', '营销1-1', null, 'c37ab8b8c5394760b3391810dc640528', null, null, '1-199fd6aec841421aaff996d8654c95fd-c37ab8b8c5394760b3391810dc640528-c189ef2469f44a9f8eea7e70c2c31c5a', null);
INSERT INTO `sys_dept` VALUES ('c2d8ad6beae04785bf6f487d305c7a74', '营销1-2', null, 'c37ab8b8c5394760b3391810dc640528', null, null, '1-199fd6aec841421aaff996d8654c95fd-c37ab8b8c5394760b3391810dc640528-c2d8ad6beae04785bf6f487d305c7a74', null);
INSERT INTO `sys_dept` VALUES ('c37ab8b8c5394760b3391810dc640528', '营销中心', null, '199fd6aec841421aaff996d8654c95fd', null, null, '1-199fd6aec841421aaff996d8654c95fd-c37ab8b8c5394760b3391810dc640528', null);

-- ----------------------------
-- Table structure for sys_gl_qx
-- ----------------------------
DROP TABLE IF EXISTS `sys_gl_qx`;
CREATE TABLE `sys_gl_qx` (
  `GL_ID` varchar(100) NOT NULL,
  `ROLE_ID` varchar(100) DEFAULT NULL,
  `FX_QX` int(10) DEFAULT NULL,
  `FW_QX` int(10) DEFAULT NULL,
  `QX1` int(10) DEFAULT NULL,
  `QX2` int(10) DEFAULT NULL,
  `QX3` int(10) DEFAULT NULL,
  `QX4` int(10) DEFAULT NULL,
  PRIMARY KEY (`GL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_gl_qx
-- ----------------------------
INSERT INTO `sys_gl_qx` VALUES ('0a543b36cabf4d00b40e4b56e90b5863', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('0aa6f2f590f24dcb96bda05c092ec1d4', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('1', '2', '1', '1', '1', '1', '1', '1');
INSERT INTO `sys_gl_qx` VALUES ('2', '1', '0', '0', '1', '1', '1', '1');
INSERT INTO `sys_gl_qx` VALUES ('20fde75610fa424486e2537e22ceb4f8', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('284c8720be7142cb97e3797ddbe0e236', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('38a4a150ad0b41bc98b9785583f21968', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('41f9473b420c419ba20fd0bdba21ea4d', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('55896f5ce3c0494fa6850775a4e29ff6', '7', '0', '0', '1', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('5a2c317a1119407b9ca684177aec3c8a', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('68f23fc0caee475bae8d52244dea8444', '7', '0', '0', '1', '1', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('7dfd8d1f7b6245d283217b7e63eec9b2', '1', '1', '1', '1', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('89074958418c4b798f513845072f758f', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('ac66961adaa2426da4470c72ffeec117', '1', '1', '0', '1', '1', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('b0c77c29dfa140dc9b14a29c056f824f', '7', '1', '0', '1', '1', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('b31ae15df6504379b787e2a5060ebc7a', '20fde75610fa424486e2537e22ceb4f8', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('bc45096d6e604537931f16a34b1ea974', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('d0170718833e49718db8b1b616256205', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('dd5a46cde2c149b6a549cd94d3e99d0d', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('e74f713314154c35bd7fc98897859fe3', '6', '1', '1', '1', '1', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('eb8aad7cc95f4652b42a2379e1f47f1a', '20fde75610fa424486e2537e22ceb4f8', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('f063960208fc4c879dc994a9e159a13f', '1', '1', '1', '0', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('f944a9df72634249bbcb8cb73b0c9b86', '7', '1', '1', '1', '1', '0', '0');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `MENU_ID` int(11) NOT NULL AUTO_INCREMENT,
  `MENU_NAME` varchar(255) DEFAULT NULL,
  `MENU_URL` varchar(255) DEFAULT NULL,
  `PARENT_ID` varchar(100) DEFAULT NULL,
  `MENU_ORDER` varchar(100) DEFAULT NULL,
  `MENU_ICON` varchar(30) DEFAULT NULL,
  `MENU_TYPE` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '#', '0', null, 'icon-desktop', null);
INSERT INTO `sys_menu` VALUES ('2', '组织管理', 'role/roleIndex', '1', '', '', '');
INSERT INTO `sys_menu` VALUES ('4', '菜单管理', 'menu/menuIndex', '1', '4', '', '1');
INSERT INTO `sys_menu` VALUES ('5', '系统用户', 'user/userIndex', '1', '', '', '');
INSERT INTO `sys_menu` VALUES ('36', '部门管理', 'dept/deptIndex', '1', '', '', '');
INSERT INTO `sys_menu` VALUES ('41', '扩展管理', '#', '0', '', '', '');
INSERT INTO `sys_menu` VALUES ('46', '扩展管理', 'cus_/goCusPage', '41', '', '', '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ROLE_ID` varchar(100) NOT NULL,
  `ROLE_NAME` varchar(100) DEFAULT NULL,
  `RIGHTS` varchar(255) DEFAULT NULL,
  `PARENT_ID` varchar(100) DEFAULT NULL,
  `ADD_QX` varchar(255) DEFAULT NULL,
  `DEL_QX` varchar(255) DEFAULT NULL,
  `EDIT_QX` varchar(255) DEFAULT NULL,
  `CHA_QX` varchar(255) DEFAULT NULL,
  `QX_ID` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('0aa6f2f590f24dcb96bda05c092ec1d4', '做单员', '5570731947797743102729388032', '1', '411041792', '411041792', '411041792', '411041792', '0aa6f2f590f24dcb96bda05c092ec1d4');
INSERT INTO `sys_role` VALUES ('0df91fa024ae419ca22be690db6a545b', '财务', '261336857795280739939871698507597986398208', '1', null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('1', '系统管理员', '10475883840311616536312637064222898419805514869243958', '0', '1', '1', '1', '1', '1');
INSERT INTO `sys_role` VALUES ('2', '总经理', '90618097142841112527409414226069191415296003407872', '1', '17527754194982', '17527754194982', '17527754194982', '17527754194982', '2');
INSERT INTO `sys_role` VALUES ('38a4a150ad0b41bc98b9785583f21968', '客户经理', '4722366834713366102016', '1', '278224961536', '1065353216', '125829120', '125829120', '38a4a150ad0b41bc98b9785583f21968');
INSERT INTO `sys_role` VALUES ('83ea47540c2841a58db5393f3b365f1c', '营销中心组长', '11692013098711026289530367879201219428306324029440', '1', null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('89074958418c4b798f513845072f758f', '运营中心总监', '5572848011280387225117786112', '1', '965704941570', '549755813890', '687605809154', '965704941570', '89074958418c4b798f513845072f758f');
INSERT INTO `sys_role` VALUES ('ac66961adaa2426da4470c72ffeec117', '运营中心组长', '4951764289914755152405856256', '1', '5242934', '54', '0', '246', 'ac66961adaa2426da4470c72ffeec117');
INSERT INTO `sys_role` VALUES ('d0170718833e49718db8b1b616256205', '营销中心总监', '185615703308012944415352566973331972000408355733504', '1', '278761832448', '0', '662700032', '278761832448', 'd0170718833e49718db8b1b616256205');
INSERT INTO `sys_role` VALUES ('e9625206ad67440bae837c6942e21863', '人事', '68719476736', '1', null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `USER_ID` varchar(100) NOT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `RIGHTS` varchar(255) DEFAULT NULL,
  `ROLE_ID` varchar(100) DEFAULT NULL,
  `LAST_LOGIN` varchar(255) DEFAULT NULL,
  `IP` varchar(100) DEFAULT NULL,
  `STATUS` varchar(32) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  `SKIN` varchar(100) DEFAULT '',
  `EMAIL` varchar(32) DEFAULT NULL,
  `NUMBER` varchar(100) DEFAULT NULL,
  `PHONE` varchar(32) DEFAULT NULL,
  `DEPT_ID` varchar(100) NOT NULL,
  `BANKCARD` varchar(50) DEFAULT NULL,
  `BANKNAME` varchar(100) DEFAULT NULL,
  `BIRTHDAY` varchar(100) DEFAULT NULL,
  `DEPT_LAYERORDER` varchar(255) DEFAULT NULL,
  `POSITION` int(2) DEFAULT NULL,
  `LEV` int(2) DEFAULT NULL,
  `QUEUE` varchar(10) DEFAULT '0',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'de41b7fb99201d8334c23c014db35ecd92df81bc', '系统管理员', '1133671055321055258374707980945218933803269864762743594642571294', '1', '2018-03-02 09:01:29', '171.221.217.40', '0', '最高统治者', 'default', 'admin@main.com', '01', '18788888888', '1', '', '事实上', '', '1', '1', null, '8151');
INSERT INTO `sys_user` VALUES ('7b85ffd2b9fc44cdbf9ece7dfb74bc46', 'admin1', '23522528c13c6a2aa3e079bc76c4be77cb3f80bf', '系统管理员', null, '1', null, null, '0', null, null, null, '00', null, '1', null, null, null, '1', '1', null, '0');

-- ----------------------------
-- Table structure for sys_user_qx
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_qx`;
CREATE TABLE `sys_user_qx` (
  `U_ID` varchar(100) NOT NULL,
  `C1` int(10) DEFAULT NULL,
  `C2` int(10) DEFAULT NULL,
  `C3` int(10) DEFAULT NULL,
  `C4` int(10) DEFAULT NULL,
  `Q1` int(10) DEFAULT NULL,
  `Q2` int(10) DEFAULT NULL,
  `Q3` int(10) DEFAULT NULL,
  `Q4` int(10) DEFAULT NULL,
  PRIMARY KEY (`U_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_qx
-- ----------------------------
INSERT INTO `sys_user_qx` VALUES ('0a543b36cabf4d00b40e4b56e90b5863', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('0aa6f2f590f24dcb96bda05c092ec1d4', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('1', '1', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('2', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `sys_user_qx` VALUES ('20fde75610fa424486e2537e22ceb4f8', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('284c8720be7142cb97e3797ddbe0e236', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('38a4a150ad0b41bc98b9785583f21968', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('41f9473b420c419ba20fd0bdba21ea4d', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('55896f5ce3c0494fa6850775a4e29ff6', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('5a2c317a1119407b9ca684177aec3c8a', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('68f23fc0caee475bae8d52244dea8444', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('7dfd8d1f7b6245d283217b7e63eec9b2', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('89074958418c4b798f513845072f758f', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('ac66961adaa2426da4470c72ffeec117', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('b0c77c29dfa140dc9b14a29c056f824f', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('b31ae15df6504379b787e2a5060ebc7a', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('bc45096d6e604537931f16a34b1ea974', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('d0170718833e49718db8b1b616256205', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('dd5a46cde2c149b6a549cd94d3e99d0d', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('e74f713314154c35bd7fc98897859fe3', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('eb8aad7cc95f4652b42a2379e1f47f1a', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('f063960208fc4c879dc994a9e159a13f', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('f944a9df72634249bbcb8cb73b0c9b86', '0', '0', '0', '0', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for traffic_facilities
-- ----------------------------
DROP TABLE IF EXISTS `traffic_facilities`;
CREATE TABLE `traffic_facilities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL COMMENT '材料内容',
  `detection_status` int(3) DEFAULT NULL COMMENT '检测状态(1默认提交申请2审批通过3审批未通过)',
  `detection_user_id` varchar(100) DEFAULT NULL COMMENT '审批人员ID',
  `detection_user_name` varchar(50) DEFAULT NULL COMMENT '审批人员姓名',
  `type` int(2) DEFAULT NULL COMMENT '类型(1检测2鉴定)',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `user_id` varchar(100) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='交通设施';

-- ----------------------------
-- Records of traffic_facilities
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
