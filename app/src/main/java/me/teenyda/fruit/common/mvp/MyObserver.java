package me.teenyda.fruit.common.mvp;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import io.reactivex.disposables.Disposable;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.view.popupview.CustomProgressDialog;

/**
 * Observer加入加载框
 * @param <T>
 */
public abstract class MyObserver<T> extends BaseObserver<T> {
    private boolean mShowDialog;
    private CustomProgressDialog mDialog;
    private Context mContext;
    private Disposable d;

    public MyObserver(Context context, Boolean showDialog) {
        mContext = context;
        mShowDialog = showDialog;
    }

    public MyObserver(Context context) {
        this(context,false);
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
        if (!isConnected(mContext)) {
            Toast.makeText(mContext,"未连接网络",Toast.LENGTH_SHORT).show();
            if (d.isDisposed()) {
                d.dispose();
            }
        } else {
            if (mDialog == null && mShowDialog == true) {
                mDialog = CustomProgressDialog.getInstance(mContext, R.drawable.anmi_loading);;
                // mDialog.setMessage("正在加载中");
                // Unable to add window -- token android.os.BinderProxy@68dbd8b is not valid; is your activity running?

                ((Activity)mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (!((Activity)mContext).isFinishing()) {
                            try {
                                mDialog.show();
                            } catch (WindowManager.BadTokenException e) {
                                Log.e("WindowManagerBad ", e.toString());
                            }
                        }
                    }
                });

            }
        }
    }
    @Override
    public void onError(Throwable e) {
        Toast.makeText(mContext,e.getMessage(),Toast.LENGTH_SHORT).show();
        if (d.isDisposed()) {
            d.dispose();
        }
        hidDialog();
        super.onError(e);
    }

    @Override
    public void onComplete() {
        if (d.isDisposed()) {
            d.dispose();
        }
        hidDialog();
        super.onComplete();
    }


    public void hidDialog() {
        if (mDialog != null && mShowDialog)
            mDialog.dismiss();
        // mDialog = null;
    }
    /**
     * 是否有网络连接，不管是wifi还是数据流量
     * @param context
     * @return
     */
    public static boolean isConnected(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null)
        {
            return false;
        }
        boolean available = info.isAvailable();
        return available;
    }

    /**
     * 取消订阅
     * 点击返回键隐藏Dialog可以重写系统点击返回键时进行处理或者调用cancleRequest()方法
     */
    public void cancleRequest(){
        if (d!=null&&d.isDisposed()) {
            d.dispose();
            hidDialog();
        }
    }
}

