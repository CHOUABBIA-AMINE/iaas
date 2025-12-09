/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MailType
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Communication
 *
 **/

package dz.mdn.iaas.common.communication.model;

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
@Entity(name="MailType")
@Table(name="T_01_02_02", uniqueConstraints = { @UniqueConstraint(name = "T_01_02_02_UK_01", columnNames = { "F_03" })})
public class MailType {
	
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
INSERT INTO T_01_02_02 (F_00, F_01, F_02, F_03) VALUES
(1, 'مرسوم', 'Decree', 'Décret'),
(2, 'قرار', 'Administrative Decree', 'Arrété'),
(3, 'أمر﻿', 'Order', 'Ordonnance'),
(4, 'منشور﻿', 'Circular', 'Circulaire'),
(5, 'توجيهة', 'Directive', 'Directive'),
(6, 'تعليمة', 'Instruction', 'Instruction'),
(7, 'لائحة', 'Note', 'Note'),
(8, 'مقرر', 'Decision', 'Décision'),
(9, 'إرسالية', 'Dispatch', 'Envoi'),
(10, 'فاكس', 'Fax', 'Fax'),
(11, 'جدول إرسال', 'Dispatch Sheet', 'Bordereau d\'Envoi'),
(12, 'برقية', 'Telegram', 'Message');
*/