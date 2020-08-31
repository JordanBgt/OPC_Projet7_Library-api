package com.openclassrooms.library.mapper;

import com.openclassrooms.library.dto.ExemplarDto;
import com.openclassrooms.library.dto.ExemplarLightDto;
import com.openclassrooms.library.entity.Exemplar;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {DocumentMapper.class, LibraryMapper.class})
public interface ExemplarMapper {

    ExemplarDto toExemplarDto(Exemplar exemplar);

    @Mappings({
            @Mapping(target = "documentTitle", source = "document.title"),
            @Mapping(target = "loanEndDate", defaultValue = "null")
    })
    ExemplarLightDto toExemplarLightDto(Exemplar exemplar);
}
