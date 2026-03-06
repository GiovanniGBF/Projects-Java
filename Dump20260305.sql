CREATE DATABASE  IF NOT EXISTS `gestao` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gestao`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: gestao
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aluno` (
  `id` smallint NOT NULL AUTO_INCREMENT,
  `Nome` varchar(60) NOT NULL,
  `Apelido` varchar(60) NOT NULL,
  `NomeLivro` varchar(60) NOT NULL,
  `Sala` varchar(60) NOT NULL,
  `Turma` int NOT NULL,
  `Numero` int NOT NULL,
  `Imagem` longblob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno`
--

LOCK TABLES `aluno` WRITE;
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livros`
--

DROP TABLE IF EXISTS `livros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `livros` (
  `id` smallint NOT NULL AUTO_INCREMENT,
  `Titulo` varchar(60) NOT NULL,
  `Autor` varchar(60) NOT NULL,
  `AnoPublico` date NOT NULL,
  `Editora` varchar(60) NOT NULL,
  `Idioma` varchar(60) NOT NULL,
  `Descrição` varchar(60) NOT NULL,
  `Condição` varchar(60) NOT NULL,
  `DataEntrada` date NOT NULL,
  `Genero` varchar(60) NOT NULL,
  `NumeroPagina` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livros`
--

LOCK TABLES `livros` WRITE;
/*!40000 ALTER TABLE `livros` DISABLE KEYS */;
INSERT INTO `livros` VALUES (1,'A minha historia','Bubacar','2014-04-20','ProdBob','Criolo','Bom','Novo','2012-02-20','   ',500),(4,'N´TORI PLAM','AUGUSTO','2026-02-01','NO PINTCHA','Criolo','BOM LIVRO','Seminovo','2026-02-04','Teatro/Drama',500),(5,'A VERDADEIRA  RELIGIÃO','ABU','2026-02-01','ISLAM','Português','FALA  SOBRE A VERDADERA RELIGIÃO','Raro','2026-02-22','História',30),(6,'1O LEIS PARA SER FELIZ','AUGUSTO CURY','2015-02-20','BRASIL.EDIT','Português','SABEDORIA','Novo','2026-02-09','Memórias',150),(7,'A MINHA ALMA','DANEL','2026-02-04','BRA.PROD','Português','SOBRE UMA MULHER DE NOME CAMILE','De colecionador','2026-02-13','Conto',27),(8,'LINGUAGEM JAVA','MOHAMED ALY JALÓ','2026-02-16','ULG','Português','LINGUAGEM DE PROGRAMAÇÃO','Novo','2026-02-14','Ciência/Divulgação científica',600),(9,'MAX-MAN','XCVN,.-','2026-02-26','XCVBNM,.','Olof','SOPERTYUIOP','Autografado','2026-02-12','Romance de época',1234560),(12,'MUNTRUS','AISSATO','2026-02-15','ULG','Olof','MUNTRUNDADE PONTO COM','Usado','2026-02-25','Fantasia',70),(13,'REDE DE COMPUTADOR','BUBA','2026-02-01','ULG','Português','SOBRE REDE DE COMPUTADORES','Novo','2026-02-01','Ciência/Divulgação científica',600),(16,'GANGSTER','LONDON BOYS','2026-02-01','AMERICAN ALG','Criolo','BOM','Usado','2026-02-19','   ',327),(20,'BASE DE DADOS','NAMIR TIDUCA','2026-02-06','ULG','Português','TRATA DE BASE DADOS','Novo','2026-02-17','Ciência/Divulgação científica',200),(21,'MATEMATICA I','MAMADU BHÁ','2026-02-01','ULG','Criolo','ENSINA TUDO SOBRE PROPOSIÇÃO.','Seminovo','2026-02-11','Ciência/Divulgação científica',200),(22,'ALGORITMOS','LAMINE','2026-02-02','ULG','Criolo','COMO PENSAR ','Novo','2026-02-17','Ciência/Divulgação científica',100),(26,'O CANTO DOS ESCRAVIZADOS','PAULINA SHIZIANE','2026-02-01','ANGOLAEEE','Português','FALA SOBRE ESCRAVATURA','Seminovo','2026-02-19','Memórias',1230),(30,'O PENSAMENTO POLITICO','DR.KOUMBA YALA','2002-03-06','NO PINTCHA','Português','BOM LIVRO PARA INPULCIONAR PENSAMENTO','Primeira edição','2026-03-03','Política/Sociologia',20);
/*!40000 ALTER TABLE `livros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `Id` smallint NOT NULL AUTO_INCREMENT,
  `Nome` varchar(30) NOT NULL,
  `Apilido` varchar(30) DEFAULT NULL,
  `Genero` varchar(10) NOT NULL,
  `DataNascimento` date NOT NULL,
  `NumeroB_I` mediumtext NOT NULL,
  `Senha` varchar(60) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (3,'Bubacar','Djaló','Masculinno','2015-04-20','000000','Bobito');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-05 11:48:49
