package com.univr.pump.insulinpump.repository;

import com.univr.pump.insulinpump.model.VitalParameters;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface VitalParametersRepository extends CrudRepository<VitalParameters, Long> {

        /**
         * Find all vital parameters of a patient by patient id
         * @param id
         * @return
         */
        Iterable<VitalParameters> findByPatientId(Long id);

        /**
         * Find all vital parameters of a patient in a given time interval
         * @param id
         * @param from
         * @param to
         * @return
         */
        Iterable<VitalParameters> findByPatientIdAndTimestampBetween(Long id, LocalDate from, LocalDate to);


        //TODO: altri metodi per eseguire operazioni statistiche sui parametri vitali
}
