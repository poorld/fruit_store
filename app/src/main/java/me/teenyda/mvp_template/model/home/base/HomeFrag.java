package me.teenyda.mvp_template.model.home.base;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.teenyda.mvp_template.R;
import me.teenyda.mvp_template.common.mvp.BaseView;
import me.teenyda.mvp_template.common.mvp.MvpFragment;
import me.teenyda.mvp_template.common.view.popupview.PopupGetPhoto;
import me.teenyda.mvp_template.model.home.base.presenter.HomeP;
import me.teenyda.mvp_template.model.home.base.view.IHomeV;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class HomeFrag extends MvpFragment<IHomeV, HomeP> implements BaseView {

    @BindView(R.id.open_camera_ll)
    LinearLayout open_camera_ll;

    private Unbinder mBind;

    private PopupGetPhoto mPopupGetPhoto;

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

            }

            @Override
            public void fromAlbum() {

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
}
