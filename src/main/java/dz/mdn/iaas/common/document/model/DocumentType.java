/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: DocumentType
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Model
 *	@Layer		: Common / Document
 *	@Package	: Common / Document / Model
 *
 **/

package dz.mdn.iaas.common.document.model;

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

/**
 * DocumentType Entity - Extends GenericModel
 * Represents types of documents with multilingual designations
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="DocumentType")
@Table(name="T_01_03_01", uniqueConstraints = { @UniqueConstraint(name = "T_01_03_01_UK_01", columnNames = { "F_03" }),
											    @UniqueConstraint(name = "T_01_03_01_UK_02", columnNames = { "F_04" }),
											    @UniqueConstraint(name = "T_01_03_01_UK_03", columnNames = { "F_05" }),
											    @UniqueConstraint(name = "T_01_03_01_UK_04", columnNames = { "F_06" })})
public class DocumentType extends GenericModel {
	
	@Column(name="F_01", length=100)
	private String designationAr;

	@Column(name="F_02", length=100)
	private String designationEn;
	
	@Column(name="F_03", length=100, nullable=false)
	private String designationFr;
	
	@Column(name="F_04", length=100)
	private String acronymAr;

	@Column(name="F_05", length=100)
	private String acronymEn;
	
	@Column(name="F_06", length=100, nullable=false)
	private String acronymFr;
	
	@Column(name="F_07", length=10, nullable=false)
	private String code;
	
	@Column(name="F_08")
	private Boolean requiresApproval;
	
	@Column(name="F_09")
	private Boolean isConfidential;
}
