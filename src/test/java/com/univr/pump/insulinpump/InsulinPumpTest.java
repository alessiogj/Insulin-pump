package com.univr.pump.insulinpump;

import com.univr.pump.insulinpump.repository.InsulinMachineRepository;
import com.univr.pump.insulinpump.service.InsulinMachineService;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsulinPumpApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class InsulinPumpTest {

    @Autowired
    private InsulinMachineRepository insulinMachineRepository;

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
        Long id = insulinMachineRepository.findFirstByOrderByIdDesc().getId();
        RestAssured
                .put("/sensors/battery/replace")
        .then()
                .statusCode(200);
        Long newId = insulinMachineRepository.findFirstByOrderByIdDesc().getId();
        assert id.equals(newId);
        assert insulinMachineRepository.findFirstByOrderByIdDesc().getCurrentCapacity() == 100;
    }

    /**
     * The system uses this API to replace the tank
     * The method should replace the insulin pump, the id
     * of the insulin pump
     * shouldn't be different from the previous one, and the
     * insulin level should be 100
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testRefillInsulinPump() {
        Long id = insulinMachineRepository.findFirstByOrderByIdDesc().getId();
        RestAssured
                .put("/sensors/tank/refill")
        .then()
                .statusCode(200);
        Long newId = insulinMachineRepository.findFirstByOrderByIdDesc().getId();
        assert id.equals(newId);
        assert insulinMachineRepository.findFirstByOrderByIdDesc().getCurrentTankLevel() == 100;
    }

    /**
     * The system uses this API to replace the insulin pump
     * The method should replace the insulin pump, the id
     * of the insulin pump should be different from the
     * previous one, the insulin level should be 100
     * and the battery level should be 100
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testReplaceInsulinPump() {
        Long id = insulinMachineRepository.findFirstByOrderByIdDesc().getId();
        RestAssured
                .post("/sensors/replace")
        .then()
                .statusCode(200);
        Long newId = insulinMachineRepository.findFirstByOrderByIdDesc().getId();
        assert !id.equals(newId);
        assert insulinMachineRepository.findFirstByOrderByIdDesc().getCurrentTankLevel() == 100;
        assert insulinMachineRepository.findFirstByOrderByIdDesc().getCurrentCapacity() == 100;
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
