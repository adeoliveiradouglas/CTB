-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema gestaodecontratos
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gestaodecontratos
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gestaodecontratos` DEFAULT CHARACTER SET utf8 ;
USE `gestaodecontratos` ;

-- -----------------------------------------------------
-- Table `gestaodecontratos`.`fontePagante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestaodecontratos`.`fontePagante` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gestaodecontratos`.`recurso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestaodecontratos`.`recurso` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gestaodecontratos`.`uso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestaodecontratos`.`uso` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gestaodecontratos`.`setor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestaodecontratos`.`setor` (
  `codigo` VARCHAR(8) NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `sigla` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gestaodecontratos`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestaodecontratos`.`usuario` (
  `matricula` INT(11) NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `login` VARCHAR(100) NOT NULL,
  `senha` VARCHAR(255) NOT NULL,
  `setor_codigo` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`matricula`, `setor_codigo`),
  INDEX `fk_usuario_setor1_idx` (`setor_codigo` ASC),
  CONSTRAINT `fk_usuario_setor1`
    FOREIGN KEY (`setor_codigo`)
    REFERENCES `gestaodecontratos`.`setor` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gestaodecontratos`.`contrato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestaodecontratos`.`contrato` (
  `numero` INT(11) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `cnpjEmpresaContratada` VARCHAR(14) NOT NULL,
  `nomeEmpresaContratada` VARCHAR(100) NOT NULL,
  `portaria` INT(11) NOT NULL,
  `dataAssinatura` DATE NOT NULL,
  `dataOrdemServico` DATE NOT NULL,
  `dataGarantia` DATE NOT NULL,
  `tempoVigente` INT(11) NOT NULL,
  `tempoVigenteDias` BIT(1) NOT NULL,
  `valorInicial` DECIMAL(10,0) NOT NULL,
  `valorTotal` DECIMAL(10,0) NOT NULL,
  `valorAditivos` FLOAT NOT NULL,
  `dataVencimentoContrato` DATE NOT NULL,
  `dataVencimentoGarantia` DATE NOT NULL,
  `objeto` VARCHAR(255) NOT NULL,
  `gestor` INT(11) NOT NULL,
  `fiscal` INT(11) NOT NULL,
  `recurso_id` INT(11) NOT NULL,
  `fontePagante_id` INT(11) NOT NULL,
  `uso_id` INT(11) NOT NULL,
  PRIMARY KEY (`numero`),
  INDEX `fk_Contrato_usuario1_idx` (`gestor` ASC),
  INDEX `fk_Contrato_usuario2_idx` (`fiscal` ASC),
  INDEX `fk_Contrato_recurso1_idx` (`recurso_id` ASC),
  INDEX `fk_Contrato_fontePagante1_idx` (`fontePagante_id` ASC),
  INDEX `fk_Contrato_uso1_idx` (`uso_id` ASC),
  CONSTRAINT `fk_Contrato_fontePagante1`
    FOREIGN KEY (`fontePagante_id`)
    REFERENCES `gestaodecontratos`.`fontePagante` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Contrato_recurso1`
    FOREIGN KEY (`recurso_id`)
    REFERENCES `gestaodecontratos`.`recurso` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Contrato_uso1`
    FOREIGN KEY (`uso_id`)
    REFERENCES `gestaodecontratos`.`uso` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Contrato_usuario1`
    FOREIGN KEY (`gestor`)
    REFERENCES `gestaodecontratos`.`usuario` (`matricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Contrato_usuario2`
    FOREIGN KEY (`fiscal`)
    REFERENCES `gestaodecontratos`.`usuario` (`matricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gestaodecontratos`.`permissao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestaodecontratos`.`permissao` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gestaodecontratos`.`processo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestaodecontratos`.`processo` (
  `id` INT(11) NOT NULL,
  `notaFiscal` VARCHAR(10) NOT NULL,
  `aditivo` FLOAT NULL DEFAULT NULL,
  `tipoAditivo` VARCHAR(45) NULL DEFAULT NULL,
  `dataPagamento` DATE NULL DEFAULT NULL,
  `numeroSei` VARCHAR(20) NOT NULL,
  `contratoNumero` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_processo_Contrato1_idx` (`contratoNumero` ASC),
  CONSTRAINT `fk_processo_Contrato1`
    FOREIGN KEY (`contratoNumero`)
    REFERENCES `gestaodecontratos`.`contrato` (`numero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gestaodecontratos`.`usuario_has_permissao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestaodecontratos`.`usuario_has_permissao` (
  `usuario_matricula` INT(11) NOT NULL,
  `permissao_id` INT(11) NOT NULL,
  PRIMARY KEY (`usuario_matricula`, `permissao_id`),
  INDEX `fk_usuario_has_permissao_permissao1_idx` (`permissao_id` ASC),
  INDEX `fk_usuario_has_permissao_usuario1_idx` (`usuario_matricula` ASC),
  CONSTRAINT `fk_usuario_has_permissao_permissao1`
    FOREIGN KEY (`permissao_id`)
    REFERENCES `gestaodecontratos`.`permissao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_permissao_usuario1`
    FOREIGN KEY (`usuario_matricula`)
    REFERENCES `gestaodecontratos`.`usuario` (`matricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
