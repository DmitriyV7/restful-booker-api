package api.restful.booker.tests;

import static io.restassured.RestAssured.given;

import static api.restful.booker.api.ApiOperations.*;
import static api.restful.booker.api_constants.ApiEndPoints.*;
import api.restful.booker.pojo.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


import java.util.List;

public class RestfulBookerTests {
    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void postCreateTokenTest(){
        Response response = performPostRequestCreateToken(POST_CREATE_TOKEN_ENDPOINT,true);
        Assert.assertNotNull(response);
        String token = response.as(ApiTokenResponse.class).getToken();
        System.out.println(token);
    }

    @Test
    public void getBookingIdsTest(){
        Response response = performGetRequestBookingId(GET_BOOKING_ID_ENDPOINT,false);

        List<Integer> bookingIds = response.jsonPath().getList("bookingid", Integer.class);
        Assert.assertNotNull("Booking IDs list is null", bookingIds);
        Assert.assertFalse("Booking IDs list is empty", bookingIds.isEmpty());
        System.out.println(bookingIds);
        for (Integer bookingId : bookingIds) {
            Assert.assertNotNull("Booking ID is null", bookingId);
            Assert.assertTrue("Booking ID is not a positive number", bookingId > 0);
        }
    }

    @Test
    public void getBookingWithIdNumberTest(){
        Response response = performGetRequestBookingWithPathParam(GET_BOOKING_WITH_ID_NUMBER_ENDPOINT,"25",false);

        BookingDetailsResponse bookingObj = response.as(BookingDetailsResponse.class);
        System.out.println(bookingObj);

        Assert.assertEquals("David", bookingObj.getFirstname());
        Assert.assertEquals("Jane",bookingObj.getLastname());
        Assert.assertEquals(111,bookingObj.getTotalprice());
        Assert.assertEquals(true,bookingObj.isDepositpaid());
        Assert.assertEquals("2018-01-01",bookingObj.getBookingdates().getCheckin());
        Assert.assertEquals("2019-01-01",bookingObj.getBookingdates().getCheckout());
        Assert.assertEquals("Extra pillows please", bookingObj.getAdditionalneeds());
    }

    @Test
    public void postCreateBookingTest(){
        String firstname = "David";
        String lastname = "Lee";
        int totalprice = 111;
        boolean depositpaid = true;
        String checkin = "2018-01-01";
        String checkout = "2019-01-01";
        String additionalneeds = "Always to eat";
        BookingDates bookingDates = new BookingDates(checkin, checkout);
        CBDResponse booking = new CBDResponse(firstname, lastname, totalprice, depositpaid, bookingDates, additionalneeds);

        Response response = performPostRequestCreateBooking(POST_CREATE_BOOKING_ENDPOINT,false,booking);

        CBDResponseMain responseObj = response.as(CBDResponseMain.class);
        extracted(responseObj);
        System.out.println(responseObj.getBookingid());
        String numberId = String.valueOf(responseObj.getBookingid());

        Response response1 = performGetRequestBookingWithPathParam(GET_BOOKING_WITH_ID_NUMBER_ENDPOINT,numberId,true);
        BookingDetailsResponse bookingObj = response1.as(BookingDetailsResponse.class);
        System.out.println(bookingObj);
        Assert.assertEquals("David", bookingObj.getFirstname());
        Assert.assertEquals("Lee", bookingObj.getLastname());
        Assert.assertEquals(111, bookingObj.getTotalprice());
        Assert.assertEquals(true, bookingObj.isDepositpaid());
        Assert.assertEquals("2018-01-01", bookingObj.getBookingdates().getCheckin());
        Assert.assertEquals("2019-01-01", bookingObj.getBookingdates().getCheckout());
        Assert.assertEquals("Always to eat", bookingObj.getAdditionalneeds());

        performDeleteBookingRequest(DELETE_BOOKING_ENDPOINT,numberId,true);

    }

