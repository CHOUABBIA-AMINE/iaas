/**
 *	
 *	@author		: MEDJERAB ABIR
 *
 *	@Name		: Infrastructure
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Network
 *
 **/

package dz.mdn.iaas.network.common.model;

import java.time.LocalDate;

import dz.mdn.iaas.configuration.template.GenericModel;
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

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="OperationalStatus")
@Table(name="T_03_01_00", uniqueConstraints = { @UniqueConstraint(name="T_03_02_05_UK_01", columnNames={"F_01"})})
public abstract class Infrastructure extends GenericModel {

    @Column(name = "F_01", nullable = false, length = 100)
    private String code;

    @Column(name = "F_02", nullable = false, length = 100)
    private String name;
    
    @Column(name = "F_03", nullable = true)
    private LocalDate installationDate;

    @Column(name = "F_04", nullable = true)
    private LocalDate commissioningDate;

    @Column(name = "F_05", nullable = true)
    private LocalDate retirementDate;

    @ManyToOne
    @JoinColumn(name="F_06", foreignKey=@ForeignKey(name="T_03_02_05_FK_01"), nullable=false)
    private OperationalStatus status;
}
