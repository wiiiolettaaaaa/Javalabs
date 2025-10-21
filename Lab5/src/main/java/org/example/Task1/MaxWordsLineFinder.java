package org.example.Task1;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MaxWordsLineFinder {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введіть назву файлу: ");
            String filePath = scanner.nextLine();

            try {
                String lineWithMaxWords = getLineWithMaxWords(filePath);
                int wordCount = countWords(lineWithMaxWords);
                System.out.println("Рядок з максимальною кількістю слів: ");
                System.out.println(lineWithMaxWords);
                System.out.println("Кількість слів у рядку: " + wordCount);
            } catch (IOException e) {
                System.out.println("Помилка при читанні файлу: " + e.getMessage());
            }
        }
    }

    private static String getLineWithMaxWords(String filePath) throws IOException {
        String lineWithMaxWords = "";
        int maxWordsCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                int wordCount = countWords(line);
                if (wordCount > maxWordsCount) {
                    maxWordsCount = wordCount;
                    lineWithMaxWords = line;
                }
            }
        }
        return lineWithMaxWords;
    }

    private static int countWords(String line) {
        String[] words = line.trim().split("\\s+");
        return words.length;
    }
}
