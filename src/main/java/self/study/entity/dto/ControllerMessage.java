package self.study.entity.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ControllerMessage {
    private HttpStatus status;
    private String message;
    private Object data;

    public ControllerMessage(HttpStatus httpStatus, String message, Object data) {
        this.status = httpStatus;
        this.message = message;
        this.data = data;
    }

    public ControllerMessage(HttpStatus httpStatus, String message) {
        this.status = httpStatus;
        this.message = message;
    }

    public ControllerMessage(String message, Object data) {
        this.message = message;
        this.data = data;
    }

}
