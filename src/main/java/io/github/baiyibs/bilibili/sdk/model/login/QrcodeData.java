package io.github.baiyibs.bilibili.sdk.model.login;

import com.google.gson.annotations.SerializedName;

public class QrcodeData {
    @SerializedName("url")
    private String url;
    @SerializedName("qrcode_key")
    private String qrcodeKey;

    public QrcodeData(String url, String qrCodeKey) {
        this.url = url;
        this.qrcodeKey = qrCodeKey;
    }

    public String getUrl() {
        return url;
    }

    public String getQrcodeKey() {
        return qrcodeKey;
    }
}
