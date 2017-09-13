package com.technocracy.app.aavartan.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.technocracy.app.aavartan.Event.View.EventListActivity;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.helper.BottomNavigationViewHelper;

public class TimingActivity extends AppCompatActivity {

    private static final int ACTIVITY_NUM =4;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timing);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("SCHEDULE");
        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btn1:
                        intent = new Intent(TimingActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btn2:
                        intent = new Intent(TimingActivity.this, EventListActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btn3:
                        intent = new Intent(TimingActivity.this,NavActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btn4:
                        intent = new Intent(TimingActivity.this, AccountActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btn5:
                        intent = new Intent(TimingActivity.this, TimingActivity.class);
                        startActivity(intent);
                        break;
                }
              //  updateNavigationBarState(bottomNavigationView,item.getItemId());
                return true;
            }
        });

          Menu menu=bottomNavigationView.getMenu();
         MenuItem menuItem =menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
  /*  private void updateNavigationBarState(BottomNavigationView bottomNavigationView,int actionId){
        Menu menu = bottomNavigationView.getMenu();
        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            item.setChecked(item.getItemId() == actionId);
        }
    }*/
    @Override
    public void onBackPressed()
    {
        Intent intent1=new Intent(TimingActivity.this,MainActivity.class);
        startActivity(intent1);
    }
}
