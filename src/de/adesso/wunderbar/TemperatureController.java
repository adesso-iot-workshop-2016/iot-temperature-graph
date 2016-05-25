package de.adesso.wunderbar;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by fgoetz on 23.05.2016.
 */
public class TemperatureController {
    private LineChart<String, Number> chart;
    private XYChart.Series temperatureData;
    private final NumberAxis xAxis;
    private final CategoryAxis yAxis;

    TemperatureController() {
        xAxis = new NumberAxis();
        yAxis = new CategoryAxis();
        chart = new LineChart<>(yAxis, xAxis);
        temperatureData = new XYChart.Series();
    }

    /**
     * configure a temperature linechart
     *
     * @return javaFX Scene with a linechart
     */
    Scene getTemperatureChartScene() {
        VBox vBox = new VBox();
        // set labels
        chart.setLegendSide(Side.RIGHT);
        chart.setTitle("adesso - Temperatur");
        temperatureData.setName("Temperatur in °C");
        yAxis.setLabel("Zeitstempel");
        xAxis.setLabel("Daten");

        //temperature Data
        chart.getData().addAll(temperatureData);
        Button resetButton = new Button();
        resetButton.setText("Daten zurücksetzen");
        resetButton.setAlignment(Pos.BOTTOM_CENTER);
        resetButton.setOnAction(event -> {
             this.resetChartData();
        });
        vBox.setFillWidth(true);
        vBox.getChildren().add(chart);
        vBox.getChildren().add(resetButton);
        vBox.setAlignment(Pos.CENTER);
        return new Scene(vBox, 800, 600);
    }

    /**
     * add data to the linechart
     *
     * @param x
     */
    void addDataToChart(Number x) {
        //get the current time to show in the linechart
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.GERMANY);
        Platform.runLater(() -> {
            temperatureData.getData().add(new XYChart.Data(format.format(date).toString(), x));
        });
    }
    void resetChartData(){
        Platform.runLater(()->{
            temperatureData.getData().clear();
        });
    }
}