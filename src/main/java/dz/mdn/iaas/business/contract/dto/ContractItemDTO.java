/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ContractItemDTO
 *	@CreatedOn	: 10-20-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Contract
 *
 **/

package dz.mdn.iaas.business.contract.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.business.contract.model.ContractItem;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ContractItem Data Transfer Object
 * Maps exactly to ContractItem model fields:
 * F_00=id, F_01=designation, F_02=reference, F_03=quantity, F_04=unitPrice, F_05=observation, F_06=contractId
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractItemDTO {

    private Long id; // F_00

    @NotBlank(message = "Designation is required")
    @Size(max = 200, message = "Designation must not exceed 200 characters")
    private String designation; // F_01

    @NotBlank(message = "Reference is required")
    @Size(max = 100, message = "Reference must not exceed 100 characters")
    private String reference; // F_02

    @DecimalMin(value = "0.0", inclusive = false, message = "Quantity must be greater than 0")
    private Double quantity; // F_03

    @DecimalMin(value = "0.0", inclusive = false, message = "Unit price must be greater than 0")
    private Double unitPrice; // F_04

    @Size(max = 500, message = "Observation must not exceed 500 characters")
    private String observation; // F_05 - optional

    @NotNull(message = "Contract is required")
    private Long contractId; // F_06 - foreign key

    // Related entity DTO (optional)
    private ContractDTO contract;

    /**
     * Create DTO from entity
     */
    public static ContractItemDTO fromEntity(ContractItem entity) {
        if (entity == null) return null;

        ContractItemDTO.ContractItemDTOBuilder builder = ContractItemDTO.builder()
                .id(entity.getId())
                .designation(entity.getDesignation())
                .reference(entity.getReference())
                .quantity(entity.getQuantity())
                .unitPrice(entity.getUnitPrice())
                .observation(entity.getObservation());

        if (entity.getContract() != null) {
            builder.contractId(entity.getContract().getId());
        }

        return builder.build();
    }

    /**
     * Create DTO from entity with related Contract DTO
     */
    public static ContractItemDTO fromEntityWithRelations(ContractItem entity) {
        ContractItemDTO dto = fromEntity(entity);
        if (dto == null) return null;

        if (entity.getContract() != null) {
            dto.setContract(ContractDTO.fromEntity(entity.getContract()));
        }

        return dto;
    }

    /**
     * Get total price (quantity × unit price)
     */
    public Double getTotalPrice() {
        if (quantity == null || unitPrice == null) return 0.0;
        return BigDecimal.valueOf(quantity)
                .multiply(BigDecimal.valueOf(unitPrice))
                .doubleValue();
    }

    /**
     * Get formatted display for lists
     */
    public String getDisplayText() {
        return String.format("%s (%s)", designation != null ? designation : "N/A",
                reference != null ? reference : "N/A");
    }

    /**
     * Check if item is valid for contract inclusion
     */
    public boolean isValid() {
        return designation != null && !designation.trim().isEmpty()
                && reference != null && !reference.trim().isEmpty()
                && quantity != null && quantity > 0
                && unitPrice != null && unitPrice > 0
                && contractId != null;
    }

    /**
     * Get completeness percentage (rough estimation)
     */
    public int getCompletenessPercentage() {
        int total = 6; // number of main fields
        int completed = 0;

        if (designation != null && !designation.trim().isEmpty()) completed++;
        if (reference != null && !reference.trim().isEmpty()) completed++;
        if (quantity != null && quantity > 0) completed++;
        if (unitPrice != null && unitPrice > 0) completed++;
        if (observation != null && !observation.trim().isEmpty()) completed++;
        if (contractId != null) completed++;

        return (completed * 100) / total;
    }

    /**
     * Get summary line for reports or UI tables
     */
    public String getSummary() {
        return String.format("%s | Ref: %s | Qty: %.2f | Unit: %.2f | Total: %.2f",
                designation != null ? designation : "N/A",
                reference != null ? reference : "N/A",
                quantity != null ? quantity : 0.0,
                unitPrice != null ? unitPrice : 0.0,
                getTotalPrice());
    }

    /**
     * Create a simplified DTO (for dropdowns, lists, etc.)
     */
    public static ContractItemDTO createSimple(Long id, String designation, String reference) {
        return ContractItemDTO.builder()
                .id(id)
                .designation(designation)
                .reference(reference)
                .build();
    }
    
    // ========== ENTITY MAPPER ==========

 	public ContractItem toEntity() {
 		ContractItem entity = new ContractItem();
 		entity.setId(this.id);
 		entity.setDesignation(this.designation);
 		entity.setReference(this.reference);
 		entity.setQuantity(this.quantity);
 		entity.setUnitPrice(this.unitPrice);
 		entity.setObservation(this.observation);
 		return entity;
 	}
}
