package me.teenyda.mvp_template.common.utils;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import me.teenyda.mvp_template.common.entity.UserToken;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:文件缓存
 * context.getFilesDir()：获取手机内置存储器的/data/data/<application package>/files目录，这个位置放存储文件。
 * context.getCacheDir()：获取手机内置存储器的/data/data/<application package>/cache目录，这个目录可以存放应用缓存文件。
 * context.getExternalFilesDir()：获取SDCard/Android/data/你的应用的包名/files/ 目录，是外部存储的目录，当我们使用外部存储时，应该先判断外部存储介质是否存在，同时还要在应用权限中加入对sd卡的读写操作。
 * //判断外部SD卡是否存在，true是存在
 * Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
 *
 * 作者：zero_sr
 * 链接：https://www.jianshu.com/p/29491fbd8e05
 * 来源：简书
 * 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
 */
public class FileCacheUtil {
    // 缓存文件名
    public static final String cacheFileName = "user_cache";
    public static final String userTokenFile = "user_token";
    // 缓存时间 30分钟
    public static final int cache_timeout = 1000 * 60 * 30;

    public static void setCache(String content, Context context, String cacheFileName, int mode) {
        FileOutputStream fos = null;

        try {
            fos = context.openFileOutput(cacheFileName, mode);
            fos.write(content.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getCache(Context context, String cacheFileName) {
        FileInputStream fis = null;
        StringBuffer sb = new StringBuffer();

        try {
            fis = context.openFileInput(cacheFileName);
            int len;
            byte[] buff = new byte[1024];
            while ((len = fis.read(buff)) != -1) {
                sb.append(new String(buff, 0, len));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

    public static void saveUserToken(Context context,UserToken userToken) {
        String json = JSONObject.toJSONString(userToken);
        setCache(json, context, userTokenFile, Context.MODE_PRIVATE);
    }

    public static String getCachePath(Context context) {
        return context.getFilesDir().getAbsolutePath();
    }

    private static boolean cacheIsOutDate(Context context, String cacheFileName) {
        File file = new File(getCachePath(context) + cacheFileName);
        long date = file.lastModified();
        long time_out = (System.currentTimeMillis() - date);

        // 过期
        if (time_out > cache_timeout) {
            return true;
        }
        return false;
    }
}
