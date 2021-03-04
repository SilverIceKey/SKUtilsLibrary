package com.silverknife.meizhi.features.main;

import com.silvericekey.skutilslibrary.base.BaseActivityImpl;
import com.silverknife.meizhi.databinding.ActivityMainBinding;

public class MainActivityImpl extends BaseActivityImpl<ActivityMainBinding, MainPresenterImpl> implements MainActivity{
    @Override
    protected ActivityMainBinding getViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected MainPresenterImpl getPresenter() {
        return new MainPresenterImpl(this);
    }

    @Override
    protected void initViewAndData() {

    }
}
