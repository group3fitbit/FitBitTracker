package tempreture.android.csulb.edu.fitbitapp;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.text.NumberFormat;

/**
 * Created by Adrian on 03.11.2016.
 */

public class DashboardDetailActivity extends AppCompatActivity {
    XYMultipleSeriesDataset xyMultipleSeriesDataset = new XYMultipleSeriesDataset();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_detail);
//        createGraph();
        drawChart();
    }


    private void createGraph() {
        XYSeries xySeries = new XYSeries("Sample Data!");
        XYMultipleSeriesDataset series = new XYMultipleSeriesDataset();

        for(int i = 0; i < 10; i++) {
            xySeries.addAnnotation("day " + i, i, i);
        }
        series.addSeries(xySeries);

        // Now we create the renderer
        XYSeriesRenderer renderer = new XYSeriesRenderer();
        renderer.setLineWidth(2);
        renderer.setColor(Color.RED);
        // Include low and max value
        renderer.setDisplayBoundingPoints(true);
        // we add point markers
        renderer.setPointStyle(PointStyle.CIRCLE);
        renderer.setPointStrokeWidth(3);

        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        mRenderer.addSeriesRenderer(renderer);

        // We want to avoid black border
        mRenderer.setMarginsColor(Color.argb(0x00, 0xff, 0x00, 0x00)); // transparent margins
// Disable Pan on two axis
        mRenderer.setPanEnabled(false, false);
        mRenderer.setYAxisMax(35);
        mRenderer.setYAxisMin(0);
        mRenderer.setShowGrid(true); // we show the grid

        GraphicalView chartView = ChartFactory.getBarChartView(this, series, mRenderer, BarChart.Type.DEFAULT);

        LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart);
        chartContainer.addView(chartView,0);
    }

    private String[] mMonth = new String[] {
            "Jan", "Feb" , "Mar", "Apr", "May", "Jun",
            "Jul", "Aug" , "Sep", "Oct", "Nov", "Dec"};

    private void drawChart(){
        int[] x_values = { 0,1,2,3,4,5,6,7};
        int[] y_values = { 1000,1500,1700,2000,1600,3000,2200,900};
        // Creating an  XYSeries for Expense
        XYSeries expenseSeries = new XYSeries("Expense");
        // Adding data to Expense Series
        for(int i=0;i<x_values.length;i++){
            expenseSeries.add(x_values[i], y_values[i]);
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
        multiRenderer.setAxisTitleTextSize(20);
        multiRenderer.setShowLegend(false);
        multiRenderer.setZoomButtonsVisible(false);
        multiRenderer.setChartTitle("Steps");
        multiRenderer.setChartTitleTextSize(36);
        multiRenderer.setZoomButtonsVisible(false);
        //Disable Y-Axis scrolling
        multiRenderer.setPanEnabled(true, true);
        multiRenderer.setYAxisMin(0.0);
        //Sets the scrolling limits xMin, xMax, yMin, yMax
        multiRenderer.setPanLimits(new double[] {-1, 9, 0, 3100});
        multiRenderer.setMarginsColor(Color.argb(1000, 255, 255, 255));
        multiRenderer.setLabelsTextSize(20);
        multiRenderer.setZoomEnabled(false, false);
        multiRenderer.setBarSpacing(1);
        for(int i=0;i<x_values.length;i++){
            multiRenderer.addXTextLabel(i, mMonth[i]);
        }
        // Adding expenseRenderer to multipleRenderer
        multiRenderer.addSeriesRenderer(renderer);
        // Getting a reference to LinearLayout of the MainActivity Layout
        LinearLayout chartContainer = (LinearLayout) findViewById(R.id.chart);
        // Creating a Line Chart
        GraphicalView chart = ChartFactory.getLineChartView(getBaseContext(), xyMultipleSeriesDataset, multiRenderer);
        // Adding the Line Chart to the LinearLayout
        chartContainer.addView(chart);
    }


}
