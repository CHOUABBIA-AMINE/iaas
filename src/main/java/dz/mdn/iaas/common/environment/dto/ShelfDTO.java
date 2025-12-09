/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: ShelfDTO
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
public class ShelfDTO {

    private Long id; // F_00

    @NotBlank(message = "Code is required")
    @Size(max = 20, message = "Code must not exceed 20 characters")
    private String code; // F_01 - required and unique

    @NotNull(message = "Room ID is required")
    private Long roomId; // F_02 - foreign key to Room (required)

    // Additional fields for display purposes (from related entities)
    private String roomCode;
    private String roomDesignationFr;
    private String roomLocationPath;
    
    // Bloc and Floor info from Room relationship
    private String blocCodeLt;
    private String blocDesignationFr;
    private String floorCode;
    private String floorDesignationFr;
    
    // Structure info from Room relationship (optional)
    private String structureName;

    private Long archiveBoxCount; // Count of archive boxes on this shelf

    // Combined display information
    private String displayTextWithCode;
    private String fullDisplayText;
    private String locationPath;
    private String shelfCapacityStatus;

    public static ShelfDTO fromEntity(dz.mdn.iaas.common.environment.model.Shelf shelf) {
        if (shelf == null) return null;
        
        ShelfDTO.ShelfDTOBuilder builder = ShelfDTO.builder()
                .id(shelf.getId())
                .code(shelf.getCode());

        // Handle Room relationship (required F_02)
        if (shelf.getRoom() != null) {
            builder.roomId(shelf.getRoom().getId())
                   .roomCode(shelf.getRoom().getCode());

            // Get Bloc info from Room
            if (shelf.getRoom().getBloc() != null) {
                builder.blocCodeLt(shelf.getRoom().getBloc().getCodeLt());
            }

            // Get Floor info from Room
            if (shelf.getRoom().getFloor() != null) {
                builder.floorCode(shelf.getRoom().getFloor().getCode())
                       .floorDesignationFr(shelf.getRoom().getFloor().getDesignationFr());
            }

            // Get Structure info from Room (optional)
            if (shelf.getRoom().getStructure() != null) {
                builder.structureName(getStructureName(shelf.getRoom().getStructure()));
            }

            // Build room location path
            builder.roomLocationPath(buildRoomLocationPath(shelf.getRoom()));
        }

        // Count archive boxes (OneToMany relationship)
        if (shelf.getArchiveBoxs() != null) {
            builder.archiveBoxCount((long) shelf.getArchiveBoxs().size());
        } else {
            builder.archiveBoxCount(0L);
        }

        ShelfDTO dto = builder.build();
        dto.setDisplayTextWithCode(buildDisplayTextWithCode(dto));
        dto.setFullDisplayText(buildFullDisplayText(dto));
        dto.setLocationPath(buildLocationPath(dto));
        dto.setShelfCapacityStatus(buildShelfCapacityStatus(dto));
        
        return dto;
    }

    public dz.mdn.iaas.common.environment.model.Shelf toEntity() {
        dz.mdn.iaas.common.environment.model.Shelf shelf = new dz.mdn.iaas.common.environment.model.Shelf();
        shelf.setId(this.id);
        shelf.setCode(this.code);
        // Note: room relationship must be set by service layer using roomId
        return shelf;
    }

    public void updateEntity(dz.mdn.iaas.common.environment.model.Shelf shelf) {
        if (this.code != null) {
            shelf.setCode(this.code);
        }
        // Note: room relationship update must be handled by service layer using roomId
    }

    public boolean hasArchiveBoxes() {
        return archiveBoxCount != null && archiveBoxCount > 0;
    }

    public boolean isComplete() {
        return roomId != null;
    }

    public Double getUtilizationPercentage() {
        if (archiveBoxCount == null) return 0.0;
        
        // Assume maximum capacity of 10 archive boxes per shelf
        double maxCapacity = 10.0;
        return Math.min((archiveBoxCount / maxCapacity) * 100, 100.0);
    }

    public boolean isEmpty() {
        return archiveBoxCount == null || archiveBoxCount == 0;
    }

    public boolean isFull() {
        return getUtilizationPercentage() >= 100.0;
    }

