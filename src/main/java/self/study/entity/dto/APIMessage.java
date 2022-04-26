package self.study.entity.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class APIMessage {
    private HttpStatus httpStatus;
    private String message;
    private Object data;


    public APIMessage(HttpStatus httpStatus, String message, Object data) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.data = data;
    }

}