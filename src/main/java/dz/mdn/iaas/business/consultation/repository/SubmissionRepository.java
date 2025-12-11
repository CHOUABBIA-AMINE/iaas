/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: SubmissionRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Business / Consultation
 *	@Package	: Business / Consultation / Repository
 *
 **/

package dz.mdn.iaas.business.consultation.repository;

import dz.mdn.iaas.business.consultation.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Submission Repository
 * Submission belongs to Consultation and Provider
 */
@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    
    /**
     * Find submissions by consultation ID
     * Used by SubmissionService.getByConsultationId()
     */
    @Query("SELECT s FROM Submission s WHERE s.consultation.id = :consultationId")
    List<Submission> findByConsultationId(@Param("consultationId") Long consultationId);
    
    /**
     * Find submissions by provider ID
     * Used by SubmissionService.getByProviderId()
     */
    @Query("SELECT s FROM Submission s WHERE s.provider.id = :providerId")
    List<Submission> findByProviderId(@Param("providerId") Long providerId);
}
