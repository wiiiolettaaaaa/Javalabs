package com.wioletta.utils;

import java.util.Scanner;

public class UserInputHandler {
    private final Scanner scanner;

    public UserInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public String askForCommand() {
        System.out.print("Enter the command: ");
        return scanner.nextLine().trim();
    }

    public String askForFileName(String action) {
        System.out.print("Please enter the filename to " + action + ": ");
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}