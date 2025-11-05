package com.wioletta;

import java.util.Scanner;
import java.util.stream.IntStream;

public class PerfectNumbers {


    public static boolean isPerfect(int n) {
        return n > 0 && IntStream.rangeClosed(1, n / 2)
                .filter(i -> n % i == 0)
                .sum() == n;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть позитивне число: ");
        int n = scanner.nextInt();

        System.out.println("Досконалі числа від 1 до " + n + ":");

        boolean found = IntStream.rangeClosed(1, n)
                .filter(PerfectNumbers::isPerfect)
                .peek(i -> {
                    String divisors = IntStream.rangeClosed(1, i / 2)
                            .filter(j -> i % j == 0)
                            .mapToObj(String::valueOf)
                            .reduce((a, b) -> a + " + " + b)
                            .orElse("");

                    int sum = IntStream.rangeClosed(1, i / 2)
                            .filter(j -> i % j == 0)
                            .sum();

                    System.out.println(i + " = " + divisors + " = " + sum);
                })
                .count() > 0;

        if (!found) {
            System.out.println("Досконалих чисел у заданому діапазоні не знайдено.");
        }
    }
}