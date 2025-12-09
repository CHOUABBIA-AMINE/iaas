/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: Person
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.model;

import java.util.Date;

import dz.mdn.iaas.system.utility.model.File;
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
@Entity(name="Person")
@Table(name="T_01_04_09")
public class Person {
	
	@Id
	@Column(name="F_00")
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="F_01", length=100, unique=false)
	private String firstnameAr;
	
	@Column(name="F_02", length=100, unique=false)
	private String lastnameAr;
	
	@Column(name="F_03", length=100, unique=false)
	private String firstnameLt;
	
	@Column(name="F_04", length=100, unique=false)
	private String lastnameLt;
	
	@Column(name="F_05", nullable=true)
	private Date birthDate;
	
	@Column(name="F_06", nullable=true)
	private String birthPlace;
	
	@Column(name="F_07", nullable=true)
	private String address;
	
	@ManyToOne
    @JoinColumn(name="F_08", foreignKey=@ForeignKey(name="T_01_04_09_FK_01"), nullable=true)
    private State birthState;
	
	@ManyToOne
    @JoinColumn(name="F_09", foreignKey=@ForeignKey(name="T_01_04_09_FK_02"), nullable=true)
    private State addressState;
	
	@ManyToOne
    @JoinColumn(name="F_10", foreignKey=@ForeignKey(name="T_01_04_09_FK_03"), nullable=true)
    private File picture;
	
}