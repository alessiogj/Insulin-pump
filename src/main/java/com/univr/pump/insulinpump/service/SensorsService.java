package com.univr.pump.insulinpump.service;

import com.univr.pump.insulinpump.sensors.Battery;
import com.univr.pump.insulinpump.sensors.BloodPressure;
import com.univr.pump.insulinpump.sensors.NTC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SensorsService {

    private final Battery battery;
    private final NTC ntc;
    private final BloodPressure bloodPressure;

    public SensorsService(Battery battery, NTC ntc, BloodPressure bloodPressure) {
        this.battery = battery;
        this.ntc = ntc;
        this.bloodPressure = bloodPressure;
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
        bloodPressure.modifyPressure();
        System.out.println("Diastolic pressure: " + bloodPressure.getPressureDiastolic());
        System.out.println("Systolic pressure: " + bloodPressure.getPressureSystolic());
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


}
