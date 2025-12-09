/**
 *	
 *	@author		: CHOUABBIA Amine
 *	@Name		: MailTypeController
 *	@CreatedOn	: 10-15-2025
 *	@Type		: REST Controller
 *	@Layer		: Presentation
 *	@Package	: Common / Communication / Controller
 *
 **/

package dz.mdn.iaas.common.communication.controller;

import dz.mdn.iaas.common.communication.service.MailTypeService;
import dz.mdn.iaas.common.communication.dto.MailTypeDTO;

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
@RequestMapping("/mailType")
@RequiredArgsConstructor
@Slf4j
public class MailTypeController {

    private final MailTypeService mailTypeService;

    // ========== POST ONE MAIL TYPE ==========

    @PostMapping
    public ResponseEntity<MailTypeDTO> createMailType(@Valid @RequestBody MailTypeDTO mailTypeDTO) {
        log.info("Creating mail type with French designation: {}", mailTypeDTO.getDesignationFr());
        
        MailTypeDTO createdMailType = mailTypeService.createMailType(mailTypeDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMailType);
    }

    // ========== GET METADATA ==========

    @GetMapping("/{id}")
    public ResponseEntity<MailTypeDTO> getMailTypeMetadata(@PathVariable Long id) {
        log.debug("Getting metadata for mail type ID: {}", id);
        
        MailTypeDTO mailTypeMetadata = mailTypeService.getMailTypeById(id);
        
        return ResponseEntity.ok(mailTypeMetadata);
    }

    @GetMapping("/designation-fr/{designationFr}")
    public ResponseEntity<MailTypeDTO> getMailTypeByDesignationFr(@PathVariable String designationFr) {
        log.debug("Getting mail type by French designation: {}", designationFr);
        
        return mailTypeService.findByDesignationFr(designationFr)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ========== DELETE ONE ==========

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMailType(@PathVariable Long id) {
        log.info("Deleting mail type with ID: {}", id);
        
        mailTypeService.deleteMailType(id);
        
        return ResponseEntity.noContent().build();
    }

    // ========== GET ALL ==========

    @GetMapping
    public ResponseEntity<Page<MailTypeDTO>> getAllMailTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Getting all mail types - page: {}, size: {}, sortBy: {}, sortDir: {}", 
                  page, size, sortBy, sortDir);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<MailTypeDTO> mailTypes = mailTypeService.getAllMailTypes(pageable);
        
