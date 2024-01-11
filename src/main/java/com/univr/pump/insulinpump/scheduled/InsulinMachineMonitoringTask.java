package com.univr.pump.insulinpump.scheduled;

import com.univr.pump.insulinpump.mock.Patient;
import com.univr.pump.insulinpump.service.InsulinMachineService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class InsulinMachineMonitoringTask {
    private final Patient patient;
    private final InsulinMachineService insulinMachineService;

    public InsulinMachineMonitoringTask(Patient patient,
                                        InsulinMachineService insulinMachineService) {
        this.patient = patient;
        this.insulinMachineService = insulinMachineService;
    }

    /**
     * Simulates the battery discharge.
     * The battery is discharged by 1% every 10 minutes.
     */
    @Scheduled(fixedRate = 60000)
    public void decrBattery() {
        if (insulinMachineService.getBatteryLevel() > 0) {
            insulinMachineService.decrBattery();
        }
        System.out.println("Battery: " + insulinMachineService.getBatteryLevel());
    }

    /**
     * Simulates the insulin pump monitoring.
     * If the current glucose level is higher than 130 and the insulin pump has enough insulin
     * the insulin pump injects insulin.
     */
    @Scheduled(fixedRate = 5000)
    public void insulinPump() {
        if(insulinMachineService.getBatteryLevel() == 0) {
            return;
        }
        if((patient.getGlucoseLevel() > 130) && (insulinMachineService.getInsulinLevel() > 0)) {
            insulinMachineService.injectInsulin();
            patient.setGlucoseToNormal();
        }
    }

}
