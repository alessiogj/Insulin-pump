package com.univr.pump.insulinpump.controller;

import com.univr.pump.insulinpump.model.Patient;
import com.univr.pump.insulinpump.service.PatientService;
import com.univr.pump.insulinpump.utils.DIABETES_TYPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/")
    public Iterable<Patient> getPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/searchbyname/{name}")
    public Iterable<Patient> searchByName(@PathVariable String name) {
        return patientService.getPatientsByName(name);
    }

    @GetMapping("/searchbysurname/{surname}")
    public Iterable<Patient> searchBySurname(@PathVariable String surname) {
        return patientService.getPatientsBySurname(surname);
    }

    @GetMapping("/searchbyfiscalcode/{fiscalCode}")
    public Iterable<Patient> searchByFiscalCode(@PathVariable String fiscalCode) {
        return patientService.getPatientsByFiscalCode(fiscalCode);
    }

    @GetMapping("/searchbydiabetestype/{diabetesType}")
    public Iterable<Patient> searchByDiabetesType(@PathVariable DIABETES_TYPE diabetesType) {
        return patientService.getPatientsByDiabetesType(diabetesType);
    }

    @PostMapping("/")
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.createPatient(patient);
    }

    @PutMapping("/")
    public Patient updatePatient(@RequestBody Patient patient) {
        return patientService.updatePatient(patient);
    }

    @DeleteMapping("/")
    public void deletePatient(@RequestBody Long id) {
        patientService.deletePatient(id);
    }
}