        return ResponseEntity.ok(mailTypes);
    }

    // ========== ADDITIONAL UTILITY ENDPOINTS ==========

    @GetMapping("/search")
    public ResponseEntity<Page<MailTypeDTO>> searchMailTypes(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "designationFr") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        log.debug("Searching mail types with query: {}", query);
        
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<MailTypeDTO> mailTypes = mailTypeService.searchMailTypes(query, pageable);
        
        return ResponseEntity.ok(mailTypes);
    }

    @GetMapping("/search/arabic")
    public ResponseEntity<Page<MailTypeDTO>> searchByArabicDesignation(
            @RequestParam String designationAr,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching mail types by Arabic designation: {}", designationAr);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationAr"));
        Page<MailTypeDTO> mailTypes = mailTypeService.searchByDesignationAr(designationAr, pageable);
        
        return ResponseEntity.ok(mailTypes);
    }

    @GetMapping("/search/english")
    public ResponseEntity<Page<MailTypeDTO>> searchByEnglishDesignation(
            @RequestParam String designationEn,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching mail types by English designation: {}", designationEn);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationEn"));
        Page<MailTypeDTO> mailTypes = mailTypeService.searchByDesignationEn(designationEn, pageable);
        
        return ResponseEntity.ok(mailTypes);
    }

    @GetMapping("/search/french")
    public ResponseEntity<Page<MailTypeDTO>> searchByFrenchDesignation(
            @RequestParam String designationFr,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Searching mail types by French designation: {}", designationFr);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MailTypeDTO> mailTypes = mailTypeService.searchByDesignationFr(designationFr, pageable);
        
        return ResponseEntity.ok(mailTypes);
    }

    // ========== CATEGORY-BASED ENDPOINTS ==========

    @GetMapping("/multilingual")
    public ResponseEntity<Page<MailTypeDTO>> getMultilingualMailTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting multilingual mail types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MailTypeDTO> mailTypes = mailTypeService.getMultilingualMailTypes(pageable);
        
        return ResponseEntity.ok(mailTypes);
    }

    @GetMapping("/incoming")
    public ResponseEntity<Page<MailTypeDTO>> getIncomingMailTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting incoming mail types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MailTypeDTO> mailTypes = mailTypeService.getIncomingMailTypes(pageable);
        
        return ResponseEntity.ok(mailTypes);
    }

    @GetMapping("/outgoing")
    public ResponseEntity<Page<MailTypeDTO>> getOutgoingMailTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting outgoing mail types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MailTypeDTO> mailTypes = mailTypeService.getOutgoingMailTypes(pageable);
        
        return ResponseEntity.ok(mailTypes);
    }

    @GetMapping("/internal")
    public ResponseEntity<Page<MailTypeDTO>> getInternalMailTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Getting internal mail types");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "designationFr"));
        Page<MailTypeDTO> mailTypes = mailTypeService.getInternalMailTypes(pageable);
        
        return ResponseEntity.ok(mailTypes);
    }

    // ========== UPDATE ENDPOINTS ==========

    @PutMapping("/{id}")
    public ResponseEntity<MailTypeDTO> updateMailType(
            @PathVariable Long id,
            @Valid @RequestBody MailTypeDTO mailTypeDTO) {
        
        log.info("Updating mail type with ID: {}", id);
        
        MailTypeDTO updatedMailType = mailTypeService.updateMailType(id, mailTypeDTO);
        
        return ResponseEntity.ok(updatedMailType);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MailTypeDTO> partialUpdateMailType(
            @PathVariable Long id,
            @RequestBody MailTypeDTO mailTypeDTO) {
        
        log.info("Partially updating mail type with ID: {}", id);
        
        MailTypeDTO updatedMailType = mailTypeService.partialUpdateMailType(id, mailTypeDTO);
        
        return ResponseEntity.ok(updatedMailType);
    }

    // ========== VALIDATION ENDPOINTS ==========

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkMailTypeExists(@PathVariable Long id) {
        log.debug("Checking existence of mail type ID: {}", id);
        
        boolean exists = mailTypeService.existsById(id);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/designation-fr/{designationFr}")
    public ResponseEntity<Boolean> checkMailTypeExistsByDesignationFr(@PathVariable String designationFr) {
        log.debug("Checking existence by French designation: {}", designationFr);
        
        boolean exists = mailTypeService.existsByDesignationFr(designationFr);
        
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getMailTypesCount() {
        log.debug("Getting total count of mail types");
        
        Long count = mailTypeService.getTotalCount();
        
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<MailTypeInfoResponse> getMailTypeInfo(@PathVariable Long id) {
        log.debug("Getting comprehensive info for mail type ID: {}", id);
        
        try {
            return mailTypeService.findOne(id)
                    .map(mailTypeDTO -> {
                        MailTypeInfoResponse response = MailTypeInfoResponse.builder()
                                .mailTypeMetadata(mailTypeDTO)
                                .hasArabicDesignation(mailTypeDTO.getDesignationAr() != null && !mailTypeDTO.getDesignationAr().trim().isEmpty())
                                .hasEnglishDesignation(mailTypeDTO.getDesignationEn() != null && !mailTypeDTO.getDesignationEn().trim().isEmpty())
                                .hasFrenchDesignation(mailTypeDTO.getDesignationFr() != null && !mailTypeDTO.getDesignationFr().trim().isEmpty())
                                .isMultilingual(mailTypeDTO.isMultilingual())
                                .defaultDesignation(mailTypeDTO.getDefaultDesignation())
                                .displayText(mailTypeDTO.getDisplayText())
                                .availableLanguages(mailTypeDTO.getAvailableLanguages())
                                .isValid(mailTypeDTO.isValid())
                                .category(mailTypeDTO.getCategory())
                                .isIncoming(mailTypeDTO.isIncoming())
                                .isOutgoing(mailTypeDTO.isOutgoing())
                                .isInternal(mailTypeDTO.isInternal())
                                .build();
                        
                        return ResponseEntity.ok(response);
                    })
                    .orElse(ResponseEntity.notFound().build());
                    
        } catch (Exception e) {
            log.error("Error getting mail type info for ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ========== RESPONSE DTOs ==========

    @lombok.Data
    @lombok.Builder
    @lombok.NoArgsConstructor
    @lombok.AllArgsConstructor
    public static class MailTypeInfoResponse {
        private MailTypeDTO mailTypeMetadata;
        private Boolean hasArabicDesignation;
        private Boolean hasEnglishDesignation;
        private Boolean hasFrenchDesignation;
        private Boolean isMultilingual;
        private String defaultDesignation;
        private String displayText;
        private String[] availableLanguages;
        private Boolean isValid;
        private String category;
        private Boolean isIncoming;
        private Boolean isOutgoing;
        private Boolean isInternal;
    }
}
