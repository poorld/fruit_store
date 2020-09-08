package me.teenyda.fruit_store.common.mvp;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.teenyda.fruit_store.common.api.BaseResponse;

/**
 * 数据返回统一处理  参考https://www.jianshu.com/p/ff619fea7e22
 * @param <T>
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {
    private static final String TAG = "BaseObserver";
    @Override
    public void onNext(BaseResponse<T> response) {
        //在这边对 基础数据 进行统一处理  举个例子：
        if(response.getCode()==200){
            onSuccess(response.getData());
        }else{
            onFailure(null,response.getMsg());
        }
    }

    @Override
    public void onError(Throwable e) {//服务器错误信息处理
        onFailure(e, RxExceptionUtil.exceptionHandler(e));
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    public abstract void onSuccess(T result);

    public abstract void onFailure(Throwable e,String errorMsg);

}
