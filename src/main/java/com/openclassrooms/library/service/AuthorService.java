package com.openclassrooms.library.service;

import com.openclassrooms.library.dao.AuthorRepository;
import com.openclassrooms.library.dto.AuthorDto;
import com.openclassrooms.library.entity.Author;
import com.openclassrooms.library.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service to manage Author
 *
 * @see Author
 * @see AuthorDto
 * @see AuthorRepository
 * @see AuthorMapper
 */
@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    /**
     * Method to get all authors from the database
     *
     * @return a list of authors
     * @see AuthorRepository#findAll()
     */
    public List<AuthorDto> findAll() {
        return authorRepository.findAll().stream().map(authorMapper::toAuthorDto).collect(Collectors.toList());
    }

    /**
     * Method to get an author by its id
     *
     * @param id id of the requested author
     *
     * @return an author
     * @see AuthorRepository#findById(Object)
     */
    public AuthorDto findById(Long id) {
        return authorMapper.toAuthorDto(authorRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    /**
     * Method to create or update an author
     *
     * @param authorDto the author to save
     *
     * @return the saved author
     * @see AuthorRepository#save(Object)
     */
    public AuthorDto createOrUpdate(AuthorDto authorDto) {
        Author author;
        if (authorDto.getId() != null) {
            author = authorRepository.findById(authorDto.getId()).orElseThrow(EntityNotFoundException::new);
        } else {
            author = new Author();
        }
        author.setLastName(authorDto.getLastName());
        author.setFirstName(authorDto.getFirstName());

        return authorMapper.toAuthorDto(authorRepository.save(author));
    }

    /**
     * Method to delete an author
     *
     * @param id id of the author to delete
     * @see AuthorRepository#deleteById(Object)
     */
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
