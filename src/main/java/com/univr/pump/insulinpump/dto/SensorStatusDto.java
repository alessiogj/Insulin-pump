package com.univr.pump.insulinpump.dto;

public class SensorStatusDto {
    private int battery;
    private int tank;
    private double ntc;
    private boolean ntcStatus;

    public SensorStatusDto(int battery, int tank, double ntc, boolean ntcStatus) {
        this.battery = battery;
        this.tank = tank;
        this.ntc = ntc;
        this.ntcStatus = ntcStatus;
    }

    public int getBattery() {
        return battery;
    }

    public int getTank() {
        return tank;
    }

    public double getNtc() {
        return ntc;
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

    public void setNtc(double ntc) {
        this.ntc = ntc;
    }

    public void setNtcStatus(boolean ntcStatus) {
        this.ntcStatus = ntcStatus;
    }
}
