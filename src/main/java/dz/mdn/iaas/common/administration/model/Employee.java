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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
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
@PrimaryKeyJoinColumn(name="F_00")
@Table(name="T_01_04_10")
public class Employee extends Person {
	
	@Column(name="F_11", length=50)
	private String registrationNumber;
	
	@ManyToOne
	@JoinColumn(name="F_12")
	private Job job;
	
	@ManyToOne
	@JoinColumn(name="F_13")
	private Structure structure;
	
	@ManyToOne
	@JoinColumn(name="F_14")
	private MilitaryRank militaryRank;
	
}
