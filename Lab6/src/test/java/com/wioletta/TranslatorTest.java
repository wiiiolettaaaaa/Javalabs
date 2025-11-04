package com.wioletta;


import org.junit.Before;
import org.junit.Test;

import java.util.InputMismatchException;

import static org.junit.Assert.assertEquals;

public class TranslatorTest {
    private Translator translator;

    @Before
    public void setUp() {
        translator = new Translator();
    }

    @Test
    public void testAddWordSuccessfully() {
        translator.addWord("hello", "привіт");
        translator.addWord("world", "світ");

        String phrase = translator.translatePhrase("hello world");
        assertEquals("привіт світ", phrase);
    }

    @Test(expected = InputMismatchException.class)
    public void testAddInvalidEnglishWord() {
        translator.addWord("привіт", "hello");
    }

    @Test(expected = InputMismatchException.class)
    public void testAddInvalidUkrainianWord() {
        translator.addWord("hello", "hello123");
    }

    @Test
    public void testTranslatePhraseSuccessfully() {
        translator.addWord("cat", "кіт");
        translator.addWord("dog", "пес");

        String translation = translator.translatePhrase("cat dog");
        assertEquals("кіт пес", translation);
    }

    @Test(expected = InputMismatchException.class)
    public void testTranslateWithUnknownWord() {
        translator.addWord("apple", "яблуко");
        translator.translatePhrase("apple orange");
    }

    @Test
    public void testTranslatePhraseWithMixedCase() {
        translator.addWord("Hello", "Привіт");
        translator.addWord("WoRLd", "Світ");

        String translation = translator.translatePhrase("hello WORLD");
        assertEquals("привіт світ", translation);
    }

    @Test(expected = InputMismatchException.class)
    public void testTranslateEmptyPhrase() {
        translator.translatePhrase("");
    }
}