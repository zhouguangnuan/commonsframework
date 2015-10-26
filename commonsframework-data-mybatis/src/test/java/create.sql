/*
Navicat MySQL Data Transfer

Source Server         : singno
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : spring-data-mybatis-write

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2015-10-26 08:13:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` int(8) unsigned NOT NULL auto_increment COMMENT '主键id',
  `name` varchar(32) collate utf8_bin NOT NULL COMMENT '班级名称',
  `year` smallint(2) unsigned NOT NULL COMMENT '年级',
  `teacher_id` int(8) unsigned NOT NULL COMMENT '班主任id',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='班级表';

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('1', '计算机1班', '1', '1');
INSERT INTO `class` VALUES ('2', '计算机2班', '2', '2');
INSERT INTO `class` VALUES ('3', '计算机3班', '3', '1');

-- ----------------------------
-- Table structure for coures
-- ----------------------------
DROP TABLE IF EXISTS `coures`;
CREATE TABLE `coures` (
  `id` int(8) unsigned NOT NULL auto_increment COMMENT '主键id',
  `name` varchar(32) collate utf8_bin NOT NULL COMMENT '课程名称',
  `required` tinyint(1) unsigned default '0' COMMENT '是否必修（1：是，0：否）',
  `credit` int(2) unsigned default '0' COMMENT '学分',
  `teacher_id` int(8) unsigned NOT NULL COMMENT '教师id',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='课程表';

-- ----------------------------
-- Records of coures
-- ----------------------------
INSERT INTO `coures` VALUES ('1', 'java', '1', '10', '1');
INSERT INTO `coures` VALUES ('2', 'android', '1', '10', '2');
INSERT INTO `coures` VALUES ('3', 'ios', '1', '10', '3');
INSERT INTO `coures` VALUES ('4', 'PHP', '1', '10', '1');

-- ----------------------------
-- Table structure for rel_student_coures
-- ----------------------------
DROP TABLE IF EXISTS `rel_student_coures`;
CREATE TABLE `rel_student_coures` (
  `id` int(8) unsigned NOT NULL auto_increment COMMENT '主键id',
  `student_id` int(8) unsigned NOT NULL COMMENT '学生id',
  `course_id` int(8) unsigned NOT NULL COMMENT '课程id',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='学生课程关系表';

-- ----------------------------
-- Records of rel_student_coures
-- ----------------------------
INSERT INTO `rel_student_coures` VALUES ('1', '56', '1');
INSERT INTO `rel_student_coures` VALUES ('2', '56', '2');
INSERT INTO `rel_student_coures` VALUES ('3', '56', '3');
INSERT INTO `rel_student_coures` VALUES ('4', '57', '1');
INSERT INTO `rel_student_coures` VALUES ('5', '57', '2');
INSERT INTO `rel_student_coures` VALUES ('6', '57', '3');
INSERT INTO `rel_student_coures` VALUES ('7', '58', '1');
INSERT INTO `rel_student_coures` VALUES ('8', '58', '2');
INSERT INTO `rel_student_coures` VALUES ('9', '58', '3');
INSERT INTO `rel_student_coures` VALUES ('10', '59', '1');
INSERT INTO `rel_student_coures` VALUES ('11', '59', '2');
INSERT INTO `rel_student_coures` VALUES ('12', '59', '3');
INSERT INTO `rel_student_coures` VALUES ('13', '60', '1');
INSERT INTO `rel_student_coures` VALUES ('14', '60', '2');
INSERT INTO `rel_student_coures` VALUES ('15', '60', '3');
INSERT INTO `rel_student_coures` VALUES ('16', '61', '1');
INSERT INTO `rel_student_coures` VALUES ('17', '61', '2');
INSERT INTO `rel_student_coures` VALUES ('18', '61', '3');
INSERT INTO `rel_student_coures` VALUES ('19', '62', '1');
INSERT INTO `rel_student_coures` VALUES ('20', '62', '2');
INSERT INTO `rel_student_coures` VALUES ('21', '62', '3');
INSERT INTO `rel_student_coures` VALUES ('22', '63', '1');
INSERT INTO `rel_student_coures` VALUES ('23', '63', '2');
INSERT INTO `rel_student_coures` VALUES ('24', '63', '3');
INSERT INTO `rel_student_coures` VALUES ('25', '64', '1');
INSERT INTO `rel_student_coures` VALUES ('26', '64', '2');
INSERT INTO `rel_student_coures` VALUES ('27', '64', '3');
INSERT INTO `rel_student_coures` VALUES ('28', '65', '1');
INSERT INTO `rel_student_coures` VALUES ('29', '65', '2');
INSERT INTO `rel_student_coures` VALUES ('30', '65', '3');
INSERT INTO `rel_student_coures` VALUES ('31', '66', '1');
INSERT INTO `rel_student_coures` VALUES ('32', '66', '2');
INSERT INTO `rel_student_coures` VALUES ('33', '66', '3');
INSERT INTO `rel_student_coures` VALUES ('34', '67', '1');
INSERT INTO `rel_student_coures` VALUES ('35', '67', '2');
INSERT INTO `rel_student_coures` VALUES ('36', '67', '3');
INSERT INTO `rel_student_coures` VALUES ('37', '68', '1');
INSERT INTO `rel_student_coures` VALUES ('38', '68', '2');
INSERT INTO `rel_student_coures` VALUES ('39', '68', '3');
INSERT INTO `rel_student_coures` VALUES ('40', '69', '1');
INSERT INTO `rel_student_coures` VALUES ('41', '69', '2');
INSERT INTO `rel_student_coures` VALUES ('42', '69', '3');
INSERT INTO `rel_student_coures` VALUES ('43', '70', '1');
INSERT INTO `rel_student_coures` VALUES ('44', '70', '2');
INSERT INTO `rel_student_coures` VALUES ('45', '70', '3');
INSERT INTO `rel_student_coures` VALUES ('46', '71', '1');
INSERT INTO `rel_student_coures` VALUES ('47', '71', '2');
INSERT INTO `rel_student_coures` VALUES ('48', '71', '3');
INSERT INTO `rel_student_coures` VALUES ('49', '72', '1');
INSERT INTO `rel_student_coures` VALUES ('50', '72', '2');
INSERT INTO `rel_student_coures` VALUES ('51', '72', '3');
INSERT INTO `rel_student_coures` VALUES ('52', '73', '1');
INSERT INTO `rel_student_coures` VALUES ('53', '73', '2');
INSERT INTO `rel_student_coures` VALUES ('54', '73', '3');
INSERT INTO `rel_student_coures` VALUES ('55', '74', '1');
INSERT INTO `rel_student_coures` VALUES ('56', '74', '2');
INSERT INTO `rel_student_coures` VALUES ('57', '74', '3');
INSERT INTO `rel_student_coures` VALUES ('58', '75', '1');
INSERT INTO `rel_student_coures` VALUES ('59', '75', '2');
INSERT INTO `rel_student_coures` VALUES ('60', '75', '3');
INSERT INTO `rel_student_coures` VALUES ('61', '76', '1');
INSERT INTO `rel_student_coures` VALUES ('62', '76', '2');
INSERT INTO `rel_student_coures` VALUES ('63', '76', '3');
INSERT INTO `rel_student_coures` VALUES ('64', '77', '1');
INSERT INTO `rel_student_coures` VALUES ('65', '77', '2');
INSERT INTO `rel_student_coures` VALUES ('66', '77', '3');
INSERT INTO `rel_student_coures` VALUES ('67', '78', '1');
INSERT INTO `rel_student_coures` VALUES ('68', '78', '2');
INSERT INTO `rel_student_coures` VALUES ('69', '78', '3');
INSERT INTO `rel_student_coures` VALUES ('70', '79', '1');
INSERT INTO `rel_student_coures` VALUES ('71', '79', '2');
INSERT INTO `rel_student_coures` VALUES ('72', '79', '3');
INSERT INTO `rel_student_coures` VALUES ('73', '80', '1');
INSERT INTO `rel_student_coures` VALUES ('74', '80', '2');
INSERT INTO `rel_student_coures` VALUES ('75', '80', '3');
INSERT INTO `rel_student_coures` VALUES ('76', '81', '1');
INSERT INTO `rel_student_coures` VALUES ('77', '81', '2');
INSERT INTO `rel_student_coures` VALUES ('78', '81', '3');
INSERT INTO `rel_student_coures` VALUES ('79', '82', '1');
INSERT INTO `rel_student_coures` VALUES ('80', '82', '2');
INSERT INTO `rel_student_coures` VALUES ('81', '82', '3');
INSERT INTO `rel_student_coures` VALUES ('82', '83', '1');
INSERT INTO `rel_student_coures` VALUES ('83', '83', '2');
INSERT INTO `rel_student_coures` VALUES ('84', '83', '3');
INSERT INTO `rel_student_coures` VALUES ('85', '84', '1');
INSERT INTO `rel_student_coures` VALUES ('86', '84', '2');
INSERT INTO `rel_student_coures` VALUES ('87', '84', '3');
INSERT INTO `rel_student_coures` VALUES ('88', '85', '1');
INSERT INTO `rel_student_coures` VALUES ('89', '85', '2');
INSERT INTO `rel_student_coures` VALUES ('90', '85', '3');
INSERT INTO `rel_student_coures` VALUES ('91', '86', '1');
INSERT INTO `rel_student_coures` VALUES ('92', '86', '2');
INSERT INTO `rel_student_coures` VALUES ('93', '86', '3');
INSERT INTO `rel_student_coures` VALUES ('94', '87', '1');
INSERT INTO `rel_student_coures` VALUES ('95', '87', '2');
INSERT INTO `rel_student_coures` VALUES ('96', '87', '3');
INSERT INTO `rel_student_coures` VALUES ('97', '88', '1');
INSERT INTO `rel_student_coures` VALUES ('98', '88', '2');
INSERT INTO `rel_student_coures` VALUES ('99', '88', '3');
INSERT INTO `rel_student_coures` VALUES ('100', '89', '1');
INSERT INTO `rel_student_coures` VALUES ('101', '89', '2');
INSERT INTO `rel_student_coures` VALUES ('102', '89', '3');
INSERT INTO `rel_student_coures` VALUES ('103', '90', '1');
INSERT INTO `rel_student_coures` VALUES ('104', '90', '2');
INSERT INTO `rel_student_coures` VALUES ('105', '90', '3');
INSERT INTO `rel_student_coures` VALUES ('106', '91', '1');
INSERT INTO `rel_student_coures` VALUES ('107', '91', '2');
INSERT INTO `rel_student_coures` VALUES ('108', '91', '3');
INSERT INTO `rel_student_coures` VALUES ('109', '92', '1');
INSERT INTO `rel_student_coures` VALUES ('110', '92', '2');
INSERT INTO `rel_student_coures` VALUES ('111', '92', '3');
INSERT INTO `rel_student_coures` VALUES ('112', '93', '1');
INSERT INTO `rel_student_coures` VALUES ('113', '93', '2');
INSERT INTO `rel_student_coures` VALUES ('114', '93', '3');
INSERT INTO `rel_student_coures` VALUES ('115', '94', '1');
INSERT INTO `rel_student_coures` VALUES ('116', '94', '2');
INSERT INTO `rel_student_coures` VALUES ('117', '94', '3');
INSERT INTO `rel_student_coures` VALUES ('118', '95', '1');
INSERT INTO `rel_student_coures` VALUES ('119', '95', '2');
INSERT INTO `rel_student_coures` VALUES ('120', '95', '3');
INSERT INTO `rel_student_coures` VALUES ('121', '96', '1');
INSERT INTO `rel_student_coures` VALUES ('122', '96', '2');
INSERT INTO `rel_student_coures` VALUES ('123', '96', '3');
INSERT INTO `rel_student_coures` VALUES ('124', '97', '1');
INSERT INTO `rel_student_coures` VALUES ('125', '97', '2');
INSERT INTO `rel_student_coures` VALUES ('126', '97', '3');
INSERT INTO `rel_student_coures` VALUES ('127', '98', '1');
INSERT INTO `rel_student_coures` VALUES ('128', '98', '2');
INSERT INTO `rel_student_coures` VALUES ('129', '98', '3');
INSERT INTO `rel_student_coures` VALUES ('130', '99', '1');
INSERT INTO `rel_student_coures` VALUES ('131', '99', '2');
INSERT INTO `rel_student_coures` VALUES ('132', '99', '3');
INSERT INTO `rel_student_coures` VALUES ('133', '100', '1');
INSERT INTO `rel_student_coures` VALUES ('134', '100', '2');
INSERT INTO `rel_student_coures` VALUES ('135', '100', '3');
INSERT INTO `rel_student_coures` VALUES ('136', '101', '1');
INSERT INTO `rel_student_coures` VALUES ('137', '101', '2');
INSERT INTO `rel_student_coures` VALUES ('138', '101', '3');
INSERT INTO `rel_student_coures` VALUES ('139', '102', '1');
INSERT INTO `rel_student_coures` VALUES ('140', '102', '2');
INSERT INTO `rel_student_coures` VALUES ('141', '102', '3');
INSERT INTO `rel_student_coures` VALUES ('142', '103', '1');
INSERT INTO `rel_student_coures` VALUES ('143', '103', '2');
INSERT INTO `rel_student_coures` VALUES ('144', '103', '3');
INSERT INTO `rel_student_coures` VALUES ('145', '104', '1');
INSERT INTO `rel_student_coures` VALUES ('146', '104', '2');
INSERT INTO `rel_student_coures` VALUES ('147', '104', '3');
INSERT INTO `rel_student_coures` VALUES ('148', '105', '1');
INSERT INTO `rel_student_coures` VALUES ('149', '105', '2');
INSERT INTO `rel_student_coures` VALUES ('150', '105', '3');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(8) unsigned NOT NULL auto_increment COMMENT '主键id',
  `name` varchar(32) collate utf8_bin NOT NULL COMMENT '姓名',
  `sex` smallint(1) default '1' COMMENT '性别（1：男，2：女）',
  `birthday` date default NULL COMMENT '生日',
  `class_id` int(11) NOT NULL COMMENT '班级id',
  `is_delete` tinyint(1) default '0' COMMENT '是否删除（1：删除，0：未删除）',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='学生表';

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('56', '菜鸟0', null, null, '1', null);
INSERT INTO `student` VALUES ('57', '菜鸟1', null, null, '2', null);
INSERT INTO `student` VALUES ('58', '菜鸟2', null, null, '3', null);
INSERT INTO `student` VALUES ('59', '菜鸟3', null, null, '1', null);
INSERT INTO `student` VALUES ('60', '菜鸟4', null, null, '2', null);
INSERT INTO `student` VALUES ('61', '菜鸟5', null, null, '3', null);
INSERT INTO `student` VALUES ('62', '菜鸟6', null, null, '1', null);
INSERT INTO `student` VALUES ('63', '菜鸟7', null, null, '2', null);
INSERT INTO `student` VALUES ('64', '菜鸟8', null, null, '3', null);
INSERT INTO `student` VALUES ('65', '菜鸟9', null, null, '1', null);
INSERT INTO `student` VALUES ('66', '菜鸟10', null, null, '2', null);
INSERT INTO `student` VALUES ('67', '菜鸟11', null, null, '3', null);
INSERT INTO `student` VALUES ('68', '菜鸟12', null, null, '1', null);
INSERT INTO `student` VALUES ('69', '菜鸟13', null, null, '2', null);
INSERT INTO `student` VALUES ('70', '菜鸟14', null, null, '3', null);
INSERT INTO `student` VALUES ('71', '菜鸟15', null, null, '1', null);
INSERT INTO `student` VALUES ('72', '菜鸟16', null, null, '2', null);
INSERT INTO `student` VALUES ('73', '菜鸟17', null, null, '3', null);
INSERT INTO `student` VALUES ('74', '菜鸟18', null, null, '1', null);
INSERT INTO `student` VALUES ('75', '菜鸟19', null, null, '2', null);
INSERT INTO `student` VALUES ('76', '菜鸟20', null, null, '3', null);
INSERT INTO `student` VALUES ('77', '菜鸟21', null, null, '1', null);
INSERT INTO `student` VALUES ('78', '菜鸟22', null, null, '2', null);
INSERT INTO `student` VALUES ('79', '菜鸟23', null, null, '3', null);
INSERT INTO `student` VALUES ('80', '菜鸟24', null, null, '1', null);
INSERT INTO `student` VALUES ('81', '菜鸟25', null, null, '2', null);
INSERT INTO `student` VALUES ('82', '菜鸟26', null, null, '3', null);
INSERT INTO `student` VALUES ('83', '菜鸟27', null, null, '1', null);
INSERT INTO `student` VALUES ('84', '菜鸟28', null, null, '2', null);
INSERT INTO `student` VALUES ('85', '菜鸟29', null, null, '3', null);
INSERT INTO `student` VALUES ('86', '菜鸟30', null, null, '1', null);
INSERT INTO `student` VALUES ('87', '菜鸟31', null, null, '2', null);
INSERT INTO `student` VALUES ('88', '菜鸟32', null, null, '3', null);
INSERT INTO `student` VALUES ('89', '菜鸟33', null, null, '1', null);
INSERT INTO `student` VALUES ('90', '菜鸟34', null, null, '2', null);
INSERT INTO `student` VALUES ('91', '菜鸟35', null, null, '3', null);
INSERT INTO `student` VALUES ('92', '菜鸟36', null, null, '1', null);
INSERT INTO `student` VALUES ('93', '菜鸟37', null, null, '2', null);
INSERT INTO `student` VALUES ('94', '菜鸟38', null, null, '3', null);
INSERT INTO `student` VALUES ('95', '菜鸟39', null, null, '1', null);
INSERT INTO `student` VALUES ('96', '菜鸟40', null, null, '2', null);
INSERT INTO `student` VALUES ('97', '菜鸟41', null, null, '3', null);
INSERT INTO `student` VALUES ('98', '菜鸟42', null, null, '1', null);
INSERT INTO `student` VALUES ('99', '菜鸟43', null, null, '2', null);
INSERT INTO `student` VALUES ('100', '菜鸟44', null, null, '3', null);
INSERT INTO `student` VALUES ('101', '菜鸟45', null, null, '1', null);
INSERT INTO `student` VALUES ('102', '菜鸟46', null, null, '2', null);
INSERT INTO `student` VALUES ('103', '菜鸟47', null, null, '3', null);
INSERT INTO `student` VALUES ('104', '菜鸟48', null, null, '1', null);
INSERT INTO `student` VALUES ('105', '菜鸟49', null, null, '2', null);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(8) unsigned NOT NULL auto_increment COMMENT '主键id',
  `name` varchar(32) collate utf8_bin NOT NULL COMMENT '教师姓名',
  `sex` smallint(1) unsigned default NULL COMMENT '性别（1：男，2：女）',
  `birthday` date default NULL COMMENT '生日',
  `work_date` date default NULL COMMENT '参加工作日期',
  `professional` varchar(32) collate utf8_bin default NULL COMMENT '职称',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='教师表';

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '周光暖2', '1', null, null, '教授3');
INSERT INTO `teacher` VALUES ('2', '鲍贱民2', '2', null, null, '高级教师3');
INSERT INTO `teacher` VALUES ('3', '蒋妮娜2', '3', null, null, '普通教师3');
