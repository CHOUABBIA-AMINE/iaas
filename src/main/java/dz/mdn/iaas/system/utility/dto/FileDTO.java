/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: FileDTO
 *	@CreatedOn	: 10-14-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: DTO
 *	@Layer		: DTO
 *	@Package	: System / Utility
 *
 **/

package dz.mdn.iaas.system.utility.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dz.mdn.iaas.system.utility.model.File;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileDTO {

    private Long id;

    @NotBlank(message = "Extension is required")
    @Size(max = 20, message = "Extension must not exceed 20 characters")
    private String extension;

    @Min(value = 0, message = "Size must not be negative")
    private Long size;

    @NotBlank(message = "Path is required")
    @Size(max = 250, message = "Path must not exceed 250 characters")
    private String path;

    @Size(max = 20, message = "File type must not exceed 20 characters")
    private String fileType;

    // Audit fields
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

    /**
     * Create DTO from entity
     */
    public static FileDTO fromEntity(File file) {
        if (file == null) return null;
        
        return FileDTO.builder()
                .id(file.getId())
                .extension(file.getExtension())
                .size(file.getSize())
                .path(file.getPath())
                .fileType(file.getFileType())
                .createdAt(file.getCreatedAt())
                .updatedAt(file.getUpdatedAt())
                .createdBy(file.getCreatedBy())
                .updatedBy(file.getUpdatedBy())
                .build();
    }

    /**
     * Convert to entity
     */
    public File toEntity() {
        File file = new File();
        file.setId(this.id);
        file.setExtension(this.extension);
        file.setSize(this.size != null ? this.size : 0L);
        file.setPath(this.path);
        file.setFileType(this.fileType);
        return file;
    }

    /**
     * Update entity from DTO
     */
    public void updateEntity(File file) {
        if (this.extension != null) file.setExtension(this.extension);
        if (this.size != null) file.setSize(this.size);
        if (this.path != null) file.setPath(this.path);
        if (this.fileType != null) file.setFileType(this.fileType);
    }
}
