package com.univr.pump.insulinpump.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class VitalParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime timestamp;
    private Integer bloodPressure;
    private Integer heartRate;
    private Integer bloodSugarLevel;
    private Double temperature;

    public VitalParameters() {}

    public VitalParameters(LocalDateTime timestamp
            , Integer bloodPressure
            , Integer heartRate
            , Integer bloodSugarLevel
            , Double temperature) {
        this.timestamp = timestamp;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.bloodSugarLevel = bloodSugarLevel;
        this.temperature = temperature;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getBloodPressure() {
        return bloodPressure;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public Integer getBloodSugarLevel() {
        return bloodSugarLevel;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setBloodPressure(Integer bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public void setBloodSugarLevel(Integer bloodSugarLevel) {
        this.bloodSugarLevel = bloodSugarLevel;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

}