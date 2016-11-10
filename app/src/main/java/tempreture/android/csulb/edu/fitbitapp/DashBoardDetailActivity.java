package tempreture.android.csulb.edu.fitbitapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

/**
 * Created by Adrian on 03.11.2016.
 */

public class DashBoardDetailActivity extends AppCompatActivity {
    XYMultipleSeriesDataset xyMultipleSeriesDataset = new XYMultipleSeriesDataset();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_detail);
        int callingId = (int) getIntent().getSerializableExtra("datatype");

        if (callingId == R.id.dashbordMain_StepsCard) {
            drawChart(DataType.STEPS);
        } else if (callingId == R.id.dashbordMain_DistanceCard) {
            drawChart(DataType.DISTANCE);
        } else if (callingId == R.id.dashbordMain_CaloriesCard) {
            drawChart(DataType.CALORIES);
        } else if (callingId == R.id.dashbordMain_TimeActiveCard) {
            drawChart(DataType.TIMEACTIVE);
        } else if (callingId == R.id.dashbordMain_ElevationCard) {
            drawChart(DataType.ELEVATION);
        }
//
//        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
//        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
//            @Override
//            public void onTabSelected(@IdRes int tabId) {
//                Toast.makeText(getApplicationContext(), "Tab selected", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
//            @Override
//            public void onTabReSelected(@IdRes int tabId) {
//                Intent intent = new Intent(getApplicationContext(), DashboardMainActivity.class);
//                startActivity(intent);
//            }
//        });
    }


//    private String[] mMonth = new String[]{
//            "Jan", "Feb", "Mar", "Apr", "May", "Jun",
//            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    private void drawChart(DataType dt) {
        DataContainer dc = DataContainer.getInstance();
        int[] x_values = {13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
//        int[] x_values = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        // Creating an  XYSeries for Expense
        XYSeries expenseSeries = new XYSeries("Expense");
        // Adding data to Expense Series
        for (int i = x_values.length - 1; i >= 0; i--) {
            switch(dt)  {
                case STEPS:
                    expenseSeries.add(x_values[i], Double.parseDouble(dc.getSteps().get(i).getValue()));
                    break;
                case DISTANCE:
                    expenseSeries.add(x_values[i], Double.parseDouble(dc.getDistance().get(i).getValue()));
                    break;
                case AVRGHEARTRATE:
                    break;
                case MAXHEARTRATE:
                    break;
                case MINHEARTRATE:
                    break;
                case CALORIES:
                    expenseSeries.add(x_values[i], Double.parseDouble(dc.getCalories().get(i).getValue()));
                    break;
                case ELEVATION:
                    expenseSeries.add(x_values[i], Double.parseDouble(dc.getElevation().get(i).getValue()));
                    break;
                case TIMEACTIVE:
                    expenseSeries.add(x_values[i], Double.parseDouble(dc.getTimeActive().get(i).getValue()));
                    break;
                default:
                    return;
            }
        }


        // Creating a dataset to hold each series

        // Adding Expense Series to the dataset
        xyMultipleSeriesDataset.addSeries(expenseSeries);
        // Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer renderer = new XYSeriesRenderer();
        renderer.setColor(Color.argb(1000, 38, 132, 150));
        renderer.setPointStyle(PointStyle.CIRCLE);
        renderer.setFillPoints(true);
        renderer.setLineWidth(3);
        renderer.setDisplayChartValues(true);
        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setShowGrid(true);
        multiRenderer.setYLabelsAngle(270);
        multiRenderer.setYLabelsAlign(Paint.Align.LEFT);
//        multiRenderer.setAxisTitleTextSize(20);
        multiRenderer.setShowLegend(false);
        multiRenderer.setZoomButtonsVisible(false);
        multiRenderer.setChartTitleTextSize(70);
        multiRenderer.setZoomButtonsVisible(false);
        //Disable/Enable Axis scrolling
        multiRenderer.setPanEnabled(true, true);
        multiRenderer.setYAxisMin(0.0);
        multiRenderer.setMarginsColor(Color.argb(1000, 255, 255, 255));
        multiRenderer.setLabelsTextSize(40);
//        multiRenderer.setZoomEnabled(false, false);
        multiRenderer.setShowGridX(false);
//        multiRenderer.setBarSpacing(1);
        int count = 1;
        for (int i = 0; i < x_values.length - 1; i++) {
            if (count == 1) {
                multiRenderer.addXTextLabel(i, dc.getSteps().get(13 - i).getDate().substring(5));
                count = 0;
            } else {
                count++;
            }
        }
        switch(dt)  {
            case STEPS:
                multiRenderer.setChartTitle("Steps");
                break;
            case DISTANCE:
                multiRenderer.setChartTitle("Distance");
                break;
            case AVRGHEARTRATE:
                break;
            case MAXHEARTRATE:
                break;
            case MINHEARTRATE:
                break;
            case CALORIES:
                multiRenderer.setChartTitle("Calories");
                break;
            case ELEVATION:
                multiRenderer.setChartTitle("Elevation");
                break;
            case TIMEACTIVE:
                multiRenderer.setChartTitle("Time Active");
                break;
            default:
                return;
        }
        // Adding expenseRenderer to multipleRenderer
        multiRenderer.addSeriesRenderer(renderer);
        //        Sets the scrolling limits xMin, xMax, yMin, yMax
        multiRenderer.setPanLimits(new double[]{-1, multiRenderer.getXAxisMax() + 1, 0, multiRenderer.getYAxisMax() + 200});
        // Getting a reference to LinearLayout of the MainActivity Layout
        LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart);
        // Creating a Line Chart
        GraphicalView chart = ChartFactory.getLineChartView(getBaseContext(), xyMultipleSeriesDataset, multiRenderer);
        // Adding the Line Chart to the LinearLayout
        chartContainer.addView(chart);
    }

}

