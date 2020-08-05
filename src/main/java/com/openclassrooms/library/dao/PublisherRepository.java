package com.openclassrooms.library.dao;

import com.openclassrooms.library.entity.Publisher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends CrudRepository<Publisher, Long> {
    List<Publisher> findAll();
}
