package io.github.baiyibs.bili4j.api.exception;

/**
 * 表示响应体为空或仅包含空白字符的异常。
 * <p>
 * 当 HTTP 请求成功但返回的响应体为 {@code null}、空字符串或仅包含空白字符（空格、换行等）时抛出。
 * </p>
 */
public class EmptyResponseException extends BiliApiException {

    /**
     * 构造一个默认的空响应异常，错误消息为“响应体为空”。
     */
    public EmptyResponseException() {
        super("响应体为空");
    }

    /**
     * 构造一个包含请求 URL 的空响应异常，便于定位具体是哪个接口返回了空响应。
     * @param url 返回空响应的请求 URL
     */
    public EmptyResponseException(String url) {
        super("从 " + url + " 返回的响应体为空");
    }
}
