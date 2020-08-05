package com.openclassrooms.library.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DocumentMapper.class, LibraryMapper.class})
public interface ExemplarMapper {
}
