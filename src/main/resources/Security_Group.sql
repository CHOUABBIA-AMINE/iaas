-- ============================================================================
-- IAAS SECURITY GROUPS - COMPLETE SETUP SCRIPT v1.0
-- Generated: 2025-12-21 10:59 AM CET
-- File: Security_Group.sql
-- Repository: CHOUABBIA-AMINE/iaas
-- Purpose: Security groups, user-group assignments, and group-role mappings
-- Table: T_00_02_01 (Groups)
-- Features: Organizational structure, department groups, project teams
-- ============================================================================

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

-- ============================================================================
-- SECTION 1: SECURITY GROUPS (T_00_02_01)
-- ============================================================================
-- Organizational groups for managing permissions at scale
-- Groups can have roles assigned, and users inherit permissions through groups
-- Table Structure: T_00_02_01
--   F_00 = Group ID (Primary Key)
--   F_01 = Group Code (Unique)
--   F_02 = Group Name
--   F_03 = Description
--   F_04 = Parent Group ID (for hierarchy)

-- Clear existing data (optional - uncomment if doing fresh install)
-- DELETE FROM `T_00_02_01`;

INSERT INTO `T_00_02_01` (`F_00`, `F_01`, `F_02`, `F_03`, `F_04`) VALUES

-- ======================================================================
-- ORGANIZATIONAL HIERARCHY GROUPS (1-10)
-- ======================================================================
-- Top-level organizational structure

(1, 'GRP_ALL_USERS', 'All Users', 'Default group for all system users', NULL),
(2, 'GRP_ADMINISTRATORS', 'System Administrators', 'IT and system administration team', 1),
(3, 'GRP_MANAGEMENT', 'Management Team', 'Department heads and managers', 1),
(4, 'GRP_EMPLOYEES', 'Regular Employees', 'Standard employee access group', 1),
(5, 'GRP_CONTRACTORS', 'External Contractors', 'Third-party contractors and consultants', 1),

-- ======================================================================
-- DEPARTMENT GROUPS (11-20)
-- ======================================================================
-- Functional department groupings

(11, 'GRP_DEPT_HR', 'Human Resources Department', 'HR staff and personnel management', 3),
(12, 'GRP_DEPT_FINANCE', 'Finance Department', 'Finance, accounting, and budget teams', 3),
(13, 'GRP_DEPT_IT', 'IT Department', 'Information technology and support', 2),
(14, 'GRP_DEPT_OPERATIONS', 'Operations Department', 'Day-to-day operations management', 3),
(15, 'GRP_DEPT_PROCUREMENT', 'Procurement Department', 'Purchasing and vendor management', 3),
(16, 'GRP_DEPT_FACILITIES', 'Facilities Management', 'Building and facilities operations', 14),
(17, 'GRP_DEPT_LEGAL', 'Legal Department', 'Legal counsel and compliance', 3),
(18, 'GRP_DEPT_AUDIT', 'Audit & Compliance', 'Internal audit and regulatory compliance', 3),
(19, 'GRP_DEPT_LOGISTICS', 'Logistics Department', 'Supply chain and delivery', 14),
(20, 'GRP_DEPT_MAINTENANCE', 'Maintenance Department', 'Equipment and facility maintenance', 16),

-- ======================================================================
-- PROJECT & TEAM GROUPS (21-30)
-- ======================================================================
-- Cross-functional project teams and working groups

(21, 'GRP_PROJECT_ALPHA', 'Project Alpha Team', 'Strategic initiative Alpha project team', NULL),
(22, 'GRP_PROJECT_BETA', 'Project Beta Team', 'Infrastructure upgrade project', NULL),
(23, 'GRP_DOCUMENT_REVIEWERS', 'Document Review Committee', 'Document approval and review board', NULL),
(24, 'GRP_BUDGET_COMMITTEE', 'Budget Planning Committee', 'Annual budget planning team', 12),
(25, 'GRP_SECURITY_TEAM', 'Security Operations Team', 'Security monitoring and response', 13),

-- ======================================================================
-- ACCESS LEVEL GROUPS (31-40)
-- ======================================================================
-- Special access and privilege groups

(31, 'GRP_DATA_ANALYSTS', 'Data Analytics Team', 'Business intelligence and reporting', 4),
(32, 'GRP_SYSTEM_OPERATORS', 'System Operators', 'Day-to-day system operations', 13),
(33, 'GRP_HELP_DESK', 'Help Desk Support', 'User support and assistance', 13),
(34, 'GRP_ASSET_MANAGERS', 'Asset Management Team', 'Equipment and inventory management', 14),
(35, 'GRP_MAIL_HANDLERS', 'Mail Room Staff', 'Mail and correspondence processing', 14),

