package com.group1.artatawe.utils;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class MonthlyBarChart {

    private static int numOfMonths;

    private static String[] dateMonth;
    private static int[] paintingSalesM;
    private static int[] sculptureSalesM;

    public static BarChart<String, Number> start() {

        setNumOfMonths(8);

        dateMonth = new String[numOfMonths+1];
        // Not using [0] to avoid confusion.

        paintingSalesM = new int[numOfMonths+1];
        // Not using [0] to avoid confusion.

        sculptureSalesM = new int[numOfMonths+1];
        // Not using [0] to avoid confusion.

        //TEST VALUES
        setDateMonth(1, "July 2017");
        setDateMonth(2, "August 2017");
        setDateMonth(3, "September 2017");
        setDateMonth(4, "October 2017");
        setDateMonth(5, "November 2017");
        setDateMonth(6, "December 2017");
        setDateMonth(7, "January 2018");
        setDateMonth(8, "February 2018");
        setPaintingSalesM(1, 80);
        setPaintingSalesM(2, 70);
        setPaintingSalesM(3, 73);
        setPaintingSalesM(4, 79);
        setPaintingSalesM(5, 79);
        setPaintingSalesM(6, 69);
        setPaintingSalesM(7, 66);
        setPaintingSalesM(8, 71);
        setSculptureSalesM(1, 50);
        setSculptureSalesM(2, 45);
        setSculptureSalesM(3, 46);
        setSculptureSalesM(4, 30);
        setSculptureSalesM(5, 38);
        setSculptureSalesM(6, 36);
        setSculptureSalesM(7, 38);
        setSculptureSalesM(8, 41);
        // ^^ TEST VALUES ^^



        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Month");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of Sales");

        // Create a BarChart
        BarChart<String, Number> monthlySalesChart = new BarChart<>(xAxis, yAxis);

        // Series 1 - Painting Sales
        XYChart.Series<String, Number> monthlyPaintingSales = new XYChart.Series<>();
        monthlyPaintingSales.setName("Paintings");

        for (int i=1; i< getNumOfMonths(); i++) {
            monthlyPaintingSales.getData().add(new XYChart.Data<>(getDateMonth(i), getPaintingSalesM(i)));
        }

        // Series 2 - Sculpture Sales
        XYChart.Series<String, Number> monthlySculptureSales = new XYChart.Series<>();
        monthlySculptureSales.setName("Sculptures");

        for (int i=1; i< getNumOfMonths(); i++) {
            monthlySculptureSales.getData().add(new XYChart.Data<>(getDateMonth(i), getSculptureSalesM(i)));
        }

        // Series 3 - Combined Sales
        XYChart.Series<String, Number> monthlyTotalArtworkSales = new XYChart.Series<>();
        monthlyTotalArtworkSales.setName("Total Artworks");

        for (int i=1; i< getNumOfMonths(); i++) {
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

    private static int getNumOfMonths() {
        return numOfMonths;
    }

    private static String getDateMonth(int index) {
        return dateMonth[index];
    }

    private static int getPaintingSalesM(int index) {
        return paintingSalesM[index];
    }

    private static int getSculptureSalesM(int index) {
        return sculptureSalesM[index];
    }


    private static void setNumOfMonths(int numberOfMonths) {
        numOfMonths = numberOfMonths;
    }

    private static void setDateMonth(int index, String month) {
        dateMonth[index] = month;
    }

    private static void setPaintingSalesM(int index, int paintingSales) {
        paintingSalesM[index] = paintingSales;
    }

    private static void setSculptureSalesM(int index, int sculptureSales) {
        sculptureSalesM[index] = sculptureSales;
    }

    /*
    public static void main(String[] args){
        launch();
    }
    */
}