/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: Facility
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Network
 *
 **/

package dz.mdn.iaas.network.core.model;

import dz.mdn.iaas.network.common.model.Location;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
 * Facility Entity - Extends GenericModel
 * 
 * Database table: T_20_08
 * Primary key: F_00 (id) - inherited from GenericModel
 * 
 * Fields:
 * - F_00: id (inherited) - Primary key
 * - F_01: name - Facility name (required)
 * - F_02: code - Facility code (unique, required)
 * - F_03: operationalStatus - Operational status relationship (required)
 * - F_04: location - Location relationship (required)
 * - F_05: facilityType - Facility type relationship (required)
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Facility")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="T_03_02_04", uniqueConstraints = { @UniqueConstraint(name="T_03_02_04_UK_01", columnNames={"F_02"}) })
public class Facility extends Infrastructure {

    @Column(name = "F_07", nullable = true, length = 100)
    private String provider;

    @ManyToOne
    @JoinColumn(name="F_08", foreignKey=@ForeignKey(name="T_03_02_03_FK_02"), nullable=false)
    private Location location;
/*
    @ManyToOne
    @JoinColumn(name="F_09", foreignKey=@ForeignKey(name="T_03_02_03_FK_03"), nullable=false)
    private FacilityType facilityType;

    @OneToMany(mappedBy = "departureFacility", cascade = CascadeType.ALL)
    private Set<Pipeline> departingPipelines = new HashSet<>();

    @OneToMany(mappedBy = "arrivalFacility", cascade = CascadeType.ALL)
    private Set<Pipeline> arrivingPipelines = new HashSet<>();

    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL)
    private Set<Equipment> equipments = new HashSet<>();
*/
}
