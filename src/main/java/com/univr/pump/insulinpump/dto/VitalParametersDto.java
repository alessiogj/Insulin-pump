package com.univr.pump.insulinpump.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * This class includes the data transfer object for the vital parameters
 * and the patient id associated with them.
 */
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
