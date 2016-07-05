-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-06-2016 a las 00:12:42
-- Versión del servidor: 5.6.24
-- Versión de PHP: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `control_acceso_bd`
--
CREATE DATABASE IF NOT EXISTS `control_acceso_bd` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `control_acceso_bd`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro`
--

CREATE TABLE IF NOT EXISTS `registro` (
  `id` int(11) NOT NULL,
  `fecha_hora` datetime NOT NULL,
  `estado` char(1) DEFAULT 'N',
  `tarjeta_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `registro`
--

INSERT INTO `registro` (`id`, `fecha_hora`, `estado`, `tarjeta_id`) VALUES
(1, '2016-06-01 07:16:37', 'E', 6),
(2, '2016-06-01 07:16:57', 'E', 1),
(3, '2016-06-01 07:17:09', 'E', 3),
(4, '2016-06-01 07:17:16', 'E', 2),
(5, '2016-06-01 07:17:22', 'S', 1),
(6, '2016-06-01 07:17:30', 'S', 6),
(7, '2016-06-01 07:17:38', 'S', 6),
(8, '2016-06-01 07:17:47', 'S', 2),
(9, '2016-06-01 07:38:21', 'S', 2),
(10, '2016-06-01 07:38:27', 'S', 2),
(11, '2016-06-01 07:38:34', 'S', 1),
(12, '2016-06-01 07:42:15', 'S', 1),
(13, '2016-06-01 07:46:08', 'S', 1),
(14, '2016-06-01 07:50:28', 'S', 1),
(17, '2016-06-01 07:58:24', 'E', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE IF NOT EXISTS `rol` (
  `id` int(11) NOT NULL,
  `rol` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sector`
--

CREATE TABLE IF NOT EXISTS `sector` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarjeta`
--

CREATE TABLE IF NOT EXISTS `tarjeta` (
  `id` int(11) NOT NULL,
  `codigo` varchar(30) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tarjeta`
--

INSERT INTO `tarjeta` (`id`, `codigo`) VALUES
(1, '1001'),
(2, '1002'),
(3, '1003'),
(4, '1004'),
(5, '1005'),
(6, '1006');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL DEFAULT 'desconocido',
  `foto` varchar(350) DEFAULT NULL,
  `documento` varchar(20) DEFAULT NULL,
  `tarjeta_id` int(11) DEFAULT NULL,
  `fecha_alta` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `foto`, `documento`, `tarjeta_id`, `fecha_alta`) VALUES
(1, 'Oscar Juafran', 'D:\\bkup\\Edicion imagenes\\oscar foto\\OSCAR_EDITADO.jpg', '54678892-8', NULL, '2016-06-01 07:11:49'),
(2, 'Giselle Andrade', 'D:\\bkup\\Edicion imagenes\\jajaja\\naty\\165657_1791704197464_1385415977_32024840_7554006_n.jpg', '876665568-2', 5, '2016-06-01 07:12:56'),
(3, 'Darwin Carrizo', 'D:\\bkup\\Edicion imagenes\\jajaja\\darwin\\foto.jpg', '438779588-0', 1, '2016-06-01 07:13:48'),
(4, 'Mu Vaca', 'D:\\bkup\\Edicion imagenes\\mu imagenes\\mu\\MU.jpg', '1111254-1', 6, '2016-06-01 07:15:30');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_has_sector`
--

CREATE TABLE IF NOT EXISTS `usuario_has_sector` (
  `usuario_id` int(11) NOT NULL,
  `sector_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_sistema`
--

CREATE TABLE IF NOT EXISTS `usuario_sistema` (
  `id` int(11) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_sistema_has_rol`
--

CREATE TABLE IF NOT EXISTS `usuario_sistema_has_rol` (
  `usuario_sistema_id` int(11) NOT NULL,
  `rol_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `registro`
--
ALTER TABLE `registro`
  ADD PRIMARY KEY (`id`,`tarjeta_id`), ADD KEY `fk_registro_tarjeta1_idx` (`tarjeta_id`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `rol_UNIQUE` (`rol`);

--
-- Indices de la tabla `sector`
--
ALTER TABLE `sector`
  ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `nombre_UNIQUE` (`nombre`);

--
-- Indices de la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
  ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `codigo_UNIQUE` (`codigo`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `documento_UNIQUE` (`documento`), ADD KEY `fk_usuario_tarjeta1_idx` (`tarjeta_id`);

--
-- Indices de la tabla `usuario_has_sector`
--
ALTER TABLE `usuario_has_sector`
  ADD PRIMARY KEY (`usuario_id`,`sector_id`), ADD KEY `fk_usuario_has_sector_sector1_idx` (`sector_id`), ADD KEY `fk_usuario_has_sector_usuario1_idx` (`usuario_id`);

--
-- Indices de la tabla `usuario_sistema`
--
ALTER TABLE `usuario_sistema`
  ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `usuario_UNIQUE` (`usuario`);

--
-- Indices de la tabla `usuario_sistema_has_rol`
--
ALTER TABLE `usuario_sistema_has_rol`
  ADD PRIMARY KEY (`usuario_sistema_id`,`rol_id`), ADD KEY `fk_usuario_sistema_has_rol_rol1_idx` (`rol_id`), ADD KEY `fk_usuario_sistema_has_rol_usuario_sistema1_idx` (`usuario_sistema_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `registro`
--
ALTER TABLE `registro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `sector`
--
ALTER TABLE `sector`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `usuario_sistema`
--
ALTER TABLE `usuario_sistema`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `registro`
--
ALTER TABLE `registro`
ADD CONSTRAINT `fk_registro_tarjeta1` FOREIGN KEY (`tarjeta_id`) REFERENCES `tarjeta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
ADD CONSTRAINT `fk_usuario_tarjeta1` FOREIGN KEY (`tarjeta_id`) REFERENCES `tarjeta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuario_has_sector`
--
ALTER TABLE `usuario_has_sector`
ADD CONSTRAINT `fk_usuario_has_sector_sector1` FOREIGN KEY (`sector_id`) REFERENCES `sector` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_usuario_has_sector_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuario_sistema_has_rol`
--
ALTER TABLE `usuario_sistema_has_rol`
ADD CONSTRAINT `fk_usuario_sistema_has_rol_rol1` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_usuario_sistema_has_rol_usuario_sistema1` FOREIGN KEY (`usuario_sistema_id`) REFERENCES `usuario_sistema` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
