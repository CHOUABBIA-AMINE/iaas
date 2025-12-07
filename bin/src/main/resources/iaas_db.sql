-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 07, 2025 at 12:30 PM
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
(1, '2025-12-08 11:19:54.000000', 'ccc96a4f-c9cf-4297-8610-2a9a4bca3278', 2);

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
  ADD UNIQUE KEY `UKee1g4vjx66c0m1iaj1dxslk8f` (`F_03`),
  ADD UNIQUE KEY `T_00_04_01_UK_01` (`F_03`);

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
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

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
-- Constraints for table `t_00_02_04`
--
ALTER TABLE `t_00_02_04`
  ADD CONSTRAINT `T_00_02_04_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_00_02_05` (`F_00`);

--
-- Constraints for table `t_00_04_01`
--
ALTER TABLE `t_00_04_01`
  ADD CONSTRAINT `T_00_04_01_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_00_02_02` (`F_00`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
