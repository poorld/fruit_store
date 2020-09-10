package me.teenyda.fruit_store.common.viewholder;

import android.view.View;


import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.teenyda.fruit_store.R;

public class VideoHolder extends RecyclerView.ViewHolder {
    public StandardGSYVideoPlayer player;

    public VideoHolder(@NonNull View view) {
        super(view);
        player = view.findViewById(R.id.player);
    }
}
