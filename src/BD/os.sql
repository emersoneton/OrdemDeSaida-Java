-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 22-Set-2020 às 23:17
-- Versão do servidor: 10.4.14-MariaDB
-- versão do PHP: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `os`
--

-- --------------------------------------------------------

--
-- Estrutura stand-in para vista `buscarcliente`
-- (Veja abaixo para a view atual)
--
CREATE TABLE `buscarcliente` (
`codigo` int(11)
,`nome` varchar(300)
,`endereco` varchar(150)
,`numero` varchar(10)
,`cep` varchar(15)
,`cidade` varchar(150)
,`estado` varchar(2)
,`pais` varchar(100)
,`email` varchar(150)
,`email2` varchar(150)
,`telefone` varchar(15)
);

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes`
--

CREATE TABLE `clientes` (
  `codigo` int(11) NOT NULL,
  `nome` varchar(300) DEFAULT NULL,
  `endereco` varchar(150) DEFAULT NULL,
  `numero` varchar(10) DEFAULT NULL,
  `cep` varchar(15) DEFAULT NULL,
  `cidade` varchar(150) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  `pais` varchar(100) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `email2` varchar(150) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `cnpj` varchar(25) DEFAULT NULL,
  `cpf` varchar(25) DEFAULT NULL,
  `bairro` varchar(50) DEFAULT NULL,
  `data` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `clientes`
--

INSERT INTO `clientes` (`codigo`, `nome`, `endereco`, `numero`, `cep`, `cidade`, `estado`, `pais`, `email`, `email2`, `telefone`, `cnpj`, `cpf`, `bairro`, `data`) VALUES
(21, 'ABNER DA SILVA JUNIOR', 'rua Jardim da aucarias de são paulo RS', '12', '19878-899', 'porto alegre', 'RS', '', '', '', '(51) 3396-8877', '   .   .   -  ', '  .   .   /    -  ', 'aparecida', '19/09/2020'),
(22, 'EMERSON DA SILVA JONAS DE LIMA JR', 'Av Das industrias 21', '8578', '91710-564', 'PORTO ALEGRE', 'RS', '', '', '', '(51) 8899-7789', '  .   .   /    -  ', '   .   .   -  ', 'aparicio', '19/09/2020'),
(23, 'CASSIO', 'rua j', '25', '99999-999', '', 'AC', '', '', '', '(55) 5555-5555', '   .   .   -  ', '  .   .   /    -  ', 'aparecida', '22/09/2020'),
(26, 'NELSON RODRIGUES', 'rua Barão', '25', '91710-566', 'Porto Alegre', 'RS', '', '', '', '(51) 3398-7788', '11.111.111/1111-11', '152.278.999-99', 'centro', '22/09/2020');

-- --------------------------------------------------------

--
-- Estrutura da tabela `estado`
--

CREATE TABLE `estado` (
  `id` int(11) NOT NULL,
  `nome` varchar(75) DEFAULT NULL,
  `uf` varchar(2) DEFAULT NULL,
  `ibge` int(11) DEFAULT NULL,
  `pais` int(11) DEFAULT NULL,
  `ddd` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `estado`
--

INSERT INTO `estado` (`id`, `nome`, `uf`, `ibge`, `pais`, `ddd`) VALUES
(1, 'Acre', 'AC', 12, 1, '68'),
(2, 'Alagoas', 'AL', 27, 1, '82'),
(3, 'Amazonas', 'AM', 13, 1, '97,92'),
(4, 'Amapá', 'AP', 16, 1, '96'),
(5, 'Bahia', 'BA', 29, 1, '77,75,73,74,71'),
(6, 'Ceará', 'CE', 23, 1, '88,85'),
(7, 'Distrito Federal', 'DF', 53, 1, '61'),
(8, 'Espírito Santo', 'ES', 32, 1, '28,27'),
(9, 'Goiás', 'GO', 52, 1, '62,64,61'),
(10, 'Maranhão', 'MA', 21, 1, '99,98'),
(11, 'Minas Gerais', 'MG', 31, 1, '34,37,31,33,35,38,32'),
(12, 'Mato Grosso do Sul', 'MS', 50, 1, '67'),
(13, 'Mato Grosso', 'MT', 51, 1, '65,66'),
(14, 'Pará', 'PA', 15, 1, '91,94,93'),
(15, 'Paraíba', 'PB', 25, 1, '83'),
(16, 'Pernambuco', 'PE', 26, 1, '81,87'),
(17, 'Piauí', 'PI', 22, 1, '89,86'),
(18, 'Paraná', 'PR', 41, 1, '43,41,42,44,45,46'),
(19, 'Rio de Janeiro', 'RJ', 33, 1, '24,22,21'),
(20, 'Rio Grande do Norte', 'RN', 24, 1, '84'),
(21, 'Rondônia', 'RO', 11, 1, '69'),
(22, 'Roraima', 'RR', 14, 1, '95'),
(23, 'Rio Grande do Sul', 'RS', 43, 1, '53,54,55,51'),
(24, 'Santa Catarina', 'SC', 42, 1, '47,48,49'),
(25, 'Sergipe', 'SE', 28, 1, '79'),
(26, 'São Paulo', 'SP', 35, 1, '11,12,13,14,15,16,17,18,19'),
(27, 'Tocantins', 'TO', 17, 1, '63'),
(99, 'Exterior', 'EX', 99, NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `filial`
--

CREATE TABLE `filial` (
  `codigo` int(11) NOT NULL,
  `razao_social` varchar(300) DEFAULT NULL,
  `razao_fantasia` varchar(200) DEFAULT NULL,
  `cnpj` varchar(25) DEFAULT NULL,
  `inscricao_estadual` varchar(20) DEFAULT NULL,
  `inscricao_municipal` varchar(20) DEFAULT NULL,
  `endereco` varchar(100) DEFAULT NULL,
  `numero` varchar(15) DEFAULT NULL,
  `bairro` varchar(50) DEFAULT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `estado` varchar(5) DEFAULT NULL,
  `pais` varchar(15) DEFAULT NULL,
  `telefone_comercial` varchar(20) DEFAULT NULL,
  `telefone_celular` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `filial`
--

INSERT INTO `filial` (`codigo`, `razao_social`, `razao_fantasia`, `cnpj`, `inscricao_estadual`, `inscricao_municipal`, `endereco`, `numero`, `bairro`, `cidade`, `estado`, `pais`, `telefone_comercial`, `telefone_celular`) VALUES
(8, '11', '11', '1', '1', '1', '', '', '', '', 'AC', '', '', ''),
(9, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'aaaaaa', NULL, NULL, NULL, NULL, NULL),
(10, 'CCCCC', 'AAAAAAA', '  .   .   /    -  ', '   .   .   .   ', '      - ', '', '', '', '', 'TO', '', '(  )     -    ', '(  )  .    -    '),
(11, '1111111111', '1111111111', '11.111.111/1   -  ', '   .   .   .   ', '      - ', '', '', '', '', 'DF', '', '(  )     -    ', '(  )  .    -    ');

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos`
--

