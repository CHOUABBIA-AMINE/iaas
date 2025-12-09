/**
 *	
 *	@author		: MEDJERAB ABIR
 *
 *	@Name		: Facility
 *	@CreatedOn	: 26-06-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Goal		: Network
 *
 **/

package dz.mdn.iaas.network.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dz.mdn.iaas.configuration.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table( name = "T_20_08", 
		uniqueConstraints = {
			@UniqueConstraint(name="T_20_08_UK_01", columnNames = "F_02")
		})
@Getter
@Setter
public class Facility extends BaseEntity {

	@Column(name = "F_01", nullable = false, length = 100)
    private String name;

	@Column(name = "F_02", nullable = false, unique = true, length = 50)
    private String code;

    @ManyToOne
    @JoinColumn(name = "F_03", foreignKey=@ForeignKey(name="T_20_08_FK_01"), nullable = false)
    private OperationalStatus operationalStatus;

    @ManyToOne
    @JoinColumn(name = "F_04", foreignKey=@ForeignKey(name="T_20_08_FK_02"), nullable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "F_05", foreignKey=@ForeignKey(name="T_20_08_FK_03"), nullable = false)
    private FacilityType facilityType;

    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL)
    private List<Equipment> equipments;
    
    @ManyToMany(mappedBy = "producers", fetch = FetchType.LAZY)
    private Set<Pipeline> pipelines = new HashSet<>();
    
}