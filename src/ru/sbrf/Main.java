package ru.sbrf;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        final Worker worker = new Worker();
        Thread t = new Thread(worker);
        t.start();

        Thread.sleep(5000);

        //worker.stop();
        t.interrupt();
        t.join();
        System.out.println(worker.getI());

    }

    private void syncCollectionsWarning() {
        final SyncCollectionsWarning syncCollectionsWarning = new SyncCollectionsWarning();

        List<Thread> threads = IntStream.range(1, 20)
                .mapToObj(i -> new Thread(() -> syncCollectionsWarning.add(Integer.toString(i))))
                .collect(Collectors.toList())
                .stream()
                .peek(t -> t.start())
                .collect(Collectors.toList());

        threads.stream().forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(syncCollectionsWarning);
    }

    private void testSimpleMonitor() {
        final AccountExample accountExample = new AccountExample();

        List<Thread> threads = IntStream.range(1, 20)
                .mapToObj(i -> new Thread(accountExample))
                .collect(Collectors.toList())
                .stream()
                .peek(t -> t.start())
                .collect(Collectors.toList());
    }

    private void ortgDataLocks() {
        final Cube cube = new Cube();

        List<Thread> moveThreads = IntStream.range(0, 20)
                .mapToObj(i -> new Thread(() -> cube.move()))
                .collect(Collectors.toList())
                .stream()
                .peek(t -> t.start())
                .collect(Collectors.toList());

        List<Thread> volumeIncrThreads = IntStream.range(0, 40)
                .mapToObj(i -> new Thread(() -> cube.increaseVolume()))
                .collect(Collectors.toList())
                .stream()
                .peek(t -> t.start())
                .collect(Collectors.toList());

        Stream.concat(moveThreads.stream(), volumeIncrThreads.stream())
                .forEach(t -> {
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

        System.out.println(cube);
    }
}











