/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ProcurementNatureRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Business / Core
 *	@Package	: Business / Core / Repository
 *
 **/

package dz.mdn.iaas.business.core.repository;

import dz.mdn.iaas.business.core.model.ProcurementNature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ProcurementNature Repository
 * Basic CRUD operations provided by JpaRepository
 */
@Repository
public interface ProcurementNatureRepository extends JpaRepository<ProcurementNature, Long> {
    // All basic CRUD operations inherited from JpaRepository
}
