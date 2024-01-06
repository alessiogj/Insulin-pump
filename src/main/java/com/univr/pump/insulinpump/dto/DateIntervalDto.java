package com.univr.pump.insulinpump.dto;

public class DateIntervalDto {
    private String startDate;
    private String endDate;

    public DateIntervalDto() {
    }

    public DateIntervalDto(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
