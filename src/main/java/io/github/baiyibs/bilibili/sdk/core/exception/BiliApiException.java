package io.github.baiyibs.bilibili.sdk.core.exception;

import java.io.IOException;

public class BiliApiException extends IOException {
    public BiliApiException(String message) {
        super(message);
    }

    public BiliApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
