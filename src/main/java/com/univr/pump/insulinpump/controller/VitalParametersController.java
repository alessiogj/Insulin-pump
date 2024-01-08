package com.univr.pump.insulinpump.controller;

import com.univr.pump.insulinpump.dto.DateIntervalDto;
import com.univr.pump.insulinpump.dto.VitalParametersDto;
import com.univr.pump.insulinpump.service.VitalParametersService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vitalparameters")
public class VitalParametersController {


    private final VitalParametersService vitalParametersService;

    public VitalParametersController(VitalParametersService vitalParametersService) {
        this.vitalParametersService = vitalParametersService;
    }

    /**
     * The system uses this API to get vital parameters of a patient in a given time interval
     * @param dateInterval
     * @return vital parameters of a patient in a given time interval
     */
    @PostMapping("/date")
    public Iterable<VitalParametersDto> searchByTimeInterval(@RequestBody DateIntervalDto dateInterval) {
        return vitalParametersService.getVitalParametersByTimeInterval(dateInterval);
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
     * The system uses this API to remove all vital parameters
     * @return void
     */
    @DeleteMapping("/")
    public void deleteAllVitalParameters() {
        vitalParametersService.deleteAllVitalParameters();
    }

}
