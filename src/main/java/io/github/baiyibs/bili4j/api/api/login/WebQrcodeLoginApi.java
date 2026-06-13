package io.github.baiyibs.bili4j.api.api.login;

import io.github.baiyibs.bili4j.api.model.ApiResponse;
import io.github.baiyibs.bili4j.api.model.login.WebQrcodeData;
import io.github.baiyibs.bili4j.api.model.login.WebQrcodePollData;

import java.io.IOException;

public interface WebQrcodeLoginApi {
    /**
     * 申请登录二维码（web端）。
     * @return {@link ApiResponse}&lt;{@link WebQrcodeData}&gt;
     * @throws IOException 发生错误时
     */
    ApiResponse<WebQrcodeData> generateQrcode() throws IOException;

    /**
     * 轮询扫码状态（web端）。
     * @param qrcodeKey 扫码秘钥
     * @return {@link ApiResponse}&lt;{@link WebQrcodePollData}&gt;
     * @throws IOException 发生错误时
     */
    ApiResponse<WebQrcodePollData> pollQrCode(String qrcodeKey) throws IOException;
}