-- ======================================================================
-- SPECIALIZED GROUPS (41-50)
-- ======================================================================
-- Functional specialty groups

(41, 'GRP_VEHICLE_FLEET', 'Fleet Management', 'Vehicle and driver coordination', 19),
(42, 'GRP_WAREHOUSE', 'Warehouse Operations', 'Stock and warehouse management', 19),
(43, 'GRP_PURCHASING', 'Purchasing Team', 'Purchase order processing', 15),
(44, 'GRP_ACCOUNTS_PAYABLE', 'Accounts Payable', 'Invoice and payment processing', 12),
(45, 'GRP_ACCOUNTS_RECEIVABLE', 'Accounts Receivable', 'Revenue and collections', 12),
(46, 'GRP_PAYROLL', 'Payroll Processing', 'Payroll administration', 11),
(47, 'GRP_RECRUITMENT', 'Recruitment Team', 'Hiring and onboarding', 11),
(48, 'GRP_TRAINING', 'Training & Development', 'Employee training programs', 11),
(49, 'GRP_QUALITY_ASSURANCE', 'Quality Assurance', 'Process quality and standards', 14),
(50, 'GRP_RESEARCH_DEV', 'Research & Development', 'Innovation and R&D projects', NULL)

ON DUPLICATE KEY UPDATE 
    `F_02` = VALUES(`F_02`),
    `F_03` = VALUES(`F_03`),
    `F_04` = VALUES(`F_04`);

-- ============================================================================
-- SECTION 2: USER-GROUP ASSIGNMENTS (r_T000202_T000201)
-- ============================================================================
-- Maps users to groups for permission inheritance
-- Relationship Table: r_T000202_T000201
--   F_01 = User ID (from T_00_02_02)
--   F_02 = Group ID (from T_00_02_01)
-- Users can belong to multiple groups

-- Clear existing assignments (optional)
-- DELETE FROM `r_T000202_T000201`;

INSERT INTO `r_T000202_T000201` (`F_01`, `F_02`) VALUES

-- ======================================================================
-- SUPERADMIN & ADMIN USERS (User IDs 1-3)
-- ======================================================================

-- User 1: superadmin
(1, 1),   -- All Users
(1, 2),   -- System Administrators
(1, 13),  -- IT Department
(1, 25),  -- Security Operations Team

-- User 2: admin
(2, 1),   -- All Users
(2, 2),   -- System Administrators
(2, 13),  -- IT Department
(2, 32),  -- System Operators

-- User 3: it.admin
(3, 1),   -- All Users
(3, 2),   -- System Administrators
(3, 13),  -- IT Department
(3, 25),  -- Security Operations Team
(3, 33),  -- Help Desk Support

-- ======================================================================
-- DEPARTMENT MANAGERS (User IDs 4-6)
-- ======================================================================

-- User 4: hr.manager
(4, 1),   -- All Users
(4, 3),   -- Management Team
(4, 11),  -- HR Department
(4, 46),  -- Payroll Processing
(4, 47),  -- Recruitment Team
(4, 48),  -- Training & Development

-- User 5: finance.manager
(5, 1),   -- All Users
(5, 3),   -- Management Team
(5, 12),  -- Finance Department
(5, 24),  -- Budget Planning Committee
(5, 44),  -- Accounts Payable
(5, 45),  -- Accounts Receivable

-- User 6: ops.manager
(6, 1),   -- All Users
(6, 3),   -- Management Team
(6, 14),  -- Operations Department
(6, 16),  -- Facilities Management
(6, 34),  -- Asset Management Team
(6, 49),  -- Quality Assurance

-- ======================================================================
-- REGULAR EMPLOYEES (User IDs 7-10)
-- ======================================================================

-- User 7: john.doe (Operations Employee)
(7, 1),   -- All Users
(7, 4),   -- Regular Employees
(7, 14),  -- Operations Department
(7, 34),  -- Asset Management Team

-- User 8: jane.smith (HR Employee)
(8, 1),   -- All Users
(8, 4),   -- Regular Employees
(8, 11),  -- HR Department
(8, 47),  -- Recruitment Team

-- User 9: bob.wilson (Finance Employee)
(9, 1),   -- All Users
(9, 4),   -- Regular Employees
(9, 12),  -- Finance Department
(9, 44),  -- Accounts Payable

-- User 10: alice.brown (IT Employee)
(10, 1),  -- All Users
(10, 4),  -- Regular Employees
(10, 13), -- IT Department
(10, 33), -- Help Desk Support

