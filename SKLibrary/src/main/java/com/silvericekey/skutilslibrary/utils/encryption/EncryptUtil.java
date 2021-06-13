package com.silvericekey.skutilslibrary.utils.encryption;

import com.orhanobut.logger.Logger;
import com.silvericekey.skutilslibrary.utils.string.StringUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 加密解密工具
 */
public class EncryptUtil {

    /**
     * 密钥算法
     */
    private static final String ALGORITHM_MD5 = "MD5";
    private static final String ALGORITHM_SHA1 = "SHA-1";
    private static final String ALGORITHM_SHA256 = "SHA-256";
    private static final String ALGORITHM_DES = "DES";
    /**
     * 加密/解密算法-工作模式-填充模式
     */
    private static final String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";

    /**
     * MD5加密
     *
     * @param message
     * @return
     */
    public static String MD5Encrypt(String message) {
        try {
            return String.format("%32x", new BigInteger(1,
                    MessageDigest.getInstance(ALGORITHM_MD5).digest(message.getBytes())));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * SHA-1加密
     *
     * @param message
     * @return
     */
    public static String SHA1Encrypt(String message) {
        try {
            return String.format("%32x", new BigInteger(1,
                    MessageDigest.getInstance(ALGORITHM_SHA1).digest(message.getBytes())));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * SHA-256加密
     *
     * @param message
     * @return
     */
    public static String SHA256Encrypt(String message) {
        try {
            return String.format("%32x", new BigInteger(1,
                    MessageDigest.getInstance(ALGORITHM_SHA256).digest(message.getBytes())));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * DES加密
     *
     * @param message
     * @param key
     * @return
     */
    public static String DESEncrypt(String message, String key) {
        if (StringUtils.isEmpty(key)||key.length()<8){
            Logger.e("密钥长度小于8位");
            throw new RuntimeException("密钥长度小于8位");
        }
        if (StringUtils.isEmpty(message)){
            return "";
        }
        Key secretKey = generateKey(key);
        Cipher cipher = Cipher.getInstance("DES")
    }

    private static Key generateKey(String key){
        try {
            DESKeySpec dks = new DESKeySpec(key.getBytes(Charset.defaultCharset().name()));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM_DES);
            return keyFactory.generateSecret(dks);
        } catch (InvalidKeyException e) {
            Logger.e(e,"加密工具:密钥生成失败");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            Logger.e(e,"加密工具:密钥生成失败");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            Logger.e(e,"加密工具:密钥生成失败");
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            Logger.e(e,"加密工具:密钥生成失败");
            e.printStackTrace();
        }
        return null;
    }
}
