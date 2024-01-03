package com.univr.pump.insulinpump.repository;


import com.univr.pump.insulinpump.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Long> {

        Iterable<Patient> findByNameContaining(String name);

        Iterable<Patient> findBySurnameContaining(String surname);

        Iterable<Patient> findByFiscalCodeContaining(String fiscalCode);

        Iterable<Patient> findByDiabetesTypeContaining(String diabetesType);
}
