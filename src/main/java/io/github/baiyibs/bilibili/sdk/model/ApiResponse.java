package io.github.baiyibs.bilibili.sdk.model;

import com.google.gson.annotations.SerializedName;

public class ApiResponse<T> {
    @SerializedName("code")
    private Integer code;
    @SerializedName("message")
    private String message;
    @SerializedName("ttl")
    private Integer ttl;
    @SerializedName("data")
    private T data;

    public ApiResponse(Integer code, String message, Integer ttl, T data) {
        this.code = code;
        this.message = message;
        this.ttl = ttl;
        this.data = data;
    }

    /**
     * 请求返回值。
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 请求响应信息。
     */
    public String getMessage() {
        return message;
    }

    /**
     * 该字段可能不存在，为 {@code null}时表示接口未返回。
     */
    public Integer getTtl() {
        return ttl;
    }

    /**
     * 请求响应的数据。
     */
    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", ttl=" + ttl +
                ", data=" + data +
                '}';
    }
}
