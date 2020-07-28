package com.silvericekey.skutilslibrary.base;

import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public abstract class BasePagerAdapter extends FragmentStatePagerAdapter {
    public BasePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public int getPageIcon(int position) {
        return 0;
    }

    public View getPageCustomView(int position){
        return null;
    }
}
