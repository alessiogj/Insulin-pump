package com.univr.pump.insulinpump.dto;

/**
 * This class includes the data transfer object for the vital parameters
 * and the patient id associated with them.
 */
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
        this.id = id.toString();
    }

    public String getId() {
        return id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getBloodPressureSystolic() {
        return bloodPressureSystolic;
    }

    public String getBloodPressureDiastolic() {
        return bloodPressureDiastolic;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public String getBloodSugarLevel() {
        return bloodSugarLevel;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setBloodPressureSystolic(String bloodPressureSystolic) {
        this.bloodPressureSystolic = bloodPressureSystolic;
    }

    public void setBloodPressureDiastolic(String bloodPressureDiastolic) {
        this.bloodPressureDiastolic = bloodPressureDiastolic;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public void setBloodSugarLevel(String bloodSugarLevel) {
        this.bloodSugarLevel = bloodSugarLevel;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

}
