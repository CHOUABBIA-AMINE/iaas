/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ItemStatus
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.model;

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
@Entity(name="ItemStatus")
@Table(name="T_02_02_02", uniqueConstraints = { @UniqueConstraint(name = "T_02_02_02_UK_01", columnNames = { "F_03" })})
public class ItemStatus {
	
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
INSERT INTO T_02_02_02 (F_00, F_01, F_02, F_03) VALUES
(1, 'مخطط', 'Planed', 'Plannifié'),
(2, 'في طور الانجاز', 'Procurement Ongoing', 'En cours de Réalisation'),
(3, 'منجز', 'Procured', 'Réalisé');
*/