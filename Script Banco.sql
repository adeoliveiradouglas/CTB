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
-- Dumping data for table `cargo`
--

LOCK TABLES `cargo` WRITE;
/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;
INSERT INTO `cargo` VALUES (1,'Administrador','Tem total domínio sobre todo o sistema'),(2,'Gestor geral','Enxerga todos os processos da empresa e inicia novos contratos'),(3,'Gestor','Adiciona processos aos contratos já existentes ou é um fiscal de processo'),(4,'Tesoureiro','Libera o pagamento de processos'),(5,'Diretor','Visualiza processos'),(6,'Presidente','Visualiza processos');
/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `contrato`
--

LOCK TABLES `contrato` WRITE;
/*!40000 ALTER TABLE `contrato` DISABLE KEYS */;
INSERT INTO `contrato` VALUES (13,'02/2016','N/A','Alberoni e Arruda-Serviços de Engenharia LTDA',10,'2016-04-01','2016-04-01','2016-04-01',2300000.00,'2018-09-22','2019-04-01','Prestação de serviços e manutenção preventiva e corretiva',2,2,1,1,1,1,1,1),(14,'001.14.7.X.00.4','13.579.586/0001-32','PRODEB',10,'2014-04-09','2014-04-09','2014-04-09',150000.00,'2018-10-08','2018-05-08','Serviços de Dados, Internet e Outros.',3,3,3,1,1,1,1,0);
/*!40000 ALTER TABLE `contrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fontepagante`
--

LOCK TABLES `fontepagante` WRITE;
/*!40000 ALTER TABLE `fontepagante` DISABLE KEYS */;
INSERT INTO `fontepagante` VALUES (1,'Custeio'),(2,'Investimento');
/*!40000 ALTER TABLE `fontepagante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `processo`
--

LOCK TABLES `processo` WRITE;
/*!40000 ALTER TABLE `processo` DISABLE KEYS */;
INSERT INTO `processo` VALUES (1,'',0.00,145.00,'Uso sem desembolço','2018-08-16','2018-08-16','',14,'2014','Janeiro',4),(2,'',0.00,0.00,'Uso sem Desembolço','2018-08-16','2018-08-16','',14,'2014','Fevereiro',4),(3,'',0.00,0.00,'Uso sem desembolço','2018-08-16','2018-08-16','',14,'2014','Março',4),(4,'',0.00,0.00,'Uso sem Desembolço','2018-08-16','2018-08-16','',14,'2014','Abril',4),(5,'3815',0.00,579.18,'1ª Fatura (FIPLAN)','2018-08-16','2014-05-29','',14,'2014','Maio',4),(6,'5194',0.00,4296.42,'NFs (5501, 5795, 5861) Fiplan, RH, RGIII, Site','2018-08-16','2014-09-15','',14,'2014','Setembro',4),(7,'6096',0.00,6671.78,'NFs (6366, 6432, 6522, 6800,6856) Diversas','2018-08-16','2014-09-21','',14,'2014','Setembro',4),(8,'4303',0.00,4272.49,'NFs (4373, 5064, 5065) Diversas','2018-08-16','2014-09-21','',14,'2014','Outubro',4),(9,'8037',0.00,5011.51,'NFs (8125, 8139, 8431, 8432) Divesrsas','2018-08-16','2014-10-27','',14,'2014','Novembro',4),(10,'8656',0.00,145.00,'RGIII','2018-08-16','2014-12-23','',14,'2014','Novembro',4),(11,'8786',0.00,4901.71,'NFs (9003, 9084, 9385, 9386, 9596, 9609)','2018-08-16','2014-12-26','',14,'2014','Dezembro',4),(12,'10766',0.00,5259.17,'NFs (10652, 11050, 11164, 10267, {Ref:2015})','2018-08-16','2014-12-17','',14,'2014','Dezembro',4),(13,'9754',0.00,4136.09,'NFs (9970, 9971, 10175, 10298)','2018-08-16','2014-12-18','',14,'2015','Janeiro',4),(14,'11.417',0.00,4775.32,'NFs (11613, 11708, 11898, 11899, 12127)','2018-08-16','2018-08-16','',14,'2015','Fevereiro',4),(15,'12338',0.00,5108.15,'NFs (12501, 12514, 12652, 12724, 12725)','2018-08-16','2018-08-16','',14,'2015','Março',4),(16,'13059',0.00,5700.58,'NFs (13419, 13420, 13616, 13830, 13914)','2018-08-16','2018-08-16','',14,'2015','Abril',4),(17,'14014',0.00,5102.62,'NFs (14241, 14242, 14405, 14671)','2018-08-16','2018-08-16','',14,'2015','Maio',4),(18,'15460',0.00,5295.24,'NFs (15269, 14994, 14993, 14843)','2018-08-16','2018-08-16','',14,'2015','Junho',4),(19,'15974',108416.00,5659.51,'NFs (15975, 16033, 16203, 18088)','2018-08-16','2018-08-16','',14,'2015','Julho',4),(20,'16299',0.00,5697.60,'NFs (16600, 16601, 16779, 16965)','2018-08-16','2018-08-16','',14,'2015','Agosto',4),(21,'17139',0.00,5942.52,'NFs (17202, 17203, 17379, 17742)','2018-08-16','2018-08-16','',14,'2015','Setembro',4),(22,'18638',0.00,7242.52,'NFs (18818, 18855, 18885, 19278, 19279, 19376, 19379)','2018-08-16','2018-08-16','',14,'2015','Outubro',4),(23,'17883',0.00,7720.27,'NFs (18049, 18089, 18090, 18321, 18373, 18556)','2018-08-16','2018-08-16','',14,'2015','Novembro',4),(24,'18048',0.00,8519.12,'NFs (20270, 20326, 20440, 20571, 20900, 20901, 20440)','2018-08-16','2018-08-16','',14,'2015','Dezembro',4),(25,'19511',0.00,8519.12,'NFs (19511, 19573, 19673, 19979, 19980, 20024)','2018-08-16','2018-08-16','',14,'2015','Janeiro',4),(26,'',54208.00,7456.25,'Reforço Orçamentário para 2016 dentro do aditivo 01','2018-08-16','2018-08-16','',14,'2016','Fevereiro',4),(27,'',166805.00,7301.26,'SEGUNDO TERMO ADITIVO CONT 001.14.X.00.4/2014','2018-08-16','2018-08-16','',14,'2016','Maio',4),(28,'28609',0.00,7277.68,'Faturas: 28609,28606,28607,28610,28611,28605,28608 ','2018-08-16','2018-08-16','',14,'2016','Outubro',4),(29,'29420',0.00,7517.33,'FATURAS:29420,29423,29424,29421,29425,29422,29426','2018-08-16','2018-08-16','',14,'2016','Novembro',4),(30,'30323',0.00,7387.32,'30.323,30.324,30.352,30.322,30.321,30.327,30.326','2018-08-16','2018-08-16','',14,'2016','Dezembro',4),(31,'30506',0.00,7387.32,'30.506,31.037,31.038,31.039,30.487,30.563,30.632','2018-08-16','2018-08-16','',14,'2017','Janeiro',4),(32,'00.104',0.00,8367.65,'32.064,32.060,32.48,32.049,32.050,32.062,32.061','2018-08-16','2018-08-16','',14,'2017','Fevereiro',4),(33,'00.111',0.00,7389.32,'32.871,32867,32.874,32.873,32.865,32.865,32.868,','2018-08-16','2018-08-16','',14,'2017','Março',4),(34,'000.182',0.00,11278.49,'33.704,33.706,33.715,33.716,33.717,33.718','2018-08-16','2018-08-16','',14,'2017','Abril',4),(35,'000.225',0.00,11141.01,'34.524,34.534,34.540,34.541,34.538,34.535','2018-08-16','2018-08-16','',14,'2017','Maio',4),(36,'000.274',96066.67,10681.34,'35.372,35359,35358,35360,35373(2º Termo Aditivo)',NULL,'2017-07-03','595',14,'2017','Junho',-1),(37,'000.348',0.00,10883.45,'36.215,36.216,36.217,36.218','2018-08-16','2018-08-16','',14,'2017','Julho',4),(38,'000.393',0.00,10520.56,'37.024,37.011,37.013,37.026,37.025','2018-08-16','2018-08-16','',14,'2017','Agosto',4),(39,'446',0.00,9833.38,'37.910, 37.879, 37880, 37881, 37.911, 37.946, ','2018-08-16','2018-08-16','',14,'2017','Setembro',4),(40,'000.507',0.00,10426.77,'38.789, 38.792, 38.793, 38.794, 38.795, 38.796','2018-08-16','2018-08-16','',14,'2017','Outubro',4),(41,'000.565',0.00,10506.14,'39.631, 39.591, 39.592, 39.632, 39.633, 39.634.','2018-08-16','2018-08-16','',14,'2017','Novembro',4),(42,'000.581',0.00,10452.84,'40.225, 39.722, 40.394, 40.226, 40.267, 39.778, ','2018-08-16','2018-08-16','',14,'2017','Dezembro',4),(43,'000.641',0.00,11401.23,'40.976, 41.036, 40.569, 40.977, 41.018, 41.145','2018-08-16','2018-08-16','',14,'2018','Janeiro',4),(44,'746',0.00,12041.17,'42.139, 42.140, 42.141, 42.142, 42.145, 42.149','2018-08-16','2018-08-16','',14,'2018','Fevereiro',4),(45,'43.010',0.00,9101.61,'42.994, 42.992, 43.011, 43.115, 43.995, 43.013','2018-08-16','2018-08-16','',14,'2018','Março',4);
/*!40000 ALTER TABLE `processo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `recurso`
--

LOCK TABLES `recurso` WRITE;
/*!40000 ALTER TABLE `recurso` DISABLE KEYS */;
INSERT INTO `recurso` VALUES (1,'Próprio'),(2,'Investimento'),(3,'Convênio');
/*!40000 ALTER TABLE `recurso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `setor`
--

LOCK TABLES `setor` WRITE;
/*!40000 ALTER TABLE `setor` DISABLE KEYS */;
INSERT INTO `setor` VALUES ('09140000','Companhia de Transportes do Estado da Bahia','CTB'),('09140174','Arquivo Central','CTB/ ARQ CENTRAL'),('09140190','Arquivo Corrente','CTB/ ARQ CORRENTE'),('09140204','Diretoria de Presidencia','CTB/ DIPRE'),('09140212','Assessoria Juridica','CTB/ ASJUR'),('09140220','Assessoria Tecnica','CTB/ ASTEC'),('09140239','Assessoria de Relacoes Institucionais','CTB/ ASREI'),('09140247','Diretoria Administrativa e Financeira','CTB/ DIRAF'),('09140255','Coordenadoria de Desenvolvimento da Gestao','CTB/ CODGE'),('09140263','Subcoordenadoria de Gestao e Qualidade dos Servicos','CTB/ QUAS'),('09140271','Subcoordenadoria de Tecnologia da Informação','CTB/ TECI'),('09140280','Subcoordenadoria de Gestao de Pessoas','CTB/ GESP'),('09140298','Coordenadoria Administrativa','CTB/ COADM'),('09140301','Subcoordenadoria de Suprimentos e Patrimonio','CTB/ SPAT'),('09140310','Subcoordenadoria de Gestao de Servicos','CTB/ GSER'),('09140328','Coordenadoria Financeira','CTB/ COFIN'),('09140336','Subcoordenadoria de Controle e Execucao Financeira','CTB/ CEFI'),('09140344','Subcoordenadoria de Faturamento e Cobranca','CTB/ FATC'),('09140352','Subcoordenadoria de Contabilidade','CTB/ CONT'),('09140360','Subcoordenadoria de Convenios e Contratos','CTB/ CONV'),('09140409','Diretoria de Operacao e Manutencao','CTB/ DIROM'),('09140417','Coordenadoria de Manutencao','CTB/ COMAN'),('09140425','Subcoordenadoria de Material Rodante','CTB/ MARO'),('09140433','Subcoordenadoria de Sistema Fixo','CTB/ SISF'),('09140441','Subcoordenadoria de Via Permanente','CTB/ VIAP'),('09140450','Coordenadoria de Operacao','CTB/ COOPE'),('09140468','Subcoordenadoria de Trens Controle e Movimento','CTB/ TMOV'),('09140476','Subcoordenadoria de Planejamento Operacional das Estacoes','CTB/ POPE'),('09140484','Diretoria das Estacoes','CTB/ DIRES'),('09140492','Coordenadoria de Bem Estar do Usuario','CTB/ COBEU'),('09140506','Subcoordenadoria de Seguranca dos Usuarios','CTB/ SEGU'),('09140514','Coordenadoria de Seguranca','CTB/ COSEG'),('09140522','Subcoordenadoria de Seguranca Patrimonial','CTB/ SEPA'),('09140530','Diretoria de Planejamento','CTB/ DIPLAN'),('09140549','Coordenadoria de Avaliacao e Acompanhamento de Projetos','CTB/ COAAP'),('09140557','Coordenadoria de Estudos e Planos','CTB/ COESP'),('09140565','Diretoria de Obras','CTB/ DIROB'),('09140573','Coordenadoria de Operacoes Especiais','CTB/ COOES'),('09140581','Coordenadoria de Fiscalizacao de Obras','CTB/ COFIS');
/*!40000 ALTER TABLE `setor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `teste`
--

LOCK TABLES `teste` WRITE;
/*!40000 ALTER TABLE `teste` DISABLE KEYS */;
INSERT INTO `teste` VALUES (999999999.99,1),(999999999.99,2),(0.00,3),(15654982.66,4);
/*!40000 ALTER TABLE `teste` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `uso`
--

LOCK TABLES `uso` WRITE;
/*!40000 ALTER TABLE `uso` DISABLE KEYS */;
INSERT INTO `uso` VALUES (1,'Contínuo'),(2,'Demanda Específica');
/*!40000 ALTER TABLE `uso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (-1,101010,'Não houve pagamento','naoremover@naoremover','','09140271',4),(1,344001960,'Anderson Araújo','anderson.araujo@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140247',2),(2,92001180,'Douglas Almeida de Oliveira','adeoliveiradouglas@gmail.com','j6OfQGeOmZ4FJxdwLenfqcbBY9HBN4hip3cwQ/F/0uo=','09140271',1),(3,344000184,'Romeu Oliveira de Jesus','romeuoj@ctb.ba.gov.br','IZQTWO+TZiB51r16gw75+sABiD1DVV25L9Oqop3KZX8=','09140271',3),(4,0,'Tesoureiro teste','tesoureiro@ctb','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140328',4),(5,110,'Ana Claudia Couto','anaclaudia.couto@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140360',3),(6,14,'André Cury','andre.cury@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140565',3),(7,1000,'Andréa de Freitas Rodrigues','andreaf.rodrigues@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140298',3),(8,5,'Avelar Argolo','avelar.argolo@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140255',3),(9,101,'Carlos Bastos','carlos.bastos@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140417',3),(10,6,'Carlos Cesar Oliveira','carloscesar.oliveira@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140468',3),(11,11,'Edimar Queiroz Santos','edimar.queiroz@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140514',3),(12,111,'Hernani Baltazar Jr.','hernani.balthazar@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140565',3),(13,2,'João Casal','joaocasal.silva@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140336',3),(14,10,'Jurandir Santos Franco','jurandir.franco@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140484',3),(15,7,'Karin Kunze','karin.kunze@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140328',3),(16,8,'Keila Lomanto','keila.lomanto@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140484',3),(17,12,'Leonardo Barreiros','leonardo.barreiros@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140220',3),(18,9,'Maria Tereza Barreto','mariatereza.barreto@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140565',3),(19,123456,'Maristela Dantas','maristela.silveira@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140484',3),(21,4,'Sinara Maria','sinara.ribeiro@ctb.ba.gov.br','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140220',3),(23,344002005,'Diretor','diretor@ctb','C9ZuVhIVP5mhoZPmOPoVRpTemZEsCRHI5qKNwSBxnms=','09140247',5),(24,1010,'Presidente','presidente@ctb','j6OfQGeOmZ4FJxdwLenfqcbBY9HBN4hip3cwQ/F/0uo=','09140000',6);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

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

-- Dump completed on 2018-08-16 17:18:56
