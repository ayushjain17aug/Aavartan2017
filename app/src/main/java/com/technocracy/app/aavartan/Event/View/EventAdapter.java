package com.technocracy.app.aavartan.Event.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.technocracy.app.aavartan.Event.Model.Data.Event;
import com.technocracy.app.aavartan.R;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    private Context context;
    private List<Event> eventList;

    public EventAdapter(Context context, List<Event> eventList) {
//        Log.d("AAVARTAN17","IN ADAPTER CONSTRUCTOR");
        this.context = context;
        this.eventList = eventList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_event, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        Log.d("AAVARTAN17","IN BIND VIEW HOLDER"+position);
        Event event = eventList.get(position);
        holder.txt.setText(event.getName() + "\nDate : " + event.getDate() + "\nTime:" + event.getTime() +
                "\nVenue:" + event.getVenue());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView txt;
//        public final ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            txt = (TextView) itemView.findViewById(R.id.event_name);
//            img = (ImageView) itemView.findViewById(R.id.icon1);
        }
    }
}
