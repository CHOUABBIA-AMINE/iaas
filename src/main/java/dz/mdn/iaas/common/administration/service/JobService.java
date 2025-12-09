/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: JobService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.service;

import dz.mdn.iaas.common.administration.model.Job;
import dz.mdn.iaas.common.administration.model.Structure;
import dz.mdn.iaas.common.administration.repository.JobRepository;
import dz.mdn.iaas.common.administration.repository.StructureRepository;
import dz.mdn.iaas.common.administration.dto.JobDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Job Service with CRUD operations
 * Handles job management operations with multilingual support and foreign key relationships
 * Based on exact field names: F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=structure
 * F_03 (designationFr) has unique constraint and is required
 * F_04 (structure) is required foreign key
 * F_01 (designationAr) and F_02 (designationEn) are optional
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class JobService {

    private final JobRepository jobRepository;
    private final StructureRepository structureRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new job
     */
    public JobDTO createJob(JobDTO jobDTO) {
        log.info("Creating job with French designation: {} and designations: AR={}, EN={}, Structure ID: {}", 
                jobDTO.getDesignationFr(), jobDTO.getDesignationAr(), 
                jobDTO.getDesignationEn(), jobDTO.getStructureId());

        // Validate required fields
        validateRequiredFields(jobDTO, "create");

        // Check for unique constraint violation
        validateUniqueConstraints(jobDTO, null);

        // Validate structure exists
        Structure structure = validateAndGetStructure(jobDTO.getStructureId());

        // Create entity with exact field mapping
        Job job = new Job();
        job.setDesignationAr(jobDTO.getDesignationAr()); // F_01
        job.setDesignationEn(jobDTO.getDesignationEn()); // F_02
        job.setDesignationFr(jobDTO.getDesignationFr()); // F_03
        job.setStructure(structure); // F_04

        Job savedJob = jobRepository.save(job);
        log.info("Successfully created job with ID: {}", savedJob.getId());

        return JobDTO.fromEntity(savedJob);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get job by ID
     */
    @Transactional(readOnly = true)
    public JobDTO getJobById(Long id) {
        log.debug("Getting job with ID: {}", id);

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with ID: " + id));

        return JobDTO.fromEntity(job);
    }

    /**
     * Get job entity by ID
     */
    @Transactional(readOnly = true)
    public Job getJobEntityById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with ID: " + id));
    }

    /**
     * Find job by French designation (unique field F_03)
     */
    @Transactional(readOnly = true)
    public Optional<JobDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding job with French designation: {}", designationFr);

        return jobRepository.findByDesignationFr(designationFr)
                .map(JobDTO::fromEntity);
    }

    /**
     * Find job by Arabic designation (F_01)
     */
    @Transactional(readOnly = true)
    public Optional<JobDTO> findByDesignationAr(String designationAr) {
        log.debug("Finding job with Arabic designation: {}", designationAr);

        return jobRepository.findByDesignationAr(designationAr)
                .map(JobDTO::fromEntity);
    }

    /**
     * Find job by English designation (F_02)
     */
    @Transactional(readOnly = true)
    public Optional<JobDTO> findByDesignationEn(String designationEn) {
        log.debug("Finding job with English designation: {}", designationEn);

        return jobRepository.findByDesignationEn(designationEn)
                .map(JobDTO::fromEntity);
    }

    /**
     * Find jobs by structure ID (F_04)
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> findByStructureId(Long structureId, Pageable pageable) {
        log.debug("Finding jobs for structure ID: {}", structureId);

        Page<Job> jobs = jobRepository.findByStructureId(structureId, pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Get all jobs with pagination
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> getAllJobs(Pageable pageable) {
        log.debug("Getting all jobs with pagination");

        Page<Job> jobs = jobRepository.findAllOrderByDesignationFr(pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Get all jobs ordered by structure and designation
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> getAllJobsOrderedByStructure(Pageable pageable) {
        log.debug("Getting all jobs ordered by structure and designation");

        Page<Job> jobs = jobRepository.findAllOrderByStructureAndDesignation(pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Find one job by ID
     */
    @Transactional(readOnly = true)
    public Optional<JobDTO> findOne(Long id) {
        log.debug("Finding job by ID: {}", id);

        return jobRepository.findById(id)
                .map(JobDTO::fromEntity);
    }

    /**
     * Search jobs by designation
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> searchJobs(String searchTerm, Pageable pageable) {
        log.debug("Searching jobs with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllJobs(pageable);
        }

        Page<Job> jobs = jobRepository.searchByDesignation(searchTerm.trim(), pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Search jobs with structure context
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> searchJobsWithStructureContext(String searchTerm, Pageable pageable) {
        log.debug("Searching jobs with structure context for term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllJobs(pageable);
        }

        Page<Job> jobs = jobRepository.searchWithStructureContext(searchTerm.trim(), pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Get multilingual jobs
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> getMultilingualJobs(Pageable pageable) {
        log.debug("Getting multilingual jobs");

        Page<Job> jobs = jobRepository.findMultilingualJobs(pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Get leadership jobs
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> getLeadershipJobs(Pageable pageable) {
        log.debug("Getting leadership jobs");

        Page<Job> jobs = jobRepository.findLeadershipJobs(pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Get administrative jobs
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> getAdministrativeJobs(Pageable pageable) {
        log.debug("Getting administrative jobs");

        Page<Job> jobs = jobRepository.findAdministrativeJobs(pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Get technical jobs
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> getTechnicalJobs(Pageable pageable) {
        log.debug("Getting technical jobs");

        Page<Job> jobs = jobRepository.findTechnicalJobs(pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Get operational jobs
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> getOperationalJobs(Pageable pageable) {
        log.debug("Getting operational jobs");

        Page<Job> jobs = jobRepository.findOperationalJobs(pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Get security jobs
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> getSecurityJobs(Pageable pageable) {
        log.debug("Getting security jobs");

        Page<Job> jobs = jobRepository.findSecurityJobs(pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Get medical jobs
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> getMedicalJobs(Pageable pageable) {
        log.debug("Getting medical jobs");

        Page<Job> jobs = jobRepository.findMedicalJobs(pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Get legal jobs
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> getLegalJobs(Pageable pageable) {
        log.debug("Getting legal jobs");

        Page<Job> jobs = jobRepository.findLegalJobs(pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Get financial jobs
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> getFinancialJobs(Pageable pageable) {
        log.debug("Getting financial jobs");

        Page<Job> jobs = jobRepository.findFinancialJobs(pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Get human resources jobs
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> getHumanResourcesJobs(Pageable pageable) {
        log.debug("Getting human resources jobs");

        Page<Job> jobs = jobRepository.findHumanResourcesJobs(pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Get communication jobs
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> getCommunicationJobs(Pageable pageable) {
        log.debug("Getting communication jobs");

        Page<Job> jobs = jobRepository.findCommunicationJobs(pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Get logistics jobs
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> getLogisticsJobs(Pageable pageable) {
        log.debug("Getting logistics jobs");

        Page<Job> jobs = jobRepository.findLogisticsJobs(pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Get training jobs
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> getTrainingJobs(Pageable pageable) {
        log.debug("Getting training jobs");

        Page<Job> jobs = jobRepository.findTrainingJobs(pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Get executive jobs
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> getExecutiveJobs(Pageable pageable) {
        log.debug("Getting executive jobs");

        Page<Job> jobs = jobRepository.findExecutiveJobs(pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Get senior management jobs
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> getSeniorManagementJobs(Pageable pageable) {
        log.debug("Getting senior management jobs");

        Page<Job> jobs = jobRepository.findSeniorManagementJobs(pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Get middle management jobs
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> getMiddleManagementJobs(Pageable pageable) {
        log.debug("Getting middle management jobs");

        Page<Job> jobs = jobRepository.findMiddleManagementJobs(pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Get specialist jobs
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> getSpecialistJobs(Pageable pageable) {
        log.debug("Getting specialist jobs");

        Page<Job> jobs = jobRepository.findSpecialistJobs(pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Get entry level jobs
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> getEntryLevelJobs(Pageable pageable) {
        log.debug("Getting entry level jobs");

        Page<Job> jobs = jobRepository.findEntryLevelJobs(pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Get security clearance jobs
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> getSecurityClearanceJobs(Pageable pageable) {
        log.debug("Getting security clearance jobs");

        Page<Job> jobs = jobRepository.findSecurityClearanceJobs(pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Get management track jobs
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> getManagementTrackJobs(Pageable pageable) {
        log.debug("Getting management track jobs");

        Page<Job> jobs = jobRepository.findManagementTrackJobs(pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Get specialist track jobs
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> getSpecialistTrackJobs(Pageable pageable) {
        log.debug("Getting specialist track jobs");

        Page<Job> jobs = jobRepository.findSpecialistTrackJobs(pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Find jobs by structure designation
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> findByStructureDesignation(String structureDesignation, Pageable pageable) {
        log.debug("Finding jobs by structure designation: {}", structureDesignation);

        Page<Job> jobs = jobRepository.findByStructureDesignation(structureDesignation, pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    /**
     * Find jobs by structure type designation
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> findByStructureTypeDesignation(String structureTypeDesignation, Pageable pageable) {
        log.debug("Finding jobs by structure type designation: {}", structureTypeDesignation);

        Page<Job> jobs = jobRepository.findByStructureTypeDesignation(structureTypeDesignation, pageable);
        return jobs.map(JobDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update job
     */
    public JobDTO updateJob(Long id, JobDTO jobDTO) {
        log.info("Updating job with ID: {}", id);

        Job existingJob = getJobEntityById(id);

        // Validate required fields
        validateRequiredFields(jobDTO, "update");

        // Check for unique constraint violation (excluding current record)
        validateUniqueConstraints(jobDTO, id);

        // Validate structure exists if being updated
        Structure structure = null;
        if (jobDTO.getStructureId() != null) {
            structure = validateAndGetStructure(jobDTO.getStructureId());
        }

        // Update fields with exact field mapping
        existingJob.setDesignationAr(jobDTO.getDesignationAr()); // F_01
        existingJob.setDesignationEn(jobDTO.getDesignationEn()); // F_02
        existingJob.setDesignationFr(jobDTO.getDesignationFr()); // F_03
        if (structure != null) {
            existingJob.setStructure(structure); // F_04
        }

        Job updatedJob = jobRepository.save(existingJob);
        log.info("Successfully updated job with ID: {}", id);

        return JobDTO.fromEntity(updatedJob);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete job
     */
    public void deleteJob(Long id) {
        log.info("Deleting job with ID: {}", id);

        Job job = getJobEntityById(id);
        jobRepository.delete(job);

        log.info("Successfully deleted job with ID: {}", id);
    }

    /**
     * Delete job by ID (direct)
     */
    public void deleteJobById(Long id) {
        log.info("Deleting job by ID: {}", id);

        if (!jobRepository.existsById(id)) {
            throw new RuntimeException("Job not found with ID: " + id);
        }

        jobRepository.deleteById(id);
        log.info("Successfully deleted job with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if job exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return jobRepository.existsById(id);
    }

    /**
     * Check if job exists by French designation
     */
    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return jobRepository.existsByDesignationFr(designationFr);
    }

    /**
     * Get total count of jobs
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return jobRepository.countAllJobs();
    }

    /**
     * Get count by structure
     */
    @Transactional(readOnly = true)
    public Long getCountByStructure(Long structureId) {
        return jobRepository.countByStructureId(structureId);
    }

    /**
     * Get count of leadership jobs
     */
    @Transactional(readOnly = true)
    public Long getLeadershipCount() {
        return jobRepository.countLeadershipJobs();
    }

    /**
     * Get count of administrative jobs
     */
    @Transactional(readOnly = true)
    public Long getAdministrativeCount() {
        return jobRepository.countAdministrativeJobs();
    }

    /**
     * Get count of technical jobs
     */
    @Transactional(readOnly = true)
    public Long getTechnicalCount() {
        return jobRepository.countTechnicalJobs();
    }

    /**
     * Get count of operational jobs
     */
    @Transactional(readOnly = true)
    public Long getOperationalCount() {
        return jobRepository.countOperationalJobs();
    }

    /**
     * Get count of medical jobs
     */
    @Transactional(readOnly = true)
    public Long getMedicalCount() {
        return jobRepository.countMedicalJobs();
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate required fields
     */
    private void validateRequiredFields(JobDTO jobDTO, String operation) {
        if (jobDTO.getDesignationFr() == null || jobDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required for " + operation);
        }
        if (jobDTO.getStructureId() == null) {
            throw new RuntimeException("Structure is required for " + operation);
        }
    }

    /**
     * Validate unique constraints
     */
    private void validateUniqueConstraints(JobDTO jobDTO, Long excludeId) {
        // Check French designation uniqueness (F_03)
        if (excludeId == null) {
            if (jobRepository.existsByDesignationFr(jobDTO.getDesignationFr())) {
                throw new RuntimeException("Job with French designation '" + jobDTO.getDesignationFr() + "' already exists");
            }
        } else {
            if (jobRepository.existsByDesignationFrAndIdNot(jobDTO.getDesignationFr(), excludeId)) {
                throw new RuntimeException("Another job with French designation '" + jobDTO.getDesignationFr() + "' already exists");
            }
        }
    }

    /**
     * Validate and get structure
     */
    private Structure validateAndGetStructure(Long structureId) {
        return structureRepository.findById(structureId)
                .orElseThrow(() -> new RuntimeException("Structure not found with ID: " + structureId));
    }
}
