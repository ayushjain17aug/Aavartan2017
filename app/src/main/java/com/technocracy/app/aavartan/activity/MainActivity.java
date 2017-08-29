package com.technocracy.app.aavartan.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.technocracy.app.aavartan.Event.View.EventActivity;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.api.User;
import com.technocracy.app.aavartan.gallery.View.GalleryActivity;
import com.technocracy.app.aavartan.helper.SQLiteHandler;
import com.technocracy.app.aavartan.helper.SessionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Boolean isFabOpen = true;
    private SessionManager sessionManager;
    private String currentDateString;
    private SimpleDateFormat dateFormat;
    FloatingActionButton fab;
    private SQLiteHandler sqLiteHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        currentDateString = dateFormat.format(Calendar.getInstance().getTime());
        sessionManager = new SessionManager(getApplicationContext());

        //new DatabaseHandler(getApplicationContext()).dropDB();

        if (sessionManager.isLoggedIn()) {
            sqLiteHandler = new SQLiteHandler(getApplicationContext());
            User user = sqLiteHandler.getUser();
            if (user.getVerified() == 0) {
                if (getTime(user.getMember_since())) {
                    sessionManager.setLogin(false);
                    sqLiteHandler.deleteUsers();
                    new AlertDialog.Builder(this).setIcon(R.drawable.ic_dialog_alert).setTitle("Verification")
                            .setMessage("Please verify your email from the verification link sent to your email to continue using this account. ")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    sessionManager.setLogin(false);
                                    sqLiteHandler.deleteUsers();
                                    Snackbar.make(findViewById(R.id.main_activity_layout),"You have been logged out.",Snackbar.LENGTH_LONG).show();
                                }
                            }).show();
                }
            }

        }

        /*DatabaseHandler db = new DatabaseHandler(this);
        db.dropDB();*/

        ImageView ulka = (ImageView) findViewById(R.id.ulka);
        Animation ulka_anim = AnimationUtils.loadAnimation(getApplication(), R.anim.ulka_anim);
        ulka_anim.setRepeatCount(Animation.INFINITE);
        ulka_anim.setRepeatMode(Animation.INFINITE);
        ulka.startAnimation(ulka_anim);
        ImageView rocket = (ImageView) findViewById(R.id.rocket);
        Animation rocket_anim = AnimationUtils.loadAnimation(getApplication(), R.anim.rocket);

        rocket.startAnimation(rocket_anim);
        ImageView revolve = (ImageView) findViewById(R.id.revolve);
        Animation revolve_anim = AnimationUtils.loadAnimation(getApplication(), R.anim.revolve_anim);
        revolve_anim.setRepeatCount(Animation.INFINITE);
        revolve_anim.setRepeatMode(Animation.INFINITE);
        revolve.startAnimation(revolve_anim);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isFabOpen) {
                    FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab_1);
                    Animation show_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_show);
                    Animation hide_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_hide);
                    fab1.startAnimation(show_fab_1);
                    fab1.setClickable(true);

                    fab1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            fab.callOnClick();
                            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                            MainActivity.this.startActivity(intent);
                        }
                    });
                    FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) fab1.getLayoutParams();
                    layoutParams1.rightMargin += (int) (fab1.getWidth() * 1.5);
                    fab1.setLayoutParams(layoutParams1);


                    FloatingActionButton fab3 = (FloatingActionButton) findViewById(R.id.fab_3);
                    Animation show_fab_3 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab3_show);
                    Animation hide_fab_3 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab3_hide);
                    fab3.startAnimation(show_fab_3);
                    fab3.setClickable(true);
                    fab3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            fab.callOnClick();
                            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                            sharingIntent.setType("text/plain");
                            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.technocracy.app.aavartan");
                            startActivity(Intent.createChooser(sharingIntent, "Share app link using"));

                        }
                    });
                    FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
                    layoutParams3.bottomMargin += (int) (fab3.getWidth() * 1.5);
                    fab3.setLayoutParams(layoutParams3);


                    FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab_2);
                    Animation show_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_show);
                    Animation hide_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_hide);
                    fab2.startAnimation(show_fab_2);
                    fab2.setClickable(true);
                    fab2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            fab.callOnClick();
                            /*Snackbar.make(view, "FAB2",Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();*/
                            Intent toContacts = new Intent(MainActivity.this, ContactsActivity.class);
                            startActivity(toContacts);
                        }
                    });

                    FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
                    layoutParams2.rightMargin += fab2.getWidth() * 1;
                    layoutParams2.bottomMargin += fab2.getWidth() * 1;
                    fab2.setLayoutParams(layoutParams2);
                    isFabOpen = false;
                } else {
                    FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab_1);
                    Animation hide_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_hide);
                    FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) fab1.getLayoutParams();
                    layoutParams1.rightMargin -= (int) (fab1.getWidth() * 1.5);
                    fab1.setLayoutParams(layoutParams1);
                    fab1.startAnimation(hide_fab_1);
                    fab1.setClickable(false);

                    FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab_2);
                    Animation hide_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_hide);
                    FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
                    layoutParams2.rightMargin -= (int) (fab2.getWidth() * 1.0);
                    layoutParams2.bottomMargin -= (int) (fab2.getWidth() * 1.0);
                    fab2.setLayoutParams(layoutParams2);
                    fab2.startAnimation(hide_fab_2);
                    fab2.setClickable(false);


                    FloatingActionButton fab3 = (FloatingActionButton) findViewById(R.id.fab_3);
                    Animation hide_fab_3 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab3_hide);
                    FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
                    layoutParams3.bottomMargin -= (int) (fab3.getWidth() * 1.5);
                    fab3.setLayoutParams(layoutParams3);
                    fab3.startAnimation(hide_fab_3);
                    fab3.setClickable(false);
                    isFabOpen = true;
                }
            }
        });

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
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this).setIcon(R.drawable.ic_dialog_alert).setTitle("Exit")
                    .setMessage("Are you sure ?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    }).setNegativeButton("No", null).show();
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
            sessionManager = new SessionManager(getApplicationContext());
            boolean userLoggedIn = sessionManager.isLoggedIn();
            if (userLoggedIn) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }
        if (id == R.id.action_notification) {
            Intent intent = new Intent(MainActivity.this, NotificationsActivity.class);
            MainActivity.this.startActivity(intent);
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Intent intent;
        switch (item.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_attractions:
                intent = new Intent(this, AttractionActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_gallery:
                intent = new Intent(this, GalleryActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_fun_events:
                intent = new Intent(this, EventActivity.class);
                intent.putExtra("event_selected", "fun");
                startActivity(intent);
                break;
            case R.id.nav_managerial_events:
                intent = new Intent(this, EventActivity.class);
                intent.putExtra("event_selected", "manager");
                startActivity(intent);
                break;
            case R.id.nav_robotics:
                intent = new Intent(this, EventActivity.class);
                intent.putExtra("event_selected", "robo");
                startActivity(intent);
                break;
            case R.id.nav_technical_events:
                intent = new Intent(this, EventActivity.class);
                intent.putExtra("event_selected", "tech");
                startActivity(intent);
                break;
            case R.id.nav_vigyaan:
                intent = new Intent(this, VigyaanActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_sponsors:
                intent = new Intent(this, SponsorsActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_initiatives:
                intent = new Intent(this, InitiativesActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_schedule:
                intent = new Intent(this, ScheduleActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_about_us:
                intent = new Intent(this, AboutUsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean getTime(String notifTime) {
        try {
            Date currentDate = dateFormat.parse(currentDateString);
            Date notifDate = dateFormat.parse(notifTime);
            long differenceInMS = currentDate.getTime() - notifDate.getTime();
            long differenceInSecs = TimeUnit.MILLISECONDS.toSeconds(differenceInMS);
            long differenceInDays = TimeUnit.SECONDS.toDays(differenceInSecs);
            String time = new SimpleDateFormat("h:mm aa").format(notifDate);
            if (differenceInDays >= 1)
                return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

}