CREATE TABLE `produtos` (
  `codigo` int(11) NOT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  `codigo_barras` varchar(50) DEFAULT NULL,
  `valor` varchar(15) DEFAULT NULL,
  `quantidade` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `produtos`
--

INSERT INTO `produtos` (`codigo`, `descricao`, `codigo_barras`, `valor`, `quantidade`) VALUES
(3, 'dijuntor', '1113333444', '15,50', '10'),
(8, 'fuzil', '10101010', '10.50', '10'),
(9, 'Fusivel de Compresão', '', '5.50', '12'),
(13, '1', '1', '1', '1'),
(14, 'Motor de compresao', '11111', '190,50', '11'),
(15, 'motor de ar', '1234', '189,90', '2');

-- --------------------------------------------------------

--
-- Estrutura para vista `buscarcliente`
--
DROP TABLE IF EXISTS `buscarcliente`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `buscarcliente`  AS  select `clientes`.`codigo` AS `codigo`,`clientes`.`nome` AS `nome`,`clientes`.`endereco` AS `endereco`,`clientes`.`numero` AS `numero`,`clientes`.`cep` AS `cep`,`clientes`.`cidade` AS `cidade`,`clientes`.`estado` AS `estado`,`clientes`.`pais` AS `pais`,`clientes`.`email` AS `email`,`clientes`.`email2` AS `email2`,`clientes`.`telefone` AS `telefone` from `clientes` where `clientes`.`codigo` = '5' ;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`codigo`);

--
-- Índices para tabela `filial`
--
ALTER TABLE `filial`
  ADD PRIMARY KEY (`codigo`);

--
-- Índices para tabela `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`codigo`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `clientes`
--
ALTER TABLE `clientes`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de tabela `filial`
--
ALTER TABLE `filial`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de tabela `produtos`
--
ALTER TABLE `produtos`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
