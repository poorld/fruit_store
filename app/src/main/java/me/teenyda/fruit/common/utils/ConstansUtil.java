package me.teenyda.fruit.common.utils;

import android.os.Environment;

import java.io.File;

import me.teenyda.fruit.common.api.Constans;

/**
 * author: teenyda
 * date: 2020/9/8
 * description:
 */
public class ConstansUtil {

    /**
     * 拍照存储路径
     * /storage/emulated/0/Pictures/take_photo.jpg
     * @return
     */
    public static String takePictureFilePath() {
        return Environment.getExternalStorageDirectory() +
                File.separator +
                Constans.PHOTO_STORAGE_PATH +
                File.separator +
                Constans.TAKE_PICTURE_PHOTO_NAME;
    }

    /**
     * 相册获取的图片 存储路径
     * /storage/emulated/0/Pictures/photo_album.jpg
     * @return
     */
    public static String getPhotoAlbumPath() {
        return Environment.getExternalStorageDirectory() +
                File.separator +
                Constans.PHOTO_STORAGE_PATH +
                File.separator +
                Constans.PHOTO_ALBUM;
    }

    public static String getStoragePath() {
        return Environment.getExternalStorageDirectory() +
                File.separator +
                Constans.PHOTO_STORAGE_PATH +
                File.separator;
    }

    /**
     * M转Byte
     * 1024Byte(字节)=1KB
     * 1024KB=1MB
     * 1024MB=1GB
     * @param M
     * @return
     */
    public static long getByte(int M){
        return M * 1024 * 1024;
    }

    public static long getKilobyte(int M){
        return M * 1024;
    }


}
