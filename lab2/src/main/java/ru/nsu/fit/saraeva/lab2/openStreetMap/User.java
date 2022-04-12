package ru.nsu.fit.saraeva.lab2.openStreetMap;

import lombok.Getter;

@Getter
public class User {
    private final String name;
    private final int changeCount;

    public User(String name, int changeCount) {
        this.name = name;
        this.changeCount = changeCount;
    }

    @Override
    public String toString() {
        return name + " " + changeCount;
    }
}
