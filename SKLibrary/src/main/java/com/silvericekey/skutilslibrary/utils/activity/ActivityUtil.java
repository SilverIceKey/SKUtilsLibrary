package com.silvericekey.skutilslibrary.utils.activity;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * activity管理工具
 */
public class ActivityUtil implements Application.ActivityLifecycleCallbacks {
    /**
     * Activity状态
     */
    public enum ActivityStatus {
        /**
         * 创建完成
         */
        CREATED,
        /**
         * 开始完成
         */
        STARTED,
        /**
         * 恢复完成
         */
        RESUMED,
        /**
         * 暂停完成
         */
        PAUSED,
        /**
         * 停止完成
         */
        STOPPED,
        /**
         * 保存状态
         */
        SAVEINSTANCESTATE
    }

    /**
     * Activity存放列表
     */
    private static Stack<Activity> mActivity = new Stack<>();
    /**
     * Activity状态
     */
    private static HashMap<Activity, ActivityStatus> mActivityStatus = new HashMap<>();

    /**
     * 获取当前activity
     *
     * @return
     */
    public static Activity getTopActivity() {
        return mActivity.peek();
    }

    /**
     * 获取顶部Activity当前状态
     *
     * @return
     */
    public static ActivityStatus getTopActivityStatus() {
        return mActivityStatus.get(mActivity.peek());
    }

    /**
     * 获取指定activity当前状态
     *
     * @param activity
     * @return
     */
    public static ActivityStatus getActivityStatus(Activity activity) {
        return mActivityStatus.get(activity);
    }

    /**
     * 判断activity是否存在
     *
     * @param activityName
     * @return
     */
    public static boolean hasActivity(String activityName) {
        for (Activity activity : mActivity) {
            if (activity.getClass().getSimpleName().contains(activityName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取activity在栈中的位置
     *
     * @param activityName
     * @return
     */
    public static int getActivityPosition(String activityName) {
        AtomicInteger position = new AtomicInteger(0);
        for (Activity activity : mActivity) {
            if (activity.getClass().getSimpleName().contains(activityName)) {
                return position.get();
            }
            position.incrementAndGet();
        }
        return -1;
    }

    /**
     * 结束到指定activity
     *
     * @param activityName
     * @return
     */
    public static boolean finishToActivity(String activityName) {
        int position = getActivityPosition(activityName);
        if (-1 == position) {
            return false;
        }
        for (int i = mActivity.size() - position - 1; i > 0; i--) {
            mActivity.pop().finish();
        }
        return true;
    }

    /**
     * 结束除最新的Activity之外的所有Activity
     *
     * @return
     */
    public static boolean finishToTop() {
        try {
            Activity tmpActivity = mActivity.pop();
            while (!mActivity.empty()) {
                mActivity.pop().finish();
            }
            mActivity.push(tmpActivity);
        }catch (Exception e){
            Logger.e("finish activity to top error:"+e);
            return false;
        }
        return true;
    }

    /**
     * activity创建完成是加入队列并修改保存状态
     *
     * @param activity
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mActivity.push(activity);
        mActivityStatus.put(activity, ActivityStatus.CREATED);
    }

    /**
     * activity 执行完onStart后修改保存状态
     *
     * @param activity
     */
    @Override
    public void onActivityStarted(Activity activity) {
        mActivityStatus.put(activity, ActivityStatus.STARTED);
    }

    /**
     * activity 执行完onResume后修改保存状态
     *
     * @param activity
     */
    @Override
    public void onActivityResumed(Activity activity) {
        mActivityStatus.put(activity, ActivityStatus.RESUMED);
    }

    /**
     * activity 执行完onPause后修改保存状态
     *
     * @param activity
     */
    @Override
    public void onActivityPaused(Activity activity) {
        mActivityStatus.put(activity, ActivityStatus.PAUSED);
    }

    /**
     * activity 执行完onStop后修改保存状态
     *
     * @param activity
     */
    @Override
    public void onActivityStopped(Activity activity) {
        mActivityStatus.put(activity, ActivityStatus.STOPPED);
    }

    /**
     * activity 执行完saveinstancestate后修改保存状态
     *
     * @param activity
     * @param outState
     */
    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        mActivityStatus.put(activity, ActivityStatus.SAVEINSTANCESTATE);
    }

    /**
     * activity 执行完onDestroy后修改保存状态
     *
     * @param activity
     */
    @Override
    public void onActivityDestroyed(Activity activity) {
        mActivity.pop();
        mActivityStatus.remove(activity);
    }
}
