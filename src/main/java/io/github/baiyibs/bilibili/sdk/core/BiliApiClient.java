package io.github.baiyibs.bilibili.sdk.core;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import io.github.baiyibs.bilibili.sdk.core.exception.EmptyResponseException;
import io.github.baiyibs.bilibili.sdk.core.exception.InvalidUrlException;
import io.github.baiyibs.bilibili.sdk.core.interceptor.UserAgentInterceptor;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class BiliApiClient {
    private final OkHttpClient httpClient;

    public BiliApiClient() {
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/147.0.7727.56 Safari/537.36";
        this.httpClient = new OkHttpClient.Builder()
                .addInterceptor(new UserAgentInterceptor(userAgent))
                .build();
    }

    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = this.httpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String get(String url, Map<String, String> params) throws IOException {
        HttpUrl requestUrl =  HttpUrl.parse(url);
        if (requestUrl == null) {
            throw new InvalidUrlException(url);
        }

        HttpUrl.Builder requestUrlBuilder = requestUrl.newBuilder();

        if (params != null) {
            params.forEach(
                    requestUrlBuilder::addQueryParameter
            );
        }
        String requestUrlWithParams =  requestUrlBuilder.build().toString();
        return this.get(requestUrlWithParams);
    }

    public <T> T get(String url, Map<String, String> params, Type type) throws IOException {
        String responseJson = this.get(url, params);
        if (responseJson == null || responseJson.trim().isEmpty()) {
            throw new EmptyResponseException(url);
        }

        try {
            return new Gson().fromJson(responseJson, type);
        } catch (JsonSyntaxException e) {
            throw new JsonSyntaxException("Json解析失败: " + responseJson, e);
        }
    }
}