package com.univr.pump.insulinpump.controller;

import com.univr.pump.insulinpump.dto.PatientVitalParametersWithDateIntervalDto;
import com.univr.pump.insulinpump.dto.VitalParametersDto;
import com.univr.pump.insulinpump.service.VitalParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vitalparameters")
public class VitalParametersController {

    @Autowired
    private VitalParametersService vitalParametersService;

    @GetMapping("/")
    public Iterable<VitalParametersDto> getVitalParameters() {
        return vitalParametersService.getAllVitalParameters();
    }

    @GetMapping("/searchbyid/{id}")
    public Iterable<VitalParametersDto> searchById(@PathVariable Long id) {
        return vitalParametersService.getVitalParametersByPatientId(id);
    }

    @GetMapping("/searchbytimeinterval")
    public Iterable<VitalParametersDto> searchByTimeInterval(
            @RequestBody PatientVitalParametersWithDateIntervalDto patientVitalParametersWithDateIntervalDto) {
        return vitalParametersService.getVitalParametersByTimeInterval(patientVitalParametersWithDateIntervalDto);
    }
}
