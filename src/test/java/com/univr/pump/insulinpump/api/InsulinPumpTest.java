package com.univr.pump.insulinpump.api;

import com.univr.pump.insulinpump.InsulinPumpApplication;
import com.univr.pump.insulinpump.controller.InsulinMachineController;
import com.univr.pump.insulinpump.model.InsulinMachine;
import com.univr.pump.insulinpump.repository.InsulinMachineRepository;
import com.univr.pump.insulinpump.scheduled.InsulinMachineMonitoringTask;
import com.univr.pump.insulinpump.scheduled.VitalParametersMonitoringTask;
import com.univr.pump.insulinpump.service.InsulinMachineService;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsulinPumpApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class InsulinPumpTest {

    @MockBean
    private InsulinMachineService insulinMachineService;

    @BeforeClass
    public static void setBaseUri() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    public void chargeBatteryTest() {
        given()
                .when()
                .put("/sensors/battery/replace")
                .then()
                .statusCode(200);
    }

    @Test
    public void refillInsulinTankTest() {
        given()
                .when()
                .put("/sensors/tank/refill")
                .then()
                .statusCode(200); // Checking for 404 status
    }

    @Test
    public void getStatusTest() {
        //TODO: Mock the service
        /*
        Mockito.when(insulinMachineService.getBatteryLevel()).thenReturn(100);
        Mockito.when(insulinMachineService.getInsulinLevel()).thenReturn(50);
        given()
                .when()
                .get("sensors/status")
                .then()
                .statusCode(200)
                .body("batteryLevel", org.hamcrest.Matchers.equalTo(100))
                .body("insulinLevel", org.hamcrest.Matchers.equalTo(50));*/
    }
}
