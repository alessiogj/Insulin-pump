package com.univr.pump.insulinpump.controller;

import com.univr.pump.insulinpump.dto.PatientBodyDto;
import com.univr.pump.insulinpump.dto.PatientDto;
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
    public Iterable<PatientDto> getPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/searchbyname/{name}")
    public Iterable<PatientDto> searchByName(@PathVariable String name) {
        return patientService.getPatientsByName(name);
    }

    @GetMapping("/searchbysurname/{surname}")
    public Iterable<PatientDto> searchBySurname(@PathVariable String surname) {
        return patientService.getPatientsBySurname(surname);
    }

    @GetMapping("/searchbyfiscalcode/{fiscalCode}")
    public Iterable<PatientDto> searchByFiscalCode(@PathVariable String fiscalCode) {
        return patientService.getPatientsByFiscalCode(fiscalCode);
    }

    @GetMapping("/searchbydiabetestype/{diabetesType}")
    public Iterable<PatientDto> searchByDiabetesType(@PathVariable String diabetesType) {
        return patientService.getPatientsByDiabetesType(diabetesType);
    }

    @PostMapping("/")
    public PatientDto createPatient(@RequestBody PatientBodyDto patient) {
        return patientService.createPatient(patient);
    }

    @PutMapping("/{id}")
    public PatientDto updatePatient(@PathVariable Long id, @RequestBody PatientBodyDto patient) {
        return patientService.updatePatient(id, patient);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }
}
