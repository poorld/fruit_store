package me.teenyda.mvp_template.common.utils;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

        //这里有一个坑,如果你想要读取照片的角度信息,那么就需要直接吧byte[] data的照片数据先保存成图片文件在从文件读成Bitmap
        //因为如果你先处理压缩图片或者裁剪图片,只要是Bitmap.createBitmap处理过就都有可能丢失这些照片信息到时候你怎么获取角度都是0
//                    FilePathSession.deleteFaceImageFile();
//        int degree = getBitmapDegree(path);
//
//        Bitmap rwaTakenImage = BitmapFactory.decodeFile(path);
//
//        Matrix matrix = new Matrix();//创建矩阵配置类,用与设置旋转角度和旋转位置
//        matrix.setRotate(degree, rwaTakenImage.getWidth(), rwaTakenImage.getHeight());//设置旋转角度和旋转位置
//
//        Bitmap handlerAngleBitmap = Bitmap.createBitmap(rwaTakenImage,0,0,rwaTakenImage.getWidth(),rwaTakenImage.getHeight(),matrix,true);

        Bitmap bitmap = getBitmapWithRightRotation(path);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, baos);
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
     * 获取正确的旋转角度的图片——一般由系统相机拍照才会导致此情况
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    public static Bitmap getBitmapWithRightRotation(String path) {
        int degree = 0;
        degree = getBitmapDegree(path);
//        Log.e("CameraUtils", "degree: " + degree);
        Bitmap bm = BitmapFactory.decodeFile(path);
        //照片没有被旋转角度，直接返回原图片
        if (degree == 0) {
            return bm;
        }
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
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
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

    public static Bitmap getBitmapByUri(@NonNull Context context, Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
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

    /**
     * 保存图片到本地
     * @param context
     * @param uri
     */
    public static void saveBitmapToLoaction(@NonNull Context context, Uri uri) {
        // Assume block needs to be inside a Try/Catch block.
        String path = Environment.getExternalStorageDirectory().toString();
        OutputStream fOut = null;
        Integer counter = 0;
        File file = new File(path, "FitnessGirl"+counter+".jpg"); // the File to save , append increasing numeric counter to prevent files from getting overwritten.
        try {
            fOut = new FileOutputStream(file);
            Bitmap pictureBitmap = getBitmapByUri(context, uri); // obtaining the Bitmap
            pictureBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
            fOut.flush(); // Not really required
            fOut.close(); // do not forget to close the stream

            MediaStore.Images.Media.insertImage(context.getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * @description 计算图片的压缩比率
     *
     * @param options 参数
     * @param reqWidth 目标的宽度
     * @param reqHeight 目标的高度
     * @return
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;	//2的倍数会更加高效
            }
        }
        return inSampleSize;
    }

    /**
     * @description 通过传入的bitmap，进行压缩，得到符合标准的bitmap
     *
     * @param src
     * @param dstWidth
     * @param dstHeight
     * @return
     */
    private static Bitmap createScaleBitmap(Bitmap src, int dstWidth, int dstHeight, int inSampleSize) {
        // 如果是放大图片，filter决定是否平滑，如果是缩小图片，filter无影响，我们这里是缩小图片，所以直接设置为false
        Bitmap dst = Bitmap.createScaledBitmap(src, dstWidth, dstHeight, true);
        if (src != dst) { // 如果没有缩放，那么不回收
            src.recycle(); // 释放Bitmap的native像素数组
        }
        return dst;
    }

    /**
     * @description 加载图片
     *
     * @param pathName
     * @param reqWidth
     * @param reqHeight
     * @return Bitmap
     * @author zhouyang
     */
    public static Bitmap decodeSampledBitmapFromFile(String pathName, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        Bitmap src = BitmapFactory.decodeFile(pathName, options);
        return createScaleBitmap(src, reqWidth, reqHeight, options.inSampleSize);
    }

}
