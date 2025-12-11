/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: StateRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Common / Administration
 *	@Package	: Common / Administration / Repository
 *
 **/

package dz.mdn.iaas.common.administration.repository;

import dz.mdn.iaas.common.administration.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * State Repository
 * Basic CRUD operations provided by JpaRepository
 */
@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    
    /**
     * Find states by country ID
     * Used by StateService.getByCountryId()
     */
    @Query("SELECT s FROM State s WHERE s.country.id = :countryId")
    List<State> findByCountryId(@Param("countryId") Long countryId);
}
