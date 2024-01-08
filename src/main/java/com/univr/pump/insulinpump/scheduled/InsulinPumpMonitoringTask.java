package com.univr.pump.insulinpump.scheduled;

import com.univr.pump.insulinpump.mock.Patient;
import com.univr.pump.insulinpump.model.Battery;
import com.univr.pump.insulinpump.mock.sensors.InsulinPump;
import com.univr.pump.insulinpump.service.BatteryService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class InsulinPumpMonitoringTask {

    private final InsulinPump insulinPump;
    private final BatteryService batteryService;
    private final Patient patient;

    public InsulinPumpMonitoringTask(InsulinPump insulinPump, BatteryService batteryService, Patient patient) {
        this.insulinPump = insulinPump;
        this.batteryService = batteryService;
        this.patient = patient;
    }

    /**
     * Simulates the insulin pump monitoring.
     * If the current glucose level is higher than 130 and the insulin pump has enough insulin
     * the insulin pump injects insulin.
     */
    @Scheduled(fixedRate = 5000)
    public void insulinPump() {
        if(batteryService.getBatteryLevel() == 0) {
            return;
        }
        if((patient.getGlucoseLevel() > 130) && (insulinPump.getCurrentTankLevel() > 0)) {
            insulinPump.injectInsulin();
        }
    }
}
