/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EconomicNature
 *	@CreatedOn	: 06-26-2025
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
@Entity(name="EconomicNature")
@Table(name="T_02_03_02", uniqueConstraints = { @UniqueConstraint(name = "T_02_03_02_UK_01", columnNames = { "F_03" }), 
											 	@UniqueConstraint(name = "T_02_03_02_UK_02", columnNames = { "F_06" })})
public class EconomicNature {
	
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
	
	@Column(name="F_04", length=20)
	private String acronymAr;
	
	@Column(name="F_05", length=20)
	private String acronymEn;
	
	@Column(name="F_06", length=20, nullable=false)
	private String acronymFr;

}

/*
INSERT INTO T_02_03_02 (F_00, F_01, F_02, F_03, F_04, F_05, F_06) VALUES
(1,'شخص طبيعي﻿','Natural Person','Personne Physique﻿','ش ط','NP','PP'),
(2,'شخص معنوي﻿','Legal Entity','Personne Morale﻿','ش م','LP','PM'),
(3,'شركة ذات مسؤولية محدودة لشخص واحد﻿','One-Person Limited Liability Company','Entreprise Unipersonnelle à Responsabilité Limitée﻿','ش ذ م م ش و','OPLLC','EURL'),
(4,'شركة ذات مسؤولية محدودة﻿','Limited Liability Company','Société à Responsabilité Limitée﻿','ش ذ م م','LLC','SARL'),
(5,'شركة ذات أسهم','Joint Stock Company','Société par Action﻿','ش ذ أ','JSC','SPA'),
(6,'شركة مساهمة مبسطة﻿','Simplified Joint Stock Company','Société par Action Simplifiée﻿','ش م م','SJSC','SPAS'),
(7,'شركة تضامن﻿','General Partnership','Société en Nom Collectif﻿','ش ت','GP','SNC'),
(8,'تجمع﻿','Grouping / Consortium','Groupement﻿','ت','C','G'),
(9,'شركة مجهولة الاسم﻿','Public Limited Company','Société Anonyme﻿','ش م إ','PLC','SA'),
(10,'مؤسسة عمومية ذات طابع صناعي وتجاري﻿','Public Industrial and Commercial Establishment','Etablissement Public à Caractère Industriel et Commerciale﻿','م ع ذ ط ص ت','PICE','EPIC'),
(11,'مؤسسة عمومية ذات إداري﻿','Public Administrative Establishment','Etablissement Public à Caractère Administratif','م ع ذ ط إ','PAE','EPA');
*/