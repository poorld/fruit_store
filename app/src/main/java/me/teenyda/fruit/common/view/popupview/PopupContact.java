package me.teenyda.fruit.common.view.popupview;

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

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.Contact;
import me.teenyda.fruit.model.classify.settlement.adapter.AddressSelectAdapter;

/**
 * author: teenyda
 * date: 2019/8/22
 * description: 支付方式
 */
public class PopupContact {

    private Context mContext;
    private PopupWindow mPopupWindow;
    private View mView;
    private RecyclerView pop_contact_rv;
    private AddressSelectAdapter mAddressAdapter;


    public PopupContact(Context context) {
        mContext = context;
        initPopup();
    }


    private void initPopup() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.pop_contact, null);
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

        pop_contact_rv = mView.findViewById(R.id.pop_contact_rv);
        mAddressAdapter = new AddressSelectAdapter(mContext);
        pop_contact_rv.setLayoutManager(new LinearLayoutManager(mContext));
        pop_contact_rv.setAdapter(mAddressAdapter);

    }

    public void addContact(List<Contact> contacts) {
        mAddressAdapter.addContacts(contacts);
    }

    public void setContactAction(AddressSelectAdapter.IContactAction contactAction) {
        mAddressAdapter.setContactAction(contactAction);
    }

    public void show(View view) {

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



}
