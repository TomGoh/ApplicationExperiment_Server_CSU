/*
 Navicat Premium Data Transfer

 Source Server         : Default
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : outpatientservice

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 20/01/2021 22:41:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `Account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`Account`) USING BTREE,
  INDEX `Account`(`Account`, `Password`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('8208180414', '123456');
INSERT INTO `account` VALUES ('8208180501', '654321');
INSERT INTO `account` VALUES ('8208180502', '123456');
INSERT INTO `account` VALUES ('8208180521', '654321');
INSERT INTO `account` VALUES ('root', '123456');

-- ----------------------------
-- Table structure for balance
-- ----------------------------
DROP TABLE IF EXISTS `balance`;
CREATE TABLE `balance`  (
  `PatientAccount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Balance` decimal(10, 0) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`PatientAccount`) USING BTREE,
  CONSTRAINT `Balance_Patient` FOREIGN KEY (`PatientAccount`) REFERENCES `patient` (`PatientAccount`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of balance
-- ----------------------------
INSERT INTO `balance` VALUES ('8208180414', 0000000000);
INSERT INTO `balance` VALUES ('8208180501', 0000000000);
INSERT INTO `balance` VALUES ('8208180502', 0000000100);
INSERT INTO `balance` VALUES ('8208180521', 0000020717);
INSERT INTO `balance` VALUES ('root', 0000000000);

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `DepartmentName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `DepartmentID` int NOT NULL AUTO_INCREMENT,
  `DepartmentDescription` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`DepartmentID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('神经外科', 1, NULL);
INSERT INTO `department` VALUES ('神经内科', 2, NULL);
INSERT INTO `department` VALUES ('妇产科', 3, NULL);
INSERT INTO `department` VALUES ('胸外科', 4, NULL);
INSERT INTO `department` VALUES ('眼科', 5, NULL);
INSERT INTO `department` VALUES ('口腔科', 6, NULL);
INSERT INTO `department` VALUES ('心血管科', 7, NULL);
INSERT INTO `department` VALUES ('耳鼻喉科', 8, NULL);
INSERT INTO `department` VALUES ('骨科', 9, NULL);
INSERT INTO `department` VALUES ('放射科', 10, NULL);
INSERT INTO `department` VALUES ('肿瘤科', 11, NULL);
INSERT INTO `department` VALUES ('消化内科', 12, NULL);
INSERT INTO `department` VALUES ('儿科', 13, NULL);

-- ----------------------------
-- Table structure for departmentreserve
-- ----------------------------
DROP TABLE IF EXISTS `departmentreserve`;
CREATE TABLE `departmentreserve`  (
  `DepartmentID` int NOT NULL,
  `DepartmentSurplus` int NOT NULL,
  `ReserveDate` date NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of departmentreserve
-- ----------------------------
INSERT INTO `departmentreserve` VALUES (1, 400, '2020-12-24');
INSERT INTO `departmentreserve` VALUES (2, 100, '2020-12-24');
INSERT INTO `departmentreserve` VALUES (3, 200, '2020-12-24');
INSERT INTO `departmentreserve` VALUES (4, 150, '2020-12-24');
INSERT INTO `departmentreserve` VALUES (5, 150, '2021-01-04');
INSERT INTO `departmentreserve` VALUES (6, 150, '2021-01-04');
INSERT INTO `departmentreserve` VALUES (7, 250, '2021-01-04');
INSERT INTO `departmentreserve` VALUES (8, 100, '2021-01-04');
INSERT INTO `departmentreserve` VALUES (9, 50, '2021-01-04');
INSERT INTO `departmentreserve` VALUES (10, 50, '2021-01-04');
INSERT INTO `departmentreserve` VALUES (11, 50, '2021-01-04');
INSERT INTO `departmentreserve` VALUES (1, 350, '2021-01-05');
INSERT INTO `departmentreserve` VALUES (2, 50, '2021-01-05');
INSERT INTO `departmentreserve` VALUES (1, 649, '2021-01-09');
INSERT INTO `departmentreserve` VALUES (12, 50, '2021-01-09');
INSERT INTO `departmentreserve` VALUES (13, 50, '2021-01-09');

-- ----------------------------
-- Table structure for doctor
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor`  (
  `DoctorName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `DoctorID` int NOT NULL AUTO_INCREMENT,
  `DoctorDescription` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `DoctorDegree` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `DepartmentID` int NULL DEFAULT NULL,
  PRIMARY KEY (`DoctorID`) USING BTREE,
  INDEX `Doctor_Dept`(`DepartmentID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doctor
-- ----------------------------
INSERT INTO `doctor` VALUES ('吴神一', 1, '一位十分优秀的神经外科医生', '主任医师', 1);
INSERT INTO `doctor` VALUES ('陈神二', 2, '一位优秀的神经外科医生', '副主任医师', 1);
INSERT INTO `doctor` VALUES ('张神三', 3, NULL, '主任医师', 2);
INSERT INTO `doctor` VALUES ('刘妇一', 4, NULL, '主任医师', 3);
INSERT INTO `doctor` VALUES ('赵妇二', 5, NULL, '医师', 3);
INSERT INTO `doctor` VALUES ('钱妇三', 6, '高超的妇产科医生', '副主任医师', 3);
INSERT INTO `doctor` VALUES ('李神三', 7, '初出茅庐的神经外科医生', '医师', 1);
INSERT INTO `doctor` VALUES ('周神四', 8, NULL, '医师', 1);
INSERT INTO `doctor` VALUES ('郑神五', 9, NULL, '医师', 1);
INSERT INTO `doctor` VALUES ('王神六', 10, NULL, '医师', 1);
INSERT INTO `doctor` VALUES ('冯神七', 11, NULL, '医师', 1);
INSERT INTO `doctor` VALUES ('诸神八', 12, '高超的神经内科医师', '副主任医师', 2);
INSERT INTO `doctor` VALUES ('卫神九', 13, '获得众多荣誉的神经内科医师', '副主任医师', 2);
INSERT INTO `doctor` VALUES ('蒋神十', 14, '初出茅庐的神经内科医生', '医师', 2);
INSERT INTO `doctor` VALUES ('沈妇四', 15, '很有经验的妇产科医生', '医师', 3);
INSERT INTO `doctor` VALUES ('韩胸一', 16, '参加过抗疫的医生', '医师', 4);
INSERT INTO `doctor` VALUES ('杨胸二', 17, '有经验的胸科医生', '主任医师', 4);
INSERT INTO `doctor` VALUES ('朱胸三', 18, NULL, '副主任医师', 4);
INSERT INTO `doctor` VALUES ('秦胸四', 19, NULL, '医师', 4);
INSERT INTO `doctor` VALUES ('尤眼一', 20, '优秀的眼科医师', '主任医师', 5);
INSERT INTO `doctor` VALUES ('许眼二', 21, '刚刚入职的眼科医师', '医师', 5);
INSERT INTO `doctor` VALUES ('何眼三', 22, NULL, '医师', 5);
INSERT INTO `doctor` VALUES ('吕口一', 23, '口腔科领头人', '主任医师', 6);
INSERT INTO `doctor` VALUES ('施口二', 24, '杰出青年', '医师', 6);
INSERT INTO `doctor` VALUES ('张口三', 25, NULL, '医师', 6);
INSERT INTO `doctor` VALUES ('孔心一', 26, '心血管科主任', '主任医师', 7);
INSERT INTO `doctor` VALUES ('曹心二', 27, '心血管科学科带头人', '副主任医师', 7);
INSERT INTO `doctor` VALUES ('严新三', 28, '心血管科杰出人才', '医师', 7);
INSERT INTO `doctor` VALUES ('华心四', 29, NULL, '医师', 7);
INSERT INTO `doctor` VALUES ('金心五', 30, NULL, '医师', 7);
INSERT INTO `doctor` VALUES ('谢雯杨', 31, 'LSP', '医师', 3);
INSERT INTO `doctor` VALUES ('蔡铭泽', 32, '', '主任医师', 8);
INSERT INTO `doctor` VALUES ('将野心', 33, '很爱摸鱼的医生', '副主任医师', 8);
INSERT INTO `doctor` VALUES ('江骨一', 34, '厉害的骨科医师', '医师', 9);
INSERT INTO `doctor` VALUES ('蒋放方', 35, '放射科扛把子', '主任医师', 10);
INSERT INTO `doctor` VALUES ('魏肿三', 36, '肿瘤学著名学者', '主任医师', 11);
INSERT INTO `doctor` VALUES ('王消消', 37, '消化科主任', '主任医师', 12);
INSERT INTO `doctor` VALUES ('陈二一', 38, '有爱心、耐心的好儿科医生', '主任医师', 13);
INSERT INTO `doctor` VALUES ('插入测试', 40, '这是一条插入测试语句', 'Master Dergee', 1);
INSERT INTO `doctor` VALUES ('插入2测试', 41, '这是一条2插入测试语句', 'Master2 Dergee', 2);
INSERT INTO `doctor` VALUES ('插入2测试', 42, '这是一条2插入测试语句', 'Master2 Dergee', 2);
INSERT INTO `doctor` VALUES ('HU', 43, NULL, '111', 1);
INSERT INTO `doctor` VALUES ('谢工', 44, NULL, '医师', 3);

-- ----------------------------
-- Table structure for doctorreserve
-- ----------------------------
DROP TABLE IF EXISTS `doctorreserve`;
CREATE TABLE `doctorreserve`  (
  `DoctorID` int NOT NULL,
  `DoctorSurplus` int NOT NULL,
  `ReserveDate` date NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doctorreserve
-- ----------------------------
INSERT INTO `doctorreserve` VALUES (1, 50, '2020-12-24');
INSERT INTO `doctorreserve` VALUES (2, 50, '2020-12-24');
INSERT INTO `doctorreserve` VALUES (3, 50, '2020-12-24');
INSERT INTO `doctorreserve` VALUES (4, 50, '2020-12-24');
INSERT INTO `doctorreserve` VALUES (5, 50, '2020-12-24');
INSERT INTO `doctorreserve` VALUES (1, 49, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (1, 50, '2021-01-05');
INSERT INTO `doctorreserve` VALUES (6, 49, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (7, 50, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (8, 50, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (9, 50, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (10, 50, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (11, 50, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (12, 50, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (13, 50, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (14, 50, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (15, 50, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (16, 50, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (17, 50, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (18, 50, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (19, 50, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (20, 50, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (21, 50, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (22, 50, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (23, 50, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (24, 50, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (25, 50, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (26, 50, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (27, 50, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (28, 50, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (29, 50, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (30, 50, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (31, 50, '2021-01-10');
INSERT INTO `doctorreserve` VALUES (32, 50, '2021-01-10');
INSERT INTO `doctorreserve` VALUES (33, 50, '2021-01-10');
INSERT INTO `doctorreserve` VALUES (1, 50, '2021-01-06');
INSERT INTO `doctorreserve` VALUES (1, 49, '2021-01-07');
INSERT INTO `doctorreserve` VALUES (1, 50, '2021-01-08');
INSERT INTO `doctorreserve` VALUES (1, 49, '2021-01-09');
INSERT INTO `doctorreserve` VALUES (34, 50, '2021-01-11');
INSERT INTO `doctorreserve` VALUES (35, 50, '2021-01-11');
INSERT INTO `doctorreserve` VALUES (36, 50, '2021-01-11');
INSERT INTO `doctorreserve` VALUES (37, 50, '2021-01-11');
INSERT INTO `doctorreserve` VALUES (38, 50, '2021-01-11');
INSERT INTO `doctorreserve` VALUES (40, 50, '2021-01-20');
INSERT INTO `doctorreserve` VALUES (41, 50, '2021-01-20');
INSERT INTO `doctorreserve` VALUES (42, 50, '2021-01-20');
INSERT INTO `doctorreserve` VALUES (43, 50, '2021-01-20');
INSERT INTO `doctorreserve` VALUES (1, 100, '2020-02-01');
INSERT INTO `doctorreserve` VALUES (12, 30, '2020-01-23');
INSERT INTO `doctorreserve` VALUES (44, 50, '2021-01-20');
INSERT INTO `doctorreserve` VALUES (12, 10, '2020-02-02');

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient`  (
  `PatientName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `PatientAccount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `PatientPassword` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`PatientAccount`) USING BTREE,
  INDEX `Patient_Account`(`PatientAccount`, `PatientPassword`) USING BTREE,
  CONSTRAINT `Patient_Account` FOREIGN KEY (`PatientAccount`, `PatientPassword`) REFERENCES `account` (`Account`, `Password`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of patient
-- ----------------------------
INSERT INTO `patient` VALUES ('姜业鑫', '8208180414', '123456');
INSERT INTO `patient` VALUES ('吴中', '8208180501', '654321');
INSERT INTO `patient` VALUES ('chenjuan', '8208180502', '123456');
INSERT INTO `patient` VALUES ('吴昊泽', '8208180521', '654321');
INSERT INTO `patient` VALUES ('root', 'root', '123456');

-- ----------------------------
-- Table structure for reserve
-- ----------------------------
DROP TABLE IF EXISTS `reserve`;
CREATE TABLE `reserve`  (
  `patientaccount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `doctorID` int NOT NULL,
  `reserveID` int NOT NULL AUTO_INCREMENT,
  `departmentID` int NOT NULL,
  `reserveDate` date NULL DEFAULT NULL,
  `payed` int(10) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`reserveID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 93 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reserve
-- ----------------------------
INSERT INTO `reserve` VALUES ('8208180521', 1, 86, 1, '2021-01-09', 0000000001);
INSERT INTO `reserve` VALUES ('8208180502', 1, 89, 1, '2021-01-07', 0000000000);
INSERT INTO `reserve` VALUES ('8208180521', 6, 91, 3, '2021-01-09', 0000000001);
