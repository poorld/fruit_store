package me.teenyda.fruit_store.common.mvp;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import me.teenyda.fruit_store.common.api.ApiUrl;
import me.teenyda.fruit_store.common.api.BaseResponse;
import me.teenyda.fruit_store.common.utils.RetrofitUtils;

/**
 * author: teenyda
 * date: 2019/8/21
 * description:
 */
public class BaseRxPresenter<V extends BaseView> {

    protected V mBaserView;

    protected Context mContext;

    protected ApiUrl mApi;

    public interface IResponse<T>{
        void success(T result);
        void failure(String errorMsg);
    }

    public interface IResponseList<T>{
        void success(List<T> result);
        void failure(String errorMsg);
    }


//    public BasePresenter(V baseView) {
//        mBaserView = baseView;
//    }

    protected void attachView(V v) {
        mBaserView = v;
        mContext = mBaserView.getMContext();
        mApi = getURL();
    }

    protected void detachView() {
        mBaserView = null;
    }

    protected ApiUrl getURL(){
        return RetrofitUtils.getApiUrl();
    }

    protected <T> void addDisposable(Observable<BaseResponse<T>> observable, BaseObserver<T> observer) {
        observable
                .compose(RxHelper.observableIO2Main(mContext))
                .subscribe(observer);
    }


    protected <T> void addDisposable1(Observable<BaseResponse<T>> observable, IResponse<T> response){
        observable
            .compose(RxHelper.observableIO2Main(mContext))
            .subscribe(new MyObserver<T>(mContext) {
                @Override
                public void onSuccess(T result) {
                    if (response != null)
                        response.success(result);
                }

                @Override
                public void onFailure(Throwable e, String errorMsg) {
                    if (response != null)
                        response.failure(errorMsg);
                }
            });
    }

    protected <T> void addDisposable2(Observable<BaseResponse<List<T>>> observable, IResponseList<T> response){
        observable
                .compose(RxHelper.observableIO2Main(mContext))
                .subscribe(new MyObserver<List<T>>(mContext) {
                    @Override
                    public void onSuccess(List<T> result) {
                        if (response != null)
                            response.success(result);
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        if (response != null)
                            response.failure(errorMsg);
                    }
                });
    }

}
