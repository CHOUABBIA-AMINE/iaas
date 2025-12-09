/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProjectPhase
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity(name="ConsultationStep")
@Table(name="T_02_04_03", uniqueConstraints = { @UniqueConstraint(name = "T_02_04_03_UK_01", columnNames = { "F_03" })})
public class ConsultationStep {
	
	@Id
	@Column(name="F_00")
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="F_01", length=200)
	private String designationAr;

	@Column(name="F_02", length=200)
	private String designationEn;
	
	@Column(name="F_03", length=200, nullable=false)
	private String designationFr;
	
	@ManyToOne
    @JoinColumn(name="F_04", foreignKey=@ForeignKey(name="T_02_04_03_FK_01"), nullable=false)
    private ConsultationPhase consultationPhase;

}
/*
INSERT INTO T_02_04_03 (F_00, F_01, F_02, F_03, F_04) VALUES
(1,'مرحلة إعداد المخطط الميزانياتي','Budget Plan Maturation Stage','Instance de maturation de plan budgétaire'),
(2,'مرحلة تحديد الإحتياجات','Needs Identification Stage','Instance du besoin'),
(3,'مرحلة إعداد البطاقة التقنية','Technical Data Sheet Preparation Stage','Instance de la fiche technique'),
(4,'مرحلة موافقة القيادة العليا','High Command Approval Stage','Instance Accord du Haut Commandement'),
(5,'مرحلة إبداء الرأي','Opinion Stage','Instance d'avis'),
(6,'إعداد ملف اللجنة القطاعية للصفقات (CSM)','Preparation of CSM File','Préparation du dossier CSM'),
(7,'مرحلة دراسة اللجنة القطاعية للصفقات (CSM)','CSM Review Stage','Instance Examen de la CSM'),
(8,'رفع التحفظات جارية','Lifting of Ongoing Reservations','Leveé des réserves en cours'),
(9,'رفع التحفظات، في إنتظار مداولة اللجنة القطاعية للصفقات (CSM)','Reservations Removed, CSM Deliberation Stage','Réserves leveés, instance délibiration de la CSM'),
(10,'إعداد ملف النشر','Preparation of Publish File','Préparation du dossier de publication'),
(11,'مرحلة النشر','Publish Stage','Instance publication'),
(12,'مرحلة إيداع العروض','Submission of Offers Stage','Instance dépôt des offres'),
(13,'تحويل الملف إلى لجنة فتح الأظرفة وتقييم العروض (COPEO)','File Transmitted to COPEO','Dossier transmis à la COPEO');
*/