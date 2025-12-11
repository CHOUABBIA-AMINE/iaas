/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: LocalityRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Common / Administration
 *	@Package	: Common / Administration / Repository
 *
 **/

package dz.mdn.iaas.common.administration.repository;

import dz.mdn.iaas.common.administration.model.Locality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Locality Repository
 * Basic CRUD operations provided by JpaRepository
 */
@Repository
public interface LocalityRepository extends JpaRepository<Locality, Long> {
    
    /**
     * Find localities by state ID
     * Used by LocalityService.getByStateId()
     */
    @Query("SELECT l FROM Locality l WHERE l.state.id = :stateId")
    List<Locality> findByStateId(@Param("stateId") Long stateId);
}
