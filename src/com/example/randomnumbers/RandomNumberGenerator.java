package com.example.randomnumbers;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomNumberGenerator {

    public static void main(String[] args) {
        Random random = new Random();

        // مپ اصلی: همه اعداد با زمان
        HashMap<String, Integer> allNumbersMap = new HashMap<>();

        // مپ اعداد زوج (کلید: زمان، مقدار: عدد زوج)
        HashMap<String, Integer> evenNumbersMap = new HashMap<>();

        // مپ اعداد فرد (کلید: زمان، مقدار: عدد فرد)
        HashMap<String, Integer> oddNumbersMap = new HashMap<>();

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        while (true) {
            int number = random.nextInt(101);
            String currentTime = sdf.format(new java.util.Date());

            // ذخیره در مپ اصلی
            allNumbersMap.put(currentTime, number);

            // ذخیره در مپ زوج یا فرد بر اساس عدد
            if (number % 2 == 0) {
                evenNumbersMap.put(currentTime, number);
                System.out.println("زمان: " + currentTime + " | عدد زوج: " + number);
            } else {
                oddNumbersMap.put(currentTime, number);
                System.out.println("زمان: " + currentTime + " | عدد فرد: " + number);
            }

            if (number == 100) {
                System.out.println("\nعدد 100 تولید شد. برنامه پایان یافت.");
                break;
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("برنامه متوقف شد.");
                Thread.currentThread().interrupt();
                break;
            }
        }

        // چاپ محتوای مپ‌ها
        System.out.println("\n--- اعداد زوج ---");
        printMap(evenNumbersMap);
        System.out.println("تعداد اعداد زوج: " + evenNumbersMap.size());

        System.out.println("\n--- اعداد فرد ---");
        printMap(oddNumbersMap);
        System.out.println("تعداد اعداد فرد: " + oddNumbersMap.size());

        System.out.println("\n--- همه اعداد ---");
        printMap(allNumbersMap);
        System.out.println("تعداد کل: " + allNumbersMap.size());
    }

    private static void printMap(HashMap<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("  " + entry.getKey() + " => " + entry.getValue());
        }
    }
}