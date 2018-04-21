package com.n11.presentation.enums;

/**
 * Created by Emre on 20.04.2018.
 */
public enum Exception {
    DEFAULT("Hata"),
    DATA_NOT_FOUND("Veri bulunamdı");

    private String type;

    Exception(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static Exception get(String type) {

        for (Exception exceptions : values()) {
            if (exceptions.getType().equalsIgnoreCase(type)) return exceptions;
        }

        return DEFAULT;
    }
}