package ru.sbrf;

import java.util.concurrent.TimeUnit;

public class Worker implements Runnable {

    private volatile boolean endFlag = false;
    private int i = 0;

    public void stop() {
        endFlag = true;
    }

    public int getI() {
        return i;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            i++;
            try {
                TimeUnit.MINUTES.sleep(100000);
            } catch (InterruptedException e) {
                System.out.printf("Thread.currentThread().isInterrupted(): " + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("END!");
    }
}
