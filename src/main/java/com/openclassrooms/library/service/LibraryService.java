package com.openclassrooms.library.service;

import com.openclassrooms.library.dao.LibraryRepository;
import com.openclassrooms.library.dto.LibraryDto;
import com.openclassrooms.library.entity.Library;
import com.openclassrooms.library.mapper.LibraryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private LibraryMapper libraryMapper;

    public List<LibraryDto> findAll() {
        return libraryRepository.findAll().stream().map(libraryMapper::toLibraryDto).collect(Collectors.toList());
    }

    public LibraryDto findById(Long id) {
        return libraryMapper.toLibraryDto(libraryRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public LibraryDto createOrUpdate(LibraryDto libraryDto) {
        Library library;
        if (libraryDto.getId() != null) {
            library = libraryRepository.findById(libraryDto.getId()).orElseThrow(EntityNotFoundException::new);
        } else {
            library = new Library();
        }
        library.setName(libraryDto.getName());

        return libraryMapper.toLibraryDto(libraryRepository.save(library));
    }

    public void delete(Long id) {
        libraryRepository.deleteById(id);
    }
}
