package com.example.randomnumbers;

import java.util.Random;

public class NumberGenerator {

    private final Random random;
    private final int max;

    public NumberGenerator(Random random, int max) {
        if (random == null) {
            throw new IllegalArgumentException("random cannot be null");
        }
        if (max < 1) {
            throw new IllegalArgumentException("max must be >= 1");
        }
        this.random = random;
        this.max = max;
    }

    public int generate() {
        return random.nextInt(max) + 1;
    }
}