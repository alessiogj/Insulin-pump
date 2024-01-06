package com.univr.pump.insulinpump;

import com.univr.pump.insulinpump.dto.VitalParametersDto;
import com.univr.pump.insulinpump.dto.DateIntervalDto;
import com.univr.pump.insulinpump.model.VitalParameters;
import com.univr.pump.insulinpump.repository.VitalParametersRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsulinPumpApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class InsulinPumpApplicationTests {

    @Autowired
    private VitalParametersRepository vitalParametersRepository;

    @BeforeClass
    public static void setBaseUri() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    /**
     * Test get vital parameters empty list
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getVitalParametersTest() {
        given()
                .when()
                .get("/vitalparameters/")
        .then()
                .statusCode(200)
                .body(Matchers.not(Matchers.empty()));
    }

    /**
     * Test get vital parameters not empty list
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getVitalParametersNotEmptyTest() {

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
                .body(Matchers.not(Matchers.empty()));
    }

    /**
     * Test get last vital parameter
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getLastVitalParameterTest() {

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
                .get("/vitalparameters/last")
                .then()
                .statusCode(200)
                .body("bloodPressureSystolic", Matchers.equalTo("90"))
                .body("bloodPressureDiastolic", Matchers.equalTo("90"))
                .body("heartRate", Matchers.equalTo("90"))
                .body("bloodSugarLevel", Matchers.equalTo("90"))
                .body("temperature", Matchers.equalTo("37.0"));
    }

    /**
     * Test get vital parameters with date interval
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getVitalParametersWithDateIntervalTest() {

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

        DateIntervalDto notIncludedDate = new DateIntervalDto(
                "2000-01-01T00:00:00",
                "2000-01-03T00:10:00"
        );

        DateIntervalDto includedDate = new DateIntervalDto(
                "2020-01-01T00:00:00",
                "2030-01-03T00:00:00"
        );

        //il valore di ritorno è un array vuoto
        given()
                .contentType(ContentType.JSON)
                .body(notIncludedDate)
        .when()
                .post("/vitalparameters/date")
        .then()
                .statusCode(200)
                .body("isEmpty()", Matchers.is(true));

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
    public void getVitalParametersWithInvalidDateIntervalTest() {

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
