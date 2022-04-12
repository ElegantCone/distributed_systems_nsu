package ru.nsu.fit.saraeva.lab2.enums;

import lombok.Getter;

public enum LoggerMessages {
    TAG_PARSING_PROCESS("Parsing tags: {0}"),
    PARSING_STARTED("Parsing started"),
    PARSING_FINISHED("Parsing finished"),
    TIMER_MESSAGE("Writing speed: {0} rows per second");

    @Getter
    private final String message;

    LoggerMessages(String s) {
        message = s;
    }
}
