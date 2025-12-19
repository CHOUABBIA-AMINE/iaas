/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: Employee
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Model
 *	@Layer		: Common / Administration
 *	@Package	: Common / Administration / Model
 *
 **/

package dz.mdn.iaas.common.administration.model;

import dz.mdn.iaas.configuration.template.GenericModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Employee Entity - Extends GenericModel
 * Represents employees with their job, structure, and military information
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Employee")
@Table(name="T_01_04_10")
public class Employee extends GenericModel {
	
	@ManyToOne
	@JoinColumn(name="F_01", nullable=false)
	private Person person;
	
	@ManyToOne
	@JoinColumn(name="F_02")
	private Job job;
	
	@ManyToOne
	@JoinColumn(name="F_03")
	private Structure structure;
	
	@ManyToOne
	@JoinColumn(name="F_04")
	private MilitaryRank rank;
	
	@Column(name="F_05", length=50)
	private String registrationNumber;
	
	@Column(name="F_06")
	private Boolean isActive;
}
