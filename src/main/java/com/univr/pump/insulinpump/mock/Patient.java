package com.univr.pump.insulinpump.mock;

import java.util.Random;

public class Patient {
    private int pressureSystolic;
    private int pressureDiastolic;
    private int heartRate;
    private double temperature;
    private int glucoseLevel;

    private final static int INITIAL_PRESSURE_SYSTOLIC = 120;
    private final static int INITIAL_PRESSURE_DIASTOLIC = 80;
    private final static int INITIAL_HEART_RATE = 80;
    private final static double INITIAL_TEMPERATURE = 37.0;
    private final static int INITIAL_GLUCOSE_LEVEL = 90;

    public Patient() {
        this.pressureSystolic = INITIAL_PRESSURE_SYSTOLIC;
        this.pressureDiastolic = INITIAL_PRESSURE_DIASTOLIC;
        this.heartRate = INITIAL_HEART_RATE;
        this.temperature = INITIAL_TEMPERATURE;
        this.glucoseLevel = INITIAL_GLUCOSE_LEVEL;
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

    public double getTemperature() {
        return temperature;
    }

    public int getGlucoseLevel() {
        return glucoseLevel;
    }

    public void setGlucoseLevel(int glucoseLevel) {
        this.glucoseLevel = glucoseLevel;
    }

    public void setPressureSystolic(int pressureSystolic) {
        this.pressureSystolic = pressureSystolic;
    }

    public void setPressureDiastolic(int pressureDiastolic) {
        this.pressureDiastolic = pressureDiastolic;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    private void reset() {
        this.pressureSystolic = INITIAL_PRESSURE_SYSTOLIC;
        this.pressureDiastolic = INITIAL_PRESSURE_DIASTOLIC;
        this.heartRate = INITIAL_HEART_RATE;
        this.temperature = INITIAL_TEMPERATURE;
        this.glucoseLevel = INITIAL_GLUCOSE_LEVEL;
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
        java.util.Random random = new java.util.Random();
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
     * Simulates the temperature variation of the NTC sensor.
     * The temperature is randomly increased or decreased by a value between 0 and 0.01.
     * If the temperature is lower than 35 or higher than 39, the sensor is broken.
     * and the temperature is reset to 37.
     * We can modify the temperature only if the sensor is not broken.
     * Randomly, the sensor can break with a probability of 1%.
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
     * La simulazione della variazione di glucosio nel sangue del paziente
     * comporta un aumento o una diminuzione del livello di glucosio nel sangue.
     * Il livello di glucosio nel sangue può variare di 10 unità in più o in meno.
     * Il livello di glucosio nel sangue non può scendere sotto 50 unità.
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
