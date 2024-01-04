package com.univr.pump.insulinpump.dto;


import java.util.Date;

public class PatientVitalParametersWithDateIntervalDto {
    private Long patientId;
    private Date startDate;
    private Date endDate;

    public PatientVitalParametersWithDateIntervalDto() {
    }

    public PatientVitalParametersWithDateIntervalDto(Long patientId, Date startDate, Date endDate) {
        this.patientId = patientId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Date getFrom() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getTo() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
