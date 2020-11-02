package me.teenyda.fruit.common.mvp;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import me.teenyda.fruit.common.api.ApiRetrofit;
import me.teenyda.fruit.common.api.ApiServer;
import me.teenyda.fruit.common.api.BaseObserver;

/**
 * author: teenyda
 * date: 2019/8/21
 * description:
 */
public class BasePresenter<V extends BaseView> {

    protected V mBaserView;

    protected Context mContext;

    private CompositeDisposable mCompositeDisposable;

    protected ApiServer mApiServer = ApiRetrofit.getInstance().getApiServer();

//    public BasePresenter(V baseView) {
//        mView = baseView;
//    }

    protected void attachView(V v) {
        mBaserView = v;
        mContext = mBaserView.getMContext();
    }

    public void detachView() {
        mBaserView = null;
        removeDisposable();
    }

    public <T> void addDisposable1(Observable<T> observable, BaseObserver<T> observer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(observer));

    }

    public <T> void addDisposable2(Observable<T> observable, BaseObserver<T> observer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(observer));

    }


//    public <T,R> void addDisposable3(Observable<T> observable,Observer<? super R> observer, Class<R> clazz) {
//
//            observable
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .map(new Function<T, R>() {
//                    @Override
//                    public R apply(T t) throws Exception {
//                        String data = ((BaseResponse)t).getData();
//                        if (JSON.isValidObject(data)) {
//                            R r = JSON.parseObject(data, clazz);
//                            return r;
//                        }
//                        return null;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
//
//    }


    public void removeDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }
}
