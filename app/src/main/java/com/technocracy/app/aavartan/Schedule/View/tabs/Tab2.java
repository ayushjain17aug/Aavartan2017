package com.technocracy.app.aavartan.Schedule.View.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.technocracy.app.aavartan.Event.Model.Data.Event;
import com.technocracy.app.aavartan.Event.View.EventDetailsActivity;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.Schedule.Model.Data.Schedule;
import com.technocracy.app.aavartan.Schedule.Model.MockScheduleProvider;
import com.technocracy.app.aavartan.Schedule.Presenter.SchedulePresenter;
import com.technocracy.app.aavartan.Schedule.Presenter.SchedulePresenterImpl;
import com.technocracy.app.aavartan.Schedule.View.ScheduleAdapter;
import com.technocracy.app.aavartan.Schedule.View.ScheduleView;
import com.technocracy.app.aavartan.helper.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class Tab2 extends Fragment implements ScheduleView {
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private View v;
    private ArrayList<Schedule> scheduleList;
    private DatabaseHandler db;
    private ScheduleAdapter adapter;
    private ProgressBar progressBar;
    private SchedulePresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tab_2, container, false);
        db = new DatabaseHandler(getContext());
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        scheduleList = db.getScheduleDay2Items();
        progressBar = (ProgressBar) v.findViewById(R.id.progress_bar_tab2);
        presenter = new SchedulePresenterImpl(new MockScheduleProvider(), this, getContext());
        presenter.getSchedule("2");
        return v;
    }

    @Override
    public void showProgressBar(boolean b) {
        if (b)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(getActivity().findViewById(R.id.tab2_rel_layout), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void showScheduleFromDatabase() {
        scheduleList = db.getScheduleDay2Items();
        adapter = new ScheduleAdapter(getContext(), scheduleList, presenter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showSchedule(List<Schedule> schedule) {
        adapter = new ScheduleAdapter(getContext(), schedule, presenter);
        for (Schedule x : schedule)
            db.addScheduleDay2Item(x);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showEventDetail(Event event) {
        Intent i = new Intent(getContext(), EventDetailsActivity.class);
        i.putExtra("id", event.getEventId());
        i.putExtra("event_name", event.getName());
        i.putExtra("event_description", event.getDescription());
        startActivity(i);
    }
}