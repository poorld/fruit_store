package me.teenyda.fruit_store.common.view.popupview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.teenyda.fruit_store.R;
import me.teenyda.fruit_store.common.entity.Specification;
import me.teenyda.fruit_store.common.viewholder.VideoHolder;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class PopupSpecifications {

    private Context mContext;
    private PopupWindow mPopupWindow;
    private View mView;
    private RecyclerView spe_rv;
    private SpecificationAdapter mAdapter;

    public PopupSpecifications(Context context) {
        mContext = context;
        initPopup();
    }


    private void initPopup() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.pop_fruit_specifications, null);
        mPopupWindow = new PopupWindow(mView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setAnimationStyle(R.style.AnimationBottomInAndOut);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setAlpha(1f);
            }
        });

        spe_rv = mView.findViewById(R.id.spe_rv);
        spe_rv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
        mAdapter = new SpecificationAdapter();
        spe_rv.setAdapter(mAdapter);

    }

    public void show(View view) {
        List<Specification> mSpecifications = new ArrayList<>();
        mSpecifications.add(new Specification("小小果(3斤)", 23.2f));
        mSpecifications.add(new Specification("小小果(5斤)", 27.5f));
        mSpecifications.add(new Specification("中果(3斤)", 33.7f));
        mSpecifications.add(new Specification("中果(5斤)", 53));
        mSpecifications.add(new Specification("特大果(3斤)", 73.8f));
        mSpecifications.add(new Specification("特大果(5斤)", 88));
        mAdapter.setSpecifications(mSpecifications);


        mPopupWindow.setFocusable(true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        setAlpha(0.5f);
    }

    public void dismiss() {
        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    private void setAlpha(float alpha) {
        WindowManager.LayoutParams layoutParams = ((Activity) mContext).getWindow().getAttributes();
        layoutParams.alpha = alpha;
        ((Activity) mContext).getWindow().setAttributes(layoutParams);
    }

    public class SpecificationAdapter extends RecyclerView.Adapter<ViewHolder>{

        private List<Specification> mSpecifications;

        public void setSpecifications(List<Specification> specifications) {
            this.mSpecifications = specifications;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_specifications, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Specification specification = mSpecifications.get(position);
            holder.tv_sprcification.setText(specification.getSpec());
        }

        @Override
        public int getItemCount() {
            return mSpecifications == null ? 0 : mSpecifications.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_sprcification;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_sprcification = itemView.findViewById(R.id.tv_sprcification);
        }
    }

}
