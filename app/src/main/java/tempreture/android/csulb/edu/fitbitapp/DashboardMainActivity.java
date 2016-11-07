package tempreture.android.csulb.edu.fitbitapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Adrian on 21.10.2016.
 */

public class DashboardMainActivity extends AppCompatActivity {
    private DataContainer dc;
    private TextView textView_dashboard_steps;
    private TextView textView_dashboard_distance;
    private TextView textView_dashboard_calories;
    private TextView textView_dashboard_avrgHeartrate;
    private TextView textView_dashboard_maxHeartrate;
    private TextView textView_dashboard_minHeartrate;
    private TextView textView_dashboard_elevation;
    private TextView textView_dashboard_timeActive;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_main);
        dc =  DataContainer.getInstance();
        initViewElements();

/* start toolbar----------------------------------------------------------------*/
        toolbar = (Toolbar) findViewById(R.id.dashboard_toolbar);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setTitle("DASHBOARD");
        toolbar.setTitleTextColor(Color.WHITE);
/* end toolbar --------------------------------------------------------------------*/
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

    public void openDetailActivity(View v) {
        //TODO the detail activity should be calling the correct detail activity
        Intent intent = new Intent(getApplicationContext(), DashBoardDetailActivity.class);
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

    }

    /**
     * This Method loads all the data from the webservice
     */
    private void loadDataFromServer() {
        loadDataFromServer(DataType.STEPS);
        loadDataFromServer(DataType.DISTANCE);
        loadDataFromServer(DataType.AVRGHEARTRATE);
        loadDataFromServer(DataType.MAXHEARTRATE);
        loadDataFromServer(DataType.MINHEARTRATE);
        loadDataFromServer(DataType.ELEVATION);
        loadDataFromServer(DataType.TIMEACTIVE);
        loadDataFromServer(DataType.CALORIES);
    }

    private void loadDataFromServer(DataType dt){
        int days = 31;
        // get calendar object of today - inputed number of days
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -days);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String date = df.format(c.getTime());

        String url = "https://api.fitbit.com/1/user/-/activities/steps/date/" + date + "/today.json";
        String jsonString = FitbitApi.getData(url, getAccess());
        JSONObject stepsObj = FitbitApi.convertStringToJson(jsonString);
        int objLen;

        try {
            switch(dt)  {
                case STEPS:
                    JSONArray stepsArray = stepsObj.getJSONArray("activities-steps");
                    objLen = stepsArray.length();

                    for (int i=objLen-1; i>=0; i--){
                        stepsObj = stepsArray.getJSONObject(i);
                        dc.addSteppsEntry(stepsObj.getString("dateTime"), stepsObj.getString("value"));
                    }
                    break;
                case DISTANCE:
                    break;
                case AVRGHEARTRATE:
                    break;
                case MAXHEARTRATE:
                    break;
                case MINHEARTRATE:
                    break;
                case CALORIES:
                    break;
                case ELEVATION:
                    break;
                case TIMEACTIVE:
                    break;
                default:
                    return;
            }

        }
        catch (JSONException e){
            Log.e("ERROR", e.getMessage(), e);
        }
        catch(NullPointerException e){
            Log.e("ERROR", e.getMessage(), e);
        }
    }

    private String getAccess(){
        try{
            /*
            if (encrypted.equals("NULL")){
                return "NULL";
            }
            Timestamp d = new Timestamp(getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0).firstInstallTime);
            String encrypted = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("AUTH_TOKEN", "NULL");
            String decrypted = Encryptor.decrypt(d.toString(), encrypted);
            return decrypted;
            */
            if (PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("AUTH_TOKEN", "NULL").equals("NULL")){
                return "NULL";
            }
            String dec = Encryptor.decrypt((new Timestamp(getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0).firstInstallTime)).toString(),
                    PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("AUTH_TOKEN", "NULL"));
            if (dec == null){
                throw new PackageManager.NameNotFoundException();
            }
            return dec;
        }
        catch (PackageManager.NameNotFoundException e){
            Log.e("ERROR", e.getMessage(), e);
            return "NULL";
        }
    }
    /* start menu ----------------------------------------------------------------- */
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.viewmenu, menu);
        menu.findItem(R.id.dashboard).setVisible(false);//disable dashboard
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.dashboard: //(id == R.id.start_challenge) {
                Toast.makeText(DashboardMainActivity.this,"dashboard",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.start_challenge: //(id == R.id.start_challenge) {
                Toast.makeText(DashboardMainActivity.this,"start challenge",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.challenge_progress:// (id == R.id.challenge_progress) {
                Toast.makeText(DashboardMainActivity.this,"challenge progress",Toast.LENGTH_SHORT).show();
                 //setContentView.setView(R.layout.user_progress_list);
                 Intent i = new Intent(DashboardMainActivity.this, UserProgress.class);
                 startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
/* end menu -----------------------------------------------------------*/

}
