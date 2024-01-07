package com.univr.pump.insulinpump.scheduled;

import com.univr.pump.insulinpump.sensors.Battery;
import com.univr.pump.insulinpump.sensors.NTC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NtcMonitoringTask {
    private final NTC ntc;
    private final Battery battery;

    public NtcMonitoringTask(NTC ntc, Battery battery) {
        this.ntc = ntc;
        this.battery = battery;
    }

    /**
     * This method is called every 5 seconds.
     * It simulates the NTC sensor by modifying the temperature.
     * If the battery is empty, the NTC is reset.
     */
    @Scheduled(fixedRate = 5000)
    public void newTemp() {
        if(battery.getCurrentCapacity() == 0) {
            ntc.reset();
            return;
        }
        ntc.modifyTemperature();
        if(ntc.isBroken())
            System.out.println("NTC is broken");
        else
            System.out.println("NTC: " + ntc.getTemperature());
    }
}
