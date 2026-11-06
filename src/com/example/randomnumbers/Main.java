package com.example.randomnumbers;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        long intervalMs = Long.parseLong(
                System.getProperty("interval.ms", "5000"));

        NumberGenerator generator = new NumberGenerator(new Random(), 100);
        NumberStorage storage = new NumberStorage();

        System.out.println("[MAIN] Started. Interval = " + intervalMs + "ms");

        boolean running = true;
        while (running) {
            int number = generator.generate();
            LocalDateTime now = LocalDateTime.now();
            storage.add(number, now);

            System.out.printf("[GENERATOR] Produced: %d at %s%n", number, now);

            if (number == 100) {
                running = false;
                System.out.println("[MAIN] 100 produced -> stopping.");
            } else {
                Thread.sleep(intervalMs);
            }
        }

        NumberClassifier classifier = new NumberClassifier();
        Map<Integer, NumberRecord> evenMap = classifier.separateEven(storage.getMainMap());
        Map<Integer, NumberRecord> oddMap = classifier.separateOdd(storage.getMainMap());

        printMap("MAIN MAP", storage.getMainMap());
        printMap("EVEN MAP", evenMap);
        printMap("ODD MAP", oddMap);
    }

    private static void printMap(String title, Map<Integer, NumberRecord> map) {
        System.out.println("\n=== " + title + " (" + map.size() + " items) ===");
        if (map.isEmpty()) {
            System.out.println("  (empty)");
            return;
        }
        for (Map.Entry<Integer, NumberRecord> entry : map.entrySet()) {
            System.out.printf("  [%d] %s%n", entry.getKey(), entry.getValue());
        }
    }
}