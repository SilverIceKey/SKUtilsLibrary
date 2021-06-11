package com.silvericekey.skutilslibrary.utils.string;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串相关
 */
public class StringUtils {

    /**
     * 判断字符串中是否包含中文
     *
     * @param str 待校验字符串
     * @return 是否为中文
     * @warn 不能校验是否为中文标点符号
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 字符串数组转字符串逗号隔开
     *
     * @param list
     * @return
     */
    public static String List2String(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).endsWith("mp4")||list.get(i).endsWith("avi")||list.get(i).endsWith("mkv")){
                continue;
            }
            stringBuilder.append(list.get(i));
            if (i<list.size()-1){
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 字符串数组获取视频地址
     *
     * @param list
     * @return
     */
    public static String ListGetVideo(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).endsWith("mp4")||list.get(i).endsWith("avi")||list.get(i).endsWith("mkv")){
                return list.get(i);
            }
        }
        return "";
    }
}
