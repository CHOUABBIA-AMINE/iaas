/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: Shelf
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Entity(name="Shelf")
@Table(name="T_01_01_05", uniqueConstraints = { @UniqueConstraint(name = "T_01_01_05_UK_01", columnNames = { "F_01" })})
public class Shelf {
	
	@Id
	@Column(name="F_00")
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="F_01", length=20, nullable=false)
	private String code;
	
	@ManyToOne
    @JoinColumn(name="F_02", foreignKey=@ForeignKey(name="T_01_01_05_FK_01"), nullable=false)
    private Room room;
	
	@OneToMany(mappedBy="shelf")
    private List<ArchiveBox> archiveBoxs;
	
}
/*
Insert into T_01_01_05 (F_00, F_01, F_02) values
(1, "SDR-01", 2),
(2, "SDR-02", 2),
(3, "SDR-03", 2),
(4, "SDR-04", 3),
(5, "SDR-05", 3),
(6, "SDR-06", 4),
(7, "SDR-07", 4),
(8, "SDR-08", 7),
(9, "SDR-09", 7),
(10, "SDR-10", 7),
(11, "SDR-11", 8),
(12, "SDR-12", 8),
(13, "SDR-13", 8),
(14, "SDR-14", 8),
(15, "SDR-15", 8),
(16, "SDR-16", 8),
(17, "SDR-17", 8),
(18, "SDR-18", 8),
(19, "SDR-19", 8),
(20, "SDR-20", 8);
*/