package me.teenyda.mvp_template.model.home.base.presenter;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.teenyda.mvp_template.common.entity.Book;
import me.teenyda.mvp_template.common.entity.FileUploadResponse;
import me.teenyda.mvp_template.common.mvp.BaseRxPresenter;
import me.teenyda.mvp_template.common.utils.BitmapUtil;
import me.teenyda.mvp_template.common.utils.MyObserver;
import me.teenyda.mvp_template.model.home.base.view.IHomeV;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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

    public void uploadFile(File file) {
        RequestBody fileRequestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), fileRequestBody);
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "image-type");
        addDisposable(mApi.uploadImage(part, description), new MyObserver<FileUploadResponse>(mContext) {
            @Override
            public void onSuccess(FileUploadResponse result) {
                mBaserView.showImage(result);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }


    public void compressImage(File file) {
        Observable.create(new ObservableOnSubscribe<File>() {
            @Override
            public void subscribe(ObservableEmitter<File> emitter) throws Exception {
                if (file != null) {
                    emitter.onNext(file);
                }
                emitter.onComplete();

            }
        })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .map(new Function<File, File>() {
                    @Override
                    public File apply(File file) throws Exception {

//                        BitmapUtil.
                        File resizedFile = BitmapUtil.compressImage(file.getPath());

                        return resizedFile;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<File>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(File file) {
                        mBaserView.compressImageSuccess(file);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
