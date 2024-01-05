package com.univr.pump.insulinpump.model;

public class Battery {
    private int maxCapacity;
    private int currentCapacity;

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

    public void discharge() {
        if (this.currentCapacity > 0) {
            this.currentCapacity--;
        }
    }
}
