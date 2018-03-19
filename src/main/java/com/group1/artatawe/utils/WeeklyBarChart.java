package com.group1.artatawe.utils;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * This class generates a bar chart, displaying one's weekly sales performance for the past 8 weeks.
 *
 * @author K Carew.
 * @version 20180315Th1830.
 */
public class WeeklyBarChart {

    private static final int NUM_OF_WEEKS = 8;

    private static String[] dateWeek = {"Week 1", "Week 2", "Week 3", "Week 4", "Week 5", "Week 6", "Week 7", "Week 8"};
    private static int[] paintingSalesWk = new int[NUM_OF_WEEKS];
    private static int[] sculptureSalesWk = new int[NUM_OF_WEEKS];

    /**
     * This method generates a bar chart object, without a container/frame/stage, of one's sales performance.
     * @return The bar chart object of a user's weekly sales performance.
     */
    public static BarChart<String, Number> start() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Week");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of Sales");

        // Create a BarChart
        BarChart<String, Number> weeklySalesChart = new BarChart<>(xAxis, yAxis);

        // Series 1 - Painting Sales
        XYChart.Series<String, Number> weeklyPaintingSales = new XYChart.Series<>();
        weeklyPaintingSales.setName("Paintings");

        for (int i = (getNumOfWeeks()-1); i >= 0; i--) {
            weeklyPaintingSales.getData().add(new XYChart.Data<>(getDateWeek(i), getPaintingSalesWk(i)));
        }


        // Series 2 - Sculpture Sales
        XYChart.Series<String, Number> weeklySculptureSales = new XYChart.Series<>();
        weeklySculptureSales.setName("Sculptures");

        for (int i = (getNumOfWeeks()-1); i >= 0; i--) {
            weeklySculptureSales.getData().add(new XYChart.Data<>(getDateWeek(i), getSculptureSalesWk(i)));
        }


        // Series 3 - Combined Sales
        XYChart.Series<String, Number> weeklyTotalArtworkSales = new XYChart.Series<>();
        weeklyTotalArtworkSales.setName("Total Artworks");

        for (int i = (getNumOfWeeks()-1); i >= 0; i--) {
            weeklyTotalArtworkSales.getData().add(new XYChart.Data<>(getDateWeek(i), (getPaintingSalesWk(i) + getSculptureSalesWk(i))));
        }


        // Add Series to BarChart.
        weeklySalesChart.getData().add(weeklyPaintingSales);
        weeklySalesChart.getData().add(weeklySculptureSales);
        weeklySalesChart.getData().add(weeklyTotalArtworkSales);

        weeklySalesChart.setTitle("Your Weekly Sales Performance");

        return weeklySalesChart;
    }

    /**
     * Method to return the number of weeks being analysed.
     * @return The number of weeks being analysed.
     */
    public static int getNumOfWeeks() {
        return NUM_OF_WEEKS;
    }

    /**
     * Method to return the week period of time, as a string.
     * @param index The week number; also the index of the array where the dateWeek String values are stored.
     * @return The week period of time, as a String.
     */
    public static String getDateWeek(int index) {
        return dateWeek[index];
    }

    /**
     * Method to return the number of paintings sold in the week number passed as a parameter.
     * @param index The week number; also the index of the array where the PaintingSalesWk values are stored.
     * @return The number of paintings sold in the given week.
     */
    public static int getPaintingSalesWk(int index) {
        return paintingSalesWk[index];
    }

    /**
     * Method to return the number of sculptures sold in the week number passed as a parameter.
     * @param index The week number; also the index of the array where the SculptureSalesWk values are stored.
     * @return The number of sculptures sold in the given week.
     */
    public static int getSculptureSalesWk(int index) {
        return sculptureSalesWk[index];
    }

    /**
     * Method to set the number of paintings sold in the given week.
     * @param index The week number; also the index of the array where the PaintingSalesWk values are stored.
     * @param paintingSales The number of paintings sold in the given week.
     */
    public static void setPaintingSalesWk(int index, int paintingSales) {
        paintingSalesWk[index] = paintingSales;
    }

    /**
     * Method to set the number of sculptures sold in the given week.
     * @param index The week number; also the index of the array where the sculptureSalesWk values are stored.
     * @param sculptureSales The number of sculptures sold in the given week.
     */
    public static void setSculptureSalesWk(int index, int sculptureSales) {
        sculptureSalesWk[index] = sculptureSales;
    }

    /**
     * Method to reset the counters for sales to 0, allowing the graph data to be generated again
     */
    public static void reset() {
        paintingSalesWk = new int[NUM_OF_WEEKS];
        sculptureSalesWk = new int[NUM_OF_WEEKS];
    }
}