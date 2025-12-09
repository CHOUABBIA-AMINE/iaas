/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: SubmissionController
 *	@CreatedOn	: 10-19-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dz.mdn.iaas.business.consultation.dto.SubmissionDTO;
import dz.mdn.iaas.business.consultation.service.SubmissionService;
import dz.mdn.iaas.business.consultation.service.SubmissionService.FinancialStatistics;
import dz.mdn.iaas.business.consultation.service.SubmissionService.SubmissionSummary;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Enhanced Submission REST Controller
 * Provides comprehensive submission management endpoints
 * Supports all CRUD operations and business analytics
 */
@RestController
@RequestMapping("/submission")
@RequiredArgsConstructor
@Slf4j
public class SubmissionController {

    private final SubmissionService submissionService;

    // ========================================
    // Core CRUD Operations (Required Endpoints)
    // ========================================

    /**
     * Create new submission (POST ONE)
     * Maps to F_00-F_07 fields with validation
     */
    @PostMapping
    public ResponseEntity<SubmissionDTO> createSubmission(@Valid @RequestBody SubmissionDTO submissionDTO) {
        log.info("Creating submission for consultation ID: {} and tender ID: {}", 
                 submissionDTO.getConsultationId(), submissionDTO.getTenderId());

        SubmissionDTO createdSubmission = submissionService.create(submissionDTO);

        log.info("Successfully created submission with ID: {}", createdSubmission.getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdSubmission);
    }

    /**
     * Get submission metadata by ID (GET METADATA)
     * Returns complete submission with all related entities
     */
    @GetMapping("/{id}")
    public ResponseEntity<SubmissionDTO> getSubmissionById(@PathVariable Long id) {
        log.debug("Fetching submission metadata with ID: {}", id);

        SubmissionDTO submission = submissionService.findById(id);

        return ResponseEntity.ok(submission);
    }

    /**
     * Delete submission by ID (DELETE ONE)
     * Removes submission and handles cascading relationships
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubmission(@PathVariable Long id) {
        log.info("Deleting submission with ID: {}", id);

        submissionService.delete(id);

        log.info("Successfully deleted submission with ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get all submissions (GET ALL)
     * Supports filtering by consultation, tender, and other criteria
     */
    @GetMapping
    public ResponseEntity<List<SubmissionDTO>> getAllSubmissions(
            @RequestParam(required = false) Long consultationId,
            @RequestParam(required = false) Long tenderId,
            @RequestParam(required = false) @Min(1) Integer recentDays) {
        
        log.debug("Fetching all submissions with filters - consultationId: {}, tenderId: {}, recentDays: {}", 
                  consultationId, tenderId, recentDays);

        List<SubmissionDTO> submissions;
        
        if (consultationId != null) {
            submissions = submissionService.findByConsultationId(consultationId);
            log.debug("Found {} submissions for consultation ID: {}", submissions.size(), consultationId);
        } else if (tenderId != null) {
            submissions = submissionService.findByTenderId(tenderId);
            log.debug("Found {} submissions for tender ID: {}", submissions.size(), tenderId);
        } else if (recentDays != null) {
            submissions = submissionService.findRecentSubmissions(recentDays);
            log.debug("Found {} recent submissions from last {} days", submissions.size(), recentDays);
        } else {
            submissions = submissionService.findAll();
            log.debug("Found {} total submissions", submissions.size());
        }

        return ResponseEntity.ok(submissions);
    }

    // ========================================
    // Additional CRUD Operations
    // ========================================

    /**
     * Update existing submission
     * Supports partial updates with validation
     */
    @PutMapping("/{id}")
    public ResponseEntity<SubmissionDTO> updateSubmission(
            @PathVariable Long id, 
            @Valid @RequestBody SubmissionDTO submissionDTO) {
        
        log.info("Updating submission with ID: {}", id);

        SubmissionDTO updatedSubmission = submissionService.update(id, submissionDTO);

        log.info("Successfully updated submission with ID: {}", updatedSubmission.getId());
        return ResponseEntity.ok(updatedSubmission);
    }

    // ========================================
    // Business Analytics Endpoints
    // ========================================

