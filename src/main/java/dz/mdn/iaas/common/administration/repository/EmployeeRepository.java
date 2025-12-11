/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EmployeeRepository
 *	@CreatedOn	: 06-26-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Repository
 *	@Layer		: Common / Administration
 *	@Package	: Common / Administration / Repository
 *
 **/

package dz.mdn.iaas.common.administration.repository;

import dz.mdn.iaas.common.administration.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Employee Repository
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    /**
     * Find employees by structure ID
     * Used by EmployeeService.getByStructureId()
     */
    @Query("SELECT e FROM Employee e WHERE e.structure.id = :structureId")
    List<Employee> findByStructureId(@Param("structureId") Long structureId);
    
    /**
     * Find active employees
     * Used by EmployeeService.getActiveEmployees()
     */
    @Query("SELECT e FROM Employee e WHERE e.isActive = true")
    List<Employee> findByIsActiveTrue();
}
