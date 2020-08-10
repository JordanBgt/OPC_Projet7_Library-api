package com.openclassrooms.library.entity;

import java.util.HashMap;
import java.util.Map;

public enum EDocumentType {
    BOOK("Livre"),
    PRESS("Presse"),
    COMIC("Bande dessinée");

    private final String label;
    private static final Map<String, EDocumentType> mapLabel = new HashMap<>();

    static {
        for (EDocumentType type : EDocumentType.values()) {
            mapLabel.put(type.label, type);
        }
    }

    EDocumentType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static EDocumentType get(String label) {
        return mapLabel.get(label);
    }
}
