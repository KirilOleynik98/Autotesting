package pojo;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostPojo {

    private int userId;
    private int id;
    private String title;
    private String body;
}
