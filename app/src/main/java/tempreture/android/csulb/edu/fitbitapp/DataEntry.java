package tempreture.android.csulb.edu.fitbitapp;

/**
 * Created by Adrian on 11/7/2016.
 */

public class DataEntry {
    private String date;
    private String value;

    public DataEntry(String d, String v) {
        this.date = d;
        this.value = v;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return date + " - " + value;
    }
}
