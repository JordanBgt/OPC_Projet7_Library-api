package com.openclassrooms.library.mapper;

import com.openclassrooms.library.dto.UserDTo;
import com.openclassrooms.library.dto.UserLightDto;
import com.openclassrooms.library.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {

    UserDTo toUserDto(User user);
    UserLightDto toUserLightDto(User user);
}
