/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PlannedItemDTO
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.ArrayList;

/**
 * PlannedItem Data Transfer Object
 * Maps exactly to PlannedItem model fields: F_00=id, F_01=designation, F_02=unitairCost, F_03=planedQuantity, 
 * F_04=allocatedAmount, F_05=itemStatusId, F_06=itemId, F_07=financialOperationId, F_08=budgetModificationId
 * Includes multiple many-to-one relationships and one-to-many relationship with ItemDistributions
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlannedItemDTO {

    private Long id; // F_00

    @NotBlank(message = "Designation is required")
    @Size(max = 200, message = "Designation must not exceed 200 characters")
    private String designation; // F_01 - required

    @DecimalMin(value = "0.0", inclusive = false, message = "Unit cost must be positive")
    private Double unitairCost; // F_02

    @DecimalMin(value = "0.0", inclusive = false, message = "Planned quantity must be positive")
    private Double planedQuantity; // F_03

    @DecimalMin(value = "0.0", inclusive = true, message = "Allocated amount must be non-negative")
    private Double allocatedAmount; // F_04

    @NotNull(message = "Item status is required")
    private Long itemStatusId; // F_05 - ItemStatus foreign key (required)

    @NotNull(message = "Item is required")
    private Long itemId; // F_06 - Item foreign key (required)

    @NotNull(message = "Financial operation is required")
    private Long financialOperationId; // F_07 - FinancialOperation foreign key (required)

    private Long budgetModificationId; // F_08 - BudgetModification foreign key (optional)

    // Related entities (populated when needed)
    private ItemStatusDTO itemStatus; // Many-to-one relationship
    private ItemDTO item; // Many-to-one relationship
    private FinancialOperationDTO financialOperation; // Many-to-one relationship
    private BudgetModificationDTO budgetModification; // Many-to-one relationship
    private List<ItemDistributionDTO> itemDistributions; // One-to-many relationship

    // Summary information
    private Integer itemDistributionsCount;

    /**
     * Create DTO from entity
     */
    public static PlannedItemDTO fromEntity(dz.mdn.iaas.business.plan.model.PlannedItem plannedItem) {
        if (plannedItem == null) return null;
        
        PlannedItemDTO.PlannedItemDTOBuilder builder = PlannedItemDTO.builder()
                .id(plannedItem.getId())
                .designation(plannedItem.getDesignation())
                .unitairCost(plannedItem.getUnitairCost())
                .planedQuantity(plannedItem.getPlanedQuantity())
                .allocatedAmount(plannedItem.getAllocatedAmount());

        // Handle foreign key relationships
        if (plannedItem.getItemStatus() != null) {
            builder.itemStatusId(plannedItem.getItemStatus().getId());
        }
        if (plannedItem.getItem() != null) {
            builder.itemId(plannedItem.getItem().getId());
        }
        if (plannedItem.getFinancialOperation() != null) {
            builder.financialOperationId(plannedItem.getFinancialOperation().getId());
        }
        if (plannedItem.getBudgetModification() != null) {
            builder.budgetModificationId(plannedItem.getBudgetModification().getId());
        }

        // Add item distributions count if available
        if (plannedItem.getItemDistribution() != null) {
            builder.itemDistributionsCount(plannedItem.getItemDistribution().size());
        }

        return builder.build();
    }

    /**
     * Create DTO from entity with related objects
     */
    public static PlannedItemDTO fromEntityWithRelations(dz.mdn.iaas.business.plan.model.PlannedItem plannedItem) {
        PlannedItemDTO dto = fromEntity(plannedItem);
        if (dto == null) return null;

        // Populate related DTOs
        if (plannedItem.getItemStatus() != null) {
            dto.setItemStatus(ItemStatusDTO.fromEntity(plannedItem.getItemStatus()));
        }
        if (plannedItem.getItem() != null) {
            dto.setItem(ItemDTO.fromEntity(plannedItem.getItem()));
        }
        if (plannedItem.getFinancialOperation() != null) {
            dto.setFinancialOperation(FinancialOperationDTO.fromEntity(plannedItem.getFinancialOperation()));
        }
        if (plannedItem.getBudgetModification() != null) {
            dto.setBudgetModification(BudgetModificationDTO.fromEntity(plannedItem.getBudgetModification()));
        }

        if (plannedItem.getItemDistribution() != null && !plannedItem.getItemDistribution().isEmpty()) {
            List<ItemDistributionDTO> itemDistributionDTOs = new ArrayList<>();
            for (var itemDistribution : plannedItem.getItemDistribution()) {
                itemDistributionDTOs.add(ItemDistributionDTO.fromEntity(itemDistribution));
            }
            dto.setItemDistributions(itemDistributionDTOs);
            dto.setItemDistributionsCount(itemDistributionDTOs.size());
        }

        return dto;
    }

    /**
     * Get display text for the planned item
     */
    public String getDisplayText() {
        return designation != null ? designation : "N/A";
    }

    /**
     * Calculate total cost (unitairCost * planedQuantity)
     */
    public Double getTotalCost() {
        if (unitairCost != null && planedQuantity != null) {
            return BigDecimal.valueOf(unitairCost)
                    .multiply(BigDecimal.valueOf(planedQuantity))
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
        }
        return 0.0;
    }

    /**
     * Calculate variance between allocated amount and total cost
     */
    public Double getVariance() {
        if (allocatedAmount != null && getTotalCost() != null) {
            return BigDecimal.valueOf(allocatedAmount)
                    .subtract(BigDecimal.valueOf(getTotalCost()))
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
        }
        return 0.0;
    }

    /**
     * Get budget utilization percentage
     */
    public Double getBudgetUtilization() {
        if (allocatedAmount != null && allocatedAmount > 0 && getTotalCost() != null) {
            return BigDecimal.valueOf(getTotalCost())
                    .divide(BigDecimal.valueOf(allocatedAmount), 4, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100))
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
        }
        return 0.0;
    }

    /**
     * Get planning status based on financial metrics
     */
    public String getPlanningStatus() {
        Double utilization = getBudgetUtilization();
        
        if (utilization == null || utilization == 0) {
            return "NOT_PLANNED";
        } else if (utilization <= 50) {
            return "UNDER_PLANNED";
        } else if (utilization <= 100) {
            return "WELL_PLANNED";
        } else if (utilization <= 120) {
            return "OVER_PLANNED";
        } else {
            return "SIGNIFICANTLY_OVER_PLANNED";
        }
    }

    /**
     * Get cost category based on unit cost
     */
    public String getCostCategory() {
        if (unitairCost == null || unitairCost == 0) {
            return "NO_COST";
        } else if (unitairCost <= 100) {
            return "LOW_COST";
        } else if (unitairCost <= 1000) {
            return "MEDIUM_COST";
        } else if (unitairCost <= 10000) {
            return "HIGH_COST";
        } else {
            return "VERY_HIGH_COST";
        }
    }

    /**
     * Get quantity scale based on planned quantity
     */
    public String getQuantityScale() {
        if (planedQuantity == null || planedQuantity == 0) {
            return "NO_QUANTITY";
        } else if (planedQuantity <= 10) {
            return "SMALL_SCALE";
        } else if (planedQuantity <= 100) {
            return "MEDIUM_SCALE";
        } else if (planedQuantity <= 1000) {
            return "LARGE_SCALE";
        } else {
            return "VERY_LARGE_SCALE";
        }
    }

    /**
     * Check if item has distributions
     */
    public boolean hasItemDistributions() {
        return itemDistributionsCount != null && itemDistributionsCount > 0;
    }

    /**
     * Get item distributions count (safe)
     */
    public int getItemDistributionsCountSafe() {
        return itemDistributionsCount != null ? itemDistributionsCount : 0;
    }

    /**
     * Get distribution complexity based on distributions count
     */
    public String getDistributionComplexity() {
        int count = getItemDistributionsCountSafe();
        
        if (count == 0) {
            return "NO_DISTRIBUTION";
        } else if (count == 1) {
            return "SIMPLE_DISTRIBUTION";
        } else if (count <= 5) {
            return "MODERATE_DISTRIBUTION";
        } else if (count <= 10) {
            return "COMPLEX_DISTRIBUTION";
        } else {
            return "VERY_COMPLEX_DISTRIBUTION";
        }
    }

    /**
     * Get short display for lists
     */
    public String getShortDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDisplayText());
        
        if (planedQuantity != null && planedQuantity > 0) {
            sb.append(" (Qty: ").append(planedQuantity).append(")");
        }
        
        if (hasItemDistributions()) {
            sb.append(" [").append(getItemDistributionsCountSafe()).append(" distributions]");
        }
        
        return sb.toString();
    }

    /**
     * Get full display with financial information
     */
    public String getFullDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDisplayText());
        
        if (unitairCost != null && planedQuantity != null) {
            sb.append(" - Unit: ").append(String.format("%.2f", unitairCost));
            sb.append(", Qty: ").append(planedQuantity);
            sb.append(", Total: ").append(String.format("%.2f", getTotalCost()));
        }
        
        if (allocatedAmount != null) {
            sb.append(", Allocated: ").append(String.format("%.2f", allocatedAmount));
        }
        
        return sb.toString();
    }

    /**
     * Get financial display with budget analysis
     */
    public String getFinancialDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDisplayText());
        
        Double totalCost = getTotalCost();
        Double variance = getVariance();
        Double utilization = getBudgetUtilization();
        
        sb.append(" - Total Cost: ").append(String.format("%.2f", totalCost));
        
        if (allocatedAmount != null) {
            sb.append(", Allocated: ").append(String.format("%.2f", allocatedAmount));
            sb.append(", Variance: ").append(String.format("%.2f", variance));
            sb.append(", Utilization: ").append(String.format("%.1f%%", utilization));
        }
        
        return sb.toString();
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static PlannedItemDTO createSimple(Long id, String designation, Double totalCost, Integer itemDistributionsCount) {
        return PlannedItemDTO.builder()
                .id(id)
                .designation(designation)
                .itemDistributionsCount(itemDistributionsCount)
                .build();
    }

    /**
     * Validate required fields are present
     */
    public boolean isValid() {
        return designation != null && !designation.trim().isEmpty() && 
               itemStatusId != null && itemId != null && financialOperationId != null;
    }

    /**
     * Get validation errors
     */
    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<>();
        
        if (designation == null || designation.trim().isEmpty()) {
            errors.add("Designation is required");
        }
        
        if (itemStatusId == null) {
            errors.add("Item status is required");
        }
        
        if (itemId == null) {
            errors.add("Item is required");
        }
        
        if (financialOperationId == null) {
            errors.add("Financial operation is required");
        }
        
        if (unitairCost != null && unitairCost <= 0) {
            errors.add("Unit cost must be positive");
        }
        
        if (planedQuantity != null && planedQuantity <= 0) {
            errors.add("Planned quantity must be positive");
        }
        
        if (allocatedAmount != null && allocatedAmount < 0) {
            errors.add("Allocated amount must be non-negative");
        }
        
        return errors;
    }

    /**
     * Get comparison key for sorting
     */
    public String getComparisonKey() {
        return getDisplayText().toLowerCase();
    }

    /**
     * Get formal display for official documents
     */
    public String getFormalDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(designation);
        
        if (item != null) {
            sb.append(" - ").append(item.getDisplayText());
        }
        
        if (financialOperation != null) {
            sb.append(" (").append(financialOperation.getDisplayText()).append(")");
        }
        
        return sb.toString();
    }

    /**
     * Get planned item classification for reports
     */
    public String getPlannedItemClassification() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Planned Item: ").append(getDisplayText()).append("\n");
        sb.append("Item: ").append(item != null ? item.getDisplayText() : "N/A").append("\n");
        sb.append("Status: ").append(itemStatus != null ? itemStatus.getDisplayText() : "N/A").append("\n");
        sb.append("Financial Operation: ").append(financialOperation != null ? financialOperation.getDisplayText() : "N/A").append("\n");
        sb.append("Unit Cost: ").append(unitairCost != null ? String.format("%.2f", unitairCost) : "N/A").append("\n");
        sb.append("Planned Quantity: ").append(planedQuantity != null ? planedQuantity.toString() : "N/A").append("\n");
        sb.append("Total Cost: ").append(String.format("%.2f", getTotalCost())).append("\n");
        sb.append("Allocated Amount: ").append(allocatedAmount != null ? String.format("%.2f", allocatedAmount) : "N/A").append("\n");
        sb.append("Budget Utilization: ").append(String.format("%.1f%%", getBudgetUtilization())).append("\n");
        sb.append("Planning Status: ").append(getPlanningStatus()).append("\n");
        sb.append("Cost Category: ").append(getCostCategory()).append("\n");
        sb.append("Quantity Scale: ").append(getQuantityScale()).append("\n");
        sb.append("Item Distributions Count: ").append(getItemDistributionsCountSafe()).append("\n");
        sb.append("Distribution Complexity: ").append(getDistributionComplexity());
        
        return sb.toString();
    }

    /**
     * Get procurement requirements
     */
    public String getProcurementRequirements() {
        String costCategory = getCostCategory();
        String quantityScale = getQuantityScale();
        
        StringBuilder sb = new StringBuilder();
        
        // Cost-based requirements
        switch (costCategory) {
            case "VERY_HIGH_COST":
                sb.append("Executive approval, Detailed justification, Competitive bidding");
                break;
            case "HIGH_COST":
                sb.append("Management approval, Cost analysis, Vendor evaluation");
                break;
            case "MEDIUM_COST":
                sb.append("Supervisor approval, Standard procurement, Quality verification");
                break;
            case "LOW_COST":
                sb.append("Standard approval, Basic procurement, Standard verification");
                break;
            default:
                sb.append("Minimal approval, Basic process");
        }
        
        // Quantity-based additional requirements
        switch (quantityScale) {
            case "VERY_LARGE_SCALE":
            case "LARGE_SCALE":
                sb.append(", Phased delivery, Storage planning, Quality assurance");
                break;
            case "MEDIUM_SCALE":
                sb.append(", Delivery coordination, Standard storage");
                break;
        }
        
        return sb.toString();
    }

    /**
     * Get financial control measures
     */
    public String getFinancialControlMeasures() {
        String planningStatus = getPlanningStatus();
        
        return switch (planningStatus) {
            case "SIGNIFICANTLY_OVER_PLANNED" -> "Budget revision required, Executive review, Cost optimization";
            case "OVER_PLANNED" -> "Budget review, Cost justification, Approval escalation";
            case "WELL_PLANNED" -> "Standard monitoring, Regular reviews, Variance tracking";
            case "UNDER_PLANNED" -> "Budget adequacy review, Resource assessment";
            case "NOT_PLANNED" -> "Planning required, Budget allocation, Cost estimation";
            default -> "Standard financial controls";
        };
    }

    /**
     * Get risk assessment
     */
    public String getRiskAssessment() {
        StringBuilder sb = new StringBuilder();
        
        String planningStatus = getPlanningStatus();
        String costCategory = getCostCategory();
        
        // Planning status risks
        switch (planningStatus) {
            case "SIGNIFICANTLY_OVER_PLANNED":
                sb.append("High budget overrun risk, Financial control breach");
                break;
            case "OVER_PLANNED":
                sb.append("Budget variance risk, Cost control concerns");
                break;
            case "UNDER_PLANNED":
                sb.append("Resource shortfall risk, Delivery compromise");
                break;
            case "NOT_PLANNED":
                sb.append("Planning inadequacy risk, Resource uncertainty");
                break;
            default:
                sb.append("Acceptable risk level");
        }
        
        // Cost category risks
        if ("VERY_HIGH_COST".equals(costCategory) || "HIGH_COST".equals(costCategory)) {
            sb.append(", High financial exposure, Vendor dependency");
        }
        
        return sb.toString();
    }

    /**
     * Get delivery timeline estimation
     */
    public String getDeliveryTimeline() {
        String quantityScale = getQuantityScale();
        String costCategory = getCostCategory();
        
        StringBuilder sb = new StringBuilder();
        
        // Base timeline by quantity
        switch (quantityScale) {
            case "VERY_LARGE_SCALE":
                sb.append("Extended delivery (12+ months)");
                break;
            case "LARGE_SCALE":
                sb.append("Long delivery (6-12 months)");
                break;
            case "MEDIUM_SCALE":
                sb.append("Standard delivery (3-6 months)");
                break;
            case "SMALL_SCALE":
                sb.append("Short delivery (1-3 months)");
                break;
            default:
                sb.append("Immediate delivery");
        }
        
        // Complexity adjustments
        if ("VERY_HIGH_COST".equals(costCategory)) {
            sb.append(", Complex procurement process");
        } else if ("HIGH_COST".equals(costCategory)) {
            sb.append(", Extended procurement cycle");
        }
        
        return sb.toString();
    }

    /**
     * Get quality assurance requirements
     */
    public String getQualityAssuranceRequirements() {
        String costCategory = getCostCategory();
        String quantityScale = getQuantityScale();
        
        StringBuilder sb = new StringBuilder();
        
        // Cost-based QA requirements
        switch (costCategory) {
            case "VERY_HIGH_COST":
            case "HIGH_COST":
                sb.append("Enhanced QA, Third-party inspection, Performance validation");
                break;
            case "MEDIUM_COST":
                sb.append("Standard QA, Quality verification, Acceptance testing");
                break;
            default:
                sb.append("Basic QA, Standard verification");
        }
        
        // Scale-based additional requirements
        if ("VERY_LARGE_SCALE".equals(quantityScale) || "LARGE_SCALE".equals(quantityScale)) {
            sb.append(", Batch testing, Statistical sampling, Continuous monitoring");
        }
        
        return sb.toString();
    }

    /**
     * Get success metrics
     */
    public String getSuccessMetrics() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Budget adherence, Delivery timeliness, Quality compliance");
        
        if (hasItemDistributions()) {
            sb.append(", Distribution accuracy, Allocation efficiency");
        }
        
        String planningStatus = getPlanningStatus();
        if ("WELL_PLANNED".equals(planningStatus)) {
            sb.append(", Cost optimization, Resource utilization");
        }
        
        return sb.toString();
    }
}