package com.silverknife.meizhi.common;

import com.silvericekey.skutilslibrary.net.AbstractRetrofitConfig;

public class RetrofitDefaultConfig extends AbstractRetrofitConfig {
    @Override
    public String getBaseUrl() {
        return "https://www.baidu.com/";
    }
}