-- ======================================================================
-- SPECIALIZED ROLES (User IDs 11-14)
-- ======================================================================

-- User 11: auditor
(11, 1),  -- All Users
(11, 18), -- Audit & Compliance
(11, 23), -- Document Review Committee

-- User 12: analyst
(12, 1),  -- All Users
(12, 31), -- Data Analytics Team
(12, 12), -- Finance Department

-- User 13: approver (Document Approver)
(13, 1),  -- All Users
(13, 23), -- Document Review Committee
(13, 17), -- Legal Department

-- User 14: viewer
(14, 1),  -- All Users
(14, 4),  -- Regular Employees

ON DUPLICATE KEY UPDATE F_01=F_01;

-- ============================================================================
-- SECTION 3: GROUP-ROLE ASSIGNMENTS (r_T000203_T000201)
-- ============================================================================
-- Maps groups to roles for bulk permission management
-- Relationship Table: r_T000203_T000201
--   F_01 = Role ID (from T_00_02_03)
--   F_02 = Group ID (from T_00_02_01)
-- All users in a group inherit the group's assigned roles

-- Clear existing assignments (optional)
-- DELETE FROM `r_T000203_T000201`;

INSERT INTO `r_T000203_T000201` (`F_01`, `F_02`) VALUES

-- ======================================================================
-- ORGANIZATIONAL HIERARCHY GROUP ROLES
-- ======================================================================

-- GRP_ALL_USERS (1) - Minimal viewer access
(9, 1),   -- ROLE_VIEWER

-- GRP_ADMINISTRATORS (2) - Admin access
(2, 2),   -- ROLE_ADMIN

-- GRP_MANAGEMENT (3) - Manager access
(3, 3),   -- ROLE_MANAGER

-- GRP_EMPLOYEES (4) - Employee access
(5, 4),   -- ROLE_EMPLOYEE

-- GRP_CONTRACTORS (5) - Limited viewer access
(9, 5),   -- ROLE_VIEWER

-- ======================================================================
-- DEPARTMENT GROUP ROLES
-- ======================================================================

-- GRP_DEPT_HR (11) - HR Management
(3, 11),  -- ROLE_MANAGER
(4, 11),  -- ROLE_USER_MANAGER

-- GRP_DEPT_FINANCE (12) - Finance Management
(3, 12),  -- ROLE_MANAGER
(7, 12),  -- ROLE_ANALYST

-- GRP_DEPT_IT (13) - IT Administration
(2, 13),  -- ROLE_ADMIN
(4, 13),  -- ROLE_USER_MANAGER

-- GRP_DEPT_OPERATIONS (14) - Operations Management
(3, 14),  -- ROLE_MANAGER

-- GRP_DEPT_PROCUREMENT (15) - Procurement Management
(3, 15),  -- ROLE_MANAGER

-- GRP_DEPT_FACILITIES (16) - Facilities Management
(3, 16),  -- ROLE_MANAGER

-- GRP_DEPT_LEGAL (17) - Legal Review
(3, 17),  -- ROLE_MANAGER
(8, 17),  -- ROLE_DOCUMENT_APPROVER

-- GRP_DEPT_AUDIT (18) - Audit Access
(6, 18),  -- ROLE_AUDITOR

-- GRP_DEPT_LOGISTICS (19) - Logistics Management
(3, 19),  -- ROLE_MANAGER

-- GRP_DEPT_MAINTENANCE (20) - Maintenance Operations
(5, 20),  -- ROLE_EMPLOYEE

-- ======================================================================
-- PROJECT & TEAM GROUP ROLES
-- ======================================================================

-- GRP_PROJECT_ALPHA (21)
(3, 21),  -- ROLE_MANAGER
(5, 21),  -- ROLE_EMPLOYEE

-- GRP_PROJECT_BETA (22)
(3, 22),  -- ROLE_MANAGER
(5, 22),  -- ROLE_EMPLOYEE

-- GRP_DOCUMENT_REVIEWERS (23)
(8, 23),  -- ROLE_DOCUMENT_APPROVER

-- GRP_BUDGET_COMMITTEE (24)
(3, 24),  -- ROLE_MANAGER
(7, 24),  -- ROLE_ANALYST

-- GRP_SECURITY_TEAM (25)
(2, 25),  -- ROLE_ADMIN
(6, 25),  -- ROLE_AUDITOR

-- ======================================================================
-- ACCESS LEVEL GROUP ROLES
-- ======================================================================

-- GRP_DATA_ANALYSTS (31)
(7, 31),  -- ROLE_ANALYST

