/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: JobController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.controller;

import dz.mdn.iaas.common.administration.service.JobService;
import dz.mdn.iaas.common.administration.dto.JobDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Job REST Controller
 * Handles job operations: create, get metadata, delete, get all
 * Based on exact Job model: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=structure
 * F_03 (designationFr) has unique constraint and is required
 * F_04 (structure) is required foreign key
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
@Slf4j
public class JobController {

    private final JobService jobService;

    // ========== POST ONE JOB ==========

    /**
     * Create new job
     * Creates job with multilingual designations and organizational structure assignment
     */
    @PostMapping
    public ResponseEntity<JobDTO> createJob(@Valid @RequestBody JobDTO jobDTO) {
        log.info("Creating job with French designation: {} and designations: AR={}, EN={}, Structure ID: {}", 
                jobDTO.getDesignationFr(), jobDTO.getDesignationAr(), 
                jobDTO.getDesignationEn(), jobDTO.getStructureId());
        
        JobDTO createdJob = jobService.createJob(jobDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdJob);
    }

    // ========== GET METADATA ==========

    /**
     * Get job metadata by ID
     * Returns job information with organizational structure context and multilingual support
     */
    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for job ID: {}", id);
        
        JobDTO jobMetadata = jobService.getJobById(id);
        
        return ResponseEntity.ok(jobMetadata);
    }

    /**
     * Get job by French designation (unique field F_03)
     */
    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<JobDTO> getJobByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting job by French designation: {}", designationFr);
        
        return jobService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get job by Arabic designation (F_01)
     */
    @GetMapping("/designation-ar/{designationAr}")
    public ResponseEntity<JobDTO> getJobByDesignationAr(@PathVariable String designationAr) {
        log.debug("Getting job by Arabic designation: {}", designationAr);
        
        return jobService.findByDesignationAr(designationAr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get job by English designation (F_02)
     */
    @GetMapping("/designation-en/{designationEn}")
    public ResponseEntity<JobDTO> getJobByDesignationEn(@PathVariable String designationEn) {
        log.debug("Getting job by English designation: {}", designationEn);
        
        return jobService.findByDesignationEn(designationEn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get jobs by structure ID (F_04)
     */
    @GetMapping("/structure/{structureId}")
    public ResponseEntity<Page<JobDTO>> getJobsByStructure(
            @PathVariable Long structureId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting jobs for structure ID: {}", structureId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<JobDTO> jobs = jobService.findByStructureId(structureId, pageable);
        
        return ResponseEntity.ok(jobs);
    }

    // ========== DELETE ONE ==========

    /**
     * Delete job by ID
     * Removes job from the system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        log.info("Deleting job with ID: {}", id);
        
        jobService.deleteJob(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all jobs with pagination
     * Returns list of all jobs ordered by French designation
     */
    @GetMapping
    public ResponseEntity<Page<JobDTO>> getAllJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all jobs - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<JobDTO> jobs = jobService.getAllJobs(pageable);
        
        return ResponseEntity.ok(jobs);
    }

    /**
     * Get all jobs ordered by structure and designation
     */
    @GetMapping("/ordered-by-structure")
    public ResponseEntity<Page<JobDTO>> getAllJobsOrderedByStructure(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting all jobs ordered by structure and designation");
        
        Pageable pageable = PageRequest.of(page, size);
        Page<JobDTO> jobs = jobService.getAllJobsOrderedByStructure(pageable);
        
        return ResponseEntity.ok(jobs);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search jobs by designation (all languages)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<JobDTO>> searchJobs(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching jobs with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<JobDTO> jobs = jobService.searchJobs(query, pageable);
        
        return ResponseEntity.ok(jobs);
    }

    /**
     * Search jobs with structure context
     */
    @GetMapping("/search/context")
    public ResponseEntity<Page<JobDTO>> searchJobsWithStructureContext(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching jobs with structure context for query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<JobDTO> jobs = jobService.searchJobsWithStructureContext(query, pageable);
        
        return ResponseEntity.ok(jobs);
    }

    // ========== JOB CATEGORY ENDPOINTS ==========

    /**
     * Get multilingual jobs
     */
    @GetMapping("/multilingual")
    public ResponseEntity<Page<JobDTO>> getMultilingualJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual jobs");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<JobDTO> jobs = jobService.getMultilingualJobs(pageable);
        
        return ResponseEntity.ok(jobs);
    }

    /**
     * Get leadership jobs
     */
    @GetMapping("/leadership")
    public ResponseEntity<Page<JobDTO>> getLeadershipJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting leadership jobs");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<JobDTO> jobs = jobService.getLeadershipJobs(pageable);
        
        return ResponseEntity.ok(jobs);
    }

    /**
     * Get administrative jobs
     */
    @GetMapping("/administrative")
    public ResponseEntity<Page<JobDTO>> getAdministrativeJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting administrative jobs");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<JobDTO> jobs = jobService.getAdministrativeJobs(pageable);
        
        return ResponseEntity.ok(jobs);
    }

    /**
     * Get technical jobs
     */
    @GetMapping("/technical")
    public ResponseEntity<Page<JobDTO>> getTechnicalJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting technical jobs");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<JobDTO> jobs = jobService.getTechnicalJobs(pageable);
        
        return ResponseEntity.ok(jobs);
    }

    /**
     * Get operational jobs
     */
    @GetMapping("/operational")
    public ResponseEntity<Page<JobDTO>> getOperationalJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting operational jobs");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<JobDTO> jobs = jobService.getOperationalJobs(pageable);
        
        return ResponseEntity.ok(jobs);
    }

    /**
     * Get security jobs
     */
    @GetMapping("/security")
    public ResponseEntity<Page<JobDTO>> getSecurityJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting security jobs");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<JobDTO> jobs = jobService.getSecurityJobs(pageable);
        
        return ResponseEntity.ok(jobs);
    }

    /**
     * Get medical jobs
     */
    @GetMapping("/medical")
    public ResponseEntity<Page<JobDTO>> getMedicalJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting medical jobs");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<JobDTO> jobs = jobService.getMedicalJobs(pageable);
        
        return ResponseEntity.ok(jobs);
    }

    /**
     * Get legal jobs
     */
    @GetMapping("/legal")
    public ResponseEntity<Page<JobDTO>> getLegalJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting legal jobs");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<JobDTO> jobs = jobService.getLegalJobs(pageable);
        
        return ResponseEntity.ok(jobs);
    }

    /**
     * Get financial jobs
     */
    @GetMapping("/financial")
    public ResponseEntity<Page<JobDTO>> getFinancialJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting financial jobs");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<JobDTO> jobs = jobService.getFinancialJobs(pageable);
        
        return ResponseEntity.ok(jobs);
    }

    /**
     * Get human resources jobs
     */
    @GetMapping("/human-resources")
    public ResponseEntity<Page<JobDTO>> getHumanResourcesJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting human resources jobs");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<JobDTO> jobs = jobService.getHumanResourcesJobs(pageable);
        
        return ResponseEntity.ok(jobs);
    }

    /**
     * Get communication jobs
     */
    @GetMapping("/communication")
    public ResponseEntity<Page<JobDTO>> getCommunicationJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting communication jobs");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<JobDTO> jobs = jobService.getCommunicationJobs(pageable);
        
        return ResponseEntity.ok(jobs);
    }

    /**
     * Get logistics jobs
     */
    @GetMapping("/logistics")
    public ResponseEntity<Page<JobDTO>> getLogisticsJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting logistics jobs");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<JobDTO> jobs = jobService.getLogisticsJobs(pageable);
        
        return ResponseEntity.ok(jobs);
    }

    /**
     * Get training jobs
     */
    @GetMapping("/training")
    public ResponseEntity<Page<JobDTO>> getTrainingJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting training jobs");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<JobDTO> jobs = jobService.getTrainingJobs(pageable);
        
        return ResponseEntity.ok(jobs);
    }

    // ========== JOB LEVEL ENDPOINTS ==========

    /**
     * Get executive jobs
     */
    @GetMapping("/level/executive")
    public ResponseEntity<Page<JobDTO>> getExecutiveJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting executive jobs");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<JobDTO> jobs = jobService.getExecutiveJobs(pageable);
        
        return ResponseEntity.ok(jobs);
    }

    /**
     * Get senior management jobs
     */
    @GetMapping("/level/senior-management")
    public ResponseEntity<Page<JobDTO>> getSeniorManagementJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting senior management jobs");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<JobDTO> jobs = jobService.getSeniorManagementJobs(pageable);
        
        return ResponseEntity.ok(jobs);
    }

    /**
     * Get middle management jobs
     */
    @GetMapping("/level/middle-management")
    public ResponseEntity<Page<JobDTO>> getMiddleManagementJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting middle management jobs");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<JobDTO> jobs = jobService.getMiddleManagementJobs(pageable);
        
        return ResponseEntity.ok(jobs);
    }

    /**
     * Get specialist jobs
     */
    @GetMapping("/level/specialist")
    public ResponseEntity<Page<JobDTO>> getSpecialistJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting specialist jobs");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<JobDTO> jobs = jobService.getSpecialistJobs(pageable);
        
        return ResponseEntity.ok(jobs);
    }

    /**
     * Get entry level jobs
     */
    @GetMapping("/level/entry-level")
    public ResponseEntity<Page<JobDTO>> getEntryLevelJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting entry level jobs");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<JobDTO> jobs = jobService.getEntryLevelJobs(pageable);
        
        return ResponseEntity.ok(jobs);
    }

    // ========== SPECIALIZED CLASSIFICATION ENDPOINTS ==========

    /**
     * Get security clearance jobs
     */
    @GetMapping("/security-clearance")
    public ResponseEntity<Page<JobDTO>> getSecurityClearanceJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting security clearance jobs");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<JobDTO> jobs = jobService.getSecurityClearanceJobs(pageable);
        
        return ResponseEntity.ok(jobs);
    }

    /**
     * Get management track jobs
     */
    @GetMapping("/track/management")
    public ResponseEntity<Page<JobDTO>> getManagementTrackJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting management track jobs");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<JobDTO> jobs = jobService.getManagementTrackJobs(pageable);
        
        return ResponseEntity.ok(jobs);
    }

    /**
     * Get specialist track jobs
     */
    @GetMapping("/track/specialist")
    public ResponseEntity<Page<JobDTO>> getSpecialistTrackJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting specialist track jobs");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<JobDTO> jobs = jobService.getSpecialistTrackJobs(pageable);
        
        return ResponseEntity.ok(jobs);
    }

    // ========== STRUCTURE-BASED ENDPOINTS ==========

    /**
     * Find jobs by structure designation
     */
    @GetMapping("/structure-designation/{structureDesignation}")
    public ResponseEntity<Page<JobDTO>> getJobsByStructureDesignation(
            @PathVariable String structureDesignation,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Finding jobs by structure designation: {}", structureDesignation);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<JobDTO> jobs = jobService.findByStructureDesignation(structureDesignation, pageable);
        
        return ResponseEntity.ok(jobs);
    }

    /**
     * Find jobs by structure type designation
     */
    @GetMapping("/structure-type-designation/{structureTypeDesignation}")
    public ResponseEntity<Page<JobDTO>> getJobsByStructureTypeDesignation(
            @PathVariable String structureTypeDesignation,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Finding jobs by structure type designation: {}", structureTypeDesignation);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<JobDTO> jobs = jobService.findByStructureTypeDesignation(structureTypeDesignation, pageable);
        
        return ResponseEntity.ok(jobs);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update job metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<JobDTO> updateJob(
            @PathVariable Long id,
            @Valid @RequestBody JobDTO jobDTO) {
        
        log.info("Updating job with ID: {}", id);
        
        JobDTO updatedJob = jobService.updateJob(id, jobDTO);
        
        return ResponseEntity.ok(updatedJob);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if job exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkJobExists(@PathVariable Long id) {
        log.debug("Checking existence of job ID: {}", id);
        
        boolean exists = jobService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if job exists by French designation
     */
    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkJobExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);
        
        boolean exists = jobService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of jobs
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getJobsCount() {
        log.debug("Getting total count of jobs");
        
        Long count = jobService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count by structure
     */
    @GetMapping("/count/structure/{structureId}")
    public ResponseEntity<Long> getCountByStructure(@PathVariable Long structureId) {
        log.debug("Getting count for structure ID: {}", structureId);
        
        Long count = jobService.getCountByStructure(structureId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of leadership jobs
     */
    @GetMapping("/count/leadership")
    public ResponseEntity<Long> getLeadershipJobsCount() {
        log.debug("Getting count of leadership jobs");
        
        Long count = jobService.getLeadershipCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of administrative jobs
     */
    @GetMapping("/count/administrative")
    public ResponseEntity<Long> getAdministrativeJobsCount() {
        log.debug("Getting count of administrative jobs");
        
        Long count = jobService.getAdministrativeCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of technical jobs
     */
    @GetMapping("/count/technical")
    public ResponseEntity<Long> getTechnicalJobsCount() {
        log.debug("Getting count of technical jobs");
        
        Long count = jobService.getTechnicalCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of operational jobs
     */
    @GetMapping("/count/operational")
    public ResponseEntity<Long> getOperationalJobsCount() {
        log.debug("Getting count of operational jobs");
        
        Long count = jobService.getOperationalCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of medical jobs
     */
    @GetMapping("/count/medical")
    public ResponseEntity<Long> getMedicalJobsCount() {
        log.debug("Getting count of medical jobs");
        
        Long count = jobService.getMedicalCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get job info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<JobInfoResponse> getJobInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for job ID: {}", id);
        
        try {
            return jobService.findOne(id)
                    .map(jobDTO -> {
                        JobInfoResponse response = JobInfoResponse.builder()
                                .jobMetadata(jobDTO)
                                .hasArabicDesignation(jobDTO.getDesignationAr() != null && !jobDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(jobDTO.getDesignationEn() != null && !jobDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(jobDTO.getDesignationFr() != null && !jobDTO.getDesignationFr().trim().isEmpty())
                                .isMultilingual(jobDTO.isMultilingual())
                                .requiresSecurityClearance(jobDTO.requiresSecurityClearance())
                                .isManagementTrack(jobDTO.isManagementTrack())
                                .isSpecialistTrack(jobDTO.isSpecialistTrack())
                                .isOperational(jobDTO.isOperational())
                                .allowsRemoteWork(jobDTO.allowsRemoteWork())
                                .isValid(jobDTO.isValid())
                                .defaultDesignation(jobDTO.getDefaultDesignation())
                                .displayText(jobDTO.getDisplayText())
                                .jobCategory(jobDTO.getJobCategory())
                                .jobLevel(jobDTO.getJobLevel())
                                .jobPriority(jobDTO.getJobPriority())
                                .educationRequirement(jobDTO.getEducationRequirement())
                                .experienceRequirement(jobDTO.getExperienceRequirement())
                                .commandResponsibility(jobDTO.getCommandResponsibility())
                                .reportingLevel(jobDTO.getReportingLevel())
                                .workSchedule(jobDTO.getWorkSchedule())
                                .structureDesignation(jobDTO.getStructureDesignation())
                                .structureAcronym(jobDTO.getStructureAcronym())
                                .structureTypeDesignation(jobDTO.getStructureTypeDesignation())
                                .structureTypeAcronym(jobDTO.getStructureTypeAcronym())
                                .jobClassification(jobDTO.getJobClassification())
                                .careerPath(jobDTO.getCareerPath())
                                .shortDisplay(jobDTO.getShortDisplay())
                                .fullDisplay(jobDTO.getFullDisplay())
                                .displayWithCategory(jobDTO.getDisplayWithCategory())
                                .displayWithStructure(jobDTO.getDisplayWithStructure())
                                .availableLanguages(jobDTO.getAvailableLanguages())
                                .comparisonKey(jobDTO.getComparisonKey())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting job info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class JobInfoResponse {
        private JobDTO jobMetadata;
        private Boolean hasArabicDesignation;
        private Boolean hasEnglishDesignation;
        private Boolean hasFrenchDesignation;
        private Boolean isMultilingual;
        private Boolean requiresSecurityClearance;
        private Boolean isManagementTrack;
        private Boolean isSpecialistTrack;
        private Boolean isOperational;
        private Boolean allowsRemoteWork;
        private Boolean isValid;
        private String defaultDesignation;
        private String displayText;
        private String jobCategory;
        private String jobLevel;
        private Integer jobPriority;
        private String educationRequirement;
        private String experienceRequirement;
        private String commandResponsibility;
        private String reportingLevel;
        private String workSchedule;
        private String structureDesignation;
        private String structureAcronym;
        private String structureTypeDesignation;
        private String structureTypeAcronym;
        private String jobClassification;
        private String[] careerPath;
        private String shortDisplay;
        private String fullDisplay;
        private String displayWithCategory;
        private String displayWithStructure;
        private String[] availableLanguages;
        private String comparisonKey;
    }
}
