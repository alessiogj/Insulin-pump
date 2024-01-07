package com.univr.pump.insulinpump.scheduled;

import com.univr.pump.insulinpump.sensors.Battery;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatteryMonitoringTask {
    private final Battery battery;

    public BatteryMonitoringTask(Battery battery) {
        this.battery = battery;
    }

    /**
     * Simulates the battery discharge.
     * The battery is discharged by 1% every 5 seconds.
     */
    @Scheduled(fixedRate = 5000)
    public void decrBattery() {
        if (battery.getCurrentCapacity() > 0) {
            battery.discharge();
        }
        System.out.println("Battery: " + battery.getCurrentCapacity());
    }

}
