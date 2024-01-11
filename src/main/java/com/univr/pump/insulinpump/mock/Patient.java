package com.univr.pump.insulinpump.mock;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class Patient {
    private int pressureSystolic;
    private int pressureDiastolic;
    private int heartRate;
    private double temperature;
    private int glucoseLevel;

    private final static int INITIAL_PRESSURE_SYSTOLIC = 120;
    private final static int INITIAL_PRESSURE_DIASTOLIC = 80;
    private final static int INITIAL_HEART_RATE = 80;
    private final static double INITIAL_TEMPERATURE = 36.5;
    private final static int INITIAL_GLUCOSE_LEVEL = 90;

    public Patient() {
        this.pressureSystolic = INITIAL_PRESSURE_SYSTOLIC;
        this.pressureDiastolic = INITIAL_PRESSURE_DIASTOLIC;
        this.heartRate = INITIAL_HEART_RATE;
        this.temperature = INITIAL_TEMPERATURE;
        this.glucoseLevel = INITIAL_GLUCOSE_LEVEL;
    }

    private void reset() {
        this.pressureSystolic = INITIAL_PRESSURE_SYSTOLIC;
        this.pressureDiastolic = INITIAL_PRESSURE_DIASTOLIC;
        this.heartRate = INITIAL_HEART_RATE;
        this.temperature = INITIAL_TEMPERATURE;
        this.glucoseLevel = INITIAL_GLUCOSE_LEVEL;
    }

    /**
     * Simulates the pressure variation of the patient's blood.
     * The pressure is randomly increased or decreased by a value between 0 and 3.
     * The heart rate is randomly increased or decreased by a value between 0 and 2.
     * If the systolic pressure is lower than 90 or higher than 140, the pressure is set to 120.
     * If the diastolic pressure is lower than 60 or higher than 90, the pressure is set to 80.
     * If the heart rate is lower than 60 or higher than 100, the heart rate is set to 80.
     */
    public void modifyPressure() {
        java.util.Random random = new java.util.Random();
        int variation = random.nextInt(5);
        if (random.nextBoolean()) {
            this.pressureSystolic += variation;
        } else {
            this.pressureSystolic -= variation;
        }
        variation = random.nextInt(5);
        if (random.nextBoolean()) {
            this.pressureDiastolic += variation;
        } else {
            this.pressureDiastolic -= variation;
        }

        variation = random.nextInt(5);
        if (random.nextBoolean()) {
            this.heartRate += variation;
        } else {
            this.heartRate -= variation;
        }

        if (this.pressureSystolic < 90 || this.pressureSystolic > 140) {
            this.reset();
        }
        if (this.pressureDiastolic < 60 || this.pressureDiastolic > 90) {
            this.reset();
        }
        if (this.heartRate < 60 || this.heartRate > 100) {
            this.reset();
        }
    }

    /**
     * Simulates the temperature variation of the patient's temperature.
     * The temperature is randomly increased or decreased by a value between 0 and 0.01.
     * If the temperature is lower than 35 or higher than 39, the temperature is set to 36.5.
     * The temperature is set to 36.5 with a probability of 1%.
     */
    public void modifyTemperature() {
        Random random = new Random();
        double variation = random.nextDouble() / 100;
        if (random.nextBoolean()) {
            this.temperature += variation;
        } else {
            this.temperature -= variation;
        }
        if (this.temperature < 35 || this.temperature > 39) {
            this.temperature = INITIAL_TEMPERATURE;
        }
        if (random.nextInt(100) == 0) {
            this.temperature = INITIAL_TEMPERATURE;
        }
    }

    /**
     * Simulates the glucose variation in the patient's blood.
     * The glucose level is randomly increased or decreased by a value between 0 and 10.
     * If the glucose level is lower than 80, the glucose level is set to 100.
     */
    public void modifyBloodGlucose() {
        int random = (int) (Math.random() * 11);
        if (random < 3) {
            this.glucoseLevel += 10;
        } else if (random < 6) {
            this.glucoseLevel -= 3;
        }
        if (this.glucoseLevel < 80) {
            this.glucoseLevel = 100;
        }
    }

    /**
     * Glucose level is set to 90.
     */
    public void setGlucoseToNormal() {
        this.glucoseLevel = INITIAL_GLUCOSE_LEVEL;
    }
}
