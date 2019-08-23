package me.teenyda.mvp_template.common.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.content.PermissionChecker;
import me.teenyda.mvp_template.BuildConfig;
import me.teenyda.mvp_template.common.constant.PermissionConstant;
import me.teenyda.mvp_template.common.constant.RequestCodeConstant;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class PermissionsUtil {

    public static final String photoName = "my_photo.jpg";

    /**
     * 拍照
     * @param context
     */
    public static File takePicture(@NonNull Context context) {

        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // images是file_paths.xml下的path
        String filePath = Environment.getExternalStorageDirectory() + File.separator + "images" + File.separator + photoName;
        File outputFile = new File(filePath);
        if (!outputFile.getParentFile().exists()) {
            outputFile.getParentFile().mkdir();
        }
        /**
         * BuildConfig.APPLICATION_ID 就是build.gradle 文件中的 applicationId值
         *
         * <provider
         *   android:authorities="${applicationId}.provider"
         *   android:name="androidx.core.content.FileProvider"
         */
        Uri contentUri = FileProvider.getUriForFile(context,
                BuildConfig.APPLICATION_ID + ".provider", outputFile);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);

        if (Build.VERSION.SDK_INT >= 23) {
            if (!hasPremission(context, Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, PermissionConstant.REQUEST_CODE_TAKE_PHOTO);
            } else {
                ((Activity)context).startActivityForResult(intent, RequestCodeConstant.REQUEST_CODE_OPEN_CAMERA);
            }
        } else {
            ((Activity)context).startActivityForResult(intent, RequestCodeConstant.REQUEST_CODE_OPEN_CAMERA);
        }

        return outputFile;

    }

    /**
     * 打开相册
     */
    @TargetApi(16)
    public static void choiceFromGallery(@NonNull Context context) {

        if (!hasPremission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // 读写权限
            ActivityCompat.requestPermissions((Activity) context, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, PermissionConstant.REQUEST_CODE_READ_WRITE_STORAGE);
        } else {
            Intent choiceFromAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
            // 设置数据类型为图片类型
            choiceFromAlbumIntent.setType("image/*");
            ((Activity)context).startActivityForResult(choiceFromAlbumIntent, RequestCodeConstant.REQUEST_CODE_CHOICE_FROM_ALBUM);
        }

    }

    /**
     * 从图库选择多张图片
     * @param context
     */
    @TargetApi(16)
    public static void choiceMultipleFromGallery(@NonNull Context context) {
        if (!hasPremission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // 读写权限
            ActivityCompat.requestPermissions((Activity) context, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, PermissionConstant.REQUEST_CODE_READ_WRITE_STORAGE);
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            ((Activity) context).startActivityForResult(Intent.createChooser(intent, "Select Picture"),
                        RequestCodeConstant.REQUEST_CODE_MULTIPLE_ALBUM);
        }
    }

    @TargetApi(23)
    public static boolean hasPremission(@NonNull Context context, @NonNull String permission) {
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED
                || PermissionChecker.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }

        return true;
    }



}
