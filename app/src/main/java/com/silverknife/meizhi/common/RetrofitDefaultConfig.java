package com.silverknife.meizhi.common;

import com.silvericekey.skutilslibrary.net.RetrofitConfig;

public class RetrofitDefaultConfig implements RetrofitConfig {
    @Override
    public String getBaseUrl() {
        return "https://www.baidu.com/";
    }

    @Override
    public Long connectTimeout() {
        return RetrofitConfig.DEFAULT_CONNECT_TIMEOUT;
    }

    @Override
    public Long ReadTimeout() {
        return RetrofitConfig.DEFAULT_READ_TIMEOUT;
    }

    @Override
    public Long WriteTimeout() {
        return RetrofitConfig.DEFAULT_WRITE_TIMEOUT;
    }
}
