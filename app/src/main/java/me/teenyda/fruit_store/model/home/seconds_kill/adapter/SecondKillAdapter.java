package me.teenyda.fruit_store.model.home.seconds_kill.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cn.iwgang.countdownview.CountdownView;
import me.teenyda.fruit_store.R;
import me.teenyda.fruit_store.common.entity.KillFruit;

/**
 * author: teenyda
 * date: 2020/9/20
 * description:
 */
public class SecondKillAdapter extends XRecyclerView.Adapter<SecondKillAdapter.ViewHolder> {

    private List<KillFruit> mFruits;

    private Context mContext;

    public SecondKillAdapter(Context context, List<KillFruit> fruits) {
        mContext = context;
        mFruits = fruits;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_seconds_kill, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // KillFruit fruit = mFruits.get(position);
        // holder.bindData(fruit);
    }

    /**
     * 以下两个接口代替 activity.onStart() 和 activity.onStop(), 控制 timer 的开关
     */
    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        // int pos = holder.getAdapterPosition();
        //            Log.d("MyViewHolder", String.format("mCvCountdownView %s is attachedToWindow", pos));

        // KillFruit itemInfo = mFruits.get(pos);

        // holder.refreshTime(itemInfo.getEndTime() - System.currentTimeMillis());
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        //            int pos = holder.getAdapterPosition();
        //            Log.d("MyViewHolder", String.format("mCvCountdownView %s is detachedFromWindow", pos));

        // holder.getCvCountdownView().stop();
    }

    @Override
    public int getItemCount() {
        return 10;
    }



    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView kill_fruit_img;
        TextView kill_fruit_name;
        TextView kill_fruit_count;
        TextView kill_price;
        CountdownView cv_countdown;
        KillFruit mFruit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // kill_fruit_img = itemView.findViewById(R.id.kill_fruit_img);
            // kill_fruit_name = itemView.findViewById(R.id.kill_fruit_name);
            // kill_fruit_count = itemView.findViewById(R.id.kill_fruit_count);
            // kill_price = itemView.findViewById(R.id.kill_price);
            // cv_countdown = itemView.findViewById(R.id.cv_countdown);
        }

/*        public void bindData(KillFruit fruit) {
            mFruit = fruit;
            refreshTime(fruit.getEndTime() - System.currentTimeMillis());
        }

        public void refreshTime(long leftTime) {
            if (leftTime > 0) {
                cv_countdown.start(leftTime);
            } else {
                cv_countdown.stop();
                cv_countdown.allShowZero();
            }
        }

        public KillFruit getBean() {
            return mFruit;
        }

        public CountdownView getCvCountdownView() {
            return cv_countdown;
        }
    }*/
    }
}
