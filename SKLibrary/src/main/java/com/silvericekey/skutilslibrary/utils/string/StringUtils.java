package com.silvericekey.skutilslibrary.utils.string;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串相关
 */
public class StringUtils {
    /**
     *  中文正则检测
     */
    public static Pattern CHINESE_COMPILE = Pattern.compile("[\u4e00-\u9fa5]");
    /**
     * 判断字符串中是否包含中文
     *
     * @param str 待校验字符串
     * @return 是否为中文
     * @warn 不能校验是否为中文标点符号
     */
    public static boolean isContainChinese(String str) {
        Matcher m = CHINESE_COMPILE.matcher(str);
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
    public static String list2String(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).endsWith("mp4") || list.get(i).endsWith("avi") || list.get(i).endsWith("mkv")) {
                continue;
            }
            stringBuilder.append(list.get(i));
            if (i < list.size() - 1) {
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
    public static String listGetVideo(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).endsWith("mp4") || list.get(i).endsWith("avi") || list.get(i).endsWith("mkv")) {
                return list.get(i);
            }
        }
        return "";
    }

    /**
     * 字符串判空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 删除所有空格
     *
     * @param str
     * @return
     */
    public static String deleteAllSpace(String str) {
        return str.replace(" ", "");
    }

    /**
     * 在字符串最前端添加相同的字符
     *
     * @param str 需要修改的字符串
     * @param element      需要添加的元素
     * @param numOfElement 添加的元素的数量
     * @return 添加完成的字符串
     */
    public static String addSomeElementBefore(String str, String element, int numOfElement) {
        return new String(new char[numOfElement]).replace("\0", element) + str;
    }

    /**
     * 在字符串最后端添加相同的字符
     *
     * @param str 需要修改的字符串
     * @param element 需要添加的元素
     * @param numOfElement 添加的元素的数量
     * @return 添加完成的字符串
     */
    public static String addSomeElementAfter(String str, String element, int numOfElement) {
        return str + new String(new char[numOfElement]).replace("\0", element);
    }

    /**
     * 在指定位置插入相同的字符
     * @param str 需要修改的字符串
     * @param index 在什么位置插入
     * @param element 需要添加的元素
     * @param numOfElement 添加的元素的数量
     * @return 添加完成的字符串
     */
    public static String addSomeElementToIndex(String str, int index, String element, int numOfElement) {
        return str.substring(0, index) + new String(new char[numOfElement]).replace("\0", element) + str.substring(index + 1);
    }
}
