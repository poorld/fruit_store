package me.teenyda.mvp_template.common.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import me.teenyda.mvp_template.common.constant.PermissionConstant;
import me.teenyda.mvp_template.common.constant.RequestCodeConstant;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class PermissionsUtil {

    /**
     * 拍照
     * @param context
     */
    public static void takePicture(@NonNull Context context) {

        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (Build.VERSION.SDK_INT >= 23) {
            if (!hasPremission(context, Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, PermissionConstant.REQUEST_CODE_TAKE_PHOTO);
            } else {
                ((Activity)context).startActivityForResult(intent, RequestCodeConstant.REQUEST_CODE_OPEN_CAMERA);
            }
        } else {
            ((Activity)context).startActivityForResult(intent, RequestCodeConstant.REQUEST_CODE_OPEN_CAMERA);
        }

    }

    /**
     * 打开相册
     */
    @TargetApi(16)
    public static void choiceFormAlbum(@NonNull Context context) {

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

    @TargetApi(23)
    public static boolean hasPremission(@NonNull Context context, @NonNull String permission) {
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED
                || PermissionChecker.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }

        return true;
    }

}
