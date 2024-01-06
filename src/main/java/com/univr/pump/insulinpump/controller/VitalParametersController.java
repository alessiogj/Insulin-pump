package com.univr.pump.insulinpump.controller;

import com.univr.pump.insulinpump.dto.DateInterval;
import com.univr.pump.insulinpump.dto.VitalParametersBodyDto;
import com.univr.pump.insulinpump.dto.VitalParametersDto;
import com.univr.pump.insulinpump.service.VitalParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vitalparameters")
public class VitalParametersController {

    @Autowired
    private VitalParametersService vitalParametersService;

    /**
     * The system uses this API to add a vital parameter
     * @param vitalParametersDto
     * @return added vital parameter
     */
    @PostMapping("/")
    public VitalParametersDto addVitalParameters(@RequestBody VitalParametersBodyDto vitalParametersDto) {
        return vitalParametersService.addVitalParameters(vitalParametersDto);
    }

    /**
     * The system uses this API to get all vital parameters
     * @return all vital parameters
     */
    @GetMapping("/")
    public Iterable<VitalParametersDto> getVitalParameters() {
        return vitalParametersService.getAllVitalParameters();
    }

    /**
     * The system uses this API to get the last vital parameter
     * @return last vital parameter
     */
    @GetMapping("/last")
    public VitalParametersDto getLastVitalParameters() {
        return vitalParametersService.getLastVitalParameters();
    }

    /**
     * The system uses this API to get vital parameters of a patient in a given time interval
     * @param dateInterval
     * @return vital parameters of a patient in a given time interval
     */
    @PostMapping("/searchdateinterval")
    public Iterable<VitalParametersDto> searchByTimeInterval(@RequestBody DateInterval dateInterval) {
        return vitalParametersService.getVitalParametersByTimeInterval(dateInterval);
    }
}
