package com.nocyan.springbootdemo.Util;

import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Map;

@Component
public class HttpUtil {
    private OkHttpClient client = new OkHttpClient();
//    private OkHttpClient client = new OkHttpClient.Builder()
//            .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888)))
//            .build();

    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String get(String url,Map<String,String> headers) throws IOException {
        Request.Builder requestBuilder = new Request.Builder()
                .url(url);
        for (Map.Entry<String,String> head:headers.entrySet()) {
            requestBuilder.addHeader(head.getKey(),head.getValue());
        }
        Request request=requestBuilder.build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String post(String url, String json, MediaType mediaType) throws IOException {
        RequestBody body = RequestBody.create(json, mediaType);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
    public String post(String url, String json, MediaType mediaType, Map<String,String> headers) throws IOException {
        RequestBody body = RequestBody.create(json, mediaType);
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .post(body);
        for (Map.Entry<String,String> head:headers.entrySet()) {
            requestBuilder.addHeader(head.getKey(),head.getValue());
        }
        Request request=requestBuilder.build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
