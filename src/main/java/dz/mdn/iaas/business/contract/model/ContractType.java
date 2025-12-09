/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractType
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.model;

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
@Entity(name="ContractType")
@Table(name="T_02_05_01", uniqueConstraints = { @UniqueConstraint(name = "T_02_05_01_UK_01", columnNames = { "F_03" })})
public class ContractType {
	
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
INSERT INTO T_02_05_01 (F_00, F_01, F_02, F_03) VALUES
(1,'اتفاقية﻿','Convention','Convention'),
(2,'اتفاقية إطار﻿','Framework Convention','Convention Cadre'),
(3,'صفقة','Contract','Marché'),
(4,'صفقة تسوية﻿','Regularization Contract','Marché de Régularisation'),
(5,'صفقة طلبات﻿','Contract on Demand','Marché à Commandes'),
(6,'عقد﻿','Contract','Contrat'),
(7,'عقد برنامج﻿','Framework Contract','Contrat Programme'),
(8,'عقد تطبيق﻿','Implementation Contract','Contrat d'Application'),
(9,'أمر شراء﻿','Purchase Order','Bon de Commande');
*/