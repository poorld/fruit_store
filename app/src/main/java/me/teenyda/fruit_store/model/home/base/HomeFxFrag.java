package me.teenyda.fruit_store.model.home.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.youth.banner.Banner;
import com.youth.banner.indicator.RoundLinesIndicator;
import com.youth.banner.transformer.ZoomOutPageTransformer;
import com.youth.banner.util.BannerUtils;
import com.youth.banner.util.LogUtils;

import java.io.File;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.teenyda.fruit_store.R;
import me.teenyda.fruit_store.common.constant.RequestCodeConstant;
import me.teenyda.fruit_store.common.entity.Book;
import me.teenyda.fruit_store.common.entity.DataBean;
import me.teenyda.fruit_store.common.entity.FileUploadResponse;
import me.teenyda.fruit_store.common.mvp.MvpRxFragment;
import me.teenyda.fruit_store.common.mvp.BitmapUtil;
import me.teenyda.fruit_store.common.utils.ConstansUtil;
import me.teenyda.fruit_store.common.utils.PermissionsUtil;
import me.teenyda.fruit_store.common.view.popupview.PopupGetPhoto;
import me.teenyda.fruit_store.model.home.base.adapter.ImageNetAdapter;
import me.teenyda.fruit_store.model.home.base.adapter.TopLineAdapter;
import me.teenyda.fruit_store.model.home.base.presenter.HomePRx;
import me.teenyda.fruit_store.model.home.base.view.IHomeV;
import me.teenyda.fruit_store.model.login.base.LoginAct;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class HomeFxFrag extends MvpRxFragment<IHomeV, HomePRx> implements IHomeV,
                                EasyPermissions.PermissionCallbacks{


    @BindView(R.id.banner)
    Banner banner;

    // @BindView(R.id.news_banner)
    // Banner newsBanner;

    private Unbinder mBind;


    @Override
    protected HomePRx createPresenter() {
        return new HomePRx();
    }

    @Override
    protected void baseInitializer() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.frag_home;
    }

    @Override
    protected void viewInitializer() {
        mBind = ButterKnife.bind(this, mView);

        banner.setAdapter(new ImageNetAdapter(DataBean.getTestData3()));
        banner.setBannerRound(BannerUtils.dp2px(5));
        banner.setIndicator(new RoundLinesIndicator(getMContext()));
        banner.setIndicatorSelectedWidth((int) BannerUtils.dp2px(15));

        //实现1号店和淘宝头条类似的效果
        // newsBanner.setAdapter(new TopLineAdapter(DataBean.getTestData2()))
        //         .setOrientation(Banner.VERTICAL)
        //         .setPageTransformer(new ZoomOutPageTransformer())
        //         .setOnBannerListener((data, position) -> {
        //             Snackbar.make(newsBanner, ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
        //             LogUtils.d("position：" + position);
        //         });



    }



    @Override
    protected void doBuseness() {

    }

    @Override
    public Context getMContext() {
        return getContext();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //框架要求必须这么写
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
//        showToast();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        showToast("请同意相关权限，否则功能无法使用");
    }

}
