-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 13 déc. 2025 à 01:04
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `iaas_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `r_t000201_t000203`
--

CREATE TABLE `r_t000201_t000203` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `r_t000201_t000203`
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
-- Structure de la table `r_t000202_t000201`
--

CREATE TABLE `r_t000202_t000201` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `r_t000202_t000201`
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
(12, 8),
(14, 7);

-- --------------------------------------------------------

--
-- Structure de la table `r_t000202_t000203`
--

CREATE TABLE `r_t000202_t000203` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `r_t000202_t000203`
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
-- Structure de la table `r_t000203_t000204`
--

CREATE TABLE `r_t000203_t000204` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `r_t000203_t000204`
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
-- Structure de la table `r_t010203_t010203`
--

CREATE TABLE `r_t010203_t010203` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `r_t020207_t020208`
--

CREATE TABLE `r_t020207_t020208` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `r_t020304_t020301`
--

CREATE TABLE `r_t020304_t020301` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `r_t020404_t010203`
--

CREATE TABLE `r_t020404_t010203` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `r_t020404_t010302`
--

CREATE TABLE `r_t020404_t010302` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `r_t020404_t020208`
--

CREATE TABLE `r_t020404_t020208` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `r_t020504_t010203`
--

CREATE TABLE `r_t020504_t010203` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `r_t020504_t010302`
--

CREATE TABLE `r_t020504_t010302` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `r_t020504_t020208`
--

CREATE TABLE `r_t020504_t020208` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `r_t020604_t010203`
--

CREATE TABLE `r_t020604_t010203` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `r_t020604_t010302`
--

CREATE TABLE `r_t020604_t010302` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_00_01_01`
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
-- Structure de la table `t_00_02_01`
--

