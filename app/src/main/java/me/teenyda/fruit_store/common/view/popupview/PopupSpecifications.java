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
import androidx.recyclerview.widget.GridLayoutManager;
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
    private TextView tv_selected_spec;
    private TextView fruit_spe_price;
    private SpecificationAdapter mAdapter;

    public interface SpecificationClick{
        void onSpecificationClick(Specification spec);
    }

    public PopupSpecifications(Context context) {
        mContext = context;
        initPopup();
    }


    private void initPopup() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.pop_fruit_specifications, null);
        mPopupWindow = new PopupWindow(mView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setAnimationStyle(R.style.AnimationBottomInAndOut);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setAlpha(1f);
            }
        });

        spe_rv = mView.findViewById(R.id.spe_rv);
        tv_selected_spec = mView.findViewById(R.id.tv_selected_spec);
        fruit_spe_price = mView.findViewById(R.id.fruit_spe_price);

        spe_rv.setLayoutManager(new GridLayoutManager(mContext, 3));
        mAdapter = new SpecificationAdapter();
        spe_rv.setAdapter(mAdapter);

        mAdapter.setSpecificationClick(new SpecificationClick() {
            @Override
            public void onSpecificationClick(Specification spec) {
                fruit_spe_price.setText("¥ " + String.valueOf(spec.getPrice()));
                tv_selected_spec.setText("您已选择[" + spec.getSpec() + "]");
            }
        });

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

        private SpecificationClick mSpecificationClick;

        private int selectedIndex = -1;


        public void setSpecifications(List<Specification> specifications) {
            this.mSpecifications = specifications;
            notifyDataSetChanged();
        }

        public void setSpecificationClick(SpecificationClick specificationClick) {
            this.mSpecificationClick = specificationClick;
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
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mSpecificationClick != null) {
                        mSpecificationClick.onSpecificationClick(specification);
                        selectedIndex = position;
                        notifyDataSetChanged();
                    }
                }
            });

            if (position == selectedIndex) {
                holder.tv_sprcification.setSelected(true);
                holder.tv_sprcification.setTextColor(mContext.getColor(R.color.c_ffffff));
            }else {
                holder.tv_sprcification.setSelected(false);
                holder.tv_sprcification.setTextColor(mContext.getColor(R.color.c_494949));
            }
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
