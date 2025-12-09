/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MilitaryCategory
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
@Entity(name="MilitaryCategory")
@Table(name="T_01_04_04", uniqueConstraints = { @UniqueConstraint(name = "T_01_04_04_UK_01", columnNames = { "F_03" })})
public class MilitaryCategory {
	
	@Id
	@Column(name="F_00")
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="F_01", length=50)
	private String designationAr;
	
	@Column(name="F_02", length=50)
	private String designationEn;
	
	@Column(name="F_03", length=50, nullable=false)
	private String designationFr;
	
}

/*

INSERT INTO T_01_04_04 (f_00, F_01, F_02, F_03) VALUES
(1,'الضباط العامون','General Officers','Officiers Généraux'),
(2,'الضباط السامون','Senior Officers','Officiers Supérieurs'),
(3,'الضباط المرؤوسون','Junior Officers','Officiers Subalternes'),
(4,'ضباط صف','Non-Commissioned Officers (NCOs)','Sous-Officiers'),
(5,'رجال الصف','Enlisted Personnel','Hommes du Rang'),
(6,'مستخدمون مدنيون','Civilian Personnel','Personnel Civile');

*/