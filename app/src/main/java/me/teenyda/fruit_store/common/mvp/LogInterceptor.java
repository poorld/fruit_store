package me.teenyda.fruit_store.common.mvp;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 *  TODO Log拦截器代码
 */
public class LogInterceptor implements Interceptor{
    private String TAG = "okhttp";

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.currentTimeMillis();

        okhttp3.Response response = chain.proceed(chain.request());
        long t2 = System.currentTimeMillis();

        long duration = t2 - t1;

        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();

        Log.e(TAG, "---------- request start ----------");
        Log.e(TAG, "|" + request.toString() + request.headers().toString());
        Log.e(TAG, "| response:" + content);
        Log.e(TAG, "---------- request end:" + duration + "毫秒 ----------");
        return response.newBuilder()
                .addHeader("Accept-Encoding", "gzip")
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .body(ResponseBody.create(mediaType, content))
                .build();
    }
}
