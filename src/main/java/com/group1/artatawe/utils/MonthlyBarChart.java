package com.group1.artatawe.utils;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MonthlyBarChart extends Application {

    private int numOfMonths;

    private String[] dateMonth;
    private int[] paintingSalesM;
    private int[] sculptureSalesM;

    @Override
    public void start(Stage monthlyStage) {

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

        VBox vbox = new VBox(monthlySalesChart);

        monthlyStage.setTitle("SALES PERFORMANCE CHART");
        Scene scene = new Scene(vbox);

        monthlyStage.setScene(scene);
        monthlyStage.setHeight(700);
        monthlyStage.setWidth(1000);

        monthlyStage.show();
    }

    public int getNumOfMonths() {
        return numOfMonths;
    }

    public String getDateMonth(int index) {
        return dateMonth[index];
    }

    public int getPaintingSalesM(int index) {
        return paintingSalesM[index];
    }

    public int getSculptureSalesM(int index) {
        return sculptureSalesM[index];
    }


    public void setNumOfMonths(int numOfMonths) {
        this.numOfMonths = numOfMonths;
    }

    public void setDateMonth(int index, String dateMonth) {
        this.dateMonth[index] = dateMonth;
    }

    public void setPaintingSalesM(int index, int paintingSalesM) {
        this.paintingSalesM[index] = paintingSalesM;
    }

    public void setSculptureSalesM(int index, int sculptureSalesM) {
        this.sculptureSalesM[index] = sculptureSalesM;
    }


    public static void showGraph(){
        Application.launch();
    }

}