/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: DocumentType
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Document
 *
 **/

package dz.mdn.iaas.common.document.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="DocumentType")
@Table(name="T_01_03_01", uniqueConstraints = { @UniqueConstraint(name = "T_01_03_01_UK_01", columnNames = { "F_03", "F_04" })})
public class DocumentType {
	
	@Id
	@Column(name="F_00")
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="F_01", length=200)
	private String designationAr;

	@Column(name="F_02", length=200)
	private String designationEn;
	
	@Column(name="F_03", length=200)
	private String designationFr;
	
	@Column(name="F_04")
	private int scope;
	
	@OneToMany(mappedBy="documentType")
    private List<Document> documents;

}
/*
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
*/