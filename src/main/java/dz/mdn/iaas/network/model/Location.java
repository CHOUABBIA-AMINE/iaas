/**
 *	
 *	@author		: MEDJERAB ABIR
 *
 *	@Name		: Location
 *	@CreatedOn	: 26-06-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Goal		: Network
 *
 **/

package dz.mdn.iaas.network.model;

import dz.mdn.iaas.configuration.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table( name = "T_20_03", 
		uniqueConstraints = {
			@UniqueConstraint(name="T_20_03_UK_01", columnNames = "F_02")
		})
@Getter
@Setter
public class Location extends BaseEntity {

	@Column(name = "F_01", nullable = false, length = 100)
    private String name;

	@Column(name = "F_02", nullable = false, unique = true, length = 50)
    private String code;

	@Column(name = "F_03", nullable = false)
    private Double latitude;

	@Column(name = "F_04", nullable = false)
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "F_05", foreignKey=@ForeignKey(name="T_20_03_FK_01"), nullable = false)
    private Region region;

    //@OneToMany(mappedBy = "origin", cascade = CascadeType.ALL)
    //private List<PipelineSystem> outgoingOriginatingSystems;

    //@OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    //private List<PipelineSystem> arrivingOriginatingSystems;
    
}