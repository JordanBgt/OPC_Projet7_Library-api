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

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    public List<AuthorDto> findAll() {
        return authorRepository.findAll().stream().map(authorMapper::toAuthorDto).collect(Collectors.toList());
    }

    public AuthorDto findById(Long id) {
        return authorMapper.toAuthorDto(authorRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

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

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
