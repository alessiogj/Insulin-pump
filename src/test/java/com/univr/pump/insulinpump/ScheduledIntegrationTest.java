package com.univr.pump.insulinpump;

import com.univr.pump.insulinpump.mock.Patient;
import com.univr.pump.insulinpump.scheduled.BatteryMonitoringTask;
import com.univr.pump.insulinpump.scheduled.InsulinPumpMonitoringTask;
import com.univr.pump.insulinpump.scheduled.VitalParametersMonitoringTask;
import com.univr.pump.insulinpump.mock.sensors.Battery;
import com.univr.pump.insulinpump.mock.sensors.InsulinPump;
import com.univr.pump.insulinpump.service.VitalParametersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsulinPumpApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ScheduledIntegrationTest {
    @Mock
    private Battery battery;

    @Mock
    private Patient patient;

    @Mock
    private InsulinPump insulinPump;

    @Mock
    private VitalParametersService vitalParametersService;

    @InjectMocks
    private BatteryMonitoringTask batteryMonitoringTask;

    @InjectMocks
    private VitalParametersMonitoringTask vitalParametersMonitoringTask;

    @InjectMocks
    private InsulinPumpMonitoringTask insulinPumpMonitoringTask;

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
        verify(patient, times(1)).modifyPressure();
        verify(patient, times(1)).modifyBloodGlucose();
        verify(patient, times(1)).modifyTemperature();
    }

    /**
     * Test the new vital signs method.
     * The method should not save the vital signs if the battery is low
     */
    @Test
    public void testNewVitalSignsBatteryLow() {
        when(battery.getCurrentCapacity()).thenReturn(0);

        vitalParametersMonitoringTask.newVitalSigns();

        verify(vitalParametersService, never()).saveVitalParameters(
                anyInt(),
                anyInt(),
                anyInt(),
                anyInt(),
                anyDouble());
    }

    /**
     * Test the new vital signs method.
     * The method should save the vital signs if the battery is not low
     */
    @Test
    public void testNewVitalSignsNormalConditions() {
        when(battery.getCurrentCapacity()).thenReturn(100);

        vitalParametersMonitoringTask.newVitalSigns();

        verify(patient, times(1)).getHeartRate();
        verify(patient, times(1)).getPressureDiastolic();
        verify(patient, times(1)).getPressureSystolic();
        verify(patient, times(1)).getGlucoseLevel();
        verify(patient, times(1)).getTemperature();
    }

    /**
     * Test inject insulin method when the glucose level is high,
     * the insulin pump has enough insulin in the tank and
     * the battery is not low
     * The method should inject insulin
     */
    @Test
    public void testInsulinInjectionWhenGlucoseLevelHigh() {

    }



}
