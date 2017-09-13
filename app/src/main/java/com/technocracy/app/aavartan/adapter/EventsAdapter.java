package com.technocracy.app.aavartan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.activity.intentactivity.EventDetailsActivity;
import com.technocracy.app.aavartan.api.Event;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder> {

    public Context context;
    ArrayList<Event> eventsList;

    public EventsAdapter(Context context, ArrayList<Event> eventsList) {
        this.eventsList = eventsList;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Event boundEvent = eventsList.get(position);
        if (boundEvent.getEventId() == 6)
            holder.img.setImageResource(R.drawable.ic_laptop_white_24dp);
        else
            Picasso.with(context).load(boundEvent.getEventImgUrl()).placeholder(R.drawable.ic_logo_small).into(holder.img);
        holder.txt.setText(boundEvent.getEventName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, EventDetailsActivity.class);
               // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("event_description", boundEvent.getEventName() + "\n\n"
                        + boundEvent.getEventType() + "\n" + boundEvent.getEventDescription() + "\n");
                i.putExtra("id", boundEvent.getEventId());
                i.putExtra("event_name", boundEvent.getEventName());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView txt;
        public final ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            txt = (TextView) itemView.findViewById(R.id.event_name);
            img = (ImageView) itemView.findViewById(R.id.icon1);
        }
    }

    public interface ClickListener {
        void itemClicked(View view, int position);
    }
}