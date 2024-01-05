package com.univr.pump.insulinpump.controller;

import com.univr.pump.insulinpump.dto.VitalParametersWithDateIntervalDto;
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
     * Il sistema utilizza tale API per aggiungere un nuovo parametro vitale
     * @param vitalParametersDto
     * @return added vital parameter
     */
    @PostMapping("/")
    public VitalParametersDto addVitalParameters(@RequestBody VitalParametersBodyDto vitalParametersDto) {
        return vitalParametersService.addVitalParameters(vitalParametersDto);
    }

    /**
     * Il sistema utilizza tale API per ottenere tutti i parametri vitali
     * @return all vital parameters
     */
    @GetMapping("/")
    public Iterable<VitalParametersDto> getVitalParameters() {
        return vitalParametersService.getAllVitalParameters();
    }

    /**
     * Ritorna l'ultimo parametro vitale inserito
     * @return
     */
    @GetMapping("/last")
    public VitalParametersDto getLastVitalParameters() {
        return vitalParametersService.getLastVitalParameters();
    }

    @GetMapping("/searchbytimeinterval")
    public Iterable<VitalParametersDto> searchByTimeInterval(
            @RequestBody VitalParametersWithDateIntervalDto patientVitalParametersWithDateIntervalDto) {
        return vitalParametersService.getVitalParametersByTimeInterval(patientVitalParametersWithDateIntervalDto);
    }
}
