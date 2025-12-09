/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ItemDistributionDTO
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.common.administration.dto.StructureDTO;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ItemDistribution Data Transfer Object
 * Maps exactly to ItemDistribution model fields: F_00=id, F_01=quantity, F_02=plannedItemId, F_03=structureId
 * Includes many-to-one relationships with PlannedItem and Structure
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDistributionDTO {

    private Long id; // F_00

    @DecimalMin(value = "0.0", inclusive = false, message = "Quantity must be positive")
    private Float quantity; // F_01

    @NotNull(message = "Planned item is required")
    private Long plannedItemId; // F_02 - PlannedItem foreign key (required)

    @NotNull(message = "Structure is required")
    private Long structureId; // F_03 - Structure foreign key (required)

    // Related entities (populated when needed)
    private PlannedItemDTO plannedItem; // Many-to-one relationship
    private StructureDTO structure; // Many-to-one relationship

    /**
     * Create DTO from entity
     */
    public static ItemDistributionDTO fromEntity(dz.mdn.iaas.business.plan.model.ItemDistribution itemDistribution) {
        if (itemDistribution == null) return null;
        
        ItemDistributionDTO.ItemDistributionDTOBuilder builder = ItemDistributionDTO.builder()
                .id(itemDistribution.getId())
                .quantity(itemDistribution.getQuantity());

        // Handle foreign key relationships
        if (itemDistribution.getPlannedItem() != null) {
            builder.plannedItemId(itemDistribution.getPlannedItem().getId());
        }
        if (itemDistribution.getStructure() != null) {
            builder.structureId(itemDistribution.getStructure().getId());
        }

        return builder.build();
    }

    /**
     * Create DTO from entity with related objects
     */
    public static ItemDistributionDTO fromEntityWithRelations(dz.mdn.iaas.business.plan.model.ItemDistribution itemDistribution) {
        ItemDistributionDTO dto = fromEntity(itemDistribution);
        if (dto == null) return null;

        // Populate related DTOs
        if (itemDistribution.getPlannedItem() != null) {
            dto.setPlannedItem(PlannedItemDTO.fromEntity(itemDistribution.getPlannedItem()));
        }
        if (itemDistribution.getStructure() != null) {
            dto.setStructure(StructureDTO.fromEntity(itemDistribution.getStructure()));
        }

        return dto;
    }

    /**
     * Get display text for the item distribution
     */
    public String getDisplayText() {
        StringBuilder sb = new StringBuilder();
        
        if (quantity != null) {
            sb.append("Qty: ").append(quantity);
        }
        
        if (plannedItem != null) {
            if (sb.length() > 0) sb.append(" of ");
            sb.append(plannedItem.getDisplayText());
        }
        
        if (structure != null) {
            if (sb.length() > 0) sb.append(" to ");
            sb.append(structure.getDisplayText());
        }
        
        return sb.length() > 0 ? sb.toString() : "Distribution #" + (id != null ? id : "N/A");
    }

    /**
     * Get distribution category based on quantity
     */
    public String getDistributionCategory() {
        if (quantity == null || quantity <= 0) {
            return "NO_DISTRIBUTION";
        } else if (quantity <= 1) {
            return "UNIT_DISTRIBUTION";
        } else if (quantity <= 10) {
            return "SMALL_DISTRIBUTION";
        } else if (quantity <= 50) {
            return "MEDIUM_DISTRIBUTION";
        } else if (quantity <= 100) {
            return "LARGE_DISTRIBUTION";
        } else {
            return "BULK_DISTRIBUTION";
        }
    }

    /**
     * Get distribution status based on various factors
     */
    public String getDistributionStatus() {
        if (quantity == null || quantity <= 0) {
            return "INVALID_DISTRIBUTION";
        } else if (plannedItem == null || structure == null) {
            return "INCOMPLETE_DISTRIBUTION";
        } else {
            return "READY_FOR_DISTRIBUTION";
        }
    }

    /**
     * Calculate distribution percentage of total planned quantity
     */
    public Double getDistributionPercentage() {
        if (quantity != null && plannedItem != null && plannedItem.getPlanedQuantity() != null && plannedItem.getPlanedQuantity() > 0) {
            return BigDecimal.valueOf(quantity)
                    .divide(BigDecimal.valueOf(plannedItem.getPlanedQuantity()), 4, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100))
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
        }
        return 0.0;
    }

    /**
     * Calculate distribution cost (quantity * unit cost)
     */
    public Double getDistributionCost() {
        if (quantity != null && plannedItem != null && plannedItem.getUnitairCost() != null) {
            return BigDecimal.valueOf(quantity)
                    .multiply(BigDecimal.valueOf(plannedItem.getUnitairCost()))
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
        }
        return 0.0;
    }

    /**
     * Get distribution complexity level
     */
    public String getDistributionComplexity() {
        String category = getDistributionCategory();
        
        switch (category) {
            case "BULK_DISTRIBUTION":return "HIGH_COMPLEXITY";
            case "LARGE_DISTRIBUTION": return "MEDIUM_COMPLEXITY";
            case "MEDIUM_DISTRIBUTION": return "LOW_COMPLEXITY";
            case "SMALL_DISTRIBUTION":
            case "UNIT_DISTRIBUTION": return "LOW_COMPLEXITY";
            default:return "MINIMAL_COMPLEXITY";
        }
    }

    /**
     * Get logistics requirements
     */
    public String getLogisticsRequirements() {
        String category = getDistributionCategory();
        String complexity = getDistributionComplexity();
        
        StringBuilder sb = new StringBuilder();
        
        // Category-based requirements
        switch (category) {
            case "BULK_DISTRIBUTION":
                sb.append("Heavy transport, Large storage, Specialized handling");
                break;
            case "LARGE_DISTRIBUTION":
                sb.append("Standard transport, Adequate storage, Standard handling");
                break;
            case "MEDIUM_DISTRIBUTION":
                sb.append("Regular transport, Standard storage, Basic handling");
                break;
            case "SMALL_DISTRIBUTION":
            case "UNIT_DISTRIBUTION":
                sb.append("Light transport, Minimal storage, Simple handling");
                break;
            default:
                sb.append("No specific requirements");
        }
        
        // Complexity-based additional requirements
        if ("VERY_HIGH_COMPLEXITY".equals(complexity) || "HIGH_COMPLEXITY".equals(complexity)) {
            sb.append(", Coordination protocols, Specialized logistics");
        } else if ("MEDIUM_COMPLEXITY".equals(complexity)) {
            sb.append(", Standard coordination, Regular logistics");
        }
        
        return sb.toString();
    }

    /**
     * Get short display for lists
     */
    public String getShortDisplay() {
        StringBuilder sb = new StringBuilder();
        
        if (quantity != null) {
            sb.append(quantity);
        }
        
        if (plannedItem != null) {
            if (sb.length() > 0) sb.append(" × ");
            String itemName = plannedItem.getDisplayText();
            if (itemName.length() > 30) {
                itemName = itemName.substring(0, 27) + "...";
            }
            sb.append(itemName);
        }
        
        if (structure != null) {
            sb.append(" → ").append(structure.getShortDisplay());
        }
        
        return sb.toString();
    }

    /**
     * Get full display with all available information
     */
    public String getFullDisplay() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Distribution: ");
        
        if (quantity != null) {
            sb.append(quantity).append(" units");
        }
        
        if (plannedItem != null) {
            sb.append(" of ").append(plannedItem.getDisplayText());
        }
        
        if (structure != null) {
            sb.append(" to ").append(structure.getDisplayText());
        }
        
        Double percentage = getDistributionPercentage();
        if (percentage > 0) {
            sb.append(" (").append(String.format("%.1f%%", percentage)).append(" of total)");
        }
        
        Double cost = getDistributionCost();
        if (cost > 0) {
            sb.append(" Cost: ").append(String.format("%.2f", cost));
        }
        
        return sb.toString();
    }

    /**
     * Get distribution display with analysis
     */
    public String getDistributionDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDisplayText());
        
        String category = getDistributionCategory();
        sb.append(" [").append(category.replace("_", " ")).append("]");
        
        return sb.toString();
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static ItemDistributionDTO createSimple(Long id, Float quantity, String plannedItemName, String structureName) {
        return ItemDistributionDTO.builder()
                .id(id)
                .quantity(quantity)
                .build();
    }

    /**
     * Validate required fields are present
     */
    public boolean isValid() {
        return plannedItemId != null && structureId != null && 
               quantity != null && quantity > 0;
    }

    /**
     * Get validation errors
     */
    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<>();
        
        if (plannedItemId == null) {
            errors.add("Planned item is required");
        }
        
        if (structureId == null) {
            errors.add("Structure is required");
        }
        
        if (quantity == null || quantity <= 0) {
            errors.add("Quantity must be positive");
        }
        
        // Business validation: Check if quantity exceeds planned quantity
        if (quantity != null && plannedItem != null && plannedItem.getPlanedQuantity() != null) {
            if (quantity > plannedItem.getPlanedQuantity()) {
                errors.add("Distribution quantity cannot exceed planned quantity");
            }
        }
        
        return errors;
    }

    /**
     * Get comparison key for sorting
     */
    public String getComparisonKey() {
        String structureName = structure != null ? structure.getDisplayText() : "zzz";
        String plannedItemName = plannedItem != null ? plannedItem.getDisplayText() : "zzz";
        return structureName.toLowerCase() + "_" + plannedItemName.toLowerCase();
    }

    /**
     * Get formal display for official documents
     */
    public String getFormalDisplay() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Item Distribution");
        
        if (id != null) {
            sb.append(" (ID: ").append(id).append(")");
        }
        
        if (quantity != null) {
            sb.append(" - Quantity: ").append(quantity);
        }
        
        if (plannedItem != null) {
            sb.append(" - Item: ").append(plannedItem.getFormalDisplay());
        }
        
        if (structure != null) {
            sb.append(" - Destination: ").append(structure.getFullDisplay());
        }
        
        return sb.toString();
    }

    /**
     * Get item distribution classification for reports
     */
    public String getItemDistributionClassification() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Item Distribution: ").append(getDisplayText()).append("\n");
        sb.append("Planned Item: ").append(plannedItem != null ? plannedItem.getDisplayText() : "N/A").append("\n");
        sb.append("Structure: ").append(structure != null ? structure.getDisplayText() : "N/A").append("\n");
        sb.append("Quantity: ").append(quantity != null ? quantity.toString() : "N/A").append("\n");
        sb.append("Distribution Category: ").append(getDistributionCategory().replace("_", " ")).append("\n");
        sb.append("Distribution Status: ").append(getDistributionStatus().replace("_", " ")).append("\n");
        sb.append("Distribution Percentage: ").append(String.format("%.1f%%", getDistributionPercentage())).append("\n");
        sb.append("Distribution Cost: ").append(String.format("%.2f", getDistributionCost())).append("\n");
        sb.append("Distribution Complexity: ").append(getDistributionComplexity().replace("_", " "));
        
        return sb.toString();
    }

    /**
     * Get distribution planning requirements
     */
    public String getDistributionPlanningRequirements() {
        String complexity = getDistributionComplexity();
        
        StringBuilder sb = new StringBuilder();
        
        // Complexity-based additional requirements
        switch (complexity) {
            case "VERY_HIGH_COMPLEXITY":
            case "HIGH_COMPLEXITY":
                sb.append(", Detailed coordination, Specialized planning, Risk assessment");
                break;
            case "MEDIUM_COMPLEXITY":
                sb.append(", Moderate coordination, Standard planning");
                break;
        }
        
        return sb.toString();
    }

    /**
     * Get distribution execution timeline
     */
    public String getDistributionTimeline() {
        String category = getDistributionCategory();
        
        StringBuilder sb = new StringBuilder();
        
        // Category-based adjustments
        if ("BULK_DISTRIBUTION".equals(category)) {
            sb.append(", Phased delivery, Extended logistics");
        } else if ("LARGE_DISTRIBUTION".equals(category)) {
            sb.append(", Coordinated delivery, Standard logistics");
        }
        
        return sb.toString();
    }

    /**
     * Get distribution risk assessment
     */
    public String getDistributionRiskAssessment() {
        String complexity = getDistributionComplexity();
        String category = getDistributionCategory();
        
        StringBuilder sb = new StringBuilder();
        
        // Complexity-based risks
        switch (complexity) {
            case "VERY_HIGH_COMPLEXITY":
                sb.append("High coordination risk, Resource strain, Logistics challenges");
                break;
            case "HIGH_COMPLEXITY":
                sb.append("Moderate coordination risk, Resource dependencies, Standard logistics");
                break;
            case "MEDIUM_COMPLEXITY":
                sb.append("Standard coordination risk, Normal resource needs");
                break;
            default:
                sb.append("Low risk, Standard execution");
        }
        
        // Category-based additional risks
        if ("BULK_DISTRIBUTION".equals(category)) {
            sb.append(", Handling difficulties, Storage constraints");
        } else if ("LARGE_DISTRIBUTION".equals(category)) {
            sb.append(", Moderate handling requirements");
        }
        
        return sb.toString();
    }

    /**
     * Get distribution success metrics
     */
    public String getDistributionSuccessMetrics() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Delivery accuracy, Timeline adherence, Quality preservation");
        
        String category = getDistributionCategory();
        if ("BULK_DISTRIBUTION".equals(category) || "LARGE_DISTRIBUTION".equals(category)) {
            sb.append(", Logistics efficiency, Resource utilization");
        }
        
        sb.append(", Cost effectiveness, Damage prevention");
        
        return sb.toString();
    }

    /**
     * Get distribution control measures
     */
    public String getDistributionControlMeasures() {
        String complexity = getDistributionComplexity();
        
        StringBuilder sb = new StringBuilder();
        
        // Complexity-based additional controls
        if ("VERY_HIGH_COMPLEXITY".equals(complexity) || "HIGH_COMPLEXITY".equals(complexity)) {
            sb.append(", Coordination checkpoints, Quality assurance, Risk mitigation");
        }
        
        return sb.toString();
    }
}