/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: BudgetType
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.model;

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
@Entity(name="BudgetType")
@Table(name="T_02_02_01", uniqueConstraints = { @UniqueConstraint(name = "T_02_02_01_UK_01", columnNames = { "F_03" }), 
											 	@UniqueConstraint(name = "T_02_02_01_UK_02", columnNames = { "F_06" })})
public class BudgetType {
	
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
	
	@Column(name="F_04", length=20)
	private String acronymAr;
	
	@Column(name="F_05", length=20)
	private String acronymEn;
	
	@Column(name="F_06", length=20, nullable=false)
	private String acronymFr;

}

/*
INSERT INTO T_02_02_01 (F_00, F_04, F_05, F_06, F_01, F_02, F_03) VALUES
(1, 'تسيير', 'F', 'F', 'ميزانية التسيير', 'Functional Budget', 'Budget de Fonctionnement'),
(2, 'تجهيز', 'E', 'E', 'ميزانية التجهيز', 'Equipment Budget', 'Budget d\'Equipement');
*/