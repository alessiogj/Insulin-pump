package com.univr.pump.insulinpump.service;

import com.univr.pump.insulinpump.dto.PatientVitalParametersWithDateIntervalDto;
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
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VitalParametersService {

    private static final Logger log = LoggerFactory.getLogger(PatientService.class);

    private final VitalParametersRepository vitalParametersRepository;

    private final PatientService patientService;

    public VitalParametersService(VitalParametersRepository vitalParametersRepository
            , PatientService patientService) {
        this.vitalParametersRepository = vitalParametersRepository;
        this.patientService = patientService;
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
     * Find all vital parameters of a patient
     * @param id
     * @return vital parameters of a patient
     */
    public Iterable<VitalParametersDto> getVitalParametersByPatientId(Long id) {
        Iterable<VitalParameters> result = vitalParametersRepository.findByPatientId(id);
        log.info("VitalParametersService.getVitalParametersByPatientId: {}", result);
        return convertToDtoList(result);
    }

    /**
     * Find all vital parameters of a patient in a given time interval
     * @param patientVitalParametersWithDateIntervalDto
     * @return vital parameters of a patient in a given time interval
     */
    public Iterable<VitalParametersDto> getVitalParametersByTimeInterval(
            PatientVitalParametersWithDateIntervalDto patientVitalParametersWithDateIntervalDto) {
        validateDateIntervalAndPatient(patientVitalParametersWithDateIntervalDto);
        Iterable<VitalParameters> result = vitalParametersRepository.findByPatientIdAndTimestampBetween(
                Long.valueOf(patientVitalParametersWithDateIntervalDto.getPatientId())
                , LocalDate.parse(patientVitalParametersWithDateIntervalDto.getFrom())
                , LocalDate.parse(patientVitalParametersWithDateIntervalDto.getTo()));
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
                new VitalParameters(LocalDate.parse(vitalParametersDto.getTimestamp())
                        , Double.valueOf(vitalParametersDto.getBloodPressure())
                        , Double.valueOf(vitalParametersDto.getHeartRate())
                        , Double.valueOf(vitalParametersDto.getBloodSugarLevel())
                        , Double.valueOf(vitalParametersDto.getTemperature())
                        , patientService.getPatientById(Long.valueOf(vitalParametersDto.getPatientId()))));
        log.info("VitalParametersService.addVitalParameters: {}", vitalParameter);
        return convertToDto(vitalParameter);
    }


    /**
     * Create a vital parameterDto list from a vital parameter list
     * @param vitalParameter
     * @return vital parameterDto list
     */
    private VitalParametersDto convertToDto(VitalParameters vitalParameter) {
        VitalParametersDto dto = new VitalParametersDto();

        dto.setPatientId(String.valueOf(vitalParameter.getPatient().getId()));
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

        validateDate(vitalParametersDto.getTimestamp());
        validateHeartRate(vitalParametersDto.getHeartRate());
        validateBloodPressure(vitalParametersDto.getBloodPressure());
        validateBloodSugarLevel(vitalParametersDto.getBloodSugarLevel());
        validateTemperature(vitalParametersDto.getTemperature());
        validatePatient(vitalParametersDto.getPatientId());
    }

    private void validateDateIntervalAndPatient(PatientVitalParametersWithDateIntervalDto dto) {
        if (dto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "DTO cannot be null");
        }

        validateDateInterval(dto.getFrom(), dto.getTo());
        validatePatient(dto.getPatientId());
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

    private void validateDate(String timestamp) {
        if (!isValidDate(timestamp)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format");
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

    private void validatePatient(String patientId) {
        if (patientId == null || patientId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patient id cannot be null or empty");
        }

        if (patientService.getPatientById(Long.valueOf(patientId)) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patient does not exist");
        }
    }

}
