/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: AmendmentStepRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Business / Amendment
 *	@Package	: Business / Amendment / Repository
 *
 **/

package dz.mdn.iaas.business.amendment.repository;

import dz.mdn.iaas.business.amendment.model.AmendmentStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * AmendmentStep Repository
 * Basic CRUD operations provided by JpaRepository
 */
@Repository
public interface AmendmentStepRepository extends JpaRepository<AmendmentStep, Long> {
    // All basic CRUD operations inherited from JpaRepository
}
