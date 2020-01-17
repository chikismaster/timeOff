-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-01-2020 a las 18:37:18
-- Versión del servidor: 10.4.10-MariaDB
-- Versión de PHP: 7.1.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ventas2`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `IdCliente` int(11) UNSIGNED NOT NULL,
  `Dni` varchar(8) DEFAULT NULL,
  `Nombres` varchar(244) DEFAULT NULL,
  `Direccion` varchar(244) DEFAULT NULL,
  `Estado` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`IdCliente`, `Dni`, `Nombres`, `Direccion`, `Estado`) VALUES
(1, '123', 'Cliente Frecuente', 'Los Alamos 345 Lima', '1'),
(2, '12345678', 'Juan Alberto Ribero', 'Los Cardenales de Oyllo', '1'),
(3, '1234567', 'Papa Francisco', 'El Vaticano', '1'),
(4, '123456', 'Obama Belaunde', 'Carretera Central', '1'),
(5, '1234', 'San Matero de Las Rosas', 'Puente Piedra Rosada', '1'),
(6, '12345', 'Los Visitantes de Lurin', 'Puente Chicharoneria', '1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_ventas`
--

CREATE TABLE `detalle_ventas` (
  `IdDetalleVentas` int(11) UNSIGNED NOT NULL,
  `IdVentas` int(11) UNSIGNED NOT NULL,
  `IdProducto` int(11) UNSIGNED NOT NULL,
  `Cantidad` int(11) UNSIGNED DEFAULT NULL,
  `PrecioVenta` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `IdProducto` int(11) UNSIGNED NOT NULL,
  `Nombres` varchar(244) DEFAULT NULL,
  `Codigo_product` varchar(200) NOT NULL,
  `Precio` double DEFAULT NULL,
  `Stock` int(11) UNSIGNED DEFAULT NULL,
  `Estado` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`IdProducto`, `Nombres`, `Codigo_product`, `Precio`, `Stock`, `Estado`) VALUES
(1, 'Teclado Inalambrico Logitech 345', '343', 150, 90, '1'),
(2, 'Mouse Ianalmbrico Logitech', '55', 20, 85, '1'),
(3, 'Laptop Lenovo Ideapad 520', '77', 1200, 88, '1'),
(4, 'Laptop Lenovo 330 IdeApad', '11', 1000, 50, '1'),
(5, 'Monitor LCD Smart 42 pulgadas', '99', 1800, 45, '1'),
(6, 'Impresora HP 3450 Inalmabrico', '654', 400, 98, '1'),
(7, 'Mouse Inalambrico Logitech', '21', 90, 83, '1'),
(8, 'chocoprispis', '8965', 32, 6, '1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vendedor`
--

CREATE TABLE `vendedor` (
  `IdVendedor` int(10) UNSIGNED NOT NULL,
  `Dni` varchar(8) NOT NULL,
  `Nombres` varchar(255) DEFAULT NULL,
  `Telefono` varchar(15) DEFAULT NULL,
  `Estado` varchar(1) DEFAULT NULL,
  `User` varchar(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `vendedor`
--

INSERT INTO `vendedor` (`IdVendedor`, `Dni`, `Nombres`, `Telefono`, `Estado`, `User`) VALUES
(1, '12345678', 'Empleado 0001', '988252459', '1', 'emp01'),
(2, '', 'Empleado 0002', '988252459', '1', 'Jo46'),
(3, '', 'Empleado 0003', '222222', '1', 'Em22'),
(4, '123', 'marco cobian avalos', '3121385579', '1', 'mc');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `IdVentas` int(11) UNSIGNED NOT NULL,
  `IdCliente` int(11) UNSIGNED DEFAULT NULL,
  `IdVendedor` int(10) UNSIGNED NOT NULL,
  `NumeroSerie` varchar(244) DEFAULT NULL,
  `FechaVentas` date DEFAULT NULL,
  `Monto` double DEFAULT NULL,
  `Estado` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`IdVentas`, `IdCliente`, `IdVendedor`, `NumeroSerie`, `FechaVentas`, `Monto`, `Estado`) VALUES
(54, NULL, 2, '001', '2020-01-17', 25, '1');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`IdCliente`);

--
-- Indices de la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  ADD PRIMARY KEY (`IdDetalleVentas`,`IdVentas`,`IdProducto`),
  ADD KEY `Ventas_has_Producto_FKIndex1` (`IdVentas`),
  ADD KEY `Ventas_has_Producto_FKIndex2` (`IdProducto`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`IdProducto`),
  ADD UNIQUE KEY `Codigo_product` (`Codigo_product`);

--
-- Indices de la tabla `vendedor`
--
ALTER TABLE `vendedor`
  ADD PRIMARY KEY (`IdVendedor`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`IdVentas`),
  ADD KEY `Ventas_FKIndex1` (`IdVendedor`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `IdCliente` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  MODIFY `IdDetalleVentas` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=118;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `IdProducto` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `vendedor`
--
ALTER TABLE `vendedor`
  MODIFY `IdVendedor` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `IdVentas` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  ADD CONSTRAINT `detalle_ventas_ibfk_1` FOREIGN KEY (`IdVentas`) REFERENCES `ventas` (`IdVentas`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `detalle_ventas_ibfk_2` FOREIGN KEY (`IdProducto`) REFERENCES `producto` (`IdProducto`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`IdVendedor`) REFERENCES `vendedor` (`IdVendedor`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
