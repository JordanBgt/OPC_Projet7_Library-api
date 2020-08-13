package com.openclassrooms.library.dao.predicate;

import com.openclassrooms.library.entity.EDocumentCategory;
import com.openclassrooms.library.entity.EDocumentType;
import com.openclassrooms.library.entity.criteria.DocumentSearch;
import com.openclassrooms.library.entity.QDocument;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

public class DocumentPredicateBuilder {

    public static final QDocument document = QDocument.document;

    public static Predicate buildSearch(DocumentSearch documentSearch) {
        return new BooleanBuilder()
                .and(isTitle(documentSearch.getTitle()))
                .and(isIsbn(documentSearch.getIsbn()))
                .and(isAuthor(documentSearch.getAuthorLastName()))
                .and(isPublisher(documentSearch.getPublisherName()))
                .and(isType(documentSearch.getType()))
                .and(isCategory(documentSearch.getCategory()));
    }

    private static BooleanExpression isTitle(String title) {
        return title != null ? document.title.containsIgnoreCase(title) : null;
    }

    private static BooleanExpression isIsbn(String isbn) {
        return isbn != null ? document.isbn.eq(isbn) : null;
    }

    private static BooleanExpression isAuthor(String authorName) {
        return authorName != null ? document.author.lastName.containsIgnoreCase(authorName)
                .or(document.author.firstName.containsIgnoreCase(authorName)) : null;
    }

    private static BooleanExpression isPublisher(String publisherName) {
        return publisherName != null ? document.publisher.name.containsIgnoreCase(publisherName) : null;
    }

    private static BooleanExpression isType(EDocumentType type) {
        return type != null ? document.type.eq(type) : null;
    }

    private static BooleanExpression isCategory(EDocumentCategory category) {
        return category != null ? document.category.eq(category) : null;
    }
}
