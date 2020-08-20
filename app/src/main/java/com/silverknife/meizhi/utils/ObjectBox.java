package com.silverknife.meizhi.utils;

import android.content.Context;

import com.silverknife.meizhi.features.feature_test.MyObjectBox;

import io.objectbox.BoxStore;

public class ObjectBox {
    private static BoxStore boxStore;
    public static void init(Context context){
        boxStore = MyObjectBox.builder()
                .androidContext(context)
                .build();
    }

    public static BoxStore get(){
        return boxStore;
    }
}