CREATE TABLE `t_00_02_01` (
  `F_00` bigint(20) NOT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_01` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_00_02_01`
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
-- Structure de la table `t_00_02_02`
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
-- Déchargement des données de la table `t_00_02_02`
--

INSERT INTO `t_00_02_02` (`F_00`, `F_04`, `F_05`, `F_06`, `F_02`, `F_07`, `F_03`, `F_01`) VALUES
(1, b'1', b'1', b'1', 'superadmin@iaas.dz', b'1', '$2a$12$0rC72g02l4Gpt4sBDiGVLeNBVsyl1cM2K9Y1BXatvqgEsgKBFIi/S', 'superadmin'),
(2, b'1', b'1', b'1', 'admin@iaas.dz', b'1', '$2a$12$0rC72g02l4Gpt4sBDiGVLeNBVsyl1cM2K9Y1BXatvqgEsgKBFIi/S', 'admin'),
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
-- Structure de la table `t_00_02_03`
--

CREATE TABLE `t_00_02_03` (
  `F_00` bigint(20) NOT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_01` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_00_02_03`
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
-- Structure de la table `t_00_02_04`
--

CREATE TABLE `t_00_02_04` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(20) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_01` varchar(100) NOT NULL,
  `F_03` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_00_02_04`
--

INSERT INTO `t_00_02_04` (`F_00`, `F_04`, `F_02`, `F_01`, `F_03`) VALUES
(1, NULL, 'Read system configuration', 'SYSTEM:READ', '1'),
(2, NULL, 'Modify system settings', 'SYSTEM:WRITE', '1'),
(3, NULL, 'Delete system data', 'SYSTEM:DELETE', '1'),
(4, NULL, 'Full system administration', 'SYSTEM:ADMIN', '1'),
(5, NULL, 'View user profiles', 'USER:READ', '2'),
(6, NULL, 'Create and modify users', 'USER:WRITE', '2'),
(7, NULL, 'Delete user accounts', 'USER:DELETE', '2'),
(8, NULL, 'Full user management', 'USER:ADMIN', '2'),
(9, NULL, 'View roles and assignments', 'ROLE:READ', '3'),
(10, NULL, 'Create and modify roles', 'ROLE:WRITE', '3'),
(11, NULL, 'Delete roles', 'ROLE:DELETE', '3'),
(12, NULL, 'Full role management', 'ROLE:ADMIN', '3'),
(13, NULL, 'View permissions', 'PERMISSION:READ', '4'),
(14, NULL, 'Modify permission assignments', 'PERMISSION:WRITE', '4'),
(15, NULL, 'Remove permissions', 'PERMISSION:DELETE', '4'),
(16, NULL, 'View groups and memberships', 'GROUP:READ', '5'),
(17, NULL, 'Create and modify groups', 'GROUP:WRITE', '5'),
(18, NULL, 'Delete groups', 'GROUP:DELETE', '5'),
(19, NULL, 'View documents', 'DOCUMENT:READ', '6'),
(20, NULL, 'Create and edit documents', 'DOCUMENT:WRITE', '6'),
(21, NULL, 'Delete documents', 'DOCUMENT:DELETE', '6'),
(22, NULL, 'Approve documents', 'DOCUMENT:APPROVE', '6'),
(23, NULL, 'View audit logs', 'AUDIT:READ', '7'),
(24, NULL, 'Create audit entries', 'AUDIT:WRITE', '7'),
(25, NULL, 'Export audit reports', 'AUDIT:EXPORT', '7'),
(26, NULL, 'View financial data', 'FINANCE:READ', '8'),
(27, NULL, 'Create financial records', 'FINANCE:WRITE', '8'),
(28, NULL, 'Approve financial transactions', 'FINANCE:APPROVE', '8'),
(29, NULL, 'View reports', 'REPORT:READ', '9'),
(30, NULL, 'Generate reports', 'REPORT:WRITE', '9'),
(31, NULL, 'Export reports', 'REPORT:EXPORT', '9');

-- --------------------------------------------------------

--
-- Structure de la table `t_00_02_05`
--

CREATE TABLE `t_00_02_05` (
  `F_00` bigint(20) NOT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_01` varchar(50) NOT NULL,
  `F_03` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_00_02_05`
--

INSERT INTO `t_00_02_05` (`F_00`, `F_02`, `F_01`, `F_03`) VALUES
(1, 'System-level operations and administration', 'SYSTEM', NULL),
(2, 'User account management operations', 'USER', NULL),
(3, 'Role management operations', 'ROLE', NULL),
(4, 'Permission management operations', 'PERMISSION', NULL),
(5, 'Group and department management', 'GROUP', NULL),
(6, 'Document management operations', 'DOCUMENT', NULL),
(7, 'Audit log access and reporting', 'AUDIT', NULL),
(8, 'Financial operations and approvals', 'FINANCE', NULL),
(9, 'Report generation and access', 'REPORT', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `t_00_03_01`
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
-- Structure de la table `t_00_04_01`
--

CREATE TABLE `t_00_04_01` (
  `F_00` bigint(20) NOT NULL,
  `F_02` datetime(6) NOT NULL,
  `F_01` varchar(255) NOT NULL,
  `F_03` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_01_01_01`
--

CREATE TABLE `t_01_01_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_01_01_01`
--

INSERT INTO `t_01_01_01` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'س', 'S', '');

-- --------------------------------------------------------

--
-- Structure de la table `t_01_01_02`
--

CREATE TABLE `t_01_01_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL,
  `F_04` int(11) DEFAULT NULL,
  `F_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_01_01_04`
--

CREATE TABLE `t_01_01_04` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(50) NOT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL,
  `F_05` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_01_01_04`
--

INSERT INTO `t_01_01_04` (`F_00`, `F_04`, `F_01`, `F_02`, `F_03`, `F_05`) VALUES
(1, 'Premier Etage', '1°', 'الطابق الأول', 'First Floor', NULL),
(2, 'Deuxième Etage', '2°', 'الطابق الأول', 'Second Floor', NULL),
(3, 'Troisième Etage', '3°', 'الطابق الأول', 'Third Floor', NULL),
(4, 'Quatrième Etage', '4°', 'الطابق الأول', 'Fourth Floor', NULL),
(5, 'Cinquième Etage', '5°', 'الطابق الأول', 'Fifth Floor', NULL),
(6, 'Siesième Etage', '6°', 'الطابق الأول', 'Sixth Floor', NULL),
(7, 'Septième Etage', '7°', 'الطابق الأول', 'Seventh Floor', NULL),
(8, 'Huitième Etage', '8°', 'الطابق الأول', 'Eigth Floor', NULL),
(9, 'Neuvième Etage', '9°', 'الطابق الأول', 'Nineth Floor', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `t_01_01_05`
--

CREATE TABLE `t_01_01_05` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(50) NOT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL,
  `F_06` int(11) DEFAULT NULL,
  `F_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_01_01_07`
--

CREATE TABLE `t_01_01_07` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(50) NOT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_01_01_08`
--

CREATE TABLE `t_01_01_08` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(50) NOT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_04` int(11) DEFAULT NULL,
  `F_03` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_01_02_01`
--

CREATE TABLE `t_01_02_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_01_02_01`
--

INSERT INTO `t_01_02_01` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'الصادر', 'Outgoing', 'Départ'),
(2, 'الوارد', 'Arriving', 'Arrivé');

-- --------------------------------------------------------

--
-- Structure de la table `t_01_02_02`
--

CREATE TABLE `t_01_02_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_01_02_02`
--

INSERT INTO `t_01_02_02` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'مرسوم', 'Decree', 'Décret'),
(2, 'قرار', 'Administrative Decree', 'Arrété'),
(3, 'أمر', 'Order', 'Ordonnance'),
(4, 'منشور', 'Circular', 'Circulaire'),
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
-- Structure de la table `t_01_02_03`
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
-- Structure de la table `t_01_03_01`
--

CREATE TABLE `t_01_03_01` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(20) NOT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_01_03_01`
--

INSERT INTO `t_01_03_01` (`F_00`, `F_04`, `F_01`, `F_02`, `F_03`) VALUES
(1, '100', 'بطاقة اقتراح', 'Proposal Form', 'Fiche de Proposition'),
(2, '100', 'بطاقة تقنية وصفية', 'Descriptive Technical Sheet', 'Fiche Technique Descriptive'),
(3, '100', 'بطاقة تقنية', 'Technical Sheet', 'Fiche Technique'),
(4, '111', 'تقرير تقديم', 'Presentation Report', 'Rapport de Présentation'),
(5, '111', 'تقرير تقديم تكميلي', 'Supplementary Presentation Report', 'Rapport de Présentation Complémentaire'),
(6, '11', 'بطاقة تحليلية', 'Analytical Sheet', 'Fiche Analytique'),
(7, '111', 'بطاقة الإقتطاع الميزانياتي', 'Budget Allocation Sheet', 'Fiche d\'Imputation Budgétaire'),
(8, '111', 'تأشيرة اللجنة القطاعية للصفقات', 'CSM Visa', 'Visa CSM'),
(9, '100', 'لوحة إعلانية', 'Advertising Board', 'Placard publicitaire'),
(10, '100', 'دفتر الشروط', 'Terms of Reference', 'Cahier des Charges'),
(11, '10', 'عقد / صفقة', 'Contract', 'Contrat / Marché'),
(12, '1', 'ملحق عقد', 'Amendment', 'Avenant'),
(13, '10', 'أمر شراء', 'Purchase Order', 'Bon de Commande'),
(14, '100', 'بطاقة تمديد الآجال', 'Deadline Extension Form', 'Fiche de Prorogation des Délais'),
(15, '100', 'لوحة إعلانية (تمديد الآجال)', 'Advertising Board (Deadline Extension)', 'Placard publicitaire (Prorogation Délai)'),
(16, '100', 'محضر فتح الأظرف الإدارية', 'Minutes of Administrative Bid Opening', 'PV Ouverture Administratif des plis'),
(17, '100', 'محضر فتح الأظرف التقنية', 'Minutes of Technical Bid Opening', 'PV Ouverture Technique'),
(18, '100', 'محضر التقييم التقني', 'Technical Evaluation Minutes', 'PV Evaluation Technique'),
(19, '100', 'محضر فتح الأظرف المالية', 'Minutes of Financial Bid Opening', 'PV Ouverture Financière'),
(20, '100', 'محضر التقييم المالي', 'Financial Evaluation Minutes', 'PV Evaluation Financière'),
(21, '100', 'محضر الإغلاق', 'Closing Minutes', 'PV de Cloture'),
(22, '100', 'مداولة الاختيار المؤقت', 'Provisional Selection Deliberation', 'Délibération du choix Provisoire'),
(23, '11', 'محضر لجنة فتح الأظرفة وتقييم العروض', 'COPEO Minutes', 'PV COPEO'),
(24, '111', 'محضر لجنة فتح الأظرفة وتقييم العروض التكميلي', 'Supplementary COPEO Minutes', 'PV COPEO Complémentaire'),
(25, '11', 'أمر خدمة', 'Service Order', 'Ordre de Service'),
(26, '100', 'كفالة التعهد', 'Bid Bond', 'Caution de Soumission'),
(27, '100', 'كفالة حسن التنفيذ', 'Performance Bond', 'Caution de Bonne Exécution'),
(28, '100', 'كفالة الضمان', 'Guarantee Bond', 'Caution de Garantie'),
(29, '100', 'الملف الإداري', 'Administrative File', 'Dossier Administratif'),
(30, '100', 'العرض المالي', 'Financial Offer', 'Offre Financière'),
(31, '100', 'العرض الفني', 'Technical Offer', 'Offre Technique');

-- --------------------------------------------------------

--
-- Structure de la table `t_01_03_02`
--

CREATE TABLE `t_01_03_02` (
  `F_00` bigint(20) NOT NULL,
  `F_03` datetime(6) DEFAULT NULL,
  `F_01` varchar(50) NOT NULL,
  `F_02` varchar(500) DEFAULT NULL,
  `F_04` bigint(20) NOT NULL,
  `F_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_01_04_01`
--

CREATE TABLE `t_01_04_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(3) NOT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_01_04_02`
--

CREATE TABLE `t_01_04_02` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(10) NOT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL,
  `F_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_01_04_03`
--

CREATE TABLE `t_01_04_03` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(10) NOT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL,
  `F_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_01_04_04`
--

CREATE TABLE `t_01_04_04` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(10) NOT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_01_04_05`
--

CREATE TABLE `t_01_04_05` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(100) DEFAULT NULL,
  `F_05` varchar(100) DEFAULT NULL,
  `F_06` varchar(100) NOT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_01_04_06`
--

CREATE TABLE `t_01_04_06` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(100) DEFAULT NULL,
  `F_05` varchar(100) DEFAULT NULL,
  `F_06` varchar(100) NOT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL,
  `F_09` int(11) DEFAULT NULL,
  `F_08` int(11) NOT NULL,
  `F_07` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_01_04_07`
--

CREATE TABLE `t_01_04_07` (
  `F_00` bigint(20) NOT NULL,
  `F_05` datetime(6) DEFAULT NULL,
  `F_06` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) NOT NULL,
  `F_04` varchar(100) NOT NULL,
  `F_01` varchar(100) NOT NULL,
  `F_03` varchar(100) NOT NULL,
  `F_07` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_01_04_08`
--

CREATE TABLE `t_01_04_08` (
  `F_00` bigint(20) NOT NULL,
  `F_06` bit(1) DEFAULT NULL,
  `F_05` varchar(50) DEFAULT NULL,
  `F_02` bigint(20) DEFAULT NULL,
  `F_01` bigint(20) NOT NULL,
  `F_04` bigint(20) DEFAULT NULL,
  `F_03` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_01_04_09`
--

CREATE TABLE `t_01_04_09` (
  `F_00` bigint(20) NOT NULL,
  `F_06` varchar(50) NOT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL,
  `F_04` bigint(20) DEFAULT NULL,
  `F_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_01_04_10`
--

CREATE TABLE `t_01_04_10` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(100) DEFAULT NULL,
  `F_05` varchar(100) DEFAULT NULL,
  `F_06` varchar(100) NOT NULL,
  `F_09` bit(1) DEFAULT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL,
  `F_07` int(11) DEFAULT NULL,
  `F_08` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_02_01_01`
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

-- --------------------------------------------------------

--
-- Structure de la table `t_02_01_02`
--

CREATE TABLE `t_02_01_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_02_01_02`
--

INSERT INTO `t_02_01_02` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'غير قابل للتطبيق', 'Not Applicabale', 'Non applicable'),
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
-- Structure de la table `t_02_01_03`
--

CREATE TABLE `t_02_01_03` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(300) DEFAULT NULL,
  `F_02` varchar(300) DEFAULT NULL,
  `F_03` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_02_01_03`
--

INSERT INTO `t_02_01_03` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'المديرية الفرعية للإنجازات', 'Sub Directorat of Realizations', 'Sous Direction Réalisations'),
(2, 'دائرة المؤن', 'Approvisionment Department', 'Département des Approvisionnements');

-- --------------------------------------------------------

--
-- Structure de la table `t_02_01_04`
--

CREATE TABLE `t_02_01_04` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_02_01_04`
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
-- Structure de la table `t_02_01_05`
--

CREATE TABLE `t_02_01_05` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_02_01_05`
--

INSERT INTO `t_02_01_05` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'قيد الانتظار', 'On Hold', 'En Instance'),
(2, 'قيد التنفيذ', 'Ongoing', 'En cours'),
(3, 'غيرمجدي', 'Defective', 'Infrectieux'),
(4, 'مُنجز', 'Finalized', 'Finalisé'),
(5, 'ملغي', 'Canceled', 'Annulé'),
(6, 'مؤجل', 'Deferred', 'Différé'),
(7, 'منقول', 'Transferred', 'Transféré');

-- --------------------------------------------------------

--
-- Structure de la table `t_02_02_01`
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
-- Déchargement des données de la table `t_02_02_01`
--

INSERT INTO `t_02_02_01` (`F_00`, `F_04`, `F_05`, `F_06`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'تسيير', 'F', 'F', 'ميزانية التسيير', 'Functional Budget', 'Budget de Fonctionnement'),
(2, 'تجهيز', 'E', 'E', 'ميزانية التجهيز', 'Equipment Budget', 'Budget d\'Equipement');

-- --------------------------------------------------------

--
-- Structure de la table `t_02_02_02`
--

CREATE TABLE `t_02_02_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_02_02_03`
--

CREATE TABLE `t_02_02_03` (
  `F_00` bigint(20) NOT NULL,
  `F_02` varchar(4) NOT NULL,
  `F_01` varchar(200) NOT NULL,
  `F_03` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_02_02_04`
--

CREATE TABLE `t_02_02_04` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_02_02_05`
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
-- Structure de la table `t_02_02_06`
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
-- Structure de la table `t_02_02_07`
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
-- Structure de la table `t_02_02_08`
--

CREATE TABLE `t_02_02_08` (
  `F_00` bigint(20) NOT NULL,
  `F_04` double DEFAULT NULL,
  `F_01` varchar(200) NOT NULL,
  `F_03` double DEFAULT NULL,
  `F_02` double DEFAULT NULL,
  `F_06` bigint(20) NOT NULL,
  `F_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_02_02_09`
--

CREATE TABLE `t_02_02_09` (
  `F_00` bigint(20) NOT NULL,
  `F_01` float DEFAULT NULL,
  `F_02` bigint(20) NOT NULL,
  `F_03` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_02_03_01`
--

CREATE TABLE `t_02_03_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) DEFAULT NULL,
  `F_04` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_02_03_02`
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
-- Déchargement des données de la table `t_02_03_02`
--

INSERT INTO `t_02_03_02` (`F_00`, `F_04`, `F_05`, `F_06`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'ش ط', 'NP', 'PP', 'شخص طبيعي', 'Natural Person', 'Personne Physique'),
(2, 'ش م', 'LP', 'PM', 'شخص معنوي', 'Legal Entity', 'Personne Morale'),
(3, 'ش ذ م م ش و', 'OPLLC', 'EURL', 'شركة ذات مسؤولية محدودة لشخص واحد', 'One-Person Limited Liability Company', 'Entreprise Unipersonnelle à Responsabilité Limitée﻿'),
(4, 'ش ذ م م', 'LLC', 'SARL', 'شركة ذات مسؤولية محدودة', 'Limited Liability Company', 'Société à Responsabilité Limitée﻿'),
(5, 'ش ذ أ', 'JSC', 'SPA', 'شركة ذات أسهم', 'Joint Stock Company', 'Société par Action﻿'),
(6, 'ش م م', 'SJSC', 'SPAS', 'شركة مساهمة مبسطة', 'Simplified Joint Stock Company', 'Société par Action Simplifiée﻿'),
(7, 'ش ت', 'GP', 'SNC', 'شركة تضامن', 'General Partnership', 'Société en Nom Collectif﻿'),
(8, 'ت', 'C', 'G', 'تجمع', 'Grouping / Consortium', 'Groupement'),
(9, 'ش م إ', 'PLC', 'SA', 'شركة مجهولة الاسم', 'Public Limited Company', 'Société Anonyme﻿'),
(10, 'م ع ذ ط ص ت', 'PICE', 'EPIC', 'مؤسسة عمومية ذات طابع صناعي وتجاري', 'Public Industrial and Commercial Establishment', 'Etablissement Public à Caractère Industriel et Commerciale﻿'),
(11, 'م ع ذ ط إ', 'PAE', 'EPA', 'مؤسسة عمومية ذات إداري', 'Public Administrative Establishment', 'Etablissement Public à Caractère Administratif');

-- --------------------------------------------------------

--
-- Structure de la table `t_02_03_03`
--

CREATE TABLE `t_02_03_03` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_02_03_03`
--

INSERT INTO `t_02_03_03` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'إقصاء مؤقت', 'Temporary exclusion', 'Exclusion à titre temporale'),
(2, 'إقصاء نهائي', 'Permanent exclusion', 'Exclusion à titre definitif');

-- --------------------------------------------------------

--
-- Structure de la table `t_02_03_04`
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
  `F_21` bigint(20) DEFAULT NULL,
  `F_18` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_02_03_05`
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
-- Structure de la table `t_02_03_06`
--

CREATE TABLE `t_02_03_06` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL,
  `F_04` bigint(20) NOT NULL,
  `F_05` bigint(20) NOT NULL,
  `F_06` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_02_03_07`
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
-- Structure de la table `t_02_04_01`
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
-- Déchargement des données de la table `t_02_04_01`
--

INSERT INTO `t_02_04_01` (`F_00`, `F_04`, `F_05`, `F_06`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'ط ع م  و', 'NOTC', 'AOON', 'طلب عروض مفتوح وطني', 'National Open Tender Call', 'Appel d\'Offres Ouvert National'),
(2, 'ط ع م و د', 'NIOTC', 'AOONI', 'طلب عروض مفتوح وطني ودولي', 'National and International Open Tender Call', 'Appel d\'Offres Ouvert National et International'),
(3, 'ت ب', 'SDN', 'GG / S', 'التراضي البسيط', 'Simple Direct Negotiation', 'Gré à Gré Simple'),
(4, 'ت ب إ', 'NAC', 'GG / AC', 'التراضي بعد الاستشارة', 'Negotiation after Consultation', 'Gré à Gré après Consultation'),
(5, 'إد', 'IC(AP)', 'CI(PA)', 'استشارة داخلية', 'Internal Consultation (Adapted Procedure)', 'Consultation Interne (Procédure Adaptée)');

-- --------------------------------------------------------

--
-- Structure de la table `t_02_04_02`
--

CREATE TABLE `t_02_04_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_02_04_02`
--

INSERT INTO `t_02_04_02` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'مبرمجة', 'Planed', 'Programmée'),
(2, 'قيد الإعداد', 'In Progress', 'En cours d\'élaboration'),
(3, 'قيد المراجعة', 'Under Review', 'En cours de contrôle'),
(4, 'قيد النشر', 'In Publication', 'En cours de publication'),
(5, 'قيد التقييم', 'Under Evaluation', 'En cours d\'évaluation');

-- --------------------------------------------------------

--
-- Structure de la table `t_02_04_03`
--

CREATE TABLE `t_02_04_03` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL,
  `F_04` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_02_04_03`
--

INSERT INTO `t_02_04_03` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`) VALUES
(1, 'مرحلة إعداد المخطط الميزانياتي', 'Budget Plan Maturation Stage', 'Instance de maturation de plan budgétaire', 1),
(2, 'مرحلة تحديد الإحتياجات', 'Needs Identification Stage', 'Instance du besoin', 1),
(3, 'مرحلة إعداد البطاقة التقنية', 'Technical Data Sheet Preparation Stage', 'Instance de la fiche technique', 1),
(4, 'مرحلة موافقة القيادة العليا', 'High Command Approval Stage', 'Instance Accord du Haut Commandement', 1),
(5, 'مرحلة إبداء الرأي', 'Opinion Stage', 'Instance d\'avis', 1),
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
-- Structure de la table `t_02_04_04`
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
-- Structure de la table `t_02_04_05`
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
-- Structure de la table `t_02_05_01`
--

CREATE TABLE `t_02_05_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_02_05_01`
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
-- Structure de la table `t_02_05_02`
--

CREATE TABLE `t_02_05_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_02_05_02`
--

INSERT INTO `t_02_05_02` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'مبرمجة', 'Planed', 'Programmée'),
(2, 'قيد الإعداد', 'In Progress', 'En cours d\'élaboration'),
(3, 'قيد المراجعة', 'Under Review', 'En cours de contrôle'),
(4, 'قيد الإلتزام', 'Under Engagement', 'En cours dengagement');

