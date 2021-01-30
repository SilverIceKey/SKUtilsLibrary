package com.silvericekey.skutilslibrary.base;

/**
 * 实现activity生命周期
 */
public interface BasePresenter {
    void onCreate();
    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestory();
}
