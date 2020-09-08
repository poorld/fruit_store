package me.teenyda.mvp_template.common.utils;

import android.content.ClipData;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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
    public static File compressImageAndSave(String path, int quality) throws IOException {

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
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        File resizedFile = new File(path);

        FileOutputStream fos = new FileOutputStream(resizedFile);

        fos.write(baos.toByteArray());
        fos.flush();
        fos.close();

        return resizedFile;
    }

    /**
     * 压缩图片
     *
     * @param bitmap
     *          被压缩的图片
     * @param sizeLimit
     *          大小限制
     * @return
     *          压缩后的图片
     */
    private static Bitmap compressBitmap(Bitmap bitmap, long sizeLimit) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int quality = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);

        // 循环判断压缩后图片是否超过限制大小
        while(baos.toByteArray().length / 1024 > sizeLimit) {
            // 清空baos
            baos.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            quality -= 10;
        }

        return BitmapFactory.decodeStream(new ByteArrayInputStream(baos.toByteArray()), null, null);
    }

    /**
     * 压缩图片
     *
     * @param bitmap
     *          被压缩的图片
     * @param sizeLimit
     *          大小限制
     * @return
     *          压缩后的图片
     */
    private Bitmap compressBitmap1(Bitmap bitmap, long sizeLimit) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int quality = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);

        // 循环判断压缩后图片是否超过限制大小
        while(baos.toByteArray().length / 1024 > sizeLimit) {
            // 清空baos
            baos.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            quality -= 10;
        }

        return BitmapFactory.decodeStream(new ByteArrayInputStream(baos.toByteArray()), null, null);
    }

    public static File compressBitmapAndSave(long sizeLimit, String path) {

        Bitmap rotationBitmap = getBitmapWithRightRotation(path);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int quality = 100;
        rotationBitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);

        // 循环判断压缩后图片是否超过限制大小
        while(baos.toByteArray().length / 1024 > sizeLimit) {
            // 清空baos
            baos.reset();
            rotationBitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            quality -= 10;
        }

        try {
            File file = new File(path);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();

            return file;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

//        Bitmap newBitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(baos.toByteArray()), null, null);

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


    // 4.4以上 content://com.android.providers.media.documents/document/image:3952
    public static String getPath(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT; // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }// DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            } else if ("content".equalsIgnoreCase(uri.getScheme())) { // Return the remote address i
                if (isGooglePhotosUri(uri)) return uri.getLastPathSegment();
                return getDataColumn(context, uri, null, null);
            } // File
            else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }
        return null;
    }

    public static void getImagePath(Context context, Uri uri) {
        //外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口

        ContentResolver resolver = context.getContentResolver();

        //此处的用于判断接收的Activity是不是你想要的那个


        String[] proj = {MediaStore.Images.Media.DATA};

        //好像是android多媒体数据库的封装接口，具体的看Android文档

        Cursor cursor = resolver.query(uri, proj, null, null, null);

        //按我个人理解 这个是获得用户选择的图片的索引值

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        //将光标移至开头 ，这个很重要，不小心很容易引起越界

        cursor.moveToFirst();

        //最后根据索引值获取图片路径

        String path = cursor.getString(column_index);

    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String[] projection = {
                MediaStore.MediaColumns.DISPLAY_NAME
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, null, null,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check. * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check. * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check. * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check. * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public interface CompressListener{
        void onCompressSuccess(File file);
        void onCompressSuccess(Bitmap bitmap);
        void onCompressError(String error);
        void onCompressComplete();
    }

    public static void compressImageByIO(String imagePath, int quatity, CompressListener compressListener) {
        Observable.just(imagePath)
                .doOnSubscribe(disposable -> {

                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .map(s -> compressImageAndSave(imagePath, quatity))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<File>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(File file) {
                        compressListener.onCompressSuccess(file);
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
    }

    /**
     * return Bitmap
     * @param bitmap
     * @param size
     * @param compressListener
     */
    public static void compressBitmapByIO(Bitmap bitmap, long size, CompressListener compressListener) {
        Observable.just(bitmap)
                .doOnSubscribe(disposable -> {

                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .map(s -> compressBitmap(bitmap, size))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap1) {
                        compressListener.onCompressSuccess(bitmap1);
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
    }

    /**
     *
     * @param sizeLimit KB
     * @param savePath
     * @param compressListener
     */
    public static void compressImageAndSaveByIO(long sizeLimit, String savePath, CompressListener compressListener) {
        Observable.just(savePath)
                .doOnSubscribe(disposable -> {

                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .map(s -> compressBitmapAndSave(sizeLimit, savePath))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<File>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(File file) {
                        compressListener.onCompressSuccess(file);
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
    }

    public static void saveBitmapToLoaction(@NonNull Bitmap bitmap, String savePath) {
        /*Observable.just(bitmap)
                .doOnSubscribe(disposable -> {

                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .map(new Function<Bitmap, File>() {
                    @Override
                    public File apply(Bitmap bitmap) throws Exception {
                        return null;
                    }
                })*/
    }

    public static void test(Context context, Uri uri) {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存图片到本地
     * 先获取图片的路径，把图片转为Bitmap，再压缩图片，最后写入baos缓存
     * @param context
     * @param uri
     */
    public static void saveBitmapToLoaction(@NonNull Context context, Uri uri, String savePath) {
        File file = null;

        try {

            file = new File(savePath);
            FileOutputStream fos = new FileOutputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();


            getImagePath(context, uri);

            String sourcePath = getPath(context, uri);
            Bitmap rotationBitmap = getBitmapWithRightRotation(sourcePath);
            compressBitmapByIO(rotationBitmap, 500, new CompressListener() {
                @Override
                public void onCompressSuccess(File file) {

                }

                @Override
                public void onCompressSuccess(Bitmap bitmap) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                }

                @Override
                public void onCompressError(String error) {

                }

                @Override
                public void onCompressComplete() {
                    try {

                        fos.write(baos.toByteArray());
                        fos.flush();

                        baos.close();
                        fos.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });



//            Bitmap pictureBitmap = getBitmapByUri(context, uri); // obtaining the Bitmap
//            pictureBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//            fos.write(baos.toByteArray());
//            fos.flush(); // Not really required
//
//            baos.close();
//            fos.close(); // do not forget to close the stream
//
//            MediaStore.Images.Media.insertImage(context.getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());
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
