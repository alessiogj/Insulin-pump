package com.univr.pump.insulinpump.controller;

import com.univr.pump.insulinpump.dto.SensorStatusDto;

import com.univr.pump.insulinpump.sensors.Battery;
import com.univr.pump.insulinpump.sensors.BloodPressure;
import com.univr.pump.insulinpump.sensors.NTC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensors")
public class SensorsController {

    private final Battery battery;
    private final NTC ntc;
    private final BloodPressure bloodPressure;

    public SensorsController(Battery battery, NTC ntc, BloodPressure bloodPressure) {
        this.battery = battery;
        this.ntc = ntc;
        this.bloodPressure = bloodPressure;
    }

    @GetMapping("/battery")
    public int getBattery() {
        return battery.getCurrentCapacity();
    }

    @GetMapping("/ntc")
    public double getNtc() {
        return ntc.getTemperature();
    }

    @GetMapping("/blood/systolic")
    public int getBloodPreassure() {
        return bloodPressure.getPressureSystolic();
    }

    @GetMapping("/blood/diastolic")
    public int getBloodPreassureDiastolic() {
        return bloodPressure.getPressureDiastolic();
    }

    @GetMapping("/ntc/status")
    public boolean getNtcStatus() {
        return ntc.isBroken();
    }

    @PostMapping("/battery/replace")
    public void chargeBattery() {
        battery.charge();
    }

    @PostMapping("/ntc/repair")
    public void repairNtc() {
        ntc.repair();
    }

    @GetMapping("/status")
    public SensorStatusDto getStatus() {
        return new SensorStatusDto(
                battery.getCurrentCapacity(),
                0, //TODO: add capacity
                ntc.getTemperature(),
                ntc.isBroken(),
                bloodPressure.getPressureSystolic(),
                bloodPressure.getPressureDiastolic());
    }
}
