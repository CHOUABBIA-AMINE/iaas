/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ConsultationPhaseDTO
 *	@CreatedOn	: 10-12-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Consultation
 *
 **/

package dz.mdn.iaas.business.consultation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.business.consultation.model.ConsultationPhase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* ConsultationPhase Data Transfer Object
* Maps exactly to ConsultationPhase model fields: F_00=id, F_01=designationAr, F_02=designationEn,
* F_03=designationFr
* Required field: F_03 (designationFr) with unique constraint
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsultationPhaseDTO {
   
   private Long id; // F_00
   
   @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
   private String designationAr; // F_01 - nullable
   
   @Size(max = 200, message = "English designation must not exceed 200 characters")
   private String designationEn; // F_02 - nullable
   
   @NotBlank(message = "French designation is required")
   @Size(max = 200, message = "French designation must not exceed 200 characters")
   private String designationFr; // F_03 - required and unique
   
   // Additional fields for enriched responses
   private Integer consultationStepsCount;
   private Boolean hasConsultationSteps;

   /**
    * Create DTO from entity
    */
   public static ConsultationPhaseDTO fromEntity(ConsultationPhase consultationPhase) {
       if (consultationPhase == null) return null;
       
       return ConsultationPhaseDTO.builder()
               .id(consultationPhase.getId())
               .designationAr(consultationPhase.getDesignationAr())
               .designationEn(consultationPhase.getDesignationEn())
               .designationFr(consultationPhase.getDesignationFr())
               .build();
   }
   
   /**
    * Create enriched DTO from entity with consultation steps info
    */
   public static ConsultationPhaseDTO fromEntityWithSteps(ConsultationPhase consultationPhase) {
       if (consultationPhase == null) return null;
       
       ConsultationPhaseDTO dto = fromEntity(consultationPhase);
       if (consultationPhase.getConsultationSteps() != null) {
           dto.setConsultationStepsCount(consultationPhase.getConsultationSteps().size());
           dto.setHasConsultationSteps(!consultationPhase.getConsultationSteps().isEmpty());
       } else {
           dto.setConsultationStepsCount(0);
           dto.setHasConsultationSteps(false);
       }
       
       return dto;
   }
   
   /**
    * Convert to entity
    */
   public ConsultationPhase toEntity() {
       ConsultationPhase consultationPhase = new ConsultationPhase();
       consultationPhase.setId(this.id);
       consultationPhase.setDesignationAr(this.designationAr);
       consultationPhase.setDesignationEn(this.designationEn);
       consultationPhase.setDesignationFr(this.designationFr);
       return consultationPhase;
   }
   
   /**
    * Update entity from DTO
    */
   public void updateEntity(ConsultationPhase consultationPhase) {
       if (this.designationAr != null) {
           consultationPhase.setDesignationAr(this.designationAr);
       }
       if (this.designationEn != null) {
           consultationPhase.setDesignationEn(this.designationEn);
       }
       if (this.designationFr != null) {
           consultationPhase.setDesignationFr(this.designationFr);
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
    * Check if consultation phase is fully multilingual
    */
   public boolean isMultilingual() {
       return designationAr != null && !designationAr.trim().isEmpty() &&
              designationEn != null && !designationEn.trim().isEmpty() &&
              designationFr != null && !designationFr.trim().isEmpty();
   }
   
   /**
    * Get available languages for this consultation phase
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
    * Get consultation phase type based on French designation patterns
    */
   public String getConsultationPhaseType() {
       if (designationFr == null) return "UNKNOWN";
       
       String designation = designationFr.toLowerCase();
       
       // Common consultation phases in Algeria's public procurement
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
       String type = getConsultationPhaseType();
       return "PREPARATION".equals(type) || "PUBLICATION".equals(type) || 
              "SUBMISSION".equals(type) || "OPENING".equals(type) || "EVALUATION".equals(type);
   }
   
   /**
    * Check if this is a post-award phase
    */
   public boolean isPostAwardPhase() {
       String type = getConsultationPhaseType();
       return "ADJUDICATION".equals(type) || "NOTIFICATION".equals(type) || 
              "CONTRACT_SIGNATURE".equals(type);
   }
   
   /**
    * Create simplified DTO for dropdowns
    */
   public static ConsultationPhaseDTO createSimple(Long id, String designationFr) {
       return ConsultationPhaseDTO.builder()
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
       if (consultationStepsCount != null && consultationStepsCount > 0) {
           display += " (" + consultationStepsCount + " étapes)";
       }
       return display;
   }
   
   /**
    * Check if consultation phase has active steps
    */
   public boolean hasActiveSteps() {
       return hasConsultationSteps != null && hasConsultationSteps;
   }
   
   /**
    * Get phase order based on typical consultation process
    */
   public Integer getPhaseOrder() {
       String type = getConsultationPhaseType();
       
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
    * Check if phase allows consultation step modifications
    */
   public boolean allowsStepModifications() {
       // Typically, preparation and evaluation phases allow step modifications
       String type = getConsultationPhaseType();
       return "PREPARATION".equals(type) || "EVALUATION".equals(type);
   }
}
