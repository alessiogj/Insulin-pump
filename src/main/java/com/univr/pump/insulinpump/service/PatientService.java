package com.univr.pump.insulinpump.service;


import com.univr.pump.insulinpump.dto.PatientBodyDto;
import com.univr.pump.insulinpump.dto.PatientDto;
import com.univr.pump.insulinpump.model.Patient;
import com.univr.pump.insulinpump.repository.PatientRepository;
import com.univr.pump.insulinpump.utils.DIABETES_TYPE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    private static final Logger log = LoggerFactory.getLogger(PatientService.class);

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
        log.info("PatientService created");
    }

    public Iterable<PatientDto> getAllPatients() {
        Iterable<Patient> result = patientRepository.findAll();
        log.info("PatientService.getAllPatients: {}", result);
        return convertToDtoList(result);
    }

    public Iterable<PatientDto> getPatientsByName(String name) {
        Iterable<Patient> result = patientRepository.findByNameContaining(name);
        log.info("PatientService.getPatientsByName: {}", result);
        return convertToDtoList(result);
    }

    public Iterable<PatientDto> getPatientsBySurname(String surname) {
        Iterable<Patient> result = patientRepository.findBySurnameContaining(surname);
        log.info("PatientService.getPatientsBySurname: {}", result);
        return convertToDtoList(result);
    }

    public Iterable<PatientDto> getPatientsByFiscalCode(String fiscalCode) {
        Iterable<Patient> result = patientRepository.findByFiscalCodeContaining(fiscalCode);
        log.info("PatientService.getPatientsByFiscalCode: {}", result);
        return convertToDtoList(result);
    }

    public Iterable<PatientDto> getPatientsByDiabetesType(DIABETES_TYPE diabetesType) {
        Iterable<Patient> result = patientRepository.findByDiabetesTypeContaining(String.valueOf(diabetesType));
        log.info("PatientService.getPatientsByDiabetesType: {}", result);
        return convertToDtoList(result);
    }

    public PatientDto createPatient(PatientBodyDto patient) {
        validatePatient(patient);
        Patient result = patientRepository.save(
                new Patient(patient.getName()
                        , patient.getSurname()
                        , LocalDate.parse(patient.getBirthDate())
                        , patient.getFiscalCode()
                        , patient.getWeight()
                        , patient.getHeight()
                        , DIABETES_TYPE.valueOf(patient.getDiabetesType())));
        log.info("PatientService.createPatient: {}", result);
        return convertToDto(result);
    }

    private void validatePatient(PatientBodyDto patient) {
        if (patient.getName() == null || patient.getName().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Name cannot be null or empty");
        }
        if (patient.getSurname() == null || patient.getSurname().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Surname cannot be null or empty");
        }
        if (patient.getBirthDate() == null || patient.getBirthDate().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Birthday cannot be null or empty");
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate.parse(patient.getBirthDate(), formatter);
            } catch (DateTimeParseException e) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Birthday must be in the format YYYY-MM-DD");
            }
        }
        //TODO: validate fiscal code
        if (patient.getFiscalCode() == null || patient.getFiscalCode().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Fiscal code cannot be null or empty");
        }
        if (patient.getWeight() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Weight cannot be null or empty");
        }
        if (patient.getHeight() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Height cannot be null or empty");
        }
        if (patient.getDiabetesType() == null || patient.getDiabetesType().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Diabetes type cannot be null or empty");
        } else if (!DIABETES_TYPE.isValid(patient.getDiabetesType())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid diabetes type");
        }
    }

    public PatientDto updatePatient(Long patientId, PatientBodyDto patientUpdateDto) {
        validatePatient(patientUpdateDto);
        Patient existingPatient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Patient not found with id: " + patientId));

        existingPatient.setName(patientUpdateDto.getName());
        existingPatient.setSurname(patientUpdateDto.getSurname());
        existingPatient.setBirthDate(LocalDate.parse(patientUpdateDto.getBirthDate()));
        existingPatient.setFiscalCode(patientUpdateDto.getFiscalCode());
        existingPatient.setWeight(patientUpdateDto.getWeight());
        existingPatient.setHeight(patientUpdateDto.getHeight());
        existingPatient.setDiabetesType(DIABETES_TYPE.valueOf(patientUpdateDto.getDiabetesType()));

        Patient updatedPatient = patientRepository.save(existingPatient);
        log.info("PatientService.updatePatient: {}", updatedPatient);
        return convertToDto(updatedPatient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
        log.info("PatientService.deletePatient: {}", id);
    }

    public Patient getPatientById(Long id) {
        Patient result = patientRepository.findById(id).orElse(null);
        log.info("PatientService.getPatientById: {}", result);
        return result;
    }

    private Iterable<PatientDto> convertToDtoList(Iterable<Patient> patients) {
        List<PatientDto> dtos = new ArrayList<>();
        for (Patient patient : patients) {
            dtos.add(convertToDto(patient));
        }
        return dtos;
    }

    private PatientDto convertToDto(Patient patient) {
        PatientDto dto = new PatientDto();

        dto.setId(patient.getId() != null ? patient.getId().toString() : "");
        dto.setName(patient.getName());
        dto.setSurname(patient.getSurname());
        dto.setBirthDate(patient.getBirthDate().toString());
        dto.setFiscalCode(patient.getFiscalCode());
        dto.setWeight(patient.getWeight());
        dto.setHeight(patient.getHeight());
        dto.setDiabetesType(patient.getDiabetesType().toString());

        return dto;
    }

    private Patient convertFromDto(PatientDto patientDto) {
        Patient patient = new Patient();
        try {
            patient.setId(!patientDto.getId().isEmpty() ? Long.valueOf(patientDto.getId()) : null);
            patient.setName(patientDto.getName());
            patient.setSurname(patientDto.getSurname());
            patient.setBirthDate(LocalDate.parse(patientDto.getBirthDate()));
            patient.setFiscalCode(patientDto.getFiscalCode());
            patient.setWeight(patientDto.getWeight());
            patient.setHeight(patientDto.getHeight());
            patient.setDiabetesType(DIABETES_TYPE.valueOf(patientDto.getDiabetesType()));

        } catch (Exception e) {
                log.error("PatientService.convertFromDto: {}", e.getMessage());
        }
        return patient;
    }
}
