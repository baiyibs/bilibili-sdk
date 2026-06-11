package io.github.baiyibs.bilibili.sdk.impl.login;

import com.google.gson.reflect.TypeToken;
import io.github.baiyibs.bilibili.sdk.api.login.QrcodeLoginApi;
import io.github.baiyibs.bilibili.sdk.core.BiliApiClient;
import io.github.baiyibs.bilibili.sdk.model.ApiResponse;
import io.github.baiyibs.bilibili.sdk.model.login.QrcodeData;

import java.io.IOException;
import java.lang.reflect.Type;

public class QrcodeLoginApiImpl implements QrcodeLoginApi {
    private final BiliApiClient client;

    public QrcodeLoginApiImpl(BiliApiClient client) {
        this.client = client;
    }

    /**
     * 申请登录二维码（web端）。
     */
    @Override
    public ApiResponse<QrcodeData> generateQrcode() throws IOException {
        String baseUrl = "https://passport.bilibili.com/x/passport-login/web/qrcode/generate";
        Type type = new TypeToken<ApiResponse<QrcodeData>>(){}.getType();
        return client.get(baseUrl, type);
    }
}
