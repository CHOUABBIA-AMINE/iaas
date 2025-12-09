/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ConsultationController
 *	@CreatedOn	: 10-12-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.controller;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import dz.mdn.iaas.business.consultation.dto.ConsultationDTO;
import dz.mdn.iaas.business.consultation.service.ConsultationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Consultation REST Controller
 * Handles consultation operations: post one, get metadata, delete one, get all
 * Based on exact Consultation model with complex foreign key relationships
 * Required fields: F_01, F_02, F_06, and all foreign keys (F_15 to F_21)
 * Unique constraint: F_01 + F_02 (internalId + consultationYear)
 */
@RestController
@RequestMapping("/consultation")
@RequiredArgsConstructor
@Slf4j
public class ConsultationController {

    private final ConsultationService consultationService;

    // ========== POST ONE CONSULTATION ==========

    /**
     * Create new consultation
     * Creates consultation with all required relationships and complex validation
     */
    @PostMapping
    public ResponseEntity<ConsultationDTO> createConsultation(@Valid @RequestBody ConsultationDTO consultationDTO) {
        log.info("Creating consultation for year: {} with internal ID: {} and French designation: {}", 
                consultationDTO.getConsultationYear(), consultationDTO.getInternalId(), 
                consultationDTO.getDesignationFr());

        ConsultationDTO createdConsultation = consultationService.createConsultation(consultationDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdConsultation);
    }

    // ========== GET METADATA ==========

    /**
     * Get consultation metadata by ID
     * Returns consultation information with all relationships
     */
    @GetMapping("/{id}")
    public ResponseEntity<ConsultationDTO> getConsultationMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for consultation ID: {}", id);

        ConsultationDTO consultationMetadata = consultationService.getConsultationById(id);

