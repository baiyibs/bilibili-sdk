package io.github.baiyibs.bilibili.sdk.core.exception;

public class EmptyResponseException extends BiliApiException {
    public EmptyResponseException() {
        super("响应体为空");
    }
    public EmptyResponseException(String url) {
        super("从 " + url + " 返回的响应体为空");
    }
}
