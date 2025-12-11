/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EmployeeService
 *	@CreatedOn	: 10-14-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Service
 *	@Layer		: Common / Administration
 *	@Package	: Common / Administration / Service
 *
 **/

package dz.mdn.iaas.common.administration.service;

import dz.mdn.iaas.common.administration.dto.EmployeeDTO;
import dz.mdn.iaas.common.administration.model.Employee;
import dz.mdn.iaas.common.administration.model.Job;
import dz.mdn.iaas.common.administration.model.MilitaryRank;
import dz.mdn.iaas.common.administration.model.Person;
import dz.mdn.iaas.common.administration.model.Structure;
import dz.mdn.iaas.common.administration.repository.EmployeeRepository;
import dz.mdn.iaas.configuration.template.GenericService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Employee Service - Extends GenericService
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class EmployeeService extends GenericService<Employee, EmployeeDTO, Long> {

    private final EmployeeRepository employeeRepository;
    private final PersonService personService;
    private final JobService jobService;
    private final StructureService structureService;
    private final MilitaryRankService militaryRankService;

    @Override
    protected JpaRepository<Employee, Long> getRepository() {
        return employeeRepository;
    }

    @Override
    protected String getEntityName() {
        return "Employee";
    }

    @Override
    protected EmployeeDTO toDTO(Employee entity) {
        return EmployeeDTO.fromEntity(entity);
    }

    @Override
    protected Employee toEntity(EmployeeDTO dto) {
        Employee entity = dto.toEntity();
        
        // Set relationships
        if (dto.getPersonId() != null) {
            Person person = personService.getEntityById(dto.getPersonId());
            entity.setPerson(person);
        }
        
        if (dto.getJobId() != null) {
            Job job = jobService.getEntityById(dto.getJobId());
            entity.setJob(job);
        }
        
        if (dto.getStructureId() != null) {
            Structure structure = structureService.getEntityById(dto.getStructureId());
            entity.setStructure(structure);
        }
        
        if (dto.getRankId() != null) {
            MilitaryRank rank = militaryRankService.getEntityById(dto.getRankId());
            entity.setRank(rank);
        }
        
        return entity;
    }

    @Override
    protected void updateEntityFromDTO(Employee entity, EmployeeDTO dto) {
        dto.updateEntity(entity);
        
        // Update relationships
        if (dto.getPersonId() != null) {
            Person person = personService.getEntityById(dto.getPersonId());
            entity.setPerson(person);
        }
        
        if (dto.getJobId() != null) {
            Job job = jobService.getEntityById(dto.getJobId());
            entity.setJob(job);
        }
        
        if (dto.getStructureId() != null) {
            Structure structure = structureService.getEntityById(dto.getStructureId());
            entity.setStructure(structure);
        }
        
        if (dto.getRankId() != null) {
            MilitaryRank rank = militaryRankService.getEntityById(dto.getRankId());
            entity.setRank(rank);
        }
    }

    @Override
    @Transactional
    public EmployeeDTO create(EmployeeDTO dto) {
        log.info("Creating employee: registrationNumber={}, personId={}", 
                dto.getRegistrationNumber(), dto.getPersonId());
        return super.create(dto);
    }

    @Override
    @Transactional
    public EmployeeDTO update(Long id, EmployeeDTO dto) {
        log.info("Updating employee with ID: {}", id);
        return super.update(id, dto);
    }

    public List<EmployeeDTO> getAll() {
        log.debug("Getting all employees without pagination");
        return employeeRepository.findAll().stream()
                .map(EmployeeDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Page<EmployeeDTO> globalSearch(String searchTerm, Pageable pageable) {
        log.debug("Global search for employees with term: {}", searchTerm);
        
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAll(pageable);
        }
        
        return getAll(pageable);
    }

    public List<EmployeeDTO> getByStructureId(Long structureId) {
        log.debug("Getting employees by structure ID: {}", structureId);
        return employeeRepository.findByStructureId(structureId).stream()
                .map(EmployeeDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> getActiveEmployees() {
        log.debug("Getting active employees");
        return employeeRepository.findByIsActiveTrue().stream()
                .map(EmployeeDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
