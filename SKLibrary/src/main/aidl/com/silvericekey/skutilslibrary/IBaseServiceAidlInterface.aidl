// IBaseServiceAidlInterface.aidl
package com.silvericekey.skutilslibrary;

// Declare any non-default types here with import statements
import com.silvericekey.skutilslibrary.IBaseMainAidlInterface;
interface IBaseServiceAidlInterface {
    void setMainAidlInterface(IBaseMainAidlInterface ibaseMainAidlInterface);
}