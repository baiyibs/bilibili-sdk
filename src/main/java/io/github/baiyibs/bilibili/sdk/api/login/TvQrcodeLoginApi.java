package io.github.baiyibs.bilibili.sdk.api.login;

import io.github.baiyibs.bilibili.sdk.model.ApiResponse;
import io.github.baiyibs.bilibili.sdk.model.login.TvQrcodeData;
import io.github.baiyibs.bilibili.sdk.model.login.WebQrcodeData;
import io.github.baiyibs.bilibili.sdk.model.login.WebQrcodePollData;

import java.io.IOException;

public interface TvQrcodeLoginApi {

    /**
     * 申请登录二维码（web端）。
     */
    ApiResponse<TvQrcodeData> generateQrcode() throws IOException;

    /**
     * 轮询扫码状态（web端）。
     */
    ApiResponse<WebQrcodePollData> pollQrCode(String qrcodeKey) throws IOException;
}
