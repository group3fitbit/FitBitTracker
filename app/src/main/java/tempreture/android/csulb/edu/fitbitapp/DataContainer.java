package tempreture.android.csulb.edu.fitbitapp;

import java.util.ArrayList;

/**
 * Created by Adrian on 04.11.2016.
 */

public class DataContainer {
    ArrayList<DataEntry> dcSteps;
    ArrayList<DataEntry> dcDistance;
    //TODO add container for other datatypes


    public DataContainer() {
        //TODO make class singleton
        dcSteps = new ArrayList<DataEntry>();
        dcDistance = new ArrayList<DataEntry>();
    }

    public DataEntry getTodayStepsEntry() {
        return dcSteps.get(0);
    }

    public void addSteppsEntry(DataEntry e) {
        dcSteps.add(e);
        //TODO sort entries by date
    }

    public void addSteppsEntries(ArrayList<DataEntry> dc) {
        this.dcSteps.addAll(dc);
        //TODO sort entries by date
    }

    public ArrayList<DataEntry> getSteps() {
        return dcSteps;
    }

    //TODO add methods to add and get other datatypes
}
