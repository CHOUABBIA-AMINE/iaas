-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 12, 2025 at 04:58 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `smsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `r_01_03__01_01`
--

CREATE TABLE `r_01_03__01_01` (
  `f_01` bigint(20) NOT NULL,
  `f_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `r_01_03__01_01`
--

INSERT INTO `r_01_03__01_01` (`f_01`, `f_02`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(1, 13),
(1, 14),
(1, 15),
(1, 16),
(2, 2),
(2, 6),
(2, 10),
(2, 14),
(3, 1),
(3, 2),
(3, 3),
(3, 5),
(3, 6),
(3, 7),
(3, 9),
(3, 10),
(3, 11),
(3, 13),
(3, 14),
(3, 15);

-- --------------------------------------------------------

--
-- Table structure for table `r_01_04__01_03`
--

CREATE TABLE `r_01_04__01_03` (
  `f_01` bigint(20) NOT NULL,
  `f_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `r_01_04__01_03`
--

INSERT INTO `r_01_04__01_03` (`f_01`, `f_02`) VALUES
(1, 1),
(2, 3),
(3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `r_01_05__01_03`
--

CREATE TABLE `r_01_05__01_03` (
  `f_01` bigint(20) NOT NULL,
  `f_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `r_01_05__01_03`
--

INSERT INTO `r_01_05__01_03` (`f_01`, `f_02`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `r_01_05__01_04`
--

CREATE TABLE `r_01_05__01_04` (
  `f_01` bigint(20) NOT NULL,
  `f_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `r_01_05__01_04`
--

INSERT INTO `r_01_05__01_04` (`f_01`, `f_02`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `r_20_08__20_11`
--

CREATE TABLE `r_20_08__20_11` (
  `f_01` bigint(20) NOT NULL,
  `f_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_01_01`
--

CREATE TABLE `t_01_01` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_02` varchar(200) DEFAULT NULL,
  `f_01` varchar(50) NOT NULL,
  `f_03` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_01_01`
--

INSERT INTO `t_01_01` (`f_00`, `a_01`, `a_02`, `a_03`, `f_02`, `f_01`, `f_03`) VALUES
(1, '2025-07-02 15:00:11.000000', NULL, NULL, 'Create new users', 'CREATE_USER', 1),
(2, '2025-07-02 15:00:11.000000', NULL, NULL, 'View users', 'READ_USER', 1),
(3, '2025-07-02 15:00:11.000000', NULL, NULL, 'Edit users', 'UPDATE_USER', 1),
(4, '2025-07-02 15:00:11.000000', NULL, NULL, 'Delete users', 'DELETE_USER', 1),
(5, '2025-07-02 15:00:11.000000', NULL, NULL, 'Create new roles', 'CREATE_ROLE', 2),
(6, '2025-07-02 15:00:11.000000', NULL, NULL, 'View roles', 'READ_ROLE', 2),
(7, '2025-07-02 15:00:11.000000', NULL, NULL, 'Edit roles', 'UPDATE_ROLE', 2),
(8, '2025-07-02 15:00:11.000000', NULL, NULL, 'Delete roles', 'DELETE_ROLE', 2),
(9, '2025-07-02 15:00:11.000000', NULL, NULL, 'Create new permissions', 'CREATE_PERMISSION', 3),
(10, '2025-07-02 15:00:11.000000', NULL, NULL, 'View permissions', 'READ_PERMISSION', 3),
(11, '2025-07-02 15:00:11.000000', NULL, NULL, 'Edit permissions', 'UPDATE_PERMISSION', 3),
(12, '2025-07-02 15:00:11.000000', NULL, NULL, 'Delete permissions', 'DELETE_PERMISSION', 3),
(13, '2025-07-02 15:00:11.000000', NULL, NULL, 'Create new groups', 'CREATE_GROUP', 4),
(14, '2025-07-02 15:00:11.000000', NULL, NULL, 'View groups', 'READ_GROUP', 4),
(15, '2025-07-02 15:00:11.000000', NULL, NULL, 'Edit groups', 'UPDATE_GROUP', 4),
(16, '2025-07-02 15:00:11.000000', NULL, NULL, 'Delete groups', 'DELETE_GROUP', 4);

-- --------------------------------------------------------

--
-- Table structure for table `t_01_02`
--

CREATE TABLE `t_01_02` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_02` varchar(200) DEFAULT NULL,
  `f_01` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_01_02`
--

INSERT INTO `t_01_02` (`f_00`, `a_01`, `a_02`, `a_03`, `f_02`, `f_01`) VALUES
(1, '2025-07-02 15:00:47.000000', NULL, NULL, 'Manage users', 'USER_MANAGEMENT'),
(2, '2025-07-02 15:00:47.000000', NULL, NULL, 'Manage roles', 'ROLE_MANAGEMENT'),
(3, '2025-07-02 15:00:47.000000', NULL, NULL, 'Manage permissions', 'PERMISSION_MANAGEMENT'),
(4, '2025-07-02 15:00:47.000000', NULL, NULL, 'Manage groups', 'GROUP_MANAGEMENT');

-- --------------------------------------------------------

--
-- Table structure for table `t_01_03`
--

CREATE TABLE `t_01_03` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_02` varchar(200) DEFAULT NULL,
  `f_01` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_01_03`
--

INSERT INTO `t_01_03` (`f_00`, `a_01`, `a_02`, `a_03`, `f_02`, `f_01`) VALUES
(1, '2025-07-02 14:59:33.000000', NULL, NULL, 'Administrator with full access', 'ADMIN'),
(2, '2025-07-02 14:59:39.000000', NULL, NULL, 'Regular user with limited access', 'USER'),
(3, '2025-07-02 14:59:42.000000', NULL, NULL, 'Manager with elevated privileges', 'MANAGER');

-- --------------------------------------------------------

--
-- Table structure for table `t_01_04`
--

CREATE TABLE `t_01_04` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_02` varchar(200) DEFAULT NULL,
  `f_01` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_01_04`
--

INSERT INTO `t_01_04` (`f_00`, `a_01`, `a_02`, `a_03`, `f_02`, `f_01`) VALUES
(1, '2025-07-02 14:59:48.000000', '2025-07-02 14:58:56.000000', 0, 'Information Technology Department', 'IT_DEPARTMENT'),
(2, '2025-07-02 14:59:52.000000', '2025-07-02 14:59:02.000000', 0, 'Human Resources Department', 'HR_DEPARTMENT'),
(3, '2025-07-02 14:59:55.000000', '2025-07-02 14:59:06.000000', 0, 'Finance Department', 'FINANCE_DEPARTMENT');

-- --------------------------------------------------------

--
-- Table structure for table `t_01_05`
--

CREATE TABLE `t_01_05` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_04` bit(1) DEFAULT NULL,
  `f_05` bit(1) DEFAULT NULL,
  `f_06` bit(1) DEFAULT NULL,
  `f_02` varchar(100) NOT NULL,
  `f_07` bit(1) DEFAULT NULL,
  `f_03` varchar(120) NOT NULL,
  `f_01` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_01_05`
--

INSERT INTO `t_01_05` (`f_00`, `a_01`, `a_02`, `a_03`, `f_04`, `f_05`, `f_06`, `f_02`, `f_07`, `f_03`, `f_01`) VALUES
(1, '2025-07-02 14:56:16.000000', '2025-07-02 14:57:13.000000', 0, b'1', b'1', b'1', 'admin@example.com', b'1', '$2a$12$QTimz.Aqo014RCzXq5T.c.wpwOn3Ysk.Q34N7n9RfXlMgBTeJ642m', 'admin'),
(2, '2025-07-07 12:55:18.000000', NULL, NULL, b'1', b'1', b'1', 'usertest@example.com', b'1', '$2a$12$QTimz.Aqo014RCzXq5T.c.wpwOn3Ysk.Q34N7n9RfXlMgBTeJ642m', 'user');

-- --------------------------------------------------------

--
-- Table structure for table `t_02_01`
--

CREATE TABLE `t_02_01` (
  `f_00` bigint(20) NOT NULL,
  `f_01` varchar(50) NOT NULL,
  `f_08` datetime(6) NOT NULL,
  `f_09` varchar(50) DEFAULT NULL,
  `f_06` varchar(255) DEFAULT NULL,
  `f_05` varchar(10) DEFAULT NULL,
  `f_07` varchar(100) NOT NULL,
  `f_04` varchar(500) DEFAULT NULL,
  `f_03` bigint(20) DEFAULT NULL,
  `f_02` varchar(50) NOT NULL,
  `f_10` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_10_01`
--

CREATE TABLE `t_10_01` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_02` varchar(500) DEFAULT NULL,
  `f_01` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_10_02`
--

CREATE TABLE `t_10_02` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_02` varchar(500) DEFAULT NULL,
  `f_01` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_10_03`
--

CREATE TABLE `t_10_03` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_02` varchar(50) DEFAULT NULL,
  `f_03` varchar(500) DEFAULT NULL,
  `f_01` varchar(100) NOT NULL,
  `f_04` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_10_04`
--

CREATE TABLE `t_10_04` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_01` varchar(50) NOT NULL,
  `f_05` varchar(20) NOT NULL,
  `f_02` varchar(100) NOT NULL,
  `f_06` date NOT NULL,
  `f_03` varchar(100) NOT NULL,
  `f_04` varchar(100) NOT NULL,
  `f_07` date DEFAULT NULL,
  `f_08` bigint(20) NOT NULL,
  `f_09` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_20_01`
--

CREATE TABLE `t_20_01` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_01` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_20_01`
--

INSERT INTO `t_20_01` (`f_00`, `a_01`, `a_02`, `a_03`, `f_01`) VALUES
(1, '2025-08-12 10:35:51.000000', NULL, NULL, 'Nord'),
(2, '2025-08-12 10:35:51.000000', NULL, NULL, 'Sud');

-- --------------------------------------------------------

--
-- Table structure for table `t_20_02`
--

CREATE TABLE `t_20_02` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_02` varchar(25) NOT NULL,
  `f_01` varchar(100) NOT NULL,
  `f_03` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_20_02`
--

INSERT INTO `t_20_02` (`f_00`, `a_01`, `a_02`, `a_03`, `f_02`, `f_01`, `f_03`) VALUES
(1, '2025-08-12 10:35:51.000000', NULL, NULL, 'RTO', 'Region de Transport Ouest', 1),
(2, '2025-08-12 10:35:51.000000', NULL, NULL, 'RTC', 'Region de Transport ', 1),
(3, '2025-08-12 10:35:51.000000', NULL, NULL, 'RTE', 'Region de Transport ', 1),
(4, '2025-08-12 10:35:51.000000', NULL, NULL, 'GPDF', 'Pedro Duran Farel', 1),
(5, '2025-08-12 10:35:51.000000', NULL, NULL, 'GEM', 'Enrico Mattei', 1),
(6, '2025-08-12 10:35:51.000000', NULL, NULL, 'CDHL', 'Centre de Dispatching d’Hydrocarbures Liquides', 2),
(7, '2025-08-12 10:35:51.000000', NULL, NULL, 'CNDG', 'Centre National de Dispatching Gaz', 2),
(8, '2025-08-12 10:35:51.000000', NULL, NULL, 'HRM', 'Region Hassi R\'Mel', 2),
(9, '2025-08-12 10:35:51.000000', NULL, NULL, 'RTH', 'Region ', 2),
(10, '2025-08-12 10:35:51.000000', NULL, NULL, 'RTI', 'Region de Transport ', 2),
(11, '2025-08-12 10:35:51.000000', NULL, NULL, 'Reggane', 'Rggane', 2);

-- --------------------------------------------------------

--
-- Table structure for table `t_20_03`
--

CREATE TABLE `t_20_03` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_02` varchar(50) NOT NULL,
  `f_03` double NOT NULL,
  `f_04` double NOT NULL,
  `f_01` varchar(100) NOT NULL,
  `f_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_20_03`
--

INSERT INTO `t_20_03` (`f_00`, `a_01`, `a_02`, `a_03`, `f_02`, `f_03`, `f_04`, `f_01`, `f_05`) VALUES
(1, '2025-08-12 10:35:51.000000', NULL, NULL, 'Alger', 0, 0, 'Alger', 1),
(2, '2025-08-12 10:35:51.000000', NULL, NULL, 'Alrar', 0, 0, 'Alrar', 1),
(3, '2025-08-12 10:35:51.000000', NULL, NULL, 'Arzew', 0, 0, 'Arzew', 1),
(4, '2025-08-12 10:35:51.000000', NULL, NULL, 'Arzew/Beni Saf', 0, 0, 'Arzew/Beni Saf', 1),
(5, '2025-08-12 10:35:51.000000', NULL, NULL, 'Béjaia', 0, 0, 'Béjaia', 1),
(6, '2025-08-12 10:35:51.000000', NULL, NULL, 'Beni Mensour', 0, 0, 'Beni Mensour', 1),
(7, '2025-08-12 10:35:51.000000', NULL, NULL, 'Beni Saf', 0, 0, 'Beni Saf', 1),
(8, '2025-08-12 10:35:51.000000', NULL, NULL, 'Bordj Menail', 0, 0, 'Bordj Menail', 1),
(9, '2025-08-12 10:35:51.000000', NULL, NULL, 'El Aricha', 0, 0, 'El Aricha', 1),
(10, '2025-08-12 10:35:51.000000', NULL, NULL, 'El Borma', 0, 0, 'El Borma', 1),
(11, '2025-08-12 10:35:51.000000', NULL, NULL, 'El Kala', 0, 0, 'El Kala', 1),
(12, '2025-08-12 10:35:51.000000', NULL, NULL, 'El Skhira', 0, 0, 'El Skhira', 1),
(13, '2025-08-12 10:35:51.000000', NULL, NULL, 'Gassi Touil', 0, 0, 'Gassi Touil', 1),
(14, '2025-08-12 10:35:51.000000', NULL, NULL, 'Haoud el Hamra', 0, 0, 'Haoud el Hamra', 1),
(15, '2025-08-12 10:35:51.000000', NULL, NULL, 'Hassi Berkine', 0, 0, 'Hassi Berkine', 1),
(16, '2025-08-12 10:35:51.000000', NULL, NULL, 'Hassi R\'mel', 0, 0, 'Hassi R\'mel', 1),
(17, '2025-08-12 10:35:51.000000', NULL, NULL, 'In Amenas', 0, 0, 'In Amenas', 1),
(18, '2025-08-12 10:35:51.000000', NULL, NULL, 'Mechtatine', 0, 0, 'Mechtatine', 1),
(19, '2025-08-12 10:35:51.000000', NULL, NULL, 'Medjedel', 0, 0, 'Medjedel', 1),
(20, '2025-08-12 10:35:51.000000', NULL, NULL, 'Mesdar', 0, 0, 'Mesdar', 1),
(21, '2025-08-12 10:35:51.000000', NULL, NULL, 'Mouctaa Douze', 0, 0, 'Mouctaa Douze', 1),
(22, '2025-08-12 10:35:51.000000', NULL, NULL, 'Ohanet', 0, 0, 'Ohanet', 1),
(23, '2025-08-12 10:35:51.000000', NULL, NULL, 'Oued Saf Saf', 0, 0, 'Oued Saf Saf', 1),
(24, '2025-08-12 10:35:51.000000', NULL, NULL, 'PC4-GR5', 0, 0, 'PC4-GR5', 1),
(25, '2025-08-12 10:35:51.000000', NULL, NULL, 'PK66 - Ohanet', 0, 0, 'PK66 - Ohanet', 1),
(26, '2025-08-12 10:35:51.000000', NULL, NULL, 'Reggane', 0, 0, 'Reggane', 1),
(27, '2025-08-12 10:35:51.000000', NULL, NULL, 'Rhoud Nouss', 0, 0, 'Rhoud Nouss', 1),
(28, '2025-08-12 10:35:51.000000', NULL, NULL, 'Rhourd El Baguel', 0, 0, 'Rhourd El Baguel', 1),
(29, '2025-08-12 10:35:51.000000', NULL, NULL, 'Skikda', 0, 0, 'Skikda', 1),
(30, '2025-08-12 10:35:51.000000', NULL, NULL, 'Tamlouka', 0, 0, 'Tamlouka', 1);

-- --------------------------------------------------------

--
-- Table structure for table `t_20_04`
--

CREATE TABLE `t_20_04` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_02` varchar(50) NOT NULL,
  `f_03` double NOT NULL,
  `f_08` varchar(500) NOT NULL,
  `f_05` double NOT NULL,
  `f_07` bit(1) NOT NULL,
  `f_01` varchar(100) NOT NULL,
  `f_06` double NOT NULL,
  `f_04` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_20_04`
--

INSERT INTO `t_20_04` (`f_00`, `a_01`, `a_02`, `a_03`, `f_02`, `f_03`, `f_08`, `f_05`, `f_07`, `f_01`, `f_06`, `f_04`) VALUES
(1, '2025-08-12 10:35:51.000000', NULL, NULL, 'G', 0, 'Gaz naturel', 0, b'1', 'Gaz naturel', 0, 0),
(2, '2025-08-12 10:35:51.000000', NULL, NULL, 'O', 0, 'Pétrole brut', 0, b'1', 'Pétrole brut', 0, 0),
(3, '2025-08-12 10:35:51.000000', NULL, NULL, 'N', 0, 'Condensat', 0, b'1', 'Condensat', 0, 0),
(4, '2025-08-12 10:35:51.000000', NULL, NULL, 'L', 0, 'Gaz de Pétrole Liquéfié (GPL)', 0, b'1', 'Gaz de Pétrole Liquéfié (GPL)', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `t_20_05`
--

CREATE TABLE `t_20_05` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_02` varchar(500) NOT NULL,
  `f_01` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_20_05`
--

INSERT INTO `t_20_05` (`f_00`, `a_01`, `a_02`, `a_03`, `f_02`, `f_01`) VALUES
(1, '2025-08-12 10:35:51.000000', NULL, NULL, 'Puit d\'extraction du pétrole brut.', 'Puit Pétrole'),
(2, '2025-08-12 10:35:51.000000', NULL, NULL, 'Puit d\'extraction du gaz naturel.', 'Puit Gaz'),
(3, '2025-08-12 10:35:51.000000', NULL, NULL, 'Puit d\'extraction des condensats.', 'Puit Condensat'),
(4, '2025-08-12 10:35:51.000000', NULL, NULL, 'Unité de liquification du gaz du pétrole.', 'Unité GPL'),
(5, '2025-08-12 10:35:51.000000', NULL, NULL, 'Liquéfie le gaz naturel pour le transport ou le regazéifie pour utilisation.', 'Terminal GNL'),
(6, '2025-08-12 10:35:51.000000', NULL, NULL, 'Port Pétrolier et gazier.', 'Terminal d\'exportation'),
(7, '2025-08-12 10:35:51.000000', NULL, NULL, 'Augmente la pression du gaz pour le transport par pipeline.', 'Station de compression'),
(8, '2025-08-12 10:35:51.000000', NULL, NULL, 'Maintient le débit du pétrole dans les pipelines en augmentant la pression.', 'Station de pompage'),
(9, '2025-08-12 10:35:51.000000', NULL, NULL, 'Stocke le pétrole brut, les LGN (liquides de gaz naturel) ou les produits raffinés.', 'Réservoirs de stockage'),
(10, '2025-08-12 10:35:51.000000', NULL, NULL, 'Brûle les hydrocarbures excédentaires pour éviter les surpressions.', 'Système de torchérage'),
(11, '2025-08-12 10:35:51.000000', NULL, NULL, 'Transforme le pétrole brut en carburants et produits pétrochimiques.', 'Raffinerie'),
(12, '2025-08-12 10:35:51.000000', NULL, NULL, 'Mesure les volumes d\'hydrocarbures pour le transfert de propriété.', 'Station de comptage'),
(13, '2025-08-12 10:35:51.000000', NULL, NULL, 'Centre Dispatsching Gaz', 'Centre Dispatsching Gaz'),
(14, '2025-08-12 10:35:51.000000', NULL, NULL, 'Centre Dispatsching Liquide', 'Centre Dispatsching Liquide');

-- --------------------------------------------------------

--
-- Table structure for table `t_20_06`
--

CREATE TABLE `t_20_06` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_02` varchar(500) NOT NULL,
  `f_01` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_20_07`
--

CREATE TABLE `t_20_07` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_02` varchar(500) NOT NULL,
  `f_01` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_20_07`
--

INSERT INTO `t_20_07` (`f_00`, `a_01`, `a_02`, `a_03`, `f_02`, `f_01`) VALUES
(1, '2025-08-12 10:35:51.000000', NULL, NULL, 'ACTIVE', 'ACTIVE'),
(2, '2025-08-12 10:35:51.000000', NULL, NULL, 'MAINTENANCE', 'MAINTENANCE'),
(3, '2025-08-12 10:35:51.000000', NULL, NULL, 'DECOMMISSIONED', 'DECOMMISSIONED'),
(4, '2025-08-12 10:35:51.000000', NULL, NULL, 'PANNE', 'FAILED');

-- --------------------------------------------------------

--
-- Table structure for table `t_20_08`
--

CREATE TABLE `t_20_08` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_02` varchar(50) NOT NULL,
  `f_01` varchar(100) NOT NULL,
  `f_05` bigint(20) NOT NULL,
  `f_04` bigint(20) NOT NULL,
  `f_03` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_20_08`
--

INSERT INTO `t_20_08` (`f_00`, `a_01`, `a_02`, `a_03`, `f_02`, `f_01`, `f_05`, `f_04`, `f_03`) VALUES
(1, '2025-08-12 10:35:51.000000', NULL, NULL, 'Raffinerie d\'Alger', 'Raffinerie d\'Alger', 11, 1, 1),
(2, '2025-08-12 10:35:51.000000', NULL, NULL, 'Puit gaz de Alrar', 'Puit gaz de Alrar', 2, 2, 1),
(3, '2025-08-12 10:35:51.000000', NULL, NULL, 'Unité GPL d\'Alrar', 'Unité GPL d\'Alrar', 4, 2, 1),
(4, '2025-08-12 10:35:51.000000', NULL, NULL, 'Terminal d\'exportation d\'Arzew', 'Terminal d\'exportation d\'Arzew', 6, 3, 1),
(5, '2025-08-12 10:35:51.000000', NULL, NULL, 'Terminal GNL d\'Arzew', 'Terminal GNL d\'Arzew', 5, 3, 1),
(6, '2025-08-12 10:35:51.000000', NULL, NULL, 'Terminal GNL d\'Arzew/Beni Saf', 'Terminal GNL d\'Arzew/Beni Saf', 5, 4, 1),
(7, '2025-08-12 10:35:51.000000', NULL, NULL, 'Terminal d\'exportation de Béjaia', 'Terminal d\'exportation de Béjaia', 6, 5, 1),
(8, '2025-08-12 10:35:51.000000', NULL, NULL, 'Puit pétrolier de Beni Mensour', 'Puit pétrolier de Beni Mensour', 1, 6, 1),
(9, '2025-08-12 10:35:51.000000', NULL, NULL, 'Terminal GNL de Beni Saf', 'Terminal GNL de Beni Saf', 5, 7, 1),
(10, '2025-08-12 10:35:51.000000', NULL, NULL, 'Terminal d\'exportation de Bordj Menail', 'Terminal d\'exportation de Bordj Menail', 6, 8, 1),
(11, '2025-08-12 10:35:51.000000', NULL, NULL, 'Terminal d\'exportation d\'El Aricha', 'Terminal d\'exportation d\'El Aricha', 6, 9, 1),
(12, '2025-08-12 10:35:51.000000', NULL, NULL, 'Puit pétrolier d\'El Borma', 'Puit pétrolier d\'El Borma', 1, 8, 1),
(13, '2025-08-12 10:35:51.000000', NULL, NULL, 'Terminal GNL d\'El Kala', 'Terminal GNL d\'El Kala', 5, 9, 1),
(14, '2025-08-12 10:35:51.000000', NULL, NULL, 'Terminal d\'exportation d\'El Skhira', 'Terminal d\'exportation d\'El Skhira', 6, 10, 1),
(15, '2025-08-12 10:35:51.000000', NULL, NULL, 'Station de pompage Gassi Touil', 'Station de pompage Gassi Touil', 8, 11, 1),
(16, '2025-08-12 10:35:51.000000', NULL, NULL, 'CDHL', 'CDHL', 14, 12, 1),
(17, '2025-08-12 10:35:51.000000', NULL, NULL, 'Puit pétrolier de Hassi Berkine', 'Puit pétrolier de Hassi Berkine', 1, 13, 1),
(18, '2025-08-12 10:35:51.000000', NULL, NULL, 'CNDG', 'CNDG', 13, 14, 1),
(19, '2025-08-12 10:35:51.000000', NULL, NULL, 'Puit pétrolier d\'In Amenas', 'Puit pétrolier d\'In Amenas', 1, 15, 1),
(20, '2025-08-12 10:35:51.000000', NULL, NULL, 'Station de compression Mechtatine', 'Station de compression Mechtatine', 7, 16, 1),
(21, '2025-08-12 10:35:51.000000', NULL, NULL, 'Station de compression Medjedel', 'Station de compression Medjedel', 7, 17, 1),
(22, '2025-08-12 10:35:51.000000', NULL, NULL, 'Puit pétrolier de Mesdar', 'Puit pétrolier de Mesdar', 1, 18, 1),
(23, '2025-08-12 10:35:51.000000', NULL, NULL, 'Station de pompage Mesdar', 'Station de pompage Mesdar', 8, 18, 1),
(24, '2025-08-12 10:35:51.000000', NULL, NULL, 'Puit gaz de Mouctaa Douze', 'Puit gaz de Mouctaa Douze', 2, 19, 1),
(25, '2025-08-12 10:35:51.000000', NULL, NULL, 'Unité GPL d\'Ohanet', 'Unité GPL d\'Ohanet', 4, 20, 1),
(26, '2025-08-12 10:35:51.000000', NULL, NULL, 'Terminal d\'exportation d\'Oued Saf Saf', 'Terminal d\'exportation d\'Oued Saf Saf', 6, 21, 1),
(27, '2025-08-12 10:35:51.000000', NULL, NULL, 'Puit gaz de PC4-GR5', 'Puit gaz de PC4-GR5', 2, 22, 1),
(28, '2025-08-12 10:35:51.000000', NULL, NULL, 'Puit Condensat du PK66 - Ohanet', 'Puit Condensat du PK66 - Ohanet', 3, 23, 1),
(29, '2025-08-12 10:35:51.000000', NULL, NULL, 'Puit gaz de Reggane', 'Puit gaz de Reggane', 2, 24, 1),
(30, '2025-08-12 10:35:51.000000', NULL, NULL, 'Puit gaz de Rhoud Nouss', 'Puit gaz de Rhoud Nouss', 2, 25, 1),
(31, '2025-08-12 10:35:51.000000', NULL, NULL, 'Puit pétrolier de Rhourd El Baguel', 'Puit pétrolier de Rhourd El Baguel', 1, 26, 1),
(32, '2025-08-12 10:35:51.000000', NULL, NULL, 'Terminal d\'exportation de Skikda', 'Terminal d\'exportation de Skikda', 6, 27, 1),
(33, '2025-08-12 10:35:51.000000', NULL, NULL, 'Terminal GNL de Skikda', 'Terminal GNL de Skikda', 5, 27, 1),
(34, '2025-08-12 10:35:51.000000', NULL, NULL, 'Station de compression Tamlouka', 'Station de compression Tamlouka', 7, 28, 1);

-- --------------------------------------------------------

--
-- Table structure for table `t_20_09`
--

CREATE TABLE `t_20_09` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_02` varchar(50) NOT NULL,
  `f_07` date NOT NULL,
  `f_08` date NOT NULL,
  `f_03` varchar(100) NOT NULL,
  `f_06` date NOT NULL,
  `f_04` varchar(50) NOT NULL,
  `f_01` varchar(100) NOT NULL,
  `f_05` varchar(100) NOT NULL,
  `f_10` bigint(20) NOT NULL,
  `f_11` bigint(20) NOT NULL,
  `f_09` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_20_10`
--

CREATE TABLE `t_20_10` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_02` varchar(50) NOT NULL,
  `f_01` varchar(100) NOT NULL,
  `f_03` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_20_10`
--

INSERT INTO `t_20_10` (`f_00`, `a_01`, `a_02`, `a_03`, `f_02`, `f_01`, `f_03`) VALUES
(1, '2025-08-12 10:35:51.000000', NULL, NULL, 'OZ1/OZ2', 'STC OZ1/OZ2', 2),
(2, '2025-08-12 10:35:51.000000', NULL, NULL, 'OK1', 'STC OK1', 2),
(3, '2025-08-12 10:35:51.000000', NULL, NULL, 'OB1/OG1', 'STC OB1/OG1', 2),
(4, '2025-08-12 10:35:51.000000', NULL, NULL, 'OT1', 'STC OT1', 2),
(5, '2025-08-12 10:35:51.000000', NULL, NULL, 'OH1', 'STC OH1', 2),
(6, '2025-08-12 10:35:51.000000', NULL, NULL, 'OD1/OD3/OH2', 'STC OD1/OD3/OH2', 2),
(7, '2025-08-12 10:35:51.000000', NULL, NULL, 'OH3/OH4', 'STC OH3/OH4', 2),
(8, '2025-08-12 10:35:51.000000', NULL, NULL, 'NZ1', 'STC NZ1', 3),
(9, '2025-08-12 10:35:51.000000', NULL, NULL, 'NK1', 'STC NK1', 3),
(10, '2025-08-12 10:35:51.000000', NULL, NULL, 'NH2', 'STC NH2', 3),
(11, '2025-08-12 10:35:51.000000', NULL, NULL, 'LZ1/LZ2', 'STC LZ1/LZ2', 4),
(12, '2025-08-12 10:35:51.000000', NULL, NULL, 'LR1/DLR1/ELR1', 'STC LR1/DLR1/ELR1', 4),
(13, '2025-08-12 10:35:51.000000', NULL, NULL, 'GZ0', 'STC GZ0', 1),
(14, '2025-08-12 10:35:51.000000', NULL, NULL, 'GZ1/GZ2/GZ3', 'STC GZ1/GZ2/GZ3', 1),
(15, '2025-08-12 10:35:51.000000', NULL, NULL, 'GZ4', 'STC GZ4', 1),
(16, '2025-08-12 10:35:51.000000', NULL, NULL, 'GPDF', 'STC GPDF', 1),
(17, '2025-08-12 10:35:51.000000', NULL, NULL, 'GK1/GK2', 'STC GK1/GK2', 1),
(18, '2025-08-12 10:35:51.000000', NULL, NULL, 'GK3', 'STC GK3', 1),
(19, '2025-08-12 10:35:51.000000', NULL, NULL, 'GO1/GO2/GO3', 'STC GO1/GO2/GO3', 1),
(20, '2025-08-12 10:35:51.000000', NULL, NULL, 'GG1', 'STC GG1', 1),
(21, '2025-08-12 10:35:51.000000', NULL, NULL, 'GR1/GR2/GR4/GR6', 'STC GR1/GR2/GR4/GR6', 1),
(22, '2025-08-12 10:35:51.000000', NULL, NULL, 'GR5', 'STC GR5', 1);

-- --------------------------------------------------------

--
-- Table structure for table `t_20_11`
--

CREATE TABLE `t_20_11` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_01` varchar(50) NOT NULL,
  `f_06` date NOT NULL,
  `f_05` varchar(50) NOT NULL,
  `f_02` double NOT NULL,
  `f_03` double NOT NULL,
  `f_04` double NOT NULL,
  `f_10` bigint(20) NOT NULL,
  `f_09` bigint(20) NOT NULL,
  `f_07` bigint(20) NOT NULL,
  `f_08` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_20_11`
--

INSERT INTO `t_20_11` (`f_00`, `a_01`, `a_02`, `a_03`, `f_01`, `f_06`, `f_05`, `f_02`, `f_03`, `f_04`, `f_10`, `f_09`, `f_07`, `f_08`) VALUES
(1, '2025-08-12 10:35:04.000000', NULL, NULL, 'OZ1', '0000-00-00', '0', 28, 801, 0, 4, 16, 1, 1),
(2, '2025-08-12 10:35:04.000000', NULL, NULL, 'OZ2', '0000-00-00', '0', 34, 821, 0, 4, 16, 1, 1),
(3, '2025-08-12 10:35:04.000000', NULL, NULL, 'OK1', '0000-00-00', '0', 34, 646, 0, 32, 16, 1, 2),
(4, '2025-08-12 10:35:04.000000', NULL, NULL, 'OB1', '0000-00-00', '0', 24, 668, 0, 7, 16, 1, 3),
(5, '2025-08-12 10:35:04.000000', NULL, NULL, 'OG1', '0000-00-00', '0', 20, 145, 0, 1, 8, 1, 3),
(6, '2025-08-12 10:35:04.000000', NULL, NULL, 'OT1', '0000-00-00', '0', 24, 265, 0, 14, 19, 1, 4),
(7, '2025-08-12 10:35:04.000000', NULL, NULL, 'OH1', '0000-00-00', '0', 30, 630, 0, 16, 19, 1, 5),
(8, '2025-08-12 10:35:04.000000', NULL, NULL, 'OD1', '0000-00-00', '0', 20, 272, 0, 23, 12, 1, 6),
(9, '2025-08-12 10:35:04.000000', NULL, NULL, 'OD3', '0000-00-00', '0', 20, 33, 0, 23, 31, 1, 6),
(10, '2025-08-12 10:35:04.000000', NULL, NULL, 'OH2', '0000-00-00', '0', 26, 108, 0, 16, 22, 1, 6),
(11, '2025-08-12 10:35:04.000000', NULL, NULL, 'OH3', '0000-00-00', '0', 30, 292, 0, 16, 17, 1, 7),
(12, '2025-08-12 10:35:04.000000', NULL, NULL, 'OH4', '0000-00-00', '0', 30, 292, 0, 16, 17, 1, 7),
(13, '2025-08-12 10:35:04.000000', NULL, NULL, 'NZ1', '0000-00-00', '0', 28, 507, 0, 4, 18, 1, 8),
(14, '2025-08-12 10:35:04.000000', NULL, NULL, 'NK1', '0000-00-00', '0', 30, 646, 0, 33, 16, 1, 9),
(15, '2025-08-12 10:35:04.000000', NULL, NULL, 'NH2', '0000-00-00', '0', 24, 565, 0, 16, 28, 1, 10),
(16, '2025-08-12 10:35:04.000000', NULL, NULL, 'LZ1', '0000-00-00', '0', 24, 503, 0, 4, 18, 1, 11),
(17, '2025-08-12 10:35:04.000000', NULL, NULL, 'LZ2', '0000-00-00', '0', 24, 495, 0, 4, 18, 1, 11),
(18, '2025-08-12 10:35:04.000000', NULL, NULL, 'LR1', '0000-00-00', '0', 24, 1022, 0, 18, 3, 1, 12),
(19, '2025-08-12 10:35:04.000000', NULL, NULL, 'DLR1', '0000-00-00', '0', 16, 404, 0, 15, 25, 1, 12),
(20, '2025-08-12 10:35:04.000000', NULL, NULL, 'ELR1', '0000-00-00', '0', 24, 336, 0, 18, 16, 1, 12),
(21, '2025-08-12 10:35:04.000000', NULL, NULL, 'GZ0', '0000-00-00', '0', 24, 509, 0, 5, 18, 1, 13),
(22, '2025-08-12 10:35:04.000000', NULL, NULL, 'GZ1', '0000-00-00', '0', 40, 507, 0, 6, 18, 1, 14),
(23, '2025-08-12 10:35:04.000000', NULL, NULL, 'GZ2', '0000-00-00', '0', 40, 511, 0, 6, 18, 1, 14),
(24, '2025-08-12 10:35:04.000000', NULL, NULL, 'GZ3/RGZ3', '0000-00-00', '0', 42, 517, 0, 6, 18, 1, 14),
(25, '2025-08-12 10:35:04.000000', NULL, NULL, 'GZ4', '0000-00-00', '0', 48, 633, 0, 5, 18, 1, 15),
(26, '2025-08-12 10:35:04.000000', NULL, NULL, 'GPDF', '0000-00-00', '0', 48, 521, 0, 11, 18, 1, 16),
(27, '2025-08-12 10:35:04.000000', NULL, NULL, 'EGPDF', '0000-00-00', '0', 48, 197, 0, 9, 11, 1, 16),
(28, '2025-08-12 10:35:04.000000', NULL, NULL, 'GK1', '0000-00-00', '0', 40, 575, 0, 33, 18, 1, 17),
(29, '2025-08-12 10:35:04.000000', NULL, NULL, 'GK2', '0000-00-00', '0', 42, 576, 0, 33, 18, 1, 17),
(30, '2025-08-12 10:35:04.000000', NULL, NULL, 'GK3', '0000-00-00', '0', 48, 786, 0, 20, 18, 1, 18),
(31, '2025-08-12 10:35:04.000000', NULL, NULL, 'GO1', '0000-00-00', '0', 48, 1644, 0, 26, 18, 1, 18),
(32, '2025-08-12 10:35:04.000000', NULL, NULL, 'GO2', '0000-00-00', '0', 48, 0, 0, 26, 18, 1, 19),
(33, '2025-08-12 10:35:04.000000', NULL, NULL, 'GO3', '0000-00-00', '0', 48, 0, 0, 26, 18, 1, 19),
(34, '2025-08-12 10:35:04.000000', NULL, NULL, 'GG1', '0000-00-00', '0', 42, 437, 0, 10, 18, 1, 20),
(35, '2025-08-12 10:35:04.000000', NULL, NULL, 'RGG1', '0000-00-00', '0', 42, 208, 0, 10, 21, 1, 20),
(36, '2025-08-12 10:35:04.000000', NULL, NULL, 'GR1', '0000-00-00', '0', 48, 966, 0, 18, 2, 1, 21),
(37, '2025-08-12 10:35:04.000000', NULL, NULL, 'GR2', '0000-00-00', '0', 48, 966, 0, 18, 2, 1, 21),
(38, '2025-08-12 10:35:04.000000', NULL, NULL, 'GR4', '0000-00-00', '0', 48, 535, 0, 18, 30, 1, 21),
(39, '2025-08-12 10:35:04.000000', NULL, NULL, 'GR6', '0000-00-00', '0', 48, 531, 0, 18, 30, 1, 21),
(40, '2025-08-12 10:35:04.000000', NULL, NULL, 'GR5', '0000-00-00', '0', 48, 770, 0, 18, 29, 1, 22),
(41, '2025-08-12 10:35:04.000000', NULL, NULL, 'GR7', '0000-00-00', '0', 48, 345, 0, 18, 27, 1, 22);

-- --------------------------------------------------------

--
-- Table structure for table `t_20_12`
--

CREATE TABLE `t_20_12` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_01` varchar(50) NOT NULL,
  `f_03` double NOT NULL,
  `f_02` double NOT NULL,
  `f_04` varchar(50) NOT NULL,
  `f_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_30_01`
--

CREATE TABLE `t_30_01` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_02` datetime(6) DEFAULT NULL,
  `f_03` double NOT NULL,
  `f_07` varchar(500) DEFAULT NULL,
  `f_06` varchar(25) NOT NULL,
  `f_04` double NOT NULL,
  `f_01` datetime(6) NOT NULL,
  `f_05` double NOT NULL,
  `f_08` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_30_02`
--

CREATE TABLE `t_30_02` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_03` double NOT NULL,
  `f_01` datetime(6) NOT NULL,
  `f_06` varchar(500) DEFAULT NULL,
  `f_04` double NOT NULL,
  `f_05` double NOT NULL,
  `f_02` double NOT NULL,
  `f_08` bigint(20) NOT NULL,
  `f_07` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_30_03`
--

CREATE TABLE `t_30_03` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_04` varchar(500) DEFAULT NULL,
  `f_01` date NOT NULL,
  `f_03` varchar(500) NOT NULL,
  `f_02` varchar(25) NOT NULL,
  `f_05` varchar(500) DEFAULT NULL,
  `f_07` bigint(20) NOT NULL,
  `f_06` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_30_04`
--

CREATE TABLE `t_30_04` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_05` double NOT NULL,
  `f_04` varchar(25) NOT NULL,
  `f_02` double NOT NULL,
  `f_01` datetime(6) NOT NULL,
  `f_06` varchar(500) DEFAULT NULL,
  `f_03` double NOT NULL,
  `f_07` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_30_05`
--

CREATE TABLE `t_30_05` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_03` varchar(500) DEFAULT NULL,
  `f_01` datetime(6) NOT NULL,
  `f_02` varchar(50) NOT NULL,
  `f_04` bigint(20) NOT NULL,
  `f_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_30_06`
--

CREATE TABLE `t_30_06` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_03` double NOT NULL,
  `f_01` double NOT NULL,
  `f_02` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_30_07`
--

CREATE TABLE `t_30_07` (
  `f_00` bigint(20) NOT NULL,
  `a_01` datetime(6) NOT NULL,
  `a_02` datetime(6) DEFAULT NULL,
  `a_03` bigint(20) DEFAULT NULL,
  `f_001` date NOT NULL,
  `f_109` double DEFAULT NULL,
  `f_106` double DEFAULT NULL,
  `f_105` double DEFAULT NULL,
  `f_102` double DEFAULT NULL,
  `f_103` double DEFAULT NULL,
  `f_104` double DEFAULT NULL,
  `f_108` double DEFAULT NULL,
  `f_121` double DEFAULT NULL,
  `f_118` double DEFAULT NULL,
  `f_117` double DEFAULT NULL,
  `f_107` double DEFAULT NULL,
  `f_114` double DEFAULT NULL,
  `f_110` double DEFAULT NULL,
  `f_111` double DEFAULT NULL,
  `f_112` double DEFAULT NULL,
  `f_115` double DEFAULT NULL,
  `f_116` double DEFAULT NULL,
  `f_113` double DEFAULT NULL,
  `f_222` double DEFAULT NULL,
  `f_224` double DEFAULT NULL,
  `f_223` double DEFAULT NULL,
  `f_122` double DEFAULT NULL,
  `f_124` double DEFAULT NULL,
  `f_123` double DEFAULT NULL,
  `f_119` double DEFAULT NULL,
  `f_120` double DEFAULT NULL,
  `f_309` double DEFAULT NULL,
  `f_306` double DEFAULT NULL,
  `f_305` double DEFAULT NULL,
  `f_302` double DEFAULT NULL,
  `f_303` double DEFAULT NULL,
  `f_304` double DEFAULT NULL,
  `f_308` double DEFAULT NULL,
  `f_321` double DEFAULT NULL,
  `f_318` double DEFAULT NULL,
  `f_317` double DEFAULT NULL,
  `f_307` double DEFAULT NULL,
  `f_314` double DEFAULT NULL,
  `f_310` double DEFAULT NULL,
  `f_311` double DEFAULT NULL,
  `f_312` double DEFAULT NULL,
  `f_315` double DEFAULT NULL,
  `f_316` double DEFAULT NULL,
  `f_313` double DEFAULT NULL,
  `f_319` double DEFAULT NULL,
  `f_320` double DEFAULT NULL,
  `f_209` double DEFAULT NULL,
  `f_206` double DEFAULT NULL,
  `f_205` double DEFAULT NULL,
  `f_202` double DEFAULT NULL,
  `f_203` double DEFAULT NULL,
  `f_204` double DEFAULT NULL,
  `f_208` double DEFAULT NULL,
  `f_221` double DEFAULT NULL,
  `f_323` double DEFAULT NULL,
  `f_218` double DEFAULT NULL,
  `f_217` double DEFAULT NULL,
  `f_207` double DEFAULT NULL,
  `f_214` double DEFAULT NULL,
  `f_210` double DEFAULT NULL,
  `f_211` double DEFAULT NULL,
  `f_212` double DEFAULT NULL,
  `f_215` double DEFAULT NULL,
  `f_216` double DEFAULT NULL,
  `f_213` double DEFAULT NULL,
  `f_322` double DEFAULT NULL,
  `f_324` double DEFAULT NULL,
  `f_225` double DEFAULT NULL,
  `f_219` double DEFAULT NULL,
  `f_220` double DEFAULT NULL,
  `f_002` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `r_01_03__01_01`
--
ALTER TABLE `r_01_03__01_01`
  ADD PRIMARY KEY (`f_01`,`f_02`),
  ADD KEY `R_01_03_01_01_FK_02` (`f_02`);

--
-- Indexes for table `r_01_04__01_03`
--
ALTER TABLE `r_01_04__01_03`
  ADD PRIMARY KEY (`f_01`,`f_02`),
  ADD KEY `R_01_04__01_03_FK_02` (`f_02`);

--
-- Indexes for table `r_01_05__01_03`
--
ALTER TABLE `r_01_05__01_03`
  ADD PRIMARY KEY (`f_01`,`f_02`),
  ADD KEY `R_01_05__01_03_FK_02` (`f_02`);

--
-- Indexes for table `r_01_05__01_04`
--
ALTER TABLE `r_01_05__01_04`
  ADD PRIMARY KEY (`f_01`,`f_02`),
  ADD KEY `R_01_05__01_04_FK_02` (`f_02`);

--
-- Indexes for table `r_20_08__20_11`
--
ALTER TABLE `r_20_08__20_11`
  ADD PRIMARY KEY (`f_01`,`f_02`),
  ADD KEY `R_20_08__20_11_FK_02` (`f_02`);

--
-- Indexes for table `t_01_01`
--
ALTER TABLE `t_01_01`
  ADD PRIMARY KEY (`f_00`),
  ADD KEY `T_01_01_FK_01` (`f_03`);

--
-- Indexes for table `t_01_02`
--
ALTER TABLE `t_01_02`
  ADD PRIMARY KEY (`f_00`);

--
-- Indexes for table `t_01_03`
--
ALTER TABLE `t_01_03`
  ADD PRIMARY KEY (`f_00`);

--
-- Indexes for table `t_01_04`
--
ALTER TABLE `t_01_04`
  ADD PRIMARY KEY (`f_00`);

--
-- Indexes for table `t_01_05`
--
ALTER TABLE `t_01_05`
  ADD PRIMARY KEY (`f_00`),
  ADD UNIQUE KEY `T_01_05_UK_01` (`f_01`),
  ADD UNIQUE KEY `T_01_05_UK_02` (`f_02`);

--
-- Indexes for table `t_02_01`
--
ALTER TABLE `t_02_01`
  ADD PRIMARY KEY (`f_00`),
  ADD KEY `idx_f_01` (`f_01`),
  ADD KEY `idx_f_07` (`f_07`),
  ADD KEY `idx_f_08` (`f_08`);

--
-- Indexes for table `t_10_01`
--
ALTER TABLE `t_10_01`
  ADD PRIMARY KEY (`f_00`);

--
-- Indexes for table `t_10_02`
--
ALTER TABLE `t_10_02`
  ADD PRIMARY KEY (`f_00`);

--
-- Indexes for table `t_10_03`
--
ALTER TABLE `t_10_03`
  ADD PRIMARY KEY (`f_00`),
  ADD KEY `T_10_03_FK_01` (`f_04`);

--
-- Indexes for table `t_10_04`
--
ALTER TABLE `t_10_04`
  ADD PRIMARY KEY (`f_00`),
  ADD UNIQUE KEY `T_10_04_UK_01` (`f_01`),
  ADD KEY `T_10_04_FK_01` (`f_08`),
  ADD KEY `T_10_04_FK_02` (`f_09`);

--
-- Indexes for table `t_20_01`
--
ALTER TABLE `t_20_01`
  ADD PRIMARY KEY (`f_00`);

--
-- Indexes for table `t_20_02`
--
ALTER TABLE `t_20_02`
  ADD PRIMARY KEY (`f_00`),
  ADD UNIQUE KEY `T_20_02_UK_01` (`f_02`),
  ADD KEY `T_20_02_FK_01` (`f_03`);

--
-- Indexes for table `t_20_03`
--
ALTER TABLE `t_20_03`
  ADD PRIMARY KEY (`f_00`),
  ADD UNIQUE KEY `T_20_03_UK_01` (`f_02`),
  ADD KEY `T_20_03_FK_01` (`f_05`);

--
-- Indexes for table `t_20_04`
--
ALTER TABLE `t_20_04`
  ADD PRIMARY KEY (`f_00`),
  ADD UNIQUE KEY `T_20_04_UK_01` (`f_02`);

--
-- Indexes for table `t_20_05`
--
ALTER TABLE `t_20_05`
  ADD PRIMARY KEY (`f_00`);

--
-- Indexes for table `t_20_06`
--
ALTER TABLE `t_20_06`
  ADD PRIMARY KEY (`f_00`);

--
-- Indexes for table `t_20_07`
--
ALTER TABLE `t_20_07`
  ADD PRIMARY KEY (`f_00`),
  ADD UNIQUE KEY `T_20_07_UK_01` (`f_01`);

--
-- Indexes for table `t_20_08`
--
ALTER TABLE `t_20_08`
  ADD PRIMARY KEY (`f_00`),
  ADD UNIQUE KEY `T_20_08_UK_01` (`f_02`),
  ADD KEY `T_20_08_FK_03` (`f_05`),
  ADD KEY `T_20_08_FK_02` (`f_04`),
  ADD KEY `T_20_08_FK_01` (`f_03`);

--
-- Indexes for table `t_20_09`
--
ALTER TABLE `t_20_09`
  ADD PRIMARY KEY (`f_00`),
  ADD KEY `T_20_09_FK_02` (`f_10`),
  ADD KEY `T_20_09_FK_03` (`f_11`),
  ADD KEY `T_20_09_FK_01` (`f_09`);

--
-- Indexes for table `t_20_10`
--
ALTER TABLE `t_20_10`
  ADD PRIMARY KEY (`f_00`),
  ADD UNIQUE KEY `T_20_10_UK_01` (`f_02`),
  ADD KEY `T_20_10_FK_01` (`f_03`);

--
-- Indexes for table `t_20_11`
--
ALTER TABLE `t_20_11`
  ADD PRIMARY KEY (`f_00`),
  ADD UNIQUE KEY `T_20_11_UK_01` (`f_01`),
  ADD KEY `T_20_11_FK_04` (`f_10`),
  ADD KEY `T_20_11_FK_03` (`f_09`),
  ADD KEY `T_20_11_FK_01` (`f_07`),
  ADD KEY `T_20_11_FK_02` (`f_08`);

--
-- Indexes for table `t_20_12`
--
ALTER TABLE `t_20_12`
  ADD PRIMARY KEY (`f_00`),
  ADD UNIQUE KEY `T_20_12_UK_01` (`f_01`),
  ADD KEY `T_20_12_FK_01` (`f_05`);

--
-- Indexes for table `t_30_01`
--
ALTER TABLE `t_30_01`
  ADD PRIMARY KEY (`f_00`),
  ADD KEY `T_30_01_FK_01` (`f_08`);

--
-- Indexes for table `t_30_02`
--
ALTER TABLE `t_30_02`
  ADD PRIMARY KEY (`f_00`),
  ADD KEY `T_30_02_FK_02` (`f_08`),
  ADD KEY `T_30_02_FK_01` (`f_07`);

--
-- Indexes for table `t_30_03`
--
ALTER TABLE `t_30_03`
  ADD PRIMARY KEY (`f_00`),
  ADD KEY `T_30_03_FK_02` (`f_07`),
  ADD KEY `T_30_03_FK_01` (`f_06`);

--
-- Indexes for table `t_30_04`
--
ALTER TABLE `t_30_04`
  ADD PRIMARY KEY (`f_00`),
  ADD KEY `T_30_04_FK_01` (`f_07`);

--
-- Indexes for table `t_30_05`
--
ALTER TABLE `t_30_05`
  ADD PRIMARY KEY (`f_00`),
  ADD KEY `T_30_05_FK_01` (`f_04`),
  ADD KEY `T_30_05_FK_02` (`f_05`);

--
-- Indexes for table `t_30_06`
--
ALTER TABLE `t_30_06`
  ADD PRIMARY KEY (`f_00`);

--
-- Indexes for table `t_30_07`
--
ALTER TABLE `t_30_07`
  ADD PRIMARY KEY (`f_00`),
  ADD UNIQUE KEY `T_30_07_UK_01` (`f_001`),
  ADD KEY `T_30_07_FK_01` (`f_002`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `t_01_01`
--
ALTER TABLE `t_01_01`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `t_01_02`
--
ALTER TABLE `t_01_02`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `t_01_03`
--
ALTER TABLE `t_01_03`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `t_01_04`
--
ALTER TABLE `t_01_04`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `t_01_05`
--
ALTER TABLE `t_01_05`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `t_02_01`
--
ALTER TABLE `t_02_01`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_10_01`
--
ALTER TABLE `t_10_01`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_10_02`
--
ALTER TABLE `t_10_02`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_10_03`
--
ALTER TABLE `t_10_03`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_10_04`
--
ALTER TABLE `t_10_04`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_20_01`
--
ALTER TABLE `t_20_01`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `t_20_02`
--
ALTER TABLE `t_20_02`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `t_20_03`
--
ALTER TABLE `t_20_03`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `t_20_04`
--
ALTER TABLE `t_20_04`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `t_20_05`
--
ALTER TABLE `t_20_05`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `t_20_06`
--
ALTER TABLE `t_20_06`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_20_07`
--
ALTER TABLE `t_20_07`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `t_20_08`
--
ALTER TABLE `t_20_08`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `t_20_09`
--
ALTER TABLE `t_20_09`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_20_10`
--
ALTER TABLE `t_20_10`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `t_20_11`
--
ALTER TABLE `t_20_11`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `t_20_12`
--
ALTER TABLE `t_20_12`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_30_01`
--
ALTER TABLE `t_30_01`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_30_02`
--
ALTER TABLE `t_30_02`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_30_03`
--
ALTER TABLE `t_30_03`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_30_04`
--
ALTER TABLE `t_30_04`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_30_05`
--
ALTER TABLE `t_30_05`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_30_06`
--
ALTER TABLE `t_30_06`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_30_07`
--
ALTER TABLE `t_30_07`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `r_01_03__01_01`
--
ALTER TABLE `r_01_03__01_01`
  ADD CONSTRAINT `R_01_03_01_01_FK_01` FOREIGN KEY (`f_01`) REFERENCES `t_01_03` (`f_00`),
  ADD CONSTRAINT `R_01_03_01_01_FK_02` FOREIGN KEY (`f_02`) REFERENCES `t_01_01` (`f_00`);

--
-- Constraints for table `r_01_04__01_03`
--
ALTER TABLE `r_01_04__01_03`
  ADD CONSTRAINT `R_01_04__01_03_FK_01` FOREIGN KEY (`f_01`) REFERENCES `t_01_04` (`f_00`),
  ADD CONSTRAINT `R_01_04__01_03_FK_02` FOREIGN KEY (`f_02`) REFERENCES `t_01_03` (`f_00`);

--
-- Constraints for table `r_01_05__01_03`
--
ALTER TABLE `r_01_05__01_03`
  ADD CONSTRAINT `R_01_05__01_03_FK_01` FOREIGN KEY (`f_01`) REFERENCES `t_01_05` (`f_00`),
  ADD CONSTRAINT `R_01_05__01_03_FK_02` FOREIGN KEY (`f_02`) REFERENCES `t_01_03` (`f_00`);

--
-- Constraints for table `r_01_05__01_04`
--
ALTER TABLE `r_01_05__01_04`
  ADD CONSTRAINT `R_01_05__01_04_FK_01` FOREIGN KEY (`f_01`) REFERENCES `t_01_05` (`f_00`),
  ADD CONSTRAINT `R_01_05__01_04_FK_02` FOREIGN KEY (`f_02`) REFERENCES `t_01_04` (`f_00`);

--
-- Constraints for table `r_20_08__20_11`
--
ALTER TABLE `r_20_08__20_11`
  ADD CONSTRAINT `R_20_08__20_11_FK_01` FOREIGN KEY (`f_01`) REFERENCES `t_20_11` (`f_00`),
  ADD CONSTRAINT `R_20_08__20_11_FK_02` FOREIGN KEY (`f_02`) REFERENCES `t_20_08` (`f_00`);

--
-- Constraints for table `t_01_01`
--
ALTER TABLE `t_01_01`
  ADD CONSTRAINT `T_01_01_FK_01` FOREIGN KEY (`f_03`) REFERENCES `t_01_02` (`f_00`);

--
-- Constraints for table `t_10_03`
--
ALTER TABLE `t_10_03`
  ADD CONSTRAINT `T_10_03_FK_01` FOREIGN KEY (`f_04`) REFERENCES `t_10_01` (`f_00`);

--
-- Constraints for table `t_10_04`
--
ALTER TABLE `t_10_04`
  ADD CONSTRAINT `T_10_04_FK_01` FOREIGN KEY (`f_08`) REFERENCES `t_10_02` (`f_00`),
  ADD CONSTRAINT `T_10_04_FK_02` FOREIGN KEY (`f_09`) REFERENCES `t_10_03` (`f_00`);

--
-- Constraints for table `t_20_02`
--
ALTER TABLE `t_20_02`
  ADD CONSTRAINT `T_20_02_FK_01` FOREIGN KEY (`f_03`) REFERENCES `t_20_01` (`f_00`);

--
-- Constraints for table `t_20_03`
--
ALTER TABLE `t_20_03`
  ADD CONSTRAINT `T_20_03_FK_01` FOREIGN KEY (`f_05`) REFERENCES `t_20_02` (`f_00`);

--
-- Constraints for table `t_20_08`
--
ALTER TABLE `t_20_08`
  ADD CONSTRAINT `T_20_08_FK_01` FOREIGN KEY (`f_03`) REFERENCES `t_20_07` (`f_00`),
  ADD CONSTRAINT `T_20_08_FK_02` FOREIGN KEY (`f_04`) REFERENCES `t_20_03` (`f_00`),
  ADD CONSTRAINT `T_20_08_FK_03` FOREIGN KEY (`f_05`) REFERENCES `t_20_05` (`f_00`);

--
-- Constraints for table `t_20_09`
--
ALTER TABLE `t_20_09`
  ADD CONSTRAINT `T_20_09_FK_01` FOREIGN KEY (`f_09`) REFERENCES `t_20_07` (`f_00`),
  ADD CONSTRAINT `T_20_09_FK_02` FOREIGN KEY (`f_10`) REFERENCES `t_20_06` (`f_00`),
  ADD CONSTRAINT `T_20_09_FK_03` FOREIGN KEY (`f_11`) REFERENCES `t_20_08` (`f_00`);

--
-- Constraints for table `t_20_10`
--
ALTER TABLE `t_20_10`
  ADD CONSTRAINT `T_20_10_FK_01` FOREIGN KEY (`f_03`) REFERENCES `t_20_04` (`f_00`);

--
-- Constraints for table `t_20_11`
--
ALTER TABLE `t_20_11`
  ADD CONSTRAINT `T_20_11_FK_01` FOREIGN KEY (`f_07`) REFERENCES `t_20_07` (`f_00`),
  ADD CONSTRAINT `T_20_11_FK_02` FOREIGN KEY (`f_08`) REFERENCES `t_20_10` (`f_00`),
  ADD CONSTRAINT `T_20_11_FK_03` FOREIGN KEY (`f_09`) REFERENCES `t_20_08` (`f_00`),
  ADD CONSTRAINT `T_20_11_FK_04` FOREIGN KEY (`f_10`) REFERENCES `t_20_08` (`f_00`);

--
-- Constraints for table `t_20_12`
--
ALTER TABLE `t_20_12`
  ADD CONSTRAINT `T_20_12_FK_01` FOREIGN KEY (`f_05`) REFERENCES `t_20_11` (`f_00`);

--
-- Constraints for table `t_30_01`
--
ALTER TABLE `t_30_01`
  ADD CONSTRAINT `T_30_01_FK_01` FOREIGN KEY (`f_08`) REFERENCES `t_20_09` (`f_00`);

--
-- Constraints for table `t_30_02`
--
ALTER TABLE `t_30_02`
  ADD CONSTRAINT `T_30_02_FK_01` FOREIGN KEY (`f_07`) REFERENCES `t_20_09` (`f_00`),
  ADD CONSTRAINT `T_30_02_FK_02` FOREIGN KEY (`f_08`) REFERENCES `t_10_04` (`f_00`);

--
-- Constraints for table `t_30_03`
--
ALTER TABLE `t_30_03`
  ADD CONSTRAINT `T_30_03_FK_01` FOREIGN KEY (`f_06`) REFERENCES `t_20_10` (`f_00`),
  ADD CONSTRAINT `T_30_03_FK_02` FOREIGN KEY (`f_07`) REFERENCES `t_10_04` (`f_00`);

--
-- Constraints for table `t_30_04`
--
ALTER TABLE `t_30_04`
  ADD CONSTRAINT `T_30_04_FK_01` FOREIGN KEY (`f_07`) REFERENCES `t_20_11` (`f_00`);

--
-- Constraints for table `t_30_05`
--
ALTER TABLE `t_30_05`
  ADD CONSTRAINT `T_30_05_FK_01` FOREIGN KEY (`f_04`) REFERENCES `t_30_01` (`f_00`),
  ADD CONSTRAINT `T_30_05_FK_02` FOREIGN KEY (`f_05`) REFERENCES `t_10_04` (`f_00`);

--
-- Constraints for table `t_30_07`
--
ALTER TABLE `t_30_07`
  ADD CONSTRAINT `T_30_07_FK_01` FOREIGN KEY (`f_002`) REFERENCES `t_10_04` (`f_00`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
