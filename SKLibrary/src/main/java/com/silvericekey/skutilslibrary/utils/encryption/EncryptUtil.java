package com.silvericekey.skutilslibrary.utils.encryption;

import android.util.Base64;

import com.orhanobut.logger.Logger;
import com.silvericekey.skutilslibrary.utils.string.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

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
    private static final String CIPHER_DES_ALGORITHM = "DES/CBC/PKCS5Padding";
    private static final String CIPHER_3DES_ALGORITHM = "DESede/EBC/PKCS5Padding";

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
     * DES 加密
     *
     * @param message
     * @param key
     * @return
     */
    public static String desEncrypt(String message, String key) {
        return encrypt(message, key, CIPHER_DES_ALGORITHM);
    }

    /**
     * DES 解密
     *
     * @param message
     * @param key
     * @return
     */
    public static String desDncrypt(String message, String key) {
        return decrypt(message, key, CIPHER_DES_ALGORITHM);
    }

    /**
     * DES加密文件
     * @param key
     * @param srcFile
     * @param destFile
     * @return
     */
    public static String desEncryptFile(String key, String srcFile, String destFile) {
        return encryptFile(key, srcFile, destFile, CIPHER_DES_ALGORITHM);
    }

    /**
     * DES解密文件
     * @param key
     * @param srcFile
     * @param destFile
     * @return
     */
    public static String desDecryptFile(String key, String srcFile, String destFile) {
        return decryptFile(key, srcFile, destFile, CIPHER_DES_ALGORITHM);
    }

    /**
     * 3DES 加密
     *
     * @param message
     * @param key
     * @return
     */
    public static String des3Encrypt(String message, String key) {
        return encrypt(message, key, CIPHER_DES_ALGORITHM);
    }

    /**
     * 3DES 解密
     *
     * @param message
     * @param key
     * @return
     */
    public static String des3Dncrypt(String message, String key) {
        return decrypt(message, key, CIPHER_DES_ALGORITHM);
    }

    /**
     * 3DES加密文件
     * @param key
     * @param srcFile
     * @param destFile
     * @return
     */
    public static String des3EncryptFile(String key, String srcFile, String destFile) {
        return encryptFile(key, srcFile, destFile, CIPHER_DES_ALGORITHM);
    }

    /**
     * 3DES解密文件
     * @param key
     * @param srcFile
     * @param destFile
     * @return
     */
    public static String des3DecryptFile(String key, String srcFile, String destFile) {
        return decryptFile(key, srcFile, destFile, CIPHER_DES_ALGORITHM);
    }

    /**
     * DES|3DES加密
     *
     * @param message
     * @param key
     * @return
     */
    public static String encrypt(String message, String key, String cipherAlgorithm) {
        if (StringUtils.isEmpty(key) || key.length() < 8) {
            Logger.e("密钥长度小于8位");
            throw new RuntimeException("密钥长度小于8位");
        }
        if (StringUtils.isEmpty(message)) {
            return "";
        }
        String encryptMessage = "";
        Key secretKey = generateKey(key);
        try {
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(key.getBytes(Charset.defaultCharset()));
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
            encryptMessage = new String(Base64.encode(cipher.doFinal(message.getBytes(Charset.defaultCharset())), Base64.DEFAULT));
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException |
                InvalidAlgorithmParameterException |
                InvalidKeyException |
                BadPaddingException |
                IllegalBlockSizeException e) {
            Logger.e(e, "加密工具:加密失败");
            e.printStackTrace();
        }
        return encryptMessage;
    }

    /**
     * DES|3DES加密
     *
     * @param message
     * @param key
     * @return
     */
    public static String decrypt(String message, String key, String cipherAlgorithm) {
        if (StringUtils.isEmpty(key) || key.length() < 8) {
            Logger.e("密钥长度小于8位");
            throw new RuntimeException("密钥长度小于8位");
        }
        if (StringUtils.isEmpty(message)) {
            return "";
        }
        String encryptMessage = "";
        Key secretKey = generateKey(key);
        try {
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(key.getBytes(Charset.defaultCharset()));
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            encryptMessage = new String(cipher.doFinal(Base64.encode(message.getBytes(Charset.defaultCharset()), Base64.DEFAULT)));
        } catch (NoSuchAlgorithmException |
                InvalidAlgorithmParameterException |
                InvalidKeyException |
                BadPaddingException |
                IllegalBlockSizeException |
                NoSuchPaddingException e) {
            Logger.e(e, "加密工具:解密失败");
            e.printStackTrace();
        }
        return encryptMessage;
    }

    /**
     * DES|3DES加密文件
     *
     * @param srcFile  待加密的文件
     * @param destFile 加密后存放的文件路径
     * @return 加密后的文件路径
     */
    public static String encryptFile(String key, String srcFile, String destFile, String cipherAlgorithm) {

        if (key == null || key.length() < 8) {
            throw new RuntimeException("加密失败，key不能小于8位");
        }
        try {
            IvParameterSpec iv = new IvParameterSpec(key.getBytes(Charset.defaultCharset()));
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, generateKey(key), iv);
            InputStream is = new FileInputStream(srcFile);
            OutputStream out = new FileOutputStream(destFile);
            CipherInputStream cis = new CipherInputStream(is, cipher);
            byte[] buffer = new byte[1024];
            int r;
            while ((r = cis.read(buffer)) > 0) {
                out.write(buffer, 0, r);
            }
            cis.close();
            is.close();
            out.close();
            return destFile;
        } catch (Exception ex) {
            Logger.e(ex, "加密工具:解密失败");
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * DES|3DES解密文件
     *
     * @param srcFile  已加密的文件
     * @param destFile 解密后存放的文件路径
     * @return 解密后的文件路径
     */
    public static String decryptFile(String key, String srcFile, String destFile, String cipherAlgorithm) {
        if (key == null || key.length() < 8) {
            throw new RuntimeException("加密失败，key不能小于8位");
        }
        try {
            File file = new File(destFile);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            IvParameterSpec iv = new IvParameterSpec(key.getBytes(Charset.defaultCharset()));
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(Cipher.DECRYPT_MODE, generateKey(key), iv);
            InputStream is = new FileInputStream(srcFile);
            OutputStream out = new FileOutputStream(destFile);
            CipherOutputStream cos = new CipherOutputStream(out, cipher);
            byte[] buffer = new byte[1024];
            int r;
            while ((r = is.read(buffer)) >= 0) {
                cos.write(buffer, 0, r);
            }
            cos.close();
            is.close();
            out.close();
            return destFile;
        } catch (Exception ex) {
            Logger.e(ex, "加密工具:解密失败");
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 生成加密密钥格式
     *
     * @param key
     * @return
     */
    private static Key generateKey(String key) {
        try {
            DESKeySpec dks = new DESKeySpec(key.getBytes(Charset.defaultCharset().name()));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM_DES);
            return keyFactory.generateSecret(dks);
        } catch (InvalidKeyException e) {
            Logger.e(e, "加密工具:密钥生成失败");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            Logger.e(e, "加密工具:密钥生成失败");
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
