package me.teenyda.mvp_template.model.home.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;
import java.util.List;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.teenyda.mvp_template.R;
import me.teenyda.mvp_template.common.constant.RequestCodeConstant;
import me.teenyda.mvp_template.common.mvp.MvpFragment;
import me.teenyda.mvp_template.common.utils.BitmapUtil;
import me.teenyda.mvp_template.common.utils.PermissionsUtil;
import me.teenyda.mvp_template.common.view.popupview.PopupGetPhoto;
import me.teenyda.mvp_template.model.home.base.presenter.HomeP;
import me.teenyda.mvp_template.model.home.base.view.IHomeV;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class HomeFrag extends MvpFragment<IHomeV, HomeP> implements IHomeV {

    @BindView(R.id.open_camera_ll)
    LinearLayout open_camera_ll;

    @BindView(R.id.photo_iv)
    ImageView photo_iv;

    private Unbinder mBind;

    private PopupGetPhoto mPopupGetPhoto;

    private File mPhotoFile;

    @Override
    protected HomeP createPresenter() {
        return new HomeP();
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
        mPopupGetPhoto = new PopupGetPhoto(getMContext());

        open_camera_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupGetPhoto.show(v);
            }
        });

        mPopupGetPhoto.setPhotoListener(new PopupGetPhoto.GetPhotoListener() {
            @Override
            public void takePhoto() {
                mPhotoFile = PermissionsUtil.takePicture(getMContext());

            }

            @Override
            public void fromAlbum() {
                PermissionsUtil.choiceMultipleFromGallery(getMContext());
            }
        });
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

    @TargetApi(16)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                // 拍照
                case RequestCodeConstant.REQUEST_CODE_OPEN_CAMERA:
                    mPresenter.compressImage(mPhotoFile);
                    break;
                // 相册
                case RequestCodeConstant.REQUEST_CODE_MULTIPLE_ALBUM:
                    ClipData clipData = data.getClipData();
                    List<Bitmap> bitmapByClipData = BitmapUtil.getBitmapByClipData(getMContext(), clipData);
                    photo_iv.setImageBitmap(bitmapByClipData.get(0));
                    break;
            }
        }
    }

    @Override
    public void compressImageSuccess(File file) {
        mPhotoFile = file;
        Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
        int bitmapDegree = BitmapUtil.getBitmapDegree(mPhotoFile.getPath());
        photo_iv.setImageBitmap(bitmap);
    }
}
