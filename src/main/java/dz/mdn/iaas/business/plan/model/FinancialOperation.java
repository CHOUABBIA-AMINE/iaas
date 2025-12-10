/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FinancialOperation
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.model;

import dz.mdn.iaas.configuration.template.GenericModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="FinancialOperation")
@Table(name="T_02_02_05", uniqueConstraints = { @UniqueConstraint(name = "T_02_02_05_UK_01", columnNames = { "F_02" })})
public class FinancialOperation extends GenericModel {
	
	@Column(name="F_01", length=20)
	private String code;
	
	@Column(name="F_02", length=200, nullable=false)
	private String designationFr;

}
