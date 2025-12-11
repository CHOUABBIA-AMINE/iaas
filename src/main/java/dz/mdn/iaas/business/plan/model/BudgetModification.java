/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: BudgetModification
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.model;

import java.util.Date;

import dz.mdn.iaas.common.document.model.Document;
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
@Entity(name="BudgetModification")
@Table(name="T_02_02_07", uniqueConstraints = { @UniqueConstraint(name = "T_02_02_07_UK_01", columnNames = { "F_03", "F_04" }) })
public class BudgetModification extends GenericModel {
	
	@Column(name="F_01", length=200)
	private String object;

	@Column(name="F_02", length=500)
	private String description;
	
	@Column(name="F_03", length=200)
	private Date approvalDate;
	
	@ManyToOne
    @JoinColumn(name="F_04", foreignKey=@ForeignKey(name="T_02_02_07_FK_01"), nullable=false)
    private Document demande;
	
	@ManyToOne
    @JoinColumn(name="F_05", foreignKey=@ForeignKey(name="T_02_02_07_FK_02"), nullable=false)
    private Document response;

}
