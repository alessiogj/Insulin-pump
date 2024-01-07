package com.univr.pump.insulinpump.controller;

import com.univr.pump.insulinpump.dto.SensorStatusDto;

import com.univr.pump.insulinpump.sensors.Battery;
import com.univr.pump.insulinpump.sensors.InsulinPump;
import com.univr.pump.insulinpump.sensors.NTC;
import org.springframework.web.bind.annotation.*;

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

    /**
     * The system uses this API to replace the battery
     */
    @PutMapping("/battery/replace")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    public void chargeBattery() {
        battery.charge();
    }

    /**
     * The system uses this API to repair the NTC
     */
    @PutMapping("/ntc/repair")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    public void repairNtc() {
        ntc.repair();
    }

    /**
     * The system uses this API to refill the insulin pump
     */
    @PutMapping("/tank/refill")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    public void refillInsulinPump() {
        insulinPump.refill();
    }

    /**
     * The system uses this API to get the status of the sensors
     * @return status of the sensors
     */
    @GetMapping("/status")
    public SensorStatusDto getStatus() {
        return new SensorStatusDto(
                battery.getCurrentCapacity(),
                insulinPump.getCurrentTankLevel(),
                ntc.isBroken());
    }
}
