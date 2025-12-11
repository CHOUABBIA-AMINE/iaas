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

package dz.mdn.iaas.network.model;

import dz.mdn.iaas.configuration.template.GenericModel;
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
 * Database table: T_20_13
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
@Table(name="T_20_13")
public class PipelineSegment extends GenericModel {

    @Column(name="F_01", length=50, nullable=false)
    private String code;

    @ManyToOne
    @JoinColumn(name="F_02", foreignKey=@ForeignKey(name="T_20_13_FK_01"), nullable=false)
    private Pipeline pipeline;
}
