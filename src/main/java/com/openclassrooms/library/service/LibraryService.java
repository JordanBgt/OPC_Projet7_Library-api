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

/**
 * Method to manage libraries
 *
 * @see Library
 * @see LibraryDto
 * @see LibraryMapper
 * @see LibraryRepository
 */
@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private LibraryMapper libraryMapper;

    /**
     * Method to find all libraries
     *
     * @return a list of libraries
     * @see LibraryRepository#findAll()
     */
    public List<LibraryDto> findAll() {
        return libraryRepository.findAll().stream().map(libraryMapper::toLibraryDto).collect(Collectors.toList());
    }


    /**
     * Method to get one library by its id
     *
     * @param id id of the requested library
     *
     * @return a library
     * @see LibraryRepository#findById(Object)
     */
    public LibraryDto findById(Long id) {
        return libraryMapper.toLibraryDto(libraryRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    /**
     * Method to create or update a library
     *
     * @param libraryDto the library to save
     *
     * @return the saved library
     * @see LibraryRepository#save(Object)
     */
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

    /**
     * Method to delete a library by its id
     *
     * @param id id of the library to delete
     * @see LibraryRepository#deleteById(Object)
     */
    public void delete(Long id) {
        libraryRepository.deleteById(id);
    }
}
