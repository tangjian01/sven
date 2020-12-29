package com.sven.netty.server;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ：jian.tang@dmall.com
 * @date ：Created in 2020/12/21 9:15
 * @description：
 * @version:
 * @see
 */
public class TimeServer2Handler {
    private ExecutorService executor;

    public TimeServer2Handler(int maxPoolSize , int queueSize){
        executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),maxPoolSize,120L, TimeUnit.SECONDS,new ArrayBlockingQueue<>(queueSize));
    }
    public void execute(Runnable task){
        executor.execute(task);
    }
}
