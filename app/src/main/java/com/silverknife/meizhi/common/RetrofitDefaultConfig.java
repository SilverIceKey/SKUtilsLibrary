package com.silverknife.meizhi.common;

import com.silvericekey.skutilslibrary.net.RetrofitConfig;

public class RetrofitDefaultConfig extends RetrofitConfig {
    @Override
    public String getBaseUrl() {
        return "https://www.baidu.com/";
    }
}
