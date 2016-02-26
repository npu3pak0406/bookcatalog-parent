SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `bookcatalog` DEFAULT CHARACTER SET utf8 ;
USE `bookcatalog` ;

-- -----------------------------------------------------
-- Table `bookcatalog`.`author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookcatalog`.`author` (
  `authorId` INT(11) NOT NULL,
  `cteateDate` DATETIME NULL DEFAULT NULL,
  `firstName` VARCHAR(255) NULL DEFAULT NULL,
  `secondName` VARCHAR(255) NULL DEFAULT NULL,
  `createDate` DATETIME NULL DEFAULT NULL,
  `countBooks` INT(11) NULL DEFAULT NULL,
  `averageRating` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`authorId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bookcatalog`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookcatalog`.`book` (
  `bookId` INT(11) NOT NULL,
  `createDate` DATETIME NULL DEFAULT NULL,
  `isbn` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `publisher` VARCHAR(255) NULL DEFAULT NULL,
  `yearPublished` INT(11) NULL DEFAULT NULL,
  `status` VARCHAR(255) NULL DEFAULT NULL,
  `average_rating` DOUBLE NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`bookId`),
  UNIQUE INDEX `UK_fovuk07v9od42vo7ho90f9g1` (`isbn` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bookcatalog`.`booksauthor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookcatalog`.`booksauthor` (
  `bookId` INT(11) NOT NULL,
  `authorId` INT(11) NOT NULL,
  PRIMARY KEY (`bookId`, `authorId`),
  INDEX `FK_j17uralybnvgtgv31lok8xk82` (`authorId` ASC),
  CONSTRAINT `FK_j17uralybnvgtgv31lok8xk82`
    FOREIGN KEY (`authorId`)
    REFERENCES `bookcatalog`.`author` (`authorId`),
  CONSTRAINT `FK_or6li4t82n6canbf7roddym5b`
    FOREIGN KEY (`bookId`)
    REFERENCES `bookcatalog`.`book` (`bookId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bookcatalog`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookcatalog`.`review` (
  `reviewId` INT(11) NOT NULL,
  `comment` VARCHAR(255) NULL DEFAULT NULL,
  `commenterName` VARCHAR(255) NULL DEFAULT NULL,
  `createDate` DATETIME NULL DEFAULT NULL,
  `rating` INT(11) NULL DEFAULT NULL,
  `bookId` INT(11) NULL DEFAULT NULL,
  `modified` BIT(1) NULL DEFAULT NULL,
  `updatedDay` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`reviewId`),
  INDEX `FK_5vwufkwf51sge1rbx27lf74oq` (`bookId` ASC),
  CONSTRAINT `FK_5vwufkwf51sge1rbx27lf74oq`
    FOREIGN KEY (`bookId`)
    REFERENCES `bookcatalog`.`book` (`bookId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

LOCK TABLES `author` WRITE;
INSERT INTO `author` VALUES 
(1,'2011-12-20 05:00:00','Steve','McConnell','2011-02-08 00:00:00',NULL,9),
(2,'2011-12-20 05:00:00','Andrew','Hunt','2011-02-09 00:00:00',NULL,9),
(3,'2011-12-20 05:00:00','David Thomas','Thomas','2011-02-08 02:00:00',NULL,9),
(4,'2011-12-20 05:00:00','Brian W','Kernighan','2011-02-08 02:00:00',NULL,9),
(5,'2011-12-20 05:00:00','Richard','Helm','2016-02-18 00:00:00',NULL,9),
(6,'2011-12-20 05:00:00','Ralph','Johnson','2016-02-18 00:00:00',NULL,8),
(7,'2011-12-20 05:00:00','John','Vlissides','2016-02-18 00:00:00',NULL,8),
(8,'2011-12-20 05:00:00','Dennis M','Ritchie','2016-02-08 00:00:00',NULL,8),
(9,'2011-12-20 05:00:00','New Author','New Author','2016-02-24 00:00:00',NULL,8),
(10,'2011-12-20 05:00:00','Charles',' Leiserson','2016-02-08 00:00:00',NULL,8),
(11,'2011-12-20 05:00:00','Ronald L.','Rivest','2016-02-16 00:00:00',NULL,7),
(12,'2011-12-20 05:00:00','Erich','Gamma','2016-02-18 00:00:00',NULL,7),
(13,'2011-12-20 05:00:00','Martin','Fowler','2016-02-18 00:00:00',NULL,7),
(14,'2011-12-20 05:00:00','Ivan','Fedak','2016-02-18 00:00:00',NULL,7),
(15,'2011-12-20 05:00:00','Alan','Moore','2016-02-18 00:00:00',NULL,7),
(16,'2011-12-20 05:00:00','Cassandra','Clare','2016-02-18 00:00:00',NULL,6),
(17,'2011-12-20 05:00:00','Dann','Brown','2016-02-18 00:00:00',NULL,6),
(18,'2011-12-20 05:00:00','Elie','Wiesel','2016-02-18 00:00:00',NULL,6),
(19,'2011-12-20 05:00:00','Frank','Miller','2016-02-18 00:00:00',NULL,5),
(20,'2011-12-20 05:00:00','George','Eliot','2016-02-18 00:00:00',NULL,2),
(21,'2011-12-20 05:00:00','Glenn','Beck','2016-02-18 00:00:00',NULL,3),
(22,'2011-12-20 05:00:00','Henry','Miller','2016-02-18 00:00:00',NULL,4),
(23,'2011-12-20 05:00:00','Helen','Fielding','2016-02-18 00:00:00',NULL,5),
(24,'2011-12-20 05:00:00','Henrik','Ibsen','2016-02-18 00:00:00',NULL,6),
(25,'2011-12-20 05:00:00','Henry','James','2016-02-18 00:00:00',NULL,7),
(26,'2011-12-20 05:00:00','Isaac','Asimov','2016-02-18 00:00:00',NULL,0),
(27,'2011-12-20 05:00:00','James','Frey','2016-02-18 00:00:00',NULL,0),
(28,'2011-12-20 05:00:00','Jane','Austen','2016-02-18 00:00:00',NULL,0),
(29,'2011-12-20 05:00:00','Jeff','Kinney','2016-02-18 00:00:00',NULL,0),
(30,'2011-12-20 05:00:00','Lord','Byron','2016-02-18 00:00:00',NULL,0),
(31,'2011-12-20 05:00:00','Marcel','Pagnol','2016-02-18 00:00:00',NULL,0),
(32,'2011-12-20 05:00:00','Marcel','Proust','2016-02-18 00:00:00',NULL,0),
(33,'2011-12-20 05:00:00','Martin','Amis','2016-02-18 00:00:00',NULL,0),
(34,'2011-12-20 05:00:00','Michael','Grant','2016-02-18 00:00:00',NULL,0),
(35,'2011-12-20 05:00:00','Nick','Hornby','2016-02-18 00:00:00',NULL,0),
(36,'2011-12-20 05:00:00','Orhan','Pamuk','2016-02-18 00:00:00',NULL,0),
(37,'2011-12-20 05:00:00','Paul','Celan','2016-02-18 00:00:00',NULL,0),
(38,'2011-12-20 05:00:00','Rhonda','Byrne','2016-02-18 00:00:00',NULL,0),
(39,'2011-12-20 05:00:00','Robert','Crais','2016-02-18 00:00:00',NULL,0),
(40,'2011-12-20 05:00:00','Robert','Jordan','2016-02-18 00:00:00',NULL,0),
(41,'2011-12-20 05:00:00','Ruskin','Bond','2016-02-18 00:00:00',NULL,0),
(42,'2011-12-20 05:00:00','Shaun','Tann','2016-02-18 00:00:00',NULL,0),
(43,'2011-12-20 05:00:00','Kent ','Stendhal','2016-02-18 00:00:00',NULL,0),
(44,'2011-12-20 05:00:00','Suse','Grafton','2016-02-18 00:00:00',NULL,0),
(45,'2011-12-20 05:00:00','Terry','Brooks','2016-02-18 00:00:00',NULL,0),
(46,'2011-12-20 05:00:00','Tomas','Clancy','2016-02-18 00:00:00',NULL,0),
(47,'2011-12-20 05:00:00','Vikram','Seth','2016-02-18 00:00:00',NULL,0),
(48,'2011-12-20 05:00:00','William','Blake','2016-02-18 00:00:00',NULL,0),
(49,'2011-12-20 05:00:00','Zane','Grey','2016-02-18 00:00:00',NULL,0),
(50,'2011-12-20 05:00:00','Wole','Soyinka','2016-02-18 00:00:00',NULL,0);
UNLOCK TABLES;


LOCK TABLES `book` WRITE;
INSERT INTO `book` VALUES 
(1,'2011-02-01 00:00:00','078-5-3426-1622-4','Code Complete','The 5th Publisher','1990','AVAILABLE',9,NULL),
(2,'2011-02-08 02:00:00','078-5-3426-1622-2','Pragmatic Programmer','ABC Journals','1990','AVAILABLE',9,NULL),
(3,'2007-12-03 00:00:00','333-2-1232-2341-4','The C Programming Language','A M Publishers','1990','UNAVAILABLE',9,NULL),
(4,'2007-10-16 03:00:00','333-2-1232-2341-3','Introduction to algorithms','Abhinav','1990','UNAVAILABLE',9,NULL),
(5,'2012-11-12 00:00:00','978-0-2016-3361-0','Design Patterns','Academe Research Journals','1990','UNAVAILABLE',9,NULL),
(6,'2011-02-08 00:00:00','333-2-1232-2341-0','Refactoring: Improving the Design of Existing Code','Academia Publishing','1990','UNAVAILABLE',8,NULL),
(7,'2016-01-13 00:00:00','333-2-1232-2343-9','The Mythical Man-Month.','Academia Research','1990','UNAVAILABLE',8,NULL),
(8,'2016-01-05 00:00:00','333-2-1232-2343-8','The Art of Computer Programming','Academia Scholarly Journals','1990','UNAVAILABLE',8,NULL),
(9,'2016-01-13 00:00:00','333-2-1232-2343-7','Dragon Book by Aho et al','Academic and Business Research Institute','1990','UNAVAILABLE',8,NULL),
(10,'2016-01-19 00:00:00','333-2-1232-2343-6','Effective C++','Academic and Scientific Publishing','1990','UNAVAILABLE',8,NULL),
(11,'2016-02-17 00:00:00','333-2-1232-2343-5','Working Effectively with Legacy Code','Cloud Journals','1990','UNAVAILABLE',7,NULL),
(12,'2016-02-17 00:00:00','333-2-1232-2343-4','Peopleware','CSCanada','1990','UNAVAILABLE',7,NULL),
(13,'2016-02-18 00:00:00','333-2-1232-2343-3','Practices of an Agile Developer','ElmCore Journals','1990','UNAVAILABLE',7,NULL),
(14,'2016-02-12 13:16:08','333-2-1232-2343-2','How to Win Friends and Influence People','Elmer Press','1990','AVAILABLE',7,NULL),
(15,'2016-02-12 19:07:06','333-2-1232-2343-1','Agile Software Development','Excellent Publishers','1990','AVAILABLE',7,NULL),
(16,'2016-02-12 19:10:30','333-2-1232-2343-0','Thinking and Learning: Refactor Your Wetware','Global Advanced Research Journals','1990','AVAILABLE',6,NULL),
(17,'2016-02-15 11:03:13','133-2-1232-2343-4','Book','Halmac Research','1990','AVAILABLE',6,NULL),
(18,'2016-02-16 13:25:42','333-2-2232-1341-4','The Pragmatic Programmer','NewBook','1990','AVAILABLE',6,NULL),
(19,'2016-02-16 13:25:42','333-2-3232-1341-4','Continuous Delivery','ABC Journals','1990','AVAILABLE',5,NULL),
(20,'2016-02-16 13:25:42','333-2-4232-1341-4','The Clean Coder','ABC Journals','1990','AVAILABLE',2,NULL),
(21,'2016-02-16 13:25:42','333-2-5232-1341-4','Head First Design Patterns','ABC Journals','1990','AVAILABLE',3,NULL),
(22,'2016-02-16 13:25:42','333-2-6232-1341-4','NewBook','ABC Journals','1990','AVAILABLE',4,NULL),
(23,'2016-02-16 13:25:42','333-2-7232-1341-4','NewBook','ABC Journals','1990','AVAILABLE',5,NULL),
(24,'2016-02-15 11:03:13','133-2-8239-2343-4','xUnit Test Patterns','ElmCore Journals','1990','AVAILABLE',6,NULL),
(25,'2016-02-15 11:03:13','133-2-9238-2343-4','Apprenticeship Patterns','ElmCore Journals','1990','AVAILABLE',7,NULL),
(26,'2016-02-15 11:03:13','133-2-1137-2343-4','The Art of Agile Development','ElmCore Journals','1990','AVAILABLE',0,NULL),
(27,'2016-02-15 11:03:13','133-2-1236-2343-4','Honorable Mentions','ElmCore Journals','1990','AVAILABLE',0,NULL),
(28,'2016-02-15 11:03:13','133-2-1335-2343-4','Programming Pearls','ElmCore Journals','1990','AVAILABLE',0,NULL),
(29,'2016-02-15 11:03:13','133-2-1434-2343-4','The software developer’s life manual','ElmCore Journals','1990','AVAILABLE',0,NULL),
(30,'2016-02-15 11:03:13','133-2-1533-2343-4','The Timeless Way of Building','ElmCore Journals','1990','AVAILABLE',0,NULL),
(31,'2016-02-15 11:03:13','133-2-1631-2343-4','Framework Design Guidelines','ElmCore Journals','1990','AVAILABLE',0,NULL),
(32,'2016-02-15 11:03:13','133-2-1730-2343-4','Computational Beauty of Nature.','ElmCore Journals','1990','AVAILABLE',0,NULL),
(33,'2016-02-15 11:03:13','133-2-1892-2343-4','Object-Oriented Analysis','Halmac Research','1990','AVAILABLE',0,NULL),
(34,'2016-02-15 11:03:13','133-2-1982-2343-4','Effective Java','Halmac Research','1990','AVAILABLE',0,NULL),
(35,'2016-02-15 11:03:13','133-2-1272-2343-4','The Career Programmer','Halmac Research','1990','AVAILABLE',0,NULL),
(36,'2016-02-15 11:03:13','133-2-1262-2343-4','Paradigms of Artificial Intelligence Programming','Halmac Research','1990','AVAILABLE',0,NULL),
(37,'2016-02-15 11:03:13','133-2-1252-2343-4','Masters of Doom','Halmac Research','1990','AVAILABLE',0,NULL),
(38,'2016-02-15 11:03:13','133-2-1242-2343-4','The Language and its Implementation','Halmac Research','1990','AVAILABLE',0,NULL),
(39,'2016-02-15 11:03:13','133-2-1222-2343-4','Big Ideas from the Computer Ag','Halmac Research','1990','AVAILABLE',0,NULL),
(40,'2016-02-15 11:03:13','133-2-1212-2343-4','The Tao of Programming','Halmac Research','1990','AVAILABLE',0,NULL);
UNLOCK TABLES;

LOCK TABLES `booksauthor` WRITE;
INSERT INTO `booksauthor` VALUES 
(1,1),
(2,2),
(3,3),
(4,4),
(5,5),
(6,6),
(7,7),
(8,8),
(9,9),
(10,10),
(11,11),
(12,12),
(13,13),
(14,14),
(15,15),
(16,16),
(17,17),
(18,18),
(19,19),
(20,20),
(21,21),
(22,22),
(23,23),
(24,24),
(25,25),
(26,26),
(27,27),
(28,28),
(29,29),
(30,30),
(31,31),
(32,32),
(33,33),
(34,34),
(35,35),
(36,36),
(37,37),
(38,38),
(38,39),
(40,40);

UNLOCK TABLES;


LOCK TABLES `review` WRITE;
INSERT INTO `review` VALUES 
(1,'Nice','GoodBook','2016-02-11 17:49:09',9,1,'','2016-02-15 18:16:05'),
(2,'Nice','GoodBook','2016-02-11 17:50:07',9,2,'','2016-02-15 18:16:27'),
(3,'Nice','GoodBook','2016-02-11 18:24:23',9,3,'','2016-02-11 18:24:50'),
(4,'Nice','GoodBook','2016-02-11 18:58:58',9,4,'','2016-02-15 18:15:54'),
(5,'Nice','GoodBook','2016-02-12 13:16:22',9,5,'','2016-02-12 14:26:28'),
(6,'Nice','GoodBook','2016-02-12 13:19:06',8,6,'\0',NULL),
(7,'Nice','GoodBook','2016-02-12 13:25:26',8,7,'\0',NULL),
(8,'Nice','GoodBook','2016-02-12 17:40:02',8,8,'','2016-02-15 10:51:44'),
(9,'Nice','GoodBook','2016-02-12 17:43:47',8,9,'','2016-02-15 10:51:50'),
(10,'Nice','GoodBook','2016-02-12 17:46:16',8,10,'','2016-02-15 10:52:07'),
(11,'Nice','GoodBook','2016-02-12 17:46:54',7,11,'','2016-02-15 10:48:29'),
(12,'Nice','GoodBook','2016-02-12 17:48:32',7,12,'\0',NULL),
(13,'Nice','GoodBook','2016-02-15 11:04:53',7,13,'\0',NULL),
(14,'Nice','GoodBook','2016-02-15 11:05:25',7,14,'\0',NULL),
(15,'Nice','GoodBook','2016-02-15 11:06:44',7,15,'\0',NULL),
(16,'Nice','GoodBook','2016-02-15 14:23:58',6,16,'\0',NULL),
(17,'Nice','GoodBook','2016-02-15 14:23:58',6,17,'\0',NULL),
(18,'Nice','GoodBook','2016-02-15 14:23:58',6,18,'\0',NULL),
(19,'Nice','GoodBook','2016-02-15 14:23:58',5,19,'\0',NULL),
(20,'Nice','GoodBook','2016-02-15 14:23:58',2,20,'\0',NULL),
(21,'Nice','GoodBook','2016-02-15 14:23:58',3,21,'\0',NULL),
(22,'Nice','GoodBook','2016-02-15 14:23:58',4,22,'\0',NULL),
(23,'Nice','GoodBook','2016-02-15 14:23:58',5,23,'\0',NULL),
(24,'Nice','GoodBook','2016-02-15 14:23:58',6,24,'\0',NULL),
(25,'Nice','GoodBook','2016-02-15 14:23:58',7,25,'\0',NULL);
UNLOCK TABLES;


