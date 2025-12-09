/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: GroupService
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
import org.springframework.transaction.annotation.Transactional;

import dz.mdn.iaas.system.security.dto.GroupDTO;
import dz.mdn.iaas.system.security.model.Group;
import dz.mdn.iaas.system.security.repository.GroupRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public List<GroupDTO> findAll() {
        return groupRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public GroupDTO findById(Long id) {
        return groupRepository.findById(id)
            .map(this::convertToDTO)
            .orElseThrow(() -> new RuntimeException("Group not found"));
    }

    @Transactional
    public GroupDTO create(GroupDTO dto) {
        Group group = new Group();
        group.setName(dto.getName());
        group.setDescription(dto.getDescription());
        return convertToDTO(groupRepository.save(group));
    }

    @Transactional
    public GroupDTO update(Long id, GroupDTO dto) {
        Group group = groupRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Group not found"));
        group.setName(dto.getName());
        group.setDescription(dto.getDescription());
        return convertToDTO(groupRepository.save(group));
    }

    @Transactional
    public void delete(Long id) {
        groupRepository.deleteById(id);
    }

    /**
     * Convert single Group entity to GroupDTO
     */
    public GroupDTO convertToDTO(Group group) {
        if (group == null) {
            return null;
        }
        return GroupDTO.builder()
            .id(group.getId())
            .name(group.getName())
            .description(group.getDescription())
            .build();
    }

    /**
     * Convert Set of Group entities to Set of GroupDTOs
     * ✅ NEW METHOD for use by other services
     */
    public Set<GroupDTO> convertToDTO(Set<Group> groups) {
        if (groups == null || groups.isEmpty()) {
            return Set.of();
        }
        return groups.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toSet());
    }

    /**
     * Convert Set of GroupDTOs to Set of Group entities
     * ✅ NEW METHOD for use by other services
     */
    public Set<Group> convertToEntity(Set<GroupDTO> groupDTOs) {
        if (groupDTOs == null || groupDTOs.isEmpty()) {
            return Set.of();
        }
        return groupDTOs.stream()
            .map(groupDTO -> groupRepository.findById(groupDTO.getId())
                .orElseThrow(() -> new RuntimeException("Group not found: " + groupDTO.getId())))
            .collect(Collectors.toSet());
    }
}
