package com.univr.pump.insulinpump.api;

import com.univr.pump.insulinpump.InsulinPumpApplication;
import com.univr.pump.insulinpump.dto.DateIntervalDto;
import com.univr.pump.insulinpump.model.VitalParameters;
import com.univr.pump.insulinpump.repository.VitalParametersRepository;
import com.univr.pump.insulinpump.scheduled.InsulinMachineMonitoringTask;
import com.univr.pump.insulinpump.scheduled.VitalParametersMonitoringTask;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsulinPumpApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class VitalParametersTest {

    @Autowired
    private VitalParametersRepository vitalParametersRepository;

    @MockBean
    private InsulinMachineMonitoringTask insulinMachineMonitoringTask;

    @MockBean
    private VitalParametersMonitoringTask vitalParametersMonitoringTask;

    @BeforeClass
    public static void setBaseUri() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    /**
     * Test get vital parameters empty list
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testGetVitalParametersEmptyList() {
        vitalParametersRepository.deleteAll();
        given()
                .when()
                .get("/vitalparameters/")
                .then()
                .statusCode(200)
                .body("", Matchers.hasSize(0));
    }

    /**
     * Test get vital parameters not empty list
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testGetVitalParametersNotEmpty() {
        vitalParametersRepository.deleteAll();

        VitalParameters firstVitalParameters = new VitalParameters(
                LocalDateTime.now(),
                80,
                80,
                80,
                80,
                36.6
        );

        VitalParameters secondVitalParameters = new VitalParameters(
                LocalDateTime.now(),
                90,
                90,
                90,
                90,
                37.0
        );

        vitalParametersRepository.save(firstVitalParameters);
        vitalParametersRepository.save(secondVitalParameters);

        given()
                .when()
                .get("/vitalparameters/")
                .then()
                .statusCode(200)
                .body("size()", Matchers.is(2));
    }

    /**
     * Test remove all vital parameters
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testRemoveAllVitalParameters() {
        vitalParametersRepository.deleteAll();

        VitalParameters firstVitalParameters = new VitalParameters(
                LocalDateTime.now(),
                80,
                80,
                80,
                80,
                36.6
        );

        VitalParameters secondVitalParameters = new VitalParameters(
                LocalDateTime.now(),
                90,
                90,
                90,
                90,
                37.0
        );

        vitalParametersRepository.save(firstVitalParameters);
        vitalParametersRepository.save(secondVitalParameters);

        given()
                .when()
                .delete("/vitalparameters/")
                .then()
                .statusCode(200);

        given()
                .when()
                .get("/vitalparameters/")
                .then()
                .statusCode(200)
                .body("isEmpty()", Matchers.is(true));
    }

    /**
     * Test get vital parameters with date interval
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testGetVitalParametersWithDateInterval() {
        vitalParametersRepository.deleteAll();

        VitalParameters firstVitalParameters = new VitalParameters(
                LocalDateTime.now().minusDays(1),
                80,
                80,
                80,
                80,
                36.6
        );

        vitalParametersRepository.save(firstVitalParameters);

        DateIntervalDto includedDate = new DateIntervalDto(
                LocalDateTime.now().minusDays(2).toString(),
                LocalDateTime.now().toString()
        );

        given()
                .contentType(ContentType.JSON)
                .body(includedDate)
                .when()
                .post("/vitalparameters/date")
                .then()
                .statusCode(200)
                .body("isEmpty()", Matchers.is(false));
    }

    /**
     * Test get vital parameters with invalid date interval
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testGetVitalParametersWithInvalidDateInterval() {
        DateIntervalDto invalidDateInterval = new DateIntervalDto(
                "2020-01-01T00:00:00",
                "2000-01-03T00:00:00"
        );

        given()
                .contentType(ContentType.JSON)
                .body(invalidDateInterval)
                .when()
                .post("/vitalparameters/date")
                .then()
                .statusCode(400);
    }
}
