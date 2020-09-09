package com.openclassrooms.library.mapper;

import com.openclassrooms.library.dto.PhotoDto;
import com.openclassrooms.library.entity.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PhotoMapper {

    @Mappings({
            @Mapping(target = "fileToBase64String", defaultValue = "null")
    })
    PhotoDto toPhotoDto(Photo photo);
}
