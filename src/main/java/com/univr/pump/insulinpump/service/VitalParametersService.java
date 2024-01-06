package com.univr.pump.insulinpump.service;

import com.univr.pump.insulinpump.dto.DateIntervalDto;
import com.univr.pump.insulinpump.dto.VitalParametersBodyDto;
import com.univr.pump.insulinpump.dto.VitalParametersDto;
import com.univr.pump.insulinpump.model.VitalParameters;
import com.univr.pump.insulinpump.repository.VitalParametersRepository;
import com.univr.pump.insulinpump.sensors.Battery;
import com.univr.pump.insulinpump.sensors.Heart;
import com.univr.pump.insulinpump.sensors.InsulinPump;
import com.univr.pump.insulinpump.sensors.NTC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VitalParametersService {

    private static final Logger log = LoggerFactory.getLogger(VitalParametersService.class);

    private final VitalParametersRepository vitalParametersRepository;

    private final Battery battery;
    private final NTC ntc;
    private final Heart heart;
    private final InsulinPump insulinPump;

    public VitalParametersService(VitalParametersRepository vitalParametersRepository, Battery battery, NTC ntc, Heart heart, InsulinPump insulinPump) {
        this.vitalParametersRepository = vitalParametersRepository;
        this.battery = battery;
        this.ntc = ntc;
        this.heart = heart;
        this.insulinPump = insulinPump;
    }

    /**
     * Simulations of the glucose level in the blood of a diabetic patient.
     */
    @Scheduled(fixedRate = 5000)
    public void newBloodGlucose() {
        insulinPump.modifyBloodGlucose();
        System.out.println("Blood glucose: " + insulinPump.getCurrentGlucoseLevel());
    }

    /**
     * Ogni 10 minuti effettua una misurazione dei parametri vitali del paziente.
     * Se la batteria è scarica o il sensore di temperatura è rotto, la misurazione
     * non viene effettuata.
     */
    @Scheduled(fixedRate = 1000)
    public void newVitalSigns() {
        if(battery.getCurrentCapacity() == 0 || ntc.isBroken()) {
            ntc.reset();
            return;
        }

        VitalParameters vitalParameter = vitalParametersRepository.save(
                new VitalParameters(LocalDateTime.now()
                        , heart.getPressureSystolic()
                        , heart.getPressureDiastolic()
                        , heart.getHeartRate()
                        , insulinPump.getCurrentGlucoseLevel()
                        , ntc.getTemperature()));
        log.info("VitalParametersService.addVitalParameters: {}", vitalParameter);
    }

    /**
     * Get all vital parameters
     * @return all vital parameters
     */
    public Iterable<VitalParametersDto> getAllVitalParameters() {
        Iterable<VitalParameters> result = vitalParametersRepository.findAll();
        log.info("VitalParametersService.getAllVitalParameters: {}", result);
        return convertToDtoList(result);
    }

    /**
     * Find all vital parameters of a patient in a given time interval
     * @param dateIntervalDto
     * @return vital parameters of a patient in a given time interval
     */
    public Iterable<VitalParametersDto> getVitalParametersByTimeInterval(
            DateIntervalDto dateIntervalDto) {
        validateDateInterval(dateIntervalDto);
        Iterable<VitalParameters> result = vitalParametersRepository.findAllByTimestampBetween(
                LocalDateTime.parse(dateIntervalDto.getStartDate())
                , LocalDateTime.parse(dateIntervalDto.getEndDate()));
        log.info("VitalParametersService.getVitalParametersByTimeInterval: {}", result);
        return convertToDtoList(result);
    }

    /**
     * Get last vital parameters
     * @return last vital parameters
     */
    public VitalParametersDto getLastVitalParameters() {
        VitalParameters vitalParameter = vitalParametersRepository.findFirstByOrderByTimestampDesc();
        log.info("VitalParametersService.getLastVitalParameters: {}", vitalParameter);
        if (vitalParameter == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No vital parameters found");
        }
        return convertToDto(vitalParameter);
    }


    /**
     * Create a vital parameterDto list from a vital parameter list
     * @param vitalParameter
     * @return vital parameterDto list
     */
    private VitalParametersDto convertToDto(VitalParameters vitalParameter) {
        VitalParametersDto dto = new VitalParametersDto();

        dto.setId(String.valueOf(vitalParameter.getId()));
        dto.setTimestamp(String.valueOf(vitalParameter.getTimestamp()));
        dto.setBloodPressureSystolic(String.valueOf(vitalParameter.getBloodPressureSystolic()));
        dto.setBloodPressureDiastolic(String.valueOf(vitalParameter.getBloodPressureDiastolic()));
        dto.setHeartRate(String.valueOf(vitalParameter.getHeartRate()));
        dto.setBloodSugarLevel(String.valueOf(vitalParameter.getBloodSugarLevel()));
        dto.setTemperature(String.valueOf(vitalParameter.getTemperature()));

        return dto;
    }

    private Iterable<VitalParametersDto> convertToDtoList(Iterable<VitalParameters> vitalParameters) {
        List<VitalParametersDto> dtos = new ArrayList<>();
        for (VitalParameters vitalParameter : vitalParameters) {
            dtos.add(convertToDto(vitalParameter));
        }
        return dtos;
    }

    /************************************************************************
     *************************** VALIDATION *********************************
     ************************************************************************/


    private void validateDateInterval(DateIntervalDto dto) {
        if (dto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "DTO cannot be null");
        }

        validateDateInterval(dto.getStartDate(), dto.getEndDate());
    }

    private void validateDateInterval(String fromDate, String toDate) {
        if (!isValidDate(fromDate) || !isValidDate(toDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format");
        }

        if (LocalDateTime.parse(fromDate).isAfter(LocalDateTime.parse(toDate))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "From date is after To date");
        }
    }

    private boolean isValidDate(String date) {
        try {
            LocalDateTime.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
