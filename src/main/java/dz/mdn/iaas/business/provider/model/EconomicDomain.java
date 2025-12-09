/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EconomicDomain
 *	@CreatedOn	: 06-26-2023
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Provider
 *
 **/

package dz.mdn.iaas.business.provider.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity(name="EconomicDomain")
@Table(name="T_02_03_01", uniqueConstraints = { @UniqueConstraint(name = "T_02_03_01_UK_01", columnNames = { "F_03" })})
public class EconomicDomain {
	
	@Id
	@Column(name="F_00")
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="F_01")
	private int code;
	
	@Column(name="F_02", length=200)
	private String designationAr;

	@Column(name="F_03", length=200)
	private String designationEn;
	
	@Column(name="F_04", length=200, nullable=false)
	private String designationFr;

}