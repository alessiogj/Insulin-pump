package com.univr.pump.insulinpump.repository;


import com.univr.pump.insulinpump.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Long> {

        /**
         * Find patients with a given name with a LIKE query
         * @param name
         * @return Iterable<Patient>
         */
        Iterable<Patient> findByNameContaining(String name);

        /**
         * Find patients with a given surname with a LIKE query
         * @param surname
         * @return Iterable<Patient>
         */
        Iterable<Patient> findBySurnameContaining(String surname);

        /**
         * Find patients with a given fiscal code with a LIKE query
         * @param fiscalCode
         * @return Iterable<Patient>
         */
        Iterable<Patient> findByFiscalCodeContaining(String fiscalCode);

        /**
         * Find all patients with a given diabetes type
         * @param diabetesType
         * @return
         */
        Iterable<Patient> findByDiabetesTypeContaining(String diabetesType);
}