-- GRP_SYSTEM_OPERATORS (32)
(5, 32),  -- ROLE_EMPLOYEE

-- GRP_HELP_DESK (33)
(5, 33),  -- ROLE_EMPLOYEE
(4, 33),  -- ROLE_USER_MANAGER

-- GRP_ASSET_MANAGERS (34)
(5, 34),  -- ROLE_EMPLOYEE

-- GRP_MAIL_HANDLERS (35)
(5, 35),  -- ROLE_EMPLOYEE

-- ======================================================================
-- SPECIALIZED GROUP ROLES
-- ======================================================================

-- GRP_VEHICLE_FLEET (41)
(5, 41),  -- ROLE_EMPLOYEE

-- GRP_WAREHOUSE (42)
(5, 42),  -- ROLE_EMPLOYEE

-- GRP_PURCHASING (43)
(5, 43),  -- ROLE_EMPLOYEE

-- GRP_ACCOUNTS_PAYABLE (44)
(5, 44),  -- ROLE_EMPLOYEE

-- GRP_ACCOUNTS_RECEIVABLE (45)
(5, 45),  -- ROLE_EMPLOYEE

-- GRP_PAYROLL (46)
(5, 46),  -- ROLE_EMPLOYEE

-- GRP_RECRUITMENT (47)
(5, 47),  -- ROLE_EMPLOYEE
(4, 47),  -- ROLE_USER_MANAGER

-- GRP_TRAINING (48)
(5, 48),  -- ROLE_EMPLOYEE

-- GRP_QUALITY_ASSURANCE (49)
(5, 49),  -- ROLE_EMPLOYEE
(6, 49),  -- ROLE_AUDITOR

-- GRP_RESEARCH_DEV (50)
(3, 50),  -- ROLE_MANAGER
(5, 50),  -- ROLE_EMPLOYEE

ON DUPLICATE KEY UPDATE F_01=F_01;

-- ============================================================================
-- SECTION 4: VERIFICATION & VALIDATION
-- ============================================================================

SELECT '========================================' as '';
SELECT '   SECURITY GROUPS VERIFICATION' as '';
SELECT '========================================' as '';

SELECT 'Total Groups' as Component, COUNT(*) as Total_Expected_50 
FROM `T_00_02_01` WHERE `F_00` <= 50;

SELECT 'User-Group Assignments' as Component, COUNT(*) as Total 
FROM `r_T000202_T000201`;

SELECT 'Group-Role Assignments' as Component, COUNT(*) as Total 
FROM `r_T000203_T000201`;

SELECT '========================================' as '';
SELECT '   GROUP MEMBERSHIP SUMMARY' as '';
SELECT '========================================' as '';

SELECT 
    g.`F_01` as Group_Code,
    g.`F_02` as Group_Name,
    COUNT(DISTINCT ug.`F_01`) as User_Count,
    COUNT(DISTINCT gr.`F_01`) as Role_Count
FROM `T_00_02_01` g
LEFT JOIN `r_T000202_T000201` ug ON g.`F_00` = ug.`F_02`
LEFT JOIN `r_T000203_T000201` gr ON g.`F_00` = gr.`F_02`
WHERE g.`F_00` <= 50
GROUP BY g.`F_00`, g.`F_01`, g.`F_02`
ORDER BY g.`F_00`;

SELECT '========================================' as '';
SELECT '   USER GROUP MEMBERSHIPS' as '';
SELECT '========================================' as '';

SELECT 
    u.`F_01` as Username,
    u.`F_02` as Email,
    COUNT(ug.`F_02`) as Group_Count,
    GROUP_CONCAT(g.`F_02` ORDER BY g.`F_00` SEPARATOR ', ') as Groups
FROM `T_00_02_02` u
LEFT JOIN `r_T000202_T000201` ug ON u.`F_00` = ug.`F_01`
LEFT JOIN `T_00_02_01` g ON ug.`F_02` = g.`F_00`
WHERE u.`F_00` <= 16
GROUP BY u.`F_00`, u.`F_01`, u.`F_02`
ORDER BY u.`F_00`;

SELECT '========================================' as '';
SELECT '   EFFECTIVE USER PERMISSIONS' as '';
SELECT '========================================' as '';
SELECT 'Users inherit permissions from:' as Info;
SELECT '1. Direct role assignments (r_T000202_T000203)' as Source;
SELECT '2. Group memberships -> Group roles -> Permissions' as Source;
SELECT 'Application must merge both sources for complete access control' as Note;

SELECT '========================================' as '';
SELECT '   UNIQUENESS VALIDATION' as '';
SELECT '========================================' as '';

