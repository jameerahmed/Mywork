-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               5.5.50 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for mywork
CREATE DATABASE IF NOT EXISTS `mywork` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `mywork`;


-- Dumping structure for table mywork.configuration
CREATE TABLE IF NOT EXISTS `configuration` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(400) DEFAULT NULL,
  `value` varchar(400) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `createdBy` int(10) unsigned DEFAULT NULL,
  `updatedBy` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table mywork.configuration: ~1 rows (approximately)
DELETE FROM `configuration`;
/*!40000 ALTER TABLE `configuration` DISABLE KEYS */;
INSERT INTO `configuration` (`id`, `name`, `value`, `createdDate`, `updatedDate`, `createdBy`, `updatedBy`) VALUES
	(1, 'uploadPhotoPath', 'G:\\myUploadFiles\\images', NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `configuration` ENABLE KEYS */;


-- Dumping structure for table mywork.persons
CREATE TABLE IF NOT EXISTS `persons` (
  `PersonID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `LastName` varchar(255) DEFAULT NULL,
  `FirstName` varchar(255) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `City` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PersonID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table mywork.persons: ~1 rows (approximately)
DELETE FROM `persons`;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
INSERT INTO `persons` (`PersonID`, `LastName`, `FirstName`, `Address`, `City`) VALUES
	(1, 'Mujawar', 'zameer', 'Pune', 'Pune');
/*!40000 ALTER TABLE `persons` ENABLE KEYS */;


-- Dumping structure for table mywork.productmaster
CREATE TABLE IF NOT EXISTS `productmaster` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `createdBy` int(10) unsigned DEFAULT NULL,
  `updatedBy` int(10) unsigned DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Dumping data for table mywork.productmaster: ~8 rows (approximately)
DELETE FROM `productmaster`;
/*!40000 ALTER TABLE `productmaster` DISABLE KEYS */;
INSERT INTO `productmaster` (`id`, `name`, `description`, `createdBy`, `updatedBy`, `createdDate`, `updatedDate`) VALUES
	(1, 'Star', 'NA', NULL, NULL, NULL, NULL),
	(2, 'SG', 'NAas', NULL, NULL, NULL, NULL),
	(3, 'RMD', 'TT', NULL, NULL, NULL, NULL),
	(4, 'Five Star', '', NULL, NULL, NULL, NULL),
	(5, 'Six StarEE', 'Test', NULL, NULL, NULL, NULL),
	(6, 'Clinic Plus', 'Test', NULL, NULL, NULL, NULL),
	(7, 'FirstWestR', 'First Desc', NULL, NULL, NULL, NULL),
	(8, 'Test', 'aa', NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `productmaster` ENABLE KEYS */;


-- Dumping structure for table mywork.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `createdBy` int(10) unsigned DEFAULT NULL,
  `updatedBy` int(10) unsigned DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- Dumping data for table mywork.role: ~10 rows (approximately)
DELETE FROM `role`;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `name`, `createdBy`, `updatedBy`, `createdDate`, `updatedDate`) VALUES
	(2, 'Customer', 1, 1, NULL, NULL),
	(3, 'Admin', NULL, NULL, NULL, NULL),
	(4, 'Volunteer', NULL, NULL, NULL, NULL),
	(5, 'Distributor', NULL, NULL, NULL, NULL),
	(6, 'First', NULL, NULL, NULL, NULL),
	(7, 'asdf', NULL, NULL, NULL, NULL),
	(8, 'Zy', NULL, NULL, NULL, NULL),
	(9, 'Admin2', NULL, NULL, NULL, NULL),
	(10, 'TRdasf', NULL, NULL, NULL, NULL),
	(11, 'TRdasfdasf', NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;


-- Dumping structure for table mywork._user
CREATE TABLE IF NOT EXISTS `_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `password` varchar(400) DEFAULT NULL,
  `emailId` varchar(45) DEFAULT NULL,
  `mobileNumber` longtext,
  `state` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `pinCode` varchar(45) DEFAULT NULL,
  `address` varchar(400) DEFAULT NULL,
  `address2` varchar(400) DEFAULT NULL,
  `userType` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `isLoggedIn` tinyint(1) DEFAULT NULL,
  `createdBy` int(10) unsigned DEFAULT NULL,
  `updatedBy` int(10) unsigned DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `ipAddress` varchar(400) DEFAULT NULL,
  `brower` varchar(400) DEFAULT NULL,
  `browerVersion` varchar(400) DEFAULT NULL,
  `roleId` int(10) unsigned NOT NULL DEFAULT '0',
  `uploadedLogo` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK__user_1` (`roleId`),
  CONSTRAINT `FK__user_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1;

-- Dumping data for table mywork._user: ~36 rows (approximately)
DELETE FROM `_user`;
/*!40000 ALTER TABLE `_user` DISABLE KEYS */;
INSERT INTO `_user` (`id`, `firstName`, `lastName`, `password`, `emailId`, `mobileNumber`, `state`, `city`, `pinCode`, `address`, `address2`, `userType`, `status`, `isLoggedIn`, `createdBy`, `updatedBy`, `createdDate`, `updatedDate`, `ipAddress`, `brower`, `browerVersion`, `roleId`, `uploadedLogo`) VALUES
	(6, 'zameer', 'Qadri', 'zameerahmed', 'zameerahmed@test.com', '9175813341', 'Karnataka', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(7, 'sameerahmed', 'Mujawar', 'ac922c899671312771c7cfe0135b892c', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, NULL),
	(8, 'za', 'Mujawar', '959848ca10cc8a60da818ac11523dc63', 'za@t.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, NULL),
	(9, 'Ameerahmed', 'Mujawar', '10c5f1cbafdcbb4cdabba9c5f555fb3d', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(10, 'zabair', 'Mujawar', '10c5f1cbafdcbb4cdabba9c5f555fb3d', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(11, 'tohid', 'Mujawar', '10c5f1cbafdcbb4cdabba9c5f555fb3d', 'touhid@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(12, 'Abu', 'Mujawar', '10c5f1cbafdcbb4cdabba9c5f555fb3d', 'Abu@test.com', '9075813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(13, 'Nisaar', 'Mujawar', '10c5f1cbafdcbb4cdabba9c5f555fb3d', 'Nisar@test.com', '9075813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(14, 'Noor', 'Mujawar', '10c5f1cbafdcbb4cdabba9c5f555fb3d', 'Noot@test.com', '9075000001', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(15, 'Maha Noor', 'Shaikh', '10c5f1cbafdcbb4cdabba9c5f555fb3d', 'mhNoot@test.com', '8475000001', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(16, 'Saad', 'Shaikh', '10c5f1cbafdcbb4cdabba9c5f555fb3d', 'saad@test.com', '8475000001', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(17, 'Seed', 'Shaikh', '10c5f1cbafdcbb4cdabba9c5f555fb3d', 'seed@test.com', '8475000001', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(18, 'Norain', 'Patel', '10c5f1cbafdcbb4cdabba9c5f555fb3d', 'noorain@test.com', '7775000001', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(19, 'sameerahmed', 'Mujawar', 'ac922c899671312771c7cfe0135b892c', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(20, 'sameerahmed', 'Mujawar', 'ac922c899671312771c7cfe0135b892c', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(21, 'sameerahmed', 'Mujawar', 'ac922c899671312771c7cfe0135b892c', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(22, 'sameerahmed', 'Mujawar', 'ac922c899671312771c7cfe0135b892c', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(23, 'sameerahmed', 'Mujawar', 'ac922c899671312771c7cfe0135b892c', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(24, 'sameerahmed', 'Mujawar', 'ac922c899671312771c7cfe0135b892c', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(25, 'sameerahmed', 'Mujawar', 'ac922c899671312771c7cfe0135b892c', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(26, 'sameerahmed', 'Mujawar', 'ac922c899671312771c7cfe0135b892c', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(27, 'sameerahmed', 'Mujawar', 'ac922c899671312771c7cfe0135b892c', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(28, 'sameerahmed', 'Mujawar', 'ac922c899671312771c7cfe0135b892c', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(29, 'sameerahmed', 'Mujawar', 'ac922c899671312771c7cfe0135b892c', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(30, 'sameerahmed', 'Mujawar', 'ac922c899671312771c7cfe0135b892c', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(31, 'sameerahmed', 'Mujawar', 'ac922c899671312771c7cfe0135b892c', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(32, 'sameerahmed', 'Mujawar', 'ac922c899671312771c7cfe0135b892c', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(33, 'sameerahmed', 'Mujawar', 'ac922c899671312771c7cfe0135b892c', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(34, 'sameerahmed', 'Mujawar', 'ac922c899671312771c7cfe0135b892c', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(35, 'sameerahmed', 'Mujawar', 'ac922c899671312771c7cfe0135b892c', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(36, 'sameerahmed', 'Mujawar', 'ac922c899671312771c7cfe0135b892c', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(37, 'sameerahmed', 'Mujawar', 'ac922c899671312771c7cfe0135b892c', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(38, 'sameerahmed', 'Mujawar', 'ac922c899671312771c7cfe0135b892c', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(39, 'sameerahmed', 'Mujawar', 'ac922c899671312771c7cfe0135b892c', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(40, 'sameerahmed', 'Mujawar', 'ac922c899671312771c7cfe0135b892c', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL),
	(41, 'sameerahmed', 'Mujawar', 'ac922c899671312771c7cfe0135b892c', 'sameerahmed@test.com', '9175813341', 'Mah', 'Pune', NULL, 'Jugul', NULL, NULL, 'Active', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL);
/*!40000 ALTER TABLE `_user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
