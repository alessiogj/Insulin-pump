package com.univr.pump.insulinpump.service;

import com.univr.pump.insulinpump.model.InsulinMachine;
import com.univr.pump.insulinpump.repository.InsulinMachineRepository;
import com.univr.pump.insulinpump.scheduled.InsulinMachineMonitoringTask;
import com.univr.pump.insulinpump.scheduled.VitalParametersMonitoringTask;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
public class InsulinMachineServiceTest {

    @Mock
    private InsulinMachineRepository insulinMachineRepository;

    @InjectMocks
    private InsulinMachineService insulinMachineService;

    private InsulinMachine insulinMachine;

    @Before
    public void setUp() {
        insulinMachine = new InsulinMachine();
    }

    @Test
    public void getBatteryLevelWhenRepositoryIsEmpty() {
        when(insulinMachineRepository.count()).thenReturn(0L);
        InsulinMachine newMachine = new InsulinMachine();
        newMachine.setCurrentCapacity(100);
        when(insulinMachineRepository.findFirstByOrderByIdDesc()).thenReturn(newMachine);
        int batteryLevel = insulinMachineService.getBatteryLevel();
        assertEquals(100, batteryLevel);
        verify(insulinMachineRepository, times(1)).save(any(InsulinMachine.class));
    }

    @Test
    public void getBatteryLevelWhenRepositoryIsNotEmpty() {
        when(insulinMachineRepository.count()).thenReturn(1L);
        InsulinMachine existingMachine = new InsulinMachine();
        existingMachine.setCurrentCapacity(75);
        when(insulinMachineRepository.findFirstByOrderByIdDesc()).thenReturn(existingMachine);
        int batteryLevel = insulinMachineService.getBatteryLevel();
        assertEquals(75, batteryLevel);
        verify(insulinMachineRepository, times(0)).save(any(InsulinMachine.class));
    }

    @Test
    public void getBatteryLevel_WhenRepositoryIsNotEmpty() {
        InsulinMachine existingMachine = new InsulinMachine();
        existingMachine.setCurrentCapacity(75);
        when(insulinMachineRepository.count()).thenReturn(1L);
        when(insulinMachineRepository.findFirstByOrderByIdDesc()).thenReturn(existingMachine);
        int batteryLevel = insulinMachineService.getBatteryLevel();
        assertEquals(75, batteryLevel);
        verify(insulinMachineRepository, times(0)).save(any(InsulinMachine.class));
    }

    @Test
    public void chargeBatteryWhenRepositoryIsEmpty() {
        when(insulinMachineRepository.count()).thenReturn(0L);
        insulinMachineService.chargeBattery();
        verify(insulinMachineRepository, times(1)).save(any(InsulinMachine.class));
    }

    @Test
    public void chargeBatteryWhenBatteryIsNotFull() {
        Long machineId = 1L;
        insulinMachine.setId(machineId);
        insulinMachine.setCurrentCapacity(50);
        when(insulinMachineRepository.count()).thenReturn(1L);
        when(insulinMachineRepository.findFirstByOrderByIdDesc()).thenReturn(insulinMachine);
        when(insulinMachineRepository.findById(machineId)).thenReturn(Optional.of(insulinMachine));
        insulinMachineService.chargeBattery();
        assertEquals(100, insulinMachine.getCurrentCapacity());
        verify(insulinMachineRepository, times(1)).save(insulinMachine);
    }

    @Test
    public void chargeBatteryWhenBatteryIsAlreadyFull() {
        Long machineId = 1L;
        insulinMachine.setId(machineId);
        insulinMachine.setCurrentCapacity(100);
        when(insulinMachineRepository.count()).thenReturn(1L);
        when(insulinMachineRepository.findFirstByOrderByIdDesc()).thenReturn(insulinMachine);
        when(insulinMachineRepository.findById(machineId)).thenReturn(Optional.of(insulinMachine));
        insulinMachineService.chargeBattery();
        assertEquals(100, insulinMachine.getCurrentCapacity());
        verify(insulinMachineRepository, times(0)).save(insulinMachine);
    }

    @Test
    public void decrBatteryWhenRepositoryIsEmpty() {
        when(insulinMachineRepository.count()).thenReturn(0L);
        insulinMachineService.decrBattery();
        verify(insulinMachineRepository, times(1)).save(any(InsulinMachine.class));
    }

    @Test
    public void decrBatteryWhenBatteryIsNotEmpty() {
        Long machineId = 1L;
        insulinMachine.setId(machineId);
        insulinMachine.setCurrentCapacity(50);
        when(insulinMachineRepository.count()).thenReturn(1L);
        when(insulinMachineRepository.findFirstByOrderByIdDesc()).thenReturn(insulinMachine);
        when(insulinMachineRepository.findById(machineId)).thenReturn(Optional.of(insulinMachine));
        insulinMachineService.decrBattery();
        assertEquals(49, insulinMachine.getCurrentCapacity());
        verify(insulinMachineRepository, times(1)).save(insulinMachine);
    }

    @Test
    public void decrBattery_WhenBatteryIsEmpty() {
        insulinMachine.setCurrentCapacity(0);
        Long machineId = 1L;
        insulinMachine.setId(machineId);
        when(insulinMachineRepository.count()).thenReturn(1L);
        when(insulinMachineRepository.findFirstByOrderByIdDesc()).thenReturn(insulinMachine);
        when(insulinMachineRepository.findById(machineId)).thenReturn(Optional.of(insulinMachine));
        insulinMachineService.decrBattery();
        assertEquals(0, insulinMachine.getCurrentCapacity());
        verify(insulinMachineRepository, times(0)).save(insulinMachine);
    }

    @Test
    public void refillInsulinPumpWhenRepositoryIsEmpty() {
        when(insulinMachineRepository.count()).thenReturn(0L);
        insulinMachineService.refillInsulinPump();
        verify(insulinMachineRepository, times(1)).save(any(InsulinMachine.class));
    }

    @Test
    public void refillInsulinPumpWhenInsulinPumpIsNotFull() {
        Long machineId = 1L;
        insulinMachine.setId(machineId);
        insulinMachine.setCurrentTankLevel(50);
        when(insulinMachineRepository.count()).thenReturn(1L);
        when(insulinMachineRepository.findFirstByOrderByIdDesc()).thenReturn(insulinMachine);
        when(insulinMachineRepository.findById(machineId)).thenReturn(Optional.of(insulinMachine));
        insulinMachineService.refillInsulinPump();
        assertEquals(100, insulinMachine.getCurrentTankLevel());
        verify(insulinMachineRepository, times(1)).save(insulinMachine);
    }

    @Test
    public void refillInsulinPumpWhenInsulinPumpIsAlreadyFull() {
        insulinMachine.setCurrentTankLevel(100);
        Long machineId = 1L;
        insulinMachine.setId(machineId);
        when(insulinMachineRepository.count()).thenReturn(1L);
        when(insulinMachineRepository.findFirstByOrderByIdDesc()).thenReturn(insulinMachine);
        when(insulinMachineRepository.findById(machineId)).thenReturn(Optional.of(insulinMachine));
        insulinMachineService.refillInsulinPump();
        assertEquals(100, insulinMachine.getCurrentTankLevel());
        verify(insulinMachineRepository, times(0)).save(insulinMachine);
    }

    @Test
    public void getInsulinLevelWhenRepositoryIsEmpty() {
        when(insulinMachineRepository.count()).thenReturn(0L);
        InsulinMachine newMachine = new InsulinMachine();
        newMachine.setCurrentTankLevel(50);
        when(insulinMachineRepository.save(any(InsulinMachine.class))).thenReturn(newMachine);
        when(insulinMachineRepository.findFirstByOrderByIdDesc()).thenReturn(newMachine);

        int insulinLevel = insulinMachineService.getInsulinLevel();
        assertEquals(50, insulinLevel);
        verify(insulinMachineRepository, times(1)).save(any(InsulinMachine.class));
    }

    @Test
    public void getInsulinLevelWhenRepositoryIsNotEmpty() {
        InsulinMachine existingMachine = new InsulinMachine();
        existingMachine.setCurrentTankLevel(75);
        when(insulinMachineRepository.count()).thenReturn(1L);
        when(insulinMachineRepository.findFirstByOrderByIdDesc()).thenReturn(existingMachine);
        int insulinLevel = insulinMachineService.getInsulinLevel();
        assertEquals(75, insulinLevel);
        verify(insulinMachineRepository, times(0)).save(any(InsulinMachine.class));
    }

    @Test
    public void injectInsulinWhenInsulinPumpIsNotEmpty() {
        // Arrange
        insulinMachine.setCurrentTankLevel(50); // Example initial insulin level
        when(insulinMachineRepository.findFirstByOrderByIdDesc()).thenReturn(insulinMachine);

        // Act
        insulinMachineService.injectInsulin();

        // Assert
        assertTrue(insulinMachine.getCurrentTankLevel() < 50); // Assuming insulin is decreased
        verify(insulinMachineRepository, times(1)).save(insulinMachine);
    }

    @Test
    public void injectInsulinWhenInsulinPumpIsEmpty() {
        // Arrange
        insulinMachine.setCurrentTankLevel(0); // Insulin tank empty
        when(insulinMachineRepository.findFirstByOrderByIdDesc()).thenReturn(insulinMachine);

        // Act
        insulinMachineService.injectInsulin();

        // Assert
        assertEquals(0, insulinMachine.getCurrentTankLevel()); // Insulin level should remain unchanged
        verify(insulinMachineRepository, times(0)).save(insulinMachine);
    }

    @Test
    public void injectInsulinWhenRepositoryIsEmpty() {
        when(insulinMachineRepository.findFirstByOrderByIdDesc()).thenReturn(null);
        when(insulinMachineRepository.count()).thenReturn(0L);
        insulinMachineService.injectInsulin();
        verify(insulinMachineRepository, times(1)).save(any(InsulinMachine.class));
    }
}