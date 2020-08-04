package com.openclassrooms.library.dao;

import com.openclassrooms.library.entity.Exemplar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemplarRepository extends CrudRepository<Exemplar, Long> {
}
