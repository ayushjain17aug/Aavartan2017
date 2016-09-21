package com.technocracy.app.aavartan.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.adapter.EventsAdapter;
import com.technocracy.app.aavartan.api.Event;
import com.technocracy.app.aavartan.api.User;
import com.technocracy.app.aavartan.gallery.GalleryActivity;
import com.technocracy.app.aavartan.helper.App;
import com.technocracy.app.aavartan.helper.ConnectivityReceiver;
import com.technocracy.app.aavartan.helper.Eventkeys;
import com.technocracy.app.aavartan.helper.SQLiteHandler;
import com.technocracy.app.aavartan.helper.SessionManager;
import com.technocracy.app.aavartan.helper.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private VolleySingleton volleySinleton;
    private RequestQueue requestQueue;
    private ArrayList<Event> eventsList;
    private String key1;
    private String key2;
    private String key3;
    private String key4, key5;
    private String url1;
    private RecyclerView rCyclerView;
    private ProgressDialog pDialog;
    private String key0;
    private EventsAdapter Recycler_Adap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Bundle data = getIntent().getExtras();
        String title = data.getString("event_selected");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(title);
        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        pDialog = new ProgressDialog(this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        if (sessionManager.isLoggedIn()) {
            SQLiteHandler sqLiteHandler = new SQLiteHandler(getApplicationContext());
            User user = sqLiteHandler.getUser();
            View navHeaderView = navigationView.getHeaderView(0);
            TextView username = (TextView) navHeaderView.findViewById(R.id.username);
            TextView usermail = (TextView) navHeaderView.findViewById(R.id.usermail);
            username.setText(user.getFirst_name());
            usermail.setText(user.getEmail());
        }

        //assinging keys to read events for the particular navigation drawer clicked
        key0 = Eventkeys.TechnicalEvents.KEY_ID;
        assert title != null;
        switch (title) {
            case "Technical Events":
                key1 = Eventkeys.TechnicalEvents.KEY_TECHNICAL;
                key2 = Eventkeys.TechnicalEvents.KEY_EVENT;
                key3 = Eventkeys.TechnicalEvents.KEY_TYPE;
                key4 = Eventkeys.TechnicalEvents.KEY_DESCRIPTION;
                key5 = Eventkeys.TechnicalEvents.KEY_IMAGE_URL;
                url1 = App.TECHNICAL_EVENTS_URL;
                break;
            case "Fun Events":
                key1 = Eventkeys.FunEvents.KEY_FUNEVENTS;
                key2 = Eventkeys.FunEvents.KEY_EVENT;
                key3 = Eventkeys.FunEvents.KEY_TYPE;
                key4 = Eventkeys.FunEvents.KEY_DESCRIPTION;
                key5 = Eventkeys.FunEvents.KEY_IMAGE_URL;
                url1 = App.FUN_EVENTS_URL;
                break;
            case "Managerial Events":
                key1 = Eventkeys.Managerial.KEY_MANAGERIAL;
                key2 = Eventkeys.Managerial.KEY_EVENT;
                key3 = Eventkeys.Managerial.KEY_TYPE;
                key4 = Eventkeys.Managerial.KEY_DESCRIPTION;
                key5 = Eventkeys.Managerial.KEY_IMAGE_URL;
                url1 = App.MANAGERIAL_EVENTS_URL;
                break;
            case "Robotics Events":
                key1 = Eventkeys.Robotics.KEY_ROBOTICS;
                key2 = Eventkeys.Robotics.KEY_EVENT;
                key3 = Eventkeys.Robotics.KEY_TYPE;
                key4 = Eventkeys.Robotics.KEY_DESCRIPTION;
                key5 = Eventkeys.Robotics.KEY_IMAGE_URL;
                url1 = App.ROBOTICS_URL;
                break;
        }
        rCyclerView = (RecyclerView) findViewById(R.id.rView);
        rCyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Checking if user is online or not
        if (!ConnectivityReceiver.isConnected())
            Snackbar.make(findViewById(R.id.drawer_layout), "Please conect to Internet!",Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        else {
            try {
                volleySinleton = VolleySingleton.getInstance();
                requestQueue = volleySinleton.getRequestQueue();
                pDialog.setMessage("Loading Events...");
                pDialog.show();
                //loading the php file of the particular category of the events
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url1, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        pDialog.hide();
                        parseJsonResponse(jsonObject);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(EventActivity.this, "Please try again!", Toast.LENGTH_LONG).show();
                         pDialog.hide();
                    }
                });
                requestQueue.add(request);
            } catch (NullPointerException e) {
            }
        }
    }

    public void parseJsonResponse(JSONObject jsonObject) {
        if (jsonObject == null || jsonObject.length() == 0)
            return;
        try {
            JSONArray object_Events = jsonObject.getJSONArray(key1);
            eventsList = new ArrayList<>();
            for (int i = 0; i < object_Events.length(); i++) {
                //getting the details of all the events in the category and storing it in Event object which contains data as name,type,description,id
                JSONObject currentEvent = object_Events.getJSONObject(i);
                String event_name = currentEvent.getString(key2);
                String event_type = currentEvent.getString(key3);
                String event_description = currentEvent.getString(key4);
                int event_id = currentEvent.getInt(key0);
                String event_img_url = currentEvent.getString(key5);
                Event events = new Event(event_id, event_name, event_type, event_description, event_img_url);
                eventsList.add(events);
            }

            Recycler_Adap = new EventsAdapter(this, eventsList);
            rCyclerView.setAdapter(Recycler_Adap);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbarbutton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Login) {
            SessionManager sessionManager = new SessionManager(getApplicationContext());
            boolean userLoggedIn = sessionManager.isLoggedIn();
            if (userLoggedIn) {
                Intent intent = new Intent(EventActivity.this, UserActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(EventActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }
        if (id == R.id.action_notification) {
            Intent intent = new Intent(EventActivity.this, NotificationsActivity.class);
            startActivity(intent);
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Intent intent;
        switch (item.getItemId()) {
            case R.id.nav_home:
                finish();
                break;
            case R.id.nav_attractions:
                intent = new Intent(this, AttractionActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_gallery:
                intent = new Intent(this, GalleryActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_fun_events:
                intent = new Intent(this, EventActivity.class);
                intent.putExtra("event_selected", "Fun Events");
                startActivity(intent);
                finish();
                break;
            case R.id.nav_managerial_events:
                intent = new Intent(this, EventActivity.class);
                intent.putExtra("event_selected", "Managerial Events");
                startActivity(intent);
                finish();
                break;
            case R.id.nav_robotics:
                intent = new Intent(this, EventActivity.class);
                intent.putExtra("event_selected", "Robotics Events");
                startActivity(intent);
                finish();
                break;
            case R.id.nav_technical_events:
                intent = new Intent(this, EventActivity.class);
                intent.putExtra("event_selected", "Technical Events");
                startActivity(intent);
                finish();
                break;
            case R.id.nav_vigyaan:
                intent = new Intent(this, VigyaanActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_sponsors:
                intent = new Intent(this, SponsorsActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_initiatives:
                intent = new Intent(this, InitiativesActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_schedule:
                intent = new Intent(this, ScheduleActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_about_us:
                intent = new Intent(this, AboutUsActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}