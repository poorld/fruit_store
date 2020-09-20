package me.teenyda.fruit_store.model.myself.member.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import java.util.List;

import me.teenyda.fruit_store.R;
import me.teenyda.fruit_store.common.entity.Member;
import me.teenyda.fruit_store.common.viewholder.MemberHolder;

/**
 * author: teenyda
 * date: 2020/9/18
 * description:
 */
public class MemberAdapter extends BannerAdapter<Member, MemberHolder> {

    private Context mContext;


    public MemberAdapter(List<Member> datas) {
        super(datas);
    }

    public MemberAdapter(List<Member> datas, Context context) {
        super(datas);
        mContext = context;
    }

    @Override
    public MemberHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new MemberHolder(BannerUtils.getView(parent, R.layout.banner_member));
    }

    @Override
    public int getItemViewType(int position) {
        return getData(getRealPosition(position)).getLevel();
    }

    @Override
    public void onBindView(MemberHolder holder, Member data, int position, int size) {

        int level = holder.getItemViewType();

        Glide.with(mContext)
                .load(mContext.getDrawable(data.getMemberImgRes()))
                .into(holder.member_img);
        // holder.member_img.setImageDrawable();
        holder.member_name.setText(data.getMemberName());
        holder.itemView.setBackground(mContext.getDrawable(data.getBackgroundRes()));

        switch (level) {
            case 1:
                holder.member_tv1.setTextColor(mContext.getColor(R.color.c_999999));
                holder.member_tv2.setTextColor(mContext.getColor(R.color.c_999999));
                holder.member_tv3.setTextColor(mContext.getColor(R.color.c_999999));
                holder.member_name.setTextColor(mContext.getColor(R.color.c_999999));
                break;
            case 2:
                holder.member_tv1.setTextColor(mContext.getColor(R.color.c_ffffff));
                holder.member_tv2.setTextColor(mContext.getColor(R.color.c_ffffff));
                holder.member_tv3.setTextColor(mContext.getColor(R.color.c_ffffff));
                holder.member_name.setTextColor(mContext.getColor(R.color.c_ffffff));
                break;
            case 3:
                holder.member_tv1.setTextColor(mContext.getColor(R.color.c_f3cadb));
                holder.member_tv2.setTextColor(mContext.getColor(R.color.c_f3cadb));
                holder.member_tv3.setTextColor(mContext.getColor(R.color.c_f3cadb));
                holder.member_name.setTextColor(mContext.getColor(R.color.c_f3cadb));
                break;
        }
    }
}
