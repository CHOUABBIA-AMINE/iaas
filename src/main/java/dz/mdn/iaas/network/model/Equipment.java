/**
 *	
 *	@author		: MEDJERAB ABIR
 *
 *	@Name		: Equipment
 *	@CreatedOn	: 26-06-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Goal		: Network
 *
 **/

package dz.mdn.iaas.network.model;

import java.time.LocalDate;

import dz.mdn.iaas.configuration.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "T_20_09")
@Getter
@Setter
public class Equipment extends BaseEntity {

	@Column(name = "F_01", nullable = false, length = 100)
    private String name;

	@Column(name = "F_02", nullable = false, length = 50)
    private String code;

	@Column(name = "F_03", nullable = false, length = 100)
    private String manufacturer;

	@Column(name = "F_04", nullable = false, length = 50)
    private String modelNumber;

	@Column(name = "F_05", nullable = false, length = 100)
    private String serialNumber;

	@Column(name = "F_06", nullable = false)
    private LocalDate manufacturingDate;

	@Column(name = "F_07", nullable = false)
    private LocalDate installationDate;

	@Column(name = "F_08", nullable = false)
    private LocalDate lastMaintenanceDate;

    @ManyToOne
    @JoinColumn(name = "F_09", foreignKey=@ForeignKey(name="T_20_09_FK_01"), nullable = false)
    private OperationalStatus operationalStatus;

    @ManyToOne
    @JoinColumn(name = "F_10", foreignKey=@ForeignKey(name="T_20_09_FK_02"), nullable = false)
    private EquipmentType equipmentType; // PUMP, COMPRESSOR, VALVE, METER

    @ManyToOne
    @JoinColumn(name = "F_11", foreignKey=@ForeignKey(name="T_20_09_FK_03"), nullable = false)
    private Facility facility;
    
}