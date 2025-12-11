/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ConsultationRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Business / Consultation
 *	@Package	: Business / Consultation / Repository
 *
 **/

package dz.mdn.iaas.business.consultation.repository;

import dz.mdn.iaas.business.consultation.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Consultation Repository
 * Consultation belongs to PlannedItem
 */
@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    
    /**
     * Find consultations by planned item ID
     * Used by ConsultationService.getByPlannedItemId()
     */
    @Query("SELECT c FROM Consultation c WHERE c.plannedItem.id = :plannedItemId")
    List<Consultation> findByPlannedItemId(@Param("plannedItemId") Long plannedItemId);
}
