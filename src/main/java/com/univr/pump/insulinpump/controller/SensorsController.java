package com.univr.pump.insulinpump.controller;

import com.univr.pump.insulinpump.dto.SensorStatusDto;

import com.univr.pump.insulinpump.sensors.Battery;
import com.univr.pump.insulinpump.sensors.Heart;
import com.univr.pump.insulinpump.sensors.InsulinPump;
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
    private final InsulinPump insulinPump;

    public SensorsController(Battery battery, NTC ntc, InsulinPump insulinPump) {
        this.battery = battery;
        this.ntc = ntc;
        this.insulinPump = insulinPump;
    }

    @GetMapping("/insulinpump")
    public int getInsulinPump() {
        return insulinPump.getCurrentTankLevel();
    }

    @GetMapping("/battery")
    public int getBattery() {
        return battery.getCurrentCapacity();
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

    @PostMapping("/insulinpump/refill")
    public void refillInsulinPump() {
        insulinPump.refill();
    }

    @GetMapping("/status")
    public SensorStatusDto getStatus() {
        return new SensorStatusDto(
                battery.getCurrentCapacity(),
                insulinPump.getCurrentTankLevel(),
                ntc.isBroken());
    }
}
