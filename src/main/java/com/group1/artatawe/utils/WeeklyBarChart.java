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

    private static int numOfWeeks;
    //Weeks are from Monday to Sunday

    private static String[] dateWeek;
    private static int[] paintingSalesWk;
    private static int[] sculptureSalesWk;

    /**
     * This method generates a bar chart object, without a container/frame/stage, of one's sales performance
     * @return The bar chart object of a user's weekly sales performance
     */
    public static BarChart<String, Number> start() {

        setNumOfWeeks(8);

        dateWeek = new String[numOfWeeks+1];
        // Not using [0] to avoid confusion.

        paintingSalesWk = new int[numOfWeeks+1];
        // Not using [0] to avoid confusion.

        sculptureSalesWk = new int[numOfWeeks+1];
        // Not using [0] to avoid confusion.


        //TEST VALUES
        setDateWeek(1, "01/01 - 07/01");
        setDateWeek(2, "08/01 - 14/01");
        setDateWeek(3, "15/01 - 21/01");
        setDateWeek(4, "22/01 - 28/01");
        setDateWeek(5, "29/01 - 04/02");
        setDateWeek(6, "05/02 - 11/02");
        setDateWeek(7, "12/02 - 18/02");
        setDateWeek(8, "19/02 - 25/02");
        setPaintingSalesWk(1, 20);
        setPaintingSalesWk(2, 15);
        setPaintingSalesWk(3, 13);
        setPaintingSalesWk(4, 18);
        setPaintingSalesWk(5, 16);
        setPaintingSalesWk(6, 21);
        setPaintingSalesWk(7, 17);
        setPaintingSalesWk(8, 18);
        setSculptureSalesWk(1, 15);
        setSculptureSalesWk(2, 12);
        setSculptureSalesWk(3, 8);
        setSculptureSalesWk(4, 11);
        setSculptureSalesWk(5, 12);
        setSculptureSalesWk(6, 10);
        setSculptureSalesWk(7, 14);
        setSculptureSalesWk(8, 11);
        // ^^ TEST VALUES ^^

        // Could probably use a for loop to set the values



        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Week");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of Sales");

        // Create a BarChart
        BarChart<String, Number> weeklySalesChart = new BarChart<>(xAxis, yAxis);

        // Series 1 - Painting Sales
        XYChart.Series<String, Number> weeklyPaintingSales = new XYChart.Series<>();
        weeklyPaintingSales.setName("Paintings");

        for (int i=1; i <= getNumOfWeeks(); i++) {
            weeklyPaintingSales.getData().add(new XYChart.Data<>(getDateWeek(i), getPaintingSalesWk(i)));
        }


        // Series 2 - Sculpture Sales
        XYChart.Series<String, Number> weeklySculptureSales = new XYChart.Series<>();
        weeklySculptureSales.setName("Sculptures");

        for (int i=1; i <= getNumOfWeeks(); i++) {
            weeklySculptureSales.getData().add(new XYChart.Data<>(getDateWeek(i), getSculptureSalesWk(i)));
        }


        // Series 3 - Combined Sales
        XYChart.Series<String, Number> weeklyTotalArtworkSales = new XYChart.Series<>();
        weeklyTotalArtworkSales.setName("Total Artworks");

        for (int i=1; i <= getNumOfWeeks(); i++) {
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
    private static int getNumOfWeeks() {
        return numOfWeeks;
    }

    /**
     * Method to return the week period of time, as a string, in the form "DD/MM - DD/MM".
     * @param index The week number; also the index of the array where the dateWeek String values are stored.
     * @return The week period of time "DD/MM - DD/MM", as a String.
     */
    private static String getDateWeek(int index) {
        return dateWeek[index];
    }

    /**
     * Method to return the number of paintings sold in the week number passed as a parameter.
     * @param index The week number; also the index of the array where the getPaintingSalesWk values are stored.
     * @return The number of paintings sold in the given week.
     */
    private static int getPaintingSalesWk(int index) {
        return paintingSalesWk[index];
    }

    /**
     * Method to return the number of sculptures sold in the week number passed as a parameter.
     * @param index The week number; also the index of the array where the getSculptureSalesWk values are stored.
     * @return The number of sculptures sold in the given week.
     */
    private static int getSculptureSalesWk(int index) {
        return sculptureSalesWk[index];
    }


    /**
     * Method to set the number of weeks to display on the bar chart.
     * @param numberOfWeeks The number of weeks.
     */
    private static void setNumOfWeeks(int numberOfWeeks) {
        numOfWeeks = numberOfWeeks;
    }

    /**
     * Method to set the week period of time, and store it in an array of all the week periods of time.
     * @param index The week number, also the index of the array
     * @param period The week period of time.
     */
    private static void setDateWeek(int index, String period) {
        dateWeek[index] = period;
    }

    private static void setPaintingSalesWk(int index, int paintingSales) {
        paintingSalesWk[index] = paintingSales;
    }

    private static void setSculptureSalesWk(int index, int sculptureSales) {
        sculptureSalesWk[index] = sculptureSales;
    }

/*
    public static void main(String[] args){
        launch();
    }
*/
}