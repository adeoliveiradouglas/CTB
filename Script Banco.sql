CREATE DATABASE  IF NOT EXISTS `gestaodecontratos` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `gestaodecontratos`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: gestaodecontratos
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cargo`
--

DROP TABLE IF EXISTS `cargo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cargo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contrato`
--

DROP TABLE IF EXISTS `contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contrato` (
  `numero` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cnpjEmpresaContratada` varchar(14) NOT NULL,
  `nomeEmpresaContratada` varchar(100) NOT NULL,
  `portaria` int(11) NOT NULL,
  `dataAssinatura` date NOT NULL,
  `dataOrdemServico` date NOT NULL,
  `dataGarantia` date NOT NULL,
  `tempoVigente` int(11) NOT NULL,
  `tempoVigenteDias` bit(1) NOT NULL,
  `valorInicial` decimal(10,0) NOT NULL,
  `valorTotal` decimal(10,0) NOT NULL,
  `valorAditivos` float NOT NULL,
  `dataVencimentoContrato` date NOT NULL,
  `dataVencimentoGarantia` date NOT NULL,
  `objeto` varchar(255) NOT NULL,
  `gestor` int(11) NOT NULL,
  `fiscal` int(11) NOT NULL,
  `recurso_id` int(11) NOT NULL,
  `fontePagante_id` int(11) NOT NULL,
  `uso_id` int(11) NOT NULL,
  PRIMARY KEY (`numero`),
  KEY `fk_Contrato_usuario1_idx` (`gestor`),
  KEY `fk_Contrato_usuario2_idx` (`fiscal`),
  KEY `fk_Contrato_recurso1_idx` (`recurso_id`),
  KEY `fk_Contrato_fontePagante1_idx` (`fontePagante_id`),
  KEY `fk_Contrato_uso1_idx` (`uso_id`),
  CONSTRAINT `fk_Contrato_fontePagante1` FOREIGN KEY (`fontePagante_id`) REFERENCES `fontepagante` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Contrato_recurso1` FOREIGN KEY (`recurso_id`) REFERENCES `recurso` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Contrato_uso1` FOREIGN KEY (`uso_id`) REFERENCES `uso` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Contrato_usuario1` FOREIGN KEY (`gestor`) REFERENCES `usuario` (`matricula`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Contrato_usuario2` FOREIGN KEY (`fiscal`) REFERENCES `usuario` (`matricula`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fontepagante`
--

DROP TABLE IF EXISTS `fontepagante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fontepagante` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `processo`
--

DROP TABLE IF EXISTS `processo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `processo` (
  `id` int(11) NOT NULL,
  `notaFiscal` varchar(10) NOT NULL,
  `aditivo` float DEFAULT NULL,
  `tipoAditivo` varchar(45) DEFAULT NULL,
  `dataPagamento` date DEFAULT NULL,
  `numeroSei` varchar(20) NOT NULL,
  `contratoNumero` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_processo_Contrato1_idx` (`contratoNumero`),
  CONSTRAINT `fk_processo_Contrato1` FOREIGN KEY (`contratoNumero`) REFERENCES `contrato` (`numero`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `recurso`
--

DROP TABLE IF EXISTS `recurso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recurso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `setor`
--

DROP TABLE IF EXISTS `setor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `setor` (
  `codigo` varchar(8) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `sigla` varchar(30) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `uso`
--

DROP TABLE IF EXISTS `uso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `matricula` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `login` varchar(100) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `setor_codigo` varchar(8) NOT NULL,
  `cargo_id` int(11) NOT NULL,
  PRIMARY KEY (`matricula`,`setor_codigo`,`cargo_id`),
  KEY `fk_usuario_setor1_idx` (`setor_codigo`),
  KEY `fk_usuario_cargo1_idx` (`cargo_id`),
  CONSTRAINT `fk_usuario_cargo1` FOREIGN KEY (`cargo_id`) REFERENCES `cargo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_setor1` FOREIGN KEY (`setor_codigo`) REFERENCES `setor` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuariosnovos`
--

DROP TABLE IF EXISTS `usuariosnovos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuariosnovos` (
  `matricula` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `login` varchar(100) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `setor_codigo` varchar(8) NOT NULL,
  `cargo_id` int(11) NOT NULL,
  PRIMARY KEY (`matricula`,`setor_codigo`,`cargo_id`),
  KEY `fk_usuariosNovos_setor1_idx` (`setor_codigo`),
  KEY `fk_usuariosNovos_cargo1_idx` (`cargo_id`),
  CONSTRAINT `fk_usuariosNovos_cargo1` FOREIGN KEY (`cargo_id`) REFERENCES `cargo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuariosNovos_setor1` FOREIGN KEY (`setor_codigo`) REFERENCES `setor` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'gestaodecontratos'
--

--
-- Dumping routines for database 'gestaodecontratos'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-04 16:56:36
