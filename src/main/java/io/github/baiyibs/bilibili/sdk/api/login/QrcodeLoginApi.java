package io.github.baiyibs.bilibili.sdk.api.login;

import io.github.baiyibs.bilibili.sdk.model.ApiResponse;
import io.github.baiyibs.bilibili.sdk.model.login.QrcodeData;
import io.github.baiyibs.bilibili.sdk.model.login.QrcodePollData;

import java.io.IOException;

public interface QrcodeLoginApi {
    /**
     * 申请登录二维码（web端）。
     */
    ApiResponse<QrcodeData> generateQrcode() throws IOException;

    /**
     * 轮询扫码状态（web端）。
     */
    ApiResponse<QrcodePollData> pollQrCode(String qrcodeKey) throws IOException;
}
