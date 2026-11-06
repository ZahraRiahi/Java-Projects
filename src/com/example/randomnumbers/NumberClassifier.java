package com.example.randomnumbers;

import com.example.randomnumbers.NumberRecord;

import java.util.HashMap;
import java.util.Map;

public class NumberClassifier {

    public Map<Integer, NumberRecord> separateEven(Map<Integer, NumberRecord> mainMap) {
        Map<Integer, NumberRecord> evenMap = new HashMap<>();
        for (Map.Entry<Integer, NumberRecord> entry : mainMap.entrySet()) {
            if (entry.getValue().getValue() % 2 == 0) {
                evenMap.put(entry.getKey(), entry.getValue());
            }
        }
        return evenMap;
    }

    public Map<Integer, NumberRecord> separateOdd(Map<Integer, NumberRecord> mainMap) {
        Map<Integer, NumberRecord> oddMap = new HashMap<>();
        for (Map.Entry<Integer, NumberRecord> entry : mainMap.entrySet()) {
            if (entry.getValue().getValue() % 2 != 0) {
                oddMap.put(entry.getKey(), entry.getValue());
            }
        }
        return oddMap;
    }
}