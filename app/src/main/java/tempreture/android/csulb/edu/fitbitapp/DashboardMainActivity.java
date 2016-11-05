package tempreture.android.csulb.edu.fitbitapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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
        getData();
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
        Intent intent = new Intent(getApplicationContext(), DashBoardDetailActivity.class);
        startActivity(intent);
    }

    private void getData() {
        ArrayList<DataContainer> dc =  this.getStepsTime(31);
        textView_dashboard_steps.setText(dc.get(8).getValue());
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

    public ArrayList<DataContainer> getStepsTime(int days){
        ArrayList<DataContainer> fitbitData = new ArrayList<DataContainer>();
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
            JSONArray stepsArray = stepsObj.getJSONArray("activities-steps");
            objLen = stepsArray.length();
            List<String> consoleList = new ArrayList<>();
            for (int i=objLen-1; i>=0; i--){
                stepsObj = stepsArray.getJSONObject(i);
                fitbitData.add(new DataContainer(stepsObj.getString("dateTime"), stepsObj.getString("value")));
            }
        }
        catch (JSONException e){
            Log.e("ERROR", e.getMessage(), e);
        }
        catch(NullPointerException e){
            Log.e("ERROR", e.getMessage(), e);
        }
        return fitbitData;
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


}
