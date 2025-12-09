/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EmployeeController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.controller;

import dz.mdn.iaas.common.administration.service.EmployeeService;
import dz.mdn.iaas.common.administration.dto.EmployeeDTO;

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
 * Employee REST Controller
 * Handles employee operations: create, get metadata, delete, get all
 * Based on exact Employee model: F_00=id, F_01=serial, F_02=hiringDate, 
 * F_03=personId, F_04=militaryRankId, F_05=jobId
 */
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    // ========== POST ONE EMPLOYEE ==========

    /**
     * Create new employee
     * Creates employee with military hierarchy integration and career tracking
     */
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        log.info("Creating employee for person ID: {}, military rank ID: {}", 
                employeeDTO.getPersonId(), employeeDTO.getMilitaryRankId());
        
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    // ========== GET METADATA ==========

    /**
     * Get employee metadata by ID
     * Returns employee information with military hierarchy details and career analysis
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for employee ID: {}", id);
        
        EmployeeDTO employeeMetadata = employeeService.getEmployeeById(id);
        
        return ResponseEntity.ok(employeeMetadata);
    }

    // ========== DELETE ONE ==========

    /**
     * Delete employee by ID
     * Removes employee from the military personnel management system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        log.info("Deleting employee with ID: {}", id);
        
        employeeService.deleteEmployee(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all employees with pagination
     * Returns list of all employees ordered by hiring date (most recent first)
     */
    @GetMapping
    public ResponseEntity<Page<EmployeeDTO>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "hiringDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        
        log.debug("Getting all employees - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<EmployeeDTO> employees = employeeService.getAllEmployees(pageable);
        
        return ResponseEntity.ok(employees);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search employees by person name
     */
    @GetMapping("/search/person-name")
    public ResponseEntity<Page<EmployeeDTO>> searchEmployeesByPersonName(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching employees by person name with query: {}", query);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.searchEmployeesByPersonName(query, pageable);
        
        return ResponseEntity.ok(employees);
    }

    /**
     * Search employees by serial number
     */
    @GetMapping("/search/serial")
    public ResponseEntity<Page<EmployeeDTO>> searchEmployeesBySerial(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching employees by serial with query: {}", query);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.searchEmployeesBySerial(query, pageable);
        
        return ResponseEntity.ok(employees);
    }

    /**
     * Advanced search employees by any field
     */
    @GetMapping("/search/advanced")
    public ResponseEntity<Page<EmployeeDTO>> searchEmployeesByAnyField(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Advanced searching employees with query: {}", query);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.searchEmployeesByAnyField(query, pageable);
        
        return ResponseEntity.ok(employees);
    }

    // ========== MILITARY RANK SPECIFIC ENDPOINTS ==========

    /**
     * Get employees by military rank
     */
    @GetMapping("/military-rank/{militaryRankId}")
    public ResponseEntity<Page<EmployeeDTO>> getEmployeesByMilitaryRank(
            @PathVariable Long militaryRankId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting employees for military rank ID: {}", militaryRankId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.getEmployeesByMilitaryRank(militaryRankId, pageable);
        
        return ResponseEntity.ok(employees);
    }

    // ========== JOB SPECIFIC ENDPOINTS ==========

    /**
     * Get employees by job
     */
    @GetMapping("/job/{jobId}")
    public ResponseEntity<Page<EmployeeDTO>> getEmployeesByJob(
            @PathVariable Long jobId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting employees for job ID: {}", jobId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.getEmployeesByJob(jobId, pageable);
        
        return ResponseEntity.ok(employees);
    }

    /**
     * Get employees without job assignment
     */
    @GetMapping("/without-job")
    public ResponseEntity<Page<EmployeeDTO>> getEmployeesWithoutJob(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting employees without job assignment");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.getEmployeesWithoutJob(pageable);
        
        return ResponseEntity.ok(employees);
    }

    /**
     * Get employees with job assignment
     */
    @GetMapping("/with-job")
    public ResponseEntity<Page<EmployeeDTO>> getEmployeesWithJob(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting employees with job assignment");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.getEmployeesWithJob(pageable);
        
        return ResponseEntity.ok(employees);
    }

    // ========== MILITARY HIERARCHY ENDPOINTS ==========

    /**
     * Get general officer employees
     */
    @GetMapping("/hierarchy/general-officers")
    public ResponseEntity<Page<EmployeeDTO>> getGeneralOfficers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting general officer employees");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.getGeneralOfficers(pageable);
        
        return ResponseEntity.ok(employees);
    }

    /**
     * Get senior officer employees
     */
    @GetMapping("/hierarchy/senior-officers")
    public ResponseEntity<Page<EmployeeDTO>> getSeniorOfficers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting senior officer employees");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.getSeniorOfficers(pageable);
        
        return ResponseEntity.ok(employees);
    }

    /**
     * Get company grade officer employees
     */
    @GetMapping("/hierarchy/company-officers")
    public ResponseEntity<Page<EmployeeDTO>> getCompanyGradeOfficers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting company grade officer employees");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.getCompanyGradeOfficers(pageable);
        
        return ResponseEntity.ok(employees);
    }

    /**
     * Get non-commissioned officer employees
     */
    @GetMapping("/hierarchy/nco")
    public ResponseEntity<Page<EmployeeDTO>> getNonCommissionedOfficers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting non-commissioned officer employees");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.getNonCommissionedOfficers(pageable);
        
        return ResponseEntity.ok(employees);
    }

    /**
     * Get enlisted employees
     */
    @GetMapping("/hierarchy/enlisted")
    public ResponseEntity<Page<EmployeeDTO>> getEnlisted(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting enlisted employees");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.getEnlisted(pageable);
        
        return ResponseEntity.ok(employees);
    }

    /**
     * Get commissioned officer employees
     */
    @GetMapping("/hierarchy/commissioned-officers")
    public ResponseEntity<Page<EmployeeDTO>> getCommissionedOfficers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting commissioned officer employees");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.getCommissionedOfficers(pageable);
        
        return ResponseEntity.ok(employees);
    }

    /**
     * Get command-eligible employees
     */
    @GetMapping("/hierarchy/command-eligible")
    public ResponseEntity<Page<EmployeeDTO>> getCommandEligibleEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting command-eligible employees");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.getCommandEligibleEmployees(pageable);
        
        return ResponseEntity.ok(employees);
    }

    // ========== SERVICE BRANCH ENDPOINTS ==========

    /**
     * Get army employees
     */
    @GetMapping("/branch/army")
    public ResponseEntity<Page<EmployeeDTO>> getArmyEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting army employees");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.getArmyEmployees(pageable);
        
        return ResponseEntity.ok(employees);
    }

    /**
     * Get navy employees
     */
    @GetMapping("/branch/navy")
    public ResponseEntity<Page<EmployeeDTO>> getNavyEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting navy employees");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.getNavyEmployees(pageable);
        
        return ResponseEntity.ok(employees);
    }

    /**
     * Get air force employees
     */
    @GetMapping("/branch/air-force")
    public ResponseEntity<Page<EmployeeDTO>> getAirForceEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting air force employees");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.getAirForceEmployees(pageable);
        
        return ResponseEntity.ok(employees);
    }

    /**
     * Get gendarmerie employees
     */
    @GetMapping("/branch/gendarmerie")
    public ResponseEntity<Page<EmployeeDTO>> getGendarmerieEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting gendarmerie employees");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.getGendarmerieEmployees(pageable);
        
        return ResponseEntity.ok(employees);
    }

    // ========== SERVICE CATEGORY ENDPOINTS ==========

    /**
     * Get veteran employees (25+ years of service)
     */
    @GetMapping("/service/veteran")
    public ResponseEntity<Page<EmployeeDTO>> getVeteranEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting veteran employees");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.getVeteranEmployees(pageable);
        
        return ResponseEntity.ok(employees);
    }

    /**
     * Get senior employees (15-25 years of service)
     */
    @GetMapping("/service/senior")
    public ResponseEntity<Page<EmployeeDTO>> getSeniorEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting senior employees");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.getSeniorEmployees(pageable);
        
        return ResponseEntity.ok(employees);
    }

    /**
     * Get experienced employees (5-15 years of service)
     */
    @GetMapping("/service/experienced")
    public ResponseEntity<Page<EmployeeDTO>> getExperiencedEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting experienced employees");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.getExperiencedEmployees(pageable);
        
        return ResponseEntity.ok(employees);
    }

    /**
     * Get junior employees (1-5 years of service)
     */
    @GetMapping("/service/junior")
    public ResponseEntity<Page<EmployeeDTO>> getJuniorEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting junior employees");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.getJuniorEmployees(pageable);
        
        return ResponseEntity.ok(employees);
    }

    /**
     * Get probationary employees (less than 1 year of service)
     */
    @GetMapping("/service/probationary")
    public ResponseEntity<Page<EmployeeDTO>> getProbationaryEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting probationary employees");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.getProbationaryEmployees(pageable);
        
        return ResponseEntity.ok(employees);
    }

    // ========== PROFILE COMPLETENESS ENDPOINTS ==========

    /**
     * Get employees with complete profiles
     */
    @GetMapping("/profile/complete")
    public ResponseEntity<Page<EmployeeDTO>> getEmployeesWithCompleteProfile(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting employees with complete profiles");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.getEmployeesWithCompleteProfile(pageable);
        
        return ResponseEntity.ok(employees);
    }

    /**
     * Get employees with incomplete profiles
     */
    @GetMapping("/profile/incomplete")
    public ResponseEntity<Page<EmployeeDTO>> getEmployeesWithIncompleteProfile(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting employees with incomplete profiles");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.getEmployeesWithIncompleteProfile(pageable);
        
        return ResponseEntity.ok(employees);
    }

    // ========== RETIREMENT ENDPOINTS ==========

    /**
     * Get retirement eligible employees (30+ years of service)
     */
    @GetMapping("/retirement/eligible")
    public ResponseEntity<Page<EmployeeDTO>> getRetirementEligibleEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting retirement eligible employees");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.getRetirementEligibleEmployees(pageable);
        
        return ResponseEntity.ok(employees);
    }

    /**
     * Get employees approaching retirement (25-30 years of service)
     */
    @GetMapping("/retirement/approaching")
    public ResponseEntity<Page<EmployeeDTO>> getApproachingRetirementEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting employees approaching retirement");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "hiringDate"));
        Page<EmployeeDTO> employees = employeeService.getApproachingRetirementEmployees(pageable);
        
        return ResponseEntity.ok(employees);
    }

    // ========== LOOKUP ENDPOINTS ==========

    /**
     * Find employee by serial number
     */
    @GetMapping("/serial/{serial}")
    public ResponseEntity<EmployeeDTO> getEmployeeBySerial(@PathVariable String serial) {
        log.debug("Getting employee by serial: {}", serial);
        
        return employeeService.findBySerial(serial)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update employee metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeDTO employeeDTO) {
        
        log.info("Updating employee with ID: {}", id);
        
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
        
        return ResponseEntity.ok(updatedEmployee);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if employee exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkEmployeeExists(@PathVariable Long id) {
        log.debug("Checking existence of employee ID: {}", id);
        
        boolean exists = employeeService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if person is already an employee
     */
    @GetMapping("/exists/person/{personId}")
    public ResponseEntity<Boolean> checkPersonIsEmployee(@PathVariable Long personId) {
        log.debug("Checking if person ID: {} is already an employee", personId);
        
        boolean exists = employeeService.existsByPersonId(personId);
        
        return ResponseEntity.ok(exists);
    }

    /**
     * Check if serial exists
     */
    @GetMapping("/exists/serial/{serial}")
    public ResponseEntity<Boolean> checkSerialExists(@PathVariable String serial) {
        log.debug("Checking if serial exists: {}", serial);
        
        boolean exists = employeeService.existsBySerial(serial);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get count of employees by military rank
     */
    @GetMapping("/military-rank/{militaryRankId}/count")
    public ResponseEntity<Long> countEmployeesByMilitaryRank(@PathVariable Long militaryRankId) {
        log.debug("Getting count of employees for military rank ID: {}", militaryRankId);
        
        Long count = employeeService.countEmployeesByMilitaryRank(militaryRankId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of employees by job
     */
    @GetMapping("/job/{jobId}/count")
    public ResponseEntity<Long> countEmployeesByJob(@PathVariable Long jobId) {
        log.debug("Getting count of employees for job ID: {}", jobId);
        
        Long count = employeeService.countEmployeesByJob(jobId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of employees without job
     */
    @GetMapping("/count/without-job")
    public ResponseEntity<Long> countEmployeesWithoutJob() {
        log.debug("Getting count of employees without job");
        
        Long count = employeeService.countEmployeesWithoutJob();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of employees with job
     */
    @GetMapping("/count/with-job")
    public ResponseEntity<Long> countEmployeesWithJob() {
        log.debug("Getting count of employees with job");
        
        Long count = employeeService.countEmployeesWithJob();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of general officer employees
     */
    @GetMapping("/count/general-officers")
    public ResponseEntity<Long> countGeneralOfficers() {
        log.debug("Getting count of general officer employees");
        
        Long count = employeeService.countGeneralOfficers();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of commissioned officer employees
     */
    @GetMapping("/count/commissioned-officers")
    public ResponseEntity<Long> countCommissionedOfficers() {
        log.debug("Getting count of commissioned officer employees");
        
        Long count = employeeService.countCommissionedOfficers();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get total count of employees
     */
    @GetMapping("/count/total")
    public ResponseEntity<Long> countTotalEmployees() {
        log.debug("Getting total count of employees");
        
        Long count = employeeService.countTotalEmployees();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get employee info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<EmployeeInfoResponse> getEmployeeInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for employee ID: {}", id);
        
        try {
            return employeeService.findOne(id)
                    .map(employeeDTO -> {
                        EmployeeInfoResponse response = EmployeeInfoResponse.builder()
                                .employeeMetadata(employeeDTO)
                                .displayName(employeeDTO.getDisplayName())
                                .fullDisplay(employeeDTO.getFullDisplay())
                                .militaryDisplay(employeeDTO.getMilitaryDisplay())
                                .yearsOfService(employeeDTO.getYearsOfService())
                                .monthsOfService(employeeDTO.getMonthsOfService())
                                .daysOfService(employeeDTO.getDaysOfService())
                                .serviceCategory(employeeDTO.getServiceCategory())
                                .rankLevel(employeeDTO.getRankLevel())
                                .authorityLevel(employeeDTO.getAuthorityLevel())
                                .serviceBranch(employeeDTO.getServiceBranch())
                                .canCommandUnits(employeeDTO.canCommandUnits())
                                .isCommissionedOfficer(employeeDTO.isCommissionedOfficer())
                                .employeeStatus(employeeDTO.getEmployeeStatus())
                                .retirementEligibility(employeeDTO.getRetirementEligibility())
                                .promotionEligibility(employeeDTO.getPromotionEligibility())
                                .age(employeeDTO.getAge())
                                .hasCompleteProfile(employeeDTO.hasCompleteProfile())
                                .hasJobAssignment(employeeDTO.hasJobAssignment())
                                .completenessPercentage(employeeDTO.getCompletenessPercentage())
                                .shortDisplay(employeeDTO.getShortDisplay())
                                .officialDisplay(employeeDTO.getOfficialDisplay())
                                .careerSummary(employeeDTO.getCareerSummary())
                                .contactInfo(employeeDTO.getContactInfo())
                                .militaryClassification(employeeDTO.getMilitaryClassification())
                                .nextPromotionEstimate(employeeDTO.getNextPromotionEstimate())
                                .securityClearanceLevel(employeeDTO.getSecurityClearanceLevel())
                                .trainingRequirements(employeeDTO.getTrainingRequirements())
                                .performanceEvaluationPeriod(employeeDTO.getPerformanceEvaluationPeriod())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting employee info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class EmployeeInfoResponse {
        private EmployeeDTO employeeMetadata;
        private String displayName;
        private String fullDisplay;
        private String militaryDisplay;
        private Long yearsOfService;
        private Long monthsOfService;
        private Long daysOfService;
        private String serviceCategory;
        private String rankLevel;
        private String authorityLevel;
        private String serviceBranch;
        private Boolean canCommandUnits;
        private Boolean isCommissionedOfficer;
        private String employeeStatus;
        private String retirementEligibility;
        private String promotionEligibility;
        private Integer age;
        private Boolean hasCompleteProfile;
        private Boolean hasJobAssignment;
        private Integer completenessPercentage;
        private String shortDisplay;
        private String officialDisplay;
        private String careerSummary;
        private String contactInfo;
        private String militaryClassification;
        private String nextPromotionEstimate;
        private String securityClearanceLevel;
        private String trainingRequirements;
        private String performanceEvaluationPeriod;
    }
}