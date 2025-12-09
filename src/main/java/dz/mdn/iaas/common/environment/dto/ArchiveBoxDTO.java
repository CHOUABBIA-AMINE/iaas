/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ArchiveBoxDTO
 *	@CreatedOn	: 10-15-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArchiveBoxDTO {

    private Long id; // F_00

    @NotBlank(message = "Code is required")
    @Size(max = 20, message = "Code must not exceed 20 characters")
    private String code; // F_01 - required and unique

    @NotNull(message = "Shelf ID is required")
    private Long shelfId; // F_02 - foreign key to Shelf (required)

    @NotNull(message = "Shelf floor ID is required")
    private Long shelfFloorId; // F_03 - foreign key to ShelfFloor (required)

    // Additional fields for display purposes (from related entities)
    private String shelfCode;
    
    // Room info from Shelf relationship
    private String roomCode;
    private String roomDesignationFr;
    private Long roomId;
    
    // Floor and Bloc info from Shelf → Room relationship chain
    private String floorCode;
    private String floorDesignationFr;
    private String blocCodeLt;
    private String blocDesignationFr;
    
    // Structure info from Shelf → Room relationship (optional)
    private String structureName;

    // ShelfFloor info from direct relationship
    private String shelfFloorCode;
    private String shelfFloorDesignationFr;

    // Combined display information
    private String displayTextWithCode;
    private String fullDisplayText;
    private String locationPath;
    private String archiveBoxType;

    public static ArchiveBoxDTO fromEntity(dz.mdn.iaas.common.environment.model.ArchiveBox archiveBox) {
        if (archiveBox == null) return null;
        
        ArchiveBoxDTO.ArchiveBoxDTOBuilder builder = ArchiveBoxDTO.builder()
                .id(archiveBox.getId())
                .code(archiveBox.getCode());

        // Handle Shelf relationship (required F_02)
        if (archiveBox.getShelf() != null) {
            builder.shelfId(archiveBox.getShelf().getId())
                   .shelfCode(archiveBox.getShelf().getCode());

            // Get Room info from Shelf
            if (archiveBox.getShelf().getRoom() != null) {
                builder.roomId(archiveBox.getShelf().getRoom().getId())
                       .roomCode(archiveBox.getShelf().getRoom().getCode());

                // Get Floor info from Room
                if (archiveBox.getShelf().getRoom().getFloor() != null) {
                    builder.floorCode(archiveBox.getShelf().getRoom().getFloor().getCode())
                           .floorDesignationFr(archiveBox.getShelf().getRoom().getFloor().getDesignationFr());
                }

                // Get Bloc info from Room
                if (archiveBox.getShelf().getRoom().getBloc() != null) {
                    builder.blocCodeLt(archiveBox.getShelf().getRoom().getBloc().getCodeLt());
                }

                // Get Structure info from Room (optional)
                if (archiveBox.getShelf().getRoom().getStructure() != null) {
                    builder.structureName(getStructureName(archiveBox.getShelf().getRoom().getStructure()));
                }
            }
        }

        // Handle ShelfFloor relationship (required F_03)
        if (archiveBox.getShelfFloor() != null) {
            builder.shelfFloorId(archiveBox.getShelfFloor().getId())
                   .shelfFloorCode(archiveBox.getShelfFloor().getCode())
                   .shelfFloorDesignationFr(archiveBox.getShelfFloor().getDesignationFr());
        }

        ArchiveBoxDTO dto = builder.build();
        dto.setDisplayTextWithCode(buildDisplayTextWithCode(dto));
        dto.setFullDisplayText(buildFullDisplayText(dto));
        dto.setLocationPath(buildLocationPath(dto));
        dto.setArchiveBoxType(buildArchiveBoxType(dto));
        
        return dto;
    }

    public dz.mdn.iaas.common.environment.model.ArchiveBox toEntity() {
        dz.mdn.iaas.common.environment.model.ArchiveBox archiveBox = new dz.mdn.iaas.common.environment.model.ArchiveBox();
        archiveBox.setId(this.id);
        archiveBox.setCode(this.code);
        // Note: shelf and shelfFloor relationships must be set by service layer using IDs
        return archiveBox;
    }

    public void updateEntity(dz.mdn.iaas.common.environment.model.ArchiveBox archiveBox) {
        if (this.code != null) {
            archiveBox.setCode(this.code);
        }
        // Note: relationship updates must be handled by service layer using IDs
    }

    public boolean isComplete() {
        return shelfId != null && shelfFloorId != null;
    }

    public String getCapacityStatus() {
        // This could be enhanced with actual folder count if folders are tracked
        // For now, return a placeholder based on archive box type
        return switch (getArchiveBoxType()) {
            case "DOCUMENT_BOX" -> "MEDIUM";
            case "ARCHIVE_BOX" -> "HIGH";
            case "TEMPORARY_BOX" -> "LOW";
            case "REFERENCE_BOX" -> "MEDIUM";
            case "CONFIDENTIAL_BOX" -> "LOW";
            default -> "UNKNOWN";
        };
    }

    public String getPriority() {
        return switch (getArchiveBoxType()) {
            case "CONFIDENTIAL_BOX" -> "HIGH";
            case "URGENT_BOX" -> "URGENT";
            case "PRIORITY_BOX" -> "HIGH";
            case "TEMPORARY_BOX" -> "LOW";
            case "ARCHIVE_BOX" -> "LOW";
            default -> "MEDIUM";
        };
    }

    public boolean isConfidential() {
        return "CONFIDENTIAL_BOX".equals(getArchiveBoxType());
    }

    public boolean isTemporary() {
        return "TEMPORARY_BOX".equals(getArchiveBoxType());
    }

    public boolean isUrgent() {
        return "URGENT_BOX".equals(getArchiveBoxType());
    }

    public String getAccessibilityRating() {
        if (shelfFloorDesignationFr == null && shelfFloorCode == null) return "UNKNOWN";
        
        String checkText = (shelfFloorCode + " " + shelfFloorDesignationFr).toLowerCase();
        
        if (checkText.contains("eye") || checkText.contains("niveau") || checkText.contains("3")) {
            return "HIGH";
        } else if (checkText.contains("middle") || checkText.contains("milieu") || checkText.contains("4")) {
            return "MEDIUM";
        } else if (checkText.contains("bottom") || checkText.contains("bas") || checkText.contains("1") || 
                  checkText.contains("top") || checkText.contains("haut") || checkText.contains("5")) {
            return "LOW";
        }
        return "MEDIUM";
    }

    public static ArchiveBoxDTO createSimple(Long id, String code, Long shelfId, Long shelfFloorId) {
        return ArchiveBoxDTO.builder()
                .id(id)
                .code(code)
                .shelfId(shelfId)
                .shelfFloorId(shelfFloorId)
                .displayTextWithCode(code)
                .build();
    }

    public boolean isValid() {
        return code != null && !code.trim().isEmpty() &&
               shelfId != null && shelfFloorId != null;
    }

    // ========== HELPER METHODS ==========

    private static String getStructureName(dz.mdn.iaas.common.administration.model.Structure structure) {
        // Assuming structure has name field - adjust based on actual Structure model
        return "Structure #" + structure.getId();
    }

    private static String buildDisplayTextWithCode(ArchiveBoxDTO dto) {
        StringBuilder sb = new StringBuilder();
        sb.append(dto.getCode());
        
        if (dto.getShelfCode() != null) {
            sb.append(" (Shelf: ").append(dto.getShelfCode()).append(")");
        }
        
        if (dto.getShelfFloorDesignationFr() != null) {
            sb.append(" - ").append(dto.getShelfFloorDesignationFr());
        }
        
        return sb.toString();
    }

    private static String buildFullDisplayText(ArchiveBoxDTO dto) {
        StringBuilder sb = new StringBuilder();
        sb.append("Archive Box ").append(dto.getCode());
        
        if (dto.getShelfCode() != null) {
            sb.append(" - Shelf: ").append(dto.getShelfCode());
        }
        
        if (dto.getShelfFloorDesignationFr() != null) {
            sb.append(" (").append(dto.getShelfFloorDesignationFr()).append(")");
        }
        
        if (dto.getRoomDesignationFr() != null) {
            sb.append(" - Room: ").append(dto.getRoomDesignationFr());
        }
        
        if (dto.getFloorDesignationFr() != null) {
            sb.append(" - Floor: ").append(dto.getFloorDesignationFr());
        }
        
        return sb.toString();
    }

    private static String buildLocationPath(ArchiveBoxDTO dto) {
        StringBuilder sb = new StringBuilder();
        
        if (dto.getBlocCodeLt() != null) {
            sb.append(dto.getBlocCodeLt());
        }
        
        if (dto.getFloorCode() != null) {
            if (sb.length() > 0) sb.append("/");
            sb.append(dto.getFloorCode());
        }
        
        if (dto.getRoomCode() != null) {
            if (sb.length() > 0) sb.append("/");
            sb.append(dto.getRoomCode());
        }
        
        if (dto.getShelfCode() != null) {
            if (sb.length() > 0) sb.append("/");
            sb.append(dto.getShelfCode());
        }
        
        if (dto.getCode() != null) {
            if (sb.length() > 0) sb.append("/");
            sb.append(dto.getCode());
        }
        
        return sb.toString();
    }

    private static String buildArchiveBoxType(ArchiveBoxDTO dto) {
        if (dto.getCode() == null) return "STANDARD_BOX";
        
        String codeUpper = dto.getCode().toUpperCase();
        
        if (codeUpper.contains("URGENT") || codeUpper.contains("URG")) {
            return "URGENT_BOX";
        } else if (codeUpper.contains("CONF") || codeUpper.contains("CONFIDENTIAL") || codeUpper.contains("SECRET")) {
            return "CONFIDENTIAL_BOX";
        } else if (codeUpper.contains("TEMP") || codeUpper.contains("TEMPORARY") || codeUpper.contains("TMP")) {
            return "TEMPORARY_BOX";
        } else if (codeUpper.contains("ARCH") || codeUpper.contains("ARCHIVE")) {
            return "ARCHIVE_BOX";
        } else if (codeUpper.contains("PRIO") || codeUpper.contains("PRIORITY") || codeUpper.contains("IMPORTANT")) {
            return "PRIORITY_BOX";
        } else if (codeUpper.contains("DOC") || codeUpper.contains("DOCUMENT")) {
            return "DOCUMENT_BOX";
        } else if (codeUpper.contains("REF") || codeUpper.contains("REFERENCE")) {
            return "REFERENCE_BOX";
        } else if (codeUpper.contains("LEGAL") || codeUpper.contains("LAW")) {
            return "LEGAL_BOX";
        } else if (codeUpper.contains("FINANCIAL") || codeUpper.contains("FINANCE") || codeUpper.contains("FIN")) {
            return "FINANCIAL_BOX";
        }
        return "STANDARD_BOX";
    }

    public String getShortDisplay() {
        return code + (shelfCode != null ? " (" + shelfCode + ")" : "");
    }

    public String getDetailedInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Box: ").append(code);
        
        if (shelfCode != null) {
            sb.append(" | Shelf: ").append(shelfCode);
        }
        
        if (shelfFloorDesignationFr != null) {
            sb.append(" | Level: ").append(shelfFloorDesignationFr);
        }
        
        if (roomDesignationFr != null) {
            sb.append(" | Room: ").append(roomDesignationFr);
        }
        
        sb.append(" | Type: ").append(getArchiveBoxType());
        sb.append(" | Access: ").append(getAccessibilityRating());
        
        return sb.toString();
    }
}
