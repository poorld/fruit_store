package me.teenyda.fruit.model.home.base.presenter;


import android.app.Activity;
import android.graphics.Bitmap;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.teenyda.fruit.common.entity.Banner;
import me.teenyda.fruit.common.entity.Book;
import me.teenyda.fruit.common.entity.FileUploadResponse;
import me.teenyda.fruit.common.mvp.BaseRxPresenter;
import me.teenyda.fruit.common.pictureselector.GlideEngine;
import me.teenyda.fruit.common.utils.BitmapUtil;
import me.teenyda.fruit.common.utils.ConstansUtil;
import me.teenyda.fruit.common.mvp.MyObserver;
import me.teenyda.fruit.common.utils.LogUtils;
import me.teenyda.fruit.model.home.base.view.IHomeV;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * author: teenyda
 * date: 2020/9/5
 * description:
 */
public class HomePRx extends BaseRxPresenter<IHomeV> {

    public void getBanners() {
        addDisposable(mApi.getBanners(), new MyObserver<List<Banner>>(mContext) {
            @Override
            public void onSuccess(List<Banner> banners) {
                mView.setBanners(banners);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }

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

    public void addBoos(Book book){
        this.addDisposable(mApi.addBook(book), new MyObserver<String>(mContext){

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
                // mView.showImage(result);
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


    public void compressImage(File file, int minSize) {
        BitmapUtil.compressImageAndSaveByIO(file.getPath(), minSize, new BitmapUtil.CompressListener() {
            @Override
            public void onCompressSuccess(File file1) {
                // mView.compressImageSuccess(file1);
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

    /**
     * 打开相册
     */
    public void pictureSelector() {
        //相册
        PictureSelector.create((Activity) mContext)
                .openGallery(PictureMimeType.ofImage())
                .isCamera(false)
                .imageEngine(GlideEngine.createGlideEngine())
                .maxSelectNum(3)
                .isCompress(true)
                .minimumCompressSize(500)
                .compressSavePath(ConstansUtil.getStoragePath())
                .forResult(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        // 结果回调
                        // 1.media.getPath(); 为原图path
                        // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                        // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                        // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                        List<File> files = new ArrayList<>();

                        for (LocalMedia localMedia : result) {
                            File file = null;
                            if (localMedia.isCompressed()) {
                                file = new File(localMedia.getCompressPath());
                            } else {
                                file = new File(localMedia.getPath());
                            }

                            files.add(file);

                            LogUtils.e(localMedia.getSize() + "");
                            LogUtils.e(localMedia.getPath() + "");
                            LogUtils.e(localMedia.isCompressed() + "");
                            LogUtils.e(localMedia.getCompressPath() + "");

                            // Log.i(Constans.TAG, localMedia.getSize() + "");
                            // Log.i(Constans.TAG, localMedia.getPath());
                            // Log.i(Constans.TAG, localMedia.isCompressed() + "");
                            // Log.i(Constans.TAG, localMedia.getCompressPath());


                        }

                        uploadFiles(files);
                    }

                    @Override
                    public void onCancel() {
                        // 取消
                    }
                });
    }

}