    /**
     * Get submission count for consultation
     * Quick count without full data retrieval
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getSubmissionCount(
            @RequestParam(required = false) Long consultationId,
            @RequestParam(required = false) Long tenderId) {
        
        log.debug("Getting submission count for consultationId: {}, tenderId: {}", consultationId, tenderId);

        long count;
        if (consultationId != null) {
            count = submissionService.countByConsultationId(consultationId);
        } else if (tenderId != null) {
            count = submissionService.countByTenderId(tenderId);
        } else {
            count = submissionService.findAll().size();
        }

        return ResponseEntity.ok(count);
    }

    /**
     * Get submission summary for consultation
     * Provides complete analytics including financial statistics
     */
    @GetMapping("/summary")
    public ResponseEntity<SubmissionSummary> getSubmissionsSummary(@RequestParam Long consultationId) {
        log.debug("Getting submissions summary for consultation ID: {}", consultationId);

        SubmissionSummary summary = submissionService.getSubmissionSummary(consultationId);

        return ResponseEntity.ok(summary);
    }

    /**
     * Get financial statistics for consultation
     * Min, max, average offers and competitive analysis
     */
    @GetMapping("/financial-statistics")
    public ResponseEntity<FinancialStatistics> getFinancialStatistics(@RequestParam Long consultationId) {
        log.debug("Getting financial statistics for consultation ID: {}", consultationId);

        FinancialStatistics statistics = submissionService.getFinancialStatistics(consultationId);

        return ResponseEntity.ok(statistics);
    }

    // ========================================
    // Specialized Query Endpoints
    // ========================================

    /**
     * Get competitive submissions (with financial offers)
     * For evaluation and ranking purposes
     */
    @GetMapping("/competitive")
    public ResponseEntity<List<SubmissionDTO>> getCompetitiveSubmissions(@RequestParam Long consultationId) {
        log.debug("Getting competitive submissions for consultation ID: {}", consultationId);

        List<SubmissionDTO> competitiveSubmissions = submissionService.findCompetitiveSubmissions(consultationId);

        return ResponseEntity.ok(competitiveSubmissions);
    }

    /**
     * Get complete submissions (all documentation attached)
     * For evaluation readiness assessment
     */
    @GetMapping("/complete")
    public ResponseEntity<List<SubmissionDTO>> getCompleteSubmissions(
            @RequestParam(required = false) Long consultationId) {
        
        log.debug("Getting complete submissions for consultation ID: {}", consultationId);

        List<SubmissionDTO> completeSubmissions;
        if (consultationId != null) {
            completeSubmissions = submissionService.findEvaluableSubmissions(consultationId);
        } else {
            completeSubmissions = submissionService.findCompleteSubmissions();
        }

        return ResponseEntity.ok(completeSubmissions);
    }

    /**
     * Get submissions ready for evaluation
     * Complete documentation + competitive offers
     */
    @GetMapping("/evaluable")
    public ResponseEntity<List<SubmissionDTO>> getEvaluableSubmissions(@RequestParam Long consultationId) {
        log.debug("Getting evaluable submissions for consultation ID: {}", consultationId);

        List<SubmissionDTO> evaluableSubmissions = submissionService.findEvaluableSubmissions(consultationId);

        return ResponseEntity.ok(evaluableSubmissions);
    }

    /**
     * Get lowest offers for consultation
     * For award recommendation and analysis
     */
    @GetMapping("/lowest-offers")
    public ResponseEntity<List<SubmissionDTO>> getLowestOffers(@RequestParam Long consultationId) {
        log.debug("Getting lowest offers for consultation ID: {}", consultationId);

        List<SubmissionDTO> lowestOffers = submissionService.findLowestOffers(consultationId);

        return ResponseEntity.ok(lowestOffers);
    }

    /**
     * Get submissions by financial offer range
     * For budget analysis and filtering
     */
    @GetMapping("/by-offer-range")
    public ResponseEntity<List<SubmissionDTO>> getSubmissionsByOfferRange(
            @RequestParam double minOffer,
            @RequestParam double maxOffer) {
        
        log.debug("Getting submissions with offers between {} and {}", minOffer, maxOffer);

        List<SubmissionDTO> submissions = submissionService.findByFinancialOfferRange(minOffer, maxOffer);

        return ResponseEntity.ok(submissions);
    }

    // ========================================
    // Validation and Status Endpoints
    // ========================================

    /**
     * Check if submission can be modified
     * Validates business rules and deadlines
     */
    @GetMapping("/{id}/can-modify")
    public ResponseEntity<Boolean> canModifySubmission(@PathVariable Long id) {
        log.debug("Checking if submission with ID {} can be modified", id);

        boolean canModify = submissionService.canModifySubmission(id);

        return ResponseEntity.ok(canModify);
    }

