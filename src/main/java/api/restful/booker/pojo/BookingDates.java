package api.restful.booker.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class   BookingDates {
    private String checkin;
    private String checkout;
}
