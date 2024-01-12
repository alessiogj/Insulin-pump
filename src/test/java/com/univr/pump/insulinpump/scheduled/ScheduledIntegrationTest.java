package com.univr.pump.insulinpump.scheduled;

import com.univr.pump.insulinpump.InsulinPumpApplication;
import com.univr.pump.insulinpump.mock.Patient;
import com.univr.pump.insulinpump.model.InsulinMachine;
import com.univr.pump.insulinpump.repository.InsulinMachineRepository;
import com.univr.pump.insulinpump.repository.VitalParametersRepository;
import com.univr.pump.insulinpump.service.InsulinMachineService;
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
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsulinPumpApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ScheduledIntegrationTest {

    @Mock
    private InsulinMachineService insulinMachineService;

    @Mock
    private Patient patient;

    @Mock
    private VitalParametersService vitalParametersService;

    @InjectMocks
    private InsulinMachineMonitoringTask insulinMachineMonitoringTask;

    @InjectMocks
    private VitalParametersMonitoringTask vitalParametersMonitoringTask;

    @Autowired
    private InsulinMachineRepository insulinMachineRepository;

    @Autowired
    private VitalParametersRepository vitalParametersRepository;

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
    //TODO: fix this test
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testDecrBatteryOnce() {
        // Save the insulin machine
        InsulinMachine insulinMachine = new InsulinMachine();
        insulinMachineRepository.save(insulinMachine);
        when(insulinMachineService.getBatteryLevel()).thenReturn(100);
        insulinMachineMonitoringTask.decrBattery();
        verify(insulinMachineService, times(1)).decrBattery();
    }

    /**
     * Test the battery monitoring task.
     * The method should not decrease the battery capacity
     * when the battery is low and the battery level should
     * be 0
     */
    @Test
    public void testDecrBatteryBatteryLow() {
        when(insulinMachineService.getBatteryLevel()).thenReturn(0);
        insulinMachineMonitoringTask.decrBattery();
        verify(insulinMachineService, never()).decrBattery();
        assert insulinMachineService.getBatteryLevel() == 0;
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
        when(insulinMachineService.getBatteryLevel()).thenReturn(0);

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
        when(insulinMachineService.getBatteryLevel()).thenReturn(100);

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
        when(insulinMachineService.getBatteryLevel()).thenReturn(100);
        when(insulinMachineService.getInsulinLevel()).thenReturn(100);

        insulinMachineMonitoringTask.insulinPump();

        verify(insulinMachineService, times(1)).injectInsulin();
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
        when(insulinMachineService.getBatteryLevel()).thenReturn(100);
        when(insulinMachineService.getInsulinLevel()).thenReturn(100);

        insulinMachineMonitoringTask.insulinPump();

        verify(insulinMachineService, never()).injectInsulin();
    }

    /**
     * Test inject insulin method when the glucose level is high,
     * the insulin pump has not enough insulin in the tank and
     * the battery is not low
     * The method should not inject insulin
     */
    @Test
    public void testInsulinInjectionWhenInsulinTankEmpty() {
        when(patient.getGlucoseLevel()).thenReturn(100);
        when(insulinMachineService.getBatteryLevel()).thenReturn(100);
        when(insulinMachineService.getInsulinLevel()).thenReturn(0);

        insulinMachineMonitoringTask.insulinPump();

        verify(insulinMachineService, never()).injectInsulin();
    }

    /**
     * Test inject insulin method when the glucose level is high,
     * the insulin pump has enough insulin in the tank and
     * the battery is low
     * The method should not inject insulin
     */
    @Test
    public void testInsulinInjectionWhenBatteryLow() {
        when(patient.getGlucoseLevel()).thenReturn(100);
        when(insulinMachineService.getBatteryLevel()).thenReturn(0);
        when(insulinMachineService.getInsulinLevel()).thenReturn(100);

        insulinMachineMonitoringTask.insulinPump();

        verify(insulinMachineService, never()).injectInsulin();
    }

}
