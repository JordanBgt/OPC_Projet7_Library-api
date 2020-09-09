package com.openclassrooms.library.mapper;

import com.openclassrooms.library.dto.DocumentDto;
import com.openclassrooms.library.dto.DocumentLightDto;
import com.openclassrooms.library.entity.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class, PublisherMapper.class})
public interface DocumentMapper {

    @Mappings({
            @Mapping(target = "type", source = "type.label"),
            @Mapping(target = "category", source = "category.label")
    })
    DocumentDto toDocumentDto(Document document);

    @Mappings({
            @Mapping(target = "authorFirstName", source = "author.firstName"),
            @Mapping(target = "authorLastName", source = "author.lastName")
    })
    DocumentLightDto toDocumentLightDto(Document document);
}
