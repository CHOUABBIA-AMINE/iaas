/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RealizationStatus
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Pakage		: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity(name="RealizationStatus")
@Table(name="T_02_01_05", uniqueConstraints = { @UniqueConstraint(name = "T_02_01_05_UK_01", columnNames = { "F_03" })})
public class ProcurementStatus {
	
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

}
/*
INSERT INTO T_02_01_05 (F_00, F_01, F_02, F_03) VALUES
(1,'قيد الانتظار','On Hold','En Instance'),
(2,'قيد التنفيذ','Ongoing','En cours'),
(3,'غيرمجدي','Defective','Infrectieux'),
(4,'مُنجز','Finalized','Finalisé'),
(5,'ملغي','Canceled','Annulé'),
(6,'مؤجل','Deferred','Différé'),
(7,'منقول','Transferred','Transféré');
*/