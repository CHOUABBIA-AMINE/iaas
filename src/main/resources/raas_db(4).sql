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
-- Database: `raas_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `r_t000201_t000203`
--

CREATE TABLE `r_t000201_t000203` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `r_t000202_t000201`
--

CREATE TABLE `r_t000202_t000201` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `r_t000202_t000203`
--

CREATE TABLE `r_t000202_t000203` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `r_t000203_t000204`
--

CREATE TABLE `r_t000203_t000204` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  `F_02` bigint(20) DEFAULT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_04` varchar(20) DEFAULT NULL,
  `F_03` varchar(250) NOT NULL
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

-- --------------------------------------------------------

--
-- Table structure for table `t_00_02_02`
--

CREATE TABLE `t_00_02_02` (
  `F_00` bigint(20) NOT NULL,
  `accountNonExpired` bit(1) NOT NULL,
  `accountNonLocked` bit(1) NOT NULL,
  `credentialsNonExpired` bit(1) NOT NULL,
  `F_02` varchar(100) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `F_03` varchar(120) NOT NULL,
  `F_01` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_00_02_02`
--

INSERT INTO `t_00_02_02` (`F_00`, `accountNonExpired`, `accountNonLocked`, `credentialsNonExpired`, `F_02`, `enabled`, `F_03`, `F_01`) VALUES
(1, b'1', b'1', b'1', 'admin@raas.dz', b'1', '$2a$12$z5IM5yIBQzbeQBcS2U6VXeQxCFFL0Q/4UAP4pWJWQ9BlaH73YolR.', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `t_00_02_03`
--

CREATE TABLE `t_00_02_03` (
  `F_00` bigint(20) NOT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_01` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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

-- --------------------------------------------------------

--
-- Table structure for table `t_00_02_05`
--

CREATE TABLE `t_00_02_05` (
  `F_00` bigint(20) NOT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_01` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  `T_00_04_01_FK_01` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  `F_02` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_01_01_05`
--

INSERT INTO `t_01_01_05` (`F_00`, `F_02`, `F_01`) VALUES
(1, 2, 'SDR-01'),
(2, 2, 'SDR-02'),
(3, 2, 'SDR-03'),
(4, 3, 'SDR-04'),
(5, 3, 'SDR-05'),
(6, 4, 'SDR-06'),
(7, 4, 'SDR-07'),
(8, 7, 'SDR-08'),
(9, 7, 'SDR-09'),
(10, 7, 'SDR-10'),
(11, 8, 'SDR-11'),
(12, 8, 'SDR-12'),
(13, 8, 'SDR-13'),
(14, 8, 'SDR-14'),
(15, 8, 'SDR-15'),
(16, 8, 'SDR-16'),
(17, 8, 'SDR-17'),
(18, 8, 'SDR-18'),
(19, 8, 'SDR-19'),
(20, 8, 'SDR-20');

-- --------------------------------------------------------

--
-- Table structure for table `t_01_01_06`
--

CREATE TABLE `t_01_01_06` (
  `F_00` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL,
  `F_03` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_01_01_07`
--

CREATE TABLE `t_01_01_07` (
  `F_00` bigint(20) NOT NULL,
  `F_05` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) DEFAULT NULL,
  `F_04` varchar(200) NOT NULL
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
  `F_06` bigint(20) NOT NULL,
  `F_07` bigint(20) NOT NULL,
  `F_08` bigint(20) NOT NULL,
  `F_09` bigint(20) NOT NULL,
  `F_01` varchar(50) DEFAULT NULL,
  `F_02` varchar(50) DEFAULT NULL,
  `F_03` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_01_03_01`
--

CREATE TABLE `t_01_03_01` (
  `F_04` int(11) DEFAULT NULL,
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_01_03_01`
--

INSERT INTO `t_01_03_01` (`F_04`, `F_00`, `F_01`, `F_02`, `F_03`) VALUES
(100, 1, 'بطاقة اقتراح', 'Proposal Form', 'Fiche de Proposition'),
(100, 2, 'بطاقة تقنية وصفية', 'Descriptive Technical Sheet', 'Fiche Technique Descriptive'),
(100, 3, 'بطاقة تقنية', 'Technical Sheet', 'Fiche Technique'),
(111, 4, 'تقرير تقديم', 'Presentation Report', 'Rapport de Présentation'),
(111, 5, 'تقرير تقديم تكميلي', 'Supplementary Presentation Report', 'Rapport de Présentation Complémentaire'),
(11, 6, 'بطاقة تحليلية', 'Analytical Sheet', 'Fiche Analytique'),
(111, 7, 'بطاقة الإقتطاع الميزانياتي', 'Budget Allocation Sheet', 'Fiche d\'Imputation Budgétaire'),
(111, 8, 'تأشيرة اللجنة القطاعية للصفقات', 'CSM Visa', 'Visa CSM'),
(100, 9, 'لوحة إعلانية', 'Advertising Board', 'Placard publicitaire'),
(100, 10, 'دفتر الشروط', 'Terms of Reference', 'Cahier des Charges'),
(10, 11, 'عقد / صفقة', 'Contract', 'Contrat / Marché'),
(1, 12, 'ملحق عقد', 'Amendment', 'Avenant'),
(10, 13, 'أمر شراء', 'Purchase Order', 'Bon de Commande'),
(100, 14, 'بطاقة تمديد الآجال', 'Deadline Extension Form', 'Fiche de Prorogation des Délais'),
(100, 15, 'لوحة إعلانية (تمديد الآجال)', 'Advertising Board (Deadline Extension)', 'Placard publicitaire (Prorogation Délai)'),
(100, 16, 'محضر فتح الأظرف الإدارية', 'Minutes of Administrative Bid Opening', 'PV Ouverture Administratif des plis'),
(100, 17, 'محضر فتح الأظرف التقنية', 'Minutes of Technical Bid Opening', 'PV Ouverture Technique'),
(100, 18, 'محضر التقييم التقني', 'Technical Evaluation Minutes', 'PV Evaluation Technique'),
(100, 19, 'محضر فتح الأظرف المالية', 'Minutes of Financial Bid Opening', 'PV Ouverture Financière'),
(100, 20, 'محضر التقييم المالي', 'Financial Evaluation Minutes', 'PV Evaluation Financière'),
(100, 21, 'محضر الإغلاق', 'Closing Minutes', 'PV de Cloture'),
(100, 22, 'مداولة الاختيار المؤقت', 'Provisional Selection Deliberation', 'Délibération du choix Provisoire'),
(11, 23, 'محضر لجنة فتح الأظرفة وتقييم العروض', 'COPEO Minutes', 'PV COPEO'),
(111, 24, 'محضر لجنة فتح الأظرفة وتقييم العروض التكميلي', 'Supplementary COPEO Minutes', 'PV COPEO Complémentaire'),
(11, 25, 'أمر خدمة', 'Service Order', 'Ordre de Service'),
(100, 26, 'كفالة التعهد', 'Bid Bond', 'Caution de Soumission'),
(100, 27, 'كفالة حسن التنفيذ', 'Performance Bond', 'Caution de Bonne Exécution'),
(100, 28, 'كفالة الضمان', 'Guarantee Bond', 'Caution de Garantie'),
(100, 29, 'الملف الإداري', 'Administrative File', 'Dossier Administratif'),
(100, 30, 'العرض المالي', 'Financial Offer', 'Offre Financière'),
(100, 31, 'العرض الفني', 'Technical Offer', 'Offre Technique');

-- --------------------------------------------------------

--
-- Table structure for table `t_01_03_02`
--

CREATE TABLE `t_01_03_02` (
  `F_00` bigint(20) NOT NULL,
  `F_02` datetime(6) DEFAULT NULL,
  `F_03` bigint(20) NOT NULL,
  `F_04` bigint(20) DEFAULT NULL,
  `F_01` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_01_04_01`
--

CREATE TABLE `t_01_04_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(3) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) DEFAULT NULL
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
  `F_01` int(3) NOT NULL,
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
  `F_04` bigint(20) NOT NULL,
  `F_01` int(5) NOT NULL,
  `F_02` varchar(100) NOT NULL,
  `F_03` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_01_04_03`
--

INSERT INTO `t_01_04_03` (`F_00`, `F_04`, `F_01`, `F_02`, `F_03`) VALUES
(1542, 1, 1, 'أدرار', 'Adrar'),
(1543, 1, 2, 'اقبلي', 'Akabli'),
(1544, 49, 3, 'أوقروت', 'Aougrout'),
(1545, 1, 4, 'أولف', 'Aoulef'),
(1546, 50, 5, 'برج باجي مختار', 'Bordj Badji Mokhtar'),
(1547, 1, 6, 'بودة', 'Bouda'),
(1548, 49, 7, 'شروين', 'Charouine'),
(1549, 49, 8, 'دلدول', 'Deldoul'),
(1550, 1, 9, 'فنوغيل', 'Fenoughil'),
(1551, 1, 10, 'إن زغمير', 'In Zghmir'),
(1552, 49, 11, 'قصر قدور', 'Ksar Kaddour'),
(1553, 49, 12, 'المطارفة', 'Metarfa'),
(1554, 1, 13, 'أولاد أحمد تيمي', 'Ouled Ahmed Timmi'),
(1555, 49, 14, 'أولاد عيسى', 'Ouled Aissa'),
(1556, 49, 15, 'أولاد السعيد', 'Ouled Said'),
(1557, 1, 16, 'رقان', 'Reggane'),
(1558, 1, 17, 'سالي', 'Sali'),
(1559, 1, 18, 'السبع', 'Sebaa'),
(1560, 49, 19, 'طالمين', 'Talmine'),
(1561, 1, 20, 'تامنطيط', 'Tamantit'),
(1562, 1, 21, 'تامست', 'Tamest'),
(1563, 1, 22, 'تيمقتن', 'Timekten'),
(1564, 50, 23, 'تيمياوين', 'Timiaouine'),
(1565, 49, 24, 'تيميمون', 'Timimoun'),
(1566, 49, 25, 'تنركوك', 'Tinerkouk'),
(1567, 1, 26, 'تيت', 'Tit'),
(1568, 1, 27, 'تسابيت', 'Tsabit'),
(1569, 1, 28, 'زاوية كنتة', 'Zaouiet Kounta'),
(1570, 2, 29, 'أبو الحسن', 'Abou El Hassane'),
(1571, 2, 30, 'عين مران', 'Ain Merane'),
(1572, 2, 31, 'بنايرية', 'Benairia'),
(1573, 2, 32, 'بني بوعتاب', 'Beni  Bouattab'),
(1574, 2, 33, 'بني حواء', 'Beni Haoua'),
(1575, 2, 34, 'بني راشد', 'Beni Rached'),
(1576, 2, 35, 'بوقادير', 'Boukadir'),
(1577, 2, 36, 'بوزغاية', 'Bouzeghaia'),
(1578, 2, 37, 'بريرة', 'Breira'),
(1579, 2, 38, 'الشطية', 'Chettia'),
(1580, 2, 39, 'الشلف', 'Chlef'),
(1581, 2, 40, 'الظهرة', 'Dahra'),
(1582, 2, 41, 'الحجاج', 'El Hadjadj'),
(1583, 2, 42, 'الكريمية', 'El Karimia'),
(1584, 2, 43, 'المرسى', 'El Marsa'),
(1585, 2, 44, 'حرشون', 'Harchoun'),
(1586, 2, 45, 'الهرانفة', 'Herenfa'),
(1587, 2, 46, 'الأبيض مجاجة', 'Labiod Medjadja'),
(1588, 2, 47, 'مصدق', 'Moussadek'),
(1589, 2, 48, 'وادي الفضة', 'Oued Fodda'),
(1590, 2, 49, 'وادي قوسين', 'Oued Goussine'),
(1591, 2, 50, 'وادي سلي', 'Oued Sly'),
(1592, 2, 51, 'أولاد عباس', 'Ouled Abbes'),
(1593, 2, 52, 'أولاد بن عبد القادر', 'Ouled Ben Abdelkader'),
(1594, 2, 53, 'أولاد فارس', 'Ouled Fares'),
(1595, 2, 54, 'أم الدروع', 'Oum Drou'),
(1596, 2, 55, 'سنجاس', 'Sendjas'),
(1597, 2, 56, 'سيدي عبد الرحمن', 'Sidi Abderrahmane'),
(1598, 2, 57, 'سيدي عكاشة', 'Sidi Akkacha'),
(1599, 2, 58, 'الصبحة', 'Sobha'),
(1600, 2, 59, 'تاجنة', 'Tadjena'),
(1601, 2, 60, 'تلعصة', 'Talassa'),
(1602, 2, 61, 'تاوقريت', 'Taougrit'),
(1603, 2, 62, 'تنس', 'Tenes'),
(1604, 2, 63, 'الزبوجة', 'Zeboudja'),
(1605, 3, 64, 'أفلو', 'Aflou'),
(1606, 3, 65, 'عين ماضي', 'Ain Madhi'),
(1607, 3, 66, 'عين سيدي علي', 'Ain Sidi Ali'),
(1608, 3, 67, 'البيضاء', 'El Beidha'),
(1609, 3, 68, 'بن ناصر بن شهرة', 'Benacer Benchohra'),
(1610, 3, 69, 'بريدة', 'Brida'),
(1611, 3, 70, 'العسافية', 'El Assafia'),
(1612, 3, 71, 'الغيشة', 'El Ghicha'),
(1613, 3, 72, 'الحويطة', 'El Haouaita'),
(1614, 3, 73, 'قلتة سيدي سعد', 'Gueltat Sidi Saad'),
(1615, 3, 74, 'الحاج مشري', 'Hadj Mechri'),
(1616, 3, 75, 'حاسي الدلاعة', 'Hassi Delaa'),
(1617, 3, 76, 'حاسي الرمل', 'Hassi R\'mel'),
(1618, 3, 77, 'الخنق', 'Kheneg'),
(1619, 3, 78, 'قصر الحيران', 'Ksar El Hirane'),
(1620, 3, 79, 'الأغواط', 'Laghouat'),
(1621, 3, 80, 'وادي مرة', 'Oued Morra'),
(1622, 3, 81, 'وادي مزي', 'Oued M\'zi'),
(1623, 3, 82, 'سبقاق', 'Sebgag'),
(1624, 3, 83, 'سيدي بوزيد', 'Sidi Bouzid'),
(1625, 3, 84, 'سيدي مخلوف', 'Sidi Makhlouf'),
(1626, 3, 85, 'تاجموت', 'Tadjemout'),
(1627, 3, 86, 'تاجرونة', 'Tadjrouna'),
(1628, 3, 87, 'تاويالة', 'Taouiala'),
(1629, 4, 88, 'عين ببوش', 'Ain Babouche'),
(1630, 4, 89, 'عين البيضاء', 'Ain Beida'),
(1631, 4, 90, 'عين الديس', 'Ain Diss'),
(1632, 4, 91, 'عين فكرون', 'Ain Fekroun'),
(1633, 4, 92, 'عين كرشة', 'Ain Kercha'),
(1634, 4, 93, 'عين مليلة', 'Ain M\'lila'),
(1635, 4, 94, 'عين الزيتون', 'Ain Zitoun'),
(1636, 4, 95, 'بحير الشرقي', 'Behir Chergui'),
(1637, 4, 96, 'بريش', 'Berriche'),
(1638, 4, 97, 'بئر الشهداء', 'Bir Chouhada'),
(1639, 4, 98, 'الضلعة', 'Dhalaa'),
(1640, 4, 99, 'العامرية', 'El Amiria'),
(1641, 4, 100, 'البلالة', 'El Belala'),
(1642, 4, 101, 'الجازية', 'El Djazia'),
(1643, 4, 102, 'الفجوج بوغرارة سعودي', 'El Fedjoudj Boughrara Sa'),
(1644, 4, 103, 'الحرملية', 'El Harmilia'),
(1645, 4, 104, 'فكيرينة', 'Fkirina'),
(1646, 4, 105, 'هنشير تومغني', 'Hanchir Toumghani'),
(1647, 4, 106, 'قصر الصباحي', 'Ksar Sbahi'),
(1648, 4, 107, 'مسكيانة', 'Meskiana'),
(1649, 4, 108, 'وادي نيني', 'Oued Nini'),
(1650, 4, 109, 'أولاد قاسم', 'Ouled Gacem'),
(1651, 4, 110, 'أولاد حملة', 'Ouled Hamla'),
(1652, 4, 111, 'أولاد زواي', 'Ouled Zouai'),
(1653, 4, 112, 'أم البواقي', 'Oum El Bouaghi'),
(1654, 4, 113, 'الرحية', 'Rahia'),
(1655, 4, 114, 'سيقوس', 'Sigus'),
(1656, 4, 115, 'سوق نعمان', 'Souk Naamane'),
(1657, 4, 116, 'الزرق', 'Zorg'),
(1658, 5, 117, 'عين جاسر', 'Ain Djasser'),
(1659, 5, 118, 'عين التوتة', 'Ain Touta'),
(1660, 5, 119, 'عين ياقوت', 'Ain Yagout'),
(1661, 5, 120, 'أريس', 'Arris'),
(1662, 5, 121, 'عزيل عبد القادر', 'Azil Abedelkader'),
(1663, 5, 122, 'بريكة', 'Barika'),
(1664, 5, 123, 'باتنة', 'Batna'),
(1665, 5, 124, 'بني فضالة الحقانية', 'Beni Foudhala El Hakania'),
(1666, 5, 125, 'بيطام', 'Bitam'),
(1667, 5, 126, 'بولهيلات', 'Boulhilat'),
(1668, 5, 127, 'بومقر', 'Boumagueur'),
(1669, 5, 128, 'بومية', 'Boumia'),
(1670, 5, 129, 'بوزينة', 'Bouzina'),
(1671, 5, 130, 'الشمرة', 'Chemora'),
(1672, 5, 131, 'شير', 'Chir'),
(1673, 5, 132, 'جرمة', 'Djerma'),
(1674, 5, 133, 'الجزار', 'Djezzar'),
(1675, 5, 134, 'الحاسي', 'El Hassi'),
(1676, 5, 135, 'المعذر', 'El Madher'),
(1677, 5, 136, 'فسديس', 'Fesdis'),
(1678, 5, 137, 'فم الطوب', 'Foum Toub'),
(1679, 5, 138, 'غسيرة', 'Ghassira'),
(1680, 5, 139, 'القصبات', 'Gosbat'),
(1681, 5, 140, 'القيقبة', 'Guigba'),
(1682, 5, 141, 'حيدوسة', 'Hidoussa'),
(1683, 5, 142, 'إشمول', 'Ichemoul'),
(1684, 5, 143, 'إينوغيسن', 'Inoughissen'),
(1685, 5, 144, 'كيمل', 'Kimmel'),
(1686, 5, 145, 'قصر بلزمة', 'Ksar Bellezma'),
(1687, 5, 146, 'لارباع', 'Larbaa'),
(1688, 5, 147, 'لازرو', 'Lazrou'),
(1689, 5, 148, 'لمسان', 'Lemcene'),
(1690, 5, 149, 'إمدوكل', 'M Doukal'),
(1691, 5, 150, 'معافة', 'Maafa'),
(1692, 5, 151, 'منعة', 'Menaa'),
(1693, 5, 152, 'مروانة', 'Merouana'),
(1694, 5, 153, 'نقاوس', 'N Gaous'),
(1695, 5, 154, 'وادي الشعبة', 'Oued Chaaba'),
(1696, 5, 155, 'وادي الماء', 'Oued El Ma'),
(1697, 5, 156, 'وادي الطاقة', 'Oued Taga'),
(1698, 5, 157, 'أولاد عمار', 'Ouled Ammar'),
(1699, 5, 158, 'أولاد عوف', 'Ouled Aouf'),
(1700, 5, 159, 'أولاد فاضل', 'Ouled Fadel'),
(1701, 5, 160, 'أولاد سلام', 'Ouled Sellem'),
(1702, 5, 161, 'أولاد سي سليمان', 'Ouled Si Slimane'),
(1703, 5, 162, 'عيون العصافير', 'Ouyoun El Assafir'),
(1704, 5, 163, 'الرحبات', 'Rahbat'),
(1705, 5, 164, 'رأس العيون', 'Ras El Aioun'),
(1706, 5, 165, 'سفيان', 'Sefiane'),
(1707, 5, 166, 'سقانة', 'Seggana'),
(1708, 5, 167, 'سريانة', 'Seriana'),
(1709, 5, 168, 'تكوت', 'T Kout'),
(1710, 5, 169, 'تالخمت', 'Talkhamt'),
(1711, 5, 170, 'تاكسلانت', 'Taxlent'),
(1712, 5, 171, 'تازولت', 'Tazoult'),
(1713, 5, 172, 'ثنية العابد', 'Teniet El Abed'),
(1714, 5, 173, 'تيغانمين', 'Tighanimine'),
(1715, 5, 174, 'تغرغار', 'Tigharghar'),
(1716, 5, 175, 'تيلاطو', 'Tilatou'),
(1717, 5, 176, 'تيمقاد', 'Timgad'),
(1718, 5, 177, 'زانة البيضاء', 'Zanet El Beida'),
(1719, 6, 178, 'أدكار', 'Adekar'),
(1720, 6, 179, 'أيت رزين', 'Ait R\'zine'),
(1721, 6, 180, 'أيت إسماعيل', 'Ait-Smail'),
(1722, 6, 181, 'أقبو', 'Akbou'),
(1723, 6, 182, 'أكفادو', 'Akfadou'),
(1724, 6, 183, 'أمالو', 'Amalou'),
(1725, 6, 184, 'أميزور', 'Amizour'),
(1726, 6, 185, 'أوقاس', 'Aokas'),
(1727, 6, 186, 'برباشة', 'Barbacha'),
(1728, 6, 187, 'بجاية', 'Bejaia'),
(1729, 6, 188, 'بني جليل', 'Beni Djellil'),
(1730, 6, 189, 'بني كسيلة', 'Beni K\'sila'),
(1731, 6, 190, 'بني مليكش', 'Beni-Mallikeche'),
(1732, 6, 191, 'بني معوش', 'Benimaouche'),
(1733, 6, 192, 'بو جليل', 'Boudjellil'),
(1734, 6, 193, 'بوحمزة', 'Bouhamza'),
(1735, 6, 194, 'بوخليفة', 'Boukhelifa'),
(1736, 6, 195, 'شلاطة', 'Chellata'),
(1737, 6, 196, 'شميني', 'Chemini'),
(1738, 6, 197, 'درقينة', 'Darguina'),
(1739, 6, 198, 'ذراع القايد', 'Dra El Caid'),
(1740, 6, 199, 'الفلاي', 'Leflaye'),
(1741, 6, 200, 'القصر', 'El Kseur'),
(1742, 6, 201, 'فناية الماثن', 'Fenaia Il Maten'),
(1743, 6, 202, 'فرعون', 'Feraoun'),
(1744, 6, 203, 'أوزلاقن', 'Ouzellaguen'),
(1745, 6, 204, 'إغيل علي', 'Ighil-Ali'),
(1746, 6, 205, 'اغرم', 'Ighram'),
(1747, 6, 206, 'كنديرة', 'Kendira'),
(1748, 6, 207, 'خراطة', 'Kherrata'),
(1749, 6, 208, 'مسيسنة', 'M\'cisna'),
(1750, 6, 209, 'مالبو', 'Melbou'),
(1751, 6, 210, 'وادي غير', 'Oued Ghir'),
(1752, 6, 211, 'صدوق', 'Seddouk'),
(1753, 6, 212, 'سيدي عياد', 'Sidi Ayad'),
(1754, 6, 213, 'سيدي عيش', 'Sidi-Aich'),
(1755, 6, 214, 'سمعون', 'Smaoun'),
(1756, 6, 215, 'سوق لإثنين', 'Souk El Tenine'),
(1757, 6, 216, 'سوق اوفلا', 'Souk Oufella'),
(1758, 6, 217, 'تالة حمزة', 'Tala Hamza'),
(1759, 6, 218, 'تامقرة', 'Tamokra'),
(1760, 6, 219, 'تامريجت', 'Tamridjet'),
(1761, 6, 220, 'تاوريرت إغيل', 'Taourit Ighil'),
(1762, 6, 221, 'تاسكريوت', 'Taskriout'),
(1763, 6, 222, 'تازمالت', 'Tazmalt'),
(1764, 6, 223, 'طيبان', 'Tibane'),
(1765, 6, 224, 'تيشي', 'Tichy'),
(1766, 6, 225, 'تيفرة', 'Tifra'),
(1767, 6, 226, 'تيمزريت', 'Timezrit'),
(1768, 6, 227, 'تينبدار', 'Tinebdar'),
(1769, 6, 228, 'تيزي نبربر', 'Tizi-N\'berber'),
(1770, 6, 229, 'توجة', 'Toudja'),
(1771, 7, 230, 'عين الناقة', 'Ain Naga'),
(1772, 7, 231, 'عين زعطوط', 'Ain Zaatout'),
(1773, 51, 232, 'بسباس', 'Besbes'),
(1774, 7, 233, 'بسكرة', 'Biskra'),
(1775, 7, 234, 'برج بن عزوز', 'Bordj Ben Azzouz'),
(1776, 7, 235, 'بوشقرون', 'Bouchakroun'),
(1777, 7, 236, 'برانيس', 'Branis'),
(1778, 51, 237, 'الشعيبة', 'Chaiba'),
(1779, 7, 238, 'شتمة', 'Chetma'),
(1780, 7, 239, 'جمورة', 'Djemorah'),
(1781, 51, 240, 'الدوسن', 'Doucen'),
(1782, 7, 241, 'الفيض', 'El Feidh'),
(1783, 7, 242, 'الغروس', 'El Ghrous'),
(1784, 7, 243, 'الحاجب', 'El Hadjab'),
(1785, 7, 244, 'الحوش', 'El Haouch'),
(1786, 7, 245, 'القنطرة', 'El Kantara'),
(1787, 7, 246, 'الوطاية', 'El Outaya'),
(1788, 7, 247, 'فوغالة', 'Foughala'),
(1789, 7, 248, 'خنقة سيدي ناجي', 'Khenguet Sidi Nadji'),
(1790, 7, 249, 'ليشانة', 'Lichana'),
(1791, 7, 250, 'ليوة', 'Lioua'),
(1792, 7, 251, 'مشونش', 'M\'chouneche'),
(1793, 7, 252, 'مخادمة', 'Mekhadma'),
(1794, 7, 253, 'المزيرعة', 'Meziraa'),
(1795, 7, 254, 'مليلي', 'M\'lili'),
(1796, 51, 255, 'أولاد جلال', 'Ouled Djellal'),
(1797, 7, 256, 'أوماش', 'Oumache'),
(1798, 7, 257, 'أورلال', 'Ourlal'),
(1799, 51, 258, 'رأس الميعاد', 'Ras El Miad'),
(1800, 51, 259, 'سيدي  خالد', 'Sidi Khaled'),
(1801, 7, 260, 'سيدي عقبة', 'Sidi Okba'),
(1802, 7, 261, 'طولقة', 'Tolga'),
(1803, 7, 262, 'زريبة الوادي', 'Zeribet El Oued'),
(1804, 8, 263, 'العبادلة', 'Abadla'),
(1805, 8, 264, 'بشار', 'Bechar'),
(1806, 52, 265, 'بني عباس', 'Beni-Abbes'),
(1807, 52, 266, 'بن يخلف', 'Beni-Ikhlef'),
(1808, 8, 267, 'بني ونيف', 'Beni-Ounif'),
(1809, 8, 268, 'بوكايس', 'Boukais'),
(1810, 52, 269, 'الواتة', 'El Ouata'),
(1811, 8, 270, 'عرق فراج', 'Erg-Ferradj'),
(1812, 52, 271, 'إقلي', 'Igli'),
(1813, 8, 272, 'القنادسة', 'Kenadsa'),
(1814, 52, 273, 'كرزاز', 'Kerzaz'),
(1815, 52, 274, 'القصابي', 'Ksabi'),
(1816, 8, 275, 'لحمر', 'Lahmar'),
(1817, 8, 276, 'مشرع هواري بومدين', 'Machraa-Houari-Boumediene'),
(1818, 8, 277, 'المريجة', 'Meridja'),
(1819, 8, 278, 'موغل', 'Mogheul'),
(1820, 52, 279, 'أولاد خضير', 'Ouled-Khodeir'),
(1821, 8, 280, 'تبلبالة', 'Tabelbala'),
(1822, 8, 281, 'تاغيت', 'Taghit'),
(1823, 52, 282, 'تامترت', 'Tamtert'),
(1824, 52, 283, 'تيمودي', 'Timoudi'),
(1825, 9, 284, 'عين الرمانة', 'Ain Romana'),
(1826, 9, 285, 'بني مراد', 'Beni Mered'),
(1827, 9, 286, 'بني تامو', 'Beni-Tamou'),
(1828, 9, 287, 'بن خليل', 'Benkhelil'),
(1829, 9, 288, 'البليدة', 'Blida'),
(1830, 9, 289, 'بوعرفة', 'Bouarfa'),
(1831, 9, 290, 'بوفاريك', 'Boufarik'),
(1832, 9, 291, 'بوقرة', 'Bougara'),
(1833, 9, 292, 'بوعينان', 'Bouinan'),
(1834, 9, 293, 'الشبلي', 'Chebli'),
(1835, 9, 294, 'الشفة', 'Chiffa'),
(1836, 9, 295, 'الشريعة', 'Chrea'),
(1837, 9, 296, 'جبابرة', 'Djebabra'),
(1838, 9, 297, 'العفرون', 'El-Affroun'),
(1839, 9, 298, 'قرواو', 'Guerrouaou'),
(1840, 9, 299, 'حمام ملوان', 'Hammam Elouane'),
(1841, 9, 300, 'الأربعاء', 'Larbaa'),
(1842, 9, 301, 'مفتاح', 'Meftah'),
(1843, 9, 302, 'موزاية', 'Mouzaia'),
(1844, 9, 303, 'وادي جر', 'Oued  Djer'),
(1845, 9, 304, 'وادي العلايق', 'Oued El Alleug'),
(1846, 9, 305, 'اولاد سلامة', 'Ouled Slama'),
(1847, 9, 306, 'أولاد يعيش', 'Ouled Yaich'),
(1848, 9, 307, 'صوحان', 'Souhane'),
(1849, 9, 308, 'الصومعة', 'Soumaa'),
(1850, 10, 309, 'أغبالو', 'Aghbalou'),
(1851, 10, 310, 'أهل القصر', 'Ahl El Ksar'),
(1852, 10, 311, 'عين الحجر', 'Ain El Hadjar'),
(1853, 10, 312, 'عين العلوي', 'Ain Laloui'),
(1854, 10, 313, 'عين الترك', 'Ain Turk'),
(1855, 10, 314, 'عين بسام', 'Ain-Bessem'),
(1856, 10, 315, 'أيت لعزيز', 'Ait Laaziz'),
(1857, 10, 316, 'أعمر', 'Aomar'),
(1858, 10, 317, 'بشلول', 'Bechloul'),
(1859, 10, 318, 'بئر غبالو', 'Bir Ghbalou'),
(1860, 10, 319, 'برج أوخريص', 'Bordj Okhriss'),
(1861, 10, 320, 'بودربالة', 'Bouderbala'),
(1862, 10, 321, 'البويرة', 'Bouira'),
(1863, 10, 322, 'بوكرم', 'Boukram'),
(1864, 10, 323, 'شرفة', 'Chorfa'),
(1865, 10, 324, 'الدشمية', 'Dechmia'),
(1866, 10, 325, 'ديرة', 'Dirah'),
(1867, 10, 326, 'جباحية', 'Djebahia'),
(1868, 10, 327, 'العجيبة', 'El Adjiba'),
(1869, 10, 328, 'الأسنام', 'El Asnam'),
(1870, 10, 329, 'الهاشمية', 'El Hachimia'),
(1871, 10, 330, 'الخبوزية', 'El Khabouzia'),
(1872, 10, 331, 'الحاكمية', 'El-Hakimia'),
(1873, 10, 332, 'المقراني', 'El-Mokrani'),
(1874, 10, 333, 'قرومة', 'Guerrouma'),
(1875, 10, 334, 'الحجرة الزرقاء', 'Hadjera Zerga'),
(1876, 10, 335, 'حيزر', 'Haizer'),
(1877, 10, 336, 'حنيف', 'Hanif'),
(1878, 10, 337, 'قادرية', 'Kadiria'),
(1879, 10, 338, 'الأخضرية', 'Lakhdaria'),
(1880, 10, 339, 'أمشدالة', 'M Chedallah'),
(1881, 10, 340, 'معلة', 'Maala'),
(1882, 10, 341, 'المعمورة', 'Maamora'),
(1883, 10, 342, 'مزدور', 'Mezdour'),
(1884, 10, 343, 'وادي البردي', 'Oued El Berdi'),
(1885, 10, 344, 'أولاد راشد', 'Ouled Rached'),
(1886, 10, 345, 'روراوة', 'Raouraoua'),
(1887, 10, 346, 'ريدان', 'Ridane'),
(1888, 10, 347, 'سحاريج', 'Saharidj'),
(1889, 10, 348, 'سوق الخميس', 'Souk El Khemis'),
(1890, 10, 349, 'سور الغزلان', 'Sour El Ghozlane'),
(1891, 10, 350, 'تاغزوت', 'Taghzout'),
(1892, 10, 351, 'تاقديت', 'Taguedite'),
(1893, 10, 352, 'آث  منصور', 'Ath Mansour'),
(1894, 10, 353, 'زبربر', 'Z\'barbar (El Isseri )'),
(1895, 11, 354, 'ابلسة', 'Abelsa'),
(1896, 11, 355, 'عين امقل', 'Ain Amguel'),
(1897, 54, 356, 'عين قزام', 'Ain Guezzam'),
(1898, 53, 357, 'عين صالح', 'Ain Salah'),
(1899, 53, 358, 'فقارة الزوى', 'Foggaret Ezzoua'),
(1900, 11, 359, 'أدلس', 'Idles'),
(1901, 53, 360, 'إينغر', 'Inghar'),
(1902, 11, 361, 'تمنراست', 'Tamanrasset'),
(1903, 11, 362, 'تاظروك', 'Tazrouk'),
(1904, 54, 363, 'تين زواتين', 'Tin Zouatine'),
(1905, 12, 364, 'عين الزرقاء', 'Ain Zerga'),
(1906, 12, 365, 'بجن', 'Bedjene'),
(1907, 12, 366, 'بكارية', 'Bekkaria'),
(1908, 12, 367, 'بئر الذهب', 'Bir Dheheb'),
(1909, 12, 368, 'بئر مقدم', 'Bir Mokkadem'),
(1910, 12, 369, 'بئر العاتر', 'Bir-El-Ater'),
(1911, 12, 370, 'بوخضرة', 'Boukhadra'),
(1912, 12, 371, 'بولحاف الدير', 'Boulhaf Dyr'),
(1913, 12, 372, 'الشريعة', 'Cheria'),
(1914, 12, 373, 'الكويف', 'El Kouif'),
(1915, 12, 374, 'الماء الابيض', 'El Malabiod'),
(1916, 12, 375, 'المريج', 'El Meridj'),
(1917, 12, 376, 'المزرعة', 'El Mezeraa'),
(1918, 12, 377, 'العقلة', 'El Ogla'),
(1919, 12, 378, 'العقلة المالحة', 'El Ogla El Malha'),
(1920, 12, 379, 'العوينات', 'El-Aouinet'),
(1921, 12, 380, 'الحويجبات', 'El-Houidjbet'),
(1922, 12, 381, 'فركان', 'Ferkane'),
(1923, 12, 382, 'قريقر', 'Guorriguer'),
(1924, 12, 383, 'الحمامات', 'Hammamet'),
(1925, 12, 384, 'مرسط', 'Morsott'),
(1926, 12, 385, 'نقرين', 'Negrine'),
(1927, 12, 386, 'الونزة', 'Ouenza'),
(1928, 12, 387, 'أم علي', 'Oum Ali'),
(1929, 12, 388, 'صفصاف الوسرى', 'Saf Saf El Ouesra'),
(1930, 12, 389, 'سطح قنطيس', 'Stah Guentis'),
(1931, 12, 390, 'تبسة', 'Tebessa'),
(1932, 12, 391, 'ثليجان', 'Telidjen'),
(1933, 13, 392, 'عين فتاح', 'Ain Fetah'),
(1934, 13, 393, 'عين فزة', 'Ain Fezza'),
(1935, 13, 394, 'عين غرابة', 'Ain Ghoraba'),
(1936, 13, 395, 'عين الكبيرة', 'Ain Kebira'),
(1937, 13, 396, 'عين النحالة', 'Ain Nehala'),
(1938, 13, 397, 'عين تالوت', 'Ain Tellout'),
(1939, 13, 398, 'عين يوسف', 'Ain Youcef'),
(1940, 13, 399, 'عمير', 'Amieur'),
(1941, 13, 400, 'باب العسة', 'Bab El Assa'),
(1942, 13, 401, 'بني بهدل', 'Beni Bahdel'),
(1943, 13, 402, 'بني بوسعيد', 'Beni Boussaid'),
(1944, 13, 403, 'بني خلاد', 'Beni Khellad'),
(1945, 13, 404, 'بني مستر', 'Beni Mester'),
(1946, 13, 405, 'بني وارسوس', 'Beni Ouarsous'),
(1947, 13, 406, 'بني صميل', 'Beni Smiel'),
(1948, 13, 407, 'بني سنوس', 'Beni Snous'),
(1949, 13, 408, 'بن سكران', 'Bensekrane'),
(1950, 13, 409, 'بوحلو', 'Bouhlou'),
(1951, 13, 410, 'البويهي', 'Bouihi'),
(1952, 13, 411, 'شتوان', 'Chetouane'),
(1953, 13, 412, 'دار يغمراسن', 'Dar Yaghmoracen'),
(1954, 13, 413, 'جبالة', 'Djebala'),
(1955, 13, 414, 'العريشة', 'El Aricha'),
(1956, 13, 415, 'العزايل', 'Azail'),
(1957, 13, 416, 'الفحول', 'El Fehoul'),
(1958, 13, 417, 'القور', 'El Gor'),
(1959, 13, 418, 'فلاوسن', 'Fellaoucene'),
(1960, 13, 419, 'الغزوات', 'Ghazaouet'),
(1961, 13, 420, 'حمام بوغرارة', 'Hammam Boughrara'),
(1962, 13, 421, 'الحناية', 'Hennaya'),
(1963, 13, 422, 'هنين', 'Honnaine'),
(1964, 13, 423, 'مغنية', 'Maghnia'),
(1965, 13, 424, 'منصورة', 'Mansourah'),
(1966, 13, 425, 'مرسى بن مهيدي', 'Marsa Ben M\'hidi'),
(1967, 13, 426, 'مسيردة الفواقة', 'M\'sirda Fouaga'),
(1968, 13, 427, 'ندرومة', 'Nedroma'),
(1969, 13, 428, 'وادي الخضر', 'Oued Lakhdar'),
(1970, 13, 429, 'أولاد ميمون', 'Ouled Mimoun'),
(1971, 13, 430, 'أولاد رياح', 'Ouled Riyah'),
(1972, 13, 431, 'الرمشي', 'Remchi'),
(1973, 13, 432, 'صبرة', 'Sabra'),
(1974, 13, 433, 'سبعة شيوخ', 'Sebbaa Chioukh'),
(1975, 13, 434, 'سبدو', 'Sebdou'),
(1976, 13, 435, 'سيدي العبدلي', 'Sidi Abdelli'),
(1977, 13, 436, 'سيدي الجيلالي', 'Sidi Djillali'),
(1978, 13, 437, 'سيدي مجاهد', 'Sidi Medjahed'),
(1979, 13, 438, 'السواحلية', 'Souahlia'),
(1980, 13, 439, 'السواني', 'Souani'),
(1981, 13, 440, 'سوق الثلاثاء', 'Souk Tleta'),
(1982, 13, 441, 'تيرني بني هديل', 'Terny Beni Hediel'),
(1983, 13, 442, 'تيانت', 'Tianet'),
(1984, 13, 443, 'تلمسان', 'Tlemcen'),
(1985, 13, 444, 'زناتة', 'Zenata'),
(1986, 14, 445, 'عين بوشقيف', 'Ain Bouchekif'),
(1987, 14, 446, 'عين الذهب', 'Ain Deheb'),
(1988, 14, 447, 'عين دزاريت', 'Ain Dzarit'),
(1989, 14, 448, 'عين الحديد', 'Ain El Hadid'),
(1990, 14, 449, 'عين كرمس', 'Ain Kermes'),
(1991, 14, 450, 'بوقرة', 'Bougara'),
(1992, 14, 451, 'شحيمة', 'Chehaima'),
(1993, 14, 452, 'دحموني', 'Dahmouni'),
(1994, 14, 453, 'جبيلات الرصفاء', 'Djebilet Rosfa'),
(1995, 14, 454, 'جيلالي بن عمار', 'Djillali Ben Amar'),
(1996, 14, 455, 'الفايجة', 'Faidja'),
(1997, 14, 456, 'فرندة', 'Frenda'),
(1998, 14, 457, 'قرطوفة', 'Guertoufa'),
(1999, 14, 458, 'حمادية', 'Hamadia'),
(2000, 14, 459, 'قصر الشلالة', 'Ksar Chellala'),
(2001, 14, 460, 'مادنة', 'Madna'),
(2002, 14, 461, 'مهدية', 'Mahdia'),
(2003, 14, 462, 'مشرع الصفا', 'Mechraa Safa'),
(2004, 14, 463, 'مدريسة', 'Medrissa'),
(2005, 14, 464, 'مدروسة', 'Medroussa'),
(2006, 14, 465, 'مغيلة', 'Meghila'),
(2007, 14, 466, 'ملاكو', 'Mellakou'),
(2008, 14, 467, 'الناظورة', 'Nadorah'),
(2009, 14, 468, 'النعيمة', 'Naima'),
(2010, 14, 469, 'وادي ليلي', 'Oued Lilli'),
(2011, 14, 470, 'الرحوية', 'Rahouia'),
(2012, 14, 471, 'الرشايقة', 'Rechaiga'),
(2013, 14, 472, 'السبعين', 'Sebaine'),
(2014, 14, 473, 'السبت', 'Sebt'),
(2015, 14, 474, 'سرغين', 'Serghine'),
(2016, 14, 475, 'سي عبد الغني', 'Si Abdelghani'),
(2017, 14, 476, 'سيدي عبد الرحمن', 'Sidi Abderrahmane'),
(2018, 14, 477, 'سيدي علي ملال', 'Sidi Ali Mellal'),
(2019, 14, 478, 'سيدي بختي', 'Sidi Bakhti'),
(2020, 14, 479, 'سيدي حسني', 'Sidi Hosni'),
(2021, 14, 480, 'السوقر', 'Sougueur'),
(2022, 14, 481, 'تاقدمت', 'Tagdempt'),
(2023, 14, 482, 'تخمرت', 'Takhemaret'),
(2024, 14, 483, 'تيارت', 'Tiaret'),
(2025, 14, 484, 'تيدة', 'Tidda'),
(2026, 14, 485, 'توسنينة', 'Tousnina'),
(2027, 14, 486, 'زمالة  الأمير عبد القادر', 'Zmalet El Emir Abdelkade'),
(2028, 15, 487, 'أبي يوسف', 'Abi-Youcef'),
(2029, 15, 488, 'أغريب', 'Aghribs'),
(2030, 15, 489, 'أقني قغران', 'Agouni-Gueghrane'),
(2031, 15, 490, 'عين الحمام', 'Ain-El-Hammam'),
(2032, 15, 491, 'عين الزاوية', 'Ain-Zaouia'),
(2033, 15, 492, 'أيت عقـواشة', 'Ait Aggouacha'),
(2034, 15, 493, 'أيت بــوادو', 'Ait Bouaddou'),
(2035, 15, 494, 'أيت بومهدي', 'Ait Boumahdi'),
(2036, 15, 495, 'أيت خليلي', 'Ait Khellili'),
(2037, 15, 496, 'أيت يحي موسى', 'Ait Yahia Moussa'),
(2038, 15, 497, 'أيت عيسى ميمون', 'Ait-Aissa-Mimoun'),
(2039, 15, 498, 'أيت شافع', 'Ait-Chafaa'),
(2040, 15, 499, 'أيت محمود', 'Ait-Mahmoud'),
(2041, 15, 500, 'أيت  أومالو', 'Ait-Oumalou'),
(2042, 15, 501, 'أيت تودرت', 'Ait-Toudert'),
(2043, 15, 502, 'أيت يحيى', 'Ait-Yahia'),
(2044, 15, 503, 'اقبيل', 'Akbil'),
(2045, 15, 504, 'أقرو', 'Akerrou'),
(2046, 15, 505, 'أسي يوسف', 'Assi-Youcef'),
(2047, 15, 506, 'عزازقة', 'Azazga'),
(2048, 15, 507, 'أزفون', 'Azeffoun'),
(2049, 15, 508, 'بنــــي زمنزار', 'Beni Zmenzer'),
(2050, 15, 509, 'بني عيسي', 'Beni-Aissi'),
(2051, 15, 510, 'بني دوالة', 'Beni-Douala'),
(2052, 15, 511, 'بني يني', 'Beni-Yenni'),
(2053, 15, 512, 'بني زيكــي', 'Beni-Zikki'),
(2054, 15, 513, 'بوغني', 'Boghni'),
(2055, 15, 514, 'بوجيمة', 'Boudjima'),
(2056, 15, 515, 'بونوح', 'Bounouh'),
(2057, 15, 516, 'بوزقــن', 'Bouzeguene'),
(2058, 15, 517, 'ذراع بن خدة', 'Draa-Ben-Khedda'),
(2059, 15, 518, 'ذراع الميزان', 'Draa-El-Mizan'),
(2060, 15, 519, 'فريحة', 'Freha'),
(2061, 15, 520, 'فريقات', 'Frikat'),
(2062, 15, 521, 'إبودرارن', 'Iboudrarene'),
(2063, 15, 522, 'إيجــار', 'Idjeur'),
(2064, 15, 523, 'إفــرحــونان', 'Iferhounene'),
(2065, 15, 524, 'إيفيغاء', 'Ifigha'),
(2066, 15, 525, 'إفليـــسن', 'Iflissen'),
(2067, 15, 526, 'إيلـيــلتـن', 'Illilten'),
(2068, 15, 527, 'إيلولة أومـــالو', 'Illoula Oumalou'),
(2069, 15, 528, 'إمســوحال', 'Imsouhal'),
(2070, 15, 529, 'إيرجـــن', 'Irdjen'),
(2071, 15, 530, 'الأربعــاء ناث إيراثن', 'Larbaa Nath Irathen'),
(2072, 15, 531, 'معـــاتقة', 'Maatkas'),
(2073, 15, 532, 'ماكودة', 'Makouda'),
(2074, 15, 533, 'مشطراس', 'Mechtras'),
(2075, 15, 534, 'مقــلع', 'Mekla'),
(2076, 15, 535, 'ميزرانـــة', 'Mizrana'),
(2077, 15, 536, 'مكيرة', 'M\'kira'),
(2078, 15, 537, 'واسيف', 'Ouacif'),
(2079, 15, 538, 'واضية', 'Ouadhias'),
(2080, 15, 539, 'واقنون', 'Ouaguenoun'),
(2081, 15, 540, 'سيدي نعمان', 'Sidi Namane'),
(2082, 15, 541, 'صوامـــع', 'Souama'),
(2083, 15, 542, 'سوق الإثنين', 'Souk-El-Tenine'),
(2084, 15, 543, 'تادمايت', 'Tadmait'),
(2085, 15, 544, 'تيقـزيرت', 'Tigzirt'),
(2086, 15, 545, 'تيمـيزار', 'Timizart'),
(2087, 15, 546, 'تيرمتين', 'Tirmitine'),
(2088, 15, 547, 'تيزي نثلاثة', 'Tizi N\'tleta'),
(2089, 15, 548, 'تيزي غنيف', 'Tizi-Gheniff'),
(2090, 15, 549, 'تيزي وزو', 'Tizi-Ouzou'),
(2091, 15, 550, 'تيزي راشد', 'Tizi-Rached'),
(2092, 15, 551, 'إعــكورن', 'Yakourene'),
(2093, 15, 552, 'يطــافن', 'Yatafene'),
(2094, 15, 553, 'زكري', 'Zekri'),
(2095, 16, 554, 'عين بنيان', 'Ain Benian'),
(2096, 16, 555, 'عين طاية', 'Ain Taya'),
(2097, 16, 556, 'الجزائر الوسطى', 'Alger Centre'),
(2098, 16, 557, 'باب الوادي', 'Bab El Oued'),
(2099, 16, 558, 'باب الزوار', 'Bab Ezzouar'),
(2100, 16, 559, 'بابا حسن', 'Baba Hassen'),
(2101, 16, 560, 'باش جراح', 'Bachedjerah'),
(2102, 16, 561, 'براقي', 'Baraki'),
(2103, 16, 562, 'ابن عكنون', 'Ben Aknoun'),
(2104, 16, 563, 'بني مسوس', 'Beni Messous'),
(2105, 16, 564, 'بئر مراد رايس', 'Bir Mourad Rais'),
(2106, 16, 565, 'بئر خادم', 'Birkhadem'),
(2107, 16, 566, 'بئر توتة', 'Bir Touta'),
(2108, 16, 567, 'بولوغين بن زيري', 'Bologhine Ibnou Ziri'),
(2109, 16, 568, 'برج البحري', 'Bordj El Bahri'),
(2110, 16, 569, 'برج الكيفان', 'Bordj El Kiffan'),
(2111, 16, 570, 'بوروبة', 'Bourouba'),
(2112, 16, 571, 'بوزريعة', 'Bouzareah'),
(2113, 16, 572, 'القصبة', 'Casbah'),
(2114, 16, 573, 'الشراقة', 'Cheraga'),
(2115, 16, 574, 'الدار البيضاء', 'Dar El Beida'),
(2116, 16, 575, 'دالي ابراهيم', 'Dely Ibrahim'),
(2117, 16, 576, 'جسر قسنطينة', 'Djasr Kasentina'),
(2118, 16, 577, 'الدويرة', 'Douira'),
(2119, 16, 578, 'الدرارية', 'Draria'),
(2120, 16, 579, 'العاشور', 'El Achour'),
(2121, 16, 580, 'الابيار', 'El Biar'),
(2122, 16, 581, 'الحراش', 'El Harrach'),
(2123, 16, 582, 'المدنية', 'El Madania'),
(2124, 16, 583, 'المغارية', 'El Magharia'),
(2125, 16, 584, 'المرسى', 'El Marsa'),
(2126, 16, 585, 'المرادية', 'El Mouradia'),
(2127, 16, 586, 'الحمامات', 'Hammamet'),
(2128, 16, 587, 'هراوة', 'Herraoua'),
(2129, 16, 588, 'حسين داي', 'Hussein Dey'),
(2130, 16, 589, 'حيدرة', 'Hydra'),
(2131, 16, 590, 'الخرايسية', 'Khraissia'),
(2132, 16, 591, 'القبة', 'Kouba'),
(2133, 16, 592, 'الكاليتوس', 'Les Eucalyptus'),
(2134, 16, 593, 'المعالمة', 'Maalma'),
(2135, 16, 594, 'محمد بلوزداد', 'Mohamed Belouzdad'),
(2136, 16, 595, 'المحمدية', 'Mohammadia'),
(2137, 16, 596, 'وادي قريش', 'Oued Koriche'),
(2138, 16, 597, 'وادي السمار', 'Oued Smar'),
(2139, 16, 598, 'اولاد شبل', 'Ouled Chebel'),
(2140, 16, 599, 'اولاد فايت', 'Ouled Fayet'),
(2141, 16, 600, 'الرحمانية', 'Rahmania'),
(2142, 16, 601, 'الرايس حميدو', 'Rais Hamidou'),
(2143, 16, 602, 'رغاية', 'Reghaia'),
(2144, 16, 603, 'الرويبة', 'Rouiba'),
(2145, 16, 604, 'السحاولة', 'Sehaoula'),
(2146, 16, 605, 'سيدي امحمد', 'Sidi M\'hamed'),
(2147, 16, 606, 'سيدي موسى', 'Sidi Moussa'),
(2148, 16, 607, 'سويدانية', 'Souidania'),
(2149, 16, 608, 'سطاوالي', 'Staoueli'),
(2150, 16, 609, 'تسالة المرجة', 'Tessala El Merdja'),
(2151, 16, 610, 'زرالدة', 'Zeralda'),
(2152, 17, 611, 'عين الشهداء', 'Ain Chouhada'),
(2153, 17, 612, 'عين الإبل', 'Ain El Ibel'),
(2154, 17, 613, 'عين فقه', 'Ain Fekka'),
(2155, 17, 614, 'عين معبد', 'Ain Maabed'),
(2156, 17, 615, 'عين وسارة', 'Ain Oussera'),
(2157, 17, 616, 'عمورة', 'Amourah'),
(2158, 17, 617, 'بنهار', 'Benhar'),
(2159, 17, 618, 'بن يعقوب', 'Benyagoub'),
(2160, 17, 619, 'بيرين', 'Birine'),
(2161, 17, 620, 'بويرة الأحداب', 'Bouira Lahdab'),
(2162, 17, 621, 'الشارف', 'Charef'),
(2163, 17, 622, 'دار الشيوخ', 'Dar Chioukh'),
(2164, 17, 623, 'دلدول', 'Deldoul'),
(2165, 17, 624, 'الجلفة', 'Djelfa'),
(2166, 17, 625, 'دويس', 'Douis'),
(2167, 17, 626, 'القديد', 'El Guedid'),
(2168, 17, 627, 'الادريسية', 'El Idrissia'),
(2169, 17, 628, 'الخميس', 'El Khemis'),
(2170, 17, 629, 'فيض البطمة', 'Faidh El Botma'),
(2171, 17, 630, 'قرنيني', 'Guernini'),
(2172, 17, 631, 'قطارة', 'Guettara'),
(2173, 17, 632, 'حد الصحاري', 'Had Sahary'),
(2174, 17, 633, 'حاسي بحبح', 'Hassi Bahbah'),
(2175, 17, 634, 'حاسي العش', 'Hassi El Euch'),
(2176, 17, 635, 'حاسي فدول', 'Hassi Fedoul'),
(2177, 17, 636, 'مسعد', 'Messaad'),
(2178, 17, 637, 'مليليحة', 'M\'liliha'),
(2179, 17, 638, 'مجبارة', 'Moudjebara'),
(2180, 17, 639, 'أم العظام', 'Oum Laadham'),
(2181, 17, 640, 'سد الرحال', 'Sed Rahal'),
(2182, 17, 641, 'سلمانة', 'Selmana'),
(2183, 17, 642, 'سيدي بايزيد', 'Sidi Baizid'),
(2184, 17, 643, 'سيدي لعجال', 'Sidi Laadjel'),
(2185, 17, 644, 'تعظميت', 'Taadmit'),
(2186, 17, 645, 'زعفران', 'Zaafrane'),
(2187, 17, 646, 'زكار', 'Zaccar'),
(2188, 18, 647, 'برج الطهر', 'Bordj T\'har'),
(2189, 18, 648, 'بودريعة بني  ياجيس', 'Boudria Beniyadjis'),
(2190, 18, 649, 'بوراوي بلهادف', 'Bouraoui Belhadef'),
(2191, 18, 650, 'بوسيف أولاد عسكر', 'Boussif Ouled Askeur'),
(2192, 18, 651, 'الشحنة', 'Chahna'),
(2193, 18, 652, 'الشقفة', 'Chekfa'),
(2194, 18, 653, 'الجمعة بني حبيبي', 'Djemaa Beni Habibi'),
(2195, 18, 654, 'جيملة', 'Djimla'),
(2196, 18, 655, 'العنصر', 'El Ancer'),
(2197, 18, 656, 'العوانة', 'El Aouana'),
(2198, 18, 657, 'القنار نشفي', 'El Kennar Nouchfi'),
(2199, 18, 658, 'الميلية', 'El Milia'),
(2200, 18, 659, 'الامير عبد القادر', 'Emir Abdelkader'),
(2201, 18, 660, 'أراقن سويسي', 'Erraguene Souissi'),
(2202, 18, 661, 'غبالة', 'Ghebala'),
(2203, 18, 662, 'جيجل', 'Jijel'),
(2204, 18, 663, 'قاوس', 'Kaous'),
(2205, 18, 664, 'خيري واد عجول', 'Khiri Oued Adjoul'),
(2206, 18, 665, 'وجانة', 'Oudjana'),
(2207, 18, 666, 'أولاد رابح', 'Ouled Rabah'),
(2208, 18, 667, 'أولاد يحيى خدروش', 'Ouled Yahia Khadrouch'),
(2209, 18, 668, 'سلمى بن زيادة', 'Selma Benziada'),
(2210, 18, 669, 'السطارة', 'Settara'),
(2211, 18, 670, 'سيدي عبد العزيز', 'Sidi Abdelaziz'),
(2212, 18, 671, 'سيدي معروف', 'Sidi Marouf'),
(2213, 18, 672, 'الطاهير', 'Taher'),
(2214, 18, 673, 'تاكسنة', 'Texenna'),
(2215, 18, 674, 'زيامة منصورية', 'Ziama Mansouriah'),
(2216, 19, 675, 'عين عباسة', 'Ain Abessa'),
(2217, 19, 676, 'عين أرنات', 'Ain Arnat'),
(2218, 19, 677, 'عين أزال', 'Ain Azel'),
(2219, 19, 678, 'عين الكبيرة', 'Ain El Kebira'),
(2220, 19, 679, 'عين الحجر', 'Ain Lahdjar'),
(2221, 19, 680, 'عين ولمان', 'Ain Oulmene'),
(2222, 19, 681, 'عين لقراج', 'Ain-Legradj'),
(2223, 19, 682, 'عين الروى', 'Ain-Roua'),
(2224, 19, 683, 'عين السبت', 'Ain-Sebt'),
(2225, 19, 684, 'أيت نوال مزادة', 'Ait Naoual Mezada'),
(2226, 19, 685, 'ايت تيزي', 'Ait-Tizi'),
(2227, 19, 686, 'عموشة', 'Amoucha'),
(2228, 19, 687, 'بابور', 'Babor'),
(2229, 19, 688, 'بازر سكرة', 'Bazer-Sakra'),
(2230, 19, 689, 'بيضاء برج', 'Beidha Bordj'),
(2231, 19, 690, 'بلاعة', 'Bellaa'),
(2232, 19, 691, 'بني شبانة', 'Beni Chebana'),
(2233, 19, 692, 'بني فودة', 'Beni Fouda'),
(2234, 19, 693, 'بني ورتيلان', 'Beni Ourtilane'),
(2235, 19, 694, 'بني وسين', 'Beni Oussine'),
(2236, 19, 695, 'بني عزيز', 'Beni-Aziz'),
(2237, 19, 696, 'بني موحلي', 'Beni-Mouhli'),
(2238, 19, 697, 'بئر حدادة', 'Bir Haddada'),
(2239, 19, 698, 'بئر العرش', 'Bir-El-Arch'),
(2240, 19, 699, 'بوعنداس', 'Bouandas'),
(2241, 19, 700, 'بوقاعة', 'Bougaa'),
(2242, 19, 701, 'بوسلام', 'Bousselam'),
(2243, 19, 702, 'بوطالب', 'Boutaleb'),
(2244, 19, 703, 'الدهامشة', 'Dehamcha'),
(2245, 19, 704, 'جميلة', 'Djemila'),
(2246, 19, 705, 'ذراع قبيلة', 'Draa-Kebila'),
(2247, 19, 706, 'العلمة', 'El Eulma'),
(2248, 19, 707, 'أوريسيا', 'El Ouricia'),
(2249, 19, 708, 'الولجة', 'El-Ouldja'),
(2250, 19, 709, 'قلال', 'Guellal'),
(2251, 19, 710, 'قلتة زرقاء', 'Guelta Zerka'),
(2252, 19, 711, 'قنزات', 'Guenzet'),
(2253, 19, 712, 'قجال', 'Guidjel'),
(2254, 19, 713, 'حمام السخنة', 'Hamam Soukhna'),
(2255, 19, 714, 'الحامة', 'Hamma'),
(2256, 19, 715, 'حمام قرقور', 'Hammam Guergour'),
(2257, 19, 716, 'حربيل', 'Harbil'),
(2258, 19, 717, 'قصر الابطال', 'Kasr El Abtal'),
(2259, 19, 718, 'معاوية', 'Maaouia'),
(2260, 19, 719, 'ماوكلان', 'Maouaklane'),
(2261, 19, 720, 'مزلوق', 'Mezloug'),
(2262, 19, 721, 'واد البارد', 'Oued El Bared'),
(2263, 19, 722, 'أولاد عدوان', 'Ouled Addouane'),
(2264, 19, 723, 'أولاد صابر', 'Ouled Sabor'),
(2265, 19, 724, 'أولاد سي أحمد', 'Ouled Si Ahmed'),
(2266, 19, 725, 'أولاد تبان', 'Ouled Tebben'),
(2267, 19, 726, 'الرصفة', 'Rosfa'),
(2268, 19, 727, 'صالح باي', 'Salah Bey'),
(2269, 19, 728, 'سرج الغول', 'Serdj-El-Ghoul'),
(2270, 19, 729, 'سطيف', 'Setif'),
(2271, 19, 730, 'تاشودة', 'Tachouda'),
(2272, 19, 731, 'تالة إيفاسن', 'Tala-Ifacene'),
(2273, 19, 732, 'الطاية', 'Taya'),
(2274, 19, 733, 'التلة', 'Tella'),
(2275, 19, 734, 'تيزي نبشار', 'Tizi N\'bechar'),
(2276, 20, 735, 'عين الحجر', 'Ain El Hadjar'),
(2277, 20, 736, 'عين السخونة', 'Ain Sekhouna'),
(2278, 20, 737, 'عين السلطان', 'Ain Soltane'),
(2279, 20, 738, 'دوي ثابت', 'Doui Thabet'),
(2280, 20, 739, 'الحساسنة', 'El Hassasna'),
(2281, 20, 740, 'هونت', 'Hounet'),
(2282, 20, 741, 'المعمورة', 'Maamora'),
(2283, 20, 742, 'مولاي العربي', 'Moulay Larbi'),
(2284, 20, 743, 'أولاد إبراهيم', 'Ouled Brahim'),
(2285, 20, 744, 'أولاد خالد', 'Ouled Khaled'),
(2286, 20, 745, 'سعيدة', 'Saida'),
(2287, 20, 746, 'سيدي احمد', 'Sidi Ahmed'),
(2288, 20, 747, 'سيدي عمر', 'Sidi Amar'),
(2289, 20, 748, 'سيدي بوبكر', 'Sidi Boubekeur'),
(2290, 20, 749, 'تيرسين', 'Tircine'),
(2291, 20, 750, 'يوب', 'Youb'),
(2292, 21, 751, 'عين بوزيان', 'Ain Bouziane'),
(2293, 21, 752, 'عين شرشار', 'Ain Charchar'),
(2294, 21, 753, 'عين قشرة', 'Ain Kechra'),
(2295, 21, 754, 'عين زويت', 'Ain Zouit'),
(2296, 21, 755, 'عزابة', 'Azzaba'),
(2297, 21, 756, 'بكوش لخضر', 'Bekkouche Lakhdar'),
(2298, 21, 757, 'بن عزوز', 'Ben Azzouz'),
(2299, 21, 758, 'بني بشير', 'Beni Bechir'),
(2300, 21, 759, 'بني ولبان', 'Beni Oulbane'),
(2301, 21, 760, 'بني زيد', 'Beni Zid'),
(2302, 21, 761, 'بين الويدان', 'Bin El Ouiden'),
(2303, 21, 762, 'بوشطاطة', 'Bouchetata'),
(2304, 21, 763, 'الشرايع', 'Cheraia'),
(2305, 21, 764, 'القل', 'Collo'),
(2306, 21, 765, 'جندل سعدي محمد', 'Djendel Saadi Mohamed'),
(2307, 21, 766, 'الحروش', 'El Arrouch'),
(2308, 21, 767, 'الغدير', 'El Ghedir'),
(2309, 21, 768, 'الحدائق', 'El Hadaiek'),
(2310, 21, 769, 'المرسى', 'El Marsa'),
(2311, 21, 770, 'مجاز الدشيش', 'Emjez Edchich'),
(2312, 21, 771, 'السبت', 'Es Sebt'),
(2313, 21, 772, 'فلفلة', 'Filfila'),
(2314, 21, 773, 'حمادي كرومة', 'Hammadi Krouma'),
(2315, 21, 774, 'قنواع', 'Kanoua'),
(2316, 21, 775, 'الكركرة', 'Kerkara'),
(2317, 21, 776, 'خناق مايو', 'Khenag Maoune'),
(2318, 21, 777, 'وادي الزهور', 'Oued Zhour'),
(2319, 21, 778, 'الولجة بولبلوط', 'Ouldja Boulbalout'),
(2320, 21, 779, 'أولاد عطية', 'Ouled Attia'),
(2321, 21, 780, 'أولاد حبابة', 'Ouled Habbaba'),
(2322, 21, 781, 'أم الطوب', 'Oum Toub'),
(2323, 21, 782, 'رمضان جمال', 'Ramdane Djamel'),
(2324, 21, 783, 'صالح بو الشعور', 'Salah Bouchaour'),
(2325, 21, 784, 'سيدي مزغيش', 'Sidi Mezghiche'),
(2326, 21, 785, 'سكيكدة', 'Skikda'),
(2327, 21, 786, 'تمالوس', 'Tamalous'),
(2328, 21, 787, 'زردازة', 'Zerdezas'),
(2329, 21, 788, 'الزيتونة', 'Zitouna'),
(2330, 22, 789, 'عين البرد', 'Ain El Berd'),
(2331, 22, 790, 'عين قادة', 'Ain Kada'),
(2332, 22, 791, 'عين الثريد', 'Ain Thrid'),
(2333, 22, 792, 'عين تندمين', 'Ain Tindamine'),
(2334, 22, 793, 'عين أدن', 'Ain- Adden'),
(2335, 22, 794, 'العمارنة', 'Amarnas'),
(2336, 22, 795, 'بضرابين المقراني', 'Bedrabine El Mokrani'),
(2337, 22, 796, 'بلعربي', 'Belarbi'),
(2338, 22, 797, 'بن باديس', 'Ben Badis'),
(2339, 22, 798, 'بن عشيبة شلية', 'Benachiba Chelia'),
(2340, 22, 799, 'بئر الحمام', 'Bir El Hammam'),
(2341, 22, 800, 'بوجبهة البرج', 'Boudjebaa El Bordj'),
(2342, 22, 801, 'بوخنفيس', 'Boukhanefis'),
(2343, 22, 802, 'شيطوان البلايلة', 'Chetouane Belaila'),
(2344, 22, 803, 'الضاية', 'Dhaya'),
(2345, 22, 804, 'الحصيبة', 'El Hacaiba'),
(2346, 22, 805, 'حاسي دحو', 'Hassi Dahou'),
(2347, 22, 806, 'حاسي زهانة', 'Hassi Zahana'),
(2348, 22, 807, 'لمطار', 'Lamtar'),
(2349, 22, 808, 'مكدرة', 'Makedra'),
(2350, 22, 809, 'مرحوم', 'Marhoum'),
(2351, 22, 810, 'مسيد', 'M\'cid'),
(2352, 22, 811, 'مرين', 'Merine'),
(2353, 22, 812, 'مزاورو', 'Mezaourou'),
(2354, 22, 813, 'مصطفى بن ابراهيم', 'Mostefa  Ben Brahim'),
(2355, 22, 814, 'مولاي سليسن', 'Moulay Slissen'),
(2356, 22, 815, 'وادي السبع', 'Oued Sebaa'),
(2357, 22, 816, 'وادي سفيون', 'Oued Sefioun'),
(2358, 22, 817, 'وادي تاوريرة', 'Oued Taourira'),
(2359, 22, 818, 'راس الماء', 'Ras El Ma'),
(2360, 22, 819, 'رجم دموش', 'Redjem Demouche'),
(2361, 22, 820, 'السهالة الثورة', 'Sehala Thaoura'),
(2362, 22, 821, 'سفيزف', 'Sfisef'),
(2363, 22, 822, 'سيدي علي بن يوب', 'Sidi Ali Benyoub'),
(2364, 22, 823, 'سيدي علي بوسيدي', 'Sidi Ali Boussidi'),
(2365, 22, 824, 'سيدي بلعباس', 'Sidi Bel-Abbes'),
(2366, 22, 825, 'سيدي ابراهيم', 'Sidi Brahim'),
(2367, 22, 826, 'سيدي شعيب', 'Sidi Chaib'),
(2368, 22, 827, 'سيدي دحو الزاير', 'Sidi Dahou Zairs'),
(2369, 22, 828, 'سيدي حمادوش', 'Sidi Hamadouche'),
(2370, 22, 829, 'سيدي خالد', 'Sidi Khaled'),
(2371, 22, 830, 'سيدي لحسن', 'Sidi Lahcene'),
(2372, 22, 831, 'سيدي يعقوب', 'Sidi Yacoub'),
(2373, 22, 832, 'طابية', 'Tabia'),
(2374, 22, 833, 'تاودموت', 'Taoudmout'),
(2375, 22, 834, 'تفسور', 'Tefessour'),
(2376, 22, 835, 'تغاليمت', 'Teghalimet'),
(2377, 22, 836, 'تلاغ', 'Telagh'),
(2378, 22, 837, 'تنيرة', 'Tenira'),
(2379, 22, 838, 'تسالة', 'Tessala'),
(2380, 22, 839, 'تلموني', 'Tilmouni'),
(2381, 22, 840, 'زروالة', 'Zerouala'),
(2382, 23, 841, 'عين الباردة', 'Ain El Berda'),
(2383, 23, 842, 'عنابة', 'Annaba'),
(2384, 23, 843, 'برحال', 'Berrahal'),
(2385, 23, 844, 'شطايبي', 'Chetaibi'),
(2386, 23, 845, 'الشرفة', 'Cheurfa'),
(2387, 23, 846, 'البوني', 'El Bouni'),
(2388, 23, 847, 'العلمة', 'El Eulma'),
(2389, 23, 848, 'الحجار', 'El Hadjar'),
(2390, 23, 849, 'واد العنب', 'Oued El Aneb'),
(2391, 23, 850, 'سرايدي', 'Seraidi'),
(2392, 23, 851, 'سيدي عمار', 'Sidi Amar'),
(2393, 23, 852, 'التريعات', 'Treat'),
(2394, 24, 853, 'عين بن بيضاء', 'Ain Ben Beida'),
(2395, 24, 854, 'عين العربي', 'Ain Larbi'),
(2396, 24, 855, 'عين مخلوف', 'Ain Makhlouf'),
(2397, 24, 856, 'عين رقادة', 'Ain Regada'),
(2398, 24, 857, 'عين صندل', 'Ain Sandel'),
(2399, 24, 858, 'بلخير', 'Belkheir'),
(2400, 24, 859, 'بن جراح', 'Bendjarah'),
(2401, 24, 860, 'بني مزلين', 'Beni Mezline'),
(2402, 24, 861, 'برج صباط', 'Bordj Sabath'),
(2403, 24, 862, 'بوحشانة', 'Bou Hachana'),
(2404, 24, 863, 'بوحمدان', 'Bou Hamdane'),
(2405, 24, 864, 'بوعاتي محمود', 'Bouati Mahmoud'),
(2406, 24, 865, 'بوشقوف', 'Bouchegouf'),
(2407, 24, 866, 'بومهرة أحمد', 'Boumahra Ahmed'),
(2408, 24, 867, 'الدهوارة', 'Dahouara'),
(2409, 24, 868, 'جبالة الخميسي', 'Djeballah Khemissi'),
(2410, 24, 869, 'الفجوج', 'El Fedjoudj'),
(2411, 24, 870, 'قلعة بوصبع', 'Guelaat Bou Sbaa'),
(2412, 24, 871, 'قالمة', 'Guelma'),
(2413, 24, 872, 'حمام دباغ', 'Hammam Debagh'),
(2414, 24, 873, 'حمام النبايل', 'Hammam N\'bail'),
(2415, 24, 874, 'هيليوبوليس', 'Heliopolis'),
(2416, 24, 875, 'نشماية', 'Nechmaya'),
(2417, 24, 876, 'لخزارة', 'Khezaras'),
(2418, 24, 877, 'مجاز عمار', 'Medjez Amar'),
(2419, 24, 878, 'مجاز الصفاء', 'Medjez Sfa'),
(2420, 24, 879, 'هواري بومدين', 'Houari Boumedienne'),
(2421, 24, 880, 'وادي الشحم', 'Oued Cheham'),
(2422, 24, 881, 'وادي فراغة', 'Oued Ferragha'),
(2423, 24, 882, 'وادي الزناتي', 'Oued Zenati'),
(2424, 24, 883, 'رأس العقبة', 'Ras El Agba'),
(2425, 24, 884, 'الركنية', 'Roknia'),
(2426, 24, 885, 'سلاوة عنونة', 'Sellaoua Announa'),
(2427, 24, 886, 'تاملوكة', 'Tamlouka'),
(2428, 25, 887, 'عين عبيد', 'Ain Abid'),
(2429, 25, 888, 'عين السمارة', 'Ain Smara'),
(2430, 25, 889, 'أبن باديس الهرية', 'Ben Badis'),
(2431, 25, 890, 'بني حميدان', 'Beni Hamidane'),
(2432, 25, 891, 'قسنطينة', 'Constantine'),
(2433, 25, 892, 'ديدوش مراد', 'Didouche Mourad'),
(2434, 25, 893, 'الخروب', 'El Khroub'),
(2435, 25, 894, 'حامة بوزيان', 'Hamma Bouziane'),
(2436, 25, 895, 'ابن زياد', 'Ibn Ziad'),
(2437, 25, 896, 'بوجريو مسعود', 'Messaoud Boudjeriou'),
(2438, 25, 897, 'أولاد رحمون', 'Ouled Rahmoun'),
(2439, 25, 898, 'زيغود يوسف', 'Zighoud Youcef'),
(2440, 26, 899, 'عين بوسيف', 'Ain Boucif'),
(2441, 26, 900, 'عين اقصير', 'Ain Ouksir'),
(2442, 26, 901, 'العيساوية', 'Aissaouia'),
(2443, 26, 902, 'عزيز', 'Aziz'),
(2444, 26, 903, 'بعطة', 'Baata'),
(2445, 26, 904, 'بن شكاو', 'Ben Chicao'),
(2446, 26, 905, 'بني سليمان', 'Beni Slimane'),
(2447, 26, 906, 'البرواقية', 'Berrouaghia'),
(2448, 26, 907, 'بئر بن عابد', 'Bir Ben Laabed'),
(2449, 26, 908, 'بوغار', 'Boghar'),
(2450, 26, 909, 'بوعيش', 'Bouaiche'),
(2451, 26, 910, 'بوعيشون', 'Bouaichoune'),
(2452, 26, 911, 'بوشراحيل', 'Bouchrahil'),
(2453, 26, 912, 'بوغزول', 'Boughzoul'),
(2454, 26, 913, 'بوسكن', 'Bouskene'),
(2455, 26, 914, 'الشهبونية', 'Chabounia'),
(2456, 26, 915, 'شلالة العذاورة', 'Chelalet El Adhaoura'),
(2457, 26, 916, 'شنيقل', 'Cheniguel'),
(2458, 26, 917, 'دراق', 'Derrag'),
(2459, 26, 918, 'جواب', 'Djouab'),
(2460, 26, 919, 'ذراع السمار', 'Draa Esmar'),
(2461, 26, 920, 'العزيزية', 'El Azizia'),
(2462, 26, 921, 'القلب الكبير', 'El Guelbelkebir'),
(2463, 26, 922, 'الحمدانية', 'El Hamdania'),
(2464, 26, 923, 'الحوضان', 'El Haoudane'),
(2465, 26, 924, 'العمارية', 'El Omaria'),
(2466, 26, 925, 'العوينات', 'El Ouinet'),
(2467, 26, 926, 'حناشة', 'Hannacha'),
(2468, 26, 927, 'الكاف الاخضر', 'Kef Lakhdar'),
(2469, 26, 928, 'خمس جوامع', 'Khams Djouamaa'),
(2470, 26, 929, 'قصر البخاري', 'Ksar El Boukhari'),
(2471, 26, 930, 'مغراوة', 'Maghraoua'),
(2472, 26, 931, 'المدية', 'Medea'),
(2473, 26, 932, 'مجبر', 'Medjebar'),
(2474, 26, 933, 'مزغنة', 'Mezerana'),
(2475, 26, 934, 'مفاتحة', 'M\'fatha'),
(2476, 26, 935, 'ميهوب', 'Mihoub'),
(2477, 26, 936, 'عوامري', 'Ouamri'),
(2478, 26, 937, 'وادي حربيل', 'Oued Harbil'),
(2479, 26, 938, 'أولاد عنتر', 'Ouled Antar'),
(2480, 26, 939, 'أولاد بوعشرة', 'Ouled Bouachra'),
(2481, 26, 940, 'أولاد إبراهيم', 'Ouled Brahim'),
(2482, 26, 941, 'أولاد دايد', 'Ouled Deid'),
(2483, 26, 942, 'أولاد امعرف', 'Ouled Emaaraf'),
(2484, 26, 943, 'أولاد هلال', 'Ouled Hellal'),
(2485, 26, 944, 'أم الجليل', 'Oum El Djellil'),
(2486, 26, 945, 'وزرة', 'Ouzera'),
(2487, 26, 946, 'الربعية', 'Rebaia'),
(2488, 26, 947, 'السانق', 'Saneg'),
(2489, 26, 948, 'سدراية', 'Sedraya'),
(2490, 26, 949, 'سغوان', 'Seghouane'),
(2491, 26, 950, 'سي المحجوب', 'Si Mahdjoub'),
(2492, 26, 951, 'سيدي دامد', 'Sidi Demed'),
(2493, 26, 952, 'سيدي نعمان', 'Sidi Naamane'),
(2494, 26, 953, 'سيدي الربيع', 'Sidi Rabie'),
(2495, 26, 954, 'سيدي زهار', 'Sidi Zahar'),
(2496, 26, 955, 'سيدي زيان', 'Sidi Ziane'),
(2497, 26, 956, 'السواقي', 'Souagui'),
(2498, 26, 957, 'تابلاط', 'Tablat'),
(2499, 26, 958, 'تفراوت', 'Tafraout'),
(2500, 26, 959, 'تمسقيدة', 'Tamesguida'),
(2501, 26, 960, 'تيزي مهدي', 'Tizi Mahdi'),
(2502, 26, 961, 'ثلاث دوائر', 'Tletat Ed Douair'),
(2503, 26, 962, 'الزبيرية', 'Zoubiria'),
(2504, 27, 963, 'عشعاشة', 'Achaacha'),
(2505, 27, 964, 'عين بودينار', 'Ain-Boudinar'),
(2506, 27, 965, 'عين نويسي', 'Ain-Nouissy'),
(2507, 27, 966, 'عين سيدي الشريف', 'Ain-Sidi Cherif'),
(2508, 27, 967, 'عين تادلس', 'Ain-Tedles'),
(2509, 27, 968, 'بن عبد المالك رمضان', 'Benabdelmalek Ramdane'),
(2510, 27, 969, 'بوقيراط', 'Bouguirat'),
(2511, 27, 970, 'فرناقة', 'Fornaka'),
(2512, 27, 971, 'حجاج', 'Hadjadj'),
(2513, 27, 972, 'حاسي ماماش', 'Hassi Mameche'),
(2514, 27, 973, 'الحسيان (بني ياحي', 'Hassiane'),
(2515, 27, 974, 'خضرة', 'Khadra'),
(2516, 27, 975, 'خير الدين', 'Kheir-Eddine'),
(2517, 27, 976, 'منصورة', 'Mansourah'),
(2518, 27, 977, 'مزغران', 'Mazagran'),
(2519, 27, 978, 'ماسرة', 'Mesra'),
(2520, 27, 979, 'مستغانم', 'Mostaganem'),
(2521, 27, 980, 'نكمارية', 'Nekmaria'),
(2522, 27, 981, 'وادي الخير', 'Oued El Kheir'),
(2523, 27, 982, 'أولاد بوغالم', 'Ouled Boughalem'),
(2524, 27, 983, 'أولاد مع الله', 'Ouled-Maalah'),
(2525, 27, 984, 'صفصاف', 'Safsaf'),
(2526, 27, 985, 'صيادة', 'Sayada'),
(2527, 27, 986, 'سيدي علي', 'Sidi Ali'),
(2528, 27, 987, 'سيدي بلعطار', 'Sidi Belaattar'),
(2529, 27, 988, 'سيدي لخضر', 'Sidi-Lakhdar'),
(2530, 27, 989, 'سيرات', 'Sirat'),
(2531, 27, 990, 'السوافلية', 'Souaflia'),
(2532, 27, 991, 'سور', 'Sour'),
(2533, 27, 992, 'ستيدية', 'Stidia'),
(2534, 27, 993, 'تزقايت', 'Tazgait'),
(2535, 27, 994, 'الطواهرية', 'Touahria'),
(2536, 28, 995, 'عين الحجل', 'Ain El Hadjel'),
(2537, 28, 996, 'عين الملح', 'Ain El Melh'),
(2538, 28, 997, 'عين فارس', 'Ain Fares'),
(2539, 28, 998, 'عين الخضراء', 'Ain Khadra'),
(2540, 28, 999, 'عين الريش', 'Ain Rich'),
(2541, 28, 1000, 'بلعايبة', 'Belaiba'),
(2542, 28, 1001, 'بن سرور', 'Ben Srour'),
(2543, 28, 1002, 'بني يلمان', 'Beni Ilmane'),
(2544, 28, 1003, 'بن زوه', 'Benzouh'),
(2545, 28, 1004, 'برهوم', 'Berhoum'),
(2546, 28, 1005, 'بئر فضة', 'Bir Foda'),
(2547, 28, 1006, 'بوسعادة', 'Bou Saada'),
(2548, 28, 1007, 'بوطي السايح', 'Bouti Sayeh'),
(2549, 28, 1008, 'شلال', 'Chellal'),
(2550, 28, 1009, 'دهاهنة', 'Dehahna'),
(2551, 28, 1010, 'جبل مساعد', 'Djebel Messaad'),
(2552, 28, 1011, 'الهامل', 'El Hamel'),
(2553, 28, 1012, 'الحوامد', 'El Houamed'),
(2554, 28, 1013, 'حمام الضلعة', 'Hammam Dalaa'),
(2555, 28, 1014, 'خطوطي سد الجير', 'Khettouti Sed-El-Jir'),
(2556, 28, 1015, 'خبانة', 'Khoubana'),
(2557, 28, 1016, 'المعاضيد', 'Maadid'),
(2558, 28, 1017, 'معاريف', 'Maarif'),
(2559, 28, 1018, 'مقرة', 'Magra'),
(2560, 28, 1019, 'مسيف', 'M\'cif'),
(2561, 28, 1020, 'امجدل', 'Medjedel'),
(2562, 28, 1021, 'مناعة', 'Menaa'),
(2563, 28, 1022, 'محمد بوضياف', 'Mohamed Boudiaf'),
(2564, 28, 1023, 'المسيلة', 'M\'sila'),
(2565, 28, 1024, 'المطارفة', 'M\'tarfa'),
(2566, 28, 1025, 'ونوغة', 'Ouanougha'),
(2567, 28, 1026, 'أولاد عدي لقبالة', 'Ouled Addi Guebala'),
(2568, 28, 1027, 'أولاد دراج', 'Ouled Derradj'),
(2569, 28, 1028, 'أولاد ماضي', 'Ouled Madhi'),
(2570, 28, 1029, 'أولاد منصور', 'Ouled Mansour'),
(2571, 28, 1030, 'أولاد سيدي ابراهيم', 'Ouled Sidi Brahim'),
(2572, 28, 1031, 'أولاد سليمان', 'Ouled Slimane'),
(2573, 28, 1032, 'ولتام', 'Oulteme'),
(2574, 28, 1033, 'سيدي عيسى', 'Sidi Aissa'),
(2575, 28, 1034, 'سيدي عامر', 'Sidi Ameur'),
(2576, 28, 1035, 'سيدي هجرس', 'Sidi Hadjeres'),
(2577, 28, 1036, 'سيدي امحمد', 'Sidi M\'hamed'),
(2578, 28, 1037, 'سليم', 'Slim'),
(2579, 28, 1038, 'السوامع', 'Souamaa'),
(2580, 28, 1039, 'تامسة', 'Tamsa'),
(2581, 28, 1040, 'تارمونت', 'Tarmount'),
(2582, 28, 1041, 'زرزور', 'Zarzour'),
(2583, 29, 1042, 'عين فارس', 'Ain Fares'),
(2584, 29, 1043, 'عين فكان', 'Ain Fekan'),
(2585, 29, 1044, 'عين فراح', 'Ain Ferah'),
(2586, 29, 1045, 'عين أفرص', 'Ain Frass'),
(2587, 29, 1046, 'العلايمية', 'Alaimia'),
(2588, 29, 1047, 'عوف', 'Aouf'),
(2589, 29, 1048, 'بنيان', 'Benian'),
(2590, 29, 1049, 'بوهني', 'Bou Henni'),
(2591, 29, 1050, 'بوحنيفية', 'Bouhanifia'),
(2592, 29, 1051, 'الشرفاء', 'Chorfa'),
(2593, 29, 1052, 'البرج', 'El Bordj'),
(2594, 29, 1053, 'القعدة', 'El Gaada'),
(2595, 29, 1054, 'الغمري', 'El Ghomri'),
(2596, 29, 1055, 'القطنة', 'El Gueitena'),
(2597, 29, 1056, 'الحشم', 'El Hachem'),
(2598, 29, 1057, 'القرط', 'El Keurt'),
(2599, 29, 1058, 'المأمونية', 'El Mamounia'),
(2600, 29, 1059, 'المنور', 'El Menaouer'),
(2601, 29, 1060, 'فراقيق', 'Ferraguig'),
(2602, 29, 1061, 'فروحة', 'Froha'),
(2603, 29, 1062, 'غروس', 'Gharrous'),
(2604, 29, 1063, 'غريس', 'Ghriss'),
(2605, 29, 1064, 'قرجوم', 'Guerdjoum'),
(2606, 29, 1065, 'حسين', 'Hacine'),
(2607, 29, 1066, 'خلوية', 'Khalouia'),
(2608, 29, 1067, 'ماقضة', 'Makhda'),
(2609, 29, 1068, 'ماوسة', 'Maoussa'),
(2610, 29, 1069, 'معسكر', 'Mascara'),
(2611, 29, 1070, 'المطمور', 'Matemore'),
(2612, 29, 1071, 'مقطع الدوز', 'Mocta-Douz'),
(2613, 29, 1072, 'المحمدية', 'Mohammadia'),
(2614, 29, 1073, 'نسمط', 'Nesmot'),
(2615, 29, 1074, 'عقاز', 'Oggaz'),
(2616, 29, 1075, 'وادي الأبطال', 'Oued El Abtal'),
(2617, 29, 1076, 'وادي التاغية', 'Oued Taria'),
(2618, 29, 1077, 'رأس عين عميروش', 'Ras El Ain Amirouche'),
(2619, 29, 1078, 'سجرارة', 'Sedjerara'),
(2620, 29, 1079, 'السهايلية', 'Sehailia'),
(2621, 29, 1080, 'سيدي عبد الجبار', 'Sidi Abdeldjebar'),
(2622, 29, 1081, 'سيدي عبد المومن', 'Sidi Abdelmoumene'),
(2623, 29, 1082, 'سيدي بوسعيد', 'Sidi Boussaid'),
(2624, 29, 1083, 'سيدي قادة', 'Sidi Kada'),
(2625, 29, 1084, 'سيق', 'Sig'),
(2626, 29, 1085, 'تيغنيف', 'Tighennif'),
(2627, 29, 1086, 'تيزي', 'Tizi'),
(2628, 29, 1087, 'زهانة', 'Zahana'),
(2629, 29, 1088, 'زلامطة', 'Zelamta'),
(2630, 30, 1089, 'عين البيضاء', 'Ain Beida'),
(2631, 55, 1090, 'بن ناصر', 'Benaceur'),
(2632, 55, 1091, 'بلدة اعمر', 'Blidet Amor'),
(2633, 55, 1092, 'العالية', 'El Alia'),
(2634, 30, 1093, 'البرمة', 'El Borma'),
(2635, 55, 1094, 'الحجيرة', 'El-Hadjira'),
(2636, 30, 1095, 'حاسي بن عبد الله', 'Hassi Ben Abdellah'),
(2637, 30, 1096, 'حاسي مسعود', 'Hassi Messaoud'),
(2638, 55, 1097, 'المقارين', 'Megarine'),
(2639, 55, 1098, 'المنقر', 'M\'naguer'),
(2640, 55, 1099, 'النزلة', 'Nezla'),
(2641, 30, 1100, 'انقوسة', 'N\'goussa'),
(2642, 30, 1101, 'ورقلة', 'Ouargla'),
(2643, 30, 1102, 'الرويسات', 'Rouissat'),
(2644, 30, 1103, 'سيدي خويلد', 'Sidi Khouiled'),
(2645, 55, 1104, 'سيدي سليمان', 'Sidi Slimane'),
(2646, 55, 1105, 'الطيبات', 'Taibet'),
(2647, 55, 1106, 'تبسبست', 'Tebesbest'),
(2648, 55, 1107, 'تماسين', 'Temacine'),
(2649, 55, 1108, 'تقرت', 'Touggourt'),
(2650, 55, 1109, 'الزاوية العابدية', 'Zaouia El Abidia'),
(2651, 31, 1110, 'عين البية', 'Ain Biya'),
(2652, 31, 1111, 'عين الكرمة', 'Ain Kerma'),
(2653, 31, 1112, 'عين الترك', 'Ain Turk'),
(2654, 31, 1113, 'أرزيو', 'Arzew'),
(2655, 31, 1114, 'بن فريحة', 'Ben Freha'),
(2656, 31, 1115, 'بطيوة', 'Bethioua'),
(2657, 31, 1116, 'بئر الجير', 'Bir El Djir'),
(2658, 31, 1117, 'بوفاتيس', 'Boufatis'),
(2659, 31, 1118, 'بوسفر', 'Bousfer'),
(2660, 31, 1119, 'بوتليليس', 'Boutlelis'),
(2661, 31, 1120, 'العنصر', 'El Ancor'),
(2662, 31, 1121, 'البراية', 'El Braya'),
(2663, 31, 1122, 'الكرمة', 'El Kerma'),
(2664, 31, 1123, 'السانية', 'Es Senia'),
(2665, 31, 1124, 'قديل', 'Gdyel'),
(2666, 31, 1125, 'حاسي بن عقبة', 'Hassi Ben Okba'),
(2667, 31, 1126, 'حاسي بونيف', 'Hassi Bounif'),
(2668, 31, 1127, 'حاسي مفسوخ', 'Hassi Mefsoukh'),
(2669, 31, 1128, 'مرسى الحجاج', 'Marsat El Hadjadj'),
(2670, 31, 1129, 'المرسى الكبير', 'Mers El Kebir'),
(2671, 31, 1130, 'مسرغين', 'Messerghin'),
(2672, 31, 1131, 'وهران', 'Oran'),
(2673, 31, 1132, 'وادي تليلات', 'Oued Tlelat'),
(2674, 31, 1133, 'سيدي بن يبقى', 'Sidi Ben Yebka'),
(2675, 31, 1134, 'سيدي الشحمي', 'Sidi Chami'),
(2676, 31, 1135, 'طفراوي', 'Tafraoui'),
(2677, 32, 1136, 'عين العراك', 'Ain El Orak'),
(2678, 32, 1137, 'اربوات', 'Arbaouat'),
(2679, 32, 1138, 'بوعلام', 'Boualem'),
(2680, 32, 1139, 'بوقطب', 'Bougtoub'),
(2681, 32, 1140, 'بوسمغون', 'Boussemghoun'),
(2682, 32, 1141, 'بريزينة', 'Brezina'),
(2683, 32, 1142, 'الشقيق', 'Cheguig'),
(2684, 32, 1143, 'شلالة', 'Chellala'),
(2685, 32, 1144, 'البيض', 'El Bayadh'),
(2686, 32, 1145, 'الأبيض سيدي الشيخ', 'Labiodh Sidi Cheikh'),
(2687, 32, 1146, 'البنود', 'El Bnoud'),
(2688, 32, 1147, 'الخيثر', 'El Kheiter'),
(2689, 32, 1148, 'المحرة', 'El Mehara'),
(2690, 32, 1149, 'الغاسول', 'Ghassoul'),
(2691, 32, 1150, 'الكاف الأحمر', 'Kef El Ahmar'),
(2692, 32, 1151, 'كراكدة', 'Krakda'),
(2693, 32, 1152, 'رقاصة', 'Rogassa'),
(2694, 32, 1153, 'سيدي عامر', 'Sidi Ameur'),
(2695, 32, 1154, 'سيدي سليمان', 'Sidi Slimane'),
(2696, 32, 1155, 'سيدي طيفور', 'Sidi Tiffour'),
(2697, 32, 1156, 'ستيتن', 'Stitten'),
(2698, 32, 1157, 'توسمولين', 'Tousmouline'),
(2699, 56, 1158, 'برج الحواس', 'Bordj El Haouass'),
(2700, 33, 1159, 'برج عمر إدريس', 'Bordj Omar Driss'),
(2701, 33, 1160, 'دبداب', 'Debdeb'),
(2702, 56, 1161, 'جانت', 'Djanet'),
(2703, 33, 1162, 'إيليزي', 'Illizi'),
(2704, 33, 1163, 'إن أمناس', 'In Amenas'),
(2705, 34, 1164, 'عين تاغروت', 'Ain Taghrout'),
(2706, 34, 1165, 'عين تسرة', 'Ain Tesra'),
(2707, 34, 1166, 'برج بوعريرج', 'B. B. Arreridj'),
(2708, 34, 1167, 'بليمور', 'Belimour'),
(2709, 34, 1168, 'بن داود', 'Ben Daoud'),
(2710, 34, 1169, 'بئر قاصد علي', 'Bir Kasdali'),
(2711, 34, 1170, 'برج الغدير', 'Bordj Ghedir'),
(2712, 34, 1171, 'برج زمورة', 'Bordj Zemmoura'),
(2713, 34, 1172, 'القلة', 'Colla'),
(2714, 34, 1173, 'جعافرة', 'Djaafra'),
(2715, 34, 1174, 'العش', 'El Euch'),
(2716, 34, 1175, 'الياشير', 'El Achir'),
(2717, 34, 1176, 'العناصر', 'El Annasseur'),
(2718, 34, 1177, 'الحمادية', 'Elhammadia'),
(2719, 34, 1178, 'الماين', 'El Main'),
(2720, 34, 1179, 'المهير', 'El M\'hir'),
(2721, 34, 1180, 'غيلاسة', 'Ghailasa'),
(2722, 34, 1181, 'حرازة', 'Haraza'),
(2723, 34, 1182, 'حسناوة', 'Hasnaoua'),
(2724, 34, 1183, 'خليل', 'Khelil'),
(2725, 34, 1184, 'القصور', 'Ksour'),
(2726, 34, 1185, 'المنصورة', 'Mansoura'),
(2727, 34, 1186, 'مجانة', 'Medjana'),
(2728, 34, 1187, 'أولاد أبراهم', 'Ouled Brahem'),
(2729, 34, 1188, 'أولاد دحمان', 'Ouled Dahmane'),
(2730, 34, 1189, 'أولاد سيدي ابراهيم', 'Ouled Sidi-Brahim'),
(2731, 34, 1190, 'الرابطة', 'Rabta'),
(2732, 34, 1191, 'رأس الوادي', 'Ras El Oued'),
(2733, 34, 1192, 'سيدي أمبارك', 'Sidi-Embarek'),
(2734, 34, 1193, 'تفرق', 'Tefreg'),
(2735, 34, 1194, 'تقلعيت', 'Taglait'),
(2736, 34, 1195, 'تسامرت', 'Tassamert'),
(2737, 34, 1196, 'ثنية النصر', 'Teniet En Nasr'),
(2738, 34, 1197, 'تيكستار', 'Tixter'),
(2739, 35, 1198, 'أعفير', 'Afir'),
(2740, 35, 1199, 'عمال', 'Ammal'),
(2741, 35, 1200, 'بغلية', 'Baghlia'),
(2742, 35, 1201, 'بن شود', 'Ben Choud'),
(2743, 35, 1202, 'بني عمران', 'Beni Amrane'),
(2744, 35, 1203, 'برج منايل', 'Bordj Menaiel'),
(2745, 35, 1204, 'بودواو', 'Boudouaou'),
(2746, 35, 1205, 'بودواو البحري', 'Boudouaou El Bahri'),
(2747, 35, 1206, 'بومرداس', 'Boumerdes'),
(2748, 35, 1207, 'بوزقزة قدارة', 'Bouzegza Keddara'),
(2749, 35, 1208, 'شعبة العامر', 'Chabet El Ameur'),
(2750, 35, 1209, 'قورصو', 'Corso'),
(2751, 35, 1210, 'دلس', 'Dellys'),
(2752, 35, 1211, 'جنات', 'Djinet'),
(2753, 35, 1212, 'الخروبة', 'El Kharrouba'),
(2754, 35, 1213, 'حمادي', 'Hammedi'),
(2755, 35, 1214, 'يسر', 'Isser'),
(2756, 35, 1215, 'خميس الخشنة', 'Khemis El Khechna'),
(2757, 35, 1216, 'الاربعطاش', 'Larbatache'),
(2758, 35, 1217, 'لقاطة', 'Leghata'),
(2759, 35, 1218, 'الناصرية', 'Naciria'),
(2760, 35, 1219, 'أولاد عيسى', 'Ouled Aissa'),
(2761, 35, 1220, 'أولاد هداج', 'Ouled Hedadj'),
(2762, 35, 1221, 'أولاد موسى', 'Ouled Moussa'),
(2763, 35, 1222, 'سي مصطفى', 'Si Mustapha'),
(2764, 35, 1223, 'سيدي داود', 'Sidi Daoud'),
(2765, 35, 1224, 'سوق الحد', 'Souk El Had'),
(2766, 35, 1225, 'تاورقة', 'Taourga'),
(2767, 35, 1226, 'الثنية', 'Thenia'),
(2768, 35, 1227, 'تيجلابين', 'Tidjelabine'),
(2769, 35, 1228, 'تيمزريت', 'Timezrit'),
(2770, 35, 1229, 'زموري', 'Zemmouri'),
(2771, 36, 1230, 'عين العسل', 'Ain El Assel'),
(2772, 36, 1231, 'عين الكرمة', 'Ain Kerma'),
(2773, 36, 1232, 'عصفور', 'Asfour'),
(2774, 36, 1233, 'بن مهيدي', 'Ben M Hidi'),
(2775, 36, 1234, 'بريحان', 'Berrihane'),
(2776, 36, 1235, 'البسباس', 'Besbes'),
(2777, 36, 1236, 'بوقوس', 'Bougous'),
(2778, 36, 1237, 'بوحجار', 'Bouhadjar');
INSERT INTO `t_01_04_03` (`F_00`, `F_04`, `F_01`, `F_02`, `F_03`) VALUES
(2779, 36, 1238, 'بوثلجة', 'Bouteldja'),
(2780, 36, 1239, 'شبيطة مختار', 'Chebaita Mokhtar'),
(2781, 36, 1240, 'الشافية', 'Chefia'),
(2782, 36, 1241, 'شحاني', 'Chihani'),
(2783, 36, 1242, 'الذرعـان', 'Drean'),
(2784, 36, 1243, 'الشط', 'Echatt'),
(2785, 36, 1244, 'العيون', 'El Aioun'),
(2786, 36, 1245, 'القالة', 'El Kala'),
(2787, 36, 1246, 'الطارف', 'El Tarf'),
(2788, 36, 1247, 'حمام بني صالح', 'Hammam Beni Salah'),
(2789, 36, 1248, 'بحيرة الطيور', 'Lac Des Oiseaux'),
(2790, 36, 1249, 'وادي الزيتون', 'Oued Zitoun'),
(2791, 36, 1250, 'رمل السوق', 'Raml Souk'),
(2792, 36, 1251, 'السوارخ', 'Souarekh'),
(2793, 36, 1252, 'زريزر', 'Zerizer'),
(2794, 36, 1253, 'الزيتونة', 'Zitouna'),
(2795, 37, 1254, 'أم العسل', 'Oum El Assel'),
(2796, 37, 1255, 'تندوف', 'Tindouf'),
(2797, 38, 1256, 'عماري', 'Ammari'),
(2798, 38, 1257, 'بني شعيب', 'Beni Chaib'),
(2799, 38, 1258, 'بني لحسن', 'Beni Lahcene'),
(2800, 38, 1259, 'برج بونعامة', 'Bordj Bounaama'),
(2801, 38, 1260, 'برج الأمير عبد القادر', 'Bordj El Emir Abdelkader'),
(2802, 38, 1261, 'بوقائد', 'Boucaid'),
(2803, 38, 1262, 'خميستي', 'Khemisti'),
(2804, 38, 1263, 'الأربعاء', 'Larbaa'),
(2805, 38, 1264, 'لرجام', 'Lardjem'),
(2806, 38, 1265, 'العيون', 'Layoune'),
(2807, 38, 1266, 'الأزهرية', 'Lazharia'),
(2808, 38, 1267, 'المعاصم', 'Maacem'),
(2809, 38, 1268, 'الملعب', 'Melaab'),
(2810, 38, 1269, 'أولاد بسام', 'Ouled Bessam'),
(2811, 38, 1270, 'سيدي عابد', 'Sidi Abed'),
(2812, 38, 1271, 'سيدي بوتوشنت', 'Sidi Boutouchent'),
(2813, 38, 1272, 'سيدي العنتري', 'Sidi Lantri'),
(2814, 38, 1273, 'سيدي سليمان', 'Sidi Slimane'),
(2815, 38, 1274, 'تملاحت', 'Tamellahet'),
(2816, 38, 1275, 'ثنية الاحد', 'Theniet El Had'),
(2817, 38, 1276, 'تيسمسيلت', 'Tissemsilt'),
(2818, 38, 1277, 'اليوسفية', 'Youssoufia'),
(2819, 39, 1278, 'البياضة', 'Bayadha'),
(2820, 39, 1279, 'بن  قشة', 'Ben Guecha'),
(2821, 39, 1280, 'الدبيلة', 'Debila'),
(2822, 57, 1281, 'جامعة', 'Djamaa'),
(2823, 39, 1282, 'دوار الماء', 'Douar El Maa'),
(2824, 39, 1283, 'العقلة', 'El Ogla'),
(2825, 57, 1284, 'المغير', 'El-M\'ghaier'),
(2826, 39, 1285, 'الوادي', 'El-Oued'),
(2827, 39, 1286, 'قمار', 'Guemar'),
(2828, 39, 1287, 'الحمراية', 'Hamraia'),
(2829, 39, 1288, 'حساني عبد الكريم', 'Hassani Abdelkrim'),
(2830, 39, 1289, 'حاسي خليفة', 'Hassi Khalifa'),
(2831, 39, 1290, 'كوينين', 'Kouinine'),
(2832, 39, 1291, 'المقرن', 'Magrane'),
(2833, 39, 1292, 'اميه وانسة', 'Mih Ouansa'),
(2834, 57, 1293, 'المرارة', 'M\'rara'),
(2835, 39, 1294, 'النخلة', 'Nakhla'),
(2836, 39, 1295, 'وادي العلندة', 'Oued El Alenda'),
(2837, 57, 1296, 'أم الطيور', 'Oum Touyour'),
(2838, 39, 1297, 'ورماس', 'Ourmes'),
(2839, 39, 1298, 'الرقيبة', 'Reguiba'),
(2840, 39, 1299, 'الرباح', 'Robbah'),
(2841, 57, 1300, 'سيدي عمران', 'Sidi Amrane'),
(2842, 39, 1301, 'سيدي عون', 'Sidi Aoun'),
(2843, 57, 1302, 'سيدي خليل', 'Sidi Khelil'),
(2844, 57, 1303, 'سطيل', 'Still'),
(2845, 39, 1304, 'تغزوت', 'Taghzout'),
(2846, 39, 1305, 'الطالب العربي', 'Taleb Larbi'),
(2847, 57, 1306, 'تندلة', 'Tenedla'),
(2848, 39, 1307, 'الطريفاوي', 'Trifaoui'),
(2849, 40, 1308, 'عين الطويلة', 'Ain Touila'),
(2850, 40, 1309, 'بابار', 'Babar'),
(2851, 40, 1310, 'بغاي', 'Baghai'),
(2852, 40, 1311, 'بوحمامة', 'Bouhmama'),
(2853, 40, 1312, 'ششار', 'Chechar'),
(2854, 40, 1313, 'شلية', 'Chelia'),
(2855, 40, 1314, 'جلال', 'Djellal'),
(2856, 40, 1315, 'الحامة', 'El Hamma'),
(2857, 40, 1316, 'المحمل', 'El Mahmal'),
(2858, 40, 1317, 'الولجة', 'El Oueldja'),
(2859, 40, 1318, 'انسيغة', 'Ensigha'),
(2860, 40, 1319, 'قايس', 'Kais'),
(2861, 40, 1320, 'خنشلة', 'Khenchela'),
(2862, 40, 1321, 'خيران', 'Khirane'),
(2863, 40, 1322, 'مصارة', 'M\'sara'),
(2864, 40, 1323, 'متوسة', 'M\'toussa'),
(2865, 40, 1324, 'أولاد رشاش', 'Ouled Rechache'),
(2866, 40, 1325, 'الرميلة', 'Remila'),
(2867, 40, 1326, 'طامزة', 'Tamza'),
(2868, 40, 1327, 'تاوزيانت', 'Taouzianat'),
(2869, 40, 1328, 'يابوس', 'Yabous'),
(2870, 41, 1329, 'عين سلطان', 'Ain Soltane'),
(2871, 41, 1330, 'عين الزانة', 'Ain Zana'),
(2872, 41, 1331, 'بئر بوحوش', 'Bir Bouhouche'),
(2873, 41, 1332, 'الدريعة', 'Drea'),
(2874, 41, 1333, 'الحدادة', 'Haddada'),
(2875, 41, 1334, 'الحنانشة', 'Hanencha'),
(2876, 41, 1335, 'الخضارة', 'Khedara'),
(2877, 41, 1336, 'خميسة', 'Khemissa'),
(2878, 41, 1337, 'المشروحة', 'Machroha'),
(2879, 41, 1338, 'مداوروش', 'M\'daourouche'),
(2880, 41, 1339, 'المراهنة', 'Merahna'),
(2881, 41, 1340, 'وادي الكبريت', 'Oued Kebrit'),
(2882, 41, 1341, 'أولاد إدريس', 'Ouled Driss'),
(2883, 41, 1342, 'أولاد مومن', 'Ouled Moumen'),
(2884, 41, 1343, 'أم العظايم', 'Oum El Adhaim'),
(2885, 41, 1344, 'ويلان', 'Ouillen'),
(2886, 41, 1345, 'الراقوبة', 'Ragouba'),
(2887, 41, 1346, 'سافل الويدان', 'Safel El Ouiden'),
(2888, 41, 1347, 'سدراتة', 'Sedrata'),
(2889, 41, 1348, 'سيدي فرج', 'Sidi Fredj'),
(2890, 41, 1349, 'سوق أهراس', 'Souk Ahras'),
(2891, 41, 1350, 'تاورة', 'Taoura'),
(2892, 41, 1351, 'ترقالت', 'Terraguelt'),
(2893, 41, 1352, 'تيفاش', 'Tiffech'),
(2894, 41, 1353, 'الزعرورية', 'Zaarouria'),
(2895, 41, 1354, 'الزوابي', 'Zouabi'),
(2896, 42, 1355, 'أغبال', 'Aghbal'),
(2897, 42, 1356, 'أحمر العين', 'Ahmer El Ain'),
(2898, 42, 1357, 'عين تاقورايت', 'Ain Tagourait'),
(2899, 42, 1358, 'الحطاطبة', 'Attatba'),
(2900, 42, 1359, 'بني ميلك', 'Beni Mileuk'),
(2901, 42, 1360, 'بوهارون', 'Bou Haroun'),
(2902, 42, 1361, 'بواسماعيل', 'Bou Ismail'),
(2903, 42, 1362, 'بورقيقة', 'Bourkika'),
(2904, 42, 1363, 'الشعيبة', 'Chaiba'),
(2905, 42, 1364, 'شرشال', 'Cherchell'),
(2906, 42, 1365, 'الداموس', 'Damous'),
(2907, 42, 1366, 'دواودة', 'Douaouda'),
(2908, 42, 1367, 'فوكة', 'Fouka'),
(2909, 42, 1368, 'قوراية', 'Gouraya'),
(2910, 42, 1369, 'حجوط', 'Hadjout'),
(2911, 42, 1370, 'حجرة النص', 'Hadjret Ennous'),
(2912, 42, 1371, 'خميستي', 'Khemisti'),
(2913, 42, 1372, 'القليعة', 'Kolea'),
(2914, 42, 1373, 'الأرهاط', 'Larhat'),
(2915, 42, 1374, 'مناصر', 'Menaceur'),
(2916, 42, 1375, 'مراد', 'Merad'),
(2917, 42, 1376, 'مسلمون', 'Messelmoun'),
(2918, 42, 1377, 'الناظور', 'Nador'),
(2919, 42, 1378, 'سيدي غيلاس', 'Sidi Ghiles'),
(2920, 42, 1379, 'سيدي راشد', 'Sidi Rached'),
(2921, 42, 1380, 'سيدي سميان', 'Sidi Semiane'),
(2922, 42, 1381, 'سيدي عامر', 'Sidi-Amar'),
(2923, 42, 1382, 'تيبازة', 'Tipaza'),
(2924, 43, 1383, 'أحمد راشدي', 'Ahmed Rachedi'),
(2925, 43, 1384, ' عين البيضاء أحريش', 'Ain Beida Harriche'),
(2926, 43, 1385, 'عين الملوك', 'Ain Mellouk'),
(2927, 43, 1386, 'عين التين', 'Ain Tine'),
(2928, 43, 1387, 'اعميرة اراس', 'Amira Arres'),
(2929, 43, 1388, 'بن يحي عبد الرحمن', 'Benyahia Abderrahmane'),
(2930, 43, 1389, 'بوحاتم', 'Bouhatem'),
(2931, 43, 1390, 'شلغوم العيد', 'Chelghoum Laid'),
(2932, 43, 1391, 'الشيقارة', 'Chigara'),
(2933, 43, 1392, 'دراحي بوصلاح', 'Derrahi Bousselah'),
(2934, 43, 1393, 'العياضي برباس', 'El Ayadi Barbes'),
(2935, 43, 1394, 'مشيرة', 'El Mechira'),
(2936, 43, 1395, 'فرجيوة', 'Ferdjioua'),
(2937, 43, 1396, 'القرارم قوقة', 'Grarem Gouga'),
(2938, 43, 1397, 'حمالة', 'Hamala'),
(2939, 43, 1398, 'ميلة', 'Mila'),
(2940, 43, 1399, 'مينار زارزة', 'Minar Zarza'),
(2941, 43, 1400, 'وادي العثمانية', 'Oued Athmenia'),
(2942, 43, 1401, 'وادي النجاء', 'Oued Endja'),
(2943, 43, 1402, 'وادي سقان', 'Oued Seguen'),
(2944, 43, 1403, 'أولاد اخلوف', 'Ouled Khalouf'),
(2945, 43, 1404, 'الرواشد', 'Rouached'),
(2946, 43, 1405, 'سيدي خليفة', 'Sidi Khelifa'),
(2947, 43, 1406, 'سيدي مروان', 'Sidi Merouane'),
(2948, 43, 1407, 'تاجنانت', 'Tadjenanet'),
(2949, 43, 1408, 'تسدان حدادة', 'Tassadane Haddada'),
(2950, 43, 1409, 'التلاغمة', 'Teleghma'),
(2951, 43, 1410, 'ترعي باينان', 'Terrai Bainen'),
(2952, 43, 1411, 'تسالة لمطاعي', 'Tassala Lematai'),
(2953, 43, 1412, 'تيبرقنت', 'Tiberguent'),
(2954, 43, 1413, 'يحي بني قشة', 'Yahia Beniguecha'),
(2955, 43, 1414, 'زغاية', 'Zeghaia'),
(2956, 44, 1415, 'عين البنيان', 'Ain-Benian'),
(2957, 44, 1416, 'عين بويحيى', 'Ain-Bouyahia'),
(2958, 44, 1417, 'عين الدفلى', 'Ain-Defla'),
(2959, 44, 1418, 'عين الاشياخ', 'Ain-Lechiakh'),
(2960, 44, 1419, 'عين السلطان', 'Ain-Soltane'),
(2961, 44, 1420, 'عين التركي', 'Ain-Torki'),
(2962, 44, 1421, 'عريب', 'Arib'),
(2963, 44, 1422, 'بربوش', 'Birbouche'),
(2964, 44, 1423, 'بطحية', 'Bathia'),
(2965, 44, 1424, 'بلعاص', 'Belaas'),
(2966, 44, 1425, 'بن علال', 'Ben Allal'),
(2967, 44, 1426, 'بئر ولد خليفة', 'Bir-Ould-Khelifa'),
(2968, 44, 1427, 'برج الأمير خالد', 'Bordj-Emir-Khaled'),
(2969, 44, 1428, 'بومدفع', 'Boumedfaa'),
(2970, 44, 1429, 'بوراشد', 'Bourached'),
(2971, 44, 1430, 'جليدة', 'Djelida'),
(2972, 44, 1431, 'جمعة أولاد الشيخ', 'Djemaa Ouled Cheikh'),
(2973, 44, 1432, 'جندل', 'Djendel'),
(2974, 44, 1433, 'العبادية', 'El-Abadia'),
(2975, 44, 1434, 'العامرة', 'El-Amra'),
(2976, 44, 1435, 'العطاف', 'El-Attaf'),
(2977, 44, 1436, 'الماين', 'El-Maine'),
(2978, 44, 1437, 'حمام ريغة', 'Hammam-Righa'),
(2979, 44, 1438, 'الحسانية', 'Hassania'),
(2980, 44, 1439, 'الحسينية', 'Hoceinia'),
(2981, 44, 1440, 'خميس مليانة', 'Khemis-Miliana'),
(2982, 44, 1441, 'المخاطرية', 'Mekhatria'),
(2983, 44, 1442, 'مليانة', 'Miliana'),
(2984, 44, 1443, 'وادي الشرفاء', 'Oued Chorfa'),
(2985, 44, 1444, 'واد الجمعة', 'Oued Djemaa'),
(2986, 44, 1445, 'الروينة', 'Rouina'),
(2987, 44, 1446, 'سيدي الأخضر', 'Sidi-Lakhdar'),
(2988, 44, 1447, 'تاشتة زقاغة', 'Tacheta Zegagha'),
(2989, 44, 1448, 'طارق بن زياد', 'Tarik-Ibn-Ziad'),
(2990, 44, 1449, 'تبركانين', 'Tiberkanine'),
(2991, 44, 1450, 'زدين', 'Zeddine'),
(2992, 45, 1451, 'عين بن خليل', 'Ain Ben Khelil'),
(2993, 45, 1452, 'عين الصفراء', 'Ain Sefra'),
(2994, 45, 1453, 'عسلة', 'Asla'),
(2995, 45, 1454, 'جنين بورزق', 'Djenienne Bourezg'),
(2996, 45, 1455, 'البيوض', 'El Biodh'),
(2997, 45, 1456, 'القصدير', 'Kasdir'),
(2998, 45, 1457, 'مكمن بن عمار', 'Makmen Ben Amar'),
(2999, 45, 1458, 'المشرية', 'Mecheria'),
(3000, 45, 1459, 'مغرار', 'Moghrar'),
(3001, 45, 1460, 'النعامة', 'Naama'),
(3002, 45, 1461, 'سفيسيفة', 'Sfissifa'),
(3003, 45, 1462, 'تيوت', 'Tiout'),
(3004, 46, 1463, 'أغلال', 'Aghlal'),
(3005, 46, 1464, 'عين الأربعاء', 'Ain El Arbaa'),
(3006, 46, 1465, 'عين الكيحل', 'Ain Kihal'),
(3007, 46, 1466, 'عين تموشنت', 'Ain Temouchent'),
(3008, 46, 1467, 'عين الطلبة', 'Ain Tolba'),
(3009, 46, 1468, 'عقب الليل', 'Aoubellil'),
(3010, 46, 1469, 'بني صاف', 'Beni Saf'),
(3011, 46, 1470, 'بوزجار', 'Bouzedjar'),
(3012, 46, 1471, 'شعبة اللحم', 'Chaabat El Ham'),
(3013, 46, 1472, 'شنتوف', 'Chentouf'),
(3014, 46, 1473, 'العامرية', 'El Amria'),
(3015, 46, 1474, 'المالح', 'El Maleh'),
(3016, 46, 1475, 'المساعيد', 'El Messaid'),
(3017, 46, 1476, 'الأمير عبد القادر', 'Emir Abdelkader'),
(3018, 46, 1477, 'حمام بوحجر', 'Hammam Bou Hadjar'),
(3019, 46, 1478, 'الحساسنة', 'Hassasna'),
(3020, 46, 1479, 'حاسي الغلة', 'Hassi El Ghella'),
(3021, 46, 1480, 'وادي برقش', 'Oued Berkeche'),
(3022, 46, 1481, 'وادي الصباح', 'Oued Sebbah'),
(3023, 46, 1482, 'أولاد بوجمعة', 'Ouled Boudjemaa'),
(3024, 46, 1483, 'أولاد الكيحل', 'Ouled Kihal'),
(3025, 46, 1484, 'ولهاصة الغرابة', 'Oulhaca El Gheraba'),
(3026, 46, 1485, 'سيدي بن عدة', 'Sidi Ben Adda'),
(3027, 46, 1486, 'سيدي بومدين', 'Sidi Boumediene'),
(3028, 46, 1487, 'سيدي ورياش', 'Sidi Ouriache'),
(3029, 46, 1488, 'سيدي صافي', 'Sidi Safi'),
(3030, 46, 1489, 'تامزورة', 'Tamzoura'),
(3031, 46, 1490, 'تارقة', 'Terga'),
(3032, 47, 1491, 'بريان', 'Berriane'),
(3033, 47, 1492, 'بونورة', 'Bounoura'),
(3034, 47, 1493, 'ضاية بن ضحوة', 'Dhayet Bendhahoua'),
(3035, 47, 1494, 'العطف', 'El Atteuf'),
(3036, 58, 1495, 'المنيعة', 'El Meniaa'),
(3037, 47, 1496, 'غرداية', 'Ghardaia'),
(3038, 47, 1497, 'القرارة', 'El Guerrara'),
(3039, 58, 1498, 'حاسي الفحل', 'Hassi Fehal'),
(3040, 58, 1499, 'حاسي القارة', 'Hassi Gara'),
(3041, 47, 1500, 'المنصورة', 'Mansoura'),
(3042, 47, 1501, 'متليلي', 'Metlili'),
(3043, 47, 1502, 'سبسب', 'Sebseb'),
(3044, 47, 1503, 'زلفانة', 'Zelfana'),
(3045, 48, 1504, 'عين الرحمة', 'Ain Rahma'),
(3046, 48, 1505, 'عين طارق', 'Ain-Tarek'),
(3047, 48, 1506, 'عمي موسى', 'Ammi Moussa'),
(3048, 48, 1507, 'بلعسل بوزقزة', 'Belaassel Bouzagza'),
(3049, 48, 1508, 'بن داود', 'Bendaoud'),
(3050, 48, 1509, 'بني درقن', 'Beni Dergoun'),
(3051, 48, 1510, 'بني زنطيس', 'Beni Zentis'),
(3052, 48, 1511, 'دار بن عبد الله', 'Dar Ben Abdelah'),
(3053, 48, 1512, 'جديوية', 'Djidiouia'),
(3054, 48, 1513, 'الحاسي', 'El Hassi'),
(3055, 48, 1514, 'الحمادنة', 'El H\'madna'),
(3056, 48, 1515, 'الولجة', 'El Ouldja'),
(3057, 48, 1516, 'القطار', 'El-Guettar'),
(3058, 48, 1517, 'المطمر', 'El-Matmar'),
(3059, 48, 1518, 'حد الشكالة', 'Had Echkalla'),
(3060, 48, 1519, 'حمري', 'Hamri'),
(3061, 48, 1520, 'القلعة', 'Kalaa'),
(3062, 48, 1521, 'لحلاف', 'Lahlef'),
(3063, 48, 1522, 'مازونة', 'Mazouna'),
(3064, 48, 1523, 'مديونة', 'Mediouna'),
(3065, 48, 1524, 'منداس', 'Mendes'),
(3066, 48, 1525, 'مرجة سيدي عابد', 'Merdja Sidi Abed'),
(3067, 48, 1526, 'واريزان', 'Ouarizane'),
(3068, 48, 1527, 'وادي الجمعة', 'Oued El Djemaa'),
(3069, 48, 1528, 'وادي السلام', 'Oued Essalem'),
(3070, 48, 1529, 'وادي رهيو', 'Oued-Rhiou'),
(3071, 48, 1530, 'أولاد يعيش', 'Ouled Aiche'),
(3072, 48, 1531, 'أولاد سيدي الميهوب', 'Ouled Sidi Mihoub'),
(3073, 48, 1532, 'الرمكة', 'Ramka'),
(3074, 48, 1533, 'غليزان', 'Relizane'),
(3075, 48, 1534, 'سيدي  خطاب', 'Sidi Khettab'),
(3076, 48, 1535, 'سيدي لزرق', 'Sidi Lazreg'),
(3077, 48, 1536, 'سيدي أمحمد بن علي', 'Sidi M\'hamed Benali'),
(3078, 48, 1537, 'سيدي امحمد بن عودة', 'Sidi M\'hamed Benaouda'),
(3079, 48, 1538, 'سيدي سعادة', 'Sidi Saada'),
(3080, 48, 1539, 'سوق الحد', 'Souk El Had'),
(3081, 48, 1540, 'يلل', 'Yellel'),
(3082, 48, 1541, 'زمورة', 'Zemmoura');

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
  `F_07` bigint(20) NOT NULL,
  `F_04` varchar(10) DEFAULT NULL,
  `F_05` varchar(10) DEFAULT NULL,
  `F_06` varchar(10) NOT NULL,
  `F_01` varchar(50) DEFAULT NULL,
  `F_02` varchar(50) DEFAULT NULL,
  `F_03` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_01_04_05`
--

INSERT INTO `t_01_04_05` (`F_00`, `F_07`, `F_04`, `F_05`, `F_06`, `F_01`, `F_02`, `F_03`) VALUES
(1, 1, NULL, NULL, '', 'General', 'فريق أول', 'Général d\'Armée'),
(2, 1, NULL, NULL, '', 'Lieutenant General', 'فريق', 'Général de Corps d\'Armée'),
(3, 1, NULL, NULL, '', 'Major General', 'لواء', 'Général Major'),
(4, 1, NULL, NULL, '', 'Brigadier General', 'عميد', 'Général'),
(5, 2, NULL, NULL, '', 'Colonel', 'عقيد', 'Colonel'),
(6, 2, NULL, NULL, '', 'Lieutenant Colonel', 'مقدم', 'Lieutenant Colonel'),
(7, 2, NULL, NULL, '', 'Major', 'رائد', 'Commandant'),
(8, 3, NULL, NULL, '', 'Captain', 'نقيب', 'Capitaine'),
(9, 3, NULL, NULL, '', 'Lieutenant', 'ملازم أول', 'Lieutenant'),
(10, 3, NULL, NULL, '', 'Second Lieutenant', 'ملازم', 'Sous Lieutenant'),
(11, 3, NULL, NULL, '', 'Aspirant', 'مرشح', 'Aspirant'),
(12, 4, NULL, NULL, '', 'Major Sergeant', 'مساعد رئيسي', 'Adjudant Major'),
(13, 4, NULL, NULL, '', 'Master Sergeant', 'مساعد أول', 'Adjudant Chef'),
(14, 4, NULL, NULL, '', 'First Class Sergeant', 'مساعد', 'Adjudant'),
(15, 4, NULL, NULL, '', 'Staff Sergeant', 'رقيب أول', 'Sergent Chef'),
(16, 4, NULL, NULL, '', 'Sergeant', 'رقيب', 'Sergent'),
(17, 5, NULL, NULL, '', 'Corporal', 'عريف أول', 'Caporal Chef'),
(18, 5, NULL, NULL, '', 'Lance Corporal', 'عريف', 'Caporal'),
(19, 5, NULL, NULL, '', 'Private Soldier', 'جندي', 'Djoundi'),
(20, 6, NULL, NULL, '', 'Assimilated Civilian Personnel', 'مستخدم مدني شبيه', 'Personnel Civile Assimilé'),
(21, 6, NULL, NULL, '', 'Contractual Civilian Personnel', 'مستخدم مدني متعاقد', 'Personnel Civile Contractuel');

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
  `F_07` bigint(20) NOT NULL,
  `F_08` bigint(20) DEFAULT NULL,
  `F_04` varchar(20) DEFAULT NULL,
  `F_05` varchar(20) DEFAULT NULL,
  `F_06` varchar(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_01_04_08`
--

CREATE TABLE `t_01_04_08` (
  `F_00` bigint(20) NOT NULL,
  `F_04` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_01_04_09`
--

CREATE TABLE `t_01_04_09` (
  `F_00` bigint(20) NOT NULL,
  `F_05` datetime(6) DEFAULT NULL,
  `F_08` bigint(20) DEFAULT NULL,
  `F_09` bigint(20) DEFAULT NULL,
  `F_10` bigint(20) DEFAULT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) DEFAULT NULL,
  `F_06` varchar(255) DEFAULT NULL,
  `F_07` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_01_04_10`
--

CREATE TABLE `t_01_04_10` (
  `F_00` bigint(20) NOT NULL,
  `F_02` datetime(6) DEFAULT NULL,
  `F_03` bigint(20) NOT NULL,
  `F_04` bigint(20) NOT NULL,
  `F_05` bigint(20) DEFAULT NULL,
  `F_01` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_01_01`
--

CREATE TABLE `t_02_01_01` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(50) NOT NULL,
  `F_05` varchar(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_02` varchar(50) NOT NULL,
  `F_03` varchar(50) NOT NULL,
  `F_06` varchar(20) NOT NULL,
  `F_07` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_02_01_01`
--

INSERT INTO `t_02_01_01` (`F_00`, `F_04`, `F_05`, `F_01`, `F_02`, `F_03`, `F_06`, `F_07`) VALUES
(1, 'Dollar des États-Unis', 'US$', 'USD', 'دولار أمريكي', 'United States Dollar', 'USD', '$'),
(2, 'Euro', '€', 'EUR', 'يورو', 'Euro', 'EUR', '€'),
(3, 'Livre Sterling', '£', 'GBP', 'جنيه إسترليني', 'British Pound Sterling', 'GBP', '£'),
(4, 'Yen Japonais', '¥', 'JPY', 'ين ياباني', 'Japanese Yen', 'JPY', '¥'),
(5, 'Franc Suisse', 'CHF', 'CHF', 'فرنك سويسري', 'Swiss Franc', 'CHF', 'Fr'),
(6, 'Dollar Canadien', 'CA$', 'CAD', 'دولار كندي', 'Canadian Dollar', 'CAD', '$'),
(7, 'Dollar Australien', 'A$', 'AUD', 'دولار أسترالي', 'Australian Dollar', 'AUD', '$'),
(8, 'Yuan Chinois', '¥', 'CNY', 'يوان صيني', 'Chinese Yuan', 'CNY', '¥'),
(9, 'Dirham des Émirats', 'د.إ', 'AED', 'درهم إماراتي', 'UAE Dirham', 'AED', 'Dh'),
(10, 'Dinar de Bahreïn', 'د.ب', 'BHD', 'دينار بحريني', 'Bahraini Dinar', 'BHD', 'BD'),
(11, 'Dinar Algérien', 'د.ج', 'DZD', 'دينار جزائري', 'Algerian Dinar', 'DZD', 'DA'),
(12, 'Livre Égyptienne', 'ج.م', 'EGP', 'جنيه مصري', 'Egyptian Pound', 'EGP', 'LE'),
(13, 'Dinar Irakien', 'د.ع', 'IQD', 'دينار عراقي', 'Iraqi Dinar', 'IQD', 'ID'),
(14, 'Dinar Jordanien', 'د.ا', 'JOD', 'دينار أردني', 'Jordanian Dinar', 'JOD', 'JD'),
(15, 'Dinar Koweïtien', 'د.ك', 'KWD', 'دينار كويتي', 'Kuwaiti Dinar', 'KWD', 'KD'),
(16, 'Livre Libanaise', 'ل.ل', 'LBP', 'ليرة لبنانية', 'Lebanese Pound', 'LBP', 'LL'),
(17, 'Dinar Libyen', 'د.ل', 'LYD', 'دينار ليبي', 'Libyan Dinar', 'LYD', 'LD'),
(18, 'Dirham Marocain', 'د.م', 'MAD', 'درهم مغربي', 'Moroccan Dirham', 'MAD', 'DH'),
(19, 'Ouguiya Mauritanien', 'أ.م', 'MRU', 'أوقية موريتانية', 'Mauritanian Ouguiya', 'MRU', 'UM'),
(20, 'Rial Omanais', 'ر.ع', 'OMR', 'ريال عماني', 'Omani Rial', 'OMR', 'OR'),
(21, 'Riyal Qatarien', 'ر.ق', 'QAR', 'ريال قطري', 'Qatari Riyal', 'QAR', 'QR'),
(22, 'Riyal Saoudien', 'ر.س', 'SAR', 'ريال سعودي', 'Saudi Riyal', 'SAR', 'SR'),
(23, 'Livre Soudanaise', 'ج.س', 'SDG', 'جنيه سوداني', 'Sudanese Pound', 'SDG', 'SD'),
(24, 'Shilling Somalien', 'ش.ص', 'SOS', 'شلن صومالي', 'Somali Shilling', 'SOS', 'So'),
(25, 'Livre Syrienne', 'ل.س', 'SYP', 'ليرة سورية', 'Syrian Pound', 'SYP', 'SP'),
(26, 'Dinar Tunisien', 'د.ت', 'TND', 'دينار تونسي', 'Tunisian Dinar', 'TND', 'TD'),
(27, 'Rial Yéménite', 'ر.ي', 'YER', 'ريال يمني', 'Yemeni Rial', 'YER', 'YR'),
(28, 'Afghani Afghan', '؋', 'AFN', 'أفغاني', 'Afghan Afghani', 'AFN', 'Af'),
(29, 'Lek Albanais', 'L', 'ALL', 'ليك ألباني', 'Albanian Lek', 'ALL', 'Lek'),
(30, 'Dram Arménien', '֏', 'AMD', 'درام أرميني', 'Armenian Dram', 'AMD', 'AMD'),
(31, 'Florin Antillais', 'ƒ', 'ANG', 'غيلدر أنتيلي', 'Netherlands Antillean Guilder', 'ANG', 'NAf'),
(32, 'Kwanza Angolais', 'Kz', 'AOA', 'كوانزا أنغولي', 'Angolan Kwanza', 'AOA', 'Kz'),
(33, 'Peso Argentin', '$', 'ARS', 'بيزو أرجنتيني', 'Argentine Peso', 'ARS', '$'),
(34, 'Manat Azerbaïdjanais', '₼', 'AZN', 'مانات أذربيجاني', 'Azerbaijani Manat', 'AZN', 'AZN'),
(35, 'Mark Bosniaque', 'KM', 'BAM', 'مارك بوسني', 'Bosnia-Herzegovina Mark', 'BAM', 'KM'),
(36, 'Dollar Barbadien', '$', 'BBD', 'دولار بربادوسي', 'Barbadian Dollar', 'BBD', '$'),
(37, 'Taka Bangladais', '৳', 'BDT', 'تاكا بنغلاديشي', 'Bangladeshi Taka', 'BDT', '৳'),
(38, 'Lev Bulgare', 'лв', 'BGN', 'ليف بلغاري', 'Bulgarian Lev', 'BGN', 'BGN'),
(39, 'Franc Burundais', 'Fr', 'BIF', 'فرنك بوروندي', 'Burundian Franc', 'BIF', 'FBu'),
(40, 'Dollar Bermudien', '$', 'BMD', 'دولار برمودي', 'Bermudian Dollar', 'BMD', '$'),
(41, 'Dollar de Brunei', '$', 'BND', 'دولار بروناي', 'Brunei Dollar', 'BND', '$'),
(42, 'Boliviano', 'Bs.', 'BOB', 'بوليفيانو', 'Bolivian Boliviano', 'BOB', 'Bs'),
(43, 'Réal Brésilien', 'R$', 'BRL', 'ريال برازيلي', 'Brazilian Real', 'BRL', 'R$'),
(44, 'Dollar Bahaméen', '$', 'BSD', 'دولار باهامي', 'Bahamian Dollar', 'BSD', '$'),
(45, 'Ngultrum Bhoutanais', 'Nu.', 'BTN', 'نغولترم بوتاني', 'Bhutanese Ngultrum', 'BTN', 'Nu'),
(46, 'Pula Botswanais', 'P', 'BWP', 'بولا بوتسواني', 'Botswanan Pula', 'BWP', 'P'),
(47, 'Rouble Biélorusse', 'Br', 'BYN', 'روبل بيلاروسي', 'Belarusian Ruble', 'BYN', 'Br'),
(48, 'Dollar Bélizien', '$', 'BZD', 'دولار بليزي', 'Belize Dollar', 'BZD', 'BZ$'),
(49, 'Franc Congolais', 'Fr', 'CDF', 'فرنك كونغولي', 'Congolese Franc', 'CDF', 'FC'),
(50, 'Peso Chilien', '$', 'CLP', 'بيزو تشيلي', 'Chilean Peso', 'CLP', '$'),
(51, 'Peso Colombien', '$', 'COP', 'بيزو كولومبي', 'Colombian Peso', 'COP', '$'),
(52, 'Colon Costaricain', '₡', 'CRC', 'كولون كوستاريكي', 'Costa Rican Colón', 'CRC', '₡'),
(53, 'Peso Cubain', '$', 'CUP', 'بيزو كوبي', 'Cuban Peso', 'CUP', '$'),
(54, 'Escudo Cap-Verdien', '$', 'CVE', 'إسكودو الرأس الأخضر', 'Cape Verdean Escudo', 'CVE', 'Esc'),
(55, 'Couronne Tchèque', 'Kč', 'CZK', 'كورونا تشيكية', 'Czech Koruna', 'CZK', 'Kč'),
(56, 'Franc Djiboutien', 'Fr', 'DJF', 'فرنك جيبوتي', 'Djiboutian Franc', 'DJF', 'Fdj'),
(57, 'Couronne Danoise', 'kr', 'DKK', 'كرونة دنماركية', 'Danish Krone', 'DKK', 'kr'),
(58, 'Peso Dominicain', '$', 'DOP', 'بيزو دومينيكاني', 'Dominican Peso', 'DOP', 'RD$'),
(59, 'Birr Éthiopien', 'Br', 'ETB', 'بير إثيوبي', 'Ethiopian Birr', 'ETB', 'Br'),
(60, 'Dollar Fidjien', '$', 'FJD', 'دولار فيجي', 'Fijian Dollar', 'FJD', '$'),
(61, 'Lari Géorgien', '₾', 'GEL', 'لاري جورجي', 'Georgian Lari', 'GEL', 'GEL'),
(62, 'Cedi Ghanéen', '₵', 'GHS', 'سيدي غاناوي', 'Ghanaian Cedi', 'GHS', 'GH₵'),
(63, 'Dalasi Gambien', 'D', 'GMD', 'دالاسي غامبي', 'Gambian Dalasi', 'GMD', 'D'),
(64, 'Franc Guinéen', 'Fr', 'GNF', 'فرنك غيني', 'Guinean Franc', 'GNF', 'FG'),
(65, 'Quetzal Guatémaltèque', 'Q', 'GTQ', 'كتزال غواتيمالي', 'Guatemalan Quetzal', 'GTQ', 'Q'),
(66, 'Dollar Guyanien', '$', 'GYD', 'دولار غياني', 'Guyanese Dollar', 'GYD', '$'),
(67, 'Dollar de Hong Kong', 'HK$', 'HKD', 'دولار هونغ كونغ', 'Hong Kong Dollar', 'HKD', 'HK$'),
(68, 'Lempira Hondurien', 'L', 'HNL', 'ليمبيرا هندوراسي', 'Honduran Lempira', 'HNL', 'L'),
(69, 'Kuna Croate', 'kn', 'HRK', 'كونا كرواتية', 'Croatian Kuna', 'HRK', 'kn'),
(70, 'Gourde Haïtienne', 'G', 'HTG', 'غورد هايتي', 'Haitian Gourde', 'HTG', 'G'),
(71, 'Forint Hongrois', 'Ft', 'HUF', 'فورنت هنغاري', 'Hungarian Forint', 'HUF', 'Ft'),
(72, 'Roupie Indonésienne', 'Rp', 'IDR', 'روبية إندونيسية', 'Indonesian Rupiah', 'IDR', 'Rp'),
(73, 'Shekel Israélien', '₪', 'ILS', 'شيكل إسرائيلي', 'Israeli New Shekel', 'ILS', '₪'),
(74, 'Roupie Indienne', '₹', 'INR', 'روبية هندية', 'Indian Rupee', 'INR', '₹'),
(75, 'Rial Iranien', '﷼', 'IRR', 'ريال إيراني', 'Iranian Rial', 'IRR', 'IR'),
(76, 'Couronne Islandaise', 'kr', 'ISK', 'كرونا آيسلندية', 'Icelandic Króna', 'ISK', 'kr'),
(77, 'Dollar Jamaïcain', '$', 'JMD', 'دولار جامايكي', 'Jamaican Dollar', 'JMD', 'J$'),
(78, 'Shilling Kenyan', 'Sh', 'KES', 'شلن كيني', 'Kenyan Shilling', 'KES', 'KSh'),
(79, 'Som Kirghize', 'с', 'KGS', 'سوم قيرغيزستاني', 'Kyrgyzstani Som', 'KGS', 'KGS'),
(80, 'Riel Cambodgien', '៛', 'KHR', 'ريال كمبودي', 'Cambodian Riel', 'KHR', '៛'),
(81, 'Franc Comorien', 'Fr', 'KMF', 'فرنك قمري', 'Comorian Franc', 'KMF', 'CF'),
(82, 'Won Sud-Coréen', '₩', 'KRW', 'وون كوري', 'South Korean Won', 'KRW', '₩'),
(83, 'Tenge Kazakh', '₸', 'KZT', 'تنغي كازاخستاني', 'Kazakhstani Tenge', 'KZT', '₸'),
(84, 'Kip Laotien', '₭', 'LAK', 'كيب لاوسي', 'Lao Kip', 'LAK', '₭'),
(85, 'Roupie Sri-Lankaise', 'Rs', 'LKR', 'روبية سريلانكية', 'Sri Lankan Rupee', 'LKR', 'Rs'),
(86, 'Dollar Libérien', '$', 'LRD', 'دولار ليبيري', 'Liberian Dollar', 'LRD', 'L$'),
(87, 'Loti du Lesotho', 'L', 'LSL', 'لوتي ليسوتو', 'Lesotho Loti', 'LSL', 'L'),
(88, 'Ariary Malgache', 'Ar', 'MGA', 'أرياري مدغشقري', 'Malagasy Ariary', 'MGA', 'Ar'),
(89, 'Denar Macédonien', 'ден', 'MKD', 'دينار مقدوني', 'Macedonian Denar', 'MKD', 'MKD'),
(90, 'Kyat Birman', 'K', 'MMK', 'كيات ميانماري', 'Myanmar Kyat', 'MMK', 'K'),
(91, 'Tugrik Mongol', '₮', 'MNT', 'توغريك منغولي', 'Mongolian Tögrög', 'MNT', '₮'),
(92, 'Pataca Macanais', 'P', 'MOP', 'باتاكا ماكاوية', 'Macanese Pataca', 'MOP', 'MOP'),
(93, 'Roupie Mauricienne', '₨', 'MUR', 'روبية موريشيوسية', 'Mauritian Rupee', 'MUR', '₨'),
(94, 'Rufiyaa Maldivien', 'Rf', 'MVR', 'روفيه مالديفية', 'Maldivian Rufiyaa', 'MVR', 'Rf'),
(95, 'Kwacha Malawien', 'MK', 'MWK', 'كواشا مالاوية', 'Malawian Kwacha', 'MWK', 'MK'),
(96, 'Peso Mexicain', '$', 'MXN', 'بيزو مكسيكي', 'Mexican Peso', 'MXN', '$'),
(97, 'Ringgit Malaisien', 'RM', 'MYR', 'رينغيت ماليزي', 'Malaysian Ringgit', 'MYR', 'RM'),
(98, 'Metical Mozambicain', 'MT', 'MZN', 'متكال موزمبيقي', 'Mozambican Metical', 'MZN', 'MT'),
(99, 'Dollar Namibien', '$', 'NAD', 'دولار ناميبي', 'Namibian Dollar', 'NAD', 'N$'),
(100, 'Naira Nigérian', '₦', 'NGN', 'نايرا نيجيري', 'Nigerian Naira', 'NGN', '₦'),
(101, 'Cordoba Nicaraguayen', 'C$', 'NIO', 'كوردوبا نيكاراغوي', 'Nicaraguan Córdoba', 'NIO', 'C$'),
(102, 'Couronne Norvégienne', 'kr', 'NOK', 'كرونة نرويجية', 'Norwegian Krone', 'NOK', 'kr'),
(103, 'Roupie Népalaise', '₨', 'NPR', 'روبية نيبالية', 'Nepalese Rupee', 'NPR', '₨'),
(104, 'Dollar Néo-Zélandais', '$', 'NZD', 'دولار نيوزيلندي', 'New Zealand Dollar', 'NZD', 'NZ$'),
(105, 'Balboa Panaméen', 'B/.', 'PAB', 'بالبوا بنمي', 'Panamanian Balboa', 'PAB', 'B/.'),
(106, 'Sol Péruvien', 'S/', 'PEN', 'سول بيروفي', 'Peruvian Sol', 'PEN', 'S/'),
(107, 'Kina Papou', 'K', 'PGK', 'كينا بابوا غينيا الجديدة', 'Papua New Guinean Kina', 'PGK', 'K'),
(108, 'Peso Philippin', '₱', 'PHP', 'بيزو فلبيني', 'Philippine Peso', 'PHP', '₱'),
(109, 'Roupie Pakistanaise', '₨', 'PKR', 'روبية باكستانية', 'Pakistani Rupee', 'PKR', '₨'),
(110, 'Zloty Polonais', 'zł', 'PLN', 'زلوتي بولندي', 'Polish Złoty', 'PLN', 'zł'),
(111, 'Guarani Paraguayen', '₲', 'PYG', 'غواراني باراغواي', 'Paraguayan Guaraní', 'PYG', '₲'),
(112, 'Leu Roumain', 'lei', 'RON', 'ليو روماني', 'Romanian Leu', 'RON', 'lei'),
(113, 'Dinar Serbe', 'дин', 'RSD', 'دينار صربي', 'Serbian Dinar', 'RSD', 'RSD'),
(114, 'Rouble Russe', '₽', 'RUB', 'روبل روسي', 'Russian Ruble', 'RUB', '₽'),
(115, 'Franc Rwandais', 'Fr', 'RWF', 'فرنك رواندي', 'Rwandan Franc', 'RWF', 'RF'),
(116, 'Roupie Seychelloise', '₨', 'SCR', 'روبية سيشلية', 'Seychellois Rupee', 'SCR', '₨'),
(117, 'Couronne Suédoise', 'kr', 'SEK', 'كرونة سويدية', 'Swedish Krona', 'SEK', 'kr'),
(118, 'Dollar Singapourien', '$', 'SGD', 'دولار سنغافوري', 'Singapore Dollar', 'SGD', 'S$'),
(119, 'Leone Sierra-Léonais', 'Le', 'SLL', 'ليون سيراليوني', 'Sierra Leonean Leone', 'SLL', 'Le'),
(120, 'Baht Thaïlandais', '฿', 'THB', 'بات تايلندي', 'Thai Baht', 'THB', '฿'),
(121, 'Somoni Tadjik', 'ЅМ', 'TJS', 'سوموني طاجيكستاني', 'Tajikistani Somoni', 'TJS', 'TJS'),
(122, 'Manat Turkmène', 'm', 'TMT', 'مانات تركمانستاني', 'Turkmenistani Manat', 'TMT', 'T'),
(123, 'Pa\'anga Tongien', 'T$', 'TOP', 'بانغا تونغي', 'Tongan Paʻanga', 'TOP', 'T$'),
(124, 'Livre Turque', '₺', 'TRY', 'ليرة تركية', 'Turkish Lira', 'TRY', '₺'),
(125, 'Dollar de Trinité', '$', 'TTD', 'دولار ترينيداد وتوباغو', 'Trinidad and Tobago Dollar', 'TTD', 'TT$'),
(126, 'Dollar Taïwanais', 'NT$', 'TWD', 'دولار تايواني', 'New Taiwan Dollar', 'TWD', 'NT$'),
(127, 'Shilling Tanzanien', 'Sh', 'TZS', 'شلن تنزاني', 'Tanzanian Shilling', 'TZS', 'TSh'),
(128, 'Hryvnia Ukrainienne', '₴', 'UAH', 'هريفنيا أوكرانية', 'Ukrainian Hryvnia', 'UAH', '₴'),
(129, 'Shilling Ougandais', 'Sh', 'UGX', 'شلن أوغندي', 'Ugandan Shilling', 'UGX', 'USh'),
(130, 'Peso Uruguayen', '$', 'UYU', 'بيزو أوروغوياني', 'Uruguayan Peso', 'UYU', '$U'),
(131, 'Sum Ouzbek', 'so\'m', 'UZS', 'سوم أوزبكستاني', 'Uzbekistani Som', 'UZS', 'UZS'),
(132, 'Bolivar Vénézuélien', 'Bs.', 'VES', 'بوليفار فنزويلي', 'Venezuelan Bolívar', 'VES', 'Bs'),
(133, 'Dong Vietnamien', '₫', 'VND', 'دونغ فيتنامي', 'Vietnamese Đồng', 'VND', '₫'),
(134, 'Vatu Vanuatuan', 'Vt', 'VUV', 'فاتو فانواتي', 'Vanuatu Vatu', 'VUV', 'Vt'),
(135, 'Franc CFA', 'Fr', 'XAF', 'فرنك وسط أفريقي', 'Central African CFA Franc', 'XAF', 'FCFA'),
(136, 'Dollar des Caraïbes', '$', 'XCD', 'دولار شرق الكاريبي', 'East Caribbean Dollar', 'XCD', 'EC$'),
(137, 'CFA', 'Fr', 'XOF', 'فرنك غرب أفريقي', 'West African CFA Franc', 'XOF', 'CFA'),
(138, 'Franc Pacifique', 'Fr', 'XPF', 'فرنك باسيفيكي', 'CFP Franc', 'XPF', 'F'),
(139, 'Rand Sud-Africain', 'R', 'ZAR', 'راند جنوب أفريقي', 'South African Rand', 'ZAR', 'R'),
(140, 'Kwacha Zambien', 'ZK', 'ZMW', 'كواشا زامبي', 'Zambian Kwacha', 'ZMW', 'ZK'),
(141, 'Dollar Zimbabwéen', '$', 'ZWL', 'دولار زيمبابوي', 'Zimbabwean Dollar', 'ZWL', 'Z$');

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
  `F_02` varchar(4) NOT NULL,
  `F_00` bigint(20) NOT NULL,
  `F_03` bigint(20) NOT NULL,
  `F_01` varchar(200) NOT NULL
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
  `F_04` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_02_06`
--

CREATE TABLE `t_02_02_06` (
  `F_00` bigint(20) NOT NULL,
  `F_04` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_02_07`
--

CREATE TABLE `t_02_02_07` (
  `F_00` bigint(20) NOT NULL,
  `F_03` datetime(6) DEFAULT NULL,
  `F_04` bigint(20) NOT NULL,
  `F_05` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_02_08`
--

CREATE TABLE `t_02_02_08` (
  `F_02` double DEFAULT NULL,
  `F_03` double DEFAULT NULL,
  `F_04` double DEFAULT NULL,
  `F_00` bigint(20) NOT NULL,
  `F_05` bigint(20) NOT NULL,
  `F_06` bigint(20) NOT NULL,
  `F_07` bigint(20) NOT NULL,
  `F_08` bigint(20) DEFAULT NULL,
  `F_01` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_02_09`
--

CREATE TABLE `t_02_02_09` (
  `F_01` float DEFAULT NULL,
  `F_00` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL,
  `F_03` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_03_01`
--

CREATE TABLE `t_02_03_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` int(11) NOT NULL,
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
  `F_06` double DEFAULT NULL,
  `F_00` bigint(20) NOT NULL,
  `F_08` datetime(6) DEFAULT NULL,
  `F_18` bigint(20) DEFAULT NULL,
  `F_19` bigint(20) NOT NULL,
  `F_20` bigint(20) NOT NULL,
  `F_21` bigint(20) DEFAULT NULL,
  `F_03` varchar(20) DEFAULT NULL,
  `F_04` varchar(20) DEFAULT NULL,
  `F_12` varchar(50) DEFAULT NULL,
  `F_13` varchar(50) DEFAULT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_05` varchar(200) DEFAULT NULL,
  `F_07` varchar(200) DEFAULT NULL,
  `F_09` varchar(200) DEFAULT NULL,
  `F_10` varchar(200) DEFAULT NULL,
  `F_11` varchar(200) DEFAULT NULL,
  `F_14` varchar(200) DEFAULT NULL,
  `F_15` varchar(200) DEFAULT NULL,
  `F_17` varchar(200) DEFAULT NULL,
  `F_16` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_03_05`
--

CREATE TABLE `t_02_03_05` (
  `F_00` bigint(20) NOT NULL,
  `F_10` bigint(20) NOT NULL,
  `F_01` varchar(50) NOT NULL,
  `F_02` varchar(50) NOT NULL,
  `F_06` varchar(50) DEFAULT NULL,
  `F_04` varchar(100) DEFAULT NULL,
  `F_05` varchar(100) DEFAULT NULL,
  `F_07` varchar(100) DEFAULT NULL,
  `F_08` varchar(100) DEFAULT NULL,
  `F_09` varchar(100) DEFAULT NULL,
  `F_03` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_03_06`
--

CREATE TABLE `t_02_03_06` (
  `F_00` bigint(20) NOT NULL,
  `F_01` datetime(6) DEFAULT NULL,
  `F_02` datetime(6) DEFAULT NULL,
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
  `F_01` datetime(6) NOT NULL,
  `F_02` datetime(6) DEFAULT NULL,
  `F_04` bigint(20) NOT NULL,
  `F_05` bigint(20) NOT NULL,
  `F_06` bigint(20) DEFAULT NULL,
  `F_03` varchar(255) DEFAULT NULL
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
  `F_04` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_02_04_03`
--

INSERT INTO `t_02_04_03` (`F_00`, `F_04`, `F_01`, `F_02`, `F_03`) VALUES
(1, 1, 'مرحلة إعداد المخطط الميزانياتي', 'Budget Plan Maturation Stage', 'Instance de maturation de plan budgétaire'),
(2, 1, 'مرحلة تحديد الإحتياجات', 'Needs Identification Stage', 'Instance du besoin'),
(3, 1, 'مرحلة إعداد البطاقة التقنية', 'Technical Data Sheet Preparation Stage', 'Instance de la fiche technique'),
(4, 2, 'مرحلة موافقة القيادة العليا', 'High Command Approval Stage', 'Instance Accord du Haut Commandement'),
(5, 2, 'مرحلة إبداء الرأي', 'Opinion Stage', 'Instance d\'avis'),
(6, 2, 'إعداد ملف اللجنة القطاعية للصفقات (CSM)', 'Preparation of CSM File', 'Préparation du dossier CSM'),
(7, 3, 'مرحلة دراسة اللجنة القطاعية للصفقات (CSM)', 'CSM Review Stage', 'Instance Examen de la CSM'),
(8, 3, 'رفع التحفظات جارية', 'Lifting of Ongoing Reservations', 'Leveé des réserves en cours'),
(9, 3, 'رفع التحفظات، في إنتظار مداولة اللجنة القطاعية للصفقات (CSM)', 'Reservations Removed, CSM Deliberation Stage', 'Réserves leveés, instance délibiration de la CSM'),
(10, 3, 'إعداد ملف النشر', 'Preparation of Publish File', 'Préparation du dossier de publication'),
(11, 4, 'مرحلة النشر', 'Publish Stage', 'Instance publication'),
(12, 4, 'مرحلة إيداع العروض', 'Submission of Offers Stage', 'Instance dépôt des offres'),
(13, 5, 'تحويل الملف إلى لجنة فتح الأظرفة وتقييم العروض (COPEO)', 'File Transmitted to COPEO', 'Dossier transmis à la COPEO');

-- --------------------------------------------------------

--
-- Table structure for table `t_02_04_04`
--

CREATE TABLE `t_02_04_04` (
  `F_01` varchar(3) NOT NULL,
  `F_02` varchar(4) NOT NULL,
  `F_07` double DEFAULT NULL,
  `F_08` double DEFAULT NULL,
  `F_00` bigint(20) NOT NULL,
  `F_09` datetime(6) DEFAULT NULL,
  `F_11` datetime(6) DEFAULT NULL,
  `F_12` datetime(6) DEFAULT NULL,
  `F_13` datetime(6) DEFAULT NULL,
  `F_15` bigint(20) NOT NULL,
  `F_16` bigint(20) NOT NULL,
  `F_17` bigint(20) NOT NULL,
  `F_18` bigint(20) NOT NULL,
  `F_19` bigint(20) NOT NULL,
  `F_20` bigint(20) NOT NULL,
  `F_21` bigint(20) NOT NULL,
  `F_03` varchar(20) DEFAULT NULL,
  `F_04` varchar(300) DEFAULT NULL,
  `F_05` varchar(300) DEFAULT NULL,
  `F_06` varchar(300) NOT NULL,
  `F_14` varchar(500) DEFAULT NULL,
  `F_10` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_04_05`
--

CREATE TABLE `t_02_04_05` (
  `F_02` double DEFAULT NULL,
  `F_00` bigint(20) NOT NULL,
  `F_01` datetime(6) DEFAULT NULL,
  `F_03` bigint(20) NOT NULL,
  `F_04` bigint(20) NOT NULL,
  `F_05` bigint(20) DEFAULT NULL,
  `F_06` bigint(20) DEFAULT NULL,
  `F_07` bigint(20) DEFAULT NULL,
  `A_01` datetime(6) NOT NULL,
  `A_04` varchar(100) DEFAULT NULL,
  `A_06` varchar(45) DEFAULT NULL,
  `A_05` varchar(100) DEFAULT NULL,
  `A_07` varchar(45) DEFAULT NULL,
  `A_02` datetime(6) DEFAULT NULL,
  `A_03` bigint(20) DEFAULT NULL
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
  `F_04` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_02_05_03`
--

INSERT INTO `t_02_05_03` (`F_00`, `F_04`, `F_01`, `F_02`, `F_03`) VALUES
(1, 1, 'مرحلة مداولات لجنة فتح الأظرفة وتقييم العروض', 'COPEO Committee Deliberation Stage', 'Instance PVs COPEO'),
(2, 2, 'إمضاء مشروع العقد/الفقة', 'Signature of the contract project', 'Signature du projet du contrat/marché'),
(3, 2, 'إعداد ملف اللجنة القطاعية للصفقات', 'Preparation of CSM File', 'Préparation du dossier CSM'),
(4, 3, 'مرحلة دراسة اللجنة القطاعية للصفقات', 'CSM Review Stage', 'Instance Examen de la CSM'),
(5, 3, 'رفع التحفظات جارية', 'Lifting of Ongoing Reservations', 'Leveé des réserves en cours'),
(6, 3, 'رفع التحفظات، في إنتظار مداولة اللجنة القطاعية للصفقات', 'Reservations Removed, CSM Deliberation Stage', 'Réserves leveés, instance délibiration de la CSM'),
(7, 3, 'إعداد ملف الالتزام', 'Preparation of Commitment File', 'Préparation du dossier du dossier d\'engagement'),
(8, 4, 'إرسال الملف للالتزام', 'File Sent for Commitment', 'Dossier transmis pour engagement');

-- --------------------------------------------------------

--
-- Table structure for table `t_02_05_04`
--

CREATE TABLE `t_02_05_04` (
  `F_07` double DEFAULT NULL,
  `F_08` double DEFAULT NULL,
  `F_14` int(11) DEFAULT NULL,
  `F_00` bigint(20) NOT NULL,
  `F_09` datetime(6) DEFAULT NULL,
  `F_11` datetime(6) DEFAULT NULL,
  `F_12` datetime(6) DEFAULT NULL,
  `F_13` datetime(6) DEFAULT NULL,
  `F_16` bigint(20) NOT NULL,
  `F_17` bigint(20) NOT NULL,
  `F_18` bigint(20) NOT NULL,
  `F_19` bigint(20) NOT NULL,
  `F_20` bigint(20) DEFAULT NULL,
  `F_21` bigint(20) NOT NULL,
  `F_22` bigint(20) DEFAULT NULL,
  `F_23` bigint(20) DEFAULT NULL,
  `F_04` varchar(300) DEFAULT NULL,
  `F_05` varchar(300) DEFAULT NULL,
  `F_06` varchar(300) NOT NULL,
  `F_15` varchar(500) DEFAULT NULL,
  `F_01` varchar(255) NOT NULL,
  `F_02` varchar(255) DEFAULT NULL,
  `F_03` varchar(255) DEFAULT NULL,
  `F_10` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_02_05_05`
--

CREATE TABLE `t_02_05_05` (
  `F_03` double DEFAULT NULL,
  `F_04` double DEFAULT NULL,
  `F_00` bigint(20) NOT NULL,
  `F_06` bigint(20) NOT NULL,
  `F_01` varchar(255) NOT NULL,
  `F_02` varchar(255) NOT NULL,
  `F_05` varchar(255) DEFAULT NULL
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
  `F_04` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `t_02_06_03`
--

INSERT INTO `t_02_06_03` (`F_00`, `F_04`, `F_01`, `F_02`, `F_03`) VALUES
(1, 1, 'مرحلة مداولات لجنة فتح الأظرفة وتقييم العروض', 'COPEO Amendment fileStage', 'Instance Dossier de l\'avenant'),
(2, 1, 'مرحلة مداولات لجنة فتح الأظرفة وتقييم العروض', 'COPEO Committee Deliberation Stage', 'Instance PVs COPEO'),
(3, 1, 'مرحلة موافقة القيادة العليا', 'High Command Approval Stage', 'Instance Accord du Haut Commandement'),
(4, 2, 'إمضاء مشروع العقد/الفقة', 'Signature of the Amendment project', 'Signature du projet de l\'avenant'),
(5, 2, 'إعداد ملف اللجنة القطاعية للصفقات', 'Preparation of CSM File', 'Préparation du dossier CSM'),
(6, 3, 'مرحلة دراسة اللجنة القطاعية للصفقات', 'CSM Review Stage', 'Instance Examen de la CSM'),
(7, 3, 'رفع التحفظات جارية', 'Lifting of Ongoing Reservations', 'Leveé des réserves en cours'),
(8, 3, 'رفع التحفظات، في إنتظار مداولة اللجنة القطاعية للصفقات', 'Reservations Removed, CSM Deliberation Stage', 'Réserves leveés, instance délibiration de la CSM'),
(9, 3, 'إعداد ملف الالتزام', 'Preparation of Commitment File', 'Préparation du dossier du dossier d\'engagement'),
(10, 4, 'إرسال الملف للالتزام', 'File Sent for Commitment', 'Dossier transmis pour engagement');

-- --------------------------------------------------------

--
-- Table structure for table `t_02_06_04`
--

CREATE TABLE `t_02_06_04` (
  `F_01` int(11) DEFAULT NULL,
  `F_06` double DEFAULT NULL,
  `F_07` double DEFAULT NULL,
  `F_00` bigint(20) NOT NULL,
  `F_08` datetime(6) DEFAULT NULL,
  `F_09` datetime(6) DEFAULT NULL,
  `F_10` datetime(6) DEFAULT NULL,
  `F_12` bigint(20) NOT NULL,
  `F_13` bigint(20) NOT NULL,
  `F_14` bigint(20) NOT NULL,
  `F_15` bigint(20) NOT NULL,
  `F_16` bigint(20) DEFAULT NULL,
  `F_17` bigint(20) NOT NULL,
  `F_03` varchar(300) DEFAULT NULL,
  `F_04` varchar(300) DEFAULT NULL,
  `F_05` varchar(300) DEFAULT NULL,
  `F_11` varchar(500) DEFAULT NULL,
  `F_02` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `t_sys_audit`
--

CREATE TABLE `t_sys_audit` (
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
  ADD UNIQUE KEY `UKmwnnc8dap9w9rh023n8wbktkk` (`F_01`),
  ADD UNIQUE KEY `T_00_02_01_UK_01` (`F_01`);

--
-- Indexes for table `t_00_02_02`
--
ALTER TABLE `t_00_02_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `UKpjc9qrt34jbhcekaguv958vcw` (`F_01`),
  ADD UNIQUE KEY `UKdurjgki4ck1asdv0g89gbuadq` (`F_02`),
  ADD UNIQUE KEY `T_00_02_02_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_00_02_02_UK_02` (`F_02`);

--
-- Indexes for table `t_00_02_03`
--
ALTER TABLE `t_00_02_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `UK3vy7hixn66tr25ngogmbtcqb4` (`F_01`),
  ADD UNIQUE KEY `T_00_02_03_UK_01` (`F_01`);

--
-- Indexes for table `t_00_02_04`
--
ALTER TABLE `t_00_02_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `UK94631asmtmb3w1llm428rye0g` (`F_01`),
  ADD UNIQUE KEY `T_00_02_04_UK_01` (`F_01`),
  ADD KEY `T_00_02_04_FK_01` (`F_03`);

--
-- Indexes for table `t_00_02_05`
--
ALTER TABLE `t_00_02_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `UKancis9tvb279g8lqsp8sfp45` (`F_01`),
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
  ADD UNIQUE KEY `UK63mg0c4orrodc86iu5yg7un1h` (`F_01`),
  ADD UNIQUE KEY `T_00_04_01_UK_01` (`F_01`),
  ADD UNIQUE KEY `UK5sbmtia6ldm66s8183my7nvap` (`T_00_04_01_FK_01`);

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
  ADD UNIQUE KEY `T_01_04_01_UK_01` (`F_01`) USING BTREE;

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
  ADD UNIQUE KEY `T_02_02_07_UK_01` (`F_03`),
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
  ADD UNIQUE KEY `T_02_03_01_UK_02` (`F_04`),
  ADD UNIQUE KEY `T_02_03_01_UK_01` (`F_01`);

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
-- Indexes for table `t_sys_audit`
--
ALTER TABLE `t_sys_audit`
  ADD PRIMARY KEY (`F_00`);

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
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_00_02_02`
--
ALTER TABLE `t_00_02_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `t_00_02_03`
--
ALTER TABLE `t_00_02_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_00_02_04`
--
ALTER TABLE `t_00_02_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_00_02_05`
--
ALTER TABLE `t_00_02_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_00_03_01`
--
ALTER TABLE `t_00_03_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_00_04_01`
--
ALTER TABLE `t_00_04_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

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
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3083;

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
-- AUTO_INCREMENT for table `t_sys_audit`
--
ALTER TABLE `t_sys_audit`
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
  ADD CONSTRAINT `FKhhkpgi3mck97fqshxi3q51ob8` FOREIGN KEY (`T_00_04_01_FK_01`) REFERENCES `t_00_02_02` (`F_00`);

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
