/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: PermissionService
 *	@CreatedOn	: 11-18-2025
 *
 *	@Type		: Class
 *	@Layer		: Service
 *	@Package	: System / Security
 *
 **/

package dz.mdn.iaas.system.security.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dz.mdn.iaas.system.security.dto.PermissionDTO;
import dz.mdn.iaas.system.security.model.Permission;
import dz.mdn.iaas.system.security.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public List<PermissionDTO> findAll() {
        return permissionRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public PermissionDTO findById(Long id) {
        return permissionRepository.findById(id)
            .map(this::convertToDTO)
            .orElseThrow(() -> new RuntimeException("Permission not found"));
    }

    /**
     * Convert single Permission entity to PermissionDTO
     */
    public PermissionDTO convertToDTO(Permission permission) {
        if (permission == null) {
            return null;
        }
        return PermissionDTO.builder()
            .id(permission.getId())
            .name(permission.getName())
            .description(permission.getDescription())
            // Note: Not including authority to avoid deep nesting
            .build();
    }

    /**
     * Convert Set of Permission entities to Set of PermissionDTOs
     */
    public Set<PermissionDTO> convertToDTO(Set<Permission> permissions) {
        if (permissions == null || permissions.isEmpty()) {
            return Set.of();
        }
        return permissions.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toSet());
    }

    /**
     * Convert Set of PermissionDTOs to Set of Permission entities
     */
    public Set<Permission> convertToEntity(Set<PermissionDTO> permissionDTOs) {
        if (permissionDTOs == null || permissionDTOs.isEmpty()) {
            return Set.of();
        }
        return permissionDTOs.stream()
            .map(permissionDTO -> permissionRepository.findById(permissionDTO.getId())
                .orElseThrow(() -> new RuntimeException("Permission not found: " + permissionDTO.getId())))
            .collect(Collectors.toSet());
    }
}
