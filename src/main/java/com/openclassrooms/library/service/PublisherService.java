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

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private PublisherMapper publisherMapper;

    public List<PublisherDto> findAll() {
        return publisherRepository.findAll().stream().map(publisherMapper::toPublisherDto).collect(Collectors.toList());
    }

    public PublisherDto findById(Long id) {
        return publisherMapper.toPublisherDto(publisherRepository.findById(id).orElseThrow(EntityExistsException::new));
    }

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

    public void delete(Long id) {
        publisherRepository.deleteById(id);
    }
}
