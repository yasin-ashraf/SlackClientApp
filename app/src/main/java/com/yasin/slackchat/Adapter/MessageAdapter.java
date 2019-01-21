package com.yasin.slackchat.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yasin.slackchat.Model.Message;
import com.yasin.slackchat.R;
import com.yasin.slackchat.SessionManager;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by im_yasinashraf started on 21/1/19.
 */
public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Message> messages;
    private Context context;
    private SessionManager sessionManager;

    public MessageAdapter(List<Message> messages, Context context) {
        this.messages = messages;
        this.context = context;
        this.sessionManager = new SessionManager(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i){
            case 0:
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_self_message,viewGroup,false);
                return new SelfMessageViewHolder(view);

            case 1:
                View view2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_other_message,viewGroup,false);
                return new OtherMessageViewHolder(view2);

            default:
                View viewDefault = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_default_message,viewGroup,false);
                return new ActionMessageViewHolder(viewDefault);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Message message = messages.get(i);
        String userId = message.getUser();
        switch (viewHolder.getItemViewType()){
            case 0:
                SelfMessageViewHolder selfMessageViewHolder = (SelfMessageViewHolder) viewHolder;
                selfMessageViewHolder.textMessage.setText(message.getText());
                break;

            case 1:
                OtherMessageViewHolder otherMessageViewHolder = (OtherMessageViewHolder) viewHolder;
                otherMessageViewHolder.textMessage.setText(message.getText());
                break;

            case 2:
                ActionMessageViewHolder actionMessageViewHolder = (ActionMessageViewHolder) viewHolder;
                actionMessageViewHolder.actionText.setText(message.getText());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        Message m = messages.get(position);
        if(m.getUser().equals(sessionManager.getSelfUserId()))
            return 0;
        else if(m.getClientMsgId() != null || m.getSubtype() != null && m.getSubtype().equals("me_message"))
            return 1;
        else
            return 2;
    }

    class SelfMessageViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView profileImage;
        private TextView textMessage;

        SelfMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profile_image);
            textMessage = itemView.findViewById(R.id.tv_message);
        }
    }

    class OtherMessageViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView profileImage;
        private TextView textMessage;

        OtherMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profile_image_other);
            textMessage = itemView.findViewById(R.id.tv_message);
        }
    }

    class ActionMessageViewHolder extends RecyclerView.ViewHolder {

        private TextView actionText;

        public ActionMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            actionText = itemView.findViewById(R.id.tv_action);
        }
    }
}
