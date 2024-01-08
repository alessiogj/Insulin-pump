package com.univr.pump.insulinpump.scheduled;

import com.univr.pump.insulinpump.mock.Patient;
import com.univr.pump.insulinpump.mock.sensors.Battery;
import com.univr.pump.insulinpump.service.VitalParametersService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class VitalParametersMonitoringTask {

    private final VitalParametersService vitalParametersService;

    private final Patient patient;
    private final Battery battery;

    public VitalParametersMonitoringTask(VitalParametersService vitalParametersService,
                                         Patient patient,
                                         Battery battery) {
        this.vitalParametersService = vitalParametersService;
        this.patient = patient;
        this.battery = battery;
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
        System.out.println("Diastolic pressure: " + patient.getPressureDiastolic());
        System.out.println("Systolic pressure: " + patient.getPressureSystolic());
        System.out.println("Blood glucose: " + patient.getGlucoseLevel());
        System.out.println("Temperature: " + patient.getTemperature());
    }

    /**
     * Ogni 10 minuti effettua una misurazione dei parametri vitali del paziente.
     * Se la batteria è scarica la misurazione
     * non viene effettuata.
     */
    @Scheduled(fixedRate = 1000)
    public void newVitalSigns() {
        if(battery.getCurrentCapacity() == 0) {
            return;
        }

        vitalParametersService.saveVitalParameters(
                patient.getPressureSystolic(),
                patient.getPressureDiastolic(),
                patient.getHeartRate(),
                patient.getGlucoseLevel(),
                patient.getTemperature()
        );
    }
}
