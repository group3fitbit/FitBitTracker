package tempreture.android.csulb.edu.fitbitapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Adrian on 21.10.2016.
 */

public class DashboardMainActivity extends AppCompatActivity {
    private DataContainer dc;
    private TextView textView_dashboard_steps;
    private TextView textView_dashboard_distance;
    private TextView textView_dashboard_calories;
    private TextView textView_dashboard_elevation;
    private TextView textView_dashboard_timeActive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_main);
        dc = DataContainer.getInstance();
        initViewElements();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.selectTabWithId(R.id.tab_dashboard);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if(tabId == R.id.tab_challenges) {
                    Intent intend = new Intent(DashboardMainActivity.this, ViewChallenges.class);
                    startActivity(intend);
                } else if (tabId == R.id.tab_startchallenge) {
                    Intent intent = new Intent(DashboardMainActivity.this, CreateChallengeActivity.class);
                    startActivity(intent);
                } else if (tabId == R.id.tab_trophies) {
                    Intent intent = new Intent(DashboardMainActivity.this, TrophiesActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * Initializes all gui elements which might be modified by code later on.
     */
    public void initViewElements() {
        this.textView_dashboard_steps = (TextView) findViewById(R.id.textView_dashboard_steps);
        this.textView_dashboard_distance = (TextView) findViewById(R.id.textView_dashboard_distance);
        this.textView_dashboard_calories = (TextView) findViewById(R.id.textView_dashboard_calories);
        this.textView_dashboard_elevation = (TextView) findViewById(R.id.textView_dashboard_elevation);
        this.textView_dashboard_timeActive = (TextView) findViewById(R.id.textView_dashboard_timeActive);
    }

    public void openDetailActivity(View v) {
        Intent intent = new Intent(getApplicationContext(), DashBoardDetailActivity.class);
        intent.putExtra("datatype", v.getId());
        startActivity(intent);
    }


    @Override
    public void onResume() {
        super.onResume();
        loadDataFromServer();
        reloadDataToView();
    }

    private void reloadDataToView() {
        textView_dashboard_steps.setText(dc.getTodaySteps());
        textView_dashboard_calories.setText(dc.getTodayCalories());
        textView_dashboard_distance.setText(dc.getTodayDistance() + " mi");
        textView_dashboard_elevation.setText(dc.getTodayElevation() + " Floors");
        textView_dashboard_timeActive.setText(dc.getTodayTimeActive() + " min");
    }

    /**
     * This Method loads all the data from the webservice
     */
    private void loadDataFromServer() {
        loadDataFromServer(DataType.STEPS);
        loadDataFromServer(DataType.DISTANCE);
        loadDataFromServer(DataType.ELEVATION);
        loadDataFromServer(DataType.TIMEACTIVE);
        loadDataFromServer(DataType.CALORIES);
    }

    private void loadDataFromServer(DataType dt) {
        int days = 13;
        // get calendar object of today - inputed number of days
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -days);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String date = df.format(c.getTime());

        String url;//= "https://api.fitbit.com/1/user/-/activities/steps/date/" + date + "/today.json";
        String jsonString;// = FitbitApi.getData(url, getAccess());
        JSONObject stepsObj;// = FitbitApi.convertStringToJson(jsonString);
        int objLen;

        try {
            switch (dt) {
                case STEPS:
                    url = "https://api.fitbit.com/1/user/-/activities/steps/date/" + date + "/today.json";
                    jsonString = FitbitApi.getData(url, getAccess());
                    stepsObj = FitbitApi.convertStringToJson(jsonString);
                    JSONArray stepsArray = stepsObj.getJSONArray("activities-steps");
                    objLen = stepsArray.length();
                    for (int i = objLen - 1; i >= 0; i--) {
                        stepsObj = stepsArray.getJSONObject(i);
                        dc.addSteppsEntry(stepsObj.getString("dateTime"), stepsObj.getString("value"));
                    }
                    break;
                case DISTANCE:
                    url = "https://api.fitbit.com/1/user/-/activities/distance/date/" + date + "/today.json";
                    jsonString = FitbitApi.getData(url, getAccess());
                    stepsObj = FitbitApi.convertStringToJson(jsonString);
                    JSONArray distanceArray = stepsObj.getJSONArray("activities-distance");
                    objLen = distanceArray.length();
                    for (int i = objLen - 1; i >= 0; i--) {
                        stepsObj = distanceArray.getJSONObject(i);
                        double value =Util.round(Double.parseDouble(stepsObj.getString("value")), 2);
                        dc.addDistanceEntry(stepsObj.getString("dateTime"), String.valueOf(value));
                    }
                    break;
                case AVRGHEARTRATE:
                    break;
                case MAXHEARTRATE:
                    break;
                case MINHEARTRATE:
                    break;
                case CALORIES:
                    url = "https://api.fitbit.com/1/user/-/activities/calories/date/" + date + "/today.json";
                    jsonString = FitbitApi.getData(url, getAccess());
                    stepsObj = FitbitApi.convertStringToJson(jsonString);
                    JSONArray caloriesArray = stepsObj.getJSONArray("activities-calories");
                    objLen = caloriesArray.length();
                    for (int i = objLen - 1; i >= 0; i--) {
                        stepsObj = caloriesArray.getJSONObject(i);
                        dc.addCaloriesEntry(stepsObj.getString("dateTime"), stepsObj.getString("value"));
                    }
                    break;
                case ELEVATION:
                    url = "https://api.fitbit.com/1/user/-/activities/elevation/date/" + date + "/today.json";
                    jsonString = FitbitApi.getData(url, getAccess());
                    stepsObj = FitbitApi.convertStringToJson(jsonString);
                    JSONArray elevationArray = stepsObj.getJSONArray("activities-elevation");
                    objLen = elevationArray.length();
                    for (int i = objLen - 1; i >= 0; i--) {
                        stepsObj = elevationArray.getJSONObject(i);
                        dc.addElevationsEntry(stepsObj.getString("dateTime"), stepsObj.getString("value"));
                    }
                    break;
                case TIMEACTIVE:
                    url = "https://api.fitbit.com/1/user/-/activities/minutesVeryActive/date/" + date + "/today.json";
                    jsonString = FitbitApi.getData(url, getAccess());
                    stepsObj = FitbitApi.convertStringToJson(jsonString);
                    JSONArray timeactiveArray = stepsObj.getJSONArray("activities-minutesVeryActive");
                    objLen = timeactiveArray.length();
                    for (int i = objLen - 1; i >= 0; i--) {
                        stepsObj = timeactiveArray.getJSONObject(i);
                        dc.addTimeactiveEntry(stepsObj.getString("dateTime"), stepsObj.getString("value"));
                    }
                    break;
                default:
                    return;
            }

        } catch (JSONException e) {
            Log.e("ERROR", e.getMessage(), e);
        } catch (NullPointerException e) {
            Log.e("ERROR", e.getMessage(), e);
        }
    }

    private String getAccess() {
        try {
            /*
            if (encrypted.equals("NULL")){
                return "NULL";
            }
            Timestamp d = new Timestamp(getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0).firstInstallTime);
            String encrypted = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("AUTH_TOKEN", "NULL");
            String decrypted = Encryptor.decrypt(d.toString(), encrypted);
            return decrypted;
            */
            if (PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("AUTH_TOKEN", "NULL").equals("NULL")) {
                return "NULL";
            }
            String dec = Encryptor.decrypt((new Timestamp(getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0).firstInstallTime)).toString(),
                    PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("AUTH_TOKEN", "NULL"));
            if (dec == null) {
                throw new PackageManager.NameNotFoundException();
            }
            return dec;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("ERROR", e.getMessage(), e);
            return "NULL";
        }
    }


}
