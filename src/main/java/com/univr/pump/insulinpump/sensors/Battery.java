package com.univr.pump.insulinpump.sensors;

public class Battery {
    private int maxCapacity;
    private int currentCapacity;

    public Battery() {
    }

    public Battery(int maxCapacity, int currentCapacity) {
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public void charge() {
        this.currentCapacity = this.maxCapacity;
    }

    /**
     * Simulates the discharge of the battery.
     * The current capacity is decreased by 1.
     */
    public void discharge() {
        if (this.currentCapacity > 0) {
            this.currentCapacity--;
        }
    }
}
