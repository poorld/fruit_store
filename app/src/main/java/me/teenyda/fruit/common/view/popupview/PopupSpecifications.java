package me.teenyda.fruit.common.view.popupview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.Spec;
import me.teenyda.fruit.common.entity.Specification;
import me.teenyda.fruit.common.utils.GlideApp;
import me.teenyda.fruit.model.classify.settlement.SettlementActicity;

/**
 * author: teenyda
 * date: 2019/8/22
 * description: 选择规格
 */
public class PopupSpecifications {

    private Context mContext;
    private PopupWindow mPopupWindow;
    private View mView;

    private Spec mSpec;
    // private int mNumber;

    @BindView(R.id.spe_rv)
    RecyclerView spe_rv;

    @BindView(R.id.spec_pop_close)
    ImageView spec_pop_close;

    @BindView(R.id.spec_img)
    ImageView spec_img;

    @BindView(R.id.tv_selected_spec)
    TextView tv_selected_spec;

    @BindView(R.id.spec_price)
    TextView spec_price;

    @BindView(R.id.spec_number)
    TextView spec_number;

    @BindView(R.id.tv_spec_confirm)
    TextView tv_spec_confirm;

    @BindView(R.id.number_sub)
    TextView number_sub;

    @BindView(R.id.number_add)
    TextView number_add;


    private SpecificationAdapter mAdapter;

    private OrderConfirmClick mOrderConfirmClick;

    public interface SpecificationClick{
        void onSpecificationClick(Spec spec, int number);
    }

    public PopupSpecifications(Context context) {
        mContext = context;
        initPopup();
    }

    public interface OrderConfirmClick {
        void onOrderConfirmClick(Spec spec, int number);
    }
    public void setOrderConfirmClick(OrderConfirmClick orderConfirmClick) {
        this.mOrderConfirmClick = orderConfirmClick;
    }


    private void initPopup() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.pop_fruit_specifications, null);
        mPopupWindow = new PopupWindow(mView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        ButterKnife.bind(this, mView);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setAnimationStyle(R.style.AnimationBottomInAndOut);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setAlpha(1f);
            }
        });

        // spe_rv = mView.findViewById(R.id.spe_rv);
        // tv_selected_spec = mView.findViewById(R.id.tv_selected_spec);
        // spec_price = mView.findViewById(R.id.spec_price);
        // tv_spec_confirm = mView.findViewById(R.id.tv_spec_confirm);

        spe_rv.setLayoutManager(new GridLayoutManager(mContext, 3));
        mAdapter = new SpecificationAdapter();
        spe_rv.setAdapter(mAdapter);

        mAdapter.setSpecificationClick(new SpecificationClick() {
            @Override
            public void onSpecificationClick(Spec spec, int number) {
                mSpec = spec;
                // mNumber = number;
                spec_price.setText("¥ " + String.valueOf(spec.getPrice()));
                tv_selected_spec.setText("您已选择[" + spec.getSpecName() + "]");
            }
        });

        // 确认 跳转结算页面
        tv_spec_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // SettlementActicity.startActivity(mContext);
                if (mOrderConfirmClick != null && mSpec != null) {
                    mOrderConfirmClick.onOrderConfirmClick(mSpec, getNumber());
                }
            }
        });

    }

    public void setSpecList(List<Spec> specList) {
        mAdapter.setSpecifications(specList);
    }

    @OnClick({R.id.number_sub, R.id.number_add, R.id.spec_pop_close})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.number_sub:
                sub();
                break;
            case R.id.number_add:
                add();
                break;
            case R.id.spec_pop_close:
                dismiss();
                break;
        }
    }

    private void sub() {
        String number = spec_number.getText().toString();
        int intNumber = Integer.parseInt(number);
        if (intNumber > 1) {
            spec_number.setText(String.valueOf(--intNumber));
        }
    }

    private void add() {
        String number = spec_number.getText().toString();
        int intNumber = Integer.parseInt(number);
        spec_number.setText(String.valueOf(++intNumber));
    }

    public void setSpecImg(String url) {
        GlideApp.with(mContext)
                .load(url)
                .override(100, 100)
                .centerCrop()
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        spec_img.setImageDrawable(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }

    public void show(View view) {
        // List<Specification> mSpecifications = new ArrayList<>();
        // mSpecifications.add(new Specification("小小果(1斤)", 27.5f));
        // mSpecifications.add(new Specification("中果(1斤)", 33.7f));
        // mSpecifications.add(new Specification("大果(1斤)", 53));
        // mSpecifications.add(new Specification("特大果(1斤)", 73.8f));
        // mSpecifications.add(new Specification("特大果(1斤)", 88));


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

    private int getNumber() {
        return Integer.parseInt(spec_number.getText().toString());
    }

    private void setAlpha(float alpha) {
        WindowManager.LayoutParams layoutParams = ((Activity) mContext).getWindow().getAttributes();
        layoutParams.alpha = alpha;
        ((Activity) mContext).getWindow().setAttributes(layoutParams);
    }


    public class SpecificationAdapter extends RecyclerView.Adapter<ViewHolder>{

        private List<Spec> mSpecifications;

        private SpecificationClick mSpecificationClick;

        private int selectedIndex = -1;


        public void setSpecifications(List<Spec> specifications) {
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
            Spec specification = mSpecifications.get(position);

            holder.tv_sprcification.setText(specification.getSpecName() + "/" + specification.getSku().getAttrbute());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mSpecificationClick != null) {
                        mSpecificationClick.onSpecificationClick(specification, getNumber());
                        selectedIndex = position;
                        spec_number.setText("1");
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
