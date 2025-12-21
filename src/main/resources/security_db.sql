-- ============================================================================
-- IAAS SECURITY DATABASE - BASED ON 90 ACTUAL CONTROLLERS v3.0
-- Generated: 2025-12-21 11:50 AM CET
-- File: security_db.sql
-- Repository: CHOUABBIA-AMINE/iaas
-- Purpose: Complete security setup based on discovered controller structure
-- Modules: system, business, common, network
-- Total: 27 categories, 180 permissions, 9 roles, 16 users
-- ============================================================================

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

-- ============================================================================
-- SECTION 1: AUTHORITY CATEGORIES (T_00_02_05)
-- ============================================================================
-- 27 categories organized by discovered module structure

INSERT INTO `T_00_02_05` (`F_00`, `F_02`, `F_01`, `F_03`) VALUES

-- SYSTEM MODULE (1-10)
(1, 'System Management', 'SYSTEM', 'Core system operations and configuration'),
(2, 'User Management', 'USER', 'User account and profile management'),
(3, 'Role Management', 'ROLE', 'Role definitions and assignments'),
(4, 'Permission Management', 'PERMISSION', 'Permission and access control'),
(5, 'Group Management', 'GROUP', 'User group organization'),
(6, 'Authority Management', 'AUTHORITY', 'Authority category management'),
(7, 'Audit Management', 'AUDIT', 'System audit logging and tracking'),
(8, 'Authentication', 'AUTH', 'Authentication and session management'),
(9, 'Configuration', 'CONFIG', 'System configuration settings'),
(10, 'System Utility', 'UTILITY', 'System utilities and tools'),

-- BUSINESS MODULE (11-18)
(11, 'Procurement Core', 'PROCUREMENT', 'Core procurement operations'),
(12, 'Approval Process', 'APPROVAL', 'Approval status and workflows'),
(13, 'Currency Management', 'CURRENCY', 'Currency and exchange rates'),
(14, 'Contract Management', 'CONTRACT', 'Contract lifecycle management'),
(15, 'Contract Amendment', 'AMENDMENT', 'Contract amendment processing'),
(16, 'Consultation Process', 'CONSULTATION', 'Consultation and tender evaluation'),
(17, 'Procurement Planning', 'PLAN', 'Annual procurement planning'),
(18, 'Provider Management', 'PROVIDER', 'Provider and vendor management'),

-- COMMON MODULE (19-22)
(19, 'Administration', 'ADMINISTRATION', 'Administrative operations'),
(20, 'Communication', 'COMMUNICATION', 'Messaging and notifications'),
(21, 'Document Management', 'DOCUMENT', 'Document handling and archiving'),
(22, 'Environment Data', 'ENVIRONMENT', 'Geographic and location data'),

-- NETWORK MODULE (23-24)
(23, 'Network Management', 'NETWORK', 'Network infrastructure management'),
(24, 'IT Infrastructure', 'INFRASTRUCTURE', 'IT infrastructure and services'),

-- CROSS-CUTTING (25-27)
(25, 'Reporting & Analytics', 'REPORT', 'Reports, dashboards, and analytics'),
(26, 'Workflow Engine', 'WORKFLOW', 'Business process workflows'),
(27, 'System Integration', 'INTEGRATION', 'External system integrations')

ON DUPLICATE KEY UPDATE `F_02` = VALUES(`F_02`), `F_03` = VALUES(`F_03`);

-- ============================================================================
-- SECTION 2: PERMISSIONS (T_00_02_04)
-- ============================================================================
-- 180 permissions covering ~90 controllers

INSERT INTO `T_00_02_04` (`F_00`, `F_04`, `F_02`, `F_01`, `F_03`) VALUES

