package com.univr.pump.insulinpump.scheduled;

import com.univr.pump.insulinpump.sensors.Battery;
import com.univr.pump.insulinpump.sensors.InsulinPump;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class InsulinPumpMonitoringTask {

    private final InsulinPump insulinPump;
    private final Battery battery;

    /**
     * Simulates the insulin pump monitoring.
     * If the current glucose level is higher than 140, the insulin pump injects insulin.
     */
    public InsulinPumpMonitoringTask(InsulinPump insulinPump, Battery battery) {
        this.insulinPump = insulinPump;
        this.battery = battery;
    }

    /**
     * Simulates the insulin pump monitoring.
     * If the current glucose level is higher than 140, the insulin pump injects insulin.
     */
    @Scheduled(fixedRate = 5000)
    public void insulinPump() {
        if(battery.getCurrentCapacity() == 0) {
            return;
        }
        int currentGlucoseLevel = insulinPump.getCurrentGlucoseLevel();
        if(currentGlucoseLevel > 140) {
            insulinPump.injectInsulin();
            System.out.println("Insulin injected");
        }
    }
}
