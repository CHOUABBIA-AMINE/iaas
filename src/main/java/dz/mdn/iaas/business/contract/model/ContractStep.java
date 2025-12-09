/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractStep
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.model;

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
@Entity(name="ContractStep")
@Table(name="T_02_05_03", uniqueConstraints = { @UniqueConstraint(name = "T_02_05_03_UK_01", columnNames = { "F_03" })})
public class ContractStep {
	
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
    @JoinColumn(name="F_04", foreignKey=@ForeignKey(name="T_02_05_03_FK_01"), nullable=false)
    private ContractPhase contractPhase;

}
/*
INSERT INTO T_02_05_03 (F_00, F_01, F_02, F_03, F_04) VALUES
(1,'مرحلة مداولات لجنة فتح الأظرفة وتقييم العروض','COPEO Committee Deliberation Stage','Instance PVs COPEO',1),
(2,'إمضاء مشروع العقد/الفقة','Signature of the contract project','Signature du projet du contrat/marché',2),
(3,'إعداد ملف اللجنة القطاعية للصفقات','Preparation of CSM File','Préparation du dossier CSM',2),
(4,'مرحلة دراسة اللجنة القطاعية للصفقات','CSM Review Stage','Instance Examen de la CSM',3),
(5,'رفع التحفظات جارية','Lifting of Ongoing Reservations','Leveé des réserves en cours',3),
(6,'رفع التحفظات، في إنتظار مداولة اللجنة القطاعية للصفقات','Reservations Removed, CSM Deliberation Stage','Réserves leveés, instance délibiration de la CSM',3),
(7,'إعداد ملف الالتزام','Preparation of Commitment File','Préparation du dossier du dossier d'engagement',3),
(8,'إرسال الملف للالتزام','File Sent for Commitment','Dossier transmis pour engagement',4);
*/