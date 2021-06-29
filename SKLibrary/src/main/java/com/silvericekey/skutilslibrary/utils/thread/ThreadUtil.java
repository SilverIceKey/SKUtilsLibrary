package com.silvericekey.skutilslibrary.utils.thread;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadUtil {
    /**
     * 默认核心池大小
     */
    public static final int DEFAULT_COREPOOLSIZE = 10;
    /**
     * 默认最大核心池大小
     */
    public static final int DEFAULT_MAXIMUMPOOLSIZE = 50;
    /**
     * 默认存活时间
     */
    public static final Long DEFAULT_KEEPALIVETIME = 0L;
    /**
     * 默认存活时间单位
     */
    public static final TimeUnit DEFAULT_TIMEUNIT = TimeUnit.MILLISECONDS;
    /**
     * 默认线程队列
     */
    public volatile BlockingQueue<Runnable> runnables = new LinkedBlockingQueue<>(1024);
    /**
     * future缓存
     */
    public volatile static HashMap<Object, Future<?>> futures = new HashMap<>();
    /**
     * 线程单例
     */
    public static ThreadUtil mInstance;
    /**
     * 线程池
     */
    public static ExecutorService threadPoolExecutor;

    /**
     * 获取线程类单例
     *
     * @return
     */
    public static synchronized ThreadUtil getInstance() {
        if (mInstance == null) {
            mInstance = new ThreadUtil();
        }
        return mInstance;
    }

    /**
     * 线程类初始化
     */
    public ThreadUtil() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        threadPoolExecutor = new ThreadPoolExecutor(DEFAULT_COREPOOLSIZE,
                DEFAULT_MAXIMUMPOOLSIZE,
                DEFAULT_KEEPALIVETIME,
                DEFAULT_TIMEUNIT,
                runnables
                , namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * 线程池执行程序
     *
     * @param runnable 线程执行内容
     */
    public void execute(Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }

    /**
     * 提交执行程序
     *
     * @param runnable 线程执行内容
     * @return 返回执行对象
     */
    public Future<?> submit(Runnable runnable) {
        return threadPoolExecutor.submit(runnable);
    }

    /**
     * 提交执行程序
     *
     * @param tag      标签
     * @param callable 线程执行内容
     * @return 返回执行对象
     */
    public <T> T submit(Object tag, Callable<T> callable) {
        T result = null;
        Future<T> future = threadPoolExecutor.submit(callable);
        futures.put(tag, future);
        try {
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 提交执行程序
     *
     * @param tag      future标签
     * @param runnable 线程执行内容
     * @param result   执行结果
     * @return 返回执行结果
     */
    public <T> T submit(Object tag, Runnable runnable, T result) {
        Future<T> future = threadPoolExecutor.submit(runnable, result);
        futures.put(tag, future);
        try {
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    private class ThreadFactoryBuilder implements ThreadFactory {
        private String nameFormat;

        public ThreadFactoryBuilder setNameFormat(String s) {
            this.nameFormat = s;
            return this;
        }

        public ThreadFactory build() {
            return this;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r);
        }
    }
}
