package com.univr.pump.insulinpump.sensors;

public class InsulinPump {
    private double currentGlucoseLevel;
    private boolean isDeviceActive;
    private int tankCapacity;
    private int currentTankLevel;

    private static final int NORMAL_GLUCOSE_LEVEL = 100;
    private static final int MAX_TANK_CAPACITY = 200;

    public InsulinPump() {
        this.currentGlucoseLevel = NORMAL_GLUCOSE_LEVEL;
        this.isDeviceActive = false;
        this.tankCapacity = MAX_TANK_CAPACITY;
        this.currentTankLevel = MAX_TANK_CAPACITY;
    }

    public void setDeviceActive(boolean isDeviceActive) {
        this.isDeviceActive = isDeviceActive;
    }

    public boolean isDeviceActive() {
        return this.isDeviceActive;
    }

    public int getTankCapacity() {
        return this.tankCapacity;
    }

    public int getCurrentTankLevel() {
        return this.currentTankLevel;
    }

    public void setCurrentTankLevel(int currentTankLevel) {
        this.currentTankLevel = currentTankLevel;
    }

    public void setTankCapacity(int tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public double getCurrentGlucoseLevel() {
        return this.currentGlucoseLevel;
    }

    public void setCurrentGlucoseLevel(double currentGlucoseLevel) {
        this.currentGlucoseLevel = currentGlucoseLevel;
    }

}
