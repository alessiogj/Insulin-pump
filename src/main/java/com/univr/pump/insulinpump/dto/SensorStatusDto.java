package com.univr.pump.insulinpump.dto;

public class SensorStatusDto {
    private int battery;
    private int tank;
    private double ntc;
    private boolean ntcStatus;
    private int pressureSystolic;
    private int pressureDiastolic;

    public SensorStatusDto(int battery, int tank, double ntc, boolean ntcStatus, int pressureSystolic, int pressureDiastolic) {
        this.battery = battery;
        this.tank = tank;
        this.ntc = ntc;
        this.ntcStatus = ntcStatus;
        this.pressureSystolic = pressureSystolic;
        this.pressureDiastolic = pressureDiastolic;
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

    public int getPressureSystolic() {
        return pressureSystolic;
    }

    public int getPressureDiastolic() {
        return pressureDiastolic;
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

    public void setPressureSystolic(int pressureSystolic) {
        this.pressureSystolic = pressureSystolic;
    }

    public void setPressureDiastolic(int pressureDiastolic) {
        this.pressureDiastolic = pressureDiastolic;
    }
}
