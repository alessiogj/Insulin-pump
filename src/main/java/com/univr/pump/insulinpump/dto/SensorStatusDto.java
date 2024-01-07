package com.univr.pump.insulinpump.dto;

public class SensorStatusDto {
    private int battery;
    private int tank;

    public SensorStatusDto(int battery, int tank) {
        this.battery = battery;
        this.tank = tank;
    }

    public int getBattery() {
        return battery;
    }

    public int getTank() {
        return tank;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public void setTank(int tank) {
        this.tank = tank;
    }

}
