package com.wioletta.task1;

import java.util.concurrent.locks.Lock;

public class Bank {
    public void transfer(Account from, Account to, int amount) {
        Lock firstLock = from.getId() < to.getId() ? from.getLock() : to.getLock();
        Lock secondLock = from.getId() < to.getId() ? to.getLock() : from.getLock();

        firstLock.lock();
        secondLock.lock();

        try {
            if (from.getBalance() >= amount) {
                from.withdraw(amount);
                to.deposit(amount);
            }
        } finally {
            firstLock.unlock();
            secondLock.unlock();
        }
    }
}
