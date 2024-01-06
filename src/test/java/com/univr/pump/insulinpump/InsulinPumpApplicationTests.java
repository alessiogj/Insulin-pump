package com.univr.pump.insulinpump;

import com.univr.pump.insulinpump.dto.VitalParametersDto;
import com.univr.pump.insulinpump.dto.DateIntervalDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsulinPumpApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class InsulinPumpApplicationTests {


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
     * Test the creation of a vital parameter
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createVitalParameterTest() {

        VitalParametersDto newVitalParameters = new VitalParametersDto();
        newVitalParameters.setBloodPressureSystolic("80");
        newVitalParameters.setBloodPressureDiastolic("80");
        newVitalParameters.setHeartRate("80");
        newVitalParameters.setBloodSugarLevel("80");
        newVitalParameters.setTemperature("36.6");

        given()
                .contentType(ContentType.JSON)
                .body(newVitalParameters)
        .when()
                .post("/vitalparameters/")
                .then()
                .statusCode(200);

        given()
                .when()
                .get("/vitalparameters/")
        .then()
                .statusCode(200)
                .body(Matchers.not(Matchers.empty()));
    }

    /**
     * Test the creation of a vital parameter with invalid blood pressure
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createVitalParameterInvalidBloodPressureTest() {

        VitalParametersDto newVitalParameters = new VitalParametersDto();
        newVitalParameters.setBloodPressureSystolic("80");
        newVitalParameters.setBloodPressureDiastolic("-80");
        newVitalParameters.setHeartRate("80");
        newVitalParameters.setBloodSugarLevel("80");
        newVitalParameters.setTemperature("36.6");

        given()
                .contentType(ContentType.JSON)
                .body(newVitalParameters)
                .when()
                .post("/vitalparameters/")
                .then()
                .statusCode(400);
    }

    /**
     * Test the creation of a vital parameter with invalid heart rate
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createVitalParameterInvalidHeartRateTest() {

        VitalParametersDto newVitalParameters = new VitalParametersDto();
        newVitalParameters.setBloodPressureSystolic("80");
        newVitalParameters.setBloodPressureDiastolic("80");
        newVitalParameters.setHeartRate("-80");
        newVitalParameters.setBloodSugarLevel("80");
        newVitalParameters.setTemperature("36.6");

        given()
                .contentType(ContentType.JSON)
                .body(newVitalParameters)
                .when()
                .post("/vitalparameters/")
                .then()
                .statusCode(400);
    }

    /**
     * Test the creation of a vital parameter with invalid blood sugar level
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createVitalParameterInvalidBloodSugarLevelTest() {

        VitalParametersDto newVitalParameters = new VitalParametersDto();
        newVitalParameters.setBloodPressureSystolic("80");
        newVitalParameters.setBloodPressureDiastolic("80");
        newVitalParameters.setHeartRate("80");
        newVitalParameters.setBloodSugarLevel("-80");
        newVitalParameters.setTemperature("36.6");


        given()
                .contentType(ContentType.JSON)
                .body(newVitalParameters)
                .when()
                .post("/vitalparameters/")
                .then()
                .statusCode(400);
    }

    /**
     * Test the creation of a vital parameter with invalid temperature
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createVitalParameterInvalidTemperatureTest() {

        VitalParametersDto newVitalParameters = new VitalParametersDto();
        newVitalParameters.setBloodPressureSystolic("80");
        newVitalParameters.setBloodPressureDiastolic("80");
        newVitalParameters.setHeartRate("80");
        newVitalParameters.setBloodSugarLevel("80");
        newVitalParameters.setTemperature("-36.6");

        given()
                .contentType(ContentType.JSON)
                .body(newVitalParameters)
                .when()
                .post("/vitalparameters/")
                .then()
                .statusCode(400);
    }

    /**
     * Test get last vital parameter
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getLastVitalParameterTest() {

        VitalParametersDto firstVitalParameters = new VitalParametersDto();
        firstVitalParameters.setBloodPressureSystolic("80");
        firstVitalParameters.setBloodPressureDiastolic("80");
        firstVitalParameters.setHeartRate("80");
        firstVitalParameters.setBloodSugarLevel("80");
        firstVitalParameters.setTemperature("36.6");

        VitalParametersDto secondVitalParameters = new VitalParametersDto();
        secondVitalParameters.setBloodPressureSystolic("90");
        secondVitalParameters.setBloodPressureDiastolic("90");
        secondVitalParameters.setHeartRate("90");
        secondVitalParameters.setBloodSugarLevel("90");
        secondVitalParameters.setTemperature("37.0");

        given()
                .contentType(ContentType.JSON)
                .body(firstVitalParameters)
                .when()
                .post("/vitalparameters/")
                .then()
                .statusCode(200);

        given()
                .contentType(ContentType.JSON)
                .body(secondVitalParameters)
                .when()
                .post("/vitalparameters/")
                .then()
                .statusCode(200);

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

        VitalParametersDto firstVitalParameters = new VitalParametersDto();
        firstVitalParameters.setBloodPressureSystolic("80");
        firstVitalParameters.setBloodPressureDiastolic("80");
        firstVitalParameters.setHeartRate("80");
        firstVitalParameters.setBloodSugarLevel("80");
        firstVitalParameters.setTemperature("36.6");

        VitalParametersDto secondVitalParameters = new VitalParametersDto();
        secondVitalParameters.setBloodPressureSystolic("90");
        secondVitalParameters.setBloodPressureDiastolic("90");
        secondVitalParameters.setHeartRate("90");
        secondVitalParameters.setBloodSugarLevel("90");
        secondVitalParameters.setTemperature("37.0");

        DateIntervalDto notIncludedDate = new DateIntervalDto(
                "2000-01-01T00:00:00",
                "2000-01-03T00:10:00"
        );

        DateIntervalDto includedDate = new DateIntervalDto(
                "2020-01-01T00:00:00",
                "2030-01-03T00:00:00"
        );

        given()
                .contentType(ContentType.JSON)
                .body(firstVitalParameters)
        .when()
                .post("/vitalparameters/")
        .then()
                .statusCode(200);

        given()
                .contentType(ContentType.JSON)
                .body(secondVitalParameters)
        .when()
                .post("/vitalparameters/")
        .then()
                .statusCode(200);

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
}
