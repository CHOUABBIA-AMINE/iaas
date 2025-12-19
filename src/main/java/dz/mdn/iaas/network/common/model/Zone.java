/**
 *	
 *	@author		: MEDJERAB ABIR
 *
 *	@Name		: Zone
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Network
 *
 **/

package dz.mdn.iaas.network.common.model;

import java.util.HashSet;
import java.util.Set;

import dz.mdn.iaas.configuration.template.GenericModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Zone Entity - Extends GenericModel
 * 
 * Database table: T_20_01
 * Primary key: F_00 (id) - inherited from GenericModel
 * 
 * Fields:
 * - F_00: id (inherited) - Primary key
 * - F_01: name - Zone name (required)
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Zone")
@Table(name="T_03_02_01", uniqueConstraints = { @UniqueConstraint(name="T_03_01_02_UK_01", columnNames={"F_01"}),
												@UniqueConstraint(name="T_03_01_02_UK_02", columnNames={"F_02"})})

public class Zone extends GenericModel {

    @Column(name="F_01", length=50, nullable=false)
    private String name;
    
    @Column(name="F_02", length=10, nullable=false)
    private String code;
    
    @Column(name="F_03", length=200, nullable=true)
    private String decsription;

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL)
    private Set<Region> regions = new HashSet<>();
}
