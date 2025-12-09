/**
 *	
 *	@author		: MEDJERAB ABIR
 *
 *	@Name		: PipelineSegment
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
@Table( name = "T_20_12", 
		uniqueConstraints = {
			@UniqueConstraint(name="T_20_12_UK_01", columnNames = "F_01")
		})
@Getter
@Setter
public class PipelineSegment extends BaseEntity {

	@Column(name = "F_01", nullable = false, unique = true, length = 50)
    private String code;

    @Column(name = "F_02", nullable = false)
    private Double startPoint; // in km from origin

    @Column(name = "F_03", nullable = false)
    private Double endPoint; // in km from origin

    @Column(name = "F_04", nullable = false, length = 50)
    private String terrainType;

    @ManyToOne
    @JoinColumn(name = "F_05", foreignKey=@ForeignKey(name="T_20_12_FK_01"), nullable = false)
    private Pipeline pipeline;
    
}