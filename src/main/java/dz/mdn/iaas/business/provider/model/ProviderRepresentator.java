/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProviderRepresentator
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity(name="ProviderRepresentator")
@Table(name="T_02_03_05")
public class ProviderRepresentator {
	
	@Id
	@Column(name="F_00")
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="F_01", length=50, nullable=false)
	private String firstname;

	@Column(name="F_02", length=50, nullable=false)
	private String lastname;
	
	@Column(name="F_03", length=200)
	private String birthDate;
	
	@Column(name="F_04", length=100)
	private String birthPlace;
	
	@Column(name="F_05", length=100)
	private String address;
	
	@Column(name="F_06", length=50)
	private String jobTitle;
	
	@Column(name="F_07", length=100)
	private String mobilePhoneNumber;
	
	@Column(name="F_08", length=100)
	private String fixPhoneNumber;
	
	@Column(name="F_09", length=100)
	private String mail;
	
	@ManyToOne
    @JoinColumn(name="F_10", foreignKey=@ForeignKey(name="T_02_03_05_FK_01"), nullable=false)
    private Provider provider;

}