package com.wioletta.task1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int numAccounts = 1000;
        int numTransfers = 10000;

        Bank bank = new Bank();
        Account[] accounts = new Account[numAccounts];
        for (int i = 0; i < numAccounts; i++) {
            accounts[i] = new Account(i, ThreadLocalRandom.current().nextInt(1000, 10000));
        }

        int totalBalanceBefore = getTotalBalance(accounts);
        System.out.println("Total balance before transfers: " + totalBalanceBefore);

        try (ExecutorService executor = Executors.newFixedThreadPool(100)) {
            for (int i = 0; i < numTransfers; i++) {
                executor.submit(() -> {
                    Account from = accounts[ThreadLocalRandom.current().nextInt(numAccounts)];
                    Account to = accounts[ThreadLocalRandom.current().nextInt(numAccounts)];
                    int amount = ThreadLocalRandom.current().nextInt(1, from.getBalance() + 1);

                    bank.transfer(from, to, amount);
                });
            }

            executor.shutdown();
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                System.out.println("Executor did not terminate in the specified time.");
            }
        }

        int totalBalanceAfter = getTotalBalance(accounts);
        System.out.println("Total balance after transfers: " + totalBalanceAfter);

        if (totalBalanceBefore == totalBalanceAfter) {
            System.out.println("Total balance is consistent");
        } else {
            System.out.println("Total balance is inconsistent");
        }
    }

    private static int getTotalBalance(Account[] accounts) {
        int total = 0;
        for (Account account : accounts) {
            total += account.getBalance();
        }
        return total;
    }
}
