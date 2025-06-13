-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema pixup
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `pixup` ;

-- -----------------------------------------------------
-- Schema pixup
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pixup` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `pixup` ;

-- -----------------------------------------------------
-- Table `pixup`.`TBL_ESTADO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pixup`.`TBL_ESTADO` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `pixup`.`TBL_ESTADO` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pixup`.`TBL_MUNICIPIO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pixup`.`TBL_MUNICIPIO` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `pixup`.`TBL_MUNICIPIO` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `MUNICIPIO` VARCHAR(45) NOT NULL,
  `TBL_ESTADO_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `fk_TBL_MUNICIPIO_TBL_ESTADO`
    FOREIGN KEY (`TBL_ESTADO_ID`)
    REFERENCES `pixup`.`TBL_ESTADO` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_TBL_MUNICIPIO_TBL_ESTADO_idx` ON `pixup`.`TBL_MUNICIPIO` (`TBL_ESTADO_ID` ASC) VISIBLE;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
