package com.openclassrooms.library.service;

import com.openclassrooms.library.dao.PublisherRepository;
import com.openclassrooms.library.dto.PublisherDto;
import com.openclassrooms.library.entity.Publisher;
import com.openclassrooms.library.mapper.PublisherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service to manage publishers
 *
 * @see Publisher
 * @see PublisherDto
 * @see PublisherRepository
 * @see PublisherMapper
 */
@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private PublisherMapper publisherMapper;

    /**
     * Method to find all publishers
     *
     * @return a list of publishers
     * @see PublisherRepository#findAll()
     */
    public List<PublisherDto> findAll() {
        return publisherRepository.findAll().stream().map(publisherMapper::toPublisherDto).collect(Collectors.toList());
    }

    /**
     * Method to get one publisher by its id
     *
     * @param id id of the requested publisher
     *
     * @return a publisher
     * @see PublisherRepository#findById(Object)
     */
    public PublisherDto findById(Long id) {
        return publisherMapper.toPublisherDto(publisherRepository.findById(id).orElseThrow(EntityExistsException::new));
    }

    /**
     * Method to create or update a publisher
     *
     * @param publisherDto the publisher to save
     *
     * @return the saved publisher
     * @see PublisherRepository#save(Object)
     */
    public PublisherDto createOrUpdate(PublisherDto publisherDto) {
        Publisher publisher;
        if (publisherDto.getId() != null) {
            publisher = publisherRepository.findById(publisherDto.getId()).orElseThrow(EntityExistsException::new);
        } else {
            publisher = new Publisher();
        }
        publisher.setName(publisherDto.getName());

        return publisherMapper.toPublisherDto(publisherRepository.save(publisher));
    }

    /**
     * Method to delete a publisher by its id
     *
     * @param id id of the publisher to delete
     * @see PublisherRepository#deleteById(Object)
     */
    public void delete(Long id) {
        publisherRepository.deleteById(id);
    }
}
