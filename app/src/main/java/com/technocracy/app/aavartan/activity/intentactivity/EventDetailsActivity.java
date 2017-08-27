package com.technocracy.app.aavartan.activity.intentactivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.activity.AboutUsActivity;
import com.technocracy.app.aavartan.activity.AttractionActivity;
import com.technocracy.app.aavartan.activity.EventActivity;
import com.technocracy.app.aavartan.activity.InitiativesActivity;
import com.technocracy.app.aavartan.activity.LoginActivity;
import com.technocracy.app.aavartan.activity.NotificationsActivity;
import com.technocracy.app.aavartan.activity.ScheduleActivity;
import com.technocracy.app.aavartan.activity.SponsorsActivity;
import com.technocracy.app.aavartan.activity.UserActivity;
import com.technocracy.app.aavartan.activity.VigyaanActivity;
import com.technocracy.app.aavartan.api.User;
import com.technocracy.app.aavartan.gallery.View.GalleryActivity;
import com.technocracy.app.aavartan.helper.App;
import com.technocracy.app.aavartan.helper.AppController;
import com.technocracy.app.aavartan.helper.SQLiteHandler;
import com.technocracy.app.aavartan.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EventDetailsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView eventDetailsTextView;
    private String eventDetail;
    private String id;
    private DrawerLayout drawer;
    private Button registerEventButton;
    private Toolbar toolbar;
    SQLiteHandler sqLiteHandler;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        Bundle data = getIntent().getExtras();
        eventDetail = data.getString("event_description");
        String title = data.getString("event_name");
        id = "" + data.getInt("id");


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(title);
        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
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
            sqLiteHandler = new SQLiteHandler(getApplicationContext());
            user = sqLiteHandler.getUser();
            View navHeaderView = navigationView.getHeaderView(0);
            TextView username = (TextView) navHeaderView.findViewById(R.id.username);
            TextView usermail = (TextView) navHeaderView.findViewById(R.id.usermail);
            username.setText(user.getFirst_name());
            usermail.setText(user.getEmail());
        }

        eventDetailsTextView = (TextView) findViewById(R.id.event_details_textView);
        eventDetailsTextView.setText(eventDetail);

        registerEventButton = (Button) findViewById(R.id.registerbutton);
        if (data.getInt("id") == 6)
            registerEventButton.setVisibility(View.GONE);

        registerEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               SessionManager sessionManager = new SessionManager(getApplicationContext());
                if (sessionManager.isLoggedIn()) {
                    sqLiteHandler = new SQLiteHandler(getApplicationContext());
                    user = sqLiteHandler.getUser();
                    final String user_id = String.valueOf(user.getUser_id());
                    registerEvent(id, user_id);
                } else {
                    Snackbar.make(v, "Please Login First", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_Login:
                SessionManager sessionManager = new SessionManager(getApplicationContext());
                boolean userLoggedIn = sessionManager.isLoggedIn();
                if (userLoggedIn) {
                    Intent intent = new Intent(EventDetailsActivity.this, UserActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(EventDetailsActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.action_notification:
                Intent intent = new Intent(EventDetailsActivity.this, NotificationsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void registerEvent(final String id, final String user_id) {
        String tag_string_req = "req_registerEvent";
        StringRequest strReq = new StringRequest(Request.Method.POST,
                App.REGISTER_EVENT, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean error = jsonResponse.getBoolean("error");
                    if (!error) {

                        Snackbar.make(findViewById(R.id.drawer_layout), "Registration Successful!",Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        } else {
                        Snackbar.make(findViewById(R.id.drawer_layout), jsonResponse.getString("error_msg"),Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                         }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(findViewById(R.id.drawer_layout), getResources().getString(R.string.no_internet_error),Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", user_id);
                params.put("event_id", id);
                return params;
            }
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

}

