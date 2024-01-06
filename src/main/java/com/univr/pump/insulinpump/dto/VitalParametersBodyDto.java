package com.univr.pump.insulinpump.dto;

public class VitalParametersBodyDto {
    private String bloodPressureSystolic;
    private String bloodPressureDiastolic;
    private String heartRate;
    private String bloodSugarLevel;
    private String temperature;

    public VitalParametersBodyDto() {
    }

    public VitalParametersBodyDto(String timestamp
            , String bloodPressureSystolic
            , String bloodPressureDiastolic
            , String heartRate
            , String bloodSugarLevel
            , String temperature) {
        this.bloodPressureSystolic = bloodPressureSystolic;
        this.bloodPressureDiastolic = bloodPressureDiastolic;
        this.heartRate = heartRate;
        this.bloodSugarLevel = bloodSugarLevel;
        this.temperature = temperature;
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