-- SYSTEM MODULE (1-40)
(1, NULL, 'View system settings', 'SYSTEM:READ', '1'),
(2, NULL, 'Modify system settings', 'SYSTEM:WRITE', '1'),
(3, NULL, 'Delete system data', 'SYSTEM:DELETE', '1'),
(4, NULL, 'Administer system', 'SYSTEM:ADMIN', '1'),
(5, NULL, 'View users', 'USER:READ', '2'),
(6, NULL, 'Create/edit users', 'USER:WRITE', '2'),
(7, NULL, 'Delete users', 'USER:DELETE', '2'),
(8, NULL, 'Administer users', 'USER:ADMIN', '2'),
(9, NULL, 'View roles', 'ROLE:READ', '3'),
(10, NULL, 'Create/edit roles', 'ROLE:WRITE', '3'),
(11, NULL, 'Delete roles', 'ROLE:DELETE', '3'),
(12, NULL, 'Administer roles', 'ROLE:ADMIN', '3'),
(13, NULL, 'View permissions', 'PERMISSION:READ', '4'),
(14, NULL, 'Create/edit permissions', 'PERMISSION:WRITE', '4'),
(15, NULL, 'Delete permissions', 'PERMISSION:DELETE', '4'),
(16, NULL, 'Administer permissions', 'PERMISSION:ADMIN', '4'),
(17, NULL, 'View groups', 'GROUP:READ', '5'),
(18, NULL, 'Create/edit groups', 'GROUP:WRITE', '5'),
(19, NULL, 'Delete groups', 'GROUP:DELETE', '5'),
(20, NULL, 'Administer groups', 'GROUP:ADMIN', '5'),
(21, NULL, 'View authority categories', 'AUTHORITY:READ', '6'),
(22, NULL, 'Create/edit authorities', 'AUTHORITY:WRITE', '6'),
(23, NULL, 'Delete authorities', 'AUTHORITY:DELETE', '6'),
(24, NULL, 'Administer authorities', 'AUTHORITY:ADMIN', '6'),
(25, NULL, 'View audit logs', 'AUDIT:READ', '7'),
(26, NULL, 'Create audit entries', 'AUDIT:WRITE', '7'),
(27, NULL, 'Delete audit logs', 'AUDIT:DELETE', '7'),
(28, NULL, 'Administer audit system', 'AUDIT:ADMIN', '7'),
(29, NULL, 'View authentication data', 'AUTH:READ', '8'),
(30, NULL, 'Manage authentication', 'AUTH:WRITE', '8'),
(31, NULL, 'Revoke authentication', 'AUTH:DELETE', '8'),
(32, NULL, 'Administer authentication', 'AUTH:ADMIN', '8'),
(33, NULL, 'View configuration', 'CONFIG:READ', '9'),
(34, NULL, 'Edit configuration', 'CONFIG:WRITE', '9'),
(35, NULL, 'Delete configuration', 'CONFIG:DELETE', '9'),
(36, NULL, 'Administer configuration', 'CONFIG:ADMIN', '9'),
(37, NULL, 'View system utilities', 'UTILITY:READ', '10'),
(38, NULL, 'Execute utilities', 'UTILITY:EXECUTE', '10'),
(39, NULL, 'Delete utility data', 'UTILITY:DELETE', '10'),
(40, NULL, 'Administer utilities', 'UTILITY:ADMIN', '10'),

