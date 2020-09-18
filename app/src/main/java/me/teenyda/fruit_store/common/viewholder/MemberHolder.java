package me.teenyda.fruit_store.common.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.teenyda.fruit_store.R;


/**
 * vip
 */

public class MemberHolder extends RecyclerView.ViewHolder {
    public ShimmerFrameLayout member_sfl;
    public TextView member_name;
    public ImageView member_img;

    public TextView member_tv1;
    public TextView member_tv2;
    public TextView member_tv3;

    public MemberHolder(@NonNull View view) {
        super(view);
        member_name = view.findViewById(R.id.member_name);
        member_img = view.findViewById(R.id.member_img);
        member_sfl = view.findViewById(R.id.member_sfl);

        member_tv1 = view.findViewById(R.id.member_tv1);
        member_tv2 = view.findViewById(R.id.member_tv2);
        member_tv3 = view.findViewById(R.id.member_tv3);

        Shimmer shimmer = new Shimmer.AlphaHighlightBuilder()
                // 设置闪一次2秒
                .setRepeatDelay(2000)
                // 没闪的地方亮一点点
                .setBaseAlpha(0.7f)
                .build();
        member_sfl.setShimmer(shimmer);
    }
}
