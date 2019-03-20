package com.blazingapps.asus.sanskriti19;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder> {

    private final Context context
            ;
    private List<EventItem> eventList;

    Animation animation;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, desc;
        ImageView imageView;
        LinearLayout desclayout;
        ImageButton sharebtn;
        Button buy_ticket;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            desc = (TextView) view.findViewById(R.id.description);
            imageView = (ImageView) view.findViewById(R.id.imageview);
            sharebtn = (ImageButton) view.findViewById(R.id.sharebtn);
            desclayout = view.findViewById(R.id.desclayout);
            buy_ticket = view.findViewById(R.id.buy_ticket);
        }
    }


    public EventsAdapter(List<EventItem> eventItems, Context context) {
        this.eventList = eventItems;
        Collections.reverse(this.eventList);
        this.context = context;
        animation = AnimationUtils.loadAnimation(context,
                R.anim.blinking);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final EventItem item = eventList.get(position);
        holder.title.setText(item.getEvent_name());
        holder.desc.setText(item.getEvent_desc());
        holder.desclayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                cycleTextViewExpansion((TextView) v);

                int collapsedMaxLines = 10;
                ObjectAnimator animation = ObjectAnimator.ofInt(holder.desc, "maxLines",
                        holder.desc.getMaxLines() == collapsedMaxLines? holder.desc.getLineCount() : collapsedMaxLines);
                animation.setDuration(200).start();
            }
        });
        holder.buy_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = item.getTicketUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setPackage("com.android.chrome");
                try {
                    context.startActivity(i);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(context.getApplicationContext(),"Don't have online ticket booking", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (item.getTicketUrl().length()<5 || item.getTicketUrl() == null){
            holder.buy_ticket.setEnabled(false);
        }
        holder.sharebtn.startAnimation(animation);
//        holder.imageView.setimage(item.getYear());
        Picasso.get().load(item.getEvent_url()).placeholder(R.drawable.logo).fit().centerCrop().into(holder.imageView);
        holder.sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Sanskriti19\nMar Athanasius Collage of Engineering,Kothamangalam\nNational level arts fest\n\n"+
                        "Event Name : "+item.getEvent_name()+"\n\n"+item.getEvent_desc()+"\n\nPoster : "
                +item.getEvent_url()+"\n\nbuy ticket : "+item.getTicketUrl()+"\n\nShared from Sanskriti19 App\nDownload @ "+"http://play.google.com/store/apps/details?id=" + context.getPackageName());

                try {
                    context.startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(context.getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

}