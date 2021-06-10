package com.silvericekey.skutilslibrary.utils.log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskTxtLogAdapter;
import com.orhanobut.logger.LogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * logger初始化帮助类
 */
public class LoggerHelper {
    private Builder builder;

    private LoggerHelper(Builder builder) {
        this.builder = builder;
    }

    /**
     * logger适配器全加载
     */
    public void init(Callback callback) {
        init();
        if (callback != null) {
            callback.initComplete();
        }
    }

    /**
     * logger适配器全加载
     */
    public void init() {
        for (int i = 0; i < builder.logAdapterWeakReference.size(); i++) {
            Logger.addLogAdapter(builder.logAdapterWeakReference.get(i));
        }
    }

    public static class Builder {
        private List<LogAdapter> logAdapterWeakReference;

        public Builder() {
            logAdapterWeakReference = new ArrayList<>();
        }

        /**
         * 添加logger logcat适配器
         *
         * @return
         */
        public Builder addAndroidLogAdapter() {
            logAdapterWeakReference.add(new AndroidLogAdapter());
            return this;
        }

        /**
         * 添加logger保存到本地适配器
         *
         * @return
         */
        public Builder addDiskTxtLogAdapter() {
            logAdapterWeakReference.add(new DiskTxtLogAdapter());
            return this;
        }

        /**
         * 添加自定义适配器
         *
         * @param adapter
         * @return
         */
        public Builder addCustomLogAdapter(LogAdapter adapter) {
            logAdapterWeakReference.add(adapter);
            return this;
        }

        /**
         * 创建helper类
         *
         * @return
         */
        public LoggerHelper build() {
            if (logAdapterWeakReference.size() == 0) {
                addAndroidLogAdapter();
            }
            return new LoggerHelper(this);
        }
    }

    public interface Callback {
        void initComplete();
    }
}
