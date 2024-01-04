package com.univr.pump.insulinpump.repository;

import com.univr.pump.insulinpump.model.Patient;
import com.univr.pump.insulinpump.model.VitalParameters;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

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
        Iterable<VitalParameters> findByPatientIdAndTimestampBetween(Long id, Date from, Date to);


        //TODO: altri metodi per eseguire operazioni statistiche sui parametri vitali
}
