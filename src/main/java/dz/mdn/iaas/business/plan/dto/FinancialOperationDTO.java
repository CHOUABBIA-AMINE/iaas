/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FinancialOperationDTO
 *	@CreatedOn	: 10-16-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.dto;

import java.time.Year;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Financial Operation Data Transfer Object
 * Maps exactly to FinancialOperation model fields: F_00=id, F_01=operation, F_02=budgetYear, F_03=budgetTypeId
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FinancialOperationDTO {

    private Long id; // F_00

    @NotBlank(message = "Operation is required")
    @Size(max = 200, message = "Operation must not exceed 200 characters")
    private String operation; // F_01 - required, unique

    @NotBlank(message = "Budget year is required")
    @Size(min = 4, max = 4, message = "Budget year must be exactly 4 characters")
    @Pattern(regexp = "^[0-9]{4}$", message = "Budget year must be a valid 4-digit year")
    private String budgetYear; // F_02 - required, 4 chars

    @NotNull(message = "Budget type is required")
    private Long budgetTypeId; // F_03 - BudgetType foreign key (required)

    // Related entity DTO for display (populated when needed)
    private BudgetTypeDTO budgetType;

    /**
     * Create DTO from entity
     */
    public static FinancialOperationDTO fromEntity(dz.mdn.iaas.business.plan.model.FinancialOperation financialOperation) {
        if (financialOperation == null) return null;
        
        FinancialOperationDTO.FinancialOperationDTOBuilder builder = FinancialOperationDTO.builder()
                .id(financialOperation.getId())
                .operation(financialOperation.getOperation())
                .budgetYear(financialOperation.getBudgetYear());

        // Handle foreign key relationship
        if (financialOperation.getBudgetType() != null) {
            builder.budgetTypeId(financialOperation.getBudgetType().getId());
        }

        return builder.build();
    }

    /**
     * Create DTO from entity with related objects
     */
    public static FinancialOperationDTO fromEntityWithRelations(dz.mdn.iaas.business.plan.model.FinancialOperation financialOperation) {
        FinancialOperationDTO dto = fromEntity(financialOperation);
        if (dto == null) return null;

        // Populate related DTOs
        if (financialOperation.getBudgetType() != null) {
            dto.setBudgetType(BudgetTypeDTO.fromEntity(financialOperation.getBudgetType()));
        }

        return dto;
    }

    /**
     * Get display text for the operation
     */
    public String getDisplayText() {
        return operation != null ? operation : "N/A";
    }

    /**
     * Get financial year as integer
     */
    public Integer getBudgetYearAsInt() {
        if (budgetYear != null && budgetYear.matches("^[0-9]{4}$")) {
            return Integer.parseInt(budgetYear);
        }
        return null;
    }

    /**
     * Get operation category based on operation name keywords
     */
    public String getOperationCategory() {
        if (operation == null) return "UNKNOWN_OPERATION";
        
        String operationLower = operation.toLowerCase();
        
        // Budget allocation operations
        if (operationLower.contains("allocation") || operationLower.contains("budget") ||
            operationLower.contains("affectation") || operationLower.contains("dotation") ||
            operationLower.contains("تخصيص") || operationLower.contains("ميزانية")) {
            return "BUDGET_ALLOCATION";
        }
        
        // Expenditure operations
        if (operationLower.contains("expenditure") || operationLower.contains("expense") ||
            operationLower.contains("dépense") || operationLower.contains("coût") ||
            operationLower.contains("إنفاق") || operationLower.contains("مصروف")) {
            return "EXPENDITURE_OPERATION";
        }
        
        // Revenue operations
        if (operationLower.contains("revenue") || operationLower.contains("income") ||
            operationLower.contains("recette") || operationLower.contains("revenu") ||
            operationLower.contains("إيراد") || operationLower.contains("دخل")) {
            return "REVENUE_OPERATION";
        }
        
        // Transfer operations
        if (operationLower.contains("transfer") || operationLower.contains("virement") ||
            operationLower.contains("transfert") || operationLower.contains("réaffectation") ||
            operationLower.contains("تحويل") || operationLower.contains("نقل")) {
            return "TRANSFER_OPERATION";
        }
        
        // Investment operations
        if (operationLower.contains("investment") || operationLower.contains("capital") ||
            operationLower.contains("investissement") || operationLower.contains("équipement") ||
            operationLower.contains("استثمار") || operationLower.contains("رأسمال")) {
            return "INVESTMENT_OPERATION";
        }
        
        // Procurement operations
        if (operationLower.contains("procurement") || operationLower.contains("purchase") ||
            operationLower.contains("acquisition") || operationLower.contains("achat") ||
            operationLower.contains("مشتريات") || operationLower.contains("شراء")) {
            return "PROCUREMENT_OPERATION";
        }
        
        // Payment operations
        if (operationLower.contains("payment") || operationLower.contains("paiement") ||
            operationLower.contains("versement") || operationLower.contains("règlement") ||
            operationLower.contains("دفع") || operationLower.contains("سداد")) {
            return "PAYMENT_OPERATION";
        }
        
        // Adjustment operations
        if (operationLower.contains("adjustment") || operationLower.contains("correction") ||
            operationLower.contains("ajustement") || operationLower.contains("rectification") ||
            operationLower.contains("تعديل") || operationLower.contains("تصحيح")) {
            return "ADJUSTMENT_OPERATION";
        }
        
        return "GENERAL_OPERATION";
    }

    /**
     * Get operation priority level
     */
    public String getOperationPriority() {
        String category = getOperationCategory();
        
        switch (category) {
            case "BUDGET_ALLOCATION":
            case "PAYMENT_OPERATION":
                return "HIGH_PRIORITY";
            case "EXPENDITURE_OPERATION":
            case "REVENUE_OPERATION":
                return "MEDIUM_PRIORITY";
            case "TRANSFER_OPERATION":
            case "INVESTMENT_OPERATION":
                return "MEDIUM_PRIORITY";
            case "PROCUREMENT_OPERATION":
                return "NORMAL_PRIORITY";
            case "ADJUSTMENT_OPERATION":
                return "LOW_PRIORITY";
            default:
                return "NORMAL_PRIORITY";
        }
    }

    /**
     * Get operation financial impact
     */
    public String getFinancialImpact() {
        String category = getOperationCategory();
        
        switch (category) {
            case "BUDGET_ALLOCATION":
                return "BUDGET_ESTABLISHMENT";
            case "EXPENDITURE_OPERATION":
                return "CASH_OUTFLOW";
            case "REVENUE_OPERATION":
                return "CASH_INFLOW";
            case "TRANSFER_OPERATION":
                return "BUDGET_REALLOCATION";
            case "INVESTMENT_OPERATION":
                return "CAPITAL_EXPENDITURE";
            case "PROCUREMENT_OPERATION":
                return "OPERATIONAL_EXPENDITURE";
            case "PAYMENT_OPERATION":
                return "SETTLEMENT_TRANSACTION";
            case "ADJUSTMENT_OPERATION":
                return "ACCOUNTING_CORRECTION";
            default:
                return "NEUTRAL_IMPACT";
        }
    }

    /**
     * Check if operation is current year
     */
    public boolean isCurrentYear() {
        Integer year = getBudgetYearAsInt();
        if (year == null) return false;
        
        return year.equals(Year.now().getValue());
    }

    /**
     * Check if operation is future year
     */
    public boolean isFutureYear() {
        Integer year = getBudgetYearAsInt();
        if (year == null) return false;
        
        return year > Year.now().getValue();
    }

    /**
     * Check if operation is past year
     */
    public boolean isPastYear() {
        Integer year = getBudgetYearAsInt();
        if (year == null) return false;
        
        return year < Year.now().getValue();
    }

    /**
     * Get operation fiscal period
     */
    public String getFiscalPeriod() {
        if (isCurrentYear()) {
            return "CURRENT_FISCAL_YEAR";
        } else if (isFutureYear()) {
            return "FUTURE_FISCAL_YEAR";
        } else if (isPastYear()) {
            return "PAST_FISCAL_YEAR";
        } else {
            return "INVALID_FISCAL_YEAR";
        }
    }

    /**
     * Get operation approval requirements
     */
    public String getApprovalRequirements() {
        String priority = getOperationPriority();
        String category = getOperationCategory();
        
        if ("HIGH_PRIORITY".equals(priority) || 
            "BUDGET_ALLOCATION".equals(category) || 
            "INVESTMENT_OPERATION".equals(category)) {
            return "EXECUTIVE_APPROVAL_REQUIRED";
        } else if ("MEDIUM_PRIORITY".equals(priority)) {
            return "DEPARTMENTAL_APPROVAL_REQUIRED";
        } else {
            return "STANDARD_APPROVAL_REQUIRED";
        }
    }

    /**
     * Get operation workflow stage
     */
    public String getWorkflowStage() {
        String category = getOperationCategory();
        
        switch (category) {
            case "BUDGET_ALLOCATION":
                return "PLANNING_STAGE";
            case "PROCUREMENT_OPERATION":
                return "PROCUREMENT_STAGE";
            case "EXPENDITURE_OPERATION":
            case "PAYMENT_OPERATION":
                return "EXECUTION_STAGE";
            case "REVENUE_OPERATION":
                return "COLLECTION_STAGE";
            case "TRANSFER_OPERATION":
                return "REALLOCATION_STAGE";
            case "ADJUSTMENT_OPERATION":
                return "RECONCILIATION_STAGE";
            default:
                return "GENERAL_STAGE";
        }
    }

    /**
     * Get short display for lists
     */
    public String getShortDisplay() {
        StringBuilder sb = new StringBuilder();
        
        if (operation != null) {
            sb.append(operation.length() > 50 ? 
                operation.substring(0, 47) + "..." : operation);
        }
        
        if (budgetYear != null) {
            sb.append(" (").append(budgetYear).append(")");
        }
        
        return sb.toString();
    }

    /**
     * Get full display with budget type
     */
    public String getFullDisplay() {
        StringBuilder sb = new StringBuilder();
        
        if (operation != null) {
            sb.append(operation);
        }
        
        if (budgetYear != null) {
            sb.append(" - ").append(budgetYear);
        }
        
        if (budgetType != null) {
            sb.append(" - ").append(budgetType.getDisplayText());
        }
        
        return sb.toString();
    }

    /**
     * Get financial display with category and impact
     */
    public String getFinancialDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDisplayText());
        
        if (budgetYear != null) {
            sb.append(" (").append(budgetYear).append(")");
        }
        
        String category = getOperationCategory();
        if (!"GENERAL_OPERATION".equals(category)) {
            sb.append(" - ").append(category.replace("_", " "));
        }
        
        String impact = getFinancialImpact();
        if (!"NEUTRAL_IMPACT".equals(impact)) {
            sb.append(" [").append(impact.replace("_", " ")).append("]");
        }
        
        return sb.toString();
    }

    /**
     * Create simplified DTO for dropdowns
     */
    public static FinancialOperationDTO createSimple(Long id, String operation, String budgetYear) {
        return FinancialOperationDTO.builder()
                .id(id)
                .operation(operation)
                .budgetYear(budgetYear)
                .build();
    }

    /**
     * Validate required fields are present
     */
    public boolean isValid() {
        return operation != null && !operation.trim().isEmpty() && 
               budgetYear != null && budgetYear.matches("^[0-9]{4}$") &&
               budgetTypeId != null;
    }

    /**
     * Get validation errors
     */
    public java.util.List<String> getValidationErrors() {
        java.util.List<String> errors = new java.util.ArrayList<>();
        
        if (operation == null || operation.trim().isEmpty()) {
            errors.add("Operation is required");
        }
        
        if (budgetYear == null || !budgetYear.matches("^[0-9]{4}$")) {
            errors.add("Budget year is required and must be a valid 4-digit year");
        }
        
        if (budgetTypeId == null) {
            errors.add("Budget type is required");
        }
        
        return errors;
    }

    /**
     * Get comparison key for sorting (by year desc, then by operation)
     */
    public String getComparisonKey() {
        String year = budgetYear != null ? budgetYear : "0000";
        String op = operation != null ? operation.toLowerCase() : "zzz";
        return year + "_" + op;
    }

    /**
     * Get formal display for official documents
     */
    public String getFormalDisplay() {
        StringBuilder sb = new StringBuilder();
        
        if (operation != null) {
            sb.append(operation);
        }
        
        if (budgetYear != null) {
            sb.append(" - Exercise ").append(budgetYear);
        }
        
        if (budgetType != null) {
            sb.append(" - ").append(budgetType.getFormalDisplay());
        }
        
        return sb.toString();
    }

    /**
     * Get operation classification for reports
     */
    public String getOperationClassification() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Operation: ").append(getDisplayText()).append("\n");
        sb.append("Budget Year: ").append(budgetYear).append("\n");
        sb.append("Fiscal Period: ").append(getFiscalPeriod().replace("_", " ")).append("\n");
        sb.append("Category: ").append(getOperationCategory().replace("_", " ")).append("\n");
        sb.append("Priority: ").append(getOperationPriority().replace("_", " ")).append("\n");
        sb.append("Financial Impact: ").append(getFinancialImpact().replace("_", " ")).append("\n");
        sb.append("Workflow Stage: ").append(getWorkflowStage().replace("_", " ")).append("\n");
        sb.append("Approval Requirements: ").append(getApprovalRequirements().replace("_", " "));
        
        if (budgetType != null) {
            sb.append("\nBudget Type: ").append(budgetType.getDisplayText());
        }
        
        return sb.toString();
    }

    /**
     * Get operation execution context
     */
    public String getExecutionContext() {
        String category = getOperationCategory();
        
        return switch (category) {
            case "BUDGET_ALLOCATION" -> "Initial budget planning and resource allocation";
            case "EXPENDITURE_OPERATION" -> "Operational spending and cost management";
            case "REVENUE_OPERATION" -> "Income generation and revenue collection";
            case "TRANSFER_OPERATION" -> "Budget reallocation and fund transfers";
            case "INVESTMENT_OPERATION" -> "Capital investment and asset acquisition";
            case "PROCUREMENT_OPERATION" -> "Goods and services procurement";
            case "PAYMENT_OPERATION" -> "Financial settlements and disbursements";
            case "ADJUSTMENT_OPERATION" -> "Accounting corrections and reconciliations";
            default -> "General financial operation";
        };
    }

    /**
     * Get monitoring requirements
     */
    public String getMonitoringRequirements() {
        String priority = getOperationPriority();
        
        return switch (priority) {
            case "HIGH_PRIORITY" -> "DAILY_MONITORING";
            case "MEDIUM_PRIORITY" -> "WEEKLY_MONITORING";
            case "NORMAL_PRIORITY" -> "MONTHLY_MONITORING";
            default -> "QUARTERLY_MONITORING";
        };
    }

    /**
     * Get audit trail requirements
     */
    public String getAuditRequirements() {
        String category = getOperationCategory();
        String priority = getOperationPriority();
        
        if ("HIGH_PRIORITY".equals(priority) || 
            "BUDGET_ALLOCATION".equals(category) ||
            "INVESTMENT_OPERATION".equals(category)) {
            return "ENHANCED_AUDIT_TRAIL";
        } else if ("MEDIUM_PRIORITY".equals(priority)) {
            return "STANDARD_AUDIT_TRAIL";
        } else {
            return "BASIC_AUDIT_TRAIL";
        }
    }

    /**
     * Get compliance requirements
     */
    public String getComplianceRequirements() {
        StringBuilder sb = new StringBuilder();
        
        // Always required
        sb.append("Financial regulations compliance, Budget law adherence");
        
        String category = getOperationCategory();
        
        // Additional requirements based on category
        switch (category) {
            case "BUDGET_ALLOCATION":
                sb.append(", Parliamentary approval for major allocations");
                break;
            case "PROCUREMENT_OPERATION":
                sb.append(", Public procurement regulations");
                break;
            case "INVESTMENT_OPERATION":
                sb.append(", Investment approval procedures");
                break;
            case "PAYMENT_OPERATION":
                sb.append(", Payment authorization protocols");
                break;
        }
        
        return sb.toString();
    }

    /**
     * Get documentation requirements
     */
    public String getDocumentationRequirements() {
        String priority = getOperationPriority();
        
        StringBuilder sb = new StringBuilder();
        
        // Standard requirements
        sb.append("Operation authorization, Financial justification");
        
        // Additional requirements based on priority
        switch (priority) {
            case "HIGH_PRIORITY":
                sb.append(", Executive approval documentation, Risk assessment");
                break;
            case "MEDIUM_PRIORITY":
                sb.append(", Departmental approval, Impact analysis");
                break;
        }
        
        return sb.toString();
    }

    /**
     * Get year-end processing requirements
     */
    public String getYearEndProcessing() {
        if (isCurrentYear()) {
            return "ACTIVE_YEAR_PROCESSING";
        } else if (isPastYear()) {
            return "CLOSED_YEAR_ARCHIVE";
        } else if (isFutureYear()) {
            return "FUTURE_YEAR_PLANNING";
        } else {
            return "INVALID_YEAR_HANDLING";
        }
    }

    /**
     * Get budget cycle stage
     */
    public String getBudgetCycleStage() {
        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH is 0-based
        
        if (isFutureYear()) {
            return "BUDGET_PREPARATION";
        } else if (isCurrentYear()) {
            if (currentMonth <= 3) {
                return "BUDGET_EXECUTION_Q1";
            } else if (currentMonth <= 6) {
                return "BUDGET_EXECUTION_Q2";
            } else if (currentMonth <= 9) {
                return "BUDGET_EXECUTION_Q3";
            } else {
                return "BUDGET_EXECUTION_Q4";
            }
        } else if (isPastYear()) {
            return "BUDGET_CLOSURE";
        } else {
            return "UNKNOWN_CYCLE_STAGE";
        }
    }

    /**
     * Get financial control measures
     */
    public String getFinancialControlMeasures() {
        String priority = getOperationPriority();
        String category = getOperationCategory();
        
        StringBuilder sb = new StringBuilder();
        
        // Standard controls
        sb.append("Budget availability verification, Authorization controls");
        
        // Additional controls based on priority and category
        if ("HIGH_PRIORITY".equals(priority)) {
            sb.append(", Multi-level approval, Real-time monitoring");
        }
        
        if ("INVESTMENT_OPERATION".equals(category) || 
            "PROCUREMENT_OPERATION".equals(category)) {
            sb.append(", Competitive bidding oversight");
        }
        
        return sb.toString();
    }
}
