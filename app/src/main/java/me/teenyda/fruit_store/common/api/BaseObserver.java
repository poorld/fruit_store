package me.teenyda.fruit_store.common.api;

import android.net.ParseException;

import com.alibaba.fastjson.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.observers.DisposableObserver;
import me.teenyda.fruit_store.common.mvp.BaseView;
import retrofit2.HttpException;

/**
 * author: teenyda
 * date: 2019/8/21
 * description: 处理了常见的错误
 */
public abstract class BaseObserver<T> extends DisposableObserver<T> {

    protected BaseView mView;
    private boolean mShowLoading = false;
    /**
     * 解析数据失败
     */
    public static final int PARSE_ERROR = 1001;
    /**
     * 网络问题
     */
    public static final int BAD_NETWORK = 1002;
    /**
     * 连接错误
     */
    public static final int CONNECT_ERROR = 1003;
    /**
     * 连接超时
     */
    public static final int CONNECT_TIMEOUT = 1004;

    public BaseObserver(BaseView view) {
        mView = view;
    }
    public BaseObserver(BaseView view, boolean showLoading) {
        mView = view;
        mShowLoading = showLoading;
    }

    /**
     * 成功
     * @param result
     */
    public abstract void onSuccess(T result);

    /**
     * 失败
     * @param errorMsg
     */
    public abstract void onError(String errorMsg);

    @Override
    protected void onStart() {
//        super.onStart();
        if (mView != null && mShowLoading) {
            mView.showLoading();
        }
    }

    @Override
    public void onNext(T t) {
        Thread thread = Thread.currentThread();
        onSuccess(t);
//        try {
//            BaseResponse response = (BaseResponse) t;
//            switch (response.getCode()) {
//                case 200:
//                    onSuccess((R) response.getData());
//                    break;
//                case 40001:
//                    mView.showToast(response.getMsg());
//                    break;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            onError(e.toString());
//        }

    }

    @Override
    public void onError(Throwable e) {
        if (mView != null && mShowLoading) {
            mView.hideLoading();
        }

        if (e instanceof HttpException) {
            // HTTP错误
            onException(BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            // 连接错误
            onException(CONNECT_ERROR);
        } else if (e instanceof InterruptedException
            || e instanceof SocketTimeoutException) {
            // 连接超时
            onException(CONNECT_TIMEOUT);
        } else if (e instanceof JSONException
                || e instanceof ParseException) {
            // 解析错误
            onException(PARSE_ERROR);
        } else {
            if (e != null) {
                onError(e.toString());
            } else {
                onError("未知错误");
            }
        }
    }

    private void onException(int unknownError) {
        switch (unknownError) {
            case CONNECT_ERROR:
                onError("连接错误");
                mView.showToast("连接错误");
                break;

            case CONNECT_TIMEOUT:
                onError("网络连接超时");
                mView.showToast("网络连接超时");
                break;

            case BAD_NETWORK:
                onError("网络出错");
                mView.showToast("网络出错");
                break;

            case PARSE_ERROR:
                onError("解析数据失败");
                mView.showToast("解析数据失败");
                break;

            default:
                break;
        }
    }

    @Override
    public void onComplete() {
        if (mView != null && mShowLoading) {
            mView.hideLoading();
        }
    }
}
