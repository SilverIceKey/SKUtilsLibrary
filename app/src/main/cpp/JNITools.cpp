//
// Created by zhouw on 2020/7/8.
//

#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_silverknife_meizhi_utils_JNITools_getApi(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("111");
}