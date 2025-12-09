/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EmployeeService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.mdn.iaas.common.administration.dto.EmployeeDTO;
import dz.mdn.iaas.common.administration.model.Employee;
import dz.mdn.iaas.common.administration.repository.EmployeeRepository;
import dz.mdn.iaas.common.administration.repository.JobRepository;
import dz.mdn.iaas.common.administration.repository.MilitaryRankRepository;
import dz.mdn.iaas.common.administration.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Employee Service with CRUD operations
 * Handles employee management operations with military hierarchy integration and career tracking
 * Based on exact field names and business rules for military personnel management
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    
    // Repository beans for related entities (injected as needed)
    private final PersonRepository personRepository;
    private final MilitaryRankRepository militaryRankRepository;
    private final JobRepository jobRepository;

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new employee
     */
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        log.info("Creating employee for person ID: {}, military rank ID: {}", 
                employeeDTO.getPersonId(), employeeDTO.getMilitaryRankId());

        // Validate required fields and business rules
        validateRequiredFields(employeeDTO, "create");
        validateBusinessRules(employeeDTO, "create");

        // Check for unique constraints and business validations
        validateUniqueConstraints(employeeDTO, null);

        // Create entity with exact field mapping
        Employee employee = new Employee();
        mapDtoToEntity(employeeDTO, employee);

        // Handle foreign key relationships
        setEntityRelationships(employeeDTO, employee);

        Employee savedEmployee = employeeRepository.save(employee);
        log.info("Successfully created employee with ID: {}", savedEmployee.getId());

        return EmployeeDTO.fromEntityWithRelations(savedEmployee);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get employee by ID
     */
    @Transactional(readOnly = true)
    public EmployeeDTO getEmployeeById(Long id) {
        log.debug("Getting employee with ID: {}", id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));

        return EmployeeDTO.fromEntityWithRelations(employee);
    }

    /**
     * Get employee entity by ID
     */
    @Transactional(readOnly = true)
    public Employee getEmployeeEntityById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
    }

    /**
     * Get all employees with pagination
     */
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getAllEmployees(Pageable pageable) {
        log.debug("Getting all employees with pagination");

        Page<Employee> employees = employeeRepository.findAllOrderByHiringDate(pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    /**
     * Find one employee by ID
     */
    @Transactional(readOnly = true)
    public Optional<EmployeeDTO> findOne(Long id) {
        log.debug("Finding employee by ID: {}", id);

        return employeeRepository.findById(id)
                .map(EmployeeDTO::fromEntityWithRelations);
    }

    /**
     * Find employee by serial number
     */
    @Transactional(readOnly = true)
    public Optional<EmployeeDTO> findBySerial(String serial) {
        log.debug("Finding employee by serial: {}", serial);

        return employeeRepository.findBySerial(serial)
                .map(EmployeeDTO::fromEntity);
    }

    /**
     * Search employees by person name
     */
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> searchEmployeesByPersonName(String searchTerm, Pageable pageable) {
        log.debug("Searching employees by person name with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllEmployees(pageable);
        }

        Page<Employee> employees = employeeRepository.searchByPersonName(searchTerm.trim(), pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    /**
     * Search employees by serial number
     */
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> searchEmployeesBySerial(String searchTerm, Pageable pageable) {
        log.debug("Searching employees by serial with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllEmployees(pageable);
        }

        Page<Employee> employees = employeeRepository.searchBySerial(searchTerm.trim(), pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    /**
     * Search employees by any field
     */
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> searchEmployeesByAnyField(String searchTerm, Pageable pageable) {
        log.debug("Searching employees by any field with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllEmployees(pageable);
        }

        Page<Employee> employees = employeeRepository.searchByAnyField(searchTerm.trim(), pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    /**
     * Get employees by military rank
     */
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getEmployeesByMilitaryRank(Long militaryRankId, Pageable pageable) {
        log.debug("Getting employees for military rank ID: {}", militaryRankId);

        Page<Employee> employees = employeeRepository.findByMilitaryRank(militaryRankId, pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    /**
     * Get employees by job
     */
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getEmployeesByJob(Long jobId, Pageable pageable) {
        log.debug("Getting employees for job ID: {}", jobId);

        Page<Employee> employees = employeeRepository.findByJob(jobId, pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    /**
     * Get employees without job assignment
     */
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getEmployeesWithoutJob(Pageable pageable) {
        log.debug("Getting employees without job assignment");

        Page<Employee> employees = employeeRepository.findWithoutJob(pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    /**
     * Get employees with job assignment
     */
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getEmployeesWithJob(Pageable pageable) {
        log.debug("Getting employees with job assignment");

        Page<Employee> employees = employeeRepository.findWithJob(pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    /**
     * Get employees by military hierarchy level
     */
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getGeneralOfficers(Pageable pageable) {
        log.debug("Getting general officer employees");

        Page<Employee> employees = employeeRepository.findGeneralOfficers(pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getSeniorOfficers(Pageable pageable) {
        log.debug("Getting senior officer employees");

        Page<Employee> employees = employeeRepository.findSeniorOfficers(pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getCompanyGradeOfficers(Pageable pageable) {
        log.debug("Getting company grade officer employees");

        Page<Employee> employees = employeeRepository.findCompanyGradeOfficers(pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getNonCommissionedOfficers(Pageable pageable) {
        log.debug("Getting non-commissioned officer employees");

        Page<Employee> employees = employeeRepository.findNonCommissionedOfficers(pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getEnlisted(Pageable pageable) {
        log.debug("Getting enlisted employees");

        Page<Employee> employees = employeeRepository.findEnlisted(pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getCommissionedOfficers(Pageable pageable) {
        log.debug("Getting commissioned officer employees");

        Page<Employee> employees = employeeRepository.findCommissionedOfficers(pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    /**
     * Get employees by service branch
     */
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getArmyEmployees(Pageable pageable) {
        log.debug("Getting army employees");

        Page<Employee> employees = employeeRepository.findArmyEmployees(pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getNavyEmployees(Pageable pageable) {
        log.debug("Getting navy employees");

        Page<Employee> employees = employeeRepository.findNavyEmployees(pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getAirForceEmployees(Pageable pageable) {
        log.debug("Getting air force employees");

        Page<Employee> employees = employeeRepository.findAirForceEmployees(pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getGendarmerieEmployees(Pageable pageable) {
        log.debug("Getting gendarmerie employees");

        Page<Employee> employees = employeeRepository.findGendarmerieEmployees(pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    /**
     * Get employees by service category
     */
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getVeteranEmployees(Pageable pageable) {
        log.debug("Getting veteran employees (25+ years)");

        Page<Employee> employees = employeeRepository.findVeteranEmployees(pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getSeniorEmployees(Pageable pageable) {
        log.debug("Getting senior employees (15-25 years)");

        Page<Employee> employees = employeeRepository.findSeniorEmployees(pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getExperiencedEmployees(Pageable pageable) {
        log.debug("Getting experienced employees (5-15 years)");

        Page<Employee> employees = employeeRepository.findExperiencedEmployees(pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getJuniorEmployees(Pageable pageable) {
        log.debug("Getting junior employees (1-5 years)");

        Page<Employee> employees = employeeRepository.findJuniorEmployees(pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getProbationaryEmployees(Pageable pageable) {
        log.debug("Getting probationary employees (<1 year)");

        Page<Employee> employees = employeeRepository.findProbationaryEmployees(pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    /**
     * Get employees with complete/incomplete profiles
     */
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getEmployeesWithCompleteProfile(Pageable pageable) {
        log.debug("Getting employees with complete profiles");

        Page<Employee> employees = employeeRepository.findWithCompleteProfile(pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getEmployeesWithIncompleteProfile(Pageable pageable) {
        log.debug("Getting employees with incomplete profiles");

        Page<Employee> employees = employeeRepository.findWithIncompleteProfile(pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    /**
     * Get retirement-related employees
     */
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getRetirementEligibleEmployees(Pageable pageable) {
        log.debug("Getting retirement eligible employees (30+ years)");

        Page<Employee> employees = employeeRepository.findRetirementEligible(pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getApproachingRetirementEmployees(Pageable pageable) {
        log.debug("Getting employees approaching retirement (25-30 years)");

        Page<Employee> employees = employeeRepository.findApproachingRetirement(pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    /**
     * Get command-eligible employees
     */
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> getCommandEligibleEmployees(Pageable pageable) {
        log.debug("Getting command-eligible employees");

        Page<Employee> employees = employeeRepository.findCommandEligible(pageable);
        return employees.map(EmployeeDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update employee
     */
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        log.info("Updating employee with ID: {}", id);

        Employee existingEmployee = getEmployeeEntityById(id);

        // Validate required fields and business rules
        validateRequiredFields(employeeDTO, "update");
        validateBusinessRules(employeeDTO, "update");

        // Check for unique constraints (excluding current record)
        validateUniqueConstraints(employeeDTO, id);

        // Update fields with exact field mapping
        mapDtoToEntity(employeeDTO, existingEmployee);

        // Handle foreign key relationships
        setEntityRelationships(employeeDTO, existingEmployee);

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        log.info("Successfully updated employee with ID: {}", id);

        return EmployeeDTO.fromEntityWithRelations(updatedEmployee);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete employee
     */
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with ID: {}", id);

        Employee employee = getEmployeeEntityById(id);
        employeeRepository.delete(employee);

        log.info("Successfully deleted employee with ID: {}", id);
    }

    /**
     * Delete employee by ID (direct)
     */
    public void deleteEmployeeById(Long id) {
        log.info("Deleting employee by ID: {}", id);

        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found with ID: " + id);
        }

        employeeRepository.deleteById(id);
        log.info("Successfully deleted employee with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if employee exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return employeeRepository.existsById(id);
    }

    /**
     * Check if person is already an employee
     */
    @Transactional(readOnly = true)
    public boolean existsByPersonId(Long personId) {
        return employeeRepository.existsByPersonId(personId);
    }

    /**
     * Check if serial exists
     */
    @Transactional(readOnly = true)
    public boolean existsBySerial(String serial) {
        return employeeRepository.existsBySerial(serial);
    }

    /**
     * Get statistics counts
     */
    @Transactional(readOnly = true)
    public Long countEmployeesByMilitaryRank(Long militaryRankId) {
        return employeeRepository.countByMilitaryRank(militaryRankId);
    }

    @Transactional(readOnly = true)
    public Long countEmployeesByJob(Long jobId) {
        return employeeRepository.countByJob(jobId);
    }

    @Transactional(readOnly = true)
    public Long countEmployeesWithoutJob() {
        return employeeRepository.countWithoutJob();
    }

    @Transactional(readOnly = true)
    public Long countEmployeesWithJob() {
        return employeeRepository.countWithJob();
    }

    @Transactional(readOnly = true)
    public Long countGeneralOfficers() {
        return employeeRepository.countGeneralOfficers();
    }

    @Transactional(readOnly = true)
    public Long countCommissionedOfficers() {
        return employeeRepository.countCommissionedOfficers();
    }

    @Transactional(readOnly = true)
    public Long countTotalEmployees() {
        return employeeRepository.countTotalEmployees();
    }

    // ========== PRIVATE HELPER METHODS ==========

    /**
     * Map DTO fields to entity (exact field mapping)
     */
    private void mapDtoToEntity(EmployeeDTO dto, Employee entity) {
        entity.setSerial(dto.getSerial()); // F_01
        entity.setHiringDate(dto.getHiringDate()); // F_02
    }

    /**
     * Set entity foreign key relationships
     */
    private void setEntityRelationships(EmployeeDTO dto, Employee entity) {
        // F_03 - Person (required)
        if (dto.getPersonId() != null) {
            entity.setPerson(personRepository.findById(dto.getPersonId())
                    .orElseThrow(() -> new RuntimeException("Person not found with ID: " + dto.getPersonId())));
        }

        // F_04 - MilitaryRank (required)
        if (dto.getMilitaryRankId() != null) {
            entity.setMilitaryRank(militaryRankRepository.findById(dto.getMilitaryRankId())
                    .orElseThrow(() -> new RuntimeException("Military rank not found with ID: " + dto.getMilitaryRankId())));
        }

        // F_05 - Job (optional)
        if (dto.getJobId() != null) {
            entity.setJob(jobRepository.findById(dto.getJobId())
                    .orElseThrow(() -> new RuntimeException("Job not found with ID: " + dto.getJobId())));
        } else {
            entity.setJob(null);
        }
    }

    /**
     * Validate required fields
     */
    private void validateRequiredFields(EmployeeDTO dto, String operation) {
        if (dto.getPersonId() == null) {
            throw new RuntimeException("Person is required for " + operation);
        }
        if (dto.getMilitaryRankId() == null) {
            throw new RuntimeException("Military rank is required for " + operation);
        }
    }

    /**
     * Validate business rules
     */
    private void validateBusinessRules(EmployeeDTO dto, String operation) {
        // Hiring date cannot be in the future (more than 1 day to account for timezone)
        if (dto.getHiringDate() != null) {
            Date tomorrow = new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000));
            if (dto.getHiringDate().after(tomorrow)) {
                throw new RuntimeException("Hiring date cannot be in the future for " + operation);
            }
        }

        // Serial number format validation (if provided)
        if (dto.getSerial() != null && !dto.getSerial().trim().isEmpty()) {
            String serial = dto.getSerial().trim();
            if (serial.length() < 3) {
                throw new RuntimeException("Serial number must be at least 3 characters long for " + operation);
            }
            // Additional serial format validation can be added here
        }
    }

    /**
     * Validate unique constraints and business validations
     */
    private void validateUniqueConstraints(EmployeeDTO dto, Long excludeId) {
        // Check if person is already an employee (one person = one employee)
        if (dto.getPersonId() != null) {
            if (excludeId == null) {
                if (employeeRepository.existsByPersonId(dto.getPersonId())) {
                    throw new RuntimeException("Person with ID " + dto.getPersonId() + " is already an employee");
                }
            } else {
                // For updates, check if the person belongs to another employee
                List<Employee> existingEmployees = employeeRepository.findByPerson(dto.getPersonId());
                boolean hasOtherEmployee = existingEmployees.stream()
                        .anyMatch(emp -> !emp.getId().equals(excludeId));
                if (hasOtherEmployee) {
                    throw new RuntimeException("Person with ID " + dto.getPersonId() + " is already associated with another employee");
                }
            }
        }

        // Check serial number uniqueness (if provided)
        if (dto.getSerial() != null && !dto.getSerial().trim().isEmpty()) {
            if (excludeId == null) {
                if (employeeRepository.existsBySerial(dto.getSerial())) {
                    throw new RuntimeException("Employee with serial '" + dto.getSerial() + "' already exists");
                }
            } else {
                if (employeeRepository.existsBySerialAndIdNot(dto.getSerial(), excludeId)) {
                    throw new RuntimeException("Another employee with serial '" + dto.getSerial() + "' already exists");
                }
            }
        }
    }
}