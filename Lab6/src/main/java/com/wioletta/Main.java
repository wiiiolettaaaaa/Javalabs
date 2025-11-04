package com.wioletta;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Translator translator = new Translator();

        translator.addWord("hello", "привіт");
        translator.addWord("world", "світ");
        translator.addWord("how", "як");
        translator.addWord("are", "є");
        translator.addWord("you", "ти");

        System.out.println("Введіть кількість слів, які ви хочете додати до словника:");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Введіть англійське слово:");
            String englishWord = scanner.nextLine();
            System.out.println("Введіть його переклад українською:");
            String ukrainianWord = scanner.nextLine();
            translator.addWord(englishWord, ukrainianWord);
        }

        System.out.println("Введіть фразу англійською мовою для перекладу:");
        String phrase = scanner.nextLine();

        String translatedPhrase = translator.translatePhrase(phrase);
        System.out.println("Переклад: " + translatedPhrase);
    }
}