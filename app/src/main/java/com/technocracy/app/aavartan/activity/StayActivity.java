package com.technocracy.app.aavartan.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.technocracy.app.aavartan.R;


public class StayActivity extends AppCompatActivity {

    private TextView stayTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stay);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Stay");
        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String stayText = "For the outstation students, we have arranged accommodation at Hotel Gagan Regency (Opp. AIIMS, Tatibandh Raipur, 1km from NIT Raipur) On a special tariff of Rs 300 per night per bed in a comfortable dormitory.\n\n\n" +
                "For any furthur query or details contact :\n\nSagar Wadekar : \n+91 9765464929, +91 9423528297\n\nHotel Gagan Regency is accommodation partner of Aavartan, NIT Raipur thus Organizing committee will not be responsible for any dispute whatsoever between the Hotel and Students";
        stayTextView = (TextView) findViewById(R.id.stay_text);
        stayTextView.setText(stayText);
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
