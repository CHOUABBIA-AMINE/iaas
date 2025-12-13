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



Insert into T_01_01_01 (F_00,F_01,F_02) values
(1,"س","S");

Insert into T_01_01_02 (F_00, F_01, F_02, F_03) values
(1, "60", 1, 6),
(2, "61", 1, 6),
(3, "62", 1, 6),
(4, "63", 1, 6),
(5, "64", 1, 6),
(6, "65", 1, 6),
(7, "66", 1, 6),
(8, "67", 1, 6),
(9, "68", 1, 6);

Insert into T_01_01_03 (F_00, F_01, F_02, F_03, F_04) values
(1, "-2°", "الطابق السفلي الثاني", "Second Floor Underground", "Deuxième Etage Sous Sol"),
(2, "-1°", "الطابق السفلي الأول", "First Floor Underground", "Premier Etage Sous Sol"),
(3, "0", "الطابق الأرضي", "Ground Floor", "Riz de Chaussé"),
(4, "1°", "الطابق الأول", "First Floor", "Premier Etage"),
(5, "2°", "الطابق الأول", "Second Floor", "Deuxième Etage"),
(6, "3°", "الطابق الأول", "Third Floor", "Troisième Etage"),
(7, "4°", "الطابق الأول", "Fourth Floor", "Quatrième Etage"),
(8, "5°", "الطابق الأول", "Fifth Floor", "Cinquième Etage"),
(9, "6°", "الطابق الأول", "Sixth Floor", "Siesième Etage"),
(10, "7°", "الطابق الأول", "Seventh Floor", "Septième Etage"),
(11, "8°", "الطابق الأول", "Eigth Floor", "Huitième Etage"),
(12, "9°", "الطابق الأول", "Nineth Floor", "Neuvième Etage");



INSERT INTO T_01_02_01 (F_00, F_01, F_02, F_03) VALUES
(1, 'الصادر', 'Outgoing', 'Départ'),
(2, 'الوارد', 'Arriving', 'Arrivé'); 

INSERT INTO T_01_02_02 (F_00, F_01, F_02, F_03) VALUES
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

INSERT INTO T_01_03_01 (F_00, F_01, F_02, F_03, F_04) VALUES
(1,'بطاقة اقتراح','Proposal Form','Fiche de Proposition',100),
(2,'بطاقة تقنية وصفية','Descriptive Technical Sheet','Fiche Technique Descriptive',100),
(3,'بطاقة تقنية','Technical Sheet','Fiche Technique',100),
(4,'تقرير تقديم','Presentation Report','Rapport de Présentation',111),
(5,'تقرير تقديم تكميلي','Supplementary Presentation Report','Rapport de Présentation Complémentaire',111),
(6,'بطاقة تحليلية','Analytical Sheet','Fiche Analytique',011),
(7,'بطاقة الإقتطاع الميزانياتي','Budget Allocation Sheet','Fiche d\'Imputation Budgétaire',111),
(8,'تأشيرة اللجنة القطاعية للصفقات','CSM Visa','Visa CSM',111),
(9,'لوحة إعلانية','Advertising Board','Placard publicitaire',100),
(10,'دفتر الشروط','Terms of Reference','Cahier des Charges',100),
(11,'عقد / صفقة','Contract','Contrat / Marché',010),
(12,'ملحق عقد','Amendment','Avenant',001),
(13,'أمر شراء','Purchase Order','Bon de Commande',010),
(14,'بطاقة تمديد الآجال','Deadline Extension Form','Fiche de Prorogation des Délais',100),
(15,'لوحة إعلانية (تمديد الآجال)','Advertising Board (Deadline Extension)','Placard publicitaire (Prorogation Délai)',100),
(16,'محضر فتح الأظرف الإدارية','Minutes of Administrative Bid Opening','PV Ouverture Administratif des plis',100),
(17,'محضر فتح الأظرف التقنية','Minutes of Technical Bid Opening','PV Ouverture Technique',100),
(18,'محضر التقييم التقني','Technical Evaluation Minutes','PV Evaluation Technique',100),
(19,'محضر فتح الأظرف المالية','Minutes of Financial Bid Opening','PV Ouverture Financière',100),
(20,'محضر التقييم المالي','Financial Evaluation Minutes','PV Evaluation Financière',100),
(21,'محضر الإغلاق','Closing Minutes','PV de Cloture',100),
(22,'مداولة الاختيار المؤقت','Provisional Selection Deliberation','Délibération du choix Provisoire',100),
(23,'محضر لجنة فتح الأظرفة وتقييم العروض','COPEO Minutes','PV COPEO',011),
(24,'محضر لجنة فتح الأظرفة وتقييم العروض التكميلي','Supplementary COPEO Minutes','PV COPEO Complémentaire',111),
(25,'أمر خدمة','Service Order','Ordre de Service',011),
(26,'كفالة التعهد','Bid Bond','Caution de Soumission',100),
(27,'كفالة حسن التنفيذ','Performance Bond','Caution de Bonne Exécution',100),
(28,'كفالة الضمان','Guarantee Bond','Caution de Garantie',100),
(29,'الملف الإداري','Administrative File','Dossier Administratif',100),
(30,'العرض المالي','Financial Offer','Offre Financière',100),
(31,'العرض الفني','Technical Offer','Offre Technique',100);

