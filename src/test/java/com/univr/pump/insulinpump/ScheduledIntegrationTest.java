package com.univr.pump.insulinpump;

import com.univr.pump.insulinpump.mock.Patient;
import com.univr.pump.insulinpump.repository.BatteryRepository;
import com.univr.pump.insulinpump.scheduled.BatteryMonitoringTask;
import com.univr.pump.insulinpump.scheduled.InsulinPumpMonitoringTask;
import com.univr.pump.insulinpump.scheduled.VitalParametersMonitoringTask;
import com.univr.pump.insulinpump.model.Battery;
import com.univr.pump.insulinpump.mock.sensors.InsulinPump;
import com.univr.pump.insulinpump.service.BatteryService;
import com.univr.pump.insulinpump.service.VitalParametersService;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsulinPumpApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ScheduledIntegrationTest {

    @Mock
    private BatteryService batteryService;

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

    @Autowired
    private BatteryRepository batteryRepository;

    @BeforeClass
    public static void setBaseUri() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    /**
     * Test the battery monitoring task.
     * The method should decrease the battery capacity
     * when the battery is not low, the id shouldn't
     * be different from the previous one
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testDecrBattery() {
        Long id = batteryRepository.findFirstByOrderByIdDesc().getId();
        when(batteryService.getBatteryLevel()).thenReturn(100);
        batteryMonitoringTask.decrBattery();
        verify(batteryService, times(1)).decrBattery();
        Long newId = batteryRepository.findFirstByOrderByIdDesc().getId();
        assert id.equals(newId);
    }

    /**
     * Test the battery monitoring task.
     * The method should not decrease the battery capacity
     * when the battery is low and the battery level should
     * be 0
     */
    @Test
    public void testDecrBatteryBatteryLow() {
        when(batteryService.getBatteryLevel()).thenReturn(0);
        batteryMonitoringTask.decrBattery();
        verify(batteryService, never()).decrBattery();
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
        when(batteryService.getBatteryLevel()).thenReturn(0);

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
        when(batteryService.getBatteryLevel()).thenReturn(100);

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
        when(patient.getGlucoseLevel()).thenReturn(200);
        when(batteryService.getBatteryLevel()).thenReturn(100);
        when(insulinPump.getCurrentTankLevel()).thenReturn(100);

        insulinPumpMonitoringTask.insulinPump();

        verify(insulinPump, times(1)).injectInsulin();
    }

    /**
     * Test inject insulin method when the glucose level is low,
     * the insulin pump has enough insulin in the tank and
     * the battery is not low
     * The method should not inject insulin
     */
    @Test
    public void testInsulinInjectionWhenGlucoseLevelLow() {
        when(patient.getGlucoseLevel()).thenReturn(80);
        when(batteryService.getBatteryLevel()).thenReturn(100);
        when(insulinPump.getCurrentTankLevel()).thenReturn(100);

        insulinPumpMonitoringTask.insulinPump();

        verify(insulinPump, never()).injectInsulin();
    }

    /**
     * Test inject insulin method when the glucose level is high,
     * the insulin pump has not enough insulin in the tank and
     * the battery is not low
     * The method should not inject insulin
     */
    @Test
    public void testInsulinInjectionWhenInsulinTankEmpty() {
        when(patient.getGlucoseLevel()).thenReturn(200);
        when(batteryService.getBatteryLevel()).thenReturn(100);
        when(insulinPump.getCurrentTankLevel()).thenReturn(0);

        insulinPumpMonitoringTask.insulinPump();

        verify(insulinPump, never()).injectInsulin();
    }

    /**
     * Test inject insulin method when the glucose level is high,
     * the insulin pump has enough insulin in the tank and
     * the battery is low
     * The method should not inject insulin
     */
    @Test
    public void testInsulinInjectionWhenBatteryLow() {
        when(patient.getGlucoseLevel()).thenReturn(200);
        when(batteryService.getBatteryLevel()).thenReturn(0);
        when(insulinPump.getCurrentTankLevel()).thenReturn(100);

        insulinPumpMonitoringTask.insulinPump();

        verify(insulinPump, never()).injectInsulin();
    }

}
