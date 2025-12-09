/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: StructureType
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
@Entity(name="StructureType")
@Table(name="T_01_04_06", uniqueConstraints = { @UniqueConstraint(name = "T_01_04_06_UK_01", columnNames = { "F_03" })})
public class StructureType {
	
	@Id
	@Column(name="F_00")
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="F_01", length=200)
	private String designationAr;
	
	@Column(name="F_02", length=200)
	private String designationEn;
	
	@Column(name="F_03", length=200, nullable=false)
	private String designationFr;
	
}
/*
Insert into T_01_04_06 (F_00, F_01, F_02, F_03) values
(1,'وزارة','Ministry','Ministère'),
(2,' أركان الجيش','Army General Staff','Etat Major de l\'Armée'),
(3,'مديرية عامة','General Directorate','Direction Générale'),
(4,'الأمانة العامة لوزارة','Ministry General Secretariat','Secretariat Générale du Ministère'),
(5,'ديوان','Cabinet','Cabinet'),
(6,'قيادة قوات','Force Command','Commandement de Forces'),
(7,'ناحية عسكرية','Military Region','Région Militaire'),
(8,' أركان ناحية عسكرية','Military Region Staff','Etat Major d\'une Région Militaire'),
(9,'دائرة','Department','Département'),
(10,'مديرية مركزية','Central Directorate','Direction Centrale'),
(11,'مديرية','Directorate','Direction'),
(12,'مصلحة مركزية','Central Service','Service Central'),
(13,'مصلحة','Service','Service'),
(14,'مركز','Center','Centre'),
(15,'مديرية دائرة','Department Directorate','Direction de Département'),
(16,'مصلحة دائرة','Department Service','Service de Département'),
(17,'مكتب دائرة','Department Office','Bureau de Département'),
(18,'فصيلة','Section','Section'),
(19,'مديرية جهوية','Regional Directorate','Direction Régionale'),
(20,'مصلحة جهوية','Regional Service','Service Régional'),
(21,'مكتب جهوي','Regional Office','Bureau Régional'),
(22,'مركز جهوي','Regional Center','Centre Régional'),
(23,'مديرية فرعية','Sub-Directorate','Sous Direction'),
(24,'خلية','Cell','Cellule'),
(25,'فصيلة فرعية','Subsection','Sous Section'),
(26,'مكتب','Office','Bureau'),
(27,'فصيلة مكتب','Office Section','Section de Bureau'),
(28,'مدرسة وطنية','National School','Ecole Nationale'),
(29,'مدرسة عليا','Higher School','Ecole Supérieure'),
(30,'مدرسة تطبيقية','Application School','Ecole d\'Application'),
(31,'مركز تدريب','Training Center','Centre d\'instruction'),
(32,'مؤسسة وزارية','Ministerial Establishment','Etablissement Ministèriel'),
(33,'مؤسسة مركزية','Central Establishment','Etablissement Centrale'),
(34,'مؤسسة جهوية','Regional Establishment','Etablissement Régional'),
(35,'فرقة','Division','Division'),
(36,'لواء','Brigade','Brigade'),
(37,'فوج','Regiment','Régiment'),
(38,'فوج جهوي','Regional Regiment','Régiment Régional'),
(39,'كتيبة','Battalion','Bataillon'),
(40,'سرية','Company','Compagnie');
*/