-- --------------------------------------------------------

--
-- Structure de la table `t_02_05_03`
--

CREATE TABLE `t_02_05_03` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL,
  `F_04` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_02_05_03`
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
-- Structure de la table `t_02_05_04`
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
  `F_18` bigint(20) NOT NULL,
  `F_17` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_02_05_05`
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
-- Structure de la table `t_02_06_01`
--

CREATE TABLE `t_02_06_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_02_06_01`
--

INSERT INTO `t_02_06_01` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'ملحق عقد', 'Amendment', 'Avenant'),
(2, 'ملحق تعديل', 'Adjustment Amendment', 'Avenant d\'Ajustement'),
(3, 'ملحق نقل الحقوق والالتزامات', 'Amendment for Transfer of Rights and Obligations', 'Avenant de transfert des droits et des obligations'),
(4, 'ملحق تعديل الكميات النهائية', 'Amendment for Adjustment of Final Quantities', 'Avenant d\'Ajustement des Quantités Définitives');

-- --------------------------------------------------------

--
-- Structure de la table `t_02_06_02`
--

CREATE TABLE `t_02_06_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_02_06_02`
--

INSERT INTO `t_02_06_02` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'مبرمجة﻿', 'Planed', 'Programmée'),
(2, 'قيد الإعداد', 'In Progress', 'En cours d\'élaboration'),
(3, 'قيد المراجعة﻿', 'Under Review', 'En cours de contrôle'),
(4, 'قيد الإلتزام', 'Under Engagement', 'En cours d\'engagement');

