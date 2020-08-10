package com.openclassrooms.library.entity;

import java.util.HashMap;
import java.util.Map;

public enum EDocumentCategory {
    TECHNOLOGY("Informatique"),
    SCIENCE_FICTION("Science-Fiction"),
    NOVEL("Roman"),
    LITERATURE("Littérature");

    private final String label;
    private static final Map<String, EDocumentCategory> mapLabel = new HashMap<>();

    static {
        for (EDocumentCategory category : EDocumentCategory.values()) {
            mapLabel.put(category.label, category);
        }
    }

    EDocumentCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static EDocumentCategory get(String label) {
        return mapLabel.get(label);
    }
}
