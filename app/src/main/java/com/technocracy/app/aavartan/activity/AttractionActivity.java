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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.adapter.AttractionAdapter;
import com.technocracy.app.aavartan.api.Attraction;
import com.technocracy.app.aavartan.api.User;
import com.technocracy.app.aavartan.gallery.GalleryActivity;
import com.technocracy.app.aavartan.helper.App;
import com.technocracy.app.aavartan.helper.AppController;
import com.technocracy.app.aavartan.helper.ConnectivityReceiver;
import com.technocracy.app.aavartan.helper.Eventkeys;
import com.technocracy.app.aavartan.helper.SQLiteHandler;
import com.technocracy.app.aavartan.helper.SessionManager;
import com.technocracy.app.aavartan.helper.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AttractionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Eventkeys.Attractions {

    private SessionManager sessionManager;
    private RecyclerView rCyclerView;
    private VolleySingleton volleySinleton;
    private ProgressDialog pDialog;
    private RequestQueue requestQueue;
    private ArrayList<Attraction> b;
    private AttractionAdapter Adap;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Attraction");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        pDialog = new ProgressDialog(this);
        rCyclerView = (RecyclerView) findViewById(R.id.rView);
        rCyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (!ConnectivityReceiver.isConnected()) {
            Snackbar.make(findViewById(R.id.drawer_layout), "Please connect to Internet!",Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            try {
                volleySinleton = VolleySingleton.getInstance();
                requestQueue = volleySinleton.getRequestQueue();
                pDialog.setMessage("Loading Attractions...");
                pDialog.show();
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, App.ATTRACTIONS_URL, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        pDialog.hide();
                        Log.d("abhi", "" + jsonObject.toString());
                        parseJsonResponse(jsonObject);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Snackbar.make(findViewById(R.id.drawer_layout), "Please try again!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        pDialog.hide();
                    }
                });
                requestQueue.add(request);
            } catch (NullPointerException e) {
            }
        }
    }

    private void parseJsonResponse(JSONObject jsonObject) {

        if (jsonObject == null || jsonObject.length() == 0)
            return;
        try {
            b = new ArrayList<Attraction>();
            JSONArray attractions = jsonObject.getJSONArray("attractions");
            for (int i = 0; i < attractions.length(); i++) {
                JSONObject currentEvent = attractions.getJSONObject(i);
                String event = currentEvent.getString(KEY_NAME);
                String description = currentEvent.getString(KEY_DESCRIPTION);
                String id = currentEvent.getString(KEY_ID);
                String imgUrl = currentEvent.getString(KEY_IMG_URL);
                Attraction a = new Attraction(event, id, description, imgUrl);
                b.add(a);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Adap = new AttractionAdapter(AppController.getInstance().getApplicationContext(), b);
        rCyclerView.setAdapter(Adap);
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
            sessionManager = new SessionManager(getApplicationContext());
            boolean userLoggedIn = sessionManager.isLoggedIn();
            if (userLoggedIn) {
                Intent intent = new Intent(AttractionActivity.this, UserActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(AttractionActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }
        if (id == R.id.action_notification) {
            Intent intent = new Intent(AttractionActivity.this, NotificationsActivity.class);
            AttractionActivity.this.startActivity(intent);
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
}