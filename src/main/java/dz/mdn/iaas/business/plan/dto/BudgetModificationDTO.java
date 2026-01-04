/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: BudgetModificationDTO
 *	@CreatedOn	: 10-16-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Business / Plan
 *
 **/

package dz.mdn.iaas.business.plan.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.business.plan.model.BudgetModification;
import dz.mdn.iaas.common.document.dto.DocumentDTO;
import dz.mdn.iaas.common.document.model.Document;
import dz.mdn.iaas.configuration.template.GenericDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * BudgetModification Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BudgetModificationDTO extends GenericDTO<BudgetModification> {

    @Size(max = 200, message = "Object must not exceed 200 characters")
    private String object;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;
    
    private Date approvalDate;
    
    @NotNull(message = "Demande document is required")
    private Long demandeId;
    
    @NotNull(message = "Response document is required")
    private Long responseId;
    
    private DocumentDTO demande;
    
    private DocumentDTO response;

    @Override
    public BudgetModification toEntity() {
        BudgetModification entity = new BudgetModification();
        entity.setId(this.getId());
        entity.setObject(this.object);
        entity.setDescription(this.description);
        entity.setApprovalDate(this.approvalDate);
        
		if (this.demandeId != null) {
			Document demande = new Document();
			demande.setId(this.demandeId);
		    entity.setDemande(demande);
		}
        
		if (this.responseId != null) {
			Document response = new Document();
			response.setId(this.responseId);
		    entity.setResponse(response);
		}
		
        return entity;
    }

    @Override
    public void updateEntity(BudgetModification entity) {
        if (this.object != null) entity.setObject(this.object);
        if (this.description != null) entity.setDescription(this.description);
        if (this.approvalDate != null) entity.setApprovalDate(this.approvalDate);
        
		if (this.demandeId != null) {
			Document demande = new Document();
			demande.setId(this.demandeId);
		    entity.setDemande(demande);
		}
        
		if (this.responseId != null) {
			Document response = new Document();
			response.setId(this.responseId);
		    entity.setResponse(response);
		}
    }

    public static BudgetModificationDTO fromEntity(BudgetModification entity) {
        if (entity == null) return null;
        return BudgetModificationDTO.builder()
                .id(entity.getId())
                .object(entity.getObject())
                .description(entity.getDescription())
                .approvalDate(entity.getApprovalDate())
                .demandeId(entity.getDemande() != null ? entity.getDemande().getId() : null)
                .responseId(entity.getResponse() != null ? entity.getResponse().getId() : null)
                
                .demande(entity.getDemande() != null ? DocumentDTO.fromEntity(entity.getDemande()) : null)
                .response(entity.getResponse() != null ? DocumentDTO.fromEntity(entity.getResponse()) : null)
                .build();
    }
}
