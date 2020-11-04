package edu.job.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Md5Util {

    public static Map<String, String> getAuthMap() {
        Map<String, String> auth = new HashMap<>();
        String accessId = "c7805ecb9de84868928a85d1fbfdfe66";
        String accessKey = "c7805ecb9de84868928a85d1fbfdfe66";
        String systemName = "soms";
        String timestamp = String.valueOf(System.currentTimeMillis()); // 获取当前时间
        auth.put("accessId", accessId);
        auth.put("accessKey", accessKey);
        auth.put("timestamp", timestamp);
        auth.put("systemName", systemName);
        auth.put("sign", getSign(accessId, accessKey, timestamp, systemName));
        return auth;
    }

    private static String getSign(String accessId, String accessKey, String timestamp, String systemName) {
        return md5("accessId=" + accessId + "|accessKey=" + accessKey + "|systemName=" + systemName + "|timestamp=" + timestamp);
    }

    /**
     * 使用md5的算法进行加密
     */
    public static String md5(String plainText) {
        byte[] secretBytes;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        StringBuilder md5code = new StringBuilder(new BigInteger(1, secretBytes).toString(16));// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        int length = md5code.length();
        for (int i = 0; i < 32 - length; i++) {
            md5code.insert(0, "0");
        }
        return md5code.toString();
    }
}
