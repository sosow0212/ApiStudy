package self.study.entity.v1.dto;

import lombok.Data;
import self.study.entity.v1.Request;

@Data

public class RequestDto {
    private final String name;
    private final String username;
    private final int age;


    public Request toEntity() {
        return Request.builder()
                .name(name)
                .username(username)
                .age(age)
                .build();
    }
}
