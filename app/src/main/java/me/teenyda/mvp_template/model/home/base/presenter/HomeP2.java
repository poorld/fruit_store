package me.teenyda.mvp_template.model.home.base.presenter;

import android.util.Log;

import com.trello.rxlifecycle2.components.RxActivity;

import me.teenyda.mvp_template.common.entity.Book;
import me.teenyda.mvp_template.common.mvp.BasePresenter2;
import me.teenyda.mvp_template.common.utils.MyObserver;
import me.teenyda.mvp_template.common.utils.RxHelper;
import me.teenyda.mvp_template.model.home.base.view.IHomeV;

/**
 * author: teenyda
 * date: 2020/9/5
 * description:
 */
public class HomeP2 extends BasePresenter2<IHomeV> {

    public void getBook(RxActivity context){
        this.getURL()
                .getDemo()
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new MyObserver<Book>(context){

                    @Override
                    public void onSuccess(Book result) {
                        Log.i("res", result.getBookName());
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {

                    }
                });
    }
}
