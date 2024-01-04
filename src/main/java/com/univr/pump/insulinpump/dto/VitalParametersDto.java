package com.univr.pump.insulinpump.dto;

/**
 * This class includes the data transfer object for the vital parameters
 * and the patient id associated with them.
 */
public class VitalParametersDto {
    private String id;
    private String timestamp;
    private String bloodPressure;
    private String heartRate;
    private String bloodSugarLevel;
    private String temperature;
    private String patientId;

    public VitalParametersDto() {
    }

    public VitalParametersDto(String id
            , String timestamp
            , String bloodPressure
            , String heartRate
            , String bloodSugarLevel
            , String temperature
            , String patientId) {
        this.id = id;
        this.timestamp = timestamp;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.bloodSugarLevel = bloodSugarLevel;
        this.temperature = temperature;
        this.patientId = patientId;
    }

    public String getId() {
        return id;
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

    public String getPatientId() {
        return patientId;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

}
