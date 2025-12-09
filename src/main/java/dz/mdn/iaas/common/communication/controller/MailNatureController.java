/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: MailNatureController
 *	@CreatedOn	: 10-15-2025
 *
 *	@Type		: Class
 *	@Layer		: Controller
 *	@Package	: Common / Communication
 *
 **/

package dz.mdn.iaas.common.communication.controller;

import dz.mdn.iaas.common.communication.service.MailNatureService;
import dz.mdn.iaas.common.communication.dto.MailNatureDTO;

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

@RestController
@RequestMapping("/mailNature")
@RequiredArgsConstructor
@Slf4j
public class MailNatureController {

    private final MailNatureService mailNatureService;

    // ========== POST ONE MAIL NATURE ==========

    @PostMapping
    public ResponseEntity<MailNatureDTO> createMailNature(@Valid @RequestBody MailNatureDTO mailNatureDTO) {
        log.info("Creating mail nature with French designation: {}", mailNatureDTO.getDesignationFr());
        
        MailNatureDTO createdMailNature = mailNatureService.createMailNature(mailNatureDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMailNature);
    }

    // ========== GET METADATA ==========

    @GetMapping("/{id}")
    public ResponseEntity<MailNatureDTO> getMailNatureMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for mail nature ID: {}", id);
        
        MailNatureDTO mailNatureMetadata = mailNatureService.getMailNatureById(id);
        
        return ResponseEntity.ok(mailNatureMetadata);
    }

    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<MailNatureDTO> getMailNatureByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting mail nature by French designation: {}", designationFr);
        
        return mailNatureService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMailNature(@PathVariable Long id) {
        log.info("Deleting mail nature with ID: {}", id);
        
        mailNatureService.deleteMailNature(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    @GetMapping
    public ResponseEntity<Page<MailNatureDTO>> getAllMailNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all mail natures - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<MailNatureDTO> mailNatures = mailNatureService.getAllMailNatures(pageable);
        
        return ResponseEntity.ok(mailNatures);
    }

    // ========== ADDITIONAL UTILITY ENDPOINTS ==========

    @GetMapping("/search")
    public ResponseEntity<Page<MailNatureDTO>> searchMailNatures(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching mail natures with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<MailNatureDTO> mailNatures = mailNatureService.searchMailNatures(query, pageable);
        
        return ResponseEntity.ok(mailNatures);
    }

    @GetMapping("/search/arabic")
    public ResponseEntity<Page<MailNatureDTO>> searchByArabicDesignation(
            @RequestParam String designationAr,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching mail natures by Arabic designation: {}", designationAr);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationAr"));
        Page<MailNatureDTO> mailNatures = mailNatureService.searchByDesignationAr(designationAr, pageable);
        
        return ResponseEntity.ok(mailNatures);
    }

    @GetMapping("/search/english")
    public ResponseEntity<Page<MailNatureDTO>> searchByEnglishDesignation(
            @RequestParam String designationEn,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching mail natures by English designation: {}", designationEn);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationEn"));
        Page<MailNatureDTO> mailNatures = mailNatureService.searchByDesignationEn(designationEn, pageable);
        
        return ResponseEntity.ok(mailNatures);
    }

    @GetMapping("/search/french")
    public ResponseEntity<Page<MailNatureDTO>> searchByFrenchDesignation(
            @RequestParam String designationFr,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching mail natures by French designation: {}", designationFr);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MailNatureDTO> mailNatures = mailNatureService.searchByDesignationFr(designationFr, pageable);
        
        return ResponseEntity.ok(mailNatures);
    }

    @GetMapping("/multilingual")
    public ResponseEntity<Page<MailNatureDTO>> getMultilingualMailNatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual mail natures");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MailNatureDTO> mailNatures = mailNatureService.getMultilingualMailNatures(pageable);
        
        return ResponseEntity.ok(mailNatures);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MailNatureDTO> updateMailNature(
            @PathVariable Long id,
            @Valid @RequestBody MailNatureDTO mailNatureDTO) {
        
        log.info("Updating mail nature with ID: {}", id);
        
        MailNatureDTO updatedMailNature = mailNatureService.updateMailNature(id, mailNatureDTO);
        
        return ResponseEntity.ok(updatedMailNature);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MailNatureDTO> partialUpdateMailNature(
            @PathVariable Long id,
            @RequestBody MailNatureDTO mailNatureDTO) {
        
        log.info("Partially updating mail nature with ID: {}", id);
        
        MailNatureDTO updatedMailNature = mailNatureService.partialUpdateMailNature(id, mailNatureDTO);
        
        return ResponseEntity.ok(updatedMailNature);
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkMailNatureExists(@PathVariable Long id) {
        log.debug("Checking existence of mail nature ID: {}", id);
        
        boolean exists = mailNatureService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkMailNatureExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);
        
        boolean exists = mailNatureService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getMailNaturesCount() {
        log.debug("Getting total count of mail natures");
        
        Long count = mailNatureService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<MailNatureInfoResponse> getMailNatureInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for mail nature ID: {}", id);
        
        try {
            return mailNatureService.findOne(id)
                    .map(mailNatureDTO -> {
                        MailNatureInfoResponse response = MailNatureInfoResponse.builder()
                                .mailNatureMetadata(mailNatureDTO)
                                .hasArabicDesignation(mailNatureDTO.getDesignationAr() != null && !mailNatureDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(mailNatureDTO.getDesignationEn() != null && !mailNatureDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(mailNatureDTO.getDesignationFr() != null && !mailNatureDTO.getDesignationFr().trim().isEmpty())
                                .isMultilingual(mailNatureDTO.isMultilingual())
                                .defaultDesignation(mailNatureDTO.getDefaultDesignation())
                                .displayText(mailNatureDTO.getDisplayText())
                                .availableLanguages(mailNatureDTO.getAvailableLanguages())
                                .isValid(mailNatureDTO.isValid())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting mail nature info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class MailNatureInfoResponse {
        private MailNatureDTO mailNatureMetadata;
        private Boolean hasArabicDesignation;
        private Boolean hasEnglishDesignation;
        private Boolean hasFrenchDesignation;
        private Boolean isMultilingual;
        private String defaultDesignation;
        private String displayText;
        private String[] availableLanguages;
        private Boolean isValid;
    }
}
