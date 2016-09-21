package com.technocracy.app.aavartan.gallery;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;
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
import com.technocracy.app.aavartan.api.GalleryItem;
import com.technocracy.app.aavartan.helper.App;
import com.technocracy.app.aavartan.helper.AppController;
import com.technocracy.app.aavartan.helper.DatabaseHandler;
import com.technocracy.app.aavartan.helper.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private static final String TAG = GalleryActivity.class.getSimpleName();
    private ArrayList<GalleryItem> galleryImageList;
    private GalleryRecyclerViewAdapter galleryRecyclerViewAdapter;
    private ProgressDialog pDialog;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Gallery");
        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        db = new DatabaseHandler(getApplicationContext());
        pDialog = new ProgressDialog(this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        galleryImageList = db.getAllGalleryItems();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);
        int value = this.getResources().getConfiguration().orientation;
        if (Configuration.ORIENTATION_LANDSCAPE == value)
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, 1));
        else
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, 1));

        galleryRecyclerViewAdapter = new GalleryRecyclerViewAdapter(GalleryActivity.this, galleryImageList);
        recyclerView.setAdapter(galleryRecyclerViewAdapter);

        getImageURLs();
    }

    private void getImageURLs() {
        // Tag used to cancel the request
        String tag_string_req = "req_gallery";

        pDialog.setMessage("Loading");
        pDialog.show();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                App.GALLERY_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Updates Response: " + response.toString());
                //swipeRefreshLayout.setRefreshing(false);
                try {
                    pDialog.dismiss();
                    JSONObject jsonObject = new JSONObject(response);
                    // show dialog box for next question or home.
                    JSONArray galleryArray = jsonObject.getJSONArray("gallery");
                    db.deleteAllGalleryItems();
                    for (int i = 0; i < galleryArray.length(); i++) {
                        JSONObject galleryObject = galleryArray.getJSONObject(i);
                        GalleryItem galleryEntry = new GalleryItem(galleryObject.getInt("id"),
                                galleryObject.getString("title"), galleryObject.getDouble("ratio"),
                                galleryObject.getString("url"));
                        db.addGalleryItem(galleryEntry);
                    }
                    galleryImageList = db.getAllGalleryItems();
                    galleryRecyclerViewAdapter = new GalleryRecyclerViewAdapter(GalleryActivity.this, galleryImageList);
                    recyclerView.setAdapter(galleryRecyclerViewAdapter);
                } catch (JSONException e) {
                    // JSON error
                    pDialog.dismiss();
                    galleryImageList = db.getAllGalleryItems();
                    galleryRecyclerViewAdapter = new GalleryRecyclerViewAdapter(GalleryActivity.this, galleryImageList);
                    recyclerView.setAdapter(galleryRecyclerViewAdapter);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                Snackbar.make(findViewById(R.id.drawer_layout), "Internet Connection not present", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                galleryImageList = db.getAllGalleryItems();
                galleryRecyclerViewAdapter = new GalleryRecyclerViewAdapter(GalleryActivity.this, galleryImageList);
                recyclerView.setAdapter(galleryRecyclerViewAdapter);
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    class GalleryRecyclerViewAdapter extends RecyclerView.Adapter<GalleryRecyclerViewAdapter.GalleryViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private Context context;
        ArrayList<GalleryItem> galleryImageList;

        public GalleryRecyclerViewAdapter(Context context, ArrayList<GalleryItem> galleryImageList) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            this.context = context;
            this.galleryImageList = galleryImageList;
        }

        @Override
        public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_gallery, parent, false);
            return new GalleryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final GalleryViewHolder holder, final int position) {
            holder.boundGalleryItem = galleryImageList.get(position);

            int width = Math.round(App.getScreenWidth(context) - 2 * getResources().getDimensionPixelSize(R.dimen.gallery_image_horizontal_margin));
            int height = (int) Math.round(width * holder.boundGalleryItem.getRatio());
            height = height / 2;

            Picasso.with(context).load(holder.boundGalleryItem.getUrl())
                    .into(holder.galleryItemImageView);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, FullscreenActivity.class);
                    i.putExtra("url", holder.boundGalleryItem.getUrl());
                    i.putExtra("title", holder.boundGalleryItem.getTitle());
                    context.startActivity(i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return galleryImageList.size();
        }

        public class GalleryViewHolder extends RecyclerView.ViewHolder {

            public final View mView;
            public final ImageView galleryItemImageView;
            public GalleryItem boundGalleryItem;

            public GalleryViewHolder(View itemView) {
                super(itemView);
                mView = itemView;
                galleryItemImageView = (ImageView) itemView.findViewById(R.id.gallery_item_imageView);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbarbutton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_Login:
                SessionManager sessionManager = new SessionManager(getApplicationContext());
                boolean userLoggedIn = sessionManager.isLoggedIn();
                if (userLoggedIn) {
                    Intent intent = new Intent(GalleryActivity.this, UserActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(GalleryActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.action_notification:
                Intent intent = new Intent(GalleryActivity.this, NotificationsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
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
