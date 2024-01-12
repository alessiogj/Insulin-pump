package com.univr.pump.insulinpump.api;

import com.univr.pump.insulinpump.InsulinPumpApplication;
import com.univr.pump.insulinpump.model.InsulinMachine;
import com.univr.pump.insulinpump.repository.InsulinMachineRepository;
import com.univr.pump.insulinpump.scheduled.InsulinMachineMonitoringTask;
import com.univr.pump.insulinpump.scheduled.VitalParametersMonitoringTask;
import com.univr.pump.insulinpump.service.InsulinMachineService;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsulinPumpApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class InsulinPumpTest {

    @Autowired
    private InsulinMachineRepository insulinMachineRepository;

    @MockBean
    private InsulinMachineMonitoringTask insulinMachineMonitoringTask;

    @MockBean
    private VitalParametersMonitoringTask vitalParametersMonitoringTask;

    @BeforeClass
    public static void setBaseUri() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    /**
     * The system uses this API to replace the battery
     * The method should replace the battery, so the
     * id of the battery shouldn't be different from the
     * previous one because the device is the same,
     * the battery level should be 100
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testReplaceBattery() {
        // Create a new machine
        InsulinMachine insulinMachine = new InsulinMachine();
        // Set the battery level to 0
        insulinMachine.setCurrentCapacity(0);
        insulinMachineRepository.save(insulinMachine);
        assert insulinMachineRepository.findFirstByOrderByIdDesc().getCurrentCapacity() == 0;
        RestAssured
                .put("/sensors/battery/replace")
        .then()
                .statusCode(200);
        assert insulinMachineRepository.findFirstByOrderByIdDesc().getId().equals(insulinMachine.getId());
        assert insulinMachineRepository.findFirstByOrderByIdDesc().getCurrentCapacity() == 100;
    }

    /**
     * The system uses this API to refill the tank
     * The method should refill the tank, so the
     * id of the tank shouldn't be different from the
     * previous one because the device is the same,
     * the tank level should be 100
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testRefillInsulinPump() {
        // Create a new machine
        InsulinMachine insulinMachine = new InsulinMachine();
        // Set the tank level to 0
        insulinMachine.setCurrentTankLevel(0);
        insulinMachineRepository.save(insulinMachine);
        assert insulinMachineRepository.findFirstByOrderByIdDesc().getCurrentTankLevel() == 0;
        RestAssured
                .put("/sensors/tank/refill")
        .then()
                .statusCode(200);
        Long newId = insulinMachineRepository.findFirstByOrderByIdDesc().getId();
        assert insulinMachineRepository.findFirstByOrderByIdDesc().getId().equals(insulinMachine.getId());
        assert insulinMachineRepository.findFirstByOrderByIdDesc().getCurrentTankLevel() == 100;
    }

    /**
     * The system uses this API to get the status of the
     * machine, the method should return the battery level
     * and the insulin level
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testGetStatus() {
        RestAssured
                .get("/sensors/status")
        .then()
                .statusCode(200)
                .assertThat()
                .body("battery", org.hamcrest.Matchers.notNullValue())
                .body("tank", org.hamcrest.Matchers.notNullValue());
    }
}