-- BUSINESS MODULE (41-96)
(41, NULL, 'View procurement data', 'PROCUREMENT:READ', '11'),
(42, NULL, 'Create/edit procurement', 'PROCUREMENT:WRITE', '11'),
(43, NULL, 'Delete procurement', 'PROCUREMENT:DELETE', '11'),
(44, NULL, 'Administer procurement', 'PROCUREMENT:ADMIN', '11'),
(45, NULL, 'View procurement status', 'PROCUREMENT_STATUS:READ', '11'),
(46, NULL, 'Manage procurement status', 'PROCUREMENT_STATUS:ADMIN', '11'),
(47, NULL, 'View procurement nature', 'PROCUREMENT_NATURE:READ', '11'),
(48, NULL, 'Manage procurement nature', 'PROCUREMENT_NATURE:ADMIN', '11'),
(49, NULL, 'View approval requests', 'APPROVAL:READ', '12'),
(50, NULL, 'Process approvals', 'APPROVAL:WRITE', '12'),
(51, NULL, 'Reject approvals', 'APPROVAL:DELETE', '12'),
(52, NULL, 'Administer approval system', 'APPROVAL:ADMIN', '12'),
(53, NULL, 'View approval status', 'APPROVAL_STATUS:READ', '12'),
(54, NULL, 'Manage approval status', 'APPROVAL_STATUS:ADMIN', '12'),
(55, NULL, 'View currencies', 'CURRENCY:READ', '13'),
(56, NULL, 'Manage currencies', 'CURRENCY:WRITE', '13'),
(57, NULL, 'Delete currencies', 'CURRENCY:DELETE', '13'),
(58, NULL, 'Administer currency system', 'CURRENCY:ADMIN', '13'),
(59, NULL, 'View contracts', 'CONTRACT:READ', '14'),
(60, NULL, 'Create/edit contracts', 'CONTRACT:WRITE', '14'),
(61, NULL, 'Delete contracts', 'CONTRACT:DELETE', '14'),
(62, NULL, 'Administer contracts', 'CONTRACT:ADMIN', '14'),
(63, NULL, 'Approve contracts', 'CONTRACT:APPROVE', '14'),
(64, NULL, 'Sign contracts', 'CONTRACT:SIGN', '14'),
(65, NULL, 'Archive contracts', 'CONTRACT:ARCHIVE', '14'),
(66, NULL, 'Export contract data', 'CONTRACT:EXPORT', '14'),
(67, NULL, 'View amendments', 'AMENDMENT:READ', '15'),
(68, NULL, 'Create amendments', 'AMENDMENT:WRITE', '15'),
(69, NULL, 'Delete amendments', 'AMENDMENT:DELETE', '15'),
(70, NULL, 'Administer amendments', 'AMENDMENT:ADMIN', '15'),
(71, NULL, 'Approve amendments', 'AMENDMENT:APPROVE', '15'),
(72, NULL, 'Execute amendments', 'AMENDMENT:EXECUTE', '15'),
(73, NULL, 'View consultations', 'CONSULTATION:READ', '16'),
(74, NULL, 'Create consultations', 'CONSULTATION:WRITE', '16'),
(75, NULL, 'Delete consultations', 'CONSULTATION:DELETE', '16'),
(76, NULL, 'Administer consultations', 'CONSULTATION:ADMIN', '16'),
(77, NULL, 'Participate in consultation', 'CONSULTATION:PARTICIPATE', '16'),
(78, NULL, 'Evaluate consultation responses', 'CONSULTATION:EVALUATE', '16'),
(79, NULL, 'View procurement plans', 'PLAN:READ', '17'),
(80, NULL, 'Create/edit plans', 'PLAN:WRITE', '17'),
(81, NULL, 'Delete plans', 'PLAN:DELETE', '17'),
(82, NULL, 'Administer planning', 'PLAN:ADMIN', '17'),
(83, NULL, 'Approve plans', 'PLAN:APPROVE', '17'),
(84, NULL, 'Validate plans', 'PLAN:VALIDATE', '17'),
(85, NULL, 'Execute plans', 'PLAN:EXECUTE', '17'),
(86, NULL, 'Monitor plan execution', 'PLAN:MONITOR', '17'),
(87, NULL, 'View providers', 'PROVIDER:READ', '18'),
(88, NULL, 'Register providers', 'PROVIDER:WRITE', '18'),
(89, NULL, 'Delete providers', 'PROVIDER:DELETE', '18'),
(90, NULL, 'Administer providers', 'PROVIDER:ADMIN', '18'),
(91, NULL, 'Evaluate providers', 'PROVIDER:EVALUATE', '18'),
(92, NULL, 'Approve providers', 'PROVIDER:APPROVE', '18'),
(93, NULL, 'Blacklist providers', 'PROVIDER:BLACKLIST', '18'),
(94, NULL, 'Export provider data', 'PROVIDER:EXPORT', '18'),
(95, NULL, 'View procurement director info', 'PROCUREMENT_DIRECTOR:READ', '11'),
(96, NULL, 'Manage procurement directors', 'PROCUREMENT_DIRECTOR:ADMIN', '11'),

