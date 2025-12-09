/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: UserDTO
 *	@CreatedOn	: 11-18-2025
 *
 *	@Type		: Class
 *	@Layer		: DTO
 *	@Package	: System / Security
 *
 **/

package dz.mdn.iaas.system.security.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor          
@AllArgsConstructor         
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
	
    private Long id;
    
    private String username;
    
    private String email;
    
    private String password;
    
    @Builder.Default
    private boolean enabled = true;
    
    @Builder.Default
    private boolean accountNonExpired = true;
    
    @Builder.Default
    private boolean accountNonLocked = true;
    
    @Builder.Default
    private boolean credentialsNonExpired = true;
    
    private Set<RoleDTO> roles;
    
    private Set<GroupDTO> groups;
    
}
