package com.univr.pump.insulinpump.controller;

import com.univr.pump.insulinpump.dto.SensorStatusDto;

import com.univr.pump.insulinpump.model.Battery;
import com.univr.pump.insulinpump.mock.sensors.InsulinPump;
import com.univr.pump.insulinpump.service.BatteryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensors")
public class SensorsController {

    private final BatteryService batteryService;
    private final InsulinPump insulinPump;

    public SensorsController(BatteryService batteryService, InsulinPump insulinPump) {
        this.batteryService = batteryService;
        this.insulinPump = insulinPump;
    }

    /**
     * The system uses this API to replace the battery
     */
    @PutMapping("/battery/replace")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    public void chargeBattery() {
        batteryService.chargeBattery();
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
                batteryService.getBatteryLevel(),
                insulinPump.getCurrentTankLevel());
    }
}
