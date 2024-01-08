package com.univr.pump.insulinpump.mock.sensors;

import com.univr.pump.insulinpump.mock.Patient;

public class InsulinPump {
    private final Patient patient;
    private int tankCapacity;
    private int currentTankLevel;

    private static final int NORMAL_GLUCOSE_LEVEL = 90;
    private static final int MAX_TANK_CAPACITY = 200;

    public InsulinPump(Patient patient) {
        this.patient = patient;
        this.tankCapacity = MAX_TANK_CAPACITY;
        this.currentTankLevel = MAX_TANK_CAPACITY;
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

    public void refill() {
        this.currentTankLevel = this.tankCapacity;
    }

    /**
     * Injects insulin.
     */
    public void injectInsulin() {
            patient.setGlucoseLevel(NORMAL_GLUCOSE_LEVEL);
            this.currentTankLevel--;
    }
}
