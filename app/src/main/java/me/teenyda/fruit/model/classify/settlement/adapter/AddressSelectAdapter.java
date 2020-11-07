package me.teenyda.fruit.model.classify.settlement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.Contact;

/**
 * author: teenyda
 * date: 2020/11/4
 * description:
 */
public class AddressSelectAdapter extends RecyclerView.Adapter<AddressSelectAdapter.ViewHolder> {

    private Context mContext;
    private List<Contact> mContacts;
    private IContactAction mIContactAction;

    public interface IContactAction {
        void onContactSelect(Contact contact);
    }

    public AddressSelectAdapter(Context context) {
        mContext = context;
        mContacts = new ArrayList<>();
    }

    public void setContactAction(IContactAction contactAction) {
        this.mIContactAction = contactAction;
    }

    public void addContacts(List<Contact> contacts) {
        if (contacts != null && contacts.size() > 0) {
            mContacts.clear();
            mContacts.addAll(contacts);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_address_select, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = mContacts.get(position);
        holder.tv_name.setText(contact.getName());
        holder.address_mobile.setText(contact.getMobile());
        holder.address.setText(contact.getAddress());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIContactAction != null) {
                    mIContactAction.onContactSelect(contact);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView address_mobile;
        TextView address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            address_mobile = itemView.findViewById(R.id.address_mobile);
            address = itemView.findViewById(R.id.address);
        }
    }
}
