package api.restful.booker.tests;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import pojo.BookingDetailsResponse;

import java.util.List;

public class RestfulBookerTests {
    private static final String BASE_URL = "https://restful-booker.herokuapp.com";
    @Test
    public void getBookingIds(){
        given()
                .when()
                .get(BASE_URL+"/booking")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("", Matchers.instanceOf(List.class));
    }

    @Test
    public void getBookingWithIdNumber(){
        Response response = given()
                .when()
                .get(BASE_URL + "/booking/30")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .response();
        BookingDetailsResponse bookingObj = response.as(BookingDetailsResponse.class);
        System.out.println(bookingObj);

        Assert.assertEquals("John", bookingObj.getFirstname());
        Assert.assertEquals("Smith",bookingObj.getLastname());
        Assert.assertEquals(111,bookingObj.getTotalprice());
        Assert.assertEquals(true,bookingObj.isDepositpaid());
        Assert.assertEquals("BookingDates(checkin=2018-01-01, checkout=2019-01-01)",bookingObj.getBookingdates());
        Assert.assertEquals("Breakfast", bookingObj.getAdditionalneeds());
    }

}
