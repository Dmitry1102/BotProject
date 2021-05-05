package com.telegram.project;

public enum Cities {
    MOSCOW("/Moscow"),BERLIN("/Berlin"),LONDON("/London"),WARSAW("/Warsaw");

    private String name;

    Cities(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
