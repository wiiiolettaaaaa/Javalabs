package org.wioletta.Task4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagFrequencyCounter {

    public static void main(String[] args) {
        String urlString = "https://schedule.kpi.ua/";

        try {
            String htmlContent = fetchHTMLContent(urlString);

            Map<String, Integer> tagFrequencyMap = countTags(htmlContent);

            System.out.println("Частота тегів у лексикографічному порядку:");
            tagFrequencyMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

            System.out.println("\nЧастота тегів за кількістю появи:");
            tagFrequencyMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        } catch (Exception e) {
            System.out.println("Помилка завантаження сторінки: " + e.getMessage());
        }
    }

    private static String fetchHTMLContent(String urlString) throws Exception {
        StringBuilder content = new StringBuilder();
        URI uri = new URI(urlString);
        URL url = uri.toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }

    private static Map<String, Integer> countTags(String htmlContent) {
        Map<String, Integer> tagFrequencyMap = new HashMap<>();
        Pattern pattern = Pattern.compile("<(\\w+)(\\s|>)");
        Matcher matcher = pattern.matcher(htmlContent);

        while (matcher.find()) {
            String tag = matcher.group(1).toLowerCase();
            tagFrequencyMap.put(tag, tagFrequencyMap.getOrDefault(tag, 0) + 1);
        }
        return tagFrequencyMap;
    }
}
