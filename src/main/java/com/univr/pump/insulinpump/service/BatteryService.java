package com.univr.pump.insulinpump.service;

import com.univr.pump.insulinpump.model.Battery;
import com.univr.pump.insulinpump.repository.BatteryRepository;
import org.springframework.stereotype.Service;

@Service
public class BatteryService {

    private final BatteryRepository batteryRepository;

    public BatteryService(BatteryRepository batteryRepository) {
        this.batteryRepository = batteryRepository;
    }

    /**
     * Get current battery level
     * if repository is empty, create a new battery
     * @return battery level
     */
    public int getBatteryLevel() {
        if (batteryRepository.count() == 0) {
            chargeBattery();
        }
        return batteryRepository.findFirstByOrderByIdDesc().getCurrentCapacity();
    }

    /**
     * Charge battery to full capacity
     */
    public void chargeBattery() {
        batteryRepository.save(new Battery());
    }

    /**
     * Discharge battery by 1%
     * if battery is empty, do nothing
     */
    public void decrBattery() {
        Battery battery = batteryRepository.findFirstByOrderByIdDesc();
        battery.discharge();
        batteryRepository.save(battery);
    }
}
