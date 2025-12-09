/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ApprovalStatus
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Pakage		: Business / Core
 *
 **/

package dz.mdn.iaas.business.core.model;

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
@Entity(name="ApprovalStatus")
@Table(name="T_02_01_02", uniqueConstraints = { @UniqueConstraint(name = "T_02_01_02_UK_01", columnNames = { "F_03" })})
public class ApprovalStatus {
	
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
INSERT INTO T_02_01_02 (F_00, F_01, F_02, F_03) VALUES
(1,'غير قابل للتطبيق﻿','Not Applicabale','Non applicable'),
(2,'قيد التحضير','Under Preparation','En cours de préparation'),
(3,'تأشيرة','Visa','Visa'),
(4,'تأشيرة مع توصيات','Visa with Recommendations','Visa avec recommandations'),
(5,'تأشيرة مع تحفظات','Visa under Reservations','Visa sous réserves'),
(6,'تأجيل','Adjournment','Ajournement'),
(7,'رفض','Rejection','Rejet'),
(8,'رفض مؤقت','Temporary Rejection','Rejet à titre temporaire'),
(9,'رفض نهائي','Definitive Rejection','Rejet définitif');
*/