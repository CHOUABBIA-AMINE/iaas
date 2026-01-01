/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: Employee
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 01-01-2026
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
 * Employee Entity - Extends Person
 * Represents employees with their job, structure, and military information
 * 
 * Uses JOINED inheritance strategy:
 * - Person data is stored in T_01_04_09 (parent table)
 * - Employee-specific data is stored in T_01_04_10 (child table)
 * - Both tables share the same primary key (F_00)
 * - The join between tables is established via @PrimaryKeyJoinColumn
 * 
 * The Employee table (T_01_04_10) has:
 * - F_00: Primary key (inherited from Person, also acts as FK)
 * - F_11: Registration Number
 * - F_12: Job ID (Foreign Key to Job table)
 * - F_13: Structure ID (Foreign Key to Structure table)
 * - F_14: Military Rank ID (Foreign Key to Military Rank table)
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Employee")
@Table(name="T_01_04_10")
@PrimaryKeyJoinColumn(name="F_00", referencedColumnName="F_00")
public class Employee extends Person {
	
	@Column(name="F_11", length=50)
	private String registrationNumber;
	
	@ManyToOne
	@JoinColumn(name="F_12", referencedColumnName="F_00")
	private Job job;
	
	@ManyToOne
	@JoinColumn(name="F_13", referencedColumnName="F_00")
	private Structure structure;
	
	@ManyToOne
	@JoinColumn(name="F_14", referencedColumnName="F_00")
	private MilitaryRank militaryRank;
	
}
