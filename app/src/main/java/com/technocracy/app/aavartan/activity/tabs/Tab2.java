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
import com.technocracy.app.aavartan.helper.App;
import com.technocracy.app.aavartan.helper.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Tab2 extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private ProgressDialog progressDialog;
    private static final String TAG_SCHEDULE = "schedule";
    private static final String TAG_EVENT = "event";
    private static final String TAG_VENUE = "venue";
    private static final String TAG_TIME="time";
    private static final String TAG_IMAGE = "image_url";
    View v;
    JSONArray schedule = null;
    ArrayList<HashMap<String, String>> scheduleList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tab_1, container, false);
        progressDialog = new ProgressDialog(getContext());
        scheduleList = new ArrayList<HashMap<String, String>>();
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        getSchedule();
        return v;
    }

    private void getSchedule() {
        // Tag used to cancel the request
        String tag_string_req = "req_schedule";
        progressDialog.setMessage("Loading Schedule...");
        progressDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.GET,
                App.SCHEDULE_DAY2_URL, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObj = new JSONObject(response);

                    // Getting JSON Array node
                    schedule = jsonObj.getJSONArray(TAG_SCHEDULE);

                    // looping through All Contacts
                    for (int i = 0; i < schedule.length(); i++) {
                        JSONObject c = schedule.getJSONObject(i);

                        String Event = c.getString(TAG_EVENT);
                        String Venue = c.getString(TAG_VENUE);
                        String Time = c.getString(TAG_TIME);
                        String imageUrl = c.getString(TAG_IMAGE);
                        // tmp hashmap for single contact
                        HashMap<String, String> schedule = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        schedule.put(TAG_EVENT, Event);
                        schedule.put(TAG_TIME,Time);
                        schedule.put(TAG_VENUE, Venue);
                        schedule.put(TAG_IMAGE, imageUrl);

                        // adding contact to contact list
                        scheduleList.add(schedule);
                    }
                    progressDialog.hide();
                    ScheduleAdapter adapter = new ScheduleAdapter(getContext(), scheduleList);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

                , new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Tab1 Schedule", "Request Error: " + error.getMessage());
                Snackbar.make(v, "Internet connection error", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                progressDialog.hide();
            }
        }

        );
        // Adding request to request queue
        AppController.getInstance().

                addToRequestQueue(strReq, tag_string_req);
    }
}