package me.teenyda.fruit_store.common.app;

import android.app.Application;

public class MyApplication extends Application {

    private static Application mApplication;

    public static Application getApplication() {
        if (mApplication == null) {
            synchronized (MyApplication.class) {
                if (mApplication == null) {
                    mApplication = new Application();
                }
            }
        }
        return mApplication;
    }
}
