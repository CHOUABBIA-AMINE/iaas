/**
 *	
 *	@author		: MEDJERAB ABIR
 *
 *	@Name		: PipelineSystem
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
@Table( name = "T_20_10", 
		uniqueConstraints = {
			@UniqueConstraint(name="T_20_10_UK_01", columnNames = "F_02")
		})
@Getter
@Setter
public class PipelineSystem extends BaseEntity {

	@Column(name = "F_01", nullable = false, length = 100)
    private String name;

	@Column(name = "F_02", nullable = false, unique = true, length = 50)
    private String code;

    @ManyToOne
    @JoinColumn(name = "F_03", foreignKey=@ForeignKey(name="T_20_10_FK_01"), nullable = false)
    private Product product;

    @OneToMany(mappedBy = "pipelineSystem", cascade = CascadeType.ALL)
    private List<Pipeline> pipelines;

    /*@Column(name = "F_03", nullable = false)
    private Double totalLength;

    @Column(name = "F_04", nullable = false)
    private Double designCapacity;

    @Column(name = "F_05", nullable = false)
    private LocalDate operationalSince;*/

    /*@ManyToOne
    @JoinColumn(name = "F_07", foreignKey=@ForeignKey(name="T_20_08_FK_02"), nullable = false)
    private Location origin;

    @ManyToOne
    @JoinColumn(name = "F_08", foreignKey=@ForeignKey(name="T_20_08_FK_03"), nullable = false)
    private Location destination;

    @OneToMany(mappedBy = "pipelineSystem", cascade = CascadeType.ALL)
    private List<SystemOperation> systemeOperations;*/
    
}