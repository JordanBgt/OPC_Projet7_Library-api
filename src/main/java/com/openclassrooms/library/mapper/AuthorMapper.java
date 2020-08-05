package com.openclassrooms.library.mapper;

import com.openclassrooms.library.dto.AuthorDto;
import com.openclassrooms.library.entity.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto toAuthorDto(Author author);
    Author toAuthor(AuthorDto authorDto);
}
