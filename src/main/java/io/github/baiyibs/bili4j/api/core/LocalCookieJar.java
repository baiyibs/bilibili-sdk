package io.github.baiyibs.bili4j.api.core;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class LocalCookieJar implements CookieJar {

    private final ConcurrentHashMap<String, List<Cookie>> cookieStore = new ConcurrentHashMap<>();

    @Override
    public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> cookies) {
        if (cookies.isEmpty()) {
            return;
        }

        cookieStore.put(httpUrl.host(), cookies);
    }

    @NotNull
    @Override
    public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
        List<Cookie> cookies = cookieStore.get(httpUrl.host());
        return cookies != null ? cookies : new ArrayList<>();
    }

    public String getCookieString(String host) {
        List<Cookie> cookies = cookieStore.get(host);
        if (cookies == null || cookies.isEmpty()) {
            return "";
        }

        return cookies.stream()
                .map(cookie -> cookie.name() + "=" + cookie.value())
                .collect(Collectors.joining("; "));
    }

    public void clearCookies(String host) {
        cookieStore.remove(host);
    }

    public void clearAll() {
        cookieStore.clear();
    }
}
