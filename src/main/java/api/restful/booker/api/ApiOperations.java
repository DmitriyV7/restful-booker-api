package api.restful.booker.api;

import static io.restassured.http.ContentType.*;

import api.restful.booker.pojo.CBDResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public class ApiOperations {
    private ApiOperations() {}


    public static Response performPostRequestCreateToken(String endpoint, boolean requiresAuth){
        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON);
        if (requiresAuth){
            String authPayload = "{\"username\":\"admin\", \"password\":\"password123\"}";
                    requestSpecification = requestSpecification.body(authPayload);
        }
        return requestSpecification
                .when()
                .post(endpoint)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
    public static Response performGetRequestBookingId(String endpoint, boolean requiresAuth){
        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON);
        if (requiresAuth){
            String authPayload = "{\"username\":\"admin\", \"password\":\"password123\"}";
            requestSpecification = requestSpecification.body(authPayload);
        }
        return requestSpecification
                .when()
                .get(endpoint)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
    public static Response performGetRequestBookingWithPathParam(String endpoint,String paramKey,String paramValue, boolean requiresAuth){
        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON);
        if (requiresAuth){
            String authPayload = "{\"username\":\"admin\", \"password\":\"password123\"}";
            requestSpecification = requestSpecification.body(authPayload);
        }
        return requestSpecification
                .pathParams(paramKey,paramValue)
                .when()
                .get(endpoint)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public static Response performPostRequestCreateBooking(String endpoint, boolean requiresAuth, CBDResponse booking){
        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON);
        if (requiresAuth){
            String authPayload = "{\"username\":\"admin\", \"password\":\"password123\"}";
            requestSpecification = requestSpecification.body(authPayload);
        }
        requestSpecification = requestSpecification.body(booking);
        return requestSpecification
                .body(booking)
                .when()
                .post(endpoint)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }


}
