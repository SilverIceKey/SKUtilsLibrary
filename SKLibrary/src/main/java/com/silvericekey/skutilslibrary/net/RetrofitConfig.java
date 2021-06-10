package com.silvericekey.skutilslibrary.net;

/**
 * retrofit配置接口
 */
public interface RetrofitConfig {
    Long DEFAULT_CONNECT_TIMEOUT = 10*1000L;
    Long DEFAULT_READ_TIMEOUT = 10*1000L;
    Long DEFAULT_WRITE_TIMEOUT = 10*1000L;
    /**
     * 获取基础host
     * @return
     */
    String getBaseUrl();

    /**
     * 获取连接超时时间
     * @return
     */
    Long connectTimeout();

    /**
     * 获取读取超时时间
     * @return
     */
    Long ReadTimeout();

    /**
     * 获取写入超时时间
     * @return
     */
    Long WriteTimeout();
}
