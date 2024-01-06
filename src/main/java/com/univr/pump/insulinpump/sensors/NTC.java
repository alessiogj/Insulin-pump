package com.univr.pump.insulinpump.sensors;

import java.util.Random;

public class NTC {
    private double temperature;
    private boolean isBroken;

    private final static double INITIAL_TEMPERATURE = 37.0;
    public NTC() {
        this.temperature = INITIAL_TEMPERATURE;
        this.isBroken = false;
    }

    public double getTemperature() {
        return temperature;
    }

    public boolean isBroken() {
        return isBroken;
    }

    public void repair() {
        this.isBroken = false;
    }

    public void reset() {
        this.temperature = INITIAL_TEMPERATURE;
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
        if (!isBroken) {
            Random random = new Random();
            double variation = random.nextDouble() / 100;
            if (random.nextBoolean()) {
                this.temperature += variation;
            } else {
                this.temperature -= variation;
            }
            if (this.temperature < 35 || this.temperature > 39) {
                this.isBroken = true;
                this.temperature = INITIAL_TEMPERATURE;
            }
            if (random.nextInt(100) == 0) {
                this.isBroken = true;
                this.temperature = INITIAL_TEMPERATURE;
            }
        }
    }
}
