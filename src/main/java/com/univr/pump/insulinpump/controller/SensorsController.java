package com.univr.pump.insulinpump.controller;

import com.univr.pump.insulinpump.dto.SensorStatusDto;
import com.univr.pump.insulinpump.model.Battery;
import com.univr.pump.insulinpump.model.NTC;
import com.univr.pump.insulinpump.model.Tank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensors")
public class SensorsController {

    @Autowired
    private Battery battery;

    @Autowired
    private Tank tank;

    @Autowired
    private NTC ntc;

    @GetMapping("/battery")
    public int getBattery() {
        return battery.getCurrentCapacity();
    }

    @GetMapping("/tank")
    public int getTank() {
        return tank.getCurrentCapacity();
    }

    @GetMapping("/ntc")
    public double getNtc() {
        return ntc.getTemperature();
    }

    @GetMapping("/ntc/status")
    public boolean getNtcStatus() {
        return ntc.isBroken();
    }

    @PostMapping("/battery/replace")
    public void chargeBattery() {
        battery.charge();
    }

    @PostMapping("/tank/fill")
    public void fillTank() {
        tank.fill();
    }

    @PostMapping("/ntc/repair")
    public void repairNtc() {
        ntc.repair();
    }

    @GetMapping("/status")
    public SensorStatusDto getStatus() {
        return new SensorStatusDto(
                battery.getCurrentCapacity(),
                tank.getCurrentCapacity(),
                ntc.getTemperature(),
                ntc.isBroken());
    }
}
