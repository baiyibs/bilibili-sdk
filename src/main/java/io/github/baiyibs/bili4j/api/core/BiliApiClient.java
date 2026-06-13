package io.github.baiyibs.bili4j.api.core;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import io.github.baiyibs.bili4j.api.exception.EmptyResponseException;
import io.github.baiyibs.bili4j.api.exception.InvalidUrlException;
import io.github.baiyibs.bili4j.api.core.interceptor.UserAgentInterceptor;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Bili Api 客户端，封装 OkHttp 来发送请求。
 */
public class BiliApiClient {
    private final OkHttpClient httpClient;
    private final LocalCookieJar cookieJar;

    /**
     * 构造一个默认的  {@code BiliApiClient} 实例。
     * <p>使用固定的 Chrome 浏览器 User-Agent 字符串，并通过 {@link UserAgentInterceptor}
     * 为所有请求添加该头。</p>
     */
    public BiliApiClient() {
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/147.0.7727.56 Safari/537.36";

        this.cookieJar = new LocalCookieJar();
        this.httpClient = new OkHttpClient.Builder()
                .addInterceptor(new UserAgentInterceptor(userAgent))
                .cookieJar(cookieJar)
                .build();
    }

    public LocalCookieJar getCookieJar() {
        return this.cookieJar;
    }

    /**
     * 发送无查询参数的 GET 请求，直接返回响应体字符串内容。
     * @param url 完整的请求URL
     * @return 服务器返回的原始 JSON 字符串
     * @throws IOException 如果网络请求失败、连接被中断或响应体无法读取
     */
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = this.httpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    /**
     * 发送带查询参数的 GET 请求，直接返回响应体字符串内容。
     * @param url 完整的请求URL
     * @param params 查询参数的键值对
     * @return 服务器返回的原始 JSON 字符串
     * @throws InvalidUrlException 如果 {@code url} 无法被解析为有效的 URL
     * @throws IOException 如果网络请求失败、连接被中断或响应体无法读取
     */
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

    /**
     * 发送无查询参数的 GET 请求，并将响应体的 JSON 反序列化为指定类型的对象。
     * @param url 完整的请求URL
     * @param type 目标类型的 {@link Type} 描述，通常通过 {@code new TypeToken<T>(){}.getType()} 获取，
     *             用于保留泛型信息（例如 {@code ApiResponse<User>}）
     * @param <T> 目标类型的泛型参数
     * @return 反序列化后的 Java 对象，类型为 {@code T}
     * @throws EmptyResponseException 如果响应体为 {@code null} 或仅包含空白字符
     * @throws JsonSyntaxException    如果 JSON 字符串无法被解析为指定的类型
     * @throws IOException 如果网络请求失败、连接被中断或响应体无法读取
     */
    public <T> T get(String url, Type type) throws IOException {
        String responseJson = this.get(url);
        if (responseJson == null || responseJson.trim().isEmpty()) {
            throw new EmptyResponseException(url);
        }

        try {
            return new Gson().fromJson(responseJson, type);
        } catch (JsonSyntaxException e) {
            throw new JsonSyntaxException("Json解析失败: " + responseJson, e);
        }
    }

    /**
     * 发送带查询参数的 GET 请求，并将响应体的 JSON 反序列化为指定类型的对象。
     * @param url 完整的请求URL
     * @param params 查询参数的键值对
     * @param type 目标类型的 {@link Type} 描述，通常通过 {@code new TypeToken<T>(){}.getType()} 获取，
     *             用于保留泛型信息（例如 {@code ApiResponse<User>}）
     * @param <T> 目标类型的泛型参数
     * @return 反序列化后的 Java 对象，类型为 {@code T}
     * @throws InvalidUrlException 如果 {@code url} 无法被解析为有效的 URL
     * @throws EmptyResponseException 如果响应体为 {@code null} 或仅包含空白字符
     * @throws JsonSyntaxException    如果 JSON 字符串无法被解析为指定的类型
     * @throws IOException  如果网络请求失败、连接被中断或响应体无法读取
     */
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

    public String post(String url, RequestBody requestBody) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try (Response response = this.httpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public <T> T post(String url, RequestBody requestBody, Type type) throws IOException {
        String responseJson = this.post(url, requestBody);
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