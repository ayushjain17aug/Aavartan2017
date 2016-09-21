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
import android.support.v7.widget.GridLayoutManager;
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
import com.technocracy.app.aavartan.adapter.SectionedGridRecyclerViewAdapter;
import com.technocracy.app.aavartan.adapter.SimpleAdapter;
import com.technocracy.app.aavartan.api.User;
import com.technocracy.app.aavartan.gallery.GalleryActivity;
import com.technocracy.app.aavartan.helper.App;
import com.technocracy.app.aavartan.helper.ConnectivityReceiver;
import com.technocracy.app.aavartan.helper.SQLiteHandler;
import com.technocracy.app.aavartan.helper.SessionManager;
import com.technocracy.app.aavartan.helper.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SponsorsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ProgressDialog pDialog;
    private VolleySingleton volleySinleton;
    private RequestQueue requestQueue;
    private RecyclerView mRecyclerView;
    private SimpleAdapter mAdapter;
    private ArrayList<SectionedGridRecyclerViewAdapter.Section> sections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        pDialog = new ProgressDialog(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        //This is the code to provide a sectioned grid
        sections = new ArrayList<SectionedGridRecyclerViewAdapter.Section>();


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
        if (!ConnectivityReceiver.isConnected()) {
            Snackbar.make(findViewById(R.id.drawer_layout), "Please Connect To Internet!",Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            volleySinleton = VolleySingleton.getInstance();
            requestQueue = volleySinleton.getRequestQueue();
            pDialog.setMessage("Loading Sponsors...");
            pDialog.show();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, App.SPONSORS_URL, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    pDialog.dismiss();
                    Log.d("abhi", "" + jsonObject.toString());
                    parseJsonResponse(jsonObject);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.d("abhi", "Error in loading " + volleyError.getMessage());
                    Snackbar.make(findViewById(R.id.drawer_layout), "Please try again!! ",Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    pDialog.dismiss();
                }
            });
            requestQueue.add(request);
        }
    }

    private void parseJsonResponse(JSONObject jsonObject) {
        if (jsonObject == null || jsonObject.length() == 0)
            return;
        try {
            JSONArray sponsors = jsonObject.getJSONArray("sponsors");
            String url0[]=null,url1[] = null, url2[] = null, url3[] = null;
            for (int i = 0; i < sponsors.length(); i++) {
                JSONObject jobj = sponsors.getJSONObject(i);
                if(i==0)
                    url0=new String[jobj.length()];
                if (i == 1)
                    url1 = new String[jobj.length()];
                if (i == 2)
                    url2 = new String[jobj.length()];
                if (i == 3)
                    url3 = new String[jobj.length()];
                String url[] = new String[jobj.length()];
                for (int j = 0; j < jobj.length(); j++) {
                    url[j] = jobj.getString("" + j);
                }
                if(i==0)
                    url0=url;
                if (i == 1) {
                    url1 = url;
                } else if (i == 2) {
                    url2 = url;
                } else {
                    url3 = url;
                }
            }
            mAdapter = new SimpleAdapter(this,url0, url1, url2, url3);
            sections.add(new SectionedGridRecyclerViewAdapter.Section(0, "ASSOCIATE SPONSORS"));
            sections.add(new SectionedGridRecyclerViewAdapter.Section(url0.length, "MEGAEVENT SPONSORS"));
            sections.add(new SectionedGridRecyclerViewAdapter.Section(url1.length+url0.length, "EVENT SPONSORS"));
            sections.add(new SectionedGridRecyclerViewAdapter.Section(url0.length+url1.length + url2.length, "PARTNERS"));
            //Add your adapter to the sectionAdapter
            SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
            SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                    SectionedGridRecyclerViewAdapter(this, R.layout.section, R.id.section_text, mRecyclerView, mAdapter);
            mSectionedAdapter.setSections(sections.toArray(dummy));
            //Your RecyclerView.Adapter
            //Apply this adapter to the RecyclerView
            mRecyclerView.setAdapter(mSectionedAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbarbutton, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_Login) {
            SessionManager sessionManager = new SessionManager(getApplicationContext());
            boolean userLoggedIn = sessionManager.isLoggedIn();
            if (userLoggedIn) {
                Intent intent = new Intent(SponsorsActivity.this, UserActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(SponsorsActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }
        if (id == R.id.action_notification) {
            Intent intent = new Intent(SponsorsActivity.this, NotificationsActivity.class);
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
