package com.wioletta.task2;

public class Conveyor implements Runnable {
    private final CircularBuffer inputBuffer;
    private final CircularBuffer outputBuffer;
    private final int id;

    public Conveyor(CircularBuffer inputBuffer, CircularBuffer outputBuffer, int id) {
        this.inputBuffer = inputBuffer;
        this.outputBuffer = outputBuffer;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = inputBuffer.read();
                String newMessage = "Потік № " + id + " переклав повідомлення: " + message;
                outputBuffer.add(newMessage);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
