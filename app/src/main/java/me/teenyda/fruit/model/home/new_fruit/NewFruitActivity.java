package me.teenyda.fruit.model.home.new_fruit;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jaeger.library.StatusBarUtil;
import com.youth.banner.transformer.ZoomOutPageTransformer;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.DataBean;
import me.teenyda.fruit.model.home.new_fruit.adapter.NewFruitAdapter;

/**
 * author: teenyda
 * date: 2020/9/19
 * description:
 */
public class NewFruitActivity extends FragmentActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, NewFruitActivity.class);
        context.startActivity(intent);
    }

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager2 viewPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private NewFruitAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_new_fruit);

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById(R.id.view_pager);
        pagerAdapter = new NewFruitAdapter(this, DataBean.getNewFruitData());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(new ZoomOutPageTransformer());

        StatusBarUtil.setTranslucentForImageViewInFragment(NewFruitActivity.this, 0,null);

    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }
}
