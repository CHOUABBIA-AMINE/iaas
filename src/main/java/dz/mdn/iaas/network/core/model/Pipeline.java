/**
 *	
 *	@author		: MEDJERAB ABIR
 *
 *	@Name		: Pipeline
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Network
 *
 **/

package dz.mdn.iaas.network.core.model;

import java.time.LocalDate;

import dz.mdn.iaas.configuration.template.GenericModel;
import dz.mdn.iaas.network.common.model.OperationalStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Pipeline Entity - Extends GenericModel
 * 
 * Database table: T_20_11
 * Primary key: F_00 (id) - inherited from GenericModel
 * 
 * Fields:
 * - F_00: id (inherited) - Primary key
 * - F_01: code - Pipeline code (unique, required)
 * - F_02: diameter - Pipeline diameter (required)
 * - F_03: length - Pipeline length in km (required)
 * - F_04: maxPressure - Maximum operating pressure (required)
 * - F_05: constructionMaterial - Material type (required)
 * - F_06: commissioningDate - Commissioning date (required)
 * - F_07: operationalStatus - Operational status relationship (required)
 * - F_08: pipelineSystem - Pipeline system relationship (required)
 * - F_09: departureTerminal - Departure facility relationship (required)
 * - F_10: arrivalTerminal - Arrival facility relationship (required)
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Pipeline")
@Table(name="T_20_11", uniqueConstraints = { @UniqueConstraint(name="T_20_11_UK_01", columnNames={"F_01"}) })
public class Pipeline extends GenericModel {

    @Column(name="F_01", length=50, nullable=false)
    private String code;

    @Column(name="F_02", nullable=false)
    private Double diameter;

    @Column(name="F_03", nullable=false)
    private Double length;

    @Column(name="F_04", nullable=false)
    private Double maxPressure;

    @Column(name="F_05", length=50, nullable=false)
    private String constructionMaterial;

    @Column(name="F_06", nullable=false)
    private LocalDate commissioningDate;

    @ManyToOne
    @JoinColumn(name="F_07", foreignKey=@ForeignKey(name="T_20_11_FK_01"), nullable=false)
    private OperationalStatus operationalStatus;

    @ManyToOne
    @JoinColumn(name="F_08", foreignKey=@ForeignKey(name="T_20_11_FK_02"), nullable=false)
    private PipelineSystem pipelineSystem;

    @ManyToOne
    @JoinColumn(name="F_09", foreignKey=@ForeignKey(name="T_20_11_FK_03"), nullable=false)
    private Facility departureTerminal;

    @ManyToOne
    @JoinColumn(name="F_10", foreignKey=@ForeignKey(name="T_20_11_FK_04"), nullable=false)
    private Facility arrivalTerminal;
}
