package com.univr.pump.insulinpump.model;

import com.univr.pump.insulinpump.utils.DIABETES_TYPE;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    private String name;
    private String surname;
    private Date birthDate;
    private String fiscalCode;
    private double weight;
    private double height;
    private DIABETES_TYPE diabetesType;

    public Patient() {}

    public Patient(String name
            , String surname
            , Date birthDate
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
    }
}