    /**
     * Validate submission uniqueness
     * Checks consultation + tender combination
     */
    @GetMapping("/validate-uniqueness")
    public ResponseEntity<Boolean> validateUniqueness(
            @RequestParam Long consultationId,
            @RequestParam Long tenderId,
            @RequestParam(required = false) Long excludeId) {
        
        log.debug("Validating uniqueness for consultationId: {}, tenderId: {}, excludeId: {}", 
                  consultationId, tenderId, excludeId);

        // This would typically be handled by the service layer
        try {
            // If excludeId is provided, we're updating an existing submission
            if (excludeId != null) {
                SubmissionDTO existing = submissionService.findById(excludeId);
                if (existing.getConsultationId().equals(consultationId) && 
                    existing.getTenderId().equals(tenderId)) {
                    return ResponseEntity.ok(true); // Same submission, valid
                }
            }
            
            // Check if combination exists
            List<SubmissionDTO> existing = submissionService.findByConsultationId(consultationId);
            boolean exists = existing.stream()
                    .anyMatch(s -> s.getTenderId().equals(tenderId) && 
                                 !s.getId().equals(excludeId));
            
            return ResponseEntity.ok(!exists);
        } catch (Exception e) {
            log.error("Error validating uniqueness", e);
            return ResponseEntity.ok(false);
        }
    }

    // ========================================
    // Bulk Operations Endpoints
    // ========================================

    /**
     * Delete all submissions for consultation
     * Cascade operation for consultation cleanup
     */
    @DeleteMapping("/by-consultation/{consultationId}")
    public ResponseEntity<Void> deleteByConsultation(@PathVariable Long consultationId) {
        log.info("Deleting all submissions for consultation ID: {}", consultationId);

        submissionService.deleteByConsultationId(consultationId);

        log.info("Successfully deleted all submissions for consultation ID: {}", consultationId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Delete all submissions for tender
     * Cascade operation for provider cleanup
     */
    @DeleteMapping("/by-tender/{tenderId}")
    public ResponseEntity<Void> deleteByTender(@PathVariable Long tenderId) {
        log.info("Deleting all submissions for tender ID: {}", tenderId);

        submissionService.deleteByTenderId(tenderId);

        log.info("Successfully deleted all submissions for tender ID: {}", tenderId);
        return ResponseEntity.noContent().build();
    }

    // ========================================
    // Export and Reporting Endpoints
    // ========================================

    /**
     * Get submissions for reporting
     * Simplified data for export purposes
     */
    @GetMapping("/export")
    public ResponseEntity<List<SubmissionExportDTO>> getSubmissionsForExport(
            @RequestParam(required = false) Long consultationId,
            @RequestParam(defaultValue = "false") boolean includeFiles) {
        
        log.debug("Exporting submissions for consultationId: {}, includeFiles: {}", consultationId, includeFiles);

        List<SubmissionDTO> submissions;
        if (consultationId != null) {
            submissions = submissionService.findByConsultationId(consultationId);
        } else {
            submissions = submissionService.findAll();
        }

        List<SubmissionExportDTO> exportData = submissions.stream()
                .map(s -> SubmissionExportDTO.builder()
                        .id(s.getId())
                        .submissionDate(s.getSubmissionDate())
                        .financialOffer(s.getFinancialOffer())
                        .tenderName(s.getTenderDisplayName())
                        .consultationReference(s.getConsultationReference())
                        .status(s.getSubmissionStatus())
                        .completeness(s.getCompletenessPercentage())
                        .hasAdministrativePart(s.getAdministrativePartId() != null)
                        .hasTechnicalPart(s.getTechnicalPartId() != null)
                        .hasFinancialPart(s.getFinancialPartId() != null)
                        .build())
                .collect(java.util.stream.Collectors.toList());

        return ResponseEntity.ok(exportData);
    }

    // ========================================
    // Helper DTOs for API Responses
    // ========================================

    /**
     * Simplified DTO for export operations
     */
    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class SubmissionExportDTO {
        private Long id;
        private java.util.Date submissionDate;
        private Double financialOffer;
        private String tenderName;
        private String consultationReference;
        private String status;
        private Integer completeness;
        private Boolean hasAdministrativePart;
        private Boolean hasTechnicalPart;
        private Boolean hasFinancialPart;
    }

    /**
     * API response wrapper for validation results
     */
    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ValidationResult {
        private Boolean valid;
        private String message;
        private List<String> errors;
    }

    /**
     * API response for operation status
     */
    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class OperationResult {
        private Boolean success;
        private String message;
        private Long recordsAffected;
    }
}
