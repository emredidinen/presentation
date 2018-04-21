package com.n11.presentation.enums;

/**
 * Created by Emre on 20.04.2018.
 */
public enum Success {
    DEFAULT("İşlem başarılı"),
    DEFAULT_DATA_ADDED("Örnek Veriler Eklendi"),
    PRESENTATION_CREATED("Sunum Eklendi."),
    PRESENTATION_UPDATED("Sunum Güncellendi."),
    PRESENTATION_DELETED("Sunum Silindi.");

    private String type;

    Success(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static Success get(String type) {
        for (Success success : values()) {
            if (success.getType().equalsIgnoreCase(type)) return success;
        }
        return DEFAULT;
    }
}