Insert into T_01_01_04 (F_00, F_01, F_02, F_03, F_04) values
(1, "1°", "الطابق الأول", "First Floor", "Premier Etage"),
(2, "2°", "الطابق الأول", "Second Floor", "Deuxième Etage"),
(3, "3°", "الطابق الأول", "Third Floor", "Troisième Etage"),
(4, "4°", "الطابق الأول", "Fourth Floor", "Quatrième Etage"),
(5, "5°", "الطابق الأول", "Fifth Floor", "Cinquième Etage"),
(6, "6°", "الطابق الأول", "Sixth Floor", "Siesième Etage"),
(7, "7°", "الطابق الأول", "Seventh Floor", "Septième Etage"),
(8, "8°", "الطابق الأول", "Eigth Floor", "Huitième Etage"),
(9, "9°", "الطابق الأول", "Nineth Floor", "Neuvième Etage");

Insert into T_01_01_05 (F_00, F_01, F_02) values
(1, "SDR-01", 2),
(2, "SDR-02", 2),
(3, "SDR-03", 2),
(4, "SDR-04", 3),
(5, "SDR-05", 3),
(6, "SDR-06", 4),
(7, "SDR-07", 4),
(8, "SDR-08", 7),
(9, "SDR-09", 7),
(10, "SDR-10", 7),
(11, "SDR-11", 8),
(12, "SDR-12", 8),
(13, "SDR-13", 8),
(14, "SDR-14", 8),
(15, "SDR-15", 8),
(16, "SDR-16", 8),
(17, "SDR-17", 8),
(18, "SDR-18", 8),
(19, "SDR-19", 8),
(20, "SDR-20", 8);

INSERT INTO T_01_04_04 (f_00, F_01, F_02, F_03) VALUES
(1,'الضباط العامون','General Officers','Officiers Généraux'),
(2,'الضباط السامون','Senior Officers','Officiers Supérieurs'),
(3,'الضباط المرؤوسون','Junior Officers','Officiers Subalternes'),
(4,'ضباط صف','Non-Commissioned Officers (NCOs)','Sous-Officiers'),
(5,'رجال الصف','Enlisted Personnel','Hommes du Rang'),
(6,'مستخدمون مدنيون','Civilian Personnel','Personnel Civile');

