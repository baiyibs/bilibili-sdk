package io.github.baiyibs.bili4j.api.api.login;

import io.github.baiyibs.bili4j.api.model.ApiResponse;
import io.github.baiyibs.bili4j.api.model.login.TvQrcodeData;
import io.github.baiyibs.bili4j.api.model.login.TvQrcodePollData;

import java.io.IOException;

public interface TvQrcodeLoginApi {

    /**
     * 申请登录二维码（web端）。
     * @return {@link ApiResponse}&lt;{@link TvQrcodeData}&gt;
     * @throws IOException 发生错误时
     */
    ApiResponse<TvQrcodeData> generateQrcode() throws IOException;

    /**
     * 轮询扫码状态（web端）。
     * @param authCode 扫码秘钥
     * @return {@link ApiResponse}&lt;{@link TvQrcodePollData}&gt;
     * @throws IOException 发生错误时
     */
    ApiResponse<TvQrcodePollData> pollQrCode(String authCode) throws IOException;
}
