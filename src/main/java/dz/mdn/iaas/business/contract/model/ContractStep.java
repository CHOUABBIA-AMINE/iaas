/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractStep
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.model;

import java.util.Date;
import java.util.List;

import dz.mdn.iaas.configuration.template.GenericModel;
import dz.mdn.iaas.common.communication.model.Mail;
import dz.mdn.iaas.common.document.model.Document;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Entity(name="ContractStep")
@Table(name="T_02_05_03", uniqueConstraints = { @UniqueConstraint(name = "T_02_05_03_UK_01", columnNames = { "F_02", "F_03" })})
public class ContractStep extends GenericModel {
	
	@Column(name="F_01")
	private int internalId;
	
	@ManyToOne
    @JoinColumn(name="F_02", foreignKey=@ForeignKey(name="T_02_05_03_FK_01"), nullable=false)
    private Contract contract;
	
	@ManyToOne
    @JoinColumn(name="F_03", foreignKey=@ForeignKey(name="T_02_05_03_FK_02"), nullable=false)
    private ContractPhase contractPhase;

	@Column(name="F_04")
	private Date date;

	@Column(name="F_05", length=500)
	private String observation;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "R_T020503_T010302", 
			joinColumns = @JoinColumn(name = "F_01", foreignKey=@ForeignKey(name="R_T020503_T010302_FK_01")), 
			inverseJoinColumns = @JoinColumn(name = "F_02", foreignKey=@ForeignKey(name="R_T020503_T010302_FK_02")),
			uniqueConstraints = @UniqueConstraint(name = "R_T020503_T010302_UK_01", columnNames = {"F_01", "F_02"}))
	private List<Document> documents;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "R_T020503_T010203", 
			joinColumns = @JoinColumn(name = "F_01", foreignKey = @ForeignKey(name = "R_T020503_T010203_FK_01")), 
			inverseJoinColumns = @JoinColumn(name = "F_02", foreignKey = @ForeignKey(name = "R_T020503_T010203_FK_02")),
			uniqueConstraints = @UniqueConstraint(name = "R_T020503_T010203_UK_01", columnNames = {"F_01", "F_02"}))
	private List<Mail> referencedMails;

}
