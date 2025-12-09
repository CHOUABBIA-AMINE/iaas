/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentStep
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Amendment
 *
 **/

package dz.mdn.iaas.business.amendment.model;

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
@Entity(name="AmendmentStep")
@Table(name="T_02_06_03", uniqueConstraints = { @UniqueConstraint(name = "T_02_06_03_UK_01", columnNames = { "F_03" })})
public class AmendmentStep {
	
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
    @JoinColumn(name="F_04", foreignKey=@ForeignKey(name="T_02_06_03_FK_01"), nullable=false)
    private AmendmentPhase amendmentPhase;

}
/*
INSERT INTO T_02_06_03 (F_00, F_01, F_02, F_03, F_04) VALUES
(1,'مرحلة مداولات لجنة فتح الأظرفة وتقييم العروض','COPEO Amendment fileStage','Instance Dossier de l'avenant',1),
(2,'مرحلة مداولات لجنة فتح الأظرفة وتقييم العروض','COPEO Committee Deliberation Stage','Instance PVs COPEO',1),
(3,'مرحلة موافقة القيادة العليا','High Command Approval Stage','Instance Accord du Haut Commandement',1),
(4,'إمضاء مشروع العقد/الفقة','Signature of the Amendment project','Signature du projet de l'avenant',2),
(5,'إعداد ملف اللجنة القطاعية للصفقات','Preparation of CSM File','Préparation du dossier CSM',2),
(6,'مرحلة دراسة اللجنة القطاعية للصفقات','CSM Review Stage','Instance Examen de la CSM',3),
(7,'رفع التحفظات جارية','Lifting of Ongoing Reservations','Leveé des réserves en cours',3),
(8,'رفع التحفظات، في إنتظار مداولة اللجنة القطاعية للصفقات','Reservations Removed, CSM Deliberation Stage','Réserves leveés, instance délibiration de la CSM',3),
(9,'إعداد ملف الالتزام','Preparation of Commitment File','Préparation du dossier du dossier d'engagement',3),
(10,'إرسال الملف للالتزام','File Sent for Commitment','Dossier transmis pour engagement',4);
*/