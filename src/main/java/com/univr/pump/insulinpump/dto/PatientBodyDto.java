package com.univr.pump.insulinpump.dto;

public class PatientBodyDto {
    private String name;
    private String surname;
    private String birthDate;
    private String fiscalCode;
    private double weight;
    private double height;
    private String diabetesType;

    public PatientBodyDto() {
    }

    public PatientBodyDto(String name
            , String surname
            , String birthDate
            , String fiscalCode
            , double weight
            , double height
            , String diabetesType) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.fiscalCode = fiscalCode;
        this.weight = weight;
        this.height = height;
        this.diabetesType = diabetesType;
    }

    public String getName() {
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public String getDiabetesType() {
        return diabetesType;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }
}
