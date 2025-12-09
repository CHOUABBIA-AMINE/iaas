/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: Folder
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
@Entity(name="Folder")
@Table(name="T_01_01_07", uniqueConstraints = { @UniqueConstraint(name = "T_01_01_07_UK_01", columnNames = { "F_01" }),
											 	@UniqueConstraint(name = "T_01_01_07_UK_02", columnNames = { "F_04" })})
public class Folder {
	
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
	
	@ManyToOne
    @JoinColumn(name="F_05", foreignKey=@ForeignKey(name="T_01_01_07_FK_01"), nullable=false)
    private ArchiveBox archiveBox;
	
}