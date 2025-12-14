/**
 *	
 *	@author		: MEDJERAB ABIR
 *
 *	@Name		: Alloy
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
import dz.mdn.iaas.network.core.model.PipelineSegment;
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

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Alloy")
@Table(name="T_03_01_06", uniqueConstraints = {@UniqueConstraint(name="T_03_01_06_UK_01", columnNames = "F_01")})
public class Alloy extends GenericModel {

	@Column(name = "F_01", nullable = false, unique = true, length = 20)
    private String code;

    @Column(name = "F_02", nullable = true, length = 500)
    private String description;

    @OneToMany(mappedBy = "constructionMetal", cascade = CascadeType.ALL)
    private Set<PipelineSegment> pipelineSegments = new HashSet<>();

    @OneToMany(mappedBy = "innerCoating", cascade = CascadeType.ALL)
    private Set<PipelineSegment> segmentsInnerCaoting = new HashSet<>();

    @OneToMany(mappedBy = "outerCoating", cascade = CascadeType.ALL)
    private Set<PipelineSegment> segmentsOuterCaoting = new HashSet<>();
    
}