package me.teenyda.mvp_template.common.utils;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class BitmapUtil {

    /**
     * 图片压缩
     * @param path
     * @return
     * @throws IOException
     */
    public static File compressImage(String path) throws IOException {
        Bitmap rwaTakenImage = BitmapFactory.decodeFile(path);
        // BitmapScaler.scaleToFitWidth(rwaTakenImage, )
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        rwaTakenImage.compress(Bitmap.CompressFormat.JPEG, 60, baos);
        File resizedFile = new File(path);

        FileOutputStream fos = new FileOutputStream(resizedFile);

        fos.write(baos.toByteArray());
        fos.flush();
        fos.close();

        return resizedFile;
    }

    /**
     * 读取图片的旋转的角度
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            String attribute1 = exifInterface.getAttribute(ExifInterface.TAG_ORIENTATION);
            int attribute2 = ExifInterface.ORIENTATION_NORMAL;

            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 将图片按照某个角度进行旋转
     *
     * @param bm     需要旋转的图片
     * @param degree 旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;

        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (returnBm == null) {
            returnBm = bm;
        }

        if (bm != null && bm != returnBm) {
            bm.recycle();
        }

        return returnBm;
    }

    public static List<Bitmap> getBitmapByClipData(@NonNull Context context, ClipData clipData) {
        if (clipData == null) {
            return null;
        }

        List<Uri> uris = new ArrayList<>();
        List<Bitmap> bitmaps = new ArrayList<>();

        try {

            for (int i = 0; i < clipData.getItemCount(); i++) {
                ClipData.Item item = clipData.getItemAt(i);
                Uri uri = item.getUri();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
                bitmaps.add(bitmap);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmaps;
    }

    public interface CompressListener{
        void onCompressSuccess(Bitmap bitmap);
        void onCompressError(String error);
        void onCompressComplete();
    }

    public static void compressImage(File file, CompressListener compressListener) {
        Observable.just(file)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .map(new Function<File, Bitmap>() {
                    @Override
                    public Bitmap apply(File file) throws Exception {
                        File compressFile = compressImage(file.getPath());
                        Bitmap bitmap = BitmapFactory.decodeFile(compressFile.getPath());
                        return bitmap;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        compressListener.onCompressSuccess(bitmap);
                    }

                    @Override
                    public void onError(Throwable e) {
                        compressListener.onCompressError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        compressListener.onCompressComplete();
                    }
                });
    };

}
