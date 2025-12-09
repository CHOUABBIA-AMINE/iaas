/**
 *	
 *	@author		: MEDJERAB ABIR
 *
 *	@Name		: Product
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
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table( name = "T_20_04", 
		uniqueConstraints = {
			@UniqueConstraint(name="T_20_04_UK_01", columnNames = "F_02")
		})
@Getter
@Setter
public class Product extends BaseEntity {

	@Column(name = "F_01", nullable = false, length = 100)
    private String name;

	@Column(name = "F_02", nullable = false, unique = true, length = 50)
    private String code;

    @Column(name = "F_03", nullable = false)
    private Double density;

    @Column(name = "F_04", nullable = false)
    private Double viscosity;

    @Column(name = "F_05", nullable = false)
    private Double flashPoint;

    @Column(name = "F_06", nullable = false)
    private Double sulfurContent;

    @Column(name = "F_07", nullable = false)
    private Boolean isHazardous;

    @Column(name = "F_08", nullable = false, length = 500)
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<PipelineSystem> pipelineSystems;
    
}