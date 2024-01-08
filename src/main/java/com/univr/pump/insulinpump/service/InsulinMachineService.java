package com.univr.pump.insulinpump.service;

import com.univr.pump.insulinpump.model.InsulinMachine;
import com.univr.pump.insulinpump.repository.InsulinMachineRepository;
import org.springframework.stereotype.Service;

@Service
public class InsulinMachineService {

    private final InsulinMachineRepository insulinMachineRepository;

    public InsulinMachineService(InsulinMachineRepository insulinMachineRepository) {
        this.insulinMachineRepository = insulinMachineRepository;
    }

    /**
     * Get current battery level
     * if repository is empty, create a new battery
     * @return battery level
     */
    public int getBatteryLevel() {
        if (insulinMachineRepository.count() == 0) {
            chargeBattery();
        }
        return insulinMachineRepository.findFirstByOrderByIdDesc().getCurrentCapacity();
    }

    /**
     * Charge battery to full capacity
     */
    public void chargeBattery() {
        insulinMachineRepository.save(new InsulinMachine());
    }

    /**
     * Discharge battery by 1%
     * if battery is empty, do nothing
     */
    public void decrBattery() {
        InsulinMachine insulinMachine = insulinMachineRepository.findFirstByOrderByIdDesc();
        insulinMachine.discharge();
        insulinMachineRepository.save(insulinMachine);
    }

    /**
     * Refill insulin pump to full capacity
     */
    public void refillInsulinPump() {
        insulinMachineRepository.findFirstByOrderByIdDesc().refill();
    }

    /**
     * Get current insulin level
     * @return insulin level
     */
    public int getInsulinLevel() {
        return insulinMachineRepository.findFirstByOrderByIdDesc().getCurrentTankLevel();
    }

    /**
     * Inject insulin
     * if insulin pump is empty, do nothing
     */
    public void injectInsulin() {
        InsulinMachine insulinMachine = insulinMachineRepository.findFirstByOrderByIdDesc();
        insulinMachine.injectInsulin();
        insulinMachineRepository.save(insulinMachine);
    }
}
