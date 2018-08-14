USE gestaodecontratos;
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
-- Dumping data for table `cargo`
--

LOCK TABLES `cargo` WRITE;
/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;
INSERT INTO `cargo` VALUES (1,'Administrador','Tem total domínio sobre todo o sistema'),
						   (2,'Gestor geral','Enxerga todos os processos da empresa e inicia novos contratos'),
						   (3,'Gestor','Adiciona processos aos contratos já existentes ou é um fiscal de processo'),
						   (4,'Tesoureiro','Libera o pagamento de processos'),
						   (5,'Diretor','Visualiza processos'),
						   (6,'Presidente','Visualiza processos');
/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fontepagante`
--

LOCK TABLES `fontepagante` WRITE;
/*!40000 ALTER TABLE `fontepagante` DISABLE KEYS */;
INSERT INTO `fontepagante` VALUES (1,'Custeio'),
								  (2,'Investimento');
/*!40000 ALTER TABLE `fontepagante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `recurso`
--

LOCK TABLES `recurso` WRITE;
/*!40000 ALTER TABLE `recurso` DISABLE KEYS */;
INSERT INTO `recurso` VALUES (1,'Próprio'),
							 (2,'Investimento'),
							 (3,'Convênio');
/*!40000 ALTER TABLE `recurso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `setor`
--

LOCK TABLES `setor` WRITE;
/*!40000 ALTER TABLE `setor` DISABLE KEYS */;
INSERT INTO `setor` VALUES ('09140000','Companhia de Transportes do Estado da Bahia','CTB'),
						   ('09140174','Arquivo Central','CTB/ ARQ CENTRAL'),
						   ('09140190','Arquivo Corrente','CTB/ ARQ CORRENTE'),
						   ('09140204','Diretoria de Presidencia','CTB/ DIPRE'),
						   ('09140212','Assessoria Juridica','CTB/ ASJUR'),
						   ('09140220','Assessoria Tecnica','CTB/ ASTEC'),
						   ('09140239','Assessoria de Relacoes Institucionais','CTB/ ASREI'),
						   ('09140247','Diretoria Administrativa e Financeira','CTB/ DIRAF'),
						   ('09140255','Coordenadoria de Desenvolvimento da Gestao','CTB/ CODGE'),
						   ('09140263','Subcoordenadoria de Gestao e Qualidade dos Servicos','CTB/ QUAS'),
						   ('09140271','Subcoordenadoria de Tecnologia da InformaÃ§ão','CTB/ TECI'),
						   ('09140280','Subcoordenadoria de Gestao de Pessoas','CTB/ GESP'),
						   ('09140298','Coordenadoria Administrativa','CTB/ COADM'),
						   ('09140301','Subcoordenadoria de Suprimentos e Patrimonio','CTB/ SPAT'),
						   ('09140310','Subcoordenadoria de Gestao de Servicos','CTB/ GSER'),
						   ('09140328','Coordenadoria Financeira','CTB/ COFIN'),
						   ('09140336','Subcoordenadoria de Controle e Execucao Financeira','CTB/ CEFI'),
						   ('09140344','Subcoordenadoria de Faturamento e Cobranca','CTB/ FATC'),
						   ('09140352','Subcoordenadoria de Contabilidade','CTB/ CONT'),
						   ('09140360','Subcoordenadoria de Convenios e Contratos','CTB/ CONV'),
						   ('09140409','Diretoria de Operacao e Manutencao','CTB/ DIROM'),
						   ('09140417','Coordenadoria de Manutencao','CTB/ COMAN'),
						   ('09140425','Subcoordenadoria de Material Rodante','CTB/ MARO'),
						   ('09140433','Subcoordenadoria de Sistema Fixo','CTB/ SISF'),
						   ('09140441','Subcoordenadoria de Via Permanente','CTB/ VIAP'),
						   ('09140450','Coordenadoria de Operacao','CTB/ COOPE'),
						   ('09140468','Subcoordenadoria de Trens Controle e Movimento','CTB/ TMOV'),
						   ('09140476','Subcoordenadoria de Planejamento Operacional das Estacoes','CTB/ POPE'),
						   ('09140484','Diretoria das Estacoes','CTB/ DIRES'),
						   ('09140492','Coordenadoria de Bem Estar do Usuario','CTB/ COBEU'),
						   ('09140506','Subcoordenadoria de Seguranca dos Usuarios','CTB/ SEGU'),
						   ('09140514','Coordenadoria de Seguranca','CTB/ COSEG'),
						   ('09140522','Subcoordenadoria de Seguranca Patrimonial','CTB/ SEPA'),
						   ('09140530','Diretoria de Planejamento','CTB/ DIPLAN'),
						   ('09140549','Coordenadoria de Avaliacao e Acompanhamento de Projetos','CTB/ COAAP'),
						   ('09140557','Coordenadoria de Estudos e Planos','CTB/ COESP'),
						   ('09140565','Diretoria de Obras','CTB/ DIROB'),
						   ('09140573','Coordenadoria de Operacoes Especiais','CTB/ COOES'),
						   ('09140581','Coordenadoria de Fiscalizacao de Obras','CTB/ COFIS');
/*!40000 ALTER TABLE `setor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `uso`
--

LOCK TABLES `uso` WRITE;
/*!40000 ALTER TABLE `uso` DISABLE KEYS */;
INSERT INTO `uso` VALUES (1,'Contínuo'),
						 (2,'Demanda Específica');
/*!40000 ALTER TABLE `uso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (-1,101010,'Não houve pagamento','NÃO REMOVER ESSE USUARIO. NECESSÁRIO PARA O SISTEMA FUNCIONAR','','09140271',4);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
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

-- Dump completed on 2018-08-07 11:52:35
