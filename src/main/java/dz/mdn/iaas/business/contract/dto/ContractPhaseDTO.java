/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractPhaseDTO
 *	@CreatedOn	: 10-12-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.business.contract.model.ContractPhase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* ContractPhase Data Transfer Object
* Maps exactly to ContractPhase model fields: F_00=id, F_01=designationAr, F_02=designationEn,
* F_03=designationFr
* Required field: F_03 (designationFr) with unique constraint
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractPhaseDTO {
   
   private Long id; // F_00
   
   @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
   private String designationAr; // F_01 - nullable
   
   @Size(max = 200, message = "English designation must not exceed 200 characters")
   private String designationEn; // F_02 - nullable
   
   @NotBlank(message = "French designation is required")
   @Size(max = 200, message = "French designation must not exceed 200 characters")
   private String designationFr; // F_03 - required and unique
   
   // Additional fields for enriched responses
   private Integer contractStepsCount;
   private Boolean hasContractSteps;

   /**
    * Create DTO from entity
    */
   public static ContractPhaseDTO fromEntity(ContractPhase contractPhase) {
       if (contractPhase == null) return null;
       
       return ContractPhaseDTO.builder()
               .id(contractPhase.getId())
               .designationAr(contractPhase.getDesignationAr())
               .designationEn(contractPhase.getDesignationEn())
               .designationFr(contractPhase.getDesignationFr())
               .build();
   }
   
   /**
    * Create enriched DTO from entity with contract steps info
    */
   public static ContractPhaseDTO fromEntityWithSteps(ContractPhase contractPhase) {
       if (contractPhase == null) return null;
       
       ContractPhaseDTO dto = fromEntity(contractPhase);
       if (contractPhase.getContractSteps() != null) {
           dto.setContractStepsCount(contractPhase.getContractSteps().size());
           dto.setHasContractSteps(!contractPhase.getContractSteps().isEmpty());
       } else {
           dto.setContractStepsCount(0);
           dto.setHasContractSteps(false);
       }
       
       return dto;
   }
   
   /**
    * Convert to entity
    */
   public ContractPhase toEntity() {
       ContractPhase contractPhase = new ContractPhase();
       contractPhase.setId(this.id);
       contractPhase.setDesignationAr(this.designationAr);
       contractPhase.setDesignationEn(this.designationEn);
       contractPhase.setDesignationFr(this.designationFr);
       return contractPhase;
   }
   
   /**
    * Update entity from DTO
    */
   public void updateEntity(ContractPhase contractPhase) {
       if (this.designationAr != null) {
           contractPhase.setDesignationAr(this.designationAr);
       }
       if (this.designationEn != null) {
           contractPhase.setDesignationEn(this.designationEn);
       }
       if (this.designationFr != null) {
           contractPhase.setDesignationFr(this.designationFr);
       }
   }
   
   /**
    * Get default designation based on system locale
    */
   public String getDefaultDesignation() {
       // Prioritize French designation as it's required and commonly used in Algeria
       return designationFr != null ? designationFr : 
              (designationEn != null ? designationEn : designationAr);
   }
   
   /**
    * Get designation by language preference
    */
   public String getDesignationByLanguage(String language) {
       if (language == null) return getDefaultDesignation();
       
       return switch (language.toLowerCase()) {
           case "ar", "arabic" -> designationAr != null ? designationAr : getDefaultDesignation();
           case "en", "english" -> designationEn != null ? designationEn : getDefaultDesignation();
           case "fr", "french" -> designationFr != null ? designationFr : getDefaultDesignation();
           default -> getDefaultDesignation();
       };
   }
   
   /**
    * Check if contract phase is fully multilingual
    */
   public boolean isMultilingual() {
       return designationAr != null && !designationAr.trim().isEmpty() &&
              designationEn != null && !designationEn.trim().isEmpty() &&
              designationFr != null && !designationFr.trim().isEmpty();
   }
   
   /**
    * Get available languages for this contract phase
    */
   public String[] getAvailableLanguages() {
       java.util.List<String> languages = new java.util.ArrayList<>();
       
       if (designationAr != null && !designationAr.trim().isEmpty()) {
           languages.add("arabic");
       }
       if (designationEn != null && !designationEn.trim().isEmpty()) {
           languages.add("english");
       }
       if (designationFr != null && !designationFr.trim().isEmpty()) {
           languages.add("french");
       }
       
       return languages.stream().toArray(String[]::new);
   }
   
   /**
    * Get contract phase type based on French designation patterns
    */
   public String getContractPhaseType() {
       if (designationFr == null) return "UNKNOWN";
       
       String designation = designationFr.toLowerCase();
       
       // Common contract phases in Algeria's public procurement
       if (designation.contains("préparation") || designation.contains("preparation")) {
           return "PREPARATION";
       }
       if (designation.contains("publication") || designation.contains("annonce")) {
           return "PUBLICATION";
       }
       if (designation.contains("soumission") || designation.contains("dépôt")) {
           return "SUBMISSION";
       }
       if (designation.contains("ouverture") || designation.contains("dépouillement")) {
           return "OPENING";
       }
       if (designation.contains("évaluation") || designation.contains("analyse")) {
           return "EVALUATION";
       }
       if (designation.contains("adjudication") || designation.contains("attribution")) {
           return "ADJUDICATION";
       }
       if (designation.contains("notification") || designation.contains("information")) {
           return "NOTIFICATION";
       }
       if (designation.contains("recours") || designation.contains("contestation")) {
           return "APPEAL";
       }
       if (designation.contains("signature") || designation.contains("contrat")) {
           return "CONTRACT_SIGNATURE";
       }
       
       return "OTHER";
   }
   
   /**
    * Check if this is a pre-award phase
    */
   public boolean isPreAwardPhase() {
       String type = getContractPhaseType();
       return "PREPARATION".equals(type) || "PUBLICATION".equals(type) || 
              "SUBMISSION".equals(type) || "OPENING".equals(type) || "EVALUATION".equals(type);
   }
   
   /**
    * Check if this is a post-award phase
    */
   public boolean isPostAwardPhase() {
       String type = getContractPhaseType();
       return "ADJUDICATION".equals(type) || "NOTIFICATION".equals(type) || 
              "CONTRACT_SIGNATURE".equals(type);
   }
   
   /**
    * Create simplified DTO for dropdowns
    */
   public static ContractPhaseDTO createSimple(Long id, String designationFr) {
       return ContractPhaseDTO.builder()
               .id(id)
               .designationFr(designationFr)
               .build();
   }
   
   /**
    * Validate all required fields are present
    */
   public boolean isValid() {
       return designationFr != null && !designationFr.trim().isEmpty();
   }
   
   /**
    * Get short display for lists
    */
   public String getShortDisplay() {
       return designationFr != null && designationFr.length() > 50 ? 
              designationFr.substring(0, 50) + "..." : designationFr;
   }
   
   /**
    * Get full display with all languages
    */
   public String getFullDisplay() {
       StringBuilder sb = new StringBuilder();
       sb.append(designationFr);
       
       if (designationEn != null && !designationEn.equals(designationFr)) {
           sb.append(" / ").append(designationEn);
       }
       
       if (designationAr != null) {
           sb.append(" / ").append(designationAr);
       }
       
       return sb.toString();
   }
   
   /**
    * Get comparison key for sorting (by French designation)
    */
   public String getComparisonKey() {
       return designationFr != null ? designationFr.toUpperCase() : "";
   }
   
   /**
    * Get display text with step count
    */
   public String getDisplayWithStepCount() {
       String display = getShortDisplay();
       if (contractStepsCount != null && contractStepsCount > 0) {
           display += " (" + contractStepsCount + " étapes)";
       }
       return display;
   }
   
   /**
    * Check if contract phase has active steps
    */
   public boolean hasActiveSteps() {
       return hasContractSteps != null && hasContractSteps;
   }
   
   /**
    * Get phase order based on typical contract process
    */
   public Integer getPhaseOrder() {
       String type = getContractPhaseType();
       
       return switch (type) {
           case "PREPARATION" -> 1;
           case "PUBLICATION" -> 2;
           case "SUBMISSION" -> 3;
           case "OPENING" -> 4;
           case "EVALUATION" -> 5;
           case "ADJUDICATION" -> 6;
           case "NOTIFICATION" -> 7;
           case "APPEAL" -> 8;
           case "CONTRACT_SIGNATURE" -> 9;
           default -> 999; // OTHER types at the end
       };
   }
   
   /**
    * Check if phase allows contract step modifications
    */
   public boolean allowsStepModifications() {
       // Typically, preparation and evaluation phases allow step modifications
       String type = getContractPhaseType();
       return "PREPARATION".equals(type) || "EVALUATION".equals(type);
   }
}
