package me.teenyda.mvp_template.common.api;

import android.net.ParseException;

import com.alibaba.fastjson.JSONException;

import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.observers.DisposableObserver;
import me.teenyda.mvp_template.common.mvp.BaseView;
import me.teenyda.mvp_template.common.net.resp.BaseResponse;
import retrofit2.HttpException;

/**
 * author: teenyda
 * date: 2019/8/21
 * description: 处理了常见的错误
 */
public abstract class BaseObserver<T> extends DisposableObserver<T> {

    protected BaseView mView;

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

    /**
     * 成功
     * @param t
     */
    public abstract void onSuccess(T t);

    /**
     * 失败
     * @param errorMsg
     */
    public abstract void onError(String errorMsg);

    @Override
    protected void onStart() {
//        super.onStart();
        if (mView != null) {
            mView.showLoading();
        }
    }

    @Override
    public void onNext(T t) {
//        try {
//            BaseResponse response = (BaseResponse) t;
//            if (response.getCode() == 200) {
//                onSuccess(t);
//            } else {
//                if (mView != null) {
//                    mView.onErrorCode(response);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            onError(e.toString());
//        }

    }

    @Override
    public void onError(Throwable e) {
        if (mView != null) {
            mView.hideLoading();
        }

        if (e instanceof HttpException) {
            // HTTP错误
            onException(BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            // 连接错误
            onException(CONNECT_ERROR);
        } else if (e instanceof InterruptedException) {
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
                break;

            case CONNECT_TIMEOUT:
                onError("连接超时");
                break;

            case BAD_NETWORK:
                onError("网络问题");
                break;

            case PARSE_ERROR:
                onError("解析数据失败");
                break;

            default:
                break;
        }
    }

    @Override
    public void onComplete() {
        if (mView != null) {
            mView.hideLoading();
        }
    }
}
