/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderRepresentator
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-10-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.model;

import java.util.Date;

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
@Entity(name="ProviderRepresentator")
@Table(name="T_02_03_07", uniqueConstraints = { @UniqueConstraint(name = "T_02_03_07_UK_01", columnNames = { "F_02", "F_03", "F_04" })})
public class ProviderRepresentator extends GenericModel {
	
	@Column(name="F_01")
	private int internalId;
	
	@ManyToOne
    @JoinColumn(name="F_02", foreignKey=@ForeignKey(name="T_02_03_07_FK_01"), nullable=false)
    private Provider provider;
	
	@Column(name="F_03", length=100, nullable=false)
	private String firstName;
	
	@Column(name="F_04", length=100, nullable=false)
	private String lastName;

	@Column(name="F_05", length=300)
	private String mail;
	
	@Column(name="F_06", length=200)
	private String phoneNumber;
	
	@Column(name="F_07")
	private Date birthDate;

}
