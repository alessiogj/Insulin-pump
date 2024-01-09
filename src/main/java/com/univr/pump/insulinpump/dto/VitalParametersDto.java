package com.univr.pump.insulinpump.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * This class includes the data transfer object for the vital parameters
 * and the patient id associated with them.
 */
@Setter
@Getter
public class VitalParametersDto {
    private String id;
    private String timestamp;
    private String bloodPressureSystolic;
    private String bloodPressureDiastolic;
    private String heartRate;
    private String bloodSugarLevel;
    private String temperature;

    public VitalParametersDto() {
    }

    public VitalParametersDto(String id
            , String timestamp
            , String bloodPressureSystolic
            , String bloodPressureDiastolic
            , String heartRate
            , String bloodSugarLevel
            , String temperature) {
        this.timestamp = timestamp;
        this.bloodPressureSystolic = bloodPressureSystolic;
        this.bloodPressureDiastolic = bloodPressureDiastolic;
        this.heartRate = heartRate;
        this.bloodSugarLevel = bloodSugarLevel;
        this.temperature = temperature;
        this.id = id;
    }
}
