package com.univr.pump.insulinpump.sensors;

import java.util.Random;

public class BloodPressure {
    private int pressureSystolic;
    private int pressureDiastolic;

    private final static int INITIAL_PRESSURE_SYSTOLIC = 120;
    private final static int INITIAL_PRESSURE_DIASTOLIC = 80;
    public BloodPressure() {
        this.pressureSystolic = INITIAL_PRESSURE_SYSTOLIC;
        this.pressureDiastolic = INITIAL_PRESSURE_DIASTOLIC;
    }

    public int getPressureSystolic() {
        return pressureSystolic;
    }

    public int getPressureDiastolic() {
        return pressureDiastolic;
    }

    private void reset() {
        this.pressureSystolic = INITIAL_PRESSURE_SYSTOLIC;
        this.pressureDiastolic = INITIAL_PRESSURE_DIASTOLIC;
    }

    /**
     * Simulates the pressure variation of the BloodPressure sensor.
     * The pressure is randomly increased or decreased by a value between 0 and 3.
     * If the systolic pressure is lower than 90 or higher than 140, the sensor is reset.
     * If the diastolic pressure is lower than 60 or higher than 90, the sensor is reset.
     */
    public void modifyPressure() {
        Random random = new Random();
        int variation = random.nextInt(3);
        if (random.nextBoolean()) {
            this.pressureSystolic += variation;
        } else {
            this.pressureSystolic -= variation;
        }
        variation = random.nextInt(3);
        if (random.nextBoolean()) {
            this.pressureDiastolic += variation;
        } else {
            this.pressureDiastolic -= variation;
        }
        if (this.pressureDiastolic < 60 || this.pressureDiastolic > 90) {
            this.reset();
        }
    }
}
