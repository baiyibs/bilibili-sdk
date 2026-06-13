package io.github.baiyibs.bili4j.api.exception.core;

import io.github.baiyibs.bili4j.api.exception.BiliApiException;

/**
 * 表示 URL 格式无效的异常。
 * <p>
 * 当传入的 URL 字符串无法被 {@link okhttp3.HttpUrl#parse(String)} 解析为合法 URL 时抛出。
 * </p>
 */
public class InvalidUrlException extends BiliApiException {

    /**
     * 构造一个包含无效 URL 字符串的异常。
     * @param url 无法被解析的 URL 字符串
     */
    public InvalidUrlException(String url) {
        super("无效的URL: " + url);
    }
}
