/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: RoomDTO
 *	@CreatedOn	: 12-11-2025
 *	@Updated	: 12-11-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: Common / Environment
 *
 **/

package dz.mdn.iaas.common.environment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import dz.mdn.iaas.common.environment.model.Bloc;
import dz.mdn.iaas.common.environment.model.Floor;
import dz.mdn.iaas.common.environment.model.Room;
import dz.mdn.iaas.configuration.template.GenericDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Room Data Transfer Object
 * Extends GenericDTO for automatic entity conversion
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDTO extends GenericDTO<Room> {

    @Size(max = 100, message = "Arabic designation must not exceed 100 characters")
    private String designationAr;

    @Size(max = 100, message = "English designation must not exceed 100 characters")
    private String designationEn;

    @NotBlank(message = "French designation is required")
    @Size(max = 100, message = "French designation must not exceed 100 characters")
    private String designationFr;

    @NotBlank(message = "Code is required")
    @Size(max = 50, message = "Code must not exceed 50 characters")
    private String code;

    @NotNull(message = "Bloc ID is required")
    private Long blocId;

    @NotNull(message = "Floor ID is required")
    private Long floorId;
    
    private BlocDTO bloc;

    private FloorDTO floor;

    @Override
    public Room toEntity() {
        Room entity = new Room();
        entity.setId(this.getId());
        entity.setDesignationAr(this.designationAr);
        entity.setDesignationEn(this.designationEn);
        entity.setDesignationFr(this.designationFr);
        entity.setCode(this.code);
        
        if (this.blocId != null) {
        	Bloc bloc = new Bloc();
        	bloc.setId(this.blocId);
            entity.setBloc(bloc);
        }
        
        if (this.floorId != null) {
        	Floor floor = new Floor();
        	floor.setId(this.floorId);
            entity.setFloor(floor);
        }
        
        return entity;
    }

    @Override
    public void updateEntity(Room entity) {
        if (this.designationAr != null) entity.setDesignationAr(this.designationAr);
        if (this.designationEn != null) entity.setDesignationEn(this.designationEn);
        if (this.designationFr != null) entity.setDesignationFr(this.designationFr);
        if (this.code != null) entity.setCode(this.code);
        
        if (this.blocId != null) {
        	Bloc bloc = new Bloc();
        	bloc.setId(this.blocId);
            entity.setBloc(bloc);
        }
        
        if (this.floorId != null) {
        	Floor floor = new Floor();
        	floor.setId(this.floorId);
            entity.setFloor(floor);
        }
        
    }

    public static RoomDTO fromEntity(Room entity) {
        if (entity == null) return null;
        return RoomDTO.builder()
                .id(entity.getId())
                .designationAr(entity.getDesignationAr())
                .designationEn(entity.getDesignationEn())
                .designationFr(entity.getDesignationFr())
                .code(entity.getCode())
                .blocId(entity.getBloc() != null ? entity.getBloc().getId() : null)
                .floorId(entity.getFloor() != null ? entity.getFloor().getId() : null)
                
                .bloc(entity.getBloc() != null ? BlocDTO.fromEntity(entity.getBloc()) : null)
                .floor(entity.getFloor() != null ? FloorDTO.fromEntity(entity.getFloor()) : null)
                .build();
    }
}