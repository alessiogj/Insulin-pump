package com.univr.pump.insulinpump.sensors;

public class InsulinPump {
    private int currentGlucoseLevel; //patient data
    private int tankCapacity; //internal data
    private int currentTankLevel; //sensor

    private static final int NORMAL_GLUCOSE_LEVEL = 90;
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
     * La simulazione della variazione di glucosio nel sangue del paziente
     * comporta un aumento o una diminuzione del livello di glucosio nel sangue.
     * Il livello di glucosio nel sangue può variare di 10 unità in più o in meno.
     * Il livello di glucosio nel sangue non può scendere sotto 50 unità.
     */
    public void modifyBloodGlucose() {
        int random = (int) (Math.random() * 11);
        if (random < 3) {
            this.currentGlucoseLevel += 10;
        } else if (random < 6) {
            this.currentGlucoseLevel -= 3;
        }
        if (this.currentGlucoseLevel < 80) {
            this.currentGlucoseLevel = 100;
        }
    }

    /**
     * La simulazione di un'iniezione di insulina comporta una diminuzione
     * del livello di glucosio nel sangue di riporto a 90 unità.
     * L'iniezione di insulina comporta una diminuzione del livello di insulina
     * nel serbatoio di 4 unità (per ipotesi).
     */
    public void injectInsulin() {
        if (this.currentTankLevel > 3) {
            this.currentTankLevel -= 4;
            this.currentGlucoseLevel = NORMAL_GLUCOSE_LEVEL;
        }
    }
}
