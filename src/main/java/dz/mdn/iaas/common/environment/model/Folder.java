/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: Folder
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *	@Type		: Model
 *	@Layer		: Common / Environment
 *	@Package	: Common / Environment / Model
 *
 **/

package dz.mdn.iaas.common.environment.model;

import dz.mdn.iaas.configuration.template.GenericModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
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
 * Folder Entity - Extends GenericModel
 * Represents folders inside archive boxes
 */
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Folder")
@Table(name="T_01_01_08", uniqueConstraints = { 
    @UniqueConstraint(name = "T_01_01_08_UK_01", columnNames = { "F_01" })
})
public class Folder extends GenericModel {
	
	@Column(name="F_01", length=50, nullable=false)
	private String code;
	
	@Column(name="F_02", length=200)
	private String description;
	
	@ManyToOne
    @JoinColumn(name="F_03", foreignKey=@ForeignKey(name="T_01_01_08_FK_01"), nullable=false)
    private ArchiveBox archiveBox;
	
	@Column(name="F_04")
	private Integer folderOrder;
}
