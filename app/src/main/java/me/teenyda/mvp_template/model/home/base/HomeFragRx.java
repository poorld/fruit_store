package me.teenyda.mvp_template.model.home.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.teenyda.mvp_template.R;
import me.teenyda.mvp_template.common.constant.RequestCodeConstant;
import me.teenyda.mvp_template.common.entity.Book;
import me.teenyda.mvp_template.common.entity.FileUploadResponse;
import me.teenyda.mvp_template.common.mvp.MvpRxFragment;
import me.teenyda.mvp_template.common.pictureselector.GlideEngine;
import me.teenyda.mvp_template.common.utils.BitmapUtil;
import me.teenyda.mvp_template.common.utils.ConstansUtil;
import me.teenyda.mvp_template.common.utils.PermissionsUtil;
import me.teenyda.mvp_template.common.view.popupview.PopupGetPhoto;
import me.teenyda.mvp_template.model.home.base.presenter.HomePRx;
import me.teenyda.mvp_template.model.home.base.view.IHomeV;
import me.teenyda.mvp_template.model.login.base.LoginAct;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class HomeFragRx extends MvpRxFragment<IHomeV, HomePRx> implements IHomeV,
                                EasyPermissions.PermissionCallbacks{

    public static final String TAG = "SB";


    @BindView(R.id.open_camera_ll)
    LinearLayout open_camera_ll;

    @BindView(R.id.getbook_ll)
    LinearLayout getbook_ll;

    @BindView(R.id.updatebook_ll)
    LinearLayout updatebook_ll;

    @BindView(R.id.photo_iv)
    ImageView photo_iv;


    private Unbinder mBind;

    private PopupGetPhoto mPopupGetPhoto;

    private File mPhotoFile;

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
        mPopupGetPhoto = new PopupGetPhoto(getMContext());

        mPopupGetPhoto.setPhotoListener(new PopupGetPhoto.GetPhotoListener() {
            @Override
            public void takePhoto() {
//                PermissionsUtil.writeStorage(getMContext());


                mPhotoFile = PermissionsUtil.takePicture(getMContext());
                mPopupGetPhoto.dismiss();


            }

            @Override
            public void fromAlbum() {
//                PermissionsUtil.choiceFromGallery(getMContext());
//                mPopupGetPhoto.dismiss();

                //相册
                PictureSelector.create((Activity) getMContext())
                        .openGallery(PictureMimeType.ofImage())
                        .imageEngine(GlideEngine.createGlideEngine())
                        .maxSelectNum(3)
                        .isCompress(true)
                        .minimumCompressSize(500)
                        .compressSavePath(ConstansUtil.getStoragePath())
                        .forResult(new OnResultCallbackListener<LocalMedia>() {
                            @Override
                            public void onResult(List<LocalMedia> result) {
                                // 结果回调
                                // 1.media.getPath(); 为原图path
                                // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                                // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                                // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                                List<File> files = new ArrayList<>();

                                for (LocalMedia localMedia : result) {
                                    File file = null;
                                    if (localMedia.isCompressed()) {
                                        file = new File(localMedia.getCompressPath());
                                    } else {
                                        file = new File(localMedia.getPath());
                                    }

                                    files.add(file);

                                    Log.i(TAG, localMedia.getSize() + "");
                                    Log.i(TAG, localMedia.getPath());
                                    Log.i(TAG, localMedia.isCompressed() + "");
                                    Log.i(TAG, localMedia.getCompressPath());


                                }

                                mPresenter.uploadFiles(files);
                            }

                            @Override
                            public void onCancel() {
                                // 取消
                            }
                        });
            }
        });


    }

    @OnClick({R.id.open_camera_ll, R.id.getbook_ll, R.id.login_ll, R.id.getbooks_ll,R.id.updatebook_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.open_camera_ll:
                mPopupGetPhoto.show(view);
                break;
            case R.id.getbook_ll:
//                HomePRx p = new HomePRx();
                mPresenter.getBook();
                break;
            case R.id.login_ll:
                startActivity(LoginAct.class);
                break;
            case R.id.getbooks_ll:
                mPresenter.getBooks();
                break;
            case R.id.updatebook_ll:
                Book book = new Book();
                book.setBookName("智取棋");
                mPresenter.updateBoos(book);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            switch (requestCode) {
                // 拍照
                case RequestCodeConstant.REQUEST_CODE_OPEN_CAMERA:
                    BitmapUtil.getBitmapDegree(mPhotoFile.getPath());

                    mPresenter.compressImage(mPhotoFile);
                    break;
                // 相册
                case RequestCodeConstant.REQUEST_CODE_CHOICE_FROM_ALBUM:
                    if (data != null) {
                        Uri uri = data.getData();
                        BitmapUtil.saveBitmapToLoaction(getMContext(),uri, ConstansUtil.getPhotoAlbumPath());

                    }

//                    Bitmap bitmap = BitmapUtil.getBitmapByUri(getMContext(), uri);

                    /*BitmapUtil.compressImageAndSaveByIO(bitmap, 500, ConstansUtil.getPhotoAlbumPath(), new BitmapUtil.CompressListener() {
                        @Override
                        public void onCompressSuccess(File file) {

                        }

                        @Override
                        public void onCompressError(String error) {

                        }

                        @Override
                        public void onCompressComplete() {

                        }
                    });*/

//                    photo_iv.setImageBitmap(bitmap);
                    break;
            }
        }
    }

    @Override
    public void compressImageSuccess(File file) {
        mPhotoFile = file;
        Bitmap bitmapWithRightRotation = BitmapUtil.getBitmapWithRightRotation(mPhotoFile.getPath());
        photo_iv.setImageBitmap(bitmapWithRightRotation);
        mPresenter.uploadFile(file);
    }

    @Override
    public void showImage(FileUploadResponse file) {
        Glide.with(getMContext())
                .load(file.getFileDownloadUrl())
                .centerCrop()
                .into(photo_iv);
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

    private void uploadMultiFile(List<File> files) {

    }
}
