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
import me.teenyda.mvp_template.common.constant.ReqeustCodeConstant;

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
                ((Activity)context).startActivityForResult(intent, ReqeustCodeConstant.REQUEST_CODE_OPEN_CAMERA);
            }
        } else {
            ((Activity)context).startActivityForResult(intent, ReqeustCodeConstant.REQUEST_CODE_OPEN_CAMERA);
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
