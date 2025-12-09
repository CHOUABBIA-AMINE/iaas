/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PersonService
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.service;

import dz.mdn.iaas.common.administration.model.Person;
import dz.mdn.iaas.common.administration.repository.PersonRepository;
import dz.mdn.iaas.common.administration.dto.PersonDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

/**
 * Person Service with CRUD operations
 * Handles person management operations with multilingual support and foreign key relationships
 * Based on exact field names: F_01=firstnameAr, F_02=lastnameAr, F_03=firstnameLt, F_04=lastnameLt, F_05=birthDate, F_06=birthPlace, F_07=address, F_08=birthState, F_09=addressState, F_10=picture
 * All fields are optional - no unique constraints
 * F_08 (birthState), F_09 (addressState), F_10 (picture) are optional foreign keys
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PersonService {

    private final PersonRepository personRepository;
    // Note: Dependencies for State and File repositories would be injected here if validation is needed

    // ========== CREATE OPERATIONS ==========

    /**
     * Create new person
     */
    public PersonDTO createPerson(PersonDTO personDTO) {
        log.info("Creating person with names: Arabic={} {}, Latin={} {}, Birth Date: {}", 
                personDTO.getFirstnameAr(), personDTO.getLastnameAr(),
                personDTO.getFirstnameLt(), personDTO.getLastnameLt(),
                personDTO.getBirthDate());

        // Basic validation - at least one name should be provided
        validatePersonData(personDTO, "create");

        // Create entity with exact field mapping
        Person person = new Person();
        person.setFirstnameAr(personDTO.getFirstnameAr()); // F_01
        person.setLastnameAr(personDTO.getLastnameAr()); // F_02
        person.setFirstnameLt(personDTO.getFirstnameLt()); // F_03
        person.setLastnameLt(personDTO.getLastnameLt()); // F_04
        person.setBirthDate(personDTO.getBirthDate()); // F_05
        person.setBirthPlace(personDTO.getBirthPlace()); // F_06
        person.setAddress(personDTO.getAddress()); // F_07
        // Note: Foreign keys (F_08, F_09, F_10) would be set here if repositories are injected

        Person savedPerson = personRepository.save(person);
        log.info("Successfully created person with ID: {}", savedPerson.getId());

        return PersonDTO.fromEntity(savedPerson);
    }

    // ========== READ OPERATIONS ==========

    /**
     * Get person by ID
     */
    @Transactional(readOnly = true)
    public PersonDTO getPersonById(Long id) {
        log.debug("Getting person with ID: {}", id);

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found with ID: " + id));

        return PersonDTO.fromEntity(person);
    }

    /**
     * Get person entity by ID
     */
    @Transactional(readOnly = true)
    public Person getPersonEntityById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found with ID: " + id));
    }

    /**
     * Find persons by Arabic firstname (F_01)
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> findByFirstnameAr(String firstnameAr, Pageable pageable) {
        log.debug("Finding persons with Arabic firstname: {}", firstnameAr);

        Page<Person> persons = personRepository.findByFirstnameAr(firstnameAr, pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Find persons by Arabic lastname (F_02)
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> findByLastnameAr(String lastnameAr, Pageable pageable) {
        log.debug("Finding persons with Arabic lastname: {}", lastnameAr);

        Page<Person> persons = personRepository.findByLastnameAr(lastnameAr, pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Find persons by Latin firstname (F_03)
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> findByFirstnameLt(String firstnameLt, Pageable pageable) {
        log.debug("Finding persons with Latin firstname: {}", firstnameLt);

        Page<Person> persons = personRepository.findByFirstnameLt(firstnameLt, pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Find persons by Latin lastname (F_04)
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> findByLastnameLt(String lastnameLt, Pageable pageable) {
        log.debug("Finding persons with Latin lastname: {}", lastnameLt);

        Page<Person> persons = personRepository.findByLastnameLt(lastnameLt, pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Find persons by birth date (F_05)
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> findByBirthDate(Date birthDate, Pageable pageable) {
        log.debug("Finding persons with birth date: {}", birthDate);

        Page<Person> persons = personRepository.findByBirthDate(birthDate, pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Find persons by birth place (F_06)
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> findByBirthPlace(String birthPlace, Pageable pageable) {
        log.debug("Finding persons with birth place: {}", birthPlace);

        Page<Person> persons = personRepository.findByBirthPlace(birthPlace, pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Find persons by birth state ID (F_08)
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> findByBirthStateId(Long birthStateId, Pageable pageable) {
        log.debug("Finding persons with birth state ID: {}", birthStateId);

        Page<Person> persons = personRepository.findByBirthStateId(birthStateId, pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Find persons by address state ID (F_09)
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> findByAddressStateId(Long addressStateId, Pageable pageable) {
        log.debug("Finding persons with address state ID: {}", addressStateId);

        Page<Person> persons = personRepository.findByAddressStateId(addressStateId, pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Find persons by full Arabic name
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> findByFullNameAr(String firstnameAr, String lastnameAr, Pageable pageable) {
        log.debug("Finding persons with full Arabic name: {} {}", firstnameAr, lastnameAr);

        Page<Person> persons = personRepository.findByFullNameAr(firstnameAr, lastnameAr, pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Find persons by full Latin name
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> findByFullNameLt(String firstnameLt, String lastnameLt, Pageable pageable) {
        log.debug("Finding persons with full Latin name: {} {}", firstnameLt, lastnameLt);

        Page<Person> persons = personRepository.findByFullNameLt(firstnameLt, lastnameLt, pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Get all persons with pagination
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> getAllPersons(Pageable pageable) {
        log.debug("Getting all persons with pagination");

        Page<Person> persons = personRepository.findAllOrderByName(pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Find one person by ID
     */
    @Transactional(readOnly = true)
    public Optional<PersonDTO> findOne(Long id) {
        log.debug("Finding person by ID: {}", id);

        return personRepository.findById(id)
                .map(PersonDTO::fromEntity);
    }

    /**
     * Search persons by name
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> searchPersons(String searchTerm, Pageable pageable) {
        log.debug("Searching persons with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllPersons(pageable);
        }

        Page<Person> persons = personRepository.searchByName(searchTerm.trim(), pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Search persons with state context
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> searchPersonsWithStateContext(String searchTerm, Pageable pageable) {
        log.debug("Searching persons with state context for term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllPersons(pageable);
        }

        Page<Person> persons = personRepository.searchWithStateContext(searchTerm.trim(), pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Comprehensive search
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> comprehensiveSearch(String searchTerm, Pageable pageable) {
        log.debug("Comprehensive search for persons with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllPersons(pageable);
        }

        Page<Person> persons = personRepository.comprehensiveSearch(searchTerm.trim(), pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Get multilingual persons
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> getMultilingualPersons(Pageable pageable) {
        log.debug("Getting multilingual persons");

        Page<Person> persons = personRepository.findMultilingualPersons(pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Get persons with Arabic names
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> getPersonsWithArabicNames(Pageable pageable) {
        log.debug("Getting persons with Arabic names");

        Page<Person> persons = personRepository.findWithArabicNames(pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Get persons with Latin names
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> getPersonsWithLatinNames(Pageable pageable) {
        log.debug("Getting persons with Latin names");

        Page<Person> persons = personRepository.findWithLatinNames(pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Get persons by age range
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> getPersonsByAgeRange(Integer minAge, Integer maxAge, Pageable pageable) {
        log.debug("Getting persons with age between {} and {}", minAge, maxAge);

        Page<Person> persons = personRepository.findByAgeRange(minAge, maxAge, pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Get persons by birth year
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> getPersonsByBirthYear(Integer year, Pageable pageable) {
        log.debug("Getting persons born in year: {}", year);

        Page<Person> persons = personRepository.findByBirthYear(year, pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Get persons by birth date range
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> getPersonsByBirthDateRange(Date startDate, Date endDate, Pageable pageable) {
        log.debug("Getting persons born between {} and {}", startDate, endDate);

        Page<Person> persons = personRepository.findByBirthDateBetween(startDate, endDate, pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Get persons with pictures
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> getPersonsWithPictures(Pageable pageable) {
        log.debug("Getting persons with pictures");

        Page<Person> persons = personRepository.findWithPictures(pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Get persons without pictures
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> getPersonsWithoutPictures(Pageable pageable) {
        log.debug("Getting persons without pictures");

        Page<Person> persons = personRepository.findWithoutPictures(pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Get persons with complete birth info
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> getPersonsWithCompleteBirthInfo(Pageable pageable) {
        log.debug("Getting persons with complete birth info");

        Page<Person> persons = personRepository.findWithCompleteBirthInfo(pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Get persons with complete address
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> getPersonsWithCompleteAddress(Pageable pageable) {
        log.debug("Getting persons with complete address");

        Page<Person> persons = personRepository.findWithCompleteAddress(pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Get persons with same birth and address state
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> getPersonsWithSameState(Pageable pageable) {
        log.debug("Getting persons with same birth and address state");

        Page<Person> persons = personRepository.findWithSameState(pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Get birthdays today
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> getBirthdaysToday(Pageable pageable) {
        log.debug("Getting persons with birthday today");

        Page<Person> persons = personRepository.findBirthdayToday(pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Get birthdays this month
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> getBirthdaysThisMonth(Pageable pageable) {
        log.debug("Getting persons with birthday this month");

        Page<Person> persons = personRepository.findBirthdayThisMonth(pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Get minors (under 18)
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> getMinors(Pageable pageable) {
        log.debug("Getting minors (under 18)");

        Page<Person> persons = personRepository.findMinors(pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Get adults (18+)
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> getAdults(Pageable pageable) {
        log.debug("Getting adults (18+)");

        Page<Person> persons = personRepository.findAdults(pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Get persons ordered by age (youngest first)
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> getPersonsOrderByAgeYoungestFirst(Pageable pageable) {
        log.debug("Getting persons ordered by age (youngest first)");

        Page<Person> persons = personRepository.findOrderByAgeYoungestFirst(pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Get persons ordered by age (oldest first)
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> getPersonsOrderByAgeOldestFirst(Pageable pageable) {
        log.debug("Getting persons ordered by age (oldest first)");

        Page<Person> persons = personRepository.findOrderByAgeOldestFirst(pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Get persons by generation
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> getPersonsByGeneration(Integer startYear, Integer endYear, Pageable pageable) {
        log.debug("Getting persons from generation {} - {}", startYear, endYear);

        Page<Person> persons = personRepository.findByGeneration(startYear, endYear, pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Find persons by state designation (birth state)
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> findByBirthStateDesignation(String stateDesignation, Pageable pageable) {
        log.debug("Finding persons by birth state designation: {}", stateDesignation);

        Page<Person> persons = personRepository.findByBirthStateDesignation(stateDesignation, pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    /**
     * Find persons by state designation (address state)
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> findByAddressStateDesignation(String stateDesignation, Pageable pageable) {
        log.debug("Finding persons by address state designation: {}", stateDesignation);

        Page<Person> persons = personRepository.findByAddressStateDesignation(stateDesignation, pageable);
        return persons.map(PersonDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    /**
     * Update person
     */
    public PersonDTO updatePerson(Long id, PersonDTO personDTO) {
        log.info("Updating person with ID: {}", id);

        Person existingPerson = getPersonEntityById(id);

        // Basic validation
        validatePersonData(personDTO, "update");

        // Update fields with exact field mapping
        existingPerson.setFirstnameAr(personDTO.getFirstnameAr()); // F_01
        existingPerson.setLastnameAr(personDTO.getLastnameAr()); // F_02
        existingPerson.setFirstnameLt(personDTO.getFirstnameLt()); // F_03
        existingPerson.setLastnameLt(personDTO.getLastnameLt()); // F_04
        existingPerson.setBirthDate(personDTO.getBirthDate()); // F_05
        existingPerson.setBirthPlace(personDTO.getBirthPlace()); // F_06
        existingPerson.setAddress(personDTO.getAddress()); // F_07
        // Note: Foreign keys (F_08, F_09, F_10) would be updated here if repositories are injected

        Person updatedPerson = personRepository.save(existingPerson);
        log.info("Successfully updated person with ID: {}", id);

        return PersonDTO.fromEntity(updatedPerson);
    }

    // ========== DELETE OPERATIONS ==========

    /**
     * Delete person
     */
    public void deletePerson(Long id) {
        log.info("Deleting person with ID: {}", id);

        Person person = getPersonEntityById(id);
        personRepository.delete(person);

        log.info("Successfully deleted person with ID: {}", id);
    }

    /**
     * Delete person by ID (direct)
     */
    public void deletePersonById(Long id) {
        log.info("Deleting person by ID: {}", id);

        if (!personRepository.existsById(id)) {
            throw new RuntimeException("Person not found with ID: " + id);
        }

        personRepository.deleteById(id);
        log.info("Successfully deleted person with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    /**
     * Check if person exists
     */
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return personRepository.existsById(id);
    }

    /**
     * Get total count of persons
     */
    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return personRepository.countAllPersons();
    }

    /**
     * Get count by birth state
     */
    @Transactional(readOnly = true)
    public Long getCountByBirthState(Long birthStateId) {
        return personRepository.countByBirthStateId(birthStateId);
    }

    /**
     * Get count by address state
     */
    @Transactional(readOnly = true)
    public Long getCountByAddressState(Long addressStateId) {
        return personRepository.countByAddressStateId(addressStateId);
    }

    /**
     * Get count of minors
     */
    @Transactional(readOnly = true)
    public Long getMinorsCount() {
        return personRepository.countMinors();
    }

    /**
     * Get count of adults
     */
    @Transactional(readOnly = true)
    public Long getAdultsCount() {
        return personRepository.countAdults();
    }

    /**
     * Get count of young adults
     */
    @Transactional(readOnly = true)
    public Long getYoungAdultsCount() {
        return personRepository.countYoungAdults();
    }

    /**
     * Get count of middle aged
     */
    @Transactional(readOnly = true)
    public Long getMiddleAgedCount() {
        return personRepository.countMiddleAged();
    }

    /**
     * Get count of seniors
     */
    @Transactional(readOnly = true)
    public Long getSeniorsCount() {
        return personRepository.countSeniors();
    }

    /**
     * Get count of persons with pictures
     */
    @Transactional(readOnly = true)
    public Long getCountWithPictures() {
        return personRepository.countWithPictures();
    }

    /**
     * Get count of multilingual persons
     */
    @Transactional(readOnly = true)
    public Long getMultilingualCount() {
        return personRepository.countMultilingualPersons();
    }

    // ========== VALIDATION METHODS ==========

    /**
     * Validate person data
     */
    private void validatePersonData(PersonDTO personDTO, String operation) {
        // Basic validation - at least one name field should be provided
        if (!personDTO.hasValidName()) {
            throw new RuntimeException("At least one name field (firstname or lastname in any language) is required for " + operation);
        }

        // Validate birth date is not in the future
        if (personDTO.getBirthDate() != null && personDTO.getBirthDate().after(new Date())) {
            throw new RuntimeException("Birth date cannot be in the future");
        }
    }
}
