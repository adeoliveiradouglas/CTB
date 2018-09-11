CREATE DATABASE  IF NOT EXISTS `gestaodecontratos` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `gestaodecontratos`;
-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: gestaodecontratos
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
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
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cargo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cargo_has_usuario`
--

DROP TABLE IF EXISTS `cargo_has_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cargo_has_usuario` (
  `idUsuario` int(11) NOT NULL,
  `cargo_id` int(11) NOT NULL,
  PRIMARY KEY (`idUsuario`,`cargo_id`),
  KEY `fk_usuario_has_cargo_cargo1_idx` (`cargo_id`),
  KEY `fk_usuario_has_cargo_usuario1_idx` (`idUsuario`),
  CONSTRAINT `fk_usuario_has_cargo_cargo1` FOREIGN KEY (`cargo_id`) REFERENCES `cargo` (`id`),
  CONSTRAINT `fk_usuario_has_cargo_usuario1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idusuario`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cargo_has_usuariosnovos`
--

DROP TABLE IF EXISTS `cargo_has_usuariosnovos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cargo_has_usuariosnovos` (
  `idUsuario` int(11) NOT NULL,
  `cargo_id` int(11) NOT NULL,
  PRIMARY KEY (`idUsuario`,`cargo_id`),
  KEY `fk_cargo_idx` (`cargo_id`),
  CONSTRAINT `fk_cargo` FOREIGN KEY (`cargo_id`) REFERENCES `cargo` (`id`),
  CONSTRAINT `fk_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuariosnovos` (`idusuario`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contrato`
--

DROP TABLE IF EXISTS `contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `contrato` (
  `idContrato` int(11) NOT NULL AUTO_INCREMENT,
  `numero` varchar(20) NOT NULL,
  `cnpjEmpresaContratada` varchar(18) NOT NULL,
  `nomeEmpresaContratada` varchar(100) NOT NULL,
  `portaria` int(11) NOT NULL,
  `dataAssinatura` date NOT NULL,
  `dataOrdemServico` date NOT NULL,
  `dataGarantia` date NOT NULL,
  `valorInicial` decimal(11,2) NOT NULL,
  `dataVencimentoContrato` date NOT NULL,
  `dataVencimentoGarantia` date NOT NULL,
  `objeto` varchar(255) NOT NULL,
  `gestor_id` int(11) NOT NULL,
  `fiscal_id` int(11) NOT NULL,
  `recurso_id` int(11) NOT NULL,
  `fontePagante_id` int(11) NOT NULL,
  `uso_id` int(11) NOT NULL,
  `avisado90` tinyint(4) NOT NULL DEFAULT '0',
  `avisado60` tinyint(4) NOT NULL DEFAULT '0',
  `avisado45` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idContrato`),
  KEY `fk_Contrato_recurso1_idx` (`recurso_id`),
  KEY `fk_Contrato_fontePagante1_idx` (`fontePagante_id`),
  KEY `fk_Contrato_uso1_idx` (`uso_id`),
  KEY `fk_Usuario_gestor_idx` (`gestor_id`),
  KEY `fk_Usuario_fiscal_idx` (`fiscal_id`),
  CONSTRAINT `fk_Contrato_fontePagante1` FOREIGN KEY (`fontePagante_id`) REFERENCES `fontepagante` (`id`),
  CONSTRAINT `fk_Contrato_recurso1` FOREIGN KEY (`recurso_id`) REFERENCES `recurso` (`id`),
  CONSTRAINT `fk_Contrato_uso1` FOREIGN KEY (`uso_id`) REFERENCES `uso` (`id`),
  CONSTRAINT `fk_Usuario_fiscal` FOREIGN KEY (`fiscal_id`) REFERENCES `usuario` (`idusuario`),
  CONSTRAINT `fk_Usuario_gestor` FOREIGN KEY (`gestor_id`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fontepagante`
--

DROP TABLE IF EXISTS `fontepagante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fontepagante` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `processo`
--

DROP TABLE IF EXISTS `processo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `processo` (
  `idProcesso` int(11) NOT NULL AUTO_INCREMENT,
  `notaFiscal` varchar(10) NOT NULL,
  `aditivo` decimal(11,2) DEFAULT NULL,
  `valor` decimal(11,2) NOT NULL,
  `tipoAditivo` varchar(255) DEFAULT NULL,
  `dataPagamento` date DEFAULT NULL,
  `dataProcesso` date NOT NULL,
  `numeroSei` varchar(20) NOT NULL,
  `contrato_id` int(11) NOT NULL,
  `referencia` date NOT NULL,
  `usuario_id` int(11) DEFAULT '-1',
  PRIMARY KEY (`idProcesso`),
  KEY `fk_processo_Contrato1_idx` (`contrato_id`),
  KEY `fk_tesoureiro_idx` (`usuario_id`),
  CONSTRAINT `fk_contrato` FOREIGN KEY (`contrato_id`) REFERENCES `contrato` (`idcontrato`),
  CONSTRAINT `fk_tesoureiro` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `recurso`
--

DROP TABLE IF EXISTS `recurso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `recurso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `setor`
--

DROP TABLE IF EXISTS `setor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
 SET character_set_client = utf8mb4 ;
CREATE TABLE `uso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `matricula` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `login` varchar(100) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `setor_codigo` varchar(8) NOT NULL,
  PRIMARY KEY (`idUsuario`,`matricula`,`setor_codigo`),
  KEY `fk_usuario_setor1_idx` (`setor_codigo`),
  CONSTRAINT `fk_usuario_setor1` FOREIGN KEY (`setor_codigo`) REFERENCES `setor` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuariosnovos`
--

DROP TABLE IF EXISTS `usuariosnovos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `usuariosnovos` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `matricula` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `login` varchar(100) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `setor_codigo` varchar(8) NOT NULL,
  PRIMARY KEY (`idUsuario`,`matricula`,`setor_codigo`),
  KEY `fk_usuariosNovos_setor1_idx` (`setor_codigo`),
  CONSTRAINT `fk_usuariosNovos_setor1` FOREIGN KEY (`setor_codigo`) REFERENCES `setor` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-11 10:34:15
