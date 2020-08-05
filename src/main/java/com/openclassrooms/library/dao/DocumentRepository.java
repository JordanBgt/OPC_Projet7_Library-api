package com.openclassrooms.library.dao;

import com.openclassrooms.library.entity.Document;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long>, QuerydslPredicateExecutor<Document> {
}
