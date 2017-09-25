package com.technocracy.app.aavartan.Event.View;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.technocracy.app.aavartan.Event.Model.Data.Event;
import com.technocracy.app.aavartan.Event.Model.MockEventProvider;
import com.technocracy.app.aavartan.Event.Presenter.EventPresenter;
import com.technocracy.app.aavartan.Event.Presenter.EventPresenterImpl;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.helper.DatabaseHandler;

import java.util.List;

public class EventActivity extends AppCompatActivity implements EventView {
    private DatabaseHandler db;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private EventAdapter adapter;
    private EventPresenter presenter;
    private String eventSetId;
    private List<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle data = getIntent().getExtras();
        eventSetId = data.getString("event_selected");


        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle(eventSetId);
//        toolbar.setSubtitleTextColor(Color.WHITE);
      //  setSupportActionBar(toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar_event);
        recyclerView = (RecyclerView) findViewById(R.id.event_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter = new EventPresenterImpl(this, new MockEventProvider(), this);
        db = new DatabaseHandler(getApplicationContext());
        //TODO : take the event set id from the intent and accordingly call the api.....

        //eventSetId="fun";
        Log.d("AAVARTAN17", eventSetId);
        presenter.getEvents(eventSetId);
//        Log.d("AAVARTAN17","IN ON CREATE EVENT");
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
        Snackbar.make(findViewById(R.id.drawer_layout), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void showEvents(List<Event> body) {
        db.deleteAllEvent(eventSetId);
        for (int i = 0; i < body.size(); i++)
            db.addEvents(body.get(i), eventSetId);
        adapter = new EventAdapter(this, body);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showEventsFromDatabase() {
        eventList = db.getAllEvents(eventSetId);
        adapter = new EventAdapter(this, eventList);
    }


}