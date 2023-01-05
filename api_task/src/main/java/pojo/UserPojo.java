package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserPojo {

    private int id;
    private String name;
    @JsonProperty("username")
    private String userName;
    private String email;
    private AddressPojo address;
    private String phone;
    private String website;
    private CompanyPojo company;

}
