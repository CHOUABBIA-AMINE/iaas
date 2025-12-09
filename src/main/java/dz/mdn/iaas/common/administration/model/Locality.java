/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: Locality
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.model;

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
@Entity(name="Locality")
@Table(name="T_01_04_03", uniqueConstraints = { @UniqueConstraint(name = "T_01_04_03_UK_01", columnNames = { "F_01" }), 
											 	@UniqueConstraint(name = "T_01_04_03_UK_02", columnNames = { "F_02" }), 
											 	@UniqueConstraint(name = "T_01_04_03_UK_03", columnNames = { "F_03" })})
public class Locality {
	
	@Id
	@Column(name="F_00")
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="F_01", nullable=false)
	private int code;
	
	@Column(name="F_02", length=100, nullable=false)
	private String designationAr;

	@Column(name="F_03", length=100, nullable=false)
	private String designationLt;	
	
	@ManyToOne
    @JoinColumn(name="F_04", foreignKey=@ForeignKey(name="T_01_04_03_FK_01"), nullable=false)
    private State state;


}