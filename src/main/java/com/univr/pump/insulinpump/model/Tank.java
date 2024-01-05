package com.univr.pump.insulinpump.model;

public class Tank {
    private int maxCapacity;
    private int currentCapacity;

    public Tank(int maxCapacity, int currentCapacity) {
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
    }

    public void fill() {
        this.currentCapacity = this.maxCapacity;
    }

    public int take(int amount) {
        if (this.currentCapacity - amount >= 0) {
            this.currentCapacity -= amount;
            return amount;
        }
        int taken = this.currentCapacity;
        this.currentCapacity = 0;
        return taken;
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
}
