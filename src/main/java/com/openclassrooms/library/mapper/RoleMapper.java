package com.openclassrooms.library.mapper;

import com.openclassrooms.library.dto.RoleDto;
import com.openclassrooms.library.entity.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toRoleDto(Role role);
    List<RoleDto> toListRoleDto(List<Role> roles);
}
