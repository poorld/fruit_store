package me.teenyda.fruit.common.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.content.PermissionChecker;
import me.teenyda.fruit.BuildConfig;
import me.teenyda.fruit.common.constant.PermissionConstant;
import me.teenyda.fruit.common.constant.RequestCodeConstant;
import pub.devrel.easypermissions.EasyPermissions;

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
    public static File takePicture(@NonNull Context context) {

        String[] permissions = {Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (!EasyPermissions.hasPermissions(context, permissions)) {
            getPermission(permissions, context);
        }

        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // images是file_paths.xml下的path
//        String filePath = Environment.getExternalStorageDirectory() + File.separator + "images" + File.separator;
        File outputFile = new File(ConstansUtil.takePictureFilePath());

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

        Uri contentUri;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            contentUri = FileProvider.getUriForFile(context,
                    BuildConfig.APPLICATION_ID + ".provider", outputFile);
        } else {
            contentUri = Uri.fromFile(outputFile);
        }


        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);


        if (EasyPermissions.hasPermissions(context, permissions)) {
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
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            startActivityForResult(intent, CHOOSE_PHOTO); // 打开相册
            Intent choiceFromAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
            // 设置数据类型为图片类型
            choiceFromAlbumIntent.setType("image/*");
            ((Activity)context).startActivityForResult(intent, RequestCodeConstant.REQUEST_CODE_CHOICE_FROM_ALBUM);
        }

    }

    /**
     * 从图库选择多张图片
     * @param context
     */
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

    public static boolean hasPremission(@NonNull Context context, @NonNull String permission) {
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED
                || PermissionChecker.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }

        return true;
    }

    public static void writeStorage(@NonNull Context context){
        if (!hasPremission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // 读写权限
            ActivityCompat.requestPermissions((Activity) context, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
            }, PermissionConstant.REQUEST_CODE_READ_WRITE_STORAGE);
        }
    }

    //获取权限
    private static void getPermission(String[] permissions, Context context) {

        if (EasyPermissions.hasPermissions(context, permissions)) {
            //已经打开权限
//            Toast.makeText(this, "已经申请相关权限", Toast.LENGTH_SHORT).show();
        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions((Activity) context, "需要获取您的相册、照相使用权限", 1, permissions);
        }

    }

}
