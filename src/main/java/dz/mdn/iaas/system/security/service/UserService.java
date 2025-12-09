/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: UserService
 *	@CreatedOn	: 11-18-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: System / Security
 *
 **/

package dz.mdn.iaas.system.security.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.mdn.iaas.system.security.dto.UserDTO;
import dz.mdn.iaas.system.security.model.Role;
import dz.mdn.iaas.system.security.model.User;
import dz.mdn.iaas.system.security.repository.RoleRepository;
import dz.mdn.iaas.system.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    
    // ✅ Inject RoleService and GroupService for conversions
    private final RoleService roleService;
    private final GroupService groupService;

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public UserDTO findById(Long id) {
        return userRepository.findById(id)
            .map(this::convertToDTO)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public UserDTO create(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEnabled(dto.isEnabled());
        user.setAccountNonExpired(dto.isAccountNonExpired());
        user.setAccountNonLocked(dto.isAccountNonLocked());
        user.setCredentialsNonExpired(dto.isCredentialsNonExpired());

        // ✅ Use RoleService to convert DTOs to entities
        if (dto.getRoles() != null && !dto.getRoles().isEmpty()) {
            user.setRoles(roleService.convertToEntity(dto.getRoles()));
        }

        // ✅ Use GroupService to convert DTOs to entities
        if (dto.getGroups() != null && !dto.getGroups().isEmpty()) {
            user.setGroups(groupService.convertToEntity(dto.getGroups()));
        }

        return convertToDTO(userRepository.save(user));
    }

    @Transactional
    public UserDTO update(Long id, UserDTO dto) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

        user.setEmail(dto.getEmail());
        user.setEnabled(dto.isEnabled());
        user.setAccountNonExpired(dto.isAccountNonExpired());
        user.setAccountNonLocked(dto.isAccountNonLocked());
        user.setCredentialsNonExpired(dto.isCredentialsNonExpired());

        // Update password only if provided
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        // ✅ Use RoleService to convert DTOs to entities
        if (dto.getRoles() != null) {
            user.setRoles(roleService.convertToEntity(dto.getRoles()));
        }

        // ✅ Use GroupService to convert DTOs to entities
        if (dto.getGroups() != null) {
            user.setGroups(groupService.convertToEntity(dto.getGroups()));
        }

        return convertToDTO(userRepository.save(user));
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public UserDTO assignRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepository.findById(roleId)
            .orElseThrow(() -> new RuntimeException("Role not found"));

        user.getRoles().add(role);
        return convertToDTO(userRepository.save(user));
    }

    /**
     * Convert User entity to UserDTO
     * ✅ Uses RoleService and GroupService for nested conversions
     */
    private UserDTO convertToDTO(User user) {
        return UserDTO.builder()
            .id(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .enabled(user.isEnabled())
            .accountNonExpired(user.isAccountNonExpired())
            .accountNonLocked(user.isAccountNonLocked())
            .credentialsNonExpired(user.isCredentialsNonExpired())
            .roles(roleService.convertToDTO(user.getRoles()))      // ✅ Use service
            .groups(groupService.convertToDTO(user.getGroups()))   // ✅ Use service
            .build();
    }
}
