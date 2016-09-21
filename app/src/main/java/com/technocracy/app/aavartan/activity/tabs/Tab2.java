package com.technocracy.app.aavartan.activity.tabs;

/**
 * Created by nsn on 9/3/2015.
 */

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.adapter.ScheduleAdapter;
import com.technocracy.app.aavartan.api.Schedule;
import com.technocracy.app.aavartan.helper.App;
import com.technocracy.app.aavartan.helper.AppController;
import com.technocracy.app.aavartan.helper.DatabaseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Tab2 extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private ProgressDialog progressDialog;
    private static final String TAG_SCHEDULE = "schedule";
    private static final String TAG_ID = "id";
    private static final String TAG_EVENT = "event";
    private static final String TAG_VENUE = "venue";
    private static final String TAG_TIME = "time";
    private static final String TAG_IMAGE = "image_url";
    private View v;
    private JSONArray schedule = null;
    private ArrayList<Schedule> scheduleList;
    private ProgressDialog pDialog;
    private DatabaseHandler db;
    private ScheduleAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tab_2, container, false);
        db = new DatabaseHandler(getContext());
        pDialog = new ProgressDialog(getContext());
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        scheduleList = db.getScheduleDay2Items();
        adapter = new ScheduleAdapter(getContext(), scheduleList);
        recyclerView.setAdapter(adapter);
        getSchedule();
        return v;
    }

    private void getSchedule() {
        // Tag used to cancel the request
        String tag_string_req = "req_schedule2";
        pDialog.setMessage("Loading Schedule...");
        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.GET,
                App.SCHEDULE_DAY2_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    pDialog.hide();
                    JSONObject jsonObj = new JSONObject(response);

                    // Getting JSON Array node
                    schedule = jsonObj.getJSONArray(TAG_SCHEDULE);

                    db.deleteScheduleDay2();
                    // looping through All Contacts
                    for (int i = 0; i < schedule.length(); i++) {
                        JSONObject c = schedule.getJSONObject(i);

                        int id = c.getInt(TAG_ID);
                        String event = c.getString(TAG_EVENT);
                        String venue = c.getString(TAG_VENUE);
                        String time = c.getString(TAG_TIME);
                        String imageUrl = c.getString(TAG_IMAGE);

                        Schedule schedule = new Schedule(id, event, venue, time, imageUrl);
                        db.addScheduleDay2Item(schedule);
                    }
                    scheduleList = db.getScheduleDay2Items();
                    adapter = new ScheduleAdapter(getContext(), scheduleList);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    pDialog.hide();
                    Snackbar.make(v, "Please try again!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    e.printStackTrace();
                    scheduleList = db.getScheduleDay2Items();
                    adapter = new ScheduleAdapter(getContext(), scheduleList);
                    recyclerView.setAdapter(adapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Tab2 Schedule", "Request Error: " + error.getMessage());
                Snackbar.make(v, "Internet Connection not Present.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                pDialog.hide();
                scheduleList = db.getScheduleDay2Items();
                adapter = new ScheduleAdapter(getContext(), scheduleList);
                recyclerView.setAdapter(adapter);
            }
        }
        );
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}