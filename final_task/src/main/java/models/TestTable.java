package models;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TestTable {

    private String name;
    private String method;
    private String status;
    private String startTime;
    private String endTime;
    private String duration;
}