-- COMMON MODULE (97-140)
(97, NULL, 'View administration data', 'ADMINISTRATION:READ', '19'),
(98, NULL, 'Manage administration', 'ADMINISTRATION:WRITE', '19'),
(99, NULL, 'Delete administration data', 'ADMINISTRATION:DELETE', '19'),
(100, NULL, 'Administer system', 'ADMINISTRATION:ADMIN', '19'),
(101, NULL, 'Manage departments', 'DEPARTMENT:ADMIN', '19'),
(102, NULL, 'Manage positions', 'POSITION:ADMIN', '19'),
(103, NULL, 'Manage employees', 'EMPLOYEE:ADMIN', '19'),
(104, NULL, 'View organization chart', 'ORGANIZATION:READ', '19'),
(105, NULL, 'View messages', 'COMMUNICATION:READ', '20'),
(106, NULL, 'Send messages', 'COMMUNICATION:WRITE', '20'),
(107, NULL, 'Delete messages', 'COMMUNICATION:DELETE', '20'),
(108, NULL, 'Administer communication', 'COMMUNICATION:ADMIN', '20'),
(109, NULL, 'View notifications', 'NOTIFICATION:READ', '20'),
(110, NULL, 'Send notifications', 'NOTIFICATION:WRITE', '20'),
(111, NULL, 'View announcements', 'ANNOUNCEMENT:READ', '20'),
(112, NULL, 'Create announcements', 'ANNOUNCEMENT:WRITE', '20'),
(113, NULL, 'View mail correspondence', 'MAIL:READ', '20'),
(114, NULL, 'Manage mail system', 'MAIL:ADMIN', '20'),
(115, NULL, 'View documents', 'DOCUMENT:READ', '21'),
(116, NULL, 'Upload documents', 'DOCUMENT:WRITE', '21'),
(117, NULL, 'Delete documents', 'DOCUMENT:DELETE', '21'),
(118, NULL, 'Administer documents', 'DOCUMENT:ADMIN', '21'),
(119, NULL, 'Approve documents', 'DOCUMENT:APPROVE', '21'),
(120, NULL, 'Sign documents', 'DOCUMENT:SIGN', '21'),
(121, NULL, 'Archive documents', 'DOCUMENT:ARCHIVE', '21'),
(122, NULL, 'View document types', 'DOCUMENT_TYPE:READ', '21'),
(123, NULL, 'Manage document types', 'DOCUMENT_TYPE:ADMIN', '21'),
(124, NULL, 'View attachments', 'ATTACHMENT:READ', '21'),
(125, NULL, 'Upload attachments', 'ATTACHMENT:WRITE', '21'),
(126, NULL, 'Share documents', 'DOCUMENT:SHARE', '21'),
(127, NULL, 'View location data', 'ENVIRONMENT:READ', '22'),
(128, NULL, 'Manage locations', 'ENVIRONMENT:WRITE', '22'),
(129, NULL, 'Delete location data', 'ENVIRONMENT:DELETE', '22'),
(130, NULL, 'Administer environment', 'ENVIRONMENT:ADMIN', '22'),
(131, NULL, 'View countries', 'COUNTRY:READ', '22'),
(132, NULL, 'Manage countries', 'COUNTRY:ADMIN', '22'),
(133, NULL, 'View regions', 'REGION:READ', '22'),
(134, NULL, 'Manage regions', 'REGION:ADMIN', '22'),
(135, NULL, 'View provinces', 'PROVINCE:READ', '22'),
(136, NULL, 'Manage provinces', 'PROVINCE:ADMIN', '22'),
(137, NULL, 'View municipalities', 'MUNICIPALITY:READ', '22'),
(138, NULL, 'Manage municipalities', 'MUNICIPALITY:ADMIN', '22'),
(139, NULL, 'View addresses', 'ADDRESS:READ', '22'),
(140, NULL, 'Manage addresses', 'ADDRESS:ADMIN', '22'),

-- NETWORK MODULE (141-160)
(141, NULL, 'View network configuration', 'NETWORK:READ', '23'),
(142, NULL, 'Configure network', 'NETWORK:WRITE', '23'),
(143, NULL, 'Delete network config', 'NETWORK:DELETE', '23'),
(144, NULL, 'Administer network', 'NETWORK:ADMIN', '23'),
(145, NULL, 'Monitor network status', 'NETWORK:MONITOR', '23'),
(146, NULL, 'View network devices', 'NETWORK_DEVICE:READ', '23'),
(147, NULL, 'Manage network devices', 'NETWORK_DEVICE:ADMIN', '23'),
(148, NULL, 'View network topology', 'NETWORK_TOPOLOGY:READ', '23'),
(149, NULL, 'View network logs', 'NETWORK_LOG:READ', '23'),
(150, NULL, 'Troubleshoot network', 'NETWORK:TROUBLESHOOT', '23'),
(151, NULL, 'View infrastructure', 'INFRASTRUCTURE:READ', '24'),
(152, NULL, 'Manage infrastructure', 'INFRASTRUCTURE:WRITE', '24'),
(153, NULL, 'Delete infrastructure', 'INFRASTRUCTURE:DELETE', '24'),
(154, NULL, 'Administer infrastructure', 'INFRASTRUCTURE:ADMIN', '24'),
(155, NULL, 'View servers', 'SERVER:READ', '24'),
(156, NULL, 'Manage servers', 'SERVER:ADMIN', '24'),
(157, NULL, 'View services', 'SERVICE:READ', '24'),
(158, NULL, 'Manage services', 'SERVICE:ADMIN', '24'),
(159, NULL, 'Monitor infrastructure', 'INFRASTRUCTURE:MONITOR', '24'),
(160, NULL, 'Backup infrastructure', 'INFRASTRUCTURE:BACKUP', '24'),

