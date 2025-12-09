/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MilitaryRank
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
@Entity(name="MilitaryRank")
@Table(name="T_01_04_05", uniqueConstraints = { @UniqueConstraint(name = "T_01_04_05_UK_01", columnNames = { "F_03" })})
public class MilitaryRank {
	
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
	
	@Column(name="F_04", length=10)
	private String abbreviationAr;
	
	@Column(name="F_05", length=10)
	private String abbreviationEn;
	
	@Column(name="F_06", length=10, nullable=false)
	private String abbreviationFr;
	
	@ManyToOne
    @JoinColumn(name="F_07", foreignKey=@ForeignKey(name="T_01_04_05_FK_01"), nullable=false)
    private MilitaryCategory militaryCategory;
	
}

/*
INSERT INTO T_01_04_05 (F_00, F_01, F_02, F_03, F_07) VALUES
(1, 'General', 'فريق أول', 'Général d\'Armée', 1),
(2, 'Lieutenant General', 'فريق', 'Général de Corps d\'Armée', 1),
(3, 'Major General', 'لواء', 'Général Major', 1),
(4, 'Brigadier General', 'عميد', 'Général', 1),
(5, 'Colonel', 'عقيد', 'Colonel', 2),
(6, 'Lieutenant Colonel', 'مقدم', 'Lieutenant Colonel', 2),
(7, 'Major', 'رائد', 'Commandant', 2),
(8, 'Captain', 'نقيب', 'Capitaine', 3),
(9, 'Lieutenant', 'ملازم أول', 'Lieutenant', 3),
(10, 'Second Lieutenant', 'ملازم', 'Sous Lieutenant', 3),
(11, 'Aspirant', 'مرشح', 'Aspirant', 3),
(12, 'Major Sergeant', 'مساعد رئيسي', 'Adjudant Major', 4),
(13, 'Master Sergeant', 'مساعد أول', 'Adjudant Chef', 4),
(14, 'First Class Sergeant', 'مساعد', 'Adjudant', 4),
(15, 'Staff Sergeant', 'رقيب أول', 'Sergent Chef', 4),
(16, 'Sergeant', 'رقيب', 'Sergent', 4),
(17, 'Corporal', 'عريف أول', 'Caporal Chef', 5),
(18, 'Lance Corporal', 'عريف', 'Caporal', 5),
(19, 'Private Soldier', 'جندي', 'Djoundi', 5),
(20, 'Assimilated Civilian Personnel', 'مستخدم مدني شبيه', 'Personnel Civile Assimilé', 6),
(21, 'Contractual Civilian Personnel', 'مستخدم مدني متعاقد', 'Personnel Civile Contractuel', 6);
*/