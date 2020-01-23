package com.example.android.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    private ArrayList<Notification> mNotificationArrayList;

    public NotificationAdapter(ArrayList<Notification> notificationArrayList) {
        mNotificationArrayList = notificationArrayList;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        NotificationViewHolder nvh = new NotificationViewHolder(view);
        return nvh;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification currentNotification = mNotificationArrayList.get(position);
        holder.tvLocation.setText(currentNotification.getLocation());
        holder.tvMessage.setText(currentNotification.getMessage());

    }

    @Override
    public int getItemCount() {
        return mNotificationArrayList.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        public TextView tvLocation;
        public TextView tvMessage;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLocation = itemView.findViewById(R.id.location);
            tvMessage = itemView.findViewById(R.id.message);
        }
    }


}