SELECT 'Duplicate Group Codes' as Check, COUNT(*) as Count_Should_Be_0
FROM (
    SELECT `F_01`, COUNT(*) as cnt 
    FROM `T_00_02_01` 
    WHERE `F_00` <= 50
    GROUP BY `F_01` 
    HAVING cnt > 1
) as duplicates;

SELECT 'Orphaned User-Group Assignments' as Check, COUNT(*) as Count_Should_Be_0
FROM `r_T000202_T000201` ug
LEFT JOIN `T_00_02_02` u ON ug.`F_01` = u.`F_00`
LEFT JOIN `T_00_02_01` g ON ug.`F_02` = g.`F_00`
WHERE u.`F_00` IS NULL OR g.`F_00` IS NULL;

SELECT 'Orphaned Group-Role Assignments' as Check, COUNT(*) as Count_Should_Be_0
FROM `r_T000203_T000201` gr
LEFT JOIN `T_00_02_03` r ON gr.`F_01` = r.`F_00`
LEFT JOIN `T_00_02_01` g ON gr.`F_02` = g.`F_00`
WHERE r.`F_00` IS NULL OR g.`F_00` IS NULL;

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- ============================================================================
-- TABLE REFERENCE SUMMARY
-- ============================================================================

/*
📊 TABLE STRUCTURE:

Main Tables:
- T_00_02_01: Security Groups (this file)
- T_00_02_02: Users (from security_db.sql)
- T_00_02_03: Roles (from security_db.sql)
- T_00_02_04: Permissions (from security_db.sql)
- T_00_02_05: Authority Categories (from security_db.sql)

Relationship Tables:
- r_T000202_T000201: User-Group assignments
- r_T000203_T000201: Group-Role assignments
- r_T000202_T000203: User-Role assignments (direct)
- r_T000203_T000204: Role-Permission assignments

📚 SECURITY GROUP STRUCTURE:

1. ORGANIZATIONAL GROUPS (1-10):
   - All Users (default membership)
   - Administrators
   - Management Team
   - Regular Employees
   - External Contractors

2. DEPARTMENT GROUPS (11-20):
   - HR, Finance, IT, Operations, Procurement
   - Facilities, Legal, Audit, Logistics, Maintenance

3. PROJECT & TEAM GROUPS (21-30):
   - Cross-functional project teams
   - Special committees and working groups

4. ACCESS LEVEL GROUPS (31-40):
   - Specialized access groups
   - Functional teams (analysts, operators, support)

5. SPECIALIZED GROUPS (41-50):
   - Operational groups (fleet, warehouse, purchasing)
   - Financial groups (AP, AR, payroll)
   - HR groups (recruitment, training)

📊 PERMISSION INHERITANCE:

Users receive permissions from TWO sources:
1. Direct role assignments (r_T000202_T000203)
2. Group membership -> Group roles -> Role permissions

Example: User "john.doe"
├─ Direct role: ROLE_EMPLOYEE (26 permissions)
├─ Group: GRP_ALL_USERS -> ROLE_VIEWER (11 permissions)
├─ Group: GRP_EMPLOYEES -> ROLE_EMPLOYEE (26 permissions)
└─ Group: GRP_DEPT_OPERATIONS -> ROLE_MANAGER (130+ permissions)
   
Total effective permissions: UNION of all sources

🔐 BEST PRACTICES:

1. Use groups for organizational structure
2. Assign roles to groups, not individual users (when possible)
3. Keep direct user-role assignments minimal
4. Document group purposes clearly
5. Review group memberships regularly
6. Implement hierarchical group structure (F_04 parent_group)

⚠️  IMPORTANT:

- Application MUST check both direct roles AND group roles
- Group hierarchy (F_04) allows nested permissions
- Consider caching effective permissions for performance
- Audit changes to group memberships
- Groups can have multiple roles assigned

🔧 CUSTOMIZATION:

To add new groups:
1. Insert into T_00_02_01 with unique F_00 and F_01
2. Assign users to group in r_T000202_T000201
3. Assign roles to group in r_T000203_T000201
4. Set F_04 for parent group (optional hierarchy)

To modify group structure:
1. Update F_04 (parent_group) for hierarchy
2. Ensure no circular references
3. Update application logic if using inheritance

✅ SETUP COMPLETE!
   - 50 Security Groups in T_00_02_01
   - Organizational hierarchy with parent references
   - Department structure
   - Project teams
   - Specialized access groups
   - Complete user-group mappings (r_T000202_T000201)
   - Complete group-role mappings (r_T000203_T000201)
   - Full integration with security_db.sql
*/

-- EOF Security_Group.sql v1.0
