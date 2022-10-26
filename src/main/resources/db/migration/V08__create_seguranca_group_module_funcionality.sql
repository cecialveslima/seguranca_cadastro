-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: seguranca
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `group_module_funcionality`
--

DROP TABLE IF EXISTS `group_module_funcionality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_module_funcionality` (
  `for_id` bigint NOT NULL,
  `fun_id` varchar(255) NOT NULL,
  `group_id` varchar(255) NOT NULL,
  `module_id` varchar(255) NOT NULL,
  PRIMARY KEY (`for_id`,`fun_id`,`group_id`,`module_id`),
  KEY `FKfxauw4dhhwj8dv73hlbj7tf3u` (`fun_id`),
  KEY `FK19efd4nvc987wq30yjx5dsmk2` (`group_id`),
  KEY `FK3xhx3vwl17c9f00n49nied6qp` (`module_id`),
  CONSTRAINT `FK19efd4nvc987wq30yjx5dsmk2` FOREIGN KEY (`group_id`) REFERENCES `grupos` (`group_id`),
  CONSTRAINT `FK2mcup3ctjvk5rlnlwcj6vqciq` FOREIGN KEY (`for_id`) REFERENCES `fornecedor` (`for_id`),
  CONSTRAINT `FK3xhx3vwl17c9f00n49nied6qp` FOREIGN KEY (`module_id`) REFERENCES `modulo` (`module_id`),
  CONSTRAINT `FKfxauw4dhhwj8dv73hlbj7tf3u` FOREIGN KEY (`fun_id`) REFERENCES `funcionalidade` (`fun_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-26 14:09:54
