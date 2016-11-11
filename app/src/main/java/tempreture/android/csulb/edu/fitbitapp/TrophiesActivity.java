package tempreture.android.csulb.edu.fitbitapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

/**
 * Created by Adrian on 10.11.2016.
 */

public class TrophiesActivity extends AppCompatActivity {
    private static int OLDSTEPS = 45435;
    private static int OLDDISTANCE = 3532;
    private static int OLDELEVATION = 189;
    private static int OLDCALORIES = 53991;
    private static int OLDTIMEACTIVE = 375;

    private DataContainer dc;
    private TextView textView_trophies_steps;
    private TextView textView_trophies_distance;
    private TextView textView_trophies_calories;
    private TextView textView_trophies_elevation;
    private TextView textView_trophies_timeActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophies);
        dc = DataContainer.getInstance();
        initViewElements();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.selectTabWithId(R.id.tab_trophies);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_challenges) {
                    Intent intend = new Intent(TrophiesActivity.this, ViewChallenges.class);
                    startActivity(intend);
                } else if (tabId == R.id.tab_dashboard) {
                    Intent intent = new Intent(TrophiesActivity.this, DashboardMainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * Initializes all gui elements which might be modified by code later on.
     */
    public void initViewElements() {
        this.textView_trophies_steps = (TextView) findViewById(R.id.textView_trophies_steps);
        this.textView_trophies_distance = (TextView) findViewById(R.id.textView_trophies_distance);
        this.textView_trophies_calories = (TextView) findViewById(R.id.textView_trophies_calories);
        this.textView_trophies_elevation = (TextView) findViewById(R.id.textView_trophies_elevation);
        this.textView_trophies_timeActive = (TextView) findViewById(R.id.textView_trophies_timeactive);
    }

    @Override
    public void onResume() {
        super.onResume();
        reloadDataToView();
    }

    private void reloadDataToView() {
        double steps = Double.parseDouble(dc.getTodaySteps()) + OLDSTEPS;
        textView_trophies_steps.setText(String.valueOf(steps));

        double calories = Double.parseDouble(dc.getTodayCalories()) + OLDCALORIES;
        textView_trophies_calories.setText(calories + " kcal");

        double distance = Double.parseDouble(dc.getTodayDistance()) + OLDDISTANCE;
        textView_trophies_distance.setText(distance + " Miles");

        double floors = Double.parseDouble(dc.getTodayElevation()) + OLDELEVATION;
        textView_trophies_elevation.setText(floors + " Floors");

        double timeactive = Double.parseDouble(dc.getTodayTimeActive()) + OLDTIMEACTIVE;
        textView_trophies_timeActive.setText(timeactive + " Minutes");
    }
}