-- --------------------------------------------------------

--
-- Structure de la table `t_02_06_03`
--

CREATE TABLE `t_02_06_03` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL,
  `F_04` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_02_06_03`
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
-- Structure de la table `t_02_06_04`
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

-- --------------------------------------------------------

--
-- Structure de la table `t_03_01_01`
--

CREATE TABLE `t_03_01_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_03_01_02`
--

CREATE TABLE `t_03_01_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(100) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_03_01_03`
--

CREATE TABLE `t_03_01_03` (
  `F_00` bigint(20) NOT NULL,
  `F_02` varchar(50) NOT NULL,
  `F_03` double NOT NULL,
  `F_04` double NOT NULL,
  `F_01` varchar(100) NOT NULL,
  `F_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_03_01_04`
--

CREATE TABLE `t_03_01_04` (
  `F_00` bigint(20) NOT NULL,
  `F_02` varchar(50) NOT NULL,
  `F_03` double NOT NULL,
  `F_08` varchar(500) NOT NULL,
  `F_05` double NOT NULL,
  `F_07` bit(1) NOT NULL,
  `F_01` varchar(100) NOT NULL,
  `F_06` double NOT NULL,
  `F_04` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_03_01_05`
--

CREATE TABLE `t_03_01_05` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(50) NOT NULL,
  `F_02` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `r_t000201_t000203`
--
ALTER TABLE `r_t000201_t000203`
  ADD PRIMARY KEY (`F_01`,`F_02`),
  ADD KEY `R_T000201_T000203_FK_02` (`F_02`);

--
-- Index pour la table `r_t000202_t000201`
--
ALTER TABLE `r_t000202_t000201`
  ADD PRIMARY KEY (`F_01`,`F_02`),
  ADD KEY `R_T000202_T000201_FK_02` (`F_02`);

--
-- Index pour la table `r_t000202_t000203`
--
ALTER TABLE `r_t000202_t000203`
  ADD PRIMARY KEY (`F_01`,`F_02`),
  ADD KEY `R_T000202_T000203_FK_02` (`F_02`);

--
-- Index pour la table `r_t000203_t000204`
--
ALTER TABLE `r_t000203_t000204`
  ADD PRIMARY KEY (`F_01`,`F_02`),
  ADD KEY `R_T000203_T000204_FK_02` (`F_02`);

--
-- Index pour la table `r_t010203_t010203`
--
ALTER TABLE `r_t010203_t010203`
  ADD UNIQUE KEY `R_T010203_T010203_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T010203_T010203_FK_02` (`F_02`);

--
-- Index pour la table `r_t020207_t020208`
--
ALTER TABLE `r_t020207_t020208`
  ADD UNIQUE KEY `R_T020207_T020208_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020207_T020208_FK_02` (`F_02`);

--
-- Index pour la table `r_t020304_t020301`
--
ALTER TABLE `r_t020304_t020301`
  ADD UNIQUE KEY `R_T020304_T020301_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020304_T020301_FK_02` (`F_02`);

--
-- Index pour la table `r_t020404_t010203`
--
ALTER TABLE `r_t020404_t010203`
  ADD UNIQUE KEY `R_T020404_T010203_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020404_T010203_FK_02` (`F_02`);

--
-- Index pour la table `r_t020404_t010302`
--
ALTER TABLE `r_t020404_t010302`
  ADD UNIQUE KEY `R_T020404_T010302_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020404_T010302_FK_02` (`F_02`);

--
-- Index pour la table `r_t020404_t020208`
--
ALTER TABLE `r_t020404_t020208`
  ADD UNIQUE KEY `R_T020404_T020208_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020404_T020208_FK_02` (`F_02`);

--
-- Index pour la table `r_t020504_t010203`
--
ALTER TABLE `r_t020504_t010203`
  ADD UNIQUE KEY `R_T020504_T010203_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020504_T010203_FK_02` (`F_02`);

--
-- Index pour la table `r_t020504_t010302`
--
ALTER TABLE `r_t020504_t010302`
  ADD UNIQUE KEY `R_T020504_T010302_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020504_T010302_FK_02` (`F_02`);

--
-- Index pour la table `r_t020504_t020208`
--
ALTER TABLE `r_t020504_t020208`
  ADD UNIQUE KEY `R_T020504_T020208_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020504_T020208_FK_02` (`F_02`);

--
-- Index pour la table `r_t020604_t010203`
--
ALTER TABLE `r_t020604_t010203`
  ADD UNIQUE KEY `R_T020604_T010203_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020604_T010203_FK_02` (`F_02`);

--
-- Index pour la table `r_t020604_t010302`
--
ALTER TABLE `r_t020604_t010302`
  ADD UNIQUE KEY `R_T020604_T010302_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020604_T010302_FK_02` (`F_02`);

--
-- Index pour la table `t_00_01_01`
--
ALTER TABLE `t_00_01_01`
  ADD PRIMARY KEY (`F_00`);

--
-- Index pour la table `t_00_02_01`
--
ALTER TABLE `t_00_02_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_00_02_01_UK_01` (`F_01`);

--
-- Index pour la table `t_00_02_02`
--
ALTER TABLE `t_00_02_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_00_02_02_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_00_02_02_UK_02` (`F_02`);

--
-- Index pour la table `t_00_02_03`
--
ALTER TABLE `t_00_02_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_00_02_03_UK_01` (`F_01`);

--
-- Index pour la table `t_00_02_04`
--
ALTER TABLE `t_00_02_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_00_02_04_UK_01` (`F_01`);

--
-- Index pour la table `t_00_02_05`
--
ALTER TABLE `t_00_02_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_00_02_05_UK_01` (`F_01`);

--
-- Index pour la table `t_00_03_01`
--
ALTER TABLE `t_00_03_01`
  ADD PRIMARY KEY (`F_00`);

--
-- Index pour la table `t_00_04_01`
--
ALTER TABLE `t_00_04_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_00_04_01_UK_01` (`F_03`);

--
-- Index pour la table `t_01_01_01`
--
ALTER TABLE `t_01_01_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_01_01_UK_01` (`F_03`);

--
-- Index pour la table `t_01_01_02`
--
ALTER TABLE `t_01_01_02`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_01_01_02_FK_01` (`F_05`);

--
-- Index pour la table `t_01_01_04`
--
ALTER TABLE `t_01_01_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_01_04_UK_01` (`F_04`);

--
-- Index pour la table `t_01_01_05`
--
ALTER TABLE `t_01_01_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_01_05_UK_01` (`F_04`),
  ADD KEY `T_01_01_05_FK_01` (`F_05`);

--
-- Index pour la table `t_01_01_07`
--
ALTER TABLE `t_01_01_07`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_01_07_UK_01` (`F_01`),
  ADD KEY `T_01_01_07_FK_01` (`F_03`);

--
-- Index pour la table `t_01_01_08`
--
ALTER TABLE `t_01_01_08`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_01_08_UK_01` (`F_01`),
  ADD KEY `T_01_01_08_FK_01` (`F_03`);

--
-- Index pour la table `t_01_02_01`
--
ALTER TABLE `t_01_02_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_02_01_UK_01` (`F_03`);

--
-- Index pour la table `t_01_02_02`
--
ALTER TABLE `t_01_02_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_02_02_UK_01` (`F_03`);

--
-- Index pour la table `t_01_02_03`
--
ALTER TABLE `t_01_02_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_02_03_UK_01` (`F_01`),
  ADD KEY `T_01_02_03_FK_04` (`F_09`),
  ADD KEY `T_01_02_03_FK_01` (`F_06`),
  ADD KEY `T_01_02_03_FK_02` (`F_07`),
  ADD KEY `T_01_02_03_FK_03` (`F_08`);

--
-- Index pour la table `t_01_03_01`
--
ALTER TABLE `t_01_03_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_03_01_UK_01` (`F_03`);

--
-- Index pour la table `t_01_03_02`
--
ALTER TABLE `t_01_03_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_03_02_UK_01` (`F_01`),
  ADD KEY `T_01_03_02_FK_01` (`F_04`),
  ADD KEY `T_01_03_02_FK_02` (`F_05`);

--
-- Index pour la table `t_01_04_01`
--
ALTER TABLE `t_01_04_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_01_UK_01` (`F_03`);

--
-- Index pour la table `t_01_04_02`
--
ALTER TABLE `t_01_04_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_02_UK_01` (`F_04`),
  ADD KEY `FKtgbadkunmn8aymon9yg86chh3` (`F_05`);

--
-- Index pour la table `t_01_04_03`
--
ALTER TABLE `t_01_04_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_03_UK_01` (`F_04`),
  ADD KEY `FKh3yoxlj36bdarasi5oxtsuoq3` (`F_05`);

--
-- Index pour la table `t_01_04_04`
--
ALTER TABLE `t_01_04_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_04_UK_01` (`F_03`);

--
-- Index pour la table `t_01_04_05`
--
ALTER TABLE `t_01_04_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_05_UK_03` (`F_06`),
  ADD UNIQUE KEY `T_01_04_05_UK_01` (`F_04`),
  ADD UNIQUE KEY `T_01_04_05_UK_02` (`F_05`);

--
-- Index pour la table `t_01_04_06`
--
ALTER TABLE `t_01_04_06`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_06_UK_03` (`F_06`),
  ADD UNIQUE KEY `T_01_04_06_UK_01` (`F_04`),
  ADD UNIQUE KEY `T_01_04_06_UK_02` (`F_05`),
  ADD KEY `FK884fi5wdj7bh5wwo0k17xav5s` (`F_07`);

--
-- Index pour la table `t_01_04_07`
--
ALTER TABLE `t_01_04_07`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `FK1vpdlpkcwt5tnxuprpqskqn9d` (`F_07`);

--
-- Index pour la table `t_01_04_08`
--
ALTER TABLE `t_01_04_08`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `FKqeelvfiftnnueele68of5qgdu` (`F_02`),
  ADD KEY `FKltwtnfsf1elnwvwgnh0oueb4p` (`F_01`),
  ADD KEY `FKgycvlba096xtpr1vubh1o3tll` (`F_04`),
  ADD KEY `FK1k8cpo0jrmaenm6dpp00tpaef` (`F_03`);

--
-- Index pour la table `t_01_04_09`
--
ALTER TABLE `t_01_04_09`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_09_UK_01` (`F_06`),
  ADD KEY `FKabm0aw2hqmliouvo9p2cpcvho` (`F_04`),
  ADD KEY `FK6j23bnilfb6pe63ism3lguoc2` (`F_05`);

--
-- Index pour la table `t_01_04_10`
--
ALTER TABLE `t_01_04_10`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_10_UK_03` (`F_06`),
  ADD UNIQUE KEY `T_01_04_10_UK_01` (`F_04`),
  ADD UNIQUE KEY `T_01_04_10_UK_02` (`F_05`);

--
-- Index pour la table `t_02_01_01`
--
ALTER TABLE `t_02_01_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_01_01_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_02_01_01_UK_02` (`F_02`),
  ADD UNIQUE KEY `T_02_01_01_UK_03` (`F_03`),
  ADD UNIQUE KEY `T_02_01_01_UK_04` (`F_04`),
  ADD UNIQUE KEY `T_02_01_01_UK_05` (`F_05`,`F_06`,`F_07`);

--
-- Index pour la table `t_02_01_02`
--
ALTER TABLE `t_02_01_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_01_02_UK_01` (`F_03`);

--
-- Index pour la table `t_02_01_03`
--
ALTER TABLE `t_02_01_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_01_03_UK_01` (`F_03`);

--
-- Index pour la table `t_02_01_04`
--
ALTER TABLE `t_02_01_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_01_04_UK_01` (`F_03`);

--
-- Index pour la table `t_02_01_05`
--
ALTER TABLE `t_02_01_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_01_05_UK_01` (`F_03`);

--
-- Index pour la table `t_02_02_01`
--
ALTER TABLE `t_02_02_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_02_01_UK_01` (`F_03`),
  ADD UNIQUE KEY `T_02_02_01_UK_02` (`F_06`);

--
-- Index pour la table `t_02_02_02`
--
ALTER TABLE `t_02_02_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_02_02_UK_01` (`F_03`);

--
-- Index pour la table `t_02_02_03`
--
ALTER TABLE `t_02_02_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_02_03_UK_01` (`F_01`),
  ADD KEY `T_02_02_03_FK_01` (`F_03`);

--
-- Index pour la table `t_02_02_04`
--
ALTER TABLE `t_02_02_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_02_04_UK_01` (`F_03`);

--
-- Index pour la table `t_02_02_05`
--
ALTER TABLE `t_02_02_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_02_05_UK_01` (`F_03`),
  ADD KEY `T_02_02_05_FK_01` (`F_04`);

--
-- Index pour la table `t_02_02_06`
--
ALTER TABLE `t_02_02_06`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_02_06_UK_01` (`F_02`),
  ADD KEY `T_02_02_06_FK_01` (`F_04`);

--
-- Index pour la table `t_02_02_07`
--
ALTER TABLE `t_02_02_07`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_02_07_UK_01` (`F_03`,`F_04`),
  ADD KEY `T_02_02_07_FK_01` (`F_04`),
  ADD KEY `T_02_02_07_FK_02` (`F_05`);

--
-- Index pour la table `t_02_02_08`
--
ALTER TABLE `t_02_02_08`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_02_02_08_FK_02` (`F_06`),
  ADD KEY `T_02_02_08_FK_01` (`F_05`);

--
-- Index pour la table `t_02_02_09`
--
ALTER TABLE `t_02_02_09`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_02_02_09_FK_01` (`F_02`),
  ADD KEY `T_02_02_09_FK_02` (`F_03`);

--
-- Index pour la table `t_02_03_01`
--
ALTER TABLE `t_02_03_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_03_01_UK_01` (`F_04`);

--
-- Index pour la table `t_02_03_02`
--
ALTER TABLE `t_02_03_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_03_02_UK_01` (`F_03`),
  ADD UNIQUE KEY `T_02_03_02_UK_02` (`F_06`);

--
-- Index pour la table `t_02_03_03`
--
ALTER TABLE `t_02_03_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_03_03_UK_01` (`F_03`);

--
-- Index pour la table `t_02_03_04`
--
ALTER TABLE `t_02_03_04`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_02_03_04_FK_03` (`F_20`),
  ADD KEY `T_02_03_04_FK_02` (`F_19`),
  ADD KEY `T_02_03_04_FK_04` (`F_21`),
  ADD KEY `T_02_03_04_FK_01` (`F_18`);

--
-- Index pour la table `t_02_03_05`
--
ALTER TABLE `t_02_03_05`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_02_03_05_FK_01` (`F_10`);

