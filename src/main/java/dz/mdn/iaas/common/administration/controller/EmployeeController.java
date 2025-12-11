/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EmployeeController
 *	@CreatedOn	: 10-14-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Controller
 *	@Layer		: Common / Administration
 *	@Package	: Common / Administration / Controller
 *
 **/

package dz.mdn.iaas.common.administration.controller;

import dz.mdn.iaas.common.administration.dto.EmployeeDTO;
import dz.mdn.iaas.common.administration.service.EmployeeService;
import dz.mdn.iaas.configuration.template.GenericController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Employee REST Controller - Extends GenericController
 * Provides standard CRUD endpoints plus employee-specific operations
 */
@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController extends GenericController<EmployeeDTO, Long> {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super(employeeService, "Employee");
        this.employeeService = employeeService;
    }

    @Override
    protected Page<EmployeeDTO> searchByQuery(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return employeeService.getAll(pageable);
        }
        return employeeService.globalSearch(query, pageable);
    }

    /**
     * Get all employees as list (non-paginated)
     * GET /employee/list
     */
    @GetMapping("/list")
    public ResponseEntity<List<EmployeeDTO>> getAllList() {
        log.debug("GET /employee/list - Getting all employees as list");
        List<EmployeeDTO> employees = employeeService.getAll();
        return success(employees);
    }

    /**
     * Get employees by structure ID
     * GET /employee/structure/{structureId}
     */
    @GetMapping("/structure/{structureId}")
    public ResponseEntity<List<EmployeeDTO>> getByStructureId(@PathVariable Long structureId) {
        log.debug("GET /employee/structure/{} - Getting employees by structure ID", structureId);
        List<EmployeeDTO> employees = employeeService.getByStructureId(structureId);
        return success(employees);
    }

    /**
     * Get active employees
     * GET /employee/active
     */
    @GetMapping("/active")
    public ResponseEntity<List<EmployeeDTO>> getActiveEmployees() {
        log.debug("GET /employee/active - Getting active employees");
        List<EmployeeDTO> employees = employeeService.getActiveEmployees();
        return success(employees);
    }
}
