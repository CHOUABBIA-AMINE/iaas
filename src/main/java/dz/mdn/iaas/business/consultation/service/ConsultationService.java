/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ConsultationService
 *	@CreatedOn	: 10-12-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.mdn.iaas.business.consultation.controller.ConsultationController;
import dz.mdn.iaas.business.consultation.dto.ConsultationDTO;
import dz.mdn.iaas.business.consultation.model.AwardMethod;
import dz.mdn.iaas.business.consultation.model.Consultation;
import dz.mdn.iaas.business.consultation.model.ConsultationStep;
import dz.mdn.iaas.business.consultation.repository.AwardMethodRepository;
import dz.mdn.iaas.business.consultation.repository.ConsultationRepository;
import dz.mdn.iaas.business.consultation.repository.ConsultationStepRepository;
import dz.mdn.iaas.business.core.model.ApprovalStatus;
import dz.mdn.iaas.business.core.model.ProcurementDirector;
import dz.mdn.iaas.business.core.model.ProcurementNature;
import dz.mdn.iaas.business.core.model.ProcurementStatus;
import dz.mdn.iaas.business.core.repository.ApprovalStatusRepository;
import dz.mdn.iaas.business.core.repository.ProcurementDirectorRepository;
import dz.mdn.iaas.business.core.repository.ProcurementNatureRepository;
import dz.mdn.iaas.business.core.repository.ProcurementStatusRepository;
import dz.mdn.iaas.business.plan.model.BudgetType;
import dz.mdn.iaas.business.plan.repository.BudgetTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
* Complete Consultation Service with CRUD operations
* Handles consultation management operations with complex relationships and unique constraints
* Based on exact field names: F_01=internalId, F_02=consultationYear, F_03=reference, etc.
* Required fields: F_01, F_02, F_06 (designationFr), and all foreign keys (F_15 to F_21)
* Unique constraint: F_01 + F_02 (internalId + consultationYear)
*/
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ConsultationService {

   private final ConsultationRepository consultationRepository;
   private final AwardMethodRepository awardMethodRepository;
   private final ProcurementNatureRepository procurementNatureRepository;
   private final BudgetTypeRepository budgetTypeRepository;
   private final ProcurementStatusRepository procurementStatusRepository;
   private final ApprovalStatusRepository approvalStatusRepository;
   private final ProcurementDirectorRepository procurementDirectorRepository;
   private final ConsultationStepRepository consultationStepRepository;

   // ========== CREATE OPERATIONS ==========

   /**
    * Create new consultation
    */
   public ConsultationDTO createConsultation(ConsultationDTO consultationDTO) {
       log.info("Creating consultation for year: {} with internal ID: {} and French designation: {}", 
               consultationDTO.getConsultationYear(), consultationDTO.getInternalId(), 
               consultationDTO.getDesignationFr());

       // Validate required fields
       validateRequiredFields(consultationDTO, "create");
       
       // Check for unique constraint violation
       validateUniqueConstraint(consultationDTO, null);
       
       // Validate and get all foreign key entities
       AwardMethod awardMethod = validateAndGetAwardMethod(consultationDTO.getAwardMethodId());
       ProcurementNature procurementNature = validateAndGetProcurementNature(consultationDTO.getProcurementNatureId());
       BudgetType budgetType = validateAndGetBudgetType(consultationDTO.getBudgetTypeId());
       ProcurementStatus procurementStatus = validateAndGetProcurementStatus(consultationDTO.getProcurementStatusId());
       ApprovalStatus approvalStatus = validateAndGetApprovalStatus(consultationDTO.getApprovalStatusId());
       ProcurementDirector procurementDirector = validateAndGetProcurementDirector(consultationDTO.getProcurementDirectorId());
       ConsultationStep consultationStep = validateAndGetConsultationStep(consultationDTO.getConsultationStepId());

       // Create entity with exact field mapping
       Consultation consultation = new Consultation();
       consultation.setInternalId(consultationDTO.getInternalId()); // F_01 - required
       consultation.setConsultationYear(consultationDTO.getConsultationYear()); // F_02 - required
       consultation.setReference(consultationDTO.getReference()); // F_03
       consultation.setDesignationAr(consultationDTO.getDesignationAr()); // F_04
       consultation.setDesignationEn(consultationDTO.getDesignationEn()); // F_05
       consultation.setDesignationFr(consultationDTO.getDesignationFr()); // F_06 - required
       consultation.setAllocatedAmount(consultationDTO.getAllocatedAmount() != null ? consultationDTO.getAllocatedAmount() : 0.0); // F_07
       consultation.setFinancialEstimation(consultationDTO.getFinancialEstimation() != null ? consultationDTO.getFinancialEstimation() : 0.0); // F_08
       consultation.setStartDate(consultationDTO.getStartDate()); // F_09
       consultation.setApprovalReference(consultationDTO.getApprovalReference()); // F_10
       consultation.setApprovalDate(consultationDTO.getApprovalDate()); // F_11
       consultation.setPublishDate(consultationDTO.getPublishDate()); // F_12
       consultation.setDeadline(consultationDTO.getDeadline()); // F_13
       consultation.setObservation(consultationDTO.getObservation()); // F_14
       
       // Set all required foreign key relationships
       consultation.setAwardMethod(awardMethod); // F_15 - required
       consultation.setProcurementNature(procurementNature); // F_16 - required
       consultation.setBudgetType(budgetType); // F_17 - required
       consultation.setProcurementStatus(procurementStatus); // F_18 - required
       consultation.setApprovalStatus(approvalStatus); // F_19 - required
       consultation.setProcurementDirector(procurementDirector); // F_20 - required
       consultation.setConsultationStep(consultationStep); // F_21 - required

       // Generate additional metadata if needed
       generateConsultationMetadata(consultation);

       Consultation savedConsultation = consultationRepository.save(consultation);
       log.info("Successfully created consultation with ID: {}", savedConsultation.getId());
       
       return mapToDTO(savedConsultation);
   }

   // ========== READ OPERATIONS ==========

   /**
    * Get consultation by ID
    */
   @Transactional(readOnly = true)
   public ConsultationDTO getConsultationById(Long id) {
       log.debug("Getting consultation with ID: {}", id);
       Consultation consultation = consultationRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Consultation not found with ID: " + id));
       return mapToDTO(consultation);
   }

   /**
    * Get consultation entity by ID
    */
   @Transactional(readOnly = true)
   public Consultation getConsultationEntityById(Long id) {
       return consultationRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Consultation not found with ID: " + id));
   }

   /**
    * Get consultation by ID with all relationships loaded
    */
   @Transactional(readOnly = true)
   public ConsultationDTO getConsultationWithDetails(Long id) {
       log.debug("Getting consultation with details for ID: {}", id);
       Consultation consultation = consultationRepository.findByIdWithDetails(id)
               .orElseThrow(() -> new RuntimeException("Consultation not found with ID: " + id));
       ConsultationDTO dto = mapToDTO(consultation);
       enrichConsultationDTO(dto, consultation);
       return dto;
   }

   /**
    * Find consultation by internal ID and year (unique constraint)
    */
   @Transactional(readOnly = true)
   public Optional<ConsultationDTO> findByInternalIdAndYear(String internalId, String consultationYear) {
       log.debug("Finding consultation with internal ID: {} and year: {}", internalId, consultationYear);
       return consultationRepository.findByInternalIdAndConsultationYear(internalId, consultationYear)
               .map(this::mapToDTO);
   }

   /**
    * Find consultation by reference
    */
   @Transactional(readOnly = true)
   public Optional<ConsultationDTO> findByReference(String reference) {
       log.debug("Finding consultation with reference: {}", reference);
       return consultationRepository.findByReference(reference)
               .map(this::mapToDTO);
   }

   /**
    * Get all consultations with pagination
    */
   @Transactional(readOnly = true)
   public Page<ConsultationDTO> getAllConsultations(Pageable pageable) {
       log.debug("Getting all consultations with pagination");
       Page<Consultation> consultations = consultationRepository.findAll(pageable);
       return consultations.map(this::mapToDTO);
   }

   /**
    * Find one consultation by ID
    */
   @Transactional(readOnly = true)
   public Optional<ConsultationDTO> findOne(Long id) {
       log.debug("Finding consultation by ID: {}", id);
       return consultationRepository.findById(id)
               .map(this::mapToDTO);
   }

   /**
    * Get consultations by year
    */
   @Transactional(readOnly = true)
   public Page<ConsultationDTO> getConsultationsByYear(String consultationYear, Pageable pageable) {
       log.debug("Getting consultations for year: {}", consultationYear);
       Page<Consultation> consultations = consultationRepository.findByConsultationYear(consultationYear, pageable);
       return consultations.map(this::mapToDTO);
   }

   /**
    * Search consultations by designation
    */
   @Transactional(readOnly = true)
   public Page<ConsultationDTO> searchConsultations(String searchTerm, Pageable pageable) {
       log.debug("Searching consultations with term: {}", searchTerm);
       if (searchTerm == null || searchTerm.trim().isEmpty()) {
           return getAllConsultations(pageable);
       }
       Page<Consultation> consultations = consultationRepository.searchByDesignation(searchTerm.trim(), pageable);
       return consultations.map(this::mapToDTO);
   }

   // ========== FILTER OPERATIONS ==========

   /**
    * Get consultations by procurement status
    */
   @Transactional(readOnly = true)
   public Page<ConsultationDTO> getConsultationsByStatus(Long statusId, Pageable pageable) {
       log.debug("Getting consultations by procurement status ID: {}", statusId);
       Page<Consultation> consultations = consultationRepository.findByProcurementStatusId(statusId, pageable);
       return consultations.map(this::mapToDTO);
   }

   /**
    * Get consultations by award method
    */
   @Transactional(readOnly = true)
   public Page<ConsultationDTO> getConsultationsByAwardMethod(Long awardMethodId, Pageable pageable) {
       log.debug("Getting consultations by award method ID: {}", awardMethodId);
       Page<Consultation> consultations = consultationRepository.findByAwardMethodId(awardMethodId, pageable);
       return consultations.map(this::mapToDTO);
   }

   /**
    * Get active consultations
    */
   @Transactional(readOnly = true)
   public Page<ConsultationDTO> getActiveConsultations(Pageable pageable) {
       log.debug("Getting active consultations");
       Date currentDate = new Date();
       Page<Consultation> consultations = consultationRepository.findActiveConsultations(currentDate, pageable);
       return consultations.map(this::mapToDTO);
   }

   /**
    * Get expired consultations
    */
   @Transactional(readOnly = true)
   public Page<ConsultationDTO> getExpiredConsultations(Pageable pageable) {
       log.debug("Getting expired consultations");
       Date currentDate = new Date();
       Page<Consultation> consultations = consultationRepository.findExpiredConsultations(currentDate, pageable);
       return consultations.map(this::mapToDTO);
   }

   /**
    * Get high-value consultations above threshold
    */
   @Transactional(readOnly = true)
   public Page<ConsultationDTO> getHighValueConsultations(double threshold, Pageable pageable) {
       log.debug("Getting high-value consultations above threshold: {}", threshold);
       Page<Consultation> consultations = consultationRepository.findHighValueConsultations(threshold, pageable);
       return consultations.map(this::mapToDTO);
   }

   // ========== STATISTICS OPERATIONS ==========

   /**
    * Get consultation statistics for a specific year
    */
   @Transactional(readOnly = true)
   public ConsultationController.ConsultationStatistics getConsultationStatistics(String year) {
       log.debug("Getting consultation statistics for year: {}", year);
       
       Long totalConsultations = consultationRepository.countByConsultationYear(year);
       Double totalAllocatedAmount = consultationRepository.sumAllocatedAmountByYear(year);
       Double totalFinancialEstimation = consultationRepository.sumFinancialEstimationByYear(year);
       
       Double averageConsultationValue = totalConsultations > 0 && totalAllocatedAmount != null ? 
               totalAllocatedAmount / totalConsultations : 0.0;
       
       // Count active and expired consultations for the year
       Date currentDate = new Date();
       Long activeConsultations = consultationRepository.countActiveConsultationsByYear(year, currentDate);
       Long expiredConsultations = consultationRepository.countExpiredConsultationsByYear(year, currentDate);
       
       // Count consultations with submissions
       Long consultationsWithSubmissions = consultationRepository.countConsultationsWithSubmissionsByYear(year);
       
       // Count high-value consultations (above 1M threshold)
       Long highValueConsultations = consultationRepository.countHighValueConsultationsByYear(year, 1000000.0);
       
       // Calculate average competitive ratio (submissions per consultation)
       Double averageCompetitiveRatio = totalConsultations > 0 && consultationsWithSubmissions > 0 ?
               consultationsWithSubmissions.doubleValue() / totalConsultations.doubleValue() : 0.0;
       
       return ConsultationController.ConsultationStatistics.builder()
               .year(year)
               .totalConsultations(totalConsultations)
               .totalAllocatedAmount(totalAllocatedAmount != null ? totalAllocatedAmount : 0.0)
               .totalFinancialEstimation(totalFinancialEstimation != null ? totalFinancialEstimation : 0.0)
               .averageConsultationValue(averageConsultationValue)
               .averageCompetitiveRatio(averageCompetitiveRatio)
               .activeConsultations(activeConsultations)
               .expiredConsultations(expiredConsultations)
               .consultationsWithSubmissions(consultationsWithSubmissions)
               .highValueConsultations(highValueConsultations)
               .generatedAt(new Date())
               .build();
   }

   // ========== UPDATE OPERATIONS ==========

   /**
    * Update consultation
    */
   public ConsultationDTO updateConsultation(Long id, ConsultationDTO consultationDTO) {
       log.info("Updating consultation with ID: {}", id);
       Consultation existingConsultation = getConsultationEntityById(id);

       // Validate required fields
       validateRequiredFields(consultationDTO, "update");
       
       // Check for unique constraint violation (excluding current record)
       validateUniqueConstraint(consultationDTO, id);
       
       // Validate and get foreign key entities if they are being changed
       AwardMethod awardMethod = null;
       ProcurementNature procurementNature = null;
       BudgetType budgetType = null;
       ProcurementStatus procurementStatus = null;
       ApprovalStatus approvalStatus = null;
       ProcurementDirector procurementDirector = null;
       ConsultationStep consultationStep = null;

       if (consultationDTO.getAwardMethodId() != null && 
           !consultationDTO.getAwardMethodId().equals(existingConsultation.getAwardMethod().getId())) {
           awardMethod = validateAndGetAwardMethod(consultationDTO.getAwardMethodId());
       }
       
       if (consultationDTO.getProcurementNatureId() != null && 
           !consultationDTO.getProcurementNatureId().equals(existingConsultation.getProcurementNature().getId())) {
           procurementNature = validateAndGetProcurementNature(consultationDTO.getProcurementNatureId());
       }
       
       if (consultationDTO.getBudgetTypeId() != null && 
           !consultationDTO.getBudgetTypeId().equals(existingConsultation.getBudgetType().getId())) {
           budgetType = validateAndGetBudgetType(consultationDTO.getBudgetTypeId());
       }
       
       if (consultationDTO.getProcurementStatusId() != null && 
           !consultationDTO.getProcurementStatusId().equals(existingConsultation.getProcurementStatus().getId())) {
           procurementStatus = validateAndGetProcurementStatus(consultationDTO.getProcurementStatusId());
       }
       
       if (consultationDTO.getApprovalStatusId() != null && 
           !consultationDTO.getApprovalStatusId().equals(existingConsultation.getApprovalStatus().getId())) {
           approvalStatus = validateAndGetApprovalStatus(consultationDTO.getApprovalStatusId());
       }
       
       if (consultationDTO.getProcurementDirectorId() != null && 
           !consultationDTO.getProcurementDirectorId().equals(existingConsultation.getProcurementDirector().getId())) {
           procurementDirector = validateAndGetProcurementDirector(consultationDTO.getProcurementDirectorId());
       }
       
       if (consultationDTO.getConsultationStepId() != null && 
           !consultationDTO.getConsultationStepId().equals(existingConsultation.getConsultationStep().getId())) {
           consultationStep = validateAndGetConsultationStep(consultationDTO.getConsultationStepId());
       }

       // Update fields with exact field mapping
       existingConsultation.setInternalId(consultationDTO.getInternalId()); // F_01 - required
       existingConsultation.setConsultationYear(consultationDTO.getConsultationYear()); // F_02 - required
       existingConsultation.setReference(consultationDTO.getReference()); // F_03
       existingConsultation.setDesignationAr(consultationDTO.getDesignationAr()); // F_04
       existingConsultation.setDesignationEn(consultationDTO.getDesignationEn()); // F_05
       existingConsultation.setDesignationFr(consultationDTO.getDesignationFr()); // F_06 - required
       existingConsultation.setAllocatedAmount(consultationDTO.getAllocatedAmount() != null ? consultationDTO.getAllocatedAmount() : 0.0); // F_07
       existingConsultation.setFinancialEstimation(consultationDTO.getFinancialEstimation() != null ? consultationDTO.getFinancialEstimation() : 0.0); // F_08
       existingConsultation.setStartDate(consultationDTO.getStartDate()); // F_09
       existingConsultation.setApprovalReference(consultationDTO.getApprovalReference()); // F_10
       existingConsultation.setApprovalDate(consultationDTO.getApprovalDate()); // F_11
       existingConsultation.setPublishDate(consultationDTO.getPublishDate()); // F_12
       existingConsultation.setDeadline(consultationDTO.getDeadline()); // F_13
       existingConsultation.setObservation(consultationDTO.getObservation()); // F_14
       
       // Update foreign key relationships if they changed
       if (awardMethod != null) {
           existingConsultation.setAwardMethod(awardMethod); // F_15
       }
       if (procurementNature != null) {
           existingConsultation.setProcurementNature(procurementNature); // F_16
       }
       if (budgetType != null) {
           existingConsultation.setBudgetType(budgetType); // F_17
       }
       if (procurementStatus != null) {
           existingConsultation.setProcurementStatus(procurementStatus); // F_18
       }
       if (approvalStatus != null) {
           existingConsultation.setApprovalStatus(approvalStatus); // F_19
       }
       if (procurementDirector != null) {
           existingConsultation.setProcurementDirector(procurementDirector); // F_20
       }
       if (consultationStep != null) {
           existingConsultation.setConsultationStep(consultationStep); // F_21
       }

       Consultation updatedConsultation = consultationRepository.save(existingConsultation);
       log.info("Successfully updated consultation with ID: {}", id);
       
       return mapToDTO(updatedConsultation);
   }

   // ========== DELETE OPERATIONS ==========

   /**
    * Delete consultation
    */
   public void deleteConsultation(Long id) {
       log.info("Deleting consultation with ID: {}", id);
       Consultation consultation = getConsultationEntityById(id);
       
       consultationRepository.delete(consultation);
       log.info("Successfully deleted consultation with ID: {}", id);
   }

   /**
    * Delete consultation by ID (direct)
    */
   public void deleteConsultationById(Long id) {
       log.info("Deleting consultation by ID: {}", id);
       if (!consultationRepository.existsById(id)) {
           throw new RuntimeException("Consultation not found with ID: " + id);
       }
       
       consultationRepository.deleteById(id);
       log.info("Successfully deleted consultation with ID: {}", id);
   }

   // ========== UTILITY METHODS ==========

   /**
    * Check if consultation exists
    */
   @Transactional(readOnly = true)
   public boolean existsById(Long id) {
       return consultationRepository.existsById(id);
   }

   /**
    * Check if consultation exists by internal ID and year
    */
   @Transactional(readOnly = true)
   public boolean existsByInternalIdAndYear(String internalId, String consultationYear) {
       return consultationRepository.findByInternalIdAndConsultationYear(internalId, consultationYear).isPresent();
   }

   /**
    * Get total count of consultations
    */
   @Transactional(readOnly = true)
   public Long getTotalCount() {
       return consultationRepository.count();
   }

   // ========== HELPER METHODS ==========

   /**
    * Generate consultation metadata (reference, etc.)
    */
   private void generateConsultationMetadata(Consultation consultation) {
       // Generate reference if not provided
       if (consultation.getReference() == null || consultation.getReference().trim().isEmpty()) {
           consultation.setReference(String.format("CONS-%s-%s", 
                   consultation.getInternalId(), consultation.getConsultationYear()));
       }
   }

   /**
    * Map entity to DTO
    */
   private ConsultationDTO mapToDTO(Consultation consultation) {
       ConsultationDTO dto = ConsultationDTO.builder()
               .id(consultation.getId())
               .internalId(consultation.getInternalId())
               .consultationYear(consultation.getConsultationYear())
               .reference(consultation.getReference())
               .designationAr(consultation.getDesignationAr())
               .designationEn(consultation.getDesignationEn())
               .designationFr(consultation.getDesignationFr())
               .allocatedAmount(consultation.getAllocatedAmount())
               .financialEstimation(consultation.getFinancialEstimation())
               .startDate(consultation.getStartDate())
               .approvalReference(consultation.getApprovalReference())
               .approvalDate(consultation.getApprovalDate())
               .publishDate(consultation.getPublishDate())
               .deadline(consultation.getDeadline())
               .observation(consultation.getObservation())
               .build();
       
       // Set foreign key IDs and designations
       if (consultation.getAwardMethod() != null) {
           dto.setAwardMethodId(consultation.getAwardMethod().getId());
           dto.setAwardMethodDesignation(consultation.getAwardMethod().getDesignationFr());
       }
       if (consultation.getProcurementNature() != null) {
           dto.setProcurementNatureId(consultation.getProcurementNature().getId());
           dto.setProcurementNatureDesignation(consultation.getProcurementNature().getDesignationFr());
       }
       if (consultation.getBudgetType() != null) {
           dto.setBudgetTypeId(consultation.getBudgetType().getId());
           dto.setBudgetTypeDesignation(consultation.getBudgetType().getDesignationFr());
       }
       if (consultation.getProcurementStatus() != null) {
           dto.setProcurementStatusId(consultation.getProcurementStatus().getId());
           dto.setProcurementStatusDesignation(consultation.getProcurementStatus().getDesignationFr());
       }
       if (consultation.getApprovalStatus() != null) {
           dto.setApprovalStatusId(consultation.getApprovalStatus().getId());
           dto.setApprovalStatusDesignation(consultation.getApprovalStatus().getDesignationFr());
       }
       if (consultation.getProcurementDirector() != null) {
           dto.setProcurementDirectorId(consultation.getProcurementDirector().getId());
           dto.setProcurementDirectorDesignation(consultation.getProcurementDirector().getDesignationFr());
       }
       if (consultation.getConsultationStep() != null) {
           dto.setConsultationStepId(consultation.getConsultationStep().getId());
           dto.setConsultationStepDesignation(consultation.getConsultationStep().getDesignationFr());
       }
       
       return dto;
   }

   /**
    * Enrich consultation DTO with calculated fields
    */
   private void enrichConsultationDTO(ConsultationDTO dto, Consultation consultation) {
       dto.setSubmissionCount(consultation.getSubmissions() != null ? consultation.getSubmissions().size() : 0);
       
       // Calculate days until deadline
       if (consultation.getDeadline() != null) {
           long daysUntilDeadline = ChronoUnit.DAYS.between(
                   LocalDate.now(),
                   consultation.getDeadline().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
           dto.setDaysUntilDeadline((int) daysUntilDeadline);
           dto.setIsExpired(daysUntilDeadline < 0);
       }

       // Set active status
       Date now = new Date();
       dto.setIsActive(consultation.getPublishDate() != null &&
               consultation.getPublishDate().before(now) &&
               (consultation.getDeadline() == null || consultation.getDeadline().after(now)));
   }

   // ========== VALIDATION METHODS ==========

   /**
    * Validate required fields
    */
   private void validateRequiredFields(ConsultationDTO consultationDTO, String operation) {
       if (consultationDTO.getInternalId() == null || consultationDTO.getInternalId().trim().isEmpty()) {
           throw new RuntimeException("Internal ID is required for " + operation);
       }
       if (consultationDTO.getConsultationYear() == null || consultationDTO.getConsultationYear().trim().isEmpty()) {
           throw new RuntimeException("Consultation year is required for " + operation);
       }
       if (consultationDTO.getDesignationFr() == null || consultationDTO.getDesignationFr().trim().isEmpty()) {
           throw new RuntimeException("French designation is required for " + operation);
       }
       // Validate all required foreign keys
       if (consultationDTO.getAwardMethodId() == null) {
           throw new RuntimeException("Award method is required for " + operation);
       }
       if (consultationDTO.getProcurementNatureId() == null) {
           throw new RuntimeException("Procurement nature is required for " + operation);
       }
       if (consultationDTO.getBudgetTypeId() == null) {
           throw new RuntimeException("Budget type is required for " + operation);
       }
       if (consultationDTO.getProcurementStatusId() == null) {
           throw new RuntimeException("Procurement status is required for " + operation);
       }
       if (consultationDTO.getApprovalStatusId() == null) {
           throw new RuntimeException("Approval status is required for " + operation);
       }
       if (consultationDTO.getProcurementDirectorId() == null) {
           throw new RuntimeException("Procurement director is required for " + operation);
       }
       if (consultationDTO.getConsultationStepId() == null) {
           throw new RuntimeException("Consultation step is required for " + operation);
       }
   }

   /**
    * Validate unique constraint
    */
   private void validateUniqueConstraint(ConsultationDTO consultationDTO, Long excludeId) {
       if (excludeId == null) {
           if (consultationRepository.findByInternalIdAndConsultationYear(
                   consultationDTO.getInternalId(), consultationDTO.getConsultationYear()).isPresent()) {
               throw new RuntimeException("Consultation with internal ID '" + consultationDTO.getInternalId() + 
                       "' and year '" + consultationDTO.getConsultationYear() + "' already exists");
           }
       } else {
           Optional<Consultation> existing = consultationRepository.findByInternalIdAndConsultationYear(
                   consultationDTO.getInternalId(), consultationDTO.getConsultationYear());
           if (existing.isPresent() && !existing.get().getId().equals(excludeId)) {
               throw new RuntimeException("Another consultation with internal ID '" + consultationDTO.getInternalId() + 
                       "' and year '" + consultationDTO.getConsultationYear() + "' already exists");
           }
       }
   }

   // ========== FOREIGN KEY VALIDATION METHODS ==========

   private AwardMethod validateAndGetAwardMethod(Long awardMethodId) {
       return awardMethodRepository.findById(awardMethodId)
               .orElseThrow(() -> new RuntimeException("AwardMethod not found with ID: " + awardMethodId));
   }

   private ProcurementNature validateAndGetProcurementNature(Long procurementNatureId) {
       return procurementNatureRepository.findById(procurementNatureId)
               .orElseThrow(() -> new RuntimeException("ProcurementNature not found with ID: " + procurementNatureId));
   }

   private BudgetType validateAndGetBudgetType(Long budgetTypeId) {
       return budgetTypeRepository.findById(budgetTypeId)
               .orElseThrow(() -> new RuntimeException("BudgetType not found with ID: " + budgetTypeId));
   }

   private ProcurementStatus validateAndGetProcurementStatus(Long procurementStatusId) {
       return procurementStatusRepository.findById(procurementStatusId)
               .orElseThrow(() -> new RuntimeException("ProcurementStatus not found with ID: " + procurementStatusId));
   }

   private ApprovalStatus validateAndGetApprovalStatus(Long approvalStatusId) {
       return approvalStatusRepository.findById(approvalStatusId)
               .orElseThrow(() -> new RuntimeException("ApprovalStatus not found with ID: " + approvalStatusId));
   }

   private ProcurementDirector validateAndGetProcurementDirector(Long procurementDirectorId) {
       return procurementDirectorRepository.findById(procurementDirectorId)
               .orElseThrow(() -> new RuntimeException("ProcurementDirector not found with ID: " + procurementDirectorId));
   }

   private ConsultationStep validateAndGetConsultationStep(Long consultationStepId) {
       return consultationStepRepository.findById(consultationStepId)
               .orElseThrow(() -> new RuntimeException("ConsultationStep not found with ID: " + consultationStepId));
   }
}