INSERT INTO T_01_04_05 (F_00, F_01, F_02, F_03, F_07) VALUES
(1, 'General', 'فريق أول', 'Général d\'Armée', 1),
(2, 'Lieutenant General', 'فريق', 'Général de Corps d\'Armée', 1),
(3, 'Major General', 'لواء', 'Général Major', 1),
(4, 'Brigadier General', 'عميد', 'Général', 1),
(5, 'Colonel', 'عقيد', 'Colonel', 2),
(6, 'Lieutenant Colonel', 'مقدم', 'Lieutenant Colonel', 2),
(7, 'Major', 'رائد', 'Commandant', 2),
(8, 'Captain', 'نقيب', 'Capitaine', 3),
(9, 'Lieutenant', 'ملازم أول', 'Lieutenant', 3),
(10, 'Second Lieutenant', 'ملازم', 'Sous Lieutenant', 3),
(11, 'Aspirant', 'مرشح', 'Aspirant', 3),
(12, 'Major Sergeant', 'مساعد رئيسي', 'Adjudant Major', 4),
(13, 'Master Sergeant', 'مساعد أول', 'Adjudant Chef', 4),
(14, 'First Class Sergeant', 'مساعد', 'Adjudant', 4),
(15, 'Staff Sergeant', 'رقيب أول', 'Sergent Chef', 4),
(16, 'Sergeant', 'رقيب', 'Sergent', 4),
(17, 'Corporal', 'عريف أول', 'Caporal Chef', 5),
(18, 'Lance Corporal', 'عريف', 'Caporal', 5),
(19, 'Private Soldier', 'جندي', 'Djoundi', 5),
(20, 'Assimilated Civilian Personnel', 'مستخدم مدني شبيه', 'Personnel Civile Assimilé', 6),
(21, 'Contractual Civilian Personnel', 'مستخدم مدني متعاقد', 'Personnel Civile Contractuel', 6);

Insert into T_01_04_06 (F_00, F_01, F_02, F_03) values
(1,'وزارة','Ministry','Ministère'),
(2,' أركان الجيش','Army General Staff','Etat Major de l\'Armée'),
(3,'مديرية عامة','General Directorate','Direction Générale'),
(4,'الأمانة العامة لوزارة','Ministry General Secretariat','Secretariat Générale du Ministère'),
(5,'ديوان','Cabinet','Cabinet'),
(6,'قيادة قوات','Force Command','Commandement de Forces'),
(7,'ناحية عسكرية','Military Region','Région Militaire'),
(8,' أركان ناحية عسكرية','Military Region Staff','Etat Major d\'une Région Militaire'),
(9,'دائرة','Department','Département'),
(10,'مديرية مركزية','Central Directorate','Direction Centrale'),
(11,'مديرية','Directorate','Direction'),
(12,'مصلحة مركزية','Central Service','Service Central'),
(13,'مصلحة','Service','Service'),
(14,'مركز','Center','Centre'),
(15,'مديرية دائرة','Department Directorate','Direction de Département'),
(16,'مصلحة دائرة','Department Service','Service de Département'),
(17,'مكتب دائرة','Department Office','Bureau de Département'),
(18,'فصيلة','Section','Section'),
(19,'مديرية جهوية','Regional Directorate','Direction Régionale'),
(20,'مصلحة جهوية','Regional Service','Service Régional'),
(21,'مكتب جهوي','Regional Office','Bureau Régional'),
(22,'مركز جهوي','Regional Center','Centre Régional'),
(23,'مديرية فرعية','Sub-Directorate','Sous Direction'),
(24,'خلية','Cell','Cellule'),
(25,'فصيلة فرعية','Subsection','Sous Section'),
(26,'مكتب','Office','Bureau'),
(27,'فصيلة مكتب','Office Section','Section de Bureau'),
(28,'مدرسة وطنية','National School','Ecole Nationale'),
(29,'مدرسة عليا','Higher School','Ecole Supérieure'),
(30,'مدرسة تطبيقية','Application School','Ecole d\'Application'),
(31,'مركز تدريب','Training Center','Centre d\'instruction'),
(32,'مؤسسة وزارية','Ministerial Establishment','Etablissement Ministèriel'),
(33,'مؤسسة مركزية','Central Establishment','Etablissement Centrale'),
(34,'مؤسسة جهوية','Regional Establishment','Etablissement Régional'),
(35,'فرقة','Division','Division'),
(36,'لواء','Brigade','Brigade'),
(37,'فوج','Regiment','Régiment'),
(38,'فوج جهوي','Regional Regiment','Régiment Régional'),
(39,'كتيبة','Battalion','Bataillon'),
(40,'سرية','Company','Compagnie');

