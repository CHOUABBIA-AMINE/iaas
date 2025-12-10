/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: Submission
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.model;

import dz.mdn.iaas.configuration.template.GenericModel;
import dz.mdn.iaas.business.provider.model.Provider;
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
@Entity(name="Submission")
@Table(name="T_02_04_05", uniqueConstraints = { @UniqueConstraint(name = "T_02_04_05_UK_01", columnNames = { "F_02", "F_03" })})
public class Submission extends GenericModel {
	
	@Column(name="F_01")
	private int internalId;
	
	@ManyToOne
    @JoinColumn(name="F_02", foreignKey=@ForeignKey(name="T_02_04_05_FK_01"), nullable=false)
    private Consultation consultation;
	
	@ManyToOne
    @JoinColumn(name="F_03", foreignKey=@ForeignKey(name="T_02_04_05_FK_02"), nullable=false)
    private Provider provider;

	@Column(name="F_04")
	private double financialOffer;
	
	@Column(name="F_05")
	private double technicalMark;
	
	@Column(name="F_06")
	private double financialMark;
	
	@Column(name="F_07")
	private double mark;
	
	@Column(name="F_08")
	private int rank;

}
