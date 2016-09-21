package com.technocracy.app.aavartan.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.technocracy.app.aavartan.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by User on 20/09/2016.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.LeaderboardViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private final ArrayList<HashMap<String, String>> eventslists;
    private final Context mContext;

    public ScheduleAdapter(Context context, ArrayList<HashMap<String, String>> eventslists) {

        mContext=context;
        mContext.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        this.eventslists = eventslists;
    }

    @Override
    public LeaderboardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_row, parent, false);
        return new LeaderboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LeaderboardViewHolder holder, final int position) {
        holder.boundLeader = eventslists.get(position);
        holder.leaderCard.setBackgroundColor(mContext.getResources().getColor(R.color.blue));
        holder.event_name.setText(Html.fromHtml(holder.boundLeader.get("event")));
        holder.event_date.setText(Html.fromHtml(holder.boundLeader.get("venuetime")));
        String eventImageUrl = holder.boundLeader.get("image_url");
            Picasso.with(mContext)
                    .load(eventImageUrl).placeholder(R.drawable.ic_logo_small)
                    .into(holder.eventImage);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });
    }

    @Override
    public int getItemCount() {
        return eventslists.size();
    }

    public class LeaderboardViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final CardView leaderCard;
        public final TextView event_name;
        public final TextView event_date;
        public final ImageView eventImage;
        public HashMap<String, String> boundLeader;

        public LeaderboardViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            leaderCard = (CardView) itemView.findViewById(R.id.leaderboard_card);
            event_name = (TextView) itemView.findViewById(R.id.ListHead);
            event_date = (TextView) itemView.findViewById(R.id.ListDesc);
            eventImage = (ImageView) itemView.findViewById(R.id.eventsImage);
        }
    }
}
