package com.univr.pump.insulinpump.service;

import com.univr.pump.insulinpump.model.InsulinMachine;
import com.univr.pump.insulinpump.repository.InsulinMachineRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class InsulineMachineServiceTest {

    @InjectMocks
    private InsulinMachineService insulinMachineService;

    @Mock
    private InsulinMachineRepository insulinMachineRepository;

    @Before
    public void setUp() {
        insulinMachineRepository = Mockito.mock(InsulinMachineRepository.class);
        insulinMachineService = new InsulinMachineService(insulinMachineRepository);
    }

    /**
     * Get current battery level
     * if repository is empty, create a new machine
     * @return battery level
     */
    @Test
    public void getBatteryLevel() {
        InsulinMachine latestMachine = new InsulinMachine();
        latestMachine.setCurrentCapacity(50);
        Mockito.when(insulinMachineRepository.count()).thenReturn(1L);
        Mockito.when(insulinMachineRepository.findFirstByOrderByIdDesc()).thenReturn(latestMachine);

        int batteryLevel = insulinMachineService.getBatteryLevel();

        assert batteryLevel == 50;
    }
}
