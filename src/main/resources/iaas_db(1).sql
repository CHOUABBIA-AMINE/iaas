-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 16, 2025 at 09:24 AM
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
-- Database: `iaas_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `r_t000201_t000203`
--

CREATE TABLE `r_t000201_t000203` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `r_t000201_t000203`
--

INSERT INTO `r_t000201_t000203` (`F_01`, `F_02`) VALUES
(1, 2),
(2, 4),
(3, 3),
(3, 8),
(5, 3),
(8, 6);

-- --------------------------------------------------------

--
-- Table structure for table `r_t000202_t000201`
--

CREATE TABLE `r_t000202_t000201` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `r_t000202_t000201`
--

INSERT INTO `r_t000202_t000201` (`F_01`, `F_02`) VALUES
(3, 1),
(4, 2),
(4, 5),
(5, 3),
(5, 5),
(6, 4),
(6, 5),
(7, 1),
(7, 6),
(8, 2),
(9, 4),
(10, 7),
(11, 8),
(12, 3),
(12, 6),
(12, 8),
(14, 7);

-- --------------------------------------------------------

--
-- Table structure for table `r_t000202_t000203`
--

CREATE TABLE `r_t000202_t000203` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `r_t000202_t000203`
--

INSERT INTO `r_t000202_t000203` (`F_01`, `F_02`) VALUES
(1, 1),
(2, 2),
(3, 2),
(4, 3),
(4, 4),
(5, 3),
(5, 8),
(6, 3),
(7, 5),
(8, 5),
(9, 5),
(10, 5),
(11, 6),
(12, 7),
(13, 8),
(14, 9),
(15, 5),
(16, 5);

-- --------------------------------------------------------

--
-- Table structure for table `r_t000203_t000204`
--

CREATE TABLE `r_t000203_t000204` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `r_t000203_t000204`
--

INSERT INTO `r_t000203_t000204` (`F_01`, `F_02`) VALUES
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
(1, 17),
(1, 18),
(1, 19),
(1, 20),
(1, 21),
(1, 22),
(1, 23),
(1, 24),
(1, 25),
(1, 26),
(1, 27),
(1, 28),
(1, 29),
(1, 30),
(1, 31),
(2, 1),
(2, 2),
(2, 4),
(2, 5),
(2, 6),
(2, 7),
(2, 8),
(2, 9),
(2, 10),
(2, 13),
(2, 16),
(2, 17),
(2, 19),
(2, 20),
(2, 23),
(2, 29),
(3, 5),
(3, 16),
(3, 19),
(3, 20),
(3, 29),
(4, 5),
(4, 6),
(4, 16),
(5, 5),
(5, 19),
(6, 1),
(6, 5),
(6, 23),
(6, 25),
(6, 29),
(6, 31),
(7, 1),
(7, 5),
(7, 19),
(7, 26),
(7, 29),
(7, 30),
(7, 31),
(8, 19),
(8, 22),
(9, 5),
(9, 19),
(9, 23),
(9, 29);

-- --------------------------------------------------------

--
-- Table structure for table `r_t010203_t010203`
--

CREATE TABLE `r_t010203_t010203` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `r_t020304_t020301`
--

CREATE TABLE `r_t020304_t020301` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `r_t020404_t010203`
--

CREATE TABLE `r_t020404_t010203` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `r_t020404_t010302`
--

CREATE TABLE `r_t020404_t010302` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `r_t020404_t020208`
--

CREATE TABLE `r_t020404_t020208` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `r_t020504_t010203`
--

CREATE TABLE `r_t020504_t010203` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `r_t020504_t010302`
--

CREATE TABLE `r_t020504_t010302` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `r_t020504_t020208`
--

CREATE TABLE `r_t020504_t020208` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `r_t020604_t010203`
--

CREATE TABLE `r_t020604_t010203` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `r_t020604_t010302`
--

CREATE TABLE `r_t020604_t010302` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_00_01_01`
--

CREATE TABLE `t_00_01_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_04` varchar(20) DEFAULT NULL,
  `F_03` varchar(250) NOT NULL,
  `F_02` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_00_02_01`
--

CREATE TABLE `t_00_02_01` (
  `F_00` bigint(20) NOT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_01` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_00_02_01`
--

INSERT INTO `t_00_02_01` (`F_00`, `F_02`, `F_01`) VALUES
(1, 'Information Technology Department', 'IT_DEPARTMENT'),
(2, 'Human Resources Department', 'HR_DEPARTMENT'),
(3, 'Finance Department', 'FINANCE_DEPARTMENT'),
(4, 'Operations Department', 'OPERATIONS_DEPARTMENT'),
(5, 'Management Team', 'MANAGEMENT_TEAM'),
(6, 'Software Development Team', 'DEVELOPMENT_TEAM'),
(7, 'Customer Support Team', 'SUPPORT_TEAM'),
(8, 'Quality Assurance Team', 'QUALITY_ASSURANCE');

-- --------------------------------------------------------

--
-- Table structure for table `t_00_02_02`
--

CREATE TABLE `t_00_02_02` (
  `F_00` bigint(20) NOT NULL,
  `F_04` bit(1) NOT NULL,
  `F_05` bit(1) NOT NULL,
  `F_06` bit(1) NOT NULL,
  `F_02` varchar(100) NOT NULL,
  `F_07` bit(1) NOT NULL,
  `F_03` varchar(120) NOT NULL,
  `F_01` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_00_02_02`
--

INSERT INTO `t_00_02_02` (`F_00`, `F_04`, `F_05`, `F_06`, `F_02`, `F_07`, `F_03`, `F_01`) VALUES
(1, b'1', b'1', b'1', 'superadmin@iaas.dz', b'1', '$2a$12$0rC72g02l4Gpt4sBDiGVLeNBVsyl1cM2K9Y1BXatvqgEsgKBFIi/S', 'superadmin'),
(2, b'1', b'1', b'1', 'admin@iaas.dz', b'1', '$2a$10$qKiSz7pTLSyQ8p.tk42KE.kdDCaiux7D4PsTZgFKfr7hVPDCAXpJ2', 'admin'),
(3, b'1', b'1', b'1', 'it.admin@iaas.dz', b'1', '$2a$12$0rC72g02l4Gpt4sBDiGVLeNBVsyl1cM2K9Y1BXatvqgEsgKBFIi/S', 'it.admin'),
(4, b'1', b'1', b'1', 'hr.manager@iaas.dz', b'1', '$2a$12$0rC72g02l4Gpt4sBDiGVLeNBVsyl1cM2K9Y1BXatvqgEsgKBFIi/S', 'hr.manager'),
(5, b'1', b'1', b'1', 'finance.manager@iaas.dz', b'1', '$2a$12$0rC72g02l4Gpt4sBDiGVLeNBVsyl1cM2K9Y1BXatvqgEsgKBFIi/S', 'finance.manager'),
(6, b'1', b'1', b'1', 'ops.manager@iaas.dz', b'1', '$2a$12$0rC72g02l4Gpt4sBDiGVLeNBVsyl1cM2K9Y1BXatvqgEsgKBFIi/S', 'ops.manager'),
(7, b'1', b'1', b'1', 'john.doe@iaas.dz', b'1', '$2a$12$0rC72g02l4Gpt4sBDiGVLeNBVsyl1cM2K9Y1BXatvqgEsgKBFIi/S', 'john.doe'),
(8, b'1', b'1', b'1', 'jane.smith@iaas.dz', b'1', '$2a$12$0rC72g02l4Gpt4sBDiGVLeNBVsyl1cM2K9Y1BXatvqgEsgKBFIi/S', 'jane.smith'),
(9, b'1', b'1', b'1', 'bob.wilson@iaas.dz', b'1', '$2a$12$0rC72g02l4Gpt4sBDiGVLeNBVsyl1cM2K9Y1BXatvqgEsgKBFIi/S', 'bob.wilson'),
(10, b'1', b'1', b'1', 'alice.brown@iaas.dz', b'1', '$2a$12$0rC72g02l4Gpt4sBDiGVLeNBVsyl1cM2K9Y1BXatvqgEsgKBFIi/S', 'alice.brown'),
(11, b'1', b'1', b'1', 'auditor@iaas.dz', b'1', '$2a$12$0rC72g02l4Gpt4sBDiGVLeNBVsyl1cM2K9Y1BXatvqgEsgKBFIi/S', 'auditor'),
(12, b'1', b'1', b'1', 'analyst@iaas.dz', b'1', '$2a$12$0rC72g02l4Gpt4sBDiGVLeNBVsyl1cM2K9Y1BXatvqgEsgKBFIi/S', 'analyst'),
(13, b'1', b'1', b'1', 'approver@iaas.dz', b'1', '$2a$12$0rC72g02l4Gpt4sBDiGVLeNBVsyl1cM2K9Y1BXatvqgEsgKBFIi/S', 'approver'),
(14, b'1', b'1', b'1', 'viewer@iaas.dz', b'1', '$2a$12$0rC72g02l4Gpt4sBDiGVLeNBVsyl1cM2K9Y1BXatvqgEsgKBFIi/S', 'viewer'),
(15, b'1', b'1', b'1', 'disabled@iaas.dz', b'0', '$2a$12$0rC72g02l4Gpt4sBDiGVLeNBVsyl1cM2K9Y1BXatvqgEsgKBFIi/S', 'disabled.user'),
(16, b'1', b'0', b'1', 'locked@iaas.dz', b'1', '$2a$12$0rC72g02l4Gpt4sBDiGVLeNBVsyl1cM2K9Y1BXatvqgEsgKBFIi/S', 'locked.user');

-- --------------------------------------------------------

--
-- Table structure for table `t_00_02_03`
--

CREATE TABLE `t_00_02_03` (
  `F_00` bigint(20) NOT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_01` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_00_02_03`
--

INSERT INTO `t_00_02_03` (`F_00`, `F_02`, `F_01`) VALUES
(1, 'Super administrator with full system access', 'ROLE_SUPER_ADMIN'),
(2, 'System administrator', 'ROLE_ADMIN'),
(3, 'Department manager', 'ROLE_MANAGER'),
(4, 'User account manager', 'ROLE_USER_MANAGER'),
(5, 'Regular employee', 'ROLE_EMPLOYEE'),
(6, 'System auditor', 'ROLE_AUDITOR'),
(7, 'Data analyst', 'ROLE_ANALYST'),
(8, 'Document approval authority', 'ROLE_DOCUMENT_APPROVER'),
(9, 'Read-only access', 'ROLE_VIEWER');

-- --------------------------------------------------------

--
-- Table structure for table `t_00_02_04`
--

CREATE TABLE `t_00_02_04` (
  `F_00` bigint(20) NOT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_01` varchar(50) NOT NULL,
  `F_03` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_00_02_04`
--

INSERT INTO `t_00_02_04` (`F_00`, `F_02`, `F_01`, `F_03`) VALUES
(1, 'Read system configuration', 'SYSTEM:READ', 1),
(2, 'Modify system settings', 'SYSTEM:WRITE', 1),
(3, 'Delete system data', 'SYSTEM:DELETE', 1),
(4, 'Full system administration', 'SYSTEM:ADMIN', 1),
(5, 'View user profiles', 'USER:READ', 2),
(6, 'Create and modify users', 'USER:WRITE', 2),
(7, 'Delete user accounts', 'USER:DELETE', 2),
(8, 'Full user management', 'USER:ADMIN', 2),
(9, 'View roles and assignments', 'ROLE:READ', 3),
(10, 'Create and modify roles', 'ROLE:WRITE', 3),
(11, 'Delete roles', 'ROLE:DELETE', 3),
(12, 'Full role management', 'ROLE:ADMIN', 3),
(13, 'View permissions', 'PERMISSION:READ', 4),
(14, 'Modify permission assignments', 'PERMISSION:WRITE', 4),
(15, 'Remove permissions', 'PERMISSION:DELETE', 4),
(16, 'View groups and memberships', 'GROUP:READ', 5),
(17, 'Create and modify groups', 'GROUP:WRITE', 5),
(18, 'Delete groups', 'GROUP:DELETE', 5),
(19, 'View documents', 'DOCUMENT:READ', 6),
(20, 'Create and edit documents', 'DOCUMENT:WRITE', 6),
(21, 'Delete documents', 'DOCUMENT:DELETE', 6),
(22, 'Approve documents', 'DOCUMENT:APPROVE', 6),
(23, 'View audit logs', 'AUDIT:READ', 7),
(24, 'Create audit entries', 'AUDIT:WRITE', 7),
(25, 'Export audit reports', 'AUDIT:EXPORT', 7),
(26, 'View financial data', 'FINANCE:READ', 8),
(27, 'Create financial records', 'FINANCE:WRITE', 8),
(28, 'Approve financial transactions', 'FINANCE:APPROVE', 8),
(29, 'View reports', 'REPORT:READ', 9),
(30, 'Generate reports', 'REPORT:WRITE', 9),
(31, 'Export reports', 'REPORT:EXPORT', 9);

-- --------------------------------------------------------

--
-- Table structure for table `t_00_02_05`
--

CREATE TABLE `t_00_02_05` (
  `F_00` bigint(20) NOT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_01` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_00_02_05`
--

INSERT INTO `t_00_02_05` (`F_00`, `F_02`, `F_01`) VALUES
(1, 'System-level operations and administration', 'SYSTEM'),
(2, 'User account management operations', 'USER'),
(3, 'Role management operations', 'ROLE'),
(4, 'Permission management operations', 'PERMISSION'),
(5, 'Group and department management', 'GROUP'),
(6, 'Document management operations', 'DOCUMENT'),
(7, 'Audit log access and reporting', 'AUDIT'),
(8, 'Financial operations and approvals', 'FINANCE'),
(9, 'Report generation and access', 'REPORT');

-- --------------------------------------------------------

--
-- Table structure for table `t_00_03_01`
--

CREATE TABLE `t_00_03_01` (
  `F_00` bigint(20) NOT NULL,
  `F_03` enum('APPROVE','ARCHIVE','CANCEL','CREATE','DELETE','READ','REJECT','RESTORE','SUBMIT','UPDATE') NOT NULL,
  `F_18` varchar(50) DEFAULT NULL,
  `F_12` varchar(1000) DEFAULT NULL,
  `F_15` bigint(20) DEFAULT NULL,
  `F_02` bigint(20) NOT NULL,
  `F_01` varchar(100) NOT NULL,
  `F_14` text DEFAULT NULL,
  `F_06` varchar(45) DEFAULT NULL,
  `F_20` text DEFAULT NULL,
  `F_08` varchar(200) DEFAULT NULL,
  `F_17` varchar(50) DEFAULT NULL,
  `F_10` text DEFAULT NULL,
  `F_09` text DEFAULT NULL,
  `F_11` text DEFAULT NULL,
  `F_19` bigint(20) DEFAULT NULL,
  `F_16` varchar(100) DEFAULT NULL,
  `F_13` enum('FAILED','PARTIAL','SUCCESS') NOT NULL,
  `F_05` datetime(6) NOT NULL,
  `F_07` varchar(500) DEFAULT NULL,
  `F_04` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_00_04_01`
--

CREATE TABLE `t_00_04_01` (
  `F_00` bigint(20) NOT NULL,
  `F_02` datetime(6) NOT NULL,
  `F_01` varchar(255) NOT NULL,
  `F_03` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_00_04_01`
--

INSERT INTO `t_00_04_01` (`F_00`, `F_02`, `F_01`, `F_03`) VALUES
(19, '2025-12-10 10:41:28.000000', '58b67668-0851-44f9-9591-f7b24192b987', 2),
(20, '2025-12-10 11:03:16.000000', '74025ab8-e22e-4844-81d7-ea90976f3854', 1);

-- --------------------------------------------------------

--
-- Table structure for table `t_01_01_01`
--

CREATE TABLE `t_01_01_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_02` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_01_01_01`
--

INSERT INTO `t_01_01_01` (`F_00`, `F_01`, `F_02`) VALUES
(1, 'س', 'S');

-- --------------------------------------------------------

--
-- Table structure for table `t_01_01_02`
--

CREATE TABLE `t_01_01_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_02` bigint(20) NOT NULL,
  `F_03` bigint(20) NOT NULL,
  `F_04` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_01_01_02`
--

INSERT INTO `t_01_01_02` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`) VALUES
(1, '60', 1, 6, NULL),
(2, '61', 1, 6, NULL),
(3, '62', 1, 6, NULL),
(4, '63', 1, 6, NULL),
(5, '64', 1, 6, NULL),
(6, '65', 1, 6, NULL),
(7, '66', 1, 6, NULL),
(8, '67', 1, 6, NULL),
(9, '68', 1, 6, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `t_01_01_03`
--

CREATE TABLE `t_01_01_03` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) DEFAULT NULL,
  `F_04` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_01_01_03`
--

INSERT INTO `t_01_01_03` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`) VALUES
(1, '-2°', 'الطابق السفلي الثاني', 'Second Floor Underground', 'Deuxième Etage Sous Sol'),
(2, '-1°', 'الطابق السفلي الأول', 'First Floor Underground', 'Premier Etage Sous Sol'),
(3, '0', 'الطابق الأرضي', 'Ground Floor', 'Riz de Chaussé'),
(4, '1°', 'الطابق الأول', 'First Floor', 'Premier Etage'),
(5, '2°', 'الطابق الأول', 'Second Floor', 'Deuxième Etage'),
(6, '3°', 'الطابق الأول', 'Third Floor', 'Troisième Etage'),
(7, '4°', 'الطابق الأول', 'Fourth Floor', 'Quatrième Etage'),
(8, '5°', 'الطابق الأول', 'Fifth Floor', 'Cinquième Etage'),
(9, '6°', 'الطابق الأول', 'Sixth Floor', 'Siesième Etage'),
(10, '7°', 'الطابق الأول', 'Seventh Floor', 'Septième Etage'),
(11, '8°', 'الطابق الأول', 'Eigth Floor', 'Huitième Etage'),
(12, '9°', 'الطابق الأول', 'Nineth Floor', 'Neuvième Etage');

-- --------------------------------------------------------

--
-- Table structure for table `t_01_01_04`
--

CREATE TABLE `t_01_01_04` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) DEFAULT NULL,
  `F_04` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_01_01_04`
--

INSERT INTO `t_01_01_04` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`) VALUES
(1, '1°', 'الطابق الأول', 'First Floor', 'Premier Etage'),
(2, '2°', 'الطابق الأول', 'Second Floor', 'Deuxième Etage'),
(3, '3°', 'الطابق الأول', 'Third Floor', 'Troisième Etage'),
(4, '4°', 'الطابق الأول', 'Fourth Floor', 'Quatrième Etage'),
(5, '5°', 'الطابق الأول', 'Fifth Floor', 'Cinquième Etage'),
(6, '6°', 'الطابق الأول', 'Sixth Floor', 'Siesième Etage'),
(7, '7°', 'الطابق الأول', 'Seventh Floor', 'Septième Etage'),
(8, '8°', 'الطابق الأول', 'Eigth Floor', 'Huitième Etage'),
(9, '9°', 'الطابق الأول', 'Nineth Floor', 'Neuvième Etage');

-- --------------------------------------------------------

--
-- Table structure for table `t_01_01_05`
--

CREATE TABLE `t_01_01_05` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_01_01_05`
--

INSERT INTO `t_01_01_05` (`F_00`, `F_01`, `F_02`) VALUES
(1, 'SDR-01', 2),
(2, 'SDR-02', 2),
(3, 'SDR-03', 2),
(4, 'SDR-04', 3),
(5, 'SDR-05', 3),
(6, 'SDR-06', 4),
(7, 'SDR-07', 4),
(8, 'SDR-08', 7),
(9, 'SDR-09', 7),
(10, 'SDR-10', 7),
(11, 'SDR-11', 8),
(12, 'SDR-12', 8),
(13, 'SDR-13', 8),
(14, 'SDR-14', 8),
(15, 'SDR-15', 8),
(16, 'SDR-16', 8),
(17, 'SDR-17', 8),
(18, 'SDR-18', 8),
(19, 'SDR-19', 8),
(20, 'SDR-20', 8);

-- --------------------------------------------------------

--
-- Table structure for table `t_01_01_06`
--

CREATE TABLE `t_01_01_06` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_02` bigint(20) NOT NULL,
  `F_03` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_01_01_07`
--

CREATE TABLE `t_01_01_07` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) DEFAULT NULL,
  `F_04` varchar(200) NOT NULL,
  `F_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_01_02_01`
--

CREATE TABLE `t_01_02_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_01_02_01`
--

INSERT INTO `t_01_02_01` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'الصادر', 'Outgoing', 'Départ'),
(2, 'الوارد', 'Arriving', 'Arrivé');

-- --------------------------------------------------------

--
-- Table structure for table `t_01_02_02`
--

CREATE TABLE `t_01_02_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_01_02_02`
--

INSERT INTO `t_01_02_02` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'مرسوم', 'Decree', 'Décret'),
(2, 'قرار', 'Administrative Decree', 'Arrété'),
(3, 'أمر﻿', 'Order', 'Ordonnance'),
(4, 'منشور﻿', 'Circular', 'Circulaire'),
(5, 'توجيهة', 'Directive', 'Directive'),
(6, 'تعليمة', 'Instruction', 'Instruction'),
(7, 'لائحة', 'Note', 'Note'),
(8, 'مقرر', 'Decision', 'Décision'),
(9, 'إرسالية', 'Dispatch', 'Envoi'),
(10, 'فاكس', 'Fax', 'Fax'),
(11, 'جدول إرسال', 'Dispatch Sheet', 'Bordereau d\'Envoi'),
(12, 'برقية', 'Telegram', 'Message');

-- --------------------------------------------------------

--
-- Table structure for table `t_01_02_03`
--

CREATE TABLE `t_01_02_03` (
  `F_00` bigint(20) NOT NULL,
  `F_04` datetime(6) DEFAULT NULL,
  `F_05` datetime(6) DEFAULT NULL,
  `F_02` varchar(50) DEFAULT NULL,
  `F_01` varchar(50) DEFAULT NULL,
  `F_03` varchar(500) DEFAULT NULL,
  `F_09` bigint(20) NOT NULL,
  `F_06` bigint(20) NOT NULL,
  `F_07` bigint(20) NOT NULL,
  `F_08` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_01_03_01`
--

CREATE TABLE `t_01_03_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) DEFAULT NULL,
  `F_04` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_01_03_01`
--

INSERT INTO `t_01_03_01` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`) VALUES
(1, 'بطاقة اقتراح', 'Proposal Form', 'Fiche de Proposition', 100),
(2, 'بطاقة تقنية وصفية', 'Descriptive Technical Sheet', 'Fiche Technique Descriptive', 100),
(3, 'بطاقة تقنية', 'Technical Sheet', 'Fiche Technique', 100),
(4, 'تقرير تقديم', 'Presentation Report', 'Rapport de Présentation', 111),
(5, 'تقرير تقديم تكميلي', 'Supplementary Presentation Report', 'Rapport de Présentation Complémentaire', 111),
(6, 'بطاقة تحليلية', 'Analytical Sheet', 'Fiche Analytique', 11),
(7, 'بطاقة الإقتطاع الميزانياتي', 'Budget Allocation Sheet', 'Fiche d\'Imputation Budgétaire', 111),
(8, 'تأشيرة اللجنة القطاعية للصفقات', 'CSM Visa', 'Visa CSM', 111),
(9, 'لوحة إعلانية', 'Advertising Board', 'Placard publicitaire', 100),
(10, 'دفتر الشروط', 'Terms of Reference', 'Cahier des Charges', 100),
(11, 'عقد / صفقة', 'Contract', 'Contrat / Marché', 10),
(12, 'ملحق عقد', 'Amendment', 'Avenant', 1),
(13, 'أمر شراء', 'Purchase Order', 'Bon de Commande', 10),
(14, 'بطاقة تمديد الآجال', 'Deadline Extension Form', 'Fiche de Prorogation des Délais', 100),
(15, 'لوحة إعلانية (تمديد الآجال)', 'Advertising Board (Deadline Extension)', 'Placard publicitaire (Prorogation Délai)', 100),
(16, 'محضر فتح الأظرف الإدارية', 'Minutes of Administrative Bid Opening', 'PV Ouverture Administratif des plis', 100),
(17, 'محضر فتح الأظرف التقنية', 'Minutes of Technical Bid Opening', 'PV Ouverture Technique', 100),
(18, 'محضر التقييم التقني', 'Technical Evaluation Minutes', 'PV Evaluation Technique', 100),
(19, 'محضر فتح الأظرف المالية', 'Minutes of Financial Bid Opening', 'PV Ouverture Financière', 100),
(20, 'محضر التقييم المالي', 'Financial Evaluation Minutes', 'PV Evaluation Financière', 100),
(21, 'محضر الإغلاق', 'Closing Minutes', 'PV de Cloture', 100),
(22, 'مداولة الاختيار المؤقت', 'Provisional Selection Deliberation', 'Délibération du choix Provisoire', 100),
(23, 'محضر لجنة فتح الأظرفة وتقييم العروض', 'COPEO Minutes', 'PV COPEO', 11),
(24, 'محضر لجنة فتح الأظرفة وتقييم العروض التكميلي', 'Supplementary COPEO Minutes', 'PV COPEO Complémentaire', 111),
(25, 'أمر خدمة', 'Service Order', 'Ordre de Service', 11),
(26, 'كفالة التعهد', 'Bid Bond', 'Caution de Soumission', 100),
(27, 'كفالة حسن التنفيذ', 'Performance Bond', 'Caution de Bonne Exécution', 100),
(28, 'كفالة الضمان', 'Guarantee Bond', 'Caution de Garantie', 100),
(29, 'الملف الإداري', 'Administrative File', 'Dossier Administratif', 100),
(30, 'العرض المالي', 'Financial Offer', 'Offre Financière', 100),
(31, 'العرض الفني', 'Technical Offer', 'Offre Technique', 100);

-- --------------------------------------------------------

--
-- Table structure for table `t_01_03_02`
--

CREATE TABLE `t_01_03_02` (
  `F_00` bigint(20) NOT NULL,
  `F_02` datetime(6) DEFAULT NULL,
  `F_01` varchar(500) DEFAULT NULL,
  `F_03` bigint(20) NOT NULL,
  `F_04` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_01_04_01`
--

CREATE TABLE `t_01_04_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(3) NOT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_01_04_01`
--

INSERT INTO `t_01_04_01` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`) VALUES
(1, 'AF', 'أفغانستان', 'Afghanistan', 'Afghanistan'),
(2, 'AX', 'جزر آلاند', 'Åland Islands', 'Îles Åland'),
(3, 'AL', 'ألبانيا', 'Albania', 'Albanie'),
(4, 'DZ', 'الجزائر', 'Algeria', 'Algérie'),
(5, 'AS', 'ساموا الأمريكية', 'American Samoa', 'Samoa Américaines'),
(6, 'AD', 'أندورا', 'Andorra', 'Andorre'),
(7, 'AO', 'أنغولا', 'Angola', 'Angola'),
(8, 'AI', 'أنغيلا', 'Anguilla', 'Anguilla'),
(9, 'AQ', 'القطب الجنوبي', 'Antarctica', 'Antarctique'),
(10, 'AG', 'أنتيغوا وباربودا', 'Antigua and Barbuda', 'Antigua-et-Barbuda'),
(11, 'AR', 'الأرجنتين', 'Argentina', 'Argentine'),
(12, 'AM', 'أرمينيا', 'Armenia', 'Arménie'),
(13, 'AW', 'أروبا', 'Aruba', 'Aruba'),
(14, 'AU', 'أستراليا', 'Australia', 'Australie'),
(15, 'AT', 'النمسا', 'Austria', 'Autriche'),
(16, 'AZ', 'أذربيجان', 'Azerbaijan', 'Azerbaïdjan'),
(17, 'BS', 'البهاما', 'Bahamas', 'Bahamas'),
(18, 'BH', 'البحرين', 'Bahrain', 'Bahreïn'),
(19, 'BD', 'بنغلاديش', 'Bangladesh', 'Bangladesh'),
(20, 'BB', 'باربادوس', 'Barbados', 'Barbade'),
(21, 'BY', 'بيلاروسيا', 'Belarus', 'Bélarus'),
(22, 'BE', 'بلجيكا', 'Belgium', 'Belgique'),
(23, 'BZ', 'بليز', 'Belize', 'Belize'),
(24, 'BJ', 'بنين', 'Benin', 'Bénin'),
(25, 'BM', 'برمودا', 'Bermuda', 'Bermudes'),
(26, 'BT', 'بوتان', 'Bhutan', 'Bhoutan'),
(27, 'BO', 'بوليفيا', 'Bolivia', 'Bolivie'),
(28, 'BQ', 'بونير وسينت أوستاتيوس وسابا', 'Bonaire, Sint Eustatius and Saba', 'Bonaire, Saint-Eustache et Saba'),
(29, 'BA', 'البوسنة والهرسك', 'Bosnia and Herzegovina', 'Bosnie-Herzégovine'),
(30, 'BW', 'بوتسوانا', 'Botswana', 'Botswana'),
(31, 'BV', 'جزيرة بوفيه', 'Bouvet Island', 'Île Bouvet'),
(32, 'BR', 'البرازيل', 'Brazil', 'Brésil'),
(33, 'IO', 'إقليم المحيط الهندي البريطاني', 'British Indian Ocean Territory', 'Territoire Britannique de l\'Océan Indien'),
(34, 'BN', 'بروناي', 'Brunei Darussalam', 'Brunéi Darussalam'),
(35, 'BG', 'بلغاريا', 'Bulgaria', 'Bulgarie'),
(36, 'BF', 'بوركينا فاسو', 'Burkina Faso', 'Burkina Faso'),
(37, 'BI', 'بوروندي', 'Burundi', 'Burundi'),
(38, 'CV', 'الرأس الأخضر', 'Cabo Verde', 'Cabo Verde'),
(39, 'KH', 'كمبوديا', 'Cambodia', 'Cambodge'),
(40, 'CM', 'الكاميرون', 'Cameroon', 'Cameroun'),
(41, 'CA', 'كندا', 'Canada', 'Canada'),
(42, 'KY', 'جزر كايمان', 'Cayman Islands', 'Îles Caïmans'),
(43, 'CF', 'جمهورية أفريقيا الوسطى', 'Central African Republic', 'République Centrafricaine'),
(44, 'TD', 'تشاد', 'Chad', 'Tchad'),
(45, 'CL', 'تشيلي', 'Chile', 'Chili'),
(46, 'CN', 'الصين', 'China', 'Chine'),
(47, 'CX', 'جزيرة كريسماس', 'Christmas Island', 'Île Christmas'),
(48, 'CC', 'جزر كوكوس', 'Cocos (Keeling) Islands', 'Îles Cocos'),
(49, 'CO', 'كولومبيا', 'Colombia', 'Colombie'),
(50, 'KM', 'جزر القمر', 'Comoros', 'Comores'),
(51, 'CD', 'جمهورية الكونغو الديمقراطية', 'Congo (Democratic Republic)', 'Congo (République Démocratique)'),
(52, 'CG', 'جمهورية الكونغو', 'Congo', 'Congo'),
(53, 'CK', 'جزر كوك', 'Cook Islands', 'Îles Cook'),
(54, 'CR', 'كوستاريكا', 'Costa Rica', 'Costa Rica'),
(55, 'CI', 'ساحل العاج', 'Côte d\'Ivoire', 'Côte d\'Ivoire'),
(56, 'HR', 'كرواتيا', 'Croatia', 'Croatie'),
(57, 'CU', 'كوبا', 'Cuba', 'Cuba'),
(58, 'CW', 'كوراساو', 'Curaçao', 'Curaçao'),
(59, 'CY', 'قبرص', 'Cyprus', 'Chypre'),
(60, 'CZ', 'التشيك', 'Czechia', 'République Tchèque'),
(61, 'DK', 'الدنمارك', 'Denmark', 'Danemark'),
(62, 'DJ', 'جيبوتي', 'Djibouti', 'Djibouti'),
(63, 'DM', 'دومينيكا', 'Dominica', 'Dominique'),
(64, 'DO', 'جمهورية الدومينيكان', 'Dominican Republic', 'République Dominicaine'),
(65, 'EC', 'الإكوادور', 'Ecuador', 'Équateur'),
(66, 'EG', 'مصر', 'Egypt', 'Égypte'),
(67, 'SV', 'السلفادور', 'El Salvador', 'El Salvador'),
(68, 'GQ', 'غينيا الاستوائية', 'Equatorial Guinea', 'Guinée Équatoriale'),
(69, 'ER', 'إريتريا', 'Eritrea', 'Érythrée'),
(70, 'EE', 'إستونيا', 'Estonia', 'Estonie'),
(71, 'SZ', 'إسواتيني', 'Eswatini', 'Eswatini'),
(72, 'ET', 'إثيوبيا', 'Ethiopia', 'Éthiopie'),
(73, 'FK', 'جزر فوكلاند', 'Falkland Islands', 'Îles Malouines'),
(74, 'FO', 'جزر فارو', 'Faroe Islands', 'Îles Féroé'),
(75, 'FJ', 'فيجي', 'Fiji', 'Fidji'),
(76, 'FI', 'فنلندا', 'Finland', 'Finlande'),
(77, 'FR', 'فرنسا', 'France', 'France'),
(78, 'GF', 'غويانا الفرنسية', 'French Guiana', 'Guyane Française'),
(79, 'PF', 'بولينيزيا الفرنسية', 'French Polynesia', 'Polynésie Française'),
(80, 'TF', 'الأقاليم الجنوبية الفرنسية', 'French Southern Territories', 'Terres Australes Françaises'),
(81, 'GA', 'الغابون', 'Gabon', 'Gabon'),
(82, 'GM', 'غامبيا', 'Gambia', 'Gambie'),
(83, 'GE', 'جورجيا', 'Georgia', 'Géorgie'),
(84, 'DE', 'ألمانيا', 'Germany', 'Allemagne'),
(85, 'GH', 'غانا', 'Ghana', 'Ghana'),
(86, 'GI', 'جبل طارق', 'Gibraltar', 'Gibraltar'),
(87, 'GR', 'اليونان', 'Greece', 'Grèce'),
(88, 'GL', 'جرينلاند', 'Greenland', 'Groenland'),
(89, 'GD', 'جرينادا', 'Grenada', 'Grenade'),
(90, 'GP', 'جوادلوب', 'Guadeloupe', 'Guadeloupe'),
(91, 'GU', 'غوام', 'Guam', 'Guam'),
(92, 'GT', 'غواتيمالا', 'Guatemala', 'Guatemala'),
(93, 'GG', 'غيرنزي', 'Guernsey', 'Guernesey'),
(94, 'GN', 'غينيا', 'Guinea', 'Guinée'),
(95, 'GW', 'غينيا بيساو', 'Guinea-Bissau', 'Guinée-Bissau'),
(96, 'GY', 'غويانا', 'Guyana', 'Guyana'),
(97, 'HT', 'هايتي', 'Haiti', 'Haïti'),
(98, 'HM', 'جزيرة هيرد وجزر ماكدونالد', 'Heard Island and McDonald Islands', 'Île Heard-et-Îles MacDonald'),
(99, 'VA', 'الفاتيكان', 'Holy See', 'Saint-Siège'),
(100, 'HN', 'هندوراس', 'Honduras', 'Honduras'),
(101, 'HK', 'هونغ كونغ', 'Hong Kong', 'Hong Kong'),
(102, 'HU', 'المجر', 'Hungary', 'Hongrie'),
(103, 'IS', 'آيسلندا', 'Iceland', 'Islande'),
(104, 'IN', 'الهند', 'India', 'Inde'),
(105, 'ID', 'إندونيسيا', 'Indonesia', 'Indonésie'),
(106, 'IR', 'إيران', 'Iran', 'Iran'),
(107, 'IQ', 'العراق', 'Iraq', 'Irak'),
(108, 'IE', 'أيرلندا', 'Ireland', 'Irlande'),
(109, 'IM', 'جزيرة مان', 'Isle of Man', 'Île de Man'),
(110, 'IL', 'إسرائيل', 'Israel', 'Israël'),
(111, 'IT', 'إيطاليا', 'Italy', 'Italie'),
(112, 'JM', 'جامايكا', 'Jamaica', 'Jamaïque'),
(113, 'JP', 'اليابان', 'Japan', 'Japon'),
(114, 'JE', 'جيرزي', 'Jersey', 'Jersey'),
(115, 'JO', 'الأردن', 'Jordan', 'Jordanie'),
(116, 'KZ', 'كازاخستان', 'Kazakhstan', 'Kazakhstan'),
(117, 'KE', 'كينيا', 'Kenya', 'Kenya'),
(118, 'KI', 'كيريباتي', 'Kiribati', 'Kiribati'),
(119, 'KP', 'كوريا الشمالية', 'Korea (North)', 'Corée du Nord'),
(120, 'KR', 'كوريا الجنوبية', 'Korea (South)', 'Corée du Sud'),
(121, 'KW', 'الكويت', 'Kuwait', 'Koweït'),
(122, 'KG', 'قيرغيزستان', 'Kyrgyzstan', 'Kirghizistan'),
(123, 'LA', 'لاوس', 'Laos', 'Laos'),
(124, 'LV', 'لاتفيا', 'Latvia', 'Lettonie'),
(125, 'LB', 'لبنان', 'Lebanon', 'Liban'),
(126, 'LS', 'ليسوتو', 'Lesotho', 'Lesotho'),
(127, 'LR', 'ليبيريا', 'Liberia', 'Libéria'),
(128, 'LY', 'ليبيا', 'Libya', 'Libye'),
(129, 'LI', 'ليختنشتاين', 'Liechtenstein', 'Liechtenstein'),
(130, 'LT', 'ليتوانيا', 'Lithuania', 'Lituanie'),
(131, 'LU', 'لوكسمبورغ', 'Luxembourg', 'Luxembourg'),
(132, 'MO', 'ماكاو', 'Macao', 'Macao'),
(133, 'MG', 'مدغشقر', 'Madagascar', 'Madagascar'),
(134, 'MW', 'مالاوي', 'Malawi', 'Malawi'),
(135, 'MY', 'ماليزيا', 'Malaysia', 'Malaisie'),
(136, 'MV', 'جزر المالديف', 'Maldives', 'Maldives'),
(137, 'ML', 'مالي', 'Mali', 'Mali'),
(138, 'MT', 'مالطا', 'Malta', 'Malte'),
(139, 'MH', 'جزر مارشال', 'Marshall Islands', 'Îles Marshall'),
(140, 'MQ', 'مارتينيك', 'Martinique', 'Martinique'),
(141, 'MR', 'موريتانيا', 'Mauritania', 'Mauritanie'),
(142, 'MU', 'موريشيوس', 'Mauritius', 'Maurice'),
(143, 'YT', 'مايوت', 'Mayotte', 'Mayotte'),
(144, 'MX', 'المكسيك', 'Mexico', 'Mexique'),
(145, 'FM', 'ميكرونيزيا', 'Micronesia', 'Micronésie'),
(146, 'MD', 'مولدوفا', 'Moldova', 'Moldavie'),
(147, 'MC', 'موناكو', 'Monaco', 'Monaco'),
(148, 'MN', 'منغوليا', 'Mongolia', 'Mongolie'),
(149, 'ME', 'الجبل الأسود', 'Montenegro', 'Monténégro'),
(150, 'MS', 'مونتسرات', 'Montserrat', 'Montserrat'),
(151, 'MA', 'المغرب', 'Morocco', 'Maroc'),
(152, 'MZ', 'موزمبيق', 'Mozambique', 'Mozambique'),
(153, 'MM', 'ميانمار', 'Myanmar', 'Myanmar'),
(154, 'NA', 'ناميبيا', 'Namibia', 'Namibie'),
(155, 'NR', 'ناورو', 'Nauru', 'Nauru'),
(156, 'NP', 'نيبال', 'Nepal', 'Népal'),
(157, 'NL', 'هولندا', 'Netherlands', 'Pays-Bas'),
(158, 'NC', 'كاليدونيا الجديدة', 'New Caledonia', 'Nouvelle-Calédonie'),
(159, 'NZ', 'نيوزيلندا', 'New Zealand', 'Nouvelle-Zélande'),
(160, 'NI', 'نيكاراغوا', 'Nicaragua', 'Nicaragua'),
(161, 'NE', 'النيجر', 'Niger', 'Niger'),
(162, 'NG', 'نيجيريا', 'Nigeria', 'Nigéria'),
(163, 'NU', 'نيوي', 'Niue', 'Niue'),
(164, 'NF', 'جزيرة نورفولك', 'Norfolk Island', 'Île Norfolk'),
(165, 'MK', 'مقدونيا الشمالية', 'North Macedonia', 'Macédoine du Nord'),
(166, 'MP', 'جزر ماريانا الشمالية', 'Northern Mariana Islands', 'Îles Mariannes du Nord'),
(167, 'NO', 'النرويج', 'Norway', 'Norvège'),
(168, 'OM', 'عمان', 'Oman', 'Oman'),
(169, 'PK', 'باكستان', 'Pakistan', 'Pakistan'),
(170, 'PW', 'بالاو', 'Palau', 'Palaos'),
(171, 'PS', 'فلسطين', 'Palestine', 'Palestine'),
(172, 'PA', 'بنما', 'Panama', 'Panama'),
(173, 'PG', 'بابوا غينيا الجديدة', 'Papua New Guinea', 'Papouasie-Nouvelle-Guinée'),
(174, 'PY', 'باراغواي', 'Paraguay', 'Paraguay'),
(175, 'PE', 'بيرو', 'Peru', 'Pérou'),
(176, 'PH', 'الفلبين', 'Philippines', 'Philippines'),
(177, 'PN', 'جزر بيتكيرن', 'Pitcairn', 'Pitcairn'),
(178, 'PL', 'بولندا', 'Poland', 'Pologne'),
(179, 'PT', 'البرتغال', 'Portugal', 'Portugal'),
(180, 'PR', 'بورتوريكو', 'Puerto Rico', 'Porto Rico'),
(181, 'QA', 'قطر', 'Qatar', 'Qatar'),
(182, 'RE', 'ريونيون', 'Réunion', 'Réunion'),
(183, 'RO', 'رومانيا', 'Romania', 'Roumanie'),
(184, 'RU', 'روسيا', 'Russia', 'Russie'),
(185, 'RW', 'رواندا', 'Rwanda', 'Rwanda'),
(186, 'BL', 'سان بارتيلمي', 'Saint Barthélemy', 'Saint-Barthélemy'),
(187, 'SH', 'سانت هيلينا وأسنسيون وتريستان دا كونا', 'Saint Helena', 'Sainte-Hélène'),
(188, 'KN', 'سانت كيتس ونيفيس', 'Saint Kitts and Nevis', 'Saint-Kitts-et-Nevis'),
(189, 'LC', 'سانت لوسيا', 'Saint Lucia', 'Sainte-Lucie'),
(190, 'MF', 'سانت مارتن', 'Saint Martin', 'Saint-Martin'),
(191, 'PM', 'سان بيير وميكلون', 'Saint Pierre and Miquelon', 'Saint-Pierre-et-Miquelon'),
(192, 'VC', 'سانت فينسنت والغرينادين', 'Saint Vincent and the Grenadines', 'Saint-Vincent-et-les-Grenadines'),
(193, 'WS', 'ساموا', 'Samoa', 'Samoa'),
(194, 'SM', 'سان مارينو', 'San Marino', 'Saint-Marin'),
(195, 'ST', 'ساو تومي وبرينسيب', 'Sao Tome and Principe', 'Sao Tomé-et-Principe'),
(196, 'SA', 'السعودية', 'Saudi Arabia', 'Arabie Saoudite'),
(197, 'SN', 'السنغال', 'Senegal', 'Sénégal'),
(198, 'RS', 'صربيا', 'Serbia', 'Serbie'),
(199, 'SC', 'سيشل', 'Seychelles', 'Seychelles'),
(200, 'SL', 'سيراليون', 'Sierra Leone', 'Sierra Leone'),
(201, 'SG', 'سنغافورة', 'Singapore', 'Singapour'),
(202, 'SX', 'سينت مارتن', 'Sint Maarten', 'Saint-Martin'),
(203, 'SK', 'سلوفاكيا', 'Slovakia', 'Slovaquie'),
(204, 'SI', 'سلوفينيا', 'Slovenia', 'Slovénie'),
(205, 'SB', 'جزر سليمان', 'Solomon Islands', 'Îles Salomon'),
(206, 'SO', 'الصومال', 'Somalia', 'Somalie'),
(207, 'ZA', 'جنوب أفريقيا', 'South Africa', 'Afrique du Sud'),
(208, 'GS', 'جورجيا الجنوبية وجزر ساندويتش الجنوبية', 'South Georgia and South Sandwich Islands', 'Géorgie du Sud-et-les Îles Sandwich du Sud'),
(209, 'SS', 'جنوب السودان', 'South Sudan', 'Soudan du Sud'),
(210, 'ES', 'إسبانيا', 'Spain', 'Espagne'),
(211, 'LK', 'سريلانكا', 'Sri Lanka', 'Sri Lanka'),
(212, 'SD', 'السودان', 'Sudan', 'Soudan'),
(213, 'SR', 'سورينام', 'Suriname', 'Suriname'),
(214, 'SJ', 'سفالبارد ويان ماين', 'Svalbard and Jan Mayen', 'Svalbard et Jan Mayen'),
(215, 'SE', 'السويد', 'Sweden', 'Suède'),
(216, 'CH', 'سويسرا', 'Switzerland', 'Suisse'),
(217, 'SY', 'سوريا', 'Syria', 'Syrie'),
(218, 'TW', 'تايوان', 'Taiwan', 'Taïwan'),
(219, 'TJ', 'طاجيكستان', 'Tajikistan', 'Tadjikistan'),
(220, 'TZ', 'تنزانيا', 'Tanzania', 'Tanzanie'),
(221, 'TH', 'تايلاند', 'Thailand', 'Thaïlande'),
(222, 'TL', 'تيمور الشرقية', 'Timor-Leste', 'Timor-Leste'),
(223, 'TG', 'توغو', 'Togo', 'Togo'),
(224, 'TK', 'توكيلاو', 'Tokelau', 'Tokelau'),
(225, 'TO', 'تونغا', 'Tonga', 'Tonga'),
(226, 'TT', 'ترينيداد وتوباغو', 'Trinidad and Tobago', 'Trinité-et-Tobago'),
(227, 'TN', 'تونس', 'Tunisia', 'Tunisie'),
(228, 'TR', 'تركيا', 'Türkiye', 'Turquie'),
(229, 'TM', 'تركمانستان', 'Turkmenistan', 'Turkménistan'),
(230, 'TC', 'جزر توركس وكايكوس', 'Turks and Caicos Islands', 'Îles Turques-et-Caïques'),
(231, 'TV', 'توفالو', 'Tuvalu', 'Tuvalu'),
(232, 'UG', 'أوغندا', 'Uganda', 'Ouganda'),
(233, 'UA', 'أوكرانيا', 'Ukraine', 'Ukraine'),
(234, 'AE', 'الإمارات العربية المتحدة', 'United Arab Emirates', 'Émirats Arabes Unis'),
(235, 'GB', 'المملكة المتحدة', 'United Kingdom', 'Royaume-Uni'),
(236, 'UM', 'جزر الولايات المتحدة البعيدة الصغيرة', 'United States Minor Outlying Islands', 'Îles Mineures Éloignées des États-Unis'),
(237, 'US', 'الولايات المتحدة', 'United States', 'États-Unis'),
(238, 'UY', 'أوروغواي', 'Uruguay', 'Uruguay'),
(239, 'UZ', 'أوزبكستان', 'Uzbekistan', 'Ouzbékistan'),
(240, 'VU', 'فانواتو', 'Vanuatu', 'Vanuatu'),
(241, 'VE', 'فنزويلا', 'Venezuela', 'Venezuela'),
(242, 'VN', 'فيتنام', 'Vietnam', 'Viêt Nam'),
(243, 'VG', 'جزر العذراء البريطانية', 'Virgin Islands (British)', 'Îles Vierges Britanniques'),
(244, 'VI', 'جزر العذراء الأمريكية', 'Virgin Islands (U.S.)', 'Îles Vierges des États-Unis'),
(245, 'WF', 'والس وفوتونا', 'Wallis and Futuna', 'Wallis-et-Futuna'),
(246, 'EH', 'الصحراء الغربية', 'Western Sahara', 'Sahara Occidental'),
(247, 'YE', 'اليمن', 'Yemen', 'Yémen'),
(248, 'ZM', 'زامبيا', 'Zambia', 'Zambie'),
(249, 'ZW', 'زيمبابوي', 'Zimbabwe', 'Zimbabwe');

