package za.co.loans;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RestAssuredTest {

    //This test is to Test all the errors in loan application and required input request parameters are passed to fetch errors
    @Test
    public void restAssuredTestFeatureIDNumber() {
        ArrayList<String> errors = (ArrayList<String>) given().contentType(ContentType.JSON)
                .body("{\"bankAccount\": {\"accountNumber\": 123456789,\"bankName\": \"SCRUM_BANK\"},\"idNumber\": 2201025391080,\"name\": \"Donald1\",\"surname\": \"Duck2\"}")
                .when()
                .post("/loans")
                .then()
                .extract().path("errors");
        if(errors != null && !errors.isEmpty()) {
            for (String err : errors)
                System.out.println("LoanApplication Test Response RestAssured::::::::::::::" + err);
        }
    }

    //This test is to Test all the warnings in loan application and required input request parameters is passed to validate Bank Name - MOLEWA_BANK
    @Test
    public void restAssuredTestFeatureBank() {
        ArrayList<String> warnings = (ArrayList<String>) given().contentType(ContentType.JSON)
                .body("{\"bankAccount\": {\"accountNumber\": 1234567890,\"bankName\": \"MOLEWA_BANK\"},\"idNumber\": 9901025391080,\"name\": \"Donald\",\"surname\": \"Duck\"}")
                .when()
                .post("/loans")
                .then()
                .extract().path("warnings");
        if(warnings != null && !warnings.isEmpty()) {
            for (String war : warnings)
                System.out.println("LoanApplication Test Response RestAssured warning for Molewa Bank::::::::::::::" + war);
        }
    }
}
