package com.univr.pump.insulinpump;

import com.univr.pump.insulinpump.repository.BatteryRepository;
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
public class SensorTest {

    @Autowired
    private BatteryRepository batteryRepository;


    @BeforeClass
    public static void setBaseUri() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    /**
     * The system uses this API to replace the battery
     * The method should replace the battery, so the
     * id of the battery should be different from the
     * previous one, and the battery level should be 100
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testReplaceBattery() {
        Long id = batteryRepository.findFirstByOrderByIdDesc().getId();
        RestAssured
                .put("/sensors/battery/replace")
        .then()
                .statusCode(200);
        Long newId = batteryRepository.findFirstByOrderByIdDesc().getId();
        assert !id.equals(newId);
        assert batteryRepository.findFirstByOrderByIdDesc().getCurrentCapacity() == 100;
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testRefillInsulinPump() {
        // TODO
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testGetStatus() {
        // TODO
    }
}