-- --------------------------------------------------------

--
-- Table structure for table `t_01_04_02`
--

CREATE TABLE `t_01_04_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` int(11) NOT NULL,
  `F_02` varchar(100) NOT NULL,
  `F_03` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_01_04_02`
--

INSERT INTO `t_01_04_02` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 1, 'أدرار', 'Adrar'),
(2, 2, 'الشلف', 'Chlef'),
(3, 3, 'الأغواط', 'Laghouat'),
(4, 4, 'أم البواقي', 'Oum El Bouaghi'),
(5, 5, 'باتنة', 'Batna'),
(6, 6, 'بجاية', 'Béjaïa'),
(7, 7, 'بسكرة', 'Biskra'),
(8, 8, 'بشار', 'Béchar'),
(9, 9, 'البليدة', 'Blida'),
(10, 10, 'البويرة', 'Bouïra'),
(11, 11, 'تمنراست', 'Tamanrasset'),
(12, 12, 'تبسة', 'Tébessa'),
(13, 13, 'تلمسان', 'Tlemcen'),
(14, 14, 'تيارت', 'Tiaret'),
(15, 15, 'تيزي وزو', 'Tizi Ouzou'),
(16, 16, 'الجزائر', 'Alger'),
(17, 17, 'الجلفة', 'Djelfa'),
(18, 18, 'جيجل', 'Jijel'),
(19, 19, 'سطيف', 'Sétif'),
(20, 20, 'سعيدة', 'Saïda'),
(21, 21, 'سكيكدة', 'Skikda'),
(22, 22, 'سيدي بلعباس', 'Sidi Bel Abbès'),
(23, 23, 'عنابة', 'Annaba'),
(24, 24, 'قالمة', 'Guelma'),
(25, 25, 'قسنطينة', 'Constantine'),
(26, 26, 'المدية', 'Médéa'),
(27, 27, 'مستغانم', 'Mostaganem'),
(28, 28, 'المسيلة', 'M\'Sila'),
(29, 29, 'معسكر', 'Mascara'),
(30, 30, 'ورقلة', 'Ouargla'),
(31, 31, 'وهران', 'Oran'),
(32, 32, 'البيض', 'El Bayadh'),
(33, 33, 'إليزي', 'Illizi'),
(34, 34, 'برج بوعريريج', 'Bordj Bou Arréridj'),
(35, 35, 'بومرداس', 'Boumerdès'),
(36, 36, 'الطارف', 'El Tarf'),
(37, 37, 'تندوف', 'Tindouf'),
(38, 38, 'تيسمسيلت', 'Tissemsilt'),
(39, 39, 'الوادي', 'El Oued'),
(40, 40, 'خنشلة', 'Khenchela'),
(41, 41, 'سوق أهراس', 'Souk Ahras'),
(42, 42, 'تيبازة', 'Tipaza'),
(43, 43, 'ميلة', 'Mila'),
(44, 44, 'عين الدفلى', 'Aïn Defla'),
(45, 45, 'النعامة', 'Naâma'),
(46, 46, 'عين تموشنت', 'Aïn Témouchent'),
(47, 47, 'غرداية', 'Ghardaïa'),
(48, 48, 'غليزان', 'Relizane'),
(49, 49, 'تيميمون', 'Timimoun'),
(50, 50, 'برج باجي مختار', 'Bordj Baji Mokhtar'),
(51, 51, 'أولاد جلال', 'Ouled Djellal'),
(52, 52, 'بني عباس', 'Béni Abbès'),
(53, 53, 'إن صالح', 'In Salah'),
(54, 54, 'إن قزام', 'In Guezzam'),
(55, 55, 'تقرت', 'Touggourt'),
(56, 56, 'جانت', 'Djanet'),
(57, 57, 'المغير', 'El M\'Ghair'),
(58, 58, 'المنيعة', 'El Menia');

-- --------------------------------------------------------

--
-- Table structure for table `t_01_04_03`
--

CREATE TABLE `t_01_04_03` (
  `F_00` bigint(20) NOT NULL,
  `F_01` int(11) NOT NULL,
  `F_02` varchar(100) NOT NULL,
  `F_03` varchar(100) NOT NULL,
  `F_04` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_01_04_04`
--

CREATE TABLE `t_01_04_04` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(50) DEFAULT NULL,
  `F_02` varchar(50) DEFAULT NULL,
  `F_03` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_01_04_04`
--

INSERT INTO `t_01_04_04` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'الضباط العمداء', 'General Officers', 'Officiers Généraux'),
(2, 'الضباط السامون', 'Senior Officers', 'Officiers Supérieurs'),
(3, 'الضباط المرؤوسون', 'Junior Officers', 'Officiers Subalternes'),
(4, 'ضباط صف', 'Non-Commissioned Officers', 'Sous-Officiers'),
(5, 'رجال الصف', 'Enlisted Personnel', 'Hommes du Rang'),
(6, 'مستخدمون مدنيون', 'Civilian Personnel', 'Personnel Civile');

-- --------------------------------------------------------

--
-- Table structure for table `t_01_04_05`
--

CREATE TABLE `t_01_04_05` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(10) DEFAULT NULL,
  `F_05` varchar(10) DEFAULT NULL,
  `F_06` varchar(10) NOT NULL,
  `F_01` varchar(50) DEFAULT NULL,
  `F_02` varchar(50) DEFAULT NULL,
  `F_03` varchar(50) NOT NULL,
  `F_07` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_01_04_05`
--

INSERT INTO `t_01_04_05` (`F_00`, `F_04`, `F_05`, `F_06`, `F_01`, `F_02`, `F_03`, `F_07`) VALUES
(1, NULL, NULL, '', 'General', 'فريق أول', 'Général d\'Armée', 1),
(2, NULL, NULL, '', 'Lieutenant General', 'فريق', 'Général de Corps d\'Armée', 1),
(3, NULL, NULL, '', 'Major General', 'لواء', 'Général Major', 1),
(4, NULL, NULL, '', 'Brigadier General', 'عميد', 'Général', 1),
(5, NULL, NULL, '', 'Colonel', 'عقيد', 'Colonel', 2),
(6, NULL, NULL, '', 'Lieutenant Colonel', 'مقدم', 'Lieutenant Colonel', 2),
(7, NULL, NULL, '', 'Major', 'رائد', 'Commandant', 2),
(8, NULL, NULL, '', 'Captain', 'نقيب', 'Capitaine', 3),
(9, NULL, NULL, '', 'Lieutenant', 'ملازم أول', 'Lieutenant', 3),
(10, NULL, NULL, '', 'Second Lieutenant', 'ملازم', 'Sous Lieutenant', 3),
(11, NULL, NULL, '', 'Aspirant', 'مرشح', 'Aspirant', 3),
(12, NULL, NULL, '', 'Major Sergeant', 'مساعد رئيسي', 'Adjudant Major', 4),
(13, NULL, NULL, '', 'Master Sergeant', 'مساعد أول', 'Adjudant Chef', 4),
(14, NULL, NULL, '', 'First Class Sergeant', 'مساعد', 'Adjudant', 4),
(15, NULL, NULL, '', 'Staff Sergeant', 'رقيب أول', 'Sergent Chef', 4),
(16, NULL, NULL, '', 'Sergeant', 'رقيب', 'Sergent', 4),
(17, NULL, NULL, '', 'Corporal', 'عريف أول', 'Caporal Chef', 5),
(18, NULL, NULL, '', 'Lance Corporal', 'عريف', 'Caporal', 5),
(19, NULL, NULL, '', 'Private Soldier', 'جندي', 'Djoundi', 5),
(20, NULL, NULL, '', 'Assimilated Civilian Personnel', 'مستخدم مدني شبيه', 'Personnel Civile Assimilé', 6),
(21, NULL, NULL, '', 'Contractual Civilian Personnel', 'مستخدم مدني متعاقد', 'Personnel Civile Contractuel', 6);

-- --------------------------------------------------------

--
-- Table structure for table `t_01_04_06`
--

CREATE TABLE `t_01_04_06` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_01_04_06`
--

INSERT INTO `t_01_04_06` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'وزارة', 'Ministry', 'Ministère'),
(2, ' أركان الجيش', 'Army General Staff', 'Etat Major de l\'Armée'),
(3, 'مديرية عامة', 'General Directorate', 'Direction Générale'),
(4, 'الأمانة العامة لوزارة', 'Ministry General Secretariat', 'Secretariat Générale du Ministère'),
(5, 'ديوان', 'Cabinet', 'Cabinet'),
(6, 'قيادة قوات', 'Force Command', 'Commandement de Forces'),
(7, 'ناحية عسكرية', 'Military Region', 'Région Militaire'),
(8, ' أركان ناحية عسكرية', 'Military Region Staff', 'Etat Major d\'une Région Militaire'),
(9, 'دائرة', 'Department', 'Département'),
(10, 'مديرية مركزية', 'Central Directorate', 'Direction Centrale'),
(11, 'مديرية', 'Directorate', 'Direction'),
(12, 'مصلحة مركزية', 'Central Service', 'Service Central'),
(13, 'مصلحة', 'Service', 'Service'),
(14, 'مركز', 'Center', 'Centre'),
(15, 'مديرية دائرة', 'Department Directorate', 'Direction de Département'),
(16, 'مصلحة دائرة', 'Department Service', 'Service de Département'),
(17, 'مكتب دائرة', 'Department Office', 'Bureau de Département'),
(18, 'فصيلة', 'Section', 'Section'),
(19, 'مديرية جهوية', 'Regional Directorate', 'Direction Régionale'),
(20, 'مصلحة جهوية', 'Regional Service', 'Service Régional'),
(21, 'مكتب جهوي', 'Regional Office', 'Bureau Régional'),
(22, 'مركز جهوي', 'Regional Center', 'Centre Régional'),
(23, 'مديرية فرعية', 'Sub-Directorate', 'Sous Direction'),
(24, 'خلية', 'Cell', 'Cellule'),
(25, 'فصيلة فرعية', 'Subsection', 'Sous Section'),
(26, 'مكتب', 'Office', 'Bureau'),
(27, 'فصيلة مكتب', 'Office Section', 'Section de Bureau'),
(28, 'مدرسة وطنية', 'National School', 'Ecole Nationale'),
(29, 'مدرسة عليا', 'Higher School', 'Ecole Supérieure'),
(30, 'مدرسة تطبيقية', 'Application School', 'Ecole d\'Application'),
(31, 'مركز تدريب', 'Training Center', 'Centre d\'instruction'),
(32, 'مؤسسة وزارية', 'Ministerial Establishment', 'Etablissement Ministèriel'),
(33, 'مؤسسة مركزية', 'Central Establishment', 'Etablissement Centrale'),
(34, 'مؤسسة جهوية', 'Regional Establishment', 'Etablissement Régional'),
(35, 'فرقة', 'Division', 'Division'),
(36, 'لواء', 'Brigade', 'Brigade'),
(37, 'فوج', 'Regiment', 'Régiment'),
(38, 'فوج جهوي', 'Regional Regiment', 'Régiment Régional'),
(39, 'كتيبة', 'Battalion', 'Bataillon'),
(40, 'سرية', 'Company', 'Compagnie');

-- --------------------------------------------------------

--
-- Table structure for table `t_01_04_07`
--

CREATE TABLE `t_01_04_07` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(20) DEFAULT NULL,
  `F_05` varchar(20) DEFAULT NULL,
  `F_06` varchar(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL,
  `F_07` bigint(20) NOT NULL,
  `F_08` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_01_04_08`
--

CREATE TABLE `t_01_04_08` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL,
  `F_04` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_01_04_09`
--

CREATE TABLE `t_01_04_09` (
  `F_00` bigint(20) NOT NULL,
  `F_07` varchar(255) DEFAULT NULL,
  `F_05` datetime(6) DEFAULT NULL,
  `F_06` varchar(255) DEFAULT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) DEFAULT NULL,
  `F_09` bigint(20) DEFAULT NULL,
  `F_08` bigint(20) DEFAULT NULL,
  `F_10` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_01_04_10`
--

CREATE TABLE `t_01_04_10` (
  `F_00` bigint(20) NOT NULL,
  `F_02` datetime(6) DEFAULT NULL,
  `F_01` varchar(255) DEFAULT NULL,
  `F_05` bigint(20) DEFAULT NULL,
  `F_04` bigint(20) NOT NULL,
  `F_03` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_01_01`
--

CREATE TABLE `t_02_01_01` (
  `F_00` bigint(20) NOT NULL,
  `F_05` varchar(20) NOT NULL,
  `F_06` varchar(20) NOT NULL,
  `F_07` varchar(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_02` varchar(50) NOT NULL,
  `F_03` varchar(50) NOT NULL,
  `F_04` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_02_01_01`
--

INSERT INTO `t_02_01_01` (`F_00`, `F_05`, `F_06`, `F_07`, `F_01`, `F_02`, `F_03`, `F_04`) VALUES
(1, 'US$', 'USD', '$', 'USD', 'دولار أمريكي', 'United States Dollar', 'Dollar des États-Unis'),
(2, '€', 'EUR', '€', 'EUR', 'يورو', 'Euro', 'Euro'),
(3, '£', 'GBP', '£', 'GBP', 'جنيه إسترليني', 'British Pound Sterling', 'Livre Sterling'),
(4, '¥', 'JPY', '¥', 'JPY', 'ين ياباني', 'Japanese Yen', 'Yen Japonais'),
(5, 'CHF', 'CHF', 'Fr', 'CHF', 'فرنك سويسري', 'Swiss Franc', 'Franc Suisse'),
(6, 'CA$', 'CAD', '$', 'CAD', 'دولار كندي', 'Canadian Dollar', 'Dollar Canadien'),
(7, 'A$', 'AUD', '$', 'AUD', 'دولار أسترالي', 'Australian Dollar', 'Dollar Australien'),
(8, '¥', 'CNY', '¥', 'CNY', 'يوان صيني', 'Chinese Yuan', 'Yuan Chinois'),
(9, 'د.إ', 'AED', 'Dh', 'AED', 'درهم إماراتي', 'UAE Dirham', 'Dirham des Émirats'),
(10, 'د.ب', 'BHD', 'BD', 'BHD', 'دينار بحريني', 'Bahraini Dinar', 'Dinar de Bahreïn'),
(11, 'د.ج', 'DZD', 'DA', 'DZD', 'دينار جزائري', 'Algerian Dinar', 'Dinar Algérien'),
(12, 'ج.م', 'EGP', 'LE', 'EGP', 'جنيه مصري', 'Egyptian Pound', 'Livre Égyptienne'),
(13, 'د.ع', 'IQD', 'ID', 'IQD', 'دينار عراقي', 'Iraqi Dinar', 'Dinar Irakien'),
(14, 'د.ا', 'JOD', 'JD', 'JOD', 'دينار أردني', 'Jordanian Dinar', 'Dinar Jordanien'),
(15, 'د.ك', 'KWD', 'KD', 'KWD', 'دينار كويتي', 'Kuwaiti Dinar', 'Dinar Koweïtien'),
(16, 'ل.ل', 'LBP', 'LL', 'LBP', 'ليرة لبنانية', 'Lebanese Pound', 'Livre Libanaise'),
(17, 'د.ل', 'LYD', 'LD', 'LYD', 'دينار ليبي', 'Libyan Dinar', 'Dinar Libyen'),
(18, 'د.م', 'MAD', 'DH', 'MAD', 'درهم مغربي', 'Moroccan Dirham', 'Dirham Marocain'),
(19, 'أ.م', 'MRU', 'UM', 'MRU', 'أوقية موريتانية', 'Mauritanian Ouguiya', 'Ouguiya Mauritanien'),
(20, 'ر.ع', 'OMR', 'OR', 'OMR', 'ريال عماني', 'Omani Rial', 'Rial Omanais'),
(21, 'ر.ق', 'QAR', 'QR', 'QAR', 'ريال قطري', 'Qatari Riyal', 'Riyal Qatarien'),
(22, 'ر.س', 'SAR', 'SR', 'SAR', 'ريال سعودي', 'Saudi Riyal', 'Riyal Saoudien'),
(23, 'ج.س', 'SDG', 'SD', 'SDG', 'جنيه سوداني', 'Sudanese Pound', 'Livre Soudanaise'),
(24, 'ش.ص', 'SOS', 'So', 'SOS', 'شلن صومالي', 'Somali Shilling', 'Shilling Somalien'),
(25, 'ل.س', 'SYP', 'SP', 'SYP', 'ليرة سورية', 'Syrian Pound', 'Livre Syrienne'),
(26, 'د.ت', 'TND', 'TD', 'TND', 'دينار تونسي', 'Tunisian Dinar', 'Dinar Tunisien'),
(27, 'ر.ي', 'YER', 'YR', 'YER', 'ريال يمني', 'Yemeni Rial', 'Rial Yéménite'),
(28, '؋', 'AFN', 'Af', 'AFN', 'أفغاني', 'Afghan Afghani', 'Afghani Afghan'),
(29, 'L', 'ALL', 'Lek', 'ALL', 'ليك ألباني', 'Albanian Lek', 'Lek Albanais'),
(30, '֏', 'AMD', 'AMD', 'AMD', 'درام أرميني', 'Armenian Dram', 'Dram Arménien'),
(31, 'ƒ', 'ANG', 'NAf', 'ANG', 'غيلدر أنتيلي', 'Netherlands Antillean Guilder', 'Florin Antillais'),
(32, 'Kz', 'AOA', 'Kz', 'AOA', 'كوانزا أنغولي', 'Angolan Kwanza', 'Kwanza Angolais'),
(33, '$', 'ARS', '$', 'ARS', 'بيزو أرجنتيني', 'Argentine Peso', 'Peso Argentin'),
(34, '₼', 'AZN', 'AZN', 'AZN', 'مانات أذربيجاني', 'Azerbaijani Manat', 'Manat Azerbaïdjanais'),
(35, 'KM', 'BAM', 'KM', 'BAM', 'مارك بوسني', 'Bosnia-Herzegovina Mark', 'Mark Bosniaque'),
(36, '$', 'BBD', '$', 'BBD', 'دولار بربادوسي', 'Barbadian Dollar', 'Dollar Barbadien'),
(37, '৳', 'BDT', '৳', 'BDT', 'تاكا بنغلاديشي', 'Bangladeshi Taka', 'Taka Bangladais'),
(38, 'лв', 'BGN', 'BGN', 'BGN', 'ليف بلغاري', 'Bulgarian Lev', 'Lev Bulgare'),
(39, 'Fr', 'BIF', 'FBu', 'BIF', 'فرنك بوروندي', 'Burundian Franc', 'Franc Burundais'),
(40, '$', 'BMD', '$', 'BMD', 'دولار برمودي', 'Bermudian Dollar', 'Dollar Bermudien'),
(41, '$', 'BND', '$', 'BND', 'دولار بروناي', 'Brunei Dollar', 'Dollar de Brunei'),
(42, 'Bs.', 'BOB', 'Bs', 'BOB', 'بوليفيانو', 'Bolivian Boliviano', 'Boliviano'),
(43, 'R$', 'BRL', 'R$', 'BRL', 'ريال برازيلي', 'Brazilian Real', 'Réal Brésilien'),
(44, '$', 'BSD', '$', 'BSD', 'دولار باهامي', 'Bahamian Dollar', 'Dollar Bahaméen'),
(45, 'Nu.', 'BTN', 'Nu', 'BTN', 'نغولترم بوتاني', 'Bhutanese Ngultrum', 'Ngultrum Bhoutanais'),
(46, 'P', 'BWP', 'P', 'BWP', 'بولا بوتسواني', 'Botswanan Pula', 'Pula Botswanais'),
(47, 'Br', 'BYN', 'Br', 'BYN', 'روبل بيلاروسي', 'Belarusian Ruble', 'Rouble Biélorusse'),
(48, '$', 'BZD', 'BZ$', 'BZD', 'دولار بليزي', 'Belize Dollar', 'Dollar Bélizien'),
(49, 'Fr', 'CDF', 'FC', 'CDF', 'فرنك كونغولي', 'Congolese Franc', 'Franc Congolais'),
(50, '$', 'CLP', '$', 'CLP', 'بيزو تشيلي', 'Chilean Peso', 'Peso Chilien'),
(51, '$', 'COP', '$', 'COP', 'بيزو كولومبي', 'Colombian Peso', 'Peso Colombien'),
(52, '₡', 'CRC', '₡', 'CRC', 'كولون كوستاريكي', 'Costa Rican Colón', 'Colon Costaricain'),
(53, '$', 'CUP', '$', 'CUP', 'بيزو كوبي', 'Cuban Peso', 'Peso Cubain'),
(54, '$', 'CVE', 'Esc', 'CVE', 'إسكودو الرأس الأخضر', 'Cape Verdean Escudo', 'Escudo Cap-Verdien'),
(55, 'Kč', 'CZK', 'Kč', 'CZK', 'كورونا تشيكية', 'Czech Koruna', 'Couronne Tchèque'),
(56, 'Fr', 'DJF', 'Fdj', 'DJF', 'فرنك جيبوتي', 'Djiboutian Franc', 'Franc Djiboutien'),
(57, 'kr', 'DKK', 'kr', 'DKK', 'كرونة دنماركية', 'Danish Krone', 'Couronne Danoise'),
(58, '$', 'DOP', 'RD$', 'DOP', 'بيزو دومينيكاني', 'Dominican Peso', 'Peso Dominicain'),
(59, 'Br', 'ETB', 'Br', 'ETB', 'بير إثيوبي', 'Ethiopian Birr', 'Birr Éthiopien'),
(60, '$', 'FJD', '$', 'FJD', 'دولار فيجي', 'Fijian Dollar', 'Dollar Fidjien'),
(61, '₾', 'GEL', 'GEL', 'GEL', 'لاري جورجي', 'Georgian Lari', 'Lari Géorgien'),
(62, '₵', 'GHS', 'GH₵', 'GHS', 'سيدي غاناوي', 'Ghanaian Cedi', 'Cedi Ghanéen'),
(63, 'D', 'GMD', 'D', 'GMD', 'دالاسي غامبي', 'Gambian Dalasi', 'Dalasi Gambien'),
(64, 'Fr', 'GNF', 'FG', 'GNF', 'فرنك غيني', 'Guinean Franc', 'Franc Guinéen'),
(65, 'Q', 'GTQ', 'Q', 'GTQ', 'كتزال غواتيمالي', 'Guatemalan Quetzal', 'Quetzal Guatémaltèque'),
(66, '$', 'GYD', '$', 'GYD', 'دولار غياني', 'Guyanese Dollar', 'Dollar Guyanien'),
(67, 'HK$', 'HKD', 'HK$', 'HKD', 'دولار هونغ كونغ', 'Hong Kong Dollar', 'Dollar de Hong Kong'),
(68, 'L', 'HNL', 'L', 'HNL', 'ليمبيرا هندوراسي', 'Honduran Lempira', 'Lempira Hondurien'),
(69, 'kn', 'HRK', 'kn', 'HRK', 'كونا كرواتية', 'Croatian Kuna', 'Kuna Croate'),
(70, 'G', 'HTG', 'G', 'HTG', 'غورد هايتي', 'Haitian Gourde', 'Gourde Haïtienne'),
(71, 'Ft', 'HUF', 'Ft', 'HUF', 'فورنت هنغاري', 'Hungarian Forint', 'Forint Hongrois'),
(72, 'Rp', 'IDR', 'Rp', 'IDR', 'روبية إندونيسية', 'Indonesian Rupiah', 'Roupie Indonésienne'),
(73, '₪', 'ILS', '₪', 'ILS', 'شيكل إسرائيلي', 'Israeli New Shekel', 'Shekel Israélien'),
(74, '₹', 'INR', '₹', 'INR', 'روبية هندية', 'Indian Rupee', 'Roupie Indienne'),
(75, '﷼', 'IRR', 'IR', 'IRR', 'ريال إيراني', 'Iranian Rial', 'Rial Iranien'),
(76, 'kr', 'ISK', 'kr', 'ISK', 'كرونا آيسلندية', 'Icelandic Króna', 'Couronne Islandaise'),
(77, '$', 'JMD', 'J$', 'JMD', 'دولار جامايكي', 'Jamaican Dollar', 'Dollar Jamaïcain'),
(78, 'Sh', 'KES', 'KSh', 'KES', 'شلن كيني', 'Kenyan Shilling', 'Shilling Kenyan'),
(79, 'с', 'KGS', 'KGS', 'KGS', 'سوم قيرغيزستاني', 'Kyrgyzstani Som', 'Som Kirghize'),
(80, '៛', 'KHR', '៛', 'KHR', 'ريال كمبودي', 'Cambodian Riel', 'Riel Cambodgien'),
(81, 'Fr', 'KMF', 'CF', 'KMF', 'فرنك قمري', 'Comorian Franc', 'Franc Comorien'),
(82, '₩', 'KRW', '₩', 'KRW', 'وون كوري', 'South Korean Won', 'Won Sud-Coréen'),
(83, '₸', 'KZT', '₸', 'KZT', 'تنغي كازاخستاني', 'Kazakhstani Tenge', 'Tenge Kazakh'),
(84, '₭', 'LAK', '₭', 'LAK', 'كيب لاوسي', 'Lao Kip', 'Kip Laotien'),
(85, 'Rs', 'LKR', 'Rs', 'LKR', 'روبية سريلانكية', 'Sri Lankan Rupee', 'Roupie Sri-Lankaise'),
(86, '$', 'LRD', 'L$', 'LRD', 'دولار ليبيري', 'Liberian Dollar', 'Dollar Libérien'),
(87, 'L', 'LSL', 'L', 'LSL', 'لوتي ليسوتو', 'Lesotho Loti', 'Loti du Lesotho'),
(88, 'Ar', 'MGA', 'Ar', 'MGA', 'أرياري مدغشقري', 'Malagasy Ariary', 'Ariary Malgache'),
(89, 'ден', 'MKD', 'MKD', 'MKD', 'دينار مقدوني', 'Macedonian Denar', 'Denar Macédonien'),
(90, 'K', 'MMK', 'K', 'MMK', 'كيات ميانماري', 'Myanmar Kyat', 'Kyat Birman'),
(91, '₮', 'MNT', '₮', 'MNT', 'توغريك منغولي', 'Mongolian Tögrög', 'Tugrik Mongol'),
(92, 'P', 'MOP', 'MOP', 'MOP', 'باتاكا ماكاوية', 'Macanese Pataca', 'Pataca Macanais'),
(93, '₨', 'MUR', '₨', 'MUR', 'روبية موريشيوسية', 'Mauritian Rupee', 'Roupie Mauricienne'),
(94, 'Rf', 'MVR', 'Rf', 'MVR', 'روفيه مالديفية', 'Maldivian Rufiyaa', 'Rufiyaa Maldivien'),
(95, 'MK', 'MWK', 'MK', 'MWK', 'كواشا مالاوية', 'Malawian Kwacha', 'Kwacha Malawien'),
(96, '$', 'MXN', '$', 'MXN', 'بيزو مكسيكي', 'Mexican Peso', 'Peso Mexicain'),
(97, 'RM', 'MYR', 'RM', 'MYR', 'رينغيت ماليزي', 'Malaysian Ringgit', 'Ringgit Malaisien'),
(98, 'MT', 'MZN', 'MT', 'MZN', 'متكال موزمبيقي', 'Mozambican Metical', 'Metical Mozambicain'),
(99, '$', 'NAD', 'N$', 'NAD', 'دولار ناميبي', 'Namibian Dollar', 'Dollar Namibien'),
(100, '₦', 'NGN', '₦', 'NGN', 'نايرا نيجيري', 'Nigerian Naira', 'Naira Nigérian'),
(101, 'C$', 'NIO', 'C$', 'NIO', 'كوردوبا نيكاراغوي', 'Nicaraguan Córdoba', 'Cordoba Nicaraguayen'),
(102, 'kr', 'NOK', 'kr', 'NOK', 'كرونة نرويجية', 'Norwegian Krone', 'Couronne Norvégienne'),
(103, '₨', 'NPR', '₨', 'NPR', 'روبية نيبالية', 'Nepalese Rupee', 'Roupie Népalaise'),
(104, '$', 'NZD', 'NZ$', 'NZD', 'دولار نيوزيلندي', 'New Zealand Dollar', 'Dollar Néo-Zélandais'),
(105, 'B/.', 'PAB', 'B/.', 'PAB', 'بالبوا بنمي', 'Panamanian Balboa', 'Balboa Panaméen'),
(106, 'S/', 'PEN', 'S/', 'PEN', 'سول بيروفي', 'Peruvian Sol', 'Sol Péruvien'),
(107, 'K', 'PGK', 'K', 'PGK', 'كينا بابوا غينيا الجديدة', 'Papua New Guinean Kina', 'Kina Papou'),
(108, '₱', 'PHP', '₱', 'PHP', 'بيزو فلبيني', 'Philippine Peso', 'Peso Philippin'),
(109, '₨', 'PKR', '₨', 'PKR', 'روبية باكستانية', 'Pakistani Rupee', 'Roupie Pakistanaise'),
(110, 'zł', 'PLN', 'zł', 'PLN', 'زلوتي بولندي', 'Polish Złoty', 'Zloty Polonais'),
(111, '₲', 'PYG', '₲', 'PYG', 'غواراني باراغواي', 'Paraguayan Guaraní', 'Guarani Paraguayen'),
(112, 'lei', 'RON', 'lei', 'RON', 'ليو روماني', 'Romanian Leu', 'Leu Roumain'),
(113, 'дин', 'RSD', 'RSD', 'RSD', 'دينار صربي', 'Serbian Dinar', 'Dinar Serbe'),
(114, '₽', 'RUB', '₽', 'RUB', 'روبل روسي', 'Russian Ruble', 'Rouble Russe'),
(115, 'Fr', 'RWF', 'RF', 'RWF', 'فرنك رواندي', 'Rwandan Franc', 'Franc Rwandais'),
(116, '₨', 'SCR', '₨', 'SCR', 'روبية سيشلية', 'Seychellois Rupee', 'Roupie Seychelloise'),
(117, 'kr', 'SEK', 'kr', 'SEK', 'كرونة سويدية', 'Swedish Krona', 'Couronne Suédoise'),
(118, '$', 'SGD', 'S$', 'SGD', 'دولار سنغافوري', 'Singapore Dollar', 'Dollar Singapourien'),
(119, 'Le', 'SLL', 'Le', 'SLL', 'ليون سيراليوني', 'Sierra Leonean Leone', 'Leone Sierra-Léonais'),
(120, '฿', 'THB', '฿', 'THB', 'بات تايلندي', 'Thai Baht', 'Baht Thaïlandais'),
(121, 'ЅМ', 'TJS', 'TJS', 'TJS', 'سوموني طاجيكستاني', 'Tajikistani Somoni', 'Somoni Tadjik'),
(122, 'm', 'TMT', 'T', 'TMT', 'مانات تركمانستاني', 'Turkmenistani Manat', 'Manat Turkmène'),
(123, 'T$', 'TOP', 'T$', 'TOP', 'بانغا تونغي', 'Tongan Paʻanga', 'Pa\'anga Tongien'),
(124, '₺', 'TRY', '₺', 'TRY', 'ليرة تركية', 'Turkish Lira', 'Livre Turque'),
(125, '$', 'TTD', 'TT$', 'TTD', 'دولار ترينيداد وتوباغو', 'Trinidad and Tobago Dollar', 'Dollar de Trinité'),
(126, 'NT$', 'TWD', 'NT$', 'TWD', 'دولار تايواني', 'New Taiwan Dollar', 'Dollar Taïwanais'),
(127, 'Sh', 'TZS', 'TSh', 'TZS', 'شلن تنزاني', 'Tanzanian Shilling', 'Shilling Tanzanien'),
(128, '₴', 'UAH', '₴', 'UAH', 'هريفنيا أوكرانية', 'Ukrainian Hryvnia', 'Hryvnia Ukrainienne'),
(129, 'Sh', 'UGX', 'USh', 'UGX', 'شلن أوغندي', 'Ugandan Shilling', 'Shilling Ougandais'),
(130, '$', 'UYU', '$U', 'UYU', 'بيزو أوروغوياني', 'Uruguayan Peso', 'Peso Uruguayen'),
(131, 'so\'m', 'UZS', 'UZS', 'UZS', 'سوم أوزبكستاني', 'Uzbekistani Som', 'Sum Ouzbek'),
(132, 'Bs.', 'VES', 'Bs', 'VES', 'بوليفار فنزويلي', 'Venezuelan Bolívar', 'Bolivar Vénézuélien'),
(133, '₫', 'VND', '₫', 'VND', 'دونغ فيتنامي', 'Vietnamese Đồng', 'Dong Vietnamien'),
(134, 'Vt', 'VUV', 'Vt', 'VUV', 'فاتو فانواتي', 'Vanuatu Vatu', 'Vatu Vanuatuan'),
(135, 'Fr', 'XAF', 'FCFA', 'XAF', 'فرنك وسط أفريقي', 'Central African CFA Franc', 'Franc CFA'),
(136, '$', 'XCD', 'EC$', 'XCD', 'دولار شرق الكاريبي', 'East Caribbean Dollar', 'Dollar des Caraïbes'),
(137, 'Fr', 'XOF', 'CFA', 'XOF', 'فرنك غرب أفريقي', 'West African CFA Franc', 'CFA'),
(138, 'Fr', 'XPF', 'F', 'XPF', 'فرنك باسيفيكي', 'CFP Franc', 'Franc Pacifique'),
(139, 'R', 'ZAR', 'R', 'ZAR', 'راند جنوب أفريقي', 'South African Rand', 'Rand Sud-Africain'),
(140, 'ZK', 'ZMW', 'ZK', 'ZMW', 'كواشا زامبي', 'Zambian Kwacha', 'Kwacha Zambien'),
(141, '$', 'ZWL', 'Z$', 'ZWL', 'دولار زيمبابوي', 'Zimbabwean Dollar', 'Dollar Zimbabwéen');

-- --------------------------------------------------------

--
-- Table structure for table `t_02_01_02`
--

CREATE TABLE `t_02_01_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_02_01_02`
--

INSERT INTO `t_02_01_02` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'غير قابل للتطبيق﻿', 'Not Applicabale', 'Non applicable'),
(2, 'قيد التحضير', 'Under Preparation', 'En cours de préparation'),
(3, 'تأشيرة', 'Visa', 'Visa'),
(4, 'تأشيرة مع توصيات', 'Visa with Recommendations', 'Visa avec recommandations'),
(5, 'تأشيرة مع تحفظات', 'Visa under Reservations', 'Visa sous réserves'),
(6, 'تأجيل', 'Adjournment', 'Ajournement'),
(7, 'رفض', 'Rejection', 'Rejet'),
(8, 'رفض مؤقت', 'Temporary Rejection', 'Rejet à titre temporaire'),
(9, 'رفض نهائي', 'Definitive Rejection', 'Rejet définitif');

-- --------------------------------------------------------

--
-- Table structure for table `t_02_01_03`
--

CREATE TABLE `t_02_01_03` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(300) DEFAULT NULL,
  `F_02` varchar(300) DEFAULT NULL,
  `F_03` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_02_01_03`
--

INSERT INTO `t_02_01_03` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'المديرية الفرعية للإنجازات', 'Sub Directorat of Realizations', 'Sous Direction Réalisations'),
(2, 'دائرة المؤن', 'Approvisionment Department', 'Département des Approvisionnements');

