package api.restful.booker.api_constants;

public class ApiEndPoints {
    public static final String BASE_URL = "https://restful-booker.herokuapp.com";
    public static final String POST_CREATE_TOKEN_ENDPOINT = BASE_URL + "/auth";
    public static final String GET_BOOKING_ID_ENDPOINT = BASE_URL + "/booking";
    public static final String GET_BOOKING_WITH_ID_NUMBER_ENDPOINT = GET_BOOKING_ID_ENDPOINT + "/{bookingId}";
    public static final String GET_BOOKING_WITH_ID_BOOKING_ENDPOINT = GET_BOOKING_ID_ENDPOINT +"/{numberId}";
    public static final String POST_CREATE_BOOKING_ENDPOINT = BASE_URL + "/booking";



}
