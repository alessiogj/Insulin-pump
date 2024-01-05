package com.univr.pump.insulinpump;

import com.univr.pump.insulinpump.dto.PatientDto;
import com.univr.pump.insulinpump.dto.VitalParametersDto;
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
     * Test the empty list of patients
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getPatientsTest() {
        given()
                .when()
                .get("/patient/")
        .then()
                .statusCode(200)
                .body(Matchers.not(Matchers.empty()));
    }

    /**
     * Test the creation of a patient
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createPatientTest() {
        PatientDto newPatient = new PatientDto();
        newPatient.setName("Mario");
        newPatient.setSurname("Rossi");
        newPatient.setFiscalCode("RSSMRA00A00A000A");
        newPatient.setDiabetesType("TYPE_1");
        newPatient.setBirthDate("1988-05-05");
        newPatient.setHeight("1.80");
        newPatient.setWeight("80.0");

        given()
                .contentType(ContentType.JSON)
                .body(newPatient)
        .when()
                .post("/patient/")
                .then()
                .statusCode(200);

        given()
                .when()
                .get("/patient/")
        .then()
                .statusCode(200)
                .body(Matchers.not(Matchers.empty()));
    }

    /**
     * Test the creation of a patient with invalid fiscal code
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createPatientInvalidFiscalCodeTest() {
        PatientDto newPatient = new PatientDto();
        newPatient.setName("Mario");
        newPatient.setSurname("Rossi");
        newPatient.setFiscalCode("");
        newPatient.setDiabetesType("TYPE_1");
        newPatient.setBirthDate("1988-05-05");
        newPatient.setHeight("1.80");
        newPatient.setWeight("80.0");

        //bad request
        given()
                .contentType(ContentType.JSON)
                .body(newPatient)
        .when()
                .post("/patient/")
                .then()
                .statusCode(400);
    }

    /**
     * Test the creation of a patient with invalid diabetes type
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createPatientInvalidDiabetesTypeTest() {
        PatientDto newPatient = new PatientDto();
        newPatient.setName("Mario");
        newPatient.setSurname("Rossi");
        newPatient.setFiscalCode("RSSMRA00A00A000A");
        newPatient.setDiabetesType("TYPE_?");
        newPatient.setBirthDate("1988-05-05");
        newPatient.setHeight("1.80");
        newPatient.setWeight("80.0");

        //bad request
        given()
                .contentType(ContentType.JSON)
                .body(newPatient)
        .when()
                .post("/patient/")
                .then()
                .statusCode(400);
    }

    /**
     * Test the creation of a patient with invalid weight
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createPatientInvalidWeightTest() {
        PatientDto newPatient = new PatientDto();
        newPatient.setName("Mario");
        newPatient.setSurname("Rossi");
        newPatient.setFiscalCode("RSSMRA00A00A000A");
        newPatient.setDiabetesType("TYPE_1");
        newPatient.setBirthDate("1988-05-05");
        newPatient.setHeight("1.80");
        newPatient.setWeight("-80.0");

        //bad request
        given()
                .contentType(ContentType.JSON)
                .body(newPatient)
        .when()
                .post("/patient/")
                .then()
                .statusCode(400);
    }

    /**
     * Test the creation of a patient with invalid height
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createPatientInvalidHeightTest() {
        PatientDto newPatient = new PatientDto();
        newPatient.setName("Mario");
        newPatient.setSurname("Rossi");
        newPatient.setFiscalCode("RSSMRA00A00A000A");
        newPatient.setDiabetesType("TYPE_1");
        newPatient.setBirthDate("1988-05-05");
        newPatient.setHeight("-1.80");
        newPatient.setWeight("80.0");

        //bad request
        given()
                .contentType(ContentType.JSON)
                .body(newPatient)
        .when()
                .post("/patient/")
                .then()
                .statusCode(400);
    }

    /**
     * Test the creation of a patient with invalid birth date
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createPatientInvalidBirthDateTest() {
        PatientDto newPatient = new PatientDto();
        newPatient.setName("Mario");
        newPatient.setSurname("Rossi");
        newPatient.setFiscalCode("RSSMRA00A00A000A");
        newPatient.setDiabetesType("TYPE_1");
        newPatient.setBirthDate("ddd");
        newPatient.setHeight("1.80");
        newPatient.setWeight("80.0");

        //bad request
        given()
                .contentType(ContentType.JSON)
                .body(newPatient)
        .when()
                .post("/patient/")
                .then()
                .statusCode(400);
    }

    /**
     * Test the creation of a patient with invalid name
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createPatientInvalidNameTest() {
        PatientDto newPatient = new PatientDto();
        newPatient.setName("");
        newPatient.setSurname("Rossi");
        newPatient.setFiscalCode("RSSMRA00A00A000A");
        newPatient.setDiabetesType("TYPE_1");
        newPatient.setBirthDate("1988-05-05");
        newPatient.setHeight("1.80");
        newPatient.setWeight("80.0");

        //bad request
        given()
                .contentType(ContentType.JSON)
                .body(newPatient)
        .when()
                .post("/patient/")
                .then()
                .statusCode(400);
    }

    /**
     * Test the creation of a patient with invalid surname
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createPatientInvalidSurnameTest() {
        PatientDto newPatient = new PatientDto();
        newPatient.setName("Mario");
        newPatient.setSurname("");
        newPatient.setFiscalCode("RSSMRA00A00A000A");
        newPatient.setDiabetesType("TYPE_1");
        newPatient.setBirthDate("1988-05-05");
        newPatient.setHeight("1.80");
        newPatient.setWeight("80.0");

        //bad request
        given()
                .contentType(ContentType.JSON)
                .body(newPatient)
        .when()
                .post("/patient/")
                .then()
                .statusCode(400);
    }

    /**
     * Test the update of a patient
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void updatePatientTest() {
        PatientDto newPatient = new PatientDto();
        newPatient.setName("Mario");
        newPatient.setSurname("Rossi");
        newPatient.setFiscalCode("RSSMRA00A00A000A");
        newPatient.setDiabetesType("TYPE_1");
        newPatient.setBirthDate("1988-05-05");
        newPatient.setHeight("1.80");
        newPatient.setWeight("80.0");

        PatientDto updatedPatient = new PatientDto();
        updatedPatient.setName("Mario");
        updatedPatient.setSurname("Verdi");
        updatedPatient.setFiscalCode("RSSMRA00A00A000A");
        updatedPatient.setDiabetesType("TYPE_1");
        updatedPatient.setBirthDate("1988-05-05");
        updatedPatient.setHeight("1.80");
        updatedPatient.setWeight("80.0");

        int createdPatientId = given()
                .contentType(ContentType.JSON)
                .body(newPatient)
        .when()
                .post("/patient/")
        .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getInt("id");

        given()
                .contentType(ContentType.JSON)
                .body(updatedPatient)
        .when()
                .put("/patient/" + createdPatientId)
                .then()
                .statusCode(200);

    }

    /**
     * Test the deletion of a patient
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void deletePatientTest() {
        PatientDto newPatient = new PatientDto();
        newPatient.setName("Mario");
        newPatient.setSurname("Rossi");
        newPatient.setFiscalCode("RSSMRA00A00A000A");
        newPatient.setDiabetesType("TYPE_1");
        newPatient.setBirthDate("1988-05-05");
        newPatient.setHeight("1.80");
        newPatient.setWeight("80.0");

        int createdPatientId = given()
                .contentType(ContentType.JSON)
                .body(newPatient)
        .when()
                .post("/patient/")
        .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getInt("id");

        given()
                .when()
                .delete("/patient/" + createdPatientId)
        .then()
                .statusCode(200);

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
        PatientDto newPatient = new PatientDto();
        newPatient.setName("Mario");
        newPatient.setSurname("Rossi");
        newPatient.setFiscalCode("RSSMRA01A00A000A");
        newPatient.setDiabetesType("TYPE_1");
        newPatient.setBirthDate("1988-05-05");
        newPatient.setHeight("1.70");
        newPatient.setWeight("70.0");

        VitalParametersDto newVitalParameters = new VitalParametersDto();
        newVitalParameters.setTimestamp("2020-05-05");
        newVitalParameters.setBloodPressure("80");
        newVitalParameters.setHeartRate("80");
        newVitalParameters.setBloodSugarLevel("80");
        newVitalParameters.setTemperature("36.6");


        int createdPatientId = given()
                .contentType(ContentType.JSON)
                .body(newPatient)
        .when()
                .post("/patient/")
        .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getInt("id");

        newVitalParameters.setPatientId(String.valueOf(createdPatientId));

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
     * Test the creation of a vital parameter with invalid patient id
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createVitalParameterInvalidPatientIdTest() {
        VitalParametersDto newVitalParameters = new VitalParametersDto();
        newVitalParameters.setTimestamp("2020-05-05");
        newVitalParameters.setBloodPressure("80");
        newVitalParameters.setHeartRate("80");
        newVitalParameters.setBloodSugarLevel("80");
        newVitalParameters.setTemperature("36.6");

        newVitalParameters.setPatientId("0");

        given()
                .contentType(ContentType.JSON)
                .body(newVitalParameters)
        .when()
                .post("/vitalparameters/")
                .then()
                .statusCode(400);
    }

    /**
     * Test the creation of a vital parameter with invalid blood pressure
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createVitalParameterInvalidBloodPressureTest() {
        PatientDto newPatient = new PatientDto();
        newPatient.setName("Mario");
        newPatient.setSurname("Rossi");
        newPatient.setFiscalCode("RSSMRA01A00A000A");
        newPatient.setDiabetesType("TYPE_1");
        newPatient.setBirthDate("1988-05-05");
        newPatient.setHeight("1.70");
        newPatient.setWeight("70.0");

        VitalParametersDto newVitalParameters = new VitalParametersDto();
        newVitalParameters.setTimestamp("2020-05-05");
        newVitalParameters.setBloodPressure("-80");
        newVitalParameters.setHeartRate("80");
        newVitalParameters.setBloodSugarLevel("80");
        newVitalParameters.setTemperature("36.6");

        int createdPatientId = given()
                .contentType(ContentType.JSON)
                .body(newPatient)
                .when()
                .post("/patient/")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getInt("id");

        newVitalParameters.setPatientId(String.valueOf(createdPatientId));

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
        PatientDto newPatient = new PatientDto();
        newPatient.setName("Mario");
        newPatient.setSurname("Rossi");
        newPatient.setFiscalCode("RSSMRA01A00A000A");
        newPatient.setDiabetesType("TYPE_1");
        newPatient.setBirthDate("1988-05-05");
        newPatient.setHeight("1.70");
        newPatient.setWeight("70.0");

        VitalParametersDto newVitalParameters = new VitalParametersDto();
        newVitalParameters.setTimestamp("2020-05-05");
        newVitalParameters.setBloodPressure("80");
        newVitalParameters.setHeartRate("-80");
        newVitalParameters.setBloodSugarLevel("80");
        newVitalParameters.setTemperature("36.6");

        int createdPatientId = given()
                .contentType(ContentType.JSON)
                .body(newPatient)
                .when()
                .post("/patient/")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getInt("id");

        newVitalParameters.setPatientId(String.valueOf(createdPatientId));

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
        PatientDto newPatient = new PatientDto();
        newPatient.setName("Mario");
        newPatient.setSurname("Rossi");
        newPatient.setFiscalCode("RSSMRA01A00A000A");
        newPatient.setDiabetesType("TYPE_1");
        newPatient.setBirthDate("1988-05-05");
        newPatient.setHeight("1.70");
        newPatient.setWeight("70.0");

        VitalParametersDto newVitalParameters = new VitalParametersDto();
        newVitalParameters.setTimestamp("2020-05-05");
        newVitalParameters.setBloodPressure("80");
        newVitalParameters.setHeartRate("80");
        newVitalParameters.setBloodSugarLevel("-80");
        newVitalParameters.setTemperature("36.6");

        int createdPatientId = given()
                .contentType(ContentType.JSON)
                .body(newPatient)
                .when()
                .post("/patient/")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getInt("id");

        newVitalParameters.setPatientId(String.valueOf(createdPatientId));

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
        PatientDto newPatient = new PatientDto();
        newPatient.setName("Mario");
        newPatient.setSurname("Rossi");
        newPatient.setFiscalCode("RSSMRA01A00A000A");
        newPatient.setDiabetesType("TYPE_1");
        newPatient.setBirthDate("1988-05-05");
        newPatient.setHeight("1.70");
        newPatient.setWeight("70.0");

        VitalParametersDto newVitalParameters = new VitalParametersDto();
        newVitalParameters.setTimestamp("2020-05-05");
        newVitalParameters.setBloodPressure("80");
        newVitalParameters.setHeartRate("80");
        newVitalParameters.setBloodSugarLevel("80");
        newVitalParameters.setTemperature("-36.6");

        int createdPatientId = given()
                .contentType(ContentType.JSON)
                .body(newPatient)
                .when()
                .post("/patient/")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getInt("id");

        newVitalParameters.setPatientId(String.valueOf(createdPatientId));

        given()
                .contentType(ContentType.JSON)
                .body(newVitalParameters)
                .when()
                .post("/vitalparameters/")
                .then()
                .statusCode(400);
    }

    /**
     * Test the creation of a vital parameter with invalid timestamp
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createVitalParameterInvalidTimestampTest() {
        PatientDto newPatient = new PatientDto();
        newPatient.setName("Mario");
        newPatient.setSurname("Rossi");
        newPatient.setFiscalCode("RSSMRA01A00A000A");
        newPatient.setDiabetesType("TYPE_1");
        newPatient.setBirthDate("1988-05-05");
        newPatient.setHeight("1.70");
        newPatient.setWeight("70.0");

        VitalParametersDto newVitalParameters = new VitalParametersDto();
        newVitalParameters.setTimestamp("ddd");
        newVitalParameters.setBloodPressure("80");
        newVitalParameters.setHeartRate("80");
        newVitalParameters.setBloodSugarLevel("80");
        newVitalParameters.setTemperature("36.6");

        int createdPatientId = given()
                .contentType(ContentType.JSON)
                .body(newPatient)
                .when()
                .post("/patient/")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getInt("id");

        newVitalParameters.setPatientId(String.valueOf(createdPatientId));

        given()
                .contentType(ContentType.JSON)
                .body(newVitalParameters)
                .when()
                .post("/vitalparameters/")
                .then()
                .statusCode(400);
    }

    /**
     * Test the creation of a vital parameter with invalid patient id
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createVitalParameterInvalidPatientId2Test() {
        VitalParametersDto newVitalParameters = new VitalParametersDto();
        newVitalParameters.setTimestamp("2020-05-05");
        newVitalParameters.setBloodPressure("80");
        newVitalParameters.setHeartRate("80");
        newVitalParameters.setBloodSugarLevel("80");
        newVitalParameters.setTemperature("36.6");

        newVitalParameters.setPatientId("0");

        given()
                .contentType(ContentType.JSON)
                .body(newVitalParameters)
                .when()
                .post("/vitalparameters/")
                .then()
                .statusCode(400);
    }
}
