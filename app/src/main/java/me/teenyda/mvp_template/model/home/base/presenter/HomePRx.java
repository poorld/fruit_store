package me.teenyda.mvp_template.model.home.base.presenter;


import android.graphics.Bitmap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    public void uploadFiles(List<File> files) {
        List<MultipartBody.Part> parts = new ArrayList<>();
        for (File file : files) {
            RequestBody fileRequestBody = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("files", file.getName(), fileRequestBody);
            parts.add(part);
        }

        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "image-type");

        addDisposable(mApi.uploadImages(parts, description), new MyObserver<List<FileUploadResponse>>(mContext) {
            @Override
            public void onSuccess(List<FileUploadResponse> result) {

            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }


    public void compressImage(File file) {
        BitmapUtil.compressImageByIO(file.getPath(), 60, new BitmapUtil.CompressListener() {
            @Override
            public void onCompressSuccess(File file1) {
                mBaserView.compressImageSuccess(file1);
            }

            @Override
            public void onCompressSuccess(Bitmap bitmap) {

            }

            @Override
            public void onCompressError(String error) {

            }

            @Override
            public void onCompressComplete() {

            }
        });

    }

}
