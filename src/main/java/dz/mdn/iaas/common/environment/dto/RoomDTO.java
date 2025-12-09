/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RoomDTO
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
public class RoomDTO {

    private Long id; // F_00

    @NotBlank(message = "Code is required")
    @Size(max = 20, message = "Code must not exceed 20 characters")
    private String code; // F_01 - required and unique

    @NotNull(message = "Bloc ID is required")
    private Long blocId; // F_02 - foreign key to Bloc (required)

    @NotNull(message = "Floor ID is required")
    private Long floorId; // F_03 - foreign key to Floor (required)

    private Long structureId; // F_04 - foreign key to Structure (optional)

    // Additional fields for display purposes (from related entities)
    private String blocCodeLt;
    private String blocCodeAr;

    private String floorCode;

    private String structureName;
    private String structureCode;

    private Long shelfCount; // Count of shelfs in this room

    // Combined display information
    private String displayText;
    private String locationPath;

    public static RoomDTO fromEntity(dz.mdn.iaas.common.environment.model.Room room) {
        if (room == null) return null;
        
        RoomDTO.RoomDTOBuilder builder = RoomDTO.builder()
                .id(room.getId())
                .code(room.getCode());

        // Handle Bloc relationship (required F_02)
        if (room.getBloc() != null) {
            builder.blocId(room.getBloc().getId())
                   .blocCodeLt(room.getBloc().getCodeLt())
                   .blocCodeAr(room.getBloc().getCodeAr());
        }

        // Handle Floor relationship (required F_03)
        if (room.getFloor() != null) {
            builder.floorId(room.getFloor().getId())
                   .floorCode(room.getFloor().getCode());
        }

        // Handle Structure relationship (optional F_04)
        if (room.getStructure() != null) {
            builder.structureId(room.getStructure().getId())
                   .structureName(getStructureName(room.getStructure()))
                   .structureCode(getStructureCode(room.getStructure()));
        }

        // Count shelfs (OneToMany relationship)
        if (room.getShelfs() != null) {
            builder.shelfCount((long) room.getShelfs().size());
        } else {
            builder.shelfCount(0L);
        }

        RoomDTO dto = builder.build();
        dto.setDisplayText(buildDisplayText(dto));
        dto.setLocationPath(buildLocationPath(dto));
        
        return dto;
    }

    public dz.mdn.iaas.common.environment.model.Room toEntity() {
        dz.mdn.iaas.common.environment.model.Room room = new dz.mdn.iaas.common.environment.model.Room();
        room.setId(this.id);
        room.setCode(this.code);
        // Note: relationships must be set by service layer using IDs
        return room;
    }

    public void updateEntity(dz.mdn.iaas.common.environment.model.Room room) {
        if (this.code != null) {
            room.setCode(this.code);
        }
        // Note: relationship updates must be handled by service layer using IDs
    }

    public boolean hasStructure() {
        return structureId != null;
    }

    public boolean hasShelfs() {
        return shelfCount != null && shelfCount > 0;
    }

    public boolean isComplete() {
        return blocId != null && floorId != null;
    }

    public String getCapacityStatus() {
        if (shelfCount == null || shelfCount == 0) {
            return "EMPTY";
        } else if (shelfCount < 5) {
            return "LOW";
        } else if (shelfCount < 15) {
            return "MEDIUM";
        } else {
            return "HIGH";
        }
    }

    public static RoomDTO createSimple(Long id, String code, String designationFr) {
        return RoomDTO.builder()
                .id(id)
                .code(code)
                .displayText(code)
                .build();
    }

    // ========== HELPER METHODS ==========

    private static String getStructureName(dz.mdn.iaas.common.administration.model.Structure structure) {
        // Assuming structure has name field - adjust based on actual Structure model
        return "Structure #" + structure.getId();
    }

    private static String getStructureCode(dz.mdn.iaas.common.administration.model.Structure structure) {
        // Assuming structure has code field - adjust based on actual Structure model
        return "STR-" + structure.getId();
    }

    private static String buildDisplayText(RoomDTO dto) {
        return dto.getCode();
    }

    private static String buildLocationPath(RoomDTO dto) {
        StringBuilder sb = new StringBuilder();
        
        if (dto.getBlocCodeLt() != null) {
            sb.append(dto.getBlocCodeLt());
        }
        
        if (dto.getFloorCode() != null) {
            if (sb.length() > 0) sb.append("/");
            sb.append(dto.getFloorCode());
        }
        
        if (dto.getCode() != null) {
            if (sb.length() > 0) sb.append("/");
            sb.append(dto.getCode());
        }
        
        return sb.toString();
    }
}
