package org.example.poolFactory;

import org.example.threadPool.FixedThreadPool;
import org.example.threadPool.ScalableThreadPool;
import org.example.threadPool.ThreadPool;

public class ThreadPoolFactory {
    public ThreadPool createThreadPool(ThreadPoolType poolType, int... sizes) {
        switch (poolType) {
            case FIXED -> {
                if (sizes.length != 1) {
                    throw new IllegalArgumentException(
                            "FixedThreadPool подразумевает только фиксированный параметр размера!");
                }
                return new FixedThreadPool(sizes[0]);
            }
            case SCALABLE -> {
                if (sizes.length != 2) {
                    throw new IllegalArgumentException(
                            "ScalableThreadPool подразумевает только два параметра: минимальный и максимальный размер!");
                }
                return new ScalableThreadPool(sizes[0], sizes[1]);
            }
            default -> throw new IllegalArgumentException(
                    String.format("Неподдерживаемый тип пула потоков: %s", poolType));
        }
    }
}
