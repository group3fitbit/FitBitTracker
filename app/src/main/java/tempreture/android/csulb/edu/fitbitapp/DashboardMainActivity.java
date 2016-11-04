package tempreture.android.csulb.edu.fitbitapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Adrian on 21.10.2016.
 */

public class DashboardMainActivity extends AppCompatActivity {

    private TextView textView_dashboard_steps;
    private TextView textView_dashboard_distance;
    private TextView textView_dashboard_calories;
    private TextView textView_dashboard_avrgHeartrate;
    private TextView textView_dashboard_maxHeartrate;
    private TextView textView_dashboard_minHeartrate;
    private TextView textView_dashboard_elevation;
    private TextView textView_dashboard_timeActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_main);
        initViewElements();
    }

    /**
     * Initializes all gui elements which might be modified by code later on.
     */
    public void initViewElements() {
        this.textView_dashboard_steps = (TextView) findViewById(R.id.textView_dashboard_steps);
        this.textView_dashboard_distance = (TextView) findViewById(R.id.textView_dashboard_distance);
        this.textView_dashboard_calories = (TextView) findViewById(R.id.textView_dashboard_calories);
        this.textView_dashboard_avrgHeartrate = (TextView) findViewById(R.id.textView_dashboard_avrgHeartrate);
        this.textView_dashboard_maxHeartrate = (TextView) findViewById(R.id.textView_dashboard_maxHeartrate);
        this.textView_dashboard_minHeartrate = (TextView) findViewById(R.id.textView_dashboard_minHeartrate);
        this.textView_dashboard_elevation = (TextView) findViewById(R.id.textView_dashboard_elevation);
        this.textView_dashboard_timeActive = (TextView) findViewById(R.id.textView_dashboard_timeActive);

    }

    public void test(View v) {
        Intent intent = new Intent(getApplicationContext(), DashboardDetailActivity.class);
        startActivity(intent);
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//                ArrayList<Double> loadItemList_Steps = Util.getLoadItemlist(999.00);
//                for (Double d : loadItemList_Steps) {
//
//                    textView_dashboard_steps.setText(String.format("%.2f", d));
//                    try {
//                        Thread.sleep(15);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//    }
}
