-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema store
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema store
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `store` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `store` ;

-- -----------------------------------------------------
-- Table `store`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`client` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NULL DEFAULT NULL,
  `address` VARCHAR(50) NULL DEFAULT NULL,
  `email` VARCHAR(25) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `store`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nume` VARCHAR(30) NULL DEFAULT NULL,
  `pret` INT NULL DEFAULT NULL,
  `stoc` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `store`.`orderr`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`orderr` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idClient` INT NULL DEFAULT NULL,
  `idProduct` INT NULL DEFAULT NULL,
  `quantity` INT NULL DEFAULT NULL,
  `totalPrice` INT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id` (`id` ASC) VISIBLE,
  INDEX `idClient` (`idClient` ASC) VISIBLE,
  INDEX `idProduct` (`idProduct` ASC) VISIBLE,
  CONSTRAINT `orderr_ibfk_1`
    FOREIGN KEY (`idClient`)
    REFERENCES `store`.`client` (`id`),
  CONSTRAINT `orderr_ibfk_2`
    FOREIGN KEY (`idProduct`)
    REFERENCES `store`.`product` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
