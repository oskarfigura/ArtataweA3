package com.group1.artatawe.utils;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * This class generates a bar chart, displaying one's monthly sales performance for the past 8 months.
 *
 * @author K Carew.
 * @version 20180315Th1830.
 */
public class MonthlyBarChart {

    private static final int NUM_OF_MONTHS = 8;

    private static String[] dateMonth = {"Month 1", "Month 2", "Month 3", "Month 4", "Month 5", "Month 6", "Month 7", "Month 8"};
    private static int[] paintingSalesM = new int[NUM_OF_MONTHS];
    private static int[] sculptureSalesM = new int[NUM_OF_MONTHS];

    /**
     * This method generates a bar chart object, without a container/frame/stage, of one's sales performance.
     * @return The bar chart object of a user's monthly sales performance.
     */
    public static BarChart<String, Number> start() {

/*
        //TEST VALUES
        setPaintingSalesM(0, 80);
        setPaintingSalesM(1, 70);
        setPaintingSalesM(2, 73);
        setPaintingSalesM(3, 79);
        setPaintingSalesM(4, 79);
        setPaintingSalesM(5, 69);
        setPaintingSalesM(6, 66);
        setPaintingSalesM(7, 71);
        setSculptureSalesM(0, 50);
        setSculptureSalesM(1, 45);
        setSculptureSalesM(2, 46);
        setSculptureSalesM(3, 30);
        setSculptureSalesM(4, 38);
        setSculptureSalesM(5, 36);
        setSculptureSalesM(6, 38);
        setSculptureSalesM(7, 41);
        // ^^ TEST VALUES ^^
*/


        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Month");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of Sales");

        // Create a BarChart
        BarChart<String, Number> monthlySalesChart = new BarChart<>(xAxis, yAxis);

        // Series 1 - Painting Sales
        XYChart.Series<String, Number> monthlyPaintingSales = new XYChart.Series<>();
        monthlyPaintingSales.setName("Paintings");

        for (int i = (getNumOfMonths()-1); i >= 0; i--) {
            monthlyPaintingSales.getData().add(new XYChart.Data<>(getDateMonth(i), getPaintingSalesM(i)));
        }

        // Series 2 - Sculpture Sales
        XYChart.Series<String, Number> monthlySculptureSales = new XYChart.Series<>();
        monthlySculptureSales.setName("Sculptures");

        for (int i = (getNumOfMonths()-1); i >= 0; i--) {
            monthlySculptureSales.getData().add(new XYChart.Data<>(getDateMonth(i), getSculptureSalesM(i)));
        }

        // Series 3 - Combined Sales
        XYChart.Series<String, Number> monthlyTotalArtworkSales = new XYChart.Series<>();
        monthlyTotalArtworkSales.setName("Total Artworks");

        for (int i = (getNumOfMonths()-1); i >= 0; i--) {
            monthlyTotalArtworkSales.getData().add(new XYChart.Data<>(getDateMonth(i), (getPaintingSalesM(i) + getSculptureSalesM(i)) ));

        }


        // Add Series to BarChart.
        monthlySalesChart.getData().add(monthlyPaintingSales);
        monthlySalesChart.getData().add(monthlySculptureSales);
        monthlySalesChart.getData().add(monthlyTotalArtworkSales);

        monthlySalesChart.setTitle("Your Monthly Sales Performance");

        /*
        VBox vbox = new VBox(monthlySalesChart);

        monthlyStage.setTitle("SALES PERFORMANCE CHART");
        Scene scene = new Scene(vbox);

        monthlyStage.setScene(scene);
        monthlyStage.setHeight(700);
        monthlyStage.setWidth(1000);

        monthlyStage.show();
        */
        return monthlySalesChart;
    }

    /**
     * Method to return the number of months being analysed.
     * @return The number of months being analysed.
     */
    public static int getNumOfMonths() {
        return NUM_OF_MONTHS;
    }

    /**
     * Method to return the month period of time, as a string.
     * @param index The month number; also the index of the array where the dateMonth String values are stored.
     * @return The month period of time, as a String.
     */
    public static String getDateMonth(int index) {
        return dateMonth[index];
    }

    /**
     * Method to return the number of paintings sold in the month number passed as a parameter.
     * @param index The month number; also the index of the array where the PaintingSalesM values are stored.
     * @return The number of paintings sold in the given month.
     */
    public static int getPaintingSalesM(int index) {
        return paintingSalesM[index];
    }

    /**
     * Method to return the number of sculptures sold in the month number passed as a parameter.
     * @param index The month number; also the index of the array where the SculptureSalesM values are stored.
     * @return The number of sculptures sold in the given month.
     */
    public static int getSculptureSalesM(int index) {
        return sculptureSalesM[index];
    }


    /**
     * Method to set the number of paintings sold in the given month.
     * @param index The month number; also the index of the array where the PaintingSalesM values are stored.
     * @param paintingSales The number of paintings sold in the given month.
     */
    public static void setPaintingSalesM(int index, int paintingSales) {
        paintingSalesM[index] = paintingSales;
    }

    /**
     * Method to set the number of sculptures sold in the given month.
     * @param index The month number; also the index of the array where the sculptureSalesM values are stored.
     * @param sculptureSales The number of sculptures sold in the given month.
     */
    public static void setSculptureSalesM(int index, int sculptureSales) {
        sculptureSalesM[index] = sculptureSales;
    }

    /*
    public static void main(String[] args){
        launch();
    }
    */
}