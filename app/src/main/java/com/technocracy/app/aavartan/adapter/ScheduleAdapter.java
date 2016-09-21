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
import com.technocracy.app.aavartan.api.Schedule;

import java.util.ArrayList;

/**
 * Created by User on 20/09/2016.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.LeaderboardViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private final ArrayList<Schedule> scheduleArrayList;
    private final Context mContext;

    public ScheduleAdapter(Context context, ArrayList<Schedule> scheduleArrayList) {

        mContext = context;
        mContext.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        this.scheduleArrayList = scheduleArrayList;
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
        holder.boundScheduleItem = scheduleArrayList.get(position);
        holder.scheduleCard.setBackgroundColor(mContext.getResources().getColor(R.color.blue));
        holder.eventNameTv.setText(Html.fromHtml(holder.boundScheduleItem.getEventName()));
        holder.eventTimeTv.setText(Html.fromHtml(holder.boundScheduleItem.getTime()));
        holder.eventVenueTv.setText(Html.fromHtml(holder.boundScheduleItem.getVenue()));

        String eventImageUrl = holder.boundScheduleItem.getImageUrl();
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
        return scheduleArrayList.size();
    }

    public class LeaderboardViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final CardView scheduleCard;
        public final TextView eventNameTv;
        public final TextView eventTimeTv;
        public final TextView eventVenueTv;

        public final ImageView eventImage;
        public Schedule boundScheduleItem;

        public LeaderboardViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            scheduleCard = (CardView) itemView.findViewById(R.id.leaderboard_card);
            eventNameTv = (TextView) itemView.findViewById(R.id.ListHead);
            eventTimeTv = (TextView) itemView.findViewById(R.id.ListTime);
            eventVenueTv = (TextView) itemView.findViewById(R.id.ListVenue);
            eventImage = (ImageView) itemView.findViewById(R.id.eventsImage);
        }
    }
}