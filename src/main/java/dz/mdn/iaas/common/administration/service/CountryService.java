/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: CountryService
 *	@CreatedOn	: 10-14-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: Common / Administration
 *
 **/

package dz.mdn.iaas.common.administration.service;

import dz.mdn.iaas.common.administration.model.Country;
import dz.mdn.iaas.common.administration.repository.CountryRepository;
import dz.mdn.iaas.common.administration.dto.CountryDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CountryService {

    private final CountryRepository countryRepository;

    // ========== CREATE OPERATIONS ==========

    public CountryDTO createCountry(CountryDTO countryDTO) {
        log.info("Creating country with French designation: {}", countryDTO.getDesignationFr());

        // Validate required field
        if (countryDTO.getDesignationFr() == null || countryDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required");
        }

        // Check for duplicate French designation (unique constraint)
        if (countryRepository.existsByDesignationFr(countryDTO.getDesignationFr())) {
            throw new RuntimeException("Country with French designation '" + countryDTO.getDesignationFr() + "' already exists");
        }

        // Create entity with exact field mapping
        Country country = new Country();
        country.setDesignationAr(countryDTO.getDesignationAr()); // F_01
        country.setDesignationEn(countryDTO.getDesignationEn()); // F_02
        country.setDesignationFr(countryDTO.getDesignationFr()); // F_03

        Country savedCountry = countryRepository.save(country);
        log.info("Successfully created country with ID: {}", savedCountry.getId());

        return CountryDTO.fromEntity(savedCountry);
    }

    // ========== READ OPERATIONS ==========

    @Transactional(readOnly = true)
    public CountryDTO getCountryById(Long id) {
        log.debug("Getting country with ID: {}", id);

        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found with ID: " + id));

        return CountryDTO.fromEntity(country);
    }

    @Transactional(readOnly = true)
    public Country getCountryEntityById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public Optional<CountryDTO> findByDesignationFr(String designationFr) {
        log.debug("Finding country with French designation: {}", designationFr);

        return countryRepository.findByDesignationFr(designationFr)
                .map(CountryDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<CountryDTO> getAllCountries(Pageable pageable) {
        log.debug("Getting all countries with pagination");

        Page<Country> countries = countryRepository.findAll(pageable);
        return countries.map(CountryDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Optional<CountryDTO> findOne(Long id) {
        log.debug("Finding country by ID: {}", id);

        return countryRepository.findById(id)
                .map(CountryDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<CountryDTO> searchCountries(String searchTerm, Pageable pageable) {
        log.debug("Searching countries with term: {}", searchTerm);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllCountries(pageable);
        }

        Page<Country> countries = countryRepository.searchByAnyDesignation(searchTerm.trim(), pageable);
        return countries.map(CountryDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<CountryDTO> searchByDesignationAr(String designationAr, Pageable pageable) {
        log.debug("Searching countries by Arabic designation: {}", designationAr);

        Page<Country> countries = countryRepository.findByDesignationArContaining(designationAr, pageable);
        return countries.map(CountryDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<CountryDTO> searchByDesignationEn(String designationEn, Pageable pageable) {
        log.debug("Searching countries by English designation: {}", designationEn);

        Page<Country> countries = countryRepository.findByDesignationEnContaining(designationEn, pageable);
        return countries.map(CountryDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<CountryDTO> searchByDesignationFr(String designationFr, Pageable pageable) {
        log.debug("Searching countries by French designation: {}", designationFr);

        Page<Country> countries = countryRepository.findByDesignationFrContaining(designationFr, pageable);
        return countries.map(CountryDTO::fromEntity);
    }

    // ========== UPDATE OPERATIONS ==========

    public CountryDTO updateCountry(Long id, CountryDTO countryDTO) {
        log.info("Updating country with ID: {}", id);

        Country existingCountry = getCountryEntityById(id);

        // Validate required French designation
        if (countryDTO.getDesignationFr() == null || countryDTO.getDesignationFr().trim().isEmpty()) {
            throw new RuntimeException("French designation is required");
        }

        // Check for duplicate French designation (excluding current record)
        if (countryRepository.existsByDesignationFrAndIdNot(countryDTO.getDesignationFr(), id)) {
            throw new RuntimeException("Another country with French designation '" + countryDTO.getDesignationFr() + "' already exists");
        }

        // Update fields with exact field mapping
        existingCountry.setDesignationAr(countryDTO.getDesignationAr()); // F_01
        existingCountry.setDesignationEn(countryDTO.getDesignationEn()); // F_02
        existingCountry.setDesignationFr(countryDTO.getDesignationFr()); // F_03

        Country updatedCountry = countryRepository.save(existingCountry);
        log.info("Successfully updated country with ID: {}", id);

        return CountryDTO.fromEntity(updatedCountry);
    }

    public CountryDTO partialUpdateCountry(Long id, CountryDTO countryDTO) {
        log.info("Partially updating country with ID: {}", id);

        Country existingCountry = getCountryEntityById(id);

        // Update only non-null fields
        if (countryDTO.getDesignationAr() != null) {
            existingCountry.setDesignationAr(countryDTO.getDesignationAr()); // F_01
        }

        if (countryDTO.getDesignationEn() != null) {
            existingCountry.setDesignationEn(countryDTO.getDesignationEn()); // F_02
        }

        if (countryDTO.getDesignationFr() != null) {
            // Validate required field
            if (countryDTO.getDesignationFr().trim().isEmpty()) {
                throw new RuntimeException("French designation cannot be empty");
            }

            // Check for duplicate French designation (excluding current record)
            if (countryRepository.existsByDesignationFrAndIdNot(countryDTO.getDesignationFr(), id)) {
                throw new RuntimeException("Another country with French designation '" + countryDTO.getDesignationFr() + "' already exists");
            }

            existingCountry.setDesignationFr(countryDTO.getDesignationFr()); // F_03
        }

        Country updatedCountry = countryRepository.save(existingCountry);
        log.info("Successfully partially updated country with ID: {}", id);

        return CountryDTO.fromEntity(updatedCountry);
    }

    // ========== DELETE OPERATIONS ==========

    public void deleteCountry(Long id) {
        log.info("Deleting country with ID: {}", id);

        Country country = getCountryEntityById(id);
        countryRepository.delete(country);

        log.info("Successfully deleted country with ID: {}", id);
    }

    public void deleteCountryById(Long id) {
        log.info("Deleting country by ID: {}", id);

        if (!countryRepository.existsById(id)) {
            throw new RuntimeException("Country not found with ID: " + id);
        }

        countryRepository.deleteById(id);
        log.info("Successfully deleted country with ID: {}", id);
    }

    // ========== UTILITY METHODS ==========

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return countryRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByDesignationFr(String designationFr) {
        return countryRepository.existsByDesignationFr(designationFr);
    }

    @Transactional(readOnly = true)
    public Long getTotalCount() {
        return countryRepository.countAllCountries();
    }
}
