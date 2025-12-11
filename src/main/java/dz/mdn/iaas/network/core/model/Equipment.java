/**
 *	
 *	@author		: MEDJERAB ABIR
 *
 *	@Name		: Equipment
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Network
 *
 **/

package dz.mdn.iaas.network.model;

import dz.mdn.iaas.configuration.template.GenericModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * Equipment Entity - Extends GenericModel
 * 
 * Database table: T_20_09
 * Primary key: F_00 (id) - inherited from GenericModel
 * 
 * Fields:
 * - F_00: id (inherited) - Primary key
 * - F_01: name - Equipment name (required)
 * - F_02: code - Equipment code (required)
 * - F_03: manufacturer - Manufacturer name (required)
 * - F_04: modelNumber - Model number (required)
 * - F_05: serialNumber - Serial number (required)
 * - F_06: manufacturingDate - Manufacturing date (required)
 * - F_07: installationDate - Installation date (required)
 * - F_08: lastMaintenanceDate - Last maintenance date (required)
 * - F_09: operationalStatus - Operational status relationship (required)
 * - F_10: equipmentType - Equipment type relationship (required)
 * - F_11: facility - Facility relationship (required)
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Equipment")
@Table(name="T_20_09")
public class Equipment extends GenericModel {

    @Column(name="F_01", length=100, nullable=false)
    private String name;

    @Column(name="F_02", length=50, nullable=false)
    private String code;

    @Column(name="F_03", length=100, nullable=false)
    private String manufacturer;

    @Column(name="F_04", length=50, nullable=false)
    private String modelNumber;

    @Column(name="F_05", length=100, nullable=false)
    private String serialNumber;

    @Column(name="F_06", nullable=false)
    private LocalDate manufacturingDate;

    @Column(name="F_07", nullable=false)
    private LocalDate installationDate;

    @Column(name="F_08", nullable=false)
    private LocalDate lastMaintenanceDate;

    @ManyToOne
    @JoinColumn(name="F_09", foreignKey=@ForeignKey(name="T_20_09_FK_01"), nullable=false)
    private OperationalStatus operationalStatus;

    @ManyToOne
    @JoinColumn(name="F_10", foreignKey=@ForeignKey(name="T_20_09_FK_02"), nullable=false)
    private EquipmentType equipmentType;

    @ManyToOne
    @JoinColumn(name="F_11", foreignKey=@ForeignKey(name="T_20_09_FK_03"), nullable=false)
    private Facility facility;
}
