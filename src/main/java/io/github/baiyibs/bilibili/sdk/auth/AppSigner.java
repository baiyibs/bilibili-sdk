package io.github.baiyibs.bilibili.sdk.auth;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * B站 API 签名工具。
 * <p>
 * 实现 APP 端签名算法
 * </p>
 */
public final class AppSigner {

    private AppSigner() {}

    /**
     * B站应用类型枚举
     */
    public enum AppType {
        // 粉版（普通客户端，通用资源获取）
        PINK("1d8b6e7d45233436", "560c52ccd288fed045859ed18bffd973"),
        // TV版（云视听小电视）
        TV("4409e2ce8ffd12b8", "59b43e04ad6965f34319062b478f83dd"),
        // 概念版
        CONCEPT("191c3b6b975af184", "1673b15a09ef5e4427627f47b03a0578"),
        // HD版
        HD("dfca71928277209b", "b5475a8825547a4fc26c7d518eaaa02e"),
        // 国际版（白版）
        INTERNATIONAL("ae57252b0c09105d", "c75875c596a69eb55bd119e74b07cfe3"),
        // 视频取流专用（Android 旧版）
        STREAM("iVGUTjsxvpLeuDCf", "aHRmhWMLkdeMuILqORnYZocwMBpMEOdt");

        private final String appKey;
        private final String appSec;

        AppType(String appKey, String appSec) {
            this.appKey = appKey;
            this.appSec = appSec;
        }

        public String getAppKey() { return appKey; }
        public String getAppSec() { return appSec; }
    }

    /**
     * 根据应用类型自动添加 appkey 并计算签名
     *
     * @param params 原始参数（不应包含 appkey 和 sign）
     * @param type   应用类型
     * @return 包含 appkey 和 sign 的完整参数 Map（新 Map，不影响原参数）
     */
    public static Map<String, String> signWithType(Map<String, String> params, AppType type) {
        Map<String, String> signedParams = new HashMap<>(params);
        signedParams.put("appkey", type.getAppKey());
        String sign = sign(signedParams, type.getAppSec());
        signedParams.put("sign", sign);
        return signedParams;
    }

    /**
     * 计算签名
     *
     * @param params    待签名的参数（应包含 appkey 等字段，不含 sign）
     * @param appSecret 应用密钥（appsec）
     * @return 32 位小写 MD5 签名
     * @throws RuntimeException 如果 MD5 算法不可用
     */
    public static String sign(Map<String, String> params, String appSecret) {
        Map<String, String> sorted = new TreeMap<>(params);

        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : sorted.entrySet()) {
            if (!stringBuilder.isEmpty()) {
                stringBuilder.append('&');
            }
            String encodedKey = encode(entry.getKey());
            String encodedValue = encode(entry.getValue());
            stringBuilder.append(encodedKey).append('=').append(encodedValue);
        }

        stringBuilder.append(appSecret);

        return md5(stringBuilder.toString());
    }

    /**
     * URL 编码
     */
    private static String encode(String raw) {
        try {
            String encoded = URLEncoder.encode(raw, StandardCharsets.UTF_8);
            return encoded.replace("+", "%20");
        } catch (Exception e) {
            throw new RuntimeException("URL encode failed: " + raw, e);
        }
    }

    /**
     * MD5 哈希计算
     */
    private static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder(32);
            for (byte b : digest) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not available", e);
        }
    }
}