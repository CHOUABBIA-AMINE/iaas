/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: Structure
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
@Entity(name="Structure")
@Table(name="T_01_04_07", uniqueConstraints = { @UniqueConstraint(name = "T_01_04_07_UK_01", columnNames = { "F_03" }), 
											 	@UniqueConstraint(name = "T_01_04_07_UK_02", columnNames = { "F_06" })})
public class Structure {
	
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
	
	@ManyToOne
    @JoinColumn(name="F_07", foreignKey=@ForeignKey(name="T_01_04_07_FK_01"), nullable=false)
    private StructureType structureType;
	
	@ManyToOne
    @JoinColumn(name="F_08", foreignKey=@ForeignKey(name="T_01_04_07_FK_02"), nullable=true)
    private Structure structureUp;
}