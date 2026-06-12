package io.github.baiyibs.bilibili.sdk.model.login;

import com.google.gson.annotations.SerializedName;

public class WebQrcodeData {

    /**
     * 二维码内容 (登录页面 url)。
     */
    private String url;

    /**
     * 扫码登录秘钥。
     */
    @SerializedName("qrcode_key")
    private String qrcodeKey;

    public WebQrcodeData(String url, String qrCodeKey) {
        this.url = url;
        this.qrcodeKey = qrCodeKey;
    }

    public String getUrl() {
        return url;
    }

    public String getQrcodeKey() {
        return qrcodeKey;
    }

    @Override
    public String toString() {
        return "QrcodeData{" +
                "url='" + url + '\'' +
                ", qrcodeKey='" + qrcodeKey + '\'' +
                '}';
    }
}
