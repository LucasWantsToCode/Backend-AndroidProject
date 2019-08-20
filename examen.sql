-- MySQL dump 10.16  Distrib 10.1.41-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: examen
-- ------------------------------------------------------
-- Server version	10.1.41-MariaDB-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) DEFAULT NULL,
  `off_id` int(11) DEFAULT NULL,
  `dem_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `off_id` (`off_id`),
  KEY `dem_id` (`dem_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`off_id`) REFERENCES `offre` (`id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`dem_id`) REFERENCES `demandeur` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'test',1,1),(2,'uguyg',1,1),(3,'fytfuggy',12,1),(4,'sgf',30,1),(5,'yfytf',28,1),(6,'hello',27,1),(7,'uygfufgiezuf',2,12);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cv`
--

DROP TABLE IF EXISTS `cv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cv` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `formation` varchar(255) DEFAULT NULL,
  `competences` varchar(255) DEFAULT NULL,
  `dem_id` int(11) DEFAULT NULL,
  `dom_id` int(11) DEFAULT NULL,
  `passion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dem_id` (`dem_id`),
  KEY `dom_id` (`dom_id`),
  CONSTRAINT `cv_ibfk_1` FOREIGN KEY (`dem_id`) REFERENCES `demandeur` (`id`),
  CONSTRAINT `cv_ibfk_2` FOREIGN KEY (`dom_id`) REFERENCES `domaine` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cv`
--

LOCK TABLES `cv` WRITE;
/*!40000 ALTER TABLE `cv` DISABLE KEYS */;
INSERT INTO `cv` VALUES (1,'hacking','hacking',1,1,'mma'),(2,'form','commm',1,1,'pass'),(4,'form','commm',8,1,'pass'),(5,'form','commm',9,1,'pass'),(6,'design','',11,1,''),(7,'uyguygu','ygyugyu',12,1,'tytfy');
/*!40000 ALTER TABLE `cv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `demandeur`
--

DROP TABLE IF EXISTS `demandeur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `demandeur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `demandeur`
--

LOCK TABLES `demandeur` WRITE;
/*!40000 ALTER TABLE `demandeur` DISABLE KEYS */;
INSERT INTO `demandeur` VALUES (1,'assaka','jordy','stevonlucas@gmail.com','passer'),(2,'\"nom\"','\"prenom\"','\"email\"','\"password;'),(3,'ytdyty','hfufyfuy','uyfyfyuf@ufu','ufyfuyf'),(4,'assaka','jordy','stev@l.c','passer'),(5,'assaka','jordy','st@l.c','passer'),(6,'yugi','iuiu','t@h.c','passer'),(7,'test','luc','k@f.c','passer'),(8,'yfytf','ytfytfyt','d@j.c','passer'),(9,'Boss','lucas','bl@g.c','passer'),(10,'balloa','loo','lo@ba.com','passer'),(11,'ytfyytfy','tfyfytf','ytfytfytf','ytfyfytfytfytf'),(12,'ytfytf','ftyufytf','ytfytfytfyt','f');
/*!40000 ALTER TABLE `demandeur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `domaine`
--

DROP TABLE IF EXISTS `domaine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `domaine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `domaine`
--

LOCK TABLES `domaine` WRITE;
/*!40000 ALTER TABLE `domaine` DISABLE KEYS */;
INSERT INTO `domaine` VALUES (1,'Informatique'),(2,'Medecine'),(3,'Juridique'),(4,'Chimique'),(5,'Design');
/*!40000 ALTER TABLE `domaine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entreprise`
--

DROP TABLE IF EXISTS `entreprise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entreprise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entreprise`
--

LOCK TABLES `entreprise` WRITE;
/*!40000 ALTER TABLE `entreprise` DISABLE KEYS */;
INSERT INTO `entreprise` VALUES (1,'ISI','isi@isi.s','passer'),(2,'ISI','isi@isi.snk','passer'),(3,'ISI','isi@isi.skn','passer'),(4,'ISI','isi@isi.sikn','passer'),(5,'ISI','isi@isif.sn','passer'),(6,'boss','isi@isi.so','passer'),(7,'ISI','isi@isi.sun','passer'),(8,'ISI','misi@isi.sn','passer'),(9,'ISI','isi@isik.sn','passer'),(10,'ISI','isi@isi.sen','passer'),(11,'ISI','isi@isi.san','passer'),(12,'ISI','isi@isi.sion','passer'),(13,'DDD','ddd@support.com','passer');
/*!40000 ALTER TABLE `entreprise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorites`
--

DROP TABLE IF EXISTS `favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorites` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dem_id` int(11) DEFAULT NULL,
  `off_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dem_id` (`dem_id`),
  KEY `off_id` (`off_id`),
  CONSTRAINT `favorites_ibfk_1` FOREIGN KEY (`dem_id`) REFERENCES `demandeur` (`id`),
  CONSTRAINT `favorites_ibfk_2` FOREIGN KEY (`off_id`) REFERENCES `offre` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorites`
--

LOCK TABLES `favorites` WRITE;
/*!40000 ALTER TABLE `favorites` DISABLE KEYS */;
INSERT INTO `favorites` VALUES (1,1,1),(2,1,12),(3,1,25),(4,11,1);
/*!40000 ALTER TABLE `favorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offre`
--

DROP TABLE IF EXISTS `offre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dateOff` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `ent_id` int(11) DEFAULT NULL,
  `dom_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ent_id` (`ent_id`),
  KEY `dom_id` (`dom_id`),
  CONSTRAINT `offre_ibfk_1` FOREIGN KEY (`ent_id`) REFERENCES `entreprise` (`id`),
  CONSTRAINT `offre_ibfk_2` FOREIGN KEY (`dom_id`) REFERENCES `domaine` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offre`
--

LOCK TABLES `offre` WRITE;
/*!40000 ALTER TABLE `offre` DISABLE KEYS */;
INSERT INTO `offre` VALUES (1,'2019-10-02','Fais si',1,1),(2,'2019-01-12','DevWeb',1,1),(10,'2019-10-02','Fais si',1,1),(12,'2019-01-12','DevOps',1,1),(19,'2019-10-02','Fais si',1,1),(20,'2019-01-12','DevOps',1,1),(25,'2019-01-12','DevWebapp',1,1),(26,'2019-01-12','DivWebapp',1,1),(27,'2019-01-12','DevOps',13,1),(28,'2019-01-14','DevOpsii',13,1),(29,'2019-01-14','DevOpsii',13,2),(30,'2020-01-1998','test',13,1);
/*!40000 ALTER TABLE `offre` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-17 11:39:33
