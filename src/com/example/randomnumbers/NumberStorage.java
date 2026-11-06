package com.example.randomnumbers;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class NumberStorage {

    private final Map<Integer, NumberRecord> mainMap = new HashMap<>();
    private int counter = 0;

    public void add(int value, LocalDateTime time) {
        if (time == null) {
            throw new IllegalArgumentException("time cannot be null");
        }
        counter++;
        mainMap.put(counter, new NumberRecord(value, time));
    }

    public Map<Integer, NumberRecord> getMainMap() {
        return Collections.unmodifiableMap(mainMap);
    }

    public int size() {
        return mainMap.size();
    }
}