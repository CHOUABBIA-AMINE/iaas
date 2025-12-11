/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: Structure
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
 * Structure Entity - Extends GenericModel
 * Represents organizational structures with multilingual designations and hierarchy
 */
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Structure")
@Table(name="T_01_04_09", uniqueConstraints = { 
    @UniqueConstraint(name = "T_01_04_09_UK_01", columnNames = { "F_06" })
})
public class Structure extends GenericModel {
	
	@Column(name="F_01", length=100)
	private String designationAr;

	@Column(name="F_02", length=100)
	private String designationEn;
	
	@Column(name="F_03", length=100, nullable=false)
	private String designationFr;
	
	@ManyToOne
	@JoinColumn(name="F_04")
	private Structure parentStructure;
	
	@ManyToOne
	@JoinColumn(name="F_05", nullable=false)
	private StructureType structureType;
	
	@Column(name="F_06", length=50, nullable=false)
	private String code;
}
