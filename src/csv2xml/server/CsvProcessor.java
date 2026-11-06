package csv2xml.server;

import csv2xml.common.Person;
import java.util.*;

public class CsvProcessor {

    public static List<Person> readCsv(String csv) {
        List<Person> list = new ArrayList<>();
        for (String line : csv.split("\\r?\\n")) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("id")) continue;
            String[] p = line.split(",");
            if (p.length < 4) continue;
            try {
                int id = Integer.parseInt(p[0].trim());
                String name = p[1].trim();
                int age = Integer.parseInt(p[2].trim());
                String city = p[3].trim();
                if (name.isEmpty()) continue;
                list.add(new Person(id, name, age, city));
            } catch (NumberFormatException e) {
                // ردیف خراب — نادیده بگیر
            }
        }
        return list;
    }

    public static void sort(List<Person> people) {
        people.sort(Comparator.comparingInt(p -> p.id));
    }

    public static String toXml(List<Person> people) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<people>\n");
        for (Person p : people) {
            sb.append("  <person>\n");
            sb.append("    <id>").append(p.id).append("</id>\n");
            sb.append("    <name>").append(p.name).append("</name>\n");
            sb.append("    <age>").append(p.age).append("</age>\n");
            sb.append("    <city>").append(p.city).append("</city>\n");
            sb.append("  </person>\n");
        }
        sb.append("</people>");
        return sb.toString();
    }
}