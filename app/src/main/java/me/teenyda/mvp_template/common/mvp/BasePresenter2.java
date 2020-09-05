package me.teenyda.mvp_template.common.mvp;

import android.content.Context;

import com.alibaba.fastjson.JSON;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.teenyda.mvp_template.common.api.ApiRetrofit;
import me.teenyda.mvp_template.common.api.ApiServer;
import me.teenyda.mvp_template.common.api.ApiUrl;
import me.teenyda.mvp_template.common.api.BaseObserver;
import me.teenyda.mvp_template.common.net.resp.BaseResponse;
import me.teenyda.mvp_template.common.utils.RetrofitUtils;

/**
 * author: teenyda
 * date: 2019/8/21
 * description:
 */
public class BasePresenter2<V extends BaseView> {

    protected V mBaserView;

    protected Context mContext;


//    public BasePresenter(V baseView) {
//        mBaserView = baseView;
//    }

    protected void attachView(V v) {
        mBaserView = v;
        mContext = mBaserView.getMContext();
    }

    protected void detachView() {
        mBaserView = null;
    }

    protected ApiUrl getURL(){
        return RetrofitUtils.getApiUrl();
    }


}
