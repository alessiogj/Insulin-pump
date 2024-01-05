package com.univr.pump.insulinpump.dto;

public class PatientBodyDto {
    private String name;
    private String surname;
    private String birthDate;
    private String fiscalCode;
    private String weight;
    private String height;
    private String diabetesType;

    public PatientBodyDto() {
    }

    public PatientBodyDto(String name
            , String surname
            , String birthDate
            , String fiscalCode
            , String weight
            , String height
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

    public String getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }
}
