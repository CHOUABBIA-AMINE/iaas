/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: Floor
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
@Entity(name="Floor")
@Table(name="T_01_01_03", uniqueConstraints = { @UniqueConstraint(name = "T_01_01_03_UK_01", columnNames = { "F_01" }), 
											    @UniqueConstraint(name = "T_01_01_03_UK_02", columnNames = { "F_04" })})
public class Floor {
	
	@Id
	@Column(name="F_00")
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="F_01", length=20, nullable=false)
	private String code;
	
	@Column(name="F_02", length=200)
	private String designationAr;
	
	@Column(name="F_03", length=200)
	private String designationEn;
	
	@Column(name="F_04", length=200, nullable=false)
	private String designationFr;
	
}
/*
Insert into T_01_01_03 (F_00, F_01, F_02, F_03, F_04) values
(1, "-2°", "الطابق السفلي الثاني", "Second Floor Underground", "Deuxième Etage Sous Sol"),
(2, "-1°", "الطابق السفلي الأول", "First Floor Underground", "Premier Etage Sous Sol"),
(3, "0", "الطابق الأرضي", "Ground Floor", "Riz de Chaussé"),
(4, "1°", "الطابق الأول", "First Floor", "Premier Etage"),
(5, "2°", "الطابق الأول", "Second Floor", "Deuxième Etage"),
(6, "3°", "الطابق الأول", "Third Floor", "Troisième Etage"),
(7, "4°", "الطابق الأول", "Fourth Floor", "Quatrième Etage"),
(8, "5°", "الطابق الأول", "Fifth Floor", "Cinquième Etage"),
(9, "6°", "الطابق الأول", "Sixth Floor", "Siesième Etage"),
(10, "7°", "الطابق الأول", "Seventh Floor", "Septième Etage"),
(11, "8°", "الطابق الأول", "Eigth Floor", "Huitième Etage"),
(12, "9°", "الطابق الأول", "Nineth Floor", "Neuvième Etage");
*/