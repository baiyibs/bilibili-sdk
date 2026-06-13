package io.github.baiyibs.bilibili.sdk.model.login;

import com.google.gson.annotations.SerializedName;

public class TvQrcodeData {

    /**
     * 二维码内容 (登录页面 url)。
     */
    private final String url;

    /**
     * 扫码登录秘钥。
     */
    @SerializedName("auth_code")
    private String auth_code;

    public TvQrcodeData(String url, String auth_code) {
        this.url = url;
        this.auth_code = auth_code;
    }

    public String getUrl() {
        return url;
    }

    public String getAuthCode() {
        return auth_code;
    }

    @Override
    public String toString() {
        return "TvQrcodeData{" +
                "url='" + url + '\'' +
                ", auth_code='" + auth_code + '\'' +
                '}';
    }
}
