package com.yasin.slackchat.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yasin.slackchat.Model.Channel;
import com.yasin.slackchat.R;

import java.util.List;

/**
 * Created by im_yasinashraf started on 21/1/19.
 */
public class ChannelsAdapter extends RecyclerView.Adapter<ChannelsAdapter.ChannelViewHolder> {

    private List<Channel> channels;
    private ChannelSelectedListener channelSelectedListener;

    public ChannelsAdapter(List<Channel> channels) {
        this.channels = channels;
    }

    public void setChannelSelectedListener(ChannelSelectedListener channelSelectedListener) {
        this.channelSelectedListener = channelSelectedListener;
    }

    @NonNull
    @Override
    public ChannelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_channel_item,viewGroup,false);
        return new ChannelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelViewHolder channelViewHolder, int i) {
        Channel channel = channels.get(i);
        channelViewHolder.channelName.setText(channel.getName());

        channelViewHolder.itemView.setOnClickListener(view -> {
            channelSelectedListener.onChannelSelected(channel.getId());
        });
    }

    @Override
    public int getItemCount() {
        return channels.size();
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {

        private TextView channelName;

        ChannelViewHolder(@NonNull View itemView) {
            super(itemView);
            channelName = itemView.findViewById(R.id.tv_channel_name);
        }
    }

    public interface ChannelSelectedListener {

        void onChannelSelected(String channelId);
    }
}
