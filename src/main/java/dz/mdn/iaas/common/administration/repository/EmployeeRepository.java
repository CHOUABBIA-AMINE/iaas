/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: EmployeeRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.repository;

import dz.mdn.iaas.common.administration.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Employee Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=serial, F_02=hiringDate, 
 * F_03=person, F_04=militaryRank, F_05=job
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Find employee by serial number
     */
    @Query("SELECT e FROM Employee e WHERE e.serial = :serial")
    Optional<Employee> findBySerial(@Param("serial") String serial);

    /**
     * Find all employees ordered by hiring date (most recent first)
     */
    @Query("SELECT e FROM Employee e ORDER BY e.hiringDate DESC NULLS LAST")
    Page<Employee> findAllOrderByHiringDate(Pageable pageable);

    /**
     * Find employees by person
     */
    @Query("SELECT e FROM Employee e WHERE e.person.id = :personId")
    List<Employee> findByPerson(@Param("personId") Long personId);

    /**
     * Find employees by military rank
     */
    @Query("SELECT e FROM Employee e WHERE e.militaryRank.id = :militaryRankId ORDER BY e.hiringDate DESC")
    Page<Employee> findByMilitaryRank(@Param("militaryRankId") Long militaryRankId, Pageable pageable);

    /**
     * Find employees by job
     */
    @Query("SELECT e FROM Employee e WHERE e.job.id = :jobId ORDER BY e.hiringDate DESC")
    Page<Employee> findByJob(@Param("jobId") Long jobId, Pageable pageable);

    /**
     * Find employees without job assignment
     */
    @Query("SELECT e FROM Employee e WHERE e.job IS NULL ORDER BY e.hiringDate DESC")
    Page<Employee> findWithoutJob(Pageable pageable);

    /**
     * Find employees with job assignment
     */
    @Query("SELECT e FROM Employee e WHERE e.job IS NOT NULL ORDER BY e.hiringDate DESC")
    Page<Employee> findWithJob(Pageable pageable);

    /**
     * Search employees by person name (first name or last name)
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "LOWER(e.person.firstnameAr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(e.person.firstnameLt) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(e.person.lastnameAr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(e.person.lastnameLt) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Employee> searchByPersonName(@Param("search") String search, Pageable pageable);

    /**
     * Search employees by serial number
     */
    @Query("SELECT e FROM Employee e WHERE e.serial LIKE %:search%")
    Page<Employee> searchBySerial(@Param("search") String search, Pageable pageable);

    /**
     * Search employees by any field
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "e.serial LIKE %:search% OR " +
           "LOWER(e.person.firstnameAr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(e.person.firstnameLt) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(e.person.lastnameAr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(e.person.lastnameLt) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(e.militaryRank.designationFr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(e.militaryRank.abbreviationFr) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Employee> searchByAnyField(@Param("search") String search, Pageable pageable);

    /**
     * Find employees hired within date range
     */
    @Query("SELECT e FROM Employee e WHERE e.hiringDate BETWEEN :startDate AND :endDate ORDER BY e.hiringDate DESC")
    Page<Employee> findByHiringDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

    /**
     * Find employees hired after specific date
     */
    @Query("SELECT e FROM Employee e WHERE e.hiringDate >= :date ORDER BY e.hiringDate DESC")
    Page<Employee> findHiredAfter(@Param("date") Date date, Pageable pageable);

    /**
     * Find employees hired before specific date
     */
    @Query("SELECT e FROM Employee e WHERE e.hiringDate <= :date ORDER BY e.hiringDate DESC")
    Page<Employee> findHiredBefore(@Param("date") Date date, Pageable pageable);

    /**
     * Find general officer employees
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "LOWER(e.militaryRank.designationFr) LIKE '%général%' OR " +
           "LOWER(e.militaryRank.designationFr) LIKE '%amiral%'")
    Page<Employee> findGeneralOfficers(Pageable pageable);

    /**
     * Find senior officer employees
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "LOWER(e.militaryRank.designationFr) LIKE '%colonel%' OR " +
           "LOWER(e.militaryRank.designationFr) LIKE '%capitaine de vaisseau%'")
    Page<Employee> findSeniorOfficers(Pageable pageable);

    /**
     * Find company grade officer employees
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "LOWER(e.militaryRank.designationFr) LIKE '%commandant%' OR " +
           "LOWER(e.militaryRank.designationFr) LIKE '%capitaine%' OR " +
           "LOWER(e.militaryRank.designationFr) LIKE '%lieutenant%'")
    Page<Employee> findCompanyGradeOfficers(Pageable pageable);

    /**
     * Find non-commissioned officer employees
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "LOWER(e.militaryRank.designationFr) LIKE '%sous-officier%' OR " +
           "LOWER(e.militaryRank.designationFr) LIKE '%sergent%' OR " +
           "LOWER(e.militaryRank.designationFr) LIKE '%adjudant%'")
    Page<Employee> findNonCommissionedOfficers(Pageable pageable);

    /**
     * Find enlisted employees
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "LOWER(e.militaryRank.designationFr) LIKE '%soldat%' OR " +
           "LOWER(e.militaryRank.designationFr) LIKE '%matelot%'")
    Page<Employee> findEnlisted(Pageable pageable);

    /**
     * Find commissioned officer employees
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "LOWER(e.militaryRank.designationFr) LIKE '%général%' OR " +
           "LOWER(e.militaryRank.designationFr) LIKE '%amiral%' OR " +
           "LOWER(e.militaryRank.designationFr) LIKE '%colonel%' OR " +
           "LOWER(e.militaryRank.designationFr) LIKE '%commandant%' OR " +
           "LOWER(e.militaryRank.designationFr) LIKE '%capitaine%' OR " +
           "LOWER(e.militaryRank.designationFr) LIKE '%lieutenant%'")
    Page<Employee> findCommissionedOfficers(Pageable pageable);

    /**
     * Find army employees (based on military category)
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "LOWER(e.militaryRank.militaryCategory.designationFr) LIKE '%terre%' OR " +
           "LOWER(e.militaryRank.militaryCategory.designationEn) LIKE '%army%'")
    Page<Employee> findArmyEmployees(Pageable pageable);

    /**
     * Find navy employees (based on military category)
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "LOWER(e.militaryRank.militaryCategory.designationFr) LIKE '%marine%' OR " +
           "LOWER(e.militaryRank.militaryCategory.designationEn) LIKE '%navy%'")
    Page<Employee> findNavyEmployees(Pageable pageable);

    /**
     * Find air force employees (based on military category)
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "LOWER(e.militaryRank.militaryCategory.designationFr) LIKE '%air%'")
    Page<Employee> findAirForceEmployees(Pageable pageable);

    /**
     * Find gendarmerie employees (based on military category)
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "LOWER(e.militaryRank.militaryCategory.designationFr) LIKE '%gendarmerie%'")
    Page<Employee> findGendarmerieEmployees(Pageable pageable);

    /**
     * Find employees by service years (calculated from hiring date)
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "e.hiringDate IS NOT NULL AND " +
           "DATEDIFF(CURRENT_DATE, e.hiringDate) >= :minDays AND " +
           "DATEDIFF(CURRENT_DATE, e.hiringDate) <= :maxDays")
    Page<Employee> findByServiceDays(@Param("minDays") Integer minDays, @Param("maxDays") Integer maxDays, Pageable pageable);

    /**
     * Find veteran employees (25+ years of service)
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "e.hiringDate IS NOT NULL AND " +
           "DATEDIFF(CURRENT_DATE, e.hiringDate) >= 9125") // 25 * 365 days
    Page<Employee> findVeteranEmployees(Pageable pageable);

    /**
     * Find senior employees (15+ years of service)
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "e.hiringDate IS NOT NULL AND " +
           "DATEDIFF(CURRENT_DATE, e.hiringDate) >= 5475 AND " +
           "DATEDIFF(CURRENT_DATE, e.hiringDate) < 9125") // 15-25 years
    Page<Employee> findSeniorEmployees(Pageable pageable);

    /**
     * Find experienced employees (5-15 years of service)
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "e.hiringDate IS NOT NULL AND " +
           "DATEDIFF(CURRENT_DATE, e.hiringDate) >= 1825 AND " +
           "DATEDIFF(CURRENT_DATE, e.hiringDate) < 5475") // 5-15 years
    Page<Employee> findExperiencedEmployees(Pageable pageable);

    /**
     * Find junior employees (1-5 years of service)
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "e.hiringDate IS NOT NULL AND " +
           "DATEDIFF(CURRENT_DATE, e.hiringDate) >= 365 AND " +
           "DATEDIFF(CURRENT_DATE, e.hiringDate) < 1825") // 1-5 years
    Page<Employee> findJuniorEmployees(Pageable pageable);

    /**
     * Find probationary employees (less than 1 year of service)
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "e.hiringDate IS NOT NULL AND " +
           "DATEDIFF(CURRENT_DATE, e.hiringDate) < 365") // Less than 1 year
    Page<Employee> findProbationaryEmployees(Pageable pageable);

    /**
     * Find employees with complete profile
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "e.person IS NOT NULL AND " +
           "e.militaryRank IS NOT NULL AND " +
           "e.hiringDate IS NOT NULL AND " +
           "e.serial IS NOT NULL AND e.serial != ''")
    Page<Employee> findWithCompleteProfile(Pageable pageable);

    /**
     * Find employees with incomplete profile
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "e.person IS NULL OR " +
           "e.militaryRank IS NULL OR " +
           "e.hiringDate IS NULL OR " +
           "e.serial IS NULL OR e.serial = ''")
    Page<Employee> findWithIncompleteProfile(Pageable pageable);

    /**
     * Count employees by military rank
     */
    @Query("SELECT COUNT(e) FROM Employee e WHERE e.militaryRank.id = :militaryRankId")
    Long countByMilitaryRank(@Param("militaryRankId") Long militaryRankId);

    /**
     * Count employees by job
     */
    @Query("SELECT COUNT(e) FROM Employee e WHERE e.job.id = :jobId")
    Long countByJob(@Param("jobId") Long jobId);

    /**
     * Count employees without job
     */
    @Query("SELECT COUNT(e) FROM Employee e WHERE e.job IS NULL")
    Long countWithoutJob();

    /**
     * Count employees with job
     */
    @Query("SELECT COUNT(e) FROM Employee e WHERE e.job IS NOT NULL")
    Long countWithJob();

    /**
     * Count general officer employees
     */
    @Query("SELECT COUNT(e) FROM Employee e WHERE " +
           "LOWER(e.militaryRank.designationFr) LIKE '%général%' OR " +
           "LOWER(e.militaryRank.designationFr) LIKE '%amiral%'")
    Long countGeneralOfficers();

    /**
     * Count commissioned officer employees
     */
    @Query("SELECT COUNT(e) FROM Employee e WHERE " +
           "LOWER(e.militaryRank.designationFr) LIKE '%général%' OR " +
           "LOWER(e.militaryRank.designationFr) LIKE '%amiral%' OR " +
           "LOWER(e.militaryRank.designationFr) LIKE '%colonel%' OR " +
           "LOWER(e.militaryRank.designationFr) LIKE '%commandant%' OR " +
           "LOWER(e.militaryRank.designationFr) LIKE '%capitaine%' OR " +
           "LOWER(e.militaryRank.designationFr) LIKE '%lieutenant%'")
    Long countCommissionedOfficers();

    /**
     * Check if person is already an employee
     */
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Employee e WHERE e.person.id = :personId")
    boolean existsByPersonId(@Param("personId") Long personId);

    /**
     * Check if serial exists
     */
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Employee e WHERE e.serial = :serial")
    boolean existsBySerial(@Param("serial") String serial);

    /**
     * Check if serial exists excluding current employee
     */
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Employee e WHERE e.serial = :serial AND e.id != :id")
    boolean existsBySerialAndIdNot(@Param("serial") String serial, @Param("id") Long id);

    /**
     * Find employees by multiple military ranks
     */
    @Query("SELECT e FROM Employee e WHERE e.militaryRank.id IN :militaryRankIds ORDER BY e.hiringDate DESC")
    Page<Employee> findByMilitaryRanks(@Param("militaryRankIds") List<Long> militaryRankIds, Pageable pageable);

    /**
     * Find employees by multiple jobs
     */
    @Query("SELECT e FROM Employee e WHERE e.job.id IN :jobIds ORDER BY e.hiringDate DESC")
    Page<Employee> findByJobs(@Param("jobIds") List<Long> jobIds, Pageable pageable);

    /**
     * Find recently hired employees (within last N days)
     */
    @Query("SELECT e FROM Employee e WHERE e.hiringDate >= :sinceDate ORDER BY e.hiringDate DESC")
    Page<Employee> findRecentlyHired(@Param("sinceDate") Date sinceDate, Pageable pageable);

    /**
     * Get employee statistics by military rank
     */
    @Query("SELECT e.militaryRank.designationFr, COUNT(e) FROM Employee e GROUP BY e.militaryRank.designationFr ORDER BY COUNT(e) DESC")
    List<Object[]> getEmployeeStatisticsByRank();

    /**
     * Get employee statistics by service branch
     */
    @Query("SELECT e.militaryRank.militaryCategory.designationFr, COUNT(e) FROM Employee e GROUP BY e.militaryRank.militaryCategory.designationFr ORDER BY COUNT(e) DESC")
    List<Object[]> getEmployeeStatisticsByServiceBranch();

    /**
     * Get employee statistics by hiring year
     */
    @Query("SELECT YEAR(e.hiringDate), COUNT(e) FROM Employee e WHERE e.hiringDate IS NOT NULL GROUP BY YEAR(e.hiringDate) ORDER BY YEAR(e.hiringDate) DESC")
    List<Object[]> getEmployeeStatisticsByHiringYear();

    /**
     * Find employees eligible for retirement (30+ years of service)
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "e.hiringDate IS NOT NULL AND " +
           "DATEDIFF(CURRENT_DATE, e.hiringDate) >= 10950") // 30 * 365 days
    Page<Employee> findRetirementEligible(Pageable pageable);

    /**
     * Find employees approaching retirement (25-30 years of service)
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "e.hiringDate IS NOT NULL AND " +
           "DATEDIFF(CURRENT_DATE, e.hiringDate) >= 9125 AND " +
           "DATEDIFF(CURRENT_DATE, e.hiringDate) < 10950") // 25-30 years
    Page<Employee> findApproachingRetirement(Pageable pageable);

    /**
     * Find command-eligible employees (officers who can command units)
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "LOWER(e.militaryRank.designationFr) LIKE '%général%' OR " +
           "LOWER(e.militaryRank.designationFr) LIKE '%amiral%' OR " +
           "LOWER(e.militaryRank.designationFr) LIKE '%colonel%' OR " +
           "LOWER(e.militaryRank.designationFr) LIKE '%commandant%' OR " +
           "LOWER(e.militaryRank.designationFr) LIKE '%capitaine%'")
    Page<Employee> findCommandEligible(Pageable pageable);

    /**
     * Find employees by age range (through person birth date)
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "e.person.birthDate IS NOT NULL AND " +
           "DATEDIFF(CURRENT_DATE, e.person.birthDate) >= :minAgeDays AND " +
           "DATEDIFF(CURRENT_DATE, e.person.birthDate) <= :maxAgeDays")
    Page<Employee> findByAgeRange(@Param("minAgeDays") Integer minAgeDays, @Param("maxAgeDays") Integer maxAgeDays, Pageable pageable);

    /**
     * Find most senior employees (by hiring date)
     */
    @Query("SELECT e FROM Employee e WHERE e.hiringDate IS NOT NULL ORDER BY e.hiringDate ASC")
    Page<Employee> findMostSeniorEmployees(Pageable pageable);

    /**
     * Find newest employees (by hiring date)
     */
    @Query("SELECT e FROM Employee e WHERE e.hiringDate IS NOT NULL ORDER BY e.hiringDate DESC")
    Page<Employee> findNewestEmployees(Pageable pageable);

    /**
     * Count total employees
     */
    @Query("SELECT COUNT(e) FROM Employee e")
    Long countTotalEmployees();

    /**
     * Find duplicate employees (same person with multiple employee records)
     */
    @Query("SELECT e1 FROM Employee e1, Employee e2 WHERE e1.id != e2.id AND e1.person.id = e2.person.id")
    List<Employee> findDuplicateEmployees();
}