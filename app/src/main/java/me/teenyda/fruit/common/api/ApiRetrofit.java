package me.teenyda.fruit.common.api;

import android.util.Log;

import com.baronzhang.retrofit2.converter.FastJsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * author: teenyda
 * date: 2019/8/21
 * @description: 初始化Retofit、OkHttpClient，加入拦截器
 */
public class ApiRetrofit {

    private static ApiRetrofit mApiRetrofit;

    private Retrofit mRetrofit;

    private OkHttpClient mClient;

    private ApiServer mApiServer;

    private String TAG = ApiRetrofit.class.getSimpleName();

    /**
     * response拦截器
     */
    private Interceptor mInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long startTime = System.currentTimeMillis();
            Response response = chain.proceed(chain.request());
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            MediaType mediaType = response.body().contentType();
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
    };

    public ApiRetrofit() {
        mClient = new OkHttpClient.Builder()
                .addInterceptor(mInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(URL.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
//                .addConverterFactory(ScalarsConverterFactory.create())
                // 支持rxjava2
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mClient)
                .build();

        mApiServer = mRetrofit.create(ApiServer.class);

    }

    public static ApiRetrofit getInstance() {
        if (mApiRetrofit == null) {
            synchronized (Object.class) {
                if (mApiRetrofit == null) {
                    mApiRetrofit = new ApiRetrofit();
                }
            }
        }
        return mApiRetrofit;
    }

    public ApiServer getApiServer() {
        return mApiServer;
    }
}
