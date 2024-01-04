package com.univr.pump.insulinpump.service;

import com.univr.pump.insulinpump.dto.PatientVitalParametersWithDateIntervalDto;
import com.univr.pump.insulinpump.dto.VitalParametersDto;
import com.univr.pump.insulinpump.model.VitalParameters;
import com.univr.pump.insulinpump.repository.PatientRepository;
import com.univr.pump.insulinpump.repository.VitalParametersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VitalParametersService {

    private static final Logger log = LoggerFactory.getLogger(PatientService.class);

    private final VitalParametersRepository vitalParametersRepository;

    private final PatientService patientService;

    public VitalParametersService(VitalParametersRepository vitalParametersRepository, PatientService patientService) {
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
        return convertToDto(result);
    }

    /**
     * Find all vital parameters of a patient
     * @param id
     * @return vital parameters of a patient
     */
    public Iterable<VitalParametersDto> getVitalParametersByPatientId(Long id) {
        Iterable<VitalParameters> result = vitalParametersRepository.findByPatientId(id);
        log.info("VitalParametersService.getVitalParametersByPatientId: {}", result);
        return convertToDto(result);
    }

    /**
     * Find all vital parameters of a patient in a given time interval
     * @param patientVitalParametersWithDateIntervalDto
     * @return vital parameters of a patient in a given time interval
     */
    public Iterable<VitalParametersDto> getVitalParametersByTimeInterval(
            PatientVitalParametersWithDateIntervalDto patientVitalParametersWithDateIntervalDto) {
        Iterable<VitalParameters> result = vitalParametersRepository.findByPatientIdAndTimestampBetween(
                patientVitalParametersWithDateIntervalDto.getPatientId(),
                patientVitalParametersWithDateIntervalDto.getFrom(),
                patientVitalParametersWithDateIntervalDto.getTo());
        log.info("VitalParametersService.getVitalParametersByTimeInterval: {}", result);
        return convertToDto(result);
    }

    /**
     * Create a vital parameterDto list from a vital parameter list
     * @param vitalParameters
     * @return vital parameterDto list
     */
    private Iterable<VitalParametersDto> convertToDto(Iterable<VitalParameters> vitalParameters) {
        List<VitalParametersDto> dtos = new ArrayList<>();
        for (VitalParameters vitalParameter : vitalParameters) {
            VitalParametersDto dto = new VitalParametersDto();

            dto.setId(vitalParameter.getId() != null ? vitalParameter.getId().toString() : "");
            dto.setPatientId(vitalParameter.getPatient() != null && vitalParameter.getPatient().getId() !=
                    null ? vitalParameter.getPatient().getId().toString() : "");
            dto.setTimestamp(vitalParameter.getTimestamp() != null ? vitalParameter.getTimestamp().toString() : "");
            dto.setHeartRate(vitalParameter.getHeartRate() != null ? vitalParameter.getHeartRate().toString() : "");
            dto.setBloodPressure(vitalParameter.getBloodPressure() != null ? vitalParameter.getBloodPressure().toString() : "");
            dto.setBloodSugarLevel(vitalParameter.getBloodSugarLevel() != null ? vitalParameter.getBloodSugarLevel().toString() : "");
            dto.setTemperature(vitalParameter.getTemperature() != null ? vitalParameter.getTemperature().toString() : "");

            dtos.add(dto);
        }
        return dtos;
    }

    /**
     * Create a vital parameter list from a vital parameterDto list
     * @param vitalParametersDtos
     * @return vital parameter list
     */
    private Iterable<VitalParameters> convertFromDto(Iterable<VitalParametersDto> vitalParametersDtos) {
        List<VitalParameters> vitalParameters = new ArrayList<>();
        for (VitalParametersDto vitalParametersDto : vitalParametersDtos) {
            try {
                VitalParameters vitalParameter = new VitalParameters();

                vitalParameter.setId(!vitalParametersDto.getId().isEmpty() ? Long.valueOf(vitalParametersDto.getId()) : null);
                vitalParameter.setPatient(!vitalParametersDto.getPatientId().isEmpty() ? patientService.getPatientById(Long.valueOf(vitalParametersDto.getPatientId())) : null);
                vitalParameter.setTimestamp(!vitalParametersDto.getTimestamp().isEmpty() ? LocalDate.parse(vitalParametersDto.getTimestamp()) : null);
                vitalParameter.setHeartRate(!vitalParametersDto.getHeartRate().isEmpty() ? Double.valueOf(vitalParametersDto.getHeartRate()) : null);
                vitalParameter.setBloodPressure(!vitalParametersDto.getBloodPressure().isEmpty() ? Double.valueOf(vitalParametersDto.getBloodPressure()) : null);
                vitalParameter.setBloodSugarLevel(!vitalParametersDto.getBloodSugarLevel().isEmpty() ? Double.valueOf(vitalParametersDto.getBloodSugarLevel()) : null);
                vitalParameter.setTemperature(!vitalParametersDto.getTemperature().isEmpty() ? Double.valueOf(vitalParametersDto.getTemperature()) : null);

                vitalParameters.add(vitalParameter);
            } catch (NumberFormatException | DateTimeParseException e) {
                log.error("Error in DTO conversion: {}", e.getMessage());
            }
        }
        return vitalParameters;
    }
}
