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
  `status` INT NOT NULL,
  `email` VARCHAR(72) NULL UNIQUE,
  `site` VARCHAR(72) NULL,
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
  `idContato` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(12) NULL,
  `celular` VARCHAR(12) NOT NULL,
  `email` VARCHAR(72) NULL,
  `funcao` VARCHAR(45) NULL,
  `idClienteFk` INT(11) NOT NULL,
  PRIMARY KEY (`idContato`),
  FOREIGN KEY (`idClienteFK`) REFERENCES `comat`.`Cliente` (`idCliente`)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `comat`.`Obra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comat`.`Obra` (
  `idObra` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(12) NULL,
  `responsavel` VARCHAR(72) NULL,
  `idClienteFk` INT NOT NULL,
  `idEnderecoFk` INT NOT NULL,
  PRIMARY KEY (`idObra`), 
  FOREIGN KEY (`idClienteFk`) REFERENCES `comat`.`Cliente` (`idCliente`),
  FOREIGN KEY (`idEnderecoFk`) REFERENCES `comat`.`Endereco` (`idEndereco`)
) 
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `comat`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comat`.`Categoria` (
  `idCategoria` INT(11) NOT NULL AUTO_INCREMENT,
  `nomeCategoria` VARCHAR(42) NOT NULL UNIQUE,
  PRIMARY KEY (`idCategoria`)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `comat`.`Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comat`.`Produto` (
  `refProduto` INT(11) NOT NULL,
  `codBarra` VARCHAR(13) NULL,
  `CodFabricante` VARCHAR(12) NULL,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(96) NULL,
  `unidade` VARCHAR(4) NOT NULL,
  `precoCusto` DOUBLE NULL,
  `precoVenda` DOUBLE NULL,
  `descontoMax` DOUBLE NULL,
  `quantidade` DOUBLE NULL,
  `status` INT NOT NULL,
  `marca` VARCHAR(45) NULL,
  `peso` DOUBLE NULL,
  `idCategoriaFk` INT NOT NULL,
  PRIMARY KEY (`refProduto`),
  FOREIGN KEY (`idCategoriaFk`) REFERENCES `comat`.`Categoria` (`idCategoria`)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `comat`.`CorrelacaoProdutos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comat`.`CorrelacaoProdutos` (
  `refProdutoFk` INT NOT NULL,
  `refProdutoRelFk` INT NOT NULL,
  `data` DATETIME NOT NULL,
  `tipo` INT NULL,
  PRIMARY KEY (`refProdutoFk`, `refProdutoRelFk`),  
  FOREIGN KEY (`refProdutoFk`) REFERENCES `comat`.`Produto` (`refProduto`), 
  FOREIGN KEY (`refProdutoRelFk`) REFERENCES `comat`.`Produto` (`refProduto`)
)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `comat`.`Orcamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comat`.`Orcamento` (
  `idOrcamento` INT NOT NULL AUTO_INCREMENT,
  `data` DATE NOT NULL,
  `status` INT NOT NULL,
  `validade` DATE NOT NULL,
  `observacoes` LONGTEXT NULL,
  `total` DOUBLE NULL,
  `idClienteFk` INT NOT NULL,
  `idObraFk` INT NULL,
  `idUsuarioFk` INT NULL,
  PRIMARY KEY (`idOrcamento`),
  FOREIGN KEY (`idClienteFk`) REFERENCES `comat`.`Cliente` (`idCliente`),
  FOREIGN KEY (`idObraFk`)REFERENCES `comat`.`Obra` (`idObra`)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comat`.`ItensOrcamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comat`.`ItensOrcamento` (
  `idOrcamentoFk` INT NOT NULL,
  `refProdutoFk` INT NOT NULL,
  `preco` DOUBLE NOT NULL,
  `quantidade` INT NOT NULL,
  `desconto` DOUBLE NULL,
  PRIMARY KEY (`idOrcamentoFk`, `refProdutoFk`),
  FOREIGN KEY (`idOrcamentoFk`) REFERENCES `comat`.`Orcamento` (`idOrcamento`),
  FOREIGN KEY (`refProdutoFk`) REFERENCES `comat`.`Produto` (`refProduto`)
)
ENGINE = InnoDB;