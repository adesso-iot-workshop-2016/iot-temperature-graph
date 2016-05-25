package de.adesso.wunderbar;

import io.relayr.java.model.action.Reading;
import rx.Observer;

/**
 * Created by fgoetz on 23.05.2016.
 */
public class TemperatureModel implements Observer<Reading> {
    private TemperatureController temperatureController;

    /*
     * Observe the readings of a temperature device.
     *
     * @param alarmManager
     *            used to trigger an alarm.
     */
    public TemperatureModel(TemperatureController controller) {
        this.temperatureController = controller;
    }

    @Override
    public void onNext(Reading reading) {
        if (reading.meaning.equals("temperature")) {
            System.out.println("Temperature: " + reading.value);
            temperatureController.addDataToChart((Double) reading.value);
        }
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable arg0) {
        System.out.println("TemperatureObsreverError");
        System.out.println(arg0.getLocalizedMessage());
    }

}