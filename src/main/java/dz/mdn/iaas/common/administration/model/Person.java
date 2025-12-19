/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: Person
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Model
 *	@Layer		: Common / Administration
 *	@Package	: Common / Administration / Model
 *
 **/

package dz.mdn.iaas.common.administration.model;

import java.util.Date;

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
 * Person Entity - Extends GenericModel
 * Represents individuals with personal information
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Person")
@Table(name="T_01_04_09")
public class Person extends GenericModel {
	
	@Column(name="F_01", length=100, nullable=false)
	private String lastNameAr;
	
	@Column(name="F_02", length=100, nullable=false)
	private String firstNameAr;
	
	@Column(name="F_03", length=100, nullable=false)
	private String lastNameLt;
	
	@Column(name="F_04", length=100, nullable=false)
	private String firstNameLt;
	
	@Column(name="F_05")
	private Date birthDate;
	
	@Column(name="F_06", length=100)
	private String birthPlace;
	
	@ManyToOne
	@JoinColumn(name="F_07")
	private Country nationality;
}
