package api.restful.booker.pojo;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDetailsResponse {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;
}
