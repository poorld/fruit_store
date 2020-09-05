package me.teenyda.mvp_template.model.home.base.presenter;

import android.util.Log;

import com.trello.rxlifecycle2.components.RxActivity;

import java.util.List;

import me.teenyda.mvp_template.common.entity.Book;
import me.teenyda.mvp_template.common.mvp.BaseRxPresenter;
import me.teenyda.mvp_template.common.utils.MyObserver;
import me.teenyda.mvp_template.common.utils.RxHelper;
import me.teenyda.mvp_template.model.home.base.view.IHomeV;

/**
 * author: teenyda
 * date: 2020/9/5
 * description:
 */
public class HomePRx extends BaseRxPresenter<IHomeV> {

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

    public void getBooks(RxActivity context){
        this.getURL()
                .getDemoList()
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new MyObserver<List<Book>>(context){

                    @Override
                    public void onSuccess(List<Book> result) {
                        Log.i("res", result.toString());
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {

                    }
                });
    }

}
