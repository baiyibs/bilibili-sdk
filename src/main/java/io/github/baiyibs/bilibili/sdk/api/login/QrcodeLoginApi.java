package io.github.baiyibs.bilibili.sdk.api.login;

import io.github.baiyibs.bilibili.sdk.model.ApiResponse;
import io.github.baiyibs.bilibili.sdk.model.login.QrcodeData;

import java.io.IOException;

public interface QrcodeLoginApi {
    /**
     * 申请登录二维码（web端）。
     */
    ApiResponse<QrcodeData> generateQrcode() throws IOException;
}
