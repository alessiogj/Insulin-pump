package com.univr.pump.insulinpump.dto;

public class VitalParametersBodyDto {
    private String bloodPressure;
    private String heartRate;
    private String bloodSugarLevel;
    private String temperature;

    public VitalParametersBodyDto() {
    }

    public VitalParametersBodyDto(String timestamp
            , String bloodPressure
            , String heartRate
            , String bloodSugarLevel
            , String temperature) {
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.bloodSugarLevel = bloodSugarLevel;
        this.temperature = temperature;
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
