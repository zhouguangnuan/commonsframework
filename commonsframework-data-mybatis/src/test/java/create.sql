/*
Navicat MySQL Data Transfer

Source Server         : singno
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : spring-data-mybatis-write

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2015-10-25 12:24:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` int(8) unsigned NOT NULL auto_increment COMMENT '主键id',
  `name` varchar(32) collate utf8_bin NOT NULL COMMENT '班级名称',
  `year` tinyint(255) unsigned NOT NULL COMMENT '年级',
  `teacher_id` int(8) unsigned NOT NULL COMMENT '班主任id',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='班级表';

-- ----------------------------
-- Table structure for coures
-- ----------------------------
DROP TABLE IF EXISTS `coures`;
CREATE TABLE `coures` (
  `id` int(8) unsigned NOT NULL auto_increment COMMENT '主键id',
  `name` varchar(32) collate utf8_bin NOT NULL COMMENT '课程名称',
  `required` tinyint(1) unsigned default '0' COMMENT '是否必修（1：是，0：否）',
  `credit` int(2) unsigned default '0' COMMENT '学费',
  `teacher_id` int(8) unsigned NOT NULL COMMENT '教师id',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='课程表';

-- ----------------------------
-- Table structure for rel_student_course
-- ----------------------------
DROP TABLE IF EXISTS `rel_student_course`;
CREATE TABLE `rel_student_course` (
  `id` int(8) unsigned NOT NULL auto_increment COMMENT '主键id',
  `student_id` int(8) unsigned NOT NULL COMMENT '学生id',
  `course_id` int(8) unsigned NOT NULL COMMENT '课程id',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='学生课程关系表';

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(8) NOT NULL,
  `name` varchar(32) collate utf8_bin NOT NULL COMMENT '姓名',
  `sex` tinyint(1) default NULL COMMENT '性别（1：男，2：女）',
  `birthday` date default NULL COMMENT '生日',
  `class_id` int(11) default NULL COMMENT '班级id',
  `delete` tinyint(1) default NULL COMMENT '是否删除（1：删除，0：未删除）',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='学生表';

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(8) unsigned NOT NULL auto_increment COMMENT '主键id',
  `name` varchar(32) collate utf8_bin NOT NULL COMMENT '教师姓名',
  `sex` tinyint(1) unsigned default '1' COMMENT '性别（1：男，2：女）',
  `birthday` date default NULL COMMENT '生日',
  `work_date` date default NULL COMMENT '参加工作日期',
  `profession` varchar(32) collate utf8_bin default NULL COMMENT '专业名称',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='教师表';
