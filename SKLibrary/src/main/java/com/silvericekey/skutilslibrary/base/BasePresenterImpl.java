package com.silvericekey.skutilslibrary.base;

public abstract class BasePresenterImpl<V extends BaseActivity> implements BasePresenter{
    private V mActivity;

    public BasePresenterImpl(V mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestory() {

    }
}
