package com.univr.pump.insulinpump.service;

import com.univr.pump.insulinpump.sensors.Battery;
import com.univr.pump.insulinpump.sensors.Heart;
import com.univr.pump.insulinpump.sensors.InsulinPump;
import com.univr.pump.insulinpump.sensors.NTC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SensorsService {

    private final Battery battery;
    private final NTC ntc;
    private final Heart heart;
    private final InsulinPump insulinPump;

    public SensorsService(Battery battery, NTC ntc, Heart heart, InsulinPump insulinPump) {
        this.battery = battery;
        this.ntc = ntc;
        this.heart = heart;
        this.insulinPump = insulinPump;
    }

    @Scheduled(fixedRate = 5000)
    public void decrBattery() {
        battery.discharge();
        System.out.println("Battery: " + battery.getCurrentCapacity());
    }

    @Scheduled(fixedRate = 5000)
    public void newPressure() {
        if(battery.getCurrentCapacity() == 0) {
            return;
        }
        heart.modifyPressure();
        System.out.println("Diastolic pressure: " + heart.getPressureDiastolic());
        System.out.println("Systolic pressure: " + heart.getPressureSystolic());
    }


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
