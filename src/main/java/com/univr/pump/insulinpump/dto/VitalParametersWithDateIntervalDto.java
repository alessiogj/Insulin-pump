package com.univr.pump.insulinpump.dto;

public class VitalParametersWithDateIntervalDto {
    private String startDate;
    private String endDate;

    public VitalParametersWithDateIntervalDto() {
    }

    public VitalParametersWithDateIntervalDto(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
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
