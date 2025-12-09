/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PersonController
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.controller;

import dz.mdn.iaas.common.administration.service.PersonService;
import dz.mdn.iaas.common.administration.dto.PersonDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Person REST Controller
 * Handles person operations: create, get metadata, delete, get all
 * Based on exact Person model: F_00=id, F_01=firstnameAr, F_02=lastnameAr, F_03=firstnameLt, F_04=lastnameLt, F_05=birthDate, F_06=birthPlace, F_07=address, F_08=birthState, F_09=addressState, F_10=picture
 * All fields are optional - no unique constraints
 * F_08 (birthState), F_09 (addressState), F_10 (picture) are optional foreign keys
 */
@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
@Slf4j
public class PersonController {

    private final PersonService personService;

    // ========== POST ONE PERSON ==========

    /**
     * Create new person
     * Creates person with multilingual names, birth information, address, and optional file attachments
     */
    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@Valid @RequestBody PersonDTO personDTO) {
        log.info("Creating person with names: Arabic={} {}, Latin={} {}, Birth Date: {}", 
                personDTO.getFirstnameAr(), personDTO.getLastnameAr(),
                personDTO.getFirstnameLt(), personDTO.getLastnameLt(),
                personDTO.getBirthDate());
        
        PersonDTO createdPerson = personService.createPerson(personDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
    }

    // ========== GET METADATA ==========

    /**
     * Get person metadata by ID
     * Returns person information with multilingual names, birth/address info, and related state/file data
     */
    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPersonMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for person ID: {}", id);
        
        PersonDTO personMetadata = personService.getPersonById(id);
        
        return ResponseEntity.ok(personMetadata);
    }

    /**
     * Get persons by Arabic firstname (F_01)
     */
    @GetMapping("/firstname-ar/{firstnameAr}")
    public ResponseEntity<Page<PersonDTO>> getPersonsByFirstnameAr(
            @PathVariable String firstnameAr,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons by Arabic firstname: {}", firstnameAr);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstnameLt", "lastnameLt"));
        Page<PersonDTO> persons = personService.findByFirstnameAr(firstnameAr, pageable);
        
        return ResponseEntity.ok(persons);
    }

    /**
     * Get persons by Arabic lastname (F_02)
     */
    @GetMapping("/lastname-ar/{lastnameAr}")
    public ResponseEntity<Page<PersonDTO>> getPersonsByLastnameAr(
            @PathVariable String lastnameAr,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons by Arabic lastname: {}", lastnameAr);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstnameLt", "lastnameLt"));
        Page<PersonDTO> persons = personService.findByLastnameAr(lastnameAr, pageable);
        
        return ResponseEntity.ok(persons);
    }

    /**
     * Get persons by Latin firstname (F_03)
     */
    @GetMapping("/firstname-lt/{firstnameLt}")
    public ResponseEntity<Page<PersonDTO>> getPersonsByFirstnameLt(
            @PathVariable String firstnameLt,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons by Latin firstname: {}", firstnameLt);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstnameLt", "lastnameLt"));
        Page<PersonDTO> persons = personService.findByFirstnameLt(firstnameLt, pageable);
        
        return ResponseEntity.ok(persons);
    }

    /**
     * Get persons by Latin lastname (F_04)
     */
    @GetMapping("/lastname-lt/{lastnameLt}")
    public ResponseEntity<Page<PersonDTO>> getPersonsByLastnameLt(
            @PathVariable String lastnameLt,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons by Latin lastname: {}", lastnameLt);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstnameLt", "lastnameLt"));
        Page<PersonDTO> persons = personService.findByLastnameLt(lastnameLt, pageable);
        
        return ResponseEntity.ok(persons);
    }

    /**
     * Get persons by full Arabic name
     */
    @GetMapping("/full-name-ar")
    public ResponseEntity<Page<PersonDTO>> getPersonsByFullNameAr(
            @RequestParam String firstnameAr,
            @RequestParam String lastnameAr,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons by full Arabic name: {} {}", firstnameAr, lastnameAr);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstnameLt", "lastnameLt"));
        Page<PersonDTO> persons = personService.findByFullNameAr(firstnameAr, lastnameAr, pageable);
        
        return ResponseEntity.ok(persons);
    }

    /**
     * Get persons by full Latin name
     */
    @GetMapping("/full-name-lt")
    public ResponseEntity<Page<PersonDTO>> getPersonsByFullNameLt(
            @RequestParam String firstnameLt,
            @RequestParam String lastnameLt,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons by full Latin name: {} {}", firstnameLt, lastnameLt);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstnameLt", "lastnameLt"));
        Page<PersonDTO> persons = personService.findByFullNameLt(firstnameLt, lastnameLt, pageable);
        
        return ResponseEntity.ok(persons);
    }

    /**
     * Get persons by birth date (F_05)
     */
    @GetMapping("/birth-date/{birthDate}")
    public ResponseEntity<Page<PersonDTO>> getPersonsByBirthDate(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons by birth date: {}", birthDate);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstnameLt", "lastnameLt"));
        Page<PersonDTO> persons = personService.findByBirthDate(birthDate, pageable);
        
        return ResponseEntity.ok(persons);
    }

    /**
     * Get persons by birth place (F_06)
     */
    @GetMapping("/birth-place/{birthPlace}")
    public ResponseEntity<Page<PersonDTO>> getPersonsByBirthPlace(
            @PathVariable String birthPlace,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons by birth place: {}", birthPlace);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstnameLt", "lastnameLt"));
        Page<PersonDTO> persons = personService.findByBirthPlace(birthPlace, pageable);
        
        return ResponseEntity.ok(persons);
    }

    /**
     * Get persons by birth state ID (F_08)
     */
    @GetMapping("/birth-state/{birthStateId}")
    public ResponseEntity<Page<PersonDTO>> getPersonsByBirthState(
            @PathVariable Long birthStateId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons by birth state ID: {}", birthStateId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstnameLt", "lastnameLt"));
        Page<PersonDTO> persons = personService.findByBirthStateId(birthStateId, pageable);
        
        return ResponseEntity.ok(persons);
    }

    /**
     * Get persons by address state ID (F_09)
     */
    @GetMapping("/address-state/{addressStateId}")
    public ResponseEntity<Page<PersonDTO>> getPersonsByAddressState(
            @PathVariable Long addressStateId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons by address state ID: {}", addressStateId);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstnameLt", "lastnameLt"));
        Page<PersonDTO> persons = personService.findByAddressStateId(addressStateId, pageable);
        
        return ResponseEntity.ok(persons);
    }

    // ========== DELETE ONE ==========

    /**
     * Delete person by ID
     * Removes person from the system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        log.info("Deleting person with ID: {}", id);
        
        personService.deletePerson(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    /**
     * Get all persons with pagination
     * Returns list of all persons ordered by Latin names first, then Arabic names
     */
    @GetMapping
    public ResponseEntity<Page<PersonDTO>> getAllPersons(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "firstnameLt,lastnameLt") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all persons - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        String[] sortFields = sortBy.split(",");
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortFields));
        
        Page<PersonDTO> persons = personService.getAllPersons(pageable);
        
        return ResponseEntity.ok(persons);
    }

    // ========== SEARCH ENDPOINTS ==========

    /**
     * Search persons by name (all languages)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<PersonDTO>> searchPersons(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "firstnameLt,lastnameLt") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching persons with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        String[] sortFields = sortBy.split(",");
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortFields));
        
        Page<PersonDTO> persons = personService.searchPersons(query, pageable);
        
        return ResponseEntity.ok(persons);
    }

    /**
     * Search persons with state context
     */
    @GetMapping("/search/context")
    public ResponseEntity<Page<PersonDTO>> searchPersonsWithStateContext(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching persons with state context for query: {}", query);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstnameLt", "lastnameLt"));
        Page<PersonDTO> persons = personService.searchPersonsWithStateContext(query, pageable);
        
        return ResponseEntity.ok(persons);
    }

    /**
     * Comprehensive search
     */
    @GetMapping("/search/comprehensive")
    public ResponseEntity<Page<PersonDTO>> comprehensiveSearch(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Comprehensive search for persons with query: {}", query);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstnameLt", "lastnameLt"));
        Page<PersonDTO> persons = personService.comprehensiveSearch(query, pageable);
        
        return ResponseEntity.ok(persons);
    }

    // ========== MULTILINGUAL ENDPOINTS ==========

    /**
     * Get multilingual persons
     */
    @GetMapping("/multilingual")
    public ResponseEntity<Page<PersonDTO>> getMultilingualPersons(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual persons");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstnameLt", "lastnameLt"));
        Page<PersonDTO> persons = personService.getMultilingualPersons(pageable);
        
        return ResponseEntity.ok(persons);
    }

    /**
     * Get persons with Arabic names
     */
    @GetMapping("/arabic-names")
    public ResponseEntity<Page<PersonDTO>> getPersonsWithArabicNames(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons with Arabic names");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstnameAr", "lastnameAr"));
        Page<PersonDTO> persons = personService.getPersonsWithArabicNames(pageable);
        
        return ResponseEntity.ok(persons);
    }

    /**
     * Get persons with Latin names
     */
    @GetMapping("/latin-names")
    public ResponseEntity<Page<PersonDTO>> getPersonsWithLatinNames(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons with Latin names");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstnameLt", "lastnameLt"));
        Page<PersonDTO> persons = personService.getPersonsWithLatinNames(pageable);
        
        return ResponseEntity.ok(persons);
    }

    // ========== AGE-BASED ENDPOINTS ==========

    /**
     * Get persons by age range
     */
    @GetMapping("/age-range")
    public ResponseEntity<Page<PersonDTO>> getPersonsByAgeRange(
            @RequestParam Integer minAge,
            @RequestParam Integer maxAge,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons with age between {} and {}", minAge, maxAge);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "birthDate"));
        Page<PersonDTO> persons = personService.getPersonsByAgeRange(minAge, maxAge, pageable);
        
        return ResponseEntity.ok(persons);
    }

    /**
     * Get persons by birth year
     */
    @GetMapping("/birth-year/{year}")
    public ResponseEntity<Page<PersonDTO>> getPersonsByBirthYear(
            @PathVariable Integer year,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons born in year: {}", year);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "birthDate"));
        Page<PersonDTO> persons = personService.getPersonsByBirthYear(year, pageable);
        
        return ResponseEntity.ok(persons);
    }

    /**
     * Get persons by birth date range
     */
    @GetMapping("/birth-date-range")
    public ResponseEntity<Page<PersonDTO>> getPersonsByBirthDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons born between {} and {}", startDate, endDate);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "birthDate"));
        Page<PersonDTO> persons = personService.getPersonsByBirthDateRange(startDate, endDate, pageable);
        
        return ResponseEntity.ok(persons);
    }

    /**
     * Get minors (under 18)
     */
    @GetMapping("/minors")
    public ResponseEntity<Page<PersonDTO>> getMinors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting minors (under 18)");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "birthDate"));
        Page<PersonDTO> persons = personService.getMinors(pageable);
        
        return ResponseEntity.ok(persons);
    }

    /**
     * Get adults (18+)
     */
    @GetMapping("/adults")
    public ResponseEntity<Page<PersonDTO>> getAdults(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting adults (18+)");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "birthDate"));
        Page<PersonDTO> persons = personService.getAdults(pageable);
        
        return ResponseEntity.ok(persons);
    }

    /**
     * Get persons ordered by age (youngest first)
     */
    @GetMapping("/by-age/youngest-first")
    public ResponseEntity<Page<PersonDTO>> getPersonsByAgeYoungestFirst(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons ordered by age (youngest first)");
        
        Pageable pageable = PageRequest.of(page, size);
        Page<PersonDTO> persons = personService.getPersonsOrderByAgeYoungestFirst(pageable);
        
        return ResponseEntity.ok(persons);
    }

    /**
     * Get persons ordered by age (oldest first)
     */
    @GetMapping("/by-age/oldest-first")
    public ResponseEntity<Page<PersonDTO>> getPersonsByAgeOldestFirst(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons ordered by age (oldest first)");
        
        Pageable pageable = PageRequest.of(page, size);
        Page<PersonDTO> persons = personService.getPersonsOrderByAgeOldestFirst(pageable);
        
        return ResponseEntity.ok(persons);
    }

    // ========== PICTURE-BASED ENDPOINTS ==========

    /**
     * Get persons with pictures
     */
    @GetMapping("/with-pictures")
    public ResponseEntity<Page<PersonDTO>> getPersonsWithPictures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons with pictures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstnameLt", "lastnameLt"));
        Page<PersonDTO> persons = personService.getPersonsWithPictures(pageable);
        
        return ResponseEntity.ok(persons);
    }

    /**
     * Get persons without pictures
     */
    @GetMapping("/without-pictures")
    public ResponseEntity<Page<PersonDTO>> getPersonsWithoutPictures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons without pictures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstnameLt", "lastnameLt"));
        Page<PersonDTO> persons = personService.getPersonsWithoutPictures(pageable);
        
        return ResponseEntity.ok(persons);
    }

    // ========== PROFILE COMPLETION ENDPOINTS ==========

    /**
     * Get persons with complete birth info
     */
    @GetMapping("/complete-birth-info")
    public ResponseEntity<Page<PersonDTO>> getPersonsWithCompleteBirthInfo(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons with complete birth info");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "birthDate"));
        Page<PersonDTO> persons = personService.getPersonsWithCompleteBirthInfo(pageable);
        
        return ResponseEntity.ok(persons);
    }

    /**
     * Get persons with complete address
     */
    @GetMapping("/complete-address")
    public ResponseEntity<Page<PersonDTO>> getPersonsWithCompleteAddress(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons with complete address");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstnameLt", "lastnameLt"));
        Page<PersonDTO> persons = personService.getPersonsWithCompleteAddress(pageable);
        
        return ResponseEntity.ok(persons);
    }

    /**
     * Get persons with same birth and address state
     */
    @GetMapping("/same-state")
    public ResponseEntity<Page<PersonDTO>> getPersonsWithSameState(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons with same birth and address state");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstnameLt", "lastnameLt"));
        Page<PersonDTO> persons = personService.getPersonsWithSameState(pageable);
        
        return ResponseEntity.ok(persons);
    }

    // ========== BIRTHDAY ENDPOINTS ==========

    /**
     * Get birthdays today
     */
    @GetMapping("/birthdays/today")
    public ResponseEntity<Page<PersonDTO>> getBirthdaysToday(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons with birthday today");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstnameLt", "lastnameLt"));
        Page<PersonDTO> persons = personService.getBirthdaysToday(pageable);
        
        return ResponseEntity.ok(persons);
    }

    /**
     * Get birthdays this month
     */
    @GetMapping("/birthdays/this-month")
    public ResponseEntity<Page<PersonDTO>> getBirthdaysThisMonth(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons with birthday this month");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "birthDate"));
        Page<PersonDTO> persons = personService.getBirthdaysThisMonth(pageable);
        
        return ResponseEntity.ok(persons);
    }

    // ========== GENERATION ENDPOINTS ==========

    /**
     * Get persons by generation
     */
    @GetMapping("/generation")
    public ResponseEntity<Page<PersonDTO>> getPersonsByGeneration(
            @RequestParam Integer startYear,
            @RequestParam Integer endYear,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting persons from generation {} - {}", startYear, endYear);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "birthDate"));
        Page<PersonDTO> persons = personService.getPersonsByGeneration(startYear, endYear, pageable);
        
        return ResponseEntity.ok(persons);
    }

    // ========== STATE-BASED ENDPOINTS ==========

    /**
     * Find persons by birth state designation
     */
    @GetMapping("/birth-state-designation/{stateDesignation}")
    public ResponseEntity<Page<PersonDTO>> getPersonsByBirthStateDesignation(
            @PathVariable String stateDesignation,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Finding persons by birth state designation: {}", stateDesignation);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstnameLt", "lastnameLt"));
        Page<PersonDTO> persons = personService.findByBirthStateDesignation(stateDesignation, pageable);
        
        return ResponseEntity.ok(persons);
    }

    /**
     * Find persons by address state designation
     */
    @GetMapping("/address-state-designation/{stateDesignation}")
    public ResponseEntity<Page<PersonDTO>> getPersonsByAddressStateDesignation(
            @PathVariable String stateDesignation,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Finding persons by address state designation: {}", stateDesignation);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstnameLt", "lastnameLt"));
        Page<PersonDTO> persons = personService.findByAddressStateDesignation(stateDesignation, pageable);
        
        return ResponseEntity.ok(persons);
    }

    // ========== UPDATE ENDPOINTS ==========

    /**
     * Update person metadata
     */
    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> updatePerson(
            @PathVariable Long id,
            @Valid @RequestBody PersonDTO personDTO) {
        
        log.info("Updating person with ID: {}", id);
        
        PersonDTO updatedPerson = personService.updatePerson(id, personDTO);
        
        return ResponseEntity.ok(updatedPerson);
    }

    // ========== VALIDATION ENDPOINTS ==========

    /**
     * Check if person exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkPersonExists(@PathVariable Long id) {
        log.debug("Checking existence of person ID: {}", id);
        
        boolean exists = personService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    // ========== STATISTICS ENDPOINTS ==========

    /**
     * Get total count of persons
     */
    @GetMapping("/count")
    public ResponseEntity<Long> getPersonsCount() {
        log.debug("Getting total count of persons");
        
        Long count = personService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count by birth state
     */
    @GetMapping("/count/birth-state/{birthStateId}")
    public ResponseEntity<Long> getCountByBirthState(@PathVariable Long birthStateId) {
        log.debug("Getting count for birth state ID: {}", birthStateId);
        
        Long count = personService.getCountByBirthState(birthStateId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count by address state
     */
    @GetMapping("/count/address-state/{addressStateId}")
    public ResponseEntity<Long> getCountByAddressState(@PathVariable Long addressStateId) {
        log.debug("Getting count for address state ID: {}", addressStateId);
        
        Long count = personService.getCountByAddressState(addressStateId);
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of minors
     */
    @GetMapping("/count/minors")
    public ResponseEntity<Long> getMinorsCount() {
        log.debug("Getting count of minors");
        
        Long count = personService.getMinorsCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of adults
     */
    @GetMapping("/count/adults")
    public ResponseEntity<Long> getAdultsCount() {
        log.debug("Getting count of adults");
        
        Long count = personService.getAdultsCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of young adults
     */
    @GetMapping("/count/young-adults")
    public ResponseEntity<Long> getYoungAdultsCount() {
        log.debug("Getting count of young adults");
        
        Long count = personService.getYoungAdultsCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of middle aged
     */
    @GetMapping("/count/middle-aged")
    public ResponseEntity<Long> getMiddleAgedCount() {
        log.debug("Getting count of middle aged");
        
        Long count = personService.getMiddleAgedCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of seniors
     */
    @GetMapping("/count/seniors")
    public ResponseEntity<Long> getSeniorsCount() {
        log.debug("Getting count of seniors");
        
        Long count = personService.getSeniorsCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of persons with pictures
     */
    @GetMapping("/count/with-pictures")
    public ResponseEntity<Long> getCountWithPictures() {
        log.debug("Getting count of persons with pictures");
        
        Long count = personService.getCountWithPictures();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get count of multilingual persons
     */
    @GetMapping("/count/multilingual")
    public ResponseEntity<Long> getMultilingualCount() {
        log.debug("Getting count of multilingual persons");
        
        Long count = personService.getMultilingualCount();
        
        return ResponseEntity.ok(count);
    }

    /**
     * Get person info with comprehensive details
     */
    @GetMapping("/{id}/info")
    public ResponseEntity<PersonInfoResponse> getPersonInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for person ID: {}", id);
        
        try {
            return personService.findOne(id)
                    .map(personDTO -> {
                        PersonInfoResponse response = PersonInfoResponse.builder()
                                .personMetadata(personDTO)
                                .fullNameAr(personDTO.getFullNameAr())
                                .fullNameLt(personDTO.getFullNameLt())
                                .displayName(personDTO.getDisplayName())
                                .hasMultilingualName(personDTO.hasMultilingualName())
                                .age(personDTO.getAge())
                                .birthYear(personDTO.getBirthYear())
                                .ageGroup(personDTO.getAgeGroup())
                                .generation(personDTO.getGeneration())
                                .hasPicture(personDTO.hasPicture())
                                .pictureUrl(personDTO.getPictureUrl())
                                .isSameState(personDTO.isSameState())
                                .hasCompleteAddress(personDTO.hasCompleteAddress())
                                .hasCompleteBirthInfo(personDTO.hasCompleteBirthInfo())
                                .isAdult(personDTO.isAdult())
                                .isMinor(personDTO.isMinor())
                                .profileCompleteness(personDTO.getProfileCompleteness())
                                .profileStatus(personDTO.getProfileStatus())
                                .birthStateDesignation(personDTO.getBirthStateDesignation())
                                .addressStateDesignation(personDTO.getAddressStateDesignation())
                                .shortDisplay(personDTO.getShortDisplay())
                                .fullDisplay(personDTO.getFullDisplay())
                                .displayWithBirthInfo(personDTO.getDisplayWithBirthInfo())
                                .displayWithAddress(personDTO.getDisplayWithAddress())
                                .formalDisplayName(personDTO.getFormalDisplayName())
                                .initials(personDTO.getInitials())
                                .availableNameLanguages(personDTO.getAvailableNameLanguages())
                                .comparisonKey(personDTO.getComparisonKey())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting person info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class PersonInfoResponse {
        private PersonDTO personMetadata;
        private String fullNameAr;
        private String fullNameLt;
        private String displayName;
        private Boolean hasMultilingualName;
        private Integer age;
        private Integer birthYear;
        private String ageGroup;
        private String generation;
        private Boolean hasPicture;
        private String pictureUrl;
        private Boolean isSameState;
        private Boolean hasCompleteAddress;
        private Boolean hasCompleteBirthInfo;
        private Boolean isAdult;
        private Boolean isMinor;
        private Double profileCompleteness;
        private String profileStatus;
        private String birthStateDesignation;
        private String addressStateDesignation;
        private String shortDisplay;
        private String fullDisplay;
        private String displayWithBirthInfo;
        private String displayWithAddress;
        private String formalDisplayName;
        private String initials;
        private String[] availableNameLanguages;
        private String comparisonKey;
    }
}
