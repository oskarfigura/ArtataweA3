package com.group1.artatawe.utils;


import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WeeklyBarChart {

    private static int numOfWeeks=8;
    //Weeks are from Monday to Sunday

    private static String[] dateWeek;
    private static int[] paintingSalesWk;
    private static int[] sculptureSalesWk;


    public static BarChart<String, Number> start() {

        //setNumOfWeeks(8);


        dateWeek = new String[numOfWeeks+1];
        // Not using [0] to avoid confusion.
        //Weeks are from Monday to Sunday

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

        //Could probably use a for loop to set the values



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

        //VBox vbox = new VBox(weeklySalesChart);
        /*
        weeklyStage.setTitle("SALES PERFORMANCE CHART");
        Scene scene = new Scene(vbox);

        weeklyStage.setScene(scene);
        weeklyStage.setHeight(700);
        weeklyStage.setWidth(1000);

        weeklyStage.show();
        */
        return weeklySalesChart;
    }

    public static int getNumOfWeeks() {
        return numOfWeeks;
    }

    public static String getDateWeek(int index) {
        return dateWeek[index];
    }

    public static int getPaintingSalesWk(int index) {
        return paintingSalesWk[index];
    }

    public static int getSculptureSalesWk(int index) {
        return sculptureSalesWk[index];
    }


    public static void setNumOfWeeks(int numOfWeeks) {
        numOfWeeks = numOfWeeks;
    }

    public static void setDateWeek(int index, String period) {
        dateWeek[index] = period;
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