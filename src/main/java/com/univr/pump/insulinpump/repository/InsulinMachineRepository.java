package com.univr.pump.insulinpump.repository;

import com.univr.pump.insulinpump.model.InsulinMachine;
import org.springframework.data.repository.CrudRepository;

public interface InsulinMachineRepository extends CrudRepository<InsulinMachine, Long> {
    public InsulinMachine findFirstByOrderByIdDesc();
}
