package com.univr.pump.insulinpump.dto;

import java.time.LocalDate;

public class PatientDto {
    private String id;
    private String name;
    private String surname;
    private String birthDate;
    private String fiscalCode;
    private String weight;
    private String height;
    private String diabetesType;



    public PatientDto() {
    }

    public PatientDto(String id
            , String name
            , String surname
            , String birthDate
            , String fiscalCode
            , String weight
            , String height
            , String diabetesType) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.fiscalCode = fiscalCode;
        this.weight = weight;
        this.height = height;
        this.diabetesType = diabetesType;
    }

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public void setFiscalCode(String fiscalCode){
        this.fiscalCode = fiscalCode;
    }

    public void setDiabetesType(String diabetesType){
        this.diabetesType = diabetesType;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
