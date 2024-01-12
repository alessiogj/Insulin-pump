package com.univr.pump.insulinpump.scheduled;

import com.univr.pump.insulinpump.mock.Patient;
import com.univr.pump.insulinpump.service.InsulinMachineService;
import com.univr.pump.insulinpump.service.VitalParametersService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class VitalParametersMonitoringTask {


    private final Patient patient;

    public VitalParametersMonitoringTask(Patient patient) {
        this.patient = patient;
    }

    /**
     * Simulate the modification of vital parameters
     * (blood pressure, heart rate, blood glucose, temperature)
     */
    @Scheduled(fixedRate = 5000)
    public void modifyVitalParameters() {
        patient.modifyPressure();
        patient.modifyBloodGlucose();
        patient.modifyTemperature();
    }
}
