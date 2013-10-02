-- -----------------------------------------------------
-- Table `comat`.`Estado`
-- -----------------------------------------------------
CREATE TABLE `comat`.`Estado` (
  `uf` VARCHAR(2) NOT NULL,
  `nome` VARCHAR(22) NOT NULL,
  PRIMARY KEY (`uf`)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `comat`.`Cidade`
-- -----------------------------------------------------
CREATE TABLE `comat`.`Cidade` (
  `idCidade` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(36) NOT NULL,
  `uf` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`idCidade`),  
  FOREIGN KEY (`uf`) REFERENCES `comat`.`Estado` (`uf`)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `comat`.`Endereco`
-- -----------------------------------------------------
CREATE TABLE `comat`.`Endereco` (
  `idEndereco` INT(11) NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(12) NOT NULL,
  `logradouro` VARCHAR(45) NOT NULL,
  `numero` INT NOT NULL,
  `complemento` VARCHAR(45) NULL,
  `bairro` VARCHAR(45) NULL,
  `cep` INT NOT NULL,
  `idCidade` INT(11) NOT NULL,
  PRIMARY KEY (`idEndereco`),
  FOREIGN KEY (`idCidade`) REFERENCES `comat`.`Cidade` (`idCidade`)
 )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `comat`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE `comat`.`Cliente` (
  `idCliente` INT(11) NOT NULL AUTO_INCREMENT,
  `status` INT NULL,
  `email` VARCHAR(72) NULL UNIQUE,
  `site` VARCHAR(72) NULL UNIQUE,
  `telefone` VARCHAR(12) NULL UNIQUE,
  `dataCadastro` DATE NOT NULL, 
  `observacoes` LONGTEXT NULL,
  `idEndereco` INT(11) NULL,
  PRIMARY KEY (`idCliente`),  
  FOREIGN KEY (`idEndereco`) REFERENCES `comat`.`Endereco` (`idEndereco`)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `comat`.`ClienteFisica`
-- -----------------------------------------------------
CREATE TABLE `comat`.`ClienteFisica` (
  `cpf` BIGINT NOT NULL,
  `rg` BIGINT NOT NULL,
  `nome` VARCHAR(72) NOT NULL,
  `dataNasc` DATE NULL,
  `celular` VARCHAR(12) NULL,
  `idCliente` INT NOT NULL,
  PRIMARY KEY (`cpf`),
  FOREIGN KEY (`idCliente`) REFERENCES `comat`.`Cliente` (`idCliente`)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `comat`.`ClienteJuridica`
-- -----------------------------------------------------
CREATE TABLE `comat`.`ClienteJuridica` (
  `cnpj` BIGINT NOT NULL,
  `inscricao` BIGINT NULL,
  `razao` VARCHAR(72) NOT NULL,
  `fantasia` VARCHAR(72) NULL,
  `fax` VARCHAR(12) NULL,
  `idCliente` INT(11) NOT NULL,
  PRIMARY KEY (`cnpj`),
  FOREIGN KEY (`idCliente`) REFERENCES `comat`.`Cliente` (`idCliente`)
 )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `comat`.`Contato`
-- -----------------------------------------------------
CREATE TABLE `comat`.`Contato` (
  `idContato` INT(11) NOT NULL,
  `nome` VARCHAR(45) NULL,
  `telefone` VARCHAR(12) NULL,
  `celular` VARCHAR(12) NULL,
  `email` VARCHAR(72) NULL,
  `funcao` VARCHAR(45) NULL,
  PRIMARY KEY (`idContato`)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `comat`.`ContatosCliente`
-- -----------------------------------------------------
CREATE TABLE `comat`.`ContatosCliente` (
  `idContato` INT(11) NOT NULL,
  `idCliente` INT(11) NOT NULL,
  PRIMARY KEY (`idContato`, `idCliente`),  
  FOREIGN KEY (`idContato`) REFERENCES `comat`.`Contato` (`idContato`),
  FOREIGN KEY (`idCliente`) REFERENCES `comat`.`Cliente` (`idCliente`)
)
ENGINE = InnoDB;