    public boolean needsAttention() {
        return getUtilizationPercentage() >= 80.0;
    }

    public String getShelfType() {
        if (code == null) return "UNKNOWN";
        
        String codeUpper = code.toUpperCase();
        
        if (codeUpper.contains("DOC") || codeUpper.contains("DOCUMENT")) {
            return "DOCUMENT_SHELF";
        } else if (codeUpper.contains("ARCH") || codeUpper.contains("ARCHIVE")) {
            return "ARCHIVE_SHELF";
        } else if (codeUpper.contains("REF") || codeUpper.contains("REFERENCE")) {
            return "REFERENCE_SHELF";
        } else if (codeUpper.contains("TEMP") || codeUpper.contains("TEMPORARY")) {
            return "TEMPORARY_SHELF";
        } else if (codeUpper.contains("SPEC") || codeUpper.contains("SPECIAL")) {
            return "SPECIAL_SHELF";
        }
        return "STANDARD_SHELF";
    }

    public boolean isDocumentShelf() {
        return "DOCUMENT_SHELF".equals(getShelfType());
    }

    public boolean isArchiveShelf() {
        return "ARCHIVE_SHELF".equals(getShelfType());
    }

    public String getPriority() {
        if (isFull()) {
            return "URGENT";
        } else if (needsAttention()) {
            return "HIGH";
        } else if (isEmpty()) {
            return "LOW";
        }
        return "MEDIUM";
    }

    public static ShelfDTO createSimple(Long id, String code, Long roomId) {
        return ShelfDTO.builder()
                .id(id)
                .code(code)
                .roomId(roomId)
                .displayTextWithCode(code)
                .build();
    }

    // ========== HELPER METHODS ==========

    private static String getStructureName(dz.mdn.iaas.common.administration.model.Structure structure) {
        // Assuming structure has name field - adjust based on actual Structure model
        return "Structure #" + structure.getId();
    }

    private static String buildRoomLocationPath(dz.mdn.iaas.common.environment.model.Room room) {
        StringBuilder sb = new StringBuilder();
        
        if (room.getBloc() != null && room.getBloc().getCodeLt() != null) {
            sb.append(room.getBloc().getCodeLt());
        }
        
        if (room.getFloor() != null && room.getFloor().getCode() != null) {
            if (sb.length() > 0) sb.append("/");
            sb.append(room.getFloor().getCode());
        }
        
        if (room.getCode() != null) {
            if (sb.length() > 0) sb.append("/");
            sb.append(room.getCode());
        }
        
        return sb.toString();
    }

    private static String buildDisplayTextWithCode(ShelfDTO dto) {
        StringBuilder sb = new StringBuilder();
        sb.append(dto.getCode());
        
        if (dto.getRoomCode() != null) {
            sb.append(" (Room: ").append(dto.getRoomCode()).append(")");
        }
        
        return sb.toString();
    }

    private static String buildFullDisplayText(ShelfDTO dto) {
        StringBuilder sb = new StringBuilder();
        sb.append("Shelf ").append(dto.getCode());
        
        if (dto.getRoomDesignationFr() != null) {
            sb.append(" - ").append(dto.getRoomDesignationFr());
        }
        
        if (dto.getFloorDesignationFr() != null) {
            sb.append(" (").append(dto.getFloorDesignationFr()).append(")");
        }
        
        if (dto.getBlocDesignationFr() != null) {
            sb.append(" - ").append(dto.getBlocDesignationFr());
        }
        
        return sb.toString();
    }

    private static String buildLocationPath(ShelfDTO dto) {
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
        
        if (dto.getCode() != null) {
            if (sb.length() > 0) sb.append("/");
            sb.append(dto.getCode());
        }
        
        return sb.toString();
    }

    private static String buildShelfCapacityStatus(ShelfDTO dto) {
        if (dto.getArchiveBoxCount() == null || dto.getArchiveBoxCount() == 0) {
            return "EMPTY";
        } else if (dto.getArchiveBoxCount() <= 3) {
            return "LOW";
        } else if (dto.getArchiveBoxCount() <= 7) {
            return "MEDIUM";
        } else if (dto.getArchiveBoxCount() <= 10) {
            return "HIGH";
        } else {
            return "FULL";
        }
    }
}
