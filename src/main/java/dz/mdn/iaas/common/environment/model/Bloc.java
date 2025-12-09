/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: Bloc
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.model;

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
@Entity(name="Bloc")
@Table(name="T_01_01_01", uniqueConstraints = { @UniqueConstraint(name = "T_01_01_01_UK_01", columnNames = { "F_01" }), 
											 	@UniqueConstraint(name = "T_01_01_01_UK_02", columnNames = { "F_02" }), 
											 	@UniqueConstraint(name = "T_01_01_01_UK_03", columnNames = { "F_05" })})
public class Bloc {
	
	@Id
	@Column(name="F_00")
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="F_01", length=20, nullable=false)
	private String codeAr;
	
	@Column(name="F_02", length=20, nullable=false)
	private String codeLt;
	
}
/*
Insert into T_01_01_01 (F_00,F_01,F_02) values
(1,"س","S");
*/