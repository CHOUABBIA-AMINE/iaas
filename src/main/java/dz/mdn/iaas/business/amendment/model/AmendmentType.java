/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentType
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
@Entity(name="AmendmentType")
@Table(name="T_02_06_01", uniqueConstraints = { @UniqueConstraint(name = "T_02_06_01_UK_01", columnNames = { "F_03" })})
public class AmendmentType {
	
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
INSERT INTO T_02_06_01 (F_00, F_01, F_02, F_03) VALUES
(1,'ملحق عقد','Amendment','Avenant'),
(2,'ملحق تعديل','Adjustment Amendment','Avenant d\'Ajustement'),
(3,'ملحق نقل الحقوق والالتزامات','Amendment for Transfer of Rights and Obligations','Avenant de transfert des droits et des obligations'),
(4,'ملحق تعديل الكميات النهائية','Amendment for Adjustment of Final Quantities','Avenant d\'Ajustement des Quantités Définitives');
*/