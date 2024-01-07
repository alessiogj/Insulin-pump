package com.univr.pump.insulinpump;

import com.univr.pump.insulinpump.scheduled.BatteryMonitoringTask;
import com.univr.pump.insulinpump.scheduled.NtcMonitoringTask;
import com.univr.pump.insulinpump.scheduled.VitalParametersMonitoringTask;
import com.univr.pump.insulinpump.sensors.Battery;
import com.univr.pump.insulinpump.sensors.Heart;
import com.univr.pump.insulinpump.sensors.InsulinPump;
import com.univr.pump.insulinpump.sensors.NTC;
import com.univr.pump.insulinpump.service.VitalParametersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsulinPumpApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ScheduledIntegrationTest {
    @Mock
    private Battery battery;

    @Mock
    private Heart heart;

    @Mock
    private InsulinPump insulinPump;

    @Mock
    private NTC ntc;

    @Mock
    private VitalParametersService vitalParametersService;

    @InjectMocks
    private BatteryMonitoringTask batteryMonitoringTask;

    @InjectMocks
    private VitalParametersMonitoringTask vitalParametersMonitoringTask;

    @InjectMocks
    private NtcMonitoringTask ntcMonitoringTask;

    /**
     * Test the battery monitoring task.
     * The method should decrease the battery capacity
     * when the battery is not low
     */
    @Test
    public void testDecrBattery() {
        when(battery.getCurrentCapacity()).thenReturn(100);
        batteryMonitoringTask.decrBattery();
        verify(battery, times(1)).discharge();
    }

    /**
     * Test the battery monitoring task.
     * The method should not decrease the battery capacity
     * when the battery is low
     */
    @Test
    public void testDecrBatteryBatteryLow() {
        when(battery.getCurrentCapacity()).thenReturn(0);
        batteryMonitoringTask.decrBattery();
        verify(battery, never()).discharge();
    }

    /**
     * Test the modify vital parameters' method.
     * The method should modify the vital parameters
     */
    @Test
    public void testModifyVitalParameters() {
        vitalParametersMonitoringTask.modifyVitalParameters();
        verify(heart, times(1)).modifyPressure();
        verify(insulinPump, times(1)).modifyBloodGlucose();
        verify(ntc, times(1)).modifyTemperature();
    }

    /**
     * Test the new vital signs method.
     * The method should not save the vital signs if the battery is low
     */
    @Test
    public void testNewVitalSignsBatteryLow() {
        when(battery.getCurrentCapacity()).thenReturn(0);

        vitalParametersMonitoringTask.newVitalSigns();

        verify(ntc, times(1)).reset();
        verify(vitalParametersService, never()).saveHeartParameters(
                anyInt(),
                anyInt(),
                anyInt(),
                anyInt(),
                anyDouble());
    }

    /**
     * Test the new vital signs method.
     * The method should not save the vital signs if the NTC is broken
     */
    public void testNewVitalSignsNtcBroken() {
        when(battery.getCurrentCapacity()).thenReturn(100);
        when(ntc.isBroken()).thenReturn(true);

        vitalParametersMonitoringTask.newVitalSigns();

        verify(ntc, times(1)).reset();
        verify(vitalParametersService, never()).saveHeartParameters(
                anyInt(),
                anyInt(),
                anyInt(),
                anyInt(),
                anyDouble());
    }

    /**
     * Test the new vital signs method.
     * The method should save the vital signs if the battery is not low
     * and the NTC is not broken
     */
    @Test
    public void testNewVitalSignsNormalConditions() {
        when(battery.getCurrentCapacity()).thenReturn(100);
        when(ntc.isBroken()).thenReturn(false);

        vitalParametersMonitoringTask.newVitalSigns();

        verify(heart, times(1)).getHeartRate();
        verify(heart, times(1)).getPressureDiastolic();
        verify(heart, times(1)).getPressureSystolic();
        verify(insulinPump, times(1)).getCurrentGlucoseLevel();
        verify(ntc, times(1)).getTemperature();
    }

    /**
     * Test the new temperature method.
     * The method should not modify the temperature if the battery is low
     */
    @Test
    public void testNewTemperatureBatteryLow() {
        when(battery.getCurrentCapacity()).thenReturn(0);
        when(ntc.isBroken()).thenReturn(false);

        ntcMonitoringTask.newTemp();

        verify(ntc, times(1)).reset();
        verify(ntc, never()).modifyTemperature();
    }

    /**
     * Test the new temperature method.
     * The method should not modify the temperature if the NTC is broken
     */
    @Test
    public void testNewTemperatureNtcBroken() {
        when(battery.getCurrentCapacity()).thenReturn(100);
        when(ntc.isBroken()).thenReturn(true);

        ntcMonitoringTask.newTemp();

        verify(ntc, times(1)).reset();
        verify(ntc, never()).modifyTemperature();
    }

    /**
     * Test the new temperature method.
     * The method should modify the temperature if the battery is not low
     * and the NTC is not broken
     */
    @Test
    public void testNewTemperatureNormalConditions() {
        when(battery.getCurrentCapacity()).thenReturn(100);
        when(ntc.isBroken()).thenReturn(false);

        ntcMonitoringTask.newTemp();

        verify(ntc, times(1)).getTemperature();
    }


}
