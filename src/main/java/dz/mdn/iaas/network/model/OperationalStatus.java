/**
 *	
 *	@author		: MEDJERAB ABIR
 *
 *	@Name		: OperationalStatus
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: Model
 *	@Package	: Network
 *
 **/

package dz.mdn.iaas.network.model;

import dz.mdn.iaas.configuration.template.GenericModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * OperationalStatus Entity - Extends GenericModel
 * 
 * Database table: T_20_05
 * Primary key: F_00 (id) - inherited from GenericModel
 * 
 * Fields:
 * - F_00: id (inherited) - Primary key
 * - F_01: code - Status code (required)
 * - F_02: designation - Status designation (required)
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="OperationalStatus")
@Table(name="T_20_05")
public class OperationalStatus extends GenericModel {

    @Column(name="F_01", length=50, nullable=false)
    private String code;

    @Column(name="F_02", length=100, nullable=false)
    private String designation;
}
