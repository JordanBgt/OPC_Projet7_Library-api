package com.openclassrooms.library.mapper;

import com.openclassrooms.library.dto.PhotoDto;
import com.openclassrooms.library.entity.Photo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhotoMapper {

    PhotoDto toPhotoDto(Photo photo);
    Photo toPhoto(PhotoDto photoDto);
}
