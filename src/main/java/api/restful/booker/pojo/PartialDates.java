package api.restful.booker.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartialDates {
    private String firstname;
    private String lastname;
    private int totalprice;
}
