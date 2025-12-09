/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PlannedItem
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name="PlannedItem")
@Table(name="T_02_02_08")
public class PlannedItem {
	
	@Id
	@Column(name="F_00")
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="F_01", length=200, nullable=false)
	private String designation;
	
	@Column(name="F_02")
	private double unitairCost;
	
	@Column(name="F_03")
	private double planedQuantity;
	
	@Column(name="F_04")
	private double allocatedAmount;
	
	@ManyToOne
    @JoinColumn(name="F_05", foreignKey=@ForeignKey(name="T_02_02_08_FK_01_"), nullable=false)
    private ItemStatus itemStatus;
	
	@ManyToOne
    @JoinColumn(name="F_06", foreignKey=@ForeignKey(name="T_02_02_08_FK_02_"), nullable=false)
    private Item item;
	
	@ManyToOne
    @JoinColumn(name="F_07", foreignKey=@ForeignKey(name="T_02_02_08_FK_03_"), nullable=false)
    private FinancialOperation financialOperation;
	
	@ManyToOne
    @JoinColumn(name="F_08", foreignKey=@ForeignKey(name="T_02_02_08_FK_04_"), nullable=true)
    private BudgetModification budgetModification;
	
	@OneToMany(mappedBy="plannedItem")
    private List<ItemDistribution> itemDistribution;

}