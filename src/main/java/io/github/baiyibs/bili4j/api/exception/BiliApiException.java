package io.github.baiyibs.bili4j.api.exception;

import java.io.IOException;

/**
 * Bili Api 的基础异常类，所有 SDK 相关的受检异常都应该继承此类。
 */
public class BiliApiException extends IOException {

    /**
     * 构造一个仅包含错误消息的异常。
     * @param message 异常详细信息
     */
    public BiliApiException(String message) {
        super(message);
    }

    /**
     * 构造一个包含错误消息和原始原因的异常。
     * @param message 异常详细信息
     * @param cause 原始异常（如 {@link java.net.ConnectException}、{@link com.google.gson.JsonSyntaxException}）
     */
    public BiliApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
