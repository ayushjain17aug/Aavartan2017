package com.technocracy.app.aavartan.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.api.User;
import com.technocracy.app.aavartan.helper.SQLiteHandler;
import com.technocracy.app.aavartan.helper.SessionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by User on 16/09/2016.
 */

public class UserActivity extends AppCompatActivity {

    Toolbar mToolbar;
    Button button1;
    Button button2;
    Button stay;
    SessionManager sessionManager;
    SQLiteHandler sqLiteHandler;
    User user;
    TextView user_id, first, last;
    TextView first_name;

    TextView email;
    TextView phone;
    TextView college;
    TextView event;
    TextView member_since;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_user);
        } catch (NullPointerException e) {
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("User");
        mToolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        sqLiteHandler = new SQLiteHandler(getApplicationContext());
        user = sqLiteHandler.getUser();
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserActivity.this, MyEventsActivity.class);
                UserActivity.this.startActivity(intent);
            }
        });
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager = new SessionManager(getApplicationContext());
                sessionManager.setLogin(false);
                sqLiteHandler.deleteUsers();
                Intent intent = new Intent(UserActivity.this, LoginActivity.class);
                UserActivity.this.startActivity(intent);
            }
        });

        stay = (Button) findViewById(R.id.stay);
        stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, StayActivity.class);
                UserActivity.this.startActivity(intent);
            }
        });
        Date notifDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;
        try {
            notifDate = dateFormat.parse(user.getMember_since());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy h:mm:ss a ");

        String outputdate = outputFormat.format(notifDate);


        user_id = (TextView) findViewById(R.id.user_id);
        first_name = (TextView) findViewById(R.id.first_name);
        first = (TextView) findViewById(R.id.first);
        last = (TextView) findViewById(R.id.last);

        email = (TextView) findViewById(R.id.email);
        phone = (TextView) findViewById(R.id.phone);
        college = (TextView) findViewById(R.id.college);
        event = (TextView) findViewById(R.id.event);
        member_since = (TextView) findViewById(R.id.member_since);

        user_id.setText(String.valueOf(user.getUser_id()));
        first_name.setText(user.getFirst_name());

        first.setText(user.getFirst_name());
        last.setText(user.getLast_name());
        email.setText(user.getEmail());
        phone.setText(user.getPhone());
        college.setText(user.getCollege());
        event.setText(String.valueOf(user.getcount_event_registered()));
        member_since.setText(outputdate);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}