INSERT INTO T_02_01_02 (F_00, F_01, F_02, F_03) VALUES
(1,'غير قابل للتطبيق','Not Applicabale','Non applicable'),
(2,'قيد التحضير','Under Preparation','En cours de préparation'),
(3,'تأشيرة','Visa','Visa'),
(4,'تأشيرة مع توصيات','Visa with Recommendations','Visa avec recommandations'),
(5,'تأشيرة مع تحفظات','Visa under Reservations','Visa sous réserves'),
(6,'تأجيل','Adjournment','Ajournement'),
(7,'رفض','Rejection','Rejet'),
(8,'رفض مؤقت','Temporary Rejection','Rejet à titre temporaire'),
(9,'رفض نهائي','Definitive Rejection','Rejet définitif');

INSERT INTO T_02_01_03 (F_00, F_01, F_02, F_03) VALUES
(1, 'المديرية الفرعية للإنجازات', 'Sub Directorat of Realizations', 'Sous Direction Réalisations'),
(2, 'دائرة المؤن', 'Approvisionment Department', 'Département des Approvisionnements');

INSERT INTO T_02_01_04 (F_00, F_01, F_02, F_03) VALUES
(1,'الوسائل الكبرى','Major Means','Moyens Majeurs'),
(2,'تسيير','Operation','Fonctionnement'),
(3,'تجهيز','Equipment','Équipement'),
(4,'قطع غيار','Spare Parts','Pièces de Rechange'),
(5,'أشغال','Constructions','Travaux'),
(6,'خدمات','Services','Prestations'),
(7,'دراسات','Studies','Études');

INSERT INTO T_02_01_05 (F_00, F_01, F_02, F_03) VALUES
(1,'قيد الانتظار','On Hold','En Instance'),
(2,'قيد التنفيذ','Ongoing','En cours'),
(3,'غيرمجدي','Defective','Infrectieux'),
(4,'مُنجز','Finalized','Finalisé'),
(5,'ملغي','Canceled','Annulé'),
(6,'مؤجل','Deferred','Différé'),
(7,'منقول','Transferred','Transféré');

INSERT INTO T_02_02_01 (F_00, F_04, F_05, F_06, F_01, F_02, F_03) VALUES
(1, 'تسيير', 'F', 'F', 'ميزانية التسيير', 'Functional Budget', 'Budget de Fonctionnement'),
(2, 'تجهيز', 'E', 'E', 'ميزانية التجهيز', 'Equipment Budget', 'Budget d\'Equipement');

INSERT INTO T_02_03_02 (F_00, F_01, F_02, F_03, F_04, F_05, F_06) VALUES
(1,'شخص طبيعي','Natural Person','Personne Physique','ش ط','NP','PP'),
(2,'شخص معنوي','Legal Entity','Personne Morale','ش م','LP','PM'),
(3,'شركة ذات مسؤولية محدودة لشخص واحد','One-Person Limited Liability Company','Entreprise Unipersonnelle à Responsabilité Limitée﻿','ش ذ م م ش و','OPLLC','EURL'),
(4,'شركة ذات مسؤولية محدودة','Limited Liability Company','Société à Responsabilité Limitée﻿','ش ذ م م','LLC','SARL'),
(5,'شركة ذات أسهم','Joint Stock Company','Société par Action﻿','ش ذ أ','JSC','SPA'),
(6,'شركة مساهمة مبسطة','Simplified Joint Stock Company','Société par Action Simplifiée﻿','ش م م','SJSC','SPAS'),
(7,'شركة تضامن','General Partnership','Société en Nom Collectif﻿','ش ت','GP','SNC'),
(8,'تجمع','Grouping / Consortium','Groupement','ت','C','G'),
(9,'شركة مجهولة الاسم','Public Limited Company','Société Anonyme﻿','ش م إ','PLC','SA'),
(10,'مؤسسة عمومية ذات طابع صناعي وتجاري','Public Industrial and Commercial Establishment','Etablissement Public à Caractère Industriel et Commerciale﻿','م ع ذ ط ص ت','PICE','EPIC'),
(11,'مؤسسة عمومية ذات إداري','Public Administrative Establishment','Etablissement Public à Caractère Administratif','م ع ذ ط إ','PAE','EPA');

