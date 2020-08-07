package com.openclassrooms.library.dao;

import com.openclassrooms.library.entity.Exemplar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExemplarRepository extends CrudRepository<Exemplar, Long> {
    List<Exemplar> findAllByDocumentId(Long documentId);
}
