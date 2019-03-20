package com.blazingapps.asus.sanskriti19;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    private List<NotificationtItem> eventList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, desc;
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            desc = (TextView) view.findViewById(R.id.description);
            imageView = (ImageView) view.findViewById(R.id.icon);
        }
    }


    public NotificationAdapter(List<NotificationtItem> eventItems) {
        this.eventList = eventItems;
        Collections.reverse(this.eventList);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NotificationtItem item = eventList.get(position);
        holder.title.setText(item.getName());
        holder.desc.setText(item.getDescription());
//        holder.imageView.setimage(item.getYear());
        Picasso.get().load(item.getUrl()).placeholder(R.drawable.logo).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}