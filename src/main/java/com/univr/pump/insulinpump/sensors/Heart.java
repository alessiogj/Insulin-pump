package com.univr.pump.insulinpump.sensors;

import java.util.Random;

public class Heart {
    private int pressureSystolic;
    private int pressureDiastolic;
    private int heartRate;

    private final static int INITIAL_PRESSURE_SYSTOLIC = 120;
    private final static int INITIAL_PRESSURE_DIASTOLIC = 80;
    private final static int INITIAL_HEART_RATE = 80;
    public Heart() {
        this.pressureSystolic = INITIAL_PRESSURE_SYSTOLIC;
        this.pressureDiastolic = INITIAL_PRESSURE_DIASTOLIC;
        this.heartRate = INITIAL_HEART_RATE;
    }

    public int getPressureSystolic() {
        return pressureSystolic;
    }

    public int getPressureDiastolic() {
        return pressureDiastolic;
    }

    public int getHeartRate() {
        return heartRate;
    }

    private void reset() {
        this.pressureSystolic = INITIAL_PRESSURE_SYSTOLIC;
        this.pressureDiastolic = INITIAL_PRESSURE_DIASTOLIC;
        this.heartRate = INITIAL_HEART_RATE;
    }

    /**
     * Simulates the pressure variation of the BloodPressure sensor.
     * The pressure is randomly increased or decreased by a value between 0 and 3.
     * The heart rate is randomly increased or decreased by a value between 0 and 2.
     * If the systolic pressure is lower than 90 or higher than 140, the sensor is reset.
     * If the diastolic pressure is lower than 60 or higher than 90, the sensor is reset.
     * If the heart rate is lower than 60 or higher than 100, the sensor is reset.
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

        variation = random.nextInt(2);
        if (random.nextBoolean()) {
            this.heartRate += variation;
        } else {
            this.heartRate -= variation;
        }

        if (this.pressureDiastolic < 60 || this.pressureDiastolic > 90) {
            this.reset();
        }
        if (this.pressureSystolic < 90 || this.pressureSystolic > 140) {
            this.reset();
        }
        if (this.heartRate < 60 || this.heartRate > 100) {
            this.reset();
        }
    }
}
