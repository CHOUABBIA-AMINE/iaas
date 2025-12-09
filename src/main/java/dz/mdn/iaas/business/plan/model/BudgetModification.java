/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: BudgetModification
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.model;

import java.util.Date;

import dz.mdn.iaas.common.document.model.Document;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name="BudgetModification")
@Table(name="T_02_02_07", uniqueConstraints = { @UniqueConstraint(name = "T_02_02_07_UK_01", columnNames = { "F_03", "F_04" }) })
public class BudgetModification {
	
	@Id
	@Column(name="F_00")
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
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