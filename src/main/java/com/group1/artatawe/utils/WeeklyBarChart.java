package com.group1.artatawe.utils;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * This class generates a bar chart, displaying one's weekly sales performance for the past 8 weeks
 *
 * @author K Carew.
 * @version 20180315Th1440
 */
public class WeeklyBarChart {

    private static final int NUM_OF_WEEKS = 8;

    private static String[] dateWeek = {"Week 1", "Week 2", "Week 3", "Week 4", "Week 5", "Week 6", "Week 7", "Week 8"};
    private static int[] paintingSalesWk = new int[NUM_OF_WEEKS];
    private static int[] sculptureSalesWk = new int[NUM_OF_WEEKS];

    /**
     * This method generates a bar chart object, without a container/frame/stage, of one's sales performance
     * @return The bar chart object of a user's weekly sales performance
     */
    public static BarChart<String, Number> start() {


        /*
        //TEST VALUES
        setPaintingSalesWk(0, 20);
        setPaintingSalesWk(1, 15);
        setPaintingSalesWk(2, 13);
        setPaintingSalesWk(3, 18);
        setPaintingSalesWk(4, 16);
        setPaintingSalesWk(5, 21);
        setPaintingSalesWk(6, 17);
        setPaintingSalesWk(7, 18);
        setSculptureSalesWk(0, 15);
        setSculptureSalesWk(1, 12);
        setSculptureSalesWk(2, 8);
        setSculptureSalesWk(3, 11);
        setSculptureSalesWk(4, 12);
        setSculptureSalesWk(5, 10);
        setSculptureSalesWk(6, 14);
        setSculptureSalesWk(7, 11);
        // ^^ TEST VALUES ^^
        */



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

        /*
        VBox vbox = new VBox(weeklySalesChart);

        weeklyStage.setTitle("SALES PERFORMANCE CHART");
        Scene scene = new Scene(vbox);

        weeklyStage.setScene(scene);
        weeklyStage.setHeight(700);
        weeklyStage.setWidth(1000);

        weeklyStage.show();
        */
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
     * Method to return the week period of time, as a string, in the form "DD/MM - DD/MM".
     * @param index The week number; also the index of the array where the dateWeek String values are stored.
     * @return The week period of time "DD/MM - DD/MM", as a String.
     */
    public static String getDateWeek(int index) {
        return dateWeek[index];
    }

    /**
     * Method to return the number of paintings sold in the week number passed as a parameter.
     * @param index The week number; also the index of the array where the getPaintingSalesWk values are stored.
     * @return The number of paintings sold in the given week.
     */
    public static int getPaintingSalesWk(int index) {
        return paintingSalesWk[index];
    }

    /**
     * Method to return the number of sculptures sold in the week number passed as a parameter.
     * @param index The week number; also the index of the array where the getSculptureSalesWk values are stored.
     * @return The number of sculptures sold in the given week.
     */
    public static int getSculptureSalesWk(int index) {
        return sculptureSalesWk[index];
    }


    public static void setPaintingSalesWk(int index, int paintingSales) {
        paintingSalesWk[index] = paintingSales;
    }

    public static void setSculptureSalesWk(int index, int sculptureSales) {
        sculptureSalesWk[index] = sculptureSales;
    }

/*
    public static void main(String[] args){
        launch();
    }
*/
}