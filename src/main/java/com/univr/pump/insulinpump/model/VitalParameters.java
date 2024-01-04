package com.univr.pump.insulinpump.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class VitalParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate timestamp;
    private Double bloodPressure;
    private Double heartRate;
    private Double bloodSugarLevel;
    private Double temperature;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public VitalParameters() {}

    public VitalParameters(LocalDate timestamp
            , Double bloodPressure
            , Double heartRate
            , Double bloodSugarLevel
            , Double temperature
            , Patient patient) {
        this.timestamp = timestamp;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.bloodSugarLevel = bloodSugarLevel;
        this.temperature = temperature;
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public Double getBloodPressure() {
        return bloodPressure;
    }

    public Double getHeartRate() {
        return heartRate;
    }

    public Double getBloodSugarLevel() {
        return bloodSugarLevel;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public void setBloodPressure(Double bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public void setHeartRate(Double heartRate) {
        this.heartRate = heartRate;
    }

    public void setBloodSugarLevel(Double bloodSugarLevel) {
        this.bloodSugarLevel = bloodSugarLevel;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}