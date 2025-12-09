/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: GroupDTO
 *	@CreatedOn	: 11-18-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: System / Security
 *
 **/

package dz.mdn.iaas.system.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@Builder
@NoArgsConstructor          
@AllArgsConstructor         
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupDTO {
    private Long id;
    private String name;
    private String description;
    private Set<RoleDTO> roles;
}
