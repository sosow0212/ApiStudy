package self.study.entity.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ControllerMessage {
    private Object data;
    private String message;
    private HttpStatus httpStatus;

    public ControllerMessage(Object data, String message, HttpStatus httpStatus) {
        this.data = data;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
