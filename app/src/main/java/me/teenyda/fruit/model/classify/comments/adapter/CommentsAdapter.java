package me.teenyda.fruit.model.classify.comments.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.Comments;
import me.teenyda.fruit.common.entity.User;

/**
 * author: teenyda
 * date: 2020/12/27
 * description:
 */
public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    private Context mContext;
    private List<Comments> mComments;

    public CommentsAdapter(Context context) {
        this.mContext = context;
        mComments = new ArrayList<>();
    }

    public void addData(List<Comments> comments) {
        mComments.clear();
        mComments.addAll(comments);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_comments, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comments comments = mComments.get(position);
        User user = comments.getUser();
        holder.username1.setText(user.getUsername());
        holder.user_comments.setText(comments.getContent());
        String reply = comments.getReply();
        if (TextUtils.isEmpty(reply)) {
            holder.comments_reply.setVisibility(View.GONE);
        } else {
            holder.comments_reply.setVisibility(View.VISIBLE);
            holder.business_reply.setText(reply);
        }
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView profile_image;
        ShimmerFrameLayout sfl;
        TextView username1;
        ImageView vip_img;
        TextView user_comments;
        LinearLayout comments_reply;
        TextView business_reply;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_image = itemView.findViewById(R.id.profile_image);
            sfl = itemView.findViewById(R.id.sfl);
            username1 = itemView.findViewById(R.id.username1);
            vip_img = itemView.findViewById(R.id.vip_img);
            user_comments = itemView.findViewById(R.id.user_comments);
            comments_reply = itemView.findViewById(R.id.comments_reply);
            business_reply = itemView.findViewById(R.id.business_reply);
        }
    }
}
