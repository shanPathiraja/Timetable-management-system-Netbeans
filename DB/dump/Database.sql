CREATE DATABASE  IF NOT EXISTS `tms2` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `tms2`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tms2
-- ------------------------------------------------------
-- Server version	5.6.4-m7

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `_course_resolution`
--

DROP TABLE IF EXISTS `_course_resolution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `_course_resolution` (
  `project_ID` bigint(10) NOT NULL COMMENT 'This is the uique project ID',
  `courseA_code` varchar(253) COLLATE latin1_general_ci NOT NULL COMMENT 'This is the Code for corse A',
  `courseA_ID` bigint(10) NOT NULL COMMENT 'ID for course A',
  `courseB_code` varchar(253) COLLATE latin1_general_ci NOT NULL COMMENT 'Code for Course B',
  `courseB_ID` bigint(10) NOT NULL COMMENT 'Id for course B',
  `resolution_ID` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'Unique ID for refering to a resolution',
  PRIMARY KEY (`project_ID`,`resolution_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_course_resolution`
--

LOCK TABLES `_course_resolution` WRITE;
/*!40000 ALTER TABLE `_course_resolution` DISABLE KEYS */;
/*!40000 ALTER TABLE `_course_resolution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_day_defect`
--

DROP TABLE IF EXISTS `_day_defect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `_day_defect` (
  `day` varchar(253) COLLATE latin1_general_ci NOT NULL,
  `defect` varchar(253) COLLATE latin1_general_ci DEFAULT 'no',
  `dayRep` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`day`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_day_defect`
--

LOCK TABLES `_day_defect` WRITE;
/*!40000 ALTER TABLE `_day_defect` DISABLE KEYS */;
/*!40000 ALTER TABLE `_day_defect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_department_detail`
--

DROP TABLE IF EXISTS `_department_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `_department_detail` (
  `project_ID` bigint(10) NOT NULL COMMENT 'This is the unique Id assigned to a project',
  `department_ID` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'This is a unique ID given to each department create',
  `department_name` varchar(253) COLLATE latin1_general_ci NOT NULL COMMENT 'This is the name of the department',
  `department_faculty` varchar(253) COLLATE latin1_general_ci NOT NULL COMMENT 'this is the faculty to which the department Belongs',
  PRIMARY KEY (`project_ID`,`department_ID`),
  UNIQUE KEY `department_ID` (`department_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_department_detail`
--

LOCK TABLES `_department_detail` WRITE;
/*!40000 ALTER TABLE `_department_detail` DISABLE KEYS */;
INSERT INTO `_department_detail` VALUES (5,8,'aaa','aaa');
/*!40000 ALTER TABLE `_department_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_final_table`
--

DROP TABLE IF EXISTS `_final_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `_final_table` (
  `day` varchar(253) COLLATE latin1_general_ci NOT NULL COMMENT 'This is the day a course is allocated to',
  `day_ID` bigint(10) NOT NULL COMMENT 'dayID, unique to all days',
  `hall_name` varchar(253) COLLATE latin1_general_ci NOT NULL COMMENT 'The Name of the hall the course is assigned to',
  `hall_ID` bigint(10) NOT NULL COMMENT 'this is the id of the hall',
  `period1` text COLLATE latin1_general_ci COMMENT 'courseID',
  `period2` text COLLATE latin1_general_ci COMMENT 'courseID',
  `period3` text COLLATE latin1_general_ci COMMENT 'courseID',
  `period4` text COLLATE latin1_general_ci COMMENT 'courseID',
  `period5` text COLLATE latin1_general_ci COMMENT 'courseID',
  `period6` text COLLATE latin1_general_ci COMMENT 'courseID',
  `period7` text COLLATE latin1_general_ci COMMENT 'courseID',
  `period8` text COLLATE latin1_general_ci COMMENT 'courseID',
  `period9` text COLLATE latin1_general_ci COMMENT 'courseID',
  `period10` text COLLATE latin1_general_ci COMMENT 'courseID',
  PRIMARY KEY (`day`,`day_ID`,`hall_name`,`hall_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_final_table`
--

LOCK TABLES `_final_table` WRITE;
/*!40000 ALTER TABLE `_final_table` DISABLE KEYS */;
INSERT INTO `_final_table` VALUES ('monday',0,'aaaa',7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('tuesday',1,'aaaa',7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('wednesday',2,'aaaa',7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('thursday',3,'aaaa',7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('friday',4,'aaaa',7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `_final_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_lecture_hall_detail`
--

DROP TABLE IF EXISTS `_lecture_hall_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `_lecture_hall_detail` (
  `project_ID` bigint(10) NOT NULL COMMENT 'This is the IDof the project this hall belongs to',
  `lecture_hall_name` varchar(253) COLLATE latin1_general_ci NOT NULL COMMENT 'This is the name of the hall',
  `lecture_hall_ID` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'This is the unique ID that this hall posses',
  `lecture_hall_capacity` bigint(10) DEFAULT NULL COMMENT 'This is the capacity of the lecture hall',
  `lecture_hall_defects` varchar(253) COLLATE latin1_general_ci NOT NULL DEFAULT 'no' COMMENT 'This holds information on Allocation that helps know defective halls',
  PRIMARY KEY (`lecture_hall_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_lecture_hall_detail`
--

LOCK TABLES `_lecture_hall_detail` WRITE;
/*!40000 ALTER TABLE `_lecture_hall_detail` DISABLE KEYS */;
INSERT INTO `_lecture_hall_detail` VALUES (5,'aaaa',7,1000,'no');
/*!40000 ALTER TABLE `_lecture_hall_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_lecturers_detail`
--

DROP TABLE IF EXISTS `_lecturers_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `_lecturers_detail` (
  `project_ID` bigint(10) NOT NULL COMMENT 'this is a unique id to the current project this lecturer resides in',
  `lecturer_ID` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'This is the ID for the current Lecturer',
  `lecturer_name` varchar(253) COLLATE latin1_general_ci NOT NULL COMMENT 'This is the name of the lecturer',
  `lecturer_number_courses` bigint(10) NOT NULL DEFAULT '0' COMMENT 'This is the number of courses the lecturer is taking',
  `lecturer_courses_IDs` text COLLATE latin1_general_ci COMMENT 'this is a text delimited by containing various id of courses the lecturer is taking',
  PRIMARY KEY (`project_ID`,`lecturer_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_lecturers_detail`
--

LOCK TABLES `_lecturers_detail` WRITE;
/*!40000 ALTER TABLE `_lecturers_detail` DISABLE KEYS */;
INSERT INTO `_lecturers_detail` VALUES (5,1,'aaaa',0,NULL),(5,2,'bbb',0,NULL);
/*!40000 ALTER TABLE `_lecturers_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_projects_details`
--

DROP TABLE IF EXISTS `_projects_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `_projects_details` (
  `project_description` varchar(253) COLLATE latin1_general_ci NOT NULL COMMENT 'This is the displayed title of the project',
  `project_ID` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'This is the unique id assigned to this project',
  PRIMARY KEY (`project_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_projects_details`
--

LOCK TABLES `_projects_details` WRITE;
/*!40000 ALTER TABLE `_projects_details` DISABLE KEYS */;
INSERT INTO `_projects_details` VALUES ('shan',5);
/*!40000 ALTER TABLE `_projects_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aaaaaa`
--

DROP TABLE IF EXISTS `aaaaaa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aaaaaa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `morning` varchar(255) DEFAULT NULL,
  `evining` varchar(255) DEFAULT NULL,
  `Competible_hall` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aaaaaa`
--

LOCK TABLES `aaaaaa` WRITE;
/*!40000 ALTER TABLE `aaaaaa` DISABLE KEYS */;
/*!40000 ALTER TABLE `aaaaaa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bbbbbb`
--

DROP TABLE IF EXISTS `bbbbbb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bbbbbb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `morning` varchar(255) DEFAULT NULL,
  `evining` varchar(255) DEFAULT NULL,
  `Competible_hall` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bbbbbb`
--

LOCK TABLES `bbbbbb` WRITE;
/*!40000 ALTER TABLE `bbbbbb` DISABLE KEYS */;
/*!40000 ALTER TABLE `bbbbbb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cccccc`
--

DROP TABLE IF EXISTS `cccccc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cccccc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `morning` varchar(255) DEFAULT NULL,
  `evining` varchar(255) DEFAULT NULL,
  `Competible_hall` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cccccc`
--

LOCK TABLES `cccccc` WRITE;
/*!40000 ALTER TABLE `cccccc` DISABLE KEYS */;
INSERT INTO `cccccc` VALUES (1,'IT0002-java',NULL,NULL,'IT0002-java'),(2,NULL,NULL,NULL,NULL),(3,NULL,NULL,NULL,NULL),(4,'IT0004-python',NULL,NULL,'IT0004-python'),(5,NULL,NULL,NULL,NULL),(6,NULL,NULL,NULL,NULL),(7,NULL,NULL,NULL,NULL),(8,NULL,'IT0005-project management',NULL,NULL);
/*!40000 ALTER TABLE `cccccc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `course_ID` bigint(10) NOT NULL AUTO_INCREMENT COMMENT 'This is a unique id this course belongs to',
  `course_code` varchar(253) COLLATE latin1_general_ci NOT NULL COMMENT 'This is the course code, that has been assigned to the course ',
  `course_name` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `course_title` varchar(253) COLLATE latin1_general_ci NOT NULL COMMENT 'This is the title of the course',
  `number_Of_Student` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `course_department_ID` varchar(255) COLLATE latin1_general_ci DEFAULT NULL COMMENT 'This is a unique department ID a course belongs to',
  PRIMARY KEY (`course_ID`),
  UNIQUE KEY `course_ID` (`course_ID`,`course_department_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (14,'c1','course','dadada','100','dep1'),(16,'c3','course','adada','11','dep3'),(17,'c2','account','account and financial','20','d4');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `dep_ID` int(11) NOT NULL AUTO_INCREMENT,
  `dep_Code` varchar(255) NOT NULL,
  `dep_Name` varchar(255) NOT NULL,
  PRIMARY KEY (`dep_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'dep1','department1'),(7,'dep2','department2'),(8,'dep3','department3'),(11,'dep4','department4');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eeeee`
--

DROP TABLE IF EXISTS `eeeee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eeeee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `morning` varchar(255) DEFAULT NULL,
  `evining` varchar(255) DEFAULT NULL,
  `Competible_hall` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eeeee`
--

LOCK TABLES `eeeee` WRITE;
/*!40000 ALTER TABLE `eeeee` DISABLE KEYS */;
INSERT INTO `eeeee` VALUES (1,'1-11-2019   -  Friday','IT0002-java',NULL,NULL),(2,NULL,NULL,NULL,NULL),(3,NULL,NULL,NULL,NULL),(4,'4-11-2019   -  Monday','IT0004-python',NULL,NULL),(5,'5-11-2019   -  Tuesday',NULL,NULL,NULL),(6,'6-11-2019   -  Wednesday',NULL,NULL,NULL),(7,'7-11-2019   -  Thursday',NULL,'IT0006-c#',NULL),(8,'8-11-2019   -  Friday',NULL,NULL,NULL),(9,NULL,NULL,NULL,NULL),(10,NULL,NULL,'IT0008-english',NULL),(11,'11-11-2019   -  Monday',NULL,NULL,NULL),(12,'12-11-2019   -  Tuesday',NULL,NULL,NULL),(13,'13-11-2019   -  Wednesday',NULL,NULL,NULL),(14,'14-11-2019   -  Thursday','IT0005-project management',NULL,NULL),(15,'15-11-2019   -  Friday',NULL,NULL,NULL),(16,NULL,NULL,NULL,NULL),(17,NULL,NULL,NULL,NULL),(18,'18-11-2019   -  Monday',NULL,'IT0007-object oriented analysing and desinging',NULL),(19,'19-11-2019   -  Tuesday',NULL,NULL,NULL),(20,'20-11-2019   -  Wednesday',NULL,NULL,NULL),(21,'21-11-2019   -  Thursday',NULL,NULL,NULL);
/*!40000 ALTER TABLE `eeeee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `final_table`
--

DROP TABLE IF EXISTS `final_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `final_table` (
  `day` varchar(255) DEFAULT NULL,
  `dayID` varchar(255) DEFAULT NULL,
  `hall_name` varchar(255) DEFAULT NULL,
  `hall_Code` varchar(255) DEFAULT NULL,
  `Morning` varchar(255) DEFAULT NULL,
  `Evining` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `final_table`
--

LOCK TABLES `final_table` WRITE;
/*!40000 ALTER TABLE `final_table` DISABLE KEYS */;
INSERT INTO `final_table` VALUES ('monday','1',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `final_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hall`
--

DROP TABLE IF EXISTS `hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hall` (
  `hall_id` int(11) NOT NULL AUTO_INCREMENT,
  `hall_code` varchar(255) NOT NULL,
  `hall_name` varchar(255) DEFAULT NULL,
  `Capacity` int(11) DEFAULT NULL,
  PRIMARY KEY (`hall_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hall`
--

LOCK TABLES `hall` WRITE;
/*!40000 ALTER TABLE `hall` DISABLE KEYS */;
INSERT INTO `hall` VALUES (1,'h1','hall1',50),(2,'h2','hall2',25),(3,'h3','hall3',100),(4,'h4','hall4',150);
/*!40000 ALTER TABLE `hall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invigilators`
--

DROP TABLE IF EXISTS `invigilators`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invigilators` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `invigilator_id` varchar(255) DEFAULT NULL,
  `invigilator_Name` varchar(255) DEFAULT NULL,
  `Tell_no` varchar(255) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invigilators`
--

LOCK TABLES `invigilators` WRITE;
/*!40000 ALTER TABLE `invigilators` DISABLE KEYS */;
INSERT INTO `invigilators` VALUES (1,'INV0001','Shan ','0712345678','ldjalkjdlakjdlkaj'),(2,'INV0002','abc','0771234567','dadadada'),(3,'INV0002','abc','0771234567','dadadada'),(4,'','','','');
/*!40000 ALTER TABLE `invigilators` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `model`
--

DROP TABLE IF EXISTS `model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `model` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `morning` varchar(255) DEFAULT NULL,
  `evining` varchar(255) DEFAULT NULL,
  `Competible_hall` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model`
--

LOCK TABLES `model` WRITE;
/*!40000 ALTER TABLE `model` DISABLE KEYS */;
/*!40000 ALTER TABLE `model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `model1`
--

DROP TABLE IF EXISTS `model1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `model1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `morning` varchar(255) DEFAULT NULL,
  `evining` varchar(255) DEFAULT NULL,
  `Competible_hall` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model1`
--

LOCK TABLES `model1` WRITE;
/*!40000 ALTER TABLE `model1` DISABLE KEYS */;
/*!40000 ALTER TABLE `model1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `subject_id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_Code` varchar(255) DEFAULT NULL,
  `subject_Name` varchar(255) DEFAULT NULL,
  `course_code` varchar(255) DEFAULT NULL,
  `credite` float DEFAULT NULL,
  `exame_duration` float DEFAULT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'IT0001','c++','c3',5,4),(2,'IT0005','project management','c1',4,2),(3,'IT0002','java','c1',3,3),(4,'IT0004','python','c1',3,1),(5,'IT0006','c#','c1',3,3),(6,'IT0007','object oriented analysing and desinging','c1',4,5),(7,'IT0008','english','c1',3,2),(8,'IT0009','Android','c1',5,3);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table1574838696650`
--

DROP TABLE IF EXISTS `table1574838696650`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `table1574838696650` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `morning` varchar(255) DEFAULT NULL,
  `evining` varchar(255) DEFAULT NULL,
  `Competible_hall` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table1574838696650`
--

LOCK TABLES `table1574838696650` WRITE;
/*!40000 ALTER TABLE `table1574838696650` DISABLE KEYS */;
INSERT INTO `table1574838696650` VALUES (1,'28-11-2019   -  Thursday','IT0002-java',NULL,'h3-hall3  /  h4-hall4  /  '),(2,'29-11-2019   -  Friday',NULL,NULL,NULL),(3,NULL,NULL,NULL,NULL),(4,NULL,NULL,NULL,NULL),(5,'2-12-2019   -  Monday',NULL,NULL,NULL),(6,'3-12-2019   -  Tuesday','IT0004-python',NULL,'h3-hall3  /  h4-hall4  /  '),(7,'4-12-2019   -  Wednesday',NULL,NULL,NULL),(8,'5-12-2019   -  Thursday',NULL,NULL,NULL),(9,'6-12-2019   -  Friday',NULL,'IT0006-c#','h3-hall3  /  h4-hall4  /  '),(10,NULL,NULL,NULL,NULL),(11,NULL,NULL,NULL,NULL),(12,'9-12-2019   -  Monday',NULL,NULL,NULL),(13,'10-12-2019   -  Tuesday',NULL,NULL,NULL),(14,'11-12-2019   -  Wednesday',NULL,NULL,NULL),(15,'12-12-2019   -  Thursday','IT0008-english',NULL,'h3-hall3  /  h4-hall4  /  '),(16,'13-12-2019   -  Friday',NULL,NULL,NULL),(17,NULL,NULL,NULL,NULL),(18,NULL,NULL,NULL,NULL),(19,'16-12-2019   -  Monday',NULL,NULL,NULL),(20,'17-12-2019   -  Tuesday',NULL,NULL,NULL),(21,'18-12-2019   -  Wednesday',NULL,'IT0005-project management','h3-hall3  /  h4-hall4  /  '),(22,'19-12-2019   -  Thursday',NULL,NULL,NULL),(23,'20-12-2019   -  Friday',NULL,NULL,NULL),(24,NULL,NULL,NULL,NULL),(25,NULL,NULL,NULL,NULL),(26,'23-12-2019   -  Monday',NULL,NULL,NULL),(27,'24-12-2019   -  Tuesday',NULL,'IT0007-object oriented analysing and desinging','h3-hall3  /  h4-hall4  /  '),(28,'25-12-2019   -  Wednesday',NULL,NULL,NULL),(29,'26-12-2019   -  Thursday',NULL,NULL,NULL),(30,'27-12-2019   -  Friday',NULL,NULL,NULL),(31,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `table1574838696650` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timetabledirectry`
--

DROP TABLE IF EXISTS `timetabledirectry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `timetabledirectry` (
  `Table_Id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `Table_Code` varchar(255) DEFAULT NULL,
  `Table_Name` varchar(255) DEFAULT NULL,
  `noINV` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Table_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timetabledirectry`
--

LOCK TABLES `timetabledirectry` WRITE;
/*!40000 ALTER TABLE `timetabledirectry` DISABLE KEYS */;
INSERT INTO `timetabledirectry` VALUES (11,'2019-11-27 12:41:36','table1574838696650','','4');
/*!40000 ALTER TABLE `timetabledirectry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `create_date` date DEFAULT NULL,
  `privilage` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=8192;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (3,'s','123','2019-11-26','Admin'),(5,'shan','1234','2019-11-26','Admin');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-27 18:58:31
