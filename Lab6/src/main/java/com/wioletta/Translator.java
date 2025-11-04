package com.wioletta;

import java.util.HashMap;
import java.util.InputMismatchException;

public class Translator {
    private final HashMap<String, String> dictionary;

    public Translator() {
        this.dictionary = new HashMap<>();
    }

    public void addWord(String englishWord, String ukrainianWord) {
        validateEnglishWord(englishWord);
        validateUkrainianWord(ukrainianWord);
        dictionary.put(englishWord.toLowerCase(), ukrainianWord.toLowerCase());
    }

    public String translatePhrase(String phrase) {
        StringBuilder translatedPhrase = new StringBuilder();
        String[] words = phrase.toLowerCase().split("\\s+");

        for (String word : words) {
            if (dictionary.containsKey(word)) translatedPhrase.append(dictionary.get(word)).append(" ");
            else throw new InputMismatchException("No translation found for '" + word + "'");
        }

        return translatedPhrase.toString().trim();
    }

    private void validateUkrainianWord(String ukrainianTranslation) {
        if (!ukrainianTranslation.matches("[А-Яа-яҐґЄєЇїІі'\\-\\s]+")) {
            throw new InputMismatchException("Ukrainian should use the Cyrillic script");
        }
    }

    private void validateEnglishWord(String englishWord) {
        if (!englishWord.matches("[A-Za-z\\-\\s]+")) {
            throw new InputMismatchException("English should use the Latin script");
        }
    }
}