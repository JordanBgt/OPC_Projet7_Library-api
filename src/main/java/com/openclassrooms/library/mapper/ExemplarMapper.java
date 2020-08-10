package com.openclassrooms.library.mapper;

import com.openclassrooms.library.dto.ExemplarDto;
import com.openclassrooms.library.dto.ExemplarLightDto;
import com.openclassrooms.library.entity.Exemplar;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DocumentMapper.class, LibraryMapper.class})
public interface ExemplarMapper {

    ExemplarDto toExemplarDto(Exemplar exemplar);
    ExemplarLightDto toExemplarLightDto(Exemplar exemplar);
}
