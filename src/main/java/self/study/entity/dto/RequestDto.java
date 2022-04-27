package self.study.entity.dto;

import lombok.Data;
import lombok.Getter;
import self.study.entity.Request;

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
