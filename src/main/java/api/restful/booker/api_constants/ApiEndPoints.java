package api.restful.booker.api_constants;

public class ApiEndPoints {
    public static final String BASE_URL = "https://restful-booker.herokuapp.com";
    public static final String POST_CREATE_TOKEN_ENDPOINT = BASE_URL + "/auth";
    public static final String GET_BOOKING_ID_ENDPOINT = BASE_URL + "/booking";
    public static final String GET_BOOKING_WITH_ID_NUMBER_ENDPOINT = BASE_URL + "/booking/{id}";
    public static final String POST_CREATE_BOOKING_ENDPOINT = BASE_URL + "/booking";
    public static final String PUT_UPDATE_ENDPOINT = BASE_URL + "/booking/{id}";
    public static final String PATCH_UPDATE_ENDPOINT = BASE_URL + "/booking/{id}";
    public static final String DELETE_BOOKING_ENDPOINT = BASE_URL + "/booking/{id}";
    public static final String GET_PING_HEALTH_CHECK_ENDPOINT = BASE_URL+"/ping";
}
