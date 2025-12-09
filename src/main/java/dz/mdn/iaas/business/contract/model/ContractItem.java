/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractItem
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.model;

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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="ContractItem")
@Table(name="T_02_05_05", uniqueConstraints = { @UniqueConstraint(name = "T_02_05_05_UK_01", columnNames = { "F_02", "F_06" }) })
public class ContractItem {
	
	@Id
	@Column(name="F_00")
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="F_01", nullable=false)
	private String designation;
	
	@Column(name="F_02", nullable=false)
	private String reference;
	
	@Column(name="F_03")
	private double quantity;
	
	@Column(name="F_04")
	private double unitPrice;
	
	@Column(name="F_05")
	private String observation;
	
	@ManyToOne
	@JoinColumn(name="F_06", foreignKey=@ForeignKey(name="T_02_05_05_FK_01"), nullable=false)
	private Contract contract;
	
}