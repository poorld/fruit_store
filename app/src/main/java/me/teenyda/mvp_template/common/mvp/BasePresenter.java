package me.teenyda.mvp_template.common.mvp;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import me.teenyda.mvp_template.common.api.ApiRetrofit;
import me.teenyda.mvp_template.common.api.ApiServer;
import me.teenyda.mvp_template.common.api.BaseObserver;

/**
 * author: teenyda
 * date: 2019/8/21
 * description:
 */
public class BasePresenter<V extends BaseView, M> {

    protected V mView;

//    protected M mModel;

    protected Context mContext;

    private CompositeDisposable mCompositeDisposable;

    protected ApiServer mApiServer = ApiRetrofit.getInstance().getApiServer();

//    public BasePresenter(V baseView) {
//        mView = baseView;
//    }

    protected void attachView(V v) {
        mView = v;
        mContext = mView.getContext();
    }

    public void detachView() {
        mView = null;
        removeDisposable();
    }

    public void addDisposable(Observable<?> observable, BaseObserver observer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer));

    }

    public void removeDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }
}
