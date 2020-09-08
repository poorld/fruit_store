package me.teenyda.mvp_template.model.home.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.teenyda.mvp_template.R;
import me.teenyda.mvp_template.common.constant.RequestCodeConstant;
import me.teenyda.mvp_template.common.entity.FileUploadResponse;
import me.teenyda.mvp_template.common.mvp.MvpFragment;
import me.teenyda.mvp_template.common.mvp.BitmapUtil;
import me.teenyda.mvp_template.common.utils.PermissionsUtil;
import me.teenyda.mvp_template.common.view.popupview.PopupGetPhoto;
import me.teenyda.mvp_template.model.home.base.presenter.HomeP;
import me.teenyda.mvp_template.model.home.base.view.IHomeV;
import me.teenyda.mvp_template.model.login.base.LoginAct;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class HomeFrag extends MvpFragment<IHomeV, HomeP> implements IHomeV {

    @BindView(R.id.open_camera_ll)
    LinearLayout open_camera_ll;

    @BindView(R.id.getbook_ll)
    LinearLayout getbook_ll;

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

        mPopupGetPhoto.setPhotoListener(new PopupGetPhoto.GetPhotoListener() {
            @Override
            public void takePhoto() {
                mPhotoFile = PermissionsUtil.takePicture(getMContext());
                mPopupGetPhoto.dismiss();

            }

            @Override
            public void fromAlbum() {
                PermissionsUtil.choiceFromGallery(getMContext());
                mPopupGetPhoto.dismiss();
            }
        });
    }

    @OnClick({R.id.open_camera_ll, R.id.getbook_ll, R.id.login_ll, R.id.getbooks_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.open_camera_ll:
                mPopupGetPhoto.show(view);
                break;
            case R.id.getbook_ll:
//                HomePRx p = new HomePRx();
//                p.getBook((RxActivity) getMContext());
                mPresenter.getBook();
                break;
            case R.id.login_ll:
                startActivity(LoginAct.class);
                break;
            case R.id.getbooks_ll:
                mPresenter.getBooks();
                break;
        }
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
                case RequestCodeConstant.REQUEST_CODE_CHOICE_FROM_ALBUM:
                    Uri uri = data.getData();
                    Bitmap bitmap = BitmapUtil.getBitmapByUri(getMContext(), uri);
                    photo_iv.setImageBitmap(bitmap);
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
        mPresenter.uploadFile(file);
    }

    @Override
    public void showImage(FileUploadResponse file) {

    }
}
