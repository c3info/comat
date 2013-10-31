-- -----------------------------------------------------
-- Table `comat`.`Estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comat`.`Estado` (
  `uf` VARCHAR(2) NOT NULL,
  `nome` VARCHAR(22) NOT NULL,
  PRIMARY KEY (`uf`)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `comat`.`Cidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comat`.`Cidade` (
  `idCidade` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(38) NOT NULL,
  `ufFk` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`idCidade`),  
  FOREIGN KEY (`ufFk`) REFERENCES `comat`.`Estado` (`uf`)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `comat`.`Endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comat`.`Endereco` (
  `idEndereco` INT(11) NOT NULL AUTO_INCREMENT, 
  `logradouro` VARCHAR(45) NOT NULL,
  `numero` INT(11) NOT NULL,
  `complemento` VARCHAR(45) NULL,
  `bairro` VARCHAR(45) NULL,
  `cep` VARCHAR(10) NOT NULL,
  `idCidadeFk` INT(11) NOT NULL,
  PRIMARY KEY (`idEndereco`),
  FOREIGN KEY (`idCidadeFk`) REFERENCES `comat`.`Cidade` (`idCidade`)
 )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `comat`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comat`.`Cliente` (
  `idCliente` INT(11) NOT NULL AUTO_INCREMENT,
  `status` INT NULL,
  `email` VARCHAR(72) NULL UNIQUE,
  `site` VARCHAR(72) NULL UNIQUE,
  `telefone` VARCHAR(12) NULL UNIQUE,
  `dataCadastro` DATE NOT NULL, 
  `observacoes` LONGTEXT NULL,
  `idEnderecoFk` INT(11) NULL,
  PRIMARY KEY (`idCliente`),  
  FOREIGN KEY (`idEnderecoFk`) REFERENCES `comat`.`Endereco` (`idEndereco`)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `comat`.`ClienteFisica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comat`.`ClienteFisica` (
  `cpf` VARCHAR(14) NOT NULL,
  `rg` VARCHAR(11) NULL,
  `nome` VARCHAR(72) NOT NULL,
  `dataNasc` DATE NULL,
  `celular` VARCHAR(12) NULL,
  `idClienteFk` INT(11) NOT NULL,
  PRIMARY KEY (`cpf`),
  FOREIGN KEY (`idClienteFk`) REFERENCES `comat`.`Cliente` (`idCliente`)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `comat`.`ClienteJuridica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comat`.`ClienteJuridica` (
  `cnpj` VARCHAR(18) NOT NULL,
  `inscricao` VARCHAR(12) NULL,
  `razao` VARCHAR(72) NOT NULL,
  `fantasia` VARCHAR(72) NULL,
  `fax` VARCHAR(12) NULL,
  `idClienteFk` INT(11) NOT NULL,
  PRIMARY KEY (`cnpj`),
  FOREIGN KEY (`idClienteFk`) REFERENCES `comat`.`Cliente` (`idCliente`)
 )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `comat`.`Contato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comat`.`Contato` (
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
CREATE TABLE IF NOT EXISTS `comat`.`ContatosCliente` (
  `idContatoFk` INT(11) NOT NULL,
  `idClienteFk` INT(11) NOT NULL,
  PRIMARY KEY (`idContatoFk`, `idClienteFk`),  
  FOREIGN KEY (`idContatoFk`) REFERENCES `comat`.`Contato` (`idContato`),
  FOREIGN KEY (`idClienteFK`) REFERENCES `comat`.`Cliente` (`idCliente`)
)
ENGINE = InnoDB;