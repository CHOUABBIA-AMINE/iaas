-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 20 déc. 2025 à 17:10
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
-- Structure de la table `r_t030303_t030307`
--

CREATE TABLE `r_t030303_t030307` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `r_t030304_t030307`
--

CREATE TABLE `r_t030304_t030307` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `r_t030305_t030207`
--

CREATE TABLE `r_t030305_t030207` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `r_t030305_t030307`
--

CREATE TABLE `r_t030305_t030307` (
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
  `F_01` varchar(20) NOT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) DEFAULT NULL,
  `F_04` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_01_01_02`
--

INSERT INTO `t_01_01_02` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`) VALUES
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
-- Structure de la table `t_01_01_03`
--

CREATE TABLE `t_01_01_03` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL,
  `F_05` bigint(20) NOT NULL,
  `F_06` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_01_01_03`
--

INSERT INTO `t_01_01_03` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`, `F_05`, `F_06`) VALUES
(1, '60', NULL, NULL, '60', 1, 6),
(2, '61', NULL, NULL, '61', 1, 6),
(3, '62', NULL, NULL, '62', 1, 6),
(4, '63', NULL, NULL, '63', 1, 6),
(5, '64', NULL, NULL, '64', 1, 6),
(6, '65', NULL, NULL, '65', 1, 6),
(7, '66', NULL, NULL, '66', 1, 6),
(8, '67', NULL, NULL, '67', 1, 6),
(9, '68', NULL, NULL, '68', 1, 6);

-- --------------------------------------------------------

--
-- Structure de la table `t_01_01_04`
--

CREATE TABLE `t_01_01_04` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL,
  `F_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_01_01_04`
--

INSERT INTO `t_01_01_04` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`, `F_05`) VALUES
(1, 'SDR-01', NULL, NULL, 'SDR-01', 2),
(2, 'SDR-02', NULL, NULL, 'SDR-02', 2),
(3, 'SDR-03', NULL, NULL, 'SDR-03', 2),
(4, 'SDR-04', NULL, NULL, 'SDR-04', 3),
(5, 'SDR-05', NULL, NULL, 'SDR-05', 3),
(6, 'SDR-06', NULL, NULL, 'SDR-06', 4),
(7, 'SDR-07', NULL, NULL, 'SDR-07', 4),
(8, 'SDR-08', NULL, NULL, 'SDR-08', 7),
(9, 'SDR-09', NULL, NULL, 'SDR-09', 7),
(10, 'SDR-10', NULL, NULL, 'SDR-10', 7),
(11, 'SDR-11', NULL, NULL, 'SDR-11', 8),
(12, 'SDR-12', NULL, NULL, 'SDR-12', 8),
(13, 'SDR-13', NULL, NULL, 'SDR-13', 8),
(14, 'SDR-14', NULL, NULL, 'SDR-14', 8),
(15, 'SDR-15', NULL, NULL, 'SDR-15', 8),
(16, 'SDR-16', NULL, NULL, 'SDR-16', 8),
(17, 'SDR-17', NULL, NULL, 'SDR-17', 8),
(18, 'SDR-18', NULL, NULL, 'SDR-18', 8),
(19, 'SDR-19', NULL, NULL, 'SDR-19', 8),
(20, 'SDR-20', NULL, NULL, 'SDR-20', 8);

-- --------------------------------------------------------

--
-- Structure de la table `t_01_01_05`
--

CREATE TABLE `t_01_01_05` (
  `F_00` bigint(20) NOT NULL,
  `F_02` varchar(20) NOT NULL,
  `F_03` varchar(200) DEFAULT NULL,
  `F_04` varchar(200) DEFAULT NULL,
  `F_05` varchar(200) NOT NULL,
  `F_01` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_01_01_05`
--

INSERT INTO `t_01_01_05` (`F_00`, `F_02`, `F_03`, `F_04`, `F_05`, `F_01`) VALUES
(1, '1°', 'الطابق الأول', 'First Floor', 'Premier Etage', 1),
(2, '2°', 'الطابق الأول', 'Second Floor', 'Deuxième Etage', 2),
(3, '3°', 'الطابق الأول', 'Third Floor', 'Troisième Etage', 3),
(4, '4°', 'الطابق الأول', 'Fourth Floor', 'Quatrième Etage', 4),
(5, '5°', 'الطابق الأول', 'Fifth Floor', 'Cinquième Etage', 5),
(6, '6°', 'الطابق الأول', 'Sixth Floor', 'Siesième Etage', 6),
(7, '7°', 'الطابق الأول', 'Seventh Floor', 'Septième Etage', 7),
(8, '8°', 'الطابق الأول', 'Eigth Floor', 'Huitième Etage', 8),
(9, '9°', 'الطابق الأول', 'Nineth Floor', 'Neuvième Etage', 9);

-- --------------------------------------------------------

--
-- Structure de la table `t_01_01_06`
--

CREATE TABLE `t_01_01_06` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(50) NOT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` bigint(20) NOT NULL,
  `F_04` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_01_01_07`
--

CREATE TABLE `t_01_01_07` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(50) NOT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` bigint(20) NOT NULL,
  `F_04` int(11) DEFAULT NULL
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
  `F_04` varchar(10) NOT NULL,
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

--
-- Déchargement des données de la table `t_01_04_01`
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
-- Structure de la table `t_01_04_02`
--

CREATE TABLE `t_01_04_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(10) NOT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_01_04_02`
--

INSERT INTO `t_01_04_02` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`) VALUES
(1, '1', 'أدرار', NULL, 'Adrar'),
(2, '2', 'الشلف', NULL, 'Chlef'),
(3, '3', 'الأغواط', NULL, 'Laghouat'),
(4, '4', 'أم البواقي', NULL, 'Oum El Bouaghi'),
(5, '5', 'باتنة', NULL, 'Batna'),
(6, '6', 'بجاية', NULL, 'Béjaïa'),
(7, '7', 'بسكرة', NULL, 'Biskra'),
(8, '8', 'بشار', NULL, 'Béchar'),
(9, '9', 'البليدة', NULL, 'Blida'),
(10, '10', 'البويرة', NULL, 'Bouïra'),
(11, '11', 'تمنراست', NULL, 'Tamanrasset'),
(12, '12', 'تبسة', NULL, 'Tébessa'),
(13, '13', 'تلمسان', NULL, 'Tlemcen'),
(14, '14', 'تيارت', NULL, 'Tiaret'),
(15, '15', 'تيزي وزو', NULL, 'Tizi Ouzou'),
(16, '16', 'الجزائر', NULL, 'Alger'),
(17, '17', 'الجلفة', NULL, 'Djelfa'),
(18, '18', 'جيجل', NULL, 'Jijel'),
(19, '19', 'سطيف', NULL, 'Sétif'),
(20, '20', 'سعيدة', NULL, 'Saïda'),
(21, '21', 'سكيكدة', NULL, 'Skikda'),
(22, '22', 'سيدي بلعباس', NULL, 'Sidi Bel Abbès'),
(23, '23', 'عنابة', NULL, 'Annaba'),
(24, '24', 'قالمة', NULL, 'Guelma'),
(25, '25', 'قسنطينة', NULL, 'Constantine'),
(26, '26', 'المدية', NULL, 'Médéa'),
(27, '27', 'مستغانم', NULL, 'Mostaganem'),
(28, '28', 'المسيلة', NULL, 'M\'Sila'),
(29, '29', 'معسكر', NULL, 'Mascara'),
(30, '30', 'ورقلة', NULL, 'Ouargla'),
(31, '31', 'وهران', NULL, 'Oran'),
(32, '32', 'البيض', NULL, 'El Bayadh'),
(33, '33', 'إليزي', NULL, 'Illizi'),
(34, '34', 'برج بوعريريج', NULL, 'Bordj Bou Arréridj'),
(35, '35', 'بومرداس', NULL, 'Boumerdès'),
(36, '36', 'الطارف', NULL, 'El Tarf'),
(37, '37', 'تندوف', NULL, 'Tindouf'),
(38, '38', 'تيسمسيلت', NULL, 'Tissemsilt'),
(39, '39', 'الوادي', NULL, 'El Oued'),
(40, '40', 'خنشلة', NULL, 'Khenchela'),
(41, '41', 'سوق أهراس', NULL, 'Souk Ahras'),
(42, '42', 'تيبازة', NULL, 'Tipaza'),
(43, '43', 'ميلة', NULL, 'Mila'),
(44, '44', 'عين الدفلى', NULL, 'Aïn Defla'),
(45, '45', 'النعامة', NULL, 'Naâma'),
(46, '46', 'عين تموشنت', NULL, 'Aïn Témouchent'),
(47, '47', 'غرداية', NULL, 'Ghardaïa'),
(48, '48', 'غليزان', NULL, 'Relizane'),
(49, '49', 'تيميمون', NULL, 'Timimoun'),
(50, '50', 'برج باجي مختار', NULL, 'Bordj Baji Mokhtar'),
(51, '51', 'أولاد جلال', NULL, 'Ouled Djellal'),
(52, '52', 'بني عباس', NULL, 'Béni Abbès'),
(53, '53', 'إن صالح', NULL, 'In Salah'),
(54, '54', 'إن قزام', NULL, 'In Guezzam'),
(55, '55', 'تقرت', NULL, 'Touggourt'),
(56, '56', 'جانت', NULL, 'Djanet'),
(57, '57', 'المغير', NULL, 'El M\'Ghair'),
(58, '58', 'المنيعة', NULL, 'El Menia');

-- --------------------------------------------------------

--
-- Structure de la table `t_01_04_03`
--

CREATE TABLE `t_01_04_03` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(10) NOT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL,
  `F_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_01_04_03`
--

INSERT INTO `t_01_04_03` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`, `F_05`) VALUES
(1, '0001', 'أدرار', 'Adrar', 'Adrar', 1),
(2, '0002', 'اقبلي', 'Akabli', 'Akabli', 1),
(3, '0003', 'أوقروت', 'Aougrout', 'Aougrout', 49),
(4, '0004', 'أولف', 'Aoulef', 'Aoulef', 1),
(5, '0005', 'برج باجي مختار', 'Bordj Badji Mokhtar', 'Bordj Badji Mokhtar', 50),
(6, '0006', 'بودة', 'Bouda', 'Bouda', 1),
(7, '0007', 'شروين', 'Charouine', 'Charouine', 49),
(8, '0008', 'دلدول', 'Deldoul', 'Deldoul', 49),
(9, '0009', 'فنوغيل', 'Fenoughil', 'Fenoughil', 1),
(10, '0010', 'إن زغمير', 'In Zghmir', 'In Zghmir', 1),
(11, '0011', 'قصر قدور', 'Ksar Kaddour', 'Ksar Kaddour', 49),
(12, '0012', 'المطارفة', 'Metarfa', 'Metarfa', 49),
(13, '0013', 'أولاد أحمد تيمي', 'Ouled Ahmed Timmi', 'Ouled Ahmed Timmi', 1),
(14, '0014', 'أولاد عيسى', 'Ouled Aissa', 'Ouled Aissa', 49),
(15, '0015', 'أولاد السعيد', 'Ouled Said', 'Ouled Said', 49),
(16, '0016', 'رقان', 'Reggane', 'Reggane', 1),
(17, '0017', 'سالي', 'Sali', 'Sali', 1),
(18, '0018', 'السبع', 'Sebaa', 'Sebaa', 1),
(19, '0019', 'طالمين', 'Talmine', 'Talmine', 49),
(20, '0020', 'تامنطيط', 'Tamantit', 'Tamantit', 1),
(21, '0021', 'تامست', 'Tamest', 'Tamest', 1),
(22, '0022', 'تيمقتن', 'Timekten', 'Timekten', 1),
(23, '0023', 'تيمياوين', 'Timiaouine', 'Timiaouine', 50),
(24, '0024', 'تيميمون', 'Timimoun', 'Timimoun', 49),
(25, '0025', 'تنركوك', 'Tinerkouk', 'Tinerkouk', 49),
(26, '0026', 'تيت', 'Tit', 'Tit', 1),
(27, '0027', 'تسابيت', 'Tsabit', 'Tsabit', 1),
(28, '0028', 'زاوية كنتة', 'Zaouiet Kounta', 'Zaouiet Kounta', 1),
(29, '0029', 'أبو الحسن', 'Abou El Hassane', 'Abou El Hassane', 2),
(30, '0030', 'عين مران', 'Ain Merane', 'Ain Merane', 2),
(31, '0031', 'بنايرية', 'Benairia', 'Benairia', 2),
(32, '0032', 'بني بوعتاب', 'Beni  Bouattab', 'Beni  Bouattab', 2),
(33, '0033', 'بني حواء', 'Beni Haoua', 'Beni Haoua', 2),
(34, '0034', 'بني راشد', 'Beni Rached', 'Beni Rached', 2),
(35, '0035', 'بوقادير', 'Boukadir', 'Boukadir', 2),
(36, '0036', 'بوزغاية', 'Bouzeghaia', 'Bouzeghaia', 2),
(37, '0037', 'بريرة', 'Breira', 'Breira', 2),
(38, '0038', 'الشطية', 'Chettia', 'Chettia', 2),
(39, '0039', 'الشلف', 'Chlef', 'Chlef', 2),
(40, '0040', 'الظهرة', 'Dahra', 'Dahra', 2),
(41, '0041', 'الحجاج', 'El Hadjadj', 'El Hadjadj', 2),
(42, '0042', 'الكريمية', 'El Karimia', 'El Karimia', 2),
(43, '0043', 'المرسى', 'El Marsa', 'El Marsa', 2),
(44, '0044', 'حرشون', 'Harchoun', 'Harchoun', 2),
(45, '0045', 'الهرانفة', 'Herenfa', 'Herenfa', 2),
(46, '0046', 'الأبيض مجاجة', 'Labiod Medjadja', 'Labiod Medjadja', 2),
(47, '0047', 'مصدق', 'Moussadek', 'Moussadek', 2),
(48, '0048', 'وادي الفضة', 'Oued Fodda', 'Oued Fodda', 2),
(49, '0049', 'وادي قوسين', 'Oued Goussine', 'Oued Goussine', 2),
(50, '0050', 'وادي سلي', 'Oued Sly', 'Oued Sly', 2),
(51, '0051', 'أولاد عباس', 'Ouled Abbes', 'Ouled Abbes', 2),
(52, '0052', 'أولاد بن عبد القادر', 'Ouled Ben Abdelkader', 'Ouled Ben Abdelkader', 2),
(53, '0053', 'أولاد فارس', 'Ouled Fares', 'Ouled Fares', 2),
(54, '0054', 'أم الدروع', 'Oum Drou', 'Oum Drou', 2),
(55, '0055', 'سنجاس', 'Sendjas', 'Sendjas', 2),
(56, '0056', 'سيدي عبد الرحمن', 'Sidi Abderrahmane', 'Sidi Abderrahmane', 2),
(57, '0057', 'سيدي عكاشة', 'Sidi Akkacha', 'Sidi Akkacha', 2),
(58, '0058', 'الصبحة', 'Sobha', 'Sobha', 2),
(59, '0059', 'تاجنة', 'Tadjena', 'Tadjena', 2),
(60, '0060', 'تلعصة', 'Talassa', 'Talassa', 2),
(61, '0061', 'تاوقريت', 'Taougrit', 'Taougrit', 2),
(62, '0062', 'تنس', 'Tenes', 'Tenes', 2),
(63, '0063', 'الزبوجة', 'Zeboudja', 'Zeboudja', 2),
(64, '0064', 'أفلو', 'Aflou', 'Aflou', 3),
(65, '0065', 'عين ماضي', 'Ain Madhi', 'Ain Madhi', 3),
(66, '0066', 'عين سيدي علي', 'Ain Sidi Ali', 'Ain Sidi Ali', 3),
(67, '0067', 'البيضاء', 'El Beidha', 'El Beidha', 3),
(68, '0068', 'بن ناصر بن شهرة', 'Benacer Benchohra', 'Benacer Benchohra', 3),
(69, '0069', 'بريدة', 'Brida', 'Brida', 3),
(70, '0070', 'العسافية', 'El Assafia', 'El Assafia', 3),
(71, '0071', 'الغيشة', 'El Ghicha', 'El Ghicha', 3),
(72, '0072', 'الحويطة', 'El Haouaita', 'El Haouaita', 3),
(73, '0073', 'قلتة سيدي سعد', 'Gueltat Sidi Saad', 'Gueltat Sidi Saad', 3),
(74, '0074', 'الحاج مشري', 'Hadj Mechri', 'Hadj Mechri', 3),
(75, '0075', 'حاسي الدلاعة', 'Hassi Delaa', 'Hassi Delaa', 3),
(76, '0076', 'حاسي الرمل', 'Hassi R\'mel', 'Hassi R\'mel', 3),
(77, '0077', 'الخنق', 'Kheneg', 'Kheneg', 3),
(78, '0078', 'قصر الحيران', 'Ksar El Hirane', 'Ksar El Hirane', 3),
(79, '0079', 'الأغواط', 'Laghouat', 'Laghouat', 3),
(80, '0080', 'وادي مرة', 'Oued Morra', 'Oued Morra', 3),
(81, '0081', 'وادي مزي', 'Oued M\'zi', 'Oued M\'zi', 3),
(82, '0082', 'سبقاق', 'Sebgag', 'Sebgag', 3),
(83, '0083', 'سيدي بوزيد', 'Sidi Bouzid', 'Sidi Bouzid', 3),
(84, '0084', 'سيدي مخلوف', 'Sidi Makhlouf', 'Sidi Makhlouf', 3),
(85, '0085', 'تاجموت', 'Tadjemout', 'Tadjemout', 3),
(86, '0086', 'تاجرونة', 'Tadjrouna', 'Tadjrouna', 3),
(87, '0087', 'تاويالة', 'Taouiala', 'Taouiala', 3),
(88, '0088', 'عين ببوش', 'Ain Babouche', 'Ain Babouche', 4),
(89, '0089', 'عين البيضاء', 'Ain Beida', 'Ain Beida', 4),
(90, '0090', 'عين الديس', 'Ain Diss', 'Ain Diss', 4),
(91, '0091', 'عين فكرون', 'Ain Fekroun', 'Ain Fekroun', 4),
(92, '0092', 'عين كرشة', 'Ain Kercha', 'Ain Kercha', 4),
(93, '0093', 'عين مليلة', 'Ain M\'lila', 'Ain M\'lila', 4),
(94, '0094', 'عين الزيتون', 'Ain Zitoun', 'Ain Zitoun', 4),
(95, '0095', 'بحير الشرقي', 'Behir Chergui', 'Behir Chergui', 4),
(96, '0096', 'بريش', 'Berriche', 'Berriche', 4),
(97, '0097', 'بئر الشهداء', 'Bir Chouhada', 'Bir Chouhada', 4),
(98, '0098', 'الضلعة', 'Dhalaa', 'Dhalaa', 4),
(99, '0099', 'العامرية', 'El Amiria', 'El Amiria', 4),
(100, '0100', 'البلالة', 'El Belala', 'El Belala', 4),
(101, '0101', 'الجازية', 'El Djazia', 'El Djazia', 4),
(102, '0102', 'الفجوج بوغرارة سعودي', 'El Fedjoudj Boughrara Sa', 'El Fedjoudj Boughrara Sa', 4),
(103, '0103', 'الحرملية', 'El Harmilia', 'El Harmilia', 4),
(104, '0104', 'فكيرينة', 'Fkirina', 'Fkirina', 4),
(105, '0105', 'هنشير تومغني', 'Hanchir Toumghani', 'Hanchir Toumghani', 4),
(106, '0106', 'قصر الصباحي', 'Ksar Sbahi', 'Ksar Sbahi', 4),
(107, '0107', 'مسكيانة', 'Meskiana', 'Meskiana', 4),
(108, '0108', 'وادي نيني', 'Oued Nini', 'Oued Nini', 4),
(109, '0109', 'أولاد قاسم', 'Ouled Gacem', 'Ouled Gacem', 4),
(110, '0110', 'أولاد حملة', 'Ouled Hamla', 'Ouled Hamla', 4),
(111, '0111', 'أولاد زواي', 'Ouled Zouai', 'Ouled Zouai', 4),
(112, '0112', 'أم البواقي', 'Oum El Bouaghi', 'Oum El Bouaghi', 4),
(113, '0113', 'الرحية', 'Rahia', 'Rahia', 4),
(114, '0114', 'سيقوس', 'Sigus', 'Sigus', 4),
(115, '0115', 'سوق نعمان', 'Souk Naamane', 'Souk Naamane', 4),
(116, '0116', 'الزرق', 'Zorg', 'Zorg', 4),
(117, '0117', 'عين جاسر', 'Ain Djasser', 'Ain Djasser', 5),
(118, '0118', 'عين التوتة', 'Ain Touta', 'Ain Touta', 5),
(119, '0119', 'عين ياقوت', 'Ain Yagout', 'Ain Yagout', 5),
(120, '0120', 'أريس', 'Arris', 'Arris', 5),
(121, '0121', 'عزيل عبد القادر', 'Azil Abedelkader', 'Azil Abedelkader', 5),
(122, '0122', 'بريكة', 'Barika', 'Barika', 5),
(123, '0123', 'باتنة', 'Batna', 'Batna', 5),
(124, '0124', 'بني فضالة الحقانية', 'Beni Foudhala El Hakania', 'Beni Foudhala El Hakania', 5),
(125, '0125', 'بيطام', 'Bitam', 'Bitam', 5),
(126, '0126', 'بولهيلات', 'Boulhilat', 'Boulhilat', 5),
(127, '0127', 'بومقر', 'Boumagueur', 'Boumagueur', 5),
(128, '0128', 'بومية', 'Boumia', 'Boumia', 5),
(129, '0129', 'بوزينة', 'Bouzina', 'Bouzina', 5),
(130, '0130', 'الشمرة', 'Chemora', 'Chemora', 5),
(131, '0131', 'شير', 'Chir', 'Chir', 5),
(132, '0132', 'جرمة', 'Djerma', 'Djerma', 5),
(133, '0133', 'الجزار', 'Djezzar', 'Djezzar', 5),
(134, '0134', 'الحاسي', 'El Hassi', 'El Hassi', 5),
(135, '0135', 'المعذر', 'El Madher', 'El Madher', 5),
(136, '0136', 'فسديس', 'Fesdis', 'Fesdis', 5),
(137, '0137', 'فم الطوب', 'Foum Toub', 'Foum Toub', 5),
(138, '0138', 'غسيرة', 'Ghassira', 'Ghassira', 5),
(139, '0139', 'القصبات', 'Gosbat', 'Gosbat', 5),
(140, '0140', 'القيقبة', 'Guigba', 'Guigba', 5),
(141, '0141', 'حيدوسة', 'Hidoussa', 'Hidoussa', 5),
(142, '0142', 'إشمول', 'Ichemoul', 'Ichemoul', 5),
(143, '0143', 'إينوغيسن', 'Inoughissen', 'Inoughissen', 5),
(144, '0144', 'كيمل', 'Kimmel', 'Kimmel', 5),
(145, '0145', 'قصر بلزمة', 'Ksar Bellezma', 'Ksar Bellezma', 5),
(146, '0146', 'لارباع', 'Larbaa', 'Larbaa', 5),
(147, '0147', 'لازرو', 'Lazrou', 'Lazrou', 5),
(148, '0148', 'لمسان', 'Lemcene', 'Lemcene', 5),
(149, '0149', 'إمدوكل', 'M Doukal', 'M Doukal', 5),
(150, '0150', 'معافة', 'Maafa', 'Maafa', 5),
(151, '0151', 'منعة', 'Menaa', 'Menaa', 5),
(152, '0152', 'مروانة', 'Merouana', 'Merouana', 5),
(153, '0153', 'نقاوس', 'N Gaous', 'N Gaous', 5),
(154, '0154', 'وادي الشعبة', 'Oued Chaaba', 'Oued Chaaba', 5),
(155, '0155', 'وادي الماء', 'Oued El Ma', 'Oued El Ma', 5),
(156, '0156', 'وادي الطاقة', 'Oued Taga', 'Oued Taga', 5),
(157, '0157', 'أولاد عمار', 'Ouled Ammar', 'Ouled Ammar', 5),
(158, '0158', 'أولاد عوف', 'Ouled Aouf', 'Ouled Aouf', 5),
(159, '0159', 'أولاد فاضل', 'Ouled Fadel', 'Ouled Fadel', 5),
(160, '0160', 'أولاد سلام', 'Ouled Sellem', 'Ouled Sellem', 5),
(161, '0161', 'أولاد سي سليمان', 'Ouled Si Slimane', 'Ouled Si Slimane', 5),
(162, '0162', 'عيون العصافير', 'Ouyoun El Assafir', 'Ouyoun El Assafir', 5),
(163, '0163', 'الرحبات', 'Rahbat', 'Rahbat', 5),
(164, '0164', 'رأس العيون', 'Ras El Aioun', 'Ras El Aioun', 5),
(165, '0165', 'سفيان', 'Sefiane', 'Sefiane', 5),
(166, '0166', 'سقانة', 'Seggana', 'Seggana', 5),
(167, '0167', 'سريانة', 'Seriana', 'Seriana', 5),
(168, '0168', 'تكوت', 'T Kout', 'T Kout', 5),
(169, '0169', 'تالخمت', 'Talkhamt', 'Talkhamt', 5),
(170, '0170', 'تاكسلانت', 'Taxlent', 'Taxlent', 5),
(171, '0171', 'تازولت', 'Tazoult', 'Tazoult', 5),
(172, '0172', 'ثنية العابد', 'Teniet El Abed', 'Teniet El Abed', 5),
(173, '0173', 'تيغانمين', 'Tighanimine', 'Tighanimine', 5),
(174, '0174', 'تغرغار', 'Tigharghar', 'Tigharghar', 5),
(175, '0175', 'تيلاطو', 'Tilatou', 'Tilatou', 5),
(176, '0176', 'تيمقاد', 'Timgad', 'Timgad', 5),
(177, '0177', 'زانة البيضاء', 'Zanet El Beida', 'Zanet El Beida', 5),
(178, '0178', 'أدكار', 'Adekar', 'Adekar', 6),
(179, '0179', 'أيت رزين', 'Ait R\'zine', 'Ait R\'zine', 6),
(180, '0180', 'أيت إسماعيل', 'Ait-Smail', 'Ait-Smail', 6),
(181, '0181', 'أقبو', 'Akbou', 'Akbou', 6),
(182, '0182', 'أكفادو', 'Akfadou', 'Akfadou', 6),
(183, '0183', 'أمالو', 'Amalou', 'Amalou', 6),
(184, '0184', 'أميزور', 'Amizour', 'Amizour', 6),
(185, '0185', 'أوقاس', 'Aokas', 'Aokas', 6),
(186, '0186', 'برباشة', 'Barbacha', 'Barbacha', 6),
(187, '0187', 'بجاية', 'Bejaia', 'Bejaia', 6),
(188, '0188', 'بني جليل', 'Beni Djellil', 'Beni Djellil', 6),
(189, '0189', 'بني كسيلة', 'Beni K\'sila', 'Beni K\'sila', 6),
(190, '0190', 'بني مليكش', 'Beni-Mallikeche', 'Beni-Mallikeche', 6),
(191, '0191', 'بني معوش', 'Benimaouche', 'Benimaouche', 6),
(192, '0192', 'بو جليل', 'Boudjellil', 'Boudjellil', 6),
(193, '0193', 'بوحمزة', 'Bouhamza', 'Bouhamza', 6),
(194, '0194', 'بوخليفة', 'Boukhelifa', 'Boukhelifa', 6),
(195, '0195', 'شلاطة', 'Chellata', 'Chellata', 6),
(196, '0196', 'شميني', 'Chemini', 'Chemini', 6),
(197, '0197', 'درقينة', 'Darguina', 'Darguina', 6),
(198, '0198', 'ذراع القايد', 'Dra El Caid', 'Dra El Caid', 6),
(199, '0199', 'الفلاي', 'Leflaye', 'Leflaye', 6),
(200, '0200', 'القصر', 'El Kseur', 'El Kseur', 6),
(201, '0201', 'فناية الماثن', 'Fenaia Il Maten', 'Fenaia Il Maten', 6),
(202, '0202', 'فرعون', 'Feraoun', 'Feraoun', 6),
(203, '0203', 'أوزلاقن', 'Ouzellaguen', 'Ouzellaguen', 6),
(204, '0204', 'إغيل علي', 'Ighil-Ali', 'Ighil-Ali', 6),
(205, '0205', 'اغرم', 'Ighram', 'Ighram', 6),
(206, '0206', 'كنديرة', 'Kendira', 'Kendira', 6),
(207, '0207', 'خراطة', 'Kherrata', 'Kherrata', 6),
(208, '0208', 'مسيسنة', 'M\'cisna', 'M\'cisna', 6),
(209, '0209', 'مالبو', 'Melbou', 'Melbou', 6),
(210, '0210', 'وادي غير', 'Oued Ghir', 'Oued Ghir', 6),
(211, '0211', 'صدوق', 'Seddouk', 'Seddouk', 6),
(212, '0212', 'سيدي عياد', 'Sidi Ayad', 'Sidi Ayad', 6),
(213, '0213', 'سيدي عيش', 'Sidi-Aich', 'Sidi-Aich', 6),
(214, '0214', 'سمعون', 'Smaoun', 'Smaoun', 6),
(215, '0215', 'سوق لإثنين', 'Souk El Tenine', 'Souk El Tenine', 6),
(216, '0216', 'سوق اوفلا', 'Souk Oufella', 'Souk Oufella', 6),
(217, '0217', 'تالة حمزة', 'Tala Hamza', 'Tala Hamza', 6),
(218, '0218', 'تامقرة', 'Tamokra', 'Tamokra', 6),
(219, '0219', 'تامريجت', 'Tamridjet', 'Tamridjet', 6),
(220, '0220', 'تاوريرت إغيل', 'Taourit Ighil', 'Taourit Ighil', 6),
(221, '0221', 'تاسكريوت', 'Taskriout', 'Taskriout', 6),
(222, '0222', 'تازمالت', 'Tazmalt', 'Tazmalt', 6),
(223, '0223', 'طيبان', 'Tibane', 'Tibane', 6),
(224, '0224', 'تيشي', 'Tichy', 'Tichy', 6),
(225, '0225', 'تيفرة', 'Tifra', 'Tifra', 6),
(226, '0226', 'تيمزريت', 'Timezrit', 'Timezrit', 6),
(227, '0227', 'تينبدار', 'Tinebdar', 'Tinebdar', 6),
(228, '0228', 'تيزي نبربر', 'Tizi-N\'berber', 'Tizi-N\'berber', 6),
(229, '0229', 'توجة', 'Toudja', 'Toudja', 6),
(230, '0230', 'عين الناقة', 'Ain Naga', 'Ain Naga', 7),
(231, '0231', 'عين زعطوط', 'Ain Zaatout', 'Ain Zaatout', 7),
(232, '0232', 'بسباس', 'Besbes', 'Besbes', 51),
(233, '0233', 'بسكرة', 'Biskra', 'Biskra', 7),
(234, '0234', 'برج بن عزوز', 'Bordj Ben Azzouz', 'Bordj Ben Azzouz', 7),
(235, '0235', 'بوشقرون', 'Bouchakroun', 'Bouchakroun', 7),
(236, '0236', 'برانيس', 'Branis', 'Branis', 7),
(237, '0237', 'الشعيبة', 'Chaiba', 'Chaiba', 51),
(238, '0238', 'شتمة', 'Chetma', 'Chetma', 7),
(239, '0239', 'جمورة', 'Djemorah', 'Djemorah', 7),
(240, '0240', 'الدوسن', 'Doucen', 'Doucen', 51),
(241, '0241', 'الفيض', 'El Feidh', 'El Feidh', 7),
(242, '0242', 'الغروس', 'El Ghrous', 'El Ghrous', 7),
(243, '0243', 'الحاجب', 'El Hadjab', 'El Hadjab', 7),
(244, '0244', 'الحوش', 'El Haouch', 'El Haouch', 7),
(245, '0245', 'القنطرة', 'El Kantara', 'El Kantara', 7),
(246, '0246', 'الوطاية', 'El Outaya', 'El Outaya', 7),
(247, '0247', 'فوغالة', 'Foughala', 'Foughala', 7),
(248, '0248', 'خنقة سيدي ناجي', 'Khenguet Sidi Nadji', 'Khenguet Sidi Nadji', 7),
(249, '0249', 'ليشانة', 'Lichana', 'Lichana', 7),
(250, '0250', 'ليوة', 'Lioua', 'Lioua', 7),
(251, '0251', 'مشونش', 'M\'chouneche', 'M\'chouneche', 7),
(252, '0252', 'مخادمة', 'Mekhadma', 'Mekhadma', 7),
(253, '0253', 'المزيرعة', 'Meziraa', 'Meziraa', 7),
(254, '0254', 'مليلي', 'M\'lili', 'M\'lili', 7),
(255, '0255', 'أولاد جلال', 'Ouled Djellal', 'Ouled Djellal', 51),
(256, '0256', 'أوماش', 'Oumache', 'Oumache', 7),
(257, '0257', 'أورلال', 'Ourlal', 'Ourlal', 7),
(258, '0258', 'رأس الميعاد', 'Ras El Miad', 'Ras El Miad', 51),
(259, '0259', 'سيدي  خالد', 'Sidi Khaled', 'Sidi Khaled', 51),
(260, '0260', 'سيدي عقبة', 'Sidi Okba', 'Sidi Okba', 7),
(261, '0261', 'طولقة', 'Tolga', 'Tolga', 7),
(262, '0262', 'زريبة الوادي', 'Zeribet El Oued', 'Zeribet El Oued', 7),
(263, '0263', 'العبادلة', 'Abadla', 'Abadla', 8),
(264, '0264', 'بشار', 'Bechar', 'Bechar', 8),
(265, '0265', 'بني عباس', 'Beni-Abbes', 'Beni-Abbes', 52),
(266, '0266', 'بن يخلف', 'Beni-Ikhlef', 'Beni-Ikhlef', 52),
(267, '0267', 'بني ونيف', 'Beni-Ounif', 'Beni-Ounif', 8),
(268, '0268', 'بوكايس', 'Boukais', 'Boukais', 8),
(269, '0269', 'الواتة', 'El Ouata', 'El Ouata', 52),
(270, '0270', 'عرق فراج', 'Erg-Ferradj', 'Erg-Ferradj', 8),
(271, '0271', 'إقلي', 'Igli', 'Igli', 52),
(272, '0272', 'القنادسة', 'Kenadsa', 'Kenadsa', 8),
(273, '0273', 'كرزاز', 'Kerzaz', 'Kerzaz', 52),
(274, '0274', 'القصابي', 'Ksabi', 'Ksabi', 52),
(275, '0275', 'لحمر', 'Lahmar', 'Lahmar', 8),
(276, '0276', 'مشرع هواري بومدين', 'Machraa-Houari-Boumediene', 'Machraa-Houari-Boumediene', 8),
(277, '0277', 'المريجة', 'Meridja', 'Meridja', 8),
(278, '0278', 'موغل', 'Mogheul', 'Mogheul', 8),
(279, '0279', 'أولاد خضير', 'Ouled-Khodeir', 'Ouled-Khodeir', 52),
(280, '0280', 'تبلبالة', 'Tabelbala', 'Tabelbala', 8),
(281, '0281', 'تاغيت', 'Taghit', 'Taghit', 8),
(282, '0282', 'تامترت', 'Tamtert', 'Tamtert', 52),
(283, '0283', 'تيمودي', 'Timoudi', 'Timoudi', 52),
(284, '0284', 'عين الرمانة', 'Ain Romana', 'Ain Romana', 9),
(285, '0285', 'بني مراد', 'Beni Mered', 'Beni Mered', 9),
(286, '0286', 'بني تامو', 'Beni-Tamou', 'Beni-Tamou', 9),
(287, '0287', 'بن خليل', 'Benkhelil', 'Benkhelil', 9),
(288, '0288', 'البليدة', 'Blida', 'Blida', 9),
(289, '0289', 'بوعرفة', 'Bouarfa', 'Bouarfa', 9),
(290, '0290', 'بوفاريك', 'Boufarik', 'Boufarik', 9),
(291, '0291', 'بوقرة', 'Bougara', 'Bougara', 9),
(292, '0292', 'بوعينان', 'Bouinan', 'Bouinan', 9),
(293, '0293', 'الشبلي', 'Chebli', 'Chebli', 9),
(294, '0294', 'الشفة', 'Chiffa', 'Chiffa', 9),
(295, '0295', 'الشريعة', 'Chrea', 'Chrea', 9),
(296, '0296', 'جبابرة', 'Djebabra', 'Djebabra', 9),
(297, '0297', 'العفرون', 'El-Affroun', 'El-Affroun', 9),
(298, '0298', 'قرواو', 'Guerrouaou', 'Guerrouaou', 9),
(299, '0299', 'حمام ملوان', 'Hammam Elouane', 'Hammam Elouane', 9),
(300, '0300', 'الأربعاء', 'Larbaa', 'Larbaa', 9),
(301, '0301', 'مفتاح', 'Meftah', 'Meftah', 9),
(302, '0302', 'موزاية', 'Mouzaia', 'Mouzaia', 9),
(303, '0303', 'وادي جر', 'Oued  Djer', 'Oued  Djer', 9),
(304, '0304', 'وادي العلايق', 'Oued El Alleug', 'Oued El Alleug', 9),
(305, '0305', 'اولاد سلامة', 'Ouled Slama', 'Ouled Slama', 9),
(306, '0306', 'أولاد يعيش', 'Ouled Yaich', 'Ouled Yaich', 9),
(307, '0307', 'صوحان', 'Souhane', 'Souhane', 9),
(308, '0308', 'الصومعة', 'Soumaa', 'Soumaa', 9),
(309, '0309', 'أغبالو', 'Aghbalou', 'Aghbalou', 10),
(310, '0310', 'أهل القصر', 'Ahl El Ksar', 'Ahl El Ksar', 10),
(311, '0311', 'عين الحجر', 'Ain El Hadjar', 'Ain El Hadjar', 10),
(312, '0312', 'عين العلوي', 'Ain Laloui', 'Ain Laloui', 10),
(313, '0313', 'عين الترك', 'Ain Turk', 'Ain Turk', 10),
(314, '0314', 'عين بسام', 'Ain-Bessem', 'Ain-Bessem', 10),
(315, '0315', 'أيت لعزيز', 'Ait Laaziz', 'Ait Laaziz', 10),
(316, '0316', 'أعمر', 'Aomar', 'Aomar', 10),
(317, '0317', 'بشلول', 'Bechloul', 'Bechloul', 10),
(318, '0318', 'بئر غبالو', 'Bir Ghbalou', 'Bir Ghbalou', 10),
(319, '0319', 'برج أوخريص', 'Bordj Okhriss', 'Bordj Okhriss', 10),
(320, '0320', 'بودربالة', 'Bouderbala', 'Bouderbala', 10),
(321, '0321', 'البويرة', 'Bouira', 'Bouira', 10),
(322, '0322', 'بوكرم', 'Boukram', 'Boukram', 10),
(323, '0323', 'شرفة', 'Chorfa', 'Chorfa', 10),
(324, '0324', 'الدشمية', 'Dechmia', 'Dechmia', 10),
(325, '0325', 'ديرة', 'Dirah', 'Dirah', 10),
(326, '0326', 'جباحية', 'Djebahia', 'Djebahia', 10),
(327, '0327', 'العجيبة', 'El Adjiba', 'El Adjiba', 10),
(328, '0328', 'الأسنام', 'El Asnam', 'El Asnam', 10),
(329, '0329', 'الهاشمية', 'El Hachimia', 'El Hachimia', 10),
(330, '0330', 'الخبوزية', 'El Khabouzia', 'El Khabouzia', 10),
(331, '0331', 'الحاكمية', 'El-Hakimia', 'El-Hakimia', 10),
(332, '0332', 'المقراني', 'El-Mokrani', 'El-Mokrani', 10),
(333, '0333', 'قرومة', 'Guerrouma', 'Guerrouma', 10),
(334, '0334', 'الحجرة الزرقاء', 'Hadjera Zerga', 'Hadjera Zerga', 10),
(335, '0335', 'حيزر', 'Haizer', 'Haizer', 10),
(336, '0336', 'حنيف', 'Hanif', 'Hanif', 10),
(337, '0337', 'قادرية', 'Kadiria', 'Kadiria', 10),
(338, '0338', 'الأخضرية', 'Lakhdaria', 'Lakhdaria', 10),
(339, '0339', 'أمشدالة', 'M Chedallah', 'M Chedallah', 10),
(340, '0340', 'معلة', 'Maala', 'Maala', 10),
(341, '0341', 'المعمورة', 'Maamora', 'Maamora', 10),
(342, '0342', 'مزدور', 'Mezdour', 'Mezdour', 10),
(343, '0343', 'وادي البردي', 'Oued El Berdi', 'Oued El Berdi', 10),
(344, '0344', 'أولاد راشد', 'Ouled Rached', 'Ouled Rached', 10),
(345, '0345', 'روراوة', 'Raouraoua', 'Raouraoua', 10),
(346, '0346', 'ريدان', 'Ridane', 'Ridane', 10),
(347, '0347', 'سحاريج', 'Saharidj', 'Saharidj', 10),
(348, '0348', 'سوق الخميس', 'Souk El Khemis', 'Souk El Khemis', 10),
(349, '0349', 'سور الغزلان', 'Sour El Ghozlane', 'Sour El Ghozlane', 10),
(350, '0350', 'تاغزوت', 'Taghzout', 'Taghzout', 10),
(351, '0351', 'تاقديت', 'Taguedite', 'Taguedite', 10),
(352, '0352', 'آث  منصور', 'Ath Mansour', 'Ath Mansour', 10),
(353, '0353', 'زبربر', 'Z\'barbar (El Isseri )', 'Z\'barbar (El Isseri )', 10),
(354, '0354', 'ابلسة', 'Abelsa', 'Abelsa', 11),
(355, '0355', 'عين امقل', 'Ain Amguel', 'Ain Amguel', 11),
(356, '0356', 'عين قزام', 'Ain Guezzam', 'Ain Guezzam', 54),
(357, '0357', 'عين صالح', 'Ain Salah', 'Ain Salah', 53),
(358, '0358', 'فقارة الزوى', 'Foggaret Ezzoua', 'Foggaret Ezzoua', 53),
(359, '0359', 'أدلس', 'Idles', 'Idles', 11),
(360, '0360', 'إينغر', 'Inghar', 'Inghar', 53),
(361, '0361', 'تمنراست', 'Tamanrasset', 'Tamanrasset', 11),
(362, '0362', 'تاظروك', 'Tazrouk', 'Tazrouk', 11),
(363, '0363', 'تين زواتين', 'Tin Zouatine', 'Tin Zouatine', 54),
(364, '0364', 'عين الزرقاء', 'Ain Zerga', 'Ain Zerga', 12),
(365, '0365', 'بجن', 'Bedjene', 'Bedjene', 12),
(366, '0366', 'بكارية', 'Bekkaria', 'Bekkaria', 12),
(367, '0367', 'بئر الذهب', 'Bir Dheheb', 'Bir Dheheb', 12),
(368, '0368', 'بئر مقدم', 'Bir Mokkadem', 'Bir Mokkadem', 12),
(369, '0369', 'بئر العاتر', 'Bir-El-Ater', 'Bir-El-Ater', 12),
(370, '0370', 'بوخضرة', 'Boukhadra', 'Boukhadra', 12),
(371, '0371', 'بولحاف الدير', 'Boulhaf Dyr', 'Boulhaf Dyr', 12),
(372, '0372', 'الشريعة', 'Cheria', 'Cheria', 12),
(373, '0373', 'الكويف', 'El Kouif', 'El Kouif', 12),
(374, '0374', 'الماء الابيض', 'El Malabiod', 'El Malabiod', 12),
(375, '0375', 'المريج', 'El Meridj', 'El Meridj', 12),
(376, '0376', 'المزرعة', 'El Mezeraa', 'El Mezeraa', 12),
(377, '0377', 'العقلة', 'El Ogla', 'El Ogla', 12),
(378, '0378', 'العقلة المالحة', 'El Ogla El Malha', 'El Ogla El Malha', 12),
(379, '0379', 'العوينات', 'El-Aouinet', 'El-Aouinet', 12),
(380, '0380', 'الحويجبات', 'El-Houidjbet', 'El-Houidjbet', 12),
(381, '0381', 'فركان', 'Ferkane', 'Ferkane', 12),
(382, '0382', 'قريقر', 'Guorriguer', 'Guorriguer', 12),
(383, '0383', 'الحمامات', 'Hammamet', 'Hammamet', 12),
(384, '0384', 'مرسط', 'Morsott', 'Morsott', 12),
(385, '0385', 'نقرين', 'Negrine', 'Negrine', 12),
(386, '0386', 'الونزة', 'Ouenza', 'Ouenza', 12),
(387, '0387', 'أم علي', 'Oum Ali', 'Oum Ali', 12),
(388, '0388', 'صفصاف الوسرى', 'Saf Saf El Ouesra', 'Saf Saf El Ouesra', 12),
(389, '0389', 'سطح قنطيس', 'Stah Guentis', 'Stah Guentis', 12),
(390, '0390', 'تبسة', 'Tebessa', 'Tebessa', 12),
(391, '0391', 'ثليجان', 'Telidjen', 'Telidjen', 12),
(392, '0392', 'عين فتاح', 'Ain Fetah', 'Ain Fetah', 13),
(393, '0393', 'عين فزة', 'Ain Fezza', 'Ain Fezza', 13),
(394, '0394', 'عين غرابة', 'Ain Ghoraba', 'Ain Ghoraba', 13),
(395, '0395', 'عين الكبيرة', 'Ain Kebira', 'Ain Kebira', 13),
(396, '0396', 'عين النحالة', 'Ain Nehala', 'Ain Nehala', 13),
(397, '0397', 'عين تالوت', 'Ain Tellout', 'Ain Tellout', 13),
(398, '0398', 'عين يوسف', 'Ain Youcef', 'Ain Youcef', 13),
(399, '0399', 'عمير', 'Amieur', 'Amieur', 13),
(400, '0400', 'باب العسة', 'Bab El Assa', 'Bab El Assa', 13),
(401, '0401', 'بني بهدل', 'Beni Bahdel', 'Beni Bahdel', 13),
(402, '0402', 'بني بوسعيد', 'Beni Boussaid', 'Beni Boussaid', 13),
(403, '0403', 'بني خلاد', 'Beni Khellad', 'Beni Khellad', 13),
(404, '0404', 'بني مستر', 'Beni Mester', 'Beni Mester', 13),
(405, '0405', 'بني وارسوس', 'Beni Ouarsous', 'Beni Ouarsous', 13),
(406, '0406', 'بني صميل', 'Beni Smiel', 'Beni Smiel', 13),
(407, '0407', 'بني سنوس', 'Beni Snous', 'Beni Snous', 13),
(408, '0408', 'بن سكران', 'Bensekrane', 'Bensekrane', 13),
(409, '0409', 'بوحلو', 'Bouhlou', 'Bouhlou', 13),
(410, '0410', 'البويهي', 'Bouihi', 'Bouihi', 13),
(411, '0411', 'شتوان', 'Chetouane', 'Chetouane', 13),
(412, '0412', 'دار يغمراسن', 'Dar Yaghmoracen', 'Dar Yaghmoracen', 13),
(413, '0413', 'جبالة', 'Djebala', 'Djebala', 13),
(414, '0414', 'العريشة', 'El Aricha', 'El Aricha', 13),
(415, '0415', 'العزايل', 'Azail', 'Azail', 13),
(416, '0416', 'الفحول', 'El Fehoul', 'El Fehoul', 13),
(417, '0417', 'القور', 'El Gor', 'El Gor', 13),
(418, '0418', 'فلاوسن', 'Fellaoucene', 'Fellaoucene', 13),
(419, '0419', 'الغزوات', 'Ghazaouet', 'Ghazaouet', 13),
(420, '0420', 'حمام بوغرارة', 'Hammam Boughrara', 'Hammam Boughrara', 13),
(421, '0421', 'الحناية', 'Hennaya', 'Hennaya', 13),
(422, '0422', 'هنين', 'Honnaine', 'Honnaine', 13),
(423, '0423', 'مغنية', 'Maghnia', 'Maghnia', 13),
(424, '0424', 'منصورة', 'Mansourah', 'Mansourah', 13),
(425, '0425', 'مرسى بن مهيدي', 'Marsa Ben M\'hidi', 'Marsa Ben M\'hidi', 13),
(426, '0426', 'مسيردة الفواقة', 'M\'sirda Fouaga', 'M\'sirda Fouaga', 13),
(427, '0427', 'ندرومة', 'Nedroma', 'Nedroma', 13),
(428, '0428', 'وادي الخضر', 'Oued Lakhdar', 'Oued Lakhdar', 13),
(429, '0429', 'أولاد ميمون', 'Ouled Mimoun', 'Ouled Mimoun', 13),
(430, '0430', 'أولاد رياح', 'Ouled Riyah', 'Ouled Riyah', 13),
(431, '0431', 'الرمشي', 'Remchi', 'Remchi', 13),
(432, '0432', 'صبرة', 'Sabra', 'Sabra', 13),
(433, '0433', 'سبعة شيوخ', 'Sebbaa Chioukh', 'Sebbaa Chioukh', 13),
(434, '0434', 'سبدو', 'Sebdou', 'Sebdou', 13),
(435, '0435', 'سيدي العبدلي', 'Sidi Abdelli', 'Sidi Abdelli', 13),
(436, '0436', 'سيدي الجيلالي', 'Sidi Djillali', 'Sidi Djillali', 13),
(437, '0437', 'سيدي مجاهد', 'Sidi Medjahed', 'Sidi Medjahed', 13),
(438, '0438', 'السواحلية', 'Souahlia', 'Souahlia', 13),
(439, '0439', 'السواني', 'Souani', 'Souani', 13),
(440, '0440', 'سوق الثلاثاء', 'Souk Tleta', 'Souk Tleta', 13),
(441, '0441', 'تيرني بني هديل', 'Terny Beni Hediel', 'Terny Beni Hediel', 13),
(442, '0442', 'تيانت', 'Tianet', 'Tianet', 13),
(443, '0443', 'تلمسان', 'Tlemcen', 'Tlemcen', 13),
(444, '0444', 'زناتة', 'Zenata', 'Zenata', 13),
(445, '0445', 'عين بوشقيف', 'Ain Bouchekif', 'Ain Bouchekif', 14),
(446, '0446', 'عين الذهب', 'Ain Deheb', 'Ain Deheb', 14),
(447, '0447', 'عين دزاريت', 'Ain Dzarit', 'Ain Dzarit', 14),
(448, '0448', 'عين الحديد', 'Ain El Hadid', 'Ain El Hadid', 14),
(449, '0449', 'عين كرمس', 'Ain Kermes', 'Ain Kermes', 14),
(450, '0450', 'بوقرة', 'Bougara', 'Bougara', 14),
(451, '0451', 'شحيمة', 'Chehaima', 'Chehaima', 14),
(452, '0452', 'دحموني', 'Dahmouni', 'Dahmouni', 14),
(453, '0453', 'جبيلات الرصفاء', 'Djebilet Rosfa', 'Djebilet Rosfa', 14),
(454, '0454', 'جيلالي بن عمار', 'Djillali Ben Amar', 'Djillali Ben Amar', 14),
(455, '0455', 'الفايجة', 'Faidja', 'Faidja', 14),
(456, '0456', 'فرندة', 'Frenda', 'Frenda', 14),
(457, '0457', 'قرطوفة', 'Guertoufa', 'Guertoufa', 14),
(458, '0458', 'حمادية', 'Hamadia', 'Hamadia', 14),
(459, '0459', 'قصر الشلالة', 'Ksar Chellala', 'Ksar Chellala', 14),
(460, '0460', 'مادنة', 'Madna', 'Madna', 14),
(461, '0461', 'مهدية', 'Mahdia', 'Mahdia', 14),
(462, '0462', 'مشرع الصفا', 'Mechraa Safa', 'Mechraa Safa', 14),
(463, '0463', 'مدريسة', 'Medrissa', 'Medrissa', 14),
(464, '0464', 'مدروسة', 'Medroussa', 'Medroussa', 14),
(465, '0465', 'مغيلة', 'Meghila', 'Meghila', 14),
(466, '0466', 'ملاكو', 'Mellakou', 'Mellakou', 14),
(467, '0467', 'الناظورة', 'Nadorah', 'Nadorah', 14),
(468, '0468', 'النعيمة', 'Naima', 'Naima', 14),
(469, '0469', 'وادي ليلي', 'Oued Lilli', 'Oued Lilli', 14),
(470, '0470', 'الرحوية', 'Rahouia', 'Rahouia', 14),
(471, '0471', 'الرشايقة', 'Rechaiga', 'Rechaiga', 14),
(472, '0472', 'السبعين', 'Sebaine', 'Sebaine', 14),
(473, '0473', 'السبت', 'Sebt', 'Sebt', 14),
(474, '0474', 'سرغين', 'Serghine', 'Serghine', 14),
(475, '0475', 'سي عبد الغني', 'Si Abdelghani', 'Si Abdelghani', 14),
(476, '0476', 'سيدي عبد الرحمن', 'Sidi Abderrahmane', 'Sidi Abderrahmane', 14),
(477, '0477', 'سيدي علي ملال', 'Sidi Ali Mellal', 'Sidi Ali Mellal', 14),
(478, '0478', 'سيدي بختي', 'Sidi Bakhti', 'Sidi Bakhti', 14),
(479, '0479', 'سيدي حسني', 'Sidi Hosni', 'Sidi Hosni', 14),
(480, '0480', 'السوقر', 'Sougueur', 'Sougueur', 14),
(481, '0481', 'تاقدمت', 'Tagdempt', 'Tagdempt', 14),
(482, '0482', 'تخمرت', 'Takhemaret', 'Takhemaret', 14),
(483, '0483', 'تيارت', 'Tiaret', 'Tiaret', 14),
(484, '0484', 'تيدة', 'Tidda', 'Tidda', 14),
(485, '0485', 'توسنينة', 'Tousnina', 'Tousnina', 14),
(486, '0486', 'زمالة  الأمير عبد القادر', 'Zmalet El Emir Abdelkade', 'Zmalet El Emir Abdelkade', 14),
(487, '0487', 'أبي يوسف', 'Abi-Youcef', 'Abi-Youcef', 15),
(488, '0488', 'أغريب', 'Aghribs', 'Aghribs', 15),
(489, '0489', 'أقني قغران', 'Agouni-Gueghrane', 'Agouni-Gueghrane', 15),
(490, '0490', 'عين الحمام', 'Ain-El-Hammam', 'Ain-El-Hammam', 15),
(491, '0491', 'عين الزاوية', 'Ain-Zaouia', 'Ain-Zaouia', 15),
(492, '0492', 'أيت عقـواشة', 'Ait Aggouacha', 'Ait Aggouacha', 15),
(493, '0493', 'أيت بــوادو', 'Ait Bouaddou', 'Ait Bouaddou', 15),
(494, '0494', 'أيت بومهدي', 'Ait Boumahdi', 'Ait Boumahdi', 15),
(495, '0495', 'أيت خليلي', 'Ait Khellili', 'Ait Khellili', 15),
(496, '0496', 'أيت يحي موسى', 'Ait Yahia Moussa', 'Ait Yahia Moussa', 15),
(497, '0497', 'أيت عيسى ميمون', 'Ait-Aissa-Mimoun', 'Ait-Aissa-Mimoun', 15),
(498, '0498', 'أيت شافع', 'Ait-Chafaa', 'Ait-Chafaa', 15),
(499, '0499', 'أيت محمود', 'Ait-Mahmoud', 'Ait-Mahmoud', 15),
(500, '0500', 'أيت  أومالو', 'Ait-Oumalou', 'Ait-Oumalou', 15),
(501, '0501', 'أيت تودرت', 'Ait-Toudert', 'Ait-Toudert', 15),
(502, '0502', 'أيت يحيى', 'Ait-Yahia', 'Ait-Yahia', 15),
(503, '0503', 'اقبيل', 'Akbil', 'Akbil', 15),
(504, '0504', 'أقرو', 'Akerrou', 'Akerrou', 15),
(505, '0505', 'أسي يوسف', 'Assi-Youcef', 'Assi-Youcef', 15),
(506, '0506', 'عزازقة', 'Azazga', 'Azazga', 15),
(507, '0507', 'أزفون', 'Azeffoun', 'Azeffoun', 15),
(508, '0508', 'بنــــي زمنزار', 'Beni Zmenzer', 'Beni Zmenzer', 15),
(509, '0509', 'بني عيسي', 'Beni-Aissi', 'Beni-Aissi', 15),
(510, '0510', 'بني دوالة', 'Beni-Douala', 'Beni-Douala', 15),
(511, '0511', 'بني يني', 'Beni-Yenni', 'Beni-Yenni', 15),
(512, '0512', 'بني زيكــي', 'Beni-Zikki', 'Beni-Zikki', 15),
(513, '0513', 'بوغني', 'Boghni', 'Boghni', 15),
(514, '0514', 'بوجيمة', 'Boudjima', 'Boudjima', 15),
(515, '0515', 'بونوح', 'Bounouh', 'Bounouh', 15),
(516, '0516', 'بوزقــن', 'Bouzeguene', 'Bouzeguene', 15),
(517, '0517', 'ذراع بن خدة', 'Draa-Ben-Khedda', 'Draa-Ben-Khedda', 15),
(518, '0518', 'ذراع الميزان', 'Draa-El-Mizan', 'Draa-El-Mizan', 15),
(519, '0519', 'فريحة', 'Freha', 'Freha', 15),
(520, '0520', 'فريقات', 'Frikat', 'Frikat', 15),
(521, '0521', 'إبودرارن', 'Iboudrarene', 'Iboudrarene', 15),
(522, '0522', 'إيجــار', 'Idjeur', 'Idjeur', 15),
(523, '0523', 'إفــرحــونان', 'Iferhounene', 'Iferhounene', 15),
(524, '0524', 'إيفيغاء', 'Ifigha', 'Ifigha', 15),
(525, '0525', 'إفليـــسن', 'Iflissen', 'Iflissen', 15),
(526, '0526', 'إيلـيــلتـن', 'Illilten', 'Illilten', 15),
(527, '0527', 'إيلولة أومـــالو', 'Illoula Oumalou', 'Illoula Oumalou', 15),
(528, '0528', 'إمســوحال', 'Imsouhal', 'Imsouhal', 15),
(529, '0529', 'إيرجـــن', 'Irdjen', 'Irdjen', 15),
(530, '0530', 'الأربعــاء ناث إيراثن', 'Larbaa Nath Irathen', 'Larbaa Nath Irathen', 15),
(531, '0531', 'معـــاتقة', 'Maatkas', 'Maatkas', 15),
(532, '0532', 'ماكودة', 'Makouda', 'Makouda', 15),
(533, '0533', 'مشطراس', 'Mechtras', 'Mechtras', 15),
(534, '0534', 'مقــلع', 'Mekla', 'Mekla', 15),
(535, '0535', 'ميزرانـــة', 'Mizrana', 'Mizrana', 15),
(536, '0536', 'مكيرة', 'M\'kira', 'M\'kira', 15),
(537, '0537', 'واسيف', 'Ouacif', 'Ouacif', 15),
(538, '0538', 'واضية', 'Ouadhias', 'Ouadhias', 15),
(539, '0539', 'واقنون', 'Ouaguenoun', 'Ouaguenoun', 15),
(540, '0540', 'سيدي نعمان', 'Sidi Namane', 'Sidi Namane', 15),
(541, '0541', 'صوامـــع', 'Souama', 'Souama', 15),
(542, '0542', 'سوق الإثنين', 'Souk-El-Tenine', 'Souk-El-Tenine', 15),
(543, '0543', 'تادمايت', 'Tadmait', 'Tadmait', 15),
(544, '0544', 'تيقـزيرت', 'Tigzirt', 'Tigzirt', 15),
(545, '0545', 'تيمـيزار', 'Timizart', 'Timizart', 15),
(546, '0546', 'تيرمتين', 'Tirmitine', 'Tirmitine', 15),
(547, '0547', 'تيزي نثلاثة', 'Tizi N\'tleta', 'Tizi N\'tleta', 15),
(548, '0548', 'تيزي غنيف', 'Tizi-Gheniff', 'Tizi-Gheniff', 15),
(549, '0549', 'تيزي وزو', 'Tizi-Ouzou', 'Tizi-Ouzou', 15),
(550, '0550', 'تيزي راشد', 'Tizi-Rached', 'Tizi-Rached', 15),
(551, '0551', 'إعــكورن', 'Yakourene', 'Yakourene', 15),
(552, '0552', 'يطــافن', 'Yatafene', 'Yatafene', 15),
(553, '0553', 'زكري', 'Zekri', 'Zekri', 15),
(554, '0554', 'عين بنيان', 'Ain Benian', 'Ain Benian', 16),
(555, '0555', 'عين طاية', 'Ain Taya', 'Ain Taya', 16),
(556, '0556', 'الجزائر الوسطى', 'Alger Centre', 'Alger Centre', 16),
(557, '0557', 'باب الوادي', 'Bab El Oued', 'Bab El Oued', 16),
(558, '0558', 'باب الزوار', 'Bab Ezzouar', 'Bab Ezzouar', 16),
(559, '0559', 'بابا حسن', 'Baba Hassen', 'Baba Hassen', 16),
(560, '0560', 'باش جراح', 'Bachedjerah', 'Bachedjerah', 16),
(561, '0561', 'براقي', 'Baraki', 'Baraki', 16),
(562, '0562', 'ابن عكنون', 'Ben Aknoun', 'Ben Aknoun', 16),
(563, '0563', 'بني مسوس', 'Beni Messous', 'Beni Messous', 16),
(564, '0564', 'بئر مراد رايس', 'Bir Mourad Rais', 'Bir Mourad Rais', 16),
(565, '0565', 'بئر خادم', 'Birkhadem', 'Birkhadem', 16),
(566, '0566', 'بئر توتة', 'Bir Touta', 'Bir Touta', 16),
(567, '0567', 'بولوغين بن زيري', 'Bologhine Ibnou Ziri', 'Bologhine Ibnou Ziri', 16),
(568, '0568', 'برج البحري', 'Bordj El Bahri', 'Bordj El Bahri', 16),
(569, '0569', 'برج الكيفان', 'Bordj El Kiffan', 'Bordj El Kiffan', 16),
(570, '0570', 'بوروبة', 'Bourouba', 'Bourouba', 16),
(571, '0571', 'بوزريعة', 'Bouzareah', 'Bouzareah', 16),
(572, '0572', 'القصبة', 'Casbah', 'Casbah', 16),
(573, '0573', 'الشراقة', 'Cheraga', 'Cheraga', 16),
(574, '0574', 'الدار البيضاء', 'Dar El Beida', 'Dar El Beida', 16),
(575, '0575', 'دالي ابراهيم', 'Dely Ibrahim', 'Dely Ibrahim', 16),
(576, '0576', 'جسر قسنطينة', 'Djasr Kasentina', 'Djasr Kasentina', 16),
(577, '0577', 'الدويرة', 'Douira', 'Douira', 16),
(578, '0578', 'الدرارية', 'Draria', 'Draria', 16),
(579, '0579', 'العاشور', 'El Achour', 'El Achour', 16),
(580, '0580', 'الابيار', 'El Biar', 'El Biar', 16),
(581, '0581', 'الحراش', 'El Harrach', 'El Harrach', 16),
(582, '0582', 'المدنية', 'El Madania', 'El Madania', 16),
(583, '0583', 'المغارية', 'El Magharia', 'El Magharia', 16),
(584, '0584', 'المرسى', 'El Marsa', 'El Marsa', 16),
(585, '0585', 'المرادية', 'El Mouradia', 'El Mouradia', 16),
(586, '0586', 'الحمامات', 'Hammamet', 'Hammamet', 16),
(587, '0587', 'هراوة', 'Herraoua', 'Herraoua', 16),
(588, '0588', 'حسين داي', 'Hussein Dey', 'Hussein Dey', 16),
(589, '0589', 'حيدرة', 'Hydra', 'Hydra', 16),
(590, '0590', 'الخرايسية', 'Khraissia', 'Khraissia', 16),
(591, '0591', 'القبة', 'Kouba', 'Kouba', 16),
(592, '0592', 'الكاليتوس', 'Les Eucalyptus', 'Les Eucalyptus', 16),
(593, '0593', 'المعالمة', 'Maalma', 'Maalma', 16),
(594, '0594', 'محمد بلوزداد', 'Mohamed Belouzdad', 'Mohamed Belouzdad', 16),
(595, '0595', 'المحمدية', 'Mohammadia', 'Mohammadia', 16),
(596, '0596', 'وادي قريش', 'Oued Koriche', 'Oued Koriche', 16),
(597, '0597', 'وادي السمار', 'Oued Smar', 'Oued Smar', 16),
(598, '0598', 'اولاد شبل', 'Ouled Chebel', 'Ouled Chebel', 16),
(599, '0599', 'اولاد فايت', 'Ouled Fayet', 'Ouled Fayet', 16),
(600, '0600', 'الرحمانية', 'Rahmania', 'Rahmania', 16),
(601, '0601', 'الرايس حميدو', 'Rais Hamidou', 'Rais Hamidou', 16),
(602, '0602', 'رغاية', 'Reghaia', 'Reghaia', 16),
(603, '0603', 'الرويبة', 'Rouiba', 'Rouiba', 16),
(604, '0604', 'السحاولة', 'Sehaoula', 'Sehaoula', 16),
(605, '0605', 'سيدي امحمد', 'Sidi M\'hamed', 'Sidi M\'hamed', 16),
(606, '0606', 'سيدي موسى', 'Sidi Moussa', 'Sidi Moussa', 16),
(607, '0607', 'سويدانية', 'Souidania', 'Souidania', 16),
(608, '0608', 'سطاوالي', 'Staoueli', 'Staoueli', 16),
(609, '0609', 'تسالة المرجة', 'Tessala El Merdja', 'Tessala El Merdja', 16),
(610, '0610', 'زرالدة', 'Zeralda', 'Zeralda', 16),
(611, '0611', 'عين الشهداء', 'Ain Chouhada', 'Ain Chouhada', 17),
(612, '0612', 'عين الإبل', 'Ain El Ibel', 'Ain El Ibel', 17),
(613, '0613', 'عين فقه', 'Ain Fekka', 'Ain Fekka', 17),
(614, '0614', 'عين معبد', 'Ain Maabed', 'Ain Maabed', 17),
(615, '0615', 'عين وسارة', 'Ain Oussera', 'Ain Oussera', 17),
(616, '0616', 'عمورة', 'Amourah', 'Amourah', 17),
(617, '0617', 'بنهار', 'Benhar', 'Benhar', 17),
(618, '0618', 'بن يعقوب', 'Benyagoub', 'Benyagoub', 17),
(619, '0619', 'بيرين', 'Birine', 'Birine', 17),
(620, '0620', 'بويرة الأحداب', 'Bouira Lahdab', 'Bouira Lahdab', 17),
(621, '0621', 'الشارف', 'Charef', 'Charef', 17),
(622, '0622', 'دار الشيوخ', 'Dar Chioukh', 'Dar Chioukh', 17),
(623, '0623', 'دلدول', 'Deldoul', 'Deldoul', 17),
(624, '0624', 'الجلفة', 'Djelfa', 'Djelfa', 17),
(625, '0625', 'دويس', 'Douis', 'Douis', 17),
(626, '0626', 'القديد', 'El Guedid', 'El Guedid', 17),
(627, '0627', 'الادريسية', 'El Idrissia', 'El Idrissia', 17),
(628, '0628', 'الخميس', 'El Khemis', 'El Khemis', 17),
(629, '0629', 'فيض البطمة', 'Faidh El Botma', 'Faidh El Botma', 17),
(630, '0630', 'قرنيني', 'Guernini', 'Guernini', 17),
(631, '0631', 'قطارة', 'Guettara', 'Guettara', 17),
(632, '0632', 'حد الصحاري', 'Had Sahary', 'Had Sahary', 17),
(633, '0633', 'حاسي بحبح', 'Hassi Bahbah', 'Hassi Bahbah', 17),
(634, '0634', 'حاسي العش', 'Hassi El Euch', 'Hassi El Euch', 17),
(635, '0635', 'حاسي فدول', 'Hassi Fedoul', 'Hassi Fedoul', 17),
(636, '0636', 'مسعد', 'Messaad', 'Messaad', 17),
(637, '0637', 'مليليحة', 'M\'liliha', 'M\'liliha', 17),
(638, '0638', 'مجبارة', 'Moudjebara', 'Moudjebara', 17),
(639, '0639', 'أم العظام', 'Oum Laadham', 'Oum Laadham', 17),
(640, '0640', 'سد الرحال', 'Sed Rahal', 'Sed Rahal', 17),
(641, '0641', 'سلمانة', 'Selmana', 'Selmana', 17),
(642, '0642', 'سيدي بايزيد', 'Sidi Baizid', 'Sidi Baizid', 17),
(643, '0643', 'سيدي لعجال', 'Sidi Laadjel', 'Sidi Laadjel', 17),
(644, '0644', 'تعظميت', 'Taadmit', 'Taadmit', 17),
(645, '0645', 'زعفران', 'Zaafrane', 'Zaafrane', 17),
(646, '0646', 'زكار', 'Zaccar', 'Zaccar', 17),
(647, '0647', 'برج الطهر', 'Bordj T\'har', 'Bordj T\'har', 18),
(648, '0648', 'بودريعة بني  ياجيس', 'Boudria Beniyadjis', 'Boudria Beniyadjis', 18),
(649, '0649', 'بوراوي بلهادف', 'Bouraoui Belhadef', 'Bouraoui Belhadef', 18),
(650, '0650', 'بوسيف أولاد عسكر', 'Boussif Ouled Askeur', 'Boussif Ouled Askeur', 18),
(651, '0651', 'الشحنة', 'Chahna', 'Chahna', 18),
(652, '0652', 'الشقفة', 'Chekfa', 'Chekfa', 18),
(653, '0653', 'الجمعة بني حبيبي', 'Djemaa Beni Habibi', 'Djemaa Beni Habibi', 18),
(654, '0654', 'جيملة', 'Djimla', 'Djimla', 18),
(655, '0655', 'العنصر', 'El Ancer', 'El Ancer', 18),
(656, '0656', 'العوانة', 'El Aouana', 'El Aouana', 18),
(657, '0657', 'القنار نشفي', 'El Kennar Nouchfi', 'El Kennar Nouchfi', 18),
(658, '0658', 'الميلية', 'El Milia', 'El Milia', 18),
(659, '0659', 'الامير عبد القادر', 'Emir Abdelkader', 'Emir Abdelkader', 18),
(660, '0660', 'أراقن سويسي', 'Erraguene Souissi', 'Erraguene Souissi', 18),
(661, '0661', 'غبالة', 'Ghebala', 'Ghebala', 18),
(662, '0662', 'جيجل', 'Jijel', 'Jijel', 18),
(663, '0663', 'قاوس', 'Kaous', 'Kaous', 18),
(664, '0664', 'خيري واد عجول', 'Khiri Oued Adjoul', 'Khiri Oued Adjoul', 18),
(665, '0665', 'وجانة', 'Oudjana', 'Oudjana', 18),
(666, '0666', 'أولاد رابح', 'Ouled Rabah', 'Ouled Rabah', 18),
(667, '0667', 'أولاد يحيى خدروش', 'Ouled Yahia Khadrouch', 'Ouled Yahia Khadrouch', 18),
(668, '0668', 'سلمى بن زيادة', 'Selma Benziada', 'Selma Benziada', 18),
(669, '0669', 'السطارة', 'Settara', 'Settara', 18),
(670, '0670', 'سيدي عبد العزيز', 'Sidi Abdelaziz', 'Sidi Abdelaziz', 18),
(671, '0671', 'سيدي معروف', 'Sidi Marouf', 'Sidi Marouf', 18),
(672, '0672', 'الطاهير', 'Taher', 'Taher', 18),
(673, '0673', 'تاكسنة', 'Texenna', 'Texenna', 18),
(674, '0674', 'زيامة منصورية', 'Ziama Mansouriah', 'Ziama Mansouriah', 18),
(675, '0675', 'عين عباسة', 'Ain Abessa', 'Ain Abessa', 19),
(676, '0676', 'عين أرنات', 'Ain Arnat', 'Ain Arnat', 19),
(677, '0677', 'عين أزال', 'Ain Azel', 'Ain Azel', 19),
(678, '0678', 'عين الكبيرة', 'Ain El Kebira', 'Ain El Kebira', 19),
(679, '0679', 'عين الحجر', 'Ain Lahdjar', 'Ain Lahdjar', 19),
(680, '0680', 'عين ولمان', 'Ain Oulmene', 'Ain Oulmene', 19),
(681, '0681', 'عين لقراج', 'Ain-Legradj', 'Ain-Legradj', 19),
(682, '0682', 'عين الروى', 'Ain-Roua', 'Ain-Roua', 19),
(683, '0683', 'عين السبت', 'Ain-Sebt', 'Ain-Sebt', 19),
(684, '0684', 'أيت نوال مزادة', 'Ait Naoual Mezada', 'Ait Naoual Mezada', 19),
(685, '0685', 'ايت تيزي', 'Ait-Tizi', 'Ait-Tizi', 19),
(686, '0686', 'عموشة', 'Amoucha', 'Amoucha', 19),
(687, '0687', 'بابور', 'Babor', 'Babor', 19),
(688, '0688', 'بازر سكرة', 'Bazer-Sakra', 'Bazer-Sakra', 19),
(689, '0689', 'بيضاء برج', 'Beidha Bordj', 'Beidha Bordj', 19),
(690, '0690', 'بلاعة', 'Bellaa', 'Bellaa', 19),
(691, '0691', 'بني شبانة', 'Beni Chebana', 'Beni Chebana', 19),
(692, '0692', 'بني فودة', 'Beni Fouda', 'Beni Fouda', 19),
(693, '0693', 'بني ورتيلان', 'Beni Ourtilane', 'Beni Ourtilane', 19),
(694, '0694', 'بني وسين', 'Beni Oussine', 'Beni Oussine', 19),
(695, '0695', 'بني عزيز', 'Beni-Aziz', 'Beni-Aziz', 19),
(696, '0696', 'بني موحلي', 'Beni-Mouhli', 'Beni-Mouhli', 19),
(697, '0697', 'بئر حدادة', 'Bir Haddada', 'Bir Haddada', 19),
(698, '0698', 'بئر العرش', 'Bir-El-Arch', 'Bir-El-Arch', 19),
(699, '0699', 'بوعنداس', 'Bouandas', 'Bouandas', 19),
(700, '0700', 'بوقاعة', 'Bougaa', 'Bougaa', 19),
(701, '0701', 'بوسلام', 'Bousselam', 'Bousselam', 19),
(702, '0702', 'بوطالب', 'Boutaleb', 'Boutaleb', 19),
(703, '0703', 'الدهامشة', 'Dehamcha', 'Dehamcha', 19),
(704, '0704', 'جميلة', 'Djemila', 'Djemila', 19),
(705, '0705', 'ذراع قبيلة', 'Draa-Kebila', 'Draa-Kebila', 19),
(706, '0706', 'العلمة', 'El Eulma', 'El Eulma', 19),
(707, '0707', 'أوريسيا', 'El Ouricia', 'El Ouricia', 19),
(708, '0708', 'الولجة', 'El-Ouldja', 'El-Ouldja', 19),
(709, '0709', 'قلال', 'Guellal', 'Guellal', 19),
(710, '0710', 'قلتة زرقاء', 'Guelta Zerka', 'Guelta Zerka', 19),
(711, '0711', 'قنزات', 'Guenzet', 'Guenzet', 19),
(712, '0712', 'قجال', 'Guidjel', 'Guidjel', 19),
(713, '0713', 'حمام السخنة', 'Hamam Soukhna', 'Hamam Soukhna', 19),
(714, '0714', 'الحامة', 'Hamma', 'Hamma', 19),
(715, '0715', 'حمام قرقور', 'Hammam Guergour', 'Hammam Guergour', 19),
(716, '0716', 'حربيل', 'Harbil', 'Harbil', 19),
(717, '0717', 'قصر الابطال', 'Kasr El Abtal', 'Kasr El Abtal', 19),
(718, '0718', 'معاوية', 'Maaouia', 'Maaouia', 19),
(719, '0719', 'ماوكلان', 'Maouaklane', 'Maouaklane', 19),
(720, '0720', 'مزلوق', 'Mezloug', 'Mezloug', 19),
(721, '0721', 'واد البارد', 'Oued El Bared', 'Oued El Bared', 19),
(722, '0722', 'أولاد عدوان', 'Ouled Addouane', 'Ouled Addouane', 19),
(723, '0723', 'أولاد صابر', 'Ouled Sabor', 'Ouled Sabor', 19),
(724, '0724', 'أولاد سي أحمد', 'Ouled Si Ahmed', 'Ouled Si Ahmed', 19),
(725, '0725', 'أولاد تبان', 'Ouled Tebben', 'Ouled Tebben', 19),
(726, '0726', 'الرصفة', 'Rosfa', 'Rosfa', 19),
(727, '0727', 'صالح باي', 'Salah Bey', 'Salah Bey', 19),
(728, '0728', 'سرج الغول', 'Serdj-El-Ghoul', 'Serdj-El-Ghoul', 19),
(729, '0729', 'سطيف', 'Setif', 'Setif', 19),
(730, '0730', 'تاشودة', 'Tachouda', 'Tachouda', 19),
(731, '0731', 'تالة إيفاسن', 'Tala-Ifacene', 'Tala-Ifacene', 19),
(732, '0732', 'الطاية', 'Taya', 'Taya', 19),
(733, '0733', 'التلة', 'Tella', 'Tella', 19),
(734, '0734', 'تيزي نبشار', 'Tizi N\'bechar', 'Tizi N\'bechar', 19),
(735, '0735', 'عين الحجر', 'Ain El Hadjar', 'Ain El Hadjar', 20),
(736, '0736', 'عين السخونة', 'Ain Sekhouna', 'Ain Sekhouna', 20),
(737, '0737', 'عين السلطان', 'Ain Soltane', 'Ain Soltane', 20),
(738, '0738', 'دوي ثابت', 'Doui Thabet', 'Doui Thabet', 20),
(739, '0739', 'الحساسنة', 'El Hassasna', 'El Hassasna', 20),
(740, '0740', 'هونت', 'Hounet', 'Hounet', 20),
(741, '0741', 'المعمورة', 'Maamora', 'Maamora', 20),
(742, '0742', 'مولاي العربي', 'Moulay Larbi', 'Moulay Larbi', 20),
(743, '0743', 'أولاد إبراهيم', 'Ouled Brahim', 'Ouled Brahim', 20),
(744, '0744', 'أولاد خالد', 'Ouled Khaled', 'Ouled Khaled', 20),
(745, '0745', 'سعيدة', 'Saida', 'Saida', 20),
(746, '0746', 'سيدي احمد', 'Sidi Ahmed', 'Sidi Ahmed', 20),
(747, '0747', 'سيدي عمر', 'Sidi Amar', 'Sidi Amar', 20),
(748, '0748', 'سيدي بوبكر', 'Sidi Boubekeur', 'Sidi Boubekeur', 20),
(749, '0749', 'تيرسين', 'Tircine', 'Tircine', 20),
(750, '0750', 'يوب', 'Youb', 'Youb', 20),
(751, '0751', 'عين بوزيان', 'Ain Bouziane', 'Ain Bouziane', 21),
(752, '0752', 'عين شرشار', 'Ain Charchar', 'Ain Charchar', 21),
(753, '0753', 'عين قشرة', 'Ain Kechra', 'Ain Kechra', 21),
(754, '0754', 'عين زويت', 'Ain Zouit', 'Ain Zouit', 21),
(755, '0755', 'عزابة', 'Azzaba', 'Azzaba', 21),
(756, '0756', 'بكوش لخضر', 'Bekkouche Lakhdar', 'Bekkouche Lakhdar', 21),
(757, '0757', 'بن عزوز', 'Ben Azzouz', 'Ben Azzouz', 21),
(758, '0758', 'بني بشير', 'Beni Bechir', 'Beni Bechir', 21),
(759, '0759', 'بني ولبان', 'Beni Oulbane', 'Beni Oulbane', 21),
(760, '0760', 'بني زيد', 'Beni Zid', 'Beni Zid', 21),
(761, '0761', 'بين الويدان', 'Bin El Ouiden', 'Bin El Ouiden', 21),
(762, '0762', 'بوشطاطة', 'Bouchetata', 'Bouchetata', 21),
(763, '0763', 'الشرايع', 'Cheraia', 'Cheraia', 21),
(764, '0764', 'القل', 'Collo', 'Collo', 21),
(765, '0765', 'جندل سعدي محمد', 'Djendel Saadi Mohamed', 'Djendel Saadi Mohamed', 21),
(766, '0766', 'الحروش', 'El Arrouch', 'El Arrouch', 21),
(767, '0767', 'الغدير', 'El Ghedir', 'El Ghedir', 21),
(768, '0768', 'الحدائق', 'El Hadaiek', 'El Hadaiek', 21),
(769, '0769', 'المرسى', 'El Marsa', 'El Marsa', 21),
(770, '0770', 'مجاز الدشيش', 'Emjez Edchich', 'Emjez Edchich', 21),
(771, '0771', 'السبت', 'Es Sebt', 'Es Sebt', 21),
(772, '0772', 'فلفلة', 'Filfila', 'Filfila', 21),
(773, '0773', 'حمادي كرومة', 'Hammadi Krouma', 'Hammadi Krouma', 21),
(774, '0774', 'قنواع', 'Kanoua', 'Kanoua', 21),
(775, '0775', 'الكركرة', 'Kerkara', 'Kerkara', 21),
(776, '0776', 'خناق مايو', 'Khenag Maoune', 'Khenag Maoune', 21),
(777, '0777', 'وادي الزهور', 'Oued Zhour', 'Oued Zhour', 21),
(778, '0778', 'الولجة بولبلوط', 'Ouldja Boulbalout', 'Ouldja Boulbalout', 21),
(779, '0779', 'أولاد عطية', 'Ouled Attia', 'Ouled Attia', 21),
(780, '0780', 'أولاد حبابة', 'Ouled Habbaba', 'Ouled Habbaba', 21),
(781, '0781', 'أم الطوب', 'Oum Toub', 'Oum Toub', 21),
(782, '0782', 'رمضان جمال', 'Ramdane Djamel', 'Ramdane Djamel', 21),
(783, '0783', 'صالح بو الشعور', 'Salah Bouchaour', 'Salah Bouchaour', 21),
(784, '0784', 'سيدي مزغيش', 'Sidi Mezghiche', 'Sidi Mezghiche', 21),
(785, '0785', 'سكيكدة', 'Skikda', 'Skikda', 21),
(786, '0786', 'تمالوس', 'Tamalous', 'Tamalous', 21),
(787, '0787', 'زردازة', 'Zerdezas', 'Zerdezas', 21),
(788, '0788', 'الزيتونة', 'Zitouna', 'Zitouna', 21),
(789, '0789', 'عين البرد', 'Ain El Berd', 'Ain El Berd', 22),
(790, '0790', 'عين قادة', 'Ain Kada', 'Ain Kada', 22),
(791, '0791', 'عين الثريد', 'Ain Thrid', 'Ain Thrid', 22),
(792, '0792', 'عين تندمين', 'Ain Tindamine', 'Ain Tindamine', 22),
(793, '0793', 'عين أدن', 'Ain- Adden', 'Ain- Adden', 22),
(794, '0794', 'العمارنة', 'Amarnas', 'Amarnas', 22),
(795, '0795', 'بضرابين المقراني', 'Bedrabine El Mokrani', 'Bedrabine El Mokrani', 22),
(796, '0796', 'بلعربي', 'Belarbi', 'Belarbi', 22),
(797, '0797', 'بن باديس', 'Ben Badis', 'Ben Badis', 22),
(798, '0798', 'بن عشيبة شلية', 'Benachiba Chelia', 'Benachiba Chelia', 22),
(799, '0799', 'بئر الحمام', 'Bir El Hammam', 'Bir El Hammam', 22),
(800, '0800', 'بوجبهة البرج', 'Boudjebaa El Bordj', 'Boudjebaa El Bordj', 22),
(801, '0801', 'بوخنفيس', 'Boukhanefis', 'Boukhanefis', 22),
(802, '0802', 'شيطوان البلايلة', 'Chetouane Belaila', 'Chetouane Belaila', 22),
(803, '0803', 'الضاية', 'Dhaya', 'Dhaya', 22),
(804, '0804', 'الحصيبة', 'El Hacaiba', 'El Hacaiba', 22),
(805, '0805', 'حاسي دحو', 'Hassi Dahou', 'Hassi Dahou', 22),
(806, '0806', 'حاسي زهانة', 'Hassi Zahana', 'Hassi Zahana', 22),
(807, '0807', 'لمطار', 'Lamtar', 'Lamtar', 22),
(808, '0808', 'مكدرة', 'Makedra', 'Makedra', 22),
(809, '0809', 'مرحوم', 'Marhoum', 'Marhoum', 22),
(810, '0810', 'مسيد', 'M\'cid', 'M\'cid', 22),
(811, '0811', 'مرين', 'Merine', 'Merine', 22),
(812, '0812', 'مزاورو', 'Mezaourou', 'Mezaourou', 22),
(813, '0813', 'مصطفى بن ابراهيم', 'Mostefa  Ben Brahim', 'Mostefa  Ben Brahim', 22),
(814, '0814', 'مولاي سليسن', 'Moulay Slissen', 'Moulay Slissen', 22),
(815, '0815', 'وادي السبع', 'Oued Sebaa', 'Oued Sebaa', 22),
(816, '0816', 'وادي سفيون', 'Oued Sefioun', 'Oued Sefioun', 22),
(817, '0817', 'وادي تاوريرة', 'Oued Taourira', 'Oued Taourira', 22),
(818, '0818', 'راس الماء', 'Ras El Ma', 'Ras El Ma', 22),
(819, '0819', 'رجم دموش', 'Redjem Demouche', 'Redjem Demouche', 22),
(820, '0820', 'السهالة الثورة', 'Sehala Thaoura', 'Sehala Thaoura', 22),
(821, '0821', 'سفيزف', 'Sfisef', 'Sfisef', 22),
(822, '0822', 'سيدي علي بن يوب', 'Sidi Ali Benyoub', 'Sidi Ali Benyoub', 22),
(823, '0823', 'سيدي علي بوسيدي', 'Sidi Ali Boussidi', 'Sidi Ali Boussidi', 22),
(824, '0824', 'سيدي بلعباس', 'Sidi Bel-Abbes', 'Sidi Bel-Abbes', 22),
(825, '0825', 'سيدي ابراهيم', 'Sidi Brahim', 'Sidi Brahim', 22),
(826, '0826', 'سيدي شعيب', 'Sidi Chaib', 'Sidi Chaib', 22),
(827, '0827', 'سيدي دحو الزاير', 'Sidi Dahou Zairs', 'Sidi Dahou Zairs', 22),
(828, '0828', 'سيدي حمادوش', 'Sidi Hamadouche', 'Sidi Hamadouche', 22),
(829, '0829', 'سيدي خالد', 'Sidi Khaled', 'Sidi Khaled', 22),
(830, '0830', 'سيدي لحسن', 'Sidi Lahcene', 'Sidi Lahcene', 22),
(831, '0831', 'سيدي يعقوب', 'Sidi Yacoub', 'Sidi Yacoub', 22),
(832, '0832', 'طابية', 'Tabia', 'Tabia', 22),
(833, '0833', 'تاودموت', 'Taoudmout', 'Taoudmout', 22),
(834, '0834', 'تفسور', 'Tefessour', 'Tefessour', 22),
(835, '0835', 'تغاليمت', 'Teghalimet', 'Teghalimet', 22),
(836, '0836', 'تلاغ', 'Telagh', 'Telagh', 22),
(837, '0837', 'تنيرة', 'Tenira', 'Tenira', 22),
(838, '0838', 'تسالة', 'Tessala', 'Tessala', 22),
(839, '0839', 'تلموني', 'Tilmouni', 'Tilmouni', 22),
(840, '0840', 'زروالة', 'Zerouala', 'Zerouala', 22),
(841, '0841', 'عين الباردة', 'Ain El Berda', 'Ain El Berda', 23),
(842, '0842', 'عنابة', 'Annaba', 'Annaba', 23),
(843, '0843', 'برحال', 'Berrahal', 'Berrahal', 23),
(844, '0844', 'شطايبي', 'Chetaibi', 'Chetaibi', 23),
(845, '0845', 'الشرفة', 'Cheurfa', 'Cheurfa', 23),
(846, '0846', 'البوني', 'El Bouni', 'El Bouni', 23),
(847, '0847', 'العلمة', 'El Eulma', 'El Eulma', 23),
(848, '0848', 'الحجار', 'El Hadjar', 'El Hadjar', 23),
(849, '0849', 'واد العنب', 'Oued El Aneb', 'Oued El Aneb', 23),
(850, '0850', 'سرايدي', 'Seraidi', 'Seraidi', 23),
(851, '0851', 'سيدي عمار', 'Sidi Amar', 'Sidi Amar', 23),
(852, '0852', 'التريعات', 'Treat', 'Treat', 23),
(853, '0853', 'عين بن بيضاء', 'Ain Ben Beida', 'Ain Ben Beida', 24),
(854, '0854', 'عين العربي', 'Ain Larbi', 'Ain Larbi', 24),
(855, '0855', 'عين مخلوف', 'Ain Makhlouf', 'Ain Makhlouf', 24),
(856, '0856', 'عين رقادة', 'Ain Regada', 'Ain Regada', 24),
(857, '0857', 'عين صندل', 'Ain Sandel', 'Ain Sandel', 24),
(858, '0858', 'بلخير', 'Belkheir', 'Belkheir', 24),
(859, '0859', 'بن جراح', 'Bendjarah', 'Bendjarah', 24),
(860, '0860', 'بني مزلين', 'Beni Mezline', 'Beni Mezline', 24),
(861, '0861', 'برج صباط', 'Bordj Sabath', 'Bordj Sabath', 24),
(862, '0862', 'بوحشانة', 'Bou Hachana', 'Bou Hachana', 24),
(863, '0863', 'بوحمدان', 'Bou Hamdane', 'Bou Hamdane', 24),
(864, '0864', 'بوعاتي محمود', 'Bouati Mahmoud', 'Bouati Mahmoud', 24),
(865, '0865', 'بوشقوف', 'Bouchegouf', 'Bouchegouf', 24),
(866, '0866', 'بومهرة أحمد', 'Boumahra Ahmed', 'Boumahra Ahmed', 24),
(867, '0867', 'الدهوارة', 'Dahouara', 'Dahouara', 24),
(868, '0868', 'جبالة الخميسي', 'Djeballah Khemissi', 'Djeballah Khemissi', 24),
(869, '0869', 'الفجوج', 'El Fedjoudj', 'El Fedjoudj', 24),
(870, '0870', 'قلعة بوصبع', 'Guelaat Bou Sbaa', 'Guelaat Bou Sbaa', 24),
(871, '0871', 'قالمة', 'Guelma', 'Guelma', 24),
(872, '0872', 'حمام دباغ', 'Hammam Debagh', 'Hammam Debagh', 24),
(873, '0873', 'حمام النبايل', 'Hammam N\'bail', 'Hammam N\'bail', 24),
(874, '0874', 'هيليوبوليس', 'Heliopolis', 'Heliopolis', 24),
(875, '0875', 'نشماية', 'Nechmaya', 'Nechmaya', 24),
(876, '0876', 'لخزارة', 'Khezaras', 'Khezaras', 24),
(877, '0877', 'مجاز عمار', 'Medjez Amar', 'Medjez Amar', 24),
(878, '0878', 'مجاز الصفاء', 'Medjez Sfa', 'Medjez Sfa', 24),
(879, '0879', 'هواري بومدين', 'Houari Boumedienne', 'Houari Boumedienne', 24),
(880, '0880', 'وادي الشحم', 'Oued Cheham', 'Oued Cheham', 24),
(881, '0881', 'وادي فراغة', 'Oued Ferragha', 'Oued Ferragha', 24),
(882, '0882', 'وادي الزناتي', 'Oued Zenati', 'Oued Zenati', 24),
(883, '0883', 'رأس العقبة', 'Ras El Agba', 'Ras El Agba', 24),
(884, '0884', 'الركنية', 'Roknia', 'Roknia', 24),
(885, '0885', 'سلاوة عنونة', 'Sellaoua Announa', 'Sellaoua Announa', 24),
(886, '0886', 'تاملوكة', 'Tamlouka', 'Tamlouka', 24),
(887, '0887', 'عين عبيد', 'Ain Abid', 'Ain Abid', 25),
(888, '0888', 'عين السمارة', 'Ain Smara', 'Ain Smara', 25),
(889, '0889', 'أبن باديس الهرية', 'Ben Badis', 'Ben Badis', 25),
(890, '0890', 'بني حميدان', 'Beni Hamidane', 'Beni Hamidane', 25),
(891, '0891', 'قسنطينة', 'Constantine', 'Constantine', 25),
(892, '0892', 'ديدوش مراد', 'Didouche Mourad', 'Didouche Mourad', 25),
(893, '0893', 'الخروب', 'El Khroub', 'El Khroub', 25),
(894, '0894', 'حامة بوزيان', 'Hamma Bouziane', 'Hamma Bouziane', 25),
(895, '0895', 'ابن زياد', 'Ibn Ziad', 'Ibn Ziad', 25);
INSERT INTO `t_01_04_03` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`, `F_05`) VALUES
(896, '0896', 'بوجريو مسعود', 'Messaoud Boudjeriou', 'Messaoud Boudjeriou', 25),
(897, '0897', 'أولاد رحمون', 'Ouled Rahmoun', 'Ouled Rahmoun', 25),
(898, '0898', 'زيغود يوسف', 'Zighoud Youcef', 'Zighoud Youcef', 25),
(899, '0899', 'عين بوسيف', 'Ain Boucif', 'Ain Boucif', 26),
(900, '0900', 'عين اقصير', 'Ain Ouksir', 'Ain Ouksir', 26),
(901, '0901', 'العيساوية', 'Aissaouia', 'Aissaouia', 26),
(902, '0902', 'عزيز', 'Aziz', 'Aziz', 26),
(903, '0903', 'بعطة', 'Baata', 'Baata', 26),
(904, '0904', 'بن شكاو', 'Ben Chicao', 'Ben Chicao', 26),
(905, '0905', 'بني سليمان', 'Beni Slimane', 'Beni Slimane', 26),
(906, '0906', 'البرواقية', 'Berrouaghia', 'Berrouaghia', 26),
(907, '0907', 'بئر بن عابد', 'Bir Ben Laabed', 'Bir Ben Laabed', 26),
(908, '0908', 'بوغار', 'Boghar', 'Boghar', 26),
(909, '0909', 'بوعيش', 'Bouaiche', 'Bouaiche', 26),
(910, '0910', 'بوعيشون', 'Bouaichoune', 'Bouaichoune', 26),
(911, '0911', 'بوشراحيل', 'Bouchrahil', 'Bouchrahil', 26),
(912, '0912', 'بوغزول', 'Boughzoul', 'Boughzoul', 26),
(913, '0913', 'بوسكن', 'Bouskene', 'Bouskene', 26),
(914, '0914', 'الشهبونية', 'Chabounia', 'Chabounia', 26),
(915, '0915', 'شلالة العذاورة', 'Chelalet El Adhaoura', 'Chelalet El Adhaoura', 26),
(916, '0916', 'شنيقل', 'Cheniguel', 'Cheniguel', 26),
(917, '0917', 'دراق', 'Derrag', 'Derrag', 26),
(918, '0918', 'جواب', 'Djouab', 'Djouab', 26),
(919, '0919', 'ذراع السمار', 'Draa Esmar', 'Draa Esmar', 26),
(920, '0920', 'العزيزية', 'El Azizia', 'El Azizia', 26),
(921, '0921', 'القلب الكبير', 'El Guelbelkebir', 'El Guelbelkebir', 26),
(922, '0922', 'الحمدانية', 'El Hamdania', 'El Hamdania', 26),
(923, '0923', 'الحوضان', 'El Haoudane', 'El Haoudane', 26),
(924, '0924', 'العمارية', 'El Omaria', 'El Omaria', 26),
(925, '0925', 'العوينات', 'El Ouinet', 'El Ouinet', 26),
(926, '0926', 'حناشة', 'Hannacha', 'Hannacha', 26),
(927, '0927', 'الكاف الاخضر', 'Kef Lakhdar', 'Kef Lakhdar', 26),
(928, '0928', 'خمس جوامع', 'Khams Djouamaa', 'Khams Djouamaa', 26),
(929, '0929', 'قصر البخاري', 'Ksar El Boukhari', 'Ksar El Boukhari', 26),
(930, '0930', 'مغراوة', 'Maghraoua', 'Maghraoua', 26),
(931, '0931', 'المدية', 'Medea', 'Medea', 26),
(932, '0932', 'مجبر', 'Medjebar', 'Medjebar', 26),
(933, '0933', 'مزغنة', 'Mezerana', 'Mezerana', 26),
(934, '0934', 'مفاتحة', 'M\'fatha', 'M\'fatha', 26),
(935, '0935', 'ميهوب', 'Mihoub', 'Mihoub', 26),
(936, '0936', 'عوامري', 'Ouamri', 'Ouamri', 26),
(937, '0937', 'وادي حربيل', 'Oued Harbil', 'Oued Harbil', 26),
(938, '0938', 'أولاد عنتر', 'Ouled Antar', 'Ouled Antar', 26),
(939, '0939', 'أولاد بوعشرة', 'Ouled Bouachra', 'Ouled Bouachra', 26),
(940, '0940', 'أولاد إبراهيم', 'Ouled Brahim', 'Ouled Brahim', 26),
(941, '0941', 'أولاد دايد', 'Ouled Deid', 'Ouled Deid', 26),
(942, '0942', 'أولاد امعرف', 'Ouled Emaaraf', 'Ouled Emaaraf', 26),
(943, '0943', 'أولاد هلال', 'Ouled Hellal', 'Ouled Hellal', 26),
(944, '0944', 'أم الجليل', 'Oum El Djellil', 'Oum El Djellil', 26),
(945, '0945', 'وزرة', 'Ouzera', 'Ouzera', 26),
(946, '0946', 'الربعية', 'Rebaia', 'Rebaia', 26),
(947, '0947', 'السانق', 'Saneg', 'Saneg', 26),
(948, '0948', 'سدراية', 'Sedraya', 'Sedraya', 26),
(949, '0949', 'سغوان', 'Seghouane', 'Seghouane', 26),
(950, '0950', 'سي المحجوب', 'Si Mahdjoub', 'Si Mahdjoub', 26),
(951, '0951', 'سيدي دامد', 'Sidi Demed', 'Sidi Demed', 26),
(952, '0952', 'سيدي نعمان', 'Sidi Naamane', 'Sidi Naamane', 26),
(953, '0953', 'سيدي الربيع', 'Sidi Rabie', 'Sidi Rabie', 26),
(954, '0954', 'سيدي زهار', 'Sidi Zahar', 'Sidi Zahar', 26),
(955, '0955', 'سيدي زيان', 'Sidi Ziane', 'Sidi Ziane', 26),
(956, '0956', 'السواقي', 'Souagui', 'Souagui', 26),
(957, '0957', 'تابلاط', 'Tablat', 'Tablat', 26),
(958, '0958', 'تفراوت', 'Tafraout', 'Tafraout', 26),
(959, '0959', 'تمسقيدة', 'Tamesguida', 'Tamesguida', 26),
(960, '0960', 'تيزي مهدي', 'Tizi Mahdi', 'Tizi Mahdi', 26),
(961, '0961', 'ثلاث دوائر', 'Tletat Ed Douair', 'Tletat Ed Douair', 26),
(962, '0962', 'الزبيرية', 'Zoubiria', 'Zoubiria', 26),
(963, '0963', 'عشعاشة', 'Achaacha', 'Achaacha', 27),
(964, '0964', 'عين بودينار', 'Ain-Boudinar', 'Ain-Boudinar', 27),
(965, '0965', 'عين نويسي', 'Ain-Nouissy', 'Ain-Nouissy', 27),
(966, '0966', 'عين سيدي الشريف', 'Ain-Sidi Cherif', 'Ain-Sidi Cherif', 27),
(967, '0967', 'عين تادلس', 'Ain-Tedles', 'Ain-Tedles', 27),
(968, '0968', 'بن عبد المالك رمضان', 'Benabdelmalek Ramdane', 'Benabdelmalek Ramdane', 27),
(969, '0969', 'بوقيراط', 'Bouguirat', 'Bouguirat', 27),
(970, '0970', 'فرناقة', 'Fornaka', 'Fornaka', 27),
(971, '0971', 'حجاج', 'Hadjadj', 'Hadjadj', 27),
(972, '0972', 'حاسي ماماش', 'Hassi Mameche', 'Hassi Mameche', 27),
(973, '0973', 'الحسيان (بني ياحي', 'Hassiane', 'Hassiane', 27),
(974, '0974', 'خضرة', 'Khadra', 'Khadra', 27),
(975, '0975', 'خير الدين', 'Kheir-Eddine', 'Kheir-Eddine', 27),
(976, '0976', 'منصورة', 'Mansourah', 'Mansourah', 27),
(977, '0977', 'مزغران', 'Mazagran', 'Mazagran', 27),
(978, '0978', 'ماسرة', 'Mesra', 'Mesra', 27),
(979, '0979', 'مستغانم', 'Mostaganem', 'Mostaganem', 27),
(980, '0980', 'نكمارية', 'Nekmaria', 'Nekmaria', 27),
(981, '0981', 'وادي الخير', 'Oued El Kheir', 'Oued El Kheir', 27),
(982, '0982', 'أولاد بوغالم', 'Ouled Boughalem', 'Ouled Boughalem', 27),
(983, '0983', 'أولاد مع الله', 'Ouled-Maalah', 'Ouled-Maalah', 27),
(984, '0984', 'صفصاف', 'Safsaf', 'Safsaf', 27),
(985, '0985', 'صيادة', 'Sayada', 'Sayada', 27),
(986, '0986', 'سيدي علي', 'Sidi Ali', 'Sidi Ali', 27),
(987, '0987', 'سيدي بلعطار', 'Sidi Belaattar', 'Sidi Belaattar', 27),
(988, '0988', 'سيدي لخضر', 'Sidi-Lakhdar', 'Sidi-Lakhdar', 27),
(989, '0989', 'سيرات', 'Sirat', 'Sirat', 27),
(990, '0990', 'السوافلية', 'Souaflia', 'Souaflia', 27),
(991, '0991', 'سور', 'Sour', 'Sour', 27),
(992, '0992', 'ستيدية', 'Stidia', 'Stidia', 27),
(993, '0993', 'تزقايت', 'Tazgait', 'Tazgait', 27),
(994, '0994', 'الطواهرية', 'Touahria', 'Touahria', 27),
(995, '0995', 'عين الحجل', 'Ain El Hadjel', 'Ain El Hadjel', 28),
(996, '0996', 'عين الملح', 'Ain El Melh', 'Ain El Melh', 28),
(997, '0997', 'عين فارس', 'Ain Fares', 'Ain Fares', 28),
(998, '0998', 'عين الخضراء', 'Ain Khadra', 'Ain Khadra', 28),
(999, '0999', 'عين الريش', 'Ain Rich', 'Ain Rich', 28),
(1000, '1000', 'بلعايبة', 'Belaiba', 'Belaiba', 28),
(1001, '1001', 'بن سرور', 'Ben Srour', 'Ben Srour', 28),
(1002, '1002', 'بني يلمان', 'Beni Ilmane', 'Beni Ilmane', 28),
(1003, '1003', 'بن زوه', 'Benzouh', 'Benzouh', 28),
(1004, '1004', 'برهوم', 'Berhoum', 'Berhoum', 28),
(1005, '1005', 'بئر فضة', 'Bir Foda', 'Bir Foda', 28),
(1006, '1006', 'بوسعادة', 'Bou Saada', 'Bou Saada', 28),
(1007, '1007', 'بوطي السايح', 'Bouti Sayeh', 'Bouti Sayeh', 28),
(1008, '1008', 'شلال', 'Chellal', 'Chellal', 28),
(1009, '1009', 'دهاهنة', 'Dehahna', 'Dehahna', 28),
(1010, '1010', 'جبل مساعد', 'Djebel Messaad', 'Djebel Messaad', 28),
(1011, '1011', 'الهامل', 'El Hamel', 'El Hamel', 28),
(1012, '1012', 'الحوامد', 'El Houamed', 'El Houamed', 28),
(1013, '1013', 'حمام الضلعة', 'Hammam Dalaa', 'Hammam Dalaa', 28),
(1014, '1014', 'خطوطي سد الجير', 'Khettouti Sed-El-Jir', 'Khettouti Sed-El-Jir', 28),
(1015, '1015', 'خبانة', 'Khoubana', 'Khoubana', 28),
(1016, '1016', 'المعاضيد', 'Maadid', 'Maadid', 28),
(1017, '1017', 'معاريف', 'Maarif', 'Maarif', 28),
(1018, '1018', 'مقرة', 'Magra', 'Magra', 28),
(1019, '1019', 'مسيف', 'M\'cif', 'M\'cif', 28),
(1020, '1020', 'امجدل', 'Medjedel', 'Medjedel', 28),
(1021, '1021', 'مناعة', 'Menaa', 'Menaa', 28),
(1022, '1022', 'محمد بوضياف', 'Mohamed Boudiaf', 'Mohamed Boudiaf', 28),
(1023, '1023', 'المسيلة', 'M\'sila', 'M\'sila', 28),
(1024, '1024', 'المطارفة', 'M\'tarfa', 'M\'tarfa', 28),
(1025, '1025', 'ونوغة', 'Ouanougha', 'Ouanougha', 28),
(1026, '1026', 'أولاد عدي لقبالة', 'Ouled Addi Guebala', 'Ouled Addi Guebala', 28),
(1027, '1027', 'أولاد دراج', 'Ouled Derradj', 'Ouled Derradj', 28),
(1028, '1028', 'أولاد ماضي', 'Ouled Madhi', 'Ouled Madhi', 28),
(1029, '1029', 'أولاد منصور', 'Ouled Mansour', 'Ouled Mansour', 28),
(1030, '1030', 'أولاد سيدي ابراهيم', 'Ouled Sidi Brahim', 'Ouled Sidi Brahim', 28),
(1031, '1031', 'أولاد سليمان', 'Ouled Slimane', 'Ouled Slimane', 28),
(1032, '1032', 'ولتام', 'Oulteme', 'Oulteme', 28),
(1033, '1033', 'سيدي عيسى', 'Sidi Aissa', 'Sidi Aissa', 28),
(1034, '1034', 'سيدي عامر', 'Sidi Ameur', 'Sidi Ameur', 28),
(1035, '1035', 'سيدي هجرس', 'Sidi Hadjeres', 'Sidi Hadjeres', 28),
(1036, '1036', 'سيدي امحمد', 'Sidi M\'hamed', 'Sidi M\'hamed', 28),
(1037, '1037', 'سليم', 'Slim', 'Slim', 28),
(1038, '1038', 'السوامع', 'Souamaa', 'Souamaa', 28),
(1039, '1039', 'تامسة', 'Tamsa', 'Tamsa', 28),
(1040, '1040', 'تارمونت', 'Tarmount', 'Tarmount', 28),
(1041, '1041', 'زرزور', 'Zarzour', 'Zarzour', 28),
(1042, '1042', 'عين فارس', 'Ain Fares', 'Ain Fares', 29),
(1043, '1043', 'عين فكان', 'Ain Fekan', 'Ain Fekan', 29),
(1044, '1044', 'عين فراح', 'Ain Ferah', 'Ain Ferah', 29),
(1045, '1045', 'عين أفرص', 'Ain Frass', 'Ain Frass', 29),
(1046, '1046', 'العلايمية', 'Alaimia', 'Alaimia', 29),
(1047, '1047', 'عوف', 'Aouf', 'Aouf', 29),
(1048, '1048', 'بنيان', 'Benian', 'Benian', 29),
(1049, '1049', 'بوهني', 'Bou Henni', 'Bou Henni', 29),
(1050, '1050', 'بوحنيفية', 'Bouhanifia', 'Bouhanifia', 29),
(1051, '1051', 'الشرفاء', 'Chorfa', 'Chorfa', 29),
(1052, '1052', 'البرج', 'El Bordj', 'El Bordj', 29),
(1053, '1053', 'القعدة', 'El Gaada', 'El Gaada', 29),
(1054, '1054', 'الغمري', 'El Ghomri', 'El Ghomri', 29),
(1055, '1055', 'القطنة', 'El Gueitena', 'El Gueitena', 29),
(1056, '1056', 'الحشم', 'El Hachem', 'El Hachem', 29),
(1057, '1057', 'القرط', 'El Keurt', 'El Keurt', 29),
(1058, '1058', 'المأمونية', 'El Mamounia', 'El Mamounia', 29),
(1059, '1059', 'المنور', 'El Menaouer', 'El Menaouer', 29),
(1060, '1060', 'فراقيق', 'Ferraguig', 'Ferraguig', 29),
(1061, '1061', 'فروحة', 'Froha', 'Froha', 29),
(1062, '1062', 'غروس', 'Gharrous', 'Gharrous', 29),
(1063, '1063', 'غريس', 'Ghriss', 'Ghriss', 29),
(1064, '1064', 'قرجوم', 'Guerdjoum', 'Guerdjoum', 29),
(1065, '1065', 'حسين', 'Hacine', 'Hacine', 29),
(1066, '1066', 'خلوية', 'Khalouia', 'Khalouia', 29),
(1067, '1067', 'ماقضة', 'Makhda', 'Makhda', 29),
(1068, '1068', 'ماوسة', 'Maoussa', 'Maoussa', 29),
(1069, '1069', 'معسكر', 'Mascara', 'Mascara', 29),
(1070, '1070', 'المطمور', 'Matemore', 'Matemore', 29),
(1071, '1071', 'مقطع الدوز', 'Mocta-Douz', 'Mocta-Douz', 29),
(1072, '1072', 'المحمدية', 'Mohammadia', 'Mohammadia', 29),
(1073, '1073', 'نسمط', 'Nesmot', 'Nesmot', 29),
(1074, '1074', 'عقاز', 'Oggaz', 'Oggaz', 29),
(1075, '1075', 'وادي الأبطال', 'Oued El Abtal', 'Oued El Abtal', 29),
(1076, '1076', 'وادي التاغية', 'Oued Taria', 'Oued Taria', 29),
(1077, '1077', 'رأس عين عميروش', 'Ras El Ain Amirouche', 'Ras El Ain Amirouche', 29),
(1078, '1078', 'سجرارة', 'Sedjerara', 'Sedjerara', 29),
(1079, '1079', 'السهايلية', 'Sehailia', 'Sehailia', 29),
(1080, '1080', 'سيدي عبد الجبار', 'Sidi Abdeldjebar', 'Sidi Abdeldjebar', 29),
(1081, '1081', 'سيدي عبد المومن', 'Sidi Abdelmoumene', 'Sidi Abdelmoumene', 29),
(1082, '1082', 'سيدي بوسعيد', 'Sidi Boussaid', 'Sidi Boussaid', 29),
(1083, '1083', 'سيدي قادة', 'Sidi Kada', 'Sidi Kada', 29),
(1084, '1084', 'سيق', 'Sig', 'Sig', 29),
(1085, '1085', 'تيغنيف', 'Tighennif', 'Tighennif', 29),
(1086, '1086', 'تيزي', 'Tizi', 'Tizi', 29),
(1087, '1087', 'زهانة', 'Zahana', 'Zahana', 29),
(1088, '1088', 'زلامطة', 'Zelamta', 'Zelamta', 29),
(1089, '1089', 'عين البيضاء', 'Ain Beida', 'Ain Beida', 30),
(1090, '1090', 'بن ناصر', 'Benaceur', 'Benaceur', 55),
(1091, '1091', 'بلدة اعمر', 'Blidet Amor', 'Blidet Amor', 55),
(1092, '1092', 'العالية', 'El Alia', 'El Alia', 55),
(1093, '1093', 'البرمة', 'El Borma', 'El Borma', 30),
(1094, '1094', 'الحجيرة', 'El-Hadjira', 'El-Hadjira', 55),
(1095, '1095', 'حاسي بن عبد الله', 'Hassi Ben Abdellah', 'Hassi Ben Abdellah', 30),
(1096, '1096', 'حاسي مسعود', 'Hassi Messaoud', 'Hassi Messaoud', 30),
(1097, '1097', 'المقارين', 'Megarine', 'Megarine', 55),
(1098, '1098', 'المنقر', 'M\'naguer', 'M\'naguer', 55),
(1099, '1099', 'النزلة', 'Nezla', 'Nezla', 55),
(1100, '1100', 'انقوسة', 'N\'goussa', 'N\'goussa', 30),
(1101, '1101', 'ورقلة', 'Ouargla', 'Ouargla', 30),
(1102, '1102', 'الرويسات', 'Rouissat', 'Rouissat', 30),
(1103, '1103', 'سيدي خويلد', 'Sidi Khouiled', 'Sidi Khouiled', 30),
(1104, '1104', 'سيدي سليمان', 'Sidi Slimane', 'Sidi Slimane', 55),
(1105, '1105', 'الطيبات', 'Taibet', 'Taibet', 55),
(1106, '1106', 'تبسبست', 'Tebesbest', 'Tebesbest', 55),
(1107, '1107', 'تماسين', 'Temacine', 'Temacine', 55),
(1108, '1108', 'تقرت', 'Touggourt', 'Touggourt', 55),
(1109, '1109', 'الزاوية العابدية', 'Zaouia El Abidia', 'Zaouia El Abidia', 55),
(1110, '1110', 'عين البية', 'Ain Biya', 'Ain Biya', 31),
(1111, '1111', 'عين الكرمة', 'Ain Kerma', 'Ain Kerma', 31),
(1112, '1112', 'عين الترك', 'Ain Turk', 'Ain Turk', 31),
(1113, '1113', 'أرزيو', 'Arzew', 'Arzew', 31),
(1114, '1114', 'بن فريحة', 'Ben Freha', 'Ben Freha', 31),
(1115, '1115', 'بطيوة', 'Bethioua', 'Bethioua', 31),
(1116, '1116', 'بئر الجير', 'Bir El Djir', 'Bir El Djir', 31),
(1117, '1117', 'بوفاتيس', 'Boufatis', 'Boufatis', 31),
(1118, '1118', 'بوسفر', 'Bousfer', 'Bousfer', 31),
(1119, '1119', 'بوتليليس', 'Boutlelis', 'Boutlelis', 31),
(1120, '1120', 'العنصر', 'El Ancor', 'El Ancor', 31),
(1121, '1121', 'البراية', 'El Braya', 'El Braya', 31),
(1122, '1122', 'الكرمة', 'El Kerma', 'El Kerma', 31),
(1123, '1123', 'السانية', 'Es Senia', 'Es Senia', 31),
(1124, '1124', 'قديل', 'Gdyel', 'Gdyel', 31),
(1125, '1125', 'حاسي بن عقبة', 'Hassi Ben Okba', 'Hassi Ben Okba', 31),
(1126, '1126', 'حاسي بونيف', 'Hassi Bounif', 'Hassi Bounif', 31),
(1127, '1127', 'حاسي مفسوخ', 'Hassi Mefsoukh', 'Hassi Mefsoukh', 31),
(1128, '1128', 'مرسى الحجاج', 'Marsat El Hadjadj', 'Marsat El Hadjadj', 31),
(1129, '1129', 'المرسى الكبير', 'Mers El Kebir', 'Mers El Kebir', 31),
(1130, '1130', 'مسرغين', 'Messerghin', 'Messerghin', 31),
(1131, '1131', 'وهران', 'Oran', 'Oran', 31),
(1132, '1132', 'وادي تليلات', 'Oued Tlelat', 'Oued Tlelat', 31),
(1133, '1133', 'سيدي بن يبقى', 'Sidi Ben Yebka', 'Sidi Ben Yebka', 31),
(1134, '1134', 'سيدي الشحمي', 'Sidi Chami', 'Sidi Chami', 31),
(1135, '1135', 'طفراوي', 'Tafraoui', 'Tafraoui', 31),
(1136, '1136', 'عين العراك', 'Ain El Orak', 'Ain El Orak', 32),
(1137, '1137', 'اربوات', 'Arbaouat', 'Arbaouat', 32),
(1138, '1138', 'بوعلام', 'Boualem', 'Boualem', 32),
(1139, '1139', 'بوقطب', 'Bougtoub', 'Bougtoub', 32),
(1140, '1140', 'بوسمغون', 'Boussemghoun', 'Boussemghoun', 32),
(1141, '1141', 'بريزينة', 'Brezina', 'Brezina', 32),
(1142, '1142', 'الشقيق', 'Cheguig', 'Cheguig', 32),
(1143, '1143', 'شلالة', 'Chellala', 'Chellala', 32),
(1144, '1144', 'البيض', 'El Bayadh', 'El Bayadh', 32),
(1145, '1145', 'الأبيض سيدي الشيخ', 'Labiodh Sidi Cheikh', 'Labiodh Sidi Cheikh', 32),
(1146, '1146', 'البنود', 'El Bnoud', 'El Bnoud', 32),
(1147, '1147', 'الخيثر', 'El Kheiter', 'El Kheiter', 32),
(1148, '1148', 'المحرة', 'El Mehara', 'El Mehara', 32),
(1149, '1149', 'الغاسول', 'Ghassoul', 'Ghassoul', 32),
(1150, '1150', 'الكاف الأحمر', 'Kef El Ahmar', 'Kef El Ahmar', 32),
(1151, '1151', 'كراكدة', 'Krakda', 'Krakda', 32),
(1152, '1152', 'رقاصة', 'Rogassa', 'Rogassa', 32),
(1153, '1153', 'سيدي عامر', 'Sidi Ameur', 'Sidi Ameur', 32),
(1154, '1154', 'سيدي سليمان', 'Sidi Slimane', 'Sidi Slimane', 32),
(1155, '1155', 'سيدي طيفور', 'Sidi Tiffour', 'Sidi Tiffour', 32),
(1156, '1156', 'ستيتن', 'Stitten', 'Stitten', 32),
(1157, '1157', 'توسمولين', 'Tousmouline', 'Tousmouline', 32),
(1158, '1158', 'برج الحواس', 'Bordj El Haouass', 'Bordj El Haouass', 56),
(1159, '1159', 'برج عمر إدريس', 'Bordj Omar Driss', 'Bordj Omar Driss', 33),
(1160, '1160', 'دبداب', 'Debdeb', 'Debdeb', 33),
(1161, '1161', 'جانت', 'Djanet', 'Djanet', 56),
(1162, '1162', 'إيليزي', 'Illizi', 'Illizi', 33),
(1163, '1163', 'إن أمناس', 'In Amenas', 'In Amenas', 33),
(1164, '1164', 'عين تاغروت', 'Ain Taghrout', 'Ain Taghrout', 34),
(1165, '1165', 'عين تسرة', 'Ain Tesra', 'Ain Tesra', 34),
(1166, '1166', 'برج بوعريرج', 'B. B. Arreridj', 'B. B. Arreridj', 34),
(1167, '1167', 'بليمور', 'Belimour', 'Belimour', 34),
(1168, '1168', 'بن داود', 'Ben Daoud', 'Ben Daoud', 34),
(1169, '1169', 'بئر قاصد علي', 'Bir Kasdali', 'Bir Kasdali', 34),
(1170, '1170', 'برج الغدير', 'Bordj Ghedir', 'Bordj Ghedir', 34),
(1171, '1171', 'برج زمورة', 'Bordj Zemmoura', 'Bordj Zemmoura', 34),
(1172, '1172', 'القلة', 'Colla', 'Colla', 34),
(1173, '1173', 'جعافرة', 'Djaafra', 'Djaafra', 34),
(1174, '1174', 'العش', 'El Euch', 'El Euch', 34),
(1175, '1175', 'الياشير', 'El Achir', 'El Achir', 34),
(1176, '1176', 'العناصر', 'El Annasseur', 'El Annasseur', 34),
(1177, '1177', 'الحمادية', 'Elhammadia', 'Elhammadia', 34),
(1178, '1178', 'الماين', 'El Main', 'El Main', 34),
(1179, '1179', 'المهير', 'El M\'hir', 'El M\'hir', 34),
(1180, '1180', 'غيلاسة', 'Ghailasa', 'Ghailasa', 34),
(1181, '1181', 'حرازة', 'Haraza', 'Haraza', 34),
(1182, '1182', 'حسناوة', 'Hasnaoua', 'Hasnaoua', 34),
(1183, '1183', 'خليل', 'Khelil', 'Khelil', 34),
(1184, '1184', 'القصور', 'Ksour', 'Ksour', 34),
(1185, '1185', 'المنصورة', 'Mansoura', 'Mansoura', 34),
(1186, '1186', 'مجانة', 'Medjana', 'Medjana', 34),
(1187, '1187', 'أولاد أبراهم', 'Ouled Brahem', 'Ouled Brahem', 34),
(1188, '1188', 'أولاد دحمان', 'Ouled Dahmane', 'Ouled Dahmane', 34),
(1189, '1189', 'أولاد سيدي ابراهيم', 'Ouled Sidi-Brahim', 'Ouled Sidi-Brahim', 34),
(1190, '1190', 'الرابطة', 'Rabta', 'Rabta', 34),
(1191, '1191', 'رأس الوادي', 'Ras El Oued', 'Ras El Oued', 34),
(1192, '1192', 'سيدي أمبارك', 'Sidi-Embarek', 'Sidi-Embarek', 34),
(1193, '1193', 'تفرق', 'Tefreg', 'Tefreg', 34),
(1194, '1194', 'تقلعيت', 'Taglait', 'Taglait', 34),
(1195, '1195', 'تسامرت', 'Tassamert', 'Tassamert', 34),
(1196, '1196', 'ثنية النصر', 'Teniet En Nasr', 'Teniet En Nasr', 34),
(1197, '1197', 'تيكستار', 'Tixter', 'Tixter', 34),
(1198, '1198', 'أعفير', 'Afir', 'Afir', 35),
(1199, '1199', 'عمال', 'Ammal', 'Ammal', 35),
(1200, '1200', 'بغلية', 'Baghlia', 'Baghlia', 35),
(1201, '1201', 'بن شود', 'Ben Choud', 'Ben Choud', 35),
(1202, '1202', 'بني عمران', 'Beni Amrane', 'Beni Amrane', 35),
(1203, '1203', 'برج منايل', 'Bordj Menaiel', 'Bordj Menaiel', 35),
(1204, '1204', 'بودواو', 'Boudouaou', 'Boudouaou', 35),
(1205, '1205', 'بودواو البحري', 'Boudouaou El Bahri', 'Boudouaou El Bahri', 35),
(1206, '1206', 'بومرداس', 'Boumerdes', 'Boumerdes', 35),
(1207, '1207', 'بوزقزة قدارة', 'Bouzegza Keddara', 'Bouzegza Keddara', 35),
(1208, '1208', 'شعبة العامر', 'Chabet El Ameur', 'Chabet El Ameur', 35),
(1209, '1209', 'قورصو', 'Corso', 'Corso', 35),
(1210, '1210', 'دلس', 'Dellys', 'Dellys', 35),
(1211, '1211', 'جنات', 'Djinet', 'Djinet', 35),
(1212, '1212', 'الخروبة', 'El Kharrouba', 'El Kharrouba', 35),
(1213, '1213', 'حمادي', 'Hammedi', 'Hammedi', 35),
(1214, '1214', 'يسر', 'Isser', 'Isser', 35),
(1215, '1215', 'خميس الخشنة', 'Khemis El Khechna', 'Khemis El Khechna', 35),
(1216, '1216', 'الاربعطاش', 'Larbatache', 'Larbatache', 35),
(1217, '1217', 'لقاطة', 'Leghata', 'Leghata', 35),
(1218, '1218', 'الناصرية', 'Naciria', 'Naciria', 35),
(1219, '1219', 'أولاد عيسى', 'Ouled Aissa', 'Ouled Aissa', 35),
(1220, '1220', 'أولاد هداج', 'Ouled Hedadj', 'Ouled Hedadj', 35),
(1221, '1221', 'أولاد موسى', 'Ouled Moussa', 'Ouled Moussa', 35),
(1222, '1222', 'سي مصطفى', 'Si Mustapha', 'Si Mustapha', 35),
(1223, '1223', 'سيدي داود', 'Sidi Daoud', 'Sidi Daoud', 35),
(1224, '1224', 'سوق الحد', 'Souk El Had', 'Souk El Had', 35),
(1225, '1225', 'تاورقة', 'Taourga', 'Taourga', 35),
(1226, '1226', 'الثنية', 'Thenia', 'Thenia', 35),
(1227, '1227', 'تيجلابين', 'Tidjelabine', 'Tidjelabine', 35),
(1228, '1228', 'تيمزريت', 'Timezrit', 'Timezrit', 35),
(1229, '1229', 'زموري', 'Zemmouri', 'Zemmouri', 35),
(1230, '1230', 'عين العسل', 'Ain El Assel', 'Ain El Assel', 36),
(1231, '1231', 'عين الكرمة', 'Ain Kerma', 'Ain Kerma', 36),
(1232, '1232', 'عصفور', 'Asfour', 'Asfour', 36),
(1233, '1233', 'بن مهيدي', 'Ben M Hidi', 'Ben M Hidi', 36),
(1234, '1234', 'بريحان', 'Berrihane', 'Berrihane', 36),
(1235, '1235', 'البسباس', 'Besbes', 'Besbes', 36),
(1236, '1236', 'بوقوس', 'Bougous', 'Bougous', 36),
(1237, '1237', 'بوحجار', 'Bouhadjar', 'Bouhadjar', 36),
(1238, '1238', 'بوثلجة', 'Bouteldja', 'Bouteldja', 36),
(1239, '1239', 'شبيطة مختار', 'Chebaita Mokhtar', 'Chebaita Mokhtar', 36),
(1240, '1240', 'الشافية', 'Chefia', 'Chefia', 36),
(1241, '1241', 'شحاني', 'Chihani', 'Chihani', 36),
(1242, '1242', 'الذرعـان', 'Drean', 'Drean', 36),
(1243, '1243', 'الشط', 'Echatt', 'Echatt', 36),
(1244, '1244', 'العيون', 'El Aioun', 'El Aioun', 36),
(1245, '1245', 'القالة', 'El Kala', 'El Kala', 36),
(1246, '1246', 'الطارف', 'El Tarf', 'El Tarf', 36),
(1247, '1247', 'حمام بني صالح', 'Hammam Beni Salah', 'Hammam Beni Salah', 36),
(1248, '1248', 'بحيرة الطيور', 'Lac Des Oiseaux', 'Lac Des Oiseaux', 36),
(1249, '1249', 'وادي الزيتون', 'Oued Zitoun', 'Oued Zitoun', 36),
(1250, '1250', 'رمل السوق', 'Raml Souk', 'Raml Souk', 36),
(1251, '1251', 'السوارخ', 'Souarekh', 'Souarekh', 36),
(1252, '1252', 'زريزر', 'Zerizer', 'Zerizer', 36),
(1253, '1253', 'الزيتونة', 'Zitouna', 'Zitouna', 36),
(1254, '1254', 'أم العسل', 'Oum El Assel', 'Oum El Assel', 37),
(1255, '1255', 'تندوف', 'Tindouf', 'Tindouf', 37),
(1256, '1256', 'عماري', 'Ammari', 'Ammari', 38),
(1257, '1257', 'بني شعيب', 'Beni Chaib', 'Beni Chaib', 38),
(1258, '1258', 'بني لحسن', 'Beni Lahcene', 'Beni Lahcene', 38),
(1259, '1259', 'برج بونعامة', 'Bordj Bounaama', 'Bordj Bounaama', 38),
(1260, '1260', 'برج الأمير عبد القادر', 'Bordj El Emir Abdelkader', 'Bordj El Emir Abdelkader', 38),
(1261, '1261', 'بوقائد', 'Boucaid', 'Boucaid', 38),
(1262, '1262', 'خميستي', 'Khemisti', 'Khemisti', 38),
(1263, '1263', 'الأربعاء', 'Larbaa', 'Larbaa', 38),
(1264, '1264', 'لرجام', 'Lardjem', 'Lardjem', 38),
(1265, '1265', 'العيون', 'Layoune', 'Layoune', 38),
(1266, '1266', 'الأزهرية', 'Lazharia', 'Lazharia', 38),
(1267, '1267', 'المعاصم', 'Maacem', 'Maacem', 38),
(1268, '1268', 'الملعب', 'Melaab', 'Melaab', 38),
(1269, '1269', 'أولاد بسام', 'Ouled Bessam', 'Ouled Bessam', 38),
(1270, '1270', 'سيدي عابد', 'Sidi Abed', 'Sidi Abed', 38),
(1271, '1271', 'سيدي بوتوشنت', 'Sidi Boutouchent', 'Sidi Boutouchent', 38),
(1272, '1272', 'سيدي العنتري', 'Sidi Lantri', 'Sidi Lantri', 38),
(1273, '1273', 'سيدي سليمان', 'Sidi Slimane', 'Sidi Slimane', 38),
(1274, '1274', 'تملاحت', 'Tamellahet', 'Tamellahet', 38),
(1275, '1275', 'ثنية الاحد', 'Theniet El Had', 'Theniet El Had', 38),
(1276, '1276', 'تيسمسيلت', 'Tissemsilt', 'Tissemsilt', 38),
(1277, '1277', 'اليوسفية', 'Youssoufia', 'Youssoufia', 38),
(1278, '1278', 'البياضة', 'Bayadha', 'Bayadha', 39),
(1279, '1279', 'بن  قشة', 'Ben Guecha', 'Ben Guecha', 39),
(1280, '1280', 'الدبيلة', 'Debila', 'Debila', 39),
(1281, '1281', 'جامعة', 'Djamaa', 'Djamaa', 57),
(1282, '1282', 'دوار الماء', 'Douar El Maa', 'Douar El Maa', 39),
(1283, '1283', 'العقلة', 'El Ogla', 'El Ogla', 39),
(1284, '1284', 'المغير', 'El-M\'ghaier', 'El-M\'ghaier', 57),
(1285, '1285', 'الوادي', 'El-Oued', 'El-Oued', 39),
(1286, '1286', 'قمار', 'Guemar', 'Guemar', 39),
(1287, '1287', 'الحمراية', 'Hamraia', 'Hamraia', 39),
(1288, '1288', 'حساني عبد الكريم', 'Hassani Abdelkrim', 'Hassani Abdelkrim', 39),
(1289, '1289', 'حاسي خليفة', 'Hassi Khalifa', 'Hassi Khalifa', 39),
(1290, '1290', 'كوينين', 'Kouinine', 'Kouinine', 39),
(1291, '1291', 'المقرن', 'Magrane', 'Magrane', 39),
(1292, '1292', 'اميه وانسة', 'Mih Ouansa', 'Mih Ouansa', 39),
(1293, '1293', 'المرارة', 'M\'rara', 'M\'rara', 57),
(1294, '1294', 'النخلة', 'Nakhla', 'Nakhla', 39),
(1295, '1295', 'وادي العلندة', 'Oued El Alenda', 'Oued El Alenda', 39),
(1296, '1296', 'أم الطيور', 'Oum Touyour', 'Oum Touyour', 57),
(1297, '1297', 'ورماس', 'Ourmes', 'Ourmes', 39),
(1298, '1298', 'الرقيبة', 'Reguiba', 'Reguiba', 39),
(1299, '1299', 'الرباح', 'Robbah', 'Robbah', 39),
(1300, '1300', 'سيدي عمران', 'Sidi Amrane', 'Sidi Amrane', 57),
(1301, '1301', 'سيدي عون', 'Sidi Aoun', 'Sidi Aoun', 39),
(1302, '1302', 'سيدي خليل', 'Sidi Khelil', 'Sidi Khelil', 57),
(1303, '1303', 'سطيل', 'Still', 'Still', 57),
(1304, '1304', 'تغزوت', 'Taghzout', 'Taghzout', 39),
(1305, '1305', 'الطالب العربي', 'Taleb Larbi', 'Taleb Larbi', 39),
(1306, '1306', 'تندلة', 'Tenedla', 'Tenedla', 57),
(1307, '1307', 'الطريفاوي', 'Trifaoui', 'Trifaoui', 39),
(1308, '1308', 'عين الطويلة', 'Ain Touila', 'Ain Touila', 40),
(1309, '1309', 'بابار', 'Babar', 'Babar', 40),
(1310, '1310', 'بغاي', 'Baghai', 'Baghai', 40),
(1311, '1311', 'بوحمامة', 'Bouhmama', 'Bouhmama', 40),
(1312, '1312', 'ششار', 'Chechar', 'Chechar', 40),
(1313, '1313', 'شلية', 'Chelia', 'Chelia', 40),
(1314, '1314', 'جلال', 'Djellal', 'Djellal', 40),
(1315, '1315', 'الحامة', 'El Hamma', 'El Hamma', 40),
(1316, '1316', 'المحمل', 'El Mahmal', 'El Mahmal', 40),
(1317, '1317', 'الولجة', 'El Oueldja', 'El Oueldja', 40),
(1318, '1318', 'انسيغة', 'Ensigha', 'Ensigha', 40),
(1319, '1319', 'قايس', 'Kais', 'Kais', 40),
(1320, '1320', 'خنشلة', 'Khenchela', 'Khenchela', 40),
(1321, '1321', 'خيران', 'Khirane', 'Khirane', 40),
(1322, '1322', 'مصارة', 'M\'sara', 'M\'sara', 40),
(1323, '1323', 'متوسة', 'M\'toussa', 'M\'toussa', 40),
(1324, '1324', 'أولاد رشاش', 'Ouled Rechache', 'Ouled Rechache', 40),
(1325, '1325', 'الرميلة', 'Remila', 'Remila', 40),
(1326, '1326', 'طامزة', 'Tamza', 'Tamza', 40),
(1327, '1327', 'تاوزيانت', 'Taouzianat', 'Taouzianat', 40),
(1328, '1328', 'يابوس', 'Yabous', 'Yabous', 40),
(1329, '1329', 'عين سلطان', 'Ain Soltane', 'Ain Soltane', 41),
(1330, '1330', 'عين الزانة', 'Ain Zana', 'Ain Zana', 41),
(1331, '1331', 'بئر بوحوش', 'Bir Bouhouche', 'Bir Bouhouche', 41),
(1332, '1332', 'الدريعة', 'Drea', 'Drea', 41),
(1333, '1333', 'الحدادة', 'Haddada', 'Haddada', 41),
(1334, '1334', 'الحنانشة', 'Hanencha', 'Hanencha', 41),
(1335, '1335', 'الخضارة', 'Khedara', 'Khedara', 41),
(1336, '1336', 'خميسة', 'Khemissa', 'Khemissa', 41),
(1337, '1337', 'المشروحة', 'Machroha', 'Machroha', 41),
(1338, '1338', 'مداوروش', 'M\'daourouche', 'M\'daourouche', 41),
(1339, '1339', 'المراهنة', 'Merahna', 'Merahna', 41),
(1340, '1340', 'وادي الكبريت', 'Oued Kebrit', 'Oued Kebrit', 41),
(1341, '1341', 'أولاد إدريس', 'Ouled Driss', 'Ouled Driss', 41),
(1342, '1342', 'أولاد مومن', 'Ouled Moumen', 'Ouled Moumen', 41),
(1343, '1343', 'أم العظايم', 'Oum El Adhaim', 'Oum El Adhaim', 41),
(1344, '1344', 'ويلان', 'Ouillen', 'Ouillen', 41),
(1345, '1345', 'الراقوبة', 'Ragouba', 'Ragouba', 41),
(1346, '1346', 'سافل الويدان', 'Safel El Ouiden', 'Safel El Ouiden', 41),
(1347, '1347', 'سدراتة', 'Sedrata', 'Sedrata', 41),
(1348, '1348', 'سيدي فرج', 'Sidi Fredj', 'Sidi Fredj', 41),
(1349, '1349', 'سوق أهراس', 'Souk Ahras', 'Souk Ahras', 41),
(1350, '1350', 'تاورة', 'Taoura', 'Taoura', 41),
(1351, '1351', 'ترقالت', 'Terraguelt', 'Terraguelt', 41),
(1352, '1352', 'تيفاش', 'Tiffech', 'Tiffech', 41),
(1353, '1353', 'الزعرورية', 'Zaarouria', 'Zaarouria', 41),
(1354, '1354', 'الزوابي', 'Zouabi', 'Zouabi', 41),
(1355, '1355', 'أغبال', 'Aghbal', 'Aghbal', 42),
(1356, '1356', 'أحمر العين', 'Ahmer El Ain', 'Ahmer El Ain', 42),
(1357, '1357', 'عين تاقورايت', 'Ain Tagourait', 'Ain Tagourait', 42),
(1358, '1358', 'الحطاطبة', 'Attatba', 'Attatba', 42),
(1359, '1359', 'بني ميلك', 'Beni Mileuk', 'Beni Mileuk', 42),
(1360, '1360', 'بوهارون', 'Bou Haroun', 'Bou Haroun', 42),
(1361, '1361', 'بواسماعيل', 'Bou Ismail', 'Bou Ismail', 42),
(1362, '1362', 'بورقيقة', 'Bourkika', 'Bourkika', 42),
(1363, '1363', 'الشعيبة', 'Chaiba', 'Chaiba', 42),
(1364, '1364', 'شرشال', 'Cherchell', 'Cherchell', 42),
(1365, '1365', 'الداموس', 'Damous', 'Damous', 42),
(1366, '1366', 'دواودة', 'Douaouda', 'Douaouda', 42),
(1367, '1367', 'فوكة', 'Fouka', 'Fouka', 42),
(1368, '1368', 'قوراية', 'Gouraya', 'Gouraya', 42),
(1369, '1369', 'حجوط', 'Hadjout', 'Hadjout', 42),
(1370, '1370', 'حجرة النص', 'Hadjret Ennous', 'Hadjret Ennous', 42),
(1371, '1371', 'خميستي', 'Khemisti', 'Khemisti', 42),
(1372, '1372', 'القليعة', 'Kolea', 'Kolea', 42),
(1373, '1373', 'الأرهاط', 'Larhat', 'Larhat', 42),
(1374, '1374', 'مناصر', 'Menaceur', 'Menaceur', 42),
(1375, '1375', 'مراد', 'Merad', 'Merad', 42),
(1376, '1376', 'مسلمون', 'Messelmoun', 'Messelmoun', 42),
(1377, '1377', 'الناظور', 'Nador', 'Nador', 42),
(1378, '1378', 'سيدي غيلاس', 'Sidi Ghiles', 'Sidi Ghiles', 42),
(1379, '1379', 'سيدي راشد', 'Sidi Rached', 'Sidi Rached', 42),
(1380, '1380', 'سيدي سميان', 'Sidi Semiane', 'Sidi Semiane', 42),
(1381, '1381', 'سيدي عامر', 'Sidi-Amar', 'Sidi-Amar', 42),
(1382, '1382', 'تيبازة', 'Tipaza', 'Tipaza', 42),
(1383, '1383', 'أحمد راشدي', 'Ahmed Rachedi', 'Ahmed Rachedi', 43),
(1384, '1384', ' عين البيضاء أحريش', 'Ain Beida Harriche', 'Ain Beida Harriche', 43),
(1385, '1385', 'عين الملوك', 'Ain Mellouk', 'Ain Mellouk', 43),
(1386, '1386', 'عين التين', 'Ain Tine', 'Ain Tine', 43),
(1387, '1387', 'اعميرة اراس', 'Amira Arres', 'Amira Arres', 43),
(1388, '1388', 'بن يحي عبد الرحمن', 'Benyahia Abderrahmane', 'Benyahia Abderrahmane', 43),
(1389, '1389', 'بوحاتم', 'Bouhatem', 'Bouhatem', 43),
(1390, '1390', 'شلغوم العيد', 'Chelghoum Laid', 'Chelghoum Laid', 43),
(1391, '1391', 'الشيقارة', 'Chigara', 'Chigara', 43),
(1392, '1392', 'دراحي بوصلاح', 'Derrahi Bousselah', 'Derrahi Bousselah', 43),
(1393, '1393', 'العياضي برباس', 'El Ayadi Barbes', 'El Ayadi Barbes', 43),
(1394, '1394', 'مشيرة', 'El Mechira', 'El Mechira', 43),
(1395, '1395', 'فرجيوة', 'Ferdjioua', 'Ferdjioua', 43),
(1396, '1396', 'القرارم قوقة', 'Grarem Gouga', 'Grarem Gouga', 43),
(1397, '1397', 'حمالة', 'Hamala', 'Hamala', 43),
(1398, '1398', 'ميلة', 'Mila', 'Mila', 43),
(1399, '1399', 'مينار زارزة', 'Minar Zarza', 'Minar Zarza', 43),
(1400, '1400', 'وادي العثمانية', 'Oued Athmenia', 'Oued Athmenia', 43),
(1401, '1401', 'وادي النجاء', 'Oued Endja', 'Oued Endja', 43),
(1402, '1402', 'وادي سقان', 'Oued Seguen', 'Oued Seguen', 43),
(1403, '1403', 'أولاد اخلوف', 'Ouled Khalouf', 'Ouled Khalouf', 43),
(1404, '1404', 'الرواشد', 'Rouached', 'Rouached', 43),
(1405, '1405', 'سيدي خليفة', 'Sidi Khelifa', 'Sidi Khelifa', 43),
(1406, '1406', 'سيدي مروان', 'Sidi Merouane', 'Sidi Merouane', 43),
(1407, '1407', 'تاجنانت', 'Tadjenanet', 'Tadjenanet', 43),
(1408, '1408', 'تسدان حدادة', 'Tassadane Haddada', 'Tassadane Haddada', 43),
(1409, '1409', 'التلاغمة', 'Teleghma', 'Teleghma', 43),
(1410, '1410', 'ترعي باينان', 'Terrai Bainen', 'Terrai Bainen', 43),
(1411, '1411', 'تسالة لمطاعي', 'Tassala Lematai', 'Tassala Lematai', 43),
(1412, '1412', 'تيبرقنت', 'Tiberguent', 'Tiberguent', 43),
(1413, '1413', 'يحي بني قشة', 'Yahia Beniguecha', 'Yahia Beniguecha', 43),
(1414, '1414', 'زغاية', 'Zeghaia', 'Zeghaia', 43),
(1415, '1415', 'عين البنيان', 'Ain-Benian', 'Ain-Benian', 44),
(1416, '1416', 'عين بويحيى', 'Ain-Bouyahia', 'Ain-Bouyahia', 44),
(1417, '1417', 'عين الدفلى', 'Ain-Defla', 'Ain-Defla', 44),
(1418, '1418', 'عين الاشياخ', 'Ain-Lechiakh', 'Ain-Lechiakh', 44),
(1419, '1419', 'عين السلطان', 'Ain-Soltane', 'Ain-Soltane', 44),
(1420, '1420', 'عين التركي', 'Ain-Torki', 'Ain-Torki', 44),
(1421, '1421', 'عريب', 'Arib', 'Arib', 44),
(1422, '1422', 'بربوش', 'Birbouche', 'Birbouche', 44),
(1423, '1423', 'بطحية', 'Bathia', 'Bathia', 44),
(1424, '1424', 'بلعاص', 'Belaas', 'Belaas', 44),
(1425, '1425', 'بن علال', 'Ben Allal', 'Ben Allal', 44),
(1426, '1426', 'بئر ولد خليفة', 'Bir-Ould-Khelifa', 'Bir-Ould-Khelifa', 44),
(1427, '1427', 'برج الأمير خالد', 'Bordj-Emir-Khaled', 'Bordj-Emir-Khaled', 44),
(1428, '1428', 'بومدفع', 'Boumedfaa', 'Boumedfaa', 44),
(1429, '1429', 'بوراشد', 'Bourached', 'Bourached', 44),
(1430, '1430', 'جليدة', 'Djelida', 'Djelida', 44),
(1431, '1431', 'جمعة أولاد الشيخ', 'Djemaa Ouled Cheikh', 'Djemaa Ouled Cheikh', 44),
(1432, '1432', 'جندل', 'Djendel', 'Djendel', 44),
(1433, '1433', 'العبادية', 'El-Abadia', 'El-Abadia', 44),
(1434, '1434', 'العامرة', 'El-Amra', 'El-Amra', 44),
(1435, '1435', 'العطاف', 'El-Attaf', 'El-Attaf', 44),
(1436, '1436', 'الماين', 'El-Maine', 'El-Maine', 44),
(1437, '1437', 'حمام ريغة', 'Hammam-Righa', 'Hammam-Righa', 44),
(1438, '1438', 'الحسانية', 'Hassania', 'Hassania', 44),
(1439, '1439', 'الحسينية', 'Hoceinia', 'Hoceinia', 44),
(1440, '1440', 'خميس مليانة', 'Khemis-Miliana', 'Khemis-Miliana', 44),
(1441, '1441', 'المخاطرية', 'Mekhatria', 'Mekhatria', 44),
(1442, '1442', 'مليانة', 'Miliana', 'Miliana', 44),
(1443, '1443', 'وادي الشرفاء', 'Oued Chorfa', 'Oued Chorfa', 44),
(1444, '1444', 'واد الجمعة', 'Oued Djemaa', 'Oued Djemaa', 44),
(1445, '1445', 'الروينة', 'Rouina', 'Rouina', 44),
(1446, '1446', 'سيدي الأخضر', 'Sidi-Lakhdar', 'Sidi-Lakhdar', 44),
(1447, '1447', 'تاشتة زقاغة', 'Tacheta Zegagha', 'Tacheta Zegagha', 44),
(1448, '1448', 'طارق بن زياد', 'Tarik-Ibn-Ziad', 'Tarik-Ibn-Ziad', 44),
(1449, '1449', 'تبركانين', 'Tiberkanine', 'Tiberkanine', 44),
(1450, '1450', 'زدين', 'Zeddine', 'Zeddine', 44),
(1451, '1451', 'عين بن خليل', 'Ain Ben Khelil', 'Ain Ben Khelil', 45),
(1452, '1452', 'عين الصفراء', 'Ain Sefra', 'Ain Sefra', 45),
(1453, '1453', 'عسلة', 'Asla', 'Asla', 45),
(1454, '1454', 'جنين بورزق', 'Djenienne Bourezg', 'Djenienne Bourezg', 45),
(1455, '1455', 'البيوض', 'El Biodh', 'El Biodh', 45),
(1456, '1456', 'القصدير', 'Kasdir', 'Kasdir', 45),
(1457, '1457', 'مكمن بن عمار', 'Makmen Ben Amar', 'Makmen Ben Amar', 45),
(1458, '1458', 'المشرية', 'Mecheria', 'Mecheria', 45),
(1459, '1459', 'مغرار', 'Moghrar', 'Moghrar', 45),
(1460, '1460', 'النعامة', 'Naama', 'Naama', 45),
(1461, '1461', 'سفيسيفة', 'Sfissifa', 'Sfissifa', 45),
(1462, '1462', 'تيوت', 'Tiout', 'Tiout', 45),
(1463, '1463', 'أغلال', 'Aghlal', 'Aghlal', 46),
(1464, '1464', 'عين الأربعاء', 'Ain El Arbaa', 'Ain El Arbaa', 46),
(1465, '1465', 'عين الكيحل', 'Ain Kihal', 'Ain Kihal', 46),
(1466, '1466', 'عين تموشنت', 'Ain Temouchent', 'Ain Temouchent', 46),
(1467, '1467', 'عين الطلبة', 'Ain Tolba', 'Ain Tolba', 46),
(1468, '1468', 'عقب الليل', 'Aoubellil', 'Aoubellil', 46),
(1469, '1469', 'بني صاف', 'Beni Saf', 'Beni Saf', 46),
(1470, '1470', 'بوزجار', 'Bouzedjar', 'Bouzedjar', 46),
(1471, '1471', 'شعبة اللحم', 'Chaabat El Ham', 'Chaabat El Ham', 46),
(1472, '1472', 'شنتوف', 'Chentouf', 'Chentouf', 46),
(1473, '1473', 'العامرية', 'El Amria', 'El Amria', 46),
(1474, '1474', 'المالح', 'El Maleh', 'El Maleh', 46),
(1475, '1475', 'المساعيد', 'El Messaid', 'El Messaid', 46),
(1476, '1476', 'الأمير عبد القادر', 'Emir Abdelkader', 'Emir Abdelkader', 46),
(1477, '1477', 'حمام بوحجر', 'Hammam Bou Hadjar', 'Hammam Bou Hadjar', 46),
(1478, '1478', 'الحساسنة', 'Hassasna', 'Hassasna', 46),
(1479, '1479', 'حاسي الغلة', 'Hassi El Ghella', 'Hassi El Ghella', 46),
(1480, '1480', 'وادي برقش', 'Oued Berkeche', 'Oued Berkeche', 46),
(1481, '1481', 'وادي الصباح', 'Oued Sebbah', 'Oued Sebbah', 46),
(1482, '1482', 'أولاد بوجمعة', 'Ouled Boudjemaa', 'Ouled Boudjemaa', 46),
(1483, '1483', 'أولاد الكيحل', 'Ouled Kihal', 'Ouled Kihal', 46),
(1484, '1484', 'ولهاصة الغرابة', 'Oulhaca El Gheraba', 'Oulhaca El Gheraba', 46),
(1485, '1485', 'سيدي بن عدة', 'Sidi Ben Adda', 'Sidi Ben Adda', 46),
(1486, '1486', 'سيدي بومدين', 'Sidi Boumediene', 'Sidi Boumediene', 46),
(1487, '1487', 'سيدي ورياش', 'Sidi Ouriache', 'Sidi Ouriache', 46),
(1488, '1488', 'سيدي صافي', 'Sidi Safi', 'Sidi Safi', 46),
(1489, '1489', 'تامزورة', 'Tamzoura', 'Tamzoura', 46),
(1490, '1490', 'تارقة', 'Terga', 'Terga', 46),
(1491, '1491', 'بريان', 'Berriane', 'Berriane', 47),
(1492, '1492', 'بونورة', 'Bounoura', 'Bounoura', 47),
(1493, '1493', 'ضاية بن ضحوة', 'Dhayet Bendhahoua', 'Dhayet Bendhahoua', 47),
(1494, '1494', 'العطف', 'El Atteuf', 'El Atteuf', 47),
(1495, '1495', 'المنيعة', 'El Meniaa', 'El Meniaa', 58),
(1496, '1496', 'غرداية', 'Ghardaia', 'Ghardaia', 47),
(1497, '1497', 'القرارة', 'El Guerrara', 'El Guerrara', 47),
(1498, '1498', 'حاسي الفحل', 'Hassi Fehal', 'Hassi Fehal', 58),
(1499, '1499', 'حاسي القارة', 'Hassi Gara', 'Hassi Gara', 58),
(1500, '1500', 'المنصورة', 'Mansoura', 'Mansoura', 47),
(1501, '1501', 'متليلي', 'Metlili', 'Metlili', 47),
(1502, '1502', 'سبسب', 'Sebseb', 'Sebseb', 47),
(1503, '1503', 'زلفانة', 'Zelfana', 'Zelfana', 47),
(1504, '1504', 'عين الرحمة', 'Ain Rahma', 'Ain Rahma', 48),
(1505, '1505', 'عين طارق', 'Ain-Tarek', 'Ain-Tarek', 48),
(1506, '1506', 'عمي موسى', 'Ammi Moussa', 'Ammi Moussa', 48),
(1507, '1507', 'بلعسل بوزقزة', 'Belaassel Bouzagza', 'Belaassel Bouzagza', 48),
(1508, '1508', 'بن داود', 'Bendaoud', 'Bendaoud', 48),
(1509, '1509', 'بني درقن', 'Beni Dergoun', 'Beni Dergoun', 48),
(1510, '1510', 'بني زنطيس', 'Beni Zentis', 'Beni Zentis', 48),
(1511, '1511', 'دار بن عبد الله', 'Dar Ben Abdelah', 'Dar Ben Abdelah', 48),
(1512, '1512', 'جديوية', 'Djidiouia', 'Djidiouia', 48),
(1513, '1513', 'الحاسي', 'El Hassi', 'El Hassi', 48),
(1514, '1514', 'الحمادنة', 'El H\'madna', 'El H\'madna', 48),
(1515, '1515', 'الولجة', 'El Ouldja', 'El Ouldja', 48),
(1516, '1516', 'القطار', 'El-Guettar', 'El-Guettar', 48),
(1517, '1517', 'المطمر', 'El-Matmar', 'El-Matmar', 48),
(1518, '1518', 'حد الشكالة', 'Had Echkalla', 'Had Echkalla', 48),
(1519, '1519', 'حمري', 'Hamri', 'Hamri', 48),
(1520, '1520', 'القلعة', 'Kalaa', 'Kalaa', 48),
(1521, '1521', 'لحلاف', 'Lahlef', 'Lahlef', 48),
(1522, '1522', 'مازونة', 'Mazouna', 'Mazouna', 48),
(1523, '1523', 'مديونة', 'Mediouna', 'Mediouna', 48),
(1524, '1524', 'منداس', 'Mendes', 'Mendes', 48),
(1525, '1525', 'مرجة سيدي عابد', 'Merdja Sidi Abed', 'Merdja Sidi Abed', 48),
(1526, '1526', 'واريزان', 'Ouarizane', 'Ouarizane', 48),
(1527, '1527', 'وادي الجمعة', 'Oued El Djemaa', 'Oued El Djemaa', 48),
(1528, '1528', 'وادي السلام', 'Oued Essalem', 'Oued Essalem', 48),
(1529, '1529', 'وادي رهيو', 'Oued-Rhiou', 'Oued-Rhiou', 48),
(1530, '1530', 'أولاد يعيش', 'Ouled Aiche', 'Ouled Aiche', 48),
(1531, '1531', 'أولاد سيدي الميهوب', 'Ouled Sidi Mihoub', 'Ouled Sidi Mihoub', 48),
(1532, '1532', 'الرمكة', 'Ramka', 'Ramka', 48),
(1533, '1533', 'غليزان', 'Relizane', 'Relizane', 48),
(1534, '1534', 'سيدي  خطاب', 'Sidi Khettab', 'Sidi Khettab', 48),
(1535, '1535', 'سيدي لزرق', 'Sidi Lazreg', 'Sidi Lazreg', 48),
(1536, '1536', 'سيدي أمحمد بن علي', 'Sidi M\'hamed Benali', 'Sidi M\'hamed Benali', 48),
(1537, '1537', 'سيدي امحمد بن عودة', 'Sidi M\'hamed Benaouda', 'Sidi M\'hamed Benaouda', 48),
(1538, '1538', 'سيدي سعادة', 'Sidi Saada', 'Sidi Saada', 48),
(1539, '1539', 'سوق الحد', 'Souk El Had', 'Souk El Had', 48),
(1540, '1540', 'يلل', 'Yellel', 'Yellel', 48),
(1541, '1541', 'زمورة', 'Zemmoura', 'Zemmoura', 48);

-- --------------------------------------------------------

--
-- Structure de la table `t_01_04_04`
--

CREATE TABLE `t_01_04_04` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_01_04_04`
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
-- Structure de la table `t_01_04_05`
--

CREATE TABLE `t_01_04_05` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(100) DEFAULT NULL,
  `F_05` varchar(100) DEFAULT NULL,
  `F_06` varchar(100) DEFAULT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL,
  `F_07` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_01_04_05`
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
-- Structure de la table `t_01_04_06`
--

CREATE TABLE `t_01_04_06` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(10) DEFAULT NULL,
  `F_05` varchar(10) DEFAULT NULL,
  `F_06` varchar(10) DEFAULT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_01_04_06`
--

INSERT INTO `t_01_04_06` (`F_00`, `F_04`, `F_05`, `F_06`, `F_01`, `F_02`, `F_03`) VALUES
(1, NULL, NULL, NULL, 'وزارة', 'Ministry', 'Ministère'),
(2, NULL, NULL, NULL, ' أركان الجيش', 'Army General Staff', 'Etat Major de l\'Armée'),
(3, NULL, NULL, NULL, 'مديرية عامة', 'General Directorate', 'Direction Générale'),
(4, NULL, NULL, NULL, 'الأمانة العامة لوزارة', 'Ministry General Secretariat', 'Secretariat Générale du Ministère'),
(5, NULL, NULL, NULL, 'ديوان', 'Cabinet', 'Cabinet'),
(6, NULL, NULL, NULL, 'قيادة قوات', 'Force Command', 'Commandement de Forces'),
(7, NULL, NULL, NULL, 'ناحية عسكرية', 'Military Region', 'Région Militaire'),
(8, NULL, NULL, NULL, ' أركان ناحية عسكرية', 'Military Region Staff', 'Etat Major d\'une Région Militaire'),
(9, NULL, NULL, NULL, 'دائرة', 'Department', 'Département'),
(10, NULL, NULL, NULL, 'مديرية مركزية', 'Central Directorate', 'Direction Centrale'),
(11, NULL, NULL, NULL, 'مديرية', 'Directorate', 'Direction'),
(12, NULL, NULL, NULL, 'مصلحة مركزية', 'Central Service', 'Service Central'),
(13, NULL, NULL, NULL, 'مصلحة', 'Service', 'Service'),
(14, NULL, NULL, NULL, 'مركز', 'Center', 'Centre'),
(15, NULL, NULL, NULL, 'مديرية دائرة', 'Department Directorate', 'Direction de Département'),
(16, NULL, NULL, NULL, 'مصلحة دائرة', 'Department Service', 'Service de Département'),
(17, NULL, NULL, NULL, 'مكتب دائرة', 'Department Office', 'Bureau de Département'),
(18, NULL, NULL, NULL, 'فصيلة', 'Section', 'Section'),
(19, NULL, NULL, NULL, 'مديرية جهوية', 'Regional Directorate', 'Direction Régionale'),
(20, NULL, NULL, NULL, 'مصلحة جهوية', 'Regional Service', 'Service Régional'),
(21, NULL, NULL, NULL, 'مكتب جهوي', 'Regional Office', 'Bureau Régional'),
(22, NULL, NULL, NULL, 'مركز جهوي', 'Regional Center', 'Centre Régional'),
(23, NULL, NULL, NULL, 'مديرية فرعية', 'Sub-Directorate', 'Sous Direction'),
(24, NULL, NULL, NULL, 'خلية', 'Cell', 'Cellule'),
(25, NULL, NULL, NULL, 'فصيلة فرعية', 'Subsection', 'Sous Section'),
(26, NULL, NULL, NULL, 'مكتب', 'Office', 'Bureau'),
(27, NULL, NULL, NULL, 'فصيلة مكتب', 'Office Section', 'Section de Bureau'),
(28, NULL, NULL, NULL, 'مدرسة وطنية', 'National School', 'Ecole Nationale'),
(29, NULL, NULL, NULL, 'مدرسة عليا', 'Higher School', 'Ecole Supérieure'),
(30, NULL, NULL, NULL, 'مدرسة تطبيقية', 'Application School', 'Ecole d\'Application'),
(31, NULL, NULL, NULL, 'مركز تدريب', 'Training Center', 'Centre d\'instruction'),
(32, NULL, NULL, NULL, 'مؤسسة وزارية', 'Ministerial Establishment', 'Etablissement Ministèriel'),
(33, NULL, NULL, NULL, 'مؤسسة مركزية', 'Central Establishment', 'Etablissement Centrale'),
(34, NULL, NULL, NULL, 'مؤسسة جهوية', 'Regional Establishment', 'Etablissement Régional'),
(35, NULL, NULL, NULL, 'فرقة', 'Division', 'Division'),
(36, NULL, NULL, NULL, 'لواء', 'Brigade', 'Brigade'),
(37, NULL, NULL, NULL, 'فوج', 'Regiment', 'Régiment'),
(38, NULL, NULL, NULL, 'فوج جهوي', 'Regional Regiment', 'Régiment Régional'),
(39, NULL, NULL, NULL, 'كتيبة', 'Battalion', 'Bataillon'),
(40, NULL, NULL, NULL, 'سرية', 'Company', 'Compagnie');

-- --------------------------------------------------------

--
-- Structure de la table `t_01_04_07`
--

CREATE TABLE `t_01_04_07` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(50) NOT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL,
  `F_06` bigint(20) DEFAULT NULL,
  `F_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_01_04_08`
--

CREATE TABLE `t_01_04_08` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(10) NOT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL,
  `F_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_01_04_09`
--

CREATE TABLE `t_01_04_09` (
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
-- Structure de la table `t_01_04_10`
--

CREATE TABLE `t_01_04_10` (
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

--
-- Déchargement des données de la table `t_02_01_01`
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

--
-- Déchargement des données de la table `t_02_02_02`
--

INSERT INTO `t_02_02_02` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'مخطط', 'Planed', 'Plannifié'),
(2, 'في طور الانجاز', 'Procurement Ongoing', 'En cours de Réalisation'),
(3, 'منجز', 'Procured', 'Réalisé');

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
  `F_07` bigint(20) NOT NULL,
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

--
-- Déchargement des données de la table `t_02_03_01`
--

INSERT INTO `t_02_03_01` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`) VALUES
(1, '101101', NULL, NULL, 'ENTREPRISE DE DEFENSE ET DE RESTAURATION DES SOLS'),
(2, '101102', NULL, NULL, 'ENTREPRISE D\'AMENAGEMENT DE PERIMETRES IRRIGUES ET DE DRAINAGE AGRICOLE'),
(3, '101103', NULL, NULL, 'ENTREPRISE DE TRAVAUX FORESTIERS ET D\'EXPLOITATION DES FORETS'),
(4, '101104', NULL, NULL, 'ENTREPRISE DE STOCKAGE ET DE TRAITEMENT DES CEREALES'),
(5, '101105', NULL, NULL, 'ENTREPRISE DE CONDITIONNEMENT DE PRODUITS AGRICOLES'),
(6, '101106', NULL, NULL, 'ENTREPRISE DE PRODUCTION ET DE DISTRIBUTION DES ESPECES VEGETALES NON CULTIVEES'),
(7, '101107', NULL, NULL, 'ENTREPRISE DE TRAVAUX AGRICOLES ET TRAITEMENT PHYTOSANITAIRE'),
(8, '101108', NULL, NULL, 'PEPINIERE'),
(9, '101109', NULL, NULL, 'PRODUCTION DES SEMENCES'),
(10, '101110', NULL, NULL, 'ENTREPRISE DE PRODUCTION ET DE CONSERVATION DE CHAMPIGNONS CULTIVES'),
(11, '101201', NULL, NULL, 'ENGRAISSEMENT INDUSTRIEL DE BOVINS ET OVINS'),
(12, '101202', NULL, NULL, 'ENGRAISSEMENT DE VOLAILLES ET ACCOUVAGES INDUSTRIELS'),
(13, '101203', NULL, NULL, 'ENTREPRISE DE LA PECHE INDUSTRIELLE'),
(14, '101204', NULL, NULL, 'ENTREPRISE DE TRANSFORMATION DE CORAIL'),
(15, '102101', NULL, NULL, 'FORAGES PETROLIERS'),
(16, '102102', NULL, NULL, 'GEOPHYSIQUE'),
(17, '102103', NULL, NULL, 'ANALYSES LIEES AUX HYDROCARBURES.'),
(18, '102104', NULL, NULL, 'ETUDES ET ENGINEERING LIES AUX HYDROCARBURES'),
(19, '102105', NULL, NULL, 'ENTREPRISE D\'INGENIERIE INDUSTRIELLE'),
(20, '102201', NULL, NULL, 'CONSTRUCTION D\'OUVRAGES DESTINES A LA PRODUCTION, LA TRANSFORMATION, TRANSPORT ET LA DISTRIBUTION DES HYDROCARBURES'),
(21, '102202', NULL, NULL, 'EXTRACTION D\'HYDROCARBURES LIQUIDES ET GAZEUX'),
(22, '102203', NULL, NULL, 'PRODUCTION, DISTRIBUTION ET TRANSPORT D\'ELECTRICITE'),
(23, '102204', NULL, NULL, 'TRANSPORT DE GAZ NATUREL'),
(24, '102205', NULL, NULL, 'TRANSPORT D\'HYDROCARBURES LIQUIDES ET CONDENSAT PAR CANALISATION'),
(25, '102206', NULL, NULL, 'DISTRIBUTION PUBLIQUE DE GAZ'),
(26, '102207', NULL, NULL, 'ENTREPRISE DE MAINTENANCE DES CANALISATIONS D\'HYDROCARBURES'),
(27, '102301', NULL, NULL, 'RAFFINAGE DU PETROLE'),
(28, '102302', NULL, NULL, 'LIQUEFACTION DE GAZ NATUREL'),
(29, '102303', NULL, NULL, 'COKERIE'),
(30, '102304', NULL, NULL, 'FABRICATION D\'AGGLOMERES DE COMBUSTIBLES'),
(31, '103101', NULL, NULL, 'EXTRACTION ET PREPARATION DE MINERAI DE FER'),
(32, '103102', NULL, NULL, 'EXTRACTION ET PREPARATION DE BAUXITE'),
(33, '103103', NULL, NULL, 'EXTRACTION ET PREPARATION DE MINERAIS DE METAUX COMMUNS NON FERREUX'),
(34, '103104', NULL, NULL, 'EXTRACTION ET PREPARATION DE MINERAIS POUR FERRO-ALLIAGES'),
(35, '103105', NULL, NULL, 'EXTRACTION ET PREPARATION DE MINERAIS DE METAUX PRECIEUX'),
(36, '103106', NULL, NULL, 'EXTRACTION ET PREPARATION DE MINERAIS METALLIQUE DIVERS'),
(37, '103107', NULL, NULL, 'EXTRACTION ET PREPARATION DE MINERAIS D\'URANIUM ET DE MINERAIS RADIOACTIFS'),
(38, '103108', NULL, NULL, 'EXTRACTION ET PREPARATION DE PYRITE, PRODUCTION DE SOUFRE'),
(39, '103109', NULL, NULL, 'EXTRACTION ET PREPARATION DE PRODUITS MINERAUX DIVERS'),
(40, '103110', NULL, NULL, 'BROYAGE A FACON DE MINERAIS ET MATERIAUX (NON ASSOCIE A L\'EXTRACTION)'),
(41, '103111', NULL, NULL, 'TRAVAUX LIES A L\'EXPLOITATION DES MINES'),
(42, '103201', NULL, NULL, 'EXTRACTION DE HOUILLE'),
(43, '103202', NULL, NULL, 'EXPLOITATION DE CARRIERES DE PIERRES DE TAILLE POUR LA CONSTRUCTION ET L\'INDUSTRIE'),
(44, '103203', NULL, NULL, 'EXTRACTION ET PREPARATION DE SABLES, EXTRACTION DE MATERIAUX ALLUVIONNAIRES'),
(45, '103204', NULL, NULL, 'EXTRACTION ET PREPARATION DE GYPSE'),
(46, '103205', NULL, NULL, 'EXTRACTION ET PREPARATION DE LA PIERRE A CHAUX'),
(47, '103206', NULL, NULL, 'EXTRACTION ET PREPARATION DE L\'ARGILE'),
(48, '103207', NULL, NULL, 'EXTRACTION ET PREPARATION DE PRODUITS DE CARRIERE DIVERS NON DESTINES AUX MATERIAUX DE CONSTRUCTION'),
(49, '103208', NULL, NULL, 'EXTRACTION ET PREPARATION DE SEL DE POTASSE'),
(50, '103209', NULL, NULL, 'EXTRACTION ET PREPARATION DE SELS Y COMPRIS MARAIS SALANTS'),
(51, '103210', NULL, NULL, 'EXTRACTION ET PREPARATION DE PHOSPHATES'),
(52, '103211', NULL, NULL, 'EXTRACTION ET PREPARATION DE BITUME ET D\'ASPHALTE NATURELS'),
(53, '104101', NULL, NULL, 'INDUSTRIE DE L\'ACIDE SULFURIQUE ET PRODUITS DERIVES'),
(54, '104102', NULL, NULL, 'FABRICATION DE PRODUITS CHIMIQUES PAR ELECTROLYSE OU ELECTROTHERMIE'),
(55, '104103', NULL, NULL, 'FABRICATION DE GAZ COMPRIME OU LIQUEFIE (NON COMPRIS G.P.L+G.N.L)'),
(56, '104104', NULL, NULL, 'FABRICATION D\'OPACIFIANTS MINERAUX, DE COMPOSITIONS ET DE COULEURS POUR EMAUX'),
(57, '104105', NULL, NULL, 'FABRICATION DE PRODUITS DIVERS DE LA CHIMIE MINERALE'),
(58, '104106', NULL, NULL, 'FABRICATION D\'ENGRAIS AZOTES ETAUTRES PRODUITS AZOTES'),
(59, '104107', NULL, NULL, 'FABRICATION D\'ENGRAIS PHOSPHATES'),
(60, '104108', NULL, NULL, 'FABRICATION D\'AUTRES ENGRAIS'),
(61, '104109', NULL, NULL, 'FABRICATION DE PRODUITS CHIMIQUES DE BASE POUR LA PHARMACIE'),
(62, '104110', NULL, NULL, 'FABRICATION DE PRODUITS CHIMIQUES A USAGE MECANIQUE ET METALLURGIQUE ET POUR L\'INDUSTRIE'),
(63, '104111', NULL, NULL, 'PREMIERE TRANSFORMATION DE LA MATIERE PLASTIQUE DE BASE'),
(64, '104112', NULL, NULL, 'FABRICATION DE REACTIFS DE LABORATOIRE'),
(65, '104201', NULL, NULL, 'FABRICATION D\'HYDROCARBURES ACYCLIQUES, CYCLIQUES OU AROMATIQUES,DERIVES D\'HYDROCARBURES ET AUTRES COMPOSES ORGANIQUES N.D.A.'),
(66, '104202', NULL, NULL, 'FABRICATION DE MATIERES COLORANTES DE SYNTHESE'),
(67, '104203', NULL, NULL, 'FABRICATION DE MATIERE PLASTIQUE DE BASE ET DE RESINES SYNTHETIQUES'),
(68, '104204', NULL, NULL, 'EXTRACTION ET TRANSFORMATION DE MATIERES ANIMALES ET VEGETALES'),
(69, '104205', NULL, NULL, 'INDUSTRIE DES GOUDRONS ET BENZOLS'),
(70, '104206', NULL, NULL, 'FABRICATION D\'EXPLOSIFS, D\'ACCESSOIRES DE MISE A FEU ET D\'ARTIFICES'),
(71, '104207', NULL, NULL, 'FABRICATION DE PRODUITS PHARMACEUTIQUES'),
(72, '104208', NULL, NULL, 'FABRICATION D\'ABRASIFS'),
(73, '104209', NULL, NULL, 'FABRICATION DE CHARBON ARTIFICIELET DE TERRES ACTIVEES'),
(74, '104210', NULL, NULL, 'FABRICATION DE PEINTURES, VERNIS,PIGMENTS ET ENCRES D\'IMPRIMERIE'),
(75, '104211', NULL, NULL, 'FABRICATION DE PRODUITS INSECTICIDES ANTICRYPTOGAMIQUES ET DESINFECTANTS'),
(76, '104212', NULL, NULL, 'FABRICATION DE PRODUITS PHOTOGRAPHIQUES'),
(77, '104213', NULL, NULL, 'FABRICATION D\'HUILES ESSENTIELLES DE PARFUMERIE ET COSMETIQUES'),
(78, '104214', NULL, NULL, 'FABRICATION DE POUDRETTE DE CAOUTCHOUC ET DE CAOUTCHOUC REGENERE'),
(79, '104215', NULL, NULL, 'FABRICATION DE PNEUMATIQUES ET BANDAGES'),
(80, '104216', NULL, NULL, 'FABRICATION D\'ARTICLES EN CAOUTCHOUC N.D.A. ( NC CHAUSSURES)'),
(81, '104217', NULL, NULL, 'FABRICATION D\'ALLUMETTES'),
(82, '104218', NULL, NULL, 'FABRICATION DE SAVON ET SAVONNETTES (SAVONNERIE)'),
(83, '104219', NULL, NULL, 'FABRICATION DE BOUGIES, CHANDELLES, CIERGES ET DERIVES DE LA STEARINERIE'),
(84, '104220', NULL, NULL, 'FABRICATION DE PATE A PAPIER ET DE PAPIER ET CARTON'),
(85, '104221', NULL, NULL, 'FABRICATION DE PRODUITS DE BLANCHIMENT ET DE PRODUITS D\'ENTRETIEN ET CONNEXES'),
(86, '104222', NULL, NULL, 'FABRICATION DE PRODUITS CHIMIQUES POUR LE TRAITEMENT DES EAUX, DES CUIRS ET TEXTILES ET AUTRES MATERIAUX'),
(87, '105101', NULL, NULL, 'FABRICATION DE MATERIEL INDUSTRIEL DE COMBUSTION, MATERIEL THERMIQUEET AERAULIQUE'),
(88, '105102', NULL, NULL, 'FABRICATION DE MATERIEL FRIGORIFIQUE (ELECTRIQUE OU NON) NON DOMESTIQUE'),
(89, '105103', NULL, NULL, 'FABRICATION DE MOTEURS STATIONNAIRES (N.C MOTEURS D\'AUTOS, D\'AVIONS ET GROS MOTEURS MARINS)'),
(90, '105104', NULL, NULL, 'FABRICATION DE MACHINES ET TURBINES A VAPEUR'),
(91, '105105', NULL, NULL, 'FABRICATION DE POMPES ET DE MATERIEL HYDRAULIQUE'),
(92, '105106', NULL, NULL, 'FABRICATION DE MATERIEL D\'INCENDIE'),
(93, '105107', NULL, NULL, 'FABRICATION D\'APPAREILS DE LEVAGE ET DE MANUTENTION, DE MATERIEL POUR INDUSTRIES LOURDES ET POUR CHEMINS DE FER'),
(94, '105108', NULL, NULL, 'FABRICATION DE MATERIEL ROULANT POUR CHEMINS DE FER'),
(95, '105201', NULL, NULL, 'FABRICATION DE MACHINES AGRICOLES'),
(96, '105202', NULL, NULL, 'FABRICATION DE MACHINES OUTILS, D\'OUTILLAGES MECANIQUES, D\'ORGANES MECANIQUES (YC MATERIEL DE SOUDAGE)'),
(97, '105203', NULL, NULL, 'FABRICATION DE MATERIEL POUR LES INDUSTRIES ALIMENTAIRES, DU TABAC ET ALLUMETTES ( INDUSTRIES DU CUIR, DU CAOUTCHOUC ET MATIERES PLASTIQUES, MACHINES DE CONDITIONNEMENT& CONGELATIO'),
(98, '105204', NULL, NULL, 'FABRICATION DE MACHINES A COUDRE ET MACHINES POUR CHAUSSURES'),
(99, '105205', NULL, NULL, 'FABRICATION DE MACHINES POUR L\'INDUSTRIE TEXTILE'),
(100, '105206', NULL, NULL, 'FABRICATION DE MACHINES POUR L\'INDUSTRIE DU PAPIER ET DU CARTON'),
(101, '105207', NULL, NULL, 'MECANIQUE DE PRECISION'),
(102, '105208', NULL, NULL, 'MODELAGE MECANIQUE'),
(103, '105209', NULL, NULL, 'FABRICATION DE ROULEMENTS'),
(104, '105210', NULL, NULL, 'FABRICATION DE RESSORTS'),
(105, '105211', NULL, NULL, 'FABRICATION DE CHAINES ET CHAINETTES'),
(106, '105212', NULL, NULL, 'FABRICATION D\'OUTILLAGE A MAIN METALLIQUE'),
(107, '105213', NULL, NULL, 'FABRICATION DE MATERIELS ET EQUIPEMENTS AVICOLES'),
(108, '105301', NULL, NULL, 'CONSTRUCTION DE NAVIRES ET ENGINS FLOTTANTS EN ACIER OU EN BOIS'),
(109, '105302', NULL, NULL, 'REPARATION NAVALE, RENFLOUEMENT ET DEMOLITION DE NAVIRES OU EPAVES'),
(110, '105303', NULL, NULL, 'TRAVAUX A FACON POUR LES NAVIRES'),
(111, '105304', NULL, NULL, 'ENTREPRISE DE TRAVAUX SOUS-MARINS, MARINS ET FLUVIAUX'),
(112, '105401', NULL, NULL, 'CONSTRUCTION DE VEHICULES AUTOMOBILES ET DE MOTEURS THERMIQUES'),
(113, '105402', NULL, NULL, 'CONSTRUCTION DE CARROSSERIE AUTOMOBILE DE REMORQUES ET DE BENNES'),
(114, '105403', NULL, NULL, 'FABRICATION D\'EQUIPEMENT, D\'ACCESSOIRES ET PIECES DETACHEES POUR AUTOS'),
(115, '105404', NULL, NULL, 'RECHAPAGE NON INDUSTRIEL DE PNEUS'),
(116, '105405', NULL, NULL, 'FABRICATION DE CYCLES ET MOTOCYCLES'),
(117, '105406', NULL, NULL, 'FABRICATION DE PIECES DETACHEES ET ACCESSOIRES POUR CYCLES ET MOTOCYCLES'),
(118, '105407', NULL, NULL, 'CONSTRUCTION DE CELLULES D\'AERODYNES'),
(119, '105408', NULL, NULL, 'CONSTRUCTION DE MOTEURS D\'AVION'),
(120, '105409', NULL, NULL, 'CONSTRUCTION ET INSTALLATION D\'EQUIPEMENTS, D\'ARMEMENTS, D\'APPAREILS DE BORD DES AVIONS'),
(121, '105410', NULL, NULL, 'CONSTRUCTION ET INSTALLATION DE MATERIEL ELECTRIQUE ET SPECIAUX DE BORD DES AVIONS'),
(122, '105411', NULL, NULL, 'CONSTRUCTION ET INSTALLATION DE MATERIEL DE TERRAIN ET D\'ENTRAINEMENT DE PILOTAGE AU SOL'),
(123, '105412', NULL, NULL, 'CONSTRUCTION ET INSTALLATION DE MATERIELS DIVERS D\'ESSAIS AERONAUTIQUES'),
(124, '105413', NULL, NULL, 'CONSTRUCTION D\'ENGINS SPECIAUX'),
(125, '105414', NULL, NULL, 'FABRICATION D\'ARMES DE GUERRE'),
(126, '105415', NULL, NULL, 'FABRICATION D\'ARMES DE CHASSE ET DE DEFENSE'),
(127, '105416', NULL, NULL, 'ENTREPRISE INDUSTRIELLE DE RECHAPAGE DE PNEUS'),
(128, '105501', NULL, NULL, 'FABRICATION DE CABLES ELECTRIQUES ET TELEPHONIQUES'),
(129, '105502', NULL, NULL, 'FABRICATION DE MACHINES ET MATERIELS ELECTRIQUES ET D\'APPAREILLAGES DE DISTRIBUTION'),
(130, '105503', NULL, NULL, 'FABRICATION D\'APPAREILS RADIOELECTRIQUES ET ELECTRONIQUES'),
(131, '105504', NULL, NULL, 'FABRICATION DE PETIT APPAREILLAGE D\'INSTALLATION ET D\'ECLAIRAGE ELECTRIQUE, MATERIEL TELEPHONIQUE ET TELEGRAPHIQUE'),
(132, '105505', NULL, NULL, 'FABRICATION DE COMPTEURS ELECTRIQUES, TRANSFORMATEURS DE MESURE, D\' APPAREILS ELECTRIQUES ET ELECTRONIQUES DE MESURE ET DE CONTRÔLE'),
(133, '105506', NULL, NULL, 'FABRICATION DE PILES ET ACCUMULATEURS'),
(134, '105507', NULL, NULL, 'FABRICATION DE LAMPES ELECTRIQUES'),
(135, '105508', NULL, NULL, 'FABRICATION D\'APPAREILS ELECTRIQUES ELECTROMENAGERS DIVERS'),
(136, '105509', NULL, NULL, 'FABRICATION DE MATERIELS ,EQUIPEMENTS LIES AUX DOMAINES DE L\'ENERGIE SOLAIRE ET ELECTRIQUE'),
(137, '105510', NULL, NULL, 'REALISATION ET CONCEPTION DE COMPOSANTS ELECTRONIQUES DESTINES A LA MAINTENANCE INDUSTRIELLE'),
(138, '105511', NULL, NULL, 'FABRICATION DE PANNEAUX DE SIGNALISATION ROUTIERE ET AUTO-ROUTIERE'),
(139, '105512', NULL, NULL, 'FABRICATION D\'EQUIPEMENTS, DE MATERIELS ET FOURNITURES DE SECURITE ET DE PROTECTION'),
(140, '105601', NULL, NULL, 'FABRICATION D\'HORLOGERIE'),
(141, '105602', NULL, NULL, 'FABRICATION DE COMPTEURS, D\'APPAREILS DE PESAGE ET DE CONTROLE ET D\'INSTRUMENTS DE METROLOGIE'),
(142, '105603', NULL, NULL, 'FABRICATION D\'INSTRUMENTS DE PRECISION ET D\'OPTIQUE'),
(143, '105604', NULL, NULL, 'FABRICATION DE LUNETTERIE'),
(144, '105605', NULL, NULL, 'FABRICATION DE MATERIEL PHOTOGRAPHIQUE ET CINEMATOGRAPHIQUE'),
(145, '105701', NULL, NULL, 'FABRICATION DE MACHINES DE BUREAU ET DE TRAITEMENT DE L\'INFORMATION'),
(146, '105702', NULL, NULL, 'FABRICATION DES PRODUITS PARA-PHARMACEUTIQUES , MATERIEL S, APPAREILS ET INSTRUMENTS MEDICAUX-CHIRURGICAUX'),
(147, '106101', NULL, NULL, 'FABRICATION D\' ACIERS COURANTS'),
(148, '106102', NULL, NULL, 'LAMINAGE A CHAUD DES ACIERS COURANTS'),
(149, '106103', NULL, NULL, 'FABRICATION DES ACIERS FINS ET SPECIAUX (A.F.S)'),
(150, '106104', NULL, NULL, 'LAMINAGE ET FABRICATION DE PRODUITS A.F.S.'),
(151, '106105', NULL, NULL, 'TRANSFORMATION DES ACIERS FINS ET SPECIAUX (AFS)'),
(152, '106106', NULL, NULL, 'LAMINAGE ET PROFILAGE A FROID DE L\'ACIER COURANT, TREFILAGE, ETIRAGE ET AUTRES TRANSFORMATIONS DE L\'ACIER COURANT'),
(153, '106107', NULL, NULL, 'FABRICATION DE TUBES D\'ACIER'),
(154, '106108', NULL, NULL, 'METALLURGIE DE L\'ALUMINIUM ET D\'AUTRES METAUX LEGERS (Y COMPRIS FABRICATION D\'ALLIAGES LEGERS, DE DURALUMIN ET D\'ALPAX)'),
(155, '106109', NULL, NULL, 'PREMIERE TRANSFORMATION DES METAUX NON FERREUX ET FABRICATION DE DEMI-PRODUITS EN METAUX NON FERREUX'),
(156, '106110', NULL, NULL, 'PRODUCTION DE FERRO ALLIAGES'),
(157, '106111', NULL, NULL, 'METALLURGIE DES METAUX ENTRANT DANS LES FERRO-ALLIAGES ET METAUX CONNEXES'),
(158, '106112', NULL, NULL, 'METALLURGIE ET AFFINAGE DES METAUX COMMUNS ET LEURS ALLIAGES'),
(159, '106113', NULL, NULL, 'AFFINAGE DES METAUX PRECIEUX'),
(160, '106114', NULL, NULL, 'LAMINAGE SUR BOIS'),
(161, '106201', NULL, NULL, 'FABRICATION DE FONTE'),
(162, '106202', NULL, NULL, 'GROSSE FORGE ET GROS EMBOUTISSAGE'),
(163, '106203', NULL, NULL, 'FONDERIE'),
(164, '106204', NULL, NULL, 'CHAUDRONNERIE'),
(165, '106205', NULL, NULL, 'ROBINETTERIE'),
(166, '106206', NULL, NULL, 'CONSTRUCTION METALLIQUE (FABRICATION D\'ELEMENTS ET POSE ASSOCIEES OU FABRICATION SEULE)'),
(167, '106207', NULL, NULL, 'PRODUCTION ET TRAVAUX D\'USINAGE (SUR PLAN)'),
(168, '106208', NULL, NULL, 'REVETEMENT ET TRAITEMENT DES METAUX'),
(169, '106209', NULL, NULL, 'DECOUPAGE ET EMBOUTISSAGE DE METAL'),
(170, '106210', NULL, NULL, 'DECOLLETAGE, TOURNAGE, BOULONNERIE, VISSERIE'),
(171, '106211', NULL, NULL, 'ESTAMPAGE, FORGE, MATRICAGE'),
(172, '106212', NULL, NULL, 'FERRONNERIE ET MENUISERIE METALLIQUES'),
(173, '106213', NULL, NULL, 'FABRICATION D\'EMBALLAGES ET CONDITIONNEMENT METALLIQUES'),
(174, '106214', NULL, NULL, 'FABRICATION DE TUYAUX METALLIQUES FLEXIBLES'),
(175, '106215', NULL, NULL, 'FABRICATION DE PETITS ARTICLES METALLIQUES'),
(176, '106216', NULL, NULL, 'FABRICATION D\'AIMANTS PERMANENTS.'),
(177, '106217', NULL, NULL, 'FABRICATION D\'ELECTRODES POUR ELECTROMETALLURGIE OU ELECTROCHIMIE EN GRAHITE OU EN CARBONE AMORPHE (N.C LES ELECTRODES DE SOUDURE)'),
(178, '106218', NULL, NULL, 'MENUISERIE ALUMINIUM'),
(179, '106219', NULL, NULL, 'FABRICATION DE STORES VENITIENS ET CALIFORNIENS'),
(180, '106220', NULL, NULL, 'ENTREPRISE DE MENUISERIE DE BOIS'),
(181, '106221', NULL, NULL, 'TRAITEMENT DE SURFACES'),
(182, '107101', NULL, NULL, 'MEUNERIE'),
(183, '107102', NULL, NULL, 'FABRICATION DE PATES ALIMENTAIRES ET COUSCOUS'),
(184, '107103', NULL, NULL, 'DECORTICAGE ET TRIAGE DE GRAINS ET GRAINES'),
(185, '107104', NULL, NULL, 'PRODUITS AMYLACES'),
(186, '107105', NULL, NULL, 'BOULANGERIE INDUSTRIELLE'),
(187, '107106', NULL, NULL, 'BISCUITERIE, PATISSERIE ET PRODUITS DE REGIME'),
(188, '107107', NULL, NULL, 'FABRICATION D\'ALIMENTS INFANTILES'),
(189, '107201', NULL, NULL, 'PRODUCTION DE LAITS ET PRODUITS LAITIERS (LAITERIE)'),
(190, '107202', NULL, NULL, 'BEURRERIE'),
(191, '107203', NULL, NULL, 'FROMAGERIE'),
(192, '107204', NULL, NULL, 'FABRICATION DE PRODUITS DE LA CONFISERIE'),
(193, '107205', NULL, NULL, 'INDUSTRIE DE LA CHOCOLATERIE'),
(194, '107206', NULL, NULL, 'FABRICATION DE GLACES INDUSTRIELLES'),
(195, '107207', NULL, NULL, 'FABRICATION D\'ESSENCES ET AROMES ALIMENTAIRES'),
(196, '107208', NULL, NULL, 'PRODUCTION INDUSTRIELLE DE CREMES GLACEES ET AUTRES PRODUITS GLACES'),
(197, '107301', NULL, NULL, 'HUILERIE ET RAFFINAGE D\'HUILES D\'ORIGINE VEGETALE'),
(198, '107302', NULL, NULL, 'FABRICATION DE CORPS GRAS D\'ORIGINE ANIMALE'),
(199, '107303', NULL, NULL, 'FABRICATION DE MARGARINES'),
(200, '107401', NULL, NULL, 'SUCRERIE ET RAFFINERIE'),
(201, '107402', NULL, NULL, 'PREPARATION DE CAFE, THE, CHICOREE (BRULERIE DE CAFE)'),
(202, '107403', NULL, NULL, 'INDUSTRIE DE LA LEVURE (LEVURERIE)'),
(203, '107404', NULL, NULL, 'TRAITEMENT ET TRANSFORMATION DE LA DATTE'),
(204, '107501', NULL, NULL, 'VINIFICATION, CONSERVATION, MANUTENTION DES VINS'),
(205, '107502', NULL, NULL, 'PREPARATION DE VINS SPECIAUX'),
(206, '107503', NULL, NULL, 'BRASSERIE, MALTERIE'),
(207, '107504', NULL, NULL, 'CIDRERIE'),
(208, '107505', NULL, NULL, 'PRODUCTION D\'EAU MINERALE ET BOISSONS DIVERSES NON ALCOOLISEES'),
(209, '107506', NULL, NULL, 'DISTILLERIE D\'ALCOOLS A USAGE ALIMENTAIRE'),
(210, '107507', NULL, NULL, 'DISTILLERIE DE LIQUEURS ET FABRICATION D\'APERITIFS'),
(211, '107508', NULL, NULL, 'DISTILLERIE DE BETTERAVES,TOPINAMBOURS, GRAINS, POMMES DE TERRE, ETC'),
(212, '107509', NULL, NULL, 'TRAITEMENT DU TABAC EN FEUILLES'),
(213, '107510', NULL, NULL, 'MANUFACTURE DE TABAC ET ALLUMETTES'),
(214, '107601', NULL, NULL, 'CONSERVERIE DE FRUITS ET LEGUMES'),
(215, '107602', NULL, NULL, 'FABRICATION DE CONSERVES DE VIANDE, VOLAILLE ET FOIE GRAS'),
(216, '107603', NULL, NULL, 'PREPARATION DES PLATS CUISINES'),
(217, '107604', NULL, NULL, 'CONSERVERIE DE POISSONS ET DE CRUSTACES'),
(218, '107605', NULL, NULL, 'FABRICATION DE CONDIMENTS DIVERS'),
(219, '107606', NULL, NULL, 'FABRICATION DE BOUILLONS , POTAGE ET AROMES'),
(220, '107607', NULL, NULL, 'ABATTAGE ET DECOUPAGE INDUSTRIEL DE VIANDES DE BOUCHERIE (ABATTOIR INDUSTRUEL)'),
(221, '107608', NULL, NULL, 'FABRICATION DE PRODUITS POUR L\'ALIMENTATION DES ANIMAUX (N.C FARINES DE POISSONS)'),
(222, '107609', NULL, NULL, 'TRANSFORMATION ET CONSERVATION DE FRUITS ET LEGUMES'),
(223, '107610', NULL, NULL, 'PRODUCTION DE VIANDES DE BOUCHERIE ET DE VOLAILLES ET DE LAPINS FRAICHES CONGELEES OU SURGELEES'),
(224, '108101', NULL, NULL, 'PREPARATION DE LA LAINE (DE TONTE)'),
(225, '108102', NULL, NULL, 'FILATURE INDUSTRIELLE DE LA LAINE'),
(226, '108103', NULL, NULL, 'TISSAGE INDUSTRIEL DE LA LAINE ET AUTRES MATIERES TEXTILES'),
(227, '108104', NULL, NULL, 'FABRICATION INDUSTRIELLE DE TAPIS'),
(228, '108105', NULL, NULL, 'FABRICATION INDUSTRIELLE DE FEUTRES ET DE TISSUS NON TISSES ET OUATERIE'),
(229, '108106', NULL, NULL, 'TRAVAIL DU COTON'),
(230, '108107', NULL, NULL, 'FILATURE INDUSTRIELLE DU COTON'),
(231, '108108', NULL, NULL, 'TISSAGE INDUSTRIEL DU COTON ASSOCIE A D\'AUTRES MATIERES TEXTILES'),
(232, '108109', NULL, NULL, 'FABRICATION INDUSTRIELLE DE FILS A COUDRE'),
(233, '108110', NULL, NULL, 'INDUSTRIES DE FIBRES VEGETALES A L\'EXCLUSION DU COTON'),
(234, '108111', NULL, NULL, 'PREPARATION DE LA SOIE NATURELLE ET FILATURE DES TEXTILES ARTIFICIELS ET SYNTHETIQUES'),
(235, '108112', NULL, NULL, 'FABRICATION DE FIBRES SYNTHETIQUES (NON CELLULOSIQUES)'),
(236, '108113', NULL, NULL, 'TISSAGE INDUSTRIEL DE LA SOIE ASSOCIEE A D\'AUTRES MATIERES TEXTILES'),
(237, '108114', NULL, NULL, 'BONNETERIE INDUSTRIELLE'),
(238, '108115', NULL, NULL, 'FABRICATION DE PRODUITS TEXTILES ELASTIQUES POUR TOUS USAGES'),
(239, '108116', NULL, NULL, 'FABRICATION INDUSTRIELLE DE DENTELLES, TULLES, GUIPURES,BOBINOTS, BRODERIES'),
(240, '108117', NULL, NULL, 'FABRICATION DE RUBANS, TRESSES, PASSEMENTERIE'),
(241, '108118', NULL, NULL, 'FABRICATION INDUSTRIELLE DE FILETS NOUES ET FILETS DE PECHE'),
(242, '108119', NULL, NULL, 'FINISSAGE DE TEXTILES (BLANCHIMENT, TEINTURE, IMPRESSION ET APPRETS)'),
(243, '108120', NULL, NULL, 'FABRICATION INDUSTRIELLE DE LINOLEUM, TOILES CIREES ET PRODUITS SIMILAIRES'),
(244, '108121', NULL, NULL, 'CONFECTION INDUSTRIELLE DE VETEMENTS ET LINGERIE'),
(245, '108122', NULL, NULL, 'INDUSTRIES DIVERSES DE L\'HABILLEMENT'),
(246, '108123', NULL, NULL, 'FABRICATION DE TOILES INDUSTRIELLES ET D\'OUVRAGES EN TISSUS'),
(247, '108124', NULL, NULL, 'TRICOTAGE INDUSTRIEL'),
(248, '108125', NULL, NULL, 'CONFECTION , FABRICATION DE L\'EMBLEME NATIONAL ET DE L\'ECUSSON PORTEUR DE L\'EMBLEME NATIONAL ET DU FANION'),
(249, '108201', NULL, NULL, 'TANNERIE INDUSTRIELLE DE CUIR DE BOVINS A DESSUS (CUIR AU CHROME)'),
(250, '108202', NULL, NULL, 'TANNERIE INDUSTRIELLE DE CUIR A DESSUS OU A SEMELLE (DE BOVIN,CAMELIDE, EQUIDE)'),
(251, '108203', NULL, NULL, 'TANNERIE ET MEGISSERIE INDUSTRIELLE D\'OVINS ET CAPRINS'),
(252, '108204', NULL, NULL, 'PELLETERIE'),
(253, '108205', NULL, NULL, 'FABRICATION DE SUCCEDANES DE CUIR (CUIR SYNTHETIQUE OU ARTIFICIEL)'),
(254, '108206', NULL, NULL, 'FABRICATION INDUSTRIELLE D\'AGGLOMERES DE CUIR ET DE SYNDERME'),
(255, '108207', NULL, NULL, 'FABRICATION DE GANTS DE PEAUX'),
(256, '108208', NULL, NULL, 'FABRICATION INDUSTRIELLE DE MAROQUINERIE, D\'ARTICLES DE VOYAGEET D\'AUTRES ARTICLES SIMILAIRES DESTINES A TOUS USAGES'),
(257, '108209', NULL, NULL, 'BOURRELLERIE'),
(258, '108210', NULL, NULL, 'FABRICATION DE COURROIES EN CUIR A USAGE INDUSTRIEL'),
(259, '108211', NULL, NULL, 'FABRICATION INDUSTRIELLE DE CHAUSSURES'),
(260, '108212', NULL, NULL, 'FABRICATION INDUSTRIELLE DE VETEMENTS EN CUIR'),
(261, '108301', NULL, NULL, 'PRODUCTION INDUSTRIELLE DE BIJOUX'),
(262, '109101', NULL, NULL, 'FABRICATION INDUSTRIELLE DE CIMENTS (CIMENTERIE)'),
(263, '109102', NULL, NULL, 'FABRICATION INDUSTRIELLE DE PLATRE ET DERIVES (PLATERERIE)'),
(264, '109103', NULL, NULL, 'FABRICATION INDUSTRIELLE DE PRODUITS EN BETON AUTRES QU\' AGGLOMERES'),
(265, '109104', NULL, NULL, 'FABRICATION INDUSTRIELLE DE PRODUITS MANUFACTURES EN BETON OU EN PLATRE (DITS AGGLOMERES)'),
(266, '109105', NULL, NULL, 'FABRICATION INDUSTRIELLE DE CARREAUX ET DALLES EN CIMENT ET GRANITO'),
(267, '109106', NULL, NULL, 'PREFABRICATION D\'ELEMENTS EN BETON ET FABRICATION DU BETON PRET A L\'EMPLOI'),
(268, '109107', NULL, NULL, 'FABRICATION INDUSTRIELLE DE PRODUITS EN ARGILE NON REFRACTAIRE (BRIQUETERIE, TUILERIE INDUSTRIELLE)'),
(269, '109108', NULL, NULL, 'FABRICATION INDUSTRIELLE DE PRODUITS REFRACTAIRES ET CALORIFUGES'),
(270, '109109', NULL, NULL, 'FABRICATION INDUSTRIELLE DE PRODUITS CERAMIQUES AUTRES QUE SANITAIRES POUR L\'INDUSTRIE ET LE BATIMENT'),
(271, '109110', NULL, NULL, 'FABRICATION INDUSTRIELLE DE PRODUITS SANITAIRES EN CERAMIQUE'),
(272, '109111', NULL, NULL, 'FABRICATION DE MATERIAUX DE CONSTRUCTION EN PLASTIQUE'),
(273, '109112', NULL, NULL, 'FABRICATION D\'ARTICLES SANITAIRES EN PLASTIQUE'),
(274, '109113', NULL, NULL, 'FABRICATION INDUSTRIELLE, FACONNAGE ET TRANSFORMATION DU VERRE PLAT ET MIROITERIE'),
(275, '109114', NULL, NULL, 'FABRICATION INDUSTRIELLE, FACONNAGE ET TRANSFORMATION DU VERRE CREUXMECANIQUE ET DU VERRE TECHNIQUE'),
(276, '109115', NULL, NULL, 'FABRICATION DE CABINES \" SAHARIENNES \" ET AUTRES PREFABRICATIONS METALLIQUES'),
(277, '109116', NULL, NULL, 'CONSTRUCTION DE MAISONS ET CHALETS PREFABRIQUES, EN BOIS'),
(278, '109201', NULL, NULL, 'ENTREPRISE DE TRAVAUX DE BATIMENT TOUT CORPS D\'ETAT'),
(279, '109202', NULL, NULL, 'ENTREPRISE INDUSTRIELLE DE MENUISERIE DU BATIMENT'),
(280, '109203', NULL, NULL, 'ENTREPRISE DE TRAVAUX D\'ETANCHEITE DU BATIMENT ET DE PLOMBERIE'),
(281, '109204', NULL, NULL, 'ENTREPRISE DE PEINTURE DU BATIMENT'),
(282, '109205', NULL, NULL, 'ENTREPRISE INDUSTRIELLE DE PRODUCTION D\'ENSEMBLES DE CONSTRUCTION METALLIQUES'),
(283, '109206', NULL, NULL, 'ENTREPRISE DE TRAVAUX D\'ELECTRICITE'),
(284, '109207', NULL, NULL, 'ENTREPRISE D\'INSTALLATION DE SYSTEMES DE CLIMATISATION ET DE REFRIGERATION'),
(285, '109208', NULL, NULL, 'ENTREPRISE DE PROTECTION CONTRE LES INCENDIES ET LE VOL'),
(286, '109209', NULL, NULL, 'ENTREPRISE DE GRANDS TRAVAUX PUBLICS ET HYDRAULIQUES'),
(287, '109210', NULL, NULL, 'ENTREPRISE DE TERRASSEMENTS ET TRAVAUX RURAUX'),
(288, '109211', NULL, NULL, 'ENTREPRISE DE TRAVAUX DE ROUTES ET D\'AERODROMES'),
(289, '109212', NULL, NULL, 'ENTREPRISE DE TRAVAUX DE VOIES FERREES'),
(290, '109213', NULL, NULL, 'ENTREPRISE DE TRAVAUX URBAINS ET D\'HYGIENE PUBLIQUE'),
(291, '109214', NULL, NULL, 'ENTREPRISE D\'INSTALLATION DE RESEAUX ET DE CENTRALES ELECTRIQUES ET TELEPHONIQUES'),
(292, '109215', NULL, NULL, 'ENTREPRISE DE POSE DE CANALISATIONS A GRANDE DISTANCE'),
(293, '109216', NULL, NULL, 'INSTALLATIONS THERMIQUES INDUSTRIELLES (ENTREPRISE DE)'),
(294, '109217', NULL, NULL, 'ENTRERPISE DE TRAVAUX DE MAINTENANCE ET D\'EXPERTISE D\'OUVRAGES D\'ART'),
(295, '109218', NULL, NULL, 'ENTREPRISE DE PROMOTION IMMOBILIERE'),
(296, '109219', NULL, NULL, 'ENTREPRISE D\'ETUDES ET DE REALISATION EN GENIE CIVIL'),
(297, '109220', NULL, NULL, 'ENTREPRISE DE RESTAURATION DE BATIMENT'),
(298, '109221', NULL, NULL, 'ENTREPRISE D\'ETUDES ET DE REALISATIONS D\'OUVRAGES ELECTRIQUES ET GAZ'),
(299, '109222', NULL, NULL, 'ENTREPRISE D\'ETUDES ET DE REALISATION DE TOUTES BRANCHES D\'ACTIVITES DU B.T.P.H'),
(300, '109223', NULL, NULL, 'ENTREPRISE DE CONSTRUCTION ET D\'AMENAGEMENT D\'INFRASTRUCTURES DIVERSES'),
(301, '110101', NULL, NULL, 'FABRICATION INDUSTRIELLE DE SERRURERIE ET QUINCAILLERIE'),
(302, '110102', NULL, NULL, 'FABRICATION INDUSTRIELLE D\'ARTICLES DE FERBLANTERIE ET TOLERIE'),
(303, '110103', NULL, NULL, 'FABRICATION INDUSTRIELLE D\'ARTICLES DE MENAGE ET SIMILAIRES'),
(304, '110104', NULL, NULL, 'FABRICATION INDUSTRIELLE D\'ARTICLES DE COUTELLERIE'),
(305, '110105', NULL, NULL, 'FABRICATION DE MOBILIER METALLIQUE'),
(306, '110106', NULL, NULL, 'FABRICATION INDUSTRIELLE DE VAISSELLE EN POTERIE FINE, EN CERAMIQUE OU EN PORCELAINE'),
(307, '110107', NULL, NULL, 'FABRICATION INDUSTRIELLE DE TOUTES BROSSES DESTINEES A TOUS USAGES ET DE TOUS ARTICLES DE NETTOYAGE ET D\'ENTRETIEN EN TOUTES MATIERES'),
(308, '110108', NULL, NULL, 'TRANSFORMATION DE LA FRIPERIE'),
(309, '110109', NULL, NULL, 'TRANSFORMATION INDUSTRIELLE DE TOUTES MATIERES DESTINEES AU NETTOYAGE ET A L\'ENTRETIEN'),
(310, '110201', NULL, NULL, 'SCIERIE INDUSTRIELLE'),
(311, '110202', NULL, NULL, 'ENTREPRISE INDUSTRIELLE DE TRANSFORMATION DU BOIS'),
(312, '110203', NULL, NULL, 'FABRICATION INDUSTRIELLE D\'ENSEMBLES D\'AMEUBLEMENT EN BOIS A USAGE DOMESTIQUE OU HOTELIER'),
(313, '110204', NULL, NULL, 'FABRICATION DE MOBILIER EN BOIS POUR COLLECTIVITES'),
(314, '110205', NULL, NULL, 'ENTREPRISE INDUSTRIELLE DE FABRICATION DE LITERIE'),
(315, '110206', NULL, NULL, 'FABRICATION D\'ARTICLES D\'AMEUBLEMENT EN PLASTIQUE'),
(316, '110207', NULL, NULL, 'ENTREPRISE INDUSTRIELLE DE TRANSFORMATION DU LIEGE ET PRODUITS A BASE DE LIEGE'),
(317, '110208', NULL, NULL, 'FABRICATION DE LUMINAIRE'),
(318, '110301', NULL, NULL, 'FABRICATION DE MOBILIERS EN TOUTES MATIERES'),
(319, '111101', NULL, NULL, 'PRODUCTION ET DISTRIBUTION DES EAUX'),
(320, '111201', NULL, NULL, 'FABRICATION DE JEUX ET JOUETS'),
(321, '111202', NULL, NULL, 'FABRICATION INDUSTRIELLE D\'ARTICLES DE SPORT ET CAMPEMENT N.D.A.'),
(322, '111203', NULL, NULL, 'FABRICATION INDUSTRIELLE D\'INSTRUMENTS MODERNES DE MUSIQUE'),
(323, '111204', NULL, NULL, 'FABRICATION INDUSTRIELLE DE DISQUES, BANDES ET CASSETTES MAGNETIQUES'),
(324, '111205', NULL, NULL, 'FABRICATION D\'ARTICLES POUR FUMEURS, ET ARTICLES DE BIMBELOTERIE EN METAL'),
(325, '111206', NULL, NULL, 'FABRICATION INDUSTRIELLE D\'ARTICLES DE DECORATION'),
(326, '111301', NULL, NULL, 'INDUSTRIE DE L\'AMIANTE'),
(327, '111302', NULL, NULL, 'TRANSFORMATION INDUSTRIELLE DU PAPIER ET FABRICATION D\'ARTICLES DIVERS EN PAPIER'),
(328, '111303', NULL, NULL, 'FABRICATION DE VOITURES D\'ENFANTSET ARTICLES DE PUERICULTURE'),
(329, '111304', NULL, NULL, 'FABRICATION INDUSTRIELLE D\'ARTICLES ET FOURNITURES SCOLAIRES ET DE BUREAU'),
(330, '111305', NULL, NULL, 'FABRICATION D\'ARTICLES EN PLASTIQUE N.D.A.'),
(331, '111307', NULL, NULL, 'IMPRIMERIE INDUSTRIELLE'),
(332, '111308', NULL, NULL, 'CONSTRUCTION DE BATEAUX DE PLAISANCE'),
(333, '111309', NULL, NULL, 'FABRICATION DE TOUT CORPS CREUX EN PLASTIQUE ET EN TOUT TYPE DE RESINE'),
(334, '111310', NULL, NULL, 'FABRICATION D\'EMBALLAGES EN PLASTIQUE'),
(335, '111311', NULL, NULL, 'FABRICATION DE TOUS TYPES D\'EMBALLAGES EN PAPIER'),
(336, '111312', NULL, NULL, 'FABRICATION D\'EMBALLAGES EN TOUTES MATIERES'),
(337, '112001', NULL, NULL, 'RECUPERATION DES MATIERES METALLIQUES RECYCLABLES'),
(338, '112002', NULL, NULL, 'RECUPERATION DES MATIERES NON METALLIQUES RECYCLABLES'),
(339, '112003', NULL, NULL, 'FABRICATION DE DIVERS PRODUITS DE LA CHIMIE MINERALE'),
(340, '112004', NULL, NULL, 'MARBRERIE INDUSTRIELLE'),
(341, '112005', NULL, NULL, 'FABRICATION D\'EQUIPEMENTS ET MATERIELS POUR HOTEL, CAFES ET RESTAURANT, BLANCHISSERIES ET TEINTURERIES'),
(342, '201101', NULL, NULL, 'ENTREPRISE ARTISANALE DE POTERIE- FAIENCERIE'),
(343, '201102', NULL, NULL, 'ENTREPRISE ARTISANALE DE CERAMIQUE'),
(344, '201103', NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION DE VAISSELLE ET ARTICLES DE MENAGE EN PORCELAINE'),
(345, '201201', NULL, NULL, 'ENTREPRISE ARTISANALE DE VERRERIE'),
(346, '201202', NULL, NULL, 'ENTREPRISE ARTISANALE DE MARBRERIE'),
(347, '201301', NULL, NULL, 'ENTREPRISE ARTISANALE DE FERRONNERIE'),
(348, '201302', NULL, NULL, 'ENTREPRISE ARTISANALE DE DINANDERIE'),
(349, '201303', NULL, NULL, 'ENTREPRISE ARTISANALE DE BIJOUTERIE'),
(350, '201304', NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTIONS LAPIDAIRES'),
(351, '201401', NULL, NULL, 'ENTREPRISE ARTISANALE D\'EBENISTERIE'),
(352, '201402', NULL, NULL, 'ENTREPRISE ARTISANALE DE SPARTERIE ET VANNERIE'),
(353, '201501', NULL, NULL, 'ENTREPRISE ARTISANALE DE FILATURE DE LAINE'),
(354, '201502', NULL, NULL, 'ENTREPRISE ARTISANALE DE TISSAGE'),
(355, '201503', NULL, NULL, 'ENTREPRISE ARTISANALE DE FABRICATION DE TAPIS'),
(356, '201601', NULL, NULL, 'ENTREPRISE ARTISANALE DE MAROQUINERIE'),
(357, '202101', NULL, NULL, 'ENTREPRISE ARTISANALE D\'EXPLOITATION DE CARRIERES (PIERRES, GYPSE, PIERRE A CHAUX )'),
(358, '202102', NULL, NULL, 'ENTREPRISE ARTISANALE D\'EXPLOITATION DE L\'ARGILE'),
(359, '202103', NULL, NULL, 'ENTREPRISE ARTISANALE D\'EXTRACTIONET DE PREPARATION DU SEL'),
(360, '202201', NULL, NULL, 'ENTREPRISE ARTISANALE DE MODELAGE MECANIQUE'),
(361, '202202', NULL, NULL, 'ENTREPRISE ARTISANALE DE FABRICATION D\'ACCESSOIRES ET PIECES DETACHEES POUR AUTOMOBILES'),
(362, '202203', NULL, NULL, 'ENTREPRISE ARTISANALE DE FABRICATION DE PIECES DETACHEES ET ACCESSOIRES POUR CYCLES ET MOTOCYCLES'),
(363, '202204', NULL, NULL, 'ENTREPRISE ARTISANALE DE FABRICATION DE ROULEMENTS EN TOUTES MATIERES'),
(364, '202206', NULL, NULL, 'ENTREPRISE ARTISANALE DE GARNISSAGE ET REPARATION DE SELLERIE AUTOMOBILE ET VEHICULE'),
(365, '202301', NULL, NULL, 'ENTREPRISE ARTISANALE DE FONDERIE ET MOULAGE'),
(366, '202302', NULL, NULL, 'ENTREPRISE ARTISANALE DE CHAUDRONNERIE'),
(367, '202303', NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION DE CHARPENTES METALLIQUES'),
(368, '202304', NULL, NULL, 'ENTREPRISE ARTISANALE DE BOULONNERIE - VISSERIE'),
(369, '202305', NULL, NULL, 'ENTREPRISE ARTISANALE DE FERRONNERIE ET MENUISERIE METALLIQUE'),
(370, '202306', NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION DE PETITS ARTICLES METALLIQUES DIVERS'),
(371, '202307', NULL, NULL, 'PRODUCTION ARTISANALE DE MOBILIER METALLIQUE'),
(372, '202308', NULL, NULL, 'PRODUCTION ARTISANALE D\'USTENSILES DE CUISINE EN METAL'),
(373, '202309', NULL, NULL, 'PRODUCTION ARTISANALE DE MEDAILLESET ARTICLES DE FANTAISIE'),
(374, '202310', NULL, NULL, 'PRODUCTION ARTISANALE D\'ARTICLES DE FERBLANTERIE'),
(375, '202311', NULL, NULL, 'PRODUCTION ARTISANALE D\'ARTICLES DE COUTELLERIE'),
(376, '202401', NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION DES HUILES D\'OLIVES'),
(377, '202402', NULL, NULL, 'ENTREPRISE ARTISANALE DE MEUNERIE'),
(378, '202403', NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION D\'ALIMENTS DE BETAIL'),
(379, '202404', NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION DE CONFISERIE ET CHOCOLATERIE'),
(380, '202405', NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION DES ESSENCES ET AROMES'),
(381, '202406', NULL, NULL, 'ENTREPRISE ARTISANALE DE TORREFACTION ET MOUTURE'),
(382, '202407', NULL, NULL, 'BOULANGERIE PATISSERIE ARTISANALE'),
(383, '202408', NULL, NULL, 'BOULANGERIE (AUTRE QU\'INDUSTRIELLE)'),
(384, '202409', NULL, NULL, 'DISTILLERIE ARTISANALE'),
(385, '202410', NULL, NULL, 'PRODUCTION ARTISANALE DE CONDIMENTS DIVERS, EPICES ET SELS'),
(386, '202411', NULL, NULL, 'FABRICATION ARTISANALE DE GLACES ET CORNETS'),
(387, '202501', NULL, NULL, 'ENTREPRISE ARTISANALE DE BONNETERIE'),
(388, '202502', NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION D\'ARTICLES D\'ORNEMENT POUR L\'HABILLEMENT ET L\'AMEUBLEMENT'),
(389, '202503', NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION ET CONFECTION DE FILETS NOUES'),
(390, '202504', NULL, NULL, 'ENTREPRISE ARTISANALE DE CONFECTION DE VETEMENTS'),
(391, '202505', NULL, NULL, 'ENTREPRISE ARTISANALE DE TANNERIE'),
(392, '202506', NULL, NULL, 'ENTREPRISE ARTISANALE DE BRODERIE'),
(393, '202507', NULL, NULL, 'FABRICATION ARTISANALE D\'ARTICLES EN TOILE ET BACHE'),
(394, '202508', NULL, NULL, 'ENTREPRISE ARTISANALE D\'ARTICLES DE PELLETERIE'),
(395, '202509', NULL, NULL, 'FABRICATION ARTISANALE DE CHAPELLERIE'),
(396, '202510', NULL, NULL, 'FABRICATION ARTISANALE DE CHAUSSURES ET COMPOSANTS DE LACHAUSSURE'),
(397, '202511', NULL, NULL, 'FABRICATION D\'ARTICLES D\'HARNACHEMENT ET DE SELLERIE'),
(398, '202512', NULL, NULL, 'FABRICATION ARTISANALE D\'ARTICLES DIVERS LIEES AUX CEREMONIES'),
(399, '202601', NULL, NULL, 'ENTREPRISE ARTISANALE DE CHARPENTERIE DE MARINE'),
(400, '202602', NULL, NULL, 'ENTREPRISE ARTISANALE DE SCIAGE DU BOIS (SCIERIE)'),
(401, '202603', NULL, NULL, 'ENTREPRISE ARTISANALE DE MENUISERIE'),
(402, '202604', NULL, NULL, 'ENTREPRISE ARTISANALE DE FABRICATION D\'EMBALLAGES EN BOIS'),
(403, '202605', NULL, NULL, 'ENTREPRISE ARTISANALE DE MENUISERIE GENERALE'),
(404, '202606', NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION DE LITERIE'),
(405, '202607', NULL, NULL, 'ENTREPRISE ARTISANALE DE BROSSERIE'),
(406, '202608', NULL, NULL, 'TRAVAIL ARTISANAL DU LIEGE'),
(407, '202609', NULL, NULL, 'ENTREPRISE ARTISANALE DE FABRICATION D\'ARTICLES DE DECORATION'),
(408, '202701', NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION DE QUINCAILLERIE DE BATIMENT'),
(409, '202702', NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION DE PRODUITS EN CIMENT'),
(410, '202703', NULL, NULL, 'ENTREPRISE ARTISANALE DE SERRURERIE ET CLES MINUTE'),
(411, '202801', NULL, NULL, 'ENTREPRISE ARTISANALE DE PRODUCTION ET DE CONDITIONNEMENT DE PRODUITS DE BLANCHIMENT ET D\'ENTRETIEN'),
(412, '202802', NULL, NULL, 'ENTREPRISE ARTISANALE DE MIROITERIE'),
(413, '202803', NULL, NULL, 'ENTREPRISE ARTISANALE DE FABRICATION D\'ARTICLES DIVERS EN PAPIER'),
(414, '202804', NULL, NULL, 'ENTREPRISE ARTISANALE DE FABRICATION DE JEUX ET JOUETS'),
(415, '202805', NULL, NULL, 'ENTREPRISE ARTISANALE DE FABRICATION D\'ARTICLES ET ACCESSOIRES DE PECHE'),
(416, '202806', NULL, NULL, 'ENTREPRISE ARTISANALE DE SERIGRAPHIE'),
(417, '301101', NULL, NULL, 'COMMERCE DE GROS DE BOISSONS NON ALCOOLISEES'),
(418, '301102', NULL, NULL, 'COMMERCE DE GROS DE BOISSONS ALCOOLISEES'),
(419, '301104', NULL, NULL, 'COMMERCE DE GROS DES LEGUMES CONDIMENTES OU EPLUCHES'),
(420, '301105', NULL, NULL, 'COMMERCE DE GROS DE LAIT, PRODUITS LAITIERS ET OEUFS'),
(421, '301106', NULL, NULL, 'COMMERCE DE GROS DE LA DATTE ET DES FRUITS CONFITS'),
(422, '301107', NULL, NULL, 'COMMERCE DE GROS DES PRODUITS DE LA BOULANGERIE ET LA PATISSERIE'),
(423, '301108', NULL, NULL, 'COMMERCE DE GROS DES LEGUMES SECS ET DES PRODUITS DE LA MINOTERIE'),
(424, '301109', NULL, NULL, 'COMMERCE DE GROS DES PRODUITS DE LA CONSERVERIE ALIMENTAIRE'),
(425, '301110', NULL, NULL, 'COMMERCE DE GROS DES HUILES ANIMALES, VEGETALES ET AUTRES CORPS GRAS'),
(426, '301111', NULL, NULL, 'COMMERCE DE GROS DE LA BISCUITERIE, CHOCOLATERIE ET CONFISERIE'),
(427, '301112', NULL, NULL, 'MANDATAIRE EN FRUITS ET LEGUMES DANS LES MARCHES DE GROS'),
(428, '301113', NULL, NULL, 'COMMERCE DE GROS DES PRODUITS LIES A L\'ALIMENTATION HUMAINE'),
(429, '301114', NULL, NULL, 'COMMERCE DE GROS DE DENREES ET PRODUITS DESTINES A L\'ALIMENTATION INFANTILE ET ARTICLES DIVERS POUR NOURRISSONS'),
(430, '301115', NULL, NULL, 'COMMERCE DE GROS DE SEL BRUT ET DE TOUS AUTRES TYPES DE SEL'),
(431, '301116', NULL, NULL, 'COMMERCE DE GROS DE PLATS PREPARES EN CONSERVE, SURGELES OU CONGELES'),
(432, '301117', NULL, NULL, 'COMMERCE DE GROS DES EPICES'),
(433, '301118', NULL, NULL, 'COMMERCE DE GROS DE FRUITS ET LEGUMES ( ACTIVITE A EXERCER DANS UN LOCAL COMMERCIAL)'),
(434, '301119', NULL, NULL, 'CENTRALE D\'ACHAT'),
(435, '301201', NULL, NULL, 'COMMERCE DE GROS DE VIANDE DE BOUCHERIE'),
(436, '301202', NULL, NULL, 'COMMERCE DE GROS DE VOLAILLES, OEUFS ET LAPINS'),
(437, '301203', NULL, NULL, 'COMMERCE DE GROS DE VIANDE CHEVALINE'),
(438, '301204', NULL, NULL, 'COMMERCE DE GROS D\'ABATS ET VIANDE PREPARES CRUS'),
(439, '301205', NULL, NULL, 'COMMERCE DE GROS DE LA CHARCUTERIE'),
(440, '301206', NULL, NULL, 'COMMERCE DE GROS DE CONSERVES A BASE DE VIANDE ET DE POISSONS'),
(441, '301301', NULL, NULL, 'COMMERCE DE GROS DE POISSONS, CRUSTACES ET COQUILLAGES'),
(442, '301401', NULL, NULL, 'COMMERCE DE GROS DE PRODUITS ALIMENTAIRES SPECIALISES'),
(443, '302101', NULL, NULL, 'COMMERCE DE GROS DE TISSUS, FILS ET FILES'),
(444, '302102', NULL, NULL, 'COMMERCE DE GROS DE LA MERCERIE ET BONNETERIE'),
(445, '302103', NULL, NULL, 'COMMERCE DE GROS D\'ARTICLES D\'HABILLEMENT ET ACCESSOIRES POUR VETEMENT'),
(446, '302104', NULL, NULL, 'COMMERCE DE GROS DE VETEMENTS USAGES ET DE FRIPERIES'),
(447, '302201', NULL, NULL, 'COMMERCE DE GROS DE PELLETERIE ET DES FOURRURES'),
(448, '302202', NULL, NULL, 'COMMERCE DE GROS DE FOURNITURES DE BOURRELLERIE'),
(449, '302203', NULL, NULL, 'COMMERCE DE GROS DE LA CHAUSSURE'),
(450, '302204', NULL, NULL, 'COMMERCE DE GROS DE LA MAROQUINERIE'),
(451, '302301', NULL, NULL, 'COMMERCE DE GROS DES ARTICLES DE L\'HORLOGERIE ET DE LA BIJOUTERIE'),
(452, '302401', NULL, NULL, 'COMMERCE DE GROS DES COSMETIQUES ET PRODUITS DE BEAUTE'),
(453, '303101', NULL, NULL, 'COMMERCE DE GROS D\'APPAREILS ELECTRONIQUES ET ELECTROMENAGERS'),
(454, '303102', NULL, NULL, 'COMMERCE DE GROS D\'APPAREILS SANITAIRES ET DE CHAUFFAGE'),
(455, '303103', NULL, NULL, 'COMMERCE DE GROS DE TAPIS, COUVERTURES ET AUTRES ARTICLES SIMILAIRES A BASE DE MATIERES TEXTILES'),
(456, '303104', NULL, NULL, 'COMMERCE DE GROS D\'ARTICLES DE VANNERIE ET DE SPARTERIE'),
(457, '303105', NULL, NULL, 'COMMERCE DE GROS DES PRODUITS DE L\'ARTISANAT ET DE LA MAROQUINERIETRADITIONNELLE'),
(458, '303106', NULL, NULL, 'COMMERCE DE GROS DE MEUBLES ET ARTICLES D\'AMEUBLEMENT A USAGE DOMESTIQUE OU DE BUREAU'),
(459, '303107', NULL, NULL, 'COMMERCE DE GROS DES ARTICLES DE DINANDERIE'),
(460, '303108', NULL, NULL, 'COMMERCE DE GROS DE LA LUSTRERIE ET ARTICLES DE DECORATION D\'INTERIEUR'),
(461, '303109', NULL, NULL, 'COMMERCE DE GROS DE TOUS MATERIELS ET EQUIPEMENTS LIES AU DOMAINE DE L\'ELECTRICITE ET L\'ELECTRONIQUE'),
(462, '303110', NULL, NULL, 'COMMERCE DE GROS DE TOUS APPAREILS ET MACHINES PROFESSIONNELS OU DOMESTIQUES D\'OCCASIONS'),
(463, '303111', NULL, NULL, 'COMMERCE DE GROS DE MATERIELS ET ARTICLES DE COIFFURE ET D\'ESTHETIQUE'),
(464, '303201', NULL, NULL, 'COMMERCE DE GROS DE LA VERRERIE,VAISSELLE, ARTICLES DE MENAGE ET USTENSILES DE CUISINE'),
(465, '303202', NULL, NULL, 'COMMERCE DE GROS DES PRODUITS DE LA DROGUERIE, PRODUITS D\'HYGIENE, D\'ENTRETIEN DOMESTIQUE, PROFESSIONNEL ET AUTRES PRODUITS SIMILAIRES'),
(466, '304101', NULL, NULL, 'COMMERCE DE GROS DE MATERIAUX DE CONSTRUCTION, CERAMIQUE SANITAIRE VERRE PLAT'),
(467, '304102', NULL, NULL, 'COMMERCE DE GROS DE QUINCAILLERIE ET FOURNITURES POUR PLOMBERIE ET CHAUFFAGE'),
(468, '304103', NULL, NULL, 'COMMERCE DE GROS DE LA VITRERIE ET DE LA MIROITERIE'),
(469, '304104', NULL, NULL, 'COMMERCE DE GROS DU BOIS, DU LIEGE ET PRODUITS DERIVES'),
(470, '304105', NULL, NULL, 'COMMERCE DE GROS DE PEINTURES ET VERNIS'),
(471, '304106', NULL, NULL, 'COMMERCE DE GROS DE PRODUITS D\'ETANCHIETE'),
(472, '304201', NULL, NULL, 'COMMERCE DE GROS DES FOURNITURES POUR L\'ELECTRICITE'),
(473, '304202', NULL, NULL, 'COMMERCE DE GROS D\'ARTICLES DE REVETEMENT D\'INTERIEUR'),
(474, '305001', NULL, NULL, 'COMMERCE DE GROS DE MATERIELS INFORMATIQUE ET DE MACHINES DE BUREAU, LEURS ACCESSOIRES ET FOURNITURES'),
(475, '305002', NULL, NULL, 'COMMERCE DE GROS DES EQUIPEMENTS ET MATERIELS THERMIQUES ET FRIGORIFIQUES'),
(476, '305003', NULL, NULL, 'COMMERCE DE GROS D\'EQUIPEMENTS, MATERIEL ET FOURNITURES DE SECURITE ET DE PROTECTION'),
(477, '305004', NULL, NULL, 'COMMERCE DE GROS DES EQUIPEMENTS DE CUISINE, DE BOULANGERIE ET EQUIPEMENTS DE MAGASINS'),
(478, '305005', NULL, NULL, 'COMMERCE DE GROS DES EQUIPEMENTS DE TRANSPORTS, DE MANUTENTION ET DE MACHINES UTILISES DANS L\'INDUSTRIE'),
(479, '305006', NULL, NULL, 'COMMERCE DE GROS DES EQUIPEMENTS ET MATERIELS POUR LA BLANCHISSERIE, EQUIPEMENTS POUR HOTELS, CAFES ET RESTAURANTS'),
(480, '305007', NULL, NULL, 'COMMERCE DE GROS D\'ARTICLES D\'EMBALLAGE'),
(481, '305008', NULL, NULL, 'COMMERCE DE GROS DES INSTRUMENTS ET APPAREILS DE MESURE ET DE PESAGE'),
(482, '305009', NULL, NULL, 'COMMERCE DE GROS DE MACHINES A TRICOTER ET A COUDRE'),
(483, '305010', NULL, NULL, 'COMMERCE DE GROS DE TOUS MATERIELS ET PRODUITS LIES AU DOMAINE DE LA TELLECOMMUNICATION, RADIOCOMMUNICATION ET LA TELEPHONIE, LEURS ACCESSOIRES ET PIECES DETACHEES .'),
(484, '305011', NULL, NULL, 'COMMERCE DE GROS DE MACHINES ET MATERIELS POUR BTP'),
(485, '305012', NULL, NULL, 'COMMERCE DE GROS D\'APPAREILS MEDICAUX-CHIRURGICAUX'),
(486, '305013', NULL, NULL, 'COMMERCE DE GROS D\'APPAREILS ET MACHINES MANUELS, MECANIQUES DESTINES A TOUS USAGES'),
(487, '305014', NULL, NULL, 'COMMERCE DE GROS DE VEHICULES (CONCESSIONNAIRE AUTOMOBILE)'),
(488, '305015', NULL, NULL, 'COMMERCE DE GROS DE PRODUITS CHIMIQUES'),
(489, '305016', NULL, NULL, 'COMMERCE DE GROS D\'APPAREILS, ARTICLES ET PRODUITS DE FILTRATION OU D\'EPURATION DES LIQUIDES ET DES GAZ'),
(490, '305017', NULL, NULL, 'COMMERCE DE GROS D\'APPAREILS EQUIPEMENTS DESTINES A TOUS TRAVAUX DE LABORATOIRE'),
(491, '306001', NULL, NULL, 'COMMERCE DE GROS DE PIECES DETACHEES ET ACCESSOIRES POUR VEHICULES AUTOMOBILES'),
(492, '306002', NULL, NULL, 'COMMERCE DE GROS DE MATERIELS ET PIECES POUR L\'ENTRETIEN ET LES REPARATIONS MECANIQUES'),
(493, '306003', NULL, NULL, 'COMMERCE DE GROS DE CYCLES ET MOTOCYCLES , PIECES DETACHEES ET ACCESSOIRES POUR CYCLES ET MOTOCYCLES'),
(494, '306004', NULL, NULL, 'COMMERCE DE GROS DE PNEUMATIQUES'),
(495, '306005', NULL, NULL, 'COMMERCE DE GROS D\'OUTILS ET OUTILLAGES'),
(496, '306101', NULL, NULL, 'COMMERCE DE GROS DE PRODUITS ET ARTICLES DESTINES A LA FABRICATION DES CHAUSSURES'),
(497, '306103', NULL, NULL, 'COMMERCE DE GROS DARTICLES EN CAOUTCHOUC ET EN PLASTIQUE DESTINES A TOUS USAGES'),
(498, '307101', NULL, NULL, 'COMMERCE DE GROS DE FOURNITURES ET ARTICLES DE SPORT ET AUTRES LOISIRS'),
(499, '307102', NULL, NULL, 'COMMERCE DE GROS DE BARQUES ET AUTRES EMBARCATIONS'),
(500, '307103', NULL, NULL, 'COMMERCE DE GROS DE JOUETS ET JEUX DE SOCIETE'),
(501, '307104', NULL, NULL, 'COMMERCE GROS DES ARMES DE CHASSE'),
(502, '307201', NULL, NULL, 'COMMERCE DE GROS DE LA LIBRAIRIE, PAPETERIE, DE FOURNITURES ET ARTICLES POUR LES ACTIVITES DES BEAUX ARTS'),
(503, '307202', NULL, NULL, 'COMMERCE DE GROS DES LIVRES D\'OCCASION'),
(504, '307203', NULL, NULL, 'COMMERCE DE GROS DE MATERIELS, ARTICLES ET FOURNITURES POUR L\'ENSEIGNEMENT ET LES COLLECTIVITES'),
(505, '307204', NULL, NULL, 'COMMERCE DE GROS DE TABACS MANUFACTURES ET D\'ALLUMETTES'),
(506, '307301', NULL, NULL, 'COMMERCE DE GROS DES INSTRUMENTS DE MUSIQUE, ARTICLES ET FOURNITURES LIES AUX ACTIVITES ARTISTIQUES'),
(507, '307302', NULL, NULL, 'COMMERCE DE GROS DES APPAREILS PHOTOGRAPHIQUES ET CINEMATOGRAPHIQUES'),
(508, '308001', NULL, NULL, 'COMMERCE DE GROS DES PRODUITS PHARMACEUTIQUES ET VETERINAIRES'),
(509, '308002', NULL, NULL, 'COMMERCE DE GROS DE PRODUITS PHARMACEUTIQUES'),
(510, '308003', NULL, NULL, 'COMMERCE DE GROS DE PRODUITS VETERINAIRES'),
(511, '308004', NULL, NULL, 'COMMERCE DE GROS DE PRODUITS PARAPHARMACEUTIQUES, MATERIELS ET INSTRUMENTS MEDICO - CHIRURGICAUX, LEURS ACCESSOIRES, PIECES DETACHEES ET CONSOMMABLES'),
(512, '308005', NULL, NULL, 'COMMERCE DE GROS D\'ARTICLES DE LUNETTERIE ET D\'OPTIQUE MEDICALE'),
(513, '308006', NULL, NULL, 'COMMERCE DE GROS DE PRODUITS PHYTOSANITAIRES'),
(514, '309001', NULL, NULL, 'COMMERCE DE GROS DE FLEURS ET PLANTES DE TOUS GENRES'),
(515, '309002', NULL, NULL, 'COMMERCE DE GROS DE SEMENCES, DE GRAINES, PLANTS ET AUTRES PRODUITS HORTICOLES'),
(516, '309003', NULL, NULL, 'COMMERCE DE GROS DES ENGRAIS ET PRODUITS PHYTOSANITAIRES'),
(517, '309004', NULL, NULL, 'COMMERCE DE GROS DES CEREALES ET ALIMENTS DU BETAIL'),
(518, '309005', NULL, NULL, 'COMMERCE DE GROS DES HUILES ET DES GRAISSES NON COMBUSTIBLES'),
(519, '309006', NULL, NULL, 'COMMERCE DE GROS D\'ANIMAUX VIVANTS'),
(520, '309007', NULL, NULL, 'COMMERCE DE GROS DE CUIRS ET PEAUX'),
(521, '309008', NULL, NULL, 'COMMERCE DE GROS D\'EQUIPEMENTS, MATERIELS ET MACHINES AGRICOLES'),
(522, '309009', NULL, NULL, 'COMMERCE DE GROS DE MATERIELS HYDRAULIQUES DESTINES A L\'AGRICULTURE'),
(523, '310001', NULL, NULL, 'COMMERCE DE GROS DES COMBUSTIBLES SOLIDES, LIQUIDES ET GAZEUX'),
(524, '310002', NULL, NULL, 'COMMERCE DE GROS DE LUBRIFIANTS ET GRAISSES INDUSTRIELLES'),
(525, '310003', NULL, NULL, 'COMMERCE DE GROS DU GAZ BUTANE, PROPANE ET DU GAZ INDUSTRIEL'),
(526, '310004', NULL, NULL, 'COMMERCE DE GROS DES METAUX'),
(527, '310005', NULL, NULL, 'COMMERCE DE GROS DES VIEUX FERS, METAUX, TESSONS, DEBRIS DE VERRE ET BROCANTERIE'),
(528, '310006', NULL, NULL, 'COMMERCE DE GROS DES DECHETS INDUSTRIELS DIVERS ET AUTRES PRODUITS RECUPERES N.D.A'),
(529, '310007', NULL, NULL, 'COMMERCE DE GROS DESEQUIPEMENTS, MATERIELS, ET PRODUITS LIES AU DOMAINE DES HYDROCARBURES'),
(530, '401101', NULL, NULL, 'IMPORT - EXPORT DES PRODUITS , EQUIPEMENTS ET MATERIELS ET PRODUITS LIES AU DOMAINE DE L\' AGRICULTURE, LEURS PIECES DETACHEES ET ACCESSOIRES'),
(531, '401102', NULL, NULL, 'IMPORT - EXPORT DE MATERIELS ET PRODUITS DESTINES A L\'AVICULTURE'),
(532, '401201', NULL, NULL, 'IMPORTATION DES MATERIELS ET PRODUITS LIES AU DOMAINE DE LA SYLVICULTURE'),
(533, '401301', NULL, NULL, 'IMPORT - EXPORT DES PRODUITS ET MATERIELS LIE AU SECTEUR DE LA PECHE ET DE L\'AQUACULTURE'),
(534, '402001', NULL, NULL, 'IMPORT - EXPORT DES PRODUITS LIES A L\'ALIMENTATION HUMAINE'),
(535, '402002', NULL, NULL, 'IMPORTATION DE DENREES ET PRODUITS DESTINES A L\'ALIMENTATION INFANTILE ET ARTICLES DIVERS POUR NOURISSONS'),
(536, '402101', NULL, NULL, 'IMPORT - EXPORT DE BOISSONS NON ALCOOLISEES'),
(537, '402102', NULL, NULL, 'IMPORT - EXPORT DE BOISSONS ALCOOLISEES'),
(538, '402103', NULL, NULL, 'IMPORT - EXPORT DE FRUITS ET LEGUMES'),
(539, '402104', NULL, NULL, 'IMPORT - EXPORT DES LEGUMES CONDIMENTES OU EPLUCHES'),
(540, '402105', NULL, NULL, 'IMPORT - EXPORT DE LAIT, PRODUITS LAITIERS ET OEUFS'),
(541, '402106', NULL, NULL, 'IMPORT - EXPORT DE SUCRE, THE, CAFE, CACAO ET EPICES'),
(542, '402107', NULL, NULL, 'IMPORT - EXPORT DE LA DATTE ET DES FRUITS SECHES OU CONFITS'),
(543, '402108', NULL, NULL, 'IMPORT - EXPORT DES PRODUITS POUR LA BOULANGERIE'),
(544, '402109', NULL, NULL, 'IMPORT - EXPORT DE LEGUMES SECS ET DES PRODUITS DE LA MINOTERIE'),
(545, '402110', NULL, NULL, 'IMPORT - EXPORT DES PRODUITS DE LA CONSERVERIE ALIMENTAIRE'),
(546, '402111', NULL, NULL, 'IMPORT - EXPORT DES HUILES ANIMALES, VEGETALES ET AUTRES CORPS GRAS'),
(547, '402112', NULL, NULL, 'IMPORT - EXPORT DE BISUITERIE, CHOCOLATERIE ET CONFISERIE'),
(548, '402113', NULL, NULL, 'IMPORT - EXPORT DE MATEIRES PREMIERES ET PRODUITS DESTINES A L\'INDUSTRIE AGRO-ALIMENTAIRE'),
(549, '402114', NULL, NULL, 'IMPORT - EXPORT D\'ARTICLES ET FOURNITURES DIVERS DE LA PUERICULTURE'),
(550, '402115', NULL, NULL, 'IMPORT - EXPORT DE DENREES ET PRODUITS DESTINES A L\'ALIMENTATION INFANTILE ET ARTICLES DIVERS DE LA PUERICULTURE'),
(551, '402201', NULL, NULL, 'IMPORT - EXPORT DE VIANDE DE BOUCHERIE'),
(552, '402202', NULL, NULL, 'IMPORT - EXPORT DE VOLAILLES, OEUFS ET LAPINS'),
(553, '402203', NULL, NULL, 'IMPORT - EXPORT DE VIANDE CHEVALINE'),
(554, '402204', NULL, NULL, 'IMPORT - EXPORT D\'ABATS ET VIANDE PREPARES'),
(555, '402205', NULL, NULL, 'IMPORT - EXPORT DE LA CHARCUTERIE'),
(556, '402206', NULL, NULL, 'IMPORT - EXPORT DE CONSERVES A BASE DE VIANDE ET DE POISSONS'),
(557, '402301', NULL, NULL, 'IMPORT - EXPORT DE PRODUITS DE LA MER'),
(558, '402401', NULL, NULL, 'IMPORT - EXPORT DE PRODUITS ALIMENTAIRES SPECIFIQUES'),
(559, '402501', NULL, NULL, 'IMPORT - EXPORT DE TABACS BRUTS OU MANUFACTURES ET ALLUMETTES (activité réservée à la S.N.T.A )'),
(560, '402502', NULL, NULL, 'IMPORT - EXPORT DARTICLES POUR FUMEURS'),
(561, '403101', NULL, NULL, 'IMPORTATION DES EQUIPEMENTS, MATERIELS ET PRODUITS LIES AU DOMAINE DES HYDROCARBURES'),
(562, '403102', NULL, NULL, 'IMPORT - EXPORT DE COMBUSTIBLES SOLIDES LIQUIDES ET GAZEUX'),
(563, '403103', NULL, NULL, 'IMPORT - EXPORT DE LUBRIFIANTS, BITUMES ET SOLVANTS'),
(564, '403201', NULL, NULL, 'IMPORTATION DES MATERIELS ET PRODUITS LIES AU DOMAINE DE L\'ENERGIE'),
(565, '403301', NULL, NULL, 'IMPORTATION DES MATERIELS ET PRODUITS LIES AU DOMAINE HYDRAULIQUE'),
(566, '404001', NULL, NULL, 'IMPORTATION DES EQUIPEMENTS, MATERIELS ET PRODUITS LIES AU DOMAINE DES MINES'),
(567, '404002', NULL, NULL, 'IMPORT - EXPORT DE METAUX ET PRODUITS METALLIQUES ET SIDERURGIQUES'),
(568, '404003', NULL, NULL, 'IMPORT - EXPORT DES VIEUX FERS METAUX, TESSONS, DEBRIS DE VERRE ET BROCANTERIE'),
(569, '405101', NULL, NULL, 'IMPORTATION DES MATERIELS ET PRODUITS LIES AU DOMAINE DES TEXTILES'),
(570, '405102', NULL, NULL, 'IMPORT - EXPORT D\'ARTICLES D\'HABILLEMENT USAGES ET DE TOUTES FRIPERIES'),
(571, '405103', NULL, NULL, 'IMPORT - EXPORT DE TISSUS, FILS ET FILES'),
(572, '405104', NULL, NULL, 'IMPORT - EXPORT DE LA MERCERIE ET BONNETERIE'),
(573, '405105', NULL, NULL, 'IMPORT - EXPORT D\'ARTICLES D\'HABILLEMENT ET ACCESSOIRES POUR VETEMENTS'),
(574, '405106', NULL, NULL, 'IMPORT - EXPORT DE TAPIS COUVERTURES ET AUTRES ARTICLES SIMILAIRES A BASE DE MATIERES TEXTILE OU SYNTHETIQUE'),
(575, '405107', NULL, NULL, 'IMPORT - EXPORT D\'AUTRES ARTICLES CONFECTIONNES EN TEXTILES');
INSERT INTO `t_02_03_01` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`) VALUES
(576, '405108', NULL, NULL, 'IMPORT - EXPORT DE BIJOUTERIE DE FANTAISIE'),
(577, '405201', NULL, NULL, 'IMPORT - EXPORT DE MATERIELS ET PRODUITS LIES AU DOMAINE DES CUIRS'),
(578, '405202', NULL, NULL, 'IMPORT - EXPORT DE PEAUX, DE CUIRS, DE PELLETERIE ET LEURS ACCESSOIRES'),
(579, '405203', NULL, NULL, 'IMPORT - EXPORT DARTICLES DE BOURRELLERIE'),
(580, '405204', NULL, NULL, 'IMPORT - EXPORT D\'ARTICLES DE MAROQUINERIE'),
(581, '405205', NULL, NULL, 'IMPORT - EXPORT DE LA CHAUSSURE'),
(582, '405206', NULL, NULL, 'IMPORT - EXPORT DES PRODUITS ET ARTICLES DESTINEES A LA FABRICATION DE CHAUSSURES'),
(583, '405207', NULL, NULL, 'IMPORT - EXPORT DES PRODUITS ET ARTICLES DESTINES A LA FABRICATION DE LA MAROQUINERIE'),
(584, '405301', NULL, NULL, 'IMPORT - EXPORT DE MATERIELS, PRODUITS ET MATIERES PREMIERES DESTINES A LA BIJOUTERIE INDUSTRIELLE'),
(585, '405302', NULL, NULL, 'IMPORT - EXPORT DES ARTICLES DE BIJOUTERIE'),
(586, '406101', NULL, NULL, 'IMPORTATION DES MATERIELS ET PRODUITS A USAGE INDUSTRIEL OU AGRICOLE'),
(587, '406102', NULL, NULL, 'IMPORT - EXPORT DE PRODUITS CHIMIQUES DE TOUTES NATURES ET PREPARATIONS CHIMIQUES DESTINEES A TOUS USAGES'),
(588, '406103', NULL, NULL, 'IMPORT - EXPORT DARTICLES EN CAOUTCHOUC ET EN PLASTIQUE DESTINES A TOUS USAGES'),
(589, '406104', NULL, NULL, 'IMPORT - EXPORT DE MATIERE PREMIERE N.D.A'),
(590, '406201', NULL, NULL, 'IMPORT - EXPORT DES PRODUITS ET MATERIELS PHARMACEUTIQUES ET VETERINAIRES'),
(591, '406202', NULL, NULL, 'IMPORT - EXPORT DES PRODUITS PHARMACEUTIQUES'),
(592, '406203', NULL, NULL, 'IMPORT - EXPORT DES PRODUITS VETERINAIRES'),
(593, '406204', NULL, NULL, 'IMPORT - EXPORT DES PRODUITS PARA-PHARMACEUTIQUES, MATERIELS ET INSTRUMENTS MEDICO-CHIRURGICAUX, PIECES DETACHEES ET CONSOMMABLES'),
(594, '406205', NULL, NULL, 'IMPORT - EXPORT D4APPAREILS, D\'INSTRUMENTS ET D\'ARTICLES DE LUNETTERIE ET D\'OPTIQUE MEDICALE'),
(595, '406206', NULL, NULL, 'IMPORT - EXPORT DE PLANTES MEDICINALES OU INDUSTRIELLES'),
(596, '406207', NULL, NULL, 'IMPORT - EXPORT DE PRODUITS PHYTOSANITAIRES'),
(597, '406208', NULL, NULL, 'IMPORT - EXPORT DE PRODUITS DIETETIQUES, NUTRITIONNELS ET DE REGIME'),
(598, '406209', NULL, NULL, 'IMPORT - EXPORT DE MATERIELS ET PRODUITS DESTINES AU NETTOYAGE ET DESINFECTION DES CENTRES HOSPITALIERS'),
(599, '406210', NULL, NULL, 'IMPORT - EXPORT DE MATERIELS ET PRODUITS DESTINES AU NETTOYAGE ET DESINFECTION AGRICOLES, ALIMENTAIRES ET COLLECTIVITES LOCALES'),
(600, '406301', NULL, NULL, 'IMPORT - EXPORT DES PRODUITS COSMETIQUES ET PRODUITS D\'HYGIENE CORPORELLE'),
(601, '406302', NULL, NULL, 'IMPORT - EXPORT DE MATIERES PREMIERES ET DES HUILES ESSENTIELLES DESTINEES A LA FABRICATION DES PRODUITS COSMETIQUES, DE LA PARFUMERIE ET D\'HYGIENE CORPORELLE'),
(602, '406303', NULL, NULL, 'IMPORT - EXPORT DE MATERIELS ET ARTICLES DE COIFFURE ET D\'ESTHETIQUE'),
(603, '407001', NULL, NULL, 'IMPORTATION DES EQUIPEMENTS,MATERIELS ET PRODUITS LIES AU DOMAINE DU BATIMENT ET DES TRAVAUX PUBLICS'),
(604, '407002', NULL, NULL, 'IMPORT - EXPORT DE MACHINES MATIERES ET PRODUITS POUR LA SECURITE ET LA SIGNALISATION ROUTIERE'),
(605, '407003', NULL, NULL, 'IMPORT - EXPORT DE LA VERRERIE'),
(606, '407101', NULL, NULL, 'IMPORT - EXPORT DE BOIS, LIEGE ET OUVRAGES DE SPARTERIE ET DE VANNERIE'),
(607, '408101', NULL, NULL, 'IMPORT - EXPORT DE MATERIELS, PIECES DETACHEES ET ACCESSOIRES LIES AU DOMAINE MECANIQUE'),
(608, '408102', NULL, NULL, 'IMPORT - EXPORT DE PNEUMATIQUES'),
(609, '408201', NULL, NULL, 'IMPORT - EXPORT DE MATERIELS ET PRODUITS LIES AU DOMAINE ELECTROMENAGER'),
(610, '408301', NULL, NULL, 'IMPORT - EXPORT DE MATERIELS ET PRODUITS LIES AU DOMAINE INFORMATIQUE ET BUREAUTIQUE'),
(611, '408302', NULL, NULL, 'IMPORT - EXPORT D\'APPAREILS, MATERIELS ET ARTICLES D\'ENREGISTREMENT ET DE REPRODUCTION DU SON, DES IMAGES LEURS ACCESSOIRES ET PIECES DETACHEES'),
(612, '408303', NULL, NULL, 'IMPORT - EXPORT D\'APPAREILS, ET EQUIPEMENTS DE GESTION DE LA MONETIQUE'),
(613, '408308', NULL, NULL, 'IMPORT - EXPORT DE PLANCHERS SURELEVES ET FAUX PLAFONDS DESTINES A L\'AMENAGEMENT DE CENTRES INFORMATIQUES ET TECHNIQUES'),
(614, '408309', NULL, NULL, 'IMPORT - EXPORT DE MATERIELS ET PRODUITS LIES AU DOMAINE PHOTOGRAPHIQUE ET CINEMATOGRAPHIQUE'),
(615, '408401', NULL, NULL, 'IMPORT - EXPORT D\'EQUIPEMENTS, MATERIELS ET ACCESSOIRES DE BOULANGERIE, BOUCHERIE, DE GRANDE CUISINE, COLLECTIVITES ET D\'EQUIPEMENTS DE MAGASINS'),
(616, '408402', NULL, NULL, 'IMPORT - EXPORT D\'EQUIPEMENTS ET MATERIELS POUR HOTEL, CAFES ET RESTAURANTS, BLANCHISSERIES ET TEINTURERIES'),
(617, '408403', NULL, NULL, 'IMPORT - EXPORT D\'EQUIPEMENTS ET MATERIELS THERMIQUES ET FRIGORIFIQUES'),
(618, '408404', NULL, NULL, 'IMPORT - EXPORT D\'EQUIPEMENTS ET MATERIELS UTILISES POUR LA PRODUCTION D\'EMBALLAGES ET LE CONDITIONNEMENT'),
(619, '408405', NULL, NULL, 'IMPORT - EXPORT D\'EQUIPEMENTS, D\'APPAREILS ET INSTRUMENTS DESTINES A L\'EXPERIMENTATION, LA RECHERCHE SCIENTIFIQUE, LE PESAGE- MESURAGE & ANALYSES PHYSIQUE ET CHIMIQUE, ET LEURS ACCESSOIRES'),
(620, '408406', NULL, NULL, 'IMPORT - EXPORT D\'EQUIPEMENTS ET MATERIELS D\'IMPRIMERIE, DE SERIGRAPHIE ET IMPORTATION DE PAPIER'),
(621, '408407', NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS ET EQUIPEMENTS LIES AU DOMAINE DE L\'ELECTRICITE ET L\'ELECTRONIQUE'),
(622, '408408', NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS ET PRODUITS LIES AU DOMAINE DE LA TELECOMMUNICATION, LA RADIOCOMMUNICATION ET LA TELEPHONIE'),
(623, '408409', NULL, NULL, 'IMPORT - EXPORT DE PAPIERS ET DERIVES DU PAPIER'),
(624, '408410', NULL, NULL, 'IMPORT - EXPORT DE MATIERES PREMIERES, EQUIPEMENTS, MATERIELS ET ACCESSOIRES LIES AU DOMAINE DE LA CERAMIQUE'),
(625, '408411', NULL, NULL, 'IMPORT - EXPORT DE TOLES GALVANISEES OU INOXYDABLES'),
(626, '409001', NULL, NULL, 'IMPORT - EXPORT DES MATERIELS ET PRODUITS LIES AU DOMAINE DE LA QUINCAILLERIE, DROGUERIE, ARTICLES DE MENAGE, ET TOUS ARTICLES D\'HYGIENE ET D\'ENTRETIEN DOMESTIQUE ET PROFESSIONNEL'),
(627, '409002', NULL, NULL, 'IMPORT - EXPORT DE TOUS EQUIPEMENTS, APPAREILS ET ARTICLES SANITAIRES EN CERAMIQUES ET AUTRES MATIERES ET ARTICLES DE CHAUFFAGES ET DE PLOMBERIE EN TOUTES MATIERES'),
(628, '409003', NULL, NULL, 'IMPORT - EXPORT D\'ARTICLE DE LA COUTELLERIE'),
(629, '409004', NULL, NULL, 'IMPORT - EXPORT D\'OUTILS ET OUTILLAGES MECANIQUES, ELECTROMECANIQUES, ELECTRIQUES ET ELECTRONIQUES'),
(630, '409005', NULL, NULL, 'IMPORT - EXPORT DES EQUIPEMENTS ET FOURNITURES INDUSTRIELLES ET LEURS CONSOMMABLES'),
(631, '409006', NULL, NULL, 'IMPORT - EXPORT D\'ARTICLES EN PAPIER A USAGE SANITAIRE OU DOMESTIQUE'),
(632, '409007', NULL, NULL, 'IMPORT - EXPORT DE MATERIELS ET PRODUITS DESTINES A LA PREPARATION ET A L\'APPLICATION DES PEINTURES AUTOMOBILES'),
(633, '409009', NULL, NULL, 'IMPORT - EXPORT DE STORES VENITIENS ET CALIFORNIENS'),
(634, '410101', NULL, NULL, 'IMPORT - EXPORT D\'ARTICLES ET MOBILIERS A USAGE DOMESTIQUES ET DE BUREAUX'),
(635, '410102', NULL, NULL, 'IMPORT - EXPORT DE LA MATIERE PREMIERE DESTINEE A LA FABRICATION DE MEUBLES ET ARTICLES D\'AMEUBLEMENTS'),
(636, '410103', NULL, NULL, 'IMPORT - EXPORT DE L\'ARTISANAT ET DE LA MAROQUINERIE TRADITIONNELLE'),
(637, '410104', NULL, NULL, 'IMPORT - EXPORT DES ARTICLES DE DINANDERIE'),
(638, '410105', NULL, NULL, 'IMPORT - EXPORT DE LA LUSTRERIE ET ARTICLES DE DECORATION D\'INTERIEUR'),
(639, '410201', NULL, NULL, 'IMPORT - EXPORT D\'ARTICLES DE LOISIRS ET DE SPORTS'),
(640, '410202', NULL, NULL, 'IMPORT - EXPORT DE JEUX , JOUETS ET JEUX EDUCATIFS'),
(641, '410203', NULL, NULL, 'IMPORT - EXPORT DE LIVRES , DE MANUELS, OUVRAGES ET REVUES SCIENTIFIQUES OU DE CULTURE GENERALE'),
(642, '410204', NULL, NULL, 'IMPORT - EXPORT DE LA PAPETERIE, DE FOURNITURES ET ARTICLES SCOLAIRES, ET ARTICLES POUR LES BEAUX ARTS'),
(643, '410205', NULL, NULL, 'IMPORT - EXPORT DE TABLEAUX ET D\'OEUVRES D\'ART'),
(644, '410206', NULL, NULL, 'IMPORT - EXPORT D\'ARTICLES PUBLICITAIRES ET CADEAUX D\'ENTREPRISES'),
(645, '410301', NULL, NULL, 'IMPORT - EXPORT D\'EMBALLAGES EN TOUTES MATIERES'),
(646, '410302', NULL, NULL, 'IMPORT - EXPORT D\'EQUIPEMENTS, MATERIELS ET FOURNITURES DE SECURITE ET DE PROTECTION'),
(647, '410303', NULL, NULL, 'IMPORT - EXPORT D\'APPAREILS, ARTICLES ET PRODUITS DESTINES A LA FILTRATION OU L\'EPURATION DES LIQUIDES ET DES GAZ'),
(648, '410304', NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS, MACHINES, MATIERE PREMIERE ET ACCESSOIRES DESTINES A LA TRANSFORMATION DU BOIS ET SES DERIVES'),
(649, '410305', NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS, MACHINES, MATIERES PREMIERES, PIECES DETACHEES ET ACCESSOIRES DESTINES A LA FABRICATION ET LA TRANSFORMATION DES METAUX'),
(650, '410306', NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS, MACHINES, PIECES DETACHEES ET ACCESSOIRES DESTINES A LA FABRICATION ET LA TRANSFORMATION DES PLASTIQUES ET CAOUTCHOUCS'),
(651, '410307', NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS, MACHINES, PIECES DETACHEES ET ACCESSOIRES DESTINES A L\'INDUSTRIE AGRO-ALIMENTAIRE'),
(652, '410308', NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS, MACHINES, PIECES DETACHEES ET ACCESSOIRES DESTINES A L\'INDUSTRIE TEXTILE'),
(653, '410309', NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS, MACHINES, PIECES DETACHEES ET ACCESSOIRES DESTINES A LA FABRICATION DES MATIERES CELLULOSIQUES OU LE FINISSAGE DU PAPIER ET CARTON'),
(654, '410310', NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS, MACHINES, PIECES DETACHEES ET ACCESSOIRES DESTINES AU CONDITIONNEMENT DE L\'AIR, TURBINES, CHAUDIERES, GENERATEURS DE GAZ'),
(655, '410311', NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS, MACHINES, PIECES DETACHEES ET ACCESSOIRES DE REFRIGERATION, DE CONGELATION, DE CLIMATISATION, DE BRULERIE ET DE CUISSON'),
(656, '410312', NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS, MACHINES DE LEVAGE ET DE MANUTENTION Y COMPRIS PIECES DETACHEES ET ACCESSOIRES'),
(657, '410313', NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS DE TRAVAUX PUBLICS ET DE BATIMENT Y COMPRIS PIECES DETACHEES ET ACCESSOIRES'),
(658, '410314', NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS AGRICOLES, LEURS PIECES DETACHEES ET ACCESSOIRES'),
(659, '410315', NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS POUR LE TRAVAIL DU CUIR, LEURS PIECES DETACHEES ET ACCESSOIRES'),
(660, '410316', NULL, NULL, 'IMPORT - EXPORT DE VOITURES ET DE TOUS MATERIELS DE TRANSPORT, LEURS PIECES DETACHEES ET ACCESSOIRES'),
(661, '410317', NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS DE TRANSPORT FERROVIAIRES, LEURS PARTIES, PIECES DETACHEES ET ACCESSOIRES'),
(662, '410318', NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS DE NAVIGATION AERIENNE ET SPATLALE LEURS PARTIES, PIECES DETACHEES ET ACCESSOIRES'),
(663, '410319', NULL, NULL, 'IMPORT - EXPORT DE TOUS MATERIELS DE NAVIGATION MARITIME ET FLUVIALE, LEURS PARTIES, PIECES DETACHEES ET ET ACCESSOIRES'),
(664, '410320', NULL, NULL, 'IMPORT - EXPORT D\'ARTICLES D\'HORLOGERIE, DE MOUVEMENTS D\'HORLOGES D\'APPAREILS DE CONTROLE DU TEMPS, LEURS PARTIES, PIECES DETACHEES ET ACCESSOIRES'),
(665, '410321', NULL, NULL, 'IMPORT - EXPORT D\'EQUIPEMENTS ET MATERIELS DES INDUSTRIELS GRAPHIQUES, LEURS PIECES DETACHEES, ACCESSOIRES ET CONSOMMABLES'),
(666, '410322', NULL, NULL, 'IMPORT - EXPORT D\'APPAREILS ET ARTICLES DE REGULATION INDUSTRIELLE'),
(667, '410323', NULL, NULL, 'IMPORT - EXPORT D\'EQUIPEMENTS ET APPAREILS DE TRANSMISSION DE VITESSE'),
(668, '410324', NULL, NULL, 'IMPORT - EXPORT DE CABINES SAHARIENNES'),
(669, '410325', NULL, NULL, 'IMPORT - EXPORT DE MOTEURS ET TURBINES INDUSTRIELLES OU AUTRES Y COMPRIS LEURS PIECES DETACHEES ET LEURS ACCESSOIRES'),
(670, '410326', NULL, NULL, 'IMPORT - EXPORT DE VEHICULES AUTOMOBILES A USAGES SPECIAUX'),
(671, '410327', NULL, NULL, 'IMPORT - EXPORT DE FILMS ET TISSUS POUR TOUS TYPES D\'ENROBAGE'),
(672, '410328', NULL, NULL, 'IMPORT - EXPORT DE BATTERIE A USAGE INDUSTRIEL'),
(673, '410329', NULL, NULL, 'IMPORT - EXPORT D\'ARTICLES DE REVETEMENT D\'INTERIEUR'),
(674, '410330', NULL, NULL, 'IMPORT- EXPORT DES ARMES DE CHASSE'),
(675, '501101', NULL, NULL, 'COMMERCE DE DETAIL D\'AROMES, ESSENCES ET COLORANTS POUR L\'ALIMENTATION'),
(676, '501102', NULL, NULL, 'COMMERCE DE DETAIL DE PRODUITS LAITIERS ET MIEL'),
(677, '501103', NULL, NULL, 'COMMERCE DE DETAIL DE BOISSONS NON ALCOOLISEES A EMPORTER'),
(678, '501104', NULL, NULL, 'COMMERCE DE DETAIL DES FRUITS ET LEGUMES'),
(679, '501105', NULL, NULL, 'COMMERCE DE DETAIL DE FRUITS ET LEGUMES EXERCE EN ETAL'),
(680, '501106', NULL, NULL, 'COMMERCE DE DETAIL DE PLANTES POTAGERES EXERCE EN ETAL'),
(681, '501107', NULL, NULL, 'COMMERCE DE DETAIL DE LA DATTE ET DES FRUITS CONFITS'),
(682, '501108', NULL, NULL, 'COMMERCE DE DETAIL DE LEGUMES SECS ET PRODUITS DE LA MINOTERIE'),
(683, '501109', NULL, NULL, 'COMMERCE DE DETAIL DE LEGUMES CONDIMENTES OU EPLUCHES'),
(684, '501110', NULL, NULL, 'COMMERCE DE DETAIL DE LEGUMES CONDIMENTES OU EPLUCHES, EXERCE ENETAL'),
(685, '501111', NULL, NULL, 'ALIMENTATION GENERALE (EPICERIE)'),
(686, '501112', NULL, NULL, 'COMMERCE DE DETAIL DE L\'ALIMENTATION GENERALE EXERCE ENETAL.'),
(687, '501113', NULL, NULL, 'COMMERCE DE DETAIL DE TABACS, ARTICLES POUR FUMEURS, ARTICLES DEBAZAR ET JOURNAUX'),
(688, '501114', NULL, NULL, 'KIOSQUE A TABACS.'),
(689, '501115', NULL, NULL, 'COMMERCE DE DETAIL DE BOISSONS ALCOOLISEES A EMPORTER'),
(690, '501116', NULL, NULL, 'COMMERCE DE DETAIL DE CAFE'),
(691, '501202', NULL, NULL, 'PATISSERIE .'),
(692, '501203', NULL, NULL, 'CONFISERIE'),
(693, '501204', NULL, NULL, 'DEPOT DE PATISSERIE ET CONFISERIE'),
(694, '501205', NULL, NULL, 'PATISSERIE TRADITIONNELLE'),
(695, '501206', NULL, NULL, 'DEPOT DE PAIN'),
(696, '501207', NULL, NULL, 'COMMERCE DE DETAIL DES PRODUITS POUR LA BOULANGERIE ET PATISSERIE'),
(697, '501301', NULL, NULL, 'COMMERCE DE DETAIL DE VOLAILLES, OEUFS ET LAPINS'),
(698, '501302', NULL, NULL, 'COMMERCE DE DETAIL DE VOLAILLE ET OEUFS EXERCE EN ETAL'),
(699, '501303', NULL, NULL, 'COMMERCE DE DETAIL DE VIANDES, DE VOLAILLES ET OEUFS'),
(700, '501304', NULL, NULL, 'COMMERCE DE DETAIL DE VIANDE DE BOUCHERIE'),
(701, '501305', NULL, NULL, 'COMMERCE DE DETAIL DE VIANDE DE BOUCHERIE EXERCE EN ETAL'),
(702, '501306', NULL, NULL, 'COMMERCE DE DETAIL DE LA VIANDE CHEVALINE'),
(703, '501307', NULL, NULL, 'COMMERCE DE DETAIL D\'ABATS ET VIANDE PREPARES CRUS'),
(704, '501308', NULL, NULL, 'COMMERCE DE DETAIL DE LA CHARCUTERIE'),
(705, '501309', NULL, NULL, 'COMMERCE DE DETAIL DE VIANDES FRAICHES, CONGELEES OU SURGELES'),
(706, '501401', NULL, NULL, 'COMMERCE DE DETAIL DE POISSONS, CRUSTACES ET COQUILLAGES (FRUITS DE MER)'),
(707, '501402', NULL, NULL, 'COMMERCE DE DETAIL DE POISSONS ET CRUSTACES EXERCE EN ETAL'),
(708, '501403', NULL, NULL, 'COMMERCE DE DETAIL DE POISSONS, CRUSTACES ET COQUILLAGES CONGELES OU SURGELETS'),
(709, '502101', NULL, NULL, 'COMMERCE DE DETAIL DE LA PELLETERIE ET DES FOURRURES'),
(710, '502102', NULL, NULL, 'COMMERCE DE DETAIL DE L\'HABILLEMENT, DE LA CHAUSSURE ET TEXTILES'),
(711, '502103', NULL, NULL, 'COMMERCE DE DETAIL DE L\'HABILLEMENT, DE LA CHAUSSURE ET DES TEXTILES EXERCES EN ETAL'),
(712, '502104', NULL, NULL, 'COMMERCE DE DETAIL DE L\'HABILLEMENT, D\'ARTICLES DE BONNETERIE ET DE LA CHAUSSURE.'),
(713, '502105', NULL, NULL, 'COMMERCE DE DETAIL DE L\'HABILLEMENT , DES ARTICLES DE BONNETERIE ET DE LA CHAUSSURE EXERCE EN ETAL'),
(714, '502106', NULL, NULL, 'COMMERCE DE DETAIL DE LAYETTES ET ARTICLES POUR BEBES.'),
(715, '502107', NULL, NULL, 'COMMERCE DE DETAIL DES VETEMENTS USAGES FRIPERIES'),
(716, '502108', NULL, NULL, 'COMMERCE DE DETAIL DE LA MERCERIE, BONNETERIE ET LAYETTES'),
(717, '502109', NULL, NULL, 'COMMERCE DE DETAIL DE LA MERCERIE, BONNETERIE ET LAYETTES EXERCE EN ETAL'),
(718, '502110', NULL, NULL, 'COMMERCE DE DETAIL DE LA MERCERIE'),
(719, '502111', NULL, NULL, 'COMMERCE DE DETAIL DE LA MERCERIE EXERCE EN ETAL'),
(720, '502112', NULL, NULL, 'COMMERCE DE DETAIL DES TISSUS POUR CONFECTION ET AMEUBLEMENT'),
(721, '502113', NULL, NULL, 'COMMERCE DE DETAIL DE TISSUS ET BONNETERIE'),
(722, '502114', NULL, NULL, 'COMMERCE DE DETAIL DE TISSUS DE CONFECTION ET D\'AMEUBLEMENT EXERCE EN ETAL'),
(723, '502115', NULL, NULL, 'COMMERCE DE DETAIL DE LA CHAUSSURE'),
(724, '502116', NULL, NULL, 'COMMERCE DE DETAIL DE LA CHAUSSURE EXERCE EN ETAL'),
(725, '502117', NULL, NULL, 'COMMERCE DE DETAIL DE LA MAROQUINERIE'),
(726, '502118', NULL, NULL, 'COMMERCE DE DETAIL DE LA MAROQUINERIE EXERCE EN ETAL'),
(727, '502201', NULL, NULL, 'COMMERCE DE DETAIL DE LA BIJOUTERIE ET DE L\'HORLOGERIE'),
(728, '502202', NULL, NULL, 'COMMERCE DE DETAIL DE LA BIJOUTERIE TRADITIONNELLE ET DE L\'HORLOGERIE EXERCE EN ETAL'),
(729, '502203', NULL, NULL, 'COMMERCE DE DETAIL DE L\'HORLOGERIE'),
(730, '502204', NULL, NULL, 'COMMERCE DE DETAIL DE MATERIELS, PRODUITS ET MATIERES PREMIERES DESTINES A LA BIJOUTERIE INDUSTRIELLE ET ARTISANALE'),
(731, '502301', NULL, NULL, 'COMMERCE DE DETAIL DES PRODUITS DE PARFUMERIE ET COSMETIQUES'),
(732, '502302', NULL, NULL, 'COMMERCE DE DETAIL DE PRODUITS DE PARFUMERIE ET COSMETIQUES EXERCE EN ETAL'),
(733, '502303', NULL, NULL, 'COMMERCE DE DETAIL DE PRODUITS ET APPAREILS LIES AU CONFORT ET A L\'ENTRETIEN DU CORPS HUMAIN'),
(734, '502304', NULL, NULL, 'COMMERCE DE DETAIL DE PRODUITS DIETETIQUES, NUTRITIONNELS ET DE REGIME'),
(735, '503101', NULL, NULL, 'COMMERCE DE DETAIL DE PETITS ANIMAUX D\'AGREMENT'),
(736, '503102', NULL, NULL, 'FLEURISTE ET COMMERCE DE DETAIL DE PLANTES EN TOUS GENRES'),
(737, '503103', NULL, NULL, 'FLEURISTE EN KIOSQUE'),
(738, '503104', NULL, NULL, 'COMMERCE DE DETAIL DE MEUBLES ET ARTICLES D\'AMEUBLEMENT'),
(739, '503105', NULL, NULL, 'COMMERCE DE DETAIL D\'ARTICLES DE REVETEMENT D\'INTERIEUR'),
(740, '503106', NULL, NULL, 'COMMERCE DE DETAIL DE MATELAS'),
(741, '503107', NULL, NULL, 'COMMERCE DE DETAIL D\'ARTICLES DE LUSTRERIE ET DE DECORATION D\'INTERIEUR'),
(742, '503201', NULL, NULL, 'COMMERCE DE DETAIL DE TISSUS ENDUITS'),
(743, '503202', NULL, NULL, 'COMMERCE DE DETAIL DE LA DROGUERIE, PEINTURE, QUINCAILLERIE, ARTICLES DE MENAGE, PRODUITS D\'HYGIENE ET D\'ENTRETIEN DOMESTIQUE ET PROFESSIONNEL'),
(744, '503203', NULL, NULL, 'COMMERCE DE DETAIL DE LA DROGUERIE, PEINTURE, QUINCAILLERIE ET ARTICLES DE MENAGE EXERCE EN ETAL'),
(745, '503204', NULL, NULL, 'COMMERCE DE DETAIL DE LA DROGUERIE'),
(746, '503205', NULL, NULL, 'COMMERCE DE DETAIL D\'ARTICLES DE MENAGE ET USTENSILES DE CUISINE'),
(747, '503206', NULL, NULL, 'COMMERCE DE DETAIL DES ARTICLES DE MENAGE EXERCE EN ETAL'),
(748, '503207', NULL, NULL, 'COMMERCE DE DETAIL DE METAUX ET PRODUITS METALLIQUES ET SIDERURGIQUES'),
(749, '503301', NULL, NULL, 'COMMERCE DE DETAIL DE LA LAINE BRUTE, DE FILS DE LAINE ET FIBRANNE'),
(750, '503302', NULL, NULL, 'COMMERCE DE DETAIL DE GAZ BUTANE ,CHARBON DE BOIS ET AUTRES COMBUSTIBLES SOLIDES, LIQUIDES OU GAZEUX'),
(751, '503303', NULL, NULL, 'COMMERCE DE DETAIL D\'ARTICLES DE VANNERIE ET DE SPARTERIE'),
(752, '503304', NULL, NULL, 'COMMERCE DE DETAIL DES ARTICLES DE VANNERIE SPARTERIE, EXERCE ENETAL'),
(753, '503305', NULL, NULL, 'COMMERCE DE DETAIL D\'APPAREILS SANITAIRES ET DE CHAUFFAGE'),
(754, '503306', NULL, NULL, 'COMMERCE DE DETAIL DE L\'ELECTRO-MENAGER ET DES APPAREILS DE REPRODUCTION DU SON ET DE L\'IMAGE'),
(755, '503307', NULL, NULL, 'COMMERCE DE DETAIL DE VEHICULES AUTOMOBILES D\'OCCASION'),
(756, '503308', NULL, NULL, 'COMMERCE DE DETAIL DE CYCLES ET MOTOCYCLES'),
(757, '503309', NULL, NULL, 'COMMERCE DE DETAIL DE MACHINES A COUDRE ET A TRICOTER'),
(758, '503310', NULL, NULL, 'COMMERCE DE DETAIL DE TAPIS'),
(759, '503311', NULL, NULL, 'COMMERCE DE DETAIL DE TAPIS, EXERCE EN ETAL'),
(760, '503312', NULL, NULL, 'COMMERCE DE DETAIL D\'OBJETS D\'ARTS ET DE COLLECTION (ANTIQUAIRE)'),
(761, '503313', NULL, NULL, 'COMMERCE DE DETAIL DES ARTICLES DE POTERIE'),
(762, '503314', NULL, NULL, 'COMMERCE DE DETAIL DES PRODUITS DE L\'ARTISANAT'),
(763, '503315', NULL, NULL, 'COMMERCE DE DETAIL DE LA MAROQUINERIE TRADITIONNELLE'),
(764, '503316', NULL, NULL, 'COMMERCE DE DETAIL DE LA MAROQUINERIE TRADITIONNELLE , EXERCE EN ETAL'),
(765, '503317', NULL, NULL, 'COMMERCE DE DETAIL DES ARTICLES DE DINANDERIE'),
(766, '503318', NULL, NULL, 'COMMERCE DE DETAIL DE VEHICULES AUTOMOBILES NEUFS'),
(767, '504101', NULL, NULL, 'COMMERCE DE DETAIL DE MATERIAUX DE CONSTRUCTION'),
(768, '504102', NULL, NULL, 'COMMERCE DE DETAIL DE QUINCAILLERIE DU BATIMENT, VITRERIE, PEINTURE ET BOIS'),
(769, '504103', NULL, NULL, 'COMMERCE DE DETAIL DU BOIS ET LIEGE POUR LA MENUISERIE'),
(770, '504104', NULL, NULL, 'COMMERCE DE DETAIL DE LA QUINCAILLERIE'),
(771, '504105', NULL, NULL, 'COMMERCE DE DETAIL DE LA QUINCAILLERIE EXERCE EN ETAL'),
(772, '504106', NULL, NULL, 'COMMERCE DE DETAIL DE VITRERIE ET MIROITERIE'),
(773, '504107', NULL, NULL, 'COMMERCE DE DETAIL DE PEINTURES ET VERNIS'),
(774, '504108', NULL, NULL, 'COMMERCE DE DETAIL DE PEINTURES ET VERNIS EXERCE EN ETAL'),
(775, '504201', NULL, NULL, 'COMMERCE DE DETAIL DE FOURNITURES POUR L\'ELECTRICITE'),
(776, '504202', NULL, NULL, 'COMMERCE DE DETAIL DES ARTICLES DE REVETEMENT D\'INTERIEUR'),
(777, '504203', NULL, NULL, 'COMMERCE DE DETAIL DE MATERIELS, EQUIPEMENTS ET FOURNITURES DE PROTECTION ET DE SECURITE, LEURS PIECES DETACHEES ET ACCESSOIRES'),
(778, '504204', NULL, NULL, 'COMMERCE DE DETAIL DE MATERIELS ET OUTILLAGES POUR BTP'),
(779, '505001', NULL, NULL, 'COMMERCE DE DETAIL DES EQUIPEMENTS ET FOURNITURES THERMIQUES'),
(780, '505002', NULL, NULL, 'COMMERCE DE DETAIL DE MATERIEL ET FOURNITURES POUR LES CANTINES ET GRANDES CUISINES DE COLLECTIVITES'),
(781, '505003', NULL, NULL, 'COMMERCE DE DETAIL DE MATERIEL ET FOURNITURES POUR BOULANGERIE, PATISSERIE, CONFISERIE'),
(782, '505004', NULL, NULL, 'COMMERCE DE DETAIL DE MATERIEL ET FOURNITURES POUR DEBITS DE BOISSONS ET SALONS DE DEGUSTATION'),
(783, '505005', NULL, NULL, 'COMMERCE DE DETAIL D\'EQUIPEMENT ET MATERIEL POUR BLANCHISSERIE'),
(784, '505006', NULL, NULL, 'COMMERCE DE DETAIL DES FOURNITURES POUR CORDONNERIE'),
(785, '505007', NULL, NULL, 'COMMERCE DE DETAIL D\'ARTICLES D\'EMBALLAGE'),
(786, '505008', NULL, NULL, 'COMMERCE DE DETAIL D\'EQUIPEMENTS, D\'APPAREILS ET INSTRUMENTS DE PESAGE , MESURAGE Y COMPRIS LEURS ACCESSOIRES , PIECES DETACHEES ET LEURS CONSOMMABLES'),
(787, '505009', NULL, NULL, 'COMMERCE DE DETAIL D\'APPAREILS MEDICAUX ET CHIRURGICAUX'),
(788, '505010', NULL, NULL, 'COMMERCE DE DETAIL D\'EQUIPEMENTS ET FOURNITURES INDUSTRIELLES'),
(789, '505011', NULL, NULL, 'COMMERCE DE DETAIL D\'APPAREILS, ARTICLES ET PRODUITS DESTINES A LA FILTRATION OU L\'EPURATION DES LIQUIDES ET DES GAZ'),
(790, '505012', NULL, NULL, 'COMMERCE DE DETAIL DES EQUIPEMENTS ET MATERIELS POUR LA BLANCHISSERIE, EQUIPEMENTS POUR HOTELS, CAFES, RESTAURANTS ET MAGASINS'),
(791, '505013', NULL, NULL, 'COMMERCE DE DETAIL DE REACTIFS DE LABORATOIRE'),
(792, '505014', NULL, NULL, 'COMMERCE DE DETAIL D\'EQUIPEMENTS ET MATERIELS UTILISES POUR LA PRODUCTION D\'EMBALLAGE ET LE CONDITIONNEMENT'),
(793, '506001', NULL, NULL, 'COMMERCE DE DETAIL D\'OUTILS ET OUTILLAGES PROFESSIONNELS POUR MACHINES-OUTILS'),
(794, '506002', NULL, NULL, 'COMMERCE DE DETAIL D\'OUTILLAGE ET AUTRES MATERIELS POUR L\'ENTRETIEN ET LES REPARATIONS MECANIQUES'),
(795, '506003', NULL, NULL, 'COMMERCE DE DETAIL DE PIECES DETACHEES ET ACCESSOIRES POUR VEHICULES'),
(796, '506004', NULL, NULL, 'COMMERCE DE DETAIL DE ROULEMENTS A BILLE'),
(797, '506005', NULL, NULL, 'COMMERCE DE DETAIL DE PIECES DETACHEES ET ACCESSOIRES POUR CYCLES ET MOTOCYCLES'),
(798, '506006', NULL, NULL, 'COMMERCE DE DETAIL DE PNEUMATIQUES'),
(799, '506007', NULL, NULL, 'COMMERCE DE DETAIL DE BATTERIES'),
(800, '506008', NULL, NULL, 'COMMERCE DE DETAIL DE LUBRIFIANTS ET GRAISSES INDUSTRIELLES'),
(801, '507101', NULL, NULL, 'COMMERCE DE DETAIL DES ARMES DE CHASSE (ARMURERIE)'),
(802, '507102', NULL, NULL, 'COMMERCE DE DETAIL DE BARQUES ET AUTRES EMBARCATIONS'),
(803, '507103', NULL, NULL, 'COMMERCE DE DETAIL DE JOUETS ET JEUX DE SOCIETE'),
(804, '507104', NULL, NULL, 'COMMERCE DE DETAIL DE FOURNITURE ET ARTICLES DE SPORT ET AUTRES LOISIRS'),
(805, '507201', NULL, NULL, 'COMMERCE DE DETAIL DE MACHINES, MATERIEL ET MOBILIER DE BUREAU'),
(806, '507202', NULL, NULL, 'VENTE AU DETAIL DE MATERIEL INFORMATIQUE : ORDINATEURS, PERIPHERIQUES, CONSOMMABLES ET ACCESSOIRES.'),
(807, '507203', NULL, NULL, 'COMMERCE DE DETAIL DE LA LIBRAIRIE ET PAPETERIE'),
(808, '507204', NULL, NULL, 'COMMERCE DE DETAIL DE LA LIBRAIRE ET PAPETERIE , EXERCE EN ETAL'),
(809, '507205', NULL, NULL, 'COMMERCE DE DETAIL DE LIVRES D\'OCCASION (BOUQUINERIE)'),
(810, '507206', NULL, NULL, 'COMMERCE DE DETAIL DE MATERIEL, ARTICLES ET FOURNITURES POUR LA CULTURE, L\'ENSEIGNEMENT ET LES COLLECTIVITES'),
(811, '507207', NULL, NULL, 'COMMERCE DE DETAIL DE FOURNITURES ET ARTICLES POUR LES ACTIVITES DES BEAUX ARTS'),
(812, '507208', NULL, NULL, 'COMMERCE DE DETAIL DE MATERIEL SCIENTIFIQUE ET DIDACTIQUE D\'ENSEIGNEMENT'),
(813, '507209', NULL, NULL, 'COMMERCE DE DETAIL DE TABLEAUX ET D\'OEUVRE D\'ART'),
(814, '507210', NULL, NULL, 'COMMERCE DE DETAIL DE MATERIELS DE TELEPHONE ET DE TELECOMMUNICATION'),
(815, '507211', NULL, NULL, 'COMMERCE DE DETAIL DE TOUS MATERIELS ET EQUIPEMENTS LIES AU DOMAINE DE L\'ELECTRICITE ET L\'ELECTRONIQUE'),
(816, '507301', NULL, NULL, 'COMMERCE DE DETAIL DES APPAREILS PHOTOGRAPHIQUES ET CINEMATOGRAPHIQUES'),
(817, '507302', NULL, NULL, 'COMMERCE DE DETAIL DE MATERIEL, ARTICLES ET FOURNITURES DESTINES AUX ACTIVITES ARTISTIQUES'),
(818, '507303', NULL, NULL, 'COMMERCE DE DETAIL DES INSTRUMENTS DE MUSIQUE.'),
(819, '507304', NULL, NULL, 'COMMERCE DE DETAIL DE DISQUES ET CASSETTES (DISQUAIRE)'),
(820, '508001', NULL, NULL, 'COMMERCE DE DETAIL DE MATERIEL HYDRAULIQUE'),
(821, '508002', NULL, NULL, 'COMMERCE DE DETAIL DE SEMENCES ET PLANTES'),
(822, '508003', NULL, NULL, 'COMMERCE DE DETAIL DE SEMENCES ET PLANTES, EXERCE EN ETAL'),
(823, '508004', NULL, NULL, 'COMMERCE DE DETAIL DES ALIMENTS DU BETAIL'),
(824, '508005', NULL, NULL, 'COMMERCE DE DETAIL DE MATERIEL ET EQUIPEMENT AVICOLES'),
(825, '508006', NULL, NULL, 'COMMERCE DE DETAIL DE MATERIEL ET FOURNITURES POUR L\'AGRICULTURE'),
(826, '508007', NULL, NULL, 'COMMERCE DE DETAIL DE VIANDES DESTINEES A L\'ALIMENTATION DES ANIMAUX DOMESTIQUES'),
(827, '509001', NULL, NULL, 'COMMERCE DE DETAIL DE BROCANTERIE'),
(828, '509002', NULL, NULL, 'COMMERCE DE VIEUX METAUX NON FERREUX'),
(829, '509003', NULL, NULL, 'COMMERCE DE RECUPERATION DE VIEUX METAUX, BROCANTERIE ET DECHETS INDUSTRIELS DIVERS'),
(830, '509004', NULL, NULL, 'COMMERCE DE VIEUX FERS FERRAILLE ET RIBLONS'),
(831, '509005', NULL, NULL, 'COMMERCE DE RECUPERATION DE PAPIER CARTONS ET CHIFFONS'),
(832, '509006', NULL, NULL, 'COMMERCEDE TESSONS ET DEBRIS DE VERRE'),
(833, '509007', NULL, NULL, 'COMMERCE DE DECHETS D\'ANIMAUX ET VEGETAUX'),
(834, '509008', NULL, NULL, 'COMMERCE DE DECHETS INDUSTRIELS ET DE PRODUITS DIVERS N D A.'),
(835, '509009', NULL, NULL, 'COMMERCE DE DETAIL DE TOUS APPAREILS ET MACHINES PROFESSIONNELS OU DOMESTIQUES D\'OCCASIONS'),
(836, '510001', NULL, NULL, 'COMMERCE DE DETAIL EN MAGASIN D\'UNE SURFACE DE VENTE COMPRISE ENTRE 120 ET 400 M2 (SUPERETTE)'),
(837, '510002', NULL, NULL, 'COMMERCE DE DETAIL EN MAGASIN D\'UNE SURFACE DE VENTE COMPRISE ENTRE 400 ET 2500 M2 (SUPERMARCHE)'),
(838, '510003', NULL, NULL, 'COMMERCE DE DETAIL EN MAGASIN D\'UNE SURFACE DE VENTE EGALE OU SUPERIEURE A 2500 M2 (HYPERMARCHE)'),
(839, '510004', NULL, NULL, 'MAGASIN OU COMPTOIR DE VENTE DE MARCHANDISES SOUS DOUANES'),
(840, '510005', NULL, NULL, 'COMMERCE MULTIPLE DE DETAIL EXERCE EN ZONES RURALES ET ELOIGNEES ET DU GRAND SUD'),
(841, '511101', NULL, NULL, 'MARCHAND AMBULANT EN ALIMENTATION GENERALE AU NIVEAU DES MARCHES COMMUNAUX, PLACES ET EMPLACEMENTS AMENAGES OU AFFECTES A CET EFFET'),
(842, '511102', NULL, NULL, 'MARCHAND AMBULANT D\'EPICES ET CONDIMENTS AU NIVEAU DES MARCHES COMMUNAUX.'),
(843, '511103', NULL, NULL, 'MARCHAND AMBULANT DE BOISSONS AU NIVEAU DES MARCHES COMMUNAUX.'),
(844, '511104', NULL, NULL, 'MARCHAND AMBULANT EN CONFISERIE AU NIVEAU DES MARCHES COMMUNAUX'),
(845, '511105', NULL, NULL, 'MARCHAND AMBULANT DE CREME GLACEE'),
(846, '511106', NULL, NULL, 'MARCHAND AMBULANT DE FRUITS ET LEGUMES AU NIVEAU DES MARCHES COMMUNAUX.'),
(847, '511107', NULL, NULL, 'MARCHAND AMBULANT DE POISSONS AU NIVEAU DES MARCHES COMMUNAUX'),
(848, '511108', NULL, NULL, 'MARCHAND AMBULANT EN BOUCHERIE AU NIVEAU DES MARCHES COMMUNAUX'),
(849, '511109', NULL, NULL, 'MARCHAND AMBULANT DE VOLAILLE ET LAPINS AU NIVEAU DES MARCHES COMMUNAUX.'),
(850, '511110', NULL, NULL, 'MARCHAND AMBULANT DE VIANDE ET POISSONS.'),
(851, '511111', NULL, NULL, 'MARCHAND AMBULANT EN HERBORISTERIE AU NIVEAU DES MARCHES COMMUNAUX'),
(852, '511112', NULL, NULL, 'MARCHAND AMBULANT DE SEMENCES ET DE PLANTS AU NIVEAU DES MARCHES COMMUNAUX'),
(853, '511113', NULL, NULL, 'MARCHAND AMBULANT D\'EFFETS VESTIMENTAIRES AU NIVEAU DES MARCHES COMMUNAUX'),
(854, '511114', NULL, NULL, 'MARCHAND AMBULANT DE TISSUS AU NIVEAU DES MARCHES COMMUNAUX'),
(855, '511115', NULL, NULL, 'MARCHAND AMBULANT DE LAINE AU NIVEAU DES MARCHES COMMUNAUX'),
(856, '511116', NULL, NULL, 'MARCHAND AMBULANT DE PRODUITS COSMETIQUES ET DE PARFUMERIE, AUNIVEAU DES MARCHES COMMUNAUX'),
(857, '511117', NULL, NULL, 'MARCHAND AMBULANT DE CHAUSSURES AU NIVEAU DES MARCHES COMMUNAUX'),
(858, '511118', NULL, NULL, 'MARCHAND AMBULANT EN MAROQUINERIE AU NIVEAU DES MARCHES COMMUNAUX, PLACES ET EMPLACEMENTS SPECIALEMENT AMENAGES OU AFFECTES A CET EFFET'),
(859, '511119', NULL, NULL, 'MARCHAND AMBULANT D\'ARTICLES DE MENAGE AU NIVEAU DES MARCHES COMMUNAUX, PLACES ET EMPLACEMENTS SPECIALEMENT AMENAGES OU AFFECTES A CET EFFET'),
(860, '511120', NULL, NULL, 'MARCHAND AMBULANT DE NATTES ET ARTICLES EN ALFA, AU NIVEAU DES MARCHES COMMUNAUX, PLACES ET EMPLACEMENTS AMENAGES OU AFFECTES CET EFFET'),
(861, '511121', NULL, NULL, 'MARCHAND AMBULANT DE MEUBLES AU NIVEAU DES MARCHES COMMUNAUX, PLACES ET EMPLACEMENTS SPECIALEMENT AMENAGES OU AFFECTES A CET EFFET'),
(862, '511122', NULL, NULL, 'MARCHAND AMBULANT EN BOURRELLERIE, SELLERIE AU NIVEAU DES MARCHES COMMUNAUX, PLACES ET EMPLACEMENTS SPECIALEMENT AMENAGES OU AFFECTES A CET EFFET'),
(863, '511123', NULL, NULL, 'MARCHAND AMBULANT EN QUINCAILLERIE AU NIVEAU DES MARCHES COMMUNAUX ,PLACES ET EMPLACEMENTS SPECIALEMENT AMENAGES OU AFFECTES A CET EFFET'),
(864, '511124', NULL, NULL, 'MARCHAND AMBULANT EN DROGUERIE AU NIVEAU DES MARCHES COMMUNAUX, PLACES ET EMPLACEMENTS SPECIALEMENT AMENAGES OU AFFECTES A CET EFFET'),
(865, '511125', NULL, NULL, 'MARCHAND AMBULANT DE JOUETS AU NIVEAU DES MARCHES COMMUNAUX, PLACES ET EMPLACEMENTS SPECIALEMENT AMENAGES OU AFFECTES A CET EFFET'),
(866, '511126', NULL, NULL, 'MARCHAND AMBULANT DE DISQUES ET CASSETTES AU NIVEAU DES MARCHES COMMUNAUX, PLACES ET EMPLACEMENTS AMENAGES OU AFFECTES A CET EFFET'),
(867, '511127', NULL, NULL, 'MARCHAND AMBULANT DE LIVRES NEUFS ET D\'OCCASION AU NIVEAU DES MARCHES COMMUNAUX, PLACES ET EMPLACEMENTS SPECIALEMENT AMENAGES A CET EFFET'),
(868, '511128', NULL, NULL, 'MARCHAND AMBULANT DE PIECES DETACHEES DE CYCLES ET MOTOCYCLES AU NIVEAU DES MARCHES COMMUNAUX, PLACES ET EMPLACEMENTS SPECIALEMENT AMENAGES OU AFFECTES A CET EFFET'),
(869, '511129', NULL, NULL, 'MARCHAND AMBULANT DE PIECES DETACHEES ET ACCESSOIRES POUR L\'ENTRETIEN ET LES REPARATIONS MECANIQUES'),
(870, '511130', NULL, NULL, 'MARCHAND AMBULANT DE PNEUMATIQUES'),
(871, '511131', NULL, NULL, 'MARCHAND AMBULANT DE LIBRAIRIE ET PAPETERIE'),
(872, '511132', NULL, NULL, 'MARCHAND AMBULANT D\'EMBALLAGES EN TOUTES MATIERES'),
(873, '511133', NULL, NULL, 'MARCHAND AMBULANT DE LA BROCANTERIE'),
(874, '511134', NULL, NULL, 'MARCHAND AMBULANT D\'ALIMENTS DE BETAIL ET VOLAILLE'),
(875, '511135', NULL, NULL, 'MARCHAND AMBULANT EN MATERIAUX DE CONSTRUCTION'),
(876, '511136', NULL, NULL, 'MARCHAND AMBULANT DE BETAIL'),
(877, '511137', NULL, NULL, 'MARCHAND AMBULANT DE LA MERCERIE'),
(878, '511138', NULL, NULL, 'MARCHANT AMBULANT D\'ARTICLES POUR FUMEURS, ARTICLES DE BAZAR ET JOURNAUX AU NIVEAU DES MARCHES COMMUNAUX'),
(879, '511139', NULL, NULL, 'MARCHAND AMBULANT DE PIECES DE RECHANGE ELECTRIQUES, ELECTRONIQUES ET HYDRAULIQUES'),
(880, '511140', NULL, NULL, 'MARCHAND AMBULANT DES PRODUITS ET MATERIELS LIES AU DOMAINE DE LA PECHE'),
(881, '511141', NULL, NULL, 'MARCHAND AMBULANT DE PRODUITS PHYTOSANITAIRES AU NIVEAU DES MARCHES COMMUNAUX'),
(882, '511142', NULL, NULL, 'MARCHAND AMBULANT DES APPAREILS PHOTOGRAPHIQUES ET CINEMATOGRAPHIQUES AU NIVEAU DES MARCHES COMMUNAUX'),
(883, '511143', NULL, NULL, 'MARCHAND AMBULANT D\'ARTICLES DE LUSTRERIE ET DE DECORATION D\'INTERIEUR AU NIVEAU DES MARCHES COMMUNAUX'),
(884, '511201', NULL, NULL, 'REMOULEUR AMBULANT'),
(885, '511202', NULL, NULL, 'RETAMEUR AMBULANT'),
(886, '511203', NULL, NULL, 'VITRIER AMBULANT'),
(887, '511204', NULL, NULL, 'PLOMBIER AMBULANT'),
(888, '511205', NULL, NULL, 'PHOTOGRAPHE AMBULANT'),
(889, '511206', NULL, NULL, 'MATELASSIER AMBULANT'),
(890, '511207', NULL, NULL, 'CALLIGRAPHE AMBULANT'),
(891, '511208', NULL, NULL, 'RESTAURATION MOBILE'),
(892, '511209', NULL, NULL, 'ATTRACTIONS FORAINES'),
(893, '511210', NULL, NULL, 'CORDONNIER AMBULANT'),
(894, '511211', NULL, NULL, 'MARECHAL FERRANT AMBULANT AU NIVEAU DES MARCHES COMMUNAUX'),
(895, '512101', NULL, NULL, 'COMMERCE DE DETAIL DE PRODUITS PARA-PHARMACEUTIQUES, APPAREILS, MATERIELS ET INSTRUMENTS MEDICO-CHIRURGICAUX LEURS ACCESSOIRES, PIECES DETACHEES ET CONSOMMABLES'),
(896, '512102', NULL, NULL, 'COMMERCE DE DETAIL DE LUNETTERIE NON OPTIQUE'),
(897, '513101', NULL, NULL, 'COMMERCE DE DETAIL D\'EMBLEMES ET DE PORTRAITS'),
(898, '513102', NULL, NULL, 'COMMERCE DE DETAIL DE FORME D\'OCCASION EN BOIS ET EN PLASTIQUE PROVENANT DE LA RECUPERATION DES FABRIQUES DE CHAUSSURES'),
(899, '513103', NULL, NULL, 'COMMERCE DE DETAIL DE CHUTES DE CUIR, DAIM ET AUTRES MATIERES PROVENANT DES TANNERIES ET FABRIQUES DE CHAUSSURES'),
(900, '513104', NULL, NULL, 'COMMERCE DE DETAIL DE BIENS D\'OCCASION EN MAGASIN'),
(901, '601101', NULL, NULL, 'HOTEL AVEC RESTAURANT ( TOURISTIQUE OU NON)'),
(902, '601102', NULL, NULL, 'HOTELS DE TOURISME SANS RESTAURANT'),
(903, '601103', NULL, NULL, 'AUBERGES DE JEUNESSE ET REFUGES A BUT LUCRATIF'),
(904, '601104', NULL, NULL, 'EXPLOITATION DE TERRAINS DE CAMPING'),
(905, '601105', NULL, NULL, 'VILLAGES ET CENTRES DE VACANCES'),
(906, '601106', NULL, NULL, 'INSTALLATION POUR HEBERGEMENT COLLECTIF NON TOURISTIQUE'),
(907, '601107', NULL, NULL, 'PENSION DE FAMILLE'),
(908, '601108', NULL, NULL, 'HOTEL, BAR, RESTAURANT'),
(909, '601109', NULL, NULL, 'MOTEL'),
(910, '601110', NULL, NULL, 'DORTOIR'),
(911, '601201', NULL, NULL, 'RESTAURATION COMPLETE (RESTAURANT)'),
(912, '601202', NULL, NULL, 'RESTAURATION RAPIDE (FAST - FOOD)'),
(913, '601203', NULL, NULL, 'RESTAURANT, CAFE (RELAIS ROUTIERS)'),
(914, '601204', NULL, NULL, 'CREMERIE, GLACES ET SORBETS'),
(915, '601205', NULL, NULL, 'ROTISSERIE'),
(916, '601206', NULL, NULL, 'KIOSQUE A BOISSONS, A BEIGNETS ET A GLACES'),
(917, '601207', NULL, NULL, 'CAFE - RESTAURANT'),
(918, '601208', NULL, NULL, 'COLLECTEUR DE LINGE'),
(919, '601209', NULL, NULL, 'BAR RESTAURANT'),
(920, '601301', NULL, NULL, 'CAFE'),
(921, '601302', NULL, NULL, 'DEBITS DE BOISSONS ALCOOLISEES'),
(922, '601303', NULL, NULL, 'SALON DE THE'),
(923, '601304', NULL, NULL, 'EXPLOITATION DE DISTRIBUTEURS AUTOMATIQUES DE CAFE ET DE BOISSONS'),
(924, '601401', NULL, NULL, 'RAVITAILLEMENT DE BASES DE VIE'),
(925, '601402', NULL, NULL, 'TRAITEUR'),
(926, '601403', NULL, NULL, 'AVITAILLEMENT'),
(927, '602101', NULL, NULL, 'PHARMACIE'),
(928, '602102', NULL, NULL, 'OPTICIEN LUNETIER'),
(929, '602103', NULL, NULL, 'ACTIVITES PRIVEES D\'HOSPITALISATION (CLINIQUES ET CENTRES DE SOINS SPECIALISES)'),
(930, '602104', NULL, NULL, 'SERVICE PRIVE D\'AMBULANCES'),
(931, '602105', NULL, NULL, 'LABORATOIRE D\'ANALYSES'),
(932, '602106', NULL, NULL, 'PROTHESE MEDICALE'),
(933, '602107', NULL, NULL, 'ACTIVITES THERMALES ET DE THALASSOTHERAPIE'),
(934, '602108', NULL, NULL, 'HERBORISTE'),
(935, '602109', NULL, NULL, 'SERVICES FUNERAIRES'),
(936, '602110', NULL, NULL, 'IMAGERIE MEDICALE'),
(937, '602111', NULL, NULL, 'MECANICIEN DENTISTE ( PROTHESE DENTAIRE)'),
(938, '602112', NULL, NULL, 'INSTALLATION ET AMENAGEMENT DE TOUTES COMMODITES DESTINEES AUX PERSONNES HANDICAPEES'),
(939, '602201', NULL, NULL, 'DRESSAGE ET TOILETTAGE D\'ANIMAUX DE COMPAGNIE ET DE CHIENS'),
(940, '602202', NULL, NULL, 'ETABLISSEMENT DE DETENTION D\'ANIMAUX NON DOMESTIQUES, PARC ZOOLOGIQUE'),
(941, '602203', NULL, NULL, 'DRESSAGE DES ANIMAUX POUR LES COURSES'),
(942, '603001', NULL, NULL, 'GARAGES'),
(943, '603002', NULL, NULL, 'AIRE DE STATIONNEMENT AMENAGE (PARKING)'),
(944, '603003', NULL, NULL, 'LOCATION DE SALLES'),
(945, '603004', NULL, NULL, 'LOCATION DE VEHICULES AVEC OU SANS CHAUFFEUR'),
(946, '603005', NULL, NULL, 'LOCATION DE BATEAUX DE PLAISANCE ET BARQUES'),
(947, '603006', NULL, NULL, 'LOCATION D\'ENGINS, MACHINES ET EQUIPEMENTS AGRICOLES'),
(948, '603007', NULL, NULL, 'LOCATION D\'ENGINS ET MATERIELS POUR LE BATIMENT ET TRAVAUX PUBLICS'),
(949, '603008', NULL, NULL, 'LOCATION D\'EQUIPEMENT INFORMATIQUEET DE BUREAU'),
(950, '603009', NULL, NULL, 'LOCATION DE MACHINES ET EQUIPEMENTS DIVERS'),
(951, '603010', NULL, NULL, 'LOCATION DE MATERIEL DE PESAGE'),
(952, '603011', NULL, NULL, 'LOCATION DE CYCLES ET MOTOCYCLES'),
(953, '603012', NULL, NULL, 'LOCATION DE MATERIEL ET D\'EQUIPEMENT DE CAMPING'),
(954, '603013', NULL, NULL, 'LOCATION DE MATERIEL POUR FETES ET SPECTACLES'),
(955, '603014', NULL, NULL, 'LOCATIONS DIVERSES, NON DETERMINEES AILLEURS'),
(956, '604101', NULL, NULL, 'TRANSPORT COLLECTIF DE VOYAGEURS EN ZONES RURALES'),
(957, '604102', NULL, NULL, 'TRANSPORT COLLECTIF DE VOYAGEURS EN ZONES URBAINES, PERIPHERIQUES ET INTER-WILAYA'),
(958, '604103', NULL, NULL, 'ENTREPRISE DE GESTION DE TAXIS'),
(959, '604104', NULL, NULL, 'AUTRES TRANSPORTS PARTICULIERS DE VOYAGEURS'),
(960, '604105', NULL, NULL, 'TRANSPORT SUR TOUTES DISTANCES DE MARCHANDISES'),
(961, '604106', NULL, NULL, 'TRANSPORT ET LIVRAISON, SOUS FROID,DE PRODUITS ET DENREES ALIMENTAIRES'),
(962, '604107', NULL, NULL, 'ENTREPRISE D\'APPROVISIONNEMENT EN EQUIPEMENTS, MATERIELS ET PRODUITS ALIMENTAIRES, CAFES, RESTAURANTS ET COLLECTIVITES'),
(963, '604108', NULL, NULL, 'EXPLOITATION DE LIGNES DE TRANSPORT TELEPHERIQUE'),
(964, '604109', NULL, NULL, 'TRANSPORT ET DISTRIBUTION DE TOUTES MARCHANDISES'),
(965, '604110', NULL, NULL, 'TRANSPORT PUBLIC ROUTIER NATIONAL ET INTERNATIONAL DE VOYAGEURS'),
(966, '604111', NULL, NULL, 'TRANSPORT ET DISTRIBUTION DE PRODUITS PETROLIERS'),
(967, '604112', NULL, NULL, 'TRANSPORT ET LIVRAISON DU GPL'),
(968, '604201', NULL, NULL, 'TRANSPORT FERROVIAIRE DE VOYAGEURS'),
(969, '604202', NULL, NULL, 'TRANSPORT FERROVIAIRE DE MARCHANDISES'),
(970, '604301', NULL, NULL, 'TRANSPORT AERIEN DE PERSONNES'),
(971, '604302', NULL, NULL, 'TRANSPORT AERIEN DE MARCHANDISES'),
(972, '604303', NULL, NULL, 'SERVICES AERIENS DE TRANSPORT'),
(973, '604304', NULL, NULL, 'SERVICES AERIENS POUR L\'AGRICULTURE'),
(974, '604401', NULL, NULL, 'TRANSPORT MARITIME DE VOYAGEURS'),
(975, '604402', NULL, NULL, 'TRANSPORT MARITIME DE MARCHANDISES'),
(976, '604403', NULL, NULL, 'CABOTAGE MARITIME'),
(977, '604404', NULL, NULL, 'SERVICES DE VOYAGES MARITIMES'),
(978, '604501', NULL, NULL, 'TRANSPORT PAR PIPE-LINE ET AUTRES CONDUITES'),
(979, '604601', NULL, NULL, 'DEMENAGEMENT TOUTES DESTINATIONS (ENTREPRISE)'),
(980, '604602', NULL, NULL, 'MANUTENTION'),
(981, '604603', NULL, NULL, 'ENTREPOSAGE FRIGORIFIQUE'),
(982, '604604', NULL, NULL, 'STOCKAGE DE MARCHANDISES'),
(983, '604605', NULL, NULL, 'MAGASINS GENERAUX (STOCKAGE SOUS DOUANE)'),
(984, '604606', NULL, NULL, 'GESTION D\'INFRASTRUCTURES DE TRANSPORT TERRESTRE'),
(985, '604607', NULL, NULL, 'ENTREPRISE DE SERVICES PORTUAIRES ET CONSIGNATAIRES DE NAVIRES ET DE MARCHANDISES'),
(986, '604608', NULL, NULL, 'SERVICES AEROPORTUAIRES'),
(987, '604609', NULL, NULL, 'AFFRETEMENT DE MOYENS DE TRANSPORTS DE MARCHANDISES ET DE VOYAGEURS'),
(988, '604610', NULL, NULL, 'REMORQUAGE ET SAUVETAGE DE NAVIRES'),
(989, '604611', NULL, NULL, 'STATIONS SERVICES'),
(990, '604612', NULL, NULL, 'AUTO-ECOLE'),
(991, '604613', NULL, NULL, 'ECOLES DE CONDUITE'),
(992, '604614', NULL, NULL, 'COURTIER DE FRET'),
(993, '604615', NULL, NULL, 'COURTAGE MARITIME, CONSIGNATAIRE DE NAVIRES ET DE CARGAISON'),
(994, '604616', NULL, NULL, 'COMMISSIONNAIRE DE TRANSPORT DE MARCHANDISES'),
(995, '604617', NULL, NULL, 'COMMISSIONNAIRE EN DOUANES'),
(996, '604618', NULL, NULL, 'FILLING STATIONS'),
(997, '604619', NULL, NULL, 'POMPES ET CUVES'),
(998, '604620', NULL, NULL, 'AVITAILLEMENT DE NAVIRES ET D\'AERONEFS EN CARBURANTS'),
(999, '604621', NULL, NULL, 'RELAIS ROUTIER'),
(1000, '604622', NULL, NULL, 'STATION DE LAVAGE'),
(1001, '604623', NULL, NULL, 'CENTRE EMPLISSEUR DE GAZ DE PETROLE LIQUEFIES'),
(1002, '604624', NULL, NULL, 'ECOLE DE PILOTAGE DES AERONEFS'),
(1003, '604625', NULL, NULL, 'ECOLE DE PILOTAGE DES NAVIRES'),
(1004, '604626', NULL, NULL, 'STATION DE GRAISSAGE MOBILE'),
(1005, '604627', NULL, NULL, 'SERVICES DE REMORQUAGE ET DEPANNAGE MOBILE'),
(1006, '604628', NULL, NULL, 'CONSIGNATION DE BAGAGES ET AUTRES'),
(1007, '605001', NULL, NULL, 'AGENCE DE PUBLICITE'),
(1008, '605002', NULL, NULL, 'AGENCES PHOTOGRAPHIQUES'),
(1009, '605003', NULL, NULL, 'ENTREPRISE DES FOIRES ET EXPOSITIONS'),
(1010, '605004', NULL, NULL, 'ENTREPRISE CINEMATOGRAPHIQUE'),
(1011, '605005', NULL, NULL, 'DISTRIBUTION DE FILMS'),
(1012, '605006', NULL, NULL, 'EDITION ET DISTRIBUTION DE PRODUITS AUDIOVISUELS'),
(1013, '605007', NULL, NULL, 'ENTREPRISE DE RADIODIFFUSION'),
(1014, '605008', NULL, NULL, 'ACTIVITE DE TELEVISION'),
(1015, '605009', NULL, NULL, 'EXPLOITATION DE SALLES DE SPECTACLES, DE LOISIRS ET AUTRES DIVERTISSEMENTS'),
(1016, '605010', NULL, NULL, 'ENTREPRISE D\'EXPLOITATION DES PECTACLES PUBLICS'),
(1017, '605011', NULL, NULL, 'ENTREPRISE D\'ATTRACTIONS'),
(1018, '605012', NULL, NULL, 'AGENCE DE VOYAGE ET TOURISME'),
(1019, '605013', NULL, NULL, 'AGENCE DE PRESSE'),
(1020, '605014', NULL, NULL, 'ENTREPRISE DE PARI-SPORTIF ET LOTERIES ( RESERVE A L\'ETAT)'),
(1021, '605015', NULL, NULL, 'SALLE DE SPORTS'),
(1022, '605016', NULL, NULL, 'ETABLISSEMENT DE SPORTS NAUTIQUES ( A BUT LUCRATIF)'),
(1023, '605017', NULL, NULL, 'COMPLEXE SPORTIF (A BUT LUCRATIF)'),
(1024, '605018', NULL, NULL, 'GARDERIE D\'ENFANTS ET CRECHES'),
(1025, '605019', NULL, NULL, 'SALLE DE JEUX'),
(1026, '605020', NULL, NULL, 'STUDIO PHOTOGRAPHIQUE'),
(1027, '605021', NULL, NULL, 'AGENCE DE COMMUNICATION'),
(1028, '605022', NULL, NULL, 'ENTREPRISE DE PHOTOGRAVURE'),
(1029, '605023', NULL, NULL, 'ANIMATION DE FETES (DISK-JOKEY)'),
(1030, '605024', NULL, NULL, 'EXPOSITION DE TOUS PRODUITS, MATERIEL ET EQUIPEMENTS ( SHOW-ROOM)'),
(1031, '606101', NULL, NULL, 'CONTROLE TECHNIQUE AUTOMOBILE'),
(1032, '606102', NULL, NULL, 'ENTREPRISE DE CONTROLE TECHNIQUE'),
(1033, '606103', NULL, NULL, 'LABORATOIRES DE CONTROLE DE QUALITE ET DE CONFORMITE'),
(1034, '606104', NULL, NULL, 'ENTREPRISE D\'ETALONNAGE ET DE VERIFICATION DES MACHINES ET APPAREILS D\'ESSAIS MECANIQUE ET D\'INSTRUMENTS DE MESURE'),
(1035, '606201', NULL, NULL, 'ENTREPRISE DE NETTOYAGE, D\'ENTRETIEN ET DE DESINFECTION'),
(1036, '606202', NULL, NULL, 'ENLEVEMENT ET TRAITEMENT DES DECHETS'),
(1037, '606203', NULL, NULL, 'EPURATION DES EAUX USEES'),
(1038, '607001', NULL, NULL, 'BUREAU D\'ETUDE ET DE CONSEIL EN INFORMATIQUE (CONSULTING)'),
(1039, '607002', NULL, NULL, 'INSTALLATION DE RESEAUX ET TRAITEMENT DE DONNEES'),
(1040, '607003', NULL, NULL, 'ENTREPRISE FIDUCIAIRE'),
(1041, '607004', NULL, NULL, 'BUREAU DE CONSEIL JURIDIQUE'),
(1042, '607005', NULL, NULL, 'ADMINISTRATION D\'ENTREPRISES DE TOUS SECTEURS D\'ACTIVITES (HOLDING)'),
(1043, '607006', NULL, NULL, 'BUREAU D\'ETUDES EN ORGANISATION, ETUDES DE MARCHES ET SONDAGES'),
(1044, '607007', NULL, NULL, 'BUREAU D\'INGENIERIE ET D\'ETUDEST TECHNIQUES'),
(1045, '607008', NULL, NULL, 'ENTREPRISE DE COMPTABILITE'),
(1046, '607009', NULL, NULL, 'ENTREPRISE D\'ARCHITECTURE'),
(1047, '607010', NULL, NULL, 'CABINET DE GEOMETRES METREURS'),
(1048, '607011', NULL, NULL, 'ENTREPRISE DE TRAVAIL TEMPORAIRE ET DE SELECTION DU PERSONNEL'),
(1049, '607012', NULL, NULL, 'ENTREPRISE DE GARDIENNAGE ET DE SECURITE'),
(1050, '607013', NULL, NULL, 'ENTREPRISES D\'AFFACTURAGE (FACTORING)'),
(1051, '607014', NULL, NULL, 'ENTREPRISES DE FORMATION ET D\'ENSEIGNEMENT DIVERS'),
(1052, '607015', NULL, NULL, 'SOCIETE D\'EXPERTISE TECHNIQUE ET DE COMMISSARIAT D\'AVARIES'),
(1053, '607016', NULL, NULL, 'SOCIETE DE PROMOTION ET D\'INFORMATION MEDICALE ET SCIENTIFIQUE SUR LES PRODUITS PHARMACEUTIQUES'),
(1054, '607017', NULL, NULL, 'CABINET CONSEIL, ETUDES ET ASSISTANCE EN INVESTISSEMENT'),
(1055, '607018', NULL, NULL, 'ENTREPRISE D\'ORGANISATION DES MANIFESTATIONS CULTURELLES, ECONOMIQUES ET SCIENTIFIQUES'),
(1056, '607019', NULL, NULL, 'ENTREPRISE D\'INGENIERIE EN INFORMATIQUE INDUSTRIELLE ET PRODUCTIQUE'),
(1057, '607020', NULL, NULL, 'ENTREPRISE DE DECORATION'),
(1058, '607021', NULL, NULL, 'ENTREPRISE DE SERVICES PETROLIERS'),
(1059, '607022', NULL, NULL, 'ENTREPRISE DE DISTRIBUTION DES PRODUITS PETROLIERS'),
(1060, '607023', NULL, NULL, 'ENTREPRISE DE SERVICE HOTELIERS ET DE RESTAURATION (CATERING)'),
(1061, '607024', NULL, NULL, 'ENTREPRISE D\'ETUDES ET DE REALISATION DE PROGRAMMES DE PREVENTION ET D\'ASSAINISSEMENT DE L\'ENVIRONNEMENT'),
(1062, '607025', NULL, NULL, 'ENTREPRISE D\'EXPLOITATION DES SERVICES INTERNET'),
(1063, '607026', NULL, NULL, 'CYBER- CAFE'),
(1064, '607028', NULL, NULL, 'CONSULTING ET ASSISTANCE AUX ENTRPRISES NATIONALES ET INTERNATIONALES DANS LES DOMAINES DE L\'INDUSTRIE ET DE L\'ENERGIE'),
(1065, '607029', NULL, NULL, 'ETUDES, CONSULTING ET ASSISTANCE DANS LE DOMAINE AGRICOLE'),
(1066, '607030', NULL, NULL, 'CONSEIL , ASSISTANCE , EVALUATION ET CONCEPTION DE PROGRAMME DE FORMATION'),
(1067, '608001', NULL, NULL, 'CONDITIONNEMENT ET EMBALLAGE DE PRODUITS ET DENREES ALIMENTAIRES'),
(1068, '608002', NULL, NULL, 'CONDITIONNEMENT ET EMBALLAGE DE MATIERES PREMIERES TEXTILES'),
(1069, '608003', NULL, NULL, 'CONDITIONNEMENT ET EMBALLAGE DE PRODUITS CHIMIQUES ET ENGRAIS'),
(1070, '608004', NULL, NULL, 'CONDITIONNEMENT DE PRODUITS DIVERS NDA'),
(1071, '608005', NULL, NULL, 'CONDITIONNEMENT DE PRODUITS PHARMACEUTIQUES'),
(1072, '609001', NULL, NULL, 'ENTREPRISE DE TRAVAUX DE SECRETARIAT ET ASSISTANCE ADMINISTRATIVE'),
(1073, '609002', NULL, NULL, 'TIRAGE DE PLANS, PHOTOCOPIES DIVERSES'),
(1074, '609003', NULL, NULL, 'CONFECTION DE CACHETS ET DE GRIFFES DE SIGNATURES'),
(1075, '610001', NULL, NULL, 'ENTREPRISE DE COURRIER EXPRESS'),
(1076, '610002', NULL, NULL, 'MESSAGERIE OU ENTREPRISE DE PRESSE'),
(1077, '610003', NULL, NULL, 'ENTREPRISE D\'EDITION ET DE DIFFUSION'),
(1078, '610004', NULL, NULL, 'ENTREPRISE DES RESEAUX DE TELECOMMUNICATION'),
(1079, '610005', NULL, NULL, 'TAXIPHONE'),
(1080, '610006', NULL, NULL, 'GESTION DE BOITES POSTALES (CEDEX)'),
(1081, '611001', NULL, NULL, 'LOCATION DE BIENS IMMOBILIERS'),
(1082, '611002', NULL, NULL, 'LOCATION DE TERRAINS'),
(1083, '611003', NULL, NULL, 'LOCATION DE STRUCTURES COMMERCIALES'),
(1084, '611004', NULL, NULL, 'AGENCE IMMOBILIERE'),
(1085, '611005', NULL, NULL, 'ENTREPRISE DE LOCATION ET DE GESTION D\'INFRASTRUCTURES COMMERCIALES'),
(1086, '611006', NULL, NULL, 'ADMINISTRATION DE BIENS IMMOBILIERS'),
(1087, '611007', NULL, NULL, 'GESTION DE ZONES INDUSTRIELLES'),
(1088, '611008', NULL, NULL, 'AGENCE FONCIERE'),
(1089, '612101', NULL, NULL, 'ENTREPRISE DE CREDIT BAIL'),
(1090, '612103', NULL, NULL, 'BANQUE'),
(1091, '612104', NULL, NULL, 'CAISSE D\'EPARGNE ET DE PREVOYANCE'),
(1092, '612105', NULL, NULL, 'ENTREPRISE D\'ASSURANCES'),
(1093, '612106', NULL, NULL, 'SOCIETE DE GESTION DE LA BOURSE DES VALEURS MOBILIERES (SPA)'),
(1094, '612107', NULL, NULL, 'INTERMEDAIRES EN OPERATIONS DE BOURSE'),
(1095, '612201', NULL, NULL, 'BUREAU DE CHANGE'),
(1096, '612202', NULL, NULL, 'AGENT DE CHANGE'),
(1097, '612203', NULL, NULL, 'COURTIER D\'ASSURANCES OU SOCIETE DE COURTAGE D\'ASSURANCE'),
(1098, '612204', NULL, NULL, 'AGENT GENERAL D\'ASSURANCES'),
(1099, '612205', NULL, NULL, 'BUREAU D\'AFFAIRES'),
(1100, '612206', NULL, NULL, 'REPRESENTANT DE COMMERCE'),
(1101, '613101', NULL, NULL, 'INSTALLATION ET REPARATION D\'EQUIPEMENTS UTILISES DANS L\'INDUSTRIE AGRO-ALIMENTAIRE'),
(1102, '613102', NULL, NULL, 'REPARATION DE MATERIELS AGRICOLES'),
(1103, '613103', NULL, NULL, 'REPARATION DE MATERIEL ET MACHINES POUR BTP'),
(1104, '613104', NULL, NULL, 'INSTALLATION ET REPARATION DES MACHINES UTILISEES DANS L\'INDUSTRIE DE TRANSFORMATION DU PAPIER ET DU CARTON'),
(1105, '613105', NULL, NULL, 'INSTALLATION ET REPARATION DES MACHINES D\'IMPRIMERIES'),
(1106, '613106', NULL, NULL, 'INSTALLATION ET REPARATION DES MATERIELS DE COMPOSITION MECANIQUE'),
(1107, '613107', NULL, NULL, 'INSTALLATION ET REPARATION DEMATERIELS DE PHOTOCOMPOSITION'),
(1108, '613108', NULL, NULL, 'INSTALLATION ET REPARATION DE MATERIELS DE LABORATOIRE D\'IMPRIMERIE'),
(1109, '613109', NULL, NULL, 'REPARATION DE MACHINES-OUTILS'),
(1110, '613110', NULL, NULL, 'INSTALLATIONS ELECTRIQUES INDUSTRIELLES ET MAINTENANCE'),
(1111, '613111', NULL, NULL, 'INSTALLATION ET REPARATION DE CHAUDIERES, EQUIPEMENTS ET MATERIELS DE CHAUFFAGE'),
(1112, '613112', NULL, NULL, 'REPARATION DE GENERATEURS DE VAPEUR D\'EAU'),
(1113, '613113', NULL, NULL, 'REPARATION MECANIQUE DE MATERIEL LOURD DE TRANSPORT ROUTIER'),
(1114, '613114', NULL, NULL, 'REPARATION DE MATERIELS HYDROMECANIQUES'),
(1115, '613115', NULL, NULL, 'MECANIQUE DIESEL ( REPARATION ET RENOVATION)'),
(1116, '613116', NULL, NULL, 'REPARATION D\'EQUIPEMENTS ELECTRIQUES ET ELECTRONIQUES'),
(1117, '613117', NULL, NULL, 'INSTALLATION ET REPARATION D\'EQUIPEMENTS DE REFRIGERATION ET DE CLIMATISATION'),
(1118, '613118', NULL, NULL, 'INSTALLATION ET REPARATION D\'EQUIPEMENTS ET D\'APPAREILLAGES MEDIC0-CHIRURGICAUX'),
(1119, '613119', NULL, NULL, 'REPARATION DE BALANCES, BASCULES ET APPAREILS DE MESURES'),
(1120, '613120', NULL, NULL, 'REPARATION D\'INSTRUMENTS DE PRECISION ET D\'OPTIQUE'),
(1121, '613121', NULL, NULL, 'REPARATION DE MACHINES A COUDRE INDUSTRIELLES'),
(1122, '613122', NULL, NULL, 'REPARATION DES METIERS A TISSER ETA TRICOTER INDUSTRIELS'),
(1123, '613123', NULL, NULL, 'INSTALLATION ET REPARATION D\'EQUIPEMENTS ET MATERIELS DE GRANDE CUISINE'),
(1124, '613124', NULL, NULL, 'REPARATION DE MATERIEL ET EQUIPEMENTS DE BOULANGERIE'),
(1125, '613125', NULL, NULL, 'INSTALLATION ET REPARATION DE MATERIEL DE SECURITE ET DE PROTECTION CONTRE L\'INCENDIE ET LE LEVOL'),
(1126, '613126', NULL, NULL, 'REPARATION DE POMPES'),
(1127, '613127', NULL, NULL, 'INSTALLATION ET REPARATION DE FOURS ET REFRACTAIRES DE FOURS'),
(1128, '613128', NULL, NULL, 'INSTALLATION ET MAINTENANCE INDUSTRIELLE DE TOUS EQUIPEMENTS, MOTEURS ET MATERIELS'),
(1129, '613129', NULL, NULL, 'INSTALLATION , REPARATION ET MAINTENANCE DE MOTEURS ET MATERIELS MARINS');
INSERT INTO `t_02_03_01` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`) VALUES
(1130, '613130', NULL, NULL, 'INSTALLATION ET MAINTENANCE DES EQUIPEMENTS ET MATERIELS POUR HOTELS, CAFES, RESTAURANTS, BLANCHISSERIES ET TEINTURERIES'),
(1131, '613131', NULL, NULL, 'ENTREPRISE DE CONTROLE DE SOUDAGE'),
(1132, '613132', NULL, NULL, 'INSTALLATION ET MONTAGE D\'ACCESSOIRES AUTOMOBILES'),
(1133, '613133', NULL, NULL, 'INSTALLATION ET REPARATION DE MATERIEL ET EQUIPEMENTS LIES AU DOMAINE DE L\'ENERGIE SOLAIRE ET ELECTRIQUE.'),
(1134, '613201', NULL, NULL, 'REPARATION D\'EQUIPEMENTS ET MATERIELS DE BUREAUTIQUE'),
(1135, '613202', NULL, NULL, 'REPARATION D\'EQUIPEMENTS CINEMATOGRAPHIQUES ET PHOTOGRAPHIQUES'),
(1136, '613203', NULL, NULL, 'REPARATION ET ENTRETIEN DES ASCENSEURS ET AUTRES APPAREILS SIMILAIRES.'),
(1137, '613204', NULL, NULL, 'REPARATION MECANIQUE DE VEHICULES AUTOS, REPARATION SPECIALISEE DE PARTIES ET PIECES MECANIQUES POUR TOUS VEHICULES'),
(1138, '613205', NULL, NULL, 'REPARATION DE CYCLES ET MOTOCYCLES'),
(1139, '613206', NULL, NULL, 'REPARATION D\'ACCUMULATEURS'),
(1140, '613207', NULL, NULL, 'MECANIQUE GENERALE'),
(1141, '613208', NULL, NULL, 'REPARATION ELECTRICITE-AUTO'),
(1142, '613209', NULL, NULL, 'ELECTRICITE AUTOMOBILE'),
(1143, '613210', NULL, NULL, 'TOLERIE ET PEINTURE AUTOMOBILE.'),
(1144, '613211', NULL, NULL, 'REPARATION DE RADIATEURS'),
(1145, '613212', NULL, NULL, 'REPARATION DE RIDEAUX EN FER POUR LOCAUX COMMERCIAUX'),
(1146, '613213', NULL, NULL, 'FUMISTERIE ET RAMONAGE NON INDUSTRIELS'),
(1147, '613214', NULL, NULL, 'REPARATION DE BARQUES ET AUTRES EMBARCATIONS'),
(1148, '613215', NULL, NULL, 'INSTALLATION ET REPARATION DES EQUIPEMENTS PORTUAIRES, DE MANUTENTION, DE LEVAGE ET DES MOYENS DE SAUVETAGE MARITIME'),
(1149, '613216', NULL, NULL, 'INSTALLATION, REPARATION ET MAINTENANCE DE TOUT EQUIPEMENT ET EXPLOITATION FONCTIONNANT AU GAZ'),
(1150, '613217', NULL, NULL, 'INSTALLATION ET REPARATION D\'APPAREILS ELECTROMENAGERS'),
(1151, '613218', NULL, NULL, 'INSTALLATION ET REPARATION DE KIT DE CONVERSION DES VEHICULES EN G.P.L'),
(1152, '613219', NULL, NULL, 'AJUSTAGE, TOURNAGE, FRAISAGE'),
(1153, '613220', NULL, NULL, 'EQUILIBRAGE, PARALLELISME DE ROUES'),
(1154, '614001', NULL, NULL, 'COIFFURE ET SOINS DE BEAUTE'),
(1155, '614002', NULL, NULL, 'HAMMAM, SAUNA'),
(1156, '614003', NULL, NULL, 'DOUCHES'),
(1157, '614004', NULL, NULL, 'DEGRAISSAGE, TEINTURERIE, BLANCHISSERIE'),
(1158, '614005', NULL, NULL, 'INSTITUT DE SOINS CORPORELLES'),
(1159, '615001', NULL, NULL, 'REPRESENTATION OU AGENCE COMMERCIALE DES ETATS ET COLLECTIVITES ETRANGERES'),
(1160, '615002', NULL, NULL, 'REPRESENTATION OU AGENCE COMMERCIALE DES ETABLISSEMENTS PUBLICS ETRANGERS'),
(1161, '615003', NULL, NULL, 'CHAMBRE ALGERIENNE DE COMMERCE ET D\'INDUSTRIE (CACI)'),
(1162, '615004', NULL, NULL, 'CHAMBRE DE COMMERCE ET D\'INDUSTRIE ( CCI )'),
(1163, '615005', NULL, NULL, 'CHAMBRE NATIONALE DE L\'ARTISANAT ET DES METIERS ( CNAM )'),
(1164, '615006', NULL, NULL, 'CHAMBRE REGIONALE DE L\'ARTISANAT ET DES METIERS'),
(1165, '615007', NULL, NULL, 'CHAMBRE NATIONALE DE L\'AGRICULTURE'),
(1166, '615008', NULL, NULL, 'CHAMBRE REGIONALE DE L\'AGRICULTURE'),
(1167, '615009', NULL, NULL, 'AGENCE NATIONALE DE L\'ARTISANAT TRADITIONNEL'),
(1168, '615010', NULL, NULL, 'PROMOTION DES PRODUITS DE L\'ARTISANAT'),
(1169, '615011', NULL, NULL, 'FONDS NATIONAL DE PEREQUATION DES OEUVRES SOCIALES'),
(1170, '615012', NULL, NULL, 'CAISSE NATIONALE DE LOGEMENT (CNL)'),
(1171, '616001', NULL, NULL, 'ENTREPRISE DE COULAGE DE BETON'),
(1172, '616002', NULL, NULL, 'AMENAGEMENT DES ESPACES VERTS'),
(1173, '616003', NULL, NULL, 'SERVICES RELATIFS A L\'UTILISATION DE L\'ELECTRICITE ET LE GAZ'),
(1174, '616004', NULL, NULL, 'GESTION ET EXPLOITATION DES HOTELS ET DES AGENCES DE TOURISME ET DE VOYAGES');

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
  `F_18` bigint(20) DEFAULT NULL,
  `F_21` bigint(20) DEFAULT NULL
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
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_03_01_01`
--

INSERT INTO `t_03_01_01` (`F_00`, `F_01`, `F_02`, `F_03`) VALUES
(1, 'مشغل عام وطني', 'National Public Operator', 'Opérateur Public National'),
(2, 'مشغل خاص وطني', 'National Private Operator', 'Opérateur Privé National'),
(3, 'مشغل دولي', 'International Operator', 'Opérateur International'),
(4, 'مشروع مشترك', 'Joint Venture', 'Coentreprise'),
(5, 'شركة نقل', 'Transport Company', 'Société de Transport'),
(6, 'شركة تكرير', 'Refining Company', 'Société de Raffinage'),
(7, 'شركة توزيع', 'Distribution Company', 'Société de Distribution'),
(8, 'شركة هندسية', 'Engineering Company', 'Société d\'Ingénierie'),
(9, 'مالك البنية التحتية', 'Infrastructure Owner', 'Propriétaire d\'Infrastructure'),
(10, 'مقاول EPC', 'EPC Contractor', 'Entrepreneur EPC'),
(11, 'مقاول بناء', 'Construction Contractor', 'Entrepreneur de Construction'),
(12, 'مستشار هندسي', 'Engineering Consultant', 'Consultant en Ingénierie'),
(13, 'إدارة مشاريع', 'Project Management', 'Gestion de Projet'),
(14, 'مصنع معدات', 'Equipment Manufacturer', 'Fabricant d\'Équipements'),
(15, 'مصنع أنابيب', 'Pipe Manufacturer', 'Fabricant de Tubes'),
(16, 'مورد أنظمة أتمتة', 'Automation Vendor', 'Fournisseur d\'Automatisation'),
(17, 'مورد أجهزة قياس', 'Instrumentation Vendor', 'Fournisseur d\'Instrumentation'),
(18, 'شركة فحص', 'Inspection Company', 'Société d\'Inspection'),
(19, 'هيئة اعتماد', 'Certification Body', 'Organisme de Certification'),
(20, 'مزود خدمة صيانة', 'Maintenance Service', 'Service de Maintenance'),
(21, 'مزود خدمات لوجستية', 'Logistics Provider', 'Prestataire Logistique'),
(22, 'مقاول محلي', 'Local Subcontractor', 'Sous-Traitant Local');

-- --------------------------------------------------------

--
-- Structure de la table `t_03_01_02`
--

CREATE TABLE `t_03_01_02` (
  `F_00` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_03_01_02`
--

INSERT INTO `t_03_01_02` (`F_00`) VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9);

-- --------------------------------------------------------

--
-- Structure de la table `t_03_01_03`
--

CREATE TABLE `t_03_01_03` (
  `F_00` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_03_01_03`
--

INSERT INTO `t_03_01_03` (`F_00`) VALUES
(10),
(11),
(12),
(13),
(14),
(15),
(16),
(17),
(18),
(19),
(20),
(21),
(22);

-- --------------------------------------------------------

--
-- Structure de la table `t_03_01_04`
--

CREATE TABLE `t_03_01_04` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_03_01_04`
--

INSERT INTO `t_03_01_04` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`) VALUES
(1, 'PUMP-STN', 'محطة ضخ', 'Pumping Station', 'Station de Pompage'),
(2, 'COMP-STN', 'محطة ضغط', 'Compression Station', 'Station de Compression'),
(3, 'PR-STN', 'محطة خفض الضغط', 'Pressure Reduction Station', 'Station de Détente'),
(4, 'PREG-STN', 'محطة تنظيم الضغط', 'Pressure Regulation Station', 'Station de Régulation de Pression'),
(5, 'METER-STN', 'محطة قياس', 'Metering Station', 'Station de Comptage'),
(6, 'CUTOFF-STN', 'محطة قطع', 'Cut-Off Station', 'Poste de Coupure'),
(7, 'SECT-STN', 'محطة تقسيم', 'Sectionalizing Station', 'Poste de Sectionnement'),
(8, 'EXPORT-TERM', 'محطة تصدير', 'Export Terminal', 'Terminal d\'Exportation'),
(9, 'IMPORT-TERM', 'محطة استيراد', 'Import Terminal', 'Terminal d\'Importation'),
(10, 'STORAGE-TERM', 'محطة تخزين', 'Storage Terminal', 'Terminal de Stockage'),
(11, 'TRANSFER-TERM', 'محطة نقل', 'Transfer Terminal', 'Terminal de Transfert'),
(12, 'DIST-TERM', 'محطة توزيع', 'Distribution Terminal', 'Terminal de Distribution'),
(13, 'REFINERY-TERM', 'محطة واجهة المصفاة', 'Refinery Interface Terminal', 'Terminal Interface Raffinerie'),
(14, 'LNG-TERM', 'محطة الغاز الطبيعي المسال', 'LNG Terminal', 'Terminal GNL'),
(15, 'LPG-TERM', 'محطة غاز البترول المسال', 'LPG Terminal', 'Terminal GPL'),
(16, 'COND-TERM', 'محطة المكثفات', 'Condensate Terminal', 'Terminal de Condensat'),
(17, 'OIL-FIELD', 'حقل نفطي', 'Oil Field', 'Champ Pétrolier'),
(18, 'GAS-FIELD', 'حقل غاز', 'Gas Field', 'Champ Gazier'),
(19, 'COND-FIELD', 'حقل مكثفات', 'Condensate Field', 'Champ de Condensat');

-- --------------------------------------------------------

--
-- Structure de la table `t_03_01_05`
--

CREATE TABLE `t_03_01_05` (
  `F_00` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_03_01_05`
--

INSERT INTO `t_03_01_05` (`F_00`) VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7);

-- --------------------------------------------------------

--
-- Structure de la table `t_03_01_06`
--

CREATE TABLE `t_03_01_06` (
  `F_00` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_03_01_06`
--

INSERT INTO `t_03_01_06` (`F_00`) VALUES
(8),
(9),
(10),
(11),
(12),
(13),
(14),
(15),
(16);

-- --------------------------------------------------------

--
-- Structure de la table `t_03_01_07`
--

CREATE TABLE `t_03_01_07` (
  `F_00` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_03_01_07`
--

INSERT INTO `t_03_01_07` (`F_00`) VALUES
(17),
(18),
(19);

-- --------------------------------------------------------

--
-- Structure de la table `t_03_01_08`
--

CREATE TABLE `t_03_01_08` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_03_01_08`
--

INSERT INTO `t_03_01_08` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`) VALUES
(1, 'T', 'توربين', 'Turbine', 'Turbine'),
(2, 'M', 'محرك', 'Motor', 'Moteur'),
(3, 'EM', 'محرك كهربائي', 'Electric Motor', 'Moteur Électrique'),
(4, 'P', 'مضخة', 'Pump', 'Pompe'),
(5, 'TP', 'مضخة توربينية', 'Turbo-Pump', 'Turbo-Pompe'),
(6, 'EP', 'مضخة كهربائية', 'Electro-Pump', 'Électro-Pompe'),
(7, 'B', 'مضخة تعزيز', 'Booster', 'Booster'),
(8, 'C', 'ضاغط', 'Compressor', 'Compresseur'),
(9, 'TC', 'ضاغط توربيني', 'Turbo-Compressor', 'Turbo-Compresseur'),
(10, 'EC', 'ضاغط كهربائي', 'Electric Compressor', 'Compresseur Électrique'),
(11, 'TA', 'مولد توربيني', 'Turbo-Alternator', 'Turbo-Alternateur'),
(12, 'GE', 'مجموعة كهربائية', 'Generator Set', 'Groupe Électrogène'),
(13, 'HX', 'مبادل حراري', 'Heat Exchanger', 'Échangeur Thermique'),
(14, 'SEP', 'فاصل', 'Separator', 'Séparateur'),
(15, 'TK', 'خزان تخزين', 'Storage Tank', 'Réservoir de Stockage'),
(16, 'PV', 'وعاء ضغط', 'Pressure Vessel', 'Réservoir sous Pression'),
(17, 'CV', 'صمام تحكم', 'Control Valve', 'Vanne de Régulation'),
(18, 'IV', 'صمام عزل', 'Isolation Valve', 'Vanne d\'Isolement'),
(19, 'SV', 'صمام أمان', 'Safety Valve', 'Soupape de Sûreté'),
(20, 'CHV', 'صمام فحص', 'Check Valve', 'Clapet Anti-Retour'),
(21, 'FM', 'مقياس تدفق', 'Flow Meter', 'Débitmètre'),
(22, 'PT', 'مرسل ضغط', 'Pressure Transmitter', 'Transmetteur de Pression'),
(23, 'TT', 'مرسل حرارة', 'Temperature Transmitter', 'Transmetteur de Température'),
(24, 'LT', 'مرسل مستوى', 'Level Transmitter', 'Transmetteur de Niveau'),
(25, 'ANA', 'محلل', 'Analyzer', 'Analyseur'),
(26, 'PLC', 'متحكم منطقي قابل للبرمجة', 'PLC', 'Automate Programmable'),
(27, 'RTU', 'وحدة طرفية بعيدة', 'RTU', 'Unité Terminale Distante'),
(28, 'DCS', 'نظام تحكم موزع', 'DCS', 'Système de Contrôle Distribué'),
(29, 'SCADA', 'نظام SCADA', 'SCADA System', 'Système SCADA'),
(30, 'CLR', 'مبرد', 'Cooler', 'Refroidisseur'),
(31, 'HTR', 'سخان', 'Heater', 'Réchauffeur'),
(32, 'FLT', 'مرشح', 'Filter', 'Filtre'),
(33, 'PIG', 'مطلق/مستقبل كاشطة', 'Scraper Launcher/Receiver', 'Lanceur/Récepteur de Racleur');

-- --------------------------------------------------------

--
-- Structure de la table `t_03_02_01`
--

CREATE TABLE `t_03_02_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(10) NOT NULL,
  `F_05` varchar(200) DEFAULT NULL,
  `F_06` varchar(200) DEFAULT NULL,
  `F_07` varchar(200) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_03_02_01`
--

INSERT INTO `t_03_02_01` (`F_00`, `F_01`, `F_05`, `F_06`, `F_07`, `F_02`, `F_03`, `F_04`) VALUES
(1, 'SUD', 'المنطقة الجنوبية للشبكة الوطنية للنقل', 'Southern zone of the national transport network', 'Zone sud du réseau national de transport', 'الجنوب', 'South', 'Sud'),
(2, 'NORD', 'المنطقة الشمالية للشبكة الوطنية للنقل', 'Northern zone of the national transport network', 'Zone nord du réseau national de transport', 'الشمال', 'North', 'Nord');

-- --------------------------------------------------------

--
-- Structure de la table `t_03_02_02`
--

CREATE TABLE `t_03_02_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(10) NOT NULL,
  `F_05` varchar(200) DEFAULT NULL,
  `F_06` varchar(200) DEFAULT NULL,
  `F_07` varchar(200) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL,
  `F_08` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_03_02_02`
--

INSERT INTO `t_03_02_02` (`F_00`, `F_01`, `F_05`, `F_06`, `F_07`, `F_02`, `F_03`, `F_04`, `F_08`) VALUES
(1, 'SCA', 'المقر الرئيسي المركزي لأنشطة النقل', 'Central headquarters for transport activities', 'Siège central pour les activités de transport', 'المقر المركزي للنشاط', 'Activity Central Headquarters', 'Siège Centrale de l\'Activité', 2),
(2, 'RTO', 'منطقة النقل الغربية للشبكة الوطنية', 'Western transport region of the national network', 'Région de transport ouest du réseau national', 'منطقة النقل الغربية', 'Western Transport Region', 'Region de Transport Ouest', 2),
(3, 'RTC', 'منطقة النقل المركزية للشبكة الوطنية', 'Central transport region of the national network', 'Région de transport centre du réseau national', 'منطقة النقل المركزية', 'Central Transport Region', 'Region de Transport Centre', 2),
(4, 'RTE', 'منطقة النقل الشرقية للشبكة الوطنية', 'Eastern transport region of the national network', 'Région de transport est du réseau national', 'منطقة النقل الشرقية', 'Eastern Transport Region', 'Region de Transport Est', 2),
(5, 'PDF', 'منطقة بيدرو دوران فاريل', 'Pedro Duran Farel region', 'Région Pedro Duran Farel', 'بيدرو دوران فاريل', 'Pedro Duran Farel', 'Pedro Duran Farel', 1),
(6, 'EM', 'منطقة إنريكو ماتي', 'Enrico Mattei region', 'Région Enrico Mattei', 'إنريكو ماتي', 'Enrico Mattei', 'Enrico Mattei', 1),
(7, 'HEH', 'منطقة حوض الحمراء للإنتاج', 'Haoud El Hamra production region', 'Région de production Haoud El Hamra', 'منطقة حوض الحمراء', 'Haoud El Hamra Region', 'Region Haoud El Hamra', 1),
(8, 'HRM', 'منطقة حاسي الرمل للغاز الطبيعي', 'Hassi R\'Mel natural gas region', 'Région de gaz naturel Hassi R\'Mel', 'منطقة حاسي الرمل', 'Hassi R\'Mel Region', 'Region Hassi R\'Mel', 1),
(9, 'RTI', 'منطقة النقل إليزي في الصحراء الشرقية', 'Illizi transport region in the eastern Sahara', 'Région de transport Illizi dans le Sahara oriental', 'منطقة النقل إليزي', 'Illizi Transport Region', 'Region de Transport Illizi', 1);

-- --------------------------------------------------------

--
-- Structure de la table `t_03_02_03`
--

CREATE TABLE `t_03_02_03` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(10) NOT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL,
  `F_07` double DEFAULT NULL,
  `F_05` double NOT NULL,
  `F_06` double NOT NULL,
  `F_08` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_03_02_04`
--

CREATE TABLE `t_03_02_04` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_05` double NOT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL,
  `F_07` double NOT NULL,
  `F_09` bit(1) NOT NULL,
  `F_08` double NOT NULL,
  `F_06` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_03_02_04`
--

INSERT INTO `t_03_02_04` (`F_00`, `F_01`, `F_05`, `F_02`, `F_03`, `F_04`, `F_07`, `F_09`, `F_08`, `F_06`) VALUES
(1, 'GN', 0.7, 'الغاز الطبيعي', 'Natural Gas', 'Gaz naturel', -188, b'1', 0.01, 0.01),
(2, 'PB', 0.85, 'النفط الخام', 'Crude Oil', 'Pétrole brut', -30, b'1', 1.5, 10.5),
(3, 'COND', 0.75, 'المكثفات', 'Condensate', 'Condensat', -40, b'1', 0.05, 0.8),
(4, 'GPL', 0.55, 'غاز البترول المسال', 'Liquefied Petroleum Gas', 'Gaz de Pétrole Liquéfié (GPL)', -104, b'1', 0.02, 0.15);

-- --------------------------------------------------------

--
-- Structure de la table `t_03_02_05`
--

CREATE TABLE `t_03_02_05` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL,
  `F_05` varchar(200) DEFAULT NULL,
  `F_06` varchar(200) DEFAULT NULL,
  `F_07` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_03_02_05`
--

INSERT INTO `t_03_02_05` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`, `F_05`, `F_06`, `F_07`) VALUES
(1, 'INSERVICE', 'قيد الخدمة', 'In Service', 'En Service', 'العمليات العادية', 'Normal operations', 'Opérations normales'),
(2, 'STOPPED', 'متوقف مؤقتا', 'Stopped', 'À l\'Arrêt', 'ظروف السوق، المرونة التشغيلية', 'Market conditions, operational flexibility', 'Conditions du marché, flexibilité opérationnelle'),
(3, 'SHUTDOWN', 'متوقف', 'Shutdown', 'Arrêté', 'صيانة مخططة أو حادث غير متوقع', 'Planned maintenance or unexpected incident', 'Maintenance planifiée ou incident imprévu'),
(4, 'MAINTENANCE', 'قيد الصيانة', 'Under Maintenance', 'En Maintenance', 'إصلاحات مجدولة، فحوصات', 'Scheduled repairs, inspections', 'Réparations programmées, inspections'),
(5, 'TESTING', 'قيد الاختبار', 'Under Testing', 'En Test', 'ما قبل التشغيل، فحوصات ما بعد الإصلاح', 'Pre-commissioning, post-repair verifications', 'Pré-mise en service, vérifications post-réparation'),
(6, 'REDUCED', 'طاقة مخفضة', 'Reduced Capacity', 'Capacité Réduite', 'عطل في المعدات، قيود تشغيلية', 'Equipment failure, operational constraints', 'Panne d\'équipement, contraintes opérationnelles'),
(7, 'EMERGENCY', 'حالة طوارئ / حادث', 'Emergency / Incident', 'Urgence / Incident', 'تسرب، انفجار، حادث أمني', 'Leak, rupture, security incident', 'Fuite, rupture, incident de sécurité'),
(8, 'DECOMMISSIONED', 'خارج الخدمة', 'Decommissioned', 'Mis Hors Service', 'نهاية العمر التشغيلي', 'End of operational life', 'Fin de vie opérationnelle');

-- --------------------------------------------------------

--
-- Structure de la table `t_03_02_06`
--

CREATE TABLE `t_03_02_06` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_05` varchar(200) DEFAULT NULL,
  `F_06` varchar(200) DEFAULT NULL,
  `F_07` varchar(200) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_03_02_06`
--

INSERT INTO `t_03_02_06` (`F_00`, `F_01`, `F_05`, `F_06`, `F_07`, `F_02`, `F_03`, `F_04`) VALUES
(1, 'API-5L-B', 'فولاذ كربوني منخفض المقاومة للأنابيب العامة، حد أدنى للخضوع 35 كيلو باوند/بوصة²', 'Low-strength carbon steel for general purpose pipelines, minimum yield 35 ksi', 'Acier au carbone de résistance faible pour pipelines usage général, limite élastique min 35 ksi', 'فولاذ API 5L درجة B', 'API 5L Grade B', 'Acier API 5L Grade B'),
(2, 'API-5L-X42', 'فولاذ متوسط المقاومة للضغوط المعتدلة، حد أدنى للخضوع 42 كيلو باوند/بوصة²', 'Medium-strength steel for moderate pressure applications, minimum yield 42 ksi', 'Acier de résistance moyenne pour applications à pression modérée, limite élastique min 42 ksi', 'فولاذ API 5L X42', 'API 5L Grade X42', 'Acier API 5L Grade X42'),
(3, 'API-5L-X52', 'فولاذ عالي المقاومة للأنابيب القياسية، حد أدنى للخضوع 52 كيلو باوند/بوصة²', 'High-strength steel for standard pipelines, minimum yield 52 ksi, widely used', 'Acier haute résistance pour pipelines standard, limite élastique min 52 ksi, très utilisé', 'فولاذ API 5L X52', 'API 5L Grade X52', 'Acier API 5L Grade X52'),
(4, 'API-5L-X60', 'فولاذ عالي المقاومة للنقل لمسافات طويلة، حد أدنى للخضوع 60 كيلو باوند/بوصة²', 'High-strength steel for long-distance transmission, minimum yield 60 ksi', 'Acier haute résistance pour transport longue distance, limite élastique min 60 ksi', 'فولاذ API 5L X60', 'API 5L Grade X60', 'Acier API 5L Grade X60'),
(5, 'API-5L-X65', 'فولاذ عالي المقاومة للأنظمة عالية الضغط، حد أدنى للخضوع 65 كيلو باوند/بوصة²', 'High-strength steel for high-pressure systems, minimum yield 65 ksi, excellent toughness', 'Acier haute résistance pour systèmes haute pression, limite élastique min 65 ksi, excellente ténacité', 'فولاذ API 5L X65', 'API 5L Grade X65', 'Acier API 5L Grade X65'),
(6, 'API-5L-X70', 'فولاذ فائق المقاومة للأنابيب الحديثة، حد أدنى للخضوع 70 كيلو باوند/بوصة²', 'Very high-strength steel for modern high-capacity pipelines, minimum yield 70 ksi', 'Acier très haute résistance pour pipelines modernes haute capacité, limite élastique min 70 ksi', 'فولاذ API 5L X70', 'API 5L Grade X70', 'Acier API 5L Grade X70'),
(7, 'API-5L-X80', 'فولاذ فائق المقاومة للظروف القصوى، حد أدنى للخضوع 80 كيلو باوند/بوصة²', 'Ultra high-strength steel for extreme conditions and ultra-high pressure, minimum yield 80 ksi', 'Acier ultra haute résistance pour conditions extrêmes et ultra haute pression, limite élastique min 80 ksi', 'فولاذ API 5L X80', 'API 5L Grade X80', 'Acier API 5L Grade X80'),
(8, 'SS-316L', 'فولاذ مقاوم للتآكل للغاز الحمضي، مقاوم ممتاز للكبريتيد والكلوريد', 'Corrosion-resistant steel for sour gas service, excellent resistance to H2S and chlorides', 'Acier résistant à la corrosion pour gaz acides, excellente résistance H2S et chlorures', 'فولاذ مقاوم للصدأ 316L', 'Stainless Steel 316L', 'Acier Inoxydable 316L'),
(9, 'DSS-2205', 'فولاذ عالي المقاومة ومقاوم للتآكل، مثالي للبيئات القاسية والتآكل بالنقر', 'High-strength corrosion-resistant steel, ideal for harsh environments and pitting resistance', 'Acier haute résistance et résistant à la corrosion, idéal pour environnements sévères et piqûres', 'فولاذ مزدوج 2205', 'Duplex Stainless Steel 2205', 'Acier Inoxydable Duplex 2205'),
(10, 'FBE', 'طلاء حماية خارجي أحادي الطبقة ضد التآكل، التصاق ممتاز ومقاومة كيميائية عالية', 'Single-layer external corrosion protection coating, excellent adhesion and chemical resistance', 'Revêtement externe monocouche protection corrosion, excellente adhérence et résistance chimique', 'إيبوكسي مدمج بالانصهار', 'Fusion Bonded Epoxy', 'Époxy Thermodurcissable'),
(11, '3LPE', 'نظام طلاء خارجي متعدد الطبقات (FBE + لاصق + PE)، حماية طويلة الأمد للأنابيب المدفونة', 'Multi-layer external coating (FBE + adhesive + PE), excellent long-term protection for buried pipelines', 'Système revêtement externe multicouche (FBE + adhésif + PE), excellente protection long terme pipelines enterrés', 'بولي إيثيلين ثلاثي الطبقات', '3-Layer Polyethylene', 'Polyéthylène 3 Couches'),
(12, '3LPP', 'نظام طلاء خارجي لدرجات الحرارة العالية (FBE + لاصق + PP)، مقاومة ميكانيكية وحرارية ممتازة', 'High-temperature external coating system (FBE + adhesive + PP), excellent mechanical and thermal resistance', 'Système revêtement externe haute température (FBE + adhésif + PP), excellente résistance mécanique et thermique', 'بولي بروبيلين ثلاثي الطبقات', '3-Layer Polypropylene', 'Polypropylène 3 Couches'),
(13, 'PU-EXT', 'طلاء خارجي مرن، مقاومة ممتازة للصدمات والتآكل الميكانيكي، مثالي للأنابيب المعرضة للحركة', 'Flexible external coating, excellent impact and abrasion resistance, ideal for pipelines subject to movement', 'Revêtement externe flexible, excellente résistance impacts et abrasion, idéal pipelines soumis à mouvement', 'طلاء بولي يوريثان خارجي', 'Polyurethane External Coating', 'Revêtement Polyuréthane Externe'),
(14, 'CTE', 'طلاء خارجي تقليدي، حماية ممتازة ضد التآكل التربة، تطبيق ساخن متعدد الطبقات', 'Traditional external coating, excellent protection against soil corrosion, hot-applied multi-layer system', 'Revêtement externe traditionnel, excellente protection contre corrosion sol, système multicouche appliqué à chaud', 'مينا قطران الفحم', 'Coal Tar Enamel', 'Email au Goudron de Houille'),
(15, 'TAPE-WRAP', 'نظام حماية خارجي بالشريط اللاصق، حماية ميكانيكية وختم ممتاز، سهل التطبيق والإصلاح', 'External tape wrap protection system, mechanical protection and excellent sealing, easy application and repair', 'Système protection externe par enrubannage, protection mécanique et excellent scellement, application et réparation faciles', 'نظام لف الشريط اللاصق', 'Tape Wrap System', 'Système Enrubannage'),
(16, 'CONCRETE', 'طلاء خارجي خرساني، حماية ميكانيكية وطفو سلبي للأنابيب البحرية والمغمورة', 'External concrete coating, mechanical protection and negative buoyancy for subsea and submerged pipelines', 'Revêtement externe béton, protection mécanique et flottabilité négative pour pipelines sous-marins et immergés', 'طلاء خرساني للوزن', 'Concrete Weight Coating', 'Revêtement Béton Lestage'),
(17, 'EPOXY-INT', 'بطانة داخلية للحماية من التآكل وتحسين كفاءة التدفق، سطح أملس يقلل الاحتكاك والترسبات', 'Internal lining for corrosion protection and flow efficiency, smooth surface reduces friction and deposits', 'Revêtement interne protection corrosion et efficacité écoulement, surface lisse réduit friction et dépôts', 'بطانة إيبوكسي داخلية', 'Internal Epoxy Lining', 'Revêtement Époxy Interne'),
(18, 'PU-INT', 'بطانة داخلية مرنة، مقاومة كيميائية ممتازة ومقاومة للتآكل، مناسبة للسوائل العدوانية', 'Flexible internal lining, excellent chemical and abrasion resistance, suitable for aggressive fluids', 'Revêtement interne flexible, excellente résistance chimique et abrasion, adapté fluides agressifs', 'بطانة بولي يوريثان داخلية', 'Polyurethane Internal Lining', 'Revêtement Polyuréthane Interne'),
(19, 'PHENOLIC-INT', 'بطانة داخلية لدرجات الحرارة العالية، مقاومة حرارية وكيميائية ممتازة حتى 200 درجة مئوية', 'High-temperature internal lining, excellent thermal and chemical resistance up to 200°C', 'Revêtement interne haute température, excellente résistance thermique et chimique jusqu\'à 200°C', 'بطانة فينولية داخلية', 'Phenolic Internal Lining', 'Revêtement Phénolique Interne'),
(20, 'CEMENT-INT', 'بطانة داخلية إسمنتية تقليدية، حماية من التآكل ومقاومة كيميائية جيدة، شائعة في أنابيب المياه', 'Traditional cement internal lining, corrosion protection and good chemical resistance, common in water pipelines', 'Revêtement interne ciment traditionnel, protection corrosion et bonne résistance chimique, courant canalisations eau', 'بطانة ملاط إسمنتي داخلية', 'Cement Mortar Lining', 'Revêtement Mortier Ciment Interne'),
(21, 'HDPE-INT', 'بطانة داخلية من البولي إيثيلين عالي الكثافة، سطح أملس جداً ومقاومة كيميائية ممتازة', 'High-density polyethylene internal lining, very smooth surface and excellent chemical resistance', 'Revêtement interne polyéthylène haute densité, surface très lisse et excellente résistance chimique', 'بطانة HDPE داخلية', 'HDPE Internal Lining', 'Revêtement PEHD Interne'),
(22, 'PRIMER-AC', 'طبقة أساسية تحضيرية مضادة للتآكل، تحسين الالتصاق وحماية أولية ضد التآكل', 'Preparatory base layer for corrosion protection, improves adhesion and provides initial corrosion protection', 'Couche base préparatoire protection corrosion, améliore adhérence et fournit protection initiale corrosion', 'طبقة أساس مضادة للتآكل', 'Anti-Corrosion Primer', 'Primaire Anticorrosion'),
(23, 'WAX-COAT', 'طلاء شمعي مؤقت للحماية أثناء النقل والتخزين، سهل الإزالة قبل التركيب', 'Temporary wax coating for protection during transport and storage, easily removable before installation', 'Revêtement cire temporaire protection durant transport et stockage, facilement amovible avant installation', 'طلاء شمعي', 'Wax Coating', 'Revêtement Cire');

-- --------------------------------------------------------

--
-- Structure de la table `t_03_02_07`
--

CREATE TABLE `t_03_02_07` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(20) NOT NULL,
  `F_04` bigint(20) NOT NULL,
  `F_03` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_03_02_07`
--

INSERT INTO `t_03_02_07` (`F_00`, `F_01`, `F_02`, `F_04`, `F_03`) VALUES
(1, 'Société Nationale pour la Recherche, la Production, le Transport, la Transformation et la Commercial', 'SONATRACH', 1, 1),
(2, 'Société Nationale de l\'Électricité et du Gaz', 'SONELGAZ', 1, 1),
(3, 'Société Nationale de Transport Maritime des Hydrocarbures', 'SNTM-HYPROC', 1, 1),
(4, 'Groupement TRC Reghaïa', 'GTP', 1, 2),
(5, 'Tassili Airlines', 'TASSILI', 1, 2),
(6, 'Ente Nazionale Idrocarburi', 'ENI', 2, 3),
(7, 'TotalEnergies SE', 'TOTALENERGIES', 3, 3),
(8, 'British Petroleum plc', 'BP', 4, 3),
(9, 'Equinor ASA', 'EQUINOR', 5, 3),
(10, 'Repsol S.A.', 'REPSOL', 6, 3),
(11, 'In Salah Gas Joint Venture', 'ISG-JV', 1, 4),
(12, 'In Amenas Joint Venture', 'IAM-JV', 1, 4),
(13, 'Tin Fouye Tabankort Condensate Company', 'TFCC', 1, 4),
(14, 'Société Nationale de Transport Maritime', 'SNTM', 1, 5),
(15, 'Groupement Transport Par Canalisation', 'GTP-CANAL', 1, 5),
(16, 'Raffinerie d\'Alger', 'RA1K', 1, 6),
(17, 'Raffinerie d\'Arzew', 'RA1Z', 1, 6),
(18, 'Raffinerie de Skikda', 'RA1S', 1, 6),
(19, 'Raffinerie d\'Hassi Messaoud', 'RHMS', 1, 6),
(20, 'Entreprise Nationale de Raffinage et de Distribution des Produits Pétroliers', 'NAFTAL', 1, 7),
(21, 'Algerian Distribution Company', 'ALDIC', 1, 7),
(22, 'Entreprise Nationale des Grands Travaux Pétroliers', 'ENGTP', 1, 8),
(23, 'Société Algérienne des Foires et Exportations', 'SAFIR', 1, 8),
(24, 'Technip Energies N.V.', 'TECHNIP', 3, 8),
(25, 'Saipem S.p.A.', 'SAIPEM', 2, 8),
(26, 'Algerian Energy Company', 'ALGERCO', 1, 9),
(27, 'Algerian National Pipeline Infrastructure', 'ALNAFT', 1, 9);

-- --------------------------------------------------------

--
-- Structure de la table `t_03_02_08`
--

CREATE TABLE `t_03_02_08` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(20) NOT NULL,
  `F_04` bigint(20) NOT NULL,
  `F_03` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_03_02_08`
--

INSERT INTO `t_03_02_08` (`F_00`, `F_01`, `F_02`, `F_04`, `F_03`) VALUES
(1, 'Saipem S.p.A.', 'SAIPEM-EPC', 2, 10),
(2, 'Technip Energies Algeria', 'TECHNIP-EPC', 3, 10),
(3, 'China National Petroleum Corporation', 'CNPC-EPC', 7, 10),
(4, 'Cosider Travaux Publics', 'COSIDER-TP', 1, 11),
(5, 'Entreprise de Travaux Routiers Hydrauliques et Bâtiments', 'ETRHB', 1, 11),
(6, 'ENGTP Construction Division', 'ENGTP-CONSTR', 1, 11),
(7, 'Wood Group Algeria', 'WOOD', 4, 12),
(8, 'Worley Algeria', 'WORLEY', 8, 12),
(9, 'Penspen Limited', 'PENSPEN', 4, 12),
(10, 'Amec Foster Wheeler Algeria', 'AFW-PMC', 4, 13),
(11, 'KBR Inc.', 'KBR', 9, 13),
(12, 'GE Oil & Gas Algeria', 'GE-OG', 9, 14),
(13, 'Siemens Energy Algeria', 'SIEMENS-E', 10, 14),
(14, 'Sulzer Pumps Services Algeria', 'SULZER', 11, 14),
(15, 'Nuovo Pignone Algeria', 'NUOVO-PIG', 2, 14),
(16, 'Alfapipe Spa', 'ALFAPIPE', 1, 15),
(17, 'Europipe GmbH', 'EUROPIPE', 10, 15),
(18, 'Vallourec Algérie', 'VALLOUREC', 3, 15),
(19, 'Schneider Electric Algérie', 'SCHNEIDER', 3, 16),
(20, 'Siemens Automation Algeria', 'SIEMENS-AUTO', 10, 16),
(21, 'Honeywell Process Solutions Algeria', 'HONEYWELL', 9, 16),
(22, 'Emerson Process Management Algeria', 'EMERSON', 9, 17),
(23, 'Endress+Hauser Algeria', 'E+H', 11, 17),
(24, 'ABB Measurement & Analytics Algeria', 'ABB-MEAS', 11, 17),
(25, 'Entreprise Nationale de Forage', 'ENAFOR', 1, 18),
(26, 'SGS Algeria', 'SGS', 11, 18),
(27, 'Applus+ Algeria', 'APPLUS', 6, 18),
(28, 'Bureau Veritas Algeria', 'BV', 3, 19),
(29, 'TÜV Rheinland Algeria', 'TUV', 10, 19),
(30, 'DNV Algeria', 'DNV', 5, 19),
(31, 'Entreprise Nationale des Travaux aux Puits', 'ENTP', 1, 20),
(32, 'Wood Group PSN Algeria', 'WOOD-PSN', 4, 20),
(33, 'Société de Transport de Marchandises Express', 'TRANSMEX', 1, 21),
(34, 'Gulf Agency Company Algeria', 'GAC', 12, 21),
(35, 'SDV Logistique Internationale Algérie', 'SDV', 3, 21),
(36, 'Kanaghaz Engineering', 'KANAGHAZ', 1, 22),
(37, 'General Emballage Algérie', 'GEA', 1, 22),
(38, 'Groupe Industriel des Ciments d\'Algérie', 'GICA', 1, 22);

-- --------------------------------------------------------

--
-- Structure de la table `t_03_03_01`
--

CREATE TABLE `t_03_03_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_04` date DEFAULT NULL,
  `F_05` date DEFAULT NULL,
  `F_03` date DEFAULT NULL,
  `F_02` varchar(100) NOT NULL,
  `F_06` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_03_03_01`
--

INSERT INTO `t_03_03_01` (`F_00`, `F_01`, `F_04`, `F_05`, `F_03`, `F_02`, `F_06`) VALUES
(1, 'CNDG', '2008-10-16', '0000-00-00', '2008-10-16', 'Centre National de Dispatching Gaz', 1),
(2, 'CDHL', '1995-06-01', '0000-00-00', '1995-06-01', 'Centre de Dispatching des Hydrocarbures Liquides', 1),
(3, 'PEHZ', '1978-02-21', '0000-00-00', '1978-02-21', 'Port d\'Exportation des Hydrocarbures d\'Arzew', 1),
(4, 'PEHK', '1982-01-01', '0000-00-00', '1982-01-01', 'Port d\'Exportation des Hydrocarbures de Skikda', 1),
(5, 'PEHB', '1960-01-01', '0000-00-00', '1960-01-01', 'Port d\'Exportation des Hydrocarbures de Bejaia', 1),
(6, 'ULGNZ', '1964-01-01', '0000-00-00', '1964-01-01', 'Usine de Liquéfaction de Gaz Naturel d\'Arzew', 1),
(7, 'ULGNK', '1972-01-01', '0000-00-00', '1972-01-01', 'Usine de Liquéfaction de Gaz Naturel de Skikda', 1),
(8, 'TEO', '1987-06-01', '0000-00-00', '1987-06-01', 'Terminal d\'Exportation d\'Oued Saf Saf', 1),
(9, 'TED', '1960-01-01', '0000-00-00', '1958-01-01', 'Terminal d\'Exportation du DEB DEB', 1),
(10, 'RA1G', '1964-01-01', '0000-00-00', '1964-01-01', 'Raffinerie d\'Alger', 1),
(11, 'RA1Z', '1972-01-01', '0000-00-00', '1972-01-01', 'Raffinerie d\'Arzew', 1),
(12, 'RA2K', '2009-01-01', '0000-00-00', '2009-01-01', 'Raffinerie de Skikda', 1),
(13, 'RA1K', '1980-01-01', '0000-00-00', '1980-01-01', 'Raffinerie de Skikda', 1),
(14, 'RHMD2', '1972-01-01', '0000-00-00', '1972-01-01', 'Raffinerie de Hassi Messaoud', 1),
(15, 'RA1D', '2007-01-01', '0000-00-00', '2007-01-01', 'Raffinerie d\'Adrar', 1),
(16, 'CP2K', '2004-01-01', '0000-00-00', '2004-01-01', 'Complexe Pétrochimique de Skikda', 1),
(17, 'SP1-OZ1/OZ2', '2003-01-01', '0000-00-00', '2003-01-01', 'Station de Pompage de Haoud El Hamra', 1),
(18, 'SP2-OZ1/OZ2', '2004-01-01', '0000-00-00', '2004-01-01', 'Station de Pompage d\'Ouargla', 1),
(19, 'SP3-OZ1/OZ2', '2003-01-01', '0000-00-00', '2003-01-01', 'Station de Pompage d\'Al Atteuf', 1),
(20, 'SP4-OZ1/OZ2', '2004-01-01', '0000-00-00', '2004-01-01', 'Station de Pompage de Hassi R\'mel', 1),
(21, 'SP5-OZ1/OZ2', '2003-01-01', '0000-00-00', '2003-01-01', 'Station de Pompage de Milok', 1),
(22, 'SP6-OZ1/OZ2', '2004-01-01', '0000-00-00', '2004-01-01', 'Station de Pompage de Djebil', 1),
(23, 'SP1-OK1', '1972-01-01', '0000-00-00', '1972-01-01', 'Station de Pompage de Haoud El Hamra', 1),
(24, 'SP2-OK1', '1976-01-01', '0000-00-00', '1976-01-01', 'Station de Pompage de Djamaa', 1),
(25, 'SP3-OK1', '1972-01-01', '0000-00-00', '1972-01-01', 'Station de Pompage de Selga', 1),
(26, 'SP3 Bis-OK1', '2005-01-01', '0000-00-00', '2005-01-01', 'Station de Pompage de Selga', 1),
(27, 'SP4-OK1', '1976-01-01', '0000-00-00', '1976-01-01', 'Station de Pompage de Boumagueur', 1),
(28, 'SP1-ROB1', '2019-07-01', '0000-00-00', '2019-07-01', 'Station de Pompage de Haoud El Hamra', 1),
(29, 'SP1 Bis-OB1', '2007-01-01', '0000-00-00', '2007-01-01', 'Station de Pompage de Djamaa', 1),
(30, 'SP2-OB1', '2008-08-24', '0000-00-00', '2008-08-24', 'Station de Pompage d\'El Outaya', 1),
(31, 'SP3-OB1', '2008-01-01', '0000-00-00', '2008-01-01', 'Station de Pompage de M\'sila', 1),
(32, 'SP1-OG1', '2006-06-27', '0000-00-00', '2006-06-27', 'Station de Pompage de Beni Mansour', 1),
(33, 'SP1-OT1', '1960-01-01', '0000-00-00', '1960-01-01', 'Station de Pompage d\'In Amenas', 1),
(34, 'SP2-OT1', '1960-01-01', '0000-00-00', '1960-01-01', 'Station de Pompage du Deb-Deb', 1),
(35, 'OPS1-OH1', '1975-01-01', '0000-00-00', '1975-01-01', 'Station de Pompage d\'Ohanet', 2),
(36, 'OPS2-OH1', '1975-01-01', '0000-00-00', '1975-01-01', 'Station de Pompage de Mederba', 2),
(37, 'OPS3-OH1', '1975-01-01', '0000-00-00', '1975-01-01', 'Station de Pompage de Gassi Touil', 2),
(38, 'OPS1-OD1', '1980-01-01', '0000-00-00', '1980-01-01', 'Station de Pompage d\'El Borma', 1),
(39, 'OPS1-OH2', '2003-03-11', '0000-00-00', '2003-03-11', 'Station de Pompage de Mesdar', 1),
(40, 'SP1-OH3/OH4', '2003-11-02', '0000-00-00', '2003-11-02', 'Station de Pompage de Hassi Berkine', 1),
(41, 'SP2-OH3/OH4', '2008-01-01', '0000-00-00', '2003-04-10', 'Station de Pompage de Nezla', 1),
(42, 'SP4-NZ1', '1978-11-01', '0000-00-00', '1978-11-01', 'Station de Pompage de Hassi R\'mel', 1),
(43, 'SP5-NZ1', '1978-11-01', '0000-00-00', '1978-11-01', 'Station de Pompage de Milok', 1),
(44, 'SP6-NZ1', '1978-11-01', '0000-00-00', '1978-11-01', 'Station de Pompage de Medareg', 1),
(45, 'SP1-NK1', '2009-01-01', '0000-00-00', '2009-01-01', 'Station de Pompage de Haoud El Hamra', 1),
(46, 'SP3-NK1', '2009-01-01', '0000-00-00', '2009-01-01', 'Station de Pompage d\'El Outaya', 1),
(47, 'SP1-LZ1', '1984-02-02', '0000-00-00', '1984-02-02', 'Station de Pompage de Hassi R\'mel', 1),
(48, 'SP2-LZ1', '2006-10-07', '0000-00-00', '2006-10-07', 'Station de Pompage de Milok', 1),
(49, 'SP1-LZ2', '2007-11-01', '0000-00-00', '2007-11-01', 'Station de Pompage de Hassi R\'mel', 1),
(50, 'SPRN-LR1/DLR1/ELR1', '1999-10-29', '0000-00-00', '1999-10-29', 'Station de Pompage de Rhoud Nouss', 1),
(51, 'SP1-LR1/DLR1/ELR1', '2018-02-01', '0000-00-00', '2018-02-01', 'Station de Pompage de Haoud El Hamra', 1),
(52, 'SP2-LR1/DLR1/ELR1', '1996-01-01', '0000-00-00', '1996-01-01', 'Station de Pompage d\'Ouargla', 1),
(53, 'SC1-GZ0', '1965-01-01', '2015-09-01', '1965-01-01', 'Station de Compression de Laghouat', 8),
(54, 'SC2-GZ0', '1972-01-01', '2015-09-01', '1972-01-01', 'Station de Compression de Medarreg', 8),
(55, 'SC3-GZ0', '1965-01-01', '2015-09-01', '1965-01-01', 'Station de Compression de Tiaret', 8),
(56, 'SC4-GZ0', '1972-01-01', '2015-09-01', '1972-01-01', 'Station de Compression de Relizane', 8),
(57, 'SC1-GZ1/GZ2/GZ3', '1979-01-01', '0000-00-00', '1979-01-01', 'Station de Compression de Timzhert', 1),
(58, 'SC2-GZ1/GZ2/GZ3', '1978-01-01', '0000-00-00', '1978-01-01', 'Station de Compression de M\'seka', 1),
(59, 'SC3-GZ1/GZ2/GZ3', '1979-01-01', '0000-00-00', '1979-01-01', 'Station de Compression de Medarreg', 1),
(60, 'SC4-GZ1/GZ2/GZ3', '1978-01-01', '0000-00-00', '1978-01-01', 'Station de Compression de Djebel Nador', 1),
(61, 'SC5-GZ1/GZ2/GZ3', '1979-01-01', '0000-00-00', '1979-01-01', 'Station de Compression de Kenenda', 1),
(62, 'SC3-GPDF/EGPDF', '2004-02-14', '0000-00-00', '2004-02-14', 'Station de Compression de Sidi Cheikh', 1),
(63, 'SCA-GK1/GK2', '1979-01-01', '0000-00-00', '1979-01-01', 'Station de Compression de Hassi Dellaa', 1),
(64, 'SCB-GK1/GK2', '1978-01-01', '0000-00-00', '1978-01-01', 'Station de Compression de Ras El Miaad', 1),
(65, 'SCC-GK1/GK2', '1979-01-01', '0000-00-00', '1979-01-01', 'Station de Compression de Chaiba', 1),
(66, 'SCD-GK1/GK2', '1978-01-01', '0000-00-00', '1978-01-01', 'Station de Compression de Sefiane', 1),
(67, 'SCE-GK1/GK2', '1979-01-01', '0000-00-00', '1979-01-01', 'Station de Compression de Mechtatine', 1),
(68, 'SC2-GO1/GO2/GO3', '1996-01-01', '0000-00-00', '1996-01-01', 'Station de Compression d\'Ain Naga', 1),
(69, 'SC3-GG1', '2006-09-01', '0000-00-00', '2006-09-01', 'Station de Compression de Moudjebara', 1),
(70, 'SCTFT-GR1/GR2', '2007-06-01', '0000-00-00', '2007-06-01', 'Station de Compression de TFT', 1),
(71, 'SC1-GR1/GR2', '2006-07-01', '0000-00-00', '2006-07-01', 'Station de Compression de Rhoude Nouss', 1),
(72, 'SC2-GR1/GR2', '2003-10-04', '0000-00-00', '2003-10-04', 'Station de Compression de Zina', 1),
(73, 'SC3-GR1/GR2', '2006-01-01', '0000-00-00', '2006-01-01', 'Station de Compression d\'Ouargla', 1),
(74, 'SC4-GR1/GR2', '1986-01-01', '2000-01-01', '1986-01-01', 'Station de Compression de Hassi R\'mel', 8),
(75, 'SCHR-GR1/GR2', '2018-01-01', '0000-00-00', '2018-01-01', 'Station de Compression de Hassi R\'mel', 1),
(76, 'SCHR-GR5/GR7', '2017-01-01', '0000-00-00', '2017-01-01', 'Station de Compression de Hassi R\'mel', 1),
(77, 'TEA', '1996-01-01', '0000-00-00', '1996-01-01', 'Terminal d\'Exportation d\'El Aricha', 1),
(78, 'TEB', '2021-01-01', '0000-00-00', '2021-01-01', 'Terminal d\'Exportation de Beni Saf', 1),
(79, 'TABM', '1961-01-01', '0000-00-00', '1961-01-01', 'Terminal d\'Arrivé Bordj Menail', 1),
(80, 'SCLR', '1961-01-01', '0000-00-00', '1961-01-01', 'Station de Collecte de Reggane', 1),
(81, 'OZ1', '1965-11-07', '0000-00-00', '1965-11-07', 'Pipeline OZ1', 1),
(82, 'OZ2', '2003-08-18', '0000-00-00', '2003-08-18', 'Pipeline OZ2', 1),
(83, 'OK1', '1972-01-01', '0000-00-00', '1972-01-01', 'Pipeline OK1', 1),
(84, 'OB1', '1959-11-10', '0000-00-00', '1959-11-10', 'Pipeline OB1', 1),
(85, 'OG1', '2005-07-04', '0000-00-00', '2005-07-04', 'Pipeline OG1', 1),
(86, 'OT1', '1960-01-01', '0000-00-00', '1960-01-01', 'Pipeline OT1', 1),
(87, 'OH1', '1982-01-01', '0000-00-00', '1982-01-01', 'Pipeline OH1', 1),
(88, 'OD1', '1977-03-23', '0000-00-00', '1977-03-23', 'Pipeline OD1', 1),
(89, 'OD3', '1999-12-11', '0000-00-00', '1999-12-11', 'Pipeline OD3', 1),
(90, 'OH2', '1972-04-10', '0000-00-00', '1972-04-10', 'Pipeline OH2', 1),
(91, 'OH3', '1999-01-01', '0000-00-00', '1999-01-01', 'Pipeline OH3', 1),
(92, 'OH4', '2006-01-01', '0000-00-00', '2006-01-01', 'Pipeline OH4', 1),
(93, 'NZ1', '1978-11-01', '0000-00-00', '1978-11-01', 'Pipeline NZ1', 1),
(94, 'NK1', '2009-08-04', '0000-00-00', '2009-08-04', 'Pipeline NK1', 1),
(95, 'NH2', '1960-01-01', '0000-00-00', '1960-01-01', 'Pipeline NH2', 1),
(96, 'LZ1', '1984-02-08', '0000-00-00', '1984-02-08', 'Pipeline LZ1', 1),
(97, 'LZ2', '2010-11-01', '0000-00-00', '2010-11-01', 'Pipeline LZ2', 1),
(98, 'LR1', '1996-01-01', '0000-00-00', '1996-01-01', 'Pipeline LR1', 1),
(99, 'DLR1', '2004-10-04', '0000-00-00', '2004-10-04', 'Pipeline DLR1', 1),
(100, 'ELR1', '2014-01-01', '0000-00-00', '2014-01-01', 'Pipeline ELR1', 1),
(101, 'GZ0', '1961-01-01', '2022-01-01', '1961-01-01', 'Pipeline GZ0', 8),
(102, 'GZ1', '1976-01-01', '0000-00-00', '1976-01-01', 'Pipeline GZ1', 1),
(103, 'GZ0 Bis', '2022-09-01', '0000-00-00', '2022-09-01', 'Pipeline GZ0 Bis', 1),
(104, 'GZ2', '1982-01-01', '0000-00-00', '1982-01-01', 'Pipeline GZ2', 1),
(105, 'GZ3', '1989-01-01', '0000-00-00', '1989-01-01', 'Pipeline GZ3', 1),
(106, 'RGZ3', '2018-01-01', '0000-00-00', '2018-01-01', 'Pipeline RGZ3', 1),
(107, 'GZ4', '2010-04-16', '0000-00-00', '2010-04-16', 'Pipeline GZ4', 1),
(108, 'GPDF', '1996-01-01', '0000-00-00', '1996-01-01', 'Pipeline GPDF', 1),
(109, 'EGPDF', '2021-01-01', '0000-00-00', '2021-01-01', 'Pipeline EGPDF', 1),
(110, 'GK1', '1978-01-01', '0000-00-00', '1971-01-01', 'Pipeline GK1', 1),
(111, 'GK2', '2001-01-01', '0000-00-00', '1998-01-01', 'Pipeline GK2', 1),
(112, 'GK3', '2012-01-01', '0000-00-00', '2012-01-01', 'Pipeline GK3', 1),
(113, 'GO1', '1982-01-01', '0000-00-00', '1982-01-01', 'Pipeline GO1', 1),
(114, 'GO2', '1987-01-01', '0000-00-00', '1987-01-01', 'Pipeline GO2', 1),
(115, 'GO3', '2010-01-01', '0000-00-00', '2008-01-01', 'Pipeline GO3', 1),
(116, 'GG1', '1981-01-01', '0000-00-00', '1981-01-01', 'Pipeline GG1', 1),
(117, 'RGG1', '2019-01-01', '0000-00-00', '2019-01-01', 'Pipeline RGG1', 1),
(118, 'GR1', '1986-01-01', '0000-00-00', '1986-01-01', 'Pipeline GR1', 1),
(119, 'GR2', '1999-01-01', '0000-00-00', '1999-01-01', 'Pipeline GR2', 1),
(120, 'GR4', '2014-01-01', '0000-00-00', '2014-01-01', 'Pipeline GR4', 1),
(121, 'GR6', '2017-01-01', '0000-00-00', '2017-01-01', 'Pipeline GR6', 1),
(122, 'GR5', '2017-06-01', '0000-00-00', '2017-06-01', 'Pipeline GR5', 1),
(123, 'GR7', '2019-12-01', '0000-00-00', '2019-12-01', 'Pipeline GR7', 1);

-- --------------------------------------------------------

--
-- Structure de la table `t_03_03_02`
--

CREATE TABLE `t_03_03_02` (
  `F_10` double DEFAULT NULL,
  `F_08` double NOT NULL,
  `F_09` double NOT NULL,
  `F_07` varchar(100) NOT NULL,
  `F_00` bigint(20) NOT NULL,
  `F_12` bigint(20) NOT NULL,
  `F_11` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_03_03_03`
--

CREATE TABLE `t_03_03_03` (
  `F_00` bigint(20) NOT NULL,
  `F_13` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_03_03_04`
--

CREATE TABLE `t_03_03_04` (
  `F_00` bigint(20) NOT NULL,
  `F_13` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_03_03_05`
--

CREATE TABLE `t_03_03_05` (
  `F_00` bigint(20) NOT NULL,
  `F_13` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_03_03_06`
--

CREATE TABLE `t_03_03_06` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(50) NOT NULL,
  `F_02` varchar(100) NOT NULL,
  `F_04` bigint(20) NOT NULL,
  `F_03` bigint(20) NOT NULL,
  `F_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `t_03_03_06`
--

INSERT INTO `t_03_03_06` (`F_00`, `F_01`, `F_02`, `F_04`, `F_03`, `F_05`) VALUES
(1, 'OZ1/OZ2', 'STC OZ1/OZ2', 1, 2, 2),
(2, 'OK1', 'STC OK1', 1, 2, 4),
(3, 'OB1/OG1', 'STC OB1/OG1', 1, 2, 3),
(4, 'OT1', 'STC OT1', 1, 2, 9),
(5, 'OH1', 'STC OH1', 1, 2, 9),
(6, 'OD1/OD3/OH2', 'STC OD1/OD3/OH2', 1, 2, 7),
(7, 'OH3/OH4', 'STC OH3/OH4', 1, 2, 7),
(8, 'NZ1', 'STC NZ1', 1, 3, 2),
(9, 'NK1', 'STC NK1', 1, 3, 4),
(10, 'NH2', 'STC NH2', 1, 3, 9),
(11, 'LZ1/LZ2', 'STC LZ1/LZ2', 1, 4, 2),
(12, 'LR1/DLR1/ELR1', 'STC LR1/DLR1/ELR1', 1, 4, 7),
(13, 'GZ0', 'STC GZ0', 1, 1, 2),
(14, 'GZ1/GZ2/GZ3', 'STC GZ1/GZ2/GZ3', 1, 1, 2),
(15, 'GZ4', 'STC GZ4', 1, 1, 2),
(16, 'GPDF', 'STC GPDF', 1, 1, 5),
(17, 'GK1/GK2', 'STC GK1/GK2', 1, 1, 4),
(18, 'GK3', 'STC GK3', 1, 1, 4),
(19, 'GO1/GO2/GO3', 'STC GO1/GO2/GO3', 1, 1, 6),
(20, 'GG1', 'STC GG1', 1, 1, 3),
(21, 'GR1/GR2/GR4/GR6', 'STC GR1/GR2/GR4/GR6', 1, 1, 9),
(22, 'GR5', 'STC GR5/GR7', 1, 1, 8);

-- --------------------------------------------------------

--
-- Structure de la table `t_03_03_07`
--

CREATE TABLE `t_03_03_07` (
  `F_15` double NOT NULL,
  `F_11` double NOT NULL,
  `F_13` double NOT NULL,
  `F_08` double NOT NULL,
  `F_07` double NOT NULL,
  `F_10` double NOT NULL,
  `F_09` double NOT NULL,
  `F_16` double NOT NULL,
  `F_12` double NOT NULL,
  `F_14` double NOT NULL,
  `F_00` bigint(20) NOT NULL,
  `F_23` bigint(20) NOT NULL,
  `F_22` bigint(20) NOT NULL,
  `F_17` bigint(20) NOT NULL,
  `F_18` bigint(20) NOT NULL,
  `F_19` bigint(20) NOT NULL,
  `F_21` bigint(20) NOT NULL,
  `F_20` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_03_03_08`
--

CREATE TABLE `t_03_03_08` (
  `F_07` double NOT NULL,
  `F_12` double NOT NULL,
  `F_08` double NOT NULL,
  `F_10` double NOT NULL,
  `F_11` double NOT NULL,
  `F_09` double NOT NULL,
  `F_00` bigint(20) NOT NULL,
  `F_13` bigint(20) DEFAULT NULL,
  `F_14` bigint(20) DEFAULT NULL,
  `F_15` bigint(20) DEFAULT NULL,
  `F_16` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_03_03_09`
--

CREATE TABLE `t_03_03_09` (
  `F_00` bigint(20) NOT NULL,
  `F_02` varchar(50) NOT NULL,
  `F_08` date DEFAULT NULL,
  `F_10` date DEFAULT NULL,
  `F_07` date DEFAULT NULL,
  `F_09` date DEFAULT NULL,
  `F_03` varchar(100) NOT NULL,
  `F_06` date DEFAULT NULL,
  `F_04` varchar(50) DEFAULT NULL,
  `F_01` varchar(100) NOT NULL,
  `F_05` varchar(100) DEFAULT NULL,
  `F_12` bigint(20) NOT NULL,
  `F_13` bigint(20) NOT NULL,
  `F_11` bigint(20) NOT NULL
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
-- Index pour la table `r_t030303_t030307`
--
ALTER TABLE `r_t030303_t030307`
  ADD PRIMARY KEY (`F_01`,`F_02`),
  ADD KEY `R_T030303_T030307_FK_02` (`F_02`);

--
-- Index pour la table `r_t030304_t030307`
--
ALTER TABLE `r_t030304_t030307`
  ADD PRIMARY KEY (`F_01`,`F_02`),
  ADD KEY `R_T030304_T030307_FK_02` (`F_02`);

--
-- Index pour la table `r_t030305_t030207`
--
ALTER TABLE `r_t030305_t030207`
  ADD PRIMARY KEY (`F_01`,`F_02`),
  ADD KEY `R_T030303_T030207_FK_02` (`F_02`);

--
-- Index pour la table `r_t030305_t030307`
--
ALTER TABLE `r_t030305_t030307`
  ADD PRIMARY KEY (`F_01`,`F_02`),
  ADD KEY `R_T030305_T030307_FK_02` (`F_02`);

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
  ADD PRIMARY KEY (`F_00`);

--
-- Index pour la table `t_01_01_03`
--
ALTER TABLE `t_01_01_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_01_03_UK_01` (`F_05`,`F_04`),
  ADD KEY `T_01_01_03_FK_02` (`F_06`);

--
-- Index pour la table `t_01_01_04`
--
ALTER TABLE `t_01_01_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_01_04_UK_01` (`F_01`),
  ADD KEY `T_01_01_04_FK_01` (`F_05`);

--
-- Index pour la table `t_01_01_05`
--
ALTER TABLE `t_01_01_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_01_05_UK_02` (`F_02`),
  ADD UNIQUE KEY `T_01_01_05_UK_03` (`F_05`),
  ADD UNIQUE KEY `T_01_01_05_UK_01` (`F_01`);

--
-- Index pour la table `t_01_01_06`
--
ALTER TABLE `t_01_01_06`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_01_06_UK_01` (`F_01`),
  ADD KEY `T_01_01_06_FK_01` (`F_03`),
  ADD KEY `T_01_01_06_FK_02` (`F_04`);

--
-- Index pour la table `t_01_01_07`
--
ALTER TABLE `t_01_01_07`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_01_07_UK_01` (`F_01`),
  ADD KEY `T_01_01_07_FK_01` (`F_03`);

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
  ADD UNIQUE KEY `T_01_04_02_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_01_04_02_UK_02` (`F_04`);

--
-- Index pour la table `t_01_04_03`
--
ALTER TABLE `t_01_04_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_03_UK_01` (`F_01`),
  ADD KEY `T_01_04_03_FK_01` (`F_05`);

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
  ADD UNIQUE KEY `T_01_04_05_UK_01` (`F_03`),
  ADD KEY `T_01_04_05_FK_01` (`F_07`);

--
-- Index pour la table `t_01_04_06`
--
ALTER TABLE `t_01_04_06`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_06_UK_01` (`F_03`);

--
-- Index pour la table `t_01_04_07`
--
ALTER TABLE `t_01_04_07`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_07_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_01_04_07_UK_02` (`F_04`),
  ADD KEY `T_01_04_07_FK_02` (`F_06`),
  ADD KEY `T_01_04_07_FK_01` (`F_05`);

--
-- Index pour la table `t_01_04_08`
--
ALTER TABLE `t_01_04_08`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_08_UK_01` (`F_01`),
  ADD KEY `T_01_04_08_FK_01` (`F_05`);

--
-- Index pour la table `t_01_04_09`
--
ALTER TABLE `t_01_04_09`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `FK5d5f2io70jjhnxm49c4nsmtjm` (`F_07`);

--
-- Index pour la table `t_01_04_10`
--
ALTER TABLE `t_01_04_10`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `FKfploqhev5tkv2uul79eyvwlsp` (`F_02`),
  ADD KEY `FKjucg9o5kabtxoi0j8qxf4bc0n` (`F_01`),
  ADD KEY `FKl4ebqb4p2x7bb2n7lg3k9pik9` (`F_04`),
  ADD KEY `FKre9gryisoebvijo6siwxm4e3s` (`F_03`);

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
  ADD KEY `T_02_02_08_FK_03` (`F_07`),
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
  ADD KEY `T_02_03_04_FK_01` (`F_18`),
  ADD KEY `T_02_03_04_FK_04` (`F_21`);

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
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_01_01_UK_01` (`F_03`);

--
-- Index pour la table `t_03_01_02`
--
ALTER TABLE `t_03_01_02`
  ADD PRIMARY KEY (`F_00`);

--
-- Index pour la table `t_03_01_03`
--
ALTER TABLE `t_03_01_03`
  ADD PRIMARY KEY (`F_00`);

--
-- Index pour la table `t_03_01_04`
--
ALTER TABLE `t_03_01_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_01_04_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_03_01_04_UK_02` (`F_04`);

--
-- Index pour la table `t_03_01_05`
--
ALTER TABLE `t_03_01_05`
  ADD PRIMARY KEY (`F_00`);

--
-- Index pour la table `t_03_01_06`
--
ALTER TABLE `t_03_01_06`
  ADD PRIMARY KEY (`F_00`);

--
-- Index pour la table `t_03_01_07`
--
ALTER TABLE `t_03_01_07`
  ADD PRIMARY KEY (`F_00`);

--
-- Index pour la table `t_03_01_08`
--
ALTER TABLE `t_03_01_08`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_01_08_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_03_01_08_UK_02` (`F_04`);

--
-- Index pour la table `t_03_02_01`
--
ALTER TABLE `t_03_02_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_01_02_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_03_01_02_UK_02` (`F_04`);

--
-- Index pour la table `t_03_02_02`
--
ALTER TABLE `t_03_02_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_02_02_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_03_02_02_UK_02` (`F_04`),
  ADD KEY `T_03_02_02_FK_01` (`F_08`);

--
-- Index pour la table `t_03_02_03`
--
ALTER TABLE `t_03_02_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_02_03_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_03_02_03_UK_02` (`F_01`),
  ADD KEY `T_03_02_03_FK_01` (`F_08`);

--
-- Index pour la table `t_03_02_04`
--
ALTER TABLE `t_03_02_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_02_04_UK_01` (`F_01`,`F_04`);

--
-- Index pour la table `t_03_02_05`
--
ALTER TABLE `t_03_02_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_02_05_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_03_02_05_UK_02` (`F_04`);

--
-- Index pour la table `t_03_02_06`
--
ALTER TABLE `t_03_02_06`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_02_06_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_03_02_06_UK_02` (`F_04`);

--
-- Index pour la table `t_03_02_07`
--
ALTER TABLE `t_03_02_07`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_02_07_UK_01` (`F_02`),
  ADD KEY `T_03_02_07_FK_02` (`F_04`),
  ADD KEY `T_03_02_07_FK_01` (`F_03`);

--
-- Index pour la table `t_03_02_08`
--
ALTER TABLE `t_03_02_08`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_02_08_UK_01` (`F_02`),
  ADD KEY `T_03_02_08_FK_02` (`F_04`),
  ADD KEY `T_03_02_08_FK_01` (`F_03`);

--
-- Index pour la table `t_03_03_01`
--
ALTER TABLE `t_03_03_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_03_01_UK_01` (`F_01`),
  ADD KEY `T_03_03_01_FK_01` (`F_06`);

--
-- Index pour la table `t_03_03_02`
--
ALTER TABLE `t_03_03_02`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_03_03_02_FK_02` (`F_12`),
  ADD KEY `T_03_03_02_FK_01` (`F_11`);

--
-- Index pour la table `t_03_03_03`
--
ALTER TABLE `t_03_03_03`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_03_03_03_FK_01` (`F_13`);

--
-- Index pour la table `t_03_03_04`
--
ALTER TABLE `t_03_03_04`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_03_03_04_FK_01` (`F_13`);

--
-- Index pour la table `t_03_03_05`
--
ALTER TABLE `t_03_03_05`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_03_03_05_FK_01` (`F_13`);

--
-- Index pour la table `t_03_03_06`
--
ALTER TABLE `t_03_03_06`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_03_06_UK_01` (`F_01`),
  ADD KEY `T_03_03_06_FK_02` (`F_04`),
  ADD KEY `T_03_03_06_FK_01` (`F_03`),
  ADD KEY `T_03_03_06_FK_03` (`F_05`);

--
-- Index pour la table `t_03_03_07`
--
ALTER TABLE `t_03_03_07`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_03_03_07_FK_07` (`F_23`),
  ADD KEY `T_03_03_07_FK_06` (`F_22`),
  ADD KEY `T_03_03_07_FK_01` (`F_17`),
  ADD KEY `T_03_03_07_FK_02` (`F_18`),
  ADD KEY `T_03_03_07_FK_03` (`F_19`),
  ADD KEY `T_03_03_07_FK_05` (`F_21`),
  ADD KEY `T_03_03_07_FK_04` (`F_20`);

--
-- Index pour la table `t_03_03_08`
--
ALTER TABLE `t_03_03_08`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_03_02_09_FK_01` (`F_13`),
  ADD KEY `T_03_02_09_FK_02` (`F_14`),
  ADD KEY `T_03_02_09_FK_03` (`F_15`),
  ADD KEY `T_03_02_10_FK_01` (`F_16`);

--
-- Index pour la table `t_03_03_09`
--
ALTER TABLE `t_03_03_09`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_03_09_UK_01` (`F_02`),
  ADD KEY `T_03_03_09_FK_02` (`F_12`),
  ADD KEY `T_03_03_09_FK_03` (`F_13`),
  ADD KEY `T_03_03_09_FK_01` (`F_11`);

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
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `t_01_01_03`
--
ALTER TABLE `t_01_01_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `t_01_01_04`
--
ALTER TABLE `t_01_01_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT pour la table `t_01_01_05`
--
ALTER TABLE `t_01_01_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `t_01_01_06`
--
ALTER TABLE `t_01_01_06`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_01_01_07`
--
ALTER TABLE `t_01_01_07`
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
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=250;

--
-- AUTO_INCREMENT pour la table `t_01_04_02`
--
ALTER TABLE `t_01_04_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- AUTO_INCREMENT pour la table `t_01_04_03`
--
ALTER TABLE `t_01_04_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1542;

--
-- AUTO_INCREMENT pour la table `t_01_04_04`
--
ALTER TABLE `t_01_04_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `t_01_04_05`
--
ALTER TABLE `t_01_04_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT pour la table `t_01_04_06`
--
ALTER TABLE `t_01_04_06`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

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
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=142;

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
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1175;

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
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT pour la table `t_03_01_04`
--
ALTER TABLE `t_03_01_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT pour la table `t_03_01_08`
--
ALTER TABLE `t_03_01_08`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT pour la table `t_03_02_01`
--
ALTER TABLE `t_03_02_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `t_03_02_02`
--
ALTER TABLE `t_03_02_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `t_03_02_03`
--
ALTER TABLE `t_03_02_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_03_02_04`
--
ALTER TABLE `t_03_02_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `t_03_02_05`
--
ALTER TABLE `t_03_02_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `t_03_02_06`
--
ALTER TABLE `t_03_02_06`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT pour la table `t_03_02_07`
--
ALTER TABLE `t_03_02_07`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT pour la table `t_03_02_08`
--
ALTER TABLE `t_03_02_08`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT pour la table `t_03_03_01`
--
ALTER TABLE `t_03_03_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=124;

--
-- AUTO_INCREMENT pour la table `t_03_03_06`
--
ALTER TABLE `t_03_03_06`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT pour la table `t_03_03_09`
--
ALTER TABLE `t_03_03_09`
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
-- Contraintes pour la table `r_t030303_t030307`
--
ALTER TABLE `r_t030303_t030307`
  ADD CONSTRAINT `R_T030303_T030307_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_03_03_03` (`F_00`),
  ADD CONSTRAINT `R_T030303_T030307_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_03_03_07` (`F_00`);

--
-- Contraintes pour la table `r_t030304_t030307`
--
ALTER TABLE `r_t030304_t030307`
  ADD CONSTRAINT `R_T030304_T030307_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_03_03_04` (`F_00`),
  ADD CONSTRAINT `R_T030304_T030307_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_03_03_07` (`F_00`);

--
-- Contraintes pour la table `r_t030305_t030207`
--
ALTER TABLE `r_t030305_t030207`
  ADD CONSTRAINT `R_T030303_T030207_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_03_03_05` (`F_00`),
  ADD CONSTRAINT `R_T030303_T030207_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_03_03_07` (`F_00`);

--
-- Contraintes pour la table `r_t030305_t030307`
--
ALTER TABLE `r_t030305_t030307`
  ADD CONSTRAINT `R_T030305_T030307_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_03_03_05` (`F_00`),
  ADD CONSTRAINT `R_T030305_T030307_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_03_03_07` (`F_00`);

--
-- Contraintes pour la table `t_00_04_01`
--
ALTER TABLE `t_00_04_01`
  ADD CONSTRAINT `T_00_04_01_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_00_02_02` (`F_00`);

--
-- Contraintes pour la table `t_01_01_03`
--
ALTER TABLE `t_01_01_03`
  ADD CONSTRAINT `T_01_01_03_FK_01` FOREIGN KEY (`F_05`) REFERENCES `t_01_01_01` (`F_00`),
  ADD CONSTRAINT `T_01_01_03_FK_02` FOREIGN KEY (`F_06`) REFERENCES `t_01_01_02` (`F_00`);

--
-- Contraintes pour la table `t_01_01_04`
--
ALTER TABLE `t_01_01_04`
  ADD CONSTRAINT `T_01_01_04_FK_01` FOREIGN KEY (`F_05`) REFERENCES `t_01_01_03` (`F_00`);

--
-- Contraintes pour la table `t_01_01_06`
--
ALTER TABLE `t_01_01_06`
  ADD CONSTRAINT `T_01_01_06_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_01_01_04` (`F_00`),
  ADD CONSTRAINT `T_01_01_06_FK_02` FOREIGN KEY (`F_04`) REFERENCES `t_01_01_05` (`F_00`);

--
-- Contraintes pour la table `t_01_01_07`
--
ALTER TABLE `t_01_01_07`
  ADD CONSTRAINT `T_01_01_07_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_01_01_05` (`F_00`);

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
-- Contraintes pour la table `t_01_04_03`
--
ALTER TABLE `t_01_04_03`
  ADD CONSTRAINT `T_01_04_03_FK_01` FOREIGN KEY (`F_05`) REFERENCES `t_01_04_02` (`F_00`);

--
-- Contraintes pour la table `t_01_04_05`
--
ALTER TABLE `t_01_04_05`
  ADD CONSTRAINT `T_01_04_05_FK_01` FOREIGN KEY (`F_07`) REFERENCES `t_01_04_04` (`F_00`);

--
-- Contraintes pour la table `t_01_04_07`
--
ALTER TABLE `t_01_04_07`
  ADD CONSTRAINT `T_01_04_07_FK_01` FOREIGN KEY (`F_05`) REFERENCES `t_01_04_06` (`F_00`),
  ADD CONSTRAINT `T_01_04_07_FK_02` FOREIGN KEY (`F_06`) REFERENCES `t_01_04_07` (`F_00`);

--
-- Contraintes pour la table `t_01_04_08`
--
ALTER TABLE `t_01_04_08`
  ADD CONSTRAINT `T_01_04_08_FK_01` FOREIGN KEY (`F_05`) REFERENCES `t_01_04_07` (`F_00`);

--
-- Contraintes pour la table `t_01_04_09`
--
ALTER TABLE `t_01_04_09`
  ADD CONSTRAINT `FK5d5f2io70jjhnxm49c4nsmtjm` FOREIGN KEY (`F_07`) REFERENCES `t_01_04_01` (`F_00`);

--
-- Contraintes pour la table `t_01_04_10`
--
ALTER TABLE `t_01_04_10`
  ADD CONSTRAINT `FKfploqhev5tkv2uul79eyvwlsp` FOREIGN KEY (`F_02`) REFERENCES `t_01_04_08` (`F_00`),
  ADD CONSTRAINT `FKjucg9o5kabtxoi0j8qxf4bc0n` FOREIGN KEY (`F_01`) REFERENCES `t_01_04_09` (`F_00`),
  ADD CONSTRAINT `FKl4ebqb4p2x7bb2n7lg3k9pik9` FOREIGN KEY (`F_04`) REFERENCES `t_01_04_05` (`F_00`),
  ADD CONSTRAINT `FKre9gryisoebvijo6siwxm4e3s` FOREIGN KEY (`F_03`) REFERENCES `t_01_04_07` (`F_00`);

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
  ADD CONSTRAINT `T_02_02_08_FK_02` FOREIGN KEY (`F_06`) REFERENCES `t_02_02_06` (`F_00`),
  ADD CONSTRAINT `T_02_02_08_FK_03` FOREIGN KEY (`F_07`) REFERENCES `t_02_02_03` (`F_00`);

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
  ADD CONSTRAINT `FKstsyiqy29nd3jit5aqxo901bn` FOREIGN KEY (`F_00`) REFERENCES `t_03_01_01` (`F_00`);

--
-- Contraintes pour la table `t_03_01_03`
--
ALTER TABLE `t_03_01_03`
  ADD CONSTRAINT `FK8pwa5h3sfhh159lyodsjr7v7t` FOREIGN KEY (`F_00`) REFERENCES `t_03_01_01` (`F_00`);

--
-- Contraintes pour la table `t_03_01_05`
--
ALTER TABLE `t_03_01_05`
  ADD CONSTRAINT `FKm6d6tm3wmkopl5utx7anh5suw` FOREIGN KEY (`F_00`) REFERENCES `t_03_01_04` (`F_00`);

--
-- Contraintes pour la table `t_03_01_06`
--
ALTER TABLE `t_03_01_06`
  ADD CONSTRAINT `FKfwd88fag562777n9e2yojop39` FOREIGN KEY (`F_00`) REFERENCES `t_03_01_04` (`F_00`);

--
-- Contraintes pour la table `t_03_01_07`
--
ALTER TABLE `t_03_01_07`
  ADD CONSTRAINT `FKn5h40fqfwftknoss2tkku4kdy` FOREIGN KEY (`F_00`) REFERENCES `t_03_01_04` (`F_00`);

--
-- Contraintes pour la table `t_03_02_02`
--
ALTER TABLE `t_03_02_02`
  ADD CONSTRAINT `T_03_02_02_FK_01` FOREIGN KEY (`F_08`) REFERENCES `t_03_02_01` (`F_00`);

--
-- Contraintes pour la table `t_03_02_03`
--
ALTER TABLE `t_03_02_03`
  ADD CONSTRAINT `T_03_02_03_FK_01` FOREIGN KEY (`F_08`) REFERENCES `t_01_04_03` (`F_00`);

--
-- Contraintes pour la table `t_03_02_07`
--
ALTER TABLE `t_03_02_07`
  ADD CONSTRAINT `T_03_02_07_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_03_01_02` (`F_00`),
  ADD CONSTRAINT `T_03_02_07_FK_02` FOREIGN KEY (`F_04`) REFERENCES `t_01_04_01` (`F_00`);

--
-- Contraintes pour la table `t_03_02_08`
--
ALTER TABLE `t_03_02_08`
  ADD CONSTRAINT `T_03_02_08_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_03_01_03` (`F_00`),
  ADD CONSTRAINT `T_03_02_08_FK_02` FOREIGN KEY (`F_04`) REFERENCES `t_01_04_01` (`F_00`);

--
-- Contraintes pour la table `t_03_03_01`
--
ALTER TABLE `t_03_03_01`
  ADD CONSTRAINT `T_03_03_01_FK_01` FOREIGN KEY (`F_06`) REFERENCES `t_03_02_05` (`F_00`);

--
-- Contraintes pour la table `t_03_03_02`
--
ALTER TABLE `t_03_03_02`
  ADD CONSTRAINT `FKm0u49vcytxoa5vkdyw3c2j7ct` FOREIGN KEY (`F_00`) REFERENCES `t_03_03_01` (`F_00`),
  ADD CONSTRAINT `T_03_03_02_FK_01` FOREIGN KEY (`F_11`) REFERENCES `t_03_02_08` (`F_00`),
  ADD CONSTRAINT `T_03_03_02_FK_02` FOREIGN KEY (`F_12`) REFERENCES `t_01_04_03` (`F_00`);

--
-- Contraintes pour la table `t_03_03_03`
--
ALTER TABLE `t_03_03_03`
  ADD CONSTRAINT `FKdlyi8pcitn594pkyqvw7aia4l` FOREIGN KEY (`F_00`) REFERENCES `t_03_03_02` (`F_00`),
  ADD CONSTRAINT `T_03_03_03_FK_01` FOREIGN KEY (`F_13`) REFERENCES `t_03_01_05` (`F_00`);

--
-- Contraintes pour la table `t_03_03_04`
--
ALTER TABLE `t_03_03_04`
  ADD CONSTRAINT `FKfbkrrnm1ngghdwl80nbkaob6d` FOREIGN KEY (`F_00`) REFERENCES `t_03_03_02` (`F_00`),
  ADD CONSTRAINT `T_03_03_04_FK_01` FOREIGN KEY (`F_13`) REFERENCES `t_03_01_06` (`F_00`);

--
-- Contraintes pour la table `t_03_03_05`
--
ALTER TABLE `t_03_03_05`
  ADD CONSTRAINT `FKaf2mrh81h9kterqpqa9fmb7nq` FOREIGN KEY (`F_00`) REFERENCES `t_03_03_02` (`F_00`),
  ADD CONSTRAINT `T_03_03_05_FK_01` FOREIGN KEY (`F_13`) REFERENCES `t_03_01_07` (`F_00`);

--
-- Contraintes pour la table `t_03_03_06`
--
ALTER TABLE `t_03_03_06`
  ADD CONSTRAINT `T_03_03_06_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_03_02_04` (`F_00`),
  ADD CONSTRAINT `T_03_03_06_FK_02` FOREIGN KEY (`F_04`) REFERENCES `t_03_02_05` (`F_00`),
  ADD CONSTRAINT `T_03_03_06_FK_03` FOREIGN KEY (`F_05`) REFERENCES `t_03_02_02` (`F_00`);

--
-- Contraintes pour la table `t_03_03_07`
--
ALTER TABLE `t_03_03_07`
  ADD CONSTRAINT `FKdvhk5c3134g3x8jcbepos8psp` FOREIGN KEY (`F_00`) REFERENCES `t_03_03_01` (`F_00`),
  ADD CONSTRAINT `T_03_03_07_FK_01` FOREIGN KEY (`F_17`) REFERENCES `t_03_02_06` (`F_00`),
  ADD CONSTRAINT `T_03_03_07_FK_02` FOREIGN KEY (`F_18`) REFERENCES `t_03_02_06` (`F_00`),
  ADD CONSTRAINT `T_03_03_07_FK_03` FOREIGN KEY (`F_19`) REFERENCES `t_03_02_06` (`F_00`),
  ADD CONSTRAINT `T_03_03_07_FK_04` FOREIGN KEY (`F_20`) REFERENCES `t_03_02_08` (`F_00`),
  ADD CONSTRAINT `T_03_03_07_FK_05` FOREIGN KEY (`F_21`) REFERENCES `t_03_03_06` (`F_00`),
  ADD CONSTRAINT `T_03_03_07_FK_06` FOREIGN KEY (`F_22`) REFERENCES `t_03_03_02` (`F_00`),
  ADD CONSTRAINT `T_03_03_07_FK_07` FOREIGN KEY (`F_23`) REFERENCES `t_03_03_02` (`F_00`);

--
-- Contraintes pour la table `t_03_03_08`
--
ALTER TABLE `t_03_03_08`
  ADD CONSTRAINT `FKahj34utm6aoq9ftq4960frf9v` FOREIGN KEY (`F_00`) REFERENCES `t_03_03_01` (`F_00`),
  ADD CONSTRAINT `T_03_02_09_FK_01` FOREIGN KEY (`F_13`) REFERENCES `t_03_02_06` (`F_00`),
  ADD CONSTRAINT `T_03_02_09_FK_02` FOREIGN KEY (`F_14`) REFERENCES `t_03_02_06` (`F_00`),
  ADD CONSTRAINT `T_03_02_09_FK_03` FOREIGN KEY (`F_15`) REFERENCES `t_03_02_06` (`F_00`),
  ADD CONSTRAINT `T_03_02_10_FK_01` FOREIGN KEY (`F_16`) REFERENCES `t_03_03_07` (`F_00`);

--
-- Contraintes pour la table `t_03_03_09`
--
ALTER TABLE `t_03_03_09`
  ADD CONSTRAINT `T_03_03_09_FK_01` FOREIGN KEY (`F_11`) REFERENCES `t_03_02_05` (`F_00`),
  ADD CONSTRAINT `T_03_03_09_FK_02` FOREIGN KEY (`F_12`) REFERENCES `t_03_01_08` (`F_00`),
  ADD CONSTRAINT `T_03_03_09_FK_03` FOREIGN KEY (`F_13`) REFERENCES `t_03_03_02` (`F_00`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
