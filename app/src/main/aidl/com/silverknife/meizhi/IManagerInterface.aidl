// IManagerInterface.aidl
package com.silverknife.meizhi;

import com.silverknife.meizhi.ICallbacklInterface;
// Declare any non-default types here with import statements

interface IManagerInterface {
    void test();
    void setCallBack(ICallbacklInterface callback);
}
