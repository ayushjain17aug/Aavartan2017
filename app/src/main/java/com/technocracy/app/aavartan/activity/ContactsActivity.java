package com.technocracy.app.aavartan.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.helper.App;
import com.technocracy.app.aavartan.helper.AppController;
import com.technocracy.app.aavartan.helper.ConnectivityReceiver;
import com.technocracy.app.aavartan.helper.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactsActivity extends AppCompatActivity {

    private GridView contactsGridView;
    private static final String TAG = ContactsActivity.class.getSimpleName();
    private ArrayList<HashMap<String, String>> contactList;
    private ContactsAdapter contactsAdapter;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Contacts");
        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        contactsGridView = (GridView) findViewById(R.id.contacts_gridView);
        contactList = new ArrayList<HashMap<String, String>>();
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        getContacts();
    }

    private void getContacts() {
        // Tag used to cancel the request
        String tag_string_req = "req_contacts";
        if (!ConnectivityReceiver.isConnected())
            Snackbar.make(findViewById(R.id.relativeLayout), "Please connect to Internet!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        else {
            pDialog.setMessage("Loading...");
            showDialog();


            StringRequest strReq = new StringRequest(Request.Method.GET,
                    App.CONTACTS_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "Contacts Response: " + response.toString());
                    hideDialog();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray contacts = jsonObject.getJSONArray("contacts");
                        contactList.clear();
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject updatesObject = contacts.getJSONObject(i);
                            HashMap<String, String> contactsEntry = new HashMap<>();
                            contactsEntry.put("name", updatesObject.getString("name"));
                            contactsEntry.put("designation", updatesObject.getString("designation"));
                            contactsEntry.put("image_url", updatesObject.getString("image_url"));
                            contactsEntry.put("fb_url", updatesObject.getString("fb_url"));
                            contactList.add(i, contactsEntry);
                        }
                        contactsAdapter = new ContactsAdapter(contactList);
                        contactsGridView.setAdapter(contactsAdapter);
                    } catch (JSONException e) {
                        // JSON error
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Contacts Request Error: " + error.getMessage());
                    Snackbar.make(findViewById(R.id.relativeLayout), "Please try again!",Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    hideDialog();
                }
            });
            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
        }
    }
    private class ContactsAdapter extends BaseAdapter {

        private ArrayList<HashMap<String, String>> contactList;
        private LayoutInflater inflater = null;

        public ContactsAdapter(ArrayList<HashMap<String, String>> contactList) {
            this.contactList = contactList;
            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return contactList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public class Holder {
            ImageView contactImageView;
            TextView contactName;
            TextView contactDesignation;
            ProgressBar progressBar;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Holder holder = new Holder();
            View rowView;

            rowView = inflater.inflate(R.layout.item_contacts, null);
            holder.contactImageView = (ImageView) rowView.findViewById(R.id.contact_image);
            holder.contactName = (TextView) rowView.findViewById(R.id.contact_name);
            holder.contactDesignation = (TextView) rowView.findViewById(R.id.contact_designation);
            holder.progressBar = (ProgressBar) rowView.findViewById(R.id.progressBar);
            holder.contactName.setText(contactList.get(position).get("name"));
            holder.contactDesignation.setText(contactList.get(position).get("designation"));

            int width = Math.round(App.getScreenWidth(ContactsActivity.this) / 2 - 10);
            int height = Math.round(width * 1);
            height = height / 2;
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            paint.setColor(ContextCompat.getColor(ContactsActivity.this, R.color.colorPrimaryDark));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawPaint(paint);
            holder.contactImageView.setImageBitmap(bitmap);
            App.showProgressBar(holder.progressBar);
            Picasso.with(ContactsActivity.this).load(contactList.get(position).get("image_url")).into(holder.contactImageView, new Callback() {
                @Override
                public void onSuccess() {
                    App.hideProgressBar(holder.progressBar);
                }

                @Override
                public void onError() {
                    App.hideProgressBar(holder.progressBar);
                }
            });

            rowView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(contactList.get(position).get("fb_url")));
                    startActivity(i);
                }
            });

            return rowView;
        }
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbarbutton, menu);
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
                    Intent intent = new Intent(ContactsActivity.this, UserActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ContactsActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.action_notification:
                Intent intent = new Intent(ContactsActivity.this, NotificationsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}