/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FolderDTO
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
public class FolderDTO {

    private Long id; // F_00

    @NotBlank(message = "Code is required")
    @Size(max = 20, message = "Code must not exceed 20 characters")
    private String code; // F_01 - required and unique

    @Size(max = 200, message = "Arabic designation must not exceed 200 characters")
    private String designationAr; // F_02 - optional

    @Size(max = 200, message = "English designation must not exceed 200 characters")
    private String designationEn; // F_03 - optional

    @NotBlank(message = "French designation is required")
    @Size(max = 200, message = "French designation must not exceed 200 characters")
    private String designationFr; // F_04 - required and unique

    @NotNull(message = "Archive box ID is required")
    private Long archiveBoxId; // F_05 - foreign key to ArchiveBox (required)

    // Additional fields for display purposes (from related entities)
    private String archiveBoxCode;
    
    // Shelf info from ArchiveBox relationship
    private String shelfCode;
    private Long shelfId;
    
    // Room info from ArchiveBox → Shelf → Room relationship chain
    private String roomCode;
    private String roomDesignationFr;
    private Long roomId;
    
    // Floor and Bloc info from complete relationship chain
    private String floorCode;
    private String floorDesignationFr;
    private String blocCodeLt;
    private String blocDesignationFr;
    
    // ShelfFloor info from ArchiveBox relationship
    private String shelfFloorCode;
    private String shelfFloorDesignationFr;

    // Combined display information
    private String displayTextWithCode;
    private String fullDisplayText;
    private String locationPath;
    private String folderType;

    public static FolderDTO fromEntity(dz.mdn.iaas.common.environment.model.Folder folder) {
        if (folder == null) return null;
        
        FolderDTO.FolderDTOBuilder builder = FolderDTO.builder()
                .id(folder.getId())
                .code(folder.getCode())
                .designationAr(folder.getDesignationAr())
                .designationEn(folder.getDesignationEn())
                .designationFr(folder.getDesignationFr());

        // Handle ArchiveBox relationship (required F_05)
        if (folder.getArchiveBox() != null) {
            builder.archiveBoxId(folder.getArchiveBox().getId())
                   .archiveBoxCode(folder.getArchiveBox().getCode());

            // Get Shelf info from ArchiveBox
            if (folder.getArchiveBox().getShelf() != null) {
                builder.shelfId(folder.getArchiveBox().getShelf().getId())
                       .shelfCode(folder.getArchiveBox().getShelf().getCode());

                // Get Room info from Shelf
                if (folder.getArchiveBox().getShelf().getRoom() != null) {
                    builder.roomId(folder.getArchiveBox().getShelf().getRoom().getId())
                           .roomCode(folder.getArchiveBox().getShelf().getRoom().getCode());

                    // Get Floor info from Room
                    if (folder.getArchiveBox().getShelf().getRoom().getFloor() != null) {
                        builder.floorCode(folder.getArchiveBox().getShelf().getRoom().getFloor().getCode())
                               .floorDesignationFr(folder.getArchiveBox().getShelf().getRoom().getFloor().getDesignationFr());
                    }

                    // Get Bloc info from Room
                    if (folder.getArchiveBox().getShelf().getRoom().getBloc() != null) {
                        builder.blocCodeLt(folder.getArchiveBox().getShelf().getRoom().getBloc().getCodeLt());
                    }
                }
            }

            // Get ShelfFloor info from ArchiveBox
            if (folder.getArchiveBox().getShelfFloor() != null) {
                builder.shelfFloorCode(folder.getArchiveBox().getShelfFloor().getCode())
                       .shelfFloorDesignationFr(folder.getArchiveBox().getShelfFloor().getDesignationFr());
            }
        }

        FolderDTO dto = builder.build();
        dto.setDisplayTextWithCode(buildDisplayTextWithCode(dto));
        dto.setFullDisplayText(buildFullDisplayText(dto));
        dto.setLocationPath(buildLocationPath(dto));
        dto.setFolderType(buildFolderType(dto));
        
        return dto;
    }

    public dz.mdn.iaas.common.environment.model.Folder toEntity() {
        dz.mdn.iaas.common.environment.model.Folder folder = new dz.mdn.iaas.common.environment.model.Folder();
        folder.setId(this.id);
        folder.setCode(this.code);
        folder.setDesignationAr(this.designationAr);
        folder.setDesignationEn(this.designationEn);
        folder.setDesignationFr(this.designationFr);
        // Note: archiveBox relationship must be set by service layer using archiveBoxId
        return folder;
    }

    public void updateEntity(dz.mdn.iaas.common.environment.model.Folder folder) {
        if (this.code != null) {
            folder.setCode(this.code);
        }
        if (this.designationAr != null) {
            folder.setDesignationAr(this.designationAr);
        }
        if (this.designationEn != null) {
            folder.setDesignationEn(this.designationEn);
        }
        if (this.designationFr != null) {
            folder.setDesignationFr(this.designationFr);
        }
        // Note: relationship updates must be handled by service layer using archiveBoxId
    }

    public String getDefaultDesignation() {
        return designationFr;
    }

    public String getDesignationByLanguage(String language) {
        if (language == null) return designationFr;
        
        return switch (language.toLowerCase()) {
            case "ar", "arabic" -> designationAr != null ? designationAr : designationFr;
            case "en", "english" -> designationEn != null ? designationEn : designationFr;
            case "fr", "french" -> designationFr;
            default -> designationFr;
        };
    }

