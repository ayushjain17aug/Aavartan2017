package com.technocracy.app.aavartan.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.adapter.CustomAdapter;
import com.technocracy.app.aavartan.api.User;
import com.technocracy.app.aavartan.gallery.View.GalleryActivity;
import com.technocracy.app.aavartan.helper.SQLiteHandler;
import com.technocracy.app.aavartan.helper.SessionManager;


public class VigyaanActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;

    DrawerLayout drawer;
    GridView gv;
    public static String[] prgmNameList = {"Architecture", "Bio Med", "Bio Tech", "Chemical", "Civil", "CSE", "Electrical", "Elex", "IT", "Mechanical", "Metallurgy", "Mining", "MCA", "E-Cell", "Go Green"};
    public static int[] movImgs = {R.drawable.archi1, R.drawable.biomed1, R.drawable.biotech1, R.drawable.chemical1, R.drawable.civil1, R.drawable.cse1, R.drawable.electrical1, R.drawable.etc1, R.drawable.it1, R.drawable.mech1, R.drawable.meta1, R.drawable.mining1, R.drawable.mca1, R.drawable.ecell1, R.drawable.gogreen1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_vigyaan);
        } catch (NullPointerException e) {
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Vigyaan");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        try {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();
        } catch (NullPointerException e) {
        }
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
        gv = (GridView) findViewById(R.id.gridView1);
        gv.setAdapter(new CustomAdapter(VigyaanActivity.this, prgmNameList, movImgs));
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Navigation Drawer
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
                Intent intent = new Intent(VigyaanActivity.this, UserActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(VigyaanActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }
        if (id == R.id.action_notification) {
            Intent intent = new Intent(VigyaanActivity.this, NotificationsActivity.class);
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