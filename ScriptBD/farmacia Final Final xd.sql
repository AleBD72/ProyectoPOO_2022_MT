-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-09-2022 a las 07:26:40
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `farmacia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cab__nvtas`
--

CREATE TABLE `cab__nvtas` (
  `CODNV` int(11) NOT NULL,
  `CODUSER` int(11) NOT NULL,
  `NOMCLIENTE` varchar(50) NOT NULL,
  `TOTALNV` double(4,2) DEFAULT NULL,
  `FECHANV` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cab__nvtas`
--

INSERT INTO `cab__nvtas` (`CODNV`, `CODUSER`, `NOMCLIENTE`, `TOTALNV`, `FECHANV`) VALUES
(8, 2, 'Diego Perdomo', 14.24, '2022-09-05 21:46:05'),
(9, 2, 'Diego Perdomo', 12.49, '2022-09-05 21:55:33'),
(10, 1, 'Camila Mier', 5.82, '2022-09-05 22:35:01'),
(11, 1, 'Diego Perdomo', 5.94, '2022-09-05 22:38:03'),
(12, 2, 'Diego Perdomo', 8.86, '2022-09-05 22:41:00'),
(13, 1, 'Camila Mier', 1.50, '2022-09-05 22:47:09'),
(14, 1, 'Camila Mier', 62.05, '2022-09-05 22:51:45'),
(15, 1, 'Diego Perdomo', 1.67, '2022-09-05 23:10:24'),
(16, 1, 'Camila Mier', 12.09, '2022-09-05 23:13:50'),
(17, 1, 'Diego Perdomo', 22.16, '2022-09-05 23:16:27'),
(18, 1, 'Felipe Pazmiño', 0.69, '2022-09-05 23:17:46'),
(19, 1, 'Diego Perdomo', 1.46, '2022-09-05 23:38:24'),
(20, 1, 'Diego Perdomo', 1.46, '2022-09-05 23:55:35'),
(21, 1, 'Diego Perdomo', 1.30, '2022-09-06 00:17:14');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `IDCLI` int(11) NOT NULL,
  `CEDULACL` int(10) NOT NULL,
  `NOMCLI` varchar(50) NOT NULL,
  `CELCLI` varchar(10) NOT NULL,
  `MAILCLI` varchar(65) DEFAULT NULL,
  `FECHACLI` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`IDCLI`, `CEDULACL`, `NOMCLI`, `CELCLI`, `MAILCLI`, `FECHACLI`) VALUES
(1, 1724567389, 'Camila Mier', '0992718380', '', '2022-09-04 11:03:51'),
(2, 1724567381, 'Felipe Pazmiño', '0992714563', '', '2022-09-04 11:15:25'),
(3, 1705536314, 'Diego Perdomo', '0992718345', 'diego@gmail.com', '2022-09-04 12:02:03'),
(4, 1705536313, 'Jhon Torres', '0992718078', 'jhont@gmail.com', '2022-09-05 23:56:34');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `det_nvtas`
--

CREATE TABLE `det_nvtas` (
  `CODDET` int(11) NOT NULL,
  `CODNV` int(11) NOT NULL,
  `CODPROD` int(11) NOT NULL,
  `CANTPROD` int(11) NOT NULL,
  `TOTALNV` decimal(4,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `det_nvtas`
--

INSERT INTO `det_nvtas` (`CODDET`, `CODNV`, `CODPROD`, `CANTPROD`, `TOTALNV`) VALUES
(12, 8, 23, 5, '4.25'),
(13, 8, 14, 1, '9.99'),
(14, 9, 14, 1, '9.99'),
(15, 9, 5, 2, '2.50'),
(16, 10, 23, 4, '3.40'),
(17, 10, 9, 2, '1.60'),
(18, 10, 86, 2, '0.82'),
(19, 11, 95, 2, '0.94'),
(20, 11, 5, 4, '5.00'),
(21, 12, 1, 2, '0.46'),
(22, 12, 5, 4, '5.00'),
(23, 12, 23, 4, '3.40'),
(24, 13, 15, 2, '0.90'),
(25, 13, 4, 3, '0.60'),
(26, 14, 15, 2, '0.90'),
(27, 14, 4, 56, '11.20'),
(28, 14, 14, 5, '49.95'),
(29, 15, 15, 2, '0.90'),
(30, 15, 4, 3, '0.60'),
(31, 15, 47, 1, '0.17'),
(32, 16, 15, 2, '0.90'),
(33, 16, 14, 1, '9.99'),
(34, 16, 45, 4, '1.20'),
(35, 17, 14, 2, '19.98'),
(36, 17, 13, 7, '1.96'),
(37, 17, 3, 1, '0.22'),
(38, 18, 1, 3, '0.69'),
(39, 19, 1, 2, '0.46'),
(40, 19, 4, 5, '1.00'),
(41, 20, 1, 2, '0.46'),
(42, 20, 4, 5, '1.00'),
(43, 21, 15, 2, '0.90'),
(44, 21, 4, 2, '0.40');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `IDEMP` int(11) NOT NULL,
  `NOMEMP` varchar(60) NOT NULL,
  `RUCEMP` int(13) NOT NULL,
  `TELFEMP` int(10) DEFAULT NULL,
  `DIRECEMP` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `CODPROD` int(11) NOT NULL,
  `NOMPROD` varchar(40) NOT NULL,
  `STOCKPROD` int(11) NOT NULL,
  `PRECIOPROD` decimal(3,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`CODPROD`, `NOMPROD`, `STOCKPROD`, `PRECIOPROD`) VALUES
(1, 'Aspirina 650 mg', 143, '0.23'),
(2, 'Apronax 400 mg', 500, '0.40'),
(3, 'Omeprazol 20 mg', 400, '0.22'),
(4, 'Paracetamol 500 mg', 628, '0.20'),
(5, 'Talco 100 g', 42, '1.25'),
(6, 'AMOXICILINA 500 mg', 402, '0.25'),
(7, 'IBUPROFENO 400 mg', 452, '0.21'),
(8, 'DICLOFENACO 50 mg', 580, '0.10'),
(9, 'ZIDOVUDINA 50 mg', 100, '0.80'),
(10, 'LAMIVUDINA 50 mg', 240, '0.30'),
(11, 'NEVIRAPINA 200 mg', 542, '0.25'),
(12, 'DIDANOSINA 100 mg', 532, '0.17'),
(13, 'EFAVIRENZ 200 mg', 413, '0.28'),
(14, 'ALBENDAZOL TABLETA 200 mg', 103, '9.99'),
(15, 'AMLODIPINO GENFAR 5 mg', 302, '0.45'),
(16, 'FUROSEMIDA 40 mg', 302, '0.31'),
(17, 'LORATADINA JARABE 5 mg/5 ml', 107, '0.28'),
(18, 'TERBINAFINA 250 mg', 98, '0.41'),
(19, 'AGUA DESTILADA PARA INYECCIÓN', 50, '2.50'),
(20, 'PROFEM 600 mg', 286, '0.40'),
(21, 'VITAMINA B12 INYECTABLE', 102, '9.99'),
(22, 'AFEBRIL 400 mg', 600, '3.50'),
(23, 'PREDNISOLONA 5 mg', 211, '0.85'),
(24, 'FERROGEME TABLETAS', 100, '2.00'),
(25, 'TERBINAFINA 1% SPRAY', 102, '3.45'),
(26, 'TINIDAZOL 1 g', 402, '0.10'),
(27, 'ÁCIDO FUSÍDICO 2% CREMA', 62, '0.32'),
(28, 'AMIKACINA GENFAR 500 mg', 400, '1.20'),
(29, 'XILBAC 1.5 g', 104, '0.15'),
(30, 'BETAMETASONA 0.05%', 30, '9.99'),
(31, 'CEFTAZIDIMA 1g', 255, '0.24'),
(32, 'DICLOXACILINA CÁPSULA', 142, '0.28'),
(33, 'ENALAPRIL MALEATO 5 mg', 41, '2.10'),
(34, 'FLUOXETINA 20 mg', 104, '1.75'),
(35, 'LOSARTAN 50 mg', 411, '0.31'),
(36, 'METRONIDAZOL 500 mg', 124, '3.01'),
(37, 'NAPROXENO 500 mg', 84, '0.23'),
(38, 'RANITIDINA 300 mg', 17, '0.45'),
(39, 'TINIDAZOL GENFAR 1g TABLETAS', 45, '0.18'),
(40, 'AFEBRIL SUSPENSIÓN', 42, '3.55'),
(41, 'ALBENSTAR 200 mg', 64, '2.10'),
(42, 'IBUPROFENO 400 mg', 452, '0.21'),
(43, 'DICLOFENACO 50 mg', 580, '0.10'),
(44, 'ZIDOVUDINA 50 mg', 100, '0.80'),
(45, 'LAMIVUDINA 50 mg', 240, '0.30'),
(46, 'NEVIRAPINA 200 mg', 542, '0.25'),
(47, 'DIDANOSINA 100 mg', 532, '0.17'),
(48, 'EFAVIRENZ 200 mg', 420, '0.28'),
(49, 'ALBENDAZOL TABLETA 200 mg', 105, '9.99'),
(50, 'AMLODIPINO GENFAR 5 mg', 304, '0.45'),
(51, 'FUROSEMIDA 40 mg', 302, '0.31'),
(52, 'LORATADINA JARABE 5 mg/5 ml', 107, '0.28'),
(53, 'TERBINAFINA 250 mg', 98, '0.41'),
(54, 'AGUA DESTILADA PARA INYECCIÓN', 50, '2.50'),
(55, 'PROFEM 600 mg', 286, '0.40'),
(56, 'VITAMINA B12 INYECTABLE', 102, '9.99'),
(57, 'AFEBRIL 400 mg', 600, '3.50'),
(58, 'PREDNISOLONA 5 mg', 211, '0.85'),
(59, 'FERROGEME TABLETAS', 100, '2.00'),
(60, 'TERBINAFINA 1% SPRAY', 102, '3.45'),
(61, 'TINIDAZOL 1 g', 402, '0.10'),
(62, 'ÁCIDO FUSÍDICO 2%', 62, '0.32'),
(63, 'AMIKACINA 500 mg', 400, '1.20'),
(64, 'XILBAC 1.5 g', 104, '0.15'),
(65, 'BETAMETASONA 0.05%', 30, '9.99'),
(66, 'CEFTAZIDIMA 1g', 255, '0.24'),
(67, 'DICLOXACILINA CÁPSULA', 142, '0.28'),
(68, 'ENALAPRIL MALEATO 5 mg', 41, '2.10'),
(69, 'FLUOXETINA 20 mg', 104, '1.75'),
(70, 'LOSARTAN 50 mg', 411, '0.31'),
(71, 'METRONIDAZOL 500 mg', 124, '3.01'),
(72, 'NAPROXENO 500 mg', 84, '0.23'),
(73, 'RANITIDINA 300 mg', 17, '0.45'),
(74, 'TINIDAZOL GENFAR 1g', 45, '0.18'),
(75, 'AFEBRIL SUSPENSIÓN', 42, '3.55'),
(76, 'ALBENSTAR 200 mg', 64, '2.10'),
(77, '\"PENICILINA G/SÓDICA 1', 33, '0.95'),
(78, 'CEFTRIAXONA 1g', 54, '1.60'),
(79, 'KLARSTAR 500 mg', 76, '0.34'),
(80, 'FLUCOSTAR 150 mg', 41, '0.26'),
(81, 'RASERTAN 100 mg', 41, '0.63'),
(82, 'OXACILINA 1 g POLVO', 42, '0.36'),
(83, 'HEALT MERON', 67, '0.63'),
(84, 'BETAMPIL', 215, '0.30'),
(85, 'CLARITROMICINA 500 mg', 301, '0.75'),
(86, 'GENTAMICINA 20 mg', 87, '0.41'),
(87, 'MICROLUT', 97, '0.56'),
(88, 'AVELOX 400 mg', 47, '3.64'),
(89, 'NIFURYL 100 mg', 79, '6.40'),
(90, 'JEROMA 100 mg', 64, '0.14'),
(91, 'CEFEPIMA POLVO ESTÉRIL 1 g', 41, '0.96'),
(92, 'MEROPENEM 500 mg', 54, '0.65'),
(93, 'MEROPENEM 1 g', 89, '1.20'),
(94, 'ACICLOVIR 800 mg TABLETAS', 56, '0.35'),
(95, 'ALENDRONATO GENFAR 70 mg', 60, '0.47'),
(96, 'AMIKACINA GENFAR 500 mg/2 ml', 68, '3.10'),
(97, 'AZITROMICINA GENFAR 500 mg', 476, '1.15'),
(98, 'CLOPIDOGREL GENFAR 75 mg', 63, '0.71'),
(99, 'DILTIAZEM GENFAR 60 mg', 98, '0.36'),
(100, 'ENALAPRIL GENFAR 20 mg', 147, '0.14'),
(101, 'FLUOXETINA CÁPSULA 20 mg', 651, '0.30'),
(102, 'LORATADINA GENFAR 10 mg', 63, '0.10'),
(103, 'METRONIDAZOL GENFAR 250 mg/5 ml', 310, '3.14'),
(104, 'TINIDAZOL GENFAR 1 g', 74, '0.13'),
(105, 'MESIGYNA INSTAYECT', 82, '5.68'),
(106, 'ALBENDAZOL 100 mg/5', 32, '3.14'),
(107, 'AMOXIPEN-T CÁPSULAS 500 mg', 97, '1.05'),
(108, 'XEFALEXIN T  250 mg/5 ml', 68, '4.10'),
(109, 'TOFINIDAZOL 125 mg', 80, '0.88'),
(110, 'TERMIFEN GOTAS', 64, '0.45'),
(111, 'TERMIFEN JARABE', 95, '6.12'),
(112, 'EPIFIL LIOFILIZADO INYECTABLE 10 mg', 42, '9.99'),
(113, 'HIDROXIUREA FILAXIS CÁPSULAS 500 mg', 10, '9.99'),
(114, 'DORMIZOLAM 15 SOLUCIÓN INYECTABLE', 45, '3.17'),
(115, 'METOTREXATO FILAXIS LIOFILIZADO 500 mg', 67, '4.20'),
(116, 'AGUA PARA INYECTABLES BIDESTILADA', 96, '9.99'),
(117, 'DIAZEPAM 10 mg/2 ml INYECTABLE', 14, '9.99'),
(118, 'OXITOCINA 10 UI/ml SOLUCIÓN', 50, '9.42'),
(119, 'BEMIN 2 mg/5 ml JARABE', 45, '6.14'),
(120, 'VERMIGEN 2 % SUSPENSIÓN', 74, '3.95'),
(121, 'AMIKACINA 500 mg', 68, '2.01'),
(122, 'AMPICILINA G.A. 1 g', 95, '0.17'),
(123, 'CEFALEXINA GA 500 mg', 95, '0.25'),
(124, 'CIPROFLOXACINO GA 500 mg COMPRIMIDOS', 61, '2.56'),
(125, 'NEUROGEN SOLUCIÓN INYECTABLE', 902, '2.50'),
(126, 'CLOXAGEN 500 mg CÁPSULAS', 91, '1.05'),
(127, 'FLUCONAZOL 150 mg CÁPSULAS', 600, '0.47'),
(128, 'METOCLOX 10 mg COMPRIMIDOS', 30, '0.29'),
(129, 'OMEPRAZOL 20 mg CÁPSULAS', 51, '0.21'),
(130, 'ACETAGEN 500 mg COMPRIMIDOS', 32, '0.44'),
(131, 'RIFADEX 300 mg CÁPSULAS', 37, '0.35'),
(132, 'AMLODIPINO 5 mg TABLETA', 200, '0.41'),
(133, 'PROFEN  600', 35, '5.54'),
(134, 'ÁCIDO FÓLICO 5 mg TABLETAS', 64, '0.37');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `CODROL` char(3) NOT NULL,
  `NOMROL` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`CODROL`, `NOMROL`) VALUES
('ADM', 'Administrador'),
('CAJ', 'Cajero'),
('JBD', 'Bodeguero');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `CODUSER` int(11) NOT NULL,
  `CODROL` char(3) NOT NULL,
  `NOMUSER` varchar(50) NOT NULL,
  `EMAILUSER` varchar(30) NOT NULL,
  `CELLUSER` varchar(10) DEFAULT NULL,
  `CLAVEUSER` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`CODUSER`, `CODROL`, `NOMUSER`, `EMAILUSER`, `CELLUSER`, `CLAVEUSER`) VALUES
(1, 'ADM', 'Camila Mier', 'camilamier@epn.edu.ec', '0992718388', '123'),
(2, 'CAJ', 'Jhon Torres', 'jhontorres@epn.edu.ec', '0992717856', '123'),
(3, 'JBD', 'Paulina Males', 'paulinamales@epn.edu.ec', '0992718356', '123'),
(4, 'ADM', 'Marcos Moreira', 'marcosmore@epn.edu.ec', '0992717856', '1234'),
(5, 'JBD', 'Marcos Males', 'marcosmales@epn.edu.ec', '0901820401', '1234');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cab__nvtas`
--
ALTER TABLE `cab__nvtas`
  ADD PRIMARY KEY (`CODNV`),
  ADD KEY `FK_USER_CABECERA` (`CODUSER`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`IDCLI`);

--
-- Indices de la tabla `det_nvtas`
--
ALTER TABLE `det_nvtas`
  ADD PRIMARY KEY (`CODDET`),
  ADD KEY `FK_DET_NVTAS` (`CODPROD`),
  ADD KEY `FK_NVTA_PRODUCTOS2` (`CODNV`);

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`IDEMP`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`CODPROD`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`CODROL`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`CODUSER`),
  ADD KEY `FK_ROL_USER` (`CODROL`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cab__nvtas`
--
ALTER TABLE `cab__nvtas`
  MODIFY `CODNV` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `IDCLI` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `det_nvtas`
--
ALTER TABLE `det_nvtas`
  MODIFY `CODDET` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `CODPROD` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=135;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `CODUSER` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