--
-- Index pour la table `t_02_03_06`
--
ALTER TABLE `t_02_03_06`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_03_06_UK_01` (`F_03`),
  ADD KEY `T_02_03_06_FK_01` (`F_04`),
  ADD KEY `T_02_03_06_FK_02` (`F_05`),
  ADD KEY `T_02_03_06_FK_03` (`F_06`);

--
-- Index pour la table `t_02_03_07`
--
ALTER TABLE `t_02_03_07`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_03_07_UK_01` (`F_01`,`F_05`),
  ADD KEY `T_02_03_07_FK_01` (`F_04`),
  ADD KEY `T_02_03_07_FK_02` (`F_05`),
  ADD KEY `T_02_03_07_FK_03` (`F_06`);

--
-- Index pour la table `t_02_04_01`
--
ALTER TABLE `t_02_04_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_04_01_UK_01` (`F_03`),
  ADD UNIQUE KEY `T_02_04_01_UK_02` (`F_06`);

--
-- Index pour la table `t_02_04_02`
--
ALTER TABLE `t_02_04_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_04_02_UK_01` (`F_03`);

--
-- Index pour la table `t_02_04_03`
--
ALTER TABLE `t_02_04_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_04_03_UK_01` (`F_03`),
  ADD KEY `T_02_04_03_FK_01` (`F_04`);

--
-- Index pour la table `t_02_04_04`
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
-- Index pour la table `t_02_04_05`
--
ALTER TABLE `t_02_04_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_04_05_UK_01` (`F_03`,`F_04`),
  ADD KEY `T_02_04_05_FK_03` (`F_05`),
  ADD KEY `T_02_04_05_FK_05` (`F_07`),
  ADD KEY `T_02_04_05_FK_04` (`F_06`),
  ADD KEY `T_02_04_05_FK_02` (`F_04`);

--
-- Index pour la table `t_02_05_01`
--
ALTER TABLE `t_02_05_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_05_01_UK_01` (`F_03`);

--
-- Index pour la table `t_02_05_02`
--
ALTER TABLE `t_02_05_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_05_02_UK_01` (`F_03`);

--
-- Index pour la table `t_02_05_03`
--
ALTER TABLE `t_02_05_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_05_03_UK_01` (`F_03`),
  ADD KEY `T_02_05_03_FK_01` (`F_04`);

--
-- Index pour la table `t_02_05_04`
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
  ADD KEY `T_02_05_04_FK_03` (`F_18`),
  ADD KEY `T_02_05_04_FK_02` (`F_17`);

--
-- Index pour la table `t_02_05_05`
--
ALTER TABLE `t_02_05_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_05_05_UK_01` (`F_02`,`F_06`),
  ADD KEY `T_02_05_05_FK_01` (`F_06`);

--
-- Index pour la table `t_02_06_01`
--
ALTER TABLE `t_02_06_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_06_01_UK_01` (`F_03`);

--
-- Index pour la table `t_02_06_02`
--
ALTER TABLE `t_02_06_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_06_02_UK_01` (`F_03`);

--
-- Index pour la table `t_02_06_03`
--
ALTER TABLE `t_02_06_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_06_03_UK_01` (`F_03`),
  ADD KEY `T_02_06_03_FK_01` (`F_04`);

--
-- Index pour la table `t_02_06_04`
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
-- Index pour la table `t_03_01_01`
--
ALTER TABLE `t_03_01_01`
  ADD PRIMARY KEY (`F_00`);

