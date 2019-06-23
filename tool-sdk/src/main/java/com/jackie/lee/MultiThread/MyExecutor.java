package com.jackie.lee.MultiThread;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by liuman on 18/3/26.
 */
public class MyExecutor {
    private static class MyExecutorHolder {
        private static final MyExecutor instance = new MyExecutor();
    }

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(180
            , 300, 60L, TimeUnit.SECONDS
            , new LinkedBlockingQueue<Runnable>(500)
            , new MyThreadFactory("MyExecutor"));

    private MyExecutor() {
    }

    public static final MyExecutor getInstance() {
        return MyExecutorHolder.instance;
    }

    public <T> Future<T> submit(Callable<T> task) {
        try {
            return executor.submit(task);
        } catch (RejectedExecutionException e) {
            String msg = "线程池队列已满!";
//            log.error(msg, e);
        } catch (Exception e) {
            String msg = "线程池执行异常!";
//            log.error(msg, e);
        }
        return null;
    }

    public <T> T getFuture(Future<T> future, long executeTimeout) {
        T t = null;
        try {
            t = future.get(executeTimeout, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
        } catch (Exception e) {
        }
        return t;
    }

    public Map<String, String> getStatistics() {
        Map<String, String> heartbeatMap = Maps.newHashMapWithExpectedSize(2);
        heartbeatMap.put("MyExecutor.activeThread", String.valueOf(executor.getActiveCount()));
        heartbeatMap.put("MyExecutor.queueFreeCapacity", String.valueOf(executor.getQueue().remainingCapacity()));
        return heartbeatMap;
    }
}