    private static void extracted(CBDResponseMain responseObj) {
        Assert.assertTrue(responseObj.getBookingid()>0);
        Assert.assertEquals("David", responseObj.getBooking().getFirstname());
        Assert.assertEquals("Lee", responseObj.getBooking().getLastname());
        Assert.assertEquals(111, responseObj.getBooking().getTotalprice());
        Assert.assertEquals(true, responseObj.getBooking().isDepositpaid());
        Assert.assertEquals("2018-01-01", responseObj.getBooking().getBookingdates().getCheckin());
        Assert.assertEquals("2019-01-01", responseObj.getBooking().getBookingdates().getCheckout());
        Assert.assertEquals("Always to eat", responseObj.getBooking().getAdditionalneeds());
    }

    @Test
    public void putUpdateBookingTest(){
        String firstname = "David";
        String lastname = "White";
        int totalprice = 348;
        boolean depositpaid = true;
        String checkin = "2018-01-01";
        String checkout = "2019-01-01";
        String additionalneeds = "Always to eat";

        BookingDates bookingDates = new BookingDates(checkin, checkout);
        CBDResponse booking = new CBDResponse(firstname, lastname, totalprice, depositpaid, bookingDates, additionalneeds);

        Response response = performPutRequestUpdateBooking(PUT_UPDATE_ENDPOINT,"25",booking,true);//
        BookingDetailsResponse responseObj = response.as(BookingDetailsResponse.class);
        extracted(responseObj);
        System.out.println(responseObj);

        Response response1 = performGetRequestBookingWithPathParam(GET_BOOKING_WITH_ID_NUMBER_ENDPOINT,"25",true);
        BookingDetailsResponse bookingObj = response1.as(BookingDetailsResponse.class);
        System.out.println(bookingObj);
        extracted(bookingObj);
    }

    private static void extracted(BookingDetailsResponse bookingObj) {
        Assert.assertEquals("David", bookingObj.getFirstname());
        Assert.assertEquals("White", bookingObj.getLastname());
        Assert.assertEquals(348, bookingObj.getTotalprice());
        Assert.assertEquals(true, bookingObj.isDepositpaid());
        Assert.assertEquals("2018-01-01", bookingObj.getBookingdates().getCheckin());
        Assert.assertEquals("2019-01-01", bookingObj.getBookingdates().getCheckout());
        Assert.assertEquals("Always to eat", bookingObj.getAdditionalneeds());
    }

    @Test
    public void patchPartialUpdateBookingTest(){
        String firstname = "David";
        String lastname = "White";
        int totalprice = 111;

        PartialDates booking = new PartialDates(firstname, lastname, totalprice);

        Response response = performPatchPartialRequestUpdateBooking(PATCH_UPDATE_ENDPOINT,"15",booking,true);
        BookingDetailsResponse responseObj = response.as(BookingDetailsResponse.class);
        Assert.assertEquals("David",responseObj.getFirstname());
        Assert.assertEquals("White",responseObj.getLastname());
        Assert.assertEquals(111,responseObj.getTotalprice());

        Response response1 = performGetRequestBookingWithPathParam(GET_BOOKING_WITH_ID_NUMBER_ENDPOINT,"15",true);

        BookingDetailsResponse bookingObj = response1.as(BookingDetailsResponse.class);
        Assert.assertEquals("David", bookingObj.getFirstname());
        Assert.assertEquals("White",bookingObj.getLastname());
        Assert.assertEquals(111,bookingObj.getTotalprice());
        System.out.println(bookingObj);
    }

    @Test
    public void deleteBookingTest(){
        performDeleteBookingRequest(DELETE_BOOKING_ENDPOINT,"11",true);
    }

    @Test
    public void getPingHealthCheckTest(){
    performGetPingHealthCheckRequest(GET_PING_HEALTH_CHECK_ENDPOINT, false);

    }
}



