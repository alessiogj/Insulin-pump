package com.univr.pump.insulinpump.dto;

public class SensorStatusDto {
    private int battery;
    private int tank;
    private boolean ntcStatus;

    public SensorStatusDto(int battery, int tank, boolean ntcStatus) {
        this.battery = battery;
        this.tank = tank;
        this.ntcStatus = ntcStatus;
    }

    public int getBattery() {
        return battery;
    }

    public int getTank() {
        return tank;
    }

    public boolean getNtcStatus() {
        return ntcStatus;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public void setTank(int tank) {
        this.tank = tank;
    }

    public void setNtcStatus(boolean ntcStatus) {
        this.ntcStatus = ntcStatus;
    }

}
