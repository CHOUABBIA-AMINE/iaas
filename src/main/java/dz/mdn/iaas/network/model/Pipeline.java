/**
 *	
 *	@author		: MEDJERAB ABIR
 *
 *	@Name		: Pipeline
 *	@CreatedOn	: 26-06-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Goal		: Network
 *
 **/

package dz.mdn.iaas.network.model;

import java.time.LocalDate;
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
import jakarta.persistence.JoinTable;
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
@Table( name = "T_20_11", 
		uniqueConstraints = {
			@UniqueConstraint(name="T_20_11_UK_01", columnNames = "F_01")
		})
@Getter
@Setter
public class Pipeline extends BaseEntity {

	@Column(name = "F_01", nullable = false, unique = true, length = 50)
    private String code;

	@Column(name = "F_02", nullable = false)
    private Double diameter;

	@Column(name = "F_03", nullable = false)
    private Double length;

	@Column(name = "F_04", nullable = false)
    private Double maxPressure;

	@Column(name = "F_05", nullable = false, length = 50)
    private String constructionMaterial;

	@Column(name = "F_06", nullable = false)
    private LocalDate commissioningDate;

    @ManyToOne
    @JoinColumn(name = "F_07", foreignKey=@ForeignKey(name="T_20_11_FK_01"), nullable = false)
    private OperationalStatus operationalStatus;

    @ManyToOne
    @JoinColumn(name = "F_08", foreignKey=@ForeignKey(name="T_20_11_FK_02"), nullable = false)
    private PipelineSystem pipelineSystem;
    
    @ManyToOne
    @JoinColumn(name = "F_09", foreignKey=@ForeignKey(name="T_20_11_FK_03"), nullable = false)
    private Facility departureTerminal;
    
    @ManyToOne
    @JoinColumn(name = "F_10", foreignKey=@ForeignKey(name="T_20_11_FK_04"), nullable = false)
    private Facility arrivalTerminal;

    @OneToMany(mappedBy = "pipeline", cascade = CascadeType.ALL)
    private List<PipelineSegment> pipelineSegments;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "R_20_08__20_11",
            joinColumns = @JoinColumn(name = "F_01", foreignKey=@ForeignKey(name="R_20_08__20_11_FK_01")),
            inverseJoinColumns = @JoinColumn(name = "F_02", foreignKey=@ForeignKey(name="R_20_08__20_11_FK_02")))
    private Set<Facility> facilities = new HashSet<>();

    /*@OneToMany(mappedBy = "pipeline", cascade = CascadeType.ALL)
    private List<PipelineInspection> inspections;*/
    
}