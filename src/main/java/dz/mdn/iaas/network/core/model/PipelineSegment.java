/**
 *	
 *	@author		: MEDJERAB ABIR
 *
 *	@Name		: PipelineSegment
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Network
 *
 **/

package dz.mdn.iaas.network.core.model;

import dz.mdn.iaas.configuration.template.GenericModel;
import dz.mdn.iaas.network.common.model.Alloy;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * PipelineSegment Entity - Extends GenericModel
 * 
 * Database table: T_03_02_07
 * Primary key: F_00 (id) - inherited from GenericModel
 * 
 * Fields:
 * - F_00: id (inherited) - Primary key
 * - F_01: code - Segment code (required)
 * - F_02: pipeline - Pipeline relationship (required)
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="PipelineSegment")
@Table(name="T_03_02_10")
public class PipelineSegment extends GenericModel {

    @Column(name="F_01", length=20, nullable=false)
    private String code;
    
    @Column(name="F_02", nullable=false)
    private Double diameter;

    @Column(name="F_03", nullable=false)
    private Double rugosity;

    @Column(name="F_04", nullable=false)
    private Double startPoint;

    @Column(name="F_05", nullable=false)
    private Double endPoint;

    @ManyToOne
    @JoinColumn(name="F_06", foreignKey=@ForeignKey(name="T_03_02_10_FK_01"), nullable=false)
    private Alloy constructionMaterial;

    @ManyToOne
    @JoinColumn(name="F_07", foreignKey=@ForeignKey(name="T_03_02_10_FK_01"), nullable=false)
    private Alloy internalCover;

    @ManyToOne
    @JoinColumn(name="F_08", foreignKey=@ForeignKey(name="T_03_02_10_FK_01"), nullable=false)
    private Alloy externalCover;

    @ManyToOne
    @JoinColumn(name="F_09", foreignKey=@ForeignKey(name="T_03_02_10_FK_01"), nullable=false)
    private Pipeline pipeline;
}
