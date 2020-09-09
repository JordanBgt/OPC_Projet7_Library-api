package com.openclassrooms.library.mapper;

import com.openclassrooms.library.dto.LibraryDto;
import com.openclassrooms.library.entity.Library;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LibraryMapper {

    LibraryDto toLibraryDto(Library library);
}
