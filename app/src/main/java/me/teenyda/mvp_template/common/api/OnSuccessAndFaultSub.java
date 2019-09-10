package me.teenyda.mvp_template.common.api;

import io.reactivex.observers.DisposableObserver;
import me.teenyda.mvp_template.common.net.resp.BaseResponse;

public class OnSuccessAndFaultSub extends DisposableObserver<BaseResponse> {

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onNext(BaseResponse baseResponse) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
