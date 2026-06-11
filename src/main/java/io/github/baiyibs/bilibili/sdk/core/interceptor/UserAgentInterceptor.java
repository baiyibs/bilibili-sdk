package io.github.baiyibs.bilibili.sdk.core.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * OkHttp 拦截器，用于为每个 HTTP 请求统一添加自定义的 User-Agent 请求头。
 * <p>
 * 通过将此拦截器添加到 {@link okhttp3.OkHttpClient} 中，可以确保所有发出的请求都携带指定的 User-Agent 字符串，
 * 避免在每次构建请求时重复设置。
 * </p>
 */
public class UserAgentInterceptor implements Interceptor {
    private final String userAgent;

    /**
     * 构造一个 UserAgentInterceptor 实例。
     * @param userAgent 要设置的 User-Agent 字符串（例如 "Mozilla/5.0 (Windows NT 10.0; Win64; x64) ..."）
     */
    public UserAgentInterceptor(String userAgent) {
        this.userAgent = userAgent;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request request = originalRequest.newBuilder()
                .header("User-Agent", userAgent)
                .build();
        return chain.proceed(request);
    }
}
