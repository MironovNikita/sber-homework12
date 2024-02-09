package org.example.task;

import org.example.poolFactory.ThreadPoolFactory;
import org.example.poolFactory.ThreadPoolType;
import org.example.threadPool.ThreadPool;
import org.example.utils.Counter;

public class TaskRunner {
    public static void task1() {
        System.out.println("Выбрано: Проверка работы FixedThreadPool");

        ThreadPoolFactory poolFactory = new ThreadPoolFactory();
        poolTest(poolFactory.createThreadPool(ThreadPoolType.FIXED, 3));
    }

    public static void task2() {
        System.out.println("Выбрано: Проверка работы ScalableThreadPool");

        ThreadPoolFactory poolFactory = new ThreadPoolFactory();
        poolTest(poolFactory.createThreadPool(ThreadPoolType.SCALABLE, 2, 5));
    }

    private static void poolTest(ThreadPool threadPool) {
        Counter counter = new Counter();
        threadPool.start();

        for (int i = 1; i <= 10; i++) {
            int threadNumber = i;

            threadPool.execute(() -> {
                System.out.println("\nЗадача " + threadNumber + " выполняется потоком " +
                        Thread.currentThread().getName());
                double answer = 0;
                answer += counter.count(threadNumber);
                System.out.println("\nОтвет потока " + Thread.currentThread().getName()
                        + " для значения " + threadNumber + " равен " + answer);
            });
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadPool.interrupt();
    }
}