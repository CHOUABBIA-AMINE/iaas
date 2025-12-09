/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: Room
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.model;

import java.util.List;

import dz.mdn.iaas.common.administration.model.Structure;
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
@Entity(name="Room")
@Table(name="T_01_01_02", uniqueConstraints = { @UniqueConstraint(name = "T_01_01_02_UK_01", columnNames = { "F_01" })})
public class Room {
	
	@Id
	@Column(name="F_00")
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="F_01", length=20, nullable=false)
	private String code;
	
	@ManyToOne
    @JoinColumn(name="F_02", foreignKey=@ForeignKey(name="T_01_01_02_FK_01"), nullable=false)
    private Bloc bloc;
	
	@ManyToOne
    @JoinColumn(name="F_03", foreignKey=@ForeignKey(name="T_01_01_02_FK_02"), nullable=false)
    private Floor floor;
	
	@ManyToOne
    @JoinColumn(name="F_04", foreignKey=@ForeignKey(name="T_01_01_02_FK_03"), nullable=true)
    private Structure structure;
	
	@OneToMany(mappedBy="room")
    private List<Shelf> shelfs;
	
}
/*
Insert into T_01_01_02 (F_00, F_01, F_02, F_03) values
(1, "60", 1, 6),
(2, "61", 1, 6),
(3, "62", 1, 6),
(4, "63", 1, 6),
(5, "64", 1, 6),
(6, "65", 1, 6),
(7, "66", 1, 6),
(8, "67", 1, 6),
(9, "68", 1, 6);
*/