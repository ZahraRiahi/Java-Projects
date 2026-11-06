package com.example.randomnumbers;

import java.time.LocalDateTime;

public class NumberRecord {

    private final int value;
    private final LocalDateTime time;

    public NumberRecord(int value, LocalDateTime time) {
        this.value = value;
        this.time = time;
    }

    public int getValue() {
        return value;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "value=" + value + ", time=" + time;
    }
}