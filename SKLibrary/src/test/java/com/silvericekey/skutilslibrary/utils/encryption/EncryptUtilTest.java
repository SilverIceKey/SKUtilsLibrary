package com.silvericekey.skutilslibrary.utils.encryption;

import junit.framework.TestCase;

import java.math.BigInteger;

public class EncryptUtilTest extends TestCase {

    public void testMD5Encode() {
        assertEquals("b0baee9d279d34fa1dfd71aadb908c3f",EncryptUtil.MD5Encode("11111"));
    }

    public void testSHA1Encode(){
        assertEquals("7b21848ac9af35be0ddb2d6b9fc3851934db8420",EncryptUtil.SHA1Encode("11111"));
    }

    public void testSHA256Encode(){
        assertEquals("d17f25ecfbcc7857f7bebea469308be0b2580943e96d13a3ad98a13675c4bfc2",EncryptUtil.SHA256Encode("11111"));
    }
}