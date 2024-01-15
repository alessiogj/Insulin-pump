package com.univr.pump.insulinpump.scheduled;

import com.univr.pump.insulinpump.mock.Patient;
import com.univr.pump.insulinpump.repository.InsulinMachineRepository;
import com.univr.pump.insulinpump.service.InsulinMachineService;
import com.univr.pump.insulinpump.service.VitalParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class InsulinMachineMonitoringTask {
    private final Patient patient;
    private final InsulinMachineService insulinMachineService;
    private final VitalParametersService vitalParametersService;

    public InsulinMachineMonitoringTask(Patient patient,
                                        InsulinMachineService insulinMachineService,
                                        VitalParametersService vitalParametersService) {
        this.patient = patient;
        this.insulinMachineService = insulinMachineService;
        this.vitalParametersService = vitalParametersService;
    }

    /**
     * Simulates the battery discharge.
     * The battery is discharged by 1% every 10 minutes.
     */
    @Scheduled(fixedRate = 600000)
    public void decrBattery() {
        if (insulinMachineService.getBatteryLevel() > 0) {
            insulinMachineService.decrBattery();
        }
    }

    /**
     * Simulates the insulin pump monitoring.
     * Checks if the sugar level is increasing and the rate of increase is stable or increasing.
     * If the conditions are met, injects insulin based on the given formula.
     */
    @Scheduled(fixedRate = 10000)
    public void insulinPump() {
        int r2 = patient.getGlucoseLevel();
        int r1 = patient.getPreviousGlucoseLevel();
        int r0 = patient.getPreviousPreviousGlucoseLevel();

        if (insulinMachineService.getBatteryLevel() == 0) {
            return;
        }

        int rateIncrease = r2 - r1;
        int CompDose = 0;

        if (rateIncrease >= (r1 - r0)) {
            CompDose = (int) (Math.round((r2 - r1) / 4.0));

            if (CompDose == 0) {
                CompDose = 1;
            }
        }

        if (CompDose > 0 && rateIncrease > 0) {
            insulinMachineService.injectInsulin(CompDose, rateIncrease);
        }

    }

    /**
     * Ogni 10 minuti effettua una misurazione dei parametri vitali del paziente.
     * Se la batteria è scarica la misurazione
     * non viene effettuata.
     */
    @Scheduled(fixedRate = 10000)
    public void newVitalSigns() {
        if(insulinMachineService.getBatteryLevel() == 0) {
            return;
        }

        vitalParametersService.saveVitalParameters(
                patient.getPressureSystolic(),
                patient.getPressureDiastolic(),
                patient.getHeartRate(),
                patient.getGlucoseLevel(),
                patient.getTemperature()
        );
    }

}
