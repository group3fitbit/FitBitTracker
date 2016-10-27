package tempreture.android.csulb.edu.fitbitapp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Created by Adrian on 21.10.2016.
 */

public class Util {

    /**
     * Returns a list of 100 items with double values between 0 and max
     * @param max
     * @return
     */
    public static ArrayList<Double> getLoadItemlist(Double max) {
        ArrayList<Double> loadItems = new ArrayList<Double>();
        int numberOfSteps = 100;
        Double steps = max / numberOfSteps;

        for(int i = 1; i <= numberOfSteps; i++) {
            loadItems.add(steps * i);
        }
        return loadItems;
    }

    /**
     * Rounds a double value
     * @param value
     * @param places
     * @return
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
