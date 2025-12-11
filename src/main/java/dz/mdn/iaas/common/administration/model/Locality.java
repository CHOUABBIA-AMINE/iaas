/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: Locality
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
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Locality Entity - Extends GenericModel
 * Represents localities/cities with multilingual designations
 */
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Locality")
@Table(name="T_01_04_03", uniqueConstraints = { 
    @UniqueConstraint(name = "T_01_04_03_UK_01", columnNames = { "F_04" })
})
public class Locality extends GenericModel {
	
	@Column(name="F_01", length=100)
	private String designationAr;

	@Column(name="F_02", length=100)
	private String designationEn;
	
	@Column(name="F_03", length=100, nullable=false)
	private String designationFr;
	
	@Column(name="F_04", length=10, nullable=false)
	private String code;
	
	@ManyToOne
	@JoinColumn(name="F_05", nullable=false)
	private State state;
}
