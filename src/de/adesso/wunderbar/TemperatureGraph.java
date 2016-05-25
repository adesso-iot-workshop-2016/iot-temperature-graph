package de.adesso.wunderbar;

import io.relayr.java.RelayrJavaSdk;
import io.relayr.java.model.Device;
import io.relayr.java.model.User;
import io.relayr.java.model.action.Reading;
import javafx.application.Application;
import javafx.stage.Stage;
import rx.Observable;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by fgoetz on 06.05.2016.
 */
public class TemperatureGraph extends Application {
    private static final String BEARER_TOKEN = "Bearer rNKSj-sA6.8wG8Bs_SYkAwQ5Lu0rG8K4";
    private static final String THERMOMETER_DEVICE_ID = "828a9815-980f-45e7-be35-5e63a5938105";

    private static TemperatureController controller;
    private static Device device;
    private static Observable<Reading> readings;

    public static void main(String[] args) {
        //find device
        try {
            device = findDevice(THERMOMETER_DEVICE_ID);
            readings = device.subscribeToCloudReadings().timeout(10000, TimeUnit.MILLISECONDS);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("can't subscribe to device");
        }
        Application.launch();
    }

    /**
     * start GUI
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        controller = new TemperatureController();
        readings.subscribe(new TemperatureModel(controller));
        stage.setTitle("adesso - IoT Workshop");
        stage.setScene(controller.getTemperatureChartScene());
        stage.show();
    }


    private static Device findDevice(String deviceId) throws IllegalArgumentException {
        new RelayrJavaSdk.Builder().setToken(BEARER_TOKEN).cacheModels(true).build();
        User user = RelayrJavaSdk.getUser().toBlocking().single();

        List<Device> devices = user.getDevices().toBlocking().first();
        for (Device device : devices) {
            if (device.getId().equals(deviceId)) {
                return device;
            }
        }
        throw new IllegalArgumentException("Device with id '" + deviceId + "' not found.");
    }
}
