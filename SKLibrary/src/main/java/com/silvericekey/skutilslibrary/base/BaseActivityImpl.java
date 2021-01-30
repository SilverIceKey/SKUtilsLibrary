package com.silvericekey.skutilslibrary.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

public abstract class BaseActivityImpl<V extends ViewBinding, P extends BasePresenter> extends AppCompatActivity implements BaseActivity {
    private V mBinding;
    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = getViewBinding();
        mPresenter = getPresenter();
        setContentView(mBinding.getRoot());
        mPresenter.onCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
        initViewAndData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
        refreshViewOnResume();
    }

    @Override
    protected void onPause() {
        mPresenter.onPause();
        saveViewAndData();
        super.onPause();
    }

    @Override
    protected void onStop() {
        mPresenter.onStop();
        stopViewAndData();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestory();
        destoryViewAndData();
        super.onDestroy();
    }

    /**
     * 刷新view和数据操作
     */
    protected void refreshViewOnResume() {

    }

    /**
     * 保存view和数据操作
     */
    protected void saveViewAndData() {

    }

    /**
     * 停止view和数据操作
     */
    protected void stopViewAndData() {

    }

    /**
     * 销毁view和数据
     */
    protected void destoryViewAndData() {

    }

    @Override
    public Context getContext() {
        return this;
    }

    /**
     * 初始化viewbinding
     *
     * @return
     */
    protected abstract V getViewBinding();

    /**
     * 初始化presenter
     *
     * @return
     */
    protected abstract P getPresenter();

    /**
     * 初始化界面和数据
     */
    protected abstract void initViewAndData();


}
