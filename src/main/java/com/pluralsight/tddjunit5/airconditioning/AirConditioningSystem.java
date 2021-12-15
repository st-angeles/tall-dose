package com.pluralsight.tddjunit5.airconditioning;

public class AirConditioningSystem {
    private Thermometer thermometer;
    private boolean open;
    private double temperatureThreshold;

    public AirConditioningSystem(){
        open = false;
    }

    public void setThermometer(Thermometer thermometer) {
        this.thermometer = thermometer;
    }

    public boolean isOpen() {
        return open;
    }

    public void setTemperatureThreshold(double temperatureThreshold) {
        this.temperatureThreshold = temperatureThreshold;
    }

    public void checkAirconditioningSystem(){
        open = (thermometer.getTemperature() >= temperatureThreshold);
    }
}
