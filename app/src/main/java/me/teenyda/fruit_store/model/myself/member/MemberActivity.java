package me.teenyda.fruit_store.model.myself.member;

import android.content.Context;
import android.content.Intent;

import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.teenyda.fruit_store.R;
import me.teenyda.fruit_store.common.entity.DataBean;
import me.teenyda.fruit_store.common.mvp.MvpActivity;
import me.teenyda.fruit_store.model.myself.member.adapter.MemberAdapter;
import me.teenyda.fruit_store.model.myself.member.presenter.MemberPresenter;
import me.teenyda.fruit_store.model.myself.member.view.IMemberView;

/**
 * author: teenyda
 * date: 2020/9/18
 * description:
 */
public class MemberActivity extends MvpActivity<IMemberView, MemberPresenter> implements IMemberView {

    @BindView(R.id.member_banner)
    Banner member_banner;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MemberActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected MemberPresenter createPresenter() {
        return new MemberPresenter();
    }

    @Override
    protected void baseInitializer() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.act_member;
    }

    @Override
    protected void viewInitializer() {
        ButterKnife.bind(this);
        setStatusBarTran(false, true);
        member_banner.setAdapter(new MemberAdapter(DataBean.getMemberData(), getMContext()));
        member_banner.setIndicator(new CircleIndicator(this));
        //添加魅族效果
        member_banner.setBannerGalleryMZ(20);
        member_banner.isAutoLoop(false);

    }

    @Override
    protected void doBuseness() {

    }

    @Override
    public Context getMContext() {
        return this;
    }
}
