/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PersonRepository
 *	@CreatedOn	: 06-26-2025
 *
 *	@Type		: Class
 *	@Layaer		: Model
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dz.mdn.iaas.common.administration.model.Person;

/**
 * Person Repository with essential CRUD operations
 * Based on exact field names: F_00=id, F_01=firstnameAr, F_02=lastnameAr, F_03=firstnameLt, F_04=lastnameLt, F_05=birthDate, F_06=birthPlace, F_07=address, F_08=birthState, F_09=addressState, F_10=picture
 * All fields are optional - no unique constraints
 * F_08 (birthState), F_09 (addressState), F_10 (picture) are optional foreign keys
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    /**
     * Find persons by Arabic firstname (F_01)
     */
    @Query("SELECT p FROM Person p WHERE p.firstnameAr = :firstnameAr")
    Page<Person> findByFirstnameAr(@Param("firstnameAr") String firstnameAr, Pageable pageable);

    /**
     * Find persons by Arabic lastname (F_02)
     */
    @Query("SELECT p FROM Person p WHERE p.lastnameAr = :lastnameAr")
    Page<Person> findByLastnameAr(@Param("lastnameAr") String lastnameAr, Pageable pageable);

    /**
     * Find persons by Latin firstname (F_03)
     */
    @Query("SELECT p FROM Person p WHERE p.firstnameLt = :firstnameLt")
    Page<Person> findByFirstnameLt(@Param("firstnameLt") String firstnameLt, Pageable pageable);

    /**
     * Find persons by Latin lastname (F_04)
     */
    @Query("SELECT p FROM Person p WHERE p.lastnameLt = :lastnameLt")
    Page<Person> findByLastnameLt(@Param("lastnameLt") String lastnameLt, Pageable pageable);

    /**
     * Find persons by birth date (F_05)
     */
    @Query("SELECT p FROM Person p WHERE p.birthDate = :birthDate")
    Page<Person> findByBirthDate(@Param("birthDate") Date birthDate, Pageable pageable);

    /**
     * Find persons by birth place (F_06)
     */
    @Query("SELECT p FROM Person p WHERE p.birthPlace = :birthPlace")
    Page<Person> findByBirthPlace(@Param("birthPlace") String birthPlace, Pageable pageable);

    /**
     * Find persons by birth state ID (F_08)
     */
    @Query("SELECT p FROM Person p WHERE p.birthState.id = :birthStateId ORDER BY p.firstnameLt ASC, p.lastnameAr ASC")
    Page<Person> findByBirthStateId(@Param("birthStateId") Long birthStateId, Pageable pageable);

    /**
     * Find persons by address state ID (F_09)
     */
    @Query("SELECT p FROM Person p WHERE p.addressState.id = :addressStateId ORDER BY p.firstnameLt ASC, p.lastnameAr ASC")
    Page<Person> findByAddressStateId(@Param("addressStateId") Long addressStateId, Pageable pageable);

    /**
     * Find all persons with pagination ordered by Latin names first, then Arabic names
     */
    @Query("SELECT p FROM Person p ORDER BY p.firstnameLt ASC, p.lastnameLt ASC, p.firstnameAr ASC, p.lastnameAr ASC")
    Page<Person> findAllOrderByName(Pageable pageable);

    /**
     * Search persons by any name field
     */
    @Query("SELECT p FROM Person p WHERE " +
           "p.firstnameAr LIKE %:search% OR " +
           "p.lastnameAr LIKE %:search% OR " +
           "p.firstnameLt LIKE %:search% OR " +
           "p.lastnameLt LIKE %:search%")
    Page<Person> searchByName(@Param("search") String search, Pageable pageable);

    /**
     * Find persons by full Arabic name
     */
    @Query("SELECT p FROM Person p WHERE p.firstnameAr = :firstnameAr AND p.lastnameAr = :lastnameAr")
    Page<Person> findByFullNameAr(@Param("firstnameAr") String firstnameAr, @Param("lastnameAr") String lastnameAr, Pageable pageable);

    /**
     * Find persons by full Latin name
     */
    @Query("SELECT p FROM Person p WHERE p.firstnameLt = :firstnameLt AND p.lastnameLt = :lastnameLt")
    Page<Person> findByFullNameLt(@Param("firstnameLt") String firstnameLt, @Param("lastnameLt") String lastnameLt, Pageable pageable);

    /**
     * Count total persons
     */
    @Query("SELECT COUNT(p) FROM Person p")
    Long countAllPersons();

    /**
     * Count persons by birth state
     */
    @Query("SELECT COUNT(p) FROM Person p WHERE p.birthState.id = :birthStateId")
    Long countByBirthStateId(@Param("birthStateId") Long birthStateId);

    /**
     * Count persons by address state
     */
    @Query("SELECT COUNT(p) FROM Person p WHERE p.addressState.id = :addressStateId")
    Long countByAddressStateId(@Param("addressStateId") Long addressStateId);

    /**
     * Find persons that have Arabic names
     */
    @Query("SELECT p FROM Person p WHERE p.firstnameAr IS NOT NULL AND p.firstnameAr != '' AND p.lastnameAr IS NOT NULL AND p.lastnameAr != ''")
    Page<Person> findWithArabicNames(Pageable pageable);

    /**
     * Find persons that have Latin names
     */
    @Query("SELECT p FROM Person p WHERE p.firstnameLt IS NOT NULL AND p.firstnameLt != '' AND p.lastnameLt IS NOT NULL AND p.lastnameLt != ''")
    Page<Person> findWithLatinNames(Pageable pageable);

    /**
     * Find multilingual persons (have both Arabic and Latin names)
     */
    @Query("SELECT p FROM Person p WHERE " +
           "(p.firstnameAr IS NOT NULL AND p.firstnameAr != '' AND p.lastnameAr IS NOT NULL AND p.lastnameAr != '') AND " +
           "(p.firstnameLt IS NOT NULL AND p.firstnameLt != '' AND p.lastnameLt IS NOT NULL AND p.lastnameLt != '')")
    Page<Person> findMultilingualPersons(Pageable pageable);

    /**
     * Find persons born in a specific year
     */
    @Query("SELECT p FROM Person p WHERE YEAR(p.birthDate) = :year")
    Page<Person> findByBirthYear(@Param("year") Integer year, Pageable pageable);

    /**
     * Find persons born between two dates
     */
    @Query("SELECT p FROM Person p WHERE p.birthDate BETWEEN :startDate AND :endDate")
    Page<Person> findByBirthDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

    /**
     * Find persons by age group (calculated)
     */
    @Query("SELECT p FROM Person p WHERE " +
           "YEAR(CURRENT_DATE) - YEAR(p.birthDate) - CASE WHEN MONTH(CURRENT_DATE) < MONTH(p.birthDate) OR (MONTH(CURRENT_DATE) = MONTH(p.birthDate) AND DAY(CURRENT_DATE) < DAY(p.birthDate)) THEN 1 ELSE 0 END " +
           "BETWEEN :minAge AND :maxAge")
    Page<Person> findByAgeRange(@Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge, Pageable pageable);

    /**
     * Find persons with pictures
     */
    @Query("SELECT p FROM Person p WHERE p.picture IS NOT NULL")
    Page<Person> findWithPictures(Pageable pageable);

    /**
     * Find persons without pictures
     */
    @Query("SELECT p FROM Person p WHERE p.picture IS NULL")
    Page<Person> findWithoutPictures(Pageable pageable);

    /**
     * Find persons with complete birth information
     */
    @Query("SELECT p FROM Person p WHERE p.birthDate IS NOT NULL AND p.birthPlace IS NOT NULL AND p.birthPlace != '' AND p.birthState IS NOT NULL")
    Page<Person> findWithCompleteBirthInfo(Pageable pageable);

    /**
     * Find persons with complete address information
     */
    @Query("SELECT p FROM Person p WHERE p.address IS NOT NULL AND p.address != '' AND p.addressState IS NOT NULL")
    Page<Person> findWithCompleteAddress(Pageable pageable);

    /**
     * Find persons with same birth and address state
     */
    @Query("SELECT p FROM Person p WHERE p.birthState = p.addressState AND p.birthState IS NOT NULL")
    Page<Person> findWithSameState(Pageable pageable);

    /**
     * Find persons by address pattern
     */
    @Query("SELECT p FROM Person p WHERE p.address LIKE %:address%")
    Page<Person> findByAddressContaining(@Param("address") String address, Pageable pageable);

    /**
     * Find persons by birth place pattern
     */
    @Query("SELECT p FROM Person p WHERE p.birthPlace LIKE %:birthPlace%")
    Page<Person> findByBirthPlaceContaining(@Param("birthPlace") String birthPlace, Pageable pageable);

    /**
     * Search persons with state context
     */
    @Query("SELECT p FROM Person p LEFT JOIN p.birthState bs LEFT JOIN p.addressState as_state WHERE " +
           "(p.firstnameAr LIKE %:search% OR p.lastnameAr LIKE %:search% OR " +
           "p.firstnameLt LIKE %:search% OR p.lastnameLt LIKE %:search% OR " +
           "p.birthPlace LIKE %:search% OR p.address LIKE %:search% OR " +
           "bs.designationLt LIKE %:search% OR as_state.designationLt LIKE %:search%) " +
           "ORDER BY p.firstnameLt ASC, p.lastnameLt ASC")
    Page<Person> searchWithStateContext(@Param("search") String search, Pageable pageable);

    /**
     * Find persons born today (birthday)
     */
    @Query("SELECT p FROM Person p WHERE MONTH(p.birthDate) = MONTH(CURRENT_DATE) AND DAY(p.birthDate) = DAY(CURRENT_DATE)")
    Page<Person> findBirthdayToday(Pageable pageable);

    /**
     * Find persons with birthday this month
     */
    @Query("SELECT p FROM Person p WHERE MONTH(p.birthDate) = MONTH(CURRENT_DATE)")
    Page<Person> findBirthdayThisMonth(Pageable pageable);

    /**
     * Find minors (under 18)
     */
    @Query("SELECT p FROM Person p WHERE " +
           "YEAR(CURRENT_DATE) - YEAR(p.birthDate) - CASE WHEN MONTH(CURRENT_DATE) < MONTH(p.birthDate) OR (MONTH(CURRENT_DATE) = MONTH(p.birthDate) AND DAY(CURRENT_DATE) < DAY(p.birthDate)) THEN 1 ELSE 0 END < 18")
    Page<Person> findMinors(Pageable pageable);

    /**
     * Find adults (18+)
     */
    @Query("SELECT p FROM Person p WHERE " +
           "YEAR(CURRENT_DATE) - YEAR(p.birthDate) - CASE WHEN MONTH(CURRENT_DATE) < MONTH(p.birthDate) OR (MONTH(CURRENT_DATE) = MONTH(p.birthDate) AND DAY(CURRENT_DATE) < DAY(p.birthDate)) THEN 1 ELSE 0 END >= 18")
    Page<Person> findAdults(Pageable pageable);

    /**
     * Find persons ordered by age (youngest first)
     */
    @Query("SELECT p FROM Person p WHERE p.birthDate IS NOT NULL ORDER BY p.birthDate DESC")
    Page<Person> findOrderByAgeYoungestFirst(Pageable pageable);

    /**
     * Find persons ordered by age (oldest first)
     */
    @Query("SELECT p FROM Person p WHERE p.birthDate IS NOT NULL ORDER BY p.birthDate ASC")
    Page<Person> findOrderByAgeOldestFirst(Pageable pageable);

    /**
     * Find persons with join fetch for states and picture
     */
    @Query("SELECT p FROM Person p LEFT JOIN FETCH p.birthState LEFT JOIN FETCH p.addressState LEFT JOIN FETCH p.picture ORDER BY p.firstnameLt ASC, p.lastnameLt ASC")
    Page<Person> findAllWithStateAndPicture(Pageable pageable);

    /**
     * Find persons by state designation (birth state)
     */
    @Query("SELECT p FROM Person p WHERE p.birthState.designationLt = :stateDesignation ORDER BY p.firstnameLt ASC, p.lastnameLt ASC")
    Page<Person> findByBirthStateDesignation(@Param("stateDesignation") String stateDesignation, Pageable pageable);

    /**
     * Find persons by state designation (address state)
     */
    @Query("SELECT p FROM Person p WHERE p.addressState.designationLt = :stateDesignation ORDER BY p.firstnameLt ASC, p.lastnameLt ASC")
    Page<Person> findByAddressStateDesignation(@Param("stateDesignation") String stateDesignation, Pageable pageable);

    /**
     * Find persons ordered by name in specific language
     */
    @Query("SELECT p FROM Person p ORDER BY p.firstnameAr ASC, p.lastnameAr ASC")
    Page<Person> findAllOrderByArabicName(Pageable pageable);

    @Query("SELECT p FROM Person p ORDER BY p.firstnameLt ASC, p.lastnameLt ASC")
    Page<Person> findAllOrderByLatinName(Pageable pageable);

    /**
     * Count persons by age group
     */
    @Query("SELECT COUNT(p) FROM Person p WHERE " +
           "YEAR(CURRENT_DATE) - YEAR(p.birthDate) - CASE WHEN MONTH(CURRENT_DATE) < MONTH(p.birthDate) OR (MONTH(CURRENT_DATE) = MONTH(p.birthDate) AND DAY(CURRENT_DATE) < DAY(p.birthDate)) THEN 1 ELSE 0 END < 18")
    Long countMinors();

    @Query("SELECT COUNT(p) FROM Person p WHERE " +
           "YEAR(CURRENT_DATE) - YEAR(p.birthDate) - CASE WHEN MONTH(CURRENT_DATE) < MONTH(p.birthDate) OR (MONTH(CURRENT_DATE) = MONTH(p.birthDate) AND DAY(CURRENT_DATE) < DAY(p.birthDate)) THEN 1 ELSE 0 END >= 18")
    Long countAdults();

    @Query("SELECT COUNT(p) FROM Person p WHERE " +
           "YEAR(CURRENT_DATE) - YEAR(p.birthDate) - CASE WHEN MONTH(CURRENT_DATE) < MONTH(p.birthDate) OR (MONTH(CURRENT_DATE) = MONTH(p.birthDate) AND DAY(CURRENT_DATE) < DAY(p.birthDate)) THEN 1 ELSE 0 END BETWEEN 18 AND 35")
    Long countYoungAdults();

    @Query("SELECT COUNT(p) FROM Person p WHERE " +
           "YEAR(CURRENT_DATE) - YEAR(p.birthDate) - CASE WHEN MONTH(CURRENT_DATE) < MONTH(p.birthDate) OR (MONTH(CURRENT_DATE) = MONTH(p.birthDate) AND DAY(CURRENT_DATE) < DAY(p.birthDate)) THEN 1 ELSE 0 END BETWEEN 36 AND 65")
    Long countMiddleAged();

    @Query("SELECT COUNT(p) FROM Person p WHERE " +
           "YEAR(CURRENT_DATE) - YEAR(p.birthDate) - CASE WHEN MONTH(CURRENT_DATE) < MONTH(p.birthDate) OR (MONTH(CURRENT_DATE) = MONTH(p.birthDate) AND DAY(CURRENT_DATE) < DAY(p.birthDate)) THEN 1 ELSE 0 END > 65")
    Long countSeniors();

    /**
     * Count persons with pictures
     */
    @Query("SELECT COUNT(p) FROM Person p WHERE p.picture IS NOT NULL")
    Long countWithPictures();

    /**
     * Count multilingual persons
     */
    @Query("SELECT COUNT(p) FROM Person p WHERE " +
           "(p.firstnameAr IS NOT NULL AND p.firstnameAr != '' AND p.lastnameAr IS NOT NULL AND p.lastnameAr != '') AND " +
           "(p.firstnameLt IS NOT NULL AND p.firstnameLt != '' AND p.lastnameLt IS NOT NULL AND p.lastnameLt != '')")
    Long countMultilingualPersons();

    /**
     * Find persons by generation (calculated based on birth year)
     */
    @Query("SELECT p FROM Person p WHERE YEAR(p.birthDate) BETWEEN :startYear AND :endYear")
    Page<Person> findByGeneration(@Param("startYear") Integer startYear, @Param("endYear") Integer endYear, Pageable pageable);

    /**
     * Search persons by comprehensive criteria
     */
    @Query("SELECT p FROM Person p LEFT JOIN p.birthState bs LEFT JOIN p.addressState as_state WHERE " +
           "(LOWER(p.firstnameAr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(p.lastnameAr) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(p.firstnameLt) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(p.lastnameLt) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(p.birthPlace) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(p.address) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(bs.designationLt) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(as_state.designationLt) LIKE LOWER(CONCAT('%', :search, '%'))) " +
           "ORDER BY p.firstnameLt ASC, p.lastnameLt ASC")
    Page<Person> comprehensiveSearch(@Param("search") String search, Pageable pageable);
}
