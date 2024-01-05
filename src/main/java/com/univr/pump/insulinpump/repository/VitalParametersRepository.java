package com.univr.pump.insulinpump.repository;

import com.univr.pump.insulinpump.model.VitalParameters;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface VitalParametersRepository extends CrudRepository<VitalParameters, Long> {

        /**
         * Find all vital parameters in a given time interval
         * @param from
         * @param to
         * @return
         */
        Iterable<VitalParameters> findAllByTimestampBetween(LocalDate from, LocalDate to);


        /**
         * Find last vital parameters of a patient
         */
        VitalParameters findFirstByOrderByTimestampDesc();

        //TODO: altri metodi per eseguire operazioni statistiche sui parametri vitali
}
