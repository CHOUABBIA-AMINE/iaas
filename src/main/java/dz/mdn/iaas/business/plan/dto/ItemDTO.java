/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ItemDTO
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Item Data Transfer Object
 * Maps exactly to Item model fields: F_00=id, F_01=designationAr, F_02=designationEn, F_03=designationFr, F_04=rubricId
 * Includes many-to-one relationship with Rubric and one-to-many relationship with PlannedItems
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDTO {

    private Long id; // F_00

    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    private String designationAr; // F_01 - optional

    @Size(max = 200, message = "English designation must not exceed 200 characters")
    private String designationEn; // F_02 - optional

    @NotBlank(message = "French designation is required")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    private String designationFr; // F_03 - required

    @NotNull(message = "Rubric is required")
    private Long rubricId; // F_04 - Rubric foreign key (required)

    // Related entities (populated when needed)
    private RubricDTO rubric; // Many-to-one relationship
    private List<PlannedItemDTO> plannedItems; // One-to-many relationship

    // Summary information
    private Integer plannedItemsCount;

    /**
     * Create DTO from entity
     */
    public static ItemDTO fromEntity(dz.mdn.iaas.business.plan.model.Item item) {
        if (item == null) return null;
        
        ItemDTO.ItemDTOBuilder builder = ItemDTO.builder()
                .id(item.getId())
                .designationAr(item.getDesignationAr())
                .designationEn(item.getDesignationEn())
                .designationFr(item.getDesignationFr());

        // Handle foreign key relationship
        if (item.getRubric() != null) {
            builder.rubricId(item.getRubric().getId());
        }

        // Add planned items count if available
        if (item.getPlannedItems() != null) {
            builder.plannedItemsCount(item.getPlannedItems().size());
        }

        return builder.build();
    }

    /**
     * Create DTO from entity with related objects
     */
    public static ItemDTO fromEntityWithRelations(dz.mdn.iaas.business.plan.model.Item item) {
        ItemDTO dto = fromEntity(item);
        if (dto == null) return null;

        // Populate related DTOs
        if (item.getRubric() != null) {
            dto.setRubric(RubricDTO.fromEntity(item.getRubric()));
        }

        if (item.getPlannedItems() != null && !item.getPlannedItems().isEmpty()) {
            List<PlannedItemDTO> plannedItemDTOs = new ArrayList<>();
            for (var plannedItem : item.getPlannedItems()) {
                plannedItemDTOs.add(PlannedItemDTO.fromEntity(plannedItem));
            }
            dto.setPlannedItems(plannedItemDTOs);
            dto.setPlannedItemsCount(plannedItemDTOs.size());
        }

        return dto;
    }

    /**
     * Get default designation (French first, then English, then Arabic)
     */
    public String getDefaultDesignation() {
        if (designationFr != null && !designationFr.trim().isEmpty()) {
            return designationFr;
        }
        if (designationEn != null && !designationEn.trim().isEmpty()) {
            return designationEn;
        }
        if (designationAr != null && !designationAr.trim().isEmpty()) {
            return designationAr;
        }
        return "N/A";
    }

    /**
     * Get display text with priority: French designation > English designation > Arabic designation
     */
    public String getDisplayText() {
        return getDefaultDesignation();
    }

    /**
     * Check if item has multiple language support
     */
    public boolean isMultilingual() {
        int languageCount = 0;
        if (designationFr != null && !designationFr.trim().isEmpty()) languageCount++;
        if (designationEn != null && !designationEn.trim().isEmpty()) languageCount++;
        if (designationAr != null && !designationAr.trim().isEmpty()) languageCount++;
        return languageCount > 1;
    }

    /**
     * Get available languages for this item
     */
    public String[] getAvailableLanguages() {
        List<String> languages = new ArrayList<>();
        
        if (designationFr != null && !designationFr.trim().isEmpty()) {
            languages.add("french");
        }
        if (designationEn != null && !designationEn.trim().isEmpty()) {
            languages.add("english");
        }
        if (designationAr != null && !designationAr.trim().isEmpty()) {
            languages.add("arabic");
        }
        
        return languages.stream().toArray(String[]::new);
    }

    /**
     * Get item category based on designation keywords
     */
    public String getItemCategory() {
        String designation = getDefaultDesignation().toLowerCase();
        
        // Equipment items
        if (designation.contains("équipement") || designation.contains("equipment") ||
            designation.contains("matériel") || designation.contains("material") ||
            designation.contains("outil") || designation.contains("tool") ||
            designation.contains("معدات") || designation.contains("أدوات")) {
            return "EQUIPMENT_ITEM";
        }
        
        // Resource items
        if (designation.contains("ressource") || designation.contains("resource") ||
            designation.contains("personnel") || designation.contains("staff") ||
            designation.contains("humain") || designation.contains("human") ||
            designation.contains("مورد") || designation.contains("موظف")) {
            return "RESOURCE_ITEM";
        }
        
        // Service items
        if (designation.contains("service") || designation.contains("prestation") ||
            designation.contains("assistance") || designation.contains("support") ||
            designation.contains("maintenance") || designation.contains("خدمة") ||
            designation.contains("دعم")) {
            return "SERVICE_ITEM";
        }
        
        // Infrastructure items
        if (designation.contains("infrastructure") || designation.contains("installation") ||
            designation.contains("facility") || designation.contains("bâtiment") ||
            designation.contains("building") || designation.contains("بنية تحتية") ||
            designation.contains("منشأة")) {
            return "INFRASTRUCTURE_ITEM";
        }
        
        // Technology items
        if (designation.contains("technologie") || designation.contains("technology") ||
            designation.contains("logiciel") || designation.contains("software") ||
            designation.contains("système") || designation.contains("system") ||
            designation.contains("تكنولوجيا") || designation.contains("برمجيات")) {
            return "TECHNOLOGY_ITEM";
        }
        
        // Consumable items
        if (designation.contains("consommable") || designation.contains("consumable") ||
            designation.contains("fourniture") || designation.contains("supply") ||
            designation.contains("carburant") || designation.contains("fuel") ||
            designation.contains("مستهلكات") || designation.contains("وقود")) {
            return "CONSUMABLE_ITEM";
        }
        
        // Training items
        if (designation.contains("formation") || designation.contains("training") ||
            designation.contains("cours") || designation.contains("course") ||
            designation.contains("apprentissage") || designation.contains("learning") ||
            designation.contains("تدريب") || designation.contains("دورة")) {
            return "TRAINING_ITEM";
        }
        
        // Document items
        if (designation.contains("document") || designation.contains("manuel") ||
            designation.contains("manual") || designation.contains("guide") ||
            designation.contains("procédure") || designation.contains("procedure") ||
            designation.contains("وثيقة") || designation.contains("دليل")) {
            return "DOCUMENT_ITEM";
        }
        
        // Vehicle items
        if (designation.contains("véhicule") || designation.contains("vehicle") ||
            designation.contains("transport") || designation.contains("automobile") ||
            designation.contains("camion") || designation.contains("truck") ||
            designation.contains("مركبة") || designation.contains("سيارة")) {
            return "VEHICLE_ITEM";
        }
        
        return "GENERAL_ITEM";
    }

    /**
     * Get item priority level based on category
     */
    public String getItemPriority() {
        String category = getItemCategory();
        
        switch (category) {
            case "EQUIPMENT_ITEM":
            case "INFRASTRUCTURE_ITEM":
                return "HIGH_PRIORITY";
            case "RESOURCE_ITEM":
            case "TECHNOLOGY_ITEM":
                return "HIGH_PRIORITY";
            case "SERVICE_ITEM":
            case "VEHICLE_ITEM":
                return "MEDIUM_PRIORITY";
            case "TRAINING_ITEM":
            case "DOCUMENT_ITEM":
                return "NORMAL_PRIORITY";
            case "CONSUMABLE_ITEM":
                return "LOW_PRIORITY";
            default:
                return "NORMAL_PRIORITY";
        }
    }

    /**
     * Get item lifecycle stage
     */
    public String getLifecycleStage() {
        String category = getItemCategory();
        
        switch (category) {
            case "EQUIPMENT_ITEM":
            case "INFRASTRUCTURE_ITEM":
            case "VEHICLE_ITEM":
                return "ASSET_LIFECYCLE";
            case "TECHNOLOGY_ITEM":
                return "TECHNOLOGY_LIFECYCLE";
            case "RESOURCE_ITEM":
                return "RESOURCE_LIFECYCLE";
            case "SERVICE_ITEM":
                return "SERVICE_LIFECYCLE";
            case "TRAINING_ITEM":
                return "TRAINING_LIFECYCLE";
            case "CONSUMABLE_ITEM":
                return "CONSUMPTION_LIFECYCLE";
            case "DOCUMENT_ITEM":
                return "DOCUMENT_LIFECYCLE";
            default:
                return "GENERAL_LIFECYCLE";
        }
    }

    /**
     * Check if item has planned items
     */
    public boolean hasPlannedItems() {
        return plannedItemsCount != null && plannedItemsCount > 0;
    }

    /**
     * Get planned items count (safe)
     */
    public int getPlannedItemsCountSafe() {
        return plannedItemsCount != null ? plannedItemsCount : 0;
    }

    /**
     * Get item planning complexity based on planned items count
     */
    public String getPlanningComplexity() {
        int count = getPlannedItemsCountSafe();
        
        if (count == 0) {
            return "NO_PLANNING";
        } else if (count <= 2) {
            return "SIMPLE_PLANNING";
        } else if (count <= 5) {
            return "MODERATE_PLANNING";
        } else if (count <= 10) {
            return "COMPLEX_PLANNING";
        } else {
            return "VERY_COMPLEX_PLANNING";
        }
    }

    /**
     * Get short display for lists
     */
    public String getShortDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDisplayText());
        
        if (hasPlannedItems()) {
            sb.append(" (").append(getPlannedItemsCountSafe()).append(" planned)");
        }
        
        if (rubric != null) {
            sb.append(" - ").append(rubric.getDisplayText());
        }
        
        return sb.toString();
    }

    /**
     * Get full display with all available information
     */
    public String getFullDisplay() {
        StringBuilder sb = new StringBuilder();
        
        if (designationFr != null && !designationFr.trim().isEmpty()) {
            sb.append(designationFr);
        }
        
        if (designationEn != null && !designationEn.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(" / ");
            sb.append(designationEn);
        }
        
        if (designationAr != null && !designationAr.trim().isEmpty()) {
            if (sb.length() > 0) sb.append(" / ");
            sb.append(designationAr);
        }
        
        return sb.toString();
    }

    /**
     * Get item display with category and planning info
     */
    public String getItemDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDisplayText());
        
        String category = getItemCategory();
        if (!"GENERAL_ITEM".equals(category)) {
            sb.append(" - ").append(category.replace("_", " "));
        }
        
        if (hasPlannedItems()) {
            sb.append(" [").append(getPlannedItemsCountSafe()).append(" planned]");
        }
        
        String complexity = getPlanningComplexity();
        if (!"NO_PLANNING".equals(complexity)) {
            sb.append(" (").append(complexity.replace("_", " ")).append(")");
        }
        
        return sb.toString();
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static ItemDTO createSimple(Long id, String designationFr, Long rubricId, Integer plannedItemsCount) {
        return ItemDTO.builder()
                .id(id)
                .designationFr(designationFr)
                .rubricId(rubricId)
                .plannedItemsCount(plannedItemsCount)
                .build();
    }

    /**
     * Validate required fields are present
     */
    public boolean isValid() {
        return designationFr != null && !designationFr.trim().isEmpty() && 
               rubricId != null;
    }

    /**
     * Get validation errors
     */
    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<>();
        
        if (designationFr == null || designationFr.trim().isEmpty()) {
            errors.add("French designation is required");
        }
        
        if (rubricId == null) {
            errors.add("Rubric is required");
        }
        
        return errors;
    }

    /**
     * Get comparison key for sorting (by rubric, then by designation)
     */
    public String getComparisonKey() {
        String rubricName = rubric != null ? rubric.getDisplayText() : "zzz";
        String itemName = getDisplayText().toLowerCase();
        return rubricName.toLowerCase() + "_" + itemName;
    }

    /**
     * Get formal display for official documents
     */
    public String getFormalDisplay() {
        StringBuilder sb = new StringBuilder();
        
        if (designationFr != null && !designationFr.trim().isEmpty()) {
            sb.append(designationFr);
        }
        
        if (rubric != null) {
            sb.append(" - ").append(rubric.getFormalDisplay());
        }
        
        return sb.toString();
    }

    /**
     * Get item classification for reports
     */
    public String getItemClassification() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Item: ").append(getDisplayText()).append("\n");
        sb.append("Rubric: ").append(rubric != null ? rubric.getDisplayText() : "N/A").append("\n");
        sb.append("Category: ").append(getItemCategory().replace("_", " ")).append("\n");
        sb.append("Priority: ").append(getItemPriority().replace("_", " ")).append("\n");
        sb.append("Lifecycle Stage: ").append(getLifecycleStage().replace("_", " ")).append("\n");
        sb.append("Planned Items Count: ").append(getPlannedItemsCountSafe()).append("\n");
        sb.append("Planning Complexity: ").append(getPlanningComplexity().replace("_", " ")).append("\n");
        sb.append("Has Planned Items: ").append(hasPlannedItems() ? "Yes" : "No").append("\n");
        sb.append("Multilingual: ").append(isMultilingual() ? "Yes" : "No");
        
        return sb.toString();
    }

    /**
     * Get item usage context
     */
    public String getItemUsageContext() {
        String category = getItemCategory();
        
        return switch (category) {
            case "EQUIPMENT_ITEM" -> "Physical equipment, tools, and machinery for operational use";
            case "RESOURCE_ITEM" -> "Human resources, personnel, and staffing requirements";
            case "SERVICE_ITEM" -> "Services, maintenance, support, and assistance provisions";
            case "INFRASTRUCTURE_ITEM" -> "Buildings, facilities, and infrastructure components";
            case "TECHNOLOGY_ITEM" -> "Software, systems, and technological solutions";
            case "CONSUMABLE_ITEM" -> "Consumables, supplies, fuel, and expendable materials";
            case "TRAINING_ITEM" -> "Training programs, courses, and educational content";
            case "DOCUMENT_ITEM" -> "Documentation, manuals, guides, and procedures";
            case "VEHICLE_ITEM" -> "Vehicles, transportation, and mobility assets";
            default -> "General item for organizational use";
        };
    }

    /**
     * Get item management requirements
     */
    public String getManagementRequirements() {
        String priority = getItemPriority();
        String complexity = getPlanningComplexity();
        
        StringBuilder sb = new StringBuilder();
        
        // Priority-based requirements
        switch (priority) {
            case "HIGH_PRIORITY":
                sb.append("Executive oversight, Strategic planning, Resource allocation");
                break;
            case "MEDIUM_PRIORITY":
                sb.append("Management oversight, Operational planning, Standard resources");
                break;
            case "NORMAL_PRIORITY":
                sb.append("Supervisor oversight, Basic planning, Standard resources");
                break;
            default:
                sb.append("Standard oversight, Routine planning, Basic resources");
        }
        
        // Complexity-based additional requirements
        switch (complexity) {
            case "VERY_COMPLEX_PLANNING":
            case "COMPLEX_PLANNING":
                sb.append(", Specialized planning expertise, Coordination mechanisms");
                break;
            case "MODERATE_PLANNING":
                sb.append(", Planning coordination, Standard planning tools");
                break;
            case "SIMPLE_PLANNING":
                sb.append(", Basic planning, Standard procedures");
                break;
        }
        
        return sb.toString();
    }

    /**
     * Get item procurement strategy
     */
    public String getProcurementStrategy() {
        String category = getItemCategory();
        
        return switch (category) {
            case "EQUIPMENT_ITEM" -> "CAPITAL_PROCUREMENT";
            case "INFRASTRUCTURE_ITEM" -> "CONSTRUCTION_CONTRACT";
            case "TECHNOLOGY_ITEM" -> "SOFTWARE_LICENSING";
            case "RESOURCE_ITEM" -> "STAFFING_CONTRACT";
            case "SERVICE_ITEM" -> "SERVICE_CONTRACT";
            case "VEHICLE_ITEM" -> "FLEET_PROCUREMENT";
            case "CONSUMABLE_ITEM" -> "SUPPLY_CHAIN_MANAGEMENT";
            case "TRAINING_ITEM" -> "TRAINING_CONTRACT";
            case "DOCUMENT_ITEM" -> "DOCUMENTATION_DEVELOPMENT";
            default -> "STANDARD_PROCUREMENT";
        };
    }

    /**
     * Get item maintenance requirements
     */
    public String getMaintenanceRequirements() {
        String category = getItemCategory();
        
        return switch (category) {
            case "EQUIPMENT_ITEM" -> "Regular maintenance, Calibration, Repair services";
            case "INFRASTRUCTURE_ITEM" -> "Facility maintenance, Safety inspections, Upgrades";
            case "TECHNOLOGY_ITEM" -> "Software updates, Security patches, Technical support";
            case "VEHICLE_ITEM" -> "Preventive maintenance, Inspections, Repairs";
            case "SERVICE_ITEM" -> "Service level monitoring, Contract management";
            case "RESOURCE_ITEM" -> "Training updates, Performance reviews, Skills development";
            case "TRAINING_ITEM" -> "Content updates, Delivery improvements, Effectiveness review";
            case "DOCUMENT_ITEM" -> "Version control, Regular updates, Accuracy validation";
            case "CONSUMABLE_ITEM" -> "Inventory monitoring, Replenishment, Quality control";
            default -> "Standard maintenance and monitoring";
        };
    }

    /**
     * Get item success metrics
     */
    public String getSuccessMetrics() {
        String category = getItemCategory();
        
        StringBuilder sb = new StringBuilder();
        
        // Category-specific metrics
        switch (category) {
            case "EQUIPMENT_ITEM":
                sb.append("Availability rate, Performance efficiency, Maintenance cost");
                break;
            case "RESOURCE_ITEM":
                sb.append("Utilization rate, Performance quality, Skill level");
                break;
            case "SERVICE_ITEM":
                sb.append("Service level compliance, Response time, Quality score");
                break;
            case "INFRASTRUCTURE_ITEM":
                sb.append("Occupancy rate, Operational status, Safety compliance");
                break;
            case "TECHNOLOGY_ITEM":
                sb.append("System availability, Performance metrics, User satisfaction");
                break;
            case "CONSUMABLE_ITEM":
                sb.append("Consumption efficiency, Waste reduction, Cost optimization");
                break;
            case "TRAINING_ITEM":
                sb.append("Completion rate, Knowledge retention, Skill improvement");
                break;
            case "DOCUMENT_ITEM":
                sb.append("Usage frequency, Accuracy level, Update currency");
                break;
            case "VEHICLE_ITEM":
                sb.append("Operational readiness, Fuel efficiency, Maintenance cost");
                break;
            default:
                sb.append("Utilization rate, Quality measures, Cost effectiveness");
        }
        
        // Add planning-based metrics
        if (hasPlannedItems()) {
            sb.append(", Planning accuracy, Execution rate, Schedule adherence");
        }
        
        return sb.toString();
    }

    /**
     * Get item risk factors
     */
    public String getRiskFactors() {
        String priority = getItemPriority();
        String complexity = getPlanningComplexity();
        
        StringBuilder sb = new StringBuilder();
        
        // Priority-based risks
        switch (priority) {
            case "HIGH_PRIORITY":
                sb.append("Critical impact on operations, High replacement cost");
                break;
            case "MEDIUM_PRIORITY":
                sb.append("Moderate operational impact, Standard replacement risk");
                break;
            case "NORMAL_PRIORITY":
                sb.append("Limited operational impact, Low replacement risk");
                break;
            default:
                sb.append("Minimal operational risk");
        }
        
        // Complexity-based risks
        switch (complexity) {
            case "VERY_COMPLEX_PLANNING":
            case "COMPLEX_PLANNING":
                sb.append(", High planning coordination risk, Resource dependencies");
                break;
            case "MODERATE_PLANNING":
                sb.append(", Moderate planning risk, Standard coordination needs");
                break;
            case "SIMPLE_PLANNING":
                sb.append(", Low planning risk, Minimal coordination requirements");
                break;
        }
        
        return sb.toString();
    }

    /**
     * Get item lifecycle management
     */
    public String getLifecycleManagement() {
        String stage = getLifecycleStage();
        
        return switch (stage) {
            case "ASSET_LIFECYCLE" -> "Acquisition, Deployment, Operation, Maintenance, Disposal";
            case "TECHNOLOGY_LIFECYCLE" -> "Development, Implementation, Operation, Updates, Retirement";
            case "RESOURCE_LIFECYCLE" -> "Recruitment, Training, Deployment, Development, Transition";
            case "SERVICE_LIFECYCLE" -> "Contract, Delivery, Monitoring, Review, Renewal";
            case "TRAINING_LIFECYCLE" -> "Design, Development, Delivery, Assessment, Update";
            case "CONSUMPTION_LIFECYCLE" -> "Procurement, Storage, Distribution, Usage, Replenishment";
            case "DOCUMENT_LIFECYCLE" -> "Creation, Review, Approval, Distribution, Maintenance";
            default -> "Planning, Implementation, Operation, Review, Update";
        };
    }
}