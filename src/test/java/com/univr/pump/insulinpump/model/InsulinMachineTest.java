package com.univr.pump.insulinpump.model;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class InsulinMachineTest {

    @Test
    public void testCreateVitalParameters() {
        VitalParameters vitalParameters = new VitalParameters();

        assertNotNull(vitalParameters);
    }

    @Test
    public void testSettersAndGetters() {
        LocalDateTime timestamp = LocalDateTime.now();
        int bloodPressureSystolic = 120;
        int bloodPressureDiastolic = 80;
        int heartRate = 80;
        int bloodSugarLevel = 120;
        double temperature = 36.6;

        VitalParameters vitalParameters = new VitalParameters();

        vitalParameters.setTimestamp(timestamp);
        vitalParameters.setBloodPressureSystolic(bloodPressureSystolic);
        vitalParameters.setBloodPressureDiastolic(bloodPressureDiastolic);
        vitalParameters.setHeartRate(heartRate);
        vitalParameters.setBloodSugarLevel(bloodSugarLevel);
        vitalParameters.setTemperature(temperature);

        assertEquals(timestamp, vitalParameters.getTimestamp());
        assertEquals(bloodPressureSystolic, vitalParameters.getBloodPressureSystolic().intValue());
        assertEquals(bloodPressureDiastolic, vitalParameters.getBloodPressureDiastolic().intValue());
        assertEquals(heartRate, vitalParameters.getHeartRate().intValue());
        assertEquals(bloodSugarLevel, vitalParameters.getBloodSugarLevel().intValue());
        assertEquals(temperature, vitalParameters.getTemperature(), 0.001);
    }

    @Test
    public void testCharge() {
        InsulinMachine insulinMachine = new InsulinMachine();

        // Verify that the charge() method correctly sets the current capacity to the maximum
        insulinMachine.charge();
        assertEquals(insulinMachine.getMaxCapacity(), insulinMachine.getCurrentCapacity());

        // Verify that the current capacity never exceeds the maximum
        insulinMachine.setCurrentCapacity(insulinMachine.getMaxCapacity() - 1);
        insulinMachine.charge();
        assertEquals(insulinMachine.getMaxCapacity(), insulinMachine.getCurrentCapacity());
    }

    @Test
    public void testRefill() {
        InsulinMachine insulinMachine = new InsulinMachine();

        // Verify that the refill() method correctly sets the current tank level to the maximum
        insulinMachine.refill();
        assertEquals(insulinMachine.getTankCapacity(), insulinMachine.getCurrentTankLevel());

        // Verify that the current tank level never exceeds the maximum
        insulinMachine.setCurrentTankLevel(insulinMachine.getTankCapacity() - 1);
        insulinMachine.refill();
        assertEquals(insulinMachine.getTankCapacity(), insulinMachine.getCurrentTankLevel());
    }

    @Test
    public void testDecrBattery() {
        InsulinMachine insulinMachine = new InsulinMachine();

        // Verify that the decrBattery() method correctly decreases the current capacity by 1
        int currentCapacity = insulinMachine.getCurrentCapacity();
        insulinMachine.decrBattery();
        assertEquals(currentCapacity - 1, insulinMachine.getCurrentCapacity());

        // Verify that the current capacity never goes below 0
        insulinMachine.setCurrentCapacity(0);
        insulinMachine.decrBattery();
        assertEquals(0, insulinMachine.getCurrentCapacity());
    }

    @Test
    public void testInjectInsulin() {
        InsulinMachine insulinMachine = new InsulinMachine();

        // Verify that the injectInsulin() method correctly decreases the current tank level by 1
        int currentTankLevel = insulinMachine.getCurrentTankLevel();
        insulinMachine.injectInsulin();
        assertEquals(currentTankLevel - 1, insulinMachine.getCurrentTankLevel());

        // Verify that the current tank level never goes below 0
        insulinMachine.setCurrentTankLevel(0);
        insulinMachine.injectInsulin();
        assertEquals(0, insulinMachine.getCurrentTankLevel());
    }
}
