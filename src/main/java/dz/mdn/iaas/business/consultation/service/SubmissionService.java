/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: SubmissionService
 *	@CreatedOn	: 10-19-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.mdn.iaas.business.consultation.dto.SubmissionDTO;
import dz.mdn.iaas.business.consultation.model.Consultation;
import dz.mdn.iaas.business.consultation.model.Submission;
import dz.mdn.iaas.business.consultation.repository.ConsultationRepository;
import dz.mdn.iaas.business.consultation.repository.SubmissionRepository;
import dz.mdn.iaas.business.provider.model.Provider;
import dz.mdn.iaas.business.provider.repository.ProviderRepository;
import dz.mdn.iaas.configuration.annotation.Auditable;
import dz.mdn.iaas.exception.BusinessValidationException;
import dz.mdn.iaas.exception.ResourceNotFoundException;
import dz.mdn.iaas.system.audit.model.AuditLog.AuditAction;
import dz.mdn.iaas.system.utility.model.File;
import dz.mdn.iaas.system.utility.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service class for Submission business logic
 * Handles CRUD operations and business rules for submissions
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final ConsultationRepository consultationRepository;
    private final ProviderRepository providerRepository;
    private final FileRepository fileRepository;

    // ========================================
    // CRUD Operations
    // ========================================

    /**
     * Create new submission
     * Validates unique constraint (consultation + tender)
     */
    @Auditable(entityName = "Submission", action = AuditAction.CREATE, module = "CONSULTATION", businessProcess = "SUBMISSION_CREATION")
    public SubmissionDTO create(SubmissionDTO submissionDTO) {
        log.info("Creating submission for consultation ID: {} and tender ID: {}", 
                 submissionDTO.getConsultationId(), submissionDTO.getTenderId());

        // Validate unique constraint (F_03 + F_04)
        if (submissionRepository.existsByConsultationIdAndTenderId(
                submissionDTO.getConsultationId(), submissionDTO.getTenderId())) {
            throw new BusinessValidationException("Submission already exists for this consultation and tender combination");
        }

        // Validate business rules
        validateSubmissionRules(submissionDTO);

        Submission submission = mapToEntity(submissionDTO);
        
        // Set submission date if not provided (F_01)
        if (submission.getSubmissionDate() == null) {
            submission.setSubmissionDate(new Date());
        }

        Submission savedSubmission = submissionRepository.save(submission);
        log.info("Created submission with ID: {}", savedSubmission.getId());

        return SubmissionDTO.fromEntityWithRelations(savedSubmission);
    }

    /**
     * Update existing submission
     * Validates unique constraint and business rules
     */
    @Auditable(entityName = "Submission", action = AuditAction.UPDATE, module = "CONSULTATION", businessProcess = "SUBMISSION_UPDATE")
    public SubmissionDTO update(Long id, SubmissionDTO submissionDTO) {
        log.info("Updating submission with ID: {}", id);

        Submission existingSubmission = submissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Submission not found with ID: " + id));

        // Validate unique constraint if consultation or tender changed (F_03 + F_04)
        if (!existingSubmission.getConsultation().getId().equals(submissionDTO.getConsultationId()) ||
            !existingSubmission.getTender().getId().equals(submissionDTO.getTenderId())) {
            
            if (submissionRepository.existsByConsultationIdAndTenderId(
                    submissionDTO.getConsultationId(), submissionDTO.getTenderId())) {
                throw new BusinessValidationException("Submission already exists for this consultation and tender combination");
            }
        }

        // Validate business rules
        validateSubmissionRules(submissionDTO);

        updateEntityFromDTO(existingSubmission, submissionDTO);
        Submission updatedSubmission = submissionRepository.save(existingSubmission);

        log.info("Updated submission with ID: {}", updatedSubmission.getId());
        return SubmissionDTO.fromEntityWithRelations(updatedSubmission);
    }

    /**
     * Find submission by ID
     */
    @Transactional(readOnly = true)
    public SubmissionDTO findById(Long id) {
        log.debug("Finding submission with ID: {}", id);

        Submission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Submission not found with ID: " + id));

        return SubmissionDTO.fromEntityWithRelations(submission);
    }

    /**
     * Find all submissions
     */
    @Transactional(readOnly = true)
    public List<SubmissionDTO> findAll() {
        log.debug("Finding all submissions");

        return submissionRepository.findAll().stream()
                .map(SubmissionDTO::fromEntityWithRelations)
                .collect(Collectors.toList());
    }

    /**
     * Delete submission by ID
     */
    @Auditable(entityName = "Submission", action = AuditAction.DELETE, module = "CONSULTATION", businessProcess = "SUBMISSION_DELETE")
    public void delete(Long id) {
        log.info("Deleting submission with ID: {}", id);

        if (!submissionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Submission not found with ID: " + id);
        }

        submissionRepository.deleteById(id);
        log.info("Deleted submission with ID: {}", id);
    }

    // ========================================
    // Business Query Methods
    // ========================================

    /**
     * Find submissions by consultation ID (F_03)
     */
    @Transactional(readOnly = true)
    public List<SubmissionDTO> findByConsultationId(Long consultationId) {
        log.debug("Finding submissions for consultation ID: {}", consultationId);

        return submissionRepository.findByConsultationId(consultationId).stream()
                .map(SubmissionDTO::fromEntityWithRelations)
                .collect(Collectors.toList());
    }

    /**
     * Find submissions by tender (provider) ID (F_04)
     */
    @Transactional(readOnly = true)
    public List<SubmissionDTO> findByTenderId(Long tenderId) {
        log.debug("Finding submissions for tender ID: {}", tenderId);

        return submissionRepository.findByTenderId(tenderId).stream()
                .map(SubmissionDTO::fromEntityWithRelations)
                .collect(Collectors.toList());
    }

    /**
     * Find competitive submissions for consultation (with financial offers > 0)
     */
    @Transactional(readOnly = true)
    public List<SubmissionDTO> findCompetitiveSubmissions(Long consultationId) {
        log.debug("Finding competitive submissions for consultation ID: {}", consultationId);

        return submissionRepository.findCompetitiveSubmissionsByConsultation(consultationId).stream()
                .map(SubmissionDTO::fromEntityWithRelations)
                .collect(Collectors.toList());
    }

    /**
     * Find complete submissions (all three parts attached)
     */
    @Transactional(readOnly = true)
    public List<SubmissionDTO> findCompleteSubmissions() {
        log.debug("Finding complete submissions");

        return submissionRepository.findCompleteSubmissions().stream()
                .map(SubmissionDTO::fromEntityWithRelations)
                .collect(Collectors.toList());
    }

    /**
     * Find evaluable submissions for consultation (complete + competitive)
     */
    @Transactional(readOnly = true)
    public List<SubmissionDTO> findEvaluableSubmissions(Long consultationId) {
        log.debug("Finding evaluable submissions for consultation ID: {}", consultationId);

        return submissionRepository.findEvaluableSubmissionsByConsultation(consultationId).stream()
                .map(SubmissionDTO::fromEntityWithRelations)
                .collect(Collectors.toList());
    }

    /**
     * Find submissions by financial offer range (F_02)
     */
    @Transactional(readOnly = true)
    public List<SubmissionDTO> findByFinancialOfferRange(double minOffer, double maxOffer) {
        log.debug("Finding submissions with offers between {} and {}", minOffer, maxOffer);

        return submissionRepository.findByFinancialOfferBetween(minOffer, maxOffer).stream()
                .map(SubmissionDTO::fromEntityWithRelations)
                .collect(Collectors.toList());
    }

    /**
     * Find lowest offers for consultation
     */
    @Transactional(readOnly = true)
    public List<SubmissionDTO> findLowestOffers(Long consultationId) {
        log.debug("Finding lowest offers for consultation ID: {}", consultationId);

        return submissionRepository.findLowestOffersByConsultation(consultationId).stream()
                .map(SubmissionDTO::fromEntityWithRelations)
                .collect(Collectors.toList());
    }

    /**
     * Find recent submissions (last N days)
     */
    @Transactional(readOnly = true)
    public List<SubmissionDTO> findRecentSubmissions(int days) {
        log.debug("Finding submissions from last {} days", days);

        Date dateFrom = new Date(System.currentTimeMillis() - (long) days * 24 * 60 * 60 * 1000);
        return submissionRepository.findRecentSubmissions(dateFrom).stream()
                .map(SubmissionDTO::fromEntityWithRelations)
                .collect(Collectors.toList());
    }

    // ========================================
    // Statistics and Analytics
    // ========================================

    /**
     * Count submissions for consultation
     */
    @Transactional(readOnly = true)
    public long countByConsultationId(Long consultationId) {
        log.debug("Counting submissions for consultation ID: {}", consultationId);
        return submissionRepository.countByConsultationId(consultationId);
    }

    /**
     * Count submissions for tender
     */
    @Transactional(readOnly = true)
    public long countByTenderId(Long tenderId) {
        log.debug("Counting submissions for tender ID: {}", tenderId);
        return submissionRepository.countByTenderId(tenderId);
    }

    /**
     * Get financial offer statistics for consultation
     */
    @Transactional(readOnly = true)
    public FinancialStatistics getFinancialStatistics(Long consultationId) {
        log.debug("Getting financial statistics for consultation ID: {}", consultationId);

        Object[] stats = submissionRepository.getFinancialOfferStatistics(consultationId);
        
        if (stats != null && stats[0] != null) {
            return FinancialStatistics.builder()
                    .minOffer((Double) stats[0])
                    .maxOffer((Double) stats[1])
                    .avgOffer((Double) stats[2])
                    .totalSubmissions(countByConsultationId(consultationId))
                    .competitiveSubmissions(submissionRepository.findCompetitiveSubmissionsByConsultation(consultationId).size())
                    .build();
        }
        
        return FinancialStatistics.builder()
                .totalSubmissions(countByConsultationId(consultationId))
                .competitiveSubmissions(0)
                .build();
    }

    /**
     * Get submission summary for consultation
     */
    @Transactional(readOnly = true)
    public SubmissionSummary getSubmissionSummary(Long consultationId) {
        log.debug("Getting submission summary for consultation ID: {}", consultationId);

        List<Submission> allSubmissions = submissionRepository.findByConsultationId(consultationId);
        List<Submission> completeSubmissions = submissionRepository.findEvaluableSubmissionsByConsultation(consultationId);
        List<Submission> competitiveSubmissions = submissionRepository.findCompetitiveSubmissionsByConsultation(consultationId);

        return SubmissionSummary.builder()
                .totalSubmissions(allSubmissions.size())
                .completeSubmissions(completeSubmissions.size())
                .competitiveSubmissions(competitiveSubmissions.size())
                .partialSubmissions(allSubmissions.size() - completeSubmissions.size())
                .financialStatistics(getFinancialStatistics(consultationId))
                .build();
    }

    // ========================================
    // Business Rule Validation
    // ========================================

    /**
     * Validate submission business rules
     */
    private void validateSubmissionRules(SubmissionDTO submissionDTO) {
        // Validate consultation exists and is active
        Consultation consultation = consultationRepository.findById(submissionDTO.getConsultationId())
                .orElseThrow(() -> new ResourceNotFoundException("Consultation not found with ID: " + submissionDTO.getConsultationId()));

        // Check if consultation deadline has passed
        if (consultation.getDeadline() != null && consultation.getDeadline().before(new Date())) {
            throw new BusinessValidationException("Cannot submit to consultation after deadline has passed");
        }

        // Validate provider exists and is active
        //Provider provider = providerRepository.findById(submissionDTO.getTenderId())
         //       .orElseThrow(() -> new ResourceNotFoundException("Provider not found with ID: " + submissionDTO.getTenderId()));

        // Validate financial offer (F_02)
        if (submissionDTO.getFinancialOffer() != null && submissionDTO.getFinancialOffer() < 0) {
            throw new BusinessValidationException("Financial offer cannot be negative");
        }

        // Validate file associations (F_05, F_06, F_07)
        validateFileAssociation(submissionDTO.getAdministrativePartId(), "Administrative");
        validateFileAssociation(submissionDTO.getTechnicalPartId(), "Technical");
        validateFileAssociation(submissionDTO.getFinancialPartId(), "Financial");
    }

    /**
     * Validate file association exists
     */
    private void validateFileAssociation(Long fileId, String fileType) {
        if (fileId != null && !fileRepository.existsById(fileId)) {
            throw new ResourceNotFoundException(fileType + " file not found with ID: " + fileId);
        }
    }

    /**
     * Check if submission can be modified
     */
    @Transactional(readOnly = true)
    public boolean canModifySubmission(Long submissionId) {
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Submission not found with ID: " + submissionId));

        // Check consultation deadline
        if (submission.getConsultation().getDeadline() != null && 
            submission.getConsultation().getDeadline().before(new Date())) {
            return false;
        }

        // Additional business rules can be added here
        return true;
    }

    // ========================================
    // Entity Mapping Methods
    // ========================================

    /**
     * Map DTO to entity for create operations
     */
    private Submission mapToEntity(SubmissionDTO dto) {
        Submission submission = new Submission();

        // Map basic fields (F_01, F_02)
        submission.setSubmissionDate(dto.getSubmissionDate());
        submission.setFinancialOffer(dto.getFinancialOffer() != null ? dto.getFinancialOffer() : 0.0);

        // Set consultation (F_03)
        Consultation consultation = consultationRepository.findById(dto.getConsultationId())
                .orElseThrow(() -> new ResourceNotFoundException("Consultation not found with ID: " + dto.getConsultationId()));
        submission.setConsultation(consultation);

        // Set tender (provider) (F_04)
        Provider tender = providerRepository.findById(dto.getTenderId())
                .orElseThrow(() -> new ResourceNotFoundException("Provider not found with ID: " + dto.getTenderId()));
        submission.setTender(tender);

        // Set file associations (F_05, F_06, F_07)
        setFileAssociation(submission, dto.getAdministrativePartId(), 
                          submission::setAdministrativePart, "Administrative");
        setFileAssociation(submission, dto.getTechnicalPartId(), 
                          submission::setTechnicalPart, "Technical");
        setFileAssociation(submission, dto.getFinancialPartId(), 
                          submission::setFinancialPart, "Financial");

        return submission;
    }

    /**
     * Update entity from DTO for update operations
     */
    private void updateEntityFromDTO(Submission submission, SubmissionDTO dto) {
        // Update basic fields (F_01, F_02)
        submission.setSubmissionDate(dto.getSubmissionDate());
        submission.setFinancialOffer(dto.getFinancialOffer() != null ? dto.getFinancialOffer() : 0.0);

        // Update consultation if changed (F_03)
        if (!submission.getConsultation().getId().equals(dto.getConsultationId())) {
            Consultation consultation = consultationRepository.findById(dto.getConsultationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Consultation not found with ID: " + dto.getConsultationId()));
            submission.setConsultation(consultation);
        }

        // Update tender if changed (F_04)
        if (!submission.getTender().getId().equals(dto.getTenderId())) {
            Provider tender = providerRepository.findById(dto.getTenderId())
                    .orElseThrow(() -> new ResourceNotFoundException("Provider not found with ID: " + dto.getTenderId()));
            submission.setTender(tender);
        }

        // Update file associations (F_05, F_06, F_07)
        setFileAssociation(submission, dto.getAdministrativePartId(), 
                          submission::setAdministrativePart, "Administrative");
        setFileAssociation(submission, dto.getTechnicalPartId(), 
                          submission::setTechnicalPart, "Technical");
        setFileAssociation(submission, dto.getFinancialPartId(), 
                          submission::setFinancialPart, "Financial");
    }

    /**
     * Helper method to set file associations
     */
    private void setFileAssociation(Submission submission, Long fileId, 
                                   java.util.function.Consumer<File> setter, String fileType) {
        if (fileId != null) {
            File file = fileRepository.findById(fileId)
                    .orElseThrow(() -> new ResourceNotFoundException(fileType + " file not found with ID: " + fileId));
            setter.accept(file);
        } else {
            setter.accept(null);
        }
    }

    // ========================================
    // Bulk Operations
    // ========================================

    /**
     * Delete all submissions for consultation (cascade operation)
     */
    public void deleteByConsultationId(Long consultationId) {
        log.info("Deleting all submissions for consultation ID: {}", consultationId);
        submissionRepository.deleteByConsultationId(consultationId);
    }

    /**
     * Delete all submissions for tender (cascade operation)
     */
    public void deleteByTenderId(Long tenderId) {
        log.info("Deleting all submissions for tender ID: {}", tenderId);
        submissionRepository.deleteByTenderId(tenderId);
    }

    // ========================================
    // Helper DTOs for Statistics
    // ========================================

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class FinancialStatistics {
        private Double minOffer;
        private Double maxOffer;
        private Double avgOffer;
        private long totalSubmissions;
        private long competitiveSubmissions;
    }

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class SubmissionSummary {
        private long totalSubmissions;
        private long completeSubmissions;
        private long competitiveSubmissions;
        private long partialSubmissions;
        private FinancialStatistics financialStatistics;
    }
}
