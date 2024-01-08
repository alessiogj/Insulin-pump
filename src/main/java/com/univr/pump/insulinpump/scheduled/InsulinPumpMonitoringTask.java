package com.univr.pump.insulinpump.scheduled;

import com.univr.pump.insulinpump.mock.Patient;
import com.univr.pump.insulinpump.mock.sensors.Battery;
import com.univr.pump.insulinpump.mock.sensors.InsulinPump;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class InsulinPumpMonitoringTask {

    private final InsulinPump insulinPump;
    private final Battery battery;
    private final Patient patient;

    /**
     * Simulates the insulin pump monitoring.
     * If the current glucose level is higher than 140, the insulin pump injects insulin.
     */
    public InsulinPumpMonitoringTask(InsulinPump insulinPump, Battery battery, Patient patient) {
        this.insulinPump = insulinPump;
        this.battery = battery;
        this.patient = patient;
    }

    /**
     * Simulates the insulin pump monitoring.
     * If the current glucose level is higher than 130 and the insulin pump has enough insulin
     * the insulin pump injects insulin.
     */
    @Scheduled(fixedRate = 5000)
    public void insulinPump() {
        if(battery.getCurrentCapacity() == 0) {
            return;
        }
        if(patient.getGlucoseLevel() > 130 && insulinPump.getCurrentTankLevel() > 0) {
            insulinPump.injectInsulin();
        }
    }
}