-- --------------------------------------------------------

--
-- Table structure for table `t_02_01_04`
--

CREATE TABLE `t_02_01_04` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_02_01_04`
--

INSERT INTO `t_02_01_04` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'الوسائل الكبرى', 'Major Means', 'Moyens Majeurs'),
(2, 'تسيير', 'Operation', 'Fonctionnement'),
(3, 'تجهيز', 'Equipment', 'Équipement'),
(4, 'قطع غيار', 'Spare Parts', 'Pièces de Rechange'),
(5, 'أشغال', 'Constructions', 'Travaux'),
(6, 'خدمات', 'Services', 'Prestations'),
(7, 'دراسات', 'Studies', 'Études');

-- --------------------------------------------------------

--
-- Table structure for table `t_02_01_05`
--

CREATE TABLE `t_02_01_05` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_02_01_05`
--

INSERT INTO `t_02_01_05` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'قيد الانتظار', 'On Hold', 'En instance'),
(2, 'قيد التنفيذ', 'Ongoing', 'En cours'),
(3, 'غيرمجدي', 'Defective', 'Infrectieux'),
(4, 'مُنجز', 'Finalized', 'Finalisé'),
(5, 'ملغي', 'Canceled', 'Annulé'),
(6, 'مؤجل', 'Deferred', 'Différé'),
(7, 'منقول', 'Transferred', 'Transféré');

-- --------------------------------------------------------

--
-- Table structure for table `t_02_02_01`
--

