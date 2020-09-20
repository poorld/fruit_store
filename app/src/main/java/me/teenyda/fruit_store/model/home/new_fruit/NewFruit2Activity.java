package me.teenyda.fruit_store.model.home.new_fruit;

import android.content.Context;
import android.content.Intent;

import com.youth.banner.transformer.ZoomOutPageTransformer;

import androidx.viewpager2.widget.ViewPager2;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.teenyda.fruit_store.R;
import me.teenyda.fruit_store.common.entity.DataBean;
import me.teenyda.fruit_store.common.mvp.MvpActivity;
import me.teenyda.fruit_store.model.home.new_fruit.adapter.NewFruitAdapter;
import me.teenyda.fruit_store.model.home.new_fruit.presenter.NewFruitPresenter;
import me.teenyda.fruit_store.model.home.new_fruit.view.INewFruitView;

/**
 * author: teenyda
 * date: 2020/9/20
 * description:
 */
public class NewFruit2Activity extends MvpActivity<INewFruitView, NewFruitPresenter> implements INewFruitView {


    @BindView(R.id.view_pager)
    ViewPager2 viewPager;

    private NewFruitAdapter pagerAdapter;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, NewFruit2Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected NewFruitPresenter createPresenter() {
        return new NewFruitPresenter();
    }

    @Override
    protected void baseInitializer() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.act_new_fruit;
    }

    @Override
    protected void viewInitializer() {
        ButterKnife.bind(this);
        setBack();
        setRightDisplay(false);
        setTitle("");

        pagerAdapter = new NewFruitAdapter(this, DataBean.getNewFruitData());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(new ZoomOutPageTransformer());
    }

    @Override
    protected void doBuseness() {

    }

    @Override
    public Context getMContext() {
        return this;
    }
}
