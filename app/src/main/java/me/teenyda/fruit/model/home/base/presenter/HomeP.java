package me.teenyda.fruit.model.home.base.presenter;

import java.io.File;
import java.util.List;

import me.teenyda.fruit.common.api.BaseObserver;
import me.teenyda.fruit.common.entity.Book;
import me.teenyda.fruit.common.mvp.BasePresenter;
import me.teenyda.fruit.model.home.base.view.IHomeV;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class HomeP extends BasePresenter<IHomeV> {

    public void compressImage(File file) {
        /*Observable.create(new ObservableOnSubscribe<File>() {
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
                        File resizedFile =
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
                        mView.compressImageSuccess(file);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/

//        BitmapUtil.compressImageAndSave(file.getPath(), 60);
    }

    public void uploadFile(File file) {
        RequestBody fileRequestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), fileRequestBody);
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "image-type");

    }

    public void getBook() {
        addDisposable1(mApiServer.book(), new BaseObserver<Book>(mBaserView) {
            @Override
            public void onSuccess(Book result) {

            }

            @Override
            public void onError(String errorMsg) {

            }
        });


    }

    public void getBooks() {

        addDisposable1(mApiServer.books(), new BaseObserver<List<Book>>(mBaserView) {
            @Override
            public void onSuccess(List<Book> result) {

            }

            @Override
            public void onError(String errorMsg) {

            }
        });

    }
}