CREATE TABLE `t_02_02_01` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(20) DEFAULT NULL,
  `F_05` varchar(20) DEFAULT NULL,
  `F_06` varchar(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_02_02_01`
--

INSERT INTO `t_02_02_01` (`F_00`, `F_04`, `F_05`, `F_06`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'تسيير', 'F', 'F', 'ميزانية التسيير', 'Functional Budget', 'Budget de Fonctionnement'),
(2, 'تجهيز', 'E', 'E', 'ميزانية التجهيز', 'Equipment Budget', 'Budget d\'Equipement');

-- --------------------------------------------------------

--
-- Table structure for table `t_02_02_02`
--

CREATE TABLE `t_02_02_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_02_02_02`
--

INSERT INTO `t_02_02_02` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'مخطط', 'Planed', 'Plannifié'),
(2, 'في طور الانجاز', 'Procurement Ongoing', 'En cours de Réalisation'),
(3, 'منجز', 'Procured', 'Réalisé');

-- --------------------------------------------------------

--
-- Table structure for table `t_02_02_03`
--

CREATE TABLE `t_02_02_03` (
  `F_00` bigint(20) NOT NULL,
  `F_02` varchar(4) NOT NULL,
  `F_01` varchar(200) NOT NULL,
  `F_03` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_02_04`
--

CREATE TABLE `t_02_02_04` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_02_05`
--

CREATE TABLE `t_02_02_05` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL,
  `F_04` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_02_06`
--

CREATE TABLE `t_02_02_06` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL,
  `F_04` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_02_07`
--

CREATE TABLE `t_02_02_07` (
  `F_00` bigint(20) NOT NULL,
  `F_03` datetime(6) DEFAULT NULL,
  `F_02` varchar(500) DEFAULT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_04` bigint(20) NOT NULL,
  `F_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_02_08`
--

CREATE TABLE `t_02_02_08` (
  `F_00` bigint(20) NOT NULL,
  `F_04` double DEFAULT NULL,
  `F_01` varchar(200) NOT NULL,
  `F_03` double DEFAULT NULL,
  `F_02` double DEFAULT NULL,
  `F_08` bigint(20) DEFAULT NULL,
  `F_07` bigint(20) NOT NULL,
  `F_06` bigint(20) NOT NULL,
  `F_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_02_09`
--

CREATE TABLE `t_02_02_09` (
  `F_00` bigint(20) NOT NULL,
  `F_01` float DEFAULT NULL,
  `F_02` bigint(20) NOT NULL,
  `F_03` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_03_01`
--

CREATE TABLE `t_02_03_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` int(11) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) DEFAULT NULL,
  `F_04` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_02_03_01`
--

INSERT INTO `t_02_03_01` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`) VALUES
(1, 101101, NULL, NULL, 'ENTREPRISE DE DEFENSE ET DE RESTAURATION DES SOLS'),
(2, 101102, NULL, NULL, 'ENTREPRISE D\'AMENAGEMENT DE PERIMETRES IRRIGUES ET DE DRAINAGE AGRICOLE'),
(3, 101103, NULL, NULL, 'ENTREPRISE DE TRAVAUX FORESTIERS ET D\'EXPLOITATION DES FORETS'),
(4, 101104, NULL, NULL, 'ENTREPRISE DE STOCKAGE ET DE TRAITEMENT DES CEREALES'),
(5, 101105, NULL, NULL, 'ENTREPRISE DE CONDITIONNEMENT DE PRODUITS AGRICOLES'),
(6, 101106, NULL, NULL, 'ENTREPRISE DE PRODUCTION ET DE DISTRIBUTION DES ESPECES VEGETALES NON CULTIVEES'),
(7, 101107, NULL, NULL, 'ENTREPRISE DE TRAVAUX AGRICOLES ET TRAITEMENT PHYTOSANITAIRE'),
(8, 101108, NULL, NULL, 'PEPINIERE'),
(9, 101109, NULL, NULL, 'PRODUCTION DES SEMENCES'),
(10, 101110, NULL, NULL, 'ENTREPRISE DE PRODUCTION ET DE CONSERVATION DE CHAMPIGNONS CULTIVES'),
(11, 101201, NULL, NULL, 'ENGRAISSEMENT INDUSTRIEL DE BOVINS ET OVINS'),
(12, 101202, NULL, NULL, 'ENGRAISSEMENT DE VOLAILLES ET ACCOUVAGES INDUSTRIELS'),
(13, 101203, NULL, NULL, 'ENTREPRISE DE LA PECHE INDUSTRIELLE'),
(14, 101204, NULL, NULL, 'ENTREPRISE DE TRANSFORMATION DE CORAIL'),
(15, 102101, NULL, NULL, 'FORAGES PETROLIERS'),
(16, 102102, NULL, NULL, 'GEOPHYSIQUE'),
(17, 102103, NULL, NULL, 'ANALYSES LIEES AUX HYDROCARBURES.'),
(18, 102104, NULL, NULL, 'ETUDES ET ENGINEERING LIES AUX HYDROCARBURES'),
(19, 102105, NULL, NULL, 'ENTREPRISE D\'INGENIERIE INDUSTRIELLE'),
(20, 102201, NULL, NULL, 'CONSTRUCTION D\'OUVRAGES DESTINES A LA PRODUCTION, LA TRANSFORMATION, TRANSPORT ET LA DISTRIBUTION DES HYDROCARBURES'),
(21, 102202, NULL, NULL, 'EXTRACTION D\'HYDROCARBURES LIQUIDES ET GAZEUX'),
(22, 102203, NULL, NULL, 'PRODUCTION, DISTRIBUTION ET TRANSPORT D\'ELECTRICITE'),
(23, 102204, NULL, NULL, 'TRANSPORT DE GAZ NATUREL'),
(24, 102205, NULL, NULL, 'TRANSPORT D\'HYDROCARBURES LIQUIDES ET CONDENSAT PAR CANALISATION'),
(25, 102206, NULL, NULL, 'DISTRIBUTION PUBLIQUE DE GAZ'),
(26, 102207, NULL, NULL, 'ENTREPRISE DE MAINTENANCE DES CANALISATIONS D\'HYDROCARBURES'),
(27, 102301, NULL, NULL, 'RAFFINAGE DU PETROLE'),
(28, 102302, NULL, NULL, 'LIQUEFACTION DE GAZ NATUREL'),
(29, 102303, NULL, NULL, 'COKERIE'),
(30, 102304, NULL, NULL, 'FABRICATION D\'AGGLOMERES DE COMBUSTIBLES'),
(31, 103101, NULL, NULL, 'EXTRACTION ET PREPARATION DE MINERAI DE FER'),
(32, 103102, NULL, NULL, 'EXTRACTION ET PREPARATION DE BAUXITE'),
(33, 103103, NULL, NULL, 'EXTRACTION ET PREPARATION DE MINERAIS DE METAUX COMMUNS NON FERREUX'),
(34, 103104, NULL, NULL, 'EXTRACTION ET PREPARATION DE MINERAIS POUR FERRO-ALLIAGES'),
(35, 103105, NULL, NULL, 'EXTRACTION ET PREPARATION DE MINERAIS DE METAUX PRECIEUX'),
(36, 103106, NULL, NULL, 'EXTRACTION ET PREPARATION DE MINERAIS METALLIQUE DIVERS'),
(37, 103107, NULL, NULL, 'EXTRACTION ET PREPARATION DE MINERAIS D\'URANIUM ET DE MINERAIS RADIOACTIFS'),
(38, 103108, NULL, NULL, 'EXTRACTION ET PREPARATION DE PYRITE, PRODUCTION DE SOUFRE'),
(39, 103109, NULL, NULL, 'EXTRACTION ET PREPARATION DE PRODUITS MINERAUX DIVERS'),
(40, 103110, NULL, NULL, 'BROYAGE A FACON DE MINERAIS ET MATERIAUX (NON ASSOCIE A L\'EXTRACTION)'),
(41, 103111, NULL, NULL, 'TRAVAUX LIES A L\'EXPLOITATION DES MINES'),
(42, 103201, NULL, NULL, 'EXTRACTION DE HOUILLE'),
(43, 103202, NULL, NULL, 'EXPLOITATION DE CARRIERES DE PIERRES DE TAILLE POUR LA CONSTRUCTION ET L\'INDUSTRIE'),
(44, 103203, NULL, NULL, 'EXTRACTION ET PREPARATION DE SABLES, EXTRACTION DE MATERIAUX ALLUVIONNAIRES'),
(45, 103204, NULL, NULL, 'EXTRACTION ET PREPARATION DE GYPSE'),
(46, 103205, NULL, NULL, 'EXTRACTION ET PREPARATION DE LA PIERRE A CHAUX'),
(47, 103206, NULL, NULL, 'EXTRACTION ET PREPARATION DE L\'ARGILE'),
(48, 103207, NULL, NULL, 'EXTRACTION ET PREPARATION DE PRODUITS DE CARRIERE DIVERS NON DESTINES AUX MATERIAUX DE CONSTRUCTION'),
(49, 103208, NULL, NULL, 'EXTRACTION ET PREPARATION DE SEL DE POTASSE'),
(50, 103209, NULL, NULL, 'EXTRACTION ET PREPARATION DE SELS Y COMPRIS MARAIS SALANTS'),
(51, 103210, NULL, NULL, 'EXTRACTION ET PREPARATION DE PHOSPHATES'),
(52, 103211, NULL, NULL, 'EXTRACTION ET PREPARATION DE BITUME ET D\'ASPHALTE NATURELS'),
(53, 104101, NULL, NULL, 'INDUSTRIE DE L\'ACIDE SULFURIQUE ET PRODUITS DERIVES'),
(54, 104102, NULL, NULL, 'FABRICATION DE PRODUITS CHIMIQUES PAR ELECTROLYSE OU ELECTROTHERMIE'),
(55, 104103, NULL, NULL, 'FABRICATION DE GAZ COMPRIME OU LIQUEFIE (NON COMPRIS G.P.L+G.N.L)'),
(56, 104104, NULL, NULL, 'FABRICATION D\'OPACIFIANTS MINERAUX, DE COMPOSITIONS ET DE COULEURS POUR EMAUX'),
(57, 104105, NULL, NULL, 'FABRICATION DE PRODUITS DIVERS DE LA CHIMIE MINERALE'),
(58, 104106, NULL, NULL, 'FABRICATION D\'ENGRAIS AZOTES ETAUTRES PRODUITS AZOTES'),
(59, 104107, NULL, NULL, 'FABRICATION D\'ENGRAIS PHOSPHATES'),
(60, 104108, NULL, NULL, 'FABRICATION D\'AUTRES ENGRAIS'),
(61, 104109, NULL, NULL, 'FABRICATION DE PRODUITS CHIMIQUES DE BASE POUR LA PHARMACIE'),
(62, 104110, NULL, NULL, 'FABRICATION DE PRODUITS CHIMIQUES A USAGE MECANIQUE ET METALLURGIQUE ET POUR L\'INDUSTRIE'),
(63, 104111, NULL, NULL, 'PREMIERE TRANSFORMATION DE LA MATIERE PLASTIQUE DE BASE'),
(64, 104112, NULL, NULL, 'FABRICATION DE REACTIFS DE LABORATOIRE'),
(65, 104201, NULL, NULL, 'FABRICATION D\'HYDROCARBURES ACYCLIQUES, CYCLIQUES OU AROMATIQUES,DERIVES D\'HYDROCARBURES ET AUTRES COMPOSES ORGANIQUES N.D.A.'),
(66, 104202, NULL, NULL, 'FABRICATION DE MATIERES COLORANTES DE SYNTHESE'),
(67, 104203, NULL, NULL, 'FABRICATION DE MATIERE PLASTIQUE DE BASE ET DE RESINES SYNTHETIQUES'),
(68, 104204, NULL, NULL, 'EXTRACTION ET TRANSFORMATION DE MATIERES ANIMALES ET VEGETALES'),
(69, 104205, NULL, NULL, 'INDUSTRIE DES GOUDRONS ET BENZOLS'),
(70, 104206, NULL, NULL, 'FABRICATION D\'EXPLOSIFS, D\'ACCESSOIRES DE MISE A FEU ET D\'ARTIFICES'),
(71, 104207, NULL, NULL, 'FABRICATION DE PRODUITS PHARMACEUTIQUES'),
(72, 104208, NULL, NULL, 'FABRICATION D\'ABRASIFS'),
(73, 104209, NULL, NULL, 'FABRICATION DE CHARBON ARTIFICIELET DE TERRES ACTIVEES'),
(74, 104210, NULL, NULL, 'FABRICATION DE PEINTURES, VERNIS,PIGMENTS ET ENCRES D\'IMPRIMERIE'),
(75, 104211, NULL, NULL, 'FABRICATION DE PRODUITS INSECTICIDES ANTICRYPTOGAMIQUES ET DESINFECTANTS'),
(76, 104212, NULL, NULL, 'FABRICATION DE PRODUITS PHOTOGRAPHIQUES'),
(77, 104213, NULL, NULL, 'FABRICATION D\'HUILES ESSENTIELLES DE PARFUMERIE ET COSMETIQUES'),
(78, 104214, NULL, NULL, 'FABRICATION DE POUDRETTE DE CAOUTCHOUC ET DE CAOUTCHOUC REGENERE'),
(79, 104215, NULL, NULL, 'FABRICATION DE PNEUMATIQUES ET BANDAGES'),
(80, 104216, NULL, NULL, 'FABRICATION D\'ARTICLES EN CAOUTCHOUC N.D.A. ( NC CHAUSSURES)'),
(81, 104217, NULL, NULL, 'FABRICATION D\'ALLUMETTES'),
(82, 104218, NULL, NULL, 'FABRICATION DE SAVON ET SAVONNETTES (SAVONNERIE)'),
(83, 104219, NULL, NULL, 'FABRICATION DE BOUGIES, CHANDELLES, CIERGES ET DERIVES DE LA STEARINERIE'),
(84, 104220, NULL, NULL, 'FABRICATION DE PATE A PAPIER ET DE PAPIER ET CARTON'),
(85, 104221, NULL, NULL, 'FABRICATION DE PRODUITS DE BLANCHIMENT ET DE PRODUITS D\'ENTRETIEN ET CONNEXES'),
(86, 104222, NULL, NULL, 'FABRICATION DE PRODUITS CHIMIQUES POUR LE TRAITEMENT DES EAUX, DES CUIRS ET TEXTILES ET AUTRES MATERIAUX'),
(87, 105101, NULL, NULL, 'FABRICATION DE MATERIEL INDUSTRIEL DE COMBUSTION, MATERIEL THERMIQUEET AERAULIQUE'),
(88, 105102, NULL, NULL, 'FABRICATION DE MATERIEL FRIGORIFIQUE (ELECTRIQUE OU NON) NON DOMESTIQUE'),
(89, 105103, NULL, NULL, 'FABRICATION DE MOTEURS STATIONNAIRES (N.C MOTEURS D\'AUTOS, D\'AVIONS ET GROS MOTEURS MARINS)'),
(90, 105104, NULL, NULL, 'FABRICATION DE MACHINES ET TURBINES A VAPEUR'),
(91, 105105, NULL, NULL, 'FABRICATION DE POMPES ET DE MATERIEL HYDRAULIQUE'),
(92, 105106, NULL, NULL, 'FABRICATION DE MATERIEL D\'INCENDIE'),
(93, 105107, NULL, NULL, 'FABRICATION D\'APPAREILS DE LEVAGE ET DE MANUTENTION, DE MATERIEL POUR INDUSTRIES LOURDES ET POUR CHEMINS DE FER'),
(94, 105108, NULL, NULL, 'FABRICATION DE MATERIEL ROULANT POUR CHEMINS DE FER'),
(95, 105201, NULL, NULL, 'FABRICATION DE MACHINES AGRICOLES'),
(96, 105202, NULL, NULL, 'FABRICATION DE MACHINES OUTILS, D\'OUTILLAGES MECANIQUES, D\'ORGANES MECANIQUES (YC MATERIEL DE SOUDAGE)'),
(97, 105203, NULL, NULL, 'FABRICATION DE MATERIEL POUR LES INDUSTRIES ALIMENTAIRES, DU TABAC ET ALLUMETTES ( INDUSTRIES DU CUIR, DU CAOUTCHOUC ET MATIERES PLASTIQUES, MACHINES DE CONDITIONNEMENT& CONGELATIO'),
(98, 105204, NULL, NULL, 'FABRICATION DE MACHINES A COUDRE ET MACHINES POUR CHAUSSURES'),
(99, 105205, NULL, NULL, 'FABRICATION DE MACHINES POUR L\'INDUSTRIE TEXTILE'),
(100, 105206, NULL, NULL, 'FABRICATION DE MACHINES POUR L\'INDUSTRIE DU PAPIER ET DU CARTON'),
(101, 105207, NULL, NULL, 'MECANIQUE DE PRECISION'),
(102, 105208, NULL, NULL, 'MODELAGE MECANIQUE'),
(103, 105209, NULL, NULL, 'FABRICATION DE ROULEMENTS'),
(104, 105210, NULL, NULL, 'FABRICATION DE RESSORTS'),
(105, 105211, NULL, NULL, 'FABRICATION DE CHAINES ET CHAINETTES'),
(106, 105212, NULL, NULL, 'FABRICATION D\'OUTILLAGE A MAIN METALLIQUE'),
(107, 105213, NULL, NULL, 'FABRICATION DE MATERIELS ET EQUIPEMENTS AVICOLES'),
(108, 105301, NULL, NULL, 'CONSTRUCTION DE NAVIRES ET ENGINS FLOTTANTS EN ACIER OU EN BOIS'),
(109, 105302, NULL, NULL, 'REPARATION NAVALE, RENFLOUEMENT ET DEMOLITION DE NAVIRES OU EPAVES'),
(110, 105303, NULL, NULL, 'TRAVAUX A FACON POUR LES NAVIRES'),
(111, 105304, NULL, NULL, 'ENTREPRISE DE TRAVAUX SOUS-MARINS, MARINS ET FLUVIAUX'),
(112, 105401, NULL, NULL, 'CONSTRUCTION DE VEHICULES AUTOMOBILES ET DE MOTEURS THERMIQUES'),
(113, 105402, NULL, NULL, 'CONSTRUCTION DE CARROSSERIE AUTOMOBILE DE REMORQUES ET DE BENNES'),
(114, 105403, NULL, NULL, 'FABRICATION D\'EQUIPEMENT, D\'ACCESSOIRES ET PIECES DETACHEES POUR AUTOS'),
(115, 105404, NULL, NULL, 'RECHAPAGE NON INDUSTRIEL DE PNEUS'),
(116, 105405, NULL, NULL, 'FABRICATION DE CYCLES ET MOTOCYCLES'),
(117, 105406, NULL, NULL, 'FABRICATION DE PIECES DETACHEES ET ACCESSOIRES POUR CYCLES ET MOTOCYCLES'),
(118, 105407, NULL, NULL, 'CONSTRUCTION DE CELLULES D\'AERODYNES'),
(119, 105408, NULL, NULL, 'CONSTRUCTION DE MOTEURS D\'AVION'),
(120, 105409, NULL, NULL, 'CONSTRUCTION ET INSTALLATION D\'EQUIPEMENTS, D\'ARMEMENTS, D\'APPAREILS DE BORD DES AVIONS'),
(121, 105410, NULL, NULL, 'CONSTRUCTION ET INSTALLATION DE MATERIEL ELECTRIQUE ET SPECIAUX DE BORD DES AVIONS'),
(122, 105411, NULL, NULL, 'CONSTRUCTION ET INSTALLATION DE MATERIEL DE TERRAIN ET D\'ENTRAINEMENT DE PILOTAGE AU SOL'),
(123, 105412, NULL, NULL, 'CONSTRUCTION ET INSTALLATION DE MATERIELS DIVERS D\'ESSAIS AERONAUTIQUES'),
(124, 105413, NULL, NULL, 'CONSTRUCTION D\'ENGINS SPECIAUX'),
(125, 105414, NULL, NULL, 'FABRICATION D\'ARMES DE GUERRE'),
(126, 105415, NULL, NULL, 'FABRICATION D\'ARMES DE CHASSE ET DE DEFENSE'),
(127, 105416, NULL, NULL, 'ENTREPRISE INDUSTRIELLE DE RECHAPAGE DE PNEUS'),
(128, 105501, NULL, NULL, 'FABRICATION DE CABLES ELECTRIQUES ET TELEPHONIQUES'),
(129, 105502, NULL, NULL, 'FABRICATION DE MACHINES ET MATERIELS ELECTRIQUES ET D\'APPAREILLAGES DE DISTRIBUTION'),
(130, 105503, NULL, NULL, 'FABRICATION D\'APPAREILS RADIOELECTRIQUES ET ELECTRONIQUES'),
(131, 105504, NULL, NULL, 'FABRICATION DE PETIT APPAREILLAGE D\'INSTALLATION ET D\'ECLAIRAGE ELECTRIQUE, MATERIEL TELEPHONIQUE ET TELEGRAPHIQUE'),
(132, 105505, NULL, NULL, 'FABRICATION DE COMPTEURS ELECTRIQUES, TRANSFORMATEURS DE MESURE, D\' APPAREILS ELECTRIQUES ET ELECTRONIQUES DE MESURE ET DE CONTRÔLE'),
(133, 105506, NULL, NULL, 'FABRICATION DE PILES ET ACCUMULATEURS'),
(134, 105507, NULL, NULL, 'FABRICATION DE LAMPES ELECTRIQUES'),
(135, 105508, NULL, NULL, 'FABRICATION D\'APPAREILS ELECTRIQUES ELECTROMENAGERS DIVERS'),
(136, 105509, NULL, NULL, 'FABRICATION DE MATERIELS ,EQUIPEMENTS LIES AUX DOMAINES DE L\'ENERGIE SOLAIRE ET ELECTRIQUE'),
(137, 105510, NULL, NULL, 'REALISATION ET CONCEPTION DE COMPOSANTS ELECTRONIQUES DESTINES A LA MAINTENANCE INDUSTRIELLE'),
(138, 105511, NULL, NULL, 'FABRICATION DE PANNEAUX DE SIGNALISATION ROUTIERE ET AUTO-ROUTIERE'),
(139, 105512, NULL, NULL, 'FABRICATION D\'EQUIPEMENTS, DE MATERIELS ET FOURNITURES DE SECURITE ET DE PROTECTION'),
(140, 105601, NULL, NULL, 'FABRICATION D\'HORLOGERIE'),
(141, 105602, NULL, NULL, 'FABRICATION DE COMPTEURS, D\'APPAREILS DE PESAGE ET DE CONTROLE ET D\'INSTRUMENTS DE METROLOGIE'),
(142, 105603, NULL, NULL, 'FABRICATION D\'INSTRUMENTS DE PRECISION ET D\'OPTIQUE'),
(143, 105604, NULL, NULL, 'FABRICATION DE LUNETTERIE'),
(144, 105605, NULL, NULL, 'FABRICATION DE MATERIEL PHOTOGRAPHIQUE ET CINEMATOGRAPHIQUE'),
(145, 105701, NULL, NULL, 'FABRICATION DE MACHINES DE BUREAU ET DE TRAITEMENT DE L\'INFORMATION'),
(146, 105702, NULL, NULL, 'FABRICATION DES PRODUITS PARA-PHARMACEUTIQUES , MATERIEL S, APPAREILS ET INSTRUMENTS MEDICAUX-CHIRURGICAUX'),
(147, 106101, NULL, NULL, 'FABRICATION D\' ACIERS COURANTS'),
(148, 106102, NULL, NULL, 'LAMINAGE A CHAUD DES ACIERS COURANTS'),
(149, 106103, NULL, NULL, 'FABRICATION DES ACIERS FINS ET SPECIAUX (A.F.S)'),
(150, 106104, NULL, NULL, 'LAMINAGE ET FABRICATION DE PRODUITS A.F.S.'),
(151, 106105, NULL, NULL, 'TRANSFORMATION DES ACIERS FINS ET SPECIAUX (AFS)'),
(152, 106106, NULL, NULL, 'LAMINAGE ET PROFILAGE A FROID DE L\'ACIER COURANT, TREFILAGE, ETIRAGE ET AUTRES TRANSFORMATIONS DE L\'ACIER COURANT'),
(153, 106107, NULL, NULL, 'FABRICATION DE TUBES D\'ACIER'),
(154, 106108, NULL, NULL, 'METALLURGIE DE L\'ALUMINIUM ET D\'AUTRES METAUX LEGERS (Y COMPRIS FABRICATION D\'ALLIAGES LEGERS, DE DURALUMIN ET D\'ALPAX)'),
(155, 106109, NULL, NULL, 'PREMIERE TRANSFORMATION DES METAUX NON FERREUX ET FABRICATION DE DEMI-PRODUITS EN METAUX NON FERREUX'),
(156, 106110, NULL, NULL, 'PRODUCTION DE FERRO ALLIAGES'),
(157, 106111, NULL, NULL, 'METALLURGIE DES METAUX ENTRANT DANS LES FERRO-ALLIAGES ET METAUX CONNEXES'),
(158, 106112, NULL, NULL, 'METALLURGIE ET AFFINAGE DES METAUX COMMUNS ET LEURS ALLIAGES'),
(159, 106113, NULL, NULL, 'AFFINAGE DES METAUX PRECIEUX'),
(160, 106114, NULL, NULL, 'LAMINAGE SUR BOIS'),
(161, 106201, NULL, NULL, 'FABRICATION DE FONTE'),
(162, 106202, NULL, NULL, 'GROSSE FORGE ET GROS EMBOUTISSAGE'),
(163, 106203, NULL, NULL, 'FONDERIE'),
(164, 106204, NULL, NULL, 'CHAUDRONNERIE'),
(165, 106205, NULL, NULL, 'ROBINETTERIE'),
(166, 106206, NULL, NULL, 'CONSTRUCTION METALLIQUE (FABRICATION D\'ELEMENTS ET POSE ASSOCIEES OU FABRICATION SEULE)'),
(167, 106207, NULL, NULL, 'PRODUCTION ET TRAVAUX D\'USINAGE (SUR PLAN)'),
(168, 106208, NULL, NULL, 'REVETEMENT ET TRAITEMENT DES METAUX'),
(169, 106209, NULL, NULL, 'DECOUPAGE ET EMBOUTISSAGE DE METAL'),
(170, 106210, NULL, NULL, 'DECOLLETAGE, TOURNAGE, BOULONNERIE, VISSERIE'),
(171, 106211, NULL, NULL, 'ESTAMPAGE, FORGE, MATRICAGE'),
(172, 106212, NULL, NULL, 'FERRONNERIE ET MENUISERIE METALLIQUES'),
(173, 106213, NULL, NULL, 'FABRICATION D\'EMBALLAGES ET CONDITIONNEMENT METALLIQUES'),
(174, 106214, NULL, NULL, 'FABRICATION DE TUYAUX METALLIQUES FLEXIBLES'),
(175, 106215, NULL, NULL, 'FABRICATION DE PETITS ARTICLES METALLIQUES'),
(176, 106216, NULL, NULL, 'FABRICATION D\'AIMANTS PERMANENTS.'),
(177, 106217, NULL, NULL, 'FABRICATION D\'ELECTRODES POUR ELECTROMETALLURGIE OU ELECTROCHIMIE EN GRAHITE OU EN CARBONE AMORPHE (N.C LES ELECTRODES DE SOUDURE)'),
(178, 106218, NULL, NULL, 'MENUISERIE ALUMINIUM'),
(179, 106219, NULL, NULL, 'FABRICATION DE STORES VENITIENS ET CALIFORNIENS'),
(180, 106220, NULL, NULL, 'ENTREPRISE DE MENUISERIE DE BOIS'),
(181, 106221, NULL, NULL, 'TRAITEMENT DE SURFACES'),
(182, 107101, NULL, NULL, 'MEUNERIE'),
(183, 107102, NULL, NULL, 'FABRICATION DE PATES ALIMENTAIRES ET COUSCOUS'),
(184, 107103, NULL, NULL, 'DECORTICAGE ET TRIAGE DE GRAINS ET GRAINES'),
(185, 107104, NULL, NULL, 'PRODUITS AMYLACES'),
(186, 107105, NULL, NULL, 'BOULANGERIE INDUSTRIELLE'),
(187, 107106, NULL, NULL, 'BISCUITERIE, PATISSERIE ET PRODUITS DE REGIME'),
(188, 107107, NULL, NULL, 'FABRICATION D\'ALIMENTS INFANTILES'),
(189, 107201, NULL, NULL, 'PRODUCTION DE LAITS ET PRODUITS LAITIERS (LAITERIE)'),
(190, 107202, NULL, NULL, 'BEURRERIE'),
(191, 107203, NULL, NULL, 'FROMAGERIE'),
(192, 107204, NULL, NULL, 'FABRICATION DE PRODUITS DE LA CONFISERIE'),
(193, 107205, NULL, NULL, 'INDUSTRIE DE LA CHOCOLATERIE'),
(194, 107206, NULL, NULL, 'FABRICATION DE GLACES INDUSTRIELLES'),
(195, 107207, NULL, NULL, 'FABRICATION D\'ESSENCES ET AROMES ALIMENTAIRES'),
(196, 107208, NULL, NULL, 'PRODUCTION INDUSTRIELLE DE CREMES GLACEES ET AUTRES PRODUITS GLACES'),
(197, 107301, NULL, NULL, 'HUILERIE ET RAFFINAGE D\'HUILES D\'ORIGINE VEGETALE'),
(198, 107302, NULL, NULL, 'FABRICATION DE CORPS GRAS D\'ORIGINE ANIMALE'),
(199, 107303, NULL, NULL, 'FABRICATION DE MARGARINES'),
(200, 107401, NULL, NULL, 'SUCRERIE ET RAFFINERIE'),
(201, 107402, NULL, NULL, 'PREPARATION DE CAFE, THE, CHICOREE (BRULERIE DE CAFE)'),
(202, 107403, NULL, NULL, 'INDUSTRIE DE LA LEVURE (LEVURERIE)'),
(203, 107404, NULL, NULL, 'TRAITEMENT ET TRANSFORMATION DE LA DATTE'),
(204, 107501, NULL, NULL, 'VINIFICATION, CONSERVATION, MANUTENTION DES VINS'),
(205, 107502, NULL, NULL, 'PREPARATION DE VINS SPECIAUX'),
(206, 107503, NULL, NULL, 'BRASSERIE, MALTERIE'),
(207, 107504, NULL, NULL, 'CIDRERIE'),
(208, 107505, NULL, NULL, 'PRODUCTION D\'EAU MINERALE ET BOISSONS DIVERSES NON ALCOOLISEES'),
(209, 107506, NULL, NULL, 'DISTILLERIE D\'ALCOOLS A USAGE ALIMENTAIRE'),
(210, 107507, NULL, NULL, 'DISTILLERIE DE LIQUEURS ET FABRICATION D\'APERITIFS'),
(211, 107508, NULL, NULL, 'DISTILLERIE DE BETTERAVES,TOPINAMBOURS, GRAINS, POMMES DE TERRE, ETC'),
(212, 107509, NULL, NULL, 'TRAITEMENT DU TABAC EN FEUILLES'),
(213, 107510, NULL, NULL, 'MANUFACTURE DE TABAC ET ALLUMETTES'),
(214, 107601, NULL, NULL, 'CONSERVERIE DE FRUITS ET LEGUMES'),
(215, 107602, NULL, NULL, 'FABRICATION DE CONSERVES DE VIANDE, VOLAILLE ET FOIE GRAS'),
(216, 107603, NULL, NULL, 'PREPARATION DES PLATS CUISINES'),
(217, 107604, NULL, NULL, 'CONSERVERIE DE POISSONS ET DE CRUSTACES'),
(218, 107605, NULL, NULL, 'FABRICATION DE CONDIMENTS DIVERS'),
(219, 107606, NULL, NULL, 'FABRICATION DE BOUILLONS , POTAGE ET AROMES'),
(220, 107607, NULL, NULL, 'ABATTAGE ET DECOUPAGE INDUSTRIEL DE VIANDES DE BOUCHERIE (ABATTOIR INDUSTRUEL)'),
(221, 107608, NULL, NULL, 'FABRICATION DE PRODUITS POUR L\'ALIMENTATION DES ANIMAUX (N.C FARINES DE POISSONS)'),
(222, 107609, NULL, NULL, 'TRANSFORMATION ET CONSERVATION DE FRUITS ET LEGUMES'),
(223, 107610, NULL, NULL, 'PRODUCTION DE VIANDES DE BOUCHERIE ET DE VOLAILLES ET DE LAPINS FRAICHES CONGELEES OU SURGELEES'),
(224, 108101, NULL, NULL, 'PREPARATION DE LA LAINE (DE TONTE)'),
(225, 108102, NULL, NULL, 'FILATURE INDUSTRIELLE DE LA LAINE'),
(226, 108103, NULL, NULL, 'TISSAGE INDUSTRIEL DE LA LAINE ET AUTRES MATIERES TEXTILES'),
(227, 108104, NULL, NULL, 'FABRICATION INDUSTRIELLE DE TAPIS'),
(228, 108105, NULL, NULL, 'FABRICATION INDUSTRIELLE DE FEUTRES ET DE TISSUS NON TISSES ET OUATERIE'),
(229, 108106, NULL, NULL, 'TRAVAIL DU COTON'),
(230, 108107, NULL, NULL, 'FILATURE INDUSTRIELLE DU COTON'),
(231, 108108, NULL, NULL, 'TISSAGE INDUSTRIEL DU COTON ASSOCIE A D\'AUTRES MATIERES TEXTILES'),
(232, 108109, NULL, NULL, 'FABRICATION INDUSTRIELLE DE FILS A COUDRE'),
(233, 108110, NULL, NULL, 'INDUSTRIES DE FIBRES VEGETALES A L\'EXCLUSION DU COTON'),
(234, 108111, NULL, NULL, 'PREPARATION DE LA SOIE NATURELLE ET FILATURE DES TEXTILES ARTIFICIELS ET SYNTHETIQUES'),
(235, 108112, NULL, NULL, 'FABRICATION DE FIBRES SYNTHETIQUES (NON CELLULOSIQUES)'),
(236, 108113, NULL, NULL, 'TISSAGE INDUSTRIEL DE LA SOIE ASSOCIEE A D\'AUTRES MATIERES TEXTILES'),
(237, 108114, NULL, NULL, 'BONNETERIE INDUSTRIELLE'),
(238, 108115, NULL, NULL, 'FABRICATION DE PRODUITS TEXTILES ELASTIQUES POUR TOUS USAGES'),
(239, 108116, NULL, NULL, 'FABRICATION INDUSTRIELLE DE DENTELLES, TULLES, GUIPURES,BOBINOTS, BRODERIES'),
(240, 108117, NULL, NULL, 'FABRICATION DE RUBANS, TRESSES, PASSEMENTERIE'),
(241, 108118, NULL, NULL, 'FABRICATION INDUSTRIELLE DE FILETS NOUES ET FILETS DE PECHE'),
(242, 108119, NULL, NULL, 'FINISSAGE DE TEXTILES (BLANCHIMENT, TEINTURE, IMPRESSION ET APPRETS)'),
(243, 108120, NULL, NULL, 'FABRICATION INDUSTRIELLE DE LINOLEUM, TOILES CIREES ET PRODUITS SIMILAIRES'),
(244, 108121, NULL, NULL, 'CONFECTION INDUSTRIELLE DE VETEMENTS ET LINGERIE'),
(245, 108122, NULL, NULL, 'INDUSTRIES DIVERSES DE L\'HABILLEMENT'),
(246, 108123, NULL, NULL, 'FABRICATION DE TOILES INDUSTRIELLES ET D\'OUVRAGES EN TISSUS'),
(247, 108124, NULL, NULL, 'TRICOTAGE INDUSTRIEL'),
(248, 108125, NULL, NULL, 'CONFECTION , FABRICATION DE L\'EMBLEME NATIONAL ET DE L\'ECUSSON PORTEUR DE L\'EMBLEME NATIONAL ET DU FANION'),
(249, 108201, NULL, NULL, 'TANNERIE INDUSTRIELLE DE CUIR DE BOVINS A DESSUS (CUIR AU CHROME)'),
(250, 108202, NULL, NULL, 'TANNERIE INDUSTRIELLE DE CUIR A DESSUS OU A SEMELLE (DE BOVIN,CAMELIDE, EQUIDE)'),
(251, 108203, NULL, NULL, 'TANNERIE ET MEGISSERIE INDUSTRIELLE D\'OVINS ET CAPRINS'),
(252, 108204, NULL, NULL, 'PELLETERIE'),
(253, 108205, NULL, NULL, 'FABRICATION DE SUCCEDANES DE CUIR (CUIR SYNTHETIQUE OU ARTIFICIEL)'),
(254, 108206, NULL, NULL, 'FABRICATION INDUSTRIELLE D\'AGGLOMERES DE CUIR ET DE SYNDERME'),
(255, 108207, NULL, NULL, 'FABRICATION DE GANTS DE PEAUX'),
(256, 108208, NULL, NULL, 'FABRICATION INDUSTRIELLE DE MAROQUINERIE, D\'ARTICLES DE VOYAGEET D\'AUTRES ARTICLES SIMILAIRES DESTINES A TOUS USAGES'),
(257, 108209, NULL, NULL, 'BOURRELLERIE'),
(258, 108210, NULL, NULL, 'FABRICATION DE COURROIES EN CUIR A USAGE INDUSTRIEL'),
(259, 108211, NULL, NULL, 'FABRICATION INDUSTRIELLE DE CHAUSSURES'),
(260, 108212, NULL, NULL, 'FABRICATION INDUSTRIELLE DE VETEMENTS EN CUIR'),
(261, 108301, NULL, NULL, 'PRODUCTION INDUSTRIELLE DE BIJOUX'),
(262, 109101, NULL, NULL, 'FABRICATION INDUSTRIELLE DE CIMENTS (CIMENTERIE)'),
(263, 109102, NULL, NULL, 'FABRICATION INDUSTRIELLE DE PLATRE ET DERIVES (PLATERERIE)'),
(264, 109103, NULL, NULL, 'FABRICATION INDUSTRIELLE DE PRODUITS EN BETON AUTRES QU\' AGGLOMERES'),
(265, 109104, NULL, NULL, 'FABRICATION INDUSTRIELLE DE PRODUITS MANUFACTURES EN BETON OU EN PLATRE (DITS AGGLOMERES)'),
(266, 109105, NULL, NULL, 'FABRICATION INDUSTRIELLE DE CARREAUX ET DALLES EN CIMENT ET GRANITO'),
(267, 109106, NULL, NULL, 'PREFABRICATION D\'ELEMENTS EN BETON ET FABRICATION DU BETON PRET A L\'EMPLOI'),
(268, 109107, NULL, NULL, 'FABRICATION INDUSTRIELLE DE PRODUITS EN ARGILE NON REFRACTAIRE (BRIQUETERIE, TUILERIE INDUSTRIELLE)'),
(269, 109108, NULL, NULL, 'FABRICATION INDUSTRIELLE DE PRODUITS REFRACTAIRES ET CALORIFUGES'),
(270, 109109, NULL, NULL, 'FABRICATION INDUSTRIELLE DE PRODUITS CERAMIQUES AUTRES QUE SANITAIRES POUR L\'INDUSTRIE ET LE BATIMENT'),
(271, 109110, NULL, NULL, 'FABRICATION INDUSTRIELLE DE PRODUITS SANITAIRES EN CERAMIQUE'),
(272, 109111, NULL, NULL, 'FABRICATION DE MATERIAUX DE CONSTRUCTION EN PLASTIQUE'),
(273, 109112, NULL, NULL, 'FABRICATION D\'ARTICLES SANITAIRES EN PLASTIQUE'),
(274, 109113, NULL, NULL, 'FABRICATION INDUSTRIELLE, FACONNAGE ET TRANSFORMATION DU VERRE PLAT ET MIROITERIE'),
(275, 109114, NULL, NULL, 'FABRICATION INDUSTRIELLE, FACONNAGE ET TRANSFORMATION DU VERRE CREUXMECANIQUE ET DU VERRE TECHNIQUE'),
(276, 109115, NULL, NULL, 'FABRICATION DE CABINES \" SAHARIENNES \" ET AUTRES PREFABRICATIONS METALLIQUES'),
(277, 109116, NULL, NULL, 'CONSTRUCTION DE MAISONS ET CHALETS PREFABRIQUES, EN BOIS'),
(278, 109201, NULL, NULL, 'ENTREPRISE DE TRAVAUX DE BATIMENT TOUT CORPS D\'ETAT'),
(279, 109202, NULL, NULL, 'ENTREPRISE INDUSTRIELLE DE MENUISERIE DU BATIMENT'),
(280, 109203, NULL, NULL, 'ENTREPRISE DE TRAVAUX D\'ETANCHEITE DU BATIMENT ET DE PLOMBERIE'),
(281, 109204, NULL, NULL, 'ENTREPRISE DE PEINTURE DU BATIMENT'),
(282, 109205, NULL, NULL, 'ENTREPRISE INDUSTRIELLE DE PRODUCTION D\'ENSEMBLES DE CONSTRUCTION METALLIQUES'),
(283, 109206, NULL, NULL, 'ENTREPRISE DE TRAVAUX D\'ELECTRICITE'),
(284, 109207, NULL, NULL, 'ENTREPRISE D\'INSTALLATION DE SYSTEMES DE CLIMATISATION ET DE REFRIGERATION'),
(285, 109208, NULL, NULL, 'ENTREPRISE DE PROTECTION CONTRE LES INCENDIES ET LE VOL'),
(286, 109209, NULL, NULL, 'ENTREPRISE DE GRANDS TRAVAUX PUBLICS ET HYDRAULIQUES'),
(287, 109210, NULL, NULL, 'ENTREPRISE DE TERRASSEMENTS ET TRAVAUX RURAUX'),
(288, 109211, NULL, NULL, 'ENTREPRISE DE TRAVAUX DE ROUTES ET D\'AERODROMES'),
(289, 109212, NULL, NULL, 'ENTREPRISE DE TRAVAUX DE VOIES FERREES'),
(290, 109213, NULL, NULL, 'ENTREPRISE DE TRAVAUX URBAINS ET D\'HYGIENE PUBLIQUE'),
(291, 109214, NULL, NULL, 'ENTREPRISE D\'INSTALLATION DE RESEAUX ET DE CENTRALES ELECTRIQUES ET TELEPHONIQUES'),
(292, 109215, NULL, NULL, 'ENTREPRISE DE POSE DE CANALISATIONS A GRANDE DISTANCE'),
(293, 109216, NULL, NULL, 'INSTALLATIONS THERMIQUES INDUSTRIELLES (ENTREPRISE DE)'),
(294, 109217, NULL, NULL, 'ENTRERPISE DE TRAVAUX DE MAINTENANCE ET D\'EXPERTISE D\'OUVRAGES D\'ART'),
(295, 109218, NULL, NULL, 'ENTREPRISE DE PROMOTION IMMOBILIERE'),
(296, 109219, NULL, NULL, 'ENTREPRISE D\'ETUDES ET DE REALISATION EN GENIE CIVIL'),
(297, 109220, NULL, NULL, 'ENTREPRISE DE RESTAURATION DE BATIMENT'),
(298, 109221, NULL, NULL, 'ENTREPRISE D\'ETUDES ET DE REALISATIONS D\'OUVRAGES ELECTRIQUES ET GAZ'),
(299, 109222, NULL, NULL, 'ENTREPRISE D\'ETUDES ET DE REALISATION DE TOUTES BRANCHES D\'ACTIVITES DU B.T.P.H'),
(300, 109223, NULL, NULL, 'ENTREPRISE DE CONSTRUCTION ET D\'AMENAGEMENT D\'INFRASTRUCTURES DIVERSES'),
(301, 110101, NULL, NULL, 'FABRICATION INDUSTRIELLE DE SERRURERIE ET QUINCAILLERIE'),
(302, 110102, NULL, NULL, 'FABRICATION INDUSTRIELLE D\'ARTICLES DE FERBLANTERIE ET TOLERIE'),
(303, 110103, NULL, NULL, 'FABRICATION INDUSTRIELLE D\'ARTICLES DE MENAGE ET SIMILAIRES'),
(304, 110104, NULL, NULL, 'FABRICATION INDUSTRIELLE D\'ARTICLES DE COUTELLERIE'),
(305, 110105, NULL, NULL, 'FABRICATION DE MOBILIER METALLIQUE'),
(306, 110106, NULL, NULL, 'FABRICATION INDUSTRIELLE DE VAISSELLE EN POTERIE FINE, EN CERAMIQUE OU EN PORCELAINE'),
(307, 110107, NULL, NULL, 'FABRICATION INDUSTRIELLE DE TOUTES BROSSES DESTINEES A TOUS USAGES ET DE TOUS ARTICLES DE NETTOYAGE ET D\'ENTRETIEN EN TOUTES MATIERES'),
(308, 110108, NULL, NULL, 'TRANSFORMATION DE LA FRIPERIE'),
(309, 110109, NULL, NULL, 'TRANSFORMATION INDUSTRIELLE DE TOUTES MATIERES DESTINEES AU NETTOYAGE ET A L\'ENTRETIEN'),
(310, 110201, NULL, NULL, 'SCIERIE INDUSTRIELLE'),
(311, 110202, NULL, NULL, 'ENTREPRISE INDUSTRIELLE DE TRANSFORMATION DU BOIS'),
(312, 110203, NULL, NULL, 'FABRICATION INDUSTRIELLE D\'ENSEMBLES D\'AMEUBLEMENT EN BOIS A USAGE DOMESTIQUE OU HOTELIER'),
(313, 110204, NULL, NULL, 'FABRICATION DE MOBILIER EN BOIS POUR COLLECTIVITES'),
(314, 110205, NULL, NULL, 'ENTREPRISE INDUSTRIELLE DE FABRICATION DE LITERIE'),
(315, 110206, NULL, NULL, 'FABRICATION D\'ARTICLES D\'AMEUBLEMENT EN PLASTIQUE'),
(316, 110207, NULL, NULL, 'ENTREPRISE INDUSTRIELLE DE TRANSFORMATION DU LIEGE ET PRODUITS A BASE DE LIEGE'),
(317, 110208, NULL, NULL, 'FABRICATION DE LUMINAIRE'),
(318, 110301, NULL, NULL, 'FABRICATION DE MOBILIERS EN TOUTES MATIERES'),
(319, 111101, NULL, NULL, 'PRODUCTION ET DISTRIBUTION DES EAUX'),
(320, 111201, NULL, NULL, 'FABRICATION DE JEUX ET JOUETS'),
(321, 111202, NULL, NULL, 'FABRICATION INDUSTRIELLE D\'ARTICLES DE SPORT ET CAMPEMENT N.D.A.'),
(322, 111203, NULL, NULL, 'FABRICATION INDUSTRIELLE D\'INSTRUMENTS MODERNES DE MUSIQUE'),
(323, 111204, NULL, NULL, 'FABRICATION INDUSTRIELLE DE DISQUES, BANDES ET CASSETTES MAGNETIQUES'),
(324, 111205, NULL, NULL, 'FABRICATION D\'ARTICLES POUR FUMEURS, ET ARTICLES DE BIMBELOTERIE EN METAL'),
(325, 111206, NULL, NULL, 'FABRICATION INDUSTRIELLE D\'ARTICLES DE DECORATION'),
(326, 111301, NULL, NULL, 'INDUSTRIE DE L\'AMIANTE'),
(327, 111302, NULL, NULL, 'TRANSFORMATION INDUSTRIELLE DU PAPIER ET FABRICATION D\'ARTICLES DIVERS EN PAPIER'),
(328, 111303, NULL, NULL, 'FABRICATION DE VOITURES D\'ENFANTSET ARTICLES DE PUERICULTURE'),
(329, 111304, NULL, NULL, 'FABRICATION INDUSTRIELLE D\'ARTICLES ET FOURNITURES SCOLAIRES ET DE BUREAU'),
(330, 111305, NULL, NULL, 'FABRICATION D\'ARTICLES EN PLASTIQUE N.D.A.'),
(331, 111307, NULL, NULL, 'IMPRIMERIE INDUSTRIELLE'),
(332, 111308, NULL, NULL, 'CONSTRUCTION DE BATEAUX DE PLAISANCE'),
(333, 111309, NULL, NULL, 'FABRICATION DE TOUT CORPS CREUX EN PLASTIQUE ET EN TOUT TYPE DE RESINE'),
(334, 111310, NULL, NULL, 'FABRICATION D\'EMBALLAGES EN PLASTIQUE'),
(335, 111311, NULL, NULL, 'FABRICATION DE TOUS TYPES D\'EMBALLAGES EN PAPIER'),
(336, 111312, NULL, NULL, 'FABRICATION D\'EMBALLAGES EN TOUTES MATIERES'),
(337, 112001, NULL, NULL, 'RECUPERATION DES MATIERES METALLIQUES RECYCLABLES'),
(338, 112002, NULL, NULL, 'RECUPERATION DES MATIERES NON METALLIQUES RECYCLABLES'),
(339, 112003, NULL, NULL, 'FABRICATION DE DIVERS PRODUITS DE LA CHIMIE MINERALE'),
(340, 112004, NULL, NULL, 'MARBRERIE INDUSTRIELLE'),
(341, 112005, NULL, NULL, 'FABRICATION D\'EQUIPEMENTS ET MATERIELS POUR HOTEL, CAFES ET RESTAURANT, BLANCHISSERIES ET TEINTURERIES'),
(342, 201101, NULL, NULL, 'ENTREPRISE ARTISANALE DE POTERIE- FAIENCERIE'),
(343, 201102, NULL, NULL, 'ENTREPRISE ARTISANALE DE CERAMIQUE'),
(344, 201103, NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION DE VAISSELLE ET ARTICLES DE MENAGE EN PORCELAINE'),
(345, 201201, NULL, NULL, 'ENTREPRISE ARTISANALE DE VERRERIE'),
(346, 201202, NULL, NULL, 'ENTREPRISE ARTISANALE DE MARBRERIE'),
(347, 201301, NULL, NULL, 'ENTREPRISE ARTISANALE DE FERRONNERIE'),
(348, 201302, NULL, NULL, 'ENTREPRISE ARTISANALE DE DINANDERIE'),
(349, 201303, NULL, NULL, 'ENTREPRISE ARTISANALE DE BIJOUTERIE'),
(350, 201304, NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTIONS LAPIDAIRES'),
(351, 201401, NULL, NULL, 'ENTREPRISE ARTISANALE D\'EBENISTERIE'),
(352, 201402, NULL, NULL, 'ENTREPRISE ARTISANALE DE SPARTERIE ET VANNERIE'),
(353, 201501, NULL, NULL, 'ENTREPRISE ARTISANALE DE FILATURE DE LAINE'),
(354, 201502, NULL, NULL, 'ENTREPRISE ARTISANALE DE TISSAGE'),
(355, 201503, NULL, NULL, 'ENTREPRISE ARTISANALE DE FABRICATION DE TAPIS'),
(356, 201601, NULL, NULL, 'ENTREPRISE ARTISANALE DE MAROQUINERIE'),
(357, 202101, NULL, NULL, 'ENTREPRISE ARTISANALE D\'EXPLOITATION DE CARRIERES (PIERRES, GYPSE, PIERRE A CHAUX )'),
(358, 202102, NULL, NULL, 'ENTREPRISE ARTISANALE D\'EXPLOITATION DE L\'ARGILE'),
(359, 202103, NULL, NULL, 'ENTREPRISE ARTISANALE D\'EXTRACTIONET DE PREPARATION DU SEL'),
(360, 202201, NULL, NULL, 'ENTREPRISE ARTISANALE DE MODELAGE MECANIQUE'),
(361, 202202, NULL, NULL, 'ENTREPRISE ARTISANALE DE FABRICATION D\'ACCESSOIRES ET PIECES DETACHEES POUR AUTOMOBILES'),
(362, 202203, NULL, NULL, 'ENTREPRISE ARTISANALE DE FABRICATION DE PIECES DETACHEES ET ACCESSOIRES POUR CYCLES ET MOTOCYCLES'),
(363, 202204, NULL, NULL, 'ENTREPRISE ARTISANALE DE FABRICATION DE ROULEMENTS EN TOUTES MATIERES'),
(364, 202206, NULL, NULL, 'ENTREPRISE ARTISANALE DE GARNISSAGE ET REPARATION DE SELLERIE AUTOMOBILE ET VEHICULE'),
(365, 202301, NULL, NULL, 'ENTREPRISE ARTISANALE DE FONDERIE ET MOULAGE'),
(366, 202302, NULL, NULL, 'ENTREPRISE ARTISANALE DE CHAUDRONNERIE'),
(367, 202303, NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION DE CHARPENTES METALLIQUES'),
(368, 202304, NULL, NULL, 'ENTREPRISE ARTISANALE DE BOULONNERIE - VISSERIE'),
(369, 202305, NULL, NULL, 'ENTREPRISE ARTISANALE DE FERRONNERIE ET MENUISERIE METALLIQUE'),
(370, 202306, NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION DE PETITS ARTICLES METALLIQUES DIVERS'),
(371, 202307, NULL, NULL, 'PRODUCTION ARTISANALE DE MOBILIER METALLIQUE'),
(372, 202308, NULL, NULL, 'PRODUCTION ARTISANALE D\'USTENSILES DE CUISINE EN METAL'),
(373, 202309, NULL, NULL, 'PRODUCTION ARTISANALE DE MEDAILLESET ARTICLES DE FANTAISIE'),
(374, 202310, NULL, NULL, 'PRODUCTION ARTISANALE D\'ARTICLES DE FERBLANTERIE'),
(375, 202311, NULL, NULL, 'PRODUCTION ARTISANALE D\'ARTICLES DE COUTELLERIE'),
(376, 202401, NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION DES HUILES D\'OLIVES'),
(377, 202402, NULL, NULL, 'ENTREPRISE ARTISANALE DE MEUNERIE'),
(378, 202403, NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION D\'ALIMENTS DE BETAIL'),
(379, 202404, NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION DE CONFISERIE ET CHOCOLATERIE'),
(380, 202405, NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION DES ESSENCES ET AROMES'),
(381, 202406, NULL, NULL, 'ENTREPRISE ARTISANALE DE TORREFACTION ET MOUTURE'),
(382, 202407, NULL, NULL, 'BOULANGERIE PATISSERIE ARTISANALE'),
(383, 202408, NULL, NULL, 'BOULANGERIE (AUTRE QU\'INDUSTRIELLE)'),
(384, 202409, NULL, NULL, 'DISTILLERIE ARTISANALE'),
(385, 202410, NULL, NULL, 'PRODUCTION ARTISANALE DE CONDIMENTS DIVERS, EPICES ET SELS'),
(386, 202411, NULL, NULL, 'FABRICATION ARTISANALE DE GLACES ET CORNETS'),
(387, 202501, NULL, NULL, 'ENTREPRISE ARTISANALE DE BONNETERIE'),
(388, 202502, NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION D\'ARTICLES D\'ORNEMENT POUR L\'HABILLEMENT ET L\'AMEUBLEMENT'),
(389, 202503, NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION ET CONFECTION DE FILETS NOUES'),
(390, 202504, NULL, NULL, 'ENTREPRISE ARTISANALE DE CONFECTION DE VETEMENTS'),
(391, 202505, NULL, NULL, 'ENTREPRISE ARTISANALE DE TANNERIE'),
(392, 202506, NULL, NULL, 'ENTREPRISE ARTISANALE DE BRODERIE'),
(393, 202507, NULL, NULL, 'FABRICATION ARTISANALE D\'ARTICLES EN TOILE ET BACHE'),
(394, 202508, NULL, NULL, 'ENTREPRISE ARTISANALE D\'ARTICLES DE PELLETERIE'),
(395, 202509, NULL, NULL, 'FABRICATION ARTISANALE DE CHAPELLERIE'),
(396, 202510, NULL, NULL, 'FABRICATION ARTISANALE DE CHAUSSURES ET COMPOSANTS DE LACHAUSSURE'),
(397, 202511, NULL, NULL, 'FABRICATION D\'ARTICLES D\'HARNACHEMENT ET DE SELLERIE'),
(398, 202512, NULL, NULL, 'FABRICATION ARTISANALE D\'ARTICLES DIVERS LIEES AUX CEREMONIES'),
(399, 202601, NULL, NULL, 'ENTREPRISE ARTISANALE DE CHARPENTERIE DE MARINE'),
(400, 202602, NULL, NULL, 'ENTREPRISE ARTISANALE DE SCIAGE DU BOIS (SCIERIE)'),
(401, 202603, NULL, NULL, 'ENTREPRISE ARTISANALE DE MENUISERIE'),
(402, 202604, NULL, NULL, 'ENTREPRISE ARTISANALE DE FABRICATION D\'EMBALLAGES EN BOIS'),
(403, 202605, NULL, NULL, 'ENTREPRISE ARTISANALE DE MENUISERIE GENERALE'),
(404, 202606, NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION DE LITERIE'),
(405, 202607, NULL, NULL, 'ENTREPRISE ARTISANALE DE BROSSERIE'),
(406, 202608, NULL, NULL, 'TRAVAIL ARTISANAL DU LIEGE'),
(407, 202609, NULL, NULL, 'ENTREPRISE ARTISANALE DE FABRICATION D\'ARTICLES DE DECORATION'),
(408, 202701, NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION DE QUINCAILLERIE DE BATIMENT'),
(409, 202702, NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION DE PRODUITS EN CIMENT'),
(410, 202703, NULL, NULL, 'ENTREPRISE ARTISANALE DE SERRURERIE ET CLES MINUTE'),
(411, 202801, NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION ET DE CONDITIONNEMENT DE PRODUITS DE BLANCHIMENT ET D\'ENTRETIEN'),
(412, 202802, NULL, NULL, 'ENTREPRISE ARTISANALE DE MIROITERIE'),
(413, 202803, NULL, NULL, 'ENTREPRISE ARTISANALE DE FABRICATION D\'ARTICLES DIVERS EN PAPIER'),
(414, 202804, NULL, NULL, 'ENTREPRISE ARTISANALE DE FABRICATION DE JEUX ET JOUETS'),
(415, 202805, NULL, NULL, 'ENTREPRISE ARTISANALE DE FABRICATION D\'ARTICLES ET ACCESSOIRES DE PECHE'),
(416, 202806, NULL, NULL, 'ENTREPRISE ARTISANALE DE SERIGRAPHIE'),
(417, 301101, NULL, NULL, 'COMMERCE DE GROS DE BOISSONS NON ALCOOLISEES'),
(418, 301102, NULL, NULL, 'COMMERCE DE GROS DE BOISSONS ALCOOLISEES'),
(419, 301104, NULL, NULL, 'COMMERCE DE GROS DES LEGUMES CONDIMENTES OU EPLUCHES'),
(420, 301105, NULL, NULL, 'COMMERCE DE GROS DE LAIT, PRODUITS LAITIERS ET OEUFS'),
(421, 301106, NULL, NULL, 'COMMERCE DE GROS DE LA DATTE ET DES FRUITS CONFITS'),
(422, 301107, NULL, NULL, 'COMMERCE DE GROS DES PRODUITS DE LA BOULANGERIE ET LA PATISSERIE'),
(423, 301108, NULL, NULL, 'COMMERCE DE GROS DES LEGUMES SECS ET DES PRODUITS DE LA MINOTERIE'),
(424, 301109, NULL, NULL, 'COMMERCE DE GROS DES PRODUITS DE LA CONSERVERIE ALIMENTAIRE'),
(425, 301110, NULL, NULL, 'COMMERCE DE GROS DES HUILES ANIMALES, VEGETALES ET AUTRES CORPS GRAS'),
(426, 301111, NULL, NULL, 'COMMERCE DE GROS DE LA BISCUITERIE, CHOCOLATERIE ET CONFISERIE'),
(427, 301112, NULL, NULL, 'MANDATAIRE EN FRUITS ET LEGUMES DANS LES MARCHES DE GROS'),
(428, 301113, NULL, NULL, 'COMMERCE DE GROS DES PRODUITS LIES A L\'ALIMENTATION HUMAINE'),
(429, 301114, NULL, NULL, 'COMMERCE DE GROS DE DENREES ET PRODUITS DESTINES A L\'ALIMENTATION INFANTILE ET ARTICLES DIVERS POUR NOURRISSONS'),
(430, 301115, NULL, NULL, 'COMMERCE DE GROS DE SEL BRUT ET DE TOUS AUTRES TYPES DE SEL'),
(431, 301116, NULL, NULL, 'COMMERCE DE GROS DE PLATS PREPARES EN CONSERVE, SURGELES OU CONGELES'),
(432, 301117, NULL, NULL, 'COMMERCE DE GROS DES EPICES'),
(433, 301118, NULL, NULL, 'COMMERCE DE GROS DE FRUITS ET LEGUMES ( ACTIVITE A EXERCER DANS UN LOCAL COMMERCIAL)'),
(434, 301119, NULL, NULL, 'CENTRALE D\'ACHAT'),
(435, 301201, NULL, NULL, 'COMMERCE DE GROS DE VIANDE DE BOUCHERIE'),
(436, 301202, NULL, NULL, 'COMMERCE DE GROS DE VOLAILLES, OEUFS ET LAPINS'),
(437, 301203, NULL, NULL, 'COMMERCE DE GROS DE VIANDE CHEVALINE'),
(438, 301204, NULL, NULL, 'COMMERCE DE GROS D\'ABATS ET VIANDE PREPARES CRUS'),
(439, 301205, NULL, NULL, 'COMMERCE DE GROS DE LA CHARCUTERIE'),
(440, 301206, NULL, NULL, 'COMMERCE DE GROS DE CONSERVES A BASE DE VIANDE ET DE POISSONS'),
(441, 301301, NULL, NULL, 'COMMERCE DE GROS DE POISSONS, CRUSTACES ET COQUILLAGES'),
(442, 301401, NULL, NULL, 'COMMERCE DE GROS DE PRODUITS ALIMENTAIRES SPECIALISES'),
(443, 302101, NULL, NULL, 'COMMERCE DE GROS DE TISSUS, FILS ET FILES'),
(444, 302102, NULL, NULL, 'COMMERCE DE GROS DE LA MERCERIE ET BONNETERIE'),
(445, 302103, NULL, NULL, 'COMMERCE DE GROS D\'ARTICLES D\'HABILLEMENT ET ACCESSOIRES POUR VETEMENT'),
(446, 302104, NULL, NULL, 'COMMERCE DE GROS DE VETEMENTS USAGES ET DE FRIPERIES'),
(447, 302201, NULL, NULL, 'COMMERCE DE GROS DE PELLETERIE ET DES FOURRURES'),
(448, 302202, NULL, NULL, 'COMMERCE DE GROS DE FOURNITURES DE BOURRELLERIE'),
(449, 302203, NULL, NULL, 'COMMERCE DE GROS DE LA CHAUSSURE'),
(450, 302204, NULL, NULL, 'COMMERCE DE GROS DE LA MAROQUINERIE'),
(451, 302301, NULL, NULL, 'COMMERCE DE GROS DES ARTICLES DE L\'HORLOGERIE ET DE LA BIJOUTERIE'),
(452, 302401, NULL, NULL, 'COMMERCE DE GROS DES COSMETIQUES ET PRODUITS DE BEAUTE'),
(453, 303101, NULL, NULL, 'COMMERCE DE GROS D\'APPAREILS ELECTRONIQUES ET ELECTROMENAGERS'),
(454, 303102, NULL, NULL, 'COMMERCE DE GROS D\'APPAREILS SANITAIRES ET DE CHAUFFAGE'),
(455, 303103, NULL, NULL, 'COMMERCE DE GROS DE TAPIS, COUVERTURES ET AUTRES ARTICLES SIMILAIRES A BASE DE MATIERES TEXTILES'),
(456, 303104, NULL, NULL, 'COMMERCE DE GROS D\'ARTICLES DE VANNERIE ET DE SPARTERIE'),
(457, 303105, NULL, NULL, 'COMMERCE DE GROS DES PRODUITS DE L\'ARTISANAT ET DE LA MAROQUINERIETRADITIONNELLE'),
(458, 303106, NULL, NULL, 'COMMERCE DE GROS DE MEUBLES ET ARTICLES D\'AMEUBLEMENT A USAGE DOMESTIQUE OU DE BUREAU'),
(459, 303107, NULL, NULL, 'COMMERCE DE GROS DES ARTICLES DE DINANDERIE'),
(460, 303108, NULL, NULL, 'COMMERCE DE GROS DE LA LUSTRERIE ET ARTICLES DE DECORATION D\'INTERIEUR'),
(461, 303109, NULL, NULL, 'COMMERCE DE GROS DE TOUS MATERIELS ET EQUIPEMENTS LIES AU DOMAINE DE L\'ELECTRICITE ET L\'ELECTRONIQUE'),
(462, 303110, NULL, NULL, 'COMMERCE DE GROS DE TOUS APPAREILS ET MACHINES PROFESSIONNELS OU DOMESTIQUES D\'OCCASIONS'),
(463, 303111, NULL, NULL, 'COMMERCE DE GROS DE MATERIELS ET ARTICLES DE COIFFURE ET D\'ESTHETIQUE'),
(464, 303201, NULL, NULL, 'COMMERCE DE GROS DE LA VERRERIE,VAISSELLE, ARTICLES DE MENAGE ET USTENSILES DE CUISINE'),
(465, 303202, NULL, NULL, 'COMMERCE DE GROS DES PRODUITS DE LA DROGUERIE, PRODUITS D\'HYGIENE, D\'ENTRETIEN DOMESTIQUE, PROFESSIONNEL ET AUTRES PRODUITS SIMILAIRES'),
(466, 304101, NULL, NULL, 'COMMERCE DE GROS DE MATERIAUX DE CONSTRUCTION, CERAMIQUE SANITAIRE VERRE PLAT'),
(467, 304102, NULL, NULL, 'COMMERCE DE GROS DE QUINCAILLERIE ET FOURNITURES POUR PLOMBERIE ET CHAUFFAGE'),
(468, 304103, NULL, NULL, 'COMMERCE DE GROS DE LA VITRERIE ET DE LA MIROITERIE'),
(469, 304104, NULL, NULL, 'COMMERCE DE GROS DU BOIS, DU LIEGE ET PRODUITS DERIVES'),
(470, 304105, NULL, NULL, 'COMMERCE DE GROS DE PEINTURES ET VERNIS'),
(471, 304106, NULL, NULL, 'COMMERCE DE GROS DE PRODUITS D\'ETANCHIETE'),
(472, 304201, NULL, NULL, 'COMMERCE DE GROS DES FOURNITURES POUR L\'ELECTRICITE'),
(473, 304202, NULL, NULL, 'COMMERCE DE GROS D\'ARTICLES DE REVETEMENT D\'INTERIEUR'),
(474, 305001, NULL, NULL, 'COMMERCE DE GROS DE MATERIELS INFORMATIQUE ET DE MACHINES DE BUREAU, LEURS ACCESSOIRES ET FOURNITURES'),
(475, 305002, NULL, NULL, 'COMMERCE DE GROS DES EQUIPEMENTS ET MATERIELS THERMIQUES ET FRIGORIFIQUES'),
(476, 305003, NULL, NULL, 'COMMERCE DE GROS D\'EQUIPEMENTS, MATERIEL ET FOURNITURES DE SECURITE ET DE PROTECTION'),
(477, 305004, NULL, NULL, 'COMMERCE DE GROS DES EQUIPEMENTS DE CUISINE, DE BOULANGERIE ET EQUIPEMENTS DE MAGASINS'),
(478, 305005, NULL, NULL, 'COMMERCE DE GROS DES EQUIPEMENTS DE TRANSPORTS, DE MANUTENTION ET DE MACHINES UTILISES DANS L\'INDUSTRIE'),
(479, 305006, NULL, NULL, 'COMMERCE DE GROS DES EQUIPEMENTS ET MATERIELS POUR LA BLANCHISSERIE, EQUIPEMENTS POUR HOTELS, CAFES ET RESTAURANTS'),
(480, 305007, NULL, NULL, 'COMMERCE DE GROS D\'ARTICLES D\'EMBALLAGE'),
(481, 305008, NULL, NULL, 'COMMERCE DE GROS DES INSTRUMENTS ET APPAREILS DE MESURE ET DE PESAGE'),
(482, 305009, NULL, NULL, 'COMMERCE DE GROS DE MACHINES A TRICOTER ET A COUDRE'),
(483, 305010, NULL, NULL, 'COMMERCE DE GROS DE TOUS MATERIELS ET PRODUITS LIES AU DOMAINE DE LA TELLECOMMUNICATION, RADIOCOMMUNICATION ET LA TELEPHONIE, LEURS ACCESSOIRES ET PIECES DETACHEES .'),
(484, 305011, NULL, NULL, 'COMMERCE DE GROS DE MACHINES ET MATERIELS POUR BTP'),
(485, 305012, NULL, NULL, 'COMMERCE DE GROS D\'APPAREILS MEDICAUX-CHIRURGICAUX'),
(486, 305013, NULL, NULL, 'COMMERCE DE GROS D\'APPAREILS ET MACHINES MANUELS, MECANIQUES DESTINES A TOUS USAGES'),
(487, 305014, NULL, NULL, 'COMMERCE DE GROS DE VEHICULES (CONCESSIONNAIRE AUTOMOBILE)'),
(488, 305015, NULL, NULL, 'COMMERCE DE GROS DE PRODUITS CHIMIQUES'),
(489, 305016, NULL, NULL, 'COMMERCE DE GROS D\'APPAREILS, ARTICLES ET PRODUITS DE FILTRATION OU D\'EPURATION DES LIQUIDES ET DES GAZ'),
(490, 305017, NULL, NULL, 'COMMERCE DE GROS D\'APPAREILS EQUIPEMENTS DESTINES A TOUS TRAVAUX DE LABORATOIRE'),
(491, 306001, NULL, NULL, 'COMMERCE DE GROS DE PIECES DETACHEES ET ACCESSOIRES POUR VEHICULES AUTOMOBILES'),
(492, 306002, NULL, NULL, 'COMMERCE DE GROS DE MATERIELS ET PIECES POUR L\'ENTRETIEN ET LES REPARATIONS MECANIQUES'),
(493, 306003, NULL, NULL, 'COMMERCE DE GROS DE CYCLES ET MOTOCYCLES , PIECES DETACHEES ET ACCESSOIRES POUR CYCLES ET MOTOCYCLES'),
(494, 306004, NULL, NULL, 'COMMERCE DE GROS DE PNEUMATIQUES'),
(495, 306005, NULL, NULL, 'COMMERCE DE GROS D\'OUTILS ET OUTILLAGES'),
(496, 306101, NULL, NULL, 'COMMERCE DE GROS DE PRODUITS ET ARTICLES DESTINES A LA FABRICATION DES CHAUSSURES'),
(497, 306103, NULL, NULL, 'COMMERCE DE GROS DARTICLES EN CAOUTCHOUC ET EN PLASTIQUE DESTINES A TOUS USAGES'),
(498, 307101, NULL, NULL, 'COMMERCE DE GROS DE FOURNITURES ET ARTICLES DE SPORT ET AUTRES LOISIRS'),
(499, 307102, NULL, NULL, 'COMMERCE DE GROS DE BARQUES ET AUTRES EMBARCATIONS'),
(500, 307103, NULL, NULL, 'COMMERCE DE GROS DE JOUETS ET JEUX DE SOCIETE'),
(501, 307104, NULL, NULL, 'COMMERCE GROS DES ARMES DE CHASSE'),
(502, 307201, NULL, NULL, 'COMMERCE DE GROS DE LA LIBRAIRIE, PAPETERIE, DE FOURNITURES ET ARTICLES POUR LES ACTIVITES DES BEAUX ARTS'),
(503, 307202, NULL, NULL, 'COMMERCE DE GROS DES LIVRES D\'OCCASION'),
(504, 307203, NULL, NULL, 'COMMERCE DE GROS DE MATERIELS, ARTICLES ET FOURNITURES POUR L\'ENSEIGNEMENT ET LES COLLECTIVITES'),
(505, 307204, NULL, NULL, 'COMMERCE DE GROS DE TABACS MANUFACTURES ET D\'ALLUMETTES'),
(506, 307301, NULL, NULL, 'COMMERCE DE GROS DES INSTRUMENTS DE MUSIQUE, ARTICLES ET FOURNITURES LIES AUX ACTIVITES ARTISTIQUES'),
(507, 307302, NULL, NULL, 'COMMERCE DE GROS DES APPAREILS PHOTOGRAPHIQUES ET CINEMATOGRAPHIQUES'),
(508, 308001, NULL, NULL, 'COMMERCE DE GROS DES PRODUITS PHARMACEUTIQUES ET VETERINAIRES'),
(509, 308002, NULL, NULL, 'COMMERCE DE GROS DE PRODUITS PHARMACEUTIQUES'),
(510, 308003, NULL, NULL, 'COMMERCE DE GROS DE PRODUITS VETERINAIRES'),
(511, 308004, NULL, NULL, 'COMMERCE DE GROS DE PRODUITS PARAPHARMACEUTIQUES, MATERIELS ET INSTRUMENTS MEDICO - CHIRURGICAUX, LEURS ACCESSOIRES, PIECES DETACHEES ET CONSOMMABLES'),
(512, 308005, NULL, NULL, 'COMMERCE DE GROS D\'ARTICLES DE LUNETTERIE ET D\'OPTIQUE MEDICALE'),
(513, 308006, NULL, NULL, 'COMMERCE DE GROS DE PRODUITS PHYTOSANITAIRES'),
(514, 309001, NULL, NULL, 'COMMERCE DE GROS DE FLEURS ET PLANTES DE TOUS GENRES'),
(515, 309002, NULL, NULL, 'COMMERCE DE GROS DE SEMENCES, DE GRAINES, PLANTS ET AUTRES PRODUITS HORTICOLES'),
(516, 309003, NULL, NULL, 'COMMERCE DE GROS DES ENGRAIS ET PRODUITS PHYTOSANITAIRES'),
(517, 309004, NULL, NULL, 'COMMERCE DE GROS DES CEREALES ET ALIMENTS DU BETAIL'),
(518, 309005, NULL, NULL, 'COMMERCE DE GROS DES HUILES ET DES GRAISSES NON COMBUSTIBLES'),
(519, 309006, NULL, NULL, 'COMMERCE DE GROS D\'ANIMAUX VIVANTS'),
(520, 309007, NULL, NULL, 'COMMERCE DE GROS DE CUIRS ET PEAUX'),
(521, 309008, NULL, NULL, 'COMMERCE DE GROS D\'EQUIPEMENTS, MATERIELS ET MACHINES AGRICOLES'),
(522, 309009, NULL, NULL, 'COMMERCE DE GROS DE MATERIELS HYDRAULIQUES DESTINES A L\'AGRICULTURE'),
(523, 310001, NULL, NULL, 'COMMERCE DE GROS DES COMBUSTIBLES SOLIDES, LIQUIDES ET GAZEUX'),
(524, 310002, NULL, NULL, 'COMMERCE DE GROS DE LUBRIFIANTS ET GRAISSES INDUSTRIELLES'),
(525, 310003, NULL, NULL, 'COMMERCE DE GROS DU GAZ BUTANE, PROPANE ET DU GAZ INDUSTRIEL'),
(526, 310004, NULL, NULL, 'COMMERCE DE GROS DES METAUX'),
(527, 310005, NULL, NULL, 'COMMERCE DE GROS DES VIEUX FERS, METAUX, TESSONS, DEBRIS DE VERRE ET BROCANTERIE'),
(528, 310006, NULL, NULL, 'COMMERCE DE GROS DES DECHETS INDUSTRIELS DIVERS ET AUTRES PRODUITS RECUPERES N.D.A'),
(529, 310007, NULL, NULL, 'COMMERCE DE GROS DESEQUIPEMENTS, MATERIELS, ET PRODUITS LIES AU DOMAINE DES HYDROCARBURES'),
(530, 401101, NULL, NULL, 'IMPORT - EXPORT DES PRODUITS , EQUIPEMENTS ET MATERIELS ET PRODUITS LIES AU DOMAINE DE L\' AGRICULTURE, LEURS PIECES DETACHEES ET ACCESSOIRES'),
(531, 401102, NULL, NULL, 'IMPORT - EXPORT DE MATERIELS ET PRODUITS DESTINES A L\'AVICULTURE'),
(532, 401201, NULL, NULL, 'IMPORTATION DES MATERIELS ET PRODUITS LIES AU DOMAINE DE LA SYLVICULTURE'),
(533, 401301, NULL, NULL, 'IMPORT - EXPORT DES PRODUITS ET MATERIELS LIE AU SECTEUR DE LA PECHE ET DE L\'AQUACULTURE'),
(534, 402001, NULL, NULL, 'IMPORT - EXPORT DES PRODUITS LIES A L\'ALIMENTATION HUMAINE'),
(535, 402002, NULL, NULL, 'IMPORTATION DE DENREES ET PRODUITS DESTINES A L\'ALIMENTATION INFANTILE ET ARTICLES DIVERS POUR NOURISSONS'),
(536, 402101, NULL, NULL, 'IMPORT - EXPORT DE BOISSONS NON ALCOOLISEES'),
(537, 402102, NULL, NULL, 'IMPORT - EXPORT DE BOISSONS ALCOOLISEES'),
(538, 402103, NULL, NULL, 'IMPORT - EXPORT DE FRUITS ET LEGUMES'),
(539, 402104, NULL, NULL, 'IMPORT - EXPORT DES LEGUMES CONDIMENTES OU EPLUCHES'),
(540, 402105, NULL, NULL, 'IMPORT - EXPORT DE LAIT, PRODUITS LAITIERS ET OEUFS'),
(541, 402106, NULL, NULL, 'IMPORT - EXPORT DE SUCRE, THE, CAFE, CACAO ET EPICES'),
(542, 402107, NULL, NULL, 'IMPORT - EXPORT DE LA DATTE ET DES FRUITS SECHES OU CONFITS'),
(543, 402108, NULL, NULL, 'IMPORT - EXPORT DES PRODUITS POUR LA BOULANGERIE'),
(544, 402109, NULL, NULL, 'IMPORT - EXPORT DE LEGUMES SECS ET DES PRODUITS DE LA MINOTERIE'),
(545, 402110, NULL, NULL, 'IMPORT - EXPORT DES PRODUITS DE LA CONSERVERIE ALIMENTAIRE'),
(546, 402111, NULL, NULL, 'IMPORT - EXPORT DES HUILES ANIMALES, VEGETALES ET AUTRES CORPS GRAS'),
(547, 402112, NULL, NULL, 'IMPORT - EXPORT DE BISUITERIE, CHOCOLATERIE ET CONFISERIE'),
(548, 402113, NULL, NULL, 'IMPORT - EXPORT DE MATEIRES PREMIERES ET PRODUITS DESTINES A L\'INDUSTRIE AGRO-ALIMENTAIRE'),
(549, 402114, NULL, NULL, 'IMPORT - EXPORT D\'ARTICLES ET FOURNITURES DIVERS DE LA PUERICULTURE'),
(550, 402115, NULL, NULL, 'IMPORT - EXPORT DE DENREES ET PRODUITS DESTINES A L\'ALIMENTATION INFANTILE ET ARTICLES DIVERS DE LA PUERICULTURE'),
(551, 402201, NULL, NULL, 'IMPORT - EXPORT DE VIANDE DE BOUCHERIE'),
(552, 402202, NULL, NULL, 'IMPORT - EXPORT DE VOLAILLES, OEUFS ET LAPINS'),
(553, 402203, NULL, NULL, 'IMPORT - EXPORT DE VIANDE CHEVALINE'),
(554, 402204, NULL, NULL, 'IMPORT - EXPORT D\'ABATS ET VIANDE PREPARES'),
(555, 402205, NULL, NULL, 'IMPORT - EXPORT DE LA CHARCUTERIE'),
(556, 402206, NULL, NULL, 'IMPORT - EXPORT DE CONSERVES A BASE DE VIANDE ET DE POISSONS'),
(557, 402301, NULL, NULL, 'IMPORT - EXPORT DE PRODUITS DE LA MER'),
(558, 402401, NULL, NULL, 'IMPORT - EXPORT DE PRODUITS ALIMENTAIRES SPECIFIQUES'),
(559, 402501, NULL, NULL, 'IMPORT - EXPORT DE TABACS BRUTS OU MANUFACTURES ET ALLUMETTES (activité réservée à la S.N.T.A )'),
(560, 402502, NULL, NULL, 'IMPORT - EXPORT DARTICLES POUR FUMEURS'),
(561, 403101, NULL, NULL, 'IMPORTATION DES EQUIPEMENTS, MATERIELS ET PRODUITS LIES AU DOMAINE DES HYDROCARBURES'),
(562, 403102, NULL, NULL, 'IMPORT - EXPORT DE COMBUSTIBLES SOLIDES LIQUIDES ET GAZEUX'),
(563, 403103, NULL, NULL, 'IMPORT - EXPORT DE LUBRIFIANTS, BITUMES ET SOLVANTS'),
(564, 403201, NULL, NULL, 'IMPORTATION DES MATERIELS ET PRODUITS LIES AU DOMAINE DE L\'ENERGIE'),
(565, 403301, NULL, NULL, 'IMPORTATION DES MATERIELS ET PRODUITS LIES AU DOMAINE HYDRAULIQUE'),
(566, 404001, NULL, NULL, 'IMPORTATION DES EQUIPEMENTS, MATERIELS ET PRODUITS LIES AU DOMAINE DES MINES'),
(567, 404002, NULL, NULL, 'IMPORT - EXPORT DE METAUX ET PRODUITS METALLIQUES ET SIDERURGIQUES'),
(568, 404003, NULL, NULL, 'IMPORT - EXPORT DES VIEUX FERS METAUX, TESSONS, DEBRIS DE VERRE ET BROCANTERIE'),
(569, 405101, NULL, NULL, 'IMPORTATION DES MATERIELS ET PRODUITS LIES AU DOMAINE DES TEXTILES'),
(570, 405102, NULL, NULL, 'IMPORT - EXPORT D\'ARTICLES D\'HABILLEMENT USAGES ET DE TOUTES FRIPERIES'),
(571, 405103, NULL, NULL, 'IMPORT - EXPORT DE TISSUS, FILS ET FILES'),
(572, 405104, NULL, NULL, 'IMPORT - EXPORT DE LA MERCERIE ET BONNETERIE'),
(573, 405105, NULL, NULL, 'IMPORT - EXPORT D\'ARTICLES D\'HABILLEMENT ET ACCESSOIRES POUR VETEMENTS'),
(574, 405106, NULL, NULL, 'IMPORT - EXPORT DE TAPIS COUVERTURES ET AUTRES ARTICLES SIMILAIRES A BASE DE MATIERES TEXTILE OU SYNTHETIQUE'),
(575, 405107, NULL, NULL, 'IMPORT - EXPORT D\'AUTRES ARTICLES CONFECTIONNES EN TEXTILES'),
(576, 405108, NULL, NULL, 'IMPORT - EXPORT DE BIJOUTERIE DE FANTAISIE'),
(577, 405201, NULL, NULL, 'IMPORT - EXPORT DE MATERIELS ET PRODUITS LIES AU DOMAINE DES CUIRS'),
(578, 405202, NULL, NULL, 'IMPORT - EXPORT DE PEAUX, DE CUIRS, DE PELLETERIE ET LEURS ACCESSOIRES'),
(579, 405203, NULL, NULL, 'IMPORT - EXPORT DARTICLES DE BOURRELLERIE'),
(580, 405204, NULL, NULL, 'IMPORT - EXPORT D\'ARTICLES DE MAROQUINERIE'),
(581, 405205, NULL, NULL, 'IMPORT - EXPORT DE LA CHAUSSURE'),
(582, 405206, NULL, NULL, 'IMPORT - EXPORT DES PRODUITS ET ARTICLES DESTINEES A LA FABRICATION DE CHAUSSURES'),
(583, 405207, NULL, NULL, 'IMPORT - EXPORT DES PRODUITS ET ARTICLES DESTINES A LA FABRICATION DE LA MAROQUINERIE'),
(584, 405301, NULL, NULL, 'IMPORT - EXPORT DE MATERIELS, PRODUITS ET MATIERES PREMIERES DESTINES A LA BIJOUTERIE INDUSTRIELLE'),
(585, 405302, NULL, NULL, 'IMPORT - EXPORT DES ARTICLES DE BIJOUTERIE'),
(586, 406101, NULL, NULL, 'IMPORTATION DES MATERIELS ET PRODUITS A USAGE INDUSTRIEL OU AGRICOLE'),
(587, 406102, NULL, NULL, 'IMPORT - EXPORT DE PRODUITS CHIMIQUES DE TOUTES NATURES ET PREPARATIONS CHIMIQUES DESTINEES A TOUS USAGES');
INSERT INTO `t_02_03_01` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`) VALUES
(588, 406103, NULL, NULL, 'IMPORT - EXPORT DARTICLES EN CAOUTCHOUC ET EN PLASTIQUE DESTINES A TOUS USAGES'),
(589, 406104, NULL, NULL, 'IMPORT - EXPORT DE MATIERE PREMIERE N.D.A'),
(590, 406201, NULL, NULL, 'IMPORT - EXPORT DES PRODUITS ET MATERIELS PHARMACEUTIQUES ET VETERINAIRES'),
(591, 406202, NULL, NULL, 'IMPORT - EXPORT DES PRODUITS PHARMACEUTIQUES'),
(592, 406203, NULL, NULL, 'IMPORT - EXPORT DES PRODUITS VETERINAIRES'),
(593, 406204, NULL, NULL, 'IMPORT - EXPORT DES PRODUITS PARA-PHARMACEUTIQUES, MATERIELS ET INSTRUMENTS MEDICO-CHIRURGICAUX, PIECES DETACHEES ET CONSOMMABLES'),
(594, 406205, NULL, NULL, 'IMPORT - EXPORT D4APPAREILS, D\'INSTRUMENTS ET D\'ARTICLES DE LUNETTERIE ET D\'OPTIQUE MEDICALE'),
(595, 406206, NULL, NULL, 'IMPORT - EXPORT DE PLANTES MEDICINALES OU INDUSTRIELLES'),
(596, 406207, NULL, NULL, 'IMPORT - EXPORT DE PRODUITS PHYTOSANITAIRES'),
(597, 406208, NULL, NULL, 'IMPORT - EXPORT DE PRODUITS DIETETIQUES, NUTRITIONNELS ET DE REGIME'),
(598, 406209, NULL, NULL, 'IMPORT - EXPORT DE MATERIELS ET PRODUITS DESTINES AU NETTOYAGE ET DESINFECTION DES CENTRES HOSPITALIERS'),
(599, 406210, NULL, NULL, 'IMPORT - EXPORT DE MATERIELS ET PRODUITS DESTINES AU NETTOYAGE ET DESINFECTION AGRICOLES, ALIMENTAIRES ET COLLECTIVITES LOCALES'),
(600, 406301, NULL, NULL, 'IMPORT - EXPORT DES PRODUITS COSMETIQUES ET PRODUITS D\'HYGIENE CORPORELLE'),
(601, 406302, NULL, NULL, 'IMPORT - EXPORT DE MATIERES PREMIERES ET DES HUILES ESSENTIELLES DESTINEES A LA FABRICATION DES PRODUITS COSMETIQUES, DE LA PARFUMERIE ET D\'HYGIENE CORPORELLE'),
(602, 406303, NULL, NULL, 'IMPORT - EXPORT DE MATERIELS ET ARTICLES DE COIFFURE ET D\'ESTHETIQUE'),
(603, 407001, NULL, NULL, 'IMPORTATION DES EQUIPEMENTS,MATERIELS ET PRODUITS LIES AU DOMAINE DU BATIMENT ET DES TRAVAUX PUBLICS'),
(604, 407002, NULL, NULL, 'IMPORT - EXPORT DE MACHINES MATIERES ET PRODUITS POUR LA SECURITE ET LA SIGNALISATION ROUTIERE'),
(605, 407003, NULL, NULL, 'IMPORT - EXPORT DE LA VERRERIE'),
(606, 407101, NULL, NULL, 'IMPORT - EXPORT DE BOIS, LIEGE ET OUVRAGES DE SPARTERIE ET DE VANNERIE'),
(607, 408101, NULL, NULL, 'IMPORT - EXPORT DE MATERIELS, PIECES DETACHEES ET ACCESSOIRES LIES AU DOMAINE MECANIQUE'),
(608, 408102, NULL, NULL, 'IMPORT - EXPORT DE PNEUMATIQUES'),
(609, 408201, NULL, NULL, 'IMPORT - EXPORT DE MATERIELS ET PRODUITS LIES AU DOMAINE ELECTROMENAGER'),
(610, 408301, NULL, NULL, 'IMPORT - EXPORT DE MATERIELS ET PRODUITS LIES AU DOMAINE INFORMATIQUE ET BUREAUTIQUE'),
(611, 408302, NULL, NULL, 'IMPORT - EXPORT D\'APPAREILS, MATERIELS ET ARTICLES D\'ENREGISTREMENT ET DE REPRODUCTION DU SON, DES IMAGES LEURS ACCESSOIRES ET PIECES DETACHEES'),
(612, 408303, NULL, NULL, 'IMPORT - EXPORT D\'APPAREILS, ET EQUIPEMENTS DE GESTION DE LA MONETIQUE'),
(613, 408308, NULL, NULL, 'IMPORT - EXPORT DE PLANCHERS SURELEVES ET FAUX PLAFONDS DESTINES A L\'AMENAGEMENT DE CENTRES INFORMATIQUES ET TECHNIQUES'),
(614, 408309, NULL, NULL, 'IMPORT - EXPORT DE MATERIELS ET PRODUITS LIES AU DOMAINE PHOTOGRAPHIQUE ET CINEMATOGRAPHIQUE'),
(615, 408401, NULL, NULL, 'IMPORT - EXPORT D\'EQUIPEMENTS, MATERIELS ET ACCESSOIRES DE BOULANGERIE, BOUCHERIE, DE GRANDE CUISINE, COLLECTIVITES ET D\'EQUIPEMENTS DE MAGASINS'),
(616, 408402, NULL, NULL, 'IMPORT - EXPORT D\'EQUIPEMENTS ET MATERIELS POUR HOTEL, CAFES ET RESTAURANTS, BLANCHISSERIES ET TEINTURERIES'),
(617, 408403, NULL, NULL, 'IMPORT - EXPORT D\'EQUIPEMENTS ET MATERIELS THERMIQUES ET FRIGORIFIQUES'),
(618, 408404, NULL, NULL, 'IMPORT - EXPORT D\'EQUIPEMENTS ET MATERIELS UTILISES POUR LA PRODUCTION D\'EMBALLAGES ET LE CONDITIONNEMENT'),
(619, 408405, NULL, NULL, 'IMPORT - EXPORT D\'EQUIPEMENTS, D\'APPAREILS ET INSTRUMENTS DESTINES A L\'EXPERIMENTATION, LA RECHERCHE SCIENTIFIQUE, LE PESAGE- MESURAGE & ANALYSES PHYSIQUE ET CHIMIQUE, ET LEURS ACCESSOIRES'),
(620, 408406, NULL, NULL, 'IMPORT - EXPORT D\'EQUIPEMENTS ET MATERIELS D\'IMPRIMERIE, DE SERIGRAPHIE ET IMPORTATION DE PAPIER'),
(621, 408407, NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS ET EQUIPEMENTS LIES AU DOMAINE DE L\'ELECTRICITE ET L\'ELECTRONIQUE'),
(622, 408408, NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS ET PRODUITS LIES AU DOMAINE DE LA TELECOMMUNICATION, LA RADIOCOMMUNICATION ET LA TELEPHONIE'),
(623, 408409, NULL, NULL, 'IMPORT - EXPORT DE PAPIERS ET DERIVES DU PAPIER'),
(624, 408410, NULL, NULL, 'IMPORT - EXPORT DE MATIERES PREMIERES, EQUIPEMENTS, MATERIELS ET ACCESSOIRES LIES AU DOMAINE DE LA CERAMIQUE'),
(625, 408411, NULL, NULL, 'IMPORT - EXPORT DE TOLES GALVANISEES OU INOXYDABLES'),
(626, 409001, NULL, NULL, 'IMPORT - EXPORT DES MATERIELS ET PRODUITS LIES AU DOMAINE DE LA QUINCAILLERIE, DROGUERIE, ARTICLES DE MENAGE, ET TOUS ARTICLES D\'HYGIENE ET D\'ENTRETIEN DOMESTIQUE ET PROFESSIONNEL'),
(627, 409002, NULL, NULL, 'IMPORT - EXPORT DE TOUS EQUIPEMENTS, APPAREILS ET ARTICLES SANITAIRES EN CERAMIQUES ET AUTRES MATIERES ET ARTICLES DE CHAUFFAGES ET DE PLOMBERIE EN TOUTES MATIERES'),
(628, 409003, NULL, NULL, 'IMPORT - EXPORT D\'ARTICLE DE LA COUTELLERIE'),
(629, 409004, NULL, NULL, 'IMPORT - EXPORT D\'OUTILS ET OUTILLAGES MECANIQUES, ELECTROMECANIQUES, ELECTRIQUES ET ELECTRONIQUES'),
(630, 409005, NULL, NULL, 'IMPORT - EXPORT DES EQUIPEMENTS ET FOURNITURES INDUSTRIELLES ET LEURS CONSOMMABLES'),
(631, 409006, NULL, NULL, 'IMPORT - EXPORT D\'ARTICLES EN PAPIER A USAGE SANITAIRE OU DOMESTIQUE'),
(632, 409007, NULL, NULL, 'IMPORT - EXPORT DE MATERIELS ET PRODUITS DESTINES A LA PREPARATION ET A L\'APPLICATION DES PEINTURES AUTOMOBILES'),
(633, 409009, NULL, NULL, 'IMPORT - EXPORT DE STORES VENITIENS ET CALIFORNIENS'),
(634, 410101, NULL, NULL, 'IMPORT - EXPORT D\'ARTICLES ET MOBILIERS A USAGE DOMESTIQUES ET DE BUREAUX'),
(635, 410102, NULL, NULL, 'IMPORT - EXPORT DE LA MATIERE PREMIERE DESTINEE A LA FABRICATION DE MEUBLES ET ARTICLES D\'AMEUBLEMENTS'),
(636, 410103, NULL, NULL, 'IMPORT - EXPORT DE L\'ARTISANAT ET DE LA MAROQUINERIE TRADITIONNELLE'),
(637, 410104, NULL, NULL, 'IMPORT - EXPORT DES ARTICLES DE DINANDERIE'),
(638, 410105, NULL, NULL, 'IMPORT - EXPORT DE LA LUSTRERIE ET ARTICLES DE DECORATION D\'INTERIEUR'),
(639, 410201, NULL, NULL, 'IMPORT - EXPORT D\'ARTICLES DE LOISIRS ET DE SPORTS'),
(640, 410202, NULL, NULL, 'IMPORT - EXPORT DE JEUX , JOUETS ET JEUX EDUCATIFS'),
(641, 410203, NULL, NULL, 'IMPORT - EXPORT DE LIVRES , DE MANUELS, OUVRAGES ET REVUES SCIENTIFIQUES OU DE CULTURE GENERALE'),
(642, 410204, NULL, NULL, 'IMPORT - EXPORT DE LA PAPETERIE, DE FOURNITURES ET ARTICLES SCOLAIRES, ET ARTICLES POUR LES BEAUX ARTS'),
(643, 410205, NULL, NULL, 'IMPORT - EXPORT DE TABLEAUX ET D\'OEUVRES D\'ART'),
(644, 410206, NULL, NULL, 'IMPORT - EXPORT D\'ARTICLES PUBLICITAIRES ET CADEAUX D\'ENTREPRISES'),
(645, 410301, NULL, NULL, 'IMPORT - EXPORT D\'EMBALLAGES EN TOUTES MATIERES'),
(646, 410302, NULL, NULL, 'IMPORT - EXPORT D\'EQUIPEMENTS, MATERIELS ET FOURNITURES DE SECURITE ET DE PROTECTION'),
(647, 410303, NULL, NULL, 'IMPORT - EXPORT D\'APPAREILS, ARTICLES ET PRODUITS DESTINES A LA FILTRATION OU L\'EPURATION DES LIQUIDES ET DES GAZ'),
(648, 410304, NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS, MACHINES, MATIERE PREMIERE ET ACCESSOIRES DESTINES A LA TRANSFORMATION DU BOIS ET SES DERIVES'),
(649, 410305, NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS, MACHINES, MATIERES PREMIERES, PIECES DETACHEES ET ACCESSOIRES DESTINES A LA FABRICATION ET LA TRANSFORMATION DES METAUX'),
(650, 410306, NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS, MACHINES, PIECES DETACHEES ET ACCESSOIRES DESTINES A LA FABRICATION ET LA TRANSFORMATION DES PLASTIQUES ET CAOUTCHOUCS'),
(651, 410307, NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS, MACHINES, PIECES DETACHEES ET ACCESSOIRES DESTINES A L\'INDUSTRIE AGRO-ALIMENTAIRE'),
(652, 410308, NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS, MACHINES, PIECES DETACHEES ET ACCESSOIRES DESTINES A L\'INDUSTRIE TEXTILE'),
(653, 410309, NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS, MACHINES, PIECES DETACHEES ET ACCESSOIRES DESTINES A LA FABRICATION DES MATIERES CELLULOSIQUES OU LE FINISSAGE DU PAPIER ET CARTON'),
(654, 410310, NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS, MACHINES, PIECES DETACHEES ET ACCESSOIRES DESTINES AU CONDITIONNEMENT DE L\'AIR, TURBINES, CHAUDIERES, GENERATEURS DE GAZ'),
(655, 410311, NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS, MACHINES, PIECES DETACHEES ET ACCESSOIRES DE REFRIGERATION, DE CONGELATION, DE CLIMATISATION, DE BRULERIE ET DE CUISSON'),
(656, 410312, NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS, MACHINES DE LEVAGE ET DE MANUTENTION Y COMPRIS PIECES DETACHEES ET ACCESSOIRES'),
(657, 410313, NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS DE TRAVAUX PUBLICS ET DE BATIMENT Y COMPRIS PIECES DETACHEES ET ACCESSOIRES'),
(658, 410314, NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS AGRICOLES, LEURS PIECES DETACHEES ET ACCESSOIRES'),
(659, 410315, NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS POUR LE TRAVAIL DU CUIR, LEURS PIECES DETACHEES ET ACCESSOIRES'),
(660, 410316, NULL, NULL, 'IMPORT - EXPORT DE VOITURES ET DE TOUS MATERIELS DE TRANSPORT, LEURS PIECES DETACHEES ET ACCESSOIRES'),
(661, 410317, NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS DE TRANSPORT FERROVIAIRES, LEURS PARTIES, PIECES DETACHEES ET ACCESSOIRES'),
(662, 410318, NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS DE NAVIGATION AERIENNE ET SPATLALE LEURS PARTIES, PIECES DETACHEES ET ACCESSOIRES'),
(663, 410319, NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS DE NAVIGATION MARITIME ET FLUVIALE, LEURS PARTIES, PIECES DETACHEES ET ET ACCESSOIRES'),
(664, 410320, NULL, NULL, 'IMPORT - EXPORT D\'ARTICLES D\'HORLOGERIE, DE MOUVEMENTS D\'HORLOGES D\'APPAREILS DE CONTROLE DU TEMPS, LEURS PARTIES, PIECES DETACHEES ET ACCESSOIRES'),
(665, 410321, NULL, NULL, 'IMPORT - EXPORT D\'EQUIPEMENTS ET MATERIELS DES INDUSTRIELS GRAPHIQUES, LEURS PIECES DETACHEES, ACCESSOIRES ET CONSOMMABLES'),
(666, 410322, NULL, NULL, 'IMPORT - EXPORT D\'APPAREILS ET ARTICLES DE REGULATION INDUSTRIELLE'),
(667, 410323, NULL, NULL, 'IMPORT - EXPORT D\'EQUIPEMENTS ET APPAREILS DE TRANSMISSION DE VITESSE'),
(668, 410324, NULL, NULL, 'IMPORT - EXPORT DE CABINES SAHARIENNES'),
(669, 410325, NULL, NULL, 'IMPORT - EXPORT DE MOTEURS ET TURBINES INDUSTRIELLES OU AUTRES Y COMPRIS LEURS PIECES DETACHEES ET LEURS ACCESSOIRES'),
(670, 410326, NULL, NULL, 'IMPORT - EXPORT DE VEHICULES AUTOMOBILES A USAGES SPECIAUX'),
(671, 410327, NULL, NULL, 'IMPORT - EXPORT DE FILMS ET TISSUS POUR TOUS TYPES D\'ENROBAGE'),
(672, 410328, NULL, NULL, 'IMPORT - EXPORT DE BATTERIE A USAGE INDUSTRIEL'),
(673, 410329, NULL, NULL, 'IMPORT - EXPORT D\'ARTICLES DE REVETEMENT D\'INTERIEUR'),
(674, 410330, NULL, NULL, 'IMPORT- EXPORT DES ARMES DE CHASSE'),
(675, 501101, NULL, NULL, 'COMMERCE DE DETAIL D\'AROMES, ESSENCES ET COLORANTS POUR L\'ALIMENTATION'),
(676, 501102, NULL, NULL, 'COMMERCE DE DETAIL DE PRODUITS LAITIERS ET MIEL'),
(677, 501103, NULL, NULL, 'COMMERCE DE DETAIL DE BOISSONS NON ALCOOLISEES A EMPORTER'),
(678, 501104, NULL, NULL, 'COMMERCE DE DETAIL DES FRUITS ET LEGUMES'),
(679, 501105, NULL, NULL, 'COMMERCE DE DETAIL DE FRUITS ET LEGUMES EXERCE EN ETAL'),
(680, 501106, NULL, NULL, 'COMMERCE DE DETAIL DE PLANTES POTAGERES EXERCE EN ETAL'),
(681, 501107, NULL, NULL, 'COMMERCE DE DETAIL DE LA DATTE ET DES FRUITS CONFITS'),
(682, 501108, NULL, NULL, 'COMMERCE DE DETAIL DE LEGUMES SECS ET PRODUITS DE LA MINOTERIE'),
(683, 501109, NULL, NULL, 'COMMERCE DE DETAIL DE LEGUMES CONDIMENTES OU EPLUCHES'),
(684, 501110, NULL, NULL, 'COMMERCE DE DETAIL DE LEGUMES CONDIMENTES OU EPLUCHES, EXERCE ENETAL'),
(685, 501111, NULL, NULL, 'ALIMENTATION GENERALE (EPICERIE)'),
(686, 501112, NULL, NULL, 'COMMERCE DE DETAIL DE L\'ALIMENTATION GENERALE EXERCE ENETAL.'),
(687, 501113, NULL, NULL, 'COMMERCE DE DETAIL DE TABACS, ARTICLES POUR FUMEURS, ARTICLES DEBAZAR ET JOURNAUX'),
(688, 501114, NULL, NULL, 'KIOSQUE A TABACS.'),
(689, 501115, NULL, NULL, 'COMMERCE DE DETAIL DE BOISSONS ALCOOLISEES A EMPORTER'),
(690, 501116, NULL, NULL, 'COMMERCE DE DETAIL DE CAFE'),
(691, 501202, NULL, NULL, 'PATISSERIE .'),
(692, 501203, NULL, NULL, 'CONFISERIE'),
(693, 501204, NULL, NULL, 'DEPOT DE PATISSERIE ET CONFISERIE'),
(694, 501205, NULL, NULL, 'PATISSERIE TRADITIONNELLE'),
(695, 501206, NULL, NULL, 'DEPOT DE PAIN'),
(696, 501207, NULL, NULL, 'COMMERCE DE DETAIL DES PRODUITS POUR LA BOULANGERIE ET PATISSERIE'),
(697, 501301, NULL, NULL, 'COMMERCE DE DETAIL DE VOLAILLES, OEUFS ET LAPINS'),
(698, 501302, NULL, NULL, 'COMMERCE DE DETAIL DE VOLAILLE ET OEUFS EXERCE EN ETAL'),
(699, 501303, NULL, NULL, 'COMMERCE DE DETAIL DE VIANDES, DE VOLAILLES ET OEUFS'),
(700, 501304, NULL, NULL, 'COMMERCE DE DETAIL DE VIANDE DE BOUCHERIE'),
(701, 501305, NULL, NULL, 'COMMERCE DE DETAIL DE VIANDE DE BOUCHERIE EXERCE EN ETAL'),
(702, 501306, NULL, NULL, 'COMMERCE DE DETAIL DE LA VIANDE CHEVALINE'),
(703, 501307, NULL, NULL, 'COMMERCE DE DETAIL D\'ABATS ET VIANDE PREPARES CRUS'),
(704, 501308, NULL, NULL, 'COMMERCE DE DETAIL DE LA CHARCUTERIE'),
(705, 501309, NULL, NULL, 'COMMERCE DE DETAIL DE VIANDES FRAICHES, CONGELEES OU SURGELES'),
(706, 501401, NULL, NULL, 'COMMERCE DE DETAIL DE POISSONS, CRUSTACES ET COQUILLAGES (FRUITS DE MER)'),
(707, 501402, NULL, NULL, 'COMMERCE DE DETAIL DE POISSONS ET CRUSTACES EXERCE EN ETAL'),
(708, 501403, NULL, NULL, 'COMMERCE DE DETAIL DE POISSONS, CRUSTACES ET COQUILLAGES CONGELES OU SURGELETS'),
(709, 502101, NULL, NULL, 'COMMERCE DE DETAIL DE LA PELLETERIE ET DES FOURRURES'),
(710, 502102, NULL, NULL, 'COMMERCE DE DETAIL DE L\'HABILLEMENT, DE LA CHAUSSURE ET TEXTILES'),
(711, 502103, NULL, NULL, 'COMMERCE DE DETAIL DE L\'HABILLEMENT, DE LA CHAUSSURE ET DES TEXTILES EXERCES EN ETAL'),
(712, 502104, NULL, NULL, 'COMMERCE DE DETAIL DE L\'HABILLEMENT, D\'ARTICLES DE BONNETERIE ET DE LA CHAUSSURE.'),
(713, 502105, NULL, NULL, 'COMMERCE DE DETAIL DE L\'HABILLEMENT , DES ARTICLES DE BONNETERIE ET DE LA CHAUSSURE EXERCE EN ETAL'),
(714, 502106, NULL, NULL, 'COMMERCE DE DETAIL DE LAYETTES ET ARTICLES POUR BEBES.'),
(715, 502107, NULL, NULL, 'COMMERCE DE DETAIL DES VETEMENTS USAGES FRIPERIES'),
(716, 502108, NULL, NULL, 'COMMERCE DE DETAIL DE LA MERCERIE, BONNETERIE ET LAYETTES'),
(717, 502109, NULL, NULL, 'COMMERCE DE DETAIL DE LA MERCERIE, BONNETERIE ET LAYETTES EXERCE EN ETAL'),
(718, 502110, NULL, NULL, 'COMMERCE DE DETAIL DE LA MERCERIE'),
(719, 502111, NULL, NULL, 'COMMERCE DE DETAIL DE LA MERCERIE EXERCE EN ETAL'),
(720, 502112, NULL, NULL, 'COMMERCE DE DETAIL DES TISSUS POUR CONFECTION ET AMEUBLEMENT'),
(721, 502113, NULL, NULL, 'COMMERCE DE DETAIL DE TISSUS ET BONNETERIE'),
(722, 502114, NULL, NULL, 'COMMERCE DE DETAIL DE TISSUS DE CONFECTION ET D\'AMEUBLEMENT EXERCE EN ETAL'),
(723, 502115, NULL, NULL, 'COMMERCE DE DETAIL DE LA CHAUSSURE'),
(724, 502116, NULL, NULL, 'COMMERCE DE DETAIL DE LA CHAUSSURE EXERCE EN ETAL'),
(725, 502117, NULL, NULL, 'COMMERCE DE DETAIL DE LA MAROQUINERIE'),
(726, 502118, NULL, NULL, 'COMMERCE DE DETAIL DE LA MAROQUINERIE EXERCE EN ETAL'),
(727, 502201, NULL, NULL, 'COMMERCE DE DETAIL DE LA BIJOUTERIE ET DE L\'HORLOGERIE'),
(728, 502202, NULL, NULL, 'COMMERCE DE DETAIL DE LA BIJOUTERIE TRADITIONNELLE ET DE L\'HORLOGERIE EXERCE EN ETAL'),
(729, 502203, NULL, NULL, 'COMMERCE DE DETAIL DE L\'HORLOGERIE'),
(730, 502204, NULL, NULL, 'COMMERCE DE DETAIL DE MATERIELS, PRODUITS ET MATIERES PREMIERES DESTINES A LA BIJOUTERIE INDUSTRIELLE ET ARTISANALE'),
(731, 502301, NULL, NULL, 'COMMERCE DE DETAIL DES PRODUITS DE PARFUMERIE ET COSMETIQUES'),
(732, 502302, NULL, NULL, 'COMMERCE DE DETAIL DE PRODUITS DE PARFUMERIE ET COSMETIQUES EXERCE EN ETAL'),
(733, 502303, NULL, NULL, 'COMMERCE DE DETAIL DE PRODUITS ET APPAREILS LIES AU CONFORT ET A L\'ENTRETIEN DU CORPS HUMAIN'),
(734, 502304, NULL, NULL, 'COMMERCE DE DETAIL DE PRODUITS DIETETIQUES, NUTRITIONNELS ET DE REGIME'),
(735, 503101, NULL, NULL, 'COMMERCE DE DETAIL DE PETITS ANIMAUX D\'AGREMENT'),
(736, 503102, NULL, NULL, 'FLEURISTE ET COMMERCE DE DETAIL DE PLANTES EN TOUS GENRES'),
(737, 503103, NULL, NULL, 'FLEURISTE EN KIOSQUE'),
(738, 503104, NULL, NULL, 'COMMERCE DE DETAIL DE MEUBLES ET ARTICLES D\'AMEUBLEMENT'),
(739, 503105, NULL, NULL, 'COMMERCE DE DETAIL D\'ARTICLES DE REVETEMENT D\'INTERIEUR'),
(740, 503106, NULL, NULL, 'COMMERCE DE DETAIL DE MATELAS'),
(741, 503107, NULL, NULL, 'COMMERCE DE DETAIL D\'ARTICLES DE LUSTRERIE ET DE DECORATION D\'INTERIEUR'),
(742, 503201, NULL, NULL, 'COMMERCE DE DETAIL DE TISSUS ENDUITS'),
(743, 503202, NULL, NULL, 'COMMERCE DE DETAIL DE LA DROGUERIE, PEINTURE, QUINCAILLERIE, ARTICLES DE MENAGE, PRODUITS D\'HYGIENE ET D\'ENTRETIEN DOMESTIQUE ET PROFESSIONNEL'),
(744, 503203, NULL, NULL, 'COMMERCE DE DETAIL DE LA DROGUERIE, PEINTURE, QUINCAILLERIE ET ARTICLES DE MENAGE EXERCE EN ETAL'),
(745, 503204, NULL, NULL, 'COMMERCE DE DETAIL DE LA DROGUERIE'),
(746, 503205, NULL, NULL, 'COMMERCE DE DETAIL D\'ARTICLES DE MENAGE ET USTENSILES DE CUISINE'),
(747, 503206, NULL, NULL, 'COMMERCE DE DETAIL DES ARTICLES DE MENAGE EXERCE EN ETAL'),
(748, 503207, NULL, NULL, 'COMMERCE DE DETAIL DE METAUX ET PRODUITS METALLIQUES ET SIDERURGIQUES'),
(749, 503301, NULL, NULL, 'COMMERCE DE DETAIL DE LA LAINE BRUTE, DE FILS DE LAINE ET FIBRANNE'),
(750, 503302, NULL, NULL, 'COMMERCE DE DETAIL DE GAZ BUTANE ,CHARBON DE BOIS ET AUTRES COMBUSTIBLES SOLIDES, LIQUIDES OU GAZEUX'),
(751, 503303, NULL, NULL, 'COMMERCE DE DETAIL D\'ARTICLES DE VANNERIE ET DE SPARTERIE'),
(752, 503304, NULL, NULL, 'COMMERCE DE DETAIL DES ARTICLES DE VANNERIE SPARTERIE, EXERCE ENETAL'),
(753, 503305, NULL, NULL, 'COMMERCE DE DETAIL D\'APPAREILS SANITAIRES ET DE CHAUFFAGE'),
(754, 503306, NULL, NULL, 'COMMERCE DE DETAIL DE L\'ELECTRO-MENAGER ET DES APPAREILS DE REPRODUCTION DU SON ET DE L\'IMAGE'),
(755, 503307, NULL, NULL, 'COMMERCE DE DETAIL DE VEHICULES AUTOMOBILES D\'OCCASION'),
(756, 503308, NULL, NULL, 'COMMERCE DE DETAIL DE CYCLES ET MOTOCYCLES'),
(757, 503309, NULL, NULL, 'COMMERCE DE DETAIL DE MACHINES A COUDRE ET A TRICOTER'),
(758, 503310, NULL, NULL, 'COMMERCE DE DETAIL DE TAPIS'),
(759, 503311, NULL, NULL, 'COMMERCE DE DETAIL DE TAPIS, EXERCE EN ETAL'),
(760, 503312, NULL, NULL, 'COMMERCE DE DETAIL D\'OBJETS D\'ARTS ET DE COLLECTION (ANTIQUAIRE)'),
(761, 503313, NULL, NULL, 'COMMERCE DE DETAIL DES ARTICLES DE POTERIE'),
(762, 503314, NULL, NULL, 'COMMERCE DE DETAIL DES PRODUITS DE L\'ARTISANAT'),
(763, 503315, NULL, NULL, 'COMMERCE DE DETAIL DE LA MAROQUINERIE TRADITIONNELLE'),
(764, 503316, NULL, NULL, 'COMMERCE DE DETAIL DE LA MAROQUINERIE TRADITIONNELLE , EXERCE EN ETAL'),
(765, 503317, NULL, NULL, 'COMMERCE DE DETAIL DES ARTICLES DE DINANDERIE'),
(766, 503318, NULL, NULL, 'COMMERCE DE DETAIL DE VEHICULES AUTOMOBILES NEUFS'),
(767, 504101, NULL, NULL, 'COMMERCE DE DETAIL DE MATERIAUX DE CONSTRUCTION'),
(768, 504102, NULL, NULL, 'COMMERCE DE DETAIL DE QUINCAILLERIE DU BATIMENT, VITRERIE, PEINTURE ET BOIS'),
(769, 504103, NULL, NULL, 'COMMERCE DE DETAIL DU BOIS ET LIEGE POUR LA MENUISERIE'),
(770, 504104, NULL, NULL, 'COMMERCE DE DETAIL DE LA QUINCAILLERIE'),
(771, 504105, NULL, NULL, 'COMMERCE DE DETAIL DE LA QUINCAILLERIE EXERCE EN ETAL'),
(772, 504106, NULL, NULL, 'COMMERCE DE DETAIL DE VITRERIE ET MIROITERIE'),
(773, 504107, NULL, NULL, 'COMMERCE DE DETAIL DE PEINTURES ET VERNIS'),
(774, 504108, NULL, NULL, 'COMMERCE DE DETAIL DE PEINTURES ET VERNIS EXERCE EN ETAL'),
(775, 504201, NULL, NULL, 'COMMERCE DE DETAIL DE FOURNITURES POUR L\'ELECTRICITE'),
(776, 504202, NULL, NULL, 'COMMERCE DE DETAIL DES ARTICLES DE REVETEMENT D\'INTERIEUR'),
(777, 504203, NULL, NULL, 'COMMERCE DE DETAIL DE MATERIELS, EQUIPEMENTS ET FOURNITURES DE PROTECTION ET DE SECURITE, LEURS PIECES DETACHEES ET ACCESSOIRES'),
(778, 504204, NULL, NULL, 'COMMERCE DE DETAIL DE MATERIELS ET OUTILLAGES POUR BTP'),
(779, 505001, NULL, NULL, 'COMMERCE DE DETAIL DES EQUIPEMENTS ET FOURNITURES THERMIQUES'),
(780, 505002, NULL, NULL, 'COMMERCE DE DETAIL DE MATERIEL ET FOURNITURES POUR LES CANTINES ET GRANDES CUISINES DE COLLECTIVITES'),
(781, 505003, NULL, NULL, 'COMMERCE DE DETAIL DE MATERIEL ET FOURNITURES POUR BOULANGERIE, PATISSERIE, CONFISERIE'),
(782, 505004, NULL, NULL, 'COMMERCE DE DETAIL DE MATERIEL ET FOURNITURES POUR DEBITS DE BOISSONS ET SALONS DE DEGUSTATION'),
(783, 505005, NULL, NULL, 'COMMERCE DE DETAIL D\'EQUIPEMENT ET MATERIEL POUR BLANCHISSERIE'),
(784, 505006, NULL, NULL, 'COMMERCE DE DETAIL DES FOURNITURES POUR CORDONNERIE'),
(785, 505007, NULL, NULL, 'COMMERCE DE DETAIL D\'ARTICLES D\'EMBALLAGE'),
(786, 505008, NULL, NULL, 'COMMERCE DE DETAIL D\'EQUIPEMENTS, D\'APPAREILS ET INSTRUMENTS DE PESAGE , MESURAGE Y COMPRIS LEURS ACCESSOIRES , PIECES DETACHEES ET LEURS CONSOMMABLES'),
(787, 505009, NULL, NULL, 'COMMERCE DE DETAIL D\'APPAREILS MEDICAUX ET CHIRURGICAUX'),
(788, 505010, NULL, NULL, 'COMMERCE DE DETAIL D\'EQUIPEMENTS ET FOURNITURES INDUSTRIELLES'),
(789, 505011, NULL, NULL, 'COMMERCE DE DETAIL D\'APPAREILS, ARTICLES ET PRODUITS DESTINES A LA FILTRATION OU L\'EPURATION DES LIQUIDES ET DES GAZ'),
(790, 505012, NULL, NULL, 'COMMERCE DE DETAIL DES EQUIPEMENTS ET MATERIELS POUR LA BLANCHISSERIE, EQUIPEMENTS POUR HOTELS, CAFES, RESTAURANTS ET MAGASINS'),
(791, 505013, NULL, NULL, 'COMMERCE DE DETAIL DE REACTIFS DE LABORATOIRE'),
(792, 505014, NULL, NULL, 'COMMERCE DE DETAIL D\'EQUIPEMENTS ET MATERIELS UTILISES POUR LA PRODUCTION D\'EMBALLAGE ET LE CONDITIONNEMENT'),
(793, 506001, NULL, NULL, 'COMMERCE DE DETAIL D\'OUTILS ET OUTILLAGES PROFESSIONNELS POUR MACHINES-OUTILS'),
(794, 506002, NULL, NULL, 'COMMERCE DE DETAIL D\'OUTILLAGE ET AUTRES MATERIELS POUR L\'ENTRETIEN ET LES REPARATIONS MECANIQUES'),
(795, 506003, NULL, NULL, 'COMMERCE DE DETAIL DE PIECES DETACHEES ET ACCESSOIRES POUR VEHICULES'),
(796, 506004, NULL, NULL, 'COMMERCE DE DETAIL DE ROULEMENTS A BILLE'),
(797, 506005, NULL, NULL, 'COMMERCE DE DETAIL DE PIECES DETACHEES ET ACCESSOIRES POUR CYCLES ET MOTOCYCLES'),
(798, 506006, NULL, NULL, 'COMMERCE DE DETAIL DE PNEUMATIQUES'),
(799, 506007, NULL, NULL, 'COMMERCE DE DETAIL DE BATTERIES'),
(800, 506008, NULL, NULL, 'COMMERCE DE DETAIL DE LUBRIFIANTS ET GRAISSES INDUSTRIELLES'),
(801, 507101, NULL, NULL, 'COMMERCE DE DETAIL DES ARMES DE CHASSE (ARMURERIE)'),
(802, 507102, NULL, NULL, 'COMMERCE DE DETAIL DE BARQUES ET AUTRES EMBARCATIONS'),
(803, 507103, NULL, NULL, 'COMMERCE DE DETAIL DE JOUETS ET JEUX DE SOCIETE'),
(804, 507104, NULL, NULL, 'COMMERCE DE DETAIL DE FOURNITURE ET ARTICLES DE SPORT ET AUTRES LOISIRS'),
(805, 507201, NULL, NULL, 'COMMERCE DE DETAIL DE MACHINES, MATERIEL ET MOBILIER DE BUREAU'),
(806, 507202, NULL, NULL, 'VENTE AU DETAIL DE MATERIEL INFORMATIQUE : ORDINATEURS, PERIPHERIQUES, CONSOMMABLES ET ACCESSOIRES.'),
(807, 507203, NULL, NULL, 'COMMERCE DE DETAIL DE LA LIBRAIRIE ET PAPETERIE'),
(808, 507204, NULL, NULL, 'COMMERCE DE DETAIL DE LA LIBRAIRE ET PAPETERIE , EXERCE EN ETAL'),
(809, 507205, NULL, NULL, 'COMMERCE DE DETAIL DE LIVRES D\'OCCASION (BOUQUINERIE)'),
(810, 507206, NULL, NULL, 'COMMERCE DE DETAIL DE MATERIEL, ARTICLES ET FOURNITURES POUR LA CULTURE, L\'ENSEIGNEMENT ET LES COLLECTIVITES'),
(811, 507207, NULL, NULL, 'COMMERCE DE DETAIL DE FOURNITURES ET ARTICLES POUR LES ACTIVITES DES BEAUX ARTS'),
(812, 507208, NULL, NULL, 'COMMERCE DE DETAIL DE MATERIEL SCIENTIFIQUE ET DIDACTIQUE D\'ENSEIGNEMENT'),
(813, 507209, NULL, NULL, 'COMMERCE DE DETAIL DE TABLEAUX ET D\'OEUVRE D\'ART'),
(814, 507210, NULL, NULL, 'COMMERCE DE DETAIL DE MATERIELS DE TELEPHONE ET DE TELECOMMUNICATION'),
(815, 507211, NULL, NULL, 'COMMERCE DE DETAIL DE TOUS MATERIELS ET EQUIPEMENTS LIES AU DOMAINE DE L\'ELECTRICITE ET L\'ELECTRONIQUE'),
(816, 507301, NULL, NULL, 'COMMERCE DE DETAIL DES APPAREILS PHOTOGRAPHIQUES ET CINEMATOGRAPHIQUES'),
(817, 507302, NULL, NULL, 'COMMERCE DE DETAIL DE MATERIEL, ARTICLES ET FOURNITURES DESTINES AUX ACTIVITES ARTISTIQUES'),
(818, 507303, NULL, NULL, 'COMMERCE DE DETAIL DES INSTRUMENTS DE MUSIQUE.'),
(819, 507304, NULL, NULL, 'COMMERCE DE DETAIL DE DISQUES ET CASSETTES (DISQUAIRE)'),
(820, 508001, NULL, NULL, 'COMMERCE DE DETAIL DE MATERIEL HYDRAULIQUE'),
(821, 508002, NULL, NULL, 'COMMERCE DE DETAIL DE SEMENCES ET PLANTES'),
(822, 508003, NULL, NULL, 'COMMERCE DE DETAIL DE SEMENCES ET PLANTES, EXERCE EN ETAL'),
(823, 508004, NULL, NULL, 'COMMERCE DE DETAIL DES ALIMENTS DU BETAIL'),
(824, 508005, NULL, NULL, 'COMMERCE DE DETAIL DE MATERIEL ET EQUIPEMENT AVICOLES'),
(825, 508006, NULL, NULL, 'COMMERCE DE DETAIL DE MATERIEL ET FOURNITURES POUR L\'AGRICULTURE'),
(826, 508007, NULL, NULL, 'COMMERCE DE DETAIL DE VIANDES DESTINEES A L\'ALIMENTATION DES ANIMAUX DOMESTIQUES'),
(827, 509001, NULL, NULL, 'COMMERCE DE DETAIL DE BROCANTERIE'),
(828, 509002, NULL, NULL, 'COMMERCE DE VIEUX METAUX NON FERREUX'),
(829, 509003, NULL, NULL, 'COMMERCE DE RECUPERATION DE VIEUX METAUX, BROCANTERIE ET DECHETS INDUSTRIELS DIVERS'),
(830, 509004, NULL, NULL, 'COMMERCE DE VIEUX FERS FERRAILLE ET RIBLONS'),
(831, 509005, NULL, NULL, 'COMMERCE DE RECUPERATION DE PAPIER CARTONS ET CHIFFONS'),
(832, 509006, NULL, NULL, 'COMMERCEDE TESSONS ET DEBRIS DE VERRE'),
(833, 509007, NULL, NULL, 'COMMERCE DE DECHETS D\'ANIMAUX ET VEGETAUX'),
(834, 509008, NULL, NULL, 'COMMERCE DE DECHETS INDUSTRIELS ET DE PRODUITS DIVERS N D A.'),
(835, 509009, NULL, NULL, 'COMMERCE DE DETAIL DE TOUS APPAREILS ET MACHINES PROFESSIONNELS OU DOMESTIQUES D\'OCCASIONS'),
(836, 510001, NULL, NULL, 'COMMERCE DE DETAIL EN MAGASIN D\'UNE SURFACE DE VENTE COMPRISE ENTRE 120 ET 400 M2 (SUPERETTE)'),
(837, 510002, NULL, NULL, 'COMMERCE DE DETAIL EN MAGASIN D\'UNE SURFACE DE VENTE COMPRISE ENTRE 400 ET 2500 M2 (SUPERMARCHE)'),
(838, 510003, NULL, NULL, 'COMMERCE DE DETAIL EN MAGASIN D\'UNE SURFACE DE VENTE EGALE OU SUPERIEURE A 2500 M2 (HYPERMARCHE)'),
(839, 510004, NULL, NULL, 'MAGASIN OU COMPTOIR DE VENTE DE MARCHANDISES SOUS DOUANES'),
(840, 510005, NULL, NULL, 'COMMERCE MULTIPLE DE DETAIL EXERCE EN ZONES RURALES ET ELOIGNEES ET DU GRAND SUD'),
(841, 511101, NULL, NULL, 'MARCHAND AMBULANT EN ALIMENTATION GENERALE AU NIVEAU DES MARCHES COMMUNAUX, PLACES ET EMPLACEMENTS AMENAGES OU AFFECTES A CET EFFET'),
(842, 511102, NULL, NULL, 'MARCHAND AMBULANT D\'EPICES ET CONDIMENTS AU NIVEAU DES MARCHES COMMUNAUX.'),
(843, 511103, NULL, NULL, 'MARCHAND AMBULANT DE BOISSONS AU NIVEAU DES MARCHES COMMUNAUX.'),
(844, 511104, NULL, NULL, 'MARCHAND AMBULANT EN CONFISERIE AU NIVEAU DES MARCHES COMMUNAUX'),
(845, 511105, NULL, NULL, 'MARCHAND AMBULANT DE CREME GLACEE'),
(846, 511106, NULL, NULL, 'MARCHAND AMBULANT DE FRUITS ET LEGUMES AU NIVEAU DES MARCHES COMMUNAUX.'),
(847, 511107, NULL, NULL, 'MARCHAND AMBULANT DE POISSONS AU NIVEAU DES MARCHES COMMUNAUX'),
(848, 511108, NULL, NULL, 'MARCHAND AMBULANT EN BOUCHERIE AU NIVEAU DES MARCHES COMMUNAUX'),
(849, 511109, NULL, NULL, 'MARCHAND AMBULANT DE VOLAILLE ET LAPINS AU NIVEAU DES MARCHES COMMUNAUX.'),
(850, 511110, NULL, NULL, 'MARCHAND AMBULANT DE VIANDE ET POISSONS.'),
(851, 511111, NULL, NULL, 'MARCHAND AMBULANT EN HERBORISTERIE AU NIVEAU DES MARCHES COMMUNAUX'),
(852, 511112, NULL, NULL, 'MARCHAND AMBULANT DE SEMENCES ET DE PLANTS AU NIVEAU DES MARCHES COMMUNAUX'),
(853, 511113, NULL, NULL, 'MARCHAND AMBULANT D\'EFFETS VESTIMENTAIRES AU NIVEAU DES MARCHES COMMUNAUX'),
(854, 511114, NULL, NULL, 'MARCHAND AMBULANT DE TISSUS AU NIVEAU DES MARCHES COMMUNAUX'),
(855, 511115, NULL, NULL, 'MARCHAND AMBULANT DE LAINE AU NIVEAU DES MARCHES COMMUNAUX'),
(856, 511116, NULL, NULL, 'MARCHAND AMBULANT DE PRODUITS COSMETIQUES ET DE PARFUMERIE, AUNIVEAU DES MARCHES COMMUNAUX'),
(857, 511117, NULL, NULL, 'MARCHAND AMBULANT DE CHAUSSURES AU NIVEAU DES MARCHES COMMUNAUX'),
(858, 511118, NULL, NULL, 'MARCHAND AMBULANT EN MAROQUINERIE AU NIVEAU DES MARCHES COMMUNAUX, PLACES ET EMPLACEMENTS SPECIALEMENT AMENAGES OU AFFECTES A CET EFFET'),
(859, 511119, NULL, NULL, 'MARCHAND AMBULANT D\'ARTICLES DE MENAGE AU NIVEAU DES MARCHES COMMUNAUX, PLACES ET EMPLACEMENTS SPECIALEMENT AMENAGES OU AFFECTES A CET EFFET'),
(860, 511120, NULL, NULL, 'MARCHAND AMBULANT DE NATTES ET ARTICLES EN ALFA, AU NIVEAU DES MARCHES COMMUNAUX, PLACES ET EMPLACEMENTS AMENAGES OU AFFECTES CET EFFET'),
(861, 511121, NULL, NULL, 'MARCHAND AMBULANT DE MEUBLES AU NIVEAU DES MARCHES COMMUNAUX, PLACES ET EMPLACEMENTS SPECIALEMENT AMENAGES OU AFFECTES A CET EFFET'),
(862, 511122, NULL, NULL, 'MARCHAND AMBULANT EN BOURRELLERIE, SELLERIE AU NIVEAU DES MARCHES COMMUNAUX, PLACES ET EMPLACEMENTS SPECIALEMENT AMENAGES OU AFFECTES A CET EFFET'),
(863, 511123, NULL, NULL, 'MARCHAND AMBULANT EN QUINCAILLERIE AU NIVEAU DES MARCHES COMMUNAUX ,PLACES ET EMPLACEMENTS SPECIALEMENT AMENAGES OU AFFECTES A CET EFFET'),
(864, 511124, NULL, NULL, 'MARCHAND AMBULANT EN DROGUERIE AU NIVEAU DES MARCHES COMMUNAUX, PLACES ET EMPLACEMENTS SPECIALEMENT AMENAGES OU AFFECTES A CET EFFET'),
(865, 511125, NULL, NULL, 'MARCHAND AMBULANT DE JOUETS AU NIVEAU DES MARCHES COMMUNAUX, PLACES ET EMPLACEMENTS SPECIALEMENT AMENAGES OU AFFECTES A CET EFFET'),
(866, 511126, NULL, NULL, 'MARCHAND AMBULANT DE DISQUES ET CASSETTES AU NIVEAU DES MARCHES COMMUNAUX, PLACES ET EMPLACEMENTS AMENAGES OU AFFECTES A CET EFFET'),
(867, 511127, NULL, NULL, 'MARCHAND AMBULANT DE LIVRES NEUFS ET D\'OCCASION AU NIVEAU DES MARCHES COMMUNAUX, PLACES ET EMPLACEMENTS SPECIALEMENT AMENAGES A CET EFFET'),
(868, 511128, NULL, NULL, 'MARCHAND AMBULANT DE PIECES DETACHEES DE CYCLES ET MOTOCYCLES AU NIVEAU DES MARCHES COMMUNAUX, PLACES ET EMPLACEMENTS SPECIALEMENT AMENAGES OU AFFECTES A CET EFFET'),
(869, 511129, NULL, NULL, 'MARCHAND AMBULANT DE PIECES DETACHEES ET ACCESSOIRES POUR L\'ENTRETIEN ET LES REPARATIONS MECANIQUES'),
(870, 511130, NULL, NULL, 'MARCHAND AMBULANT DE PNEUMATIQUES'),
(871, 511131, NULL, NULL, 'MARCHAND AMBULANT DE LIBRAIRIE ET PAPETERIE'),
(872, 511132, NULL, NULL, 'MARCHAND AMBULANT D\'EMBALLAGES EN TOUTES MATIERES'),
(873, 511133, NULL, NULL, 'MARCHAND AMBULANT DE LA BROCANTERIE'),
(874, 511134, NULL, NULL, 'MARCHAND AMBULANT D\'ALIMENTS DE BETAIL ET VOLAILLE'),
(875, 511135, NULL, NULL, 'MARCHAND AMBULANT EN MATERIAUX DE CONSTRUCTION'),
(876, 511136, NULL, NULL, 'MARCHAND AMBULANT DE BETAIL'),
(877, 511137, NULL, NULL, 'MARCHAND AMBULANT DE LA MERCERIE'),
(878, 511138, NULL, NULL, 'MARCHANT AMBULANT D\'ARTICLES POUR FUMEURS, ARTICLES DE BAZAR ET JOURNAUX AU NIVEAU DES MARCHES COMMUNAUX'),
(879, 511139, NULL, NULL, 'MARCHAND AMBULANT DE PIECES DE RECHANGE ELECTRIQUES, ELECTRONIQUES ET HYDRAULIQUES'),
(880, 511140, NULL, NULL, 'MARCHAND AMBULANT DES PRODUITS ET MATERIELS LIES AU DOMAINE DE LA PECHE'),
(881, 511141, NULL, NULL, 'MARCHAND AMBULANT DE PRODUITS PHYTOSANITAIRES AU NIVEAU DES MARCHES COMMUNAUX'),
(882, 511142, NULL, NULL, 'MARCHAND AMBULANT DES APPAREILS PHOTOGRAPHIQUES ET CINEMATOGRAPHIQUES AU NIVEAU DES MARCHES COMMUNAUX'),
(883, 511143, NULL, NULL, 'MARCHAND AMBULANT D\'ARTICLES DE LUSTRERIE ET DE DECORATION D\'INTERIEUR AU NIVEAU DES MARCHES COMMUNAUX'),
(884, 511201, NULL, NULL, 'REMOULEUR AMBULANT'),
(885, 511202, NULL, NULL, 'RETAMEUR AMBULANT'),
(886, 511203, NULL, NULL, 'VITRIER AMBULANT'),
(887, 511204, NULL, NULL, 'PLOMBIER AMBULANT'),
(888, 511205, NULL, NULL, 'PHOTOGRAPHE AMBULANT'),
(889, 511206, NULL, NULL, 'MATELASSIER AMBULANT'),
(890, 511207, NULL, NULL, 'CALLIGRAPHE AMBULANT'),
(891, 511208, NULL, NULL, 'RESTAURATION MOBILE'),
(892, 511209, NULL, NULL, 'ATTRACTIONS FORAINES'),
(893, 511210, NULL, NULL, 'CORDONNIER AMBULANT'),
(894, 511211, NULL, NULL, 'MARECHAL FERRANT AMBULANT AU NIVEAU DES MARCHES COMMUNAUX'),
(895, 512101, NULL, NULL, 'COMMERCE DE DETAIL DE PRODUITS PARA-PHARMACEUTIQUES, APPAREILS, MATERIELS ET INSTRUMENTS MEDICO-CHIRURGICAUX LEURS ACCESSOIRES, PIECES DETACHEES ET CONSOMMABLES'),
(896, 512102, NULL, NULL, 'COMMERCE DE DETAIL DE LUNETTERIE NON OPTIQUE'),
(897, 513101, NULL, NULL, 'COMMERCE DE DETAIL D\'EMBLEMES ET DE PORTRAITS'),
(898, 513102, NULL, NULL, 'COMMERCE DE DETAIL DE FORME D\'OCCASION EN BOIS ET EN PLASTIQUE PROVENANT DE LA RECUPERATION DES FABRIQUES DE CHAUSSURES'),
(899, 513103, NULL, NULL, 'COMMERCE DE DETAIL DE CHUTES DE CUIR, DAIM ET AUTRES MATIERES PROVENANT DES TANNERIES ET FABRIQUES DE CHAUSSURES'),
(900, 513104, NULL, NULL, 'COMMERCE DE DETAIL DE BIENS D\'OCCASION EN MAGASIN'),
(901, 601101, NULL, NULL, 'HOTEL AVEC RESTAURANT ( TOURISTIQUE OU NON)'),
(902, 601102, NULL, NULL, 'HOTELS DE TOURISME SANS RESTAURANT'),
(903, 601103, NULL, NULL, 'AUBERGES DE JEUNESSE ET REFUGES A BUT LUCRATIF'),
(904, 601104, NULL, NULL, 'EXPLOITATION DE TERRAINS DE CAMPING'),
(905, 601105, NULL, NULL, 'VILLAGES ET CENTRES DE VACANCES'),
(906, 601106, NULL, NULL, 'INSTALLATION POUR HEBERGEMENT COLLECTIF NON TOURISTIQUE'),
(907, 601107, NULL, NULL, 'PENSION DE FAMILLE'),
(908, 601108, NULL, NULL, 'HOTEL, BAR, RESTAURANT'),
(909, 601109, NULL, NULL, 'MOTEL'),
(910, 601110, NULL, NULL, 'DORTOIR'),
(911, 601201, NULL, NULL, 'RESTAURATION COMPLETE (RESTAURANT)'),
(912, 601202, NULL, NULL, 'RESTAURATION RAPIDE (FAST - FOOD)'),
(913, 601203, NULL, NULL, 'RESTAURANT, CAFE (RELAIS ROUTIERS)'),
(914, 601204, NULL, NULL, 'CREMERIE, GLACES ET SORBETS'),
(915, 601205, NULL, NULL, 'ROTISSERIE'),
(916, 601206, NULL, NULL, 'KIOSQUE A BOISSONS, A BEIGNETS ET A GLACES'),
(917, 601207, NULL, NULL, 'CAFE - RESTAURANT'),
(918, 601208, NULL, NULL, 'COLLECTEUR DE LINGE'),
(919, 601209, NULL, NULL, 'BAR RESTAURANT'),
(920, 601301, NULL, NULL, 'CAFE'),
(921, 601302, NULL, NULL, 'DEBITS DE BOISSONS ALCOOLISEES'),
(922, 601303, NULL, NULL, 'SALON DE THE'),
(923, 601304, NULL, NULL, 'EXPLOITATION DE DISTRIBUTEURS AUTOMATIQUES DE CAFE ET DE BOISSONS'),
(924, 601401, NULL, NULL, 'RAVITAILLEMENT DE BASES DE VIE'),
(925, 601402, NULL, NULL, 'TRAITEUR'),
(926, 601403, NULL, NULL, 'AVITAILLEMENT'),
(927, 602101, NULL, NULL, 'PHARMACIE'),
(928, 602102, NULL, NULL, 'OPTICIEN LUNETIER'),
(929, 602103, NULL, NULL, 'ACTIVITES PRIVEES D\'HOSPITALISATION (CLINIQUES ET CENTRES DE SOINS SPECIALISES)'),
(930, 602104, NULL, NULL, 'SERVICE PRIVE D\'AMBULANCES'),
(931, 602105, NULL, NULL, 'LABORATOIRE D\'ANALYSES'),
(932, 602106, NULL, NULL, 'PROTHESE MEDICALE'),
(933, 602107, NULL, NULL, 'ACTIVITES THERMALES ET DE THALASSOTHERAPIE'),
(934, 602108, NULL, NULL, 'HERBORISTE'),
(935, 602109, NULL, NULL, 'SERVICES FUNERAIRES'),
(936, 602110, NULL, NULL, 'IMAGERIE MEDICALE'),
(937, 602111, NULL, NULL, 'MECANICIEN DENTISTE ( PROTHESE DENTAIRE)'),
(938, 602112, NULL, NULL, 'INSTALLATION ET AMENAGEMENT DE TOUTES COMMODITES DESTINEES AUX PERSONNES HANDICAPEES'),
(939, 602201, NULL, NULL, 'DRESSAGE ET TOILETTAGE D\'ANIMAUX DE COMPAGNIE ET DE CHIENS'),
(940, 602202, NULL, NULL, 'ETABLISSEMENT DE DETENTION D\'ANIMAUX NON DOMESTIQUES, PARC ZOOLOGIQUE'),
(941, 602203, NULL, NULL, 'DRESSAGE DES ANIMAUX POUR LES COURSES'),
(942, 603001, NULL, NULL, 'GARAGES'),
(943, 603002, NULL, NULL, 'AIRE DE STATIONNEMENT AMENAGE (PARKING)'),
(944, 603003, NULL, NULL, 'LOCATION DE SALLES'),
(945, 603004, NULL, NULL, 'LOCATION DE VEHICULES AVEC OU SANS CHAUFFEUR'),
(946, 603005, NULL, NULL, 'LOCATION DE BATEAUX DE PLAISANCE ET BARQUES'),
(947, 603006, NULL, NULL, 'LOCATION D\'ENGINS, MACHINES ET EQUIPEMENTS AGRICOLES'),
(948, 603007, NULL, NULL, 'LOCATION D\'ENGINS ET MATERIELS POUR LE BATIMENT ET TRAVAUX PUBLICS'),
(949, 603008, NULL, NULL, 'LOCATION D\'EQUIPEMENT INFORMATIQUEET DE BUREAU'),
(950, 603009, NULL, NULL, 'LOCATION DE MACHINES ET EQUIPEMENTS DIVERS'),
(951, 603010, NULL, NULL, 'LOCATION DE MATERIEL DE PESAGE'),
(952, 603011, NULL, NULL, 'LOCATION DE CYCLES ET MOTOCYCLES'),
(953, 603012, NULL, NULL, 'LOCATION DE MATERIEL ET D\'EQUIPEMENT DE CAMPING'),
(954, 603013, NULL, NULL, 'LOCATION DE MATERIEL POUR FETES ET SPECTACLES'),
(955, 603014, NULL, NULL, 'LOCATIONS DIVERSES, NON DETERMINEES AILLEURS'),
(956, 604101, NULL, NULL, 'TRANSPORT COLLECTIF DE VOYAGEURS EN ZONES RURALES'),
(957, 604102, NULL, NULL, 'TRANSPORT COLLECTIF DE VOYAGEURS EN ZONES URBAINES, PERIPHERIQUES ET INTER-WILAYA'),
(958, 604103, NULL, NULL, 'ENTREPRISE DE GESTION DE TAXIS'),
(959, 604104, NULL, NULL, 'AUTRES TRANSPORTS PARTICULIERS DE VOYAGEURS'),
(960, 604105, NULL, NULL, 'TRANSPORT SUR TOUTES DISTANCES DE MARCHANDISES'),
(961, 604106, NULL, NULL, 'TRANSPORT ET LIVRAISON, SOUS FROID,DE PRODUITS ET DENREES ALIMENTAIRES'),
(962, 604107, NULL, NULL, 'ENTREPRISE D\'APPROVISIONNEMENT EN EQUIPEMENTS, MATERIELS ET PRODUITS ALIMENTAIRES, CAFES, RESTAURANTS ET COLLECTIVITES'),
(963, 604108, NULL, NULL, 'EXPLOITATION DE LIGNES DE TRANSPORT TELEPHERIQUE'),
(964, 604109, NULL, NULL, 'TRANSPORT ET DISTRIBUTION DE TOUTES MARCHANDISES'),
(965, 604110, NULL, NULL, 'TRANSPORT PUBLIC ROUTIER NATIONAL ET INTERNATIONAL DE VOYAGEURS'),
(966, 604111, NULL, NULL, 'TRANSPORT ET DISTRIBUTION DE PRODUITS PETROLIERS'),
(967, 604112, NULL, NULL, 'TRANSPORT ET LIVRAISON DU GPL'),
(968, 604201, NULL, NULL, 'TRANSPORT FERROVIAIRE DE VOYAGEURS'),
(969, 604202, NULL, NULL, 'TRANSPORT FERROVIAIRE DE MARCHANDISES'),
(970, 604301, NULL, NULL, 'TRANSPORT AERIEN DE PERSONNES'),
(971, 604302, NULL, NULL, 'TRANSPORT AERIEN DE MARCHANDISES'),
(972, 604303, NULL, NULL, 'SERVICES AERIENS DE TRANSPORT'),
(973, 604304, NULL, NULL, 'SERVICES AERIENS POUR L\'AGRICULTURE'),
(974, 604401, NULL, NULL, 'TRANSPORT MARITIME DE VOYAGEURS'),
(975, 604402, NULL, NULL, 'TRANSPORT MARITIME DE MARCHANDISES'),
(976, 604403, NULL, NULL, 'CABOTAGE MARITIME'),
(977, 604404, NULL, NULL, 'SERVICES DE VOYAGES MARITIMES'),
(978, 604501, NULL, NULL, 'TRANSPORT PAR PIPE-LINE ET AUTRES CONDUITES'),
(979, 604601, NULL, NULL, 'DEMENAGEMENT TOUTES DESTINATIONS (ENTREPRISE)'),
(980, 604602, NULL, NULL, 'MANUTENTION'),
(981, 604603, NULL, NULL, 'ENTREPOSAGE FRIGORIFIQUE'),
(982, 604604, NULL, NULL, 'STOCKAGE DE MARCHANDISES'),
(983, 604605, NULL, NULL, 'MAGASINS GENERAUX (STOCKAGE SOUS DOUANE)'),
(984, 604606, NULL, NULL, 'GESTION D\'INFRASTRUCTURES DE TRANSPORT TERRESTRE'),
(985, 604607, NULL, NULL, 'ENTREPRISE DE SERVICES PORTUAIRES ET CONSIGNATAIRES DE NAVIRES ET DE MARCHANDISES'),
(986, 604608, NULL, NULL, 'SERVICES AEROPORTUAIRES'),
(987, 604609, NULL, NULL, 'AFFRETEMENT DE MOYENS DE TRANSPORTS DE MARCHANDISES ET DE VOYAGEURS'),
(988, 604610, NULL, NULL, 'REMORQUAGE ET SAUVETAGE DE NAVIRES'),
(989, 604611, NULL, NULL, 'STATIONS SERVICES'),
(990, 604612, NULL, NULL, 'AUTO-ECOLE'),
(991, 604613, NULL, NULL, 'ECOLES DE CONDUITE'),
(992, 604614, NULL, NULL, 'COURTIER DE FRET'),
(993, 604615, NULL, NULL, 'COURTAGE MARITIME, CONSIGNATAIRE DE NAVIRES ET DE CARGAISON'),
(994, 604616, NULL, NULL, 'COMMISSIONNAIRE DE TRANSPORT DE MARCHANDISES'),
(995, 604617, NULL, NULL, 'COMMISSIONNAIRE EN DOUANES'),
(996, 604618, NULL, NULL, 'FILLING STATIONS'),
(997, 604619, NULL, NULL, 'POMPES ET CUVES'),
(998, 604620, NULL, NULL, 'AVITAILLEMENT DE NAVIRES ET D\'AERONEFS EN CARBURANTS'),
(999, 604621, NULL, NULL, 'RELAIS ROUTIER'),
(1000, 604622, NULL, NULL, 'STATION DE LAVAGE'),
(1001, 604623, NULL, NULL, 'CENTRE EMPLISSEUR DE GAZ DE PETROLE LIQUEFIES'),
(1002, 604624, NULL, NULL, 'ECOLE DE PILOTAGE DES AERONEFS'),
(1003, 604625, NULL, NULL, 'ECOLE DE PILOTAGE DES NAVIRES'),
(1004, 604626, NULL, NULL, 'STATION DE GRAISSAGE MOBILE'),
(1005, 604627, NULL, NULL, 'SERVICES DE REMORQUAGE ET DEPANNAGE MOBILE'),
(1006, 604628, NULL, NULL, 'CONSIGNATION DE BAGAGES ET AUTRES'),
(1007, 605001, NULL, NULL, 'AGENCE DE PUBLICITE'),
(1008, 605002, NULL, NULL, 'AGENCES PHOTOGRAPHIQUES'),
(1009, 605003, NULL, NULL, 'ENTREPRISE DES FOIRES ET EXPOSITIONS'),
(1010, 605004, NULL, NULL, 'ENTREPRISE CINEMATOGRAPHIQUE'),
(1011, 605005, NULL, NULL, 'DISTRIBUTION DE FILMS'),
(1012, 605006, NULL, NULL, 'EDITION ET DISTRIBUTION DE PRODUITS AUDIOVISUELS'),
(1013, 605007, NULL, NULL, 'ENTREPRISE DE RADIODIFFUSION'),
(1014, 605008, NULL, NULL, 'ACTIVITE DE TELEVISION'),
(1015, 605009, NULL, NULL, 'EXPLOITATION DE SALLES DE SPECTACLES, DE LOISIRS ET AUTRES DIVERTISSEMENTS'),
(1016, 605010, NULL, NULL, 'ENTREPRISE D\'EXPLOITATION DES PECTACLES PUBLICS'),
(1017, 605011, NULL, NULL, 'ENTREPRISE D\'ATTRACTIONS'),
(1018, 605012, NULL, NULL, 'AGENCE DE VOYAGE ET TOURISME'),
(1019, 605013, NULL, NULL, 'AGENCE DE PRESSE'),
(1020, 605014, NULL, NULL, 'ENTREPRISE DE PARI-SPORTIF ET LOTERIES ( RESERVE A L\'ETAT)'),
(1021, 605015, NULL, NULL, 'SALLE DE SPORTS'),
(1022, 605016, NULL, NULL, 'ETABLISSEMENT DE SPORTS NAUTIQUES ( A BUT LUCRATIF)'),
(1023, 605017, NULL, NULL, 'COMPLEXE SPORTIF (A BUT LUCRATIF)'),
(1024, 605018, NULL, NULL, 'GARDERIE D\'ENFANTS ET CRECHES'),
(1025, 605019, NULL, NULL, 'SALLE DE JEUX'),
(1026, 605020, NULL, NULL, 'STUDIO PHOTOGRAPHIQUE'),
(1027, 605021, NULL, NULL, 'AGENCE DE COMMUNICATION'),
(1028, 605022, NULL, NULL, 'ENTREPRISE DE PHOTOGRAVURE'),
(1029, 605023, NULL, NULL, 'ANIMATION DE FETES (DISK-JOKEY)'),
(1030, 605024, NULL, NULL, 'EXPOSITION DE TOUS PRODUITS, MATERIEL ET EQUIPEMENTS ( SHOW-ROOM)'),
(1031, 606101, NULL, NULL, 'CONTROLE TECHNIQUE AUTOMOBILE'),
(1032, 606102, NULL, NULL, 'ENTREPRISE DE CONTROLE TECHNIQUE'),
(1033, 606103, NULL, NULL, 'LABORATOIRES DE CONTROLE DE QUALITE ET DE CONFORMITE'),
(1034, 606104, NULL, NULL, 'ENTREPRISE D\'ETALONNAGE ET DE VERIFICATION DES MACHINES ET APPAREILS D\'ESSAIS MECANIQUE ET D\'INSTRUMENTS DE MESURE'),
(1035, 606201, NULL, NULL, 'ENTREPRISE DE NETTOYAGE, D\'ENTRETIEN ET DE DESINFECTION'),
(1036, 606202, NULL, NULL, 'ENLEVEMENT ET TRAITEMENT DES DECHETS'),
(1037, 606203, NULL, NULL, 'EPURATION DES EAUX USEES'),
(1038, 607001, NULL, NULL, 'BUREAU D\'ETUDE ET DE CONSEIL EN INFORMATIQUE (CONSULTING)'),
(1039, 607002, NULL, NULL, 'INSTALLATION DE RESEAUX ET TRAITEMENT DE DONNEES'),
(1040, 607003, NULL, NULL, 'ENTREPRISE FIDUCIAIRE'),
(1041, 607004, NULL, NULL, 'BUREAU DE CONSEIL JURIDIQUE'),
(1042, 607005, NULL, NULL, 'ADMINISTRATION D\'ENTREPRISES DE TOUS SECTEURS D\'ACTIVITES (HOLDING)'),
(1043, 607006, NULL, NULL, 'BUREAU D\'ETUDES EN ORGANISATION, ETUDES DE MARCHES ET SONDAGES'),
(1044, 607007, NULL, NULL, 'BUREAU D\'INGENIERIE ET D\'ETUDEST TECHNIQUES'),
(1045, 607008, NULL, NULL, 'ENTREPRISE DE COMPTABILITE'),
(1046, 607009, NULL, NULL, 'ENTREPRISE D\'ARCHITECTURE'),
(1047, 607010, NULL, NULL, 'CABINET DE GEOMETRES METREURS'),
(1048, 607011, NULL, NULL, 'ENTREPRISE DE TRAVAIL TEMPORAIRE ET DE SELECTION DU PERSONNEL'),
(1049, 607012, NULL, NULL, 'ENTREPRISE DE GARDIENNAGE ET DE SECURITE'),
(1050, 607013, NULL, NULL, 'ENTREPRISES D\'AFFACTURAGE (FACTORING)'),
(1051, 607014, NULL, NULL, 'ENTREPRISES DE FORMATION ET D\'ENSEIGNEMENT DIVERS'),
(1052, 607015, NULL, NULL, 'SOCIETE D\'EXPERTISE TECHNIQUE ET DE COMMISSARIAT D\'AVARIES'),
(1053, 607016, NULL, NULL, 'SOCIETE DE PROMOTION ET D\'INFORMATION MEDICALE ET SCIENTIFIQUE SUR LES PRODUITS PHARMACEUTIQUES'),
(1054, 607017, NULL, NULL, 'CABINET CONSEIL, ETUDES ET ASSISTANCE EN INVESTISSEMENT'),
(1055, 607018, NULL, NULL, 'ENTREPRISE D\'ORGANISATION DES MANIFESTATIONS CULTURELLES, ECONOMIQUES ET SCIENTIFIQUES'),
(1056, 607019, NULL, NULL, 'ENTREPRISE D\'INGENIERIE EN INFORMATIQUE INDUSTRIELLE ET PRODUCTIQUE'),
(1057, 607020, NULL, NULL, 'ENTREPRISE DE DECORATION'),
(1058, 607021, NULL, NULL, 'ENTREPRISE DE SERVICES PETROLIERS'),
(1059, 607022, NULL, NULL, 'ENTREPRISE DE DISTRIBUTION DES PRODUITS PETROLIERS'),
(1060, 607023, NULL, NULL, 'ENTREPRISE DE SERVICE HOTELIERS ET DE RESTAURATION (CATERING)'),
(1061, 607024, NULL, NULL, 'ENTREPRISE D\'ETUDES ET DE REALISATION DE PROGRAMMES DE PREVENTION ET D\'ASSAINISSEMENT DE L\'ENVIRONNEMENT'),
(1062, 607025, NULL, NULL, 'ENTREPRISE D\'EXPLOITATION DES SERVICES INTERNET'),
(1063, 607026, NULL, NULL, 'CYBER- CAFE'),
(1064, 607028, NULL, NULL, 'CONSULTING ET ASSISTANCE AUX ENTRPRISES NATIONALES ET INTERNATIONALES DANS LES DOMAINES DE L\'INDUSTRIE ET DE L\'ENERGIE'),
(1065, 607029, NULL, NULL, 'ETUDES, CONSULTING ET ASSISTANCE DANS LE DOMAINE AGRICOLE'),
(1066, 607030, NULL, NULL, 'CONSEIL , ASSISTANCE , EVALUATION ET CONCEPTION DE PROGRAMME DE FORMATION'),
(1067, 608001, NULL, NULL, 'CONDITIONNEMENT ET EMBALLAGE DE PRODUITS ET DENREES ALIMENTAIRES'),
(1068, 608002, NULL, NULL, 'CONDITIONNEMENT ET EMBALLAGE DE MATIERES PREMIERES TEXTILES'),
(1069, 608003, NULL, NULL, 'CONDITIONNEMENT ET EMBALLAGE DE PRODUITS CHIMIQUES ET ENGRAIS'),
(1070, 608004, NULL, NULL, 'CONDITIONNEMENT DE PRODUITS DIVERS NDA'),
(1071, 608005, NULL, NULL, 'CONDITIONNEMENT DE PRODUITS PHARMACEUTIQUES'),
(1072, 609001, NULL, NULL, 'ENTREPRISE DE TRAVAUX DE SECRETARIAT ET ASSISTANCE ADMINISTRATIVE'),
(1073, 609002, NULL, NULL, 'TIRAGE DE PLANS, PHOTOCOPIES DIVERSES'),
(1074, 609003, NULL, NULL, 'CONFECTION DE CACHETS ET DE GRIFFES DE SIGNATURES'),
(1075, 610001, NULL, NULL, 'ENTREPRISE DE COURRIER EXPRESS'),
(1076, 610002, NULL, NULL, 'MESSAGERIE OU ENTREPRISE DE PRESSE'),
(1077, 610003, NULL, NULL, 'ENTREPRISE D\'EDITION ET DE DIFFUSION'),
(1078, 610004, NULL, NULL, 'ENTREPRISE DES RESEAUX DE TELECOMMUNICATION'),
(1079, 610005, NULL, NULL, 'TAXIPHONE'),
(1080, 610006, NULL, NULL, 'GESTION DE BOITES POSTALES (CEDEX)'),
(1081, 611001, NULL, NULL, 'LOCATION DE BIENS IMMOBILIERS'),
(1082, 611002, NULL, NULL, 'LOCATION DE TERRAINS'),
(1083, 611003, NULL, NULL, 'LOCATION DE STRUCTURES COMMERCIALES'),
(1084, 611004, NULL, NULL, 'AGENCE IMMOBILIERE'),
(1085, 611005, NULL, NULL, 'ENTREPRISE DE LOCATION ET DE GESTION D\'INFRASTRUCTURES COMMERCIALES'),
(1086, 611006, NULL, NULL, 'ADMINISTRATION DE BIENS IMMOBILIERS'),
(1087, 611007, NULL, NULL, 'GESTION DE ZONES INDUSTRIELLES'),
(1088, 611008, NULL, NULL, 'AGENCE FONCIERE'),
(1089, 612101, NULL, NULL, 'ENTREPRISE DE CREDIT BAIL'),
(1090, 612103, NULL, NULL, 'BANQUE'),
(1091, 612104, NULL, NULL, 'CAISSE D\'EPARGNE ET DE PREVOYANCE'),
(1092, 612105, NULL, NULL, 'ENTREPRISE D\'ASSURANCES'),
(1093, 612106, NULL, NULL, 'SOCIETE DE GESTION DE LA BOURSE DES VALEURS MOBILIERES (SPA)'),
(1094, 612107, NULL, NULL, 'INTERMEDAIRES EN OPERATIONS DE BOURSE'),
(1095, 612201, NULL, NULL, 'BUREAU DE CHANGE'),
(1096, 612202, NULL, NULL, 'AGENT DE CHANGE'),
(1097, 612203, NULL, NULL, 'COURTIER D\'ASSURANCES OU SOCIETE DE COURTAGE D\'ASSURANCE'),
(1098, 612204, NULL, NULL, 'AGENT GENERAL D\'ASSURANCES'),
(1099, 612205, NULL, NULL, 'BUREAU D\'AFFAIRES'),
(1100, 612206, NULL, NULL, 'REPRESENTANT DE COMMERCE'),
(1101, 613101, NULL, NULL, 'INSTALLATION ET REPARATION D\'EQUIPEMENTS UTILISES DANS L\'INDUSTRIE AGRO-ALIMENTAIRE'),
(1102, 613102, NULL, NULL, 'REPARATION DE MATERIELS AGRICOLES'),
(1103, 613103, NULL, NULL, 'REPARATION DE MATERIEL ET MACHINES POUR BTP'),
(1104, 613104, NULL, NULL, 'INSTALLATION ET REPARATION DES MACHINES UTILISEES DANS L\'INDUSTRIE DE TRANSFORMATION DU PAPIER ET DU CARTON'),
(1105, 613105, NULL, NULL, 'INSTALLATION ET REPARATION DES MACHINES D\'IMPRIMERIES'),
(1106, 613106, NULL, NULL, 'INSTALLATION ET REPARATION DES MATERIELS DE COMPOSITION MECANIQUE'),
(1107, 613107, NULL, NULL, 'INSTALLATION ET REPARATION DEMATERIELS DE PHOTOCOMPOSITION'),
(1108, 613108, NULL, NULL, 'INSTALLATION ET REPARATION DE MATERIELS DE LABORATOIRE D\'IMPRIMERIE'),
(1109, 613109, NULL, NULL, 'REPARATION DE MACHINES-OUTILS'),
(1110, 613110, NULL, NULL, 'INSTALLATIONS ELECTRIQUES INDUSTRIELLES ET MAINTENANCE'),
(1111, 613111, NULL, NULL, 'INSTALLATION ET REPARATION DE CHAUDIERES, EQUIPEMENTS ET MATERIELS DE CHAUFFAGE'),
(1112, 613112, NULL, NULL, 'REPARATION DE GENERATEURS DE VAPEUR D\'EAU'),
(1113, 613113, NULL, NULL, 'REPARATION MECANIQUE DE MATERIEL LOURD DE TRANSPORT ROUTIER'),
(1114, 613114, NULL, NULL, 'REPARATION DE MATERIELS HYDROMECANIQUES'),
(1115, 613115, NULL, NULL, 'MECANIQUE DIESEL ( REPARATION ET RENOVATION)'),
(1116, 613116, NULL, NULL, 'REPARATION D\'EQUIPEMENTS ELECTRIQUES ET ELECTRONIQUES'),
(1117, 613117, NULL, NULL, 'INSTALLATION ET REPARATION D\'EQUIPEMENTS DE REFRIGERATION ET DE CLIMATISATION'),
(1118, 613118, NULL, NULL, 'INSTALLATION ET REPARATION D\'EQUIPEMENTS ET D\'APPAREILLAGES MEDIC0-CHIRURGICAUX'),
(1119, 613119, NULL, NULL, 'REPARATION DE BALANCES, BASCULES ET APPAREILS DE MESURES'),
(1120, 613120, NULL, NULL, 'REPARATION D\'INSTRUMENTS DE PRECISION ET D\'OPTIQUE'),
(1121, 613121, NULL, NULL, 'REPARATION DE MACHINES A COUDRE INDUSTRIELLES'),
(1122, 613122, NULL, NULL, 'REPARATION DES METIERS A TISSER ETA TRICOTER INDUSTRIELS'),
(1123, 613123, NULL, NULL, 'INSTALLATION ET REPARATION D\'EQUIPEMENTS ET MATERIELS DE GRANDE CUISINE'),
(1124, 613124, NULL, NULL, 'REPARATION DE MATERIEL ET EQUIPEMENTS DE BOULANGERIE'),
(1125, 613125, NULL, NULL, 'INSTALLATION ET REPARATION DE MATERIEL DE SECURITE ET DE PROTECTION CONTRE L\'INCENDIE ET LE LEVOL'),
(1126, 613126, NULL, NULL, 'REPARATION DE POMPES'),
(1127, 613127, NULL, NULL, 'INSTALLATION ET REPARATION DE FOURS ET REFRACTAIRES DE FOURS'),
(1128, 613128, NULL, NULL, 'INSTALLATION ET MAINTENANCE INDUSTRIELLE DE TOUS EQUIPEMENTS, MOTEURS ET MATERIELS'),
(1129, 613129, NULL, NULL, 'INSTALLATION , REPARATION ET MAINTENANCE DE MOTEURS ET MATERIELS MARINS'),
(1130, 613130, NULL, NULL, 'INSTALLATION ET MAINTENANCE DES EQUIPEMENTS ET MATERIELS POUR HOTELS, CAFES, RESTAURANTS, BLANCHISSERIES ET TEINTURERIES'),
(1131, 613131, NULL, NULL, 'ENTREPRISE DE CONTROLE DE SOUDAGE'),
(1132, 613132, NULL, NULL, 'INSTALLATION ET MONTAGE D\'ACCESSOIRES AUTOMOBILES'),
(1133, 613133, NULL, NULL, 'INSTALLATION ET REPARATION DE MATERIEL ET EQUIPEMENTS LIES AU DOMAINE DE L\'ENERGIE SOLAIRE ET ELECTRIQUE.'),
(1134, 613201, NULL, NULL, 'REPARATION D\'EQUIPEMENTS ET MATERIELS DE BUREAUTIQUE'),
(1135, 613202, NULL, NULL, 'REPARATION D\'EQUIPEMENTS CINEMATOGRAPHIQUES ET PHOTOGRAPHIQUES'),
(1136, 613203, NULL, NULL, 'REPARATION ET ENTRETIEN DES ASCENSEURS ET AUTRES APPAREILS SIMILAIRES.'),
(1137, 613204, NULL, NULL, 'REPARATION MECANIQUE DE VEHICULES AUTOS, REPARATION SPECIALISEE DE PARTIES ET PIECES MECANIQUES POUR TOUS VEHICULES'),
(1138, 613205, NULL, NULL, 'REPARATION DE CYCLES ET MOTOCYCLES'),
(1139, 613206, NULL, NULL, 'REPARATION D\'ACCUMULATEURS'),
(1140, 613207, NULL, NULL, 'MECANIQUE GENERALE'),
(1141, 613208, NULL, NULL, 'REPARATION ELECTRICITE-AUTO'),
(1142, 613209, NULL, NULL, 'ELECTRICITE AUTOMOBILE'),
(1143, 613210, NULL, NULL, 'TOLERIE ET PEINTURE AUTOMOBILE.'),
(1144, 613211, NULL, NULL, 'REPARATION DE RADIATEURS'),
(1145, 613212, NULL, NULL, 'REPARATION DE RIDEAUX EN FER POUR LOCAUX COMMERCIAUX'),
(1146, 613213, NULL, NULL, 'FUMISTERIE ET RAMONAGE NON INDUSTRIELS'),
(1147, 613214, NULL, NULL, 'REPARATION DE BARQUES ET AUTRES EMBARCATIONS'),
(1148, 613215, NULL, NULL, 'INSTALLATION ET REPARATION DES EQUIPEMENTS PORTUAIRES, DE MANUTENTION, DE LEVAGE ET DES MOYENS DE SAUVETAGE MARITIME'),
(1149, 613216, NULL, NULL, 'INSTALLATION, REPARATION ET MAINTENANCE DE TOUT EQUIPEMENT ET EXPLOITATION FONCTIONNANT AU GAZ'),
(1150, 613217, NULL, NULL, 'INSTALLATION ET REPARATION D\'APPAREILS ELECTROMENAGERS'),
(1151, 613218, NULL, NULL, 'INSTALLATION ET REPARATION DE KIT DE CONVERSION DES VEHICULES EN G.P.L'),
(1152, 613219, NULL, NULL, 'AJUSTAGE, TOURNAGE, FRAISAGE'),
(1153, 613220, NULL, NULL, 'EQUILIBRAGE, PARALLELISME DE ROUES'),
(1154, 614001, NULL, NULL, 'COIFFURE ET SOINS DE BEAUTE'),
(1155, 614002, NULL, NULL, 'HAMMAM, SAUNA'),
(1156, 614003, NULL, NULL, 'DOUCHES'),
(1157, 614004, NULL, NULL, 'DEGRAISSAGE, TEINTURERIE, BLANCHISSERIE');
INSERT INTO `t_02_03_01` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`) VALUES
(1158, 614005, NULL, NULL, 'INSTITUT DE SOINS CORPORELLES'),
(1159, 615001, NULL, NULL, 'REPRESENTATION OU AGENCE COMMERCIALE DES ETATS ET COLLECTIVITES ETRANGERES'),
(1160, 615002, NULL, NULL, 'REPRESENTATION OU AGENCE COMMERCIALE DES ETABLISSEMENTS PUBLICS ETRANGERS'),
(1161, 615003, NULL, NULL, 'CHAMBRE ALGERIENNE DE COMMERCE ET D\'INDUSTRIE (CACI)'),
(1162, 615004, NULL, NULL, 'CHAMBRE DE COMMERCE ET D\'INDUSTRIE ( CCI )'),
(1163, 615005, NULL, NULL, 'CHAMBRE NATIONALE DE L\'ARTISANAT ET DES METIERS ( CNAM )'),
(1164, 615006, NULL, NULL, 'CHAMBRE REGIONALE DE L\'ARTISANAT ET DES METIERS'),
(1165, 615007, NULL, NULL, 'CHAMBRE NATIONALE DE L\'AGRICULTURE'),
(1166, 615008, NULL, NULL, 'CHAMBRE REGIONALE DE L\'AGRICULTURE'),
(1167, 615009, NULL, NULL, 'AGENCE NATIONALE DE L\'ARTISANAT TRADITIONNEL'),
(1168, 615010, NULL, NULL, 'PROMOTION DES PRODUITS DE L\'ARTISANAT'),
(1169, 615011, NULL, NULL, 'FONDS NATIONAL DE PEREQUATION DES OEUVRES SOCIALES'),
(1170, 615012, NULL, NULL, 'CAISSE NATIONALE DE LOGEMENT (CNL)'),
(1171, 616001, NULL, NULL, 'ENTREPRISE DE COULAGE DE BETON'),
(1172, 616002, NULL, NULL, 'AMENAGEMENT DES ESPACES VERTS'),
(1173, 616003, NULL, NULL, 'SERVICES RELATIFS A L\'UTILISATION DE L\'ELECTRICITE ET LE GAZ'),
(1174, 616004, NULL, NULL, 'GESTION ET EXPLOITATION DES HOTELS ET DES AGENCES DE TOURISME ET DE VOYAGES');

-- --------------------------------------------------------

--
-- Table structure for table `t_02_03_02`
--

CREATE TABLE `t_02_03_02` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(20) DEFAULT NULL,
  `F_05` varchar(20) DEFAULT NULL,
  `F_06` varchar(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_02_03_02`
--

INSERT INTO `t_02_03_02` (`F_00`, `F_04`, `F_05`, `F_06`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'ش ط', 'NP', 'PP', 'شخص طبيعي﻿', 'Natural Person', 'Personne Physique﻿'),
(2, 'ش م', 'LP', 'PM', 'شخص معنوي﻿', 'Legal Entity', 'Personne Morale﻿'),
(3, 'ش ذ م م ش و', 'OPLLC', 'EURL', 'شركة ذات مسؤولية محدودة لشخص واحد﻿', 'One-Person Limited Liability Company', 'Entreprise Unipersonnelle à Responsabilité Limitée﻿'),
(4, 'ش ذ م م', 'LLC', 'SARL', 'شركة ذات مسؤولية محدودة﻿', 'Limited Liability Company', 'Société à Responsabilité Limitée﻿'),
(5, 'ش ذ أ', 'JSC', 'SPA', 'شركة ذات أسهم', 'Joint Stock Company', 'Société par Action﻿'),
(6, 'ش م م', 'SJSC', 'SPAS', 'شركة مساهمة مبسطة﻿', 'Simplified Joint Stock Company', 'Société par Action Simplifiée﻿'),
(7, 'ش ت', 'GP', 'SNC', 'شركة تضامن﻿', 'General Partnership', 'Société en Nom Collectif﻿'),
(8, 'ت', 'C', 'G', 'تجمع﻿', 'Grouping / Consortium', 'Groupement﻿'),
(9, 'ش م إ', 'PLC', 'SA', 'شركة مجهولة الاسم﻿', 'Public Limited Company', 'Société Anonyme﻿'),
(10, 'م ع ذ ط ص ت', 'PICE', 'EPIC', 'مؤسسة عمومية ذات طابع صناعي وتجاري﻿', 'Public Industrial and Commercial Establishment', 'Etablissement Public à Caractère Industriel et Commerciale﻿'),
(11, 'م ع ذ ط إ', 'PAE', 'EPA', 'مؤسسة عمومية ذات إداري﻿', 'Public Administrative Establishment', 'Etablissement Public à Caractère Administratif');

-- --------------------------------------------------------

--
-- Table structure for table `t_02_03_03`
--

CREATE TABLE `t_02_03_03` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_02_03_03`
--

INSERT INTO `t_02_03_03` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'إقصاء مؤقت', 'Temporary exclusion', 'Exclusion à titre temporale'),
(2, 'إقصاء نهائي', 'Permanent exclusion', 'Exclusion à titre definitif');

-- --------------------------------------------------------

--
-- Table structure for table `t_02_03_04`
--

CREATE TABLE `t_02_03_04` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(20) DEFAULT NULL,
  `F_03` varchar(20) DEFAULT NULL,
  `F_05` varchar(200) DEFAULT NULL,
  `F_11` varchar(200) DEFAULT NULL,
  `F_12` varchar(50) DEFAULT NULL,
  `F_06` double DEFAULT NULL,
  `F_08` datetime(6) DEFAULT NULL,
  `F_07` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_15` varchar(200) DEFAULT NULL,
  `F_16` varchar(300) DEFAULT NULL,
  `F_14` varchar(200) DEFAULT NULL,
  `F_10` varchar(200) DEFAULT NULL,
  `F_13` varchar(50) DEFAULT NULL,
  `F_09` varchar(200) DEFAULT NULL,
  `F_17` varchar(200) DEFAULT NULL,
  `F_20` bigint(20) NOT NULL,
  `F_19` bigint(20) NOT NULL,
  `F_18` bigint(20) DEFAULT NULL,
  `F_21` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_03_05`
--

CREATE TABLE `t_02_03_05` (
  `F_00` bigint(20) NOT NULL,
  `F_05` varchar(100) DEFAULT NULL,
  `F_03` varchar(200) DEFAULT NULL,
  `F_04` varchar(100) DEFAULT NULL,
  `F_01` varchar(50) NOT NULL,
  `F_08` varchar(100) DEFAULT NULL,
  `F_06` varchar(50) DEFAULT NULL,
  `F_02` varchar(50) NOT NULL,
  `F_09` varchar(100) DEFAULT NULL,
  `F_07` varchar(100) DEFAULT NULL,
  `F_10` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_03_06`
--

CREATE TABLE `t_02_03_06` (
  `F_00` bigint(20) NOT NULL,
  `F_02` datetime(6) DEFAULT NULL,
  `F_01` datetime(6) DEFAULT NULL,
  `F_03` bigint(20) NOT NULL,
  `F_04` bigint(20) NOT NULL,
  `F_05` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_03_07`
--

CREATE TABLE `t_02_03_07` (
  `F_00` bigint(20) NOT NULL,
  `F_03` varchar(255) DEFAULT NULL,
  `F_02` datetime(6) DEFAULT NULL,
  `F_01` datetime(6) NOT NULL,
  `F_04` bigint(20) NOT NULL,
  `F_05` bigint(20) NOT NULL,
  `F_06` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_04_01`
--

CREATE TABLE `t_02_04_01` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(20) DEFAULT NULL,
  `F_05` varchar(20) DEFAULT NULL,
  `F_06` varchar(20) NOT NULL,
  `F_01` varchar(150) DEFAULT NULL,
  `F_02` varchar(150) DEFAULT NULL,
  `F_03` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_02_04_01`
--

INSERT INTO `t_02_04_01` (`F_00`, `F_04`, `F_05`, `F_06`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'ط ع م  و', 'NOTC', 'AOON', 'طلب عروض مفتوح وطني', 'National Open Tender Call', 'Appel d\'Offres Ouvert National'),
(2, 'ط ع م و د', 'NIOTC', 'AOONI', 'طلب عروض مفتوح وطني ودولي', 'National and International Open Tender Call', 'Appel d\'Offres Ouvert National et International'),
(3, 'ت ب', 'SDN', 'GGS', 'التراضي البسيط', 'Simple Direct Negotiation', 'Gré à Gré Simple'),
(4, 'ت ب إ', 'NAC', 'GGAC', 'التراضي بعد الاستشارة', 'Negotiation after Consultation', 'Gré à Gré après Consultation'),
(5, 'إد', 'IC(AP)', 'CI(PA)', 'استشارة داخلية', 'Internal Consultation (Adapted Procedure)', 'Consultation Interne (Procédure Adaptée)');

-- --------------------------------------------------------

--
-- Table structure for table `t_02_04_02`
--

CREATE TABLE `t_02_04_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_02_04_02`
--

INSERT INTO `t_02_04_02` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'مبرمجة', 'Planed', 'Programmée'),
(2, 'قيد الإعداد', 'In Progress', 'En cours d\'élaboration'),
(3, 'قيد المراجعة', 'Under Review', 'En cours de contrôle'),
(4, 'قيد النشر', 'In Publication', 'En cours de publication'),
(5, 'قيد التقييم', 'Under Evaluation', 'En cours d\'évaluation');

-- --------------------------------------------------------

--
-- Table structure for table `t_02_04_03`
--

CREATE TABLE `t_02_04_03` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL,
  `F_04` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_02_04_03`
--

INSERT INTO `t_02_04_03` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`) VALUES
(1, 'مرحلة إعداد المخطط الميزانياتي', 'Budget Plan Maturation Stage', 'Instance de maturation de plan budgétaire', 1),
(2, 'مرحلة تحديد الإحتياجات', 'Needs Identification Stage', 'Instance du besoin', 1),
(3, 'مرحلة إعداد البطاقة التقنية', 'Technical Data Sheet Preparation Stage', 'Instance de la fiche technique', 1),
(4, 'مرحلة موافقة القيادة العليا', 'High Command Approval Stage', 'Instance Accord du Haut Commandement', 2),
(5, 'مرحلة إبداء الرأي', 'Opinion Stage', 'Instance d\'avis', 2),
(6, 'إعداد ملف اللجنة القطاعية للصفقات (CSM)', 'Preparation of CSM File', 'Préparation du dossier CSM', 2),
(7, 'مرحلة دراسة اللجنة القطاعية للصفقات (CSM)', 'CSM Review Stage', 'Instance Examen de la CSM', 3),
(8, 'رفع التحفظات جارية', 'Lifting of Ongoing Reservations', 'Leveé des réserves en cours', 3),
(9, 'رفع التحفظات، في إنتظار مداولة اللجنة القطاعية للصفقات (CSM)', 'Reservations Removed, CSM Deliberation Stage', 'Réserves leveés, instance délibiration de la CSM', 3),
(10, 'إعداد ملف النشر', 'Preparation of Publish File', 'Préparation du dossier de publication', 3),
(11, 'مرحلة النشر', 'Publish Stage', 'Instance publication', 4),
(12, 'مرحلة إيداع العروض', 'Submission of Offers Stage', 'Instance dépôt des offres', 4),
(13, 'تحويل الملف إلى لجنة فتح الأظرفة وتقييم العروض (COPEO)', 'File Transmitted to COPEO', 'Dossier transmis à la COPEO', 5);

-- --------------------------------------------------------

--
-- Table structure for table `t_02_04_04`
--

CREATE TABLE `t_02_04_04` (
  `F_00` bigint(20) NOT NULL,
  `F_07` double DEFAULT NULL,
  `F_11` datetime(6) DEFAULT NULL,
  `F_10` varchar(255) DEFAULT NULL,
  `F_02` varchar(4) NOT NULL,
  `F_13` datetime(6) DEFAULT NULL,
  `F_04` varchar(300) DEFAULT NULL,
  `F_05` varchar(300) DEFAULT NULL,
  `F_06` varchar(300) NOT NULL,
  `F_08` double DEFAULT NULL,
  `F_01` varchar(3) NOT NULL,
  `F_14` varchar(500) DEFAULT NULL,
  `F_12` datetime(6) DEFAULT NULL,
  `F_03` varchar(20) DEFAULT NULL,
  `F_09` datetime(6) DEFAULT NULL,
  `F_19` bigint(20) NOT NULL,
  `F_15` bigint(20) NOT NULL,
  `F_17` bigint(20) NOT NULL,
  `F_21` bigint(20) NOT NULL,
  `F_20` bigint(20) NOT NULL,
  `F_16` bigint(20) NOT NULL,
  `F_18` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_04_05`
--

CREATE TABLE `t_02_04_05` (
  `F_00` bigint(20) NOT NULL,
  `F_02` double DEFAULT NULL,
  `F_01` datetime(6) DEFAULT NULL,
  `F_05` bigint(20) DEFAULT NULL,
  `F_03` bigint(20) NOT NULL,
  `F_07` bigint(20) DEFAULT NULL,
  `F_06` bigint(20) DEFAULT NULL,
  `F_04` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_05_01`
--

CREATE TABLE `t_02_05_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_02_05_01`
--

INSERT INTO `t_02_05_01` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'اتفاقية', 'Convention', 'Convention'),
(2, 'اتفاقية إطار', 'Framework Convention', 'Convention Cadre'),
(3, 'صفقة', 'Contract', 'Marché'),
(4, 'صفقة تسوية', 'Regularization Contract', 'Marché de Régularisation'),
(5, 'صفقة طلبات', 'Contract on Demand', 'Marché à Commandes'),
(6, 'عقد', 'Contract', 'Contrat'),
(7, 'عقد برنامج', 'Framework Contract', 'Contrat Programme'),
(8, 'عقد تطبيق', 'Implementation Contract', 'Contrat d\'Application'),
(9, 'أمر شراء', 'Purchase Order', 'Bon de Commande');

-- --------------------------------------------------------

--
-- Table structure for table `t_02_05_02`
--

CREATE TABLE `t_02_05_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_02_05_02`
--

INSERT INTO `t_02_05_02` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'مبرمجة﻿', 'Planed', 'Programmée'),
(2, 'قيد الإعداد', 'In Progress', 'En cours d\'élaboration'),
(3, 'قيد المراجعة﻿', 'Under Review', 'En cours de contrôle'),
(4, 'قيد الإلتزام', 'Under Engagement', 'En cours dengagement');

-- --------------------------------------------------------

--
-- Table structure for table `t_02_05_03`
--

CREATE TABLE `t_02_05_03` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL,
  `F_04` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_02_05_03`
--

INSERT INTO `t_02_05_03` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`) VALUES
(1, 'مرحلة مداولات لجنة فتح الأظرفة وتقييم العروض', 'COPEO Committee Deliberation Stage', 'Instance PVs COPEO', 1),
(2, 'إمضاء مشروع العقد/الفقة', 'Signature of the contract project', 'Signature du projet du contrat/marché', 2),
(3, 'إعداد ملف اللجنة القطاعية للصفقات', 'Preparation of CSM File', 'Préparation du dossier CSM', 2),
(4, 'مرحلة دراسة اللجنة القطاعية للصفقات', 'CSM Review Stage', 'Instance Examen de la CSM', 3),
(5, 'رفع التحفظات جارية', 'Lifting of Ongoing Reservations', 'Leveé des réserves en cours', 3),
(6, 'رفع التحفظات، في إنتظار مداولة اللجنة القطاعية للصفقات', 'Reservations Removed, CSM Deliberation Stage', 'Réserves leveés, instance délibiration de la CSM', 3),
(7, 'إعداد ملف الالتزام', 'Preparation of Commitment File', 'Préparation du dossier du dossier d\'engagement', 3),
(8, 'إرسال الملف للالتزام', 'File Sent for Commitment', 'Dossier transmis pour engagement', 4);

-- --------------------------------------------------------

--
-- Table structure for table `t_02_05_04`
--

CREATE TABLE `t_02_05_04` (
  `F_00` bigint(20) NOT NULL,
  `F_07` double DEFAULT NULL,
  `F_11` datetime(6) DEFAULT NULL,
  `F_10` varchar(255) DEFAULT NULL,
  `F_12` datetime(6) DEFAULT NULL,
  `F_14` int(11) DEFAULT NULL,
  `F_02` varchar(255) DEFAULT NULL,
  `F_04` varchar(300) DEFAULT NULL,
  `F_05` varchar(300) DEFAULT NULL,
  `F_06` varchar(300) NOT NULL,
  `F_01` varchar(255) NOT NULL,
  `F_13` datetime(6) DEFAULT NULL,
  `F_15` varchar(500) DEFAULT NULL,
  `F_03` varchar(255) DEFAULT NULL,
  `F_09` datetime(6) DEFAULT NULL,
  `F_08` double DEFAULT NULL,
  `F_20` bigint(20) DEFAULT NULL,
  `F_22` bigint(20) DEFAULT NULL,
  `F_19` bigint(20) NOT NULL,
  `F_16` bigint(20) NOT NULL,
  `F_23` bigint(20) DEFAULT NULL,
  `F_21` bigint(20) NOT NULL,
  `F_17` bigint(20) NOT NULL,
  `F_18` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_05_05`
--

CREATE TABLE `t_02_05_05` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(255) NOT NULL,
  `F_05` varchar(255) DEFAULT NULL,
  `F_03` double DEFAULT NULL,
  `F_02` varchar(255) NOT NULL,
  `F_04` double DEFAULT NULL,
  `F_06` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_06_01`
--

CREATE TABLE `t_02_06_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_02_06_01`
--

INSERT INTO `t_02_06_01` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'ملحق عقد', 'Amendment', 'Avenant'),
(2, 'ملحق تعديل', 'Adjustment Amendment', 'Avenant d\'Ajustement'),
(3, 'ملحق نقل الحقوق والالتزامات', 'Amendment for Transfer of Rights and Obligations', 'Avenant de transfert des droits et des obligations'),
(4, 'ملحق تعديل الكميات النهائية', 'Amendment for Adjustment of Final Quantities', 'Avenant d\'Ajustement des Quantités Définitives');

-- --------------------------------------------------------

--
-- Table structure for table `t_02_06_02`
--

CREATE TABLE `t_02_06_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_02_06_02`
--

INSERT INTO `t_02_06_02` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'مبرمجة﻿', 'Planed', 'Programmée'),
(2, 'قيد الإعداد', 'In Progress', 'En cours d\'élaboration'),
(3, 'قيد المراجعة﻿', 'Under Review', 'En cours de contrôle'),
(4, 'قيد الإلتزام', 'Under Engagement', 'En cours dengagement');

-- --------------------------------------------------------

--
-- Table structure for table `t_02_06_03`
--

CREATE TABLE `t_02_06_03` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL,
  `F_04` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_02_06_03`
--

INSERT INTO `t_02_06_03` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`) VALUES
(1, 'مرحلة مداولات لجنة فتح الأظرفة وتقييم العروض', 'COPEO Amendment fileStage', 'Instance Dossier de l\'avenant', 1),
(2, 'مرحلة مداولات لجنة فتح الأظرفة وتقييم العروض', 'COPEO Committee Deliberation Stage', 'Instance PVs COPEO', 1),
(3, 'مرحلة موافقة القيادة العليا', 'High Command Approval Stage', 'Instance Accord du Haut Commandement', 1),
(4, 'إمضاء مشروع العقد/الفقة', 'Signature of the Amendment project', 'Signature du projet de l\'avenant', 2),
(5, 'إعداد ملف اللجنة القطاعية للصفقات', 'Preparation of CSM File', 'Préparation du dossier CSM', 2),
(6, 'مرحلة دراسة اللجنة القطاعية للصفقات', 'CSM Review Stage', 'Instance Examen de la CSM', 3),
(7, 'رفع التحفظات جارية', 'Lifting of Ongoing Reservations', 'Leveé des réserves en cours', 3),
(8, 'رفع التحفظات، في إنتظار مداولة اللجنة القطاعية للصفقات', 'Reservations Removed, CSM Deliberation Stage', 'Réserves leveés, instance délibiration de la CSM', 3),
(9, 'إعداد ملف الالتزام', 'Preparation of Commitment File', 'Préparation du dossier du dossier d\'engagement', 3),
(10, 'إرسال الملف للالتزام', 'File Sent for Commitment', 'Dossier transmis pour engagement', 4);

-- --------------------------------------------------------

--
-- Table structure for table `t_02_06_04`
--

CREATE TABLE `t_02_06_04` (
  `F_00` bigint(20) NOT NULL,
  `F_06` double DEFAULT NULL,
  `F_09` datetime(6) DEFAULT NULL,
  `F_03` varchar(300) DEFAULT NULL,
  `F_04` varchar(300) DEFAULT NULL,
  `F_05` varchar(300) DEFAULT NULL,
  `F_01` int(11) DEFAULT NULL,
  `F_10` datetime(6) DEFAULT NULL,
  `F_11` varchar(500) DEFAULT NULL,
  `F_02` varchar(255) DEFAULT NULL,
  `F_08` datetime(6) DEFAULT NULL,
  `F_07` double DEFAULT NULL,
  `F_15` bigint(20) NOT NULL,
  `F_13` bigint(20) NOT NULL,
  `F_16` bigint(20) DEFAULT NULL,
  `F_12` bigint(20) NOT NULL,
  `F_17` bigint(20) NOT NULL,
  `F_14` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `r_t000201_t000203`
--
ALTER TABLE `r_t000201_t000203`
  ADD PRIMARY KEY (`F_01`,`F_02`),
  ADD KEY `R_T000201_T000203_FK_02` (`F_02`);

--
-- Indexes for table `r_t000202_t000201`
--
ALTER TABLE `r_t000202_t000201`
  ADD PRIMARY KEY (`F_01`,`F_02`),
  ADD KEY `R_T000202_T000201_FK_02` (`F_02`);

--
-- Indexes for table `r_t000202_t000203`
--
ALTER TABLE `r_t000202_t000203`
  ADD PRIMARY KEY (`F_01`,`F_02`),
  ADD KEY `R_T000202_T000203_FK_02` (`F_02`);

--
-- Indexes for table `r_t000203_t000204`
--
ALTER TABLE `r_t000203_t000204`
  ADD PRIMARY KEY (`F_01`,`F_02`),
  ADD KEY `R_T000203_T000204_FK_02` (`F_02`);

--
-- Indexes for table `r_t010203_t010203`
--
ALTER TABLE `r_t010203_t010203`
  ADD UNIQUE KEY `R_T010203_T010203_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T010203_T010203_FK_02` (`F_02`);

--
-- Indexes for table `r_t020304_t020301`
--
ALTER TABLE `r_t020304_t020301`
  ADD UNIQUE KEY `R_T020304_T020301_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020304_T020301_FK_02` (`F_02`);

--
-- Indexes for table `r_t020404_t010203`
--
ALTER TABLE `r_t020404_t010203`
  ADD UNIQUE KEY `R_T020404_T010203_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020404_T010203_FK_02` (`F_02`);

--
-- Indexes for table `r_t020404_t010302`
--
ALTER TABLE `r_t020404_t010302`
  ADD UNIQUE KEY `R_T020404_T010302_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020404_T010302_FK_02` (`F_02`);

--
-- Indexes for table `r_t020404_t020208`
--
ALTER TABLE `r_t020404_t020208`
  ADD UNIQUE KEY `R_T020404_T020208_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020404_T020208_FK_02` (`F_02`);

--
-- Indexes for table `r_t020504_t010203`
--
ALTER TABLE `r_t020504_t010203`
  ADD UNIQUE KEY `R_T020504_T010203_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020504_T010203_FK_02` (`F_02`);

--
-- Indexes for table `r_t020504_t010302`
--
ALTER TABLE `r_t020504_t010302`
  ADD UNIQUE KEY `R_T020504_T010302_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020504_T010302_FK_02` (`F_02`);

--
-- Indexes for table `r_t020504_t020208`
--
ALTER TABLE `r_t020504_t020208`
  ADD UNIQUE KEY `R_T020504_T020208_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020504_T020208_FK_02` (`F_02`);

--
-- Indexes for table `r_t020604_t010203`
--
ALTER TABLE `r_t020604_t010203`
  ADD UNIQUE KEY `R_T020604_T010203_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020604_T010203_FK_02` (`F_02`);

--
-- Indexes for table `r_t020604_t010302`
--
ALTER TABLE `r_t020604_t010302`
  ADD UNIQUE KEY `R_T020604_T010302_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020604_T010302_FK_02` (`F_02`);

--
-- Indexes for table `t_00_01_01`
--
ALTER TABLE `t_00_01_01`
  ADD PRIMARY KEY (`F_00`);

--
-- Indexes for table `t_00_02_01`
--
ALTER TABLE `t_00_02_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_00_02_01_UK_01` (`F_01`);

--
-- Indexes for table `t_00_02_02`
--
ALTER TABLE `t_00_02_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_00_02_02_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_00_02_02_UK_02` (`F_02`);

--
-- Indexes for table `t_00_02_03`
--
ALTER TABLE `t_00_02_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_00_02_03_UK_01` (`F_01`);

--
-- Indexes for table `t_00_02_04`
--
ALTER TABLE `t_00_02_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_00_02_04_UK_01` (`F_01`),
  ADD KEY `T_00_02_04_FK_01` (`F_03`);

--
-- Indexes for table `t_00_02_05`
--
ALTER TABLE `t_00_02_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_00_02_05_UK_01` (`F_01`);

--
-- Indexes for table `t_00_03_01`
--
ALTER TABLE `t_00_03_01`
  ADD PRIMARY KEY (`F_00`);

--
-- Indexes for table `t_00_04_01`
--
ALTER TABLE `t_00_04_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_00_04_01_UK_01` (`F_03`);

--
-- Indexes for table `t_01_01_01`
--
ALTER TABLE `t_01_01_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_01_01_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_01_01_01_UK_02` (`F_02`);

--
-- Indexes for table `t_01_01_02`
--
ALTER TABLE `t_01_01_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_01_02_UK_01` (`F_01`),
  ADD KEY `T_01_01_02_FK_01` (`F_02`),
  ADD KEY `T_01_01_02_FK_02` (`F_03`),
  ADD KEY `T_01_01_02_FK_03` (`F_04`);

--
-- Indexes for table `t_01_01_03`
--
ALTER TABLE `t_01_01_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_01_03_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_01_01_03_UK_02` (`F_04`);

--
-- Indexes for table `t_01_01_04`
--
ALTER TABLE `t_01_01_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_01_04_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_01_01_04_UK_02` (`F_04`);

--
-- Indexes for table `t_01_01_05`
--
ALTER TABLE `t_01_01_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_01_05_UK_01` (`F_01`),
  ADD KEY `T_01_01_05_FK_01` (`F_02`);

--
-- Indexes for table `t_01_01_06`
--
ALTER TABLE `t_01_01_06`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_01_06_UK_01` (`F_01`),
  ADD KEY `T_01_01_06_FK_01` (`F_02`),
  ADD KEY `T_01_01_06_FK_02` (`F_03`);

--
-- Indexes for table `t_01_01_07`
--
ALTER TABLE `t_01_01_07`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_01_07_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_01_01_07_UK_02` (`F_04`),
  ADD KEY `T_01_01_07_FK_01` (`F_05`);

--
-- Indexes for table `t_01_02_01`
--
ALTER TABLE `t_01_02_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_02_01_UK_01` (`F_03`);

--
-- Indexes for table `t_01_02_02`
--
ALTER TABLE `t_01_02_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_02_02_UK_01` (`F_03`);

--
-- Indexes for table `t_01_02_03`
--
ALTER TABLE `t_01_02_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_02_03_UK_01` (`F_01`),
  ADD KEY `T_01_02_03_FK_04` (`F_09`),
  ADD KEY `T_01_02_03_FK_01` (`F_06`),
  ADD KEY `T_01_02_03_FK_02` (`F_07`),
  ADD KEY `T_01_02_03_FK_03` (`F_08`);

--
-- Indexes for table `t_01_03_01`
--
ALTER TABLE `t_01_03_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_03_01_UK_01` (`F_03`,`F_04`);

--
-- Indexes for table `t_01_03_02`
--
ALTER TABLE `t_01_03_02`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_01_03_02_FK_01` (`F_03`),
  ADD KEY `T_01_03_02_FK_02` (`F_04`);

--
-- Indexes for table `t_01_04_01`
--
ALTER TABLE `t_01_04_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_01_UK_01` (`F_03`);

--
-- Indexes for table `t_01_04_02`
--
ALTER TABLE `t_01_04_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_02_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_01_04_02_UK_02` (`F_02`),
  ADD UNIQUE KEY `T_01_04_02_UK_03` (`F_03`);

--
-- Indexes for table `t_01_04_03`
--
ALTER TABLE `t_01_04_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_03_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_01_04_03_UK_02` (`F_02`),
  ADD UNIQUE KEY `T_01_04_03_UK_03` (`F_03`),
  ADD KEY `T_01_04_03_FK_01` (`F_04`);

--
-- Indexes for table `t_01_04_04`
--
ALTER TABLE `t_01_04_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_04_UK_01` (`F_03`);

--
-- Indexes for table `t_01_04_05`
--
ALTER TABLE `t_01_04_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_05_UK_01` (`F_03`),
  ADD KEY `T_01_04_05_FK_01` (`F_07`);

--
-- Indexes for table `t_01_04_06`
--
ALTER TABLE `t_01_04_06`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_06_UK_01` (`F_03`);

--
-- Indexes for table `t_01_04_07`
--
ALTER TABLE `t_01_04_07`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_07_UK_01` (`F_03`),
  ADD UNIQUE KEY `T_01_04_07_UK_02` (`F_06`),
  ADD KEY `T_01_04_07_FK_01` (`F_07`),
  ADD KEY `T_01_04_07_FK_02` (`F_08`);

--
-- Indexes for table `t_01_04_08`
--
ALTER TABLE `t_01_04_08`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_08_UK_01` (`F_03`),
  ADD KEY `T_01_04_08_FK_01` (`F_04`);

--
-- Indexes for table `t_01_04_09`
--
ALTER TABLE `t_01_04_09`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_01_04_09_FK_02` (`F_09`),
  ADD KEY `T_01_04_09_FK_01` (`F_08`),
  ADD KEY `T_01_04_09_FK_03` (`F_10`);

--
-- Indexes for table `t_01_04_10`
--
ALTER TABLE `t_01_04_10`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_01_04_10_FK_03` (`F_05`),
  ADD KEY `T_01_04_10_FK_02` (`F_04`),
  ADD KEY `T_01_04_10_FK_01` (`F_03`);

--
-- Indexes for table `t_02_01_01`
--
ALTER TABLE `t_02_01_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_01_01_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_02_01_01_UK_02` (`F_02`),
  ADD UNIQUE KEY `T_02_01_01_UK_03` (`F_03`),
  ADD UNIQUE KEY `T_02_01_01_UK_04` (`F_04`),
  ADD UNIQUE KEY `T_02_01_01_UK_05` (`F_05`,`F_06`,`F_07`);

--
-- Indexes for table `t_02_01_02`
--
ALTER TABLE `t_02_01_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_01_02_UK_01` (`F_03`);

--
-- Indexes for table `t_02_01_03`
--
ALTER TABLE `t_02_01_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_01_03_UK_01` (`F_03`);

--
-- Indexes for table `t_02_01_04`
--
ALTER TABLE `t_02_01_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_01_04_UK_01` (`F_03`),
  ADD UNIQUE KEY `T_02_01_04_UK_02` (`F_03`);

--
-- Indexes for table `t_02_01_05`
--
ALTER TABLE `t_02_01_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_01_05_UK_01` (`F_03`);

--
-- Indexes for table `t_02_02_01`
--
ALTER TABLE `t_02_02_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_02_01_UK_01` (`F_03`),
  ADD UNIQUE KEY `T_02_02_01_UK_02` (`F_06`);

--
-- Indexes for table `t_02_02_02`
--
ALTER TABLE `t_02_02_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_02_02_UK_01` (`F_03`);

--
-- Indexes for table `t_02_02_03`
--
ALTER TABLE `t_02_02_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_02_03_UK_01` (`F_01`),
  ADD KEY `T_02_02_03_FK_01` (`F_03`);

--
-- Indexes for table `t_02_02_04`
--
ALTER TABLE `t_02_02_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_02_04_UK_01` (`F_03`);

--
-- Indexes for table `t_02_02_05`
--
ALTER TABLE `t_02_02_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_02_05_UK_01` (`F_03`),
  ADD KEY `T_02_02_05_FK_01` (`F_04`);

--
-- Indexes for table `t_02_02_06`
--
ALTER TABLE `t_02_02_06`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_02_02_06_FK_01` (`F_04`);

--
-- Indexes for table `t_02_02_07`
--
ALTER TABLE `t_02_02_07`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_02_07_UK_01` (`F_03`,`F_04`),
  ADD KEY `T_02_02_07_FK_01` (`F_04`),
  ADD KEY `T_02_02_07_FK_02` (`F_05`);

--
-- Indexes for table `t_02_02_08`
--
ALTER TABLE `t_02_02_08`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_02_02_08_FK_04_` (`F_08`),
  ADD KEY `T_02_02_08_FK_03_` (`F_07`),
  ADD KEY `T_02_02_08_FK_02_` (`F_06`),
  ADD KEY `T_02_02_08_FK_01_` (`F_05`);

--
-- Indexes for table `t_02_02_09`
--
ALTER TABLE `t_02_02_09`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_02_02_09_FK_01` (`F_02`),
  ADD KEY `T_02_02_09_FK_02` (`F_03`);

--
-- Indexes for table `t_02_03_01`
--
ALTER TABLE `t_02_03_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_03_01_UK_01` (`F_03`);

--
-- Indexes for table `t_02_03_02`
--
ALTER TABLE `t_02_03_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_03_02_UK_01` (`F_03`),
  ADD UNIQUE KEY `T_02_03_02_UK_02` (`F_06`);

--
-- Indexes for table `t_02_03_03`
--
ALTER TABLE `t_02_03_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_03_03_UK_01` (`F_03`);

--
-- Indexes for table `t_02_03_04`
--
ALTER TABLE `t_02_03_04`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_02_03_04_FK_03` (`F_20`),
  ADD KEY `T_02_03_04_FK_02` (`F_19`),
  ADD KEY `T_02_03_04_FK_01` (`F_18`),
  ADD KEY `T_02_03_04_FK_04` (`F_21`);

--
-- Indexes for table `t_02_03_05`
--
ALTER TABLE `t_02_03_05`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_02_03_05_FK_01` (`F_10`);

--
-- Indexes for table `t_02_03_06`
--
ALTER TABLE `t_02_03_06`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_02_03_06_FK_01` (`F_03`),
  ADD KEY `T_02_03_06_FK_02` (`F_04`),
  ADD KEY `T_02_03_06_FK_03` (`F_05`);

--
-- Indexes for table `t_02_03_07`
--
ALTER TABLE `t_02_03_07`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_02_03_07_FK_01` (`F_04`),
  ADD KEY `T_02_03_07_FK_02` (`F_05`),
  ADD KEY `T_02_03_07_FK_03` (`F_06`);

--
-- Indexes for table `t_02_04_01`
--
ALTER TABLE `t_02_04_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_04_01_UK_01` (`F_03`),
  ADD UNIQUE KEY `T_02_04_01_UK_02` (`F_06`);

--
-- Indexes for table `t_02_04_02`
--
ALTER TABLE `t_02_04_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_04_02_UK_01` (`F_03`);

--
-- Indexes for table `t_02_04_03`
--
ALTER TABLE `t_02_04_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_04_03_UK_01` (`F_03`),
  ADD KEY `T_02_04_03_FK_01` (`F_04`);

--
-- Indexes for table `t_02_04_04`
--
ALTER TABLE `t_02_04_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_04_04_UK_01` (`F_01`,`F_02`),
  ADD KEY `T_02_04_04_FK_05` (`F_19`),
  ADD KEY `T_02_04_04_FK_01` (`F_15`),
  ADD KEY `T_02_04_04_FK_03` (`F_17`),
  ADD KEY `T_02_04_04_FK_07` (`F_21`),
  ADD KEY `T_02_04_04_FK_06` (`F_20`),
  ADD KEY `T_02_04_04_FK_02` (`F_16`),
  ADD KEY `T_02_04_04_FK_04` (`F_18`);

--
-- Indexes for table `t_02_04_05`
--
ALTER TABLE `t_02_04_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_04_05_UK_01` (`F_03`,`F_04`),
  ADD KEY `T_02_04_05_FK_03` (`F_05`),
  ADD KEY `T_02_04_05_FK_05` (`F_07`),
  ADD KEY `T_02_04_05_FK_04` (`F_06`),
  ADD KEY `T_02_04_05_FK_02` (`F_04`);

--
-- Indexes for table `t_02_05_01`
--
ALTER TABLE `t_02_05_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_05_01_UK_01` (`F_03`);

--
-- Indexes for table `t_02_05_02`
--
ALTER TABLE `t_02_05_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_05_02_UK_01` (`F_03`);

--
-- Indexes for table `t_02_05_03`
--
ALTER TABLE `t_02_05_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_05_03_UK_01` (`F_03`),
  ADD KEY `T_02_05_03_FK_01` (`F_04`);

--
-- Indexes for table `t_02_05_04`
--
ALTER TABLE `t_02_05_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_05_04_UK_01` (`F_01`),
  ADD KEY `T_02_05_04_FK_05` (`F_20`),
  ADD KEY `T_02_05_04_FK_07` (`F_22`),
  ADD KEY `T_02_05_04_FK_04` (`F_19`),
  ADD KEY `T_02_05_04_FK_01` (`F_16`),
  ADD KEY `T_02_05_04_FK_08` (`F_23`),
  ADD KEY `T_02_05_04_FK_06` (`F_21`),
  ADD KEY `T_02_05_04_FK_02` (`F_17`),
  ADD KEY `T_02_05_04_FK_03` (`F_18`);

--
-- Indexes for table `t_02_05_05`
--
ALTER TABLE `t_02_05_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_05_05_UK_01` (`F_02`,`F_06`),
  ADD KEY `T_02_05_05_FK_01` (`F_06`);

--
-- Indexes for table `t_02_06_01`
--
ALTER TABLE `t_02_06_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_06_01_UK_01` (`F_03`);

--
-- Indexes for table `t_02_06_02`
--
ALTER TABLE `t_02_06_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_06_02_UK_01` (`F_03`);

--
-- Indexes for table `t_02_06_03`
--
ALTER TABLE `t_02_06_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_06_03_UK_01` (`F_03`),
  ADD KEY `T_02_06_03_FK_01` (`F_04`);

--
-- Indexes for table `t_02_06_04`
--
ALTER TABLE `t_02_06_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_06_04_UK_01` (`F_02`),
  ADD KEY `T_02_06_04_FK_04` (`F_15`),
  ADD KEY `T_02_06_04_FK_02` (`F_13`),
  ADD KEY `T_02_06_04_FK_05` (`F_16`),
  ADD KEY `T_02_06_04_FK_01` (`F_12`),
  ADD KEY `T_02_06_04_FK_06` (`F_17`),
  ADD KEY `T_02_06_04_FK_03` (`F_14`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `t_00_01_01`
--
ALTER TABLE `t_00_01_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_00_02_01`
--
ALTER TABLE `t_00_02_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `t_00_02_02`
--
ALTER TABLE `t_00_02_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `t_00_02_03`
--
ALTER TABLE `t_00_02_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `t_00_02_04`
--
ALTER TABLE `t_00_02_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `t_00_02_05`
--
ALTER TABLE `t_00_02_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `t_00_03_01`
--
ALTER TABLE `t_00_03_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_00_04_01`
--
ALTER TABLE `t_00_04_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `t_01_01_01`
--
ALTER TABLE `t_01_01_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `t_01_01_02`
--
ALTER TABLE `t_01_01_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `t_01_01_03`
--
ALTER TABLE `t_01_01_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `t_01_01_04`
--
ALTER TABLE `t_01_01_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `t_01_01_05`
--
ALTER TABLE `t_01_01_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `t_01_01_06`
--
ALTER TABLE `t_01_01_06`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_01_01_07`
--
ALTER TABLE `t_01_01_07`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_01_02_01`
--
ALTER TABLE `t_01_02_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `t_01_02_02`
--
ALTER TABLE `t_01_02_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `t_01_02_03`
--
ALTER TABLE `t_01_02_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_01_03_01`
--
ALTER TABLE `t_01_03_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `t_01_03_02`
--
ALTER TABLE `t_01_03_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_01_04_01`
--
ALTER TABLE `t_01_04_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=250;

--
-- AUTO_INCREMENT for table `t_01_04_02`
--
ALTER TABLE `t_01_04_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- AUTO_INCREMENT for table `t_01_04_03`
--
ALTER TABLE `t_01_04_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2807;

--
-- AUTO_INCREMENT for table `t_01_04_04`
--
ALTER TABLE `t_01_04_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `t_01_04_05`
--
ALTER TABLE `t_01_04_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `t_01_04_06`
--
ALTER TABLE `t_01_04_06`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `t_01_04_07`
--
ALTER TABLE `t_01_04_07`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_01_04_08`
--
ALTER TABLE `t_01_04_08`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_01_04_09`
--
ALTER TABLE `t_01_04_09`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_01_04_10`
--
ALTER TABLE `t_01_04_10`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_01_01`
--
ALTER TABLE `t_02_01_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=142;

--
-- AUTO_INCREMENT for table `t_02_01_02`
--
ALTER TABLE `t_02_01_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `t_02_01_03`
--
ALTER TABLE `t_02_01_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `t_02_01_04`
--
ALTER TABLE `t_02_01_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `t_02_01_05`
--
ALTER TABLE `t_02_01_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `t_02_02_01`
--
ALTER TABLE `t_02_02_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `t_02_02_02`
--
ALTER TABLE `t_02_02_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `t_02_02_03`
--
ALTER TABLE `t_02_02_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_02_04`
--
ALTER TABLE `t_02_02_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_02_05`
--
ALTER TABLE `t_02_02_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_02_06`
--
ALTER TABLE `t_02_02_06`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_02_07`
--
ALTER TABLE `t_02_02_07`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_02_08`
--
ALTER TABLE `t_02_02_08`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_02_09`
--
ALTER TABLE `t_02_02_09`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_03_01`
--
ALTER TABLE `t_02_03_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1175;

--
-- AUTO_INCREMENT for table `t_02_03_02`
--
ALTER TABLE `t_02_03_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `t_02_03_03`
--
ALTER TABLE `t_02_03_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `t_02_03_04`
--
ALTER TABLE `t_02_03_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_03_05`
--
ALTER TABLE `t_02_03_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_03_06`
--
ALTER TABLE `t_02_03_06`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_03_07`
--
ALTER TABLE `t_02_03_07`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_04_01`
--
ALTER TABLE `t_02_04_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `t_02_04_02`
--
ALTER TABLE `t_02_04_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `t_02_04_03`
--
ALTER TABLE `t_02_04_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `t_02_04_04`
--
ALTER TABLE `t_02_04_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_04_05`
--
ALTER TABLE `t_02_04_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_05_01`
--
ALTER TABLE `t_02_05_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `t_02_05_02`
--
ALTER TABLE `t_02_05_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `t_02_05_03`
--
ALTER TABLE `t_02_05_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `t_02_05_04`
--
ALTER TABLE `t_02_05_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_05_05`
--
ALTER TABLE `t_02_05_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_06_01`
--
ALTER TABLE `t_02_06_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `t_02_06_02`
--
ALTER TABLE `t_02_06_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `t_02_06_03`
--
ALTER TABLE `t_02_06_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `t_02_06_04`
--
ALTER TABLE `t_02_06_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `r_t000201_t000203`
--
ALTER TABLE `r_t000201_t000203`
  ADD CONSTRAINT `R_T000201_T000203_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_00_02_01` (`F_00`),
  ADD CONSTRAINT `R_T000201_T000203_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_00_02_03` (`F_00`);

--
-- Constraints for table `r_t000202_t000201`
--
ALTER TABLE `r_t000202_t000201`
  ADD CONSTRAINT `R_T000202_T000201_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_00_02_02` (`F_00`),
  ADD CONSTRAINT `R_T000202_T000201_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_00_02_01` (`F_00`);

--
-- Constraints for table `r_t000202_t000203`
--
ALTER TABLE `r_t000202_t000203`
  ADD CONSTRAINT `R_T000202_T000203_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_00_02_02` (`F_00`),
  ADD CONSTRAINT `R_T000202_T000203_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_00_02_03` (`F_00`);

--
-- Constraints for table `r_t000203_t000204`
--
ALTER TABLE `r_t000203_t000204`
  ADD CONSTRAINT `R_T000203_T000204_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_00_02_03` (`F_00`),
  ADD CONSTRAINT `R_T000203_T000204_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_00_02_04` (`F_00`);

--
-- Constraints for table `r_t010203_t010203`
--
ALTER TABLE `r_t010203_t010203`
  ADD CONSTRAINT `R_T010203_T010203_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_01_02_03` (`F_00`),
  ADD CONSTRAINT `R_T010203_T010203_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_01_02_03` (`F_00`);

--
-- Constraints for table `r_t020304_t020301`
--
ALTER TABLE `r_t020304_t020301`
  ADD CONSTRAINT `R_T020304_T020301_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_03_04` (`F_00`),
  ADD CONSTRAINT `R_T020304_T020301_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_02_03_01` (`F_00`);

--
-- Constraints for table `r_t020404_t010203`
--
ALTER TABLE `r_t020404_t010203`
  ADD CONSTRAINT `R_T020404_T010203_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_04_04` (`F_00`),
  ADD CONSTRAINT `R_T020404_T010203_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_01_02_03` (`F_00`);

--
-- Constraints for table `r_t020404_t010302`
--
ALTER TABLE `r_t020404_t010302`
  ADD CONSTRAINT `R_T020404_T010302_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_04_04` (`F_00`),
  ADD CONSTRAINT `R_T020404_T010302_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_01_03_02` (`F_00`);

--
-- Constraints for table `r_t020404_t020208`
--
ALTER TABLE `r_t020404_t020208`
  ADD CONSTRAINT `R_T020404_T020208_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_04_04` (`F_00`),
  ADD CONSTRAINT `R_T020404_T020208_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_02_02_08` (`F_00`);

--
-- Constraints for table `r_t020504_t010203`
--
ALTER TABLE `r_t020504_t010203`
  ADD CONSTRAINT `R_T020504_T010203_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_05_04` (`F_00`),
  ADD CONSTRAINT `R_T020504_T010203_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_01_02_03` (`F_00`);

--
-- Constraints for table `r_t020504_t010302`
--
ALTER TABLE `r_t020504_t010302`
  ADD CONSTRAINT `R_T020504_T010302_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_05_04` (`F_00`),
  ADD CONSTRAINT `R_T020504_T010302_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_01_03_02` (`F_00`);

--
-- Constraints for table `r_t020504_t020208`
--
ALTER TABLE `r_t020504_t020208`
  ADD CONSTRAINT `R_T020504_T020208_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_05_04` (`F_00`),
  ADD CONSTRAINT `R_T020504_T020208_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_02_02_08` (`F_00`);

--
-- Constraints for table `r_t020604_t010203`
--
ALTER TABLE `r_t020604_t010203`
  ADD CONSTRAINT `R_T020604_T010203_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_06_04` (`F_00`),
  ADD CONSTRAINT `R_T020604_T010203_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_01_02_03` (`F_00`);

--
-- Constraints for table `r_t020604_t010302`
--
ALTER TABLE `r_t020604_t010302`
  ADD CONSTRAINT `R_T020604_T010302_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_06_04` (`F_00`),
  ADD CONSTRAINT `R_T020604_T010302_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_01_03_02` (`F_00`);

--
-- Constraints for table `t_00_02_04`
--
ALTER TABLE `t_00_02_04`
  ADD CONSTRAINT `T_00_02_04_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_00_02_05` (`F_00`);

--
-- Constraints for table `t_00_04_01`
--
ALTER TABLE `t_00_04_01`
  ADD CONSTRAINT `T_00_04_01_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_00_02_02` (`F_00`);

--
-- Constraints for table `t_01_01_02`
--
ALTER TABLE `t_01_01_02`
  ADD CONSTRAINT `T_01_01_02_FK_01` FOREIGN KEY (`F_02`) REFERENCES `t_01_01_01` (`F_00`),
  ADD CONSTRAINT `T_01_01_02_FK_02` FOREIGN KEY (`F_03`) REFERENCES `t_01_01_03` (`F_00`),
  ADD CONSTRAINT `T_01_01_02_FK_03` FOREIGN KEY (`F_04`) REFERENCES `t_01_04_07` (`F_00`);

--
-- Constraints for table `t_01_01_05`
--
ALTER TABLE `t_01_01_05`
  ADD CONSTRAINT `T_01_01_05_FK_01` FOREIGN KEY (`F_02`) REFERENCES `t_01_01_02` (`F_00`);

--
-- Constraints for table `t_01_01_06`
--
ALTER TABLE `t_01_01_06`
  ADD CONSTRAINT `T_01_01_06_FK_01` FOREIGN KEY (`F_02`) REFERENCES `t_01_01_05` (`F_00`),
  ADD CONSTRAINT `T_01_01_06_FK_02` FOREIGN KEY (`F_03`) REFERENCES `t_01_01_04` (`F_00`);

--
-- Constraints for table `t_01_01_07`
--
ALTER TABLE `t_01_01_07`
  ADD CONSTRAINT `T_01_01_07_FK_01` FOREIGN KEY (`F_05`) REFERENCES `t_01_01_06` (`F_00`);

--
-- Constraints for table `t_01_02_03`
--
ALTER TABLE `t_01_02_03`
  ADD CONSTRAINT `T_01_02_03_FK_01` FOREIGN KEY (`F_06`) REFERENCES `t_01_02_01` (`F_00`),
  ADD CONSTRAINT `T_01_02_03_FK_02` FOREIGN KEY (`F_07`) REFERENCES `t_01_02_02` (`F_00`),
  ADD CONSTRAINT `T_01_02_03_FK_03` FOREIGN KEY (`F_08`) REFERENCES `t_01_04_07` (`F_00`),
  ADD CONSTRAINT `T_01_02_03_FK_04` FOREIGN KEY (`F_09`) REFERENCES `t_00_01_01` (`F_00`);

--
-- Constraints for table `t_01_03_02`
--
ALTER TABLE `t_01_03_02`
  ADD CONSTRAINT `T_01_03_02_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_01_03_01` (`F_00`),
  ADD CONSTRAINT `T_01_03_02_FK_02` FOREIGN KEY (`F_04`) REFERENCES `t_00_01_01` (`F_00`);

--
-- Constraints for table `t_01_04_03`
--
ALTER TABLE `t_01_04_03`
  ADD CONSTRAINT `T_01_04_03_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_01_04_02` (`F_00`);

--
-- Constraints for table `t_01_04_05`
--
ALTER TABLE `t_01_04_05`
  ADD CONSTRAINT `T_01_04_05_FK_01` FOREIGN KEY (`F_07`) REFERENCES `t_01_04_04` (`F_00`);

--
-- Constraints for table `t_01_04_07`
--
ALTER TABLE `t_01_04_07`
  ADD CONSTRAINT `T_01_04_07_FK_01` FOREIGN KEY (`F_07`) REFERENCES `t_01_04_06` (`F_00`),
  ADD CONSTRAINT `T_01_04_07_FK_02` FOREIGN KEY (`F_08`) REFERENCES `t_01_04_07` (`F_00`);

--
-- Constraints for table `t_01_04_08`
--
ALTER TABLE `t_01_04_08`
  ADD CONSTRAINT `T_01_04_08_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_01_04_07` (`F_00`);

--
-- Constraints for table `t_01_04_09`
--
ALTER TABLE `t_01_04_09`
  ADD CONSTRAINT `T_01_04_09_FK_01` FOREIGN KEY (`F_08`) REFERENCES `t_01_04_02` (`F_00`),
  ADD CONSTRAINT `T_01_04_09_FK_02` FOREIGN KEY (`F_09`) REFERENCES `t_01_04_02` (`F_00`),
  ADD CONSTRAINT `T_01_04_09_FK_03` FOREIGN KEY (`F_10`) REFERENCES `t_00_01_01` (`F_00`);

--
-- Constraints for table `t_01_04_10`
--
ALTER TABLE `t_01_04_10`
  ADD CONSTRAINT `T_01_04_10_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_01_04_09` (`F_00`),
  ADD CONSTRAINT `T_01_04_10_FK_02` FOREIGN KEY (`F_04`) REFERENCES `t_01_04_05` (`F_00`),
  ADD CONSTRAINT `T_01_04_10_FK_03` FOREIGN KEY (`F_05`) REFERENCES `t_01_04_08` (`F_00`);

--
-- Constraints for table `t_02_02_03`
--
ALTER TABLE `t_02_02_03`
  ADD CONSTRAINT `T_02_02_03_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_02_02_01` (`F_00`);

--
-- Constraints for table `t_02_02_05`
--
ALTER TABLE `t_02_02_05`
  ADD CONSTRAINT `T_02_02_05_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_02_02_04` (`F_00`);

--
-- Constraints for table `t_02_02_06`
--
ALTER TABLE `t_02_02_06`
  ADD CONSTRAINT `T_02_02_06_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_02_02_05` (`F_00`);

--
-- Constraints for table `t_02_02_07`
--
ALTER TABLE `t_02_02_07`
  ADD CONSTRAINT `T_02_02_07_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_01_03_02` (`F_00`),
  ADD CONSTRAINT `T_02_02_07_FK_02` FOREIGN KEY (`F_05`) REFERENCES `t_01_03_02` (`F_00`);

--
-- Constraints for table `t_02_02_08`
--
ALTER TABLE `t_02_02_08`
  ADD CONSTRAINT `T_02_02_08_FK_01_` FOREIGN KEY (`F_05`) REFERENCES `t_02_02_02` (`F_00`),
  ADD CONSTRAINT `T_02_02_08_FK_02_` FOREIGN KEY (`F_06`) REFERENCES `t_02_02_06` (`F_00`),
  ADD CONSTRAINT `T_02_02_08_FK_03_` FOREIGN KEY (`F_07`) REFERENCES `t_02_02_03` (`F_00`),
  ADD CONSTRAINT `T_02_02_08_FK_04_` FOREIGN KEY (`F_08`) REFERENCES `t_02_02_07` (`F_00`);

--
-- Constraints for table `t_02_02_09`
--
ALTER TABLE `t_02_02_09`
  ADD CONSTRAINT `T_02_02_09_FK_01` FOREIGN KEY (`F_02`) REFERENCES `t_02_02_08` (`F_00`),
  ADD CONSTRAINT `T_02_02_09_FK_02` FOREIGN KEY (`F_03`) REFERENCES `t_01_04_07` (`F_00`);

--
-- Constraints for table `t_02_03_04`
--
ALTER TABLE `t_02_03_04`
  ADD CONSTRAINT `T_02_03_04_FK_01` FOREIGN KEY (`F_18`) REFERENCES `t_00_01_01` (`F_00`),
  ADD CONSTRAINT `T_02_03_04_FK_02` FOREIGN KEY (`F_19`) REFERENCES `t_02_03_02` (`F_00`),
  ADD CONSTRAINT `T_02_03_04_FK_03` FOREIGN KEY (`F_20`) REFERENCES `t_01_04_01` (`F_00`),
  ADD CONSTRAINT `T_02_03_04_FK_04` FOREIGN KEY (`F_21`) REFERENCES `t_01_04_02` (`F_00`);

--
-- Constraints for table `t_02_03_05`
--
ALTER TABLE `t_02_03_05`
  ADD CONSTRAINT `T_02_03_05_FK_01` FOREIGN KEY (`F_10`) REFERENCES `t_02_03_04` (`F_00`);

--
-- Constraints for table `t_02_03_06`
--
ALTER TABLE `t_02_03_06`
  ADD CONSTRAINT `T_02_03_06_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_02_03_04` (`F_00`),
  ADD CONSTRAINT `T_02_03_06_FK_02` FOREIGN KEY (`F_04`) REFERENCES `t_02_03_05` (`F_00`),
  ADD CONSTRAINT `T_02_03_06_FK_03` FOREIGN KEY (`F_05`) REFERENCES `t_01_02_03` (`F_00`);

--
-- Constraints for table `t_02_03_07`
--
ALTER TABLE `t_02_03_07`
  ADD CONSTRAINT `T_02_03_07_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_02_03_03` (`F_00`),
  ADD CONSTRAINT `T_02_03_07_FK_02` FOREIGN KEY (`F_05`) REFERENCES `t_02_03_04` (`F_00`),
  ADD CONSTRAINT `T_02_03_07_FK_03` FOREIGN KEY (`F_06`) REFERENCES `t_01_02_03` (`F_00`);

--
-- Constraints for table `t_02_04_03`
--
ALTER TABLE `t_02_04_03`
  ADD CONSTRAINT `T_02_04_03_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_02_04_02` (`F_00`);

--
-- Constraints for table `t_02_04_04`
--
ALTER TABLE `t_02_04_04`
  ADD CONSTRAINT `T_02_04_04_FK_01` FOREIGN KEY (`F_15`) REFERENCES `t_02_04_01` (`F_00`),
  ADD CONSTRAINT `T_02_04_04_FK_02` FOREIGN KEY (`F_16`) REFERENCES `t_02_01_04` (`F_00`),
  ADD CONSTRAINT `T_02_04_04_FK_03` FOREIGN KEY (`F_17`) REFERENCES `t_02_02_01` (`F_00`),
  ADD CONSTRAINT `T_02_04_04_FK_04` FOREIGN KEY (`F_18`) REFERENCES `t_02_01_05` (`F_00`),
  ADD CONSTRAINT `T_02_04_04_FK_05` FOREIGN KEY (`F_19`) REFERENCES `t_02_01_02` (`F_00`),
  ADD CONSTRAINT `T_02_04_04_FK_06` FOREIGN KEY (`F_20`) REFERENCES `t_02_01_03` (`F_00`),
  ADD CONSTRAINT `T_02_04_04_FK_07` FOREIGN KEY (`F_21`) REFERENCES `t_02_04_03` (`F_00`);

--
-- Constraints for table `t_02_04_05`
--
ALTER TABLE `t_02_04_05`
  ADD CONSTRAINT `T_02_04_05_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_02_04_04` (`F_00`),
  ADD CONSTRAINT `T_02_04_05_FK_02` FOREIGN KEY (`F_04`) REFERENCES `t_02_03_04` (`F_00`),
  ADD CONSTRAINT `T_02_04_05_FK_03` FOREIGN KEY (`F_05`) REFERENCES `t_00_01_01` (`F_00`),
  ADD CONSTRAINT `T_02_04_05_FK_04` FOREIGN KEY (`F_06`) REFERENCES `t_00_01_01` (`F_00`),
  ADD CONSTRAINT `T_02_04_05_FK_05` FOREIGN KEY (`F_07`) REFERENCES `t_00_01_01` (`F_00`);

--
-- Constraints for table `t_02_05_03`
--
ALTER TABLE `t_02_05_03`
  ADD CONSTRAINT `T_02_05_03_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_02_05_02` (`F_00`);

--
-- Constraints for table `t_02_05_04`
--
ALTER TABLE `t_02_05_04`
  ADD CONSTRAINT `T_02_05_04_FK_01` FOREIGN KEY (`F_16`) REFERENCES `t_02_05_01` (`F_00`),
  ADD CONSTRAINT `T_02_05_04_FK_02` FOREIGN KEY (`F_17`) REFERENCES `t_02_03_04` (`F_00`),
  ADD CONSTRAINT `T_02_05_04_FK_03` FOREIGN KEY (`F_18`) REFERENCES `t_02_01_05` (`F_00`),
  ADD CONSTRAINT `T_02_05_04_FK_04` FOREIGN KEY (`F_19`) REFERENCES `t_02_05_03` (`F_00`),
  ADD CONSTRAINT `T_02_05_04_FK_05` FOREIGN KEY (`F_20`) REFERENCES `t_02_01_02` (`F_00`),
  ADD CONSTRAINT `T_02_05_04_FK_06` FOREIGN KEY (`F_21`) REFERENCES `t_02_01_01` (`F_00`),
  ADD CONSTRAINT `T_02_05_04_FK_07` FOREIGN KEY (`F_22`) REFERENCES `t_02_04_04` (`F_00`),
  ADD CONSTRAINT `T_02_05_04_FK_08` FOREIGN KEY (`F_23`) REFERENCES `t_02_05_04` (`F_00`);

--
-- Constraints for table `t_02_05_05`
--
ALTER TABLE `t_02_05_05`
  ADD CONSTRAINT `T_02_05_05_FK_01` FOREIGN KEY (`F_06`) REFERENCES `t_02_05_04` (`F_00`);

--
-- Constraints for table `t_02_06_03`
--
ALTER TABLE `t_02_06_03`
  ADD CONSTRAINT `T_02_06_03_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_02_06_02` (`F_00`);

--
-- Constraints for table `t_02_06_04`
--
ALTER TABLE `t_02_06_04`
  ADD CONSTRAINT `T_02_06_04_FK_01` FOREIGN KEY (`F_12`) REFERENCES `t_02_05_04` (`F_00`),
  ADD CONSTRAINT `T_02_06_04_FK_02` FOREIGN KEY (`F_13`) REFERENCES `t_02_06_01` (`F_00`),
  ADD CONSTRAINT `T_02_06_04_FK_03` FOREIGN KEY (`F_14`) REFERENCES `t_02_01_05` (`F_00`),
  ADD CONSTRAINT `T_02_06_04_FK_04` FOREIGN KEY (`F_15`) REFERENCES `t_02_06_02` (`F_00`),
  ADD CONSTRAINT `T_02_06_04_FK_05` FOREIGN KEY (`F_16`) REFERENCES `t_02_01_02` (`F_00`),
  ADD CONSTRAINT `T_02_06_04_FK_06` FOREIGN KEY (`F_17`) REFERENCES `t_02_01_01` (`F_00`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
