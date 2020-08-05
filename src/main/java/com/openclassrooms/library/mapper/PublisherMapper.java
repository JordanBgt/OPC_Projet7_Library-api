package com.openclassrooms.library.mapper;

import com.openclassrooms.library.dto.PublisherDto;
import com.openclassrooms.library.entity.Publisher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

    PublisherDto toPublisherDto(Publisher publisher);
    Publisher toPublisher(PublisherDto publisherDto);
}
