package com.univr.pump.insulinpump.scheduled;

import com.univr.pump.insulinpump.service.BatteryService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatteryMonitoringTask {
    private final BatteryService batteryService;

    public BatteryMonitoringTask(BatteryService batteryService) {
        this.batteryService = batteryService;
    }

    /**
     * Simulates the battery discharge.
     * The battery is discharged by 1% every 10 minutes.
     */
    @Scheduled(fixedRate = 60000)
    public void decrBattery() {
        if (batteryService.getBatteryLevel() > 0) {
            batteryService.decrBattery();
        }
        System.out.println("Battery: " + batteryService.getBatteryLevel());
    }

}
