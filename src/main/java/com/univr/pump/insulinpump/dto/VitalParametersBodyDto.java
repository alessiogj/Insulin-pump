package com.univr.pump.insulinpump.dto;

public class VitalParametersBodyDto {
    private String timestamp;
    private String bloodPressure;
    private String heartRate;
    private String bloodSugarLevel;
    private String temperature;
    private String patientId;

    public VitalParametersBodyDto() {
    }

    public VitalParametersBodyDto(String timestamp
            , String bloodPressure
            , String heartRate
            , String bloodSugarLevel
            , String temperature
            , String patient) {
        this.timestamp = timestamp;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.bloodSugarLevel = bloodSugarLevel;
        this.temperature = temperature;
        this.patientId = patient;
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

    public void setPatient(String patient) {
        this.patientId = patient;
    }
}
