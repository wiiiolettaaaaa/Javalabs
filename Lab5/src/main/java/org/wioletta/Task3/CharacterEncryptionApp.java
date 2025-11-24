package org.wioletta.Task3;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class CharacterEncryptionApp {
    private static final Logger logger = LogManager.getLogger(CharacterEncryptionApp.class);

    public static void main(String[] args) {
        Configurator.setRootLevel(Level.DEBUG);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Locale locale = selectLanguage(reader);
            ResourceBundle messages = ResourceBundle.getBundle("location.messages", locale);
            logger.info(messages.getString("start"));

            System.out.print(messages.getString("enterInputFilePath"));
            String inputFilePath = reader.readLine();

            System.out.print(messages.getString("enterEncryptedFilePath"));
            String encryptedFilePath = reader.readLine();

            System.out.print(messages.getString("enterDecryptedFilePath"));
            String decryptedFilePath = reader.readLine();

            System.out.print(messages.getString("key"));
            String keyInput = reader.readLine();
            char keyChar = keyInput.charAt(0);

            try (
                    FileInputStream inputFileStream = new FileInputStream(inputFilePath);
                    FileOutputStream encryptedFileStream = new FileOutputStream(encryptedFilePath);
                    FileOutputStream decryptedFileStream = new FileOutputStream(decryptedFilePath)
            ) {
                logger.info("{} {}", messages.getString("encrypting"), inputFilePath);
                try (Encryptor encryptor = new Encryptor(encryptedFileStream, keyChar, messages)) {
                    int data;
                    while ((data = inputFileStream.read()) != -1) {
                        encryptor.write(data);
                    }
                }
                logger.info("{} {}", messages.getString("encryptionComplete"), encryptedFilePath);

                logger.info("{} {}", messages.getString("decrypting"), encryptedFilePath);
                try (Decryptor decryptor = new Decryptor(new FileInputStream(encryptedFilePath), keyChar, messages)) {
                    int data;
                    while ((data = decryptor.read()) != -1) {
                        decryptedFileStream.write(data);
                    }
                }
                logger.info("{} {}", messages.getString("decryptionComplete"), decryptedFilePath);

                logger.info(messages.getString("success"));
            } catch (IOException e) {
                logger.fatal("{} {}", messages.getString("fileFat"), e.getMessage());
            }
        } catch (IOException e) {
            logger.error("Error reading input: {}", e.getMessage());
        }
    }

    private static Locale selectLanguage(BufferedReader reader) {
        try {
            System.out.println("Select language: ");
            System.out.println("1 - English");
            System.out.println("2 - German");
            System.out.println("3 - Norwegian");

            int languageChoice = Integer.parseInt(reader.readLine());

            return switch (languageChoice) {
                case 1 -> Locale.ENGLISH;
                case 2 -> Locale.GERMANY;
                case 3 -> Locale.forLanguageTag("no-NO");
                default -> {
                    logger.warn("Invalid language choice. Default language is English.");
                    yield Locale.ENGLISH;
                }
            };
        } catch (Exception e) {
            logger.error("Error selecting language: {}", e.getMessage());
            return Locale.ENGLISH;
        }
    }
}
