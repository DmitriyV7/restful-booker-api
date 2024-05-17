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
    public static Response performGetRequestBookingWithPathParam(String endpoint,String paramValue, boolean requiresAuth){
        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON);
        if (requiresAuth){
            String authPayload = "{\"username\":\"admin\", \"password\":\"password123\"}";
            requestSpecification = requestSpecification.body(authPayload);
        }
        return requestSpecification
                .pathParams("id",paramValue)
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

    public static Response performPutRequestUpdateBooking(String endpoint,String paramValue, CBDResponse booking,boolean requiresAuth){
        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON);
        if (requiresAuth){
            String authPayload = "{\"username\":\"admin\", \"password\":\"password123\"}";
            requestSpecification = requestSpecification.body(authPayload);
        }
        requestSpecification = requestSpecification.body(booking);
        return requestSpecification
                .pathParam("id",paramValue)
                .body(booking)
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .put(endpoint)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public static Response performPatchPartialRequestUpdateBooking(String endpoint,String ParamValue,CBDResponse booking,boolean requiresAuth){
        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON);
        if (requiresAuth){
            String authPayload = "{\"username\":\"admin\", \"password\":\"password123\"}";
            requestSpecification = requestSpecification.body(authPayload);
        }
        requestSpecification = requestSpecification.body(booking);
        return requestSpecification
                .pathParam("id",ParamValue)
                .cookie("token","token=f37095bc3c1b793")
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .body(booking)
                .when()
                .patch(endpoint)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public static Response performDeleteBookingRequest(String endpoint,String ParamValue, boolean requiresAuth){
        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON);
        if (requiresAuth){
            String authPayload = "{\"username\":\"admin\", \"password\":\"password123\"}";
            requestSpecification = requestSpecification.body(authPayload);
        }
        ;
        return requestSpecification
                .pathParam("id",ParamValue)
                .cookie("token","token=f37095bc3c1b793")
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .delete(endpoint)
                .then()
                .statusCode(201)
                .extract()
                .response();
    }

    public static Response performGetPingHealthCheckRequest(String endpoint, boolean requiresAuth){
        RequestSpecification requestSpecification = given()
                .contentType(ContentType.JSON);
        if (requiresAuth){
            String authPayload = "{\"username\":\"admin\", \"password\":\"password123\"}";
            requestSpecification = requestSpecification.body(authPayload);
        }
        ;
        return requestSpecification

                .when()
                .get(endpoint)
                .then()
                .statusCode(201)
                .extract()
                .response();

    }
}