INSERT INTO T_02_03_03 (F_00, F_01, F_02, F_03) VALUES
(1,'إقصاء مؤقت','Temporary exclusion','Exclusion à titre temporale'),
(2,'إقصاء نهائي','Permanent exclusion','Exclusion à titre definitif');

INSERT INTO T_02_04_01 (F_00, F_04, F_05, F_06, F_01, F_02, F_03) VALUES
(1, 'ط ع م  و', 'NOTC', 'AOON', 'طلب عروض مفتوح وطني', 'National Open Tender Call', 'Appel d\'Offres Ouvert National'),
(2, 'ط ع م و د', 'NIOTC', 'AOONI', 'طلب عروض مفتوح وطني ودولي', 'National and International Open Tender Call', 'Appel d\'Offres Ouvert National et International'),
(3, 'ت ب', 'SDN', 'GG / S', 'التراضي البسيط', 'Simple Direct Negotiation', 'Gré à Gré Simple'),
(4, 'ت ب إ', 'NAC', 'GG / AC', 'التراضي بعد الاستشارة', 'Negotiation after Consultation', 'Gré à Gré après Consultation'),
(5, 'إد', 'IC(AP)', 'CI(PA)', 'استشارة داخلية', 'Internal Consultation (Adapted Procedure)', 'Consultation Interne (Procédure Adaptée)');

INSERT INTO T_02_04_02 (F_00, F_01, F_02, F_03) VALUES
(1,'مبرمجة','Planed','Programmée'),
(2,'قيد الإعداد','In Progress','En cours d\'élaboration'),
(3,'قيد المراجعة','Under Review','En cours de contrôle'),
(4,'قيد النشر','In Publication','En cours de publication'),
(5,'قيد التقييم','Under Evaluation','En cours d\'évaluation');

INSERT INTO T_02_04_03 (F_00, F_01, F_02, F_03, F_04) VALUES
(1,'مرحلة إعداد المخطط الميزانياتي','Budget Plan Maturation Stage','Instance de maturation de plan budgétaire', 1),
(2,'مرحلة تحديد الإحتياجات','Needs Identification Stage','Instance du besoin', 1),
(3,'مرحلة إعداد البطاقة التقنية','Technical Data Sheet Preparation Stage','Instance de la fiche technique', 1),
(4,'مرحلة موافقة القيادة العليا','High Command Approval Stage','Instance Accord du Haut Commandement', 1),
(5,'مرحلة إبداء الرأي','Opinion Stage','Instance d\'avis', 1),
(6,'إعداد ملف اللجنة القطاعية للصفقات (CSM)','Preparation of CSM File','Préparation du dossier CSM', 2),
(7,'مرحلة دراسة اللجنة القطاعية للصفقات (CSM)','CSM Review Stage','Instance Examen de la CSM', 3),
(8,'رفع التحفظات جارية','Lifting of Ongoing Reservations','Leveé des réserves en cours', 3),
(9,'رفع التحفظات، في إنتظار مداولة اللجنة القطاعية للصفقات (CSM)','Reservations Removed, CSM Deliberation Stage','Réserves leveés, instance délibiration de la CSM', 3),
(10,'إعداد ملف النشر','Preparation of Publish File','Préparation du dossier de publication', 3),
(11,'مرحلة النشر','Publish Stage','Instance publication', 4),
(12,'مرحلة إيداع العروض','Submission of Offers Stage','Instance dépôt des offres', 4),
(13,'تحويل الملف إلى لجنة فتح الأظرفة وتقييم العروض (COPEO)','File Transmitted to COPEO','Dossier transmis à la COPEO', 5);

INSERT INTO T_02_05_01 (F_00, F_01, F_02, F_03) VALUES
(1,'اتفاقية','Convention','Convention'),
(2,'اتفاقية إطار','Framework Convention','Convention Cadre'),
(3,'صفقة','Contract','Marché'),
(4,'صفقة تسوية','Regularization Contract','Marché de Régularisation'),
(5,'صفقة طلبات','Contract on Demand','Marché à Commandes'),
(6,'عقد','Contract','Contrat'),
(7,'عقد برنامج','Framework Contract','Contrat Programme'),
(8,'عقد تطبيق','Implementation Contract','Contrat d\'Application'),
(9,'أمر شراء','Purchase Order','Bon de Commande');
 
