package com.univr.pump.insulinpump.model;

import com.univr.pump.insulinpump.utils.DIABETES_TYPE;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String fiscalCode;
    private double weight;
    private double height;
    private DIABETES_TYPE diabetesType;

    @OneToMany(mappedBy = "patient")
    private List<VitalParameters> vitalParameters;

    public Patient() {}

    public Patient(String name
            , String surname
            , LocalDate birthDate
            , String fiscalCode
            , double weight
            , double height
            , DIABETES_TYPE diabetesType) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.fiscalCode = fiscalCode;
        this.weight = weight;
        this.height = height;
        this.diabetesType = diabetesType;

        vitalParameters = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public DIABETES_TYPE getDiabetesType() {
        return diabetesType;
    }

    public List<VitalParameters> getVitalParameters() {
        return vitalParameters;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name=name;
    }

    public void setSurname(String surname) {
        this.surname=surname;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate=birthDate;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode=fiscalCode;
    }

    public void setWeight(double weight) {
        this.weight=weight;
    }

    public void setHeight(double height) {
        this.height=height;
    }

    public void setDiabetesType(DIABETES_TYPE diabetesType) {
        this.diabetesType=diabetesType;
    }

    /**
     * Set new vital parameters for the patient
     * @param vitalParameter
     */
    public void setVitalParameters(VitalParameters vitalParameter) {
        this.vitalParameters.add(vitalParameter);
    }
}
