package com.silvericekey.skutilslibrary.base;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.silvericekey.skutilslibrary.IBaseMainAidlInterface;
import com.silvericekey.skutilslibrary.IBaseServiceAidlInterface;

public abstract class BaseService<T extends IBaseServiceAidlInterface.Stub, V extends IBaseMainAidlInterface> extends Service {

    private T mServiceAidlInterface;
    private V mMainAdilInterface;

    protected abstract T getInterface();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mServiceAidlInterface = getInterface();
        return mServiceAidlInterface;
    }
}