--
-- Index pour la table `t_03_01_02`
--
ALTER TABLE `t_03_01_02`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_03_01_02_FK_01` (`F_02`);

--
-- Index pour la table `t_03_01_03`
--
ALTER TABLE `t_03_01_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_01_03_UK_01` (`F_02`),
  ADD KEY `T_03_01_03_FK_01` (`F_05`);

--
-- Index pour la table `t_03_01_04`
--
ALTER TABLE `t_03_01_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_01_04_UK_01` (`F_02`);

--
-- Index pour la table `t_03_01_05`
--
ALTER TABLE `t_03_01_05`
  ADD PRIMARY KEY (`F_00`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `t_00_01_01`
--
ALTER TABLE `t_00_01_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_00_02_01`
--
ALTER TABLE `t_00_02_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `t_00_02_02`
--
ALTER TABLE `t_00_02_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `t_00_02_03`
--
ALTER TABLE `t_00_02_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `t_00_02_04`
--
ALTER TABLE `t_00_02_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT pour la table `t_00_02_05`
--
ALTER TABLE `t_00_02_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `t_00_03_01`
--
ALTER TABLE `t_00_03_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_00_04_01`
--
ALTER TABLE `t_00_04_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_01_01_01`
--
ALTER TABLE `t_01_01_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `t_01_01_02`
--
ALTER TABLE `t_01_01_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_01_01_04`
--
ALTER TABLE `t_01_01_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `t_01_01_05`
--
ALTER TABLE `t_01_01_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_01_01_07`
--
ALTER TABLE `t_01_01_07`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_01_01_08`
--
ALTER TABLE `t_01_01_08`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_01_02_01`
--
ALTER TABLE `t_01_02_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `t_01_02_02`
--
ALTER TABLE `t_01_02_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `t_01_02_03`
--
ALTER TABLE `t_01_02_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_01_03_01`
--
ALTER TABLE `t_01_03_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT pour la table `t_01_03_02`
--
ALTER TABLE `t_01_03_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_01_04_01`
--
ALTER TABLE `t_01_04_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_01_04_02`
--
ALTER TABLE `t_01_04_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_01_04_03`
--
ALTER TABLE `t_01_04_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_01_04_04`
--
ALTER TABLE `t_01_04_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_01_04_05`
--
ALTER TABLE `t_01_04_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_01_04_06`
--
ALTER TABLE `t_01_04_06`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_01_04_07`
--
ALTER TABLE `t_01_04_07`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_01_04_08`
--
ALTER TABLE `t_01_04_08`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_01_04_09`
--
ALTER TABLE `t_01_04_09`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_01_04_10`
--
ALTER TABLE `t_01_04_10`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_02_01_01`
--
ALTER TABLE `t_02_01_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_02_01_02`
--
ALTER TABLE `t_02_01_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `t_02_01_03`
--
ALTER TABLE `t_02_01_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `t_02_01_04`
--
ALTER TABLE `t_02_01_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `t_02_01_05`
--
ALTER TABLE `t_02_01_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `t_02_02_01`
--
ALTER TABLE `t_02_02_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `t_02_02_02`
--
ALTER TABLE `t_02_02_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_02_02_03`
--
ALTER TABLE `t_02_02_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_02_02_04`
--
ALTER TABLE `t_02_02_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_02_02_05`
--
ALTER TABLE `t_02_02_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_02_02_06`
--
ALTER TABLE `t_02_02_06`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_02_02_07`
--
ALTER TABLE `t_02_02_07`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_02_02_08`
--
ALTER TABLE `t_02_02_08`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_02_02_09`
--
ALTER TABLE `t_02_02_09`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_02_03_01`
--
ALTER TABLE `t_02_03_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_02_03_02`
--
ALTER TABLE `t_02_03_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `t_02_03_03`
--
ALTER TABLE `t_02_03_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `t_02_03_04`
--
ALTER TABLE `t_02_03_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_02_03_05`
--
ALTER TABLE `t_02_03_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_02_03_06`
--
ALTER TABLE `t_02_03_06`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_02_03_07`
--
ALTER TABLE `t_02_03_07`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_02_04_01`
--
ALTER TABLE `t_02_04_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `t_02_04_02`
--
ALTER TABLE `t_02_04_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `t_02_04_03`
--
ALTER TABLE `t_02_04_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `t_02_04_04`
--
ALTER TABLE `t_02_04_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_02_04_05`
--
ALTER TABLE `t_02_04_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_02_05_01`
--
ALTER TABLE `t_02_05_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `t_02_05_02`
--
ALTER TABLE `t_02_05_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `t_02_05_03`
--
ALTER TABLE `t_02_05_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `t_02_05_04`
--
ALTER TABLE `t_02_05_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_02_05_05`
--
ALTER TABLE `t_02_05_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_02_06_01`
--
ALTER TABLE `t_02_06_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `t_02_06_02`
--
ALTER TABLE `t_02_06_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `t_02_06_03`
--
ALTER TABLE `t_02_06_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `t_02_06_04`
--
ALTER TABLE `t_02_06_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_03_01_01`
--
ALTER TABLE `t_03_01_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_03_01_02`
--
ALTER TABLE `t_03_01_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_03_01_03`
--
ALTER TABLE `t_03_01_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_03_01_04`
--
ALTER TABLE `t_03_01_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_03_01_05`
--
ALTER TABLE `t_03_01_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `r_t000201_t000203`
--
ALTER TABLE `r_t000201_t000203`
  ADD CONSTRAINT `R_T000201_T000203_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_00_02_01` (`F_00`),
  ADD CONSTRAINT `R_T000201_T000203_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_00_02_03` (`F_00`);

--
-- Contraintes pour la table `r_t000202_t000201`
--
ALTER TABLE `r_t000202_t000201`
  ADD CONSTRAINT `R_T000202_T000201_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_00_02_02` (`F_00`),
  ADD CONSTRAINT `R_T000202_T000201_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_00_02_01` (`F_00`);

--
-- Contraintes pour la table `r_t000202_t000203`
--
ALTER TABLE `r_t000202_t000203`
  ADD CONSTRAINT `R_T000202_T000203_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_00_02_02` (`F_00`),
  ADD CONSTRAINT `R_T000202_T000203_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_00_02_03` (`F_00`);

--
-- Contraintes pour la table `r_t000203_t000204`
--
ALTER TABLE `r_t000203_t000204`
  ADD CONSTRAINT `R_T000203_T000204_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_00_02_03` (`F_00`),
  ADD CONSTRAINT `R_T000203_T000204_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_00_02_04` (`F_00`);

--
-- Contraintes pour la table `r_t010203_t010203`
--
ALTER TABLE `r_t010203_t010203`
  ADD CONSTRAINT `R_T010203_T010203_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_01_02_03` (`F_00`),
  ADD CONSTRAINT `R_T010203_T010203_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_01_02_03` (`F_00`);

--
-- Contraintes pour la table `r_t020207_t020208`
--
ALTER TABLE `r_t020207_t020208`
  ADD CONSTRAINT `R_T020207_T020208_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_02_07` (`F_00`),
  ADD CONSTRAINT `R_T020207_T020208_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_02_02_08` (`F_00`);

--
-- Contraintes pour la table `r_t020304_t020301`
--
ALTER TABLE `r_t020304_t020301`
  ADD CONSTRAINT `R_T020304_T020301_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_03_04` (`F_00`),
  ADD CONSTRAINT `R_T020304_T020301_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_02_03_01` (`F_00`);

--
-- Contraintes pour la table `r_t020404_t010203`
--
ALTER TABLE `r_t020404_t010203`
  ADD CONSTRAINT `R_T020404_T010203_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_04_04` (`F_00`),
  ADD CONSTRAINT `R_T020404_T010203_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_01_02_03` (`F_00`);

--
-- Contraintes pour la table `r_t020404_t010302`
--
ALTER TABLE `r_t020404_t010302`
  ADD CONSTRAINT `R_T020404_T010302_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_04_04` (`F_00`),
  ADD CONSTRAINT `R_T020404_T010302_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_01_03_02` (`F_00`);

--
-- Contraintes pour la table `r_t020404_t020208`
--
ALTER TABLE `r_t020404_t020208`
  ADD CONSTRAINT `R_T020404_T020208_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_04_04` (`F_00`),
  ADD CONSTRAINT `R_T020404_T020208_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_02_02_08` (`F_00`);

--
-- Contraintes pour la table `r_t020504_t010203`
--
ALTER TABLE `r_t020504_t010203`
  ADD CONSTRAINT `R_T020504_T010203_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_05_04` (`F_00`),
  ADD CONSTRAINT `R_T020504_T010203_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_01_02_03` (`F_00`);

--
-- Contraintes pour la table `r_t020504_t010302`
--
ALTER TABLE `r_t020504_t010302`
  ADD CONSTRAINT `R_T020504_T010302_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_05_04` (`F_00`),
  ADD CONSTRAINT `R_T020504_T010302_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_01_03_02` (`F_00`);

--
-- Contraintes pour la table `r_t020504_t020208`
--
ALTER TABLE `r_t020504_t020208`
  ADD CONSTRAINT `R_T020504_T020208_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_05_04` (`F_00`),
  ADD CONSTRAINT `R_T020504_T020208_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_02_02_08` (`F_00`);

--
-- Contraintes pour la table `r_t020604_t010203`
--
ALTER TABLE `r_t020604_t010203`
  ADD CONSTRAINT `R_T020604_T010203_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_06_04` (`F_00`),
  ADD CONSTRAINT `R_T020604_T010203_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_01_02_03` (`F_00`);

--
-- Contraintes pour la table `r_t020604_t010302`
--
ALTER TABLE `r_t020604_t010302`
  ADD CONSTRAINT `R_T020604_T010302_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_06_04` (`F_00`),
  ADD CONSTRAINT `R_T020604_T010302_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_01_03_02` (`F_00`);

--
-- Contraintes pour la table `t_00_04_01`
--
ALTER TABLE `t_00_04_01`
  ADD CONSTRAINT `T_00_04_01_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_00_02_02` (`F_00`);

--
-- Contraintes pour la table `t_01_01_02`
--
ALTER TABLE `t_01_01_02`
  ADD CONSTRAINT `T_01_01_02_FK_01` FOREIGN KEY (`F_05`) REFERENCES `t_01_01_01` (`F_00`);

--
-- Contraintes pour la table `t_01_01_05`
--
ALTER TABLE `t_01_01_05`
  ADD CONSTRAINT `T_01_01_05_FK_01` FOREIGN KEY (`F_05`) REFERENCES `t_01_01_04` (`F_00`);

--
-- Contraintes pour la table `t_01_01_07`
--
ALTER TABLE `t_01_01_07`
  ADD CONSTRAINT `T_01_01_07_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_01_01_05` (`F_00`);

--
-- Contraintes pour la table `t_01_01_08`
--
ALTER TABLE `t_01_01_08`
  ADD CONSTRAINT `T_01_01_08_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_01_01_07` (`F_00`);

--
-- Contraintes pour la table `t_01_02_03`
--
ALTER TABLE `t_01_02_03`
  ADD CONSTRAINT `T_01_02_03_FK_01` FOREIGN KEY (`F_06`) REFERENCES `t_01_02_01` (`F_00`),
  ADD CONSTRAINT `T_01_02_03_FK_02` FOREIGN KEY (`F_07`) REFERENCES `t_01_02_02` (`F_00`),
  ADD CONSTRAINT `T_01_02_03_FK_03` FOREIGN KEY (`F_08`) REFERENCES `t_01_04_09` (`F_00`),
  ADD CONSTRAINT `T_01_02_03_FK_04` FOREIGN KEY (`F_09`) REFERENCES `t_00_01_01` (`F_00`);

--
-- Contraintes pour la table `t_01_03_02`
--
ALTER TABLE `t_01_03_02`
  ADD CONSTRAINT `T_01_03_02_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_01_03_01` (`F_00`),
  ADD CONSTRAINT `T_01_03_02_FK_02` FOREIGN KEY (`F_05`) REFERENCES `t_00_01_01` (`F_00`);

--
-- Contraintes pour la table `t_01_04_02`
--
ALTER TABLE `t_01_04_02`
  ADD CONSTRAINT `FKtgbadkunmn8aymon9yg86chh3` FOREIGN KEY (`F_05`) REFERENCES `t_01_04_01` (`F_00`);

--
-- Contraintes pour la table `t_01_04_03`
--
ALTER TABLE `t_01_04_03`
  ADD CONSTRAINT `FKh3yoxlj36bdarasi5oxtsuoq3` FOREIGN KEY (`F_05`) REFERENCES `t_01_04_02` (`F_00`);

--
-- Contraintes pour la table `t_01_04_06`
--
ALTER TABLE `t_01_04_06`
  ADD CONSTRAINT `FK884fi5wdj7bh5wwo0k17xav5s` FOREIGN KEY (`F_07`) REFERENCES `t_01_04_05` (`F_00`);

--
-- Contraintes pour la table `t_01_04_07`
--
ALTER TABLE `t_01_04_07`
  ADD CONSTRAINT `FK1vpdlpkcwt5tnxuprpqskqn9d` FOREIGN KEY (`F_07`) REFERENCES `t_01_04_01` (`F_00`);

--
-- Contraintes pour la table `t_01_04_08`
--
ALTER TABLE `t_01_04_08`
  ADD CONSTRAINT `FK1k8cpo0jrmaenm6dpp00tpaef` FOREIGN KEY (`F_03`) REFERENCES `t_01_04_09` (`F_00`),
  ADD CONSTRAINT `FKgycvlba096xtpr1vubh1o3tll` FOREIGN KEY (`F_04`) REFERENCES `t_01_04_06` (`F_00`),
  ADD CONSTRAINT `FKltwtnfsf1elnwvwgnh0oueb4p` FOREIGN KEY (`F_01`) REFERENCES `t_01_04_07` (`F_00`),
  ADD CONSTRAINT `FKqeelvfiftnnueele68of5qgdu` FOREIGN KEY (`F_02`) REFERENCES `t_01_04_04` (`F_00`);

--
-- Contraintes pour la table `t_01_04_09`
--
ALTER TABLE `t_01_04_09`
  ADD CONSTRAINT `FK6j23bnilfb6pe63ism3lguoc2` FOREIGN KEY (`F_05`) REFERENCES `t_01_04_10` (`F_00`),
  ADD CONSTRAINT `FKabm0aw2hqmliouvo9p2cpcvho` FOREIGN KEY (`F_04`) REFERENCES `t_01_04_09` (`F_00`);

--
-- Contraintes pour la table `t_02_02_03`
--
ALTER TABLE `t_02_02_03`
  ADD CONSTRAINT `T_02_02_03_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_02_02_01` (`F_00`);

--
-- Contraintes pour la table `t_02_02_05`
--
ALTER TABLE `t_02_02_05`
  ADD CONSTRAINT `T_02_02_05_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_02_02_04` (`F_00`);

--
-- Contraintes pour la table `t_02_02_06`
--
ALTER TABLE `t_02_02_06`
  ADD CONSTRAINT `T_02_02_06_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_02_02_05` (`F_00`);

--
-- Contraintes pour la table `t_02_02_07`
--
ALTER TABLE `t_02_02_07`
  ADD CONSTRAINT `T_02_02_07_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_01_03_02` (`F_00`),
  ADD CONSTRAINT `T_02_02_07_FK_02` FOREIGN KEY (`F_05`) REFERENCES `t_01_03_02` (`F_00`);

--
-- Contraintes pour la table `t_02_02_08`
--
ALTER TABLE `t_02_02_08`
  ADD CONSTRAINT `T_02_02_08_FK_01` FOREIGN KEY (`F_05`) REFERENCES `t_02_02_02` (`F_00`),
  ADD CONSTRAINT `T_02_02_08_FK_02` FOREIGN KEY (`F_06`) REFERENCES `t_02_02_06` (`F_00`);

--
-- Contraintes pour la table `t_02_02_09`
--
ALTER TABLE `t_02_02_09`
  ADD CONSTRAINT `T_02_02_09_FK_01` FOREIGN KEY (`F_02`) REFERENCES `t_02_02_08` (`F_00`),
  ADD CONSTRAINT `T_02_02_09_FK_02` FOREIGN KEY (`F_03`) REFERENCES `t_01_04_09` (`F_00`);

--
-- Contraintes pour la table `t_02_03_04`
--
ALTER TABLE `t_02_03_04`
  ADD CONSTRAINT `T_02_03_04_FK_01` FOREIGN KEY (`F_18`) REFERENCES `t_00_01_01` (`F_00`),
  ADD CONSTRAINT `T_02_03_04_FK_02` FOREIGN KEY (`F_19`) REFERENCES `t_02_03_02` (`F_00`),
  ADD CONSTRAINT `T_02_03_04_FK_03` FOREIGN KEY (`F_20`) REFERENCES `t_01_04_01` (`F_00`),
  ADD CONSTRAINT `T_02_03_04_FK_04` FOREIGN KEY (`F_21`) REFERENCES `t_01_04_02` (`F_00`);

--
-- Contraintes pour la table `t_02_03_05`
--
ALTER TABLE `t_02_03_05`
  ADD CONSTRAINT `T_02_03_05_FK_01` FOREIGN KEY (`F_10`) REFERENCES `t_02_03_04` (`F_00`);

--
-- Contraintes pour la table `t_02_03_06`
--
ALTER TABLE `t_02_03_06`
  ADD CONSTRAINT `T_02_03_06_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_02_03_04` (`F_00`),
  ADD CONSTRAINT `T_02_03_06_FK_02` FOREIGN KEY (`F_05`) REFERENCES `t_02_03_05` (`F_00`),
  ADD CONSTRAINT `T_02_03_06_FK_03` FOREIGN KEY (`F_06`) REFERENCES `t_01_02_03` (`F_00`);

--
-- Contraintes pour la table `t_02_03_07`
--
ALTER TABLE `t_02_03_07`
  ADD CONSTRAINT `T_02_03_07_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_02_03_03` (`F_00`),
  ADD CONSTRAINT `T_02_03_07_FK_02` FOREIGN KEY (`F_05`) REFERENCES `t_02_03_04` (`F_00`),
  ADD CONSTRAINT `T_02_03_07_FK_03` FOREIGN KEY (`F_06`) REFERENCES `t_01_02_03` (`F_00`);

--
-- Contraintes pour la table `t_02_04_03`
--
ALTER TABLE `t_02_04_03`
  ADD CONSTRAINT `T_02_04_03_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_02_04_02` (`F_00`);

--
-- Contraintes pour la table `t_02_04_04`
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
-- Contraintes pour la table `t_02_04_05`
--
ALTER TABLE `t_02_04_05`
  ADD CONSTRAINT `T_02_04_05_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_02_04_04` (`F_00`),
  ADD CONSTRAINT `T_02_04_05_FK_02` FOREIGN KEY (`F_04`) REFERENCES `t_02_03_04` (`F_00`),
  ADD CONSTRAINT `T_02_04_05_FK_03` FOREIGN KEY (`F_05`) REFERENCES `t_00_01_01` (`F_00`),
  ADD CONSTRAINT `T_02_04_05_FK_04` FOREIGN KEY (`F_06`) REFERENCES `t_00_01_01` (`F_00`),
  ADD CONSTRAINT `T_02_04_05_FK_05` FOREIGN KEY (`F_07`) REFERENCES `t_00_01_01` (`F_00`);

--
-- Contraintes pour la table `t_02_05_03`
--
ALTER TABLE `t_02_05_03`
  ADD CONSTRAINT `T_02_05_03_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_02_05_02` (`F_00`);

--
-- Contraintes pour la table `t_02_05_04`
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
-- Contraintes pour la table `t_02_05_05`
--
ALTER TABLE `t_02_05_05`
  ADD CONSTRAINT `T_02_05_05_FK_01` FOREIGN KEY (`F_06`) REFERENCES `t_02_05_04` (`F_00`);

--
-- Contraintes pour la table `t_02_06_03`
--
ALTER TABLE `t_02_06_03`
  ADD CONSTRAINT `T_02_06_03_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_02_06_02` (`F_00`);

--
-- Contraintes pour la table `t_02_06_04`
--
ALTER TABLE `t_02_06_04`
  ADD CONSTRAINT `T_02_06_04_FK_01` FOREIGN KEY (`F_12`) REFERENCES `t_02_05_04` (`F_00`),
  ADD CONSTRAINT `T_02_06_04_FK_02` FOREIGN KEY (`F_13`) REFERENCES `t_02_06_01` (`F_00`),
  ADD CONSTRAINT `T_02_06_04_FK_03` FOREIGN KEY (`F_14`) REFERENCES `t_02_01_05` (`F_00`),
  ADD CONSTRAINT `T_02_06_04_FK_04` FOREIGN KEY (`F_15`) REFERENCES `t_02_06_02` (`F_00`),
  ADD CONSTRAINT `T_02_06_04_FK_05` FOREIGN KEY (`F_16`) REFERENCES `t_02_01_02` (`F_00`),
  ADD CONSTRAINT `T_02_06_04_FK_06` FOREIGN KEY (`F_17`) REFERENCES `t_02_01_01` (`F_00`);

--
-- Contraintes pour la table `t_03_01_02`
--
ALTER TABLE `t_03_01_02`
  ADD CONSTRAINT `T_03_01_02_FK_01` FOREIGN KEY (`F_02`) REFERENCES `t_03_01_01` (`F_00`);

--
-- Contraintes pour la table `t_03_01_03`
--
ALTER TABLE `t_03_01_03`
  ADD CONSTRAINT `T_03_01_03_FK_01` FOREIGN KEY (`F_05`) REFERENCES `t_03_01_02` (`F_00`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
