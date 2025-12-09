/**
 *	
 *	@author		: MEDJERAB ABIR
 *
 *	@Name		: EquipmentType
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "T_20_06")
@Getter
@Setter
public class EquipmentType extends BaseEntity {

	@Column(name = "F_01", nullable = false, length = 100)
    private String name;

	@Column(name = "F_02", nullable = false, length = 500)
    private String description;

    @OneToMany(mappedBy = "equipmentType", cascade = CascadeType.ALL)
    private List<Equipment> equipments;
    
}