/**
 *	
 *	@author		: MEDJERAB ABIR
 *
 *	@Name		: Region
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Network
 *
 **/

package dz.mdn.iaas.network.common.model;

import dz.mdn.iaas.configuration.template.GenericModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Region Entity - Extends GenericModel
 * 
 * Database table: T_20_02
 * Primary key: F_00 (id) - inherited from GenericModel
 * 
 * Fields:
 * - F_00: id (inherited) - Primary key
 * - F_01: name - Region name (required)
 * - F_02: zone - Zone relationship (required)
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Region")
@Table(name="T_03_01_02", uniqueConstraints = { @UniqueConstraint(name="T_03_01_02_UK_01", columnNames={"F_01"}),
												@UniqueConstraint(name="T_03_01_02_UK_02", columnNames={"F_02"})})
public class Region extends GenericModel {

    @Column(name="F_01", length=100, nullable=false)
    private String name;

	@Column(name="F_02", length=10, nullable=false)
    private String code;

	@ManyToOne
    @JoinColumn(name = "F_03", foreignKey=@ForeignKey(name="T_03_01_02_FK_01"), nullable = false)
    private Location location;
	
	@ManyToOne
    @JoinColumn(name = "F_04", foreignKey=@ForeignKey(name="T_03_01_02_FK_02"), nullable = false)
    private Zone zone;
}
