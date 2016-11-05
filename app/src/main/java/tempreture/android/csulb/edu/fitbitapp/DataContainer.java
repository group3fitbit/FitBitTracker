package tempreture.android.csulb.edu.fitbitapp;

/**
 * Created by Adrian on 04.11.2016.
 */

public class DataContainer {
    private String date;
    private String value;

    public DataContainer() {
        this.date = "";
        this.value = "";
    }

    public DataContainer(String date, String value) {
        this.date = date;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDate(String date) {
        this.date = date;
    }
}