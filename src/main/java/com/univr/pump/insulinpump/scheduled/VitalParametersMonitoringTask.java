package com.univr.pump.insulinpump.scheduled;

import com.univr.pump.insulinpump.sensors.Battery;
import com.univr.pump.insulinpump.sensors.Heart;
import com.univr.pump.insulinpump.sensors.InsulinPump;
import com.univr.pump.insulinpump.sensors.NTC;
import com.univr.pump.insulinpump.service.VitalParametersService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class VitalParametersMonitoringTask {

    private final VitalParametersService vitalParametersService;

    private final Heart heart;
    private final InsulinPump insulinPump;
    private final Battery battery;
    private final NTC ntc;

    public VitalParametersMonitoringTask(VitalParametersService vitalParametersService, Heart heart, InsulinPump insulinPump, Battery battery, NTC ntc) {
        this.vitalParametersService = vitalParametersService;
        this.heart = heart;
        this.insulinPump = insulinPump;
        this.battery = battery;
        this.ntc = ntc;
    }

    /**
     * Simulate the modification of vital parameters
     * (blood pressure, heart rate, blood glucose, temperature)
     */
    @Scheduled(fixedRate = 5000)
    public void modifyVitalParameters() {
        heart.modifyPressure();
        insulinPump.modifyBloodGlucose();
        ntc.modifyTemperature();
        System.out.println("Diastolic pressure: " + heart.getPressureDiastolic());
        System.out.println("Systolic pressure: " + heart.getPressureSystolic());
        System.out.println("Blood glucose: " + insulinPump.getCurrentGlucoseLevel());
        System.out.println("Temperature: " + ntc.getTemperature());
    }

    /**
     * Ogni 10 minuti effettua una misurazione dei parametri vitali del paziente.
     * Se la batteria è scarica o il sensore di temperatura è rotto, la misurazione
     * non viene effettuata.
     */
    @Scheduled(fixedRate = 1000)
    public void newVitalSigns() {
        if(battery.getCurrentCapacity() == 0 || ntc.isBroken()) {
            ntc.reset();
            return;
        }

        vitalParametersService.saveHeartParameters(
                heart.getPressureSystolic(),
                heart.getPressureDiastolic(),
                heart.getHeartRate(),
                insulinPump.getCurrentGlucoseLevel(),
                ntc.getTemperature()
        );
    }
}
