package io.github.baiyibs.bilibili.sdk.core.exception;

public class InvalidUrlException extends BiliApiException{
    public InvalidUrlException(String url) {
        super("无效的URL: " + url);
    }
}
