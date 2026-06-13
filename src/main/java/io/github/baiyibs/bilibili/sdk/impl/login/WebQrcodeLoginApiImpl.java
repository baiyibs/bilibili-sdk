package io.github.baiyibs.bilibili.sdk.impl.login;

import com.google.gson.reflect.TypeToken;
import io.github.baiyibs.bilibili.sdk.api.login.WebQrcodeLoginApi;
import io.github.baiyibs.bilibili.sdk.core.BiliApiClient;
import io.github.baiyibs.bilibili.sdk.model.ApiResponse;
import io.github.baiyibs.bilibili.sdk.model.login.WebQrcodeData;
import io.github.baiyibs.bilibili.sdk.model.login.WebQrcodePollData;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class WebQrcodeLoginApiImpl implements WebQrcodeLoginApi {
    private final BiliApiClient client;

    public WebQrcodeLoginApiImpl(BiliApiClient client) {
        this.client = client;
    }

    /**
     * 申请登录二维码（web端）。
     */
    @Override
    public ApiResponse<WebQrcodeData> generateQrcode() throws IOException {
        String baseUrl = "https://passport.bilibili.com/x/passport-login/web/qrcode/generate";
        Type type = new TypeToken<ApiResponse<WebQrcodeData>>(){}.getType();
        return client.get(baseUrl, type);
    }

    /**
     * 轮询扫码状态（web端）。
     * @param qrcodeKey 扫码登录秘钥
     */
    @Override
    public ApiResponse<WebQrcodePollData> pollQrCode(String qrcodeKey) throws IOException {
        String baseUrl = "https://passport.bilibili.com/x/passport-login/web/qrcode/poll";
        Type type = new TypeToken<ApiResponse<WebQrcodePollData>>(){}.getType();

        Map<String, String> params = new HashMap<>();
        params.put("qrcode_key", qrcodeKey);
        return client.get(baseUrl, params,type);
    }
}
