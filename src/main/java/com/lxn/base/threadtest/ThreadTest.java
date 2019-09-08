package com.lxn.base.threadtest;

import java.util.concurrent.*;

public class ThreadTest {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(100);
        ExecutorService service = new ThreadPoolExecutor(10,3,
                60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
//        service.invokeAll()
        FutureTask<Integer> futureTask = new FutureTask<Integer>((Callable<Integer>) new Thread());
//        service.submit();

    }
}
