package com.silvericekey.skutilslibrary.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * json处理工具类
 */
public class JSONUtil {
    public static String getValue(String json, String key) {
        JsonElement jsonElement = new JsonParser().parse(json);
        if (jsonElement.isJsonObject()) {
            return jsonElement.getAsJsonObject().get(key).toString();
        } else {
            return null;
        }
    }

    public static <T> T getModel(String json, String key, Class<T> clazz) {
        JsonElement jsonElement = new JsonParser().parse(json);
        if (jsonElement.isJsonObject()) {
            return new Gson().fromJson(jsonElement.getAsJsonObject().get(key).toString(), clazz);
        } else {
            return null;
        }
    }

    public static <T> T toModel(String json, Class<T> clazz) {
        return new Gson().fromJson(json, clazz);
    }
}
