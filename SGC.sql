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
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo`
--

LOCK TABLES `cargo` WRITE;
/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;
INSERT INTO `cargo` VALUES (1,'Administrador','Tem total domínio sobre todo o sistema'),(2,'Gestor geral','Enxerga todos os processos da empresa e inicia novos contratos'),(3,'Gestor','Adiciona processos aos contratos já existentes ou é um fiscal de processo'),(4,'Tesoureiro','Libera o pagamento de processos');
/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contrato`
--

DROP TABLE IF EXISTS `contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contrato` (
  `idContrato` int(11) NOT NULL AUTO_INCREMENT,
  `numero` varchar(20) NOT NULL,
  `cnpjEmpresaContratada` varchar(14) NOT NULL,
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
  PRIMARY KEY (`idContrato`,`numero`),
  KEY `fk_Contrato_recurso1_idx` (`recurso_id`),
  KEY `fk_Contrato_fontePagante1_idx` (`fontePagante_id`),
  KEY `fk_Contrato_uso1_idx` (`uso_id`),
  KEY `fk_Usuario_gestor_idx` (`gestor_id`),
  KEY `fk_Usuario_fiscal_idx` (`fiscal_id`),
  CONSTRAINT `fk_Contrato_fontePagante1` FOREIGN KEY (`fontePagante_id`) REFERENCES `fontepagante` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Contrato_recurso1` FOREIGN KEY (`recurso_id`) REFERENCES `recurso` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Contrato_uso1` FOREIGN KEY (`uso_id`) REFERENCES `uso` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuario_fiscal` FOREIGN KEY (`fiscal_id`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuario_gestor` FOREIGN KEY (`gestor_id`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contrato`
--

LOCK TABLES `contrato` WRITE;
/*!40000 ALTER TABLE `contrato` DISABLE KEYS */;
INSERT INTO `contrato` VALUES (1,'02/2016','N/A','Alberoni e arruda',10,'2016-04-01','2016-04-01','2016-04-01',2300000.00,'2016-04-01','2016-04-01','Prestação de serviços',3,3,1,1,1),(2,'001.14.7.X.00.4','N/A','Prodeb',10,'2014-04-09','2014-04-09','2014-04-09',150000.00,'2018-05-08','2018-05-08','Serviços de Dados, Internet e Outros.',3,3,3,1,1);
/*!40000 ALTER TABLE `contrato` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fontepagante`
--

LOCK TABLES `fontepagante` WRITE;
/*!40000 ALTER TABLE `fontepagante` DISABLE KEYS */;
INSERT INTO `fontepagante` VALUES (1,'Custeio'),(2,'Investimento');
/*!40000 ALTER TABLE `fontepagante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `processo`
--

DROP TABLE IF EXISTS `processo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `processo` (
  `notaFiscal` varchar(10) NOT NULL,
  `aditivo` decimal(11,2) DEFAULT NULL,
  `valor` decimal(11,2) NOT NULL,
  `tipoAditivo` varchar(45) DEFAULT NULL,
  `dataPagamento` date DEFAULT NULL,
  `dataProcesso` date NOT NULL,
  `numeroSei` varchar(20) NOT NULL,
  `contrato_id` int(11) NOT NULL,
  `ano` varchar(4) NOT NULL,
  `mes` varchar(9) NOT NULL,
  PRIMARY KEY (`numeroSei`),
  KEY `fk_processo_Contrato1_idx` (`contrato_id`),
  CONSTRAINT `fk_contrato` FOREIGN KEY (`contrato_id`) REFERENCES `contrato` (`idContrato`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `processo`
--

LOCK TABLES `processo` WRITE;
/*!40000 ALTER TABLE `processo` DISABLE KEYS */;
/*!40000 ALTER TABLE `processo` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recurso`
--

LOCK TABLES `recurso` WRITE;
/*!40000 ALTER TABLE `recurso` DISABLE KEYS */;
INSERT INTO `recurso` VALUES (1,'Próprio'),(2,'Investimento'),(3,'Convênio');
/*!40000 ALTER TABLE `recurso` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `setor`
--

LOCK TABLES `setor` WRITE;
/*!40000 ALTER TABLE `setor` DISABLE KEYS */;
INSERT INTO `setor` VALUES ('09140000','Companhia de Transportes do Estado da Bahia','CTB'),('09140174','Arquivo Central','CTB/ ARQ CENTRAL'),('09140190','Arquivo Corrente','CTB/ ARQ CORRENTE'),('09140204','Diretoria de Presidencia','CTB/ DIPRE'),('09140212','Assessoria Juridica','CTB/ ASJUR'),('09140220','Assessoria Tecnica','CTB/ ASTEC'),('09140239','Assessoria de Relacoes Institucionais','CTB/ ASREI'),('09140247','Diretoria Administrativa e Financeira','CTB/ DIRAF'),('09140255','Coordenadoria de Desenvolvimento da Gestao','CTB/ CODGE'),('09140263','Subcoordenadoria de Gestao e Qualidade dos Servicos','CTB/ QUAS'),('09140271','Subcoordenadoria de Tecnologia da Informação','CTB/ TECI'),('09140280','Subcoordenadoria de Gestao de Pessoas','CTB/ GESP'),('09140298','Coordenadoria Administrativa','CTB/ COADM'),('09140301','Subcoordenadoria de Suprimentos e Patrimonio','CTB/ SPAT'),('09140310','Subcoordenadoria de Gestao de Servicos','CTB/ GSER'),('09140328','Coordenadoria Financeira','CTB/ COFIN'),('09140336','Subcoordenadoria de Controle e Execucao Financeira','CTB/ CEFI'),('09140344','Subcoordenadoria de Faturamento e Cobranca','CTB/ FATC'),('09140352','Subcoordenadoria de Contabilidade','CTB/ CONT'),('09140360','Subcoordenadoria de Convenios e Contratos','CTB/ CONV'),('09140409','Diretoria de Operacao e Manutencao','CTB/ DIROM'),('09140417','Coordenadoria de Manutencao','CTB/ COMAN'),('09140425','Subcoordenadoria de Material Rodante','CTB/ MARO'),('09140433','Subcoordenadoria de Sistema Fixo','CTB/ SISF'),('09140441','Subcoordenadoria de Via Permanente','CTB/ VIAP'),('09140450','Coordenadoria de Operacao','CTB/ COOPE'),('09140468','Subcoordenadoria de Trens Controle e Movimento','CTB/ TMOV'),('09140476','Subcoordenadoria de Planejamento Operacional das Estacoes','CTB/ POPE'),('09140484','Diretoria das Estacoes','CTB/ DIRES'),('09140492','Coordenadoria de Bem Estar do Usuario','CTB/ COBEU'),('09140506','Subcoordenadoria de Seguranca dos Usuarios','CTB/ SEGU'),('09140514','Coordenadoria de Seguranca','CTB/ COSEG'),('09140522','Subcoordenadoria de Seguranca Patrimonial','CTB/ SEPA'),('09140530','Diretoria de Planejamento','CTB/ DIPLAN'),('09140549','Coordenadoria de Avaliacao e Acompanhamento de Projetos','CTB/ COAAP'),('09140557','Coordenadoria de Estudos e Planos','CTB/ COESP'),('09140565','Diretoria de Obras','CTB/ DIROB'),('09140573','Coordenadoria de Operacoes Especiais','CTB/ COOES'),('09140581','Coordenadoria de Fiscalizacao de Obras','CTB/ COFIS');
/*!40000 ALTER TABLE `setor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teste`
--

DROP TABLE IF EXISTS `teste`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teste` (
  `numero` decimal(11,2) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teste`
--

LOCK TABLES `teste` WRITE;
/*!40000 ALTER TABLE `teste` DISABLE KEYS */;
INSERT INTO `teste` VALUES (999999999.99,1),(999999999.99,2),(0.00,3),(15654982.66,4);
/*!40000 ALTER TABLE `teste` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uso`
--

LOCK TABLES `uso` WRITE;
/*!40000 ALTER TABLE `uso` DISABLE KEYS */;
INSERT INTO `uso` VALUES (1,'Contínuo'),(2,'Demanda Específica');
/*!40000 ALTER TABLE `uso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `matricula` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `login` varchar(100) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `setor_codigo` varchar(8) NOT NULL,
  `cargo_id` int(11) NOT NULL,
  PRIMARY KEY (`idUsuario`,`matricula`,`setor_codigo`,`cargo_id`),
  KEY `fk_usuario_setor1_idx` (`setor_codigo`),
  KEY `fk_usuario_cargo1_idx` (`cargo_id`),
  CONSTRAINT `fk_usuario_cargo1` FOREIGN KEY (`cargo_id`) REFERENCES `cargo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_setor1` FOREIGN KEY (`setor_codigo`) REFERENCES `setor` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,33445566,'Anderson Araújo','anderson.araujo@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140247',2),(2,92001180,'Douglas Almeida de Oliveira','adeoliveiradouglas@gmail.com','j6OfQGeOmZ4FJxdwLenfqcbBY9HBN4hip3cwQ/F/0uo=','09140271',1),(3,344000184,'Romeu Oliveira de Jesus','romeuoj@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140271',3),(4,0,'Tesoureiro','tesoureiro@ctb','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140328',4),(5,110,'Ana Claudia Couto','anaclaudia.couto@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140360',3),(6,14,'André Curv','andre.cury@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140565',3),(7,1000,'Andréa de Freitas Rodrigues','andreaf.rodrigues@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140298',3),(8,5,'Avelar Argolo','avelar.argolo@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140255',3),(9,101,'Carlos Bastos','carlos.bastos@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140417',3),(10,6,'Carlos Cesar Oliveira','carloscesar.oliveira@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140468',3),(11,11,'Edimar Queiroz Santos','edimar.queiroz@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140514',3),(12,111,'Hernani Baltazar Jr.','hernani.balthazar@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140565',3),(13,2,'João Casal','joaocasal.silva@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140336',3),(14,10,'Jurandir Santos Franco','jurandir.franco@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140484',3),(15,7,'Karin Kunze','karin.kunze@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140328',3),(16,8,'Keila Lomanto','keila.lomanto@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140484',3),(17,12,'Leonardo Barreiros','leonardo.barreiros@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140220',3),(18,9,'Maria Tereza Barreto','mariatereza.barreto@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140565',3),(19,123456,'Maristela Dantas','maristela.silveira@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140484',3),(20,13,'Raimundo Filgueiras','raimundo.filgueiras@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140530',3),(21,4,'Sinara Maria','sinara.ribeiro@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140220',3);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuariosnovos`
--

DROP TABLE IF EXISTS `usuariosnovos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuariosnovos` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `matricula` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `login` varchar(100) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `setor_codigo` varchar(8) NOT NULL,
  `cargo_id` int(11) NOT NULL,
  PRIMARY KEY (`idUsuario`,`matricula`,`setor_codigo`,`cargo_id`),
  KEY `fk_usuariosNovos_setor1_idx` (`setor_codigo`),
  KEY `fk_usuariosNovos_cargo1_idx` (`cargo_id`),
  CONSTRAINT `fk_usuariosNovos_cargo1` FOREIGN KEY (`cargo_id`) REFERENCES `cargo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuariosNovos_setor1` FOREIGN KEY (`setor_codigo`) REFERENCES `setor` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuariosnovos`
--

LOCK TABLES `usuariosnovos` WRITE;
/*!40000 ALTER TABLE `usuariosnovos` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuariosnovos` ENABLE KEYS */;
UNLOCK TABLES;

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

-- Dump completed on 2018-07-20  9:12:16
