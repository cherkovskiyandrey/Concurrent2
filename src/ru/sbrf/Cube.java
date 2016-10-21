package ru.sbrf;

public class Cube {
    private final Object volumeLock = new Object();
    private final Object positionLock = new Object();
    private int length, width, height;
    private int x, y, z;

    void increaseVolume() {
        synchronized (volumeLock) {
            ++length;
            sleep();
            ++width;
            sleep();
            ++height;
        }
    }

    void move() {
        synchronized (positionLock) {
            ++x;
            sleep();
            ++y;
            sleep();
            ++z;
        }
    }

    private void sleep() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Cube{" +
                "length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
