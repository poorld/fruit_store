package me.teenyda.mvp_template.model.home.base.presenter;


import java.util.List;

import me.teenyda.mvp_template.common.entity.Book;
import me.teenyda.mvp_template.common.mvp.BaseRxPresenter;
import me.teenyda.mvp_template.common.utils.MyObserver;
import me.teenyda.mvp_template.common.utils.ResponseEntity;
import me.teenyda.mvp_template.model.home.base.view.IHomeV;

/**
 * author: teenyda
 * date: 2020/9/5
 * description:
 */
public class HomePRx extends BaseRxPresenter<IHomeV> {

    public void getBook(){
        /*this.getURL()
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
                });*/

        addDisposable(mApi.getDemo(), new MyObserver<Book>(mContext){

            @Override
            public void onSuccess(Book result) {

            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }

    public void getBooks(){
        /*this.getURL()
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
                });*/
        /*this.addDisposable2(mApi.getDemoList(), new IResponseList<Book>() {
            @Override
            public void success(List<Book> result) {

            }

            @Override
            public void failure(String errorMsg) {

            }
        });*/

        addDisposable(mApi.getDemoList(), new MyObserver<List<Book>>(mContext) {
            @Override
            public void onSuccess(List<Book> result) {

            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }


    public void updateBoos(Book book){
        this.addDisposable(mApi.updateBook(book), new MyObserver<String>(mContext){

            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });

    }

}
