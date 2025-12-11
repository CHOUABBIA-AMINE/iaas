/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ConsultationStepRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Business / Consultation
 *	@Package	: Business / Consultation / Repository
 *
 **/

package dz.mdn.iaas.business.consultation.repository;

import dz.mdn.iaas.business.consultation.model.ConsultationStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ConsultationStep Repository
 * Basic CRUD operations provided by JpaRepository
 */
@Repository
public interface ConsultationStepRepository extends JpaRepository<ConsultationStep, Long> {
    // All basic CRUD operations inherited from JpaRepository
}
