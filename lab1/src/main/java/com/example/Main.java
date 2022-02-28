package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOG.info("Application started");
        System.out.println("Hello, world!");
        LOG.info("Application finished");
    }
}