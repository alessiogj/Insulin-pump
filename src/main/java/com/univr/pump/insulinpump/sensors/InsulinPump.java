package com.univr.pump.insulinpump.sensors;

public class InsulinPump {
    private int currentGlucoseLevel; //patient data
    private int tankCapacity; //internal data
    private int currentTankLevel; //sensor

    private static final int NORMAL_GLUCOSE_LEVEL = 100;
    private static final int MAX_TANK_CAPACITY = 200;

    public InsulinPump() {
        this.currentGlucoseLevel = NORMAL_GLUCOSE_LEVEL;
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

    public int getCurrentGlucoseLevel() {
        return this.currentGlucoseLevel;
    }

    public void setCurrentGlucoseLevel(int currentGlucoseLevel) {
        this.currentGlucoseLevel = currentGlucoseLevel;
    }

    public void refill() {
        this.currentTankLevel = this.tankCapacity;
    }

    /**
     * La simulazione di un aumento del livello di glucosio nel sangue
     * comporta un incremento casuale del livello di glucosio nel sangue
     * compreso tra 0 e 10 unità.
     */
    public void modifyBloodGlucose() {
        int variation = (int) (Math.random() * 5);
        this.currentGlucoseLevel += variation;
    }

    /**
     * La simulazione di un'iniezione di insulina comporta una diminuzione
     * del livello di glucosio nel sangue di 10 unità. Inoltre, il livello
     * di insulina nel serbatoio diminuisce di 1 unità.
     */
    public void injectInsulin() {
        if (this.currentTankLevel > 3) {
            this.currentTankLevel -= 4;
            this.currentGlucoseLevel -= 40;
        }
    }
}
