package com.univr.pump.insulinpump.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class VitalParametersDto {
    private String id;
    private String timestamp;
    private String bloodPressureSystolic;
    private String bloodPressureDiastolic;
    private String heartRate;
    private String bloodSugarLevel;
    private String temperature;
}
