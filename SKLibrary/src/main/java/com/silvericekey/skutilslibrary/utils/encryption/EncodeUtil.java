package com.silvericekey.skutilslibrary.utils.encryption;

import android.os.Build;
import android.text.Html;
import android.util.Base64;

import com.orhanobut.logger.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * 编码相关
 */
public class EncodeUtil {
    /**
     * Url编码
     *
     * @param url
     * @return
     */
    public static String urlEncode(String url) {
        return urlEncode(url, Charset.defaultCharset().name());
    }

    /**
     * Url编码
     *
     * @param url
     * @return
     */
    public static String urlEncode(String url, String charSetName) {
        try {
            return URLEncoder.encode(url, charSetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Logger.e("urlencode失败");
            return "";
        }
    }

    /**
     * url解码
     */
    public static String urlDecode(String url) {
        return urlDecode(url, Charset.defaultCharset().name());
    }

    /**
     * url解码
     */
    public static String urlDecode(String url, String charSetName) {
        try {
            return URLDecoder.decode(url, charSetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Logger.e("urldecode失败");
            return "";
        }
    }

    /**
     * base64编码
     */
    public static String base64Encode(String message) {
        byte[] encoded = Base64.encode(message.getBytes(), Base64.DEFAULT);
        return new String(encoded);
    }

    /**
     * base64编码
     */
    public static String base64Encode(byte[] message) {
        byte[] encoded = Base64.encode(message, Base64.DEFAULT);
        return new String(encoded);
    }

    /**
     * base64解码
     */
    public static String base64Decode(String message) {
        byte[] decode = Base64.decode(message, Base64.DEFAULT);
        return new String(decode);
    }

    /**
     * base64解码
     */
    public static String base64Decode(byte[] message) {
        byte[] decode = Base64.decode(message, Base64.DEFAULT);
        return new String(decode);
    }

    /**
     * html编码
     *
     * @param input The input.
     * @return html-encode string
     */
    public static String htmlEncode(final CharSequence input) {
        if (input == null || input.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char c;
        for (int i = 0, len = input.length(); i < len; i++) {
            c = input.charAt(i);
            switch (c) {
                case '<':
                    sb.append("&lt;"); //$NON-NLS-1$
                    break;
                case '>':
                    sb.append("&gt;"); //$NON-NLS-1$
                    break;
                case '&':
                    sb.append("&amp;"); //$NON-NLS-1$
                    break;
                case '\'':
                    //http://www.w3.org/TR/xhtml1
                    // The named character reference &apos; (the apostrophe, U+0027) was
                    // introduced in XML 1.0 but does not appear in HTML. Authors should
                    // therefore use &#39; instead of &apos; to work as expected in HTML 4
                    // user agents.
                    sb.append("&#39;"); //$NON-NLS-1$
                    break;
                case '"':
                    sb.append("&quot;"); //$NON-NLS-1$
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * html解码
     *
     * @param input The input.
     * @return the string of decode html-encode string
     */
    public static CharSequence htmlDecode(final String input) {
        if (input == null || input.length() == 0) {
            return "";
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(input, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(input);
        }
    }

    /**
     * 获取二进制编码含中间空格
     *
     * @param input The input.
     * @return binary string
     */
    public static String binaryEncode(final String input) {
        if (input == null || input.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (char i : input.toCharArray()) {
            sb.append(Integer.toBinaryString(i)).append(" ");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    /**
     * 二进制转String中间需加空格
     *
     * @param input binary string
     * @return UTF-8 String
     */
    public static String binaryDecode(final String input) {
        if (input == null || input.length() == 0) {
            return "";
        }
        String[] splits = input.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String split : splits) {
            sb.append(((char) Integer.parseInt(split, 2)));
        }
        return sb.toString();
    }
}
