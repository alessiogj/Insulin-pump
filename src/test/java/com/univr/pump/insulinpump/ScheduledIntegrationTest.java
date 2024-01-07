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

    @InjectMocks
    private BatteryMonitoringTask batteryMonitoringTask;

    @InjectMocks
    private VitalParametersMonitoringTask vitalParametersMonitoringTask;

    @InjectMocks
    private NtcMonitoringTask ntcMonitoringTask;

    @Test
    public void testDecrBattery() {
        batteryMonitoringTask.decrBattery();
        verify(battery, times(1)).discharge();
    }

    @Test
    public void testModifyVitalParameters() {
        vitalParametersMonitoringTask.modifyVitalParameters();
        verify(heart, times(1)).modifyPressure();
        verify(insulinPump, times(1)).modifyBloodGlucose();
    }

    @Test
    public void testNewVitalSignsNormalConditions() {
        vitalParametersMonitoringTask.newVitalSigns();
        //TODO
    }

    @Test
    public void testNewTemperatureNormalConditions() {
        ntcMonitoringTask.newTemp();
        //TODO
    }

}
