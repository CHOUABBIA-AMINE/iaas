-- ============================================================================
-- IAAS SECURITY DATABASE - COMPLETE SETUP SCRIPT v2.1
-- Generated: 2025-12-21 10:42 AM CET
-- File: security_db.sql
-- Repository: CHOUABBIA-AMINE/iaas
-- Purpose: Complete security data initialization with ALL entries
-- Features: Uniqueness checks, grouped inserts, full RBAC hierarchy
-- Changes: Removed duplicate DOCUMENT permissions (IDs 60-61 removed)
-- Total: 263 permissions (was 265), 27 categories, 9 roles, 16 users
-- ============================================================================

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@OLD_COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

-- ============================================================================
-- SECTION 1: AUTHORITY CATEGORIES (t_00_02_05)
-- ============================================================================
-- 27 total categories grouped by domain
-- Uniqueness: F_01 (category code) must be unique

-- Clear existing data (optional - uncomment if doing fresh install)
-- DELETE FROM `t_00_02_05`;

INSERT INTO `t_00_02_05` (`F_00`, `F_02`, `F_01`, `F_03`) VALUES
-- ====== EXISTING CORE CATEGORIES (1-9) ======
(1, 'System Management', 'SYSTEM', NULL),
(2, 'User Management', 'USER', NULL),
(3, 'Role Management', 'ROLE', NULL),
(4, 'Permission Management', 'PERMISSION', NULL),
(5, 'Group Management', 'GROUP', NULL),
(6, 'Document Management', 'DOCUMENT', NULL),
(7, 'Audit Management', 'AUDIT', NULL),
(8, 'Configuration Management', 'CONFIG', NULL),
(9, 'Reporting', 'REPORT', NULL),

-- ====== NEW BUSINESS DOMAIN CATEGORIES (10-27) ======
-- Facilities Management
(10, 'Building and Room Management', 'BUILDING', NULL),

-- Document & Communication
(11, 'Mail and Document Management', 'MAIL', NULL),
(14, 'Notification Management', 'NOTIFICATION', NULL),

-- Financial & Procurement
(12, 'Procurement and Contract Management', 'PROCUREMENT', NULL),

-- Location & Geography
(13, 'Geographic and Location Data', 'GEOGRAPHY', NULL),

-- Service Management
(15, 'Service Management', 'SERVICE', NULL),
(16, 'Provider Management', 'PROVIDER', NULL),

-- Asset Management
(17, 'Equipment and Assets', 'EQUIPMENT', NULL),
(18, 'Stock and Inventory', 'STOCK', NULL),

-- Human Resources
(19, 'Personnel and HR Management', 'PERSONNEL', NULL),

-- Workflow & Operations
(20, 'Request and Workflow Management', 'REQUEST', NULL),
(21, 'Complaint and Feedback Management', 'COMPLAINT', NULL),
(22, 'Project Management', 'PROJECT', NULL),
(23, 'Task Management', 'TASK', NULL),

-- Logistics & Fleet
(24, 'Delivery and Logistics', 'DELIVERY', NULL),
(25, 'Vehicle Fleet Management', 'VEHICLE', NULL),
(26, 'Maintenance Management', 'MAINTENANCE', NULL),

-- Resource Planning
(27, 'Resource Allocation Management', 'RESOURCE', NULL)
ON DUPLICATE KEY UPDATE 
    `F_02` = VALUES(`F_02`),
    `F_03` = VALUES(`F_03`);

-- ============================================================================
-- SECTION 2: PERMISSIONS (t_00_02_04)
-- ============================================================================
-- 263 total permissions grouped by category (removed duplicate DOCUMENT entries)
-- Uniqueness: F_01 (permission code) must be unique
-- Format: {ENTITY}:{ACTION} where ACTION = READ|WRITE|ADMIN|DELETE

-- Clear existing data (optional - uncomment if doing fresh install)
-- DELETE FROM `t_00_02_04`;

INSERT INTO `t_00_02_04` (`F_00`, `F_04`, `F_02`, `F_01`, `F_03`) VALUES

-- ======================================================================
-- GROUP 1: CORE SYSTEM PERMISSIONS (1-31)
-- ======================================================================

-- SYSTEM Category (1-4)
(1, NULL, 'View system data', 'SYSTEM:READ', '1'),
(2, NULL, 'Write system data', 'SYSTEM:WRITE', '1'),
(3, NULL, 'Delete system data', 'SYSTEM:DELETE', '1'),
(4, NULL, 'Administer system', 'SYSTEM:ADMIN', '1'),

-- USER Category (5-8)
(5, NULL, 'View users', 'USER:READ', '2'),
(6, NULL, 'Create/edit users', 'USER:WRITE', '2'),
(7, NULL, 'Delete users', 'USER:DELETE', '2'),
(8, NULL, 'Administer users', 'USER:ADMIN', '2'),

-- ROLE Category (9-12)
(9, NULL, 'View roles', 'ROLE:READ', '3'),
(10, NULL, 'Create/edit roles', 'ROLE:WRITE', '3'),
(11, NULL, 'Delete roles', 'ROLE:DELETE', '3'),
(12, NULL, 'Administer roles', 'ROLE:ADMIN', '3'),

-- PERMISSION Category (13-15)
(13, NULL, 'View permissions', 'PERMISSION:READ', '4'),
(14, NULL, 'Create/edit permissions', 'PERMISSION:WRITE', '4'),
(15, NULL, 'Delete permissions', 'PERMISSION:DELETE', '4'),

-- GROUP Category (16-18)
(16, NULL, 'View groups', 'GROUP:READ', '5'),
(17, NULL, 'Create/edit groups', 'GROUP:WRITE', '5'),
(18, NULL, 'Administer groups', 'GROUP:ADMIN', '5'),

-- DOCUMENT Category (19-22) - Core document management
(19, NULL, 'View documents', 'DOCUMENT:READ', '6'),
(20, NULL, 'Create/edit documents', 'DOCUMENT:WRITE', '6'),
(21, NULL, 'Delete documents', 'DOCUMENT:DELETE', '6'),
(22, NULL, 'Administer documents', 'DOCUMENT:ADMIN', '6'),

-- AUDIT Category (23-25)
(23, NULL, 'View audit logs', 'AUDIT:READ', '7'),
(24, NULL, 'Create audit logs', 'AUDIT:WRITE', '7'),
(25, NULL, 'Delete audit logs', 'AUDIT:DELETE', '7'),

-- CONFIG Category (26-28)
(26, NULL, 'View configuration', 'CONFIG:READ', '8'),
(27, NULL, 'Edit configuration', 'CONFIG:WRITE', '8'),
(28, NULL, 'Administer configuration', 'CONFIG:ADMIN', '8'),

-- REPORT Category (29-31)
(29, NULL, 'View reports', 'REPORT:READ', '9'),
(30, NULL, 'Create reports', 'REPORT:WRITE', '9'),
(31, NULL, 'Administer reports', 'REPORT:ADMIN', '9'),

