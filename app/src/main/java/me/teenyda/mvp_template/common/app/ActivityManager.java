package me.teenyda.mvp_template.common.app;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;


/**
 * author: teenyda
 * date: 2019/8/21
 * description: Activity管理器
 */
public class ActivityManager {

    private static Stack<Activity> mActivities;

    private static ActivityManager instance;

    /**
     * 但实例，无需考虑多线程同步问题
     * @return
     */
    public static ActivityManager getAppManager() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    public void addActivity(Activity activity) {
        if (mActivities == null) {
            mActivities = new Stack<Activity>();
        }
        mActivities.add(activity);
    }

    /**
     * 获取栈顶activity
     * @return
     */
    public Activity currentActivity() {
        if (mActivities == null || mActivities.isEmpty()) {
            return null;
        }
        Activity activity = mActivities.lastElement();
        return activity;
    }

    public void finishActivity() {
        Activity activity = mActivities.lastElement();
        finishActivity(activity);
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            if (activity.isFinishing()) {
                mActivities.remove(activity);
                // activity.finish();
                activity = null;
            }
        }
    }

    /**
     * 关闭除了指定activity以外所有activity
     * @param clazz
     */
    public void finishOthersActivity(Class<?> clazz) {
        for (Activity activity : mActivities) {
            if (!activity.getClass().equals(clazz)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有activity
     */
    public void finishAllActivity() {
        for (Activity activity : mActivities) {
            if (activity != null) {
                activity.finish();
            }
        }
        mActivities.clear();
    }

    /**
     * 退出应用程序
     * @param context
     */
    public void AppExit(Context context) {
        finishAllActivity();
        android.app.ActivityManager activityManager = (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.killBackgroundProcesses(context.getPackageName());
        System.exit(0);
    }


}
