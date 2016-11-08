package tempreture.android.csulb.edu.fitbitapp;

import java.util.ArrayList;

/**
 * Created by Adrian on 04.11.2016.
 */

/**
 * This class holds all Data retrieved from FitBit Webapi. The Data from the last 31 days is stored in here.
 */
public class DataContainer {
    private static DataContainer dc;
    private ArrayList<DataEntry> steps;
    private ArrayList<DataEntry> distance;
    private ArrayList<DataEntry> elevation;
    private ArrayList<DataEntry> avrgHeartrate;
    private ArrayList<DataEntry> timeActive;
    private ArrayList<DataEntry> calories;

    public static synchronized DataContainer getInstance() {
        if (dc == null) {
            dc = new DataContainer();
        }
        return dc;
    }

    private DataContainer() {
        steps = new ArrayList<DataEntry>();
        distance = new ArrayList<DataEntry>();
        elevation = new ArrayList<DataEntry>();
        avrgHeartrate = new ArrayList<DataEntry>();
        timeActive = new ArrayList<DataEntry>();
        calories = new ArrayList<DataEntry>();
    }

    public String getTodaySteps() {
        return steps.get(0).getValue();
    }

    public String getTodayDistance() {
        return distance.get(0).getValue();
    }

    public String getTodayElevation() {
        return elevation.get(0).getValue();
    }

    public String getTodayTimeActive() {
        return timeActive.get(0).getValue();
    }

    public String getTodayCalories() {
        return calories.get(0).getValue();
    }

    public void addSteppsEntry(String date, String value) {
        steps.add(new DataEntry(date, value));
    }

    public ArrayList<DataEntry> getSteps() {
        return steps;
    }

    public void addDistanceEntry(String date, String value) {
        distance.add(new DataEntry(date, value));
    }

    public ArrayList<DataEntry> getDistance() {
        return distance;
    }

    public void addElevationsEntry(String date, String value) {
        elevation.add(new DataEntry(date, value));
    }

    public ArrayList<DataEntry> getElevation() {
        return elevation;
    }

    public void addAvrgHeartrateEntry(String date, String value) {
        avrgHeartrate.add(new DataEntry(date, value));
    }

    public ArrayList<DataEntry> getAvrgHeartrate() {
        return avrgHeartrate;
    }

    public void addTimeactiveEntry(String date, String value) {
        timeActive.add(new DataEntry(date, value));
    }

    public ArrayList<DataEntry> getTimeActive() {
        return timeActive;
    }

    public void addCaloriesEntry(String date, String value) {
        calories.add(new DataEntry(date, value));
    }

    public ArrayList<DataEntry> getCalories() {
        return calories;
    }
}
