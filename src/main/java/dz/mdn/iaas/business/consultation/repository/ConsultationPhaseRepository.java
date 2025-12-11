/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ConsultationPhaseRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Business / Consultation
 *	@Package	: Business / Consultation / Repository
 *
 **/

package dz.mdn.iaas.business.consultation.repository;

import dz.mdn.iaas.business.consultation.model.ConsultationPhase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ConsultationPhase Repository
 * Basic CRUD operations provided by JpaRepository
 */
@Repository
public interface ConsultationPhaseRepository extends JpaRepository<ConsultationPhase, Long> {
    // All basic CRUD operations inherited from JpaRepository
}
