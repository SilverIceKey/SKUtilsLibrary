package com.silvericekey.skutilslibrary.net;

import com.silvericekey.skutilslibrary.base.BaseApplication;

import java.io.File;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * retrofit客户端
 */
public class RetrofitClient {
    /**
     * okhttp客户端
     */
    private OkHttpClient okHttpClient;
    /**
     * retrofit实例
     */
    private Retrofit retrofit;
    /**
     * retrofit临时配置
     */
    private AbstractRetrofitConfig retrofitConfig;
    /**
     * retrofit默认配置
     */
    private AbstractRetrofitConfig defaultConfig;
    /**
     * 目前配置是否为默认配置
     */
    private boolean isConfigDefault = false;
    /**
     * 是否使用默认配置
     */
    private boolean useDefaultConfig = false;
    /**
     * 日志拦截器
     */
    private HttpLoggingInterceptor httpLoggingInterceptor;

    /**
     * RetrofitClient 懒汉模式
     */
    private static class RetrofitClientHolder {
        public static RetrofitClient retrofitClient = new RetrofitClient();
    }

    /**
     * RetrofitClient单例获取
     * @return
     */
    public static RetrofitClient getInstance() {
        return RetrofitClientHolder.retrofitClient;
    }

    /**
     * RetrofitClient初始化
     */
    private RetrofitClient() {
        //判断日志拦截器是否为空
        if (httpLoggingInterceptor==null){
            //初始化日志拦截器
            httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLogger());
            //设置拦截等级
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
    }

    /**
     * 设置默认配置
     *
     * @param retrofitConfig
     * @return
     */
    public RetrofitClient defaultConfig(AbstractRetrofitConfig retrofitConfig) {
        this.defaultConfig = retrofitConfig;
        this.retrofitConfig = retrofitConfig;
        useDefaultConfig = true;
        return this;
    }

    /**
     * 设置临时配置
     *
     * @param retrofitConfig
     * @return
     */
    public RetrofitClient config(AbstractRetrofitConfig retrofitConfig) {
        this.retrofitConfig = retrofitConfig;
        useDefaultConfig = false;
        return this;
    }

    /**
     * 初始化retrofit和okhttpclient
     */
    private void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl(retrofitConfig.getBaseUrl())
                .client(initOkhttp())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 初始化okhttpclient
     *
     * @return
     */
    private OkHttpClient initOkhttp() {
        long cacheSize = 10 * 1024 * 1024;
        File cacheFile = new File(BaseApplication.app.getExternalCacheDir(), "retrofit");
        if (!cacheFile.exists()) {
            cacheFile.mkdirs();
        }
        Cache cache = new Cache(cacheFile, cacheSize);
        Proxy proxy = new Proxy(retrofitConfig.proxyType(),new InetSocketAddress(retrofitConfig.proxyIPAddr(),retrofitConfig.proxyPort()));
        okHttpClient = new OkHttpClient.Builder()
                .proxy(proxy)
                .proxyAuthenticator((route, response) -> {
                    String credential = Credentials.basic(retrofitConfig.proxyUserName(), retrofitConfig.proxyPassword());
                    return response.request().newBuilder()
                            .header("Proxy-Authorization", credential)
                            .build();
                })
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .connectTimeout(retrofitConfig.connectTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(retrofitConfig.readTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(retrofitConfig.writeTimeout(), TimeUnit.MILLISECONDS)
                .build();
        return okHttpClient;
    }

    /**
     * 添加拦截器
     *
     * @param interceptor
     * @return
     */
    public RetrofitClient addInterceptor(Interceptor interceptor) {
        okHttpClient = okHttpClient.newBuilder().addInterceptor(interceptor).build();
        retrofit = retrofit.newBuilder().client(okHttpClient).build();
        return this;
    }

    /**
     * 添加网络拦截器
     *
     * @param interceptor
     * @return
     */
    public RetrofitClient addNetworkInterceptor(Interceptor interceptor) {
        okHttpClient = okHttpClient.newBuilder().addNetworkInterceptor(interceptor).build();
        retrofit = retrofit.newBuilder().client(okHttpClient).build();
        return this;
    }

    /**
     * 返回请求接口实例
     *
     * @param service 请求接口类
     * @param <T> 请求接口类类型
     * @return 请求接口
     */
    public <T> T createService(Class<T> service) {
        if (defaultConfig == null || retrofitConfig == null) {
            throw new NullPointerException("请先设置默认配置或临时配置");
        }
        if (useDefaultConfig) {
            if (isConfigDefault) {
                return retrofit.create(service);
            } else {
                retrofitConfig = defaultConfig;
                init();
                isConfigDefault = true;
                return retrofit.create(service);
            }
        } else {
            init();
            isConfigDefault = false;
            useDefaultConfig = true;
            return retrofit.create(service);
        }
    }
}
