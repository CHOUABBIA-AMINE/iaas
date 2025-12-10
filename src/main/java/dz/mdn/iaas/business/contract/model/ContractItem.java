/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractItem
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.model;

import dz.mdn.iaas.configuration.template.GenericModel;
import dz.mdn.iaas.business.plan.model.PlannedItem;
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
@Entity(name="ContractItem")
@Table(name="T_02_05_05", uniqueConstraints = { @UniqueConstraint(name = "T_02_05_05_UK_01", columnNames = { "F_02", "F_03" })})
public class ContractItem extends GenericModel {
	
	@Column(name="F_01")
	private int internalId;
	
	@ManyToOne
    @JoinColumn(name="F_02", foreignKey=@ForeignKey(name="T_02_05_05_FK_01"), nullable=false)
    private Contract contract;
	
	@ManyToOne
    @JoinColumn(name="F_03", foreignKey=@ForeignKey(name="T_02_05_05_FK_02"), nullable=false)
    private PlannedItem plannedItem;

}