        return ResponseEntity.ok(consultationMetadata);
    }

    /**
     * Get consultation metadata by ID with full details
     */
    @GetMapping("/{id}/details")
    public ResponseEntity<ConsultationDTO> getConsultationWithDetails(@PathVariable Long id) {
        log.debug("Getting consultation with details for ID: {}", id);

        ConsultationDTO consultationWithDetails = consultationService.getConsultationWithDetails(id);

        return ResponseEntity.ok(consultationWithDetails);
    }

    /**
     * Get consultation by internal ID and year (unique constraint)
     */
    @GetMapping("/internal/{internalId}/year/{consultationYear}")
    public ResponseEntity<ConsultationDTO> getConsultationByInternalIdAndYear(
            @PathVariable String internalId, @PathVariable String consultationYear) {
        log.debug("Getting consultation by internal ID: {} and year: {}", internalId, consultationYear);

        return consultationService.findByInternalIdAndYear(internalId, consultationYear)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get consultation by reference
     */
    @GetMapping("/reference/{reference}")
    public ResponseEntity<ConsultationDTO> getConsultationByReference(@PathVariable String reference) {
        log.debug("Getting consultation by reference: {}", reference);

        return consultationService.findByReference(reference)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    /**
     * Delete consultation by ID
     * Removes consultation from the system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long id) {
        log.info("Deleting consultation with ID: {}", id);

        consultationService.deleteConsultation(id);

        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all consultations with pagination
     * Returns list of all consultations with sorting support
     */
    @GetMapping
    public ResponseEntity<Page<ConsultationDTO>> getAllConsultations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "startDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {

        log.debug("Getting all consultations - page: {}, size: {}, sortBy: {}, sortDir: {}",
                page, size, sortBy, sortDir);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<ConsultationDTO> consultations = consultationService.getAllConsultations(pageable);

        return ResponseEntity.ok(consultations);
    }

    /**
     * Get consultations by year
     */
    @GetMapping("/year/{consultationYear}")
    public ResponseEntity<Page<ConsultationDTO>> getConsultationsByYear(
            @PathVariable String consultationYear,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "internalId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.debug("Getting consultations for year: {}", consultationYear);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<ConsultationDTO> consultations = consultationService.getConsultationsByYear(consultationYear, pageable);

        return ResponseEntity.ok(consultations);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search consultations by designation and reference
     */
    @GetMapping("/search")
    public ResponseEntity<Page<ConsultationDTO>> searchConsultations(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "startDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {

        log.debug("Searching consultations with query: {}", query);

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<ConsultationDTO> consultations = consultationService.searchConsultations(query, pageable);

        return ResponseEntity.ok(consultations);
    }

    // ========== FILTER ENDPOINTS ==========

    /**
     * Get consultations by realization status
     */
    @GetMapping("/status/{statusId}")
    public ResponseEntity<Page<ConsultationDTO>> getConsultationsByStatus(
            @PathVariable Long statusId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting consultations by realization status ID: {}", statusId);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "startDate"));
        Page<ConsultationDTO> consultations = consultationService.getConsultationsByStatus(statusId, pageable);

        return ResponseEntity.ok(consultations);
    }

    /**
     * Get consultations by award method
     */
    @GetMapping("/award-method/{awardMethodId}")
    public ResponseEntity<Page<ConsultationDTO>> getConsultationsByAwardMethod(
            @PathVariable Long awardMethodId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting consultations by award method ID: {}", awardMethodId);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "publishDate"));
        Page<ConsultationDTO> consultations = consultationService.getConsultationsByAwardMethod(awardMethodId, pageable);

        return ResponseEntity.ok(consultations);
    }

    /**
     * Get active consultations
     */
    @GetMapping("/active")
    public ResponseEntity<Page<ConsultationDTO>> getActiveConsultations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting active consultations");

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "publishDate"));
        Page<ConsultationDTO> consultations = consultationService.getActiveConsultations(pageable);

        return ResponseEntity.ok(consultations);
    }

    /**
     * Get expired consultations
     */
    @GetMapping("/expired")
    public ResponseEntity<Page<ConsultationDTO>> getExpiredConsultations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting expired consultations");

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "deadline"));
        Page<ConsultationDTO> consultations = consultationService.getExpiredConsultations(pageable);

        return ResponseEntity.ok(consultations);
    }

    /**
     * Get high-value consultations
     */
    @GetMapping("/high-value")
    public ResponseEntity<Page<ConsultationDTO>> getHighValueConsultations(
            @RequestParam(defaultValue = "1000000") double threshold,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        log.debug("Getting high-value consultations above threshold: {}", threshold);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "allocatedAmount"));
        Page<ConsultationDTO> consultations = consultationService.getHighValueConsultations(threshold, pageable);

        return ResponseEntity.ok(consultations);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update consultation metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<ConsultationDTO> updateConsultation(
            @PathVariable Long id,
            @Valid @RequestBody ConsultationDTO consultationDTO) {

        log.info("Updating consultation with ID: {}", id);

        ConsultationDTO updatedConsultation = consultationService.updateConsultation(id, consultationDTO);

        return ResponseEntity.ok(updatedConsultation);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if consultation exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkConsultationExists(@PathVariable Long id) {
        log.debug("Checking existence of consultation ID: {}", id);

        boolean exists = consultationService.existsById(id);

        return ResponseEntity.ok(exists);
    }

    /**
     * Check if consultation exists by internal ID and year
     */
    @GetMapping("/exists/internal/{internalId}/year/{consultationYear}")
    public ResponseEntity<Boolean> checkConsultationExistsByInternalIdAndYear(
            @PathVariable String internalId, @PathVariable String consultationYear) {
        log.debug("Checking existence by internal ID: {} and year: {}", internalId, consultationYear);

        boolean exists = consultationService.existsByInternalIdAndYear(internalId, consultationYear);

        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of consultations
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getConsultationsCount() {
        log.debug("Getting total count of consultations");

        Long count = consultationService.getTotalCount();

        return ResponseEntity.ok(count);
    }

    /**
     * Get consultations statistics for a year
     */
    @GetMapping("/statistics/year/{year}")
    public ResponseEntity<ConsultationStatistics> getConsultationStatistics(@PathVariable String year) {
        log.debug("Getting consultation statistics for year: {}", year);

        ConsultationStatistics statistics = consultationService.getConsultationStatistics(year);

        return ResponseEntity.ok(statistics);
    }

    /**
     * Get consultation info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<ConsultationInfoResponse> getConsultationInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for consultation ID: {}", id);

        try {
            return consultationService.findOne(id)
                    .map(consultationDTO -> {
                        ConsultationInfoResponse response = ConsultationInfoResponse.builder()
                                .consultationMetadata(consultationDTO)
                                .hasArabicDesignation(consultationDTO.getDesignationAr() != null && !consultationDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(consultationDTO.getDesignationEn() != null && !consultationDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(consultationDTO.getDesignationFr() != null && !consultationDTO.getDesignationFr().trim().isEmpty())
                                .isMultilingual(consultationDTO.isMultilingual())
                                .isValid(consultationDTO.isValid())
                                .consultationStatus(consultationDTO.getConsultationStatus())
                                .isUrgent(consultationDTO.isUrgent())
                                .isHighValue(consultationDTO.isHighValue())
                                .hasSubmissions(consultationDTO.hasSubmissions())
                                .hasBudgetOverrun(consultationDTO.hasBudgetOverrun())
                                .defaultDesignation(consultationDTO.getDefaultDesignation())
                                .shortDisplay(consultationDTO.getShortDisplay())
                                .fullDisplay(consultationDTO.getFullDisplay())
                                .comparisonKey(consultationDTO.getComparisonKey())
                                .consultationDurationDays(consultationDTO.getConsultationDurationDays())
                                .financialEfficiencyRatio(consultationDTO.getFinancialEfficiencyRatio())
                                .budgetVariancePercentage(consultationDTO.getBudgetVariancePercentage())
                                .competitiveRatio(consultationDTO.getCompetitiveRatio())
                                .build();

                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());

        } catch (Exception e) {
            log.error("Error getting consultation info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ConsultationInfoResponse {
        private ConsultationDTO consultationMetadata;
        private Boolean hasArabicDesignation;
        private Boolean hasEnglishDesignation;
        private Boolean hasFrenchDesignation;
        private Boolean isMultilingual;
        private Boolean isValid;
        private String consultationStatus;
        private Boolean isUrgent;
        private Boolean isHighValue;
        private Boolean hasSubmissions;
        private Boolean hasBudgetOverrun;
        private String defaultDesignation;
        private String shortDisplay;
        private String fullDisplay;
        private String comparisonKey;
        private Integer consultationDurationDays;
        private Double financialEfficiencyRatio;
        private Double budgetVariancePercentage;
        private Double competitiveRatio;
    }

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class ConsultationStatistics {
        private String year;
        private Long totalConsultations;
        private Double totalAllocatedAmount;
        private Double totalFinancialEstimation;
        private Double averageConsultationValue;
        private Double averageCompetitiveRatio;
        private Long activeConsultations;
        private Long expiredConsultations;
        private Long consultationsWithSubmissions;
        private Long highValueConsultations;
        private Date generatedAt;
    }
}