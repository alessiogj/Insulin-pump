package com.univr.pump.insulinpump;

import com.univr.pump.insulinpump.dto.VitalParametersDto;
import com.univr.pump.insulinpump.dto.DateInterval;
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
        newVitalParameters.setBloodPressure("80");
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
        newVitalParameters.setBloodPressure("-80");
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
        newVitalParameters.setBloodPressure("80");
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
        newVitalParameters.setBloodPressure("80");
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
        newVitalParameters.setBloodPressure("80");
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
        firstVitalParameters.setBloodPressure("80");
        firstVitalParameters.setHeartRate("80");
        firstVitalParameters.setBloodSugarLevel("80");
        firstVitalParameters.setTemperature("36.6");

        VitalParametersDto secondVitalParameters = new VitalParametersDto();
        secondVitalParameters.setBloodPressure("90");
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
                .body("bloodPressure", Matchers.equalTo(secondVitalParameters.getBloodPressure()))
                .body("heartRate", Matchers.equalTo(secondVitalParameters.getHeartRate()))
                .body("bloodSugarLevel", Matchers.equalTo(secondVitalParameters.getBloodSugarLevel()))
                .body("temperature", Matchers.equalTo(secondVitalParameters.getTemperature()));
    }

    /**
     * Test get vital parameters with date interval
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getVitalParametersWithDateIntervalTest() {

        VitalParametersDto firstVitalParameters = new VitalParametersDto();
        firstVitalParameters.setBloodPressure("80");
        firstVitalParameters.setHeartRate("80");
        firstVitalParameters.setBloodSugarLevel("80");
        firstVitalParameters.setTemperature("36.6");

        VitalParametersDto secondVitalParameters = new VitalParametersDto();
        secondVitalParameters.setBloodPressure("90");
        secondVitalParameters.setHeartRate("90");
        secondVitalParameters.setBloodSugarLevel("90");
        secondVitalParameters.setTemperature("37.0");

        DateInterval notIncludedDate = new DateInterval(
                "2000-01-01T00:00:00",
                "2000-01-03T00:00:00"
        );

        DateInterval includedDate = new DateInterval(
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

        given()
                .contentType(ContentType.JSON)
                .body(notIncludedDate)
                .when()
                .get("/vitalparameters/searchbytimeinterval")
                .then()
                .statusCode(200)
                .body(Matchers.empty());

        given()
                .contentType(ContentType.JSON)
                .body(includedDate)
                .when()
                .get("/vitalparameters/searchbytimeinterval")
                .then()
                .statusCode(200)
                .body(Matchers.not(Matchers.empty()));
    }
}
