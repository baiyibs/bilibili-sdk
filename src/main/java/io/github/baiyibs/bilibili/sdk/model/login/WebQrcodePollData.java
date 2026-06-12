package io.github.baiyibs.bilibili.sdk.model.login;

import com.google.gson.annotations.SerializedName;

public class WebQrcodePollData {
    /**
     * 游戏分站跨域登录 url。
     */
    private String url;

    /**
     * 刷新令牌。
     */
    @SerializedName("refresh_token")
    private String refreshToken;

    /**
     * 登录时间戳，单位为毫秒。
     */
    private Long timestamp;

    /**
     * 扫码状态码。
     * <p>取值及含义：</p>
     * <ul>
     *     <li>{@code 0}：扫码登录成功</li>
     *     <li>{@code 86038}：二维码已失效</li>
     *     <li>{@code 86090}：二维码已扫码但未确认</li>
     *     <li>{@code 86101}：未扫码</li>
     * </ul>
     */
    private Integer code;

    private String message;

    public WebQrcodePollData(String url, String refreshToken, Long timestamp, Integer code, String message) {
        this.url = url;
        this.refreshToken = refreshToken;
        this.timestamp = timestamp;
        this.code = code;
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "QrcodePollData{" +
                "url='" + url + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", timestamp=" + timestamp +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