    public boolean isMultilingual() {
        int languageCount = 0;
        if (designationAr != null && !designationAr.trim().isEmpty()) languageCount++;
        if (designationEn != null && !designationEn.trim().isEmpty()) languageCount++;
        if (designationFr != null && !designationFr.trim().isEmpty()) languageCount++;
        return languageCount > 1;
    }

    public boolean isComplete() {
        return archiveBoxId != null;
    }

    public String getPriority() {
        if (folderType == null) return "MEDIUM";
        
        return switch (folderType) {
            case "URGENT_FOLDER" -> "URGENT";
            case "PRIORITY_FOLDER" -> "HIGH";
            case "CONFIDENTIAL_FOLDER" -> "HIGH";
            case "TEMPORARY_FOLDER" -> "LOW";
            case "ARCHIVE_FOLDER" -> "LOW";
            default -> "MEDIUM";
        };
    }

    public boolean isConfidential() {
        return "CONFIDENTIAL_FOLDER".equals(folderType);
    }

    public boolean isTemporary() {
        return "TEMPORARY_FOLDER".equals(folderType);
    }

    public boolean isUrgent() {
        return "URGENT_FOLDER".equals(folderType);
    }

    public String[] getAvailableLanguages() {
        java.util.List<String> languages = new java.util.ArrayList<>();
        
        if (code != null && !code.trim().isEmpty()) {
            languages.add("code");
        }
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

    public static FolderDTO createSimple(Long id, String code, String designationFr, Long archiveBoxId) {
        return FolderDTO.builder()
                .id(id)
                .code(code)
                .designationFr(designationFr)
                .archiveBoxId(archiveBoxId)
                .displayTextWithCode(code + " - " + designationFr)
                .build();
    }

    public boolean isValid() {
        return code != null && !code.trim().isEmpty() &&
               designationFr != null && !designationFr.trim().isEmpty() &&
               archiveBoxId != null;
    }

    // ========== HELPER METHODS ==========

    private static String buildDisplayTextWithCode(FolderDTO dto) {
        StringBuilder sb = new StringBuilder();
        sb.append(dto.getCode()).append(" - ").append(dto.getDesignationFr());
        
        if (dto.getArchiveBoxCode() != null) {
            sb.append(" (Box: ").append(dto.getArchiveBoxCode()).append(")");
        }
        
        return sb.toString();
    }

    private static String buildFullDisplayText(FolderDTO dto) {
        StringBuilder sb = new StringBuilder();
        sb.append("Folder ").append(dto.getCode()).append(" - ").append(dto.getDesignationFr());
        
        if (dto.getArchiveBoxCode() != null) {
            sb.append(" (Archive Box: ").append(dto.getArchiveBoxCode()).append(")");
        }
        
        if (dto.getShelfCode() != null) {
            sb.append(" - Shelf: ").append(dto.getShelfCode());
        }
        
        if (dto.getRoomDesignationFr() != null) {
            sb.append(" - Room: ").append(dto.getRoomDesignationFr());
        }
        
        if (dto.getFloorDesignationFr() != null) {
            sb.append(" (").append(dto.getFloorDesignationFr()).append(")");
        }
        
        return sb.toString();
    }

    private static String buildLocationPath(FolderDTO dto) {
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
        
        if (dto.getArchiveBoxCode() != null) {
            if (sb.length() > 0) sb.append("/");
            sb.append(dto.getArchiveBoxCode());
        }
        
        if (dto.getCode() != null) {
            if (sb.length() > 0) sb.append("/");
            sb.append(dto.getCode());
        }
        
        return sb.toString();
    }

    private static String buildFolderType(FolderDTO dto) {
        if (dto.getCode() == null) return "STANDARD_FOLDER";
        
        String codeUpper = dto.getCode().toUpperCase();
        
        if (codeUpper.contains("URGENT") || codeUpper.contains("URG")) {
            return "URGENT_FOLDER";
        } else if (codeUpper.contains("CONF") || codeUpper.contains("CONFIDENTIAL") || codeUpper.contains("SECRET")) {
            return "CONFIDENTIAL_FOLDER";
        } else if (codeUpper.contains("TEMP") || codeUpper.contains("TEMPORARY") || codeUpper.contains("TMP")) {
            return "TEMPORARY_FOLDER";
        } else if (codeUpper.contains("ARCH") || codeUpper.contains("ARCHIVE")) {
            return "ARCHIVE_FOLDER";
        } else if (codeUpper.contains("PRIO") || codeUpper.contains("PRIORITY") || codeUpper.contains("IMPORTANT")) {
            return "PRIORITY_FOLDER";
        } else if (codeUpper.contains("DOC") || codeUpper.contains("DOCUMENT")) {
            return "DOCUMENT_FOLDER";
        } else if (codeUpper.contains("REF") || codeUpper.contains("REFERENCE")) {
            return "REFERENCE_FOLDER";
        }
        return "STANDARD_FOLDER";
    }

    public String getShortDisplay() {
        return code + " - " + (designationFr != null && designationFr.length() > 30 ? 
                designationFr.substring(0, 30) + "..." : designationFr);
    }
}