-- CROSS-CUTTING (161-180)
(161, NULL, 'View reports', 'REPORT:READ', '25'),
(162, NULL, 'Create reports', 'REPORT:WRITE', '25'),
(163, NULL, 'Delete reports', 'REPORT:DELETE', '25'),
(164, NULL, 'Administer reports', 'REPORT:ADMIN', '25'),
(165, NULL, 'Export reports', 'REPORT:EXPORT', '25'),
(166, NULL, 'Schedule reports', 'REPORT:SCHEDULE', '25'),
(167, NULL, 'View dashboards', 'DASHBOARD:READ', '25'),
(168, NULL, 'Create dashboards', 'DASHBOARD:WRITE', '25'),
(169, NULL, 'View analytics', 'ANALYTICS:READ', '25'),
(170, NULL, 'Generate analytics', 'ANALYTICS:GENERATE', '25'),
(171, NULL, 'View workflows', 'WORKFLOW:READ', '26'),
(172, NULL, 'Design workflows', 'WORKFLOW:WRITE', '26'),
(173, NULL, 'Delete workflows', 'WORKFLOW:DELETE', '26'),
(174, NULL, 'Administer workflows', 'WORKFLOW:ADMIN', '26'),
(175, NULL, 'Execute workflows', 'WORKFLOW:EXECUTE', '26'),
(176, NULL, 'Monitor workflows', 'WORKFLOW:MONITOR', '26'),
(177, NULL, 'View integrations', 'INTEGRATION:READ', '27'),
(178, NULL, 'Configure integrations', 'INTEGRATION:WRITE', '27'),
(179, NULL, 'Delete integrations', 'INTEGRATION:DELETE', '27'),
(180, NULL, 'Administer integrations', 'INTEGRATION:ADMIN', '27')

ON DUPLICATE KEY UPDATE `F_02` = VALUES(`F_02`), `F_03` = VALUES(`F_03`), `F_04` = VALUES(`F_04`);

-- ============================================================================
-- SECTION 3: ROLES (T_00_02_03)
-- ============================================================================

INSERT INTO `T_00_02_03` (`F_00`, `F_02`, `F_01`, `F_03`) VALUES
(1, 'Super Administrator - Full system access', 'ROLE_SUPER_ADMIN', NULL),
(2, 'System Administrator - IT operations', 'ROLE_ADMIN', NULL),
(3, 'Procurement Manager - Full procurement access', 'ROLE_PROCUREMENT_MANAGER', NULL),
(4, 'Contract Manager - Contract lifecycle', 'ROLE_CONTRACT_MANAGER', NULL),
(5, 'Procurement Officer - Daily operations', 'ROLE_PROCUREMENT_OFFICER', NULL),
(6, 'Auditor - Compliance and audit', 'ROLE_AUDITOR', NULL),
(7, 'Analyst - Reporting and analytics', 'ROLE_ANALYST', NULL),
(8, 'Approver - Approval authority', 'ROLE_APPROVER', NULL),
(9, 'Viewer - Read-only access', 'ROLE_VIEWER', NULL)
ON DUPLICATE KEY UPDATE `F_02` = VALUES(`F_02`), `F_03` = VALUES(`F_03`);

-- ============================================================================
-- SECTION 4: USERS (T_00_02_02)
-- ============================================================================

