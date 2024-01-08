package com.univr.pump.insulinpump.repository;

import ch.qos.logback.classic.Logger;
import com.univr.pump.insulinpump.model.Battery;
import org.springframework.data.repository.CrudRepository;

public interface BatteryRepository extends CrudRepository<Battery, Long> {
    public Battery findFirstByOrderByIdDesc();
}
