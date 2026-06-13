package io.github.baiyibs.bilibili.sdk.impl.login;

import com.google.gson.reflect.TypeToken;
import io.github.baiyibs.bilibili.sdk.api.login.TvQrcodeLoginApi;
import io.github.baiyibs.bilibili.sdk.auth.AppSigner;
import io.github.baiyibs.bilibili.sdk.core.BiliApiClient;
import io.github.baiyibs.bilibili.sdk.model.ApiResponse;
import io.github.baiyibs.bilibili.sdk.model.login.TvQrcodeData;
import io.github.baiyibs.bilibili.sdk.model.login.TvQrcodePollData;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

public class TvQrcodeLoginApiImpl implements TvQrcodeLoginApi {
    private final BiliApiClient client;

    public TvQrcodeLoginApiImpl(BiliApiClient client) {
        this.client = client;
    }

    /**
     * 申请登录二维码（web端）。
     */
    @Override
    public ApiResponse<TvQrcodeData> generateQrcode() throws IOException {
        String baseUrl = "https://passport.bilibili.com/x/passport-tv-login/qrcode/auth_code";

        Map<String, String> params = new LinkedHashMap<>();
        params.put("local_id", "0");
        params.put("ts", String.valueOf(System.currentTimeMillis() / 1000));

        Map<String, String> signParams = AppSigner.signWithType(params, AppSigner.AppType.TV);

        FormBody.Builder formBuilder = new FormBody.Builder();
        signParams.forEach(
                formBuilder::add
        );

        RequestBody requestBody = formBuilder.build();

        Type type = new TypeToken<ApiResponse<TvQrcodeData>>(){}.getType();

        return client.post(baseUrl, requestBody, type);
    }

    /**
     * 轮询扫码状态（web端）。
     * @param authCode 扫码登录秘钥
     */
    @Override
    public ApiResponse<TvQrcodePollData> pollQrCode(String authCode) throws IOException {
        String baseUrl = "https://passport.bilibili.com/x/passport-tv-login/qrcode/poll";

        Map<String, String> params = new LinkedHashMap<>();
        params.put("auth_code", authCode);
        params.put("local_id", "0");
        params.put("ts", String.valueOf(System.currentTimeMillis() / 1000));

        Map<String, String> signParams = AppSigner.signWithType(params, AppSigner.AppType.TV);

        FormBody.Builder formBuilder = new FormBody.Builder();
        signParams.forEach(
                formBuilder::add
        );

        RequestBody requestBody = formBuilder.build();

        Type type = new TypeToken<ApiResponse<TvQrcodePollData>>(){}.getType();

        return client.post(baseUrl, requestBody, type);
    }
}
