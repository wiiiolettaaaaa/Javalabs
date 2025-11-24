package com.wioletta.task2;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CircularBuffer {
    private final LinkedList<String> buffer;
    private final int capacity;
    private int readIndex;
    private int writeIndex;

    private final Lock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    public CircularBuffer(int capacity) {
        this.buffer = new LinkedList<>();
        this.capacity = capacity;
        this.readIndex = 0;
        this.writeIndex = 0;
        this.lock = new ReentrantLock();
        this.notFull = lock.newCondition();
        this.notEmpty = lock.newCondition();
    }

    public boolean isEmpty() {
        return readIndex == writeIndex;
    }

    public boolean isFull() {
        return (writeIndex + 1) % capacity == readIndex;
    }

    public void add(String value) throws InterruptedException {
        lock.lock();
        try {
            while (isFull()) {
                notFull.await();
            }

            if (writeIndex == buffer.size()) {
                buffer.add(value);
            } else {
                buffer.set(writeIndex, value);
            }

            writeIndex = (writeIndex + 1) % capacity;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public String read() throws InterruptedException {
        lock.lock();
        try {
            while (isEmpty()) {
                notEmpty.await();
            }

            String result = buffer.get(readIndex);

            readIndex = (readIndex + 1) % capacity;
            notFull.signal();

            return result;
        } finally {
            lock.unlock();
        }
    }
}