package tempreture.android.csulb.edu.fitbitapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Adrian on 04.11.2016.
 */

/**
 * This class holds all Data retrieved from FitBit Webapi. The Data from the last 31 days is stored in here.
 */
public class DataContainer {
    private static DataContainer dc;
    HashMap<String, String> steps;
    HashMap<String, String> distance;
    HashMap<String, String> elevation;
    HashMap<String, String> avrgHeartrate; //TODO have to take a look into it
    HashMap<String, String> timeActive;
    HashMap<String, String> calories;

    public static synchronized DataContainer getInstance() {
        if (dc == null) {
            dc = new DataContainer();
        }
        return dc;
    }

    private DataContainer() {
        steps = new HashMap<String, String>();
        distance = new HashMap<String, String>();
        elevation = new HashMap<String, String>();
        avrgHeartrate = new HashMap<String, String>();
        timeActive = new HashMap<String, String>();
        calories = new HashMap<String, String>();
    }

    public String getTodaySteps() {
        Map.Entry newestEntry;
        for (Map.Entry<String, String> entry : steps.entrySet()) {

        }
        return "";
    }

    public void addSteppsEntry(String date, String value) {
        steps.put(date, value);
    }

    public HashMap<String, String> getSteps() {
        return steps;
    }

    public void addDistanceEntry(String date, String value) {
        distance.put(date, value);
    }

    public HashMap<String, String> getDistance() {
        return distance;
    }

    public void addElevationsEntry(String date, String value) {
        elevation.put(date, value);
    }

    public HashMap<String, String> getElevation() {
        return elevation;
    }

    public void addAvrgHeartrateEntry(String date, String value) {
        avrgHeartrate.put(date, value);
    }

    public HashMap<String, String> getAvrgHeartrate() {
        return avrgHeartrate;
    }

    public void addTimeactiveEntry(String date, String value) {
        timeActive.put(date, value);
    }

    public HashMap<String, String> getTimeActive() {
        return timeActive;
    }

    public void addCaloriesEntry(String date, String value) {
        calories.put(date, value);
    }

    public HashMap<String, String> getCalories() {
        return calories;
    }
}
