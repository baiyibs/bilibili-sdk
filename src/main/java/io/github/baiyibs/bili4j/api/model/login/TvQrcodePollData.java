package io.github.baiyibs.bili4j.api.model.login;

import com.google.gson.annotations.SerializedName;
import io.github.baiyibs.bili4j.api.model.ApiResponse;

/**
 * TV 端扫码登录轮询数据
 * <p>
 *     二维码状态在 {@link ApiResponse} 中的 {@code code} 表示
 * </p>
 * <ul>
 *     <li>{@code 0}：成功</li>
 *     <li>{@code -3}：API校验密匙错误</li>
 *     <li>{@code -400}：请求错误</li>
 *     <li>{@code -404}：啥都木有</li>
 *     <li>{@code 86038}：二维码已失效</li>
 *     <li>{@code 86039}：二维码尚未确认</li>
 *     <li>{@code 86090}：二维码已扫码未确认</li>
 * </ul>
 */
public class TvQrcodePollData {

    /**
     * 登录用户 mid
     */
    private final Integer mid;

    /**
     * APP登录Token
     */
    @SerializedName("access_token")
    private final String accessToken;

    /**
     * APP刷新Token
     */
    @SerializedName("refresh_token")
    private final String refreshToken;

    /**
     * 有效时间
     */
    @SerializedName("expires_in")
    private final Integer expiresIn;

    public TvQrcodePollData(Integer mid, String accessToken, String refreshToken, Integer expiresIn) {
        this.mid = mid;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
    }

    public Integer getMid() {
        return mid;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    @Override
    public String toString() {
        return "TvQrcodePollData{" +
                "mid=" + mid +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}
