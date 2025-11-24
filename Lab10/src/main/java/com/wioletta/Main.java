package com.wioletta;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String literalString = "Happy Xmas!";
        System.out.println("[Строковий літерал] До зміни: " + literalString);
        literalString = modifyString(literalString, "Modified Literal");
        System.out.println("[Строковий літерал] Після зміни: " + literalString);

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введіть рядок з клавіатури: ");
            String userInputString = scanner.nextLine();
            System.out.println("[Рядок з клавіатури] До зміни: " + userInputString);

            System.out.print("Введіть значення для заміни: ");
            String newValue = scanner.nextLine();
            userInputString = modifyString(userInputString, newValue);
            System.out.println("[Рядок з клавіатури] Після зміни: " + userInputString);
        }
    }

    private static String modifyString(String str, String newValue) {
        try {
            Class<?> stringClass = String.class;
            Field valueField = stringClass.getDeclaredField("value");
            valueField.setAccessible(true);

            byte[] newValueBytes = newValue.getBytes(StandardCharsets.UTF_8);

            valueField.set(str, newValueBytes);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return str;
    }
}

//private static String modifyString(String str, String newValue) {
//    try {
//        Field valueField = String.class.getDeclaredField("value");
//        valueField.setAccessible(true);
//
//        byte[] newBytes = newValue.getBytes(StandardCharsets.UTF_8);
//
//        valueField.set(str, newBytes);
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//    return str;
//}
