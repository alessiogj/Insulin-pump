package com.univr.pump.insulinpump.service;


import com.univr.pump.insulinpump.model.Patient;
import com.univr.pump.insulinpump.repository.PatientRepository;
import com.univr.pump.insulinpump.utils.DIABETES_TYPE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private static final Logger log = LoggerFactory.getLogger(PatientService.class);

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
        log.info("PatientService created");
    }

    public Iterable<Patient> getAllPatients() {
        Iterable<Patient> result = patientRepository.findAll();
        log.info("PatientService.getAllPatients: {}", result);
        return result;
    }

    public Iterable<Patient> getPatientsByName(String name) {
        Iterable<Patient> result = patientRepository.findByNameContaining(name);
        log.info("PatientService.getPatientsByName: {}", result);
        return result;
    }

    public Iterable<Patient> getPatientsBySurname(String surname) {
        Iterable<Patient> result = patientRepository.findBySurnameContaining(surname);
        log.info("PatientService.getPatientsBySurname: {}", result);
        return result;
    }

    public Iterable<Patient> getPatientsByFiscalCode(String fiscalCode) {
        Iterable<Patient> result = patientRepository.findByFiscalCodeContaining(fiscalCode);
        log.info("PatientService.getPatientsByFiscalCode: {}", result);
        return result;
    }

    public Iterable<Patient> getPatientsByDiabetesType(DIABETES_TYPE diabetesType) {
        Iterable<Patient> result = patientRepository.findByDiabetesTypeContaining(String.valueOf(diabetesType));
        log.info("PatientService.getPatientsByDiabetesType: {}", result);
        return result;
    }

    public Patient createPatient(Patient patient) {
        Patient result = patientRepository.save(patient);
        log.info("PatientService.createPatient: {}", result);
        return result;
    }

    public Patient updatePatient(Patient patient) {
        Patient result = patientRepository.save(patient);
        log.info("PatientService.updatePatient: {}", result);
        return result;
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
        log.info("PatientService.deletePatient: {}", id);
    }
}
