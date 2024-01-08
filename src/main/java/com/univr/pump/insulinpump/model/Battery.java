package com.univr.pump.insulinpump.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Battery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int maxCapacity;
    private int currentCapacity;

    private final static int DEFAULT_MAX_CAPACITY = 100;
    private final static int DEFAULT_CURRENT_CAPACITY = 100;

    public Battery() {
        this.maxCapacity = DEFAULT_MAX_CAPACITY;
        this.currentCapacity = DEFAULT_CURRENT_CAPACITY;
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
