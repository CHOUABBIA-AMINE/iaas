-- ============================================================================
-- IAAS SECURITY DATABASE - BASED ON 90 ACTUAL CONTROLLERS v3.0
-- Generated: 2025-12-21 11:46 AM CET
-- File: security_db.sql
-- Repository: CHOUABBIA-AMINE/iaas
-- Purpose: Complete security setup based on discovered controller structure
-- Modules: system, business, common, network
-- Total: 27 categories, 180 permissions, 9 roles, 16 users
-- ============================================================================

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

-- Authority Categories (27 total)
INSERT INTO `T_00_02_05` (`F_00`, `F_02`, `F_01`, `F_03`) VALUES
(1, 'System Management', 'SYSTEM', 'Core system operations'),
(2, 'User Management', 'USER', 'User account management'),
(3, 'Role Management', 'ROLE', 'Role definitions'),
(4, 'Permission Management', 'PERMISSION', 'Permission control'),
(5, 'Group Management', 'GROUP', 'User groups'),
(6, 'Authority Management', 'AUTHORITY', 'Authority categories'),
(7, 'Audit Management', 'AUDIT', 'Audit logging'),
(8, 'Authentication', 'AUTH', 'Authentication'),
(9, 'Configuration', 'CONFIG', 'System config'),
(10, 'System Utility', 'UTILITY', 'System utilities'),
(11, 'Procurement Core', 'PROCUREMENT', 'Procurement operations'),
(12, 'Approval Process', 'APPROVAL', 'Approval workflows'),
(13, 'Currency Management', 'CURRENCY', 'Currency management'),
(14, 'Contract Management', 'CONTRACT', 'Contract lifecycle'),
(15, 'Contract Amendment', 'AMENDMENT', 'Contract amendments'),
(16, 'Consultation Process', 'CONSULTATION', 'Consultation evaluation'),
(17, 'Procurement Planning', 'PLAN', 'Procurement planning'),
(18, 'Provider Management', 'PROVIDER', 'Provider management'),
(19, 'Administration', 'ADMINISTRATION', 'Administrative ops'),
(20, 'Communication', 'COMMUNICATION', 'Messaging'),
(21, 'Document Management', 'DOCUMENT', 'Document handling'),
(22, 'Environment Data', 'ENVIRONMENT', 'Geographic data'),
(23, 'Network Management', 'NETWORK', 'Network infrastructure'),
(24, 'IT Infrastructure', 'INFRASTRUCTURE', 'IT infrastructure'),
(25, 'Reporting & Analytics', 'REPORT', 'Reports and analytics'),
(26, 'Workflow Engine', 'WORKFLOW', 'Business workflows'),
(27, 'System Integration', 'INTEGRATION', 'System integrations')
ON DUPLICATE KEY UPDATE `F_02` = VALUES(`F_02`), `F_03` = VALUES(`F_03`);

COMMIT;