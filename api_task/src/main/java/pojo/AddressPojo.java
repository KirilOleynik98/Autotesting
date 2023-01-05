package pojo;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressPojo {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoPojo geo;
}