INSERT INTO T_02_05_02 (F_00, F_01, F_02, F_03) VALUES
(1,'مبرمجة','Planed','Programmée'),
(2,'قيد الإعداد','In Progress','En cours d\'élaboration'),
(3,'قيد المراجعة','Under Review','En cours de contrôle'),
(4,'قيد الإلتزام','Under Engagement','En cours d\engagement');

INSERT INTO T_02_05_03 (F_00, F_01, F_02, F_03, F_04) VALUES
(1,'مرحلة مداولات لجنة فتح الأظرفة وتقييم العروض','COPEO Committee Deliberation Stage','Instance PVs COPEO',1),
(2,'إمضاء مشروع العقد/الفقة','Signature of the contract project','Signature du projet du contrat/marché',2),
(3,'إعداد ملف اللجنة القطاعية للصفقات','Preparation of CSM File','Préparation du dossier CSM',2),
(4,'مرحلة دراسة اللجنة القطاعية للصفقات','CSM Review Stage','Instance Examen de la CSM',3),
(5,'رفع التحفظات جارية','Lifting of Ongoing Reservations','Leveé des réserves en cours',3),
(6,'رفع التحفظات، في إنتظار مداولة اللجنة القطاعية للصفقات','Reservations Removed, CSM Deliberation Stage','Réserves leveés, instance délibiration de la CSM',3),
(7,'إعداد ملف الالتزام','Preparation of Commitment File','Préparation du dossier du dossier d\'engagement',3),
(8,'إرسال الملف للالتزام','File Sent for Commitment','Dossier transmis pour engagement',4);
 
INSERT INTO T_02_06_01 (F_00, F_01, F_02, F_03) VALUES
(1,'ملحق عقد','Amendment','Avenant'),
(2,'ملحق تعديل','Adjustment Amendment','Avenant d\'Ajustement'),
(3,'ملحق نقل الحقوق والالتزامات','Amendment for Transfer of Rights and Obligations','Avenant de transfert des droits et des obligations'),
(4,'ملحق تعديل الكميات النهائية','Amendment for Adjustment of Final Quantities','Avenant d\'Ajustement des Quantités Définitives');

INSERT INTO T_02_06_02 (F_00, F_01, F_02, F_03) VALUES
(1,'مبرمجة﻿','Planed','Programmée'),
(2,'قيد الإعداد','In Progress','En cours d\'élaboration'),
(3,'قيد المراجعة﻿','Under Review','En cours de contrôle'),
(4,'قيد الإلتزام','Under Engagement','En cours d\'engagement');

INSERT INTO T_02_06_03 (F_00, F_01, F_02, F_03, F_04) VALUES
(1,'مرحلة مداولات لجنة فتح الأظرفة وتقييم العروض','COPEO Amendment fileStage','Instance Dossier de l\'avenant',1),
(2,'مرحلة مداولات لجنة فتح الأظرفة وتقييم العروض','COPEO Committee Deliberation Stage','Instance PVs COPEO',1),
(3,'مرحلة موافقة القيادة العليا','High Command Approval Stage','Instance Accord du Haut Commandement',1),
(4,'إمضاء مشروع العقد/الفقة','Signature of the Amendment project','Signature du projet de l\'avenant',2),
(5,'إعداد ملف اللجنة القطاعية للصفقات','Preparation of CSM File','Préparation du dossier CSM',2),
(6,'مرحلة دراسة اللجنة القطاعية للصفقات','CSM Review Stage','Instance Examen de la CSM',3),
(7,'رفع التحفظات جارية','Lifting of Ongoing Reservations','Leveé des réserves en cours',3),
(8,'رفع التحفظات، في إنتظار مداولة اللجنة القطاعية للصفقات','Reservations Removed, CSM Deliberation Stage','Réserves leveés, instance délibiration de la CSM',3),
(9,'إعداد ملف الالتزام','Preparation of Commitment File','Préparation du dossier du dossier d\'engagement',3),
(10,'إرسال الملف للالتزام','File Sent for Commitment','Dossier transmis pour engagement',4);

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
