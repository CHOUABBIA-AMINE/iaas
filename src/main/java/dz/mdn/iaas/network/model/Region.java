/**
 *	
 *	@author		: MEDJERAB ABIR
 *
 *	@Name		: Region
 *	@CreatedOn	: 26-06-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Goal		: Network
 *
 **/

package dz.mdn.iaas.network.model;

import java.util.List;

import dz.mdn.iaas.configuration.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table( name = "T_20_02",
	    uniqueConstraints = {
		@UniqueConstraint(name="T_20_02_UK_01", columnNames = "F_02"),
	})
@Getter
@Setter
public class Region extends BaseEntity {

	@Column(name = "F_01", nullable = false, length = 100)
    private String name;

	@Column(name = "F_02", nullable = false, length = 25)
    private String code;
	
	@ManyToOne
    @JoinColumn(name = "F_03", foreignKey=@ForeignKey(name="T_20_02_FK_01"), nullable = false)
    private Zone zone;
	
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private List<Location> locations;
    
}