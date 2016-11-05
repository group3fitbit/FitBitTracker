package tempreture.android.csulb.edu.fitbitapp;

import java.util.ArrayList;
import java.util.HashMap;

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

    public static DataContainer getInstance() {
        if(dc == null) {
            dc = new DataContainer();
        }
        return dc;
    }

    private DataContainer() {
        steps = new HashMap<String, String>();
    }

    public String getTodaySteps() {
        return ""; //TODO get today item should be maybe last
    }

    public void addSteppsEntry(String date, String value) {
        steps.put(date, value);
    }

    public HashMap<String, String> getSteps() {
        return steps;
    }

    //TODO add methods to add and get other datatypes
}
