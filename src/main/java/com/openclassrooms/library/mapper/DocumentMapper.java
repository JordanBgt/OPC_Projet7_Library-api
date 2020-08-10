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

    default DocumentLightDto toDocumentLightDto(Document document) {
        String authorName = document.getAuthor().getFirstName() + ' ' + document.getAuthor().getLastName();
        return new DocumentLightDto(document.getId(), document.getTitle(), authorName);
    }

    @Mappings({
            @Mapping(target = "photo", defaultValue = "null")
    })
    Document toDocument(DocumentDto documentDto);
}
