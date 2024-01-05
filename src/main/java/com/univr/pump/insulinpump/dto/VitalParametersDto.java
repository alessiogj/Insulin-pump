package com.univr.pump.insulinpump.dto;

/**
 * This class includes the data transfer object for the vital parameters
 * and the patient id associated with them.
 */
public class VitalParametersDto {
    private String timestamp;
    private String bloodPressure;
    private String heartRate;
    private String bloodSugarLevel;
    private String temperature;

    public VitalParametersDto() {
    }

    public VitalParametersDto(String timestamp
            , String bloodPressure
            , String heartRate
            , String bloodSugarLevel
            , String temperature) {
        this.timestamp = timestamp;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.bloodSugarLevel = bloodSugarLevel;
        this.temperature = temperature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getBloodPressure() {
        return bloodPressure;
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

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
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