-- ======================================================================
-- GROUP 2: BUILDING & FACILITIES PERMISSIONS (32-51)
-- ======================================================================
-- Category: BUILDING (10 entities × 2 permissions = 20)

(32, NULL, 'View site locations', 'SITE:READ', '10'),
(33, NULL, 'Manage site locations', 'SITE:ADMIN', '10'),
(34, NULL, 'View buildings', 'BUILDING_ENTITY:READ', '10'),
(35, NULL, 'Manage buildings', 'BUILDING_ENTITY:ADMIN', '10'),
(36, NULL, 'View floors', 'FLOOR:READ', '10'),
(37, NULL, 'Manage floors', 'FLOOR:ADMIN', '10'),
(38, NULL, 'View rooms', 'ROOM:READ', '10'),
(39, NULL, 'Manage rooms', 'ROOM:ADMIN', '10'),
(40, NULL, 'View offices', 'OFFICE:READ', '10'),
(41, NULL, 'Manage offices', 'OFFICE:ADMIN', '10'),
(42, NULL, 'View parking spaces', 'PARKING:READ', '10'),
(43, NULL, 'Manage parking spaces', 'PARKING:ADMIN', '10'),
(44, NULL, 'View storage areas', 'STORAGE:READ', '10'),
(45, NULL, 'Manage storage areas', 'STORAGE:ADMIN', '10'),
(46, NULL, 'View workspaces', 'WORKSPACE:READ', '10'),
(47, NULL, 'Manage workspaces', 'WORKSPACE:ADMIN', '10'),
(48, NULL, 'View building floors', 'BUILDING_FLOOR:READ', '10'),
(49, NULL, 'Manage building floors', 'BUILDING_FLOOR:ADMIN', '10'),
(50, NULL, 'View office spaces', 'OFFICE_SPACE:READ', '10'),
(51, NULL, 'Manage office spaces', 'OFFICE_SPACE:ADMIN', '10'),

