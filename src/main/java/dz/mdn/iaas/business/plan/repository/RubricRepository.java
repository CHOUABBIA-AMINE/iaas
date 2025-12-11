/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RubricRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Business / Plan
 *	@Package	: Business / Plan / Repository
 *
 **/

package dz.mdn.iaas.business.plan.repository;

import dz.mdn.iaas.business.plan.model.Rubric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Rubric Repository
 * Rubric belongs to Domain (many-to-one)
 * Rubric has one-to-many relationship with Items
 */
@Repository
public interface RubricRepository extends JpaRepository<Rubric, Long> {
    
    /**
     * Find rubrics by domain ID
     * Used by RubricService.getByDomainId()
     */
    @Query("SELECT r FROM Rubric r WHERE r.domain.id = :domainId")
    List<Rubric> findByDomainId(@Param("domainId") Long domainId);
}
