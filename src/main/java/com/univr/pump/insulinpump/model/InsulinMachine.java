package com.univr.pump.insulinpump.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InsulinMachine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int maxCapacity;
    private int currentCapacity;
    private int tankCapacity;
    private int currentTankLevel;

    private static final int MAX_TANK_CAPACITY = 200;

    private final static int DEFAULT_MAX_CAPACITY = 100;
    private final static int DEFAULT_CURRENT_CAPACITY = 100;

    public InsulinMachine() {
        this.maxCapacity = DEFAULT_MAX_CAPACITY;
        this.currentCapacity = DEFAULT_CURRENT_CAPACITY;
        this.tankCapacity = MAX_TANK_CAPACITY;
        this.currentTankLevel = MAX_TANK_CAPACITY;
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public void charge() {
        this.currentCapacity = this.maxCapacity;
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

    public void refill() {
        this.currentTankLevel = this.tankCapacity;
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

    /**
     * Injects insulin.
     */
    public void injectInsulin() {
        if (this.currentTankLevel > 0) {
            this.currentTankLevel--;
        }
    }

}
