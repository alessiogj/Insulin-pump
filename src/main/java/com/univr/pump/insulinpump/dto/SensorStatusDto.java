package com.univr.pump.insulinpump.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SensorStatusDto {
    private int battery;
    private int tank;

    public SensorStatusDto(int battery, int tank) {
        this.battery = battery;
        this.tank = tank;
    }
}