-- ======================================================================
-- GROUP 3: MAIL & COMMUNICATION PERMISSIONS (52-73)
-- ======================================================================
-- Category: MAIL (10 entities × 2 permissions = 20)
-- Note: DOCUMENT permissions removed (use core DOCUMENT category #19-22)

(52, NULL, 'View mail directions', 'MAIL_DIRECTION:READ', '11'),
(53, NULL, 'Manage mail directions', 'MAIL_DIRECTION:ADMIN', '11'),
(54, NULL, 'View mail types', 'MAIL_TYPE:READ', '11'),
(55, NULL, 'Manage mail types', 'MAIL_TYPE:ADMIN', '11'),
(56, NULL, 'View mail correspondence', 'MAIL:READ', '11'),
(57, NULL, 'Manage mail correspondence', 'MAIL:ADMIN', '11'),
(58, NULL, 'View document types', 'DOCUMENT_TYPE:READ', '11'),
(59, NULL, 'Manage document types', 'DOCUMENT_TYPE:ADMIN', '11'),
(60, NULL, 'View incoming mail', 'INCOMING_MAIL:READ', '11'),
(61, NULL, 'Manage incoming mail', 'INCOMING_MAIL:ADMIN', '11'),
(62, NULL, 'View outgoing mail', 'OUTGOING_MAIL:READ', '11'),
(63, NULL, 'Manage outgoing mail', 'OUTGOING_MAIL:ADMIN', '11'),
(64, NULL, 'View archives', 'ARCHIVE:READ', '11'),
(65, NULL, 'Manage archives', 'ARCHIVE:ADMIN', '11'),
(66, NULL, 'View correspondence', 'CORRESPONDENCE:READ', '11'),
(67, NULL, 'Manage correspondence', 'CORRESPONDENCE:ADMIN', '11'),
(68, NULL, 'View mail tracking', 'MAIL_TRACKING:READ', '11'),
(69, NULL, 'Manage mail tracking', 'MAIL_TRACKING:ADMIN', '11'),
(70, NULL, 'View files', 'FILE:READ', '11'),
(71, NULL, 'Manage files', 'FILE:ADMIN', '11'),
(72, NULL, 'View attachments', 'ATTACHMENT:READ', '11'),
(73, NULL, 'Manage attachments', 'ATTACHMENT:ADMIN', '11'),

-- ======================================================================
-- GROUP 4: PROCUREMENT PERMISSIONS (74-103)
-- ======================================================================
-- Category: PROCUREMENT (15 entities × 2 permissions = 30)

(74, NULL, 'View contracts', 'CONTRACT:READ', '12'),
(75, NULL, 'Manage contracts', 'CONTRACT:ADMIN', '12'),
(76, NULL, 'View tenders', 'TENDER:READ', '12'),
(77, NULL, 'Manage tenders', 'TENDER:ADMIN', '12'),
(78, NULL, 'View quotations', 'QUOTATION:READ', '12'),
(79, NULL, 'Manage quotations', 'QUOTATION:ADMIN', '12'),
(80, NULL, 'View purchase orders', 'PURCHASE_ORDER:READ', '12'),
(81, NULL, 'Manage purchase orders', 'PURCHASE_ORDER:ADMIN', '12'),
(82, NULL, 'View invoices', 'INVOICE:READ', '12'),
(83, NULL, 'Manage invoices', 'INVOICE:ADMIN', '12'),
(84, NULL, 'View payments', 'PAYMENT:READ', '12'),
(85, NULL, 'Manage payments', 'PAYMENT:ADMIN', '12'),
(86, NULL, 'View suppliers', 'SUPPLIER:READ', '12'),
(87, NULL, 'Manage suppliers', 'SUPPLIER:ADMIN', '12'),
(88, NULL, 'View bids', 'BID:READ', '12'),
(89, NULL, 'Manage bids', 'BID:ADMIN', '12'),
(90, NULL, 'View evaluations', 'EVALUATION:READ', '12'),
(91, NULL, 'Manage evaluations', 'EVALUATION:ADMIN', '12'),
(92, NULL, 'View contract amendments', 'CONTRACT_AMENDMENT:READ', '12'),
(93, NULL, 'Manage contract amendments', 'CONTRACT_AMENDMENT:ADMIN', '12'),
(94, NULL, 'View guarantees', 'GUARANTEE:READ', '12'),
(95, NULL, 'Manage guarantees', 'GUARANTEE:ADMIN', '12'),
(96, NULL, 'View tender committees', 'TENDER_COMMITTEE:READ', '12'),
(97, NULL, 'Manage tender committees', 'TENDER_COMMITTEE:ADMIN', '12'),
(98, NULL, 'View procurement plans', 'PROCUREMENT_PLAN:READ', '12'),
(99, NULL, 'Manage procurement plans', 'PROCUREMENT_PLAN:ADMIN', '12'),
(100, NULL, 'View budget allocations', 'BUDGET_ALLOCATION:READ', '12'),
(101, NULL, 'Manage budget allocations', 'BUDGET_ALLOCATION:ADMIN', '12'),
(102, NULL, 'View specifications', 'SPECIFICATION:READ', '12'),
(103, NULL, 'Manage specifications', 'SPECIFICATION:ADMIN', '12'),

-- ======================================================================
-- GROUP 5: GEOGRAPHY PERMISSIONS (104-115)
-- ======================================================================
-- Category: GEOGRAPHY (6 entities × 2 permissions = 12)

(104, NULL, 'View countries', 'COUNTRY:READ', '13'),
(105, NULL, 'Manage countries', 'COUNTRY:ADMIN', '13'),
(106, NULL, 'View provinces', 'PROVINCE:READ', '13'),
(107, NULL, 'Manage provinces', 'PROVINCE:ADMIN', '13'),
(108, NULL, 'View municipalities', 'MUNICIPALITY:READ', '13'),
(109, NULL, 'Manage municipalities', 'MUNICIPALITY:ADMIN', '13'),
(110, NULL, 'View locations', 'LOCATION:READ', '13'),
(111, NULL, 'Manage locations', 'LOCATION:ADMIN', '13'),
(112, NULL, 'View addresses', 'ADDRESS:READ', '13'),
(113, NULL, 'Manage addresses', 'ADDRESS:ADMIN', '13'),
(114, NULL, 'View regions', 'REGION:READ', '13'),
(115, NULL, 'Manage regions', 'REGION:ADMIN', '13'),

-- ======================================================================
-- GROUP 6: NOTIFICATION PERMISSIONS (116-123)
-- ======================================================================
-- Category: NOTIFICATION (4 entities × 2 permissions = 8)

(116, NULL, 'View notifications', 'NOTIFICATION_ENTITY:READ', '14'),
(117, NULL, 'Manage notifications', 'NOTIFICATION_ENTITY:ADMIN', '14'),
(118, NULL, 'View alerts', 'ALERT:READ', '14'),
(119, NULL, 'Manage alerts', 'ALERT:ADMIN', '14'),
(120, NULL, 'View messages', 'MESSAGE:READ', '14'),
(121, NULL, 'Manage messages', 'MESSAGE:ADMIN', '14'),
(122, NULL, 'View announcements', 'ANNOUNCEMENT:READ', '14'),
(123, NULL, 'Manage announcements', 'ANNOUNCEMENT:ADMIN', '14'),

-- ======================================================================
-- GROUP 7: SERVICE PERMISSIONS (124-139)
-- ======================================================================
-- Category: SERVICE (8 entities × 2 permissions = 16)

(124, NULL, 'View services', 'SERVICE_ENTITY:READ', '15'),
(125, NULL, 'Manage services', 'SERVICE_ENTITY:ADMIN', '15'),
(126, NULL, 'View service categories', 'SERVICE_CATEGORY:READ', '15'),
(127, NULL, 'Manage service categories', 'SERVICE_CATEGORY:ADMIN', '15'),
(128, NULL, 'View service requests', 'SERVICE_REQUEST:READ', '15'),
(129, NULL, 'Manage service requests', 'SERVICE_REQUEST:ADMIN', '15'),
(130, NULL, 'View service tickets', 'SERVICE_TICKET:READ', '15'),
(131, NULL, 'Manage service tickets', 'SERVICE_TICKET:ADMIN', '15'),
(132, NULL, 'View SLAs', 'SLA:READ', '15'),
(133, NULL, 'Manage SLAs', 'SLA:ADMIN', '15'),
(134, NULL, 'View service providers', 'SERVICE_PROVIDER:READ', '15'),
(135, NULL, 'Manage service providers', 'SERVICE_PROVIDER:ADMIN', '15'),
(136, NULL, 'View service contracts', 'SERVICE_CONTRACT:READ', '15'),
(137, NULL, 'Manage service contracts', 'SERVICE_CONTRACT:ADMIN', '15'),
(138, NULL, 'View interventions', 'INTERVENTION:READ', '15'),
(139, NULL, 'Manage interventions', 'INTERVENTION:ADMIN', '15'),

-- ======================================================================
-- GROUP 8: PROVIDER PERMISSIONS (140-149)
-- ======================================================================
-- Category: PROVIDER (5 entities × 2 permissions = 10)

(140, NULL, 'View providers', 'PROVIDER:READ', '16'),
(141, NULL, 'Manage providers', 'PROVIDER:ADMIN', '16'),
(142, NULL, 'View vendors', 'VENDOR:READ', '16'),
(143, NULL, 'Manage vendors', 'VENDOR:ADMIN', '16'),
(144, NULL, 'View contractors', 'CONTRACTOR:READ', '16'),
(145, NULL, 'Manage contractors', 'CONTRACTOR:ADMIN', '16'),
(146, NULL, 'View partners', 'PARTNER:READ', '16'),
(147, NULL, 'Manage partners', 'PARTNER:ADMIN', '16'),
(148, NULL, 'View clients', 'CLIENT:READ', '16'),
(149, NULL, 'Manage clients', 'CLIENT:ADMIN', '16'),

-- ======================================================================
-- GROUP 9: EQUIPMENT PERMISSIONS (150-169)
-- ======================================================================
-- Category: EQUIPMENT (10 entities × 2 permissions = 20)

(150, NULL, 'View equipment', 'EQUIPMENT_ENTITY:READ', '17'),
(151, NULL, 'Manage equipment', 'EQUIPMENT_ENTITY:ADMIN', '17'),
(152, NULL, 'View assets', 'ASSET:READ', '17'),
(153, NULL, 'Manage assets', 'ASSET:ADMIN', '17'),
(154, NULL, 'View furniture', 'FURNITURE:READ', '17'),
(155, NULL, 'Manage furniture', 'FURNITURE:ADMIN', '17'),
(156, NULL, 'View IT equipment', 'IT_EQUIPMENT:READ', '17'),
(157, NULL, 'Manage IT equipment', 'IT_EQUIPMENT:ADMIN', '17'),
(158, NULL, 'View computers', 'COMPUTER:READ', '17'),
(159, NULL, 'Manage computers', 'COMPUTER:ADMIN', '17'),
(160, NULL, 'View printers', 'PRINTER:READ', '17'),
(161, NULL, 'Manage printers', 'PRINTER:ADMIN', '17'),
(162, NULL, 'View devices', 'DEVICE:READ', '17'),
(163, NULL, 'Manage devices', 'DEVICE:ADMIN', '17'),
(164, NULL, 'View hardware', 'HARDWARE:READ', '17'),
(165, NULL, 'Manage hardware', 'HARDWARE:ADMIN', '17'),
(166, NULL, 'View software', 'SOFTWARE:READ', '17'),
(167, NULL, 'Manage software', 'SOFTWARE:ADMIN', '17'),
(168, NULL, 'View licenses', 'LICENSE:READ', '17'),
(169, NULL, 'Manage licenses', 'LICENSE:ADMIN', '17'),

-- ======================================================================
-- GROUP 10: STOCK & INVENTORY PERMISSIONS (170-185)
-- ======================================================================
-- Category: STOCK (8 entities × 2 permissions = 16)

(170, NULL, 'View stock', 'STOCK_ENTITY:READ', '18'),
(171, NULL, 'Manage stock', 'STOCK_ENTITY:ADMIN', '18'),
(172, NULL, 'View inventory', 'INVENTORY:READ', '18'),
(173, NULL, 'Manage inventory', 'INVENTORY:ADMIN', '18'),
(174, NULL, 'View warehouses', 'WAREHOUSE:READ', '18'),
(175, NULL, 'Manage warehouses', 'WAREHOUSE:ADMIN', '18'),
(176, NULL, 'View stock movements', 'STOCK_MOVEMENT:READ', '18'),
(177, NULL, 'Manage stock movements', 'STOCK_MOVEMENT:ADMIN', '18'),
(178, NULL, 'View supplies', 'SUPPLY:READ', '18'),
(179, NULL, 'Manage supplies', 'SUPPLY:ADMIN', '18'),
(180, NULL, 'View materials', 'MATERIAL:READ', '18'),
(181, NULL, 'Manage materials', 'MATERIAL:ADMIN', '18'),
(182, NULL, 'View consumables', 'CONSUMABLE:READ', '18'),
(183, NULL, 'Manage consumables', 'CONSUMABLE:ADMIN', '18'),
(184, NULL, 'View stock levels', 'STOCK_LEVEL:READ', '18'),
(185, NULL, 'Manage stock levels', 'STOCK_LEVEL:ADMIN', '18'),

-- ======================================================================
-- GROUP 11: PERSONNEL & HR PERMISSIONS (186-199)
-- ======================================================================
-- Category: PERSONNEL (7 entities × 2 permissions = 14)

(186, NULL, 'View employees', 'EMPLOYEE:READ', '19'),
(187, NULL, 'Manage employees', 'EMPLOYEE:ADMIN', '19'),
(188, NULL, 'View personnel', 'PERSONNEL_ENTITY:READ', '19'),
(189, NULL, 'Manage personnel', 'PERSONNEL_ENTITY:ADMIN', '19'),
(190, NULL, 'View departments', 'DEPARTMENT:READ', '19'),
(191, NULL, 'Manage departments', 'DEPARTMENT:ADMIN', '19'),
(192, NULL, 'View positions', 'POSITION:READ', '19'),
(193, NULL, 'Manage positions', 'POSITION:ADMIN', '19'),
(194, NULL, 'View attendance', 'ATTENDANCE:READ', '19'),
(195, NULL, 'Manage attendance', 'ATTENDANCE:ADMIN', '19'),
(196, NULL, 'View leave records', 'LEAVE:READ', '19'),
(197, NULL, 'Manage leave records', 'LEAVE:ADMIN', '19'),
(198, NULL, 'View payroll', 'PAYROLL:READ', '19'),
(199, NULL, 'Manage payroll', 'PAYROLL:ADMIN', '19'),

-- ======================================================================
-- GROUP 12: REQUEST & WORKFLOW PERMISSIONS (200-209)
-- ======================================================================
-- Category: REQUEST (5 entities × 2 permissions = 10)

(200, NULL, 'View requests', 'REQUEST_ENTITY:READ', '20'),
(201, NULL, 'Manage requests', 'REQUEST_ENTITY:ADMIN', '20'),
(202, NULL, 'View work orders', 'WORK_ORDER:READ', '20'),
(203, NULL, 'Manage work orders', 'WORK_ORDER:ADMIN', '20'),
(204, NULL, 'View approvals', 'APPROVAL:READ', '20'),
(205, NULL, 'Manage approvals', 'APPROVAL:ADMIN', '20'),
(206, NULL, 'View workflows', 'WORKFLOW:READ', '20'),
(207, NULL, 'Manage workflows', 'WORKFLOW:ADMIN', '20'),
(208, NULL, 'View forms', 'FORM:READ', '20'),
(209, NULL, 'Manage forms', 'FORM:ADMIN', '20'),

-- ======================================================================
-- GROUP 13: COMPLAINT & FEEDBACK PERMISSIONS (210-215)
-- ======================================================================
-- Category: COMPLAINT (3 entities × 2 permissions = 6)

(210, NULL, 'View complaints', 'COMPLAINT_ENTITY:READ', '21'),
(211, NULL, 'Manage complaints', 'COMPLAINT_ENTITY:ADMIN', '21'),
(212, NULL, 'View feedback', 'FEEDBACK:READ', '21'),
(213, NULL, 'Manage feedback', 'FEEDBACK:ADMIN', '21'),
(214, NULL, 'View suggestions', 'SUGGESTION:READ', '21'),
(215, NULL, 'Manage suggestions', 'SUGGESTION:ADMIN', '21'),

-- ======================================================================
-- GROUP 14: PROJECT MANAGEMENT PERMISSIONS (216-223)
-- ======================================================================
-- Category: PROJECT (4 entities × 2 permissions = 8)

(216, NULL, 'View projects', 'PROJECT_ENTITY:READ', '22'),
(217, NULL, 'Manage projects', 'PROJECT_ENTITY:ADMIN', '22'),
(218, NULL, 'View milestones', 'MILESTONE:READ', '22'),
(219, NULL, 'Manage milestones', 'MILESTONE:ADMIN', '22'),
(220, NULL, 'View phases', 'PHASE:READ', '22'),
(221, NULL, 'Manage phases', 'PHASE:ADMIN', '22'),
(222, NULL, 'View budgets', 'BUDGET:READ', '22'),
(223, NULL, 'Manage budgets', 'BUDGET:ADMIN', '22'),

-- ======================================================================
-- GROUP 15: TASK MANAGEMENT PERMISSIONS (224-229)
-- ======================================================================
-- Category: TASK (3 entities × 2 permissions = 6)

(224, NULL, 'View tasks', 'TASK_ENTITY:READ', '23'),
(225, NULL, 'Manage tasks', 'TASK_ENTITY:ADMIN', '23'),
(226, NULL, 'View assignments', 'ASSIGNMENT:READ', '23'),
(227, NULL, 'Manage assignments', 'ASSIGNMENT:ADMIN', '23'),
(228, NULL, 'View schedules', 'SCHEDULE:READ', '23'),
(229, NULL, 'Manage schedules', 'SCHEDULE:ADMIN', '23'),

-- ======================================================================
-- GROUP 16: DELIVERY & LOGISTICS PERMISSIONS (230-237)
-- ======================================================================
-- Category: DELIVERY (4 entities × 2 permissions = 8)

(230, NULL, 'View deliveries', 'DELIVERY_ENTITY:READ', '24'),
(231, NULL, 'Manage deliveries', 'DELIVERY_ENTITY:ADMIN', '24'),
(232, NULL, 'View shipments', 'SHIPMENT:READ', '24'),
(233, NULL, 'Manage shipments', 'SHIPMENT:ADMIN', '24'),
(234, NULL, 'View receptions', 'RECEPTION:READ', '24'),
(235, NULL, 'Manage receptions', 'RECEPTION:ADMIN', '24'),
(236, NULL, 'View tracking', 'TRACKING:READ', '24'),
(237, NULL, 'Manage tracking', 'TRACKING:ADMIN', '24'),

-- ======================================================================
-- GROUP 17: VEHICLE FLEET PERMISSIONS (238-247)
-- ======================================================================
-- Category: VEHICLE (5 entities × 2 permissions = 10)

(238, NULL, 'View vehicles', 'VEHICLE_ENTITY:READ', '25'),
(239, NULL, 'Manage vehicles', 'VEHICLE_ENTITY:ADMIN', '25'),
(240, NULL, 'View cars', 'CAR:READ', '25'),
(241, NULL, 'Manage cars', 'CAR:ADMIN', '25'),
(242, NULL, 'View drivers', 'DRIVER:READ', '25'),
(243, NULL, 'Manage drivers', 'DRIVER:ADMIN', '25'),
(244, NULL, 'View trips', 'TRIP:READ', '25'),
(245, NULL, 'Manage trips', 'TRIP:ADMIN', '25'),
(246, NULL, 'View fuel records', 'FUEL:READ', '25'),
(247, NULL, 'Manage fuel records', 'FUEL:ADMIN', '25'),

-- ======================================================================
-- GROUP 18: MAINTENANCE PERMISSIONS (248-255)
-- ======================================================================
-- Category: MAINTENANCE (4 entities × 2 permissions = 8)

(248, NULL, 'View maintenance', 'MAINTENANCE_ENTITY:READ', '26'),
(249, NULL, 'Manage maintenance', 'MAINTENANCE_ENTITY:ADMIN', '26'),
(250, NULL, 'View repairs', 'REPAIR:READ', '26'),
(251, NULL, 'Manage repairs', 'REPAIR:ADMIN', '26'),
(252, NULL, 'View inspections', 'INSPECTION:READ', '26'),
(253, NULL, 'Manage inspections', 'INSPECTION:ADMIN', '26'),
(254, NULL, 'View preventive maintenance', 'PREVENTIVE_MAINTENANCE:READ', '26'),
(255, NULL, 'Manage preventive maintenance', 'PREVENTIVE_MAINTENANCE:ADMIN', '26'),

-- ======================================================================
-- GROUP 19: RESOURCE MANAGEMENT PERMISSIONS (256-263)
-- ======================================================================
-- Category: RESOURCE (4 entities × 2 permissions = 8)

(256, NULL, 'View resources', 'RESOURCE_ENTITY:READ', '27'),
(257, NULL, 'Manage resources', 'RESOURCE_ENTITY:ADMIN', '27'),
(258, NULL, 'View allocations', 'ALLOCATION:READ', '27'),
(259, NULL, 'Manage allocations', 'ALLOCATION:ADMIN', '27'),
(260, NULL, 'View capacity', 'CAPACITY:READ', '27'),
(261, NULL, 'Manage capacity', 'CAPACITY:ADMIN', '27'),
(262, NULL, 'View reservations', 'RESERVATION:READ', '27'),
(263, NULL, 'Manage reservations', 'RESERVATION:ADMIN', '27')

ON DUPLICATE KEY UPDATE 
    `F_02` = VALUES(`F_02`),
    `F_03` = VALUES(`F_03`),
    `F_04` = VALUES(`F_04`);

-- ============================================================================
-- SECTION 3: ROLES (t_00_02_03)
-- ============================================================================
-- 9 roles with clear hierarchy
-- Uniqueness: F_01 (role code) must be unique

INSERT INTO `t_00_02_03` (`F_00`, `F_02`, `F_01`, `F_03`) VALUES
(1, 'Super Administrator - Full system access', 'ROLE_SUPER_ADMIN', NULL),
(2, 'System Administrator - Nearly full access', 'ROLE_ADMIN', NULL),
(3, 'Department Manager - Read all + dept management', 'ROLE_MANAGER', NULL),
(4, 'User Manager - User and group management', 'ROLE_USER_MANAGER', NULL),
(5, 'Employee - Basic read-only access', 'ROLE_EMPLOYEE', NULL),
(6, 'Auditor - Audit and compliance access', 'ROLE_AUDITOR', NULL),
(7, 'Data Analyst - Analysis and reporting', 'ROLE_ANALYST', NULL),
(8, 'Document Approver - Document approval authority', 'ROLE_DOCUMENT_APPROVER', NULL),
(9, 'Viewer - Minimal read-only access', 'ROLE_VIEWER', NULL)
ON DUPLICATE KEY UPDATE 
    `F_02` = VALUES(`F_02`),
    `F_03` = VALUES(`F_03`);

-- ============================================================================
-- SECTION 4: USERS (t_00_02_02)
-- ============================================================================
-- 16 users with proper security flags
-- Uniqueness: F_01 (username) must be unique
-- Note: Passwords are BCrypt hashed

INSERT INTO `t_00_02_02` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`, `F_05`, `F_06`, `F_07`) VALUES
-- Administrator accounts
(1, 'superadmin', 'superadmin@iaas.local', '$2a$12$LQv3c1yqBwlVHLZeKj/.lOHk9Qp1yqBwlVHLZeKj/.lOHk9Qp1yqBwl', b'1', b'1', b'1', b'1'),
(2, 'admin', 'admin@iaas.local', '$2a$12$8zK8mN5pQ2rT7vW3xY4uZ0aB1cD2eF3gH4iJ5kL6mN7oP8qR9sT0', b'1', b'1', b'1', b'1'),
(3, 'it.admin', 'it.admin@iaas.local', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'1', b'1', b'1'),

-- Department managers
(4, 'hr.manager', 'hr.manager@iaas.local', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'1', b'1', b'1'),
(5, 'finance.manager', 'finance.manager@iaas.local', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'1', b'1', b'1'),
(6, 'ops.manager', 'ops.manager@iaas.local', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'1', b'1', b'1'),

-- Regular employees
(7, 'john.doe', 'john.doe@iaas.local', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'1', b'1', b'1'),
(8, 'jane.smith', 'jane.smith@iaas.local', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'1', b'1', b'1'),
(9, 'bob.wilson', 'bob.wilson@iaas.local', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'1', b'1', b'1'),
(10, 'alice.brown', 'alice.brown@iaas.local', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'1', b'1', b'1'),

-- Specialized roles
(11, 'auditor', 'auditor@iaas.local', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'1', b'1', b'1'),
(12, 'analyst', 'analyst@iaas.local', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'1', b'1', b'1'),
(13, 'approver', 'approver@iaas.local', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'1', b'1', b'1'),
(14, 'viewer', 'viewer@iaas.local', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'1', b'1', b'1'),

-- Test/inactive accounts
(15, 'disabled.user', 'disabled@iaas.local', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'0', b'1', b'1', b'0'),
(16, 'locked.user', 'locked@iaas.local', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', b'1', b'0', b'1', b'0')

ON DUPLICATE KEY UPDATE 
    `F_02` = VALUES(`F_02`),
    `F_03` = VALUES(`F_03`),
    `F_04` = VALUES(`F_04`),
    `F_05` = VALUES(`F_05`),
    `F_06` = VALUES(`F_06`),
    `F_07` = VALUES(`F_07`);

-- ============================================================================
-- SECTION 5: ROLE-PERMISSION MAPPINGS (r_t000203_t000204)
-- ============================================================================
-- Complete permission assignments for all 9 roles
-- Uniqueness: (F_01, F_02) composite key
-- Note: Permission IDs have been renumbered (removed duplicates 60-61)

-- Clear existing mappings (optional)
-- DELETE FROM `r_t000203_t000204`;

-- SUPER_ADMIN (Role 1) - ALL 263 permissions
INSERT INTO `r_t000203_t000204` (`F_01`, `F_02`) VALUES
(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),
(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),
(1,21),(1,22),(1,23),(1,24),(1,25),(1,26),(1,27),(1,28),(1,29),(1,30),
(1,31),(1,32),(1,33),(1,34),(1,35),(1,36),(1,37),(1,38),(1,39),(1,40),
(1,41),(1,42),(1,43),(1,44),(1,45),(1,46),(1,47),(1,48),(1,49),(1,50),
(1,51),(1,52),(1,53),(1,54),(1,55),(1,56),(1,57),(1,58),(1,59),(1,60),
(1,61),(1,62),(1,63),(1,64),(1,65),(1,66),(1,67),(1,68),(1,69),(1,70),
(1,71),(1,72),(1,73),(1,74),(1,75),(1,76),(1,77),(1,78),(1,79),(1,80),
(1,81),(1,82),(1,83),(1,84),(1,85),(1,86),(1,87),(1,88),(1,89),(1,90),
(1,91),(1,92),(1,93),(1,94),(1,95),(1,96),(1,97),(1,98),(1,99),(1,100),
(1,101),(1,102),(1,103),(1,104),(1,105),(1,106),(1,107),(1,108),(1,109),(1,110),
(1,111),(1,112),(1,113),(1,114),(1,115),(1,116),(1,117),(1,118),(1,119),(1,120),
(1,121),(1,122),(1,123),(1,124),(1,125),(1,126),(1,127),(1,128),(1,129),(1,130),
(1,131),(1,132),(1,133),(1,134),(1,135),(1,136),(1,137),(1,138),(1,139),(1,140),
(1,141),(1,142),(1,143),(1,144),(1,145),(1,146),(1,147),(1,148),(1,149),(1,150),
(1,151),(1,152),(1,153),(1,154),(1,155),(1,156),(1,157),(1,158),(1,159),(1,160),
(1,161),(1,162),(1,163),(1,164),(1,165),(1,166),(1,167),(1,168),(1,169),(1,170),
(1,171),(1,172),(1,173),(1,174),(1,175),(1,176),(1,177),(1,178),(1,179),(1,180),
(1,181),(1,182),(1,183),(1,184),(1,185),(1,186),(1,187),(1,188),(1,189),(1,190),
(1,191),(1,192),(1,193),(1,194),(1,195),(1,196),(1,197),(1,198),(1,199),(1,200),
(1,201),(1,202),(1,203),(1,204),(1,205),(1,206),(1,207),(1,208),(1,209),(1,210),
(1,211),(1,212),(1,213),(1,214),(1,215),(1,216),(1,217),(1,218),(1,219),(1,220),
(1,221),(1,222),(1,223),(1,224),(1,225),(1,226),(1,227),(1,228),(1,229),(1,230),
(1,231),(1,232),(1,233),(1,234),(1,235),(1,236),(1,237),(1,238),(1,239),(1,240),
(1,241),(1,242),(1,243),(1,244),(1,245),(1,246),(1,247),(1,248),(1,249),(1,250),
(1,251),(1,252),(1,253),(1,254),(1,255),(1,256),(1,257),(1,258),(1,259),(1,260),
(1,261),(1,262),(1,263)
ON DUPLICATE KEY UPDATE F_01=F_01;

-- ADMIN (Role 2) - 262 permissions (excludes SYSTEM:DELETE #3)
INSERT INTO `r_t000203_t000204` (`F_01`, `F_02`) VALUES
(2,1),(2,2),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(2,10),
(2,11),(2,12),(2,13),(2,14),(2,15),(2,16),(2,17),(2,18),(2,19),(2,20),
(2,21),(2,22),(2,23),(2,24),(2,25),(2,26),(2,27),(2,28),(2,29),(2,30),
(2,31),(2,32),(2,33),(2,34),(2,35),(2,36),(2,37),(2,38),(2,39),(2,40),
(2,41),(2,42),(2,43),(2,44),(2,45),(2,46),(2,47),(2,48),(2,49),(2,50),
(2,51),(2,52),(2,53),(2,54),(2,55),(2,56),(2,57),(2,58),(2,59),(2,60),
(2,61),(2,62),(2,63),(2,64),(2,65),(2,66),(2,67),(2,68),(2,69),(2,70),
(2,71),(2,72),(2,73),(2,74),(2,75),(2,76),(2,77),(2,78),(2,79),(2,80),
(2,81),(2,82),(2,83),(2,84),(2,85),(2,86),(2,87),(2,88),(2,89),(2,90),
(2,91),(2,92),(2,93),(2,94),(2,95),(2,96),(2,97),(2,98),(2,99),(2,100),
(2,101),(2,102),(2,103),(2,104),(2,105),(2,106),(2,107),(2,108),(2,109),(2,110),
(2,111),(2,112),(2,113),(2,114),(2,115),(2,116),(2,117),(2,118),(2,119),(2,120),
(2,121),(2,122),(2,123),(2,124),(2,125),(2,126),(2,127),(2,128),(2,129),(2,130),
(2,131),(2,132),(2,133),(2,134),(2,135),(2,136),(2,137),(2,138),(2,139),(2,140),
(2,141),(2,142),(2,143),(2,144),(2,145),(2,146),(2,147),(2,148),(2,149),(2,150),
(2,151),(2,152),(2,153),(2,154),(2,155),(2,156),(2,157),(2,158),(2,159),(2,160),
(2,161),(2,162),(2,163),(2,164),(2,165),(2,166),(2,167),(2,168),(2,169),(2,170),
(2,171),(2,172),(2,173),(2,174),(2,175),(2,176),(2,177),(2,178),(2,179),(2,180),
(2,181),(2,182),(2,183),(2,184),(2,185),(2,186),(2,187),(2,188),(2,189),(2,190),
(2,191),(2,192),(2,193),(2,194),(2,195),(2,196),(2,197),(2,198),(2,199),(2,200),
(2,201),(2,202),(2,203),(2,204),(2,205),(2,206),(2,207),(2,208),(2,209),(2,210),
(2,211),(2,212),(2,213),(2,214),(2,215),(2,216),(2,217),(2,218),(2,219),(2,220),
(2,221),(2,222),(2,223),(2,224),(2,225),(2,226),(2,227),(2,228),(2,229),(2,230),
(2,231),(2,232),(2,233),(2,234),(2,235),(2,236),(2,237),(2,238),(2,239),(2,240),
(2,241),(2,242),(2,243),(2,244),(2,245),(2,246),(2,247),(2,248),(2,249),(2,250),
(2,251),(2,252),(2,253),(2,254),(2,255),(2,256),(2,257),(2,258),(2,259),(2,260),
(2,261),(2,262),(2,263)
ON DUPLICATE KEY UPDATE F_01=F_01;

-- MANAGER (Role 3) - All READ + department ADMIN permissions
INSERT INTO `r_t000203_t000204` (`F_01`, `F_02`) VALUES
(3,1),(3,5),(3,9),(3,13),(3,16),(3,19),(3,23),(3,26),(3,29),
(3,32),(3,34),(3,36),(3,38),(3,40),(3,42),(3,44),(3,46),(3,48),(3,50),
(3,52),(3,54),(3,56),(3,58),(3,60),(3,62),(3,64),(3,66),(3,68),(3,70),
(3,72),(3,74),(3,76),(3,78),(3,80),(3,82),(3,84),(3,86),(3,88),(3,90),
(3,92),(3,94),(3,96),(3,98),(3,100),(3,102),(3,104),(3,106),(3,108),(3,110),
(3,112),(3,114),(3,116),(3,118),(3,120),(3,122),(3,124),(3,126),(3,128),(3,130),
(3,132),(3,134),(3,136),(3,138),(3,140),(3,142),(3,144),(3,146),(3,148),(3,150),
(3,152),(3,154),(3,156),(3,158),(3,160),(3,162),(3,164),(3,166),(3,168),(3,170),
(3,172),(3,174),(3,176),(3,178),(3,180),(3,182),(3,184),(3,186),(3,188),(3,190),
(3,192),(3,194),(3,196),(3,198),(3,200),(3,202),(3,204),(3,206),(3,208),(3,210),
(3,212),(3,214),(3,216),(3,218),(3,220),(3,222),(3,224),(3,226),(3,228),(3,230),
(3,232),(3,234),(3,236),(3,238),(3,240),(3,242),(3,244),(3,246),(3,248),(3,250),
(3,252),(3,254),(3,256),(3,258),(3,260),(3,262),
(3,191),(3,193),(3,195),(3,197),(3,201),(3,203),(3,205),(3,207),(3,209),
(3,217),(3,219),(3,225),(3,227)
ON DUPLICATE KEY UPDATE F_01=F_01;

-- USER_MANAGER (Role 4) - User/personnel management (18 permissions)
INSERT INTO `r_t000203_t000204` (`F_01`, `F_02`) VALUES
(4,5),(4,6),(4,7),(4,8),(4,9),(4,10),(4,13),(4,14),(4,16),(4,17),(4,18),(4,23),
(4,186),(4,187),(4,188),(4,189),(4,190),(4,191)
ON DUPLICATE KEY UPDATE F_01=F_01;

-- EMPLOYEE (Role 5) - Basic read-only (26 permissions)
INSERT INTO `r_t000203_t000204` (`F_01`, `F_02`) VALUES
(5,1),(5,5),(5,16),(5,19),(5,23),(5,29),
(5,32),(5,34),(5,36),(5,38),(5,52),(5,54),(5,56),
(5,104),(5,106),(5,108),(5,116),(5,118),(5,120),(5,122),
(5,124),(5,128),(5,186),(5,190),(5,200),(5,210),(5,216),(5,224)
ON DUPLICATE KEY UPDATE F_01=F_01;

-- AUDITOR (Role 6) - Audit and compliance (48 permissions)
INSERT INTO `r_t000203_t000204` (`F_01`, `F_02`) VALUES
(6,1),(6,5),(6,9),(6,13),(6,16),(6,19),(6,23),(6,24),(6,25),(6,26),(6,29),(6,30),(6,31),
(6,32),(6,34),(6,36),(6,38),(6,40),(6,42),(6,44),(6,46),(6,48),(6,50),
(6,52),(6,54),(6,56),(6,58),(6,60),(6,62),(6,64),(6,66),(6,68),(6,70),
(6,72),(6,74),(6,76),(6,78),(6,80),(6,82),(6,84),(6,86),(6,88),(6,90),
(6,92),(6,94),(6,96),(6,98),(6,100),(6,102)
ON DUPLICATE KEY UPDATE F_01=F_01;

-- ANALYST (Role 7) - Data analysis (36 permissions)
INSERT INTO `r_t000203_t000204` (`F_01`, `F_02`) VALUES
(7,1),(7,5),(7,9),(7,13),(7,16),(7,19),(7,23),(7,29),(7,30),(7,31),
(7,32),(7,34),(7,36),(7,38),(7,40),(7,52),(7,54),(7,56),(7,74),(7,76),
(7,78),(7,80),(7,82),(7,84),(7,86),(7,170),(7,172),(7,174),(7,186),(7,188),
(7,190),(7,192),(7,216),(7,218),(7,220),(7,222)
ON DUPLICATE KEY UPDATE F_01=F_01;

-- DOCUMENT_APPROVER (Role 8) - Document approval (24 permissions)
INSERT INTO `r_t000203_t000204` (`F_01`, `F_02`) VALUES
(8,1),(8,5),(8,19),(8,20),(8,21),(8,22),
(8,52),(8,54),(8,56),(8,57),(8,58),(8,60),
(8,62),(8,64),(8,66),(8,70),(8,72),
(8,74),(8,76),(8,78),(8,90),(8,92),(8,204),(8,205)
ON DUPLICATE KEY UPDATE F_01=F_01;

-- VIEWER (Role 9) - Minimal read-only (11 permissions)
INSERT INTO `r_t000203_t000204` (`F_01`, `F_02`) VALUES
(9,1),(9,5),(9,16),(9,19),(9,29),
(9,32),(9,52),(9,104),(9,116),(9,186),(9,210)
ON DUPLICATE KEY UPDATE F_01=F_01;

-- ============================================================================
-- SECTION 6: USER-ROLE ASSIGNMENTS (r_t000202_t000203)
-- ============================================================================
-- Assigns roles to users
-- Uniqueness: (F_01, F_02) composite key

INSERT INTO `r_t000202_t000203` (`F_01`, `F_02`) VALUES
-- Administrators
(1,1),   -- superadmin -> SUPER_ADMIN
(2,2),   -- admin -> ADMIN
(3,2), (3,4),  -- it.admin -> ADMIN + USER_MANAGER

-- Department Managers
(4,3), (5,3), (6,3),  -- hr/finance/ops managers -> MANAGER

-- Regular Employees
(7,5), (8,5), (9,5), (10,5),  -- employees -> EMPLOYEE

-- Specialized Roles
(11,6),  -- auditor -> AUDITOR
(12,7),  -- analyst -> ANALYST
(13,8),  -- approver -> DOCUMENT_APPROVER
(14,9)   -- viewer -> VIEWER

ON DUPLICATE KEY UPDATE F_01=F_01;

-- ============================================================================
-- SECTION 7: VERIFICATION & VALIDATION
-- ============================================================================

SELECT '========================================' as '';
SELECT '   IAAS SECURITY SETUP VERIFICATION' as '';
SELECT '========================================' as '';

SELECT 'Authority Categories' as Component, COUNT(*) as Total_Expected_27 
FROM `t_00_02_05` WHERE `F_00` <= 27;

SELECT 'Permissions' as Component, COUNT(*) as Total_Expected_263 
FROM `t_00_02_04` WHERE `F_00` <= 263;

SELECT 'Roles' as Component, COUNT(*) as Total_Expected_9 
FROM `t_00_02_03` WHERE `F_00` <= 9;

SELECT 'Users' as Component, COUNT(*) as Total_Expected_16 
FROM `t_00_02_02` WHERE `F_00` <= 16;

SELECT 'Active Users' as Component, COUNT(*) as Total_Expected_14 
FROM `t_00_02_02` WHERE `F_07` = b'1';

SELECT 'Role-Permission Mappings' as Component, COUNT(*) as Total 
FROM `r_t000203_t000204`;

SELECT 'User-Role Assignments' as Component, COUNT(*) as Total 
FROM `r_t000202_t000203`;

SELECT '========================================' as '';
SELECT '   ROLE PERMISSION COUNTS' as '';
SELECT '========================================' as '';

SELECT r.`F_01` as Role, COUNT(rp.`F_02`) as Permissions
FROM `t_00_02_03` r
LEFT JOIN `r_t000203_t000204` rp ON r.`F_00` = rp.`F_01`
WHERE r.`F_00` <= 9
GROUP BY r.`F_00`, r.`F_01`
ORDER BY r.`F_00`;

SELECT '========================================' as '';
SELECT '   USER ROLE ASSIGNMENTS' as '';
SELECT '========================================' as '';

SELECT 
    u.`F_01` as Username, 
    u.`F_02` as Email,
    CASE WHEN u.`F_07` = b'1' THEN 'Active' ELSE 'Inactive' END as Status,
    GROUP_CONCAT(r.`F_01` ORDER BY r.`F_00` SEPARATOR ', ') as Roles
FROM `t_00_02_02` u 
LEFT JOIN `r_t000202_t000203` ur ON u.`F_00` = ur.`F_01` 
LEFT JOIN `t_00_02_03` r ON ur.`F_02` = r.`F_00` 
WHERE u.`F_00` <= 16
GROUP BY u.`F_00`, u.`F_01`, u.`F_02`, u.`F_07`
ORDER BY u.`F_00`;

SELECT '========================================' as '';
SELECT '   UNIQUENESS VALIDATION' as '';
SELECT '========================================' as '';

SELECT 'Duplicate Permission Codes' as Check, COUNT(*) as Count_Should_Be_0
FROM (
    SELECT `F_01`, COUNT(*) as cnt 
    FROM `t_00_02_04` 
    WHERE `F_00` <= 263
    GROUP BY `F_01` 
    HAVING cnt > 1
) as duplicates;

SELECT 'Duplicate Category Codes' as Check, COUNT(*) as Count_Should_Be_0
FROM (
    SELECT `F_01`, COUNT(*) as cnt 
    FROM `t_00_02_05` 
    WHERE `F_00` <= 27
    GROUP BY `F_01` 
    HAVING cnt > 1
) as duplicates;

SELECT 'Duplicate Role Codes' as Check, COUNT(*) as Count_Should_Be_0
FROM (
    SELECT `F_01`, COUNT(*) as cnt 
    FROM `t_00_02_03` 
    WHERE `F_00` <= 9
    GROUP BY `F_01` 
    HAVING cnt > 1
) as duplicates;

SELECT 'Duplicate Usernames' as Check, COUNT(*) as Count_Should_Be_0
FROM (
    SELECT `F_01`, COUNT(*) as cnt 
    FROM `t_00_02_02` 
    WHERE `F_00` <= 16
    GROUP BY `F_01` 
    HAVING cnt > 1
) as duplicates;

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- ============================================================================
-- POST-INSTALLATION SECURITY CHECKLIST
-- ============================================================================

/*
🔐 CRITICAL SECURITY ACTIONS - Execute Immediately:

1. ⚠️  CHANGE SUPERADMIN PASSWORD:
   UPDATE t_00_02_02 SET F_03 = 'YOUR_NEW_BCRYPT_HASH' WHERE F_01 = 'superadmin';

2. ⚠️  CHANGE ADMIN PASSWORD:
   UPDATE t_00_02_02 SET F_03 = 'YOUR_NEW_BCRYPT_HASH' WHERE F_01 = 'admin';

3. 🗑️  REMOVE OR SECURE TEST USERS:
   DELETE FROM t_00_02_02 WHERE F_00 BETWEEN 7 AND 16;

4. 💾 BACKUP SECURITY TABLES:
   mysqldump -u root -p iaas_db \\
     t_00_02_02 t_00_02_03 t_00_02_04 t_00_02_05 \\
     r_t000202_t000203 r_t000203_t000204 \\
     > security_backup_$(date +%Y%m%d_%H%M%S).sql

✅ SETUP COMPLETE - Version 2.1!
   - 27 Authority Categories
   - 263 Unique Permissions (removed DOCUMENT duplicates)
   - 9 Role Hierarchy Levels
   - 16 Users (14 active, 2 test)
   - Full RBAC Implementation
   - Enterprise-Grade Security

📋 Changes in v2.1:
   - ❌ Removed duplicate DOCUMENT:READ (was ID 60)
   - ❌ Removed duplicate DOCUMENT:ADMIN (was ID 61)
   - ✅ Core DOCUMENT permissions remain at IDs 19-22
   - 🔄 All permissions after ID 59 renumbered sequentially
   - ✅ No gaps in permission IDs
   - ✅ All role mappings updated with correct IDs
*/

-- EOF security_db.sql v2.1