INSERT INTO `T_00_02_02` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`, `F_05`, `F_06`, `F_07`) VALUES
(1, 'superadmin', 'superadmin@iaas.dz', '$2a$12$LQv3c1yqBwlVHLZeKj/.lOHk9Qp1yqBwlVHLZeKj/.lOHk9Qp1yqBwl', b'1', b'1', b'1', b'1'),
(2, 'admin', 'admin@iaas.dz', '$2a$12$8zK8mN5pQ2rT7vW3xY4uZ0aB1cD2eF3gH4iJ5kL6mN7oP8qR9sT0', b'1', b'1', b'1', b'1'),
(3, 'it.admin', 'it.admin@iaas.dz', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'1', b'1', b'1'),
(4, 'procurement.manager', 'procurement.manager@iaas.dz', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'1', b'1', b'1'),
(5, 'contract.manager', 'contract.manager@iaas.dz', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'1', b'1', b'1'),
(6, 'provider.manager', 'provider.manager@iaas.dz', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'1', b'1', b'1'),
(7, 'procurement.officer1', 'officer1@iaas.dz', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'1', b'1', b'1'),
(8, 'procurement.officer2', 'officer2@iaas.dz', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'1', b'1', b'1'),
(9, 'procurement.officer3', 'officer3@iaas.dz', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'1', b'1', b'1'),
(10, 'contract.officer', 'contract.officer@iaas.dz', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'1', b'1', b'1'),
(11, 'auditor', 'auditor@iaas.dz', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'1', b'1', b'1'),
(12, 'analyst', 'analyst@iaas.dz', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'1', b'1', b'1'),
(13, 'approver', 'approver@iaas.dz', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'1', b'1', b'1'),
(14, 'viewer', 'viewer@iaas.dz', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'1', b'1', b'1'),
(15, 'disabled.user', 'disabled@iaas.dz', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'0', b'1', b'1', b'0'),
(16, 'locked.user', 'locked@iaas.dz', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'0', b'1', b'0')
ON DUPLICATE KEY UPDATE `F_02` = VALUES(`F_02`), `F_03` = VALUES(`F_03`), `F_04` = VALUES(`F_04`), `F_05` = VALUES(`F_05`), `F_06` = VALUES(`F_06`), `F_07` = VALUES(`F_07`);

-- ============================================================================
-- SECTION 5: ROLE-PERMISSION MAPPINGS (r_T000203_T000204)
-- ============================================================================

INSERT INTO `r_T000203_T000204` (`F_01`, `F_02`) VALUES
(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25),(1,26),(1,27),(1,28),(1,29),(1,30),(1,31),(1,32),(1,33),(1,34),(1,35),(1,36),(1,37),(1,38),(1,39),(1,40),(1,41),(1,42),(1,43),(1,44),(1,45),(1,46),(1,47),(1,48),(1,49),(1,50),(1,51),(1,52),(1,53),(1,54),(1,55),(1,56),(1,57),(1,58),(1,59),(1,60),(1,61),(1,62),(1,63),(1,64),(1,65),(1,66),(1,67),(1,68),(1,69),(1,70),(1,71),(1,72),(1,73),(1,74),(1,75),(1,76),(1,77),(1,78),(1,79),(1,80),(1,81),(1,82),(1,83),(1,84),(1,85),(1,86),(1,87),(1,88),(1,89),(1,90),(1,91),(1,92),(1,93),(1,94),(1,95),(1,96),(1,97),(1,98),(1,99),(1,100),(1,101),(1,102),(1,103),(1,104),(1,105),(1,106),(1,107),(1,108),(1,109),(1,110),(1,111),(1,112),(1,113),(1,114),(1,115),(1,116),(1,117),(1,118),(1,119),(1,120),(1,121),(1,122),(1,123),(1,124),(1,125),(1,126),(1,127),(1,128),(1,129),(1,130),(1,131),(1,132),(1,133),(1,134),(1,135),(1,136),(1,137),(1,138),(1,139),(1,140),(1,141),(1,142),(1,143),(1,144),(1,145),(1,146),(1,147),(1,148),(1,149),(1,150),(1,151),(1,152),(1,153),(1,154),(1,155),(1,156),(1,157),(1,158),(1,159),(1,160),(1,161),(1,162),(1,163),(1,164),(1,165),(1,166),(1,167),(1,168),(1,169),(1,170),(1,171),(1,172),(1,173),(1,174),(1,175),(1,176),(1,177),(1,178),(1,179),(1,180)
ON DUPLICATE KEY UPDATE F_01=F_01;

INSERT INTO `r_T000203_T000204` (`F_01`, `F_02`) VALUES
(2,1),(2,2),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(2,10),(2,11),(2,12),(2,13),(2,14),(2,15),(2,16),(2,17),(2,18),(2,19),(2,20),(2,21),(2,22),(2,23),(2,24),(2,25),(2,26),(2,27),(2,28),(2,29),(2,30),(2,31),(2,32),(2,33),(2,34),(2,35),(2,36),(2,37),(2,38),(2,39),(2,40),(2,141),(2,142),(2,143),(2,144),(2,145),(2,146),(2,147),(2,148),(2,149),(2,150),(2,151),(2,152),(2,153),(2,154),(2,155),(2,156),(2,157),(2,158),(2,159),(2,160),
(3,1),(3,5),(3,9),(3,13),(3,17),(3,21),(3,25),(3,29),(3,33),(3,37),(3,41),(3,42),(3,43),(3,44),(3,45),(3,46),(3,47),(3,48),(3,49),(3,50),(3,51),(3,52),(3,53),(3,54),(3,55),(3,56),(3,57),(3,58),(3,59),(3,60),(3,61),(3,62),(3,63),(3,64),(3,65),(3,66),(3,67),(3,68),(3,69),(3,70),(3,71),(3,72),(3,73),(3,74),(3,75),(3,76),(3,77),(3,78),(3,79),(3,80),(3,81),(3,82),(3,83),(3,84),(3,85),(3,86),(3,87),(3,88),(3,89),(3,90),(3,91),(3,92),(3,93),(3,94),(3,95),(3,96),(3,97),(3,105),(3,115),(3,127),(3,161),(3,162),(3,165),(3,167),(3,169),
(4,1),(4,5),(4,9),(4,25),(4,29),(4,49),(4,50),(4,52),(4,53),(4,54),(4,59),(4,60),(4,61),(4,62),(4,63),(4,64),(4,65),(4,66),(4,67),(4,68),(4,69),(4,70),(4,71),(4,72),(4,87),(4,91),(4,115),(4,116),(4,117),(4,118),(4,119),(4,120),(4,161),(4,165),
(5,1),(5,5),(5,9),(5,25),(5,29),(5,41),(5,42),(5,45),(5,47),(5,49),(5,50),(5,55),(5,59),(5,60),(5,73),(5,74),(5,77),(5,79),(5,80),(5,87),(5,88),(5,105),(5,106),(5,109),(5,111),(5,115),(5,116),(5,124),(5,125),(5,161),
(6,1),(6,5),(6,9),(6,13),(6,17),(6,21),(6,25),(6,26),(6,27),(6,28),(6,41),(6,49),(6,55),(6,59),(6,67),(6,73),(6,79),(6,87),(6,97),(6,105),(6,115),(6,127),(6,161),(6,162),(6,165),(6,167),(6,169),(6,170),
(7,1),(7,5),(7,25),(7,41),(7,49),(7,55),(7,59),(7,73),(7,79),(7,87),(7,161),(7,162),(7,163),(7,164),(7,165),(7,166),(7,167),(7,168),(7,169),(7,170),
(8,1),(8,5),(8,25),(8,49),(8,50),(8,52),(8,53),(8,54),(8,59),(8,63),(8,64),(8,67),(8,71),(8,79),(8,83),(8,84),(8,87),(8,92),(8,115),(8,119),(8,120),
(9,1),(9,5),(9,9),(9,13),(9,17),(9,21),(9,25),(9,29),(9,33),(9,37),(9,41),(9,45),(9,47),(9,49),(9,53),(9,55),(9,59),(9,67),(9,73),(9,79),(9,87),(9,95),(9,97),(9,105),(9,109),(9,111),(9,115),(9,122),(9,124),(9,127),(9,131),(9,133),(9,135),(9,137),(9,139),(9,161),(9,167)
ON DUPLICATE KEY UPDATE F_01=F_01;

-- ============================================================================
-- SECTION 6: USER-ROLE ASSIGNMENTS (r_T000202_T000203)
-- ============================================================================

INSERT INTO `r_T000202_T000203` (`F_01`, `F_02`) VALUES
(1,1),(2,2),(3,2),(4,3),(5,4),(6,3),(7,5),(8,5),(9,5),(10,4),(11,6),(12,7),(13,8),(14,9)
ON DUPLICATE KEY UPDATE F_01=F_01;

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- ============================================================================
-- INSTALLATION COMPLETE - v3.0
-- ============================================================================
-- 27 Authority Categories | 180 Permissions | 9 Roles | 16 Users
-- Based on ~90 controllers from: system, business, common, network modules
-- ============================================================================