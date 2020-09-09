package com.openclassrooms.library.dao;

import com.openclassrooms.library.dto.ExemplarAvailableDto;
import com.openclassrooms.library.entity.Exemplar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ExemplarRepository extends CrudRepository<Exemplar, Long> {

    List<Exemplar> findAllByDocumentId(Long documentId);

    @Query(value = "SELECT COUNT(*) as number, name as libraryName, ex.id as exemplarId " +
            "FROM exemplar as ex " +
            "INNER JOIN library as lib ON library_id = lib.id " +
            "WHERE document_id = ? " +
            "AND ex.id not in (SELECT exemplar_id from loan where state = 'PENDING')" +
            "GROUP BY library_id",
            nativeQuery = true)
    Collection<ExemplarAvailableDto> findAllAvailableByDocumentId(Long documentId);
}
