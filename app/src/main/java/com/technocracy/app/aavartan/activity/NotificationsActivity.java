package com.technocracy.app.aavartan.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.adapter.NotificationAdapter;
import com.technocracy.app.aavartan.api.Notifications;
import com.technocracy.app.aavartan.api.User;
import com.technocracy.app.aavartan.helper.App;
import com.technocracy.app.aavartan.helper.AppController;
import com.technocracy.app.aavartan.helper.DatabaseHandler;
import com.technocracy.app.aavartan.helper.SQLiteHandler;
import com.technocracy.app.aavartan.helper.SessionManager;
import com.technocracy.app.aavartan.util.DividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationsActivity extends AppCompatActivity {
    private List<Notifications> notificationsList;
    private RecyclerView recyclerView;
    private NotificationAdapter mAdapter;
    private Toolbar mToolbar;
    private TextView noNotificationTextView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_notification_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("Notification");
        mToolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        noNotificationTextView = (TextView) findViewById(R.id.noNotificationsTextView);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new NotificationAdapter(notificationsList, getApplicationContext());
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setColorSchemeResources(App.SWIPE_REFRESH_COLORS);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SessionManager sessionManager = new SessionManager(getApplicationContext());
                if (sessionManager.isLoggedIn()) {
                    SQLiteHandler sqLiteHandler = new SQLiteHandler(getApplicationContext());
                    User user = sqLiteHandler.getUser();
                    String userid = String.valueOf(user.getUser_id());
                    preparenotificationdata(userid);
                } else {
                    preparenotificationdata();
                }
            }
        });
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                SessionManager sessionManager = new SessionManager(getApplicationContext());
                if (sessionManager.isLoggedIn()) {
                    SQLiteHandler sqLiteHandler = new SQLiteHandler(getApplicationContext());
                    User user = sqLiteHandler.getUser();
                    String userid = String.valueOf(user.getUser_id());
                    preparenotificationdata(userid);
                } else {
                    preparenotificationdata();
                }
            }
        });

    }

    private void preparenotificationdata(final String userID) {
        String tag_string_req = "req_getNotification";
        swipeRefreshLayout.setRefreshing(true);

        StringRequest strReq = new StringRequest(Request.Method.POST,
                App.GET_NOTIFICATION, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                swipeRefreshLayout.setRefreshing(false);
                try {
                    Log.d("ayush", "got response");
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("notifications");
                    boolean error = jsonResponse.getBoolean("error");
                    if (!error) {
                        Log.d("ayush", "no error");
                        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                        db.deleteAllNotifications();
                        noNotificationTextView.setVisibility(View.INVISIBLE);
                        if (jsonArray.length() == 0)
                            noNotificationTextView.setVisibility(View.VISIBLE);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Notifications notifications = new Notifications();
                            notifications.setId(jsonObject.getInt("id"));
                            notifications.setType(jsonObject.getString("type"));
                            notifications.setEventId(jsonObject.getInt("event_id"));
                            notifications.setTitle(jsonObject.getString("title"));
                            notifications.setMessage(jsonObject.getString("message"));
                            notifications.setImageUrl(jsonObject.getString("image_url"));
                            notifications.setCreatedAt(jsonObject.getString("created_at"));
                            db.addNotification(notifications);
                        }
                        notificationsList = db.getAllNotifications();
                        mAdapter = new NotificationAdapter(notificationsList, NotificationsActivity.this);
                        recyclerView.setAdapter(mAdapter);
                    } else {
                        Log.d("ayush", "error");
                        noNotificationTextView.setVisibility(View.VISIBLE);
                        Toast.makeText(NotificationsActivity.this, jsonResponse.getString("error_msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DatabaseHandler db = new DatabaseHandler(NotificationsActivity.this);
                notificationsList = db.getAllNotifications();
                mAdapter = new NotificationAdapter(notificationsList, NotificationsActivity.this);
                recyclerView.setAdapter(mAdapter);
                swipeRefreshLayout.setRefreshing(false);
                Snackbar.make(findViewById(R.id.relativeLayout), "Internet connection error.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", userID);
                return params;
            }
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void preparenotificationdata() {
        String tag_string_req = "req_getNotification";
        swipeRefreshLayout.setRefreshing(true);

        StringRequest strReq = new StringRequest(Request.Method.POST,
                App.GET_NOTIFICATION, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                swipeRefreshLayout.setRefreshing(false);
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("notifications");
                    boolean error = jsonResponse.getBoolean("error");
                    if (!error) {
                        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                        db.deleteAllNotifications();
                        noNotificationTextView.setVisibility(View.INVISIBLE);
                        if (jsonArray.length() == 0)
                            noNotificationTextView.setVisibility(View.VISIBLE);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Notifications notifications = new Notifications();
                            notifications.setId(jsonObject.getInt("id"));
                            notifications.setType(jsonObject.getString("type"));
                            notifications.setEventId(jsonObject.getInt("event_id"));
                            notifications.setTitle(jsonObject.getString("title"));
                            notifications.setMessage(jsonObject.getString("message"));
                            notifications.setImageUrl(jsonObject.getString("image_url"));
                            notifications.setCreatedAt(jsonObject.getString("created_at"));
                            db.addNotification(notifications);
                        }
                        notificationsList = db.getAllNotifications();
                        mAdapter = new NotificationAdapter(notificationsList, NotificationsActivity.this);
                        recyclerView.setAdapter(mAdapter);
                    } else {
                        noNotificationTextView.setVisibility(View.VISIBLE);
                        Toast.makeText(NotificationsActivity.this, jsonResponse.getString("error_msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DatabaseHandler db = new DatabaseHandler(NotificationsActivity.this);
                notificationsList = db.getAllNotifications();
                mAdapter = new NotificationAdapter(notificationsList, NotificationsActivity.this);
                recyclerView.setAdapter(mAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    @Override
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
