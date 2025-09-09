package com.wioletta;

import java.util.Scanner;

public class PerfectNumbers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть позитивне число: ");
        int n = scanner.nextInt();

        boolean found = false;
        System.out.println("Досконалі числа від 1 до " + n + ":");

        for (int i = 1; i <= n; i++) {
            int sum = 0;
            StringBuilder divisors = new StringBuilder();

            for (int j = 1; j <= i / 2; j++) {
                if (i % j == 0) {
                    sum += j;
                    if (divisors.length() > 0) divisors.append(" + ");
                    divisors.append(j);
                }
            }

            if (sum == i && i != 0) {
                found = true;
                System.out.println(i + " = " + divisors + " = " + sum);
            }
        }

        if (!found) {
            System.out.println("Досконалих чисел у заданому діапазоні не знайдено.");
        }
    }
}