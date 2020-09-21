package me.teenyda.fruit_store.model.home.seconds_kill;

import android.content.Context;
import android.content.Intent;


import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.teenyda.fruit_store.R;
import me.teenyda.fruit_store.common.entity.KillFruit;
import me.teenyda.fruit_store.common.mvp.MvpActivity;
import me.teenyda.fruit_store.common.utils.CalendarsUtils;
import me.teenyda.fruit_store.model.home.seconds_kill.adapter.SBAdapter;
import me.teenyda.fruit_store.model.home.seconds_kill.adapter.SecondKillAdapter;
import me.teenyda.fruit_store.model.home.seconds_kill.presenter.SecondKillPresenter;
import me.teenyda.fruit_store.model.home.seconds_kill.view.ISecondKillView;

/**
 * author: teenyda
 * date: 2020/9/20
 * description: 限时秒杀
 */
public class SecondKillActivity extends MvpActivity<ISecondKillView, SecondKillPresenter> implements ISecondKillView {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SecondKillActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.seconds_kill_rv)
    RecyclerView seconds_kill_xrv;

    private SecondKillAdapter mAdapter;


    @Override
    protected SecondKillPresenter createPresenter() {
        return new SecondKillPresenter();
    }

    @Override
    protected void baseInitializer() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.act_seconds_kill;
    }

    @Override
    protected void viewInitializer() {
        ButterKnife.bind(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getMContext());

        seconds_kill_xrv.setLayoutManager(linearLayoutManager);
        // seconds_kill_xrv.setAdapter(mAdapter = new SecondKillAdapter(getMContext(), initData()));
        seconds_kill_xrv.setAdapter(new SBAdapter(getMContext(), initData()));
    }

    private List<KillFruit> initData() {
        List<KillFruit> fruits = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            KillFruit f = new KillFruit("水果", CalendarsUtils.nextHour(i));
            fruits.add(f);
        }
        return fruits;
    }

    @Override
    protected void doBuseness() {

    }

    @Override
    public Context getMContext() {
        return this;
    }
}
