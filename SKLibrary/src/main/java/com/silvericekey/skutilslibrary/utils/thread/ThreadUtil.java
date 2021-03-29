package com.silvericekey.skutilslibrary.utils.thread;

import com.blankj.utilcode.util.ThreadUtils;

import java.util.WeakHashMap;

public class ThreadUtil {
    public volatile WeakHashMap<Object,Thread> threads = new WeakHashMap<>();
    public static ThreadUtil mInstance;

    public static synchronized ThreadUtil getInstance(){
        if (mInstance==null){
            mInstance = new ThreadUtil();
        }
        return mInstance;
    }

    public Thread createThread(Object tag,Runnable runnable){
        Thread thread = new Thread(runnable);
        threads.put(tag,thread);
        return thread;
    }

    public Thread getThread(Object tag){
        return threads.get(tag);
    }
}
