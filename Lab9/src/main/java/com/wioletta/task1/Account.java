package com.wioletta.task1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private final int id;
    private int balance;
    private final Lock lock = new ReentrantLock();

    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public Lock getLock() {
        return lock;
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        if (balance >= amount) balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }
}
