package com.univr.pump.insulinpump.dto;

public class PatientVitalParametersWithDateIntervalDto {
    private String patientId;
    private String startDate;
    private String endDate;

    public PatientVitalParametersWithDateIntervalDto() {
    }

    public PatientVitalParametersWithDateIntervalDto(String patientId, String startDate, String endDate) {
        this.patientId = patientId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getFrom() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getTo() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
