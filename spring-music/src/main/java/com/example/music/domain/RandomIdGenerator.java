package com.example.music.domain;

import java.util.UUID;

public class RandomIdGenerator {
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
