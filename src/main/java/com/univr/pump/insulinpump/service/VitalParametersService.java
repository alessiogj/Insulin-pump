package com.univr.pump.insulinpump.service;

import com.univr.pump.insulinpump.dto.VitalParametersWithDateIntervalDto;
import com.univr.pump.insulinpump.dto.VitalParametersBodyDto;
import com.univr.pump.insulinpump.dto.VitalParametersDto;
import com.univr.pump.insulinpump.model.VitalParameters;
import com.univr.pump.insulinpump.repository.VitalParametersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VitalParametersService {

    private static final Logger log = LoggerFactory.getLogger(VitalParametersService.class);

    private final VitalParametersRepository vitalParametersRepository;


    public VitalParametersService(VitalParametersRepository vitalParametersRepository) {
        this.vitalParametersRepository = vitalParametersRepository;
        log.info("VitalParametersService created");
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
     * @param vitalParametersWithDateIntervalDto
     * @return vital parameters of a patient in a given time interval
     */
    public Iterable<VitalParametersDto> getVitalParametersByTimeInterval(
            VitalParametersWithDateIntervalDto vitalParametersWithDateIntervalDto) {
        validateDateIntervalAndPatient(vitalParametersWithDateIntervalDto);
        Iterable<VitalParameters> result = vitalParametersRepository.findAllByTimestampBetween(
                LocalDate.parse(vitalParametersWithDateIntervalDto.getFrom())
                , LocalDate.parse(vitalParametersWithDateIntervalDto.getTo()));
        log.info("VitalParametersService.getVitalParametersByTimeInterval: {}", result);
        return convertToDtoList(result);
    }

    /**
     * Add a vital parameter
     * @param vitalParametersDto
     * @return added vital parameter
     */
    public VitalParametersDto addVitalParameters(VitalParametersBodyDto vitalParametersDto) {
        validateVitalParameters(vitalParametersDto);
        VitalParameters vitalParameter = vitalParametersRepository.save(
                new VitalParameters(LocalDateTime.now()
                        , Double.valueOf(vitalParametersDto.getBloodPressure())
                        , Double.valueOf(vitalParametersDto.getHeartRate())
                        , Double.valueOf(vitalParametersDto.getBloodSugarLevel())
                        , Double.valueOf(vitalParametersDto.getTemperature())));
        log.info("VitalParametersService.addVitalParameters: {}", vitalParameter);
        return convertToDto(vitalParameter);
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

        dto.setTimestamp(String.valueOf(vitalParameter.getTimestamp()));
        dto.setBloodPressure(String.valueOf(vitalParameter.getBloodPressure()));
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
    private void validateVitalParameters(VitalParametersBodyDto vitalParametersDto) {
        if (vitalParametersDto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "DTO cannot be null");
        }

        validateHeartRate(vitalParametersDto.getHeartRate());
        validateBloodPressure(vitalParametersDto.getBloodPressure());
        validateBloodSugarLevel(vitalParametersDto.getBloodSugarLevel());
        validateTemperature(vitalParametersDto.getTemperature());
    }

    private void validateDateIntervalAndPatient(VitalParametersWithDateIntervalDto dto) {
        if (dto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "DTO cannot be null");
        }

        validateDateInterval(dto.getFrom(), dto.getTo());
    }

    private void validateTemperature(String temperature) {
        double temperatureDouble = Double.parseDouble(temperature);
        if (temperatureDouble < 20 || temperatureDouble > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid temperature value");
        }
    }

    private void validateBloodSugarLevel(String bloodSugarLevel) {
        double bloodSugarLevelDouble = Double.parseDouble(bloodSugarLevel);
        if (bloodSugarLevelDouble < 0 || bloodSugarLevelDouble > 1000) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid blood sugar level value");
        }
    }

    private void validateBloodPressure(String bloodPressure) {
        double bloodPressureDouble = Double.parseDouble(bloodPressure);
        if (bloodPressureDouble < 0 || bloodPressureDouble > 300) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid blood pressure value");
        }
    }

    private void validateHeartRate(String heartRate) {
        double heartRateDouble = Double.parseDouble(heartRate);
        if (heartRateDouble < 0 || heartRateDouble > 300) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid heart rate value");
        }
    }

    private void validateDateInterval(String fromDate, String toDate) {
        if (!isValidDate(fromDate) || !isValidDate(toDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format");
        }

        if (LocalDate.parse(fromDate).isAfter(LocalDate.parse(toDate))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "From date is after To date");
        }
    }

    private boolean isValidDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
