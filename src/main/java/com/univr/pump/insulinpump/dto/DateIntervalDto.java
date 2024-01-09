package com.univr.pump.insulinpump.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateIntervalDto {
    private String startDate;
    private String endDate;

    public DateIntervalDto() {
    }

    public DateIntervalDto(